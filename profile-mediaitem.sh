#!/bin/sh

java -server -Xrunhprof:cpu=samples,depth=10,verbose=n,interval=2  -jar target/microbenchmarks.jar ".*StdRead.*PojoMediaItem.*" -wi 5 -w 2 -i 10 -r 5 -f 1 -t 1
