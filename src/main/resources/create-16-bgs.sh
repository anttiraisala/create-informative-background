#!/bin/bash

#  ./create-16-bgs.sh && display -resize 800x800 -delay 2 -loop 0 bg-array*.png &

echo "Creating array of background images..."
for i in 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16
do
    echo "Creating #$i..."
    ./create-bg.sh bg-array-$i.png
    #convert cloud.png create-bg.png bar-mask.png -composite bg-array-$i.png
    echo "Done creating #$i..."
done
echo "Done creating (array of background images)."
echo
echo "Try: $ display -resize 800x800 -delay 2 -loop 0 bg-array*.png"

