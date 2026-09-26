#!/bin/bash

echo "Creating background image..."

BG_IMAGE_NAME=bg.png
if [ ! -z "$1" ]; then
    BG_IMAGE_NAME=$1
fi

./bars.sh && povray create-bg.pov -D +w2880 +h1800 +a0.1
./colorClouds.sh -oi cloud-1.png -os cloud-1.script && eval "$(cat cloud-1.script)"
./colorClouds.sh -oi cloud-2.png -os cloud-2.script && eval "$(cat cloud-2.script)"
./masks.sh -t clouds -oi color-mask.png -os color-mask.script && eval "$(cat color-mask.script)"
./masks.sh -t circles -oi bar-mask.png -os bar-mask.script && eval "$(cat bar-mask.script)"
convert cloud-1.png cloud-2.png color-mask.png -composite cloud.png
convert cloud.png create-bg.png bar-mask.png -composite bg-temp.png
#PRETTY_NAME=$(grep -Po "(?<=^PRETTY_NAME=\")[^\"]*" /etc/os-release) && NAME=$(grep -Po "(?<=^NAME=\")[^\"]*" /etc/os-release) && VERSION=$(grep -Po "(?<=^VERSION=\")[^\"]*" /etc/os-release) && DISTRIB_DESCRIPTION=$(grep -Po "(?<=^DISTRIB_DESCRIPTION=\")[^\"]*" /etc/lsb-release) && if [ "$DISTRIB_DESCRIPTION" == "$PRETTY_NAME" ]; then INFO_TEXT=$(printf "$NAME $VERSION"); else INFO_TEXT=$(printf "$DISTRIB_DESCRIPTION - $NAME $VERSION"); fi && INFO_TEXT=$(echo $INFO_TEXT | base64 -w0)
PRETTY_NAME=$(grep -Po "(?<=^PRETTY_NAME=\")[^\"]*" /etc/os-release) && NAME=$(grep -Po "(?<=^NAME=\")[^\"]*" /etc/os-release) && VERSION=$(grep -Po "(?<=^VERSION=\")[^\"]*" /etc/os-release) && DISTRIB_DESCRIPTION=$(grep -Po "(?<=^DISTRIB_DESCRIPTION=\")[^\"]*" /etc/lsb-release) && if [ "$DISTRIB_DESCRIPTION" == "$PRETTY_NAME" ]; then INFO_TEXT=$(printf "$NAME $VERSION"); else INFO_TEXT=$(printf "$DISTRIB_DESCRIPTION ($NAME $VERSION)"); fi && INFO_TEXT=$(echo $INFO_TEXT | base64 -w0)
./text.sh -ii bg-temp.png -oi $BG_IMAGE_NAME -os text.script -bt $(hostname) -it $INFO_TEXT -st $(cat ~/system-create-date.txt | base64 -w0) -font $(find /usr/share/fonts -iname "*bold*" | head -n 1) && eval "$(cat text.script)"

echo "Done creating background image."
