#!/bin/sh

java -Xmx256m -jar target/microbenchmarks.jar ".*DZoneWrite.*writeAsString.*" -wi 4 -i 5 -f 9 -t 1
