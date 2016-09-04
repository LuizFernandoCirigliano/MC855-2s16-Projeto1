package Sentences;

import java.io.IOException;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import Stemmer.Tokenizer;
import WordScoring.WordScorer;

public class SentenceScoreMapper  extends Mapper<LongWritable, Text, Text, DoubleWritable> {
    private final DoubleWritable scoreWritable = new DoubleWritable(1);

    private Text word = new Text();

    public SentenceScoreMapper()  {
        System.out.println("Init Sentence Score Mapper");
    }

    @Override
    protected void map(LongWritable key, Text value,
                       Context context)
            throws IOException, InterruptedException {
        String[] result = value.toString().split("\\s");
        double currentScore = 0;
        for (String nextToken : result) {
            nextToken = Tokenizer.tokenForString(nextToken);
            if (!nextToken.equals("")) {
                // Implement this:
                currentScore += WordScorer.getScoreForToken(nextToken);
            }
        }
        scoreWritable.set(currentScore);
        context.write(value, scoreWritable);
    }

}
