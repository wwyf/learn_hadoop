#!/bin/bash
# 编译
javac -classpath /opt/hadoop-1.2.1/hadoop-core-1.2.1.jar:/opt/hadoop-1.2.1/lib/commons-cli-1.2.jar -d work_count_class WordCount.java

jar -cvf word_count_class/wordcount.jar word_count_class/*.class
