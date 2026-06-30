#!/usr/bin/env python3
"""Little numpy 2 refresher with the pareto-principlish
20% of the library you'd use 80% of the time.

Other things that are not covered:
- np.linalg - linear algebra (det, inv, solve, eig, svd, qr, norm)
- np.fft - Fourier transforms
- np.polynomial - polynomials with fitting and evaluation
- 30+ random distributions via default_rng() (normal, uniform, poisson, ...)
- np.ma - masked arrays for missing data
- Structured arrays - compound dtypes with named fields
- np.char - string vector operations
- np.testing - assert helpers for tests
- Coordinate grids - meshgrid, ogrid, mgrid
- Array manipulation - tile, repeat, pad, squeeze, flip, roll
- Axis manipulation - swapaxes, moveaxis, expand_dims, broadcast_to
- Window functions - blackman, hamming, kaiser, bartlett
- Set operations - intersect1d, union1d, isin
- Ufunc methods - reduce, accumulate, outer
- C API - extending NumPy with C/Cython
"""

import numpy as np

print("--- 1 arrays ---\n")
# homogeneous, typed, fixed-size, mutable

a = np.array([1, 2, 3])  # [1,2,3]
b = np.array([[4, 5, 6], [7, 8, 9]])  # [[4 5 6][7 8 9]]]

zeros1 = np.zeros([3])  # floating point values!
zeros2 = np.zeros([2, 3])  # floating point values!
ones = np.ones([3, 2])  # floating point values!

full1 = np.full((2, 2), 7)  # create an array of shape filled with given value
full2 = np.full((2, 2), [7, 6])  # ! fills 4x4 mat: 2 rows each [7,6]
# full2 = np.full((2, 2), [7,6, 5])  # fails: ValueError: could not broadcast input array from shape (3,) into shape (2,2)
arange = np.arange(0, 10, 2)  # evenly spaced values [start,stop[ with step (exclusive)
linspace = np.linspace(
    0, 1, 3
)  # num of evenly spaced points over [start, stop] (inclusive)

eye = np.eye(3)

rng = np.random.default_rng()  # random number generator
random = rng.random((2, 3))

print(f"""{a= }
{b= }
{zeros1= }
{zeros2= }
{ones= }
{full1= }
{full2= }
{arange= }
{linspace= }
{eye= }
{random= }
""")

print("\n--- 2 shapes and types ---\n")

print(f"{a.shape= }")  # (3,)
print(f"{a.ndim= }")  # 1
print(f"{a.size= }")  # 3
print(f"{a.dtype= }")  # dtype('int64')
print(f"{a.astype(np.float32)}")  # [1. 2. 3.]

print(f"{b.shape= }")  # (2, 3)
print(f"{b.ndim= }")  # 2
print(f"{b.size= }")  # 6
print(f"{b.dtype= }")  # dtype('int64')
print(f"{b.astype(np.float32)}")  # [[4. 5. 6.] [7. 8. 9.]]

print("\n--- 3 indexing and slicing ---\n")
# indexing and slicing returns a view

print(f"{b[0,1]= }")  # np.int64(5)  # scalar - copy, no reference!!
print(f"{b[1]= }")  # 2nd row: [7, 8, 9]
print(f"{b[-1]= }")  # last row: [7, 8, 9]
print(f"{b[:,0]= }")  # 1st column: [4, 7]; .ndim = 1; ,shape = (2,)
print(f"{b[0:2,1:]= }")  # incidentally all rows, all cols except 1st: [[5, 6][8, 9]]
print(f"{b[:,-1]= }")  # all rows, last column: [6, 9]

# === fancy indexing returns a copy, not a view! ===

print(f"{eye[[0, 2]]= }")  # give me row 0 and row 2: [[1. 0. 0.][0. 0. 1.]]
print(f"{eye[[0, 2], 1]= }")  # give me row 0 and row 2 on column 1 [0. 0.]

# give me row 0 on col 0, row 1 at col 1 and row 2 at col 2: [1. 1. 1.]
print(f"{eye[[0, 1,2], [0,1, 2]]= }")

print("\n--- 4 boolean filtering ---\n")

print(a > 1)  #  gives filter mask: [False True True]
print(b[(b > 3) & (b < 9)])  # gives matching elements but loses structure: [4 5 6 7 8]

print(f"{a[(a < 2) | (a > 2)]= }")  # [1, 3]

print("\n--- 5 axis filtering ---\n")
# axis=k means dimension k from the shape
# usually means to collapse (think: squash) that dimension
# dude

print(f"{b.mean(axis=1)= }")  # mean along column values: [5. 8.] (mean per row)
print(f"{b[b.mean(axis=1) > 5]= }")  # keep rows with mean > 5: [[7, 8, 9]]

# first mask any element not > 7:
# [[False, False, False],
#  [False,  True,  True]]
# then collapse the 0 dimension (rows), with `any`:
# [False  True  True]
print(f"{(b > 7).any(axis=0)= }")

# for all rows, keep cols with any element > 7: [[5 6][8 9]]
print(f"{b[:, (b > 7).any(axis=0)]= }")

print("\n--- 5 vectorized operations / broadcasting / matrix operations ---\n")

print(f"{a + 10= }")  # [11 12 13]
print(f"{a * 2= }")  # [2 4 6]
print(f"{a ** 2= }")  # [1 4 9]
print(f"{a ** 0.5= }")  # [1. 1.41421356 1.73205081])
print(f"{np.sqrt(a)= }")  # [1. 1.41421356 1.73205081])
print(f"{np.log(a)= }")
print(f"{np.sin(a)= }")

