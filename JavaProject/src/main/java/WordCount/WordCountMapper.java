package WordCount;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import Stemmer.Tokenizer;

public class WordCountMapper  extends Mapper<LongWritable, Text, Text, IntWritable> {
    private final IntWritable one = new IntWritable(1);

    private Text word = new Text();

    public WordCountMapper()  {
        System.out.println("Init WordCount Mapper");
    }

    @Override
    protected void map(LongWritable key, Text value,
                       Context context)
            throws IOException, InterruptedException {
        String[] result = value.toString().split("\\s");
        for (String nextToken : result) {
            nextToken = Tokenizer.tokenForString(nextToken);
            if (!nextToken.equals("")) {
                word.set(nextToken);
                context.write(word, one);
            }
        }

    }

}
