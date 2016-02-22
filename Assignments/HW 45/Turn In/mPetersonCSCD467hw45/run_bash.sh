#! /bin/bash

java -classpath bin ServerMain &
processId=($(jobs -p))
sleep 1s
java -classpath bin TestClient
kill "$processId"