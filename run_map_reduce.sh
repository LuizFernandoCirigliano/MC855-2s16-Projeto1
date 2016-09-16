cd JavaProject
mvn package
cd ..
${HADOOP_HOME:?"HADOOP_HOME env variable not set. BIRL!"}

$HADOOP_HOME/bin/hdfs dfs -rm -r /output_dilma

FILES="./InputFiles/Discursos/*"
for f in $FILES
do
  $HADOOP_HOME/bin/hdfs dfs -rm -r /tmp1 /tmp2
  name=`basename "$f"`
  echo $name

  input="/discursos/$name"
  output="/output_dilma/$name"

  $HADOOP_HOME/bin/hadoop jar JavaProject/target/proj1-1.0-SNAPSHOT.jar App $input $output
  $HADOOP_HOME/bin/hdfs dfs -cat "$output/*" > "Output/$name"

done
