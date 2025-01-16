#!/bin/sh

java -Xmx256m -jar target/perf.jar ".*JsonStdReadVan.*PojoMedia.*" -wi 1 -w 1 -i 999 -r 1 -f 1 $*

