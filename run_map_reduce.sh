${HADOOP_HOME:?"HADOOP_HOME env variable not set. BIRL!"}
$HADOOP_HOME/bin/hdfs dfs -rm -r /output_dilma
$HADOOP_HOME/bin/hadoop jar JavaProject/target/proj1-1.0-SNAPSHOT.jar App /discursos /output_dilma

