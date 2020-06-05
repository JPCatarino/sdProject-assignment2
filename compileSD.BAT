if not exist "out\sdProject" (
    mkdir "out\sdProject" 2>nul
)

start cmd.exe /k "javac -d out/sdProject -classpath out/sdProject src/common/* src/exceptions/* src/states/* src/entities/* src/interfaces/* src/sharedRegions/* src/stubs/* src/proxies/* src/main/* "
