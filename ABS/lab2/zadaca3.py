import gymnasium as gym
import numpy as np
from q_learning import random_q_table, calculate_new_q_value, get_action


def get_discrete_state(state, low_value, window_size):
    new_state = (state - low_value) / window_size
    return tuple(new_state.astype(np.int))


if __name__ == '__main__':
    env = gym.make('MountainCar-v0', render_mode='human')

    num_actions = env.action_space.n

    observation_space_size = [3, 3]
    observation_space_low_value = env.observation_space.low
    observation_space_high_value = env.observation_space.high
    observation_window_size = (observation_space_high_value - observation_space_low_value) / observation_space_size

    q_table = random_q_table(-1, 0, (observation_space_size + [num_actions]))

    num_episodes = 100

    for episode in range(num_episodes):
        state = env.reset()
        discrete_state = get_discrete_state(state, observation_space_low_value, observation_window_size)
        terminated = False

        while not terminated:
            action = get_action(env, q_table, discrete_state, epsilon=0.1)

            new_state, reward, terminated, _, _ = env.step(action)
            new_discrete_state = get_discrete_state(new_state, observation_space_low_value, observation_window_size)

            # Update Q-value
            q_table[state, action] = calculate_new_q_value(q_table, discrete_state, new_discrete_state,
                                                           action, reward)
            discrete_state = new_discrete_state
