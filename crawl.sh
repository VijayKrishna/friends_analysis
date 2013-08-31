#!/bin/sh

for i in 1 2 3 4 5 6 7
do
	for j in 01 02 03 04 05 06 07 08 09 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24
	do
		curl http://friends.crissov.de/transcripts/$i$j > $i$j.html
	done
done

# for (( j = 101; j <= 102; j++ ))
# 	do
# 		curl http://friends.crissov.de/transcripts/$j > $j.txt
# 	done