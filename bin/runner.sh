#!/usr/bin/env bash

javac ./Magenta/Lexer/Lexer.java
/bin/bash ./parse.sh --direct "`java Magenta.Lexer.Lexer $1`"