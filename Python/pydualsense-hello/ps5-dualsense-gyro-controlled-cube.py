#!/usr/bin/env python3
"""Showcase a 3D cube that can be controlled via your PS5 DualSense controller's
gyro or keyboard arrow keys.
"""
from contextlib import suppress

import pygame
import wat
from OpenGL.GL import *
from OpenGL.GLU import *
from pydualsense import pydualsense
from pygame.locals import *

# Global variables for gyro data
gyro_roll = 0
gyro_pitch = 0
gyro_yaw = 0


def gyro_changed(pitch, yaw, roll):
    global gyro_roll, gyro_pitch, gyro_yaw
    gyro_roll = roll
    gyro_pitch = pitch
    gyro_yaw = yaw


# Define the cube vertices, edges, and surfaces

# fmt: off
vertices = [
    [ 1, 1,-1],
    [ 1,-1,-1],
    [-1,-1,-1],
    [-1, 1,-1],
    [ 1, 1, 1],
    [ 1,-1, 1],
    [-1,-1, 1],
    [-1, 1, 1],
]

edges = [
    (0,1),
    (1,2),
    (2,3),
    (3,0),
    (4,5),
    (5,6),
    (6,7),
    (7,4),
    (0,4),
    (1,5),
    (2,6),
    (3,7),
]

surfaces = (
    (0,1,2,3),
    (3,2,6,7),
    (7,6,5,4),
    (4,5,1,0),
    (1,5,6,2),
    (4,0,3,7),
)

colors = (
    (1,0,0),
    (0,1,0),
    (0,0,1),
    (1,1,0),
    (1,0,1),
    (0,1,1),
)
# fmt: on


def Cube():
    glBegin(GL_QUADS)
    for i_surface, surface in enumerate(surfaces):
        glColor3fv(colors[i_surface])
        for vertex in surface:
            glVertex3fv(vertices[vertex])
    glEnd()

    glColor3fv((1, 1, 1))
    glBegin(GL_LINES)
    for edge in edges:
        for vertex in edge:
            glVertex3fv(vertices[vertex])
    glEnd()


def draw_hud(width, height):
    # Save the current projection and modelview matrices
    glMatrixMode(GL_PROJECTION)
    glPushMatrix()
    glLoadIdentity()
    glOrtho(0, width, 0, height, -1, 1)

    glMatrixMode(GL_MODELVIEW)
    glPushMatrix()
    glLoadIdentity()

    # Disable depth testing to draw HUD over 3D scene
    glDisable(GL_DEPTH_TEST)

    # Map gyro values to bar lengths
    max_gyro_value = 15000  # Adjust as necessary based on your gyro range
    roll_norm = gyro_roll / max_gyro_value
    pitch_norm = gyro_pitch / max_gyro_value
    yaw_norm = gyro_yaw / max_gyro_value

    # Clamp values between -1 and 1
    roll_norm = max(min(roll_norm, 1), -1)
    pitch_norm = max(min(pitch_norm, 1), -1)
    yaw_norm = max(min(yaw_norm, 1), -1)

    # Calculate bar dimensions
    bar_width = 20
    bar_max_length = height / 2 - 20  # Leave some margin
    center_x = width / 2
    center_y = height / 2

    # Positions for the bars
    # fmt: off
    positions = [
        (10,         center_y),  # Left side (Roll)
        (width - 30, center_y),  # Right side (Yaw)
        (center_x,         20),  # Bottom center (Pitch)
    ]

    # Colors for the bars
    bar_colors = [
        (1, 0, 0),  # Red for Pitch
        (0, 1, 0),  # Green for Yaw
        (0, 0, 1),  # Blue for Roll
    ]
    # fmt: on

    # Gyro normalized values
    norms = [roll_norm, yaw_norm, pitch_norm]

    # Draw each bar
    for i in range(3):
        x, y = positions[i]
        norm = norms[i]
        color = bar_colors[i]

        # Calculate bar length
        bar_length = norm * bar_max_length

        # Draw the bar
        glColor3fv(color)
        glBegin(GL_QUADS)
        if i < 2:  # Vertical bars for Roll and Yaw
            glVertex2f(x, y)
            glVertex2f(x + bar_width, y)
            glVertex2f(x + bar_width, y + bar_length)
            glVertex2f(x, y + bar_length)
        else:  # Horizontal bar for Pitch
            glVertex2f(x, y)
            glVertex2f(x, y + bar_width)
            glVertex2f(x + bar_length, y + bar_width)
            glVertex2f(x + bar_length, y)
        glEnd()

    # Re-enable depth testing
    glEnable(GL_DEPTH_TEST)

    # Restore the previous projection and modelview matrices
    glMatrixMode(GL_MODELVIEW)
    glPopMatrix()

    glMatrixMode(GL_PROJECTION)
    glPopMatrix()

    glMatrixMode(GL_MODELVIEW)


