#!/bin/bash

# 将输入的数据放到hdfs文件系统中
hadoop fs -mkdir input_wordcounthadoop




# 编译
javac -classpath /opt/hadoop-1.2.1/hadoop-core-1.2.1.jar:/opt/hadoop-1.2.1/lib/commons-cli-1.2.jar -d work_count_class WordCount.java

jar -cvf wordcount.jar *.class
