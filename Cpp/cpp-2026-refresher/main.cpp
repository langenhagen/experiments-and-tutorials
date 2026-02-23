#include <algorithm>
#include <array>
#include <charconv>
#include <chrono>
#include <condition_variable>
#include <concepts>
#include <cstddef>
#include <cstdint>
#include <filesystem>
#include <iomanip>
#include <iostream>
#include <map>
#include <memory>
#include <mutex>
#include <numeric>
#include <optional>
#include <queue>
#include <ranges>
#include <span>
#include <string>
#include <string_view>
#include <thread>
#include <tuple>
#include <unordered_map>
#include <utility>
#include <variant>
#include <vector>

#if __cplusplus >= 202302L && __has_include(<expected>)
#include <expected>
#define CPP23_HAS_EXPECTED 1
#else
#define CPP23_HAS_EXPECTED 0
#endif

///////////////////////////////////////////////////////////////////////////////
// PROGRAM STYLE NOTE
// This file is intentionally written like a step-by-step "script".
// main() runs numbered sections in sequence so it reads like your Python example.
///////////////////////////////////////////////////////////////////////////////

///////////////////////////////////////////////////////////////////////////////
// C++ SYNTAX QUICK MAP (PYTHON-TO-C++ REFRESHER)
// - T x{...};             -> brace-init (uniform initialization)
// - explicit Ctor(...)    -> prevent implicit conversions
// - noexcept (C++11)      -> function promises not to throw
// - const                 -> read-only object/reference
// - & / *                 -> reference / pointer
// - auto (C++11)          -> compiler-deduced type
// - ->                    -> member access through pointer
// - ::                    -> namespace/class scope qualifier
// - <...>                 -> template arguments (generic parameters)
///////////////////////////////////////////////////////////////////////////////

///////////////////////////////////////////////////////////////////////////////
// FEATURE TIMELINE (WHAT THIS FILE USES)
// C++11: move semantics, smart pointers, lambdas, range-for, thread/mutex/cv,
//         unordered_map, noexcept, constexpr basics, enum class-era style APIs
// C++14: std::make_unique, std::exchange, generic lambdas (auto params)
// C++17: std::optional/nullopt, std::variant/visit/monostate, string_view,
//         from_chars, structured bindings, filesystem, if-with-initializer,
//         try_emplace/insert_or_assign, std::clamp
// C++20: std::span, ranges/views pipelines, concepts (std::integral),
//         std::jthread, designated initializers, string::starts_with
// C++23: std::expected, string::contains, std::to_underlying,
//         std::byteswap, more ranges adaptors/utilities
///////////////////////////////////////////////////////////////////////////////

///////////////////////////////////////////////////////////////////////////////
// MINI GLOSSARY (FIRST-READ REFRESHER)
// - std::move(x) [C++11]: casts x to rvalue so move operations are considered.
//   It does not move by itself; receiving code performs the move.
// - lvalue: named object with stable identity (you can keep using it normally).
// - rvalue: temporary/expiring value (safe candidate to steal resources from).
// - significance of std::move: often avoids deep copies by transferring internals
//   (buffer pointers, heap ownership, etc.). This is key for performance and for
//   move-only types such as unique_ptr.
// - after move: object is valid but content is unspecified; destroy, assign, or
//   reinitialize it before relying on its value.
// - std::exchange(obj, new_value) [C++14]: set obj=new_value, return old value.
//   Great for move ctors/assignments to "take old, leave safe state".
// - std::optional<T> [C++17]: value can exist or be absent.
// - std::nullopt [C++17]: explicit "no value" marker for optional.
// - std::span<T> [C++20]: non-owning view over contiguous memory.
// - std::string_view [C++17]: non-owning read-only string view.
// - std::variant<A,B,...> [C++17]: tagged union (one active type at a time).
// - std::monostate [C++17]: explicit empty alternative for variant.
// - std::visit(fn, v) [C++17]: call fn with variant's currently active value.
// - std::filesystem::path [C++17]: path object for cross-platform FS APIs.
// - std::mutex + std::condition_variable [C++11]: thread coordination tools.
// - concept [C++20]: named compile-time constraint for templates.
// Python analogy rough map:
// - optional<T> ~ T | None, nullopt ~ None
// - variant<Ts...> ~ Union[T1, T2, ...] with runtime active tag
// - span/string_view ~ non-owning view/slice
// - unique_ptr/shared_ptr/weak_ptr ~ owning/shared/weak references
///////////////////////////////////////////////////////////////////////////////

