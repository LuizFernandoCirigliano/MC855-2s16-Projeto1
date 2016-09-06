package WordScoring;

import java.util.HashMap;
import java.util.Map;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.BufferedReader;
import java.io.FileReader;

import java.io.Serializable;

public class WordScorer {

		private static HashMap<String, Integer> dictionary;

		public static Integer getFrequency(String token) {
				// IMPLEMENTATION MISSING

				if (dictionary == null) {
          try {
					    FileInputStream fin = new FileInputStream("df.ser");
					    ObjectInputStream ois = new ObjectInputStream(fin);
              dictionary = (HashMap<String, Integer>) ois.readObject();
          } catch (Exception e) {
            e.printStackTrace();
            return 0;
          }

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
