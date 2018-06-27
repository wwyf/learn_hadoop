#!/bin/bash

# 在1-work_count下运行
# 将输入的数据放到hdfs文件系统中
hadoop fs -mkdir input_wordcount
hadoop fs -put input/* input_wordcount/
hadoop fs -ls input_wordcount
hadoop fs -cat input_wordcount/file1


# 编译
javac -classpath /opt/hadoop-1.2.1/hadoop-core-1.2.1.jar:/opt/hadoop-1.2.1/lib/commons-cli-1.2.jar -d work_count_class WordCount.java

jar -cvf word_count_class/wordcount.jar work_count_class/*.class
