#!/bin/bash
if [ "$1" == "" ]
then
echo "No IP Adress Found"
echo "Syntax ./ipsweepV2.sh  x.x.x.x"

else
for ip in `seq 1 254` ; do
ping -c 1 $1.$ip | grep "64 bytes" | cut -d " " -f 4 | tr -d ":" &
done
fi
