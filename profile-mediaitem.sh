#!/bin/sh

java -server -Xrunhprof:cpu=samples,depth=10,verbose=n,interval=2  -jar target/microbenchmarks.jar ".*StdRead.*PojoMediaItem.*" -wi 4 -i 5 -f 9 -t 1
