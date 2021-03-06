#!/bin/sh
# This code would require jdk and jre, which can be installed using the following commands. Assuming the jdk and jre would be already installed.
# sudo apt-get install openjdk-7-jdk openjdk-7-jre
#Assigning the present directory variable
myPath=$(pwd)
#Creating different folder variables for input and output
inputVar=$myPath"/wc_input/"
outputVarWC=$myPath"/wc_output/wc_result.txt"
outputVarMedian=$myPath"/wc_output/med_result.txt"
#Getting inside the ./src folder
cd src
# Permissions are being granted to the files.
chmod 777 WordCount.java
chmod 777 MedianOfLines.java
# Compilation of the two files:
javac WordCount.java
javac MedianOfLines.java
# Finally, we would execute these files using the following commands. It will accept two arguments as the input.
# First Argument will the be folder where it will be reading the the input text files.
# Second Argument would be the output file name where it will be storing the output data.
java WordCount $inputVar $outputVarWC
java MedianOfLines $inputVar $outputVarMedian
