${HADOOP_HOME:?"HADOOP_HOME not set. BIRL!"}
$HADOOP_HOME/sbin/stop-yarn.sh
$HADOOP_HOME/sbin/stop-dfs.sh
