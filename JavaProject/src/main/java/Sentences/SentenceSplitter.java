package Sentences;

import java.text.BreakIterator;
import java.util.Locale;
import java.util.ArrayList;

public class SentenceSplitter {
    public static ArrayList<String> getSentences( String text ) {
        final Locale ptBR = new Locale("pt", "BR");
        ArrayList<String> sentences = new ArrayList<String>();
        BreakIterator iterator = BreakIterator.getSentenceInstance(ptBR);
        iterator.setText(text);
        int start = iterator.first();
        for (int end = iterator.next();
             end != BreakIterator.DONE;
             start = end, end = iterator.next()) {
            sentences.add(text.substring(start, end));
        }
        return sentences;
    }
}