///////////////////////////////////////////////////////////////////////////////
// SMALL OUTPUT HELPER
///////////////////////////////////////////////////////////////////////////////

void print_section(std::string_view title) {
  std::cout << "\n--- " << title << " ---\n\n";
}

///////////////////////////////////////////////////////////////////////////////
// CORE TYPES: RAII + MOVE-ONLY BUFFER
///////////////////////////////////////////////////////////////////////////////

class ScopedTimer {
 public:
  // explicit blocks accidental implicit conversion from const char*.
  explicit ScopedTimer(std::string label)
      // std::move enables move-construction of the string member.
      : label_(std::move(label)), start_(std::chrono::steady_clock::now()) {}

  ~ScopedTimer() {
    using namespace std::chrono;
    const auto elapsed = duration_cast<microseconds>(steady_clock::now() - start_);
    // RAII in action: destructor runs automatically at scope exit.
    std::cout << "[timer] " << label_ << ": " << elapsed.count() << " us\n";
  }

 private:
  std::string label_;
  std::chrono::steady_clock::time_point start_;
};

class Buffer {
 public:
  // move semantics
  // explicit prevents accidental Buffer b = 4 conversions.
  explicit Buffer(std::size_t n)
      : data_(std::make_unique<int[]>(n)), size_(n) {
    // unique_ptr<T[]> owns heap array and frees it automatically.
    // Python contrast: destruction timing is deterministic via RAII.
    std::iota(data_.get(), data_.get() + static_cast<std::ptrdiff_t>(size_), 1);
  }

  // Move-only type for explicit ownership transfer demo.
  Buffer(const Buffer&) = delete;
  Buffer& operator=(const Buffer&) = delete;

  // noexcept matters: containers prefer noexcept moves on reallocation.
  Buffer(Buffer&& other) noexcept
      // exchange returns old value and assigns a safe replacement.
      : data_(std::exchange(other.data_, nullptr)),
        size_(std::exchange(other.size_, 0)) {}

  Buffer& operator=(Buffer&& other) noexcept {
    if (this != &other) {
      data_ = std::exchange(other.data_, nullptr);
      size_ = std::exchange(other.size_, 0);
    }
    return *this;
  }

  int& operator[](std::size_t i) { return data_[i]; }
  int operator[](std::size_t i) const { return data_[i]; }
  std::size_t size() const { return size_; }

  // span is a non-owning view, like "borrowed slice" semantics.
  std::span<const int> view() const { return {data_.get(), size_}; }

 private:
  std::unique_ptr<int[]> data_;
  std::size_t size_{};
};

struct TaskInput {
  // string_view avoids copies; caller must keep backing storage alive.
  std::string_view name;
  int priority;
  std::string_view group;
};

struct Task {
  std::string name;
  int priority{};
  std::string group;
  Buffer samples{1};

  // std::variant/visit/monostate
  // variant stores one active alternative at a time.
  // monostate is explicit "no note yet".
  std::variant<std::monostate, int, std::string> note;
};

using TaskPtr = std::shared_ptr<Task>;
// smart pointers, unordered_map
using TaskBuckets = std::unordered_map<std::string, std::vector<TaskPtr>>;

///////////////////////////////////////////////////////////////////////////////
// API DESIGN EXAMPLES (OWNERSHIP + PARAMETER STYLE)
///////////////////////////////////////////////////////////////////////////////

// T& means required non-null mutable parameter.
void bump_priority(Task& task, int delta) {
  task.priority += delta;
}

