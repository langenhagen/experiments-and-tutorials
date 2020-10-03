"""A simple artificial neuron implementation.

author: andreasl
"""
import numpy as np


class Neuron:
    """A simpe feed-forward artificial Neuron."""

    def __init__(self, n_inputs, activation_fn):
        self.weights = np.random.rand(n_inputs)
        self.bias = np.random.rand(1)
        self.activation_fn = activation_fn

    def forward(self, x):
        """Forward the input signal through the neuron."""
        z = np.dot(x, self.weights) + self.bias
        return self.activation_fn(z)


if __name__ == "__main__":
    np.random.seed(42)
    x = np.random.rand(3).reshape(1, 3)
    print(f"x: {x}")
    step_fn = lambda y: 0 if y < 0 else 1
    perceptron = Neuron(x.size, activation_fn=step_fn)
    print(f"perceptron.weights: {perceptron.weights}")
    print(f"perceptron.bias: {perceptron.bias}")
    out = perceptron.forward(x)
    print(f"out: {out}")
