#!/bin/sh

./gradlew -Pinput=./jmh-result-read.json -Poutput=./jmh-report-html jmhReport

