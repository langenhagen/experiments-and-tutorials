# C++ Refresher 2026
A lil refresher.

A compact, dense demo program that exercises typical modern C++ concepts in one place.

The `main.cpp` flow is intentionally script-like (Python-style): numbered sections
run in order so you can read top-to-bottom and refresh quickly.

## What it demonstrates

- RAII (`ScopedTimer`)
- Move semantics with a custom move-only type (`Buffer`)
- Smart pointers (`std::unique_ptr`, `std::shared_ptr`, `std::weak_ptr`)
- Containers (`std::vector`, `std::array`, `std::unordered_map`)
- String utilities (`std::string`, `std::string_view`, `starts_with`)
- Parsing with `std::from_chars` + `std::optional`
- `std::span` for non-owning views
- `std::variant` + `std::visit`
- Ranges algorithms (`std::ranges::sort`, `std::ranges::count_if`)
- API ownership signaling (`T&`, `const T*`, `unique_ptr`, `shared_ptr`, `weak_ptr`)
- C++20 concepts (`template <std::integral T> ...`)
- Filesystem basics (`std::filesystem::path`, `current_path`)
- Concurrency basics (`std::mutex`, `std::condition_variable`, `std::jthread`)
- Lambdas (inline callables, captures, generic lambda params)
- Construction patterns (`emplace_back`, `try_emplace`, `insert_or_assign`, `piecewise_construct`, `in_place`)
- Iteration styles (index loop, range-for, structured bindings, views pipeline)
- Structured bindings and designated initializers
- C++23 common features (`std::expected`, `std::string::contains`, `std::to_underlying`, `std::byteswap`)

## Feature timeline (standard versions)

- C++11: move semantics, smart pointers, lambdas, range-for, `thread`/`mutex`/`condition_variable`, `unordered_map`, `noexcept`
- C++14: `std::make_unique`, `std::exchange`, generic lambdas (`auto` params)
- C++17: `std::optional`/`std::nullopt`, `std::variant`/`std::visit`/`std::monostate`, `std::string_view`, `std::from_chars`, structured bindings, `std::filesystem`, `if` with initializer
- C++20: `std::span`, ranges/views pipelines, concepts (`std::integral`), `std::jthread`, designated initializers, `std::string::starts_with`
- C++23: `std::expected`, `std::string::contains`, `std::to_underlying`, `std::byteswap`

## Tooling detected on this machine

- `g++ 13.3.0`
- `cmake 3.28.3`
- `ninja 1.11.1`
- `clang++` not found

## Build and run

### Option 1: Script

```bash
./run.sh
```

### Option 2: Manual CMake

```bash
cmake -S . -B build -G Ninja
cmake --build build --parallel
./build/cpp_round_trip
```

## Program layout

- `0 Program intro`: what the file is doing
- `1 Parse input, build tasks, move values`
- `2 Group tasks and inspect with structured bindings`
- `3 Construction patterns`
- `4 Iteration styles`
- `5 Filesystem basics`
- `6 Concepts basics`
- `7 Concurrency basics`
- `8 Common C++23 features`

## CLI option long forms (where available)

Based on current CMake/Ninja docs and local `ninja -h`:

- `cmake -S <src>`: no long form.
- `cmake -B <build>`: no long form.
- `cmake -G <generator>`: no long form.
- `cmake --build <dir>`: already long form.
- `cmake --build <dir> -j <N>` -> `cmake --build <dir> --parallel <N>`.
- `cmake --build <dir> -t <target>` -> `cmake --build <dir> --target <target>`.
- `cmake --build <dir> -v` -> `cmake --build <dir> --verbose`.

Ninja (if you call it directly):

- `ninja -v` -> `ninja --verbose`
- `ninja -C <dir>`: no long form.
- `ninja -f <file>`: no long form.
- `ninja -j/-k/-l/-n/-d/-t/-w`: short forms only.

## Notes

- The project uses CMake and requests C++23.
- Warnings are enabled (`-Wall -Wextra -Wpedantic`) for GCC/Clang.

## Construction quick guide

- `emplace_back(args...)`: constructs in-place in a container; avoids one temporary compared to `push_back(T{...})`.
- `push_back(x)` vs `push_back(std::move(x))`: copy vs move semantics.
- `try_emplace(k, args...)`: only constructs value if key does not exist (often cheaper than `operator[]`+assignment).
- `insert_or_assign(k, v)`: overwrite-or-insert convenience when replacement is intended.
- `make_unique<T>(...)` / `make_shared<T>(...)`: preferred ownership constructors (exception-safe, concise).
- `std::in_place` / `std::in_place_type<T>`: direct construction inside `optional`/`variant` storage.

## Iteration quick guide

- Index loop: good when you need the index and contiguous access.
- Range-for: clean default when index is not needed.
- Structured bindings: best for key/value maps (`for (auto& [k, v] : m)`).
- Views pipeline: lazy filter/transform chains with low allocation overhead.

## Pipelines and `std::views`

- `std::views` (C++20) is a namespace containing range adaptors.
- Adaptors such as `std::views::filter` and `std::views::transform` do not immediately create a new container; they create a lazy view.
- The pipe operator (`|`) composes adaptors left-to-right into a pipeline.
- A pipeline executes when you iterate it (for example in a `for` loop), not when you define it.
- This style is close to Python generator expressions: readable transformations without intermediate lists.

Example from `main.cpp`:

```cpp
auto even = priorities
    | std::views::filter([](int p) { return p % 20 == 0; })
    | std::views::transform([](int p) { return p / 10; });
```

Meaning:

- Start from `priorities`.
- Keep only values divisible by 20.
- Transform each kept value by dividing by 10.
- Materialize results by iterating `even`.

Python analogy:

```python
even = (p // 10 for p in priorities if p % 20 == 0)
for p in even:
    print(p)
```

## Lambda quick guide

- Form: `[capture](params) { body }`; `[]` means capture nothing.
- Generic lambda: use `auto` params (for example `[](const auto& x) { ... }`).
- Capture by value `[x]`: lambda keeps its own copy of `x`.
- Capture by reference `[&x]` or `[&]`: lambda refers to outer variable directly.
- Prefer explicit captures (`[x, &y]`) over broad `[=]`/`[&]` for readability.
- In this project lambdas are used with `views`, `count_if`, and `visit`.

## C++ Modules status

Yes, modules are real (`import` / `export module`) and standardized in C++20.

Practical status in 2026:

- Language support is good in current GCC/Clang/MSVC, but build integration still has rough edges compared to headers.
- CMake supports modules (`FILE_SET CXX_MODULES`) in newer versions; behavior can still vary across compilers.
- For refresher/demo projects, headers are still the most friction-free; modules are great once your toolchain path is stable.
