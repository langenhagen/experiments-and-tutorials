use rand::Rng;

// Function in the module
pub fn generate_greeting(name: &str) -> String {
    format!("Hello, {}!", name)
}

// Struct and implementation in the module
pub struct Calculator {
    value: i32,
}

impl Calculator {
    pub fn new(initial: i32) -> Calculator {
        Calculator { value: initial }
    }

    pub fn add(&mut self, number: i32) -> i32 {
        self.value += number;
        self.value
    }

    /// Generates a random integer between 1 and 100 (inclusive).
    ///
    /// rand::thread_rng(): Get a handle to a thread-local random number generator.
    /// .gen_range(1..=100): Generate a random integer within the inclusive range, similar to
    /// std::uniform_int_distribution<int>(1, 100) in C++.
    pub fn generate_random(&self) -> i32 {
        // the /function/ gen_range comes with `use run::Rng`. seems I don't have to import anything
        // for `rand::thread_rng()` though. Weird.
        //
        // Acc. to GPT3.5:
        // - Functions and items directly attached to a crate (like thread_rng in the rand crate)
        //   can be used without explicit import.
        // - For traits, methods, or types from traits, explicit import is generally required to
        //   bring them into scope. This helps prevent naming conflicts and ensures clarity.
        // - Traits in Rust define shared behavior by specifying a set of method signatures that
        //   types can implement for code reuse and polymorphism. Think Python Protocols.
        rand::thread_rng().gen_range(1..=100)
    }
}
