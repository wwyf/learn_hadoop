NUM=4

STEP=step$(NUM)

CORE_JAR=$(HADOOP_HOME)/hadoop-core-1.2.1.jar
COMMONS_CLI=$(HADOOP_HOME)/lib/commons-cli-1.2.1.jar
JAR=$(CORE_JAR):$(COMMONS_CLI)

IN_PATH=step1_input_matrix
OUT_PATH=$(STEP)_output_matrix

FILE=Mapred.java Reducer$(NUM).java Mapper$(NUM).java

TARGET=../../build/$(STEP)
EXEC=Mapred
ROOT=../../

default_target: $(TARGET)/$(EXEC).jar

$(TARGET)/Mapred.class: $(FILE)
	javac -classpath $(JAR) -d $(TARGET) $^

$(TARGET)/$(EXEC).jar: $(TARGET)/$(EXEC).class
	make $(EXEC).jar -C $(TARGET)/

clean:
	find $(TARGET) -type f -name "*.class" -delete
	find $(TARGET) -type f -name "*.jar" -delete
	-hadoop fs -rmr $(OUT_PATH)
run:
	hadoop jar $(TARGET)/$(EXEC).jar $(EXEC) $(IN_PATH) $(OUT_PATH)

