import gymnasium as gym
import numpy as np
from PIL import Image
from tensorflow.keras import Sequential
from tensorflow.keras.layers import Dense, Conv2D, Flatten, MaxPool2D
from tensorflow.keras.optimizers import Adam
from tensorflow.keras.losses import MeanSquaredError
from deep_q_learning import DQN


def build_model(state_space_shape, num_actions, learning_rate):
    model = Sequential()
    model.add(Conv2D(64, (8, 8), strides=4, activation='relu', input_shape=state_space_shape))
    model.add(MaxPool2D())
    model.add(Conv2D(32, (4, 4), strides=2, activation='relu'))
    model.add(MaxPool2D())
    model.add(Conv2D(16, (3, 3), strides=1, activation='relu'))
    model.add(MaxPool2D())
    model.add(Flatten())
    model.add(Dense(128, activation='relu'))
    model.add(Dense(num_actions, activation='softmax'))
    model.compile(Adam(learning_rate=learning_rate), loss=MeanSquaredError())


    return model


def preprocess_state(state):
    img = Image.fromarray(state)
    img2 = img.convert('L')

    img3 = np.array(img2, dtype=float)
    img3 /= 255
    return img3


if __name__ == '__main__':
    env = gym.make('ALE/MsPacman-v5')

    state_space_shape = env.observation_space.shape[:-1] + (1,)
    num_actions = env.action_space.n

    num_episodes = 20
    epsilon = 1.0
    epsilon_decay = 0.995
    min_epsilon = 0.1

    model = build_model(state_space_shape, num_actions, learning_rate=0.05)
    target_model = build_model(state_space_shape, num_actions, learning_rate=0.05)

    agent = DQN(state_space_shape, num_actions, model, target_model)

    total_rewards = []

    for episode in range(num_episodes):
        state, _ = env.reset()
        state = preprocess_state(state)
        done = False
        rewards = 0
        while not done:
            action = agent.get_action(state, max(min_epsilon, epsilon**episode))
            new_state, reward, done, _, _ = env.step(action)
            new_state = preprocess_state(new_state)
            agent.update_memory(state, action, reward, new_state, done)
            state = new_state
            rewards += reward
            if done:
                print(f'Episode: {episode}, reward: {rewards}')
                total_rewards.append(rewards)
                agent.train()
        if epsilon > min_epsilon:
            epsilon *= epsilon_decay
        if episode % 5 == 0:
            agent.update_target_model()

    agent.save('pacman_z3', num_episodes)

    print(np.mean(total_rewards))

    agent.load('pacman_z3', 20)

    done = False
    env = gym.make('ALE/MsPacman-v5', render_mode='human')
    state, _ = env.reset()
    state = preprocess_state(state)
    env.render()
    rewards = 0
    while not done:
        action = agent.get_action(state, min_epsilon)
        state, reward, done, _, _ = env.step(action)
        state = preprocess_state(state)
        env.render()
        rewards += reward
    print(f'Reward after training: {rewards}')
