import numpy as np
import gymnasium as gym
from mdp import value_iteration, policy_iteration

value_iteration_averages = {}
policy_iteration_averages = {}

'''
Решенијата за двете задачи ги комбинирав(преку iteration_type параметарот) во една скрипта во која на крајот се принтаат статистиките.
По повеќе извршувања на скриптата изгледа дека за конкретниот проблем двата алгоритми даваат исти резултати,
со варијанса на конечните поени и чекорите поради различните стартни позиции на таксито и патникот.
Освен тоа, кога правев споредби за која вредност за факторот на намалување е најдобра, добивав неконзистентни резултати.
Во различни извршувања различна вредност беше најдобра, веројатно пак поради горенаведената причина.
Пробав и со вредности под .5 и над .9, и алгоритамот не конвергираше, играта траеше бесконечно долго, освен за gamma∈[0.4, 0.5]
'''

def taxi_task(env, num_iter, discount, iteration_type):
    if iteration_type == 'value_iteration':
        averages = value_iteration_averages
        iteration_function = value_iteration
    elif iteration_type == 'policy_iteration':
        averages = policy_iteration_averages
        iteration_function = policy_iteration
    else:
        raise ValueError("Invalid iteration type. Valid options are 'value_iteration' and 'policy_iteration'.")

    policy, _ = iteration_function(env,
                                   env.action_space.n,
                                   env.observation_space.n,
                                   discount_factor=discount)

    if num_iter not in averages:
        averages[num_iter] = {}

    averages[num_iter][discount] = {'steps': 0, 'rewards': 0}

    for i in range(num_iter):
        state, _ = env.reset()
        terminated = False
        steps = 0
        total_reward = 0

        while not terminated:
            action = np.argmax(policy[state])
            state, reward, terminated, _, _ = env.step(action)
            steps += 1
            total_reward += reward

        averages[num_iter][discount]['steps'] += steps
        averages[num_iter][discount]['rewards'] += total_reward

    averages[num_iter][discount]['steps'] /= num_iter
    averages[num_iter][discount]['rewards'] /= num_iter


if __name__ == '__main__':
    env = gym.make('Taxi-v3', render_mode='ansi')

    num_iterations = [50, 100]
    discount_factors = [0.5, 0.7, 0.9]
    iteration_types = ['value_iteration', 'policy_iteration']

    for iteration_type in iteration_types:
        for num_iter in num_iterations:
            for discount in discount_factors:
                taxi_task(env, num_iter, discount, iteration_type)

    print("Comparison of Averages:")
    best_gamma_overall = {num_iter: {'gamma': None, 'average_rewards': float('-inf')} for num_iter in num_iterations}

    for num_iter in num_iterations:
        print()
        print(f"For Number of Iterations: {num_iter}")
        for discount in discount_factors:
            vi_steps = value_iteration_averages[num_iter][discount]['steps']
            vi_rewards = value_iteration_averages[num_iter][discount]['rewards']
            pi_steps = policy_iteration_averages[num_iter][discount]['steps']
            pi_rewards = policy_iteration_averages[num_iter][discount]['rewards']

            steps_difference = vi_steps - pi_steps
            rewards_difference = vi_rewards - pi_rewards

            print(f"Discount Factor: {discount}")
            print(f"Value Iteration - Steps: {vi_steps}, Rewards: {vi_rewards}")
            print(f"Policy Iteration - Steps: {pi_steps}, Rewards: {pi_rewards}")
            print(f"Difference - Steps: {steps_difference}, Rewards: {rewards_difference}")

            # Determine the best gamma overall
            average_rewards = (vi_rewards + pi_rewards) / 2
            if average_rewards > best_gamma_overall[num_iter]['average_rewards']:
                best_gamma_overall[num_iter]['gamma'] = discount
                best_gamma_overall[num_iter]['average_rewards'] = average_rewards

        print("----------------------------------------------")

    print("\nBest Gamma Overall:")
    for num_iter, info in best_gamma_overall.items():
        print(f"For {num_iter} Iterations: Gamma = {info['gamma']}, Average Rewards = {info['average_rewards']}")

