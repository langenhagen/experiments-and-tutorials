# NumPy Refresher

## Import

``` python
import numpy as np
```

## Create arrays

``` python
a = np.array([1,2,3])
b = np.array([[1,2],[3,4]])

np.zeros((2,3))
np.ones((3,2))
np.full((2,2),7)
np.arange(0,10,2)
np.linspace(0,1,5)
np.eye(3)
rng = np.random.default_rng()
rng.random((2,3))
```

## Shape and type

``` python
x.shape
x.ndim
x.size
x.dtype
x.astype(np.float32)
```

## Indexing and slicing

``` python
x[0,1]
x[1]
x[:,0]
x[0:2,1:]
x[-1]
x[:,-1]

# Fancy indexing (returns a copy, not a view)
x[[0, 2]]
x[[0, 2], :]
```

## Boolean filtering

``` python
a[a > 3]
a[(a > 2) & (a < 5)]
```

Use `&` and `|`, not `and` / `or`.

## Vectorized operations

``` python
a + 10
a * 2
a ** 2
np.sqrt(a)
np.log(a)
np.sin(a)
```

## Broadcasting

``` python
x + np.array([100,200,300])
```

Rule: dimensions must match or one dimension must be `1`.

## Reshape and transpose

``` python
a.reshape(3,4)
a.reshape(2,-1)
x.T
```

## Aggregations

``` python
np.sum(x)
np.mean(x)
np.max(x)
np.min(x)
np.std(x)

np.sum(x, axis=0)
np.sum(x, axis=1)

# NaN-safe variants
np.nansum(x)
np.nanmean(x)
np.nanstd(x)
```

-   `axis=0` → collapse rows (operate vertically)
-   `axis=1` → collapse columns (operate horizontally)

## arg\* functions (find positions)

``` python
a = np.array([10,50,30,20])

np.argmax(a)     # 1
np.argmin(a)     # 0
np.argsort(a)    # [0,3,2,1]

a[np.argsort(a)]
# [10,20,30,50]
```

2D with axis:

``` python
x = np.array([
 [5,9,2],
 [7,1,6]
])

np.argmax(x, axis=0)
# [1,0,1]

np.argmax(x, axis=1)
# [1,0]
```

Related:

``` python
np.argwhere(a > 20)
np.nonzero(a)
np.where(a > 20)
np.where(a > 20, 1, 0)
```

## Sorting

``` python
np.sort(a)    # returns sorted copy
a.sort()      # sorts in-place, returns None
```

Memory trick:

-   `max()` → value
-   `argmax()` → where it is
-   `sort()` → sorted values
-   `argsort()` → ordering/index map

## Matrix operations

``` python
a * b
a @ b
np.dot(a,b)
```

## Combine arrays

``` python
np.concatenate([a,b])
np.vstack([a,b])
np.hstack([a,b])
np.split(np.arange(10),2)
```

## I/O

``` python
np.save("array.npy", a)
loaded = np.load("array.npy")

np.savetxt("data.csv", a, delimiter=",")
loaded_csv = np.loadtxt("data.csv", delimiter=",")
```

## Copy vs view

``` python
# Assignment creates a reference (not a copy)
b = a
b[0] = 99       # modifies a too!

# Basic slicing returns a view (shares data)
view = a[:3]
view[0] = 99    # modifies a too!

# .copy() creates a new, independent array
c = a.copy()
c[0] = 99       # a is unchanged

# Fancy indexing returns a copy
copy = a[[0, 2]]
copy[0] = 99    # a is unchanged
```

## Typical workflow

``` python
rng = np.random.default_rng()
data = rng.random(100)
filtered = data[data > 0.5]
mean = np.mean(filtered)
print(mean)
```
