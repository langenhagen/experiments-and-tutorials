#!/bin/bash
# Get the topmost window and if it is a chrome window, grab the URL and print it.
#
# xdotool sadly works poorly and needs long calls to sleep in order to work.
#
# author: andreasl

autokey-run -s "copy-url"
sleep 0.01
xclip -o | dmenu
