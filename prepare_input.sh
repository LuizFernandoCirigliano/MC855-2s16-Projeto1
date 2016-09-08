$HADOOP_HOME/bin/hdfs dfs -mkdir /discursos
$HADOOP_HOME/bin/hdfs dfs -put ./JavaProject/df.ser /
$HADOOP_HOME/bin/hdfs dfs -put ./InputFiles/Discursos/* /discursos
