"""
Playground for a marquee-like text effect.

E.g.:

A string that reads "Scanning..." is 11 characters long and has 22 variations:
"S          "
"Sc         "
"Sca        "
"Scan       "
"Scann      "
"Scanni     "
"Scannin    "
"Scanning   "
"Scanning.  "
"Scanning.. "
"Scanning..."
" canning..."   # < here the seond for-loop kicks in
"  anning..."
"   nning..."
"    ning..."
"     ing..."
"      ng..."
"       g..."
"        ..."
"         .."
"          ."
"           "
"""


def make_marquee_generator(text: str):
    """Yield a marquee-like text effect version of the given string."""
    while True:
        for i in range(len(text)):
            yield text[:i].ljust(len(text))
        for i in range(len(text)):
            yield text[i:].rjust(len(text))


s = "Scanning..."

print("---1---")
for c in make_marquee_generator(s):
    print(c)


print("---2---")
gen = make_marquee_generator(s)  # creates a generator-object
t = gen.send(None)  # a generator object is initially invoked with send(None)
print(f"+ {t}")
for i in range(3000000000):
    t = gen.send(i)  # later, send() is called with an integer
    print(f"> {t}")
