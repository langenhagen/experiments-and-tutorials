//! Showcase Rust lifetimes that can prevent dangling references.
//!
//! Demonstrates Rust lifetimes and their role in preventing dangling references.
//! That's one of those Rust specialties.
//!
//! build like:
//!
//!   rustc lifetimes-hello.rs
//!
//! author: andreasl

// A struct with a lifetime annotation 'a
struct Person<'a> {
    name: &'a str,
}

// A function that takes two Person references and returns a string slice
// with the same as the input persons.
fn greet<'a>(person1: &'a Person, person2: &'a Person) -> &'a str {
    if person1.name == person2.name {
        "Hello, identical twins!"
    } else {
        "Hello, friends!"
    }
}

// A counter-example without lifetimes, will not compile .
// fn greet_no_lifetimes(person1: &Person, person2: &Person) -> &str {
//     if person1.name == person2.name {
//         "Hello, identical twins!"
//     } else {
//         "Hello, friends!"
//     }
// }

// Function that returns a reference to the name of the person with a longer name
fn longer_name<'a, 'b>(person1: &'a Person, person2: &'b Person) -> &'a str
where
    'b: 'a, // basically  'b >= 'a, i.e. lifetime 'b is at least as long as lifetime 'a
{
    if person1.name.len() >= person2.name.len() {
        person1.name
    } else {
        person2.name
    }
}

fn main() {
    // Create two Person instances with different lifetimes
    let alice = Person { name: "Alice" };
    let bob = Person { name: "Bob" };

    let greeting = greet(&alice, &bob);
    println!("Greeting: {}", greeting);

    // Call the longer_name function with references to different lifetimes
    let longer_name = longer_name(&alice, &bob);
    println!("Longer person's name: {}", longer_name);
}
