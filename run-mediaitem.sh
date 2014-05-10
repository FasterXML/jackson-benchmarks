#!/bin/sh

java -jar target/microbenchmarks.jar ".*MediaItem.*" -wi 7 -i 7 -f 3 -t 2
