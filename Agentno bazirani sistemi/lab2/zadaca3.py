import gymnasium as gym
import numpy as np
from q_learning import random_q_table, get_action, calculate_new_q_value, get_best_action


def get_discrete_state(state, low_value, window_size):
    new_state = (state - low_value) / window_size
    return tuple(new_state.astype(int))

def epsilon_decay(initial_epsilon, episode, decay_rate, min_epsilon):
    epsilon = initial_epsilon * np.exp(-decay_rate * episode)
    return max(epsilon, min_epsilon)

'''
Понекогаш дава Index out of bounds, не знам зошто. 
'''


if __name__ == '__main__':
    env = gym.make('MountainCar-v0', render_mode=None)

    num_actions = env.action_space.n

    observation_space_size = [9, 9]
    observation_space_low_value = env.observation_space.low
    observation_space_high_value = env.observation_space.high
    observation_window_size = (observation_space_high_value - observation_space_low_value) / observation_space_size
    num_episodes = range(100, 1000, 150)
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
    env = gym.make('MountainCar-v0', render_mode=None)

    state, _ = env.reset()
    # while not terminated:
    for _ in range(num_steps):
        discrete_state = get_discrete_state(state, observation_space_low_value, observation_window_size)
        action = get_best_action(q_table, discrete_state)
        new_state, reward, terminated, _, _ = env.step(action)
        discrete_state_new = get_discrete_state(new_state, observation_space_low_value, observation_window_size)
        state = new_state

    print(f"Best Q-table Stats:")
    print(f"Number of Episodes: {best_q_table}")
    print(f"Max Reward: {max_reward}")
    print()

    initial_epsilon = 1.0
    decay_rate = 0.005
    min_epsilon = 0.01

    epsilon_trained_q_tables = {}
    best_epsilon_q_table = None
    for episode in num_episodes:
        total_reward = 0
        epsilon = epsilon_decay(initial_epsilon, episode, decay_rate, min_epsilon)
        q_table = random_q_table(-1, 0, (observation_space_size + [num_actions]))
        for _ in range(episode):
            state, _ = env.reset()
            for _ in range(num_steps):
                discrete_state = get_discrete_state(state, observation_space_low_value, observation_window_size)

                action = get_action(env, q_table, discrete_state, epsilon)

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

    for episode in num_episodes:
        total_reward = 0
        epsilon = epsilon_decay(initial_epsilon, episode, decay_rate, min_epsilon)
        q_table = random_q_table(-1, 0, (observation_space_size + [num_actions]))
        for _ in range(episode):
            state, _ = env.reset()
            for _ in range(num_steps):
                discrete_state = get_discrete_state(state, observation_space_low_value, observation_window_size)

                action = get_action(env, q_table, discrete_state, epsilon)

                new_state, reward, terminated, _, _ = env.step(action)

                discrete_state_new = get_discrete_state(new_state, observation_space_low_value, observation_window_size)
                new_q = calculate_new_q_value(q_table, discrete_state, discrete_state_new, action, reward)

                q_table[discrete_state, action] = new_q
                state = new_state

                total_reward += reward
        if total_reward >= max_reward:
            max_reward = total_reward
            best_epsilon_q_table = episode
        trained_q_tables[episode] = q_table

        epsilon_trained_q_tables[episode] = q_table

    print(f"Best Q-table Stats with Epsilon Decay:")
    print(f"Number of Episodes: {best_epsilon_q_table}")
    print(f"Total Reward: {sum(epsilon_trained_q_tables[best_epsilon_q_table].flatten())}")
    print()
