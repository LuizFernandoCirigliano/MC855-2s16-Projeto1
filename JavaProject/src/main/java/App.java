import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

import WordCount.WordCountMapper;
import WordCount.WordCountReducer;
import WordScoring.WordScoreMapper;
import Sentences.SentenceScoreMapper;

import org.apache.hadoop.mapred.Reducer;

public class App {

    public App() {
        System.out.println("Init WordCountTest");
    }

    public static void main(String[] args) throws Exception{
        //JobClient client  = new JobClient();
        Configuration conf = new Configuration();

        // Count occurrences of words
        Job job1 = Job.getInstance(conf, "word count");
        String[] otherArgs = new GenericOptionsParser(conf,args).getRemainingArgs();
        job1.setCombinerClass(WordCountReducer.class);
        job1.setReducerClass(WordCountReducer.class);
        job1.setMapperClass(WordCountMapper.class);
        job1.setJarByClass(App.class);
        job1.setOutputKeyClass(Text.class);
        job1.setOutputValueClass(IntWritable.class);
        FileInputFormat.addInputPath(job1, new Path(otherArgs[0]));
        FileOutputFormat.setOutputPath(job1, new Path("/tmp1"));
        if (!job1.waitForCompletion(true)) {
          System.exit(1);
        }

        // Convert the occurrences of words into a normalized score
        Job job2 = Job.getInstance(conf, "word scoring");
        job2.setNumReduceTasks(0);
        job2.setMapperClass(WordScoreMapper.class);
        job2.setJarByClass(App.class);
        job2.setOutputKeyClass(Text.class);
        job2.setOutputValueClass(DoubleWritable.class);
        FileInputFormat.addInputPath(job2, new Path("/tmp1"));
        FileOutputFormat.setOutputPath(job2, new Path("/tmp2"));
        if (!job2.waitForCompletion(true)) {
          System.exit(1);
        }

        // Score each sentence based on the words
        Job job3 = Job.getInstance(conf, "sentence scoring");
        job3.setMapperClass(SentenceScoreMapper.class);
        // job3.setReducerClass(Reducer.class);
        job3.setJarByClass(App.class);
        job3.setOutputKeyClass(DoubleWritable.class);
        job3.setOutputValueClass(Text.class);
        FileInputFormat.addInputPath(job3, new Path(otherArgs[0]));
        FileOutputFormat.setOutputPath(job3, new Path(otherArgs[1]));
        System.exit(job3.waitForCompletion(true) ? 0: 1);
    }
}
