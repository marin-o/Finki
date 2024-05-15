import numpy as np
import gymnasium as gym
from PIL import Image
from deep_q_learning import DuelingDQN, DQN
from tensorflow.keras.layers import Conv2D, MaxPool2D, Flatten, Dense

'''
Добивам грешка која никако не можев да ја средам. 
За време на терминот од лабораториските вежби, кодов беше функционален, но моделот беше лош или недоволно трениран.

Traceback (most recent call last):
  File "\lab3\zadaca4.py", line 36, in <module>
    agent.build_model(layers)
  File "\lab3\deep_q_learning.py", line 276, in build_model
    self.model = self._build_model(layers)
  File "\lab3\deep_q_learning.py", line 265, in _build_model
    q = (v + (a - reduce_mean(a, axis=1, keepdims=True)))
  File "\venv\lib\site-packages\tensorflow\python\ops\weak_tensor_ops.py", line 88, in wrapper
    return op(*args, **kwargs)
  File "\venv\lib\site-packages\tensorflow\python\util\traceback_utils.py", line 153, in error_handler
    raise e.with_traceback(filtered_tb) from None
  File "\venv\lib\site-packages\keras\src\backend\common\keras_tensor.py", line 91, in __tf_tensor__
    raise ValueError(
ValueError: A KerasTensor cannot be used as input to a TensorFlow function. A KerasTensor is a symbolic placeholder for a shape and dtype, used when constructing Keras Functional models or Keras Functions. You can only use it as input to a Keras layer or a Keras operation (from the namespaces `keras.layers` and `keras.operations`). You are likely doing something like:

```
x = Input(...)
...
tf_fn(x)  # Invalid.
```

What you should do instead is wrap `tf_fn` in a layer:

```
class MyLayer(Layer):
    def call(self, x):
        return tf_fn(x)

x = MyLayer()(x)
```
'''

def preprocess_state(state):
    img = Image.fromarray(state)
    img2 = img.convert('L')

    img3 = np.array(img2, dtype=float)
    img3 /= 255
    return img3


if __name__ == '__main__':
    env = gym.make('ALE/MsPacman-v5', render_mode='rgb_array')
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
