#!/usearch_results/bin/env python3
# Showcase the usage of Python's regex engine
#
# author: andreasl
import re

print("--- 1 - greedy ---")
txt = "The rain in Spain. I mean, The man in the alley."
pattern = "The.*in"
search_results = re.search(pattern, txt)
find_results = re.findall(pattern, txt)
split = re.split(pattern, txt)

print(f"search_results: {search_results}")
print(f"find_results: {find_results}")
print(f"split: {split}")

recombined = [None] * (len(split) + len(find_results))
recombined[::2] = split
recombined[1::2] = find_results
print(f"recombined: {recombined}")

print("--- 2 - non-greedy with .*? ---")
# See: https://docs.python.org/3/howto/regex.html#greedy-versus-non-greedy
# python regexes do not find overlapping matches
txt = "The rain in Spain. I mean, The man in the alley."
pattern = "The.*?in"
search_results = re.search(pattern, txt)
find_results = re.findall(pattern, txt)
split = re.split(pattern, txt)

print(f"search_results: {search_results}")
print(f"find_results: {find_results}")
print(f"split: {split}")

recombined = [None] * (len(split) + len(find_results))
recombined[::2] = split
recombined[1::2] = find_results
print(f"recombined: {recombined}")

print("--- 3 - wordwise ---")
txt = "The rain in Spain. I mean, The man in the alley."
pattern = r"\w*in\w*"
search_results = re.search(pattern, txt)  # use raw strings since you use backslashes
find_results = re.findall(pattern, txt)
split = re.split(pattern, txt)

print(f"search_results: {search_results}")
print(f"find_results: {find_results}")
print(f"split: {split}")

recombined = [None] * (len(split) + len(find_results))
recombined[::2] = split
recombined[1::2] = find_results
print(f"recombined: {recombined}")
