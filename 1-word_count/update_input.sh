#!/bin/bash

# 在1-work_count下运行
# 将输入的数据放到hdfs文件系统中
hadoop fs -mkdir input_wordcount
hadoop fs -put input/* input_wordcount/
hadoop fs -ls input_wordcount
hadoop fs -cat input_wordcount/file1


