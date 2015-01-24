#!/bin/sh

java -server -Xrunhprof:cpu=samples,depth=10,verbose=n,interval=2  -jar target/microbenchmarks.jar ".*StdRead.*PojoMediaItem.*" -wi 8 -i 30 -f 1 -t 1
