#!/bin/sh

java -Xmx256m -jar target/perf.jar ".*JsonStdReadVan.*PojoMedia.*" -wi 4 -w 1 -i 5 -r 1 -f 9 -t 1

