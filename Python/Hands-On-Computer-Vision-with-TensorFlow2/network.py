"""A simple feed-forward neural network.

author: andreasl
"""
import numpy as np

from layer import FullyConnectedLayer


def sigmoid(x):
    """Logistic activation function."""
    return 1 / (1 + np.exp(-x))


class SimpleNetwork:
    """Simple fully connected neural network."""

    def __init__(self, n_inputs, n_outputs, hidden_layer_sizes=(64,32)):
        sizes = [n_inputs, *hidden_layer_sizes, n_outputs]
        self.layers = []
        for i in range(len(sizes) -1):
            self.layers.append(FullyConnectedLayer(sizes[i], sizes[i + 1], sigmoid))

    def forward(self, x):
        """Forward the given input vector through the layers."""
        for layer in self.layers:
            x = layer.forward(x)
        return x

    def predict(self, x):
        """Compute the output corresponding to the given input vector and return
        the index of the largest output value."""
        estimations = self.forward(x)
        best_class = np.argmax(estimations)
        return best_class

    def evaluate_accuracy(self, X_val, y_val):
        """Evaluate the network's accuracy on a validation dataset."""
        n_corrects = 0
        for i in range(len(X_val)):
            if self.predict(X_val[i]) == y_val[i]:
                n_corrects += 1
        return n_corrects / len(X_val)
