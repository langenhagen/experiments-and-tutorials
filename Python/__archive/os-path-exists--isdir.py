#!/usr/bin/env python
# -*- coding: utf-8 -*-
import os

#from os.path import isfile, join

path = "/Users/langenha/personal/Media/Images/Fotos/DSLR"
image_files = [os.path.join(path,f) for f in os.listdir(path) if os.path.isfile(os.path.join(path,f)) and f.lower().endswith(".jpg") ]


print os.path.isdir("/Users/langenha/personal/Media/Images/Fotos/DSLR")
print os.path.exists("/Users/langenha/personal/Media/Images/Fotos/DSLR")


