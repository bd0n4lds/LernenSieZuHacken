#!/bin/bash
cd /var/log
find . -iname "*.gz" -atime +14 -type f | xargs -I{} mv {} /var/log/oldlogs > /var/log/oldlog_output 2>&1