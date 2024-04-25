import gymnasium as gym
from q_learning import get_random_action, get_best_action, get_action, \
    random_q_table, calculate_new_q_value


'''
    Не дава најдобро решение на проблемот. 
'''
if __name__ == '__main__':
    env = gym.make('Taxi-v3', render_mode='ansi')

    num_states = env.observation_space.n
    num_actions = env.action_space.n

    num_steps_per_episode = 8

    dfs = [0.5, 0.9]
    lrs = [0.1, 0.01]
    episode_values = [100000]
    iterations = 50

    trained_q_tables = {}

    for episode_value in episode_values:
        for df in dfs:
            for lr in lrs:
                q_table = random_q_table(-1, 0, (num_states, num_actions))

                for episode in range(episode_value):
                    state, _ = env.reset()
                    terminated = False
                    # for step in range(num_steps_per_episode):
                    print(episode)
                    while not terminated:
                        action = get_best_action(q_table, state)

                        new_state, reward, terminated, _, _ = env.step(action)

                        new_q = calculate_new_q_value(q_table,
                                                      state, new_state,
                                                      action, reward,
                                                      lr, df)

                        q_table[state, action] = new_q

                        state = new_state

                trained_q_tables[f"{episode_value},{df},{lr}"] = q_table

    best_reward = float("-inf")
    best_steps = float("-inf")
    best_q_table = None
    total_steps = 0
    for key in trained_q_tables.keys():
        q_table = trained_q_tables[key]
        total_reward = 0
        for i in range(100):
            state, _ = env.reset()
            terminated = False
            total_steps = 0
            print(i)
            while not terminated and total_steps < 100000:
                action = get_best_action(q_table, state)

                new_state, reward, terminated, _, _ = env.step(action)
                state = new_state
                total_reward += reward
                total_steps += 1

        if total_reward/100 > best_reward:
            best_reward = total_reward/100
            best_q_table = key
            total_steps /= 100


    splits = best_q_table.split(",")
    episodes = splits[0]
    discount = splits[1]
    learning_rate = splits[2]

    print(f"The best average reward was: {best_reward}.")
    print(f"The best q-table was gotten from {episodes} episodes, {discount} discount factor and {learning_rate}"
          f" learning rate")
    print(f"Termination was reached in {total_steps} steps on average with this table")
    print()
    print("Stand by for epsilon-decay")

    q_table = trained_q_tables[best_q_table]
    total_reward = 0
    total_steps = 0
    best_reward_epsilon = float('-inf')

    initial_epsilon = 1.0
    min_epsilon = 0.01
    decay_rate = 0.85
    for _ in range(100):
        state, _ = env.reset()
        terminated = False
        epsilon = initial_epsilon
        while not terminated:
            action = get_action(env, q_table, state, epsilon)

            new_state, reward, terminated, _, _ = env.step(action)
            state = new_state
            total_reward += reward
            total_steps += 1

            epsilon = max(initial_epsilon, epsilon * decay_rate)

    best_reward_epsilon = total_reward/100
    total_steps /= 100

    print(f"The same q-table but with epsilon-greedy policy gave us an average reward of: {best_reward_epsilon}")
    print(f"Termination was reached after {total_steps} steps on average")

    env = gym.make('Taxi-v3', render_mode='human')
    state, _ = env.reset()
    terminated = False
    epsilon = initial_epsilon
    q_table = trained_q_tables[best_q_table]
    while not terminated:
        action = get_best_action(q_table, state)

        new_state, reward, terminated, _, _ = env.step(action)
        state = new_state

    env.render()
