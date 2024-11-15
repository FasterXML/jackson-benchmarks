#!/bin/sh

if [ $# -eq 0 ]; then
  echo "Usage: $0 [process-id] (zero or more pass-through args)"
  exit 1
fi
pid=$1
if kill -0 "$pid" 2>/dev/null; then
  echo "Process with PID $pid exists; can try profiling"
else
  echo "No process found with PID $pid, quitting."
  exit 2
fi

shift 1

echo "Profiling process $pid for 30 seconds..."
~/bin/asprof -i 2ms -d 30 -j 10  --alloc 64k $* $pid
