package WordScoring;

import java.util.HashMap;
import java.util.Map;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.BufferedReader;
import java.io.FileReader;

import java.io.Serializable;

public class WordScorer {

		private HashMap<String, Integer> dictionary;

		public static Integer getFrequency(String token) {
				// IMPLEMENTATION MISSING
				if (dictionary == null) {
					FileInputStream fin = new FileInputStream("df.ser");
					ObjectInputStream ois = new ObjectInputStream(fin);
					dictionary = (HashMap<String, Integer>) ois.readObject();
				}

				Integer value = dictionary.get(token);
				if (value != null) {
					return value;
				}
				else {
					return 0;
				}
		}

    public static double getScoreForToken(String token) {
        // IMPLEMENTATION MISSING
        return 1.0;
    }
}
