# Overview

This project contains simple set of performance micro-benchmarks, using excellent
[JMH](http://openjdk.java.net/projects/code-tools/jmh/) package.

# Status

Has been used since Jackson 2.4 to track performance changes: see various `results-pojo-2.x-home.txt`
files for numbers.

Tests usually run with

    profile-mediaitem.sh

or directly with something like

    java -Xmx256m -jar target/microbenchmarks.jar ".*StdReadAfter.*PojoMediaItem.*" -wi 4 -w 1 -i 5 -r 1 -f 3 -t 1

(see `results-pojo-2.13-home.txt` and others for specific invocations used for each group of numbers)
