# Overview

This project contains simple set of performance micro-benchmarks, using excellent
[JMH](http://openjdk.java.net/projects/code-tools/jmh/) package.

# Status

Has been used since Jackson 2.4 to track performance changes: see various `results-pojo-2.x-home.txt`
files for numbers.

## Usage

Tests usually run with

    run-mediaitem.sh

or directly with something like

    java -Xmx256m -jar target/microbenchmarks.jar ".*StdReadVanilla.*PojoMediaItem.*" -wi 4 -w 1 -i 5 -r 1 -f 3 -t 1

(see `results-pojo-2.13-home.txt` and others for specific invocations used for each group of numbers)

## Profiling

Can use

    ./profile-mediaitem.sh

to use basic JMH settings over runs.

## Test sets

### POJO: MediaItem

* Standard: java -Xmx256m -jar target/microbenchmarks.jar ".*StdReadVan.*PojoMedia.*" -wi 4 -w 1 -i 5 -r 1 -f 9 -t 1
* Afterburner: java -Xmx256m -jar target/microbenchmarks.jar ".*StdReadAfter.*PojoMedia.*" -wi 4 -w 1 -i 5 -r 1 -f 9 -t 
1

### POJO: Currency (floating-point)

* Default/JSON: java -Xmx256m -jar target/microbenchmarks.jar ".*Json.*readCurrencyPojoDefault.*" -wi 4 -w 1 -i 5 -r 1 -f 1 -t 1
* FastFP/JSON: java -Xmx256m -jar target/microbenchmarks.jar ".*Json.*readCurrencyPojoFast.*" -wi 4 -w 1 -i 5 -r 1 -f 1 -t 1
* All: java -Xmx256m -jar target/microbenchmarks.jar ".*readCurrencyPojo.*" -wi 4 -w 1 -i 5 -r 1 -f 1 -t 1
