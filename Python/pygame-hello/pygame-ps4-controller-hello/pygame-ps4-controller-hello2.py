"""Another showcase of using the PS4 controller

Maybe more correct and up to date with pygame==2.1.2.
Also cleaner.

"""
import json
from pathlib import Path
from typing import List

import pygame
from pygame import Vector2
from pygame.joystick import Joystick

DISPLAY_W, DISPLAY_H = 480, 240


def _init_joysticks() -> Joystick:
    """Initiaize the controller or Joystick."""
    joysticks: List[Joystick] = []
    for i in range(pygame.joystick.get_count()):
        joystick = pygame.joystick.Joystick(i)
        joystick.init()
        joysticks.append(joystick)

    return joysticks


def main():
    """Program main entry point."""
    pygame.init()
    canvas = pygame.Surface((DISPLAY_W, DISPLAY_H))
    window = pygame.display.set_mode((DISPLAY_W, DISPLAY_H))
    clock = pygame.time.Clock()

    joysticks = _init_joysticks()
    print(f"Found {len(joysticks)} joysticks")

    with open(Path(__file__).resolve().parent / "ps4_keys.json") as file:
        key_names2key_codes = json.load(file)
    key_codes2key_names = {v: k for k, v in key_names2key_codes.items()}

    analog_forces = {
        0: 0,  # Left analog horizonal,
        1: 0,  # Left Analog vertical,
        2: -1,  # Left Trigger,
        3: 0,  # Right Analog horizontal
        4: 0,  # Right Analog vertical
        5: -1,  # Right Trigger
    }

    line_length = 40
    left_analog_pos = Vector2(150, 100)
    left_trigger_pos = Vector2(50, 100)
    right_analog_pos = Vector2(300, 100)
    right_trigger_pos = Vector2(400, 100)

    keep_running = True
    while keep_running:
        for event in pygame.event.get():

            if event.type == pygame.QUIT:
                keep_running = False
            if event.type == pygame.KEYDOWN:
                if event.unicode == "q":
                    keep_running = False
            if event.type == pygame.JOYBUTTONDOWN:
                print(str(event.button) + " " + key_codes2key_names[event.button])
            if event.type == pygame.JOYAXISMOTION:
                analog_forces[event.axis] = event.value
            if event.type == pygame.MOUSEMOTION:  # PS4 controller trackpad and mouse
                print(event)

        canvas.fill((255, 255, 255))

        # left analog stick
        pygame.draw.line(
            surface=canvas,
            color=(255, 0, 0),
            start_pos=left_analog_pos,
            end_pos=(
                left_analog_pos.x + analog_forces[0] * line_length,
                left_analog_pos.y + analog_forces[1] * line_length,
            ),
            width=5,
        )
        # L2
        pygame.draw.line(
            surface=canvas,
            color=(255, 0, 0),
            start_pos=left_trigger_pos,
            end_pos=(
                left_trigger_pos.x,
                left_trigger_pos.y - (1 + analog_forces[2]) * 0.5 * line_length,
            ),
            width=5,
        )

        # right analog stick
        pygame.draw.line(
            surface=canvas,
            color=(0, 128, 0),
            start_pos=right_analog_pos,
            end_pos=(
                right_analog_pos.x + analog_forces[3] * line_length,
                right_analog_pos.y + analog_forces[4] * line_length,
            ),
            width=5,
        )
        # R2
        pygame.draw.line(
            surface=canvas,
            color=(128, 0, 0),
            start_pos=right_trigger_pos,
            end_pos=(
                right_trigger_pos.x,
                right_trigger_pos.y - (1 + analog_forces[5]) * 0.5 * line_length,
            ),
            width=5,
        )

        window.blit(canvas, (0, 0))
        clock.tick(60)
        pygame.display.update()


if __name__ == "__main__":
    main()
