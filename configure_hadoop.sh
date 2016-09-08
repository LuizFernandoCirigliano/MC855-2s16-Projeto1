${HADOOP_HOME:?"HADOOP_HOME env variable not set. BIRL!"}

cp HadoopConfig/core-site.xml $HADOOP_HOME/etc/hadoop/core-site.xml
cp HadoopConfig/hdfs-site.xml $HADOOP_HOME/etc/hadoop/hdfs-site.xml
cp HadoopConfig/mapred-site.xml $HADOOP_HOME/etc/hadoop/mapred-site.xml
cp HadoopConfig/yarn-site.xml $HADOOP_HOME/etc/hadoop/yarn-site.xml

$HADOOP_HOME/bin/hdfs namenode -format
