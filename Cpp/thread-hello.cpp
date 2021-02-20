/**Showcase the usage of std::thread.
 *
 * @author: andreasl
 */
#include <atomic>
#include <cstdlib>
#include <iostream>
#include <thread>
#include <vector>

void show_simple_lambda_threads() {
    std::vector<std::thread> threads;
    std::atomic<unsigned int> n_finished_threads(0);

    for(int i = 0; i < 10; ++i) {
        threads.emplace_back([&n_finished_threads, i]() {
            const auto id = std::this_thread::get_id();
            std::cout << "Hello from thread " << id << " (" << i << "/10)!\n";
            const auto wait_ms = std::chrono::milliseconds(std::rand() % 10000);
            std::this_thread::sleep_for(wait_ms);
            ++n_finished_threads;
            std::cout << "Goodbye from thread " << id << " (" << i << "/10)! after "
                << wait_ms.count() << "\n";
        });
    }

    while(n_finished_threads < 10) {
        std::cout << "Processing..." << std::endl;
        std::this_thread::sleep_for(std::chrono::seconds(1));
    }

    for(auto& thread : threads) {
        thread.join();
    }
}

int main() {
    show_simple_lambda_threads();
    return 0;
}
