package WordScoring;

import java.io.IOException;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import Stemmer.Tokenizer;

public class WordScoreMapper  extends Mapper<LongWritable, Text, Text, DoubleWritable> {
    private DoubleWritable score = new DoubleWritable();
    private Text word = new Text();

    public WordScoreMapper()  {
        System.out.println("Init WordCount Mapper");
    }

    @Override
    protected void map(LongWritable key, Text value,
                       Context context)
            throws IOException, InterruptedException {
        String[] result = value.toString().split(" ");
        String token = result[0];
        Integer count = Integer.parseInt(result[1]);
        if (!token.equals("") && count > 0) {
            Integer ptFreq = WordScorer.getFrequency(token);
            word.set(token);
            score.set(count/ptFreq);
            context.write(word, score);
        }
    }
}
