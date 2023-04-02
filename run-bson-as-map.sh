#!/bin/sh

java -Xmx256m -jar target/perf.jar ".*Mongo.*Read.*UntypedMediaItem.*" -wi 4 -i 5 -f 9 -t 1
