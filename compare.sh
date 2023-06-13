#!/bin/bash

echo "Starte Java Programm..."
/usr/bin/time -v java SortDemo 2> java_metrics.txt

echo "Starte Rust Programm..."
/usr/bin/time -v ./sort_demo/target/release/sort_demo 2> rust_metrics.txt

java_time=$(grep "Elapsed (wall clock) time" java_metrics.txt | awk '{print substr($8, 0, index($8, ":") - 1) * 60 + substr($8, index($8, ":") + 1)}')
java_mem=$(grep "Maximum resident set size" java_metrics.txt | awk '{print $6}')

rust_time=$(grep "Elapsed (wall clock) time" rust_metrics.txt | awk '{print substr($8, 0, index($8, ":") - 1) * 60 + substr($8, index($8, ":") + 1)}')
rust_mem=$(grep "Maximum resident set size" rust_metrics.txt | awk '{print $6}')

time_ratio=$(awk "BEGIN {printf \"%.2f\",${rust_time}/${java_time}*100}")
mem_ratio=$(awk "BEGIN {printf \"%.2f\",${rust_mem}/${java_mem}*100}")

echo "Java Laufzeit: $java_time Sekunden, Speichernutzung: $java_mem KB"
echo "Rust Laufzeit: $rust_time Sekunden, Speichernutzung: $rust_mem KB"

echo "Rust verwendet $time_ratio% der Laufzeit von Java."
echo "Rust verwendet $mem_ratio% des Speichers von Java."

