#!/bin/bash
EMAIL=your.email@address.com 
cd /var/log 
tail -n 30 auth.log | grep -i "failed" | mail -s "Failed SSH Logins" $EMAIL