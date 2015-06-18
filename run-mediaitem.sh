#!/bin/sh

java -jar -Xmx256m target/microbenchmarks.jar ".*StdRead.*PojoMediaItem.*" -wi 4 -i 5 -f 9 -t 1
