import numpy as np


def value_iteration(env, num_actions, num_states, theta=0.00001, discount_factor=1.0):
    """
    This section is for Value Iteration Algorithm.

    Arguments:
        env: OpenAI env. env.P represents the transition probabilities of the environment.
            env.P[s][a] is a list of transition tuples (prob, next_state, reward, done).
            env.nS is a number of states in the environment.
            env.nA is a number of actions in the environment.
        theta: Stop evaluation once value function change is less than theta for all states.
        discount_factor: Gamma discount factor.

    Returns:
        A tuple (policy, V) of the optimal policy and the optimal value function.
    """

    def one_step_lookahead(state, V):
        """
        Function to calculate the value for all actions in a given state.

        Arguments:
            state: The state to consider (int)
            V: The value to use as an estimator, Vector of length env.nS

        Returns:
            A vector of length env.nA containing the expected value of each action.
        """
        A = np.zeros(num_actions)
        for a in range(num_actions):
            for prob, nextState, reward, done in env.P[state][a]:
                A[a] += prob * (reward + discount_factor * V[nextState])

        return A

    V = np.zeros(num_states)

    numIterations = 0

    while True:
        numIterations += 1
        delta = 0

        for s in range(num_states):
            qValues = one_step_lookahead(s, V)
            newValue = np.max(qValues)

            delta = max(delta, np.abs(newValue - V[s]))
            V[s] = newValue

        if delta < theta:
            break

    policy = np.zeros([num_states, num_actions])
    for s in range(num_states):  # for all states, create deterministic policy
        qValues = one_step_lookahead(s, V)

        newAction = np.argmax(qValues)
        policy[s][newAction] = 1

    return policy, V


def policy_evaluation(policy, env, num_actions, num_states, discount_factor=1.0, theta=0.00001):
    """
    Implement the policy evaluation algorithm here given a policy and a complete model of the environment.


    Arguments:
        policy: [S, A] shaped matrix representing the policy.
        env: OpenAI env. env.P represents the transition probabilities of the environment.
            env.P[s][a] is a list of transition tuples (prob, next_state, reward, done).
            env.nS is a number of states in the environment.
            env.nA is a number of actions in the environment.
        theta: This is the minimum threshold for the error in two consecutive iteration of the value function.
        discount_factor: This is the discount factor - Gamma.

    Returns:
        Vector of length env.nS representing the value function.
    """
    #     Start with a random (all 0) value function
    V = np.zeros(num_states)

    counter = 0

    while True:
        counter += 1
        delta = 0
        for s in range(num_states):
            vNew = 0
            for a in range(num_actions):
                for prob, nextState, reward, done in env.P[s][a]:
                    vNew += policy[s][a] * prob * (reward + discount_factor * V[nextState])

            delta = max(delta, np.abs(V[s] - vNew))
            V[s] = vNew

        if delta < theta:
            break

    return np.array(V)


def policy_iteration(env, num_actions, num_states, policy_eval_fn=policy_evaluation, discount_factor=1.0):
    """
    Implement the Policy Improvement Algorithm here which iteratively evaluates and improves a policy
    until an optimal policy is found.

    Arguments:
        env: The OpenAI envrionment.
        policy_eval_fn: Policy Evaluation function that takes 3 arguments:
            policy, env, discount_factor.
        discount_factor: gamma discount factor.

    Returns:
        A tuple (policy, V).
        policy is the optimal policy, a matrix of shape [S, A] where each state s
        contains a valid probability distribution over actions.
        V is the value function for the optimal policy.

    """

    def one_step_lookahead(state, V):
        """
        Implement the function to calculate the value for all actions in a given state.

        Arguments:
            state: The state to consider (int)
            V: The value to use as an estimator, Vector of length env.nS

        Returns:
            A vector of length env.nA containing the expected value of each action.
        """
        A = np.zeros(num_actions)
        for a in range(num_actions):
            for prob, nextState, reward, done in env.P[state][a]:
                A[a] += prob * (reward + discount_factor * V[nextState])

        return A

    # Start with a random policy
    policy = np.ones([num_states, num_actions]) / num_actions

    numIterations = 0

    while True:
        numIterations += 1

        V = policy_eval_fn(policy, env, num_actions, num_states, discount_factor)
        policyStable = True

        for s in range(num_states):
            oldAction = np.argmax(policy[s])

            qValues = one_step_lookahead(s, V)
            newAction = np.argmax(qValues)

            if oldAction != newAction:
                policyStable = False

            policy[s] = np.zeros([num_actions])
            policy[s][newAction] = 1

        if policyStable:
            return policy, V

    return policy, np.zeros(num_states)