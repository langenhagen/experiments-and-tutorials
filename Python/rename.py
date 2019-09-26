"""Rename several files in a directory."""
# Muss in das Verzeichnis kopiert werden, in der sie arbeiten soll
import os
import os.path
import math

mode = int( input("Which type of mode?\n\
\t0: Add pre-string, \n\
\t1: add pre-number with leading zeros, \n\
\t2: add pre-number without leading zeros\n\
\t3: replace substring with given string\n: "))

# get path to the script's folder
realpath = os.path.dirname(os.path.realpath(__file__))
flist = [file for file in os.listdir(realpath) if os.path.isfile(file)]

# count the number of processed files
count = 1

if mode == 0:
	preString = input("Which pre-string?: ")
elif mode == 3:
	oldString = input("Which substring is to be replaced?: ")
	newString = input("Type the new string: ")

for fname in flist:
	if fname != "rename.py":
		if mode == 0:
			# Add pre-string
			os.rename(fname, preString + fname)
		elif mode == 1:
			# pre-number with leading zeros
			num_digits_all = math.ceil(math.log(len(flist)+1,10)) # num of digits of the number
			num_digits_cur = math.ceil(math.log(count+1,10)) # num of digits of current number
			num_of_zeros = num_digits_all - num_digits_cur
			preString = "";

			for z in range(0, num_of_zeros):
				preString += "0"

			preString += str(count) + " "
			os.rename(fname, preString + fname)
		elif mode == 2:
			# pre-number without leading zeros
			preString = str(count) + " "
			os.rename(fname, preString + fname)
		elif mode == 3:
			# substring replacement with given other string
			os.rename( fname, fname.replace(oldString, newString));
		else:
			# error case
			print("ERROR: UNKNOWN MODE:" + mode + "\nABORTING.");

	count += 1

input("DONE.")
