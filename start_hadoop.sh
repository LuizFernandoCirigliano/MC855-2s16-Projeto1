${HADOOP_HOME:?"HADOOP_HOME env variable not set. BIRL!"}

$HADOOP_HOME/sbin/start-dfs.sh
$HADOOP_HOME/sbin/start-yarn.sh
