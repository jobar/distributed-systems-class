#!/bin/bash

latex architecture-systemes-distribues.tex
bibtex architecture-systemes-distribues.aux
latex architecture-systemes-distribues.tex
latex architecture-systemes-distribues.tex
pdflatex architecture-systemes-distribues.tex
echo 'Done.'