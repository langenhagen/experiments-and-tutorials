"""Implement the karate chop code kata."""


def binary_search_1(n, arr):
    """Perform a binary search in order to find the integer n in the sorted array arr.

    Keyword arguments:
    n -- integer to be found
    arr -- a sorted array of integers

    Returns: integer
    """
    debug = False

    result_index = 0

    while(len(arr) > 0):
        pivot = len(arr) // 2
        value = arr[pivot]
        if debug: print('arr:{}  result_index:{}  pivot:{}'.format(arr, result_index, pivot))

        if value == n:
            if debug: print('HIT = return result_index + pivot = {}\n'.format(result_index + pivot))
            return result_index + pivot

        elif value < n:
            # to right
            arr = arr[pivot+1:]
            result_index += pivot + 1
            if debug: print('RIGHT value<n {} < {} pivot:{} and result_index={}\n'.format(value, n, pivot, result_index))

        elif value > n:
            # to left
            arr = arr[:pivot]
            if debug: print('LEFT value>n {} > {} pivot:{} and result_index={}\n'.format(value, n, pivot, result_index))
        else:
            #shd never happen
            raise Exception('something strange just happened...')

    return -1 # not found


def binary_search_2(n, arr, init_index=0):
    """Perform a binary search in order to find the integer n in the sorted array arr.

    Keyword arguments:
    n -- integer to be found
    arr -- a sorted array of integers
    init_index -- an initial offset used for recursive calls. Let it be 0.
    Returns: integer
    """

    if len(arr) == 0:
        return -1

    result_index = -1

    pivot = len(arr) // 2
    value = arr[pivot]


    if len(arr) <= 1 and value != n:
        # not found
        return -1

    elif value == n:
        # hit
        return init_index + pivot
    elif value < n:
        # to right
        arr = arr[pivot+1:]
        return binary_search_2(n, arr, init_index + pivot + 1)
    elif value > n:
        # to left
        arr = arr[:pivot]
        return binary_search_2(n, arr, init_index)
    else:
        #shd never happen
        raise Exception('something strange just happened...')


def binary_search_3(n, arr):
    """Perform a binary search in order to find the integer n in the sorted array arr.

    Keyword arguments:
    n -- integer to be found
    arr -- a sorted array of integers

    Returns: integer
    """

    lower_index = 0
    upper_index = range = len(arr)

    while range > 0:
        range = upper_index - lower_index
        pivot = lower_index + (range // 2)

        if pivot >= len(arr):
            return -1

        value = arr[pivot]

        if value == n:
            # hit
            return pivot

        elif value < n:
            # to right
            lower_index = pivot + 1

        elif value > n:
            # to left
            upper_index = pivot

        else:
            #shd never happen
            raise Exception('something strange just happened...')

    return -1 # not found



if __name__ == "__main__":
    test_arr = [1, 2, 3, 5, 6, 7, 9, 11, 13, 17, 99]
    print(f"test_arr: {test_arr}  length: {len(test_arr)}")

    while True:
        n = int(input("which number?: "))
        r = binary_search_3(n, test_arr)
        print(f"result index: {r}")
