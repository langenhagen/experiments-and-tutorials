@echo off

set LOCALCLASSPATH=
for %%i in ("lib\*.jar") do call "lcp.bat" %%i

"%JAVA_HOME%\bin\java" -cp %LOCALCLASSPATH% org/sercho/masp/models/Context/providers/upnp/UPnPInteractionResource %1 %2 %3