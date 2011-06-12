#!/bin/bash

function printHeader(){
    echo ""
    echo "==================================================="
    echo "  "$1
    echo "==================================================="
    echo ""
}

function printSmallHeader(){
    echo ""
    echo "====== "$1"======="
    echo ""
}

printHeader "Start"
echo "  requirements:"
echo "    java 1.6"
echo "    maven > 3.0.2"
echo "  before run build maven projects:" 
echo "    mvn clean install"
echo ""

script_basedir=$PWD

cd $PWD/performance/PerformanceRunner && ./run.sh
cd $PWD

