# Hello Trace Module
Showcase Python's built-in `trace` module to track program execution, code coverage report
generation, and printing calling relationships between functions.

Powerful for debugging.

Trace can do much more than I cover here. You can also use its functions from within Python.

Track print lines of code as they get executed; think `set -x` in Bash:
```bash
python3 -m trace --trace main.py
```

Generate code coverage files, `main.cover` and `hello.cover`:
```bash
python3 -m trace --count main.py
```

List functions called:
```bash
python3 -m trace --listfuncs main.py
```

Track function call relationships:
```bash
python3 -m trace --trackcalls main.py
```

See also: https://pymotw.com/3/trace/
