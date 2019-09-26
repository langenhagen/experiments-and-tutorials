author: langenhagen
contact: andreas.langenhagen@dai-labor.de
version: 20131118

***** gLayer2 readme *****

CONTENTS:
	0. INTRO
	1. USAGE
    2. JSON client
	3. GOOD TO KNOW

0. INTRO ======================================================================

This is gLayer.
It layers multiple screens either from a webpage
or some shared memory onto each other.

using
    - OgreSDK
	- Awesomium SDK
	- Boost
    - JsonCpp
	- Shm_Ring_Buffer
	- Taksi (the taksi_shm_hack)


1. USAGE ======================================================================

Usage:       gLayer2.exe
or           gLayer2.exe <config-file>

First one uses a standard config file "gLayer2.cfg" in the application directory.
Second lets you use a specified config file.


2. JSON CLIENT ================================================================

gLayer2 consists of an internal JSON client that can handle messages for dealing
with compositors. currently available events are:

create                  // enables automatically
enable
disable
changeVP

Below are some reference API calls.
Please note, that the context field "ctx", which must be sent in every
json message can be changed in the config file of gLayer2 (see 1. USAGE).

The API is as follows:

{
	"ctx":          "comp0.in",
    "evt":          "create",
	"listener":     "web",
	"id":           "someUniqueIdOrName",
	"pos":          -1,
	"viewport":     "vp0",
	"uri":          "http://www.google.de",
	"width":        1920,
	"height":       1080,
	"use_alpha":    true,
	"inject_input": false
}

{
    "ctx":          "comp0.in",
	"evt":          "create",
	"listener":     "shm",
	"id":           "someUniqueIdOrName",
	"pos":          -1,          /* pos in compositor chain, -1 is the top end */
	"viewport":     "vp0",       /* viewport. In gLayer2 just let it this way */
	"uri":          "shmName",
	"use_alpha":    true
}

{
    "ctx":     "comp0.in",
	"evt":     "enable",
	"id":      "someUniqueIdOrName"
}

{
    "ctx":      "comp0.in",
	"evt":      "disable",
	"id":       "someUniqueIdOrName"
}

{
    "ctx":      "comp0.in",
    "evt":      "changeVP",
    "id":       "someUniqueIdOrName",
    "viewport": "vp1"                   /* the new viewport to be used */
}


3. GOOD TO KNOW ===============================================================

The Port for remote debugging is usually defined in the WebTexture.hpp in the variable
__awe_remote_debugging_port as 1225 (the number-encoding for the letters "awe") but you
can change it anyway via the config file in the section [MISC] with the variable
awe_remote_debugging_port.
