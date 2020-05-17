/*Showcase some issues that valgrind can detect.

Wrap your program call with valgrind to check the program while it is running.

Install valgrind like:
  sudo apt install -y valgrind

Valgrind example usage in Fish Shell:

set G_SLICE always-malloc;
set G_DEBUG gc-friendly;

valgrind \
    -v \
    --tool=memcheck \
    --leak-check=full \
    --num-callers=40 \
    --log-file=valgrind.log \
    $PWD/08-my-text-input-field

author: andreasl
*/
#include <stdlib.h>

void f(void) {
   int* x = malloc(10 * sizeof(int));
   x[10] = 0;        // problem 1: heap block overrun
}                    // problem 2: memory leak -- x not freed

int main(void) {
   f();
   return 0;
}
