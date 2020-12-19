#!/usr/bin/env bash

find ./Magenta/Parser -type f \
    -not -name 'Parser.jjt' \
    -delete

find ./Magenta -type f -name '*.class' -delete