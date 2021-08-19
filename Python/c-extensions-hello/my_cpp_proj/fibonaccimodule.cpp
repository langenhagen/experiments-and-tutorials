/**
C extension code.

see:
- https://www.youtube.com/watch?v=a65JdvOaygM&ab_channel=DrapsTV
- https://docs.python.org/3/extending/extending.html
*/

/*Python recommends to always define PY_SSIZE_T_CLEAN before including Python.h.*/
#define PY_SSIZE_T_CLEAN

/*Python.h defines some macros that could influence standard-library headers, so Python recommends
to include this before all other header includes.*/
#include <Python.h>

/** A native C/C++ function.*/
int Cfib(const int n) {
    return n < 2 ? n : Cfib(n-1) + Cfib(n-2);
}

/** Wrapper function.
The C function always has two arguments, conventionally named self and args.

The self argument points to the module object for module-level functions; for a method it would
point to the object instance.

The args argument will be a pointer to a Python tuple object containing the arguments. Each item of
the tuple corresponds to an argument in the call’s argument list.
*/
static PyObject* fib(PyObject* self, PyObject* args) {
    int n;
    if (!PyArg_ParseTuple(args, "i", &n)) /*"i" means, parse args as an int.*/
        return nullptr;

    return Py_BuildValue("i", Cfib(n));
}

/**Another wrapper function.*/
static PyObject* version(PyObject* self) {
    return Py_BuildValue("s", "Version 1.0");
}

static PyMethodDef my_methods[] = {
    {"fib", fib, METH_VARARGS, "This is a docstring."},
    {"version", (PyCFunction)version, METH_NOARGS, "returns the module version"},
    {nullptr, nullptr, 0, nullptr}  /*dunno seems important*/
};

static struct PyModuleDef my_module = {
    PyModuleDef_HEAD_INIT,
    "myModule",  /*module name.*/
    "my module docstring",
    -1, /*-1 means global state or so*/
    my_methods
};

PyMODINIT_FUNC PyInit_myModule() {
    PyObject* m = PyModule_Create(&my_module);
    return m;
};