// const T* means optional/nullable non-owning return.
// Return nullptr when no match exists.
const Task* find_first_by_group(const TaskBuckets& by_group, std::string_view group) {
  auto it = by_group.find(std::string(group));
  if (it == by_group.end() || it->second.empty()) {
    return nullptr;
  }
  return it->second.front().get();
}

// concepts (std::integral)
// C++20 concept: template works only for integral types.
template <std::integral T>
T sum_integral(std::span<const T> values) {
  return std::accumulate(values.begin(), values.end(), static_cast<T>(0));
}

class WorkQueue {
 public:
  // thread/mutex/cv
  void push(int value) {
    std::lock_guard<std::mutex> lock(mu_);
    q_.push(value);
    cv_.notify_one();
  }

  void close() {
    std::lock_guard<std::mutex> lock(mu_);
    closed_ = true;
    cv_.notify_all();
  }

  std::optional<int> pop() {
    std::unique_lock<std::mutex> lock(mu_);
    cv_.wait(lock, [&] { return closed_ || !q_.empty(); });
    if (q_.empty()) {
      return std::nullopt;
    }
    const int value = q_.front();
    q_.pop();
    return value;
  }

 private:
  std::mutex mu_;
  std::condition_variable cv_;
  std::queue<int> q_;
  bool closed_ = false;
};

///////////////////////////////////////////////////////////////////////////////
// PARSING + HELPER FUNCTIONS
///////////////////////////////////////////////////////////////////////////////

std::optional<int> to_int(std::string_view text) {
  // std::optional/nullopt, string_view, from_chars
  // from_chars is non-allocating and locale-independent.
  int value = 0;
  const char* first = text.data();
  const char* last = text.data() + static_cast<std::ptrdiff_t>(text.size());
  const auto [ptr, ec] = std::from_chars(first, last, value);

  // Structured binding above is like tuple unpacking in Python.
  // std::errc{} and std::errc() are equivalent here ("no error").
  if (ec != std::errc{} || ptr != last) {
    return std::nullopt;
  }
  return value;
}

std::optional<TaskInput> parse_line(std::string_view line) {
  // string_view parsing avoids temporary allocations while slicing.
  const auto c1 = line.find(',');
  if (c1 == std::string_view::npos) {
    return std::nullopt;
  }

  const auto c2 = line.find(',', c1 + 1);
  if (c2 == std::string_view::npos) {
    return std::nullopt;
  }

  const auto name = line.substr(0, c1);
  const auto priority_text = line.substr(c1 + 1, c2 - c1 - 1);
  const auto group = line.substr(c2 + 1);

  auto priority = to_int(priority_text);
  if (!priority) {
    return std::nullopt;
  }
  return TaskInput{name, *priority, group};
}

int checksum(std::span<const int> values) {
  return std::accumulate(values.begin(), values.end(), 0);
}

std::vector<Task> build_demo_tasks() {
  constexpr std::array<std::string_view, 6> lines = {
      "build-core,5,core",
      "test-smoke,3,qa",
      "package,4,ops",
      "deploy,2,ops",
      "bench,6,core",
      "broken_line"
  };

  std::vector<Task> tasks;
  tasks.reserve(lines.size());

  // range-for
  for (std::string_view line : lines) {
    auto parsed = parse_line(line);
    if (!parsed) {
      std::cout << "skip invalid: " << line << "\n";
      continue;
    }

    Buffer samples(8);
    for (std::size_t i = 0; i < samples.size(); ++i) {
      samples[i] *= parsed->priority;
    }

    // designated initializers
    Task task{
        .name = std::string(parsed->name),
        .priority = parsed->priority,
        .group = std::string(parsed->group),
        .samples = std::move(samples),
        .note = std::monostate{}
    };

    // string::starts_with
    if (task.name.starts_with("build")) {
      task.note = std::string("hot path");
    } else if (task.priority >= 5) {
      task.note = task.priority * 10;
    }

    tasks.push_back(std::move(task));
  }

  return tasks;
}

std::string_view lane_name(int priority) {
  constexpr std::array<std::string_view, 7> lane = {
      "none", "tiny", "low", "medium", "high", "critical", "pager"
  };
  // std::clamp
  return lane[std::clamp(priority, 0, static_cast<int>(lane.size() - 1))];
}

