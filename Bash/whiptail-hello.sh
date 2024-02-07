#!/bin/bash
# Display the use of the common basic TUI dialog app `whiptail`.
#
# see for colors:
#   https://gist.github.com/ymkins/bb0885326f3e38850bc444d89291987a and
#   https://askubuntu.com/questions/776831/whiptail-change-background-color-dynamically-from-magenta
#
# author: andreasl

# colors
# root                  root fg, bg
# border                border fg, bg
# window                window fg, bg
# shadow                shadow fg, bg
# title                 title fg, bg
# button                button fg, bg       also active compactbutton
# actbutton             active button fg, bg  not active compactbutton
# checkbox              checkbox fg, bg
# actcheckbox           active checkbox fg, bg
# entry                 entry box fg, bg
# label                 label fg, bg
# listbox               listbox fg, bg
# actlistbox            active listbox fg, bg
# textbox               textbox label fg, bg
# acttextbox            active textbox fg, bg
# helpline              help line
# roottext              root text
# emptyscale            scale full
# fullscale             scale empty
# disentry              disabled entry fg, bg
# compactbutton         compact button fg, bg       OK/Cancel
# actsellistbox         active & sel listbox
# sellistbox            selected listbox
export NEWT_COLORS='
root=red,black
window=red,lightgray
title=black,lightgray
border=black,lightgray
textbox=black,lightgray
entry=black,white
button=black,gray
compactbutton=black,lightgray
shadow=red,black
'

# to capture stuff, we have to swap stdout and stderr via `3>&1 1>&2 2>&3`; whiptail allegedly
# writes to stderr by default, thus we need to swap stdout and sterr. We need a 3rd "temporary" file
# descriptor to do that. If we don't do that swappadillo, the whiptail TUI dialog would not show up
# when capturing output.
nick="$(whiptail --title "Enter Nickname" --inputbox "What's your nickname" 15 50  3>&1 1>&2 2>&3)" # 15 lines high, 50 chars wide
echo "Result: ${?}"  # 0 on OK, 1 Cancel, 255 on <ESCAPE>
echo "Nickname: ${nick}"

whiptail --title "What now" --yesno "yay or nay?" 10 40 3>&1 1>&2 2>&3
echo "Result: ${?}"  # 0 on YES, 1 NO, 255 on <ESCAPE>
