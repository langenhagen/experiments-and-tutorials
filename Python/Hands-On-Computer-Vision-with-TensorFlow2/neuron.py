"""A simple artificial neuron implementation based on the contents in the book
Hands-On Computer Vision with TensorFlow 2.

author: andreasl
"""
# /import numpy as np
import numpy as np


class Neuron:
    """A simpe feed-forward artificial Neuron."""

    def __init__(self, n_inputs, activation_fn):
        self.W = np.random.rand(n_inputs)
        self.b = np.random.rand(1)
        self.activation_fn = activation_fn
