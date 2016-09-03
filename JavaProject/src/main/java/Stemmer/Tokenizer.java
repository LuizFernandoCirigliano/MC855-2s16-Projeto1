package Stemmer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.text.Normalizer;

public class Tokenizer {
    private static final Pattern alphaPattern = Pattern.compile("[^a-z]");
    private static final PortugueseStemmer stemmer = new PortugueseStemmer();

    public static String tokenForString(String s) {
        // Remover acentos
        s = Normalizer.normalize(s, Normalizer.Form.NFD);
        s = s.toLowerCase();
        // Remove tudo que nao e letra
        Matcher matcher = alphaPattern.matcher(s);
        String cleanWord = matcher.replaceAll("");
        stemmer.setCurrent(cleanWord);
        stemmer.stem();
        return stemmer.getCurrent();
    }
}
