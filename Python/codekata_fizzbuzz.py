"""Implement the fizzbuzz code kata."""

for i in range(1, 100):
    if i % 3 == 0 and i % 5 == 0:
        result = "fizzbuzz"
    elif i % 3 == 0:
        result = "fizz"
    elif i % 5 == 0:
        result = "buzz"
    else:
        result = "{0}".format(i)

    print(result)
