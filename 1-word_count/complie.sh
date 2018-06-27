#!/bin/bash
# 编译
javac -classpath /opt/hadoop-1.2.1/hadoop-core-1.2.1.jar:/opt/hadoop-1.2.1/lib/commons-cli-1.2.jar -d word_count_class WordCount.java
# NOTICE:很奇怪，如果不在那个目录下jar的话会出问题，找不到类
cd word_count_class
jar -cvf wordcount.jar  *.class
