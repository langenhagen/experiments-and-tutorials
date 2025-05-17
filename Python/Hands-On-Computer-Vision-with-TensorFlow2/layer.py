"""A simple fully connected neural network layer.

author: andreasl
"""

import numpy as np


class FullyConnectedLayer:
    """A simple fully connected neural network layer."""

    def __init__(self, n_inputs, layer_size, activation_fn) -> None:
        self.weights = np.random.standard_normal((n_inputs, layer_size))
        self.bias = np.random.standard_normal(layer_size)
        self.size = layer_size
        self.activation_fn = activation_fn

    def forward(self, x):
        """Forward the input signal through the layer."""
        z = np.dot(x, self.weights) + self.bias
        return self.activation_fn(z)


if __name__ == "__main__":
    np.random.seed(42)
    x1 = np.random.uniform(-1, 1, 2).reshape(1, 2)
    print(f"x1: {x1}")
    x2 = np.random.uniform(-1, 1, 2).reshape(1, 2)
    print(f"x2: {x2}")

    relu_fn = lambda y: np.maximum(y, 0)
    layer = FullyConnectedLayer(2, 3, relu_fn)

    out1 = layer.forward(x1)
    print(f"out1: {out1}")
    out2 = layer.forward(x2)
    print(f"out2: {out2}")

    # the layer can handle single input vectors or stacked vectors, aka batches
    x12 = np.concatenate((x1, x2))
    print(f"x12: {x12}")
    out12 = layer.forward(x12)
    print(f"out12: {out12}")