// enum class-era style APIs
enum class BuildState : std::uint8_t {
  queued = 1,
  running = 2,
  done = 3
};

#if CPP23_HAS_EXPECTED
// std::expected
std::expected<int, std::string> parse_non_negative(std::string_view text) {
  auto value = to_int(text);
  if (!value) {
    return std::unexpected("not an integer");
  }
  if (*value < 0) {
    return std::unexpected("must be non-negative");
  }
  return *value;
}
#endif

///////////////////////////////////////////////////////////////////////////////
// SECTION 1: PARSE INPUT + MOVE + SORT
///////////////////////////////////////////////////////////////////////////////

void section_1_parse_move_sort() {
  print_section("1 Parse input, build tasks, move values");

  // constexpr basics, move semantics
  // constexpr means compile-time constant when possible.
  constexpr int demo_line_count = 6;

  Buffer scratch(4);
  // scratch is an lvalue (named object).
  // std::move(scratch) turns it into an rvalue expression so Buffer's move
  // constructor can transfer ownership instead of copying.
  Buffer relocated = std::move(scratch);
  std::cout << "scratch.size=" << scratch.size()
            << ", relocated.size=" << relocated.size() << "\n";
  std::cout << "demo lines configured=" << demo_line_count << "\n";

  auto tasks = build_demo_tasks();

  // Projection (&Task::priority) is similar to Python key=lambda t: t.priority.
  std::ranges::sort(tasks, std::greater{}, &Task::priority);
  std::cout << "tasks parsed=" << tasks.size() << "\n";
}

///////////////////////////////////////////////////////////////////////////////
// SECTION 2: OWNERSHIP + GROUPING + VARIANT VISIT
///////////////////////////////////////////////////////////////////////////////

void section_2_group_and_visit() {
  print_section("2 Group tasks and inspect with structured bindings");

  auto tasks = build_demo_tasks();
  std::ranges::sort(tasks, std::greater{}, &Task::priority);

  TaskBuckets by_group;
  for (auto& task : tasks) {
    bump_priority(task, 0);  // Example T& API usage.

    // make_shared builds object + control block in one allocation.
    auto ptr = std::make_shared<Task>(std::move(task));
    by_group[ptr->group].push_back(ptr);
  }
  tasks.clear();

  std::weak_ptr<Task> focused;
  // if-with-initializer, structured bindings
  // if-with-initializer keeps iterator scope local to the if statement.
  if (auto it = by_group.find("core"); it != by_group.end() && !it->second.empty()) {
    focused = it->second.front();
  }

  // Python analogy: for group, bucket in by_group.items():
  for (const auto& [group, bucket] : by_group) {
    // const auto& [group, bucket]: structured binding + by-reference no copy.
    std::cout << "group=" << group << " (" << bucket.size() << ")\n";

    for (const auto& task : bucket) {
      std::cout << "  - " << task->name
                << " priority=" << task->priority
                << " lane=" << lane_name(task->priority)
                << " checksum=" << checksum(task->samples.view());

      // lambdas (generic lambdas (auto params) via const auto&)
      std::visit(
          [&](const auto& value) {
            // decay_t removes refs/cv qualifiers for stable type checks.
            using T = std::decay_t<decltype(value)>;
            if constexpr (std::is_same_v<T, std::monostate>) {
              std::cout << " note=(none)";
            } else {
              std::cout << " note=" << value;
            }
          },
          task->note);

      std::cout << "\n";
    }
  }

  const auto hot_count = std::ranges::count_if(
      by_group["ops"],
      [](const TaskPtr& task) { return task->priority >= 4; });
  std::cout << "ops tasks with priority>=4: " << hot_count << "\n";

  if (auto locked = focused.lock()) {
    std::cout << "focused: " << locked->name << " (use_count=" << locked.use_count() << ")\n";
  }

  // const T* API means optional nullable non-owning return.
  if (const Task* core_head = find_first_by_group(by_group, "core")) {
    std::cout << "core_head=" << core_head->name << "\n";
  }

  std::cout << "groups formed=" << by_group.size() << "\n";
}

