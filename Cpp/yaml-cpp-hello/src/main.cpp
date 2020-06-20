/*Showcase usage of the external library yaml-cpp.

- see:
    - https://github.com/jbeder/yaml-cpp/
    - https://github.com/jbeder/yaml-cpp/wiki/Tutorial
    - http://docs.ros.org/diamondback/api/yaml_cpp/html/classYAML_1_1Node.html

- build like:
    mkdir -p build; cd build; cmake .. && cmake --build .

- run from project root in order to get the cwd right

author: andreasl*/
#include <ctime>  /*tm, strftime()*/
#include <fstream>
#include <iomanip> /*get_time()*/
#include <iostream>
#include <string>
#include <sstream>

#include <yaml-cpp/yaml.h>

/*Simple datetime structure that can parse and output simple datetime strings.*/
struct DateTime {
    int year;           /*4 digit year, e.g. 2020.*/
    int month;          /*Number of mont in [1, 12].*/
    int day_of_month;   /*Number of day in [1, 31].*/
    int hour;           /*Hour of day in 24-hours in [0, 23].*/
    int minute;         /*Minute of hour in [0, 59].*/

    explicit DateTime(int year_, int month_, int day_of_month_, int hour_ = 0, int minute_ = 0)
        : year(year_), month(month_), day_of_month(day_of_month_), hour(hour_), minute(minute_)
    {}

    /*Constructor*/
    explicit DateTime(const std::string& str, const char* fmt = "%Y-%m-%d %H:%M") {
        std::stringstream ss(str);
        std::tm tm;
        ss >> std::get_time(&tm, fmt);

        this->year = tm.tm_year + 1900;
        this->month = tm.tm_mon + 1;
        this->day_of_month = tm.tm_mday;
        this->hour = tm.tm_hour;
        this->minute = tm.tm_min;
    }

    /*Convert to std::tm struct.*/
    std::tm tm() const {
        std::tm tm;
        tm.tm_year = this->year - 1900;
        tm.tm_mon = this->month - 1;
        tm.tm_mday = this->day_of_month;
        tm.tm_hour = this->hour;
        tm.tm_min = this->minute;
        return tm;
    }

    /*Convert to c string.*/
    void c_str(char* str, const size_t count, const char* fmt = "%Y-%m-%d %H:%M") const {
        std::tm tm(this->tm());
        std::strftime(str, count, fmt, &tm);
    }

    /*Convert to string.*/
    std::string str(const char* fmt = "%Y-%m-%d %H:%M") const {
        char dt_c_str[100];
        this->c_str(dt_c_str, sizeof(dt_c_str), fmt);
        return std::string(dt_c_str);
    }
};

static void load_yaml_1() {
    std::cout << "---\nVery simple test:\n";

    const YAML::Node& primes = YAML::Load("[2, 3, 5, 7, 11]");
    for(const auto& prime : primes) {
        std::cout << prime.as<int>() << ", ";
    }

    std::cout << "\nnonexisting: " << primes["nonexisting"].as<std::string>("some default");
    std::cout << std::endl;
}

static void load_yaml_vector() {
    std::cout << "---\nVery simple test but parse the vector directly:\n";

    const YAML::Node& primes = YAML::Load("[2, 3, 5, 7, 11]");
    std::vector<int> ints = primes.as<std::vector<int>>();

    for( const auto& i : ints) {
        std::cout << ">> " << i << std::endl;
    }


}

static void load_yaml_2() {
    std::cout << "---\nLoading from file:\n";

    const YAML::Node& node = YAML::LoadFile("my settings.yaml");
    std::cout << "editor: " << node["editor"] << std::endl;
    std::cout << "open_browser_command: " << node["open_browser_command"] << std::endl;

    const auto& sequence_node = node["add_bookmark_sequence"];
    std::cout << "sequence_node:" << std::endl;
    for(const auto& node : sequence_node) {
        std::cout << "  " <<  node << std::endl;
    }
    // std::cout << sequence_node << std::endl;  /*you could also do like this*/

    /*both statements work, even when the nodes are not in the file.*/
    std::cout << "foo does not exist, but that does not throw an error: " << node["foo"] << ".\n";
    for(const auto& n : node["bar"]) {
        std::cout << "  that works" <<  n << std::endl;
    }
}

