@echo off
REM Generate an audiobook from an .epub ebook using the Python tool `audiblez`
REM Usage: script.bat <ebook_file> <voice>

set "ebook_file=%~1"
if "%ebook_file%"=="" set "ebook_file=myebook.epub"
set "voice=%~2"
if "%voice%"=="" set "voice=af_heart"

time /T
audiblez --cuda "%ebook_file%" -v "%voice%"
time /T
