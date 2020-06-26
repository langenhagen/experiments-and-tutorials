#!/usr/bin/env python
# -*- coding: utf-8 -*-

# Crawls the given directory, finds all the jpg-Images, retrieves their exif data
# and appends iso, f number, focal length and exposure time to a given csv file.
# author: langenhagen
# version: 17-08-24

from fractions import Fraction
from optparse import OptionParser
from PIL import Image
from PIL.ExifTags import TAGS
import os
import sys
import traceback


def get_exif(image_path):
    """Retrieve the exif data from the given image."""
    exif = {}
    i = Image.open(image_path)
    info = i._getexif()

    if not hasattr(info, "items") or info.items() == None:
        print "Image " + image_path + " does not contain any exif information."
        return None

    for tag, value in info.items():
        decoded = TAGS.get(tag, tag)
        exif[decoded] = value
    return exif


def append_exif_data_to_csv(csv_file, image_paths):
    """Append the exif data to the csv file with the given path."""

    for image_path in image_paths:
        exif = get_exif(image_path)
        if exif == None:
            continue

        iso = str(exif['ISOSpeedRatings'])
        fnumber = str(float(exif['FNumber'][0]) / exif['FNumber'][1])
        focal_length = str(Fraction(exif['FocalLength'][
                           0], exif['FocalLength'][1]))
        exposure_time = str(Fraction(exif['ExposureTime'][
                            0], exif['ExposureTime'][1]))

        path, file = os.path.split(image_path)
        csv_line = "" + image_path + ";" + file + ";" + iso + \
            ";" + fnumber + ";" + focal_length + ";" + exposure_time
        print csv_line
        csv_file.write(csv_line + "\n")


def tabulate_images(csv_file_path, image_paths):
    """Extract exif data from the images in the given directory and appends them to the given csv file."""

    try:
        csv_file = open(csv_file_path, 'a')
        append_exif_data_to_csv(csv_file, image_paths)
    except:
        print("Exception:")
        print '-' * 60
        traceback.print_exc(file=sys.stdout)
        print '-' * 60

    csv_file.close()


if __name__ == "__main__":

    # parse command line options
    parser = OptionParser(usage="usage: %prog [options]",
                          version="%prog 1.0")
    parser.add_option("-f", "--csv-file-path",
                      dest="csv_file_path",
                      type="string",
                      help="The path to the csv file")
    parser.add_option("-p", "--images-path",
                      dest="images_path",
                      type="string",
                      help="The path on which to look for images")

    (options, args) = parser.parse_args()

    # check if the paths are correct
    if options.csv_file_path == None or not os.path.isfile(options.csv_file_path):
        print "Error: The given csv file path should be a valid file."
        sys.exit()
    if options.images_path == None or not os.path.isdir(options.images_path):
        print "Error: The given path to the image files should be a valid path."
        sys.exit()

    image_file_paths = [os.path.join(options.images_path, f) for f in os.listdir(options.images_path) if os.path.isfile(
        os.path.join(options.images_path, f)) and f.lower().endswith(".jpg")]

    tabulate_images(options.csv_file_path, image_file_paths)

    print "Done."