///////////////////////////////////////////////////////////////////////////////
// SECTION 3: CONSTRUCTION PATTERNS
///////////////////////////////////////////////////////////////////////////////

void section_3_construction_patterns() {
  print_section("3 Construction patterns");

  std::vector<std::string> labels;
  labels.reserve(4);
  labels.emplace_back("alpha");  // In-place construction.

  std::string name = "beta";
  labels.push_back(name);             // Copy.
  labels.push_back(std::move(name));  // Move from named object.
  std::cout << "name.size after move=" << name.size() << "\n";

  // unordered_map
  std::unordered_map<std::string, Task> index;
  // unordered_map behaves like hash dict (average O(1) lookup).
  // try_emplace/insert_or_assign
  index.try_emplace(
      "build",
      Task{.name = "build", .priority = 6, .group = "core", .samples = Buffer(4), .note = 60});
  index.insert_or_assign(
      "build",
      Task{.name = "build", .priority = 7, .group = "core", .samples = Buffer(4), .note = 70});

  std::map<std::string, int> counters;
  // map is tree-based and key-ordered (O(log n)).
  // piecewise_construct passes key/value constructor args separately.
  // For this tiny case, emplace("core", 1) is simpler, but this shows the pattern.
  counters.emplace(
      std::piecewise_construct,
      std::forward_as_tuple("core"),
      std::forward_as_tuple(1));
  counters.try_emplace("ops", 2);

  // std::make_unique
  auto from_heap = std::make_unique<Task>(
      Task{.name = "heap", .priority = 5, .group = "ops", .samples = Buffer(3), .note = std::string("heap obj")});
  std::optional<TaskInput> ad_hoc{std::in_place, TaskInput{"adhoc", 8, "core"}};
  std::variant<int, std::string> info{std::in_place_type<std::string>, "constructed in-place"};

  std::cout << "heap=" << from_heap->name
            << ", optional.priority=" << ad_hoc->priority
            << ", counters.size=" << counters.size() << "\n";

  std::visit([](const auto& v) { std::cout << "variant=" << v << "\n"; }, info);
}

///////////////////////////////////////////////////////////////////////////////
// SECTION 4: ITERATION STYLES
///////////////////////////////////////////////////////////////////////////////

void section_4_iteration_styles() {
  print_section("4 Iteration styles");

  std::array<int, 6> priorities = {1, 2, 3, 4, 5, 6};

  std::cout << "index loop: ";
  for (std::size_t i = 0; i < priorities.size(); ++i) {
    std::cout << "[" << i << ":" << priorities[i] << "] ";
  }
  std::cout << "\n";

  for (int& p : priorities) {
    p *= 10;
  }

  // range-for
  std::cout << "range loop: ";
  for (int p : priorities) {
    std::cout << p << " ";
  }
  std::cout << "\n";

  const std::map<std::string, int> counters{{"core", 1}, {"ops", 2}};
  std::cout << "structured bindings: ";
  for (const auto& [k, v] : counters) {
    std::cout << k << "=" << v << " ";
  }
  std::cout << "\n";

  // ranges/views pipelines
  std::cout << "views pipeline (even/10): ";
  // std::views (C++20) is a namespace of range adaptors.
  // Adaptors like filter/transform build lazy non-owning views over a range.
  // The pipe operator composes adaptors left-to-right, similar to data pipelines.
  // Work happens only when iterating, so this avoids intermediate containers.
  auto even = priorities
      | std::views::filter([](int p) { return p % 20 == 0; })
      | std::views::transform([](int p) { return p / 10; });

  // more ranges adaptors/utilities (C++23 adds more of these)
  for (int p : even) {
    std::cout << p << " ";
  }
  std::cout << "\n";
}

///////////////////////////////////////////////////////////////////////////////
// SECTION 5: FILESYSTEM BASICS
///////////////////////////////////////////////////////////////////////////////