static void load_yaml_3() {
    std::cout << "---\nLoading from file #2:\n";

    const YAML::Node node = YAML::LoadFile("info.yaml");
    std::cout << "name: " << node["name"] << std::endl;
    std::cout << "url: " << node["url"] << std::endl;

    const DateTime dt_created(node["created"].as<std::string>());
    std::cout << "created:\n";
    std::cout << "  year: " << dt_created.year << "\n";
    std::cout << "  month: " << dt_created.month << "\n";
    std::cout << "  day: " << dt_created.day_of_month << "\n";
    std::cout << "  hour: " << dt_created.hour << "\n";
    std::cout << "  minute: " << dt_created.minute << "\n";

    const DateTime dt_accessed(node["last_access"].as<std::string>());
    std::cout << "last_access:\n";
    std::cout << "  year: " << dt_accessed.year << "\n";
    std::cout << "  month: " << dt_accessed.month << "\n";
    std::cout << "  day: " << dt_accessed.day_of_month << "\n";
    std::cout << "  hour: " << dt_accessed.hour << "\n";
    std::cout << "  minute: " << dt_accessed.minute << "\n";

    const auto tags_node = node["tags"];
    std::cout << "tags:\n";
    for(const auto& tag : tags_node) {
        std::cout << "  " << tag << std::endl;
    }

    std::cout << "rating: " << node["rating"] << std::endl;
    std::cout << "comment:\n" << node["comment"].as<std::string>() << std::endl;
}

static void load_missing_yaml_file() {
    std::cout << "---\nLoading missing yaml file:\n";

    try {
        const YAML::Node node = YAML::LoadFile("nonexisting.yaml");
    } catch (const YAML::BadFile&  e) {
        /*the output of BadFile.what() is of little use*/
        std::cerr << "Error: We have a bad file: " << e.what() << "\n";
    }
}

static void load_broken_yaml_file() {
    std::cout << "---\nLoading broken yaml file:\n";

    try {
        const YAML::Node node = YAML::LoadFile("broken.yaml");
    } catch (const YAML::ParserException& e) {
        std::cerr << "Error: We have a broken file: \n" << e.what() << "\n";
    }
}

static void write_yaml_1() {
    std::cout << "---\nWriting to file #1:\n";

    YAML::Node node;
    node["name"] = "Andreas";
    node["id"] = 44;
    DateTime dt {2089, 7, 8, 6, 51};
    node[" the date"] = dt.str();
    node["long-comment"] = "This is \n a very long commment.\n\n  I am curious\nhow it will look.";
    node["tags"].push_back("Elem 1");
    node["tags"].push_back(2);
    node["tags"].push_back("Elem3");

    std::cout << node << std::endl;

    std::ofstream fout("out_file_1.yaml");
    fout << node;
}

static void write_yaml_2() {
    std::cout << "---\nMore sophisticated writing to file:\n";

    YAML::Emitter out;
    out.SetIndent(4);

    out << YAML::BeginMap
        << YAML::Key << "name" << YAML::Value << "Andreas"
        << YAML::Key << "id" << YAML::Value << 44;
    DateTime dt {2089, 7, 8, 6, 51};
    out << YAML::Key << " the date" << YAML::Value << dt.str()
        << YAML::Key << "long-comment" << YAML::Value << YAML::Literal
        <<  "This is \n a very long commment.\n\n\n  I am curious\nhow it will look."
        << YAML::Key << "tags" << YAML::Value << YAML::Flow
        << YAML::BeginSeq << 2 << 3 << 5 << 7 << 11 << YAML::EndSeq
        << YAML::Key << "mylist"
        << YAML::BeginSeq << "two" << 3 << "five" << 7 << "eleven" << YAML::EndSeq
        << YAML::EndMap;

    std::cout << out.c_str() << std::endl;

    std::ofstream fout("out_file_2.yaml");
    fout << out.c_str();
}

int main(int argc, const char* argv[]) {
    std::cout << "Hello!\n" << std::endl;

    load_yaml_1();
    load_yaml_vector();
    load_yaml_2();
    load_yaml_3();
    load_missing_yaml_file();
    load_broken_yaml_file();
    write_yaml_1();
    write_yaml_2();

    std::cout << "\nBye!" << std::endl;
    return 0;
}
