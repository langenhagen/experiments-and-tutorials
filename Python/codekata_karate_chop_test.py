#!/usr/bin/env python3
"""Implement the codekata carate chop."""

import unittest

import codekata_karate_chop


class EmptyArray(unittest.TestCase):
    test_arr = []

    known_values = (
        (-10, -1),
        (0, -1),
        (1, -1),
        (2, -1),
        (3, -1),
        (4, -1),
        (5, -1),
        (6, -1),
        (7, -1),
        (8, -1),
        (9, -1),
        (11, -1),
        (13, -1),
        (17, -1),
        (99, -1),
        (1000, -1),
    )

    def test_karate_chop_1(self):
        """TODO"""
        for n, idx in self.known_values:
            result = codekata_karate_chop.binary_search_1(n, self.test_arr)
            self.assertEqual(idx, result)

    def test_karate_chop_2(self):
        """TODO"""
        for n, idx in self.known_values:
            result = codekata_karate_chop.binary_search_2(n, self.test_arr)
            self.assertEqual(idx, result)

    def test_karate_chop_3(self):
        """TODO"""
        for n, idx in self.known_values:
            result = codekata_karate_chop.binary_search_3(n, self.test_arr)
            self.assertEqual(idx, result)


class KnownValues(unittest.TestCase):
    test_arr = [1, 2, 3, 5, 6, 7, 9, 11, 13, 17, 99]

    known_values = (
        (-10, -1),
        (0, -1),
        (1, 0),
        (2, 1),
        (3, 2),
        (4, -1),
        (5, 3),
        (6, 4),
        (7, 5),
        (8, -1),
        (9, 6),
        (11, 7),
        (13, 8),
        (17, 9),
        (99, 10),
        (1000, -1),
    )

    def test_karate_chop_1(self):
        """TODO"""
        for n, idx in self.known_values:
            result = codekata_karate_chop.binary_search_1(n, self.test_arr)
            self.assertEqual(idx, result)

    def test_karate_chop_2(self):
        """TODO"""
        for n, idx in self.known_values:
            result = codekata_karate_chop.binary_search_2(n, self.test_arr)
            self.assertEqual(idx, result)

    def test_karate_chop_3(self):
        """TODO"""
        for n, idx in self.known_values:
            result = codekata_karate_chop.binary_search_3(n, self.test_arr)
            self.assertEqual(idx, result)


if __name__ == "__main__":
    unittest.main()
