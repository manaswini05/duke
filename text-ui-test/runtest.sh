#!/usr/bin/env bash

# create bin directory if it doesn't exist
if [ ! -d "/Users/manaswinitalagadadivi/duke/bin" ]
then
mkdir /Users/manaswinitalagadadivi/duke/bin
fi

# delete output from previous run
if [ -e "./ACTUAL.TXT" ]
then
rm ACTUAL.TXT
fi

# compile the code into the bin folder, terminates if error occurred
if ! javac -cp /Users/manaswinitalagadadivi/duke/src/main/java/ca/demo/terminal -Xlint:none -d /Users/manaswinitalagadadivi/duke/bin /Users/manaswinitalagadadivi/duke/src/main/java/ca/demo/terminal/Duke.java
then
echo "********** BUILD FAILURE **********"
exit 1
fi

# run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath /Users/manaswinitalagadadivi/duke/bin Duke < input.txt > ACTUAL.TXT

# compare the output to the expected output
diff ACTUAL.TXT EXPECTED.TXT
if [ $? -eq 0 ]
then
echo "Test result: PASSED"
exit 0
else
echo "Test result: FAILED"
exit 1
fi
