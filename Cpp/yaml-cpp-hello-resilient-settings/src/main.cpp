/*Experiment on a resilient approach to retrieving settings from a yaml file.

- build like:
    mkdir -p build; cd build; cmake .. && cmake --build .

author: andreasl*/
#include <fstream>
#include <experimental/filesystem>
#include <iostream>
#include <string>
#include <unordered_map>
#include <vector>

#include <yaml-cpp/yaml.h>

namespace fs = std::experimental::filesystem;

enum class MyEnum {
    ONE,
    TWO,
    THREE
};

struct MySettings {
    std::string name;
    bool flag;
    int num;
    std::vector<MyEnum> vec;
};

struct MyDefaults {
    const std::string name = "Andreas";
    constexpr static const bool flag = true;
    constexpr static const int num = 42;
    const std::vector<std::string> vec = {
        "ONE",
        "THREE",
        "TWO"
    };
};

/*Write the default settings to the given file paths.*/
void write_default_settings(const fs::path& path) {
    const auto directory(fs::path(path).remove_filename());
    try {
        fs::create_directories(directory);
    } catch (const std::exception& e) {
        std::cerr << "Could not create directory " << directory << ":\n" << e.what() << std::endl;
        exit(1);
    }
    YAML::Node node;
    const MyDefaults defaults;
    node["name"] = defaults.name;
    node["flag"] = defaults.flag;
    node["num"] = defaults.num;
    for (const std::string& item : defaults.vec) {
        node["vec"].push_back(item);
    }

    std::ofstream out(path);
    out << node;
    if (!out) {
        std::cerr << "Could not write settings to file: " << path << std::endl;
        exit(1);
    }
}

MySettings yaml_to_settings(const YAML::Node& node, const MyDefaults& defaults) {
    MySettings settings;
    settings.name = node["name"].as<decltype(settings.name)>(defaults.name);
    settings.flag = node["flag"].as<decltype(settings.flag)>(defaults.flag);
    settings.num = node["num"].as<decltype(settings.num)>(defaults.num);
    const std::unordered_map<std::string, MyEnum> str_to_enum {
        {"ONE", MyEnum::ONE},
        {"TWO", MyEnum::TWO},
        {"THREE", MyEnum::THREE}
    };
    std::vector<std::string> str_vec = node["vec"].as<std::vector<std::string>>(defaults.vec);
    for (const std::string& item : str_vec) {
        try {
            const MyEnum val = str_to_enum.at(item);
            settings.vec.emplace_back(val);
        } catch (const std::out_of_range& e) {
            std::cerr << "Warning: string value not in enum \"" << item << "\"" << std::endl;
        }
    }
    return settings;
}

static MySettings load_settings(const fs::path& path) {
    try {
        const YAML::Node node = YAML::LoadFile(path);
        return yaml_to_settings(node, MyDefaults());
    } catch (const YAML::BadFile& e) {
        std::cerr << "Error: Bad file: " << e.what() << "\n";
        write_default_settings(path);
        return load_settings(path);
    } catch (const YAML::ParserException& e) {
        std::cerr << "Error: Broken file: \n" << e.what() << "\n";
        exit(1);
    }
}

int main(int argc, const char* argv[]) {
    std::cout << "Hello!\n" << std::endl;

    MySettings s = load_settings(fs::current_path() / "settings.yaml");

    std::cout << "Settings: \n"
        << "name: " << s.name << "\n"
        << "flag: " << s.flag << "\n"
        << "num: " << s.num << "\n"
        << "vec: [ ";
    for(const auto& item : s.vec) {
        std::cout << static_cast<int>(item) << ", ";
    }
    std::cout << "]" << std::endl;

    std::cout << "\nBye!" << std::endl;
    return 0;
}
