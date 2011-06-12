#!/bin/bash

script_basedir=$PWD

function printHeader(){
    echo ""
    echo "==================================================="
    echo "  "$1
    echo "==================================================="
    echo ""
}

function printSmallHeader(){
    echo ""
    echo "====== "$1" ======="
    echo ""
}

function init_size_parameters {
    SIZE=0
    MAX_SIZE=30000
    STEP=1000
}

function init {
    MAIN_CLASS="scalavsjruby.performance.performancerunner.Execute"
    methods=( "default" "sorted" "reversed" )
}

function kill_java {
    echo "I'm going to kill java :)"
    # killall java
}

function prepare_sorting {
    for i in "${sizes[@]}"
        do
            mvn exec:java -Dexec.mainClass=$MAIN_CLASS -Dexec.classpathScope=runtime -Dexec.args="procedure=prepare_sorting;size="$i
        done
}

function execute_sorting {
    for j in "${methods[@]}"
        do
            printSmallHeader "sorting: generate method "$j""
            init_size_parameters
            while [ $SIZE -le $MAX_SIZE ]
                do
                    printSmallHeader "sorting: "$SIZE" elements (generate method "$j")"
                    kill_java
		    printSmallHeader "java"
                    mvn exec:java -Dexec.mainClass=$MAIN_CLASS -Dexec.classpathScope=runtime -Dexec.args="procedure=sort;language=java;sorting_method=quicksort;generate_method="$j";size="$SIZE
                    kill_java
		    printSmallHeader "scala"
                    mvn exec:java -Dexec.mainClass=$MAIN_CLASS -Dexec.classpathScope=runtime -Dexec.args="procedure=sort;language=scala;sorting_method=quicksort;generate_method="$j";size="$SIZE
                    kill_java
		    printSmallHeader "jruby"
                    mvn exec:java -Dexec.mainClass=$MAIN_CLASS -Dexec.classpathScope=runtime -Dexec.args="procedure=sort;language=ruby;sorting_method=quicksort;generate_method="$j";size="$SIZE
                    kill_java
	            SIZE=$[$SIZE+$STEP]
                done
        done
}

function cpu {
    printSmallHeader "cpu"
    kill_java
#    printSmallHeader "java"
#    mvn exec:java -Dexec.mainClass=$MAIN_CLASS -Dexec.classpathScope=runtime -Dexec.args="procedure=cpu;language=java;sorting_method=quicksort;generate_method=default;size=5000000"
    kill_java
#    printSmallHeader "scala"
#    mvn exec:java -Dexec.mainClass=$MAIN_CLASS -Dexec.classpathScope=runtime -Dexec.args="procedure=cpu;language=scala;sorting_method=quicksort;generate_method=default;size=5000000"
    kill_java
    printSmallHeader "jruby"
    mvn exec:java -Dexec.mainClass=$MAIN_CLASS -Dexec.classpathScope=runtime -Dexec.args="procedure=cpu;language=ruby;sorting_method=quicksort;generate_method=default;size=5000000"
    kill_java
}

function execute_memory_consuption {
    printSmallHeader "memory consuption..."
    kill_java
    printSmallHeader "java"
    mvn exec:java -Dexec.mainClass=$MAIN_CLASS -Dexec.classpathScope=runtime -Dexec.args="procedure=memory_consumption;language=java"
    kill_java
    printSmallHeader "scala"
    mvn exec:java -Dexec.mainClass=$MAIN_CLASS -Dexec.classpathScope=runtime -Dexec.args="procedure=memory_consumption;language=scala"
    kill_java
    printSmallHeader "jruby"
    mvn exec:java -Dexec.mainClass=$MAIN_CLASS -Dexec.classpathScope=runtime -Dexec.args="procedure=memory_consumption;language=ruby"
    kill_java
}

function execute {
    init
    #prepare_sorting
    #execute_sorting
    #execute_memory_consuption
    cpu
}


printHeader "Sorting"

echo "I'm going to kill all your java processes...."
echo " eg. hudson, netbeans, eclipse, tomcat"
echo "Are you aware of that? (yes, no)"
read aware

if [ "yes" == "$aware" ]; then
	echo "...roger that..."
	execute
else
	echo "...as you wish..."
fi 
