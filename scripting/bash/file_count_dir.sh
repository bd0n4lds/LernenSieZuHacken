#!/bin/bash
cd $HOME 
find * -maxdepth 0 -type d -exec sh -c "echo -n {} ' '; ls -lR {} | wc -l" \; > $HOME/filecount_homedir.txt 
cd public_html/
find * -maxdepth 0 -type d -exec sh -c "echo -n {} ' '; ls -lR {} | wc -l" \; > $HOME/filecountpublic_html.txt