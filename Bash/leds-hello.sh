#!/bin/bash
#
# Get the status of the computer's LEDs.
#
# author: andreasl

declare -A active_leds

for dir in /sys/class/leds/*; do
    if [ -d "$dir" ]; then
        led_name="${dir##*/}"
        status=$(cat "$dir/brightness")
        [ "$status" -gt 0 ] && active_leds["$dir"]="$status"
    fi
done

# Print all directories with a status > 0 along with their brightness value
for dir in "${!active_leds[@]}"; do
    echo "$dir: ${active_leds[$dir]}"
done
