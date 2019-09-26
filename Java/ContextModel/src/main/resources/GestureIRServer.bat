@echo off

set LOCALCLASSPATH=
for %%i in ("lib\*.jar") do call "lcp.bat" %%i

java -cp %LOCALCLASSPATH% org/sercho/masp/models/Context/gesture/GestureTestServer %1 %2 %3