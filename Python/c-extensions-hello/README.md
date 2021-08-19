# Python C Extensions Hello
A project to showcase basic Python C extensions.

author: andreasl

Based on: https://docs.python.org/3/extending/extending.html

## Prerequisites

You need to have `python-dev`:
```bash
sudo apt install python-dev
```


## Usage
To build the C/C++ extension, call:
```bash
cd my_cpp_proj;
python setup.py build
```

This creates some shared objects under `build/`


Alternatively, you can also call:
```bash
python setup.py install
```

This installs the shared objects directly in your active python environment.

Then copy the `.so` file in the environment for your project. It should be available for import.

E.g.:
```bash
cp 'my_cpp_proj/build/lib.linux-x86_64-3.9/myModule.cpython-39-x86_64-linux-gnu.so' 'my_python_proj/'
```

Then, just call:
```bash
cd my_python_proj/
python main.py
```


You can of course also just spawn a normal python shell and import the module nicely.
