import numpy as np
import gymnasium as gym
from deep_q_learning import DDPG, OrnsteinUhlenbeckActionNoise


def create_and_build_agent(state_space_shape, action_space_shape, learning_rate_actor, learning_rate_critic, discount_factor, batch_size, memory_size):
    agent = DDPG(state_space_shape, action_space_shape, learning_rate_actor, learning_rate_critic,
                 discount_factor, batch_size, memory_size)
    agent.build_model()
    return agent


def parse_episodes(model_name):
    parts = model_name.split('_')
    if len(parts) < 2:
        raise ValueError("Invalid model name format. Expected format: 'lunar_<number of episodes>'")
    return int(parts[1])


def postprocess_action(action):
    new_action = ...
    return new_action


def preprocess_reward(reward):
    new_reward = np.clip(reward, -5., 5.)
    return new_reward


if __name__ == '__main__':
    env = gym.make('LunarLanderContinuous-v2', render_mode='rgb_array')
    env.reset()
    env.render()

    state_space_shape = env.observation_space.shape
    action_space_shape = env.action_space.shape

    num_episodes = [10, 15, 20]
    learning_rate_actor = 0.01
    learning_rate_critic = 0.05
    discount_factor = 0.95
    batch_size = 96
    memory_size = 1000

    noise = OrnsteinUhlenbeckActionNoise(action_space_shape)

    models = {}

    for episode in num_episodes:
        cumulative_reward = 0

        agent = create_and_build_agent(state_space_shape, action_space_shape, learning_rate_actor, learning_rate_critic,
                                       discount_factor, batch_size, memory_size)

        for e in range(episode):
            state, _ = env.reset()
            env.render()
            done = False
            print(f'{episode}: {e+1}')
            reward = 0
            while not done:
                action = agent.get_action(state, discrete=False) + noise()
                next_state, reward, done, terminated, _ = env.step(action)
                env.render()
                reward = preprocess_reward(reward)
                numeric_done = 1 if done else 0
                agent.update_memory(state, action, reward, next_state, numeric_done)
                state = next_state
            cumulative_reward += reward

            agent.train()
            if e % 5 == 0:
                agent.update_target_model()

        agent.save(f'lunar_{episode}', episode)

        models[f'lunar_{episode}'] = cumulative_reward

    best_model = max(models, key=models.get)
    print(models)
    print(f'Best model: {best_model}')
    parsed_episodes = parse_episodes(best_model)

    env = gym.make('LunarLanderContinuous-v2', render_mode='human')
    agent = create_and_build_agent(state_space_shape, action_space_shape, learning_rate_actor, learning_rate_critic,
                                   discount_factor, batch_size, memory_size)
    agent.load(best_model, parsed_episodes)
    state, _ = env.reset()
    env.render()
    done = False

    while not done:
        action = agent.get_action(state, discrete=False) + noise()
        next_state, reward, done, terminated, _ = env.step(action)
        reward = preprocess_reward(reward)
        state = next_state
    env.close()

    iterations = [50, 100]
    env = gym.make('LunarLanderContinuous-v2', render_mode='rgb_array')
    done = False
    for it in iterations:
        average_reward = 0
        for _ in range(it):
            state, _ = env.reset()
            done = False
            reward = 0
            while not done:
                action = agent.get_action(state, discrete=False) + noise()
                next_state, reward, done, terminated, _ = env.step(action)
                reward = preprocess_reward(reward)
                state = next_state
            average_reward += reward
        print(f'Average reward: after {it} iterations: {average_reward / it}')
