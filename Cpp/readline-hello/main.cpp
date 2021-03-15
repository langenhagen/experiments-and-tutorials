/** @brief Showcase the usage of the readline library for nice CLI applications.
 * taken from: https://eli.thegreenplace.net/2016/basics-of-using-the-readline-library/
 *
 * @author: andreasl
 */
#include <stdio.h>
#include <stdlib.h>
#include <string>

#include <readline/readline.h>
#include <readline/history.h>

int main(int argc, char** argv) {
  printf("Welcome! You can exit by pressing Ctrl+C at any time...\n");

  if (argc > 1 && std::string(argv[1]) == "-d") {
    // By default readline does filename completion. With -d, we disable this
    // by asking readline to just insert the TAB character itself.
    rl_bind_key('\t', rl_insert);
  }

  char* buf;
  while ((buf = readline(">> ")) != nullptr) {
    if (strlen(buf) > 0) {
      add_history(buf);
    }

    printf("[%s]\n", buf);

    // readline malloc's a new buffer every time.
    free(buf);
  }

  return 0;
}
