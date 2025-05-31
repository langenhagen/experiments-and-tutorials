"""Hello world for pygame.

Taken from: https://gist.github.com/nicolasfig/3903404
"""

import sys

import pygame
from pygame.locals import *

# Set up pygame
pygame.init()

# Set up the window
windowSurface = pygame.display.set_mode((500, 400), 0, 32)
pygame.display.set_caption("Hello World")

# Set up the colors
BLACK = (0, 0, 0)
RED = (255, 0, 0)
GREEN = (0, 255, 0)
BLUE = (0, 0, 255)
WHITE = (255, 255, 255)

# Set up fonts
basicFont = pygame.font.SysFont(None, 48)

# Set up the text
text = basicFont.render("HELLO WORLD", True, WHITE)
textRect = text.get_rect()
textRect.centerx = windowSurface.get_rect().centerx
textRect.centery = windowSurface.get_rect().centery

# Draw the white background onto the surface
windowSurface.fill(WHITE)

# Draw a blue poligon onto the surface
pygame.draw.polygon(windowSurface, BLUE, ((250, 0), (500, 200), (250, 400), (0, 200)))

# Draw a green poligon onto the surface
pygame.draw.polygon(
    windowSurface, GREEN, ((125, 100), (375, 100), (375, 300), (125, 300))
)

# Draw a red circle onto the surface
pygame.draw.circle(windowSurface, RED, (250, 200), 125)

# Get a pixel array of the surface
pixArray = pygame.PixelArray(windowSurface)
pixArray[480][380] = BLACK
del pixArray

# Draw the text onto the surface
windowSurface.blit(text, textRect)

# Draw the window onto the screen
pygame.display.update()

# Run the game loop
while True:
    for event in pygame.event.get():
        if event.type == QUIT:
            pygame.quit()
            sys.exit()
