#!/usr/local/bin/ruby
#
# COnvert Github flavored Markdown to HTML.
#
# based on:
# https://stackoverflow.com/a/13379919/5714632
# https://stackoverflow.com/questions/5545068/what-are-all-the-common-ways-to-read-a-file-in-ruby
#
# author: andreasl
# version: 19-06-26
require 'github/markdown'

html_string = "<link rel = \"stylesheet\"type = \"text/css\" href = \"pandoc.css\" />"

File.open("day-notes.md", "r") do |f|
  f.each_line do |line|
    html_string.concat(line)
  end
end

File.open("day-notes.html", "w") do |f|
  f.puts GitHub::Markdown.render_gfm(html_string)
end
