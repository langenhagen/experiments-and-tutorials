@echo off

set LOCALCLASSPATH=
for %%i in ("lib\*.jar") do call "lcp.bat" %%i

java -cp %LOCALCLASSPATH% org/sercho/masp/models/Context/gesture/GestureInteractionResource %1 %2 %3