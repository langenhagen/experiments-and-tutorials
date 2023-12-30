// Compile/run like
//
//   cargo run
use std::io;

mod custom_module;

fn main() {
    // Get user input
    let mut input = String::new();
    println!("Enter your name: ");
    io::stdin().read_line(&mut input).expect("Failed to read line");
    let name = input.trim();

    // Call a function from custom_module
    let greeting = custom_module::generate_greeting(name);

    // Object instantiation and method calls from custom_module
    let mut calculator = custom_module::Calculator::new(10);
    let result = calculator.add(5);
    let random_number = calculator.generate_random();

    // Print the results
    println!("Greeting: {}", greeting);
    println!("Result of adding 5: {}", result);
    println!("Random number: {}", random_number);
}
