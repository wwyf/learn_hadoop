
# 配置笔记

[toc]

## 安装jdk

```sh
sudo apt install openjdk-9-jdk
```


```sh
export JAVA_HOME=/usr/lib/jvm/java-9-openjdk-amd64/
export JRE_HOME=$JAVA_HOME/jre
export CLASSPATH=$JAVA_HOME/lib:$JRE_HOME/lib:$CLASSPATH
export PATH=$JAVA_HOME/bin:$JRE_HOME/bin:$PATH
```

## 配置hadoop

### 安装

下载


解压

```sh
cd /opt/
wget https://archive.apache.org/dist/hadoop/core/hadoop-1.2.1/hadoop-1.2.1.tar.gz
tar -zxvf hadoop-1.2.1.tar.gz
cd hadoop-1.2.1
```

然后就安装好了

### 配置hadoop-env.sh

在其中设置JAVA_HOME的值

![](https://lh3.googleusercontent.com/-1B_uMmgm-c0/WzHkTsIZwxI/AAAAAAAAIqE/G_xg-eg_Mu0M5gQu19I66oL_E9AZHJ85wCHMYCw/s0/Snipaste_2018-06-26_14-59-27.png)

### 配置hadoop的配置文件

hadoop 1.2.1 的配置文件在conf中

```xml
<!-- core-site.xml -->
<property>
	<name>hadoop.tmp.dir</name>
	<value>/hadoop/tmp</value>
</property>
<property>
	<name>dfs.name.dir</name>
	<value>/hadoop/name</value>
</property>
<property>
	<name>fs.default.name</name>
	<value>hdfs://walker-aliyun-server:9000</value>
</property>
<!-- hdfs-site.xml -->
<property>
	<name>dfs.data.dir</name>
	<value>/hadoop/data</value>
</property>
<!-- mapred-site.xml -->
<property>
	<name>mapred.job.tracker</name>
	<value>walker-aliyun-server:9001</value>
</property>
```

### 配置hadoop环境变量

配置/etc/profile

```sh
export HADOOP_HOME=/opt/hadoop-1.2.1
export PATH=$JAVA_HOME/bin:$JRE_HOME/bin:$HADOOP_HOME/bin:$PATH
```


### 测试配置

一些问题的解决
https://www.jianshu.com/p/ec63eef00703
ssh的问题
https://blog.csdn.net/coffeeandice/article/details/78879151 