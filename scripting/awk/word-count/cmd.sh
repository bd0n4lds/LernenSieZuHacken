awk '
{for (i = 1; i<=NF; i++) words[$i]++} 
END {for (i in words) print i, words[i]}' words.txt | \
sort -nr -k2
