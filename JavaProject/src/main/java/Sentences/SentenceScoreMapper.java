package Sentences;

import java.io.IOException;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import Stemmer.Tokenizer;
import WordScoring.WordScorer;

public class SentenceScoreMapper  extends Mapper<LongWritable, Text, Text, DoubleWritable> {
    private DoubleWritable scoreWritable = new DoubleWritable();

    public SentenceScoreMapper()  {
        System.out.println("Init Sentence Score Mapper");
    }

    @Override
    protected void map(LongWritable key, Text value,
                       Context context)
            throws IOException, InterruptedException {
        String[] result = value.toString().split("\\s");
        double currentScore = 0;
        int count = 0;
        for (String nextToken : result) {
            nextToken = Tokenizer.tokenForString(nextToken);
            if (!nextToken.equals("")) {
                // Implement this:
                Double tokenScore = WordScorer.getScoreForToken(nextToken);
                if (tokenScore != null) {
                  currentScore += tokenScore.doubleValue();
                  count += 1;
                }
            }
        }
        if (count > 0) {
            scoreWritable.set(currentScore / count);
            context.write(value, scoreWritable);
        }
    }
}
