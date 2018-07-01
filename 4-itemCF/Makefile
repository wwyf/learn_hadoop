default_target: help
help:
	@echo "make all - make all the step.\n"
	@echo "make clean - reset the whole project.\n"
	@echo "make init - send local files into hadoop fs.\n"
	@echo "make run - run mapreduce.\n"
	@echo "make show - show result.\n"
	@echo "how to run:\n"
	@echo "\t make clean (no need if it is first time)\n"
	@echo "\t make all \n"
	@echo "\t make init\n"
	@echo "\t make run\n"
	@echo "\t make show\n"
all: step1 step2 step3 step4 step5

step1:
	@make -C src/step1/
step2:
	@make -C src/step2/
step3:
	@make -C src/step3/
step4:
	@make -C src/step4/
step5:
	@make -C src/step5/
run:
	@echo "runing step1 ...\n"
	@make run -C src/step1/
	@echo "step1 funished. now runing step2...\n"
	@make run -C src/step2/
	@echo "step2 funished. now runing step3...\n"
	@make run -C src/step3/
	@echo "step3 funished. now runing step4...\n"
	@make run -C src/step4/
	@echo "step4 funished. now runing step5...\n"
	@make run -C src/step5/
	@echo "finish all.\n"
clean:
	-@make clean -C src/step1/
	-@make clean -C src/step2/
	-@make clean -C src/step3/
	-@make clean -C src/step4/
	-@make clean -C src/step5/
	-hadoop fs -rmr step1_input_matrix
	-hadoop fs -rmr step2_input_matrix
init:
	-hadoop fs -mkdir step1_input_matrix
	-hadoop fs -mkdir step2_input_matrix
	-hadoop fs -put step1_input/* step1_input_matrix
	-hadoop fs -put step2_input/* step2_input_matrix
dataset:
	-hadoop fs -mkdir step1_input_matrix
	-hadoop fs -mkdir step2_input_matrix
	-hadoop fs -put ~/dataset/input-data/ActionList step1_input_matrix/ 
get:
	-hadoop fs -get step5_output_matrix/part-r-00000 ~/dataset/result
show:
	-hadoop fs -cat step5_output_matrix/part-r-00000
