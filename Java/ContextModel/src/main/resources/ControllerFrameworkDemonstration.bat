@echo off

set LOCALCLASSPATH=
for %%i in ("lib\*.jar") do call "lcp.bat" %%i

SET LOCALCLASSPATH=..\conf\;%LOCALCLASSPATH%
"%JAVA_HOME%\bin\java" -cp %LOCALCLASSPATH% org/sercho/masp/models/Context/ControllerFrameworkDemonstration ControllerFrameworkDemonstration.xmi