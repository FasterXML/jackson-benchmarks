#!/bin/sh

java -jar target/microbenchmarks.jar ".*MediaItem.*" -wi 5 -i 5 -f 5 -t 2
