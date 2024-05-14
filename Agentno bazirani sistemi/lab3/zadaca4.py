import numpy as np
import gymnasium as gym
from PIL import Image
from deep_q_learning import DuelingDQN, DQN
from tensorflow.keras.layers import Conv2D, MaxPool2D, Flatten, Dense


def preprocess_state(state):
    img = Image.fromarray(state)
    img2 = img.convert('L')

    img3 = np.array(img2, dtype=np.float)
    img3 /= 255
    return img3


if __name__ == '__main__':
    env = gym.make('ALE/MsPacman-v5', render_mode='rgb_array')
    env.metadata['render_fps'] = 30
    state, _ = env.reset()
    env.render()

    layers = [Conv2D(32, activation='relu', kernel_size=(2, 2)),
              MaxPool2D(),
              Conv2D(16, activation='relu', kernel_size=(2, 2)),
              MaxPool2D(),
              Flatten(),
              Dense(32, activation='relu'),
              Dense(16, activation='relu'),
              Dense(env.action_space.n, activation='linear')]

    num_episodes = 20
    num_steps = 20

    agent = DuelingDQN(state_space_shape=(210, 160, 1), num_actions=env.action_space.n, learning_rate=0.05)
    agent.build_model(layers)

    for episode in range(num_episodes):
        state, _ = env.reset()
        for step in range(num_steps):
            processed_state = preprocess_state(state)
            action = agent.get_action(processed_state, 0)
            new_state, reward, terminated, _, _ = env.step(action)
            processed_new_state = preprocess_state(new_state)
            state = new_state
            agent.update_memory(processed_state, action, reward, processed_new_state, terminated)

        agent.train()

        if episode % 10 == 0:
            agent.update_target_model()
    print()
