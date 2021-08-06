"""
Showcase the usage of a Python 2 script that will run under sikuliX will execute.

SikuliX does on all major platforms basically what AutoIT does for Windows, and
sikuliX is far superior. Via the IDE, you can quickly take screenshots and based
on the resulting images, tell sikuliX what to do, e.g. where to click or for what
to wait. sikuliX /feels/ a bit messy, however.

The scripting language sikuliX 2 uses is Python 2.

SikuliX comes with a semi shitty IDE, but it is mighty powerful to quickly automate
user interaction tasks.

author: andreasl
"""
import sys

print("Running under Python: " + sys.version)

is_running = True

def panic_shutdown(event):
    """A panic button to shut down a running sikuli script,
    e.g. when its execution goes out of hand."""
    print("Panic shutdown requested")
    # I used `sys.exit(1)` before, but that kills sikulix entirely
    global is_running
    is_running = False


Env.addHotkey(Key.ESC, KeyModifier.CTRL, panic_shutdown)

# open chrome
# not necessary; instead, open chrome manually, log in and focus on the browser
# sikulu seems to have some issues with WIN Key on GNOME. Therefore go the console way
# type("t",Key.SHIFT + Key.ALT)
# wait(1)
# paste(ucode("google-chrome"))
# type(Key.ENTER)

# somebullshit # any broken statement halts the script

# repeat some user interaction repeatedly to see if there is a flaky issue
for step in range(30):
    if not is_running:
        # die handsomely
        print("Commencing panic shutdown!")
        break

    print("Running step " + str(step))
    if not exists("1628177309638.png"):
        print("Found no stock icon. Bailing out!")
        break


    # focus the chrome address bar
    type("l", Key.CTRL)

    # paste the url instead of typing, its faster
    paste(ucode("http://core.bof.mo.local/storage/live_stock/"))
    type(Key.ENTER)

    # wait max 4 seconds for the image to appear
    wait("1628177309638.png", 4)
    paste(ucode("S-rdqkfu"))
    wait("1628178147146.png", 4)
    paste(ucode("A-penis"))
    wait("1628178200271.png", 4)
    paste(ucode("S-rdqkfu"))
