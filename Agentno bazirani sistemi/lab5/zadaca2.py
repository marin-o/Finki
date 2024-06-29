import random
import numpy as np
from pettingzoo.classic import tictactoe_v3
from q_learning import get_random_action, get_best_action, get_action, \
    random_q_table, calculate_new_q_value


# https://pettingzoo.farama.org/environments/classic/tictactoe/

'''
Кодот не функционира и не знам како да го направам да функционира.
'''
def policy(obs):
    return random.choice(np.flatnonzero(obs['action_mask']))


if __name__ == '__main__':
    env = tictactoe_v3.env(render_mode='human')

    env.reset()

    num_episodes = 5

    num_states = np.prod(env.agent_iter().env.unwrapped.observation_spaces['player_1'].spaces['observation'].shape)
    num_actions = env.agent_iter().env.unwrapped.action_spaces['player_1'].n
    # num_states = 18
    # num_actions = 9
    learning_rate = 0.001
    discount_factor = 0.1
    epsilon = 0.5
    num_steps_per_episode = 5

    q_table_1 = random_q_table(-1, 0, (num_states, num_actions))
    q_table_2 = random_q_table(-1, 0, (num_states, num_actions))

    for _ in range(num_episodes):
        env.reset()
        # while not done:
        for agent in env.agent_iter():
            state, reward, terminated, truncated, info = env.last()
            observation = state['observation']
            action_mask = state['action_mask']
            if terminated or truncated:
                env.reset()
                break
            if agent == 'player_1':
                action = get_action(env, q_table_1, state, epsilon)
                new_state, reward, terminated, truncated, info = env.step(action)
                new_q = calculate_new_q_value(q_table_1, state, new_state, action, reward, learning_rate, discount_factor)
                q_table_1[state, action] = new_q
                state = new_state
            else:
                action = get_action(env, q_table_2, state, epsilon)
                new_state, reward, terminated, truncated, info = env.step(action)
                new_q = calculate_new_q_value(q_table_2, state, new_state, action, reward, learning_rate,
                                              discount_factor)
                q_table_2[state, action] = new_q
                state = new_state
