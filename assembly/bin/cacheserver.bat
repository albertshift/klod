@set BIN=%~dp0

@set CLASSPATH="%BIN%\..\conf"

@for %%a in (%BIN%\..\lib\*.jar) do @call %BIN%\add2cp.bat %%a

@java -classpath %CLASSPATH% com.shvid.klod.net.CacheServer %BIN% %1

@pause