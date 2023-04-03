#!/bin/sh

java -Xmx256m -server -cp target/perf.jar \
 -Xrunhprof:cpu=samples,depth=10,verbose=n,interval=2 \
 com.fasterxml.jackson.perf.manual.$*
