# Rust Hello World
The first program that I, Andreas Langenahge , have written in the Rust programming language.


Related common and helpful commands and tools:
```bash
# Rust compiler

# Rust project management tool
rustc main.rs  # invoke the rust comiler, only works in this simple form with simple programs
cargo new my_project  # create an empty scaffold for a my_project
cargo build  # build via the higher level build system and package management tool `cargo`
cargo run  # run your build

# rustup ist the toolchain installer and manager, comparable with Python's `pyenv`
rustup
rustup install stable  # add the `stable` toolchain
rustup update

rustfmt myfile.rs  # format the given file
cargo fmt  # format the current directory

# clippy is a linter for Rust that provides helpful suggestions and detects common issues.
# It is an official extension for the Rust compiler.
rustup component add clippy  # install clippy
cargo clippy  # run clippy

# cargo-audit is a tool that checks your dependencies for known vulnerabilities
# using the RustSec Advisory Database.
cargo install cargo-audit  # isntall cargo-audit
cargo audit  # run audit on you current project


# cargo-deadlinks: check the documentation for broken links.
cargo install cargo-deadlinks
cargo install cargo-deadlinks
cargo deadlinks
```