void section_5_filesystem_basics() {
  print_section("5 Filesystem basics");

  // filesystem
  namespace fs = std::filesystem;
  const fs::path cwd = fs::current_path();
  std::cout << "cwd filename=" << cwd.filename().string() << "\n";
}

///////////////////////////////////////////////////////////////////////////////
// SECTION 6: CONCEPT-CONSTRAINED TEMPLATE
///////////////////////////////////////////////////////////////////////////////

void section_6_concepts_basics() {
  print_section("6 Concepts basics");

  // std::span
  const std::array<int, 5> values = {1, 2, 3, 4, 5};
  const int total = sum_integral<int>(values);
  std::cout << "sum_integral total=" << total << "\n";
}

///////////////////////////////////////////////////////////////////////////////
// SECTION 7: CONCURRENCY BASICS
///////////////////////////////////////////////////////////////////////////////

void section_7_concurrency_basics() {
  print_section("7 Concurrency basics");

  WorkQueue queue;

  // thread/mutex/cv, std::jthread
  // jthread auto-joins on destruction (RAII thread lifetime).
  std::jthread producer([&queue] {
    for (int i = 1; i <= 5; ++i) {
      queue.push(i);
    }
    queue.close();
  });

  std::vector<int> received;
  while (auto value = queue.pop()) {
    received.push_back(*value);
  }

  const int total = std::accumulate(received.begin(), received.end(), 0);
  std::cout << "queued items=" << received.size() << " total=" << total << "\n";
}

///////////////////////////////////////////////////////////////////////////////
// SECTION 8: COMMON C++23 FEATURES
///////////////////////////////////////////////////////////////////////////////

void section_8_cpp23_features() {
  print_section("8 Common C++23 features");

  const std::string title = "cpp_round_trip";
#if defined(__cpp_lib_string_contains) && __cpp_lib_string_contains >= 202011L
  // string::contains
  std::cout << "string.contains(\"trip\")=" << title.contains("trip") << "\n";
#else
  std::cout << "string.contains unavailable in this stdlib build\n";
#endif

#if CPP23_HAS_EXPECTED
  // expected<T, E> is a value-or-error result type.
  // Python analogy: returning (value, error) but encoded in one typed object.
  auto ok = parse_non_negative("42");
  auto bad = parse_non_negative("-7");
  if (ok) {
    std::cout << "expected ok value=" << *ok << "\n";
  }
  if (!bad) {
    std::cout << "expected error=" << bad.error() << "\n";
  }
#else
  std::cout << "std::expected unavailable in this stdlib build\n";
#endif

#if defined(__cpp_lib_to_underlying) && __cpp_lib_to_underlying >= 202102L
  // std::to_underlying
  const int state_raw = static_cast<int>(std::to_underlying(BuildState::running));
  std::cout << "to_underlying(BuildState::running)=" << state_raw << "\n";
#else
  const int state_raw = static_cast<int>(BuildState::running);
  std::cout << "to_underlying unavailable, static_cast fallback=" << state_raw << "\n";
#endif

#if defined(__cpp_lib_byteswap) && __cpp_lib_byteswap >= 202110L
  // std::byteswap
  const std::uint32_t original = 0x11223344u;
  const std::uint32_t swapped = std::byteswap(original);
  std::cout << "byteswap 0x" << std::hex << original
            << " -> 0x" << swapped << std::dec << "\n";
#else
  std::cout << "std::byteswap unavailable in this stdlib build\n";
#endif
}

///////////////////////////////////////////////////////////////////////////////
// MAIN ROUND TRIP FLOW (SCRIPT-LIKE)
///////////////////////////////////////////////////////////////////////////////

int main() {
  ScopedTimer timer("round-trip");

  std::cout << "This is a dense modern C++ refresher, organized as numbered sections.\n";

  section_1_parse_move_sort();
  section_2_group_and_visit();
  section_3_construction_patterns();
  section_4_iteration_styles();
  section_5_filesystem_basics();
  section_6_concepts_basics();
  section_7_concurrency_basics();
  section_8_cpp23_features();

  std::cout << "Round-trip complete.\n";
  return 0;
}
