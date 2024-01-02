//! Showcase the Rust ownership - & borrowing principles.
//!
//! build like:
//!
//!   rustc ownership-hello.rs
//!
//! author: andreasl

struct Person {
    name: String,
}

// A function that takes ownership of a Person and returns the name.
// The ownership of the String "name" is transferred to this function.
fn take_ownership(person: Person) -> String {
    person.name
}

// A function that borrows a reference to a Person and returns the name.
// Return a reference to the String "name" without taking ownership.
fn borrow_reference(person: &Person) -> &String {
    &person.name
}

// Demonstrate ownership transfer.
fn transfer_ownership() {
    let alice = Person {
        name: String::from("Alice"),
    };

    take_ownership(alice); // moves ownership

    // Uncommenting the line below would result in a compile-time error.
    // println!("Alice's name: {}", alice.name); // Error: value borrowed after move
}

// Demonstrate borrowing a reference.
fn borrowing() {
    let bob = Person {
        name: String::from("Bob"),
    };
    let bob_reference = borrow_reference(&bob);

    // Since we borrow a reference, we can still use Bob afterwards.
    println!("Bob's name (borrowed): {}", bob_reference);
}

fn main() {
    transfer_ownership();
    borrowing();
}
