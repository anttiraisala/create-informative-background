#!/bin/bash

echo "Creating (+deploy) background image..."

BG_IMAGE_NAME=bg.png
if [ ! -z "$1" ]; then
    BG_IMAGE_NAME=$1
fi

./create-bg.sh $BG_IMAGE_NAME
gsettings set org.gnome.desktop.background picture-uri file:$(pwd)/$BG_IMAGE_NAME && gsettings set org.gnome.desktop.background picture-options 'stretched'

echo "Done creating (+deploy) background image."
