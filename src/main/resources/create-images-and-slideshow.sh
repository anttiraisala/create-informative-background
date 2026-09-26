#!/bin/bash

echo "Creating array of background images and then start slideshow..."
./create-16-bgs.sh && display -resize 800x800 -delay 2 -loop 0 bg-array*.png
echo "Done (creating array of background images and then start slideshow)"

