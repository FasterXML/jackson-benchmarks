#!/bin/sh

java -server -Xrunhprof:cpu=samples,depth=15,verbose=n,interval=2  -jar target/microbenchmarks.jar ".*JsonWastefulW.*Untyped.*" -wi 4 -w 2 -i 8 -r 5 -f 1 -t 1
