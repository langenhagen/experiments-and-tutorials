#!/usr/bin/env python

import platform

def print_platform_info():
    print "platform.machine():   " + platform.machine()
    print "platform.version():   " + platform.version()
    print "platform.platform():  " + platform.platform()
    print "platform.uname():     " + str(platform.uname())
    print "platform.system():    " + platform.system()
    print "platform.processor(): " + platform.processor()

if __name__ == "__main__":
    print_platform_info()