print(f"{a + np.array([100,200,300])= }")  # [101, 202, 303])

a = np.array([1, 2, 3])  # [1,2,3]
b = np.array([[4, 5, 6], [7, 8, 9]])  # [[4 5 6][7 8 9]]]

# Hadamard product: element-wise multiplication (commutative)
# [[4*1, 5*2, 6*3][7*1, 8*2, 9*3]]
print(f"{b * a= }")  # [[4 10 18][7 16 27]]
print(f"{a * b = }")  # same

# Dot-product / inner-product
print(f"{b @ a = }")  # [(4*1 + 5*2 + 6*3) (7*1 + 8*2 + 9*3)] = [32 50]

print("\n--- 6 reshaping ---\n")
# rule: product of new shape dimensions must equal total elements

print(f"{a.reshape(3,1)= }")  # 3 rows, 1 col: [[1][2][3]]

# -1 tells NumPy to infer that dimension - pick whatever size makes the total
# element count match. You can use -1 only once in the arguments.
print(f"{a.reshape(3,-1)= }")  # 3 rows, 1 col: [[1][2][3]]

print(f"{a.T=}")  # shorthand for transpose; noop for 1d-arrays

print(f"{b.reshape(3,2)= }")  # 3 rows, 2 cols: [[4 5][6 7][8 9]]
print(f"{b.reshape(1,-1)= }")  # 1 rows, 6 col: [[4 5 6 7 8 9]]
print(f"{b.T=}")  # [[4, 7][5, 8][6, 9]]

print("\n--- 7 aggregations ---\n")

print(f"{np.sum(a)= }")  # 6
print(f"{np.mean(a)= }")  # 2.0
print(f"{np.max(a)= }")  # 3
print(f"{np.min(a)= }")  # 1
print(f"{np.std(a)= }")  # 0.816496580927726

print(f"{np.max(b, axis=0)= }")  # max along rows: [7, 8, 9]
print(f"{np.max(b, axis=1)= }")  # max along cols: [6, 9]

print("\nNaN-safe variants - ignore NaN values")
print("which would fuck over everything otherwise")
print(f"{np.nansum(a)= }")
print(f"{np.nanmean(a)= }")
print(f"{np.nanmax(a)= }")
print(f"{np.nanmin(a)= }")
print(f"{np.nanstd(a)= }")

print("\narg* functions return index instead of values")
print(f"{np.argmax(a)= }")  # 2
print(f"{np.argmin(a)= }")  # 0
print(f"{np.argsort(a)= }")  # [0,1,2]

print(f"{np.argmax(b, axis=0)= }")  # max along rows with row-indices: [1, 1, 1]
print(f"{np.argmax(b, axis=1)= }")  # max along cols with col-indices: [2, 2]

print(f"{np.nanargmin(a)= }")  # 0
print(f"{np.nanargmax(a)= }")  # 2

print("\n--- 8 where, argwhere & nonzero ---\n")

print(f"{np.where(a > 1)= }")  # give me indices where condition is True: tuple[[1, 2]]
print(f"{np.nonzero(a)= }")  # give me indices where condition is True: tuple[[0, 1, 2]]

print(f"{a[np.where(a > 1)]= }")  # same shit as a[a > 1]

# 3-way where(): IF (condition) THEN value ELSE other_value
print(f"{np.where(a > 1, 100, np.nan)= }")  # [-1, 100, 100]

print("\n --- 9 sorting ---\n")
c = np.array([[0, 6, 2], [4, 2, 5]])
print(f"{np.sort(c)= }")  # sorts across first dimentsion: [[0 2 6][2 4 5]]
print(f"{np.sort(c, axis=-1)= }")  # sort/collapse across lowest dim: [[0 2 6][2 4 5]]
print(f"{np.sort(c, axis=1)= }")  # sort/collapse across 2nd dim: [[0 2 6][2 4 5]]
print(f"{np.sort(c, axis=0)= }")  # sort/collapse across highest dim: [[0 2 2][4 6 5]]

print("\n --- 10 combining arrays ---\n")

print(f"{np.concat([a,a])= }")  # [1 2 3 1 2 3]
print(f"{np.concat([b,b])= }")  # [[4 5 6][7 8 9][4 5 6][7 8 9]]
print(f"{np.concat([b,b],axis=1)= }")  # [[4 5 6 4 5 6][7 8 9 7 8 9]]

print(f"{np.vstack([a,a])= }")  # [[1 2 3][1 2 3]]
print(f"{np.vstack([a,b])= }")  # [[1 2 3][4 5 6][7 8 9]]
print(f"{np.hstack([a,a])= }")  # [1 2 3 1 2 3]


print("\n --- 11 many things are view - consider copying explicitly ---\n")
# Fancy indexing returns a copy however

d = np.array([11, 12, 13])
d_copy = d.copy()
d[1] = 99

print(f"{d= }")
print(f"{d_copy= }")

print("\n --- 12 I/O ---\n")

np.save("array.npy", a)  # binary representation!
loaded = np.load("array.npy")
print(f"{loaded= }")  # [1 2 3]

np.savetxt("data.csv", a, delimiter=",")
loaded_csv = np.loadtxt("data.csv", delimiter=",")
print(f"{loaded_csv= }")  # [1. 2. 3.]