def resize_window(width, height):
    if height == 0:
        height = 1
    glViewport(0, 0, width, height)
    glMatrixMode(GL_PROJECTION)
    glLoadIdentity()
    gluPerspective(45, (width / height), 0.1, 50.0)
    glMatrixMode(GL_MODELVIEW)


def main():
    pygame.init()
    display = (800, 600)

    # Antialiasing
    pygame.display.gl_set_attribute(pygame.GL_MULTISAMPLEBUFFERS, 1)
    pygame.display.gl_set_attribute(pygame.GL_MULTISAMPLESAMPLES, 8)

    pygame.display.set_mode(display, DOUBLEBUF | OPENGL | RESIZABLE)

    resize_window(display[0], display[1])

    glEnable(GL_DEPTH_TEST)

    # Initialize DualSense controller
    try:
        dualsense = pydualsense()
        dualsense.init()
        dualsense.gyro_changed += gyro_changed
        controller_connected = True
    except Exception as e:
        print(f"Warning: Caught Exception: {e}\n  Proceeding without controller.")
        controller_connected = False
        dualsense = None  # Ensure dualsense variable exists

    clock = pygame.time.Clock()

    # Initialize smoothed angles
    smoothed_pitch_angle = 0.0
    smoothed_roll_angle = 0.0
    smoothed_yaw_angle = 0.0

    alpha = 0.15  # Smoothing factor between 0 and 1

    # Initialize keyboard movement variables
    keyboard_pitch = 0.0
    keyboard_roll = 0.0

    # Main loop
    with suppress(KeyboardInterrupt):
        while True:
            for event in pygame.event.get():
                if event.type == pygame.QUIT:
                    if controller_connected:
                        dualsense.close()
                    pygame.quit()
                    quit()
                elif event.type == pygame.VIDEORESIZE:
                    display = (event.w, event.h)
                    resize_window(display[0], display[1])
                elif event.type == pygame.KEYDOWN:
                    if event.key == pygame.K_F11:
                        pygame.display.toggle_fullscreen()
                    elif event.key == pygame.K_LEFT:
                        keyboard_roll = 1
                    elif event.key == pygame.K_RIGHT:
                        keyboard_roll = -1
                    elif event.key == pygame.K_UP:
                        keyboard_pitch = -1
                    elif event.key == pygame.K_DOWN:
                        keyboard_pitch = 1
                elif event.type == pygame.KEYUP:
                    if event.key in [pygame.K_LEFT, pygame.K_RIGHT]:
                        keyboard_roll = 0
                    elif event.key in [pygame.K_UP, pygame.K_DOWN]:
                        keyboard_pitch = 0

            # Map gyro values to rotation angles
            SENSITIVITY = 100

            # Gyro inputs (if controller connected)
            if controller_connected:
                pitch_angle = gyro_roll / SENSITIVITY
                roll_angle = gyro_pitch / SENSITIVITY
                yaw_angle = gyro_yaw / SENSITIVITY
            else:
                pitch_angle = 0.0
                roll_angle = 0.0
                yaw_angle = 0.0

            # Combine keyboard inputs
            keyboard_sensitivity = 50  # Adjust the multiplier to set speed
            pitch_angle += keyboard_pitch * keyboard_sensitivity
            roll_angle += keyboard_roll * keyboard_sensitivity

            # Apply exponential smoothing to the angles
            smoothed_pitch_angle = (
                alpha * pitch_angle + (1 - alpha) * smoothed_pitch_angle
            )
            smoothed_roll_angle = alpha * roll_angle + (1 - alpha) * smoothed_roll_angle
            smoothed_yaw_angle = alpha * yaw_angle + (1 - alpha) * smoothed_yaw_angle

            # Clear buffers
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT)

            # Reset transformations
            glLoadIdentity()

            # Move back to see the cube
            glTranslatef(0.0, 0.0, -5)

            # Apply rotations with smoothed angles
            glRotatef(smoothed_roll_angle, 0, 0, 1)  # Roll
            glRotatef(-smoothed_pitch_angle, 1, 0, 0)  # Pitch
            # glRotatef(smoothed_yaw_angle, 0, 0, 1)    # Yaw (if needed); not really yaw but an indicator whether the controller looks forward or backward?

            Cube()

            # Draw the HUD (only if controller is connected)
            if controller_connected:
                draw_hud(display[0], display[1])

            pygame.display.flip()
            clock.tick(60)

    # Close DualSense controller
    if controller_connected:
        dualsense.close()


if __name__ == "__main__":
    main()
