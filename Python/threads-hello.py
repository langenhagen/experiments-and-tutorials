#!/usr/bin/env python
"""
Showcase simple threading in python by creating and starting several threads
that each print when they start, wait 1 second and then print when they end.
Their output will be interleaved.
"""
import threading
import time


class MyThread(threading.Thread):
    def run(self):
        print("{} started!".format(self.getName()))        # "Thread-x started!"
        time.sleep(1)                                      # pretend to work for a second
        print("{} finished!".format(self.getName()))       # "Thread-x finished!"


def main():
    for x in range(4):                                     # 4 times
        mythread = MyThread(name="Thread-{}".format(x + 1))  # instantiate a thread and pass a unique ID to it
        mythread.start()                                   # start the thread, invoke the run method
        # mythread.join()     # that would make the threads run sequentially
        time.sleep(.9)                                     # wait 0.9 seconds before starting another thread


if __name__ == "__main__":
    main()
