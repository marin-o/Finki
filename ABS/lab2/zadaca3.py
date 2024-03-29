import gymnasium as gym
from q_learning import random_q_table, get_action, calculate_new_q_value, get_best_action


def get_discrete_state(state, low_value, window_size):
    new_state = (state - low_value) / window_size
    return tuple(new_state.astype(int))

'''
    Ни оваа задача не можам да ја доведам да конвергира. На визуелизацијата од играта користејќи ја „најдобрата“ Q-табела,
    автомобилот упорно се обидува да се искачи налево, никогаш не се ни обидува да оди надесно.
    Постојано се враќа долу и пак налево, повторувајќи до бесконечност.
'''

if __name__ == '__main__':
    env = gym.make('MountainCar-v0', render_mode=None)

    num_actions = env.action_space.n

    observation_space_size = [3, 3]
    observation_space_low_value = env.observation_space.low
    observation_space_high_value = env.observation_space.high
    observation_window_size = (observation_space_high_value - observation_space_low_value) / observation_space_size
    num_episodes = [10, 30, 50, ]
    num_steps = 200

    terminated = False
    total_reward = 0
    max_reward = float('-inf')
    best_q_table = None

    trained_q_tables = {}
    for episode in num_episodes:
        total_reward = 0
        q_table = random_q_table(-1, 0, (observation_space_size + [num_actions]))
        for _ in range(episode):
            state, _ = env.reset()
            for _ in range(num_steps):
                discrete_state = get_discrete_state(state, observation_space_low_value, observation_window_size)
                action = get_action(env, q_table, discrete_state, 1.0)
                new_state, reward, terminated, _, _ = env.step(action)

                discrete_state_new = get_discrete_state(new_state, observation_space_low_value, observation_window_size)
                new_q = calculate_new_q_value(q_table, discrete_state, discrete_state_new, action, reward)

                q_table[discrete_state, action] = new_q
                state = new_state

                total_reward += reward
        if total_reward >= max_reward:
            max_reward = total_reward
            best_q_table = episode
        trained_q_tables[episode] = q_table

    q_table = trained_q_tables[best_q_table]
    env = gym.make('MountainCar-v0', render_mode='human')

    state, _ = env.reset()
    while not terminated:
        discrete_state = get_discrete_state(state, observation_space_low_value, observation_window_size)
        action = get_best_action(q_table, discrete_state)
        new_state, reward, terminated, _, _ = env.step(action)
        discrete_state_new = get_discrete_state(new_state, observation_space_low_value, observation_window_size)
        state = new_state
    env.render()
