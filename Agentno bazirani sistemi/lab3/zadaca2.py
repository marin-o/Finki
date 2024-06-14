import gymnasium as gym
import tensorflow.keras.optimizers
from keras import Input
from keras.src.optimizers import Adam
import tensorflow as tf

from deep_q_learning import DQN, DDQN
from tensorflow.keras.layers import Dense
from tensorflow.keras import Sequential
from tensorflow.keras.optimizers import SGD
import os

'''
Што и да направам или времето потребно за тренинг е преголемо, или играта не терминира. Не можам никако да го истренирам моделот за добро да ја игра играта.
Нема никакви разлики помеѓу резултатите од овој код, и DQN верзијата.
'''

def build_model(state_space_shape, num_actions):
    model = Sequential()
    model.add(Input(state_space_shape))
    model.add(Dense(64))
    model.add(Dense(16))
    model.add(Dense(8))
    model.add(Dense(num_actions, activation='softmax'))

    model.compile(Adam(), loss='mse')

    return model


if __name__ == '__main__':
    env = gym.make('MountainCar-v0', render_mode=None)
    env.reset()
    #env.render()

    state_space_shape = env.observation_space.shape
    num_actions = env.action_space.n
    num_episodes = 10
    num_steps_per_episode = 20

    model = build_model(state_space_shape, num_actions)
    target_model = build_model(state_space_shape, num_actions)

    agent = DDQN(state_space_shape, num_actions, model, target_model, batch_size=32, memory_size=1000)

    epsilon = 0.5

    for episode in range(num_episodes):
        state, _ = env.reset()
        for step in range(num_steps_per_episode):
            action = agent.get_action(state, epsilon)
            new_state, reward, terminated, _, _ = env.step(action)
            agent.update_memory(state, action, reward, new_state, terminated)

        agent.train()

        if episode > 10:
            epsilon = max(0.5, 0)
        print(f'Current episode: {episode}')

        if episode % 10 == 0:
            agent.update_target_model()

    agent.save(model_name='pacman_z2', episode=10)

    agent.load('pacman_z2', 10)

    env = gym.make('MountainCar-v0', render_mode='human')
    env.reset()
    env.render()
    # for episode in range(num_episodes):
    state, _ = env.reset()
    terminated = False
    total_reward = 0
    for i in range(20):
        action = agent.get_action(state, 0)
        new_state, reward, terminated, _, _ = env.step(action)
        total_reward += reward

    print(f'Total reward: {total_reward}. Average reward: {total_reward / 20}')