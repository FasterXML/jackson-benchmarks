#!/bin/sh

java -Xmx256m -jar target/microbenchmarks.jar ".*StdRead.*PojoMediaItem.*" -wi 4 -w 1 -i 5 -r 1 -f 9 -t 1  -rf json
