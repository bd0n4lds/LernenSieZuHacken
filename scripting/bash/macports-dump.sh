#!/bin/sh

# Remove the caches
sudo port clean --all installed

# Remove any inactive packages
sudo port -f uninstall inactive