#!/bin/bash
DATE=date sudo apt-get update 
echo "apt-get update has been run at $DATE" >> /var/log/apt-getupdatestats