//! Showcase the use of traits.
//!
//! Traits in Rust are a mechanism for defining shared behavior by specifying a set of method
//! signatures that types can implement, enabling code reuse and polymorphism. Think C++ interfaces,
//! Go interfaces or Python Protocols.
//!
//! use like
//!
//!   rustc traits-hello.rs && ./traits-hello
//!
//! author: andreasl

// A trait named Printable.
trait Printable {
    fn print(&self);
}

struct Dog;

// Implement the Printable trait for the type Dog.
impl Printable for Dog {
    fn print(&self) {
        println!("This is a dog");
    }
}

// Function that accepts any type implementing Printable
fn print_something<T: Printable>(thing: T) {
    thing.print();
}

fn main() {
    let my_dog = Dog;
    print_something(my_dog);
}
