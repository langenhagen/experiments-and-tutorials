#!/bin/bash
# Convenience automation to stop the experiment.

k3d cluster delete cloud
k3d cluster delete os
