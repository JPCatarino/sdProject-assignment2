#!/bin/bash

java -classpath out/sdProject/ main.ArrivalLoungeServer &
java -classpath out/sdProject/ main.ArrivalQuayServer &
java -classpath out/sdProject/ main.ArrivalTerminalExitServer &
java -classpath out/sdProject/ main.BagColPointServer &
java -classpath out/sdProject/ main.BagRecOfficeServer &
java -classpath out/sdProject/ main.DepartureQuayServer &
java -classpath out/sdProject/ main.DepartureTerminalEntranceServer &
java -classpath out/sdProject/ main.RepositoryServer &
java -classpath out/sdProject/ main.TempStgAreaServer &
java -classpath out/sdProject/ main.ClientAirportRhapsody