#!/usr/bin/env python3
"""A simple rotating cube."""

import pygame
from OpenGL.GL import *
from OpenGL.GLU import *
from pygame.locals import *

# Define the vertices, edges, and surfaces of the cube
vertices = (
    (1, -1, -1),
    (1, 1, -1),
    (-1, 1, -1),
    (-1, -1, -1),
    (1, -1, 1),
    (1, 1, 1),
    (-1, -1, 1),
    (-1, 1, 1),
)

edges = (
    (0, 1),
    (1, 2),
    (2, 3),
    (3, 0),
    (4, 5),
    (5, 7),
    (7, 6),
    (6, 4),
    (0, 4),
    (1, 5),
    (2, 7),
    (3, 6),
)

surfaces = (
    (0, 1, 2, 3),
    (4, 5, 1, 0),
    (6, 7, 5, 4),
    (2, 7, 6, 3),
    (1, 5, 7, 2),
    (4, 0, 3, 6),
)

colors = (
    (1, 0, 0),  # Red
    (0, 1, 0),  # Green
    (0, 0, 1),  # Blue
    (1, 1, 0),  # Yellow
    (1, 0, 1),  # Magenta
    (0, 1, 1),  # Cyan
)


def Cube():
    glBegin(GL_QUADS)
    for i, surface in enumerate(surfaces):
        glColor3fv(colors[i])  # Set the color for each face
        for vertex in surface:
            glVertex3fv(vertices[vertex])
    glEnd()

    glColor3fv((0, 0, 0))  # Set edge color to black
    glLineWidth(2)
    glBegin(GL_LINES)
    for edge in edges:
        for vertex in edge:
            glVertex3fv(vertices[vertex])
    glEnd()


def main():
    pygame.init()
    display = (800, 600)
    pygame.display.set_mode(display, DOUBLEBUF | OPENGL)

    # Set up the perspective and camera position
    gluPerspective(45, (display[0] / display[1]), 0.1, 50.0)
    glTranslatef(0.0, 0.0, -5)

    # Enable depth testing for proper rendering of surfaces
    glEnable(GL_DEPTH_TEST)

    clock = pygame.time.Clock()
    rotation = [0, 0, 0]  # Rotation angles for x, y, z axes

    while True:
        dt = clock.tick(60) / 1000.0  # Delta time for consistent rotation speed

        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                pygame.quit()
                return

        # Update rotation angles
        rotation[0] += 60 * dt  # Rotate 60 degrees per second around x-axis
        rotation[1] += 30 * dt  # Rotate 30 degrees per second around y-axis
        rotation[2] += 45 * dt  # Rotate 45 degrees per second around z-axis

        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT)

        glPushMatrix()
        glRotatef(rotation[0], 1, 0, 0)
        glRotatef(rotation[1], 0, 1, 0)
        glRotatef(rotation[2], 0, 0, 1)
        Cube()
        glPopMatrix()

        pygame.display.flip()


if __name__ == "__main__":
    main()
