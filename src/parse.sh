#!/usr/bin/env bash

mkdir -p Magenta/Parser/generatednodes

(cd Magenta/Parser/generatednodes; jjtree ../Parser.jjt && mv ./Parser.jj ../Parser.jj)
(cd Magenta/Parser/; javacc ./Parser.jj)
javac Magenta/Parser/Parser.java

if [ $# -eq 0 ]
then
    java Magenta.Parser.Parser
else
    java Magenta.Parser.Parser "$@"
fi