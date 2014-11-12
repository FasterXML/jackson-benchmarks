#!/bin/sh

java -jar target/microbenchmarks.jar ".*StdRead.*MediaItem.*" -wi 4 -i 5 -f 9 -t 2
