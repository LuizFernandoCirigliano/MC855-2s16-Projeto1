package WordScoring;

import java.util.HashMap;
import java.util.Map;

import java.io.InputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.BufferedReader;
import java.io.FileReader;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class WordScorer {

		private static HashMap<String, Integer> dictionary = null;

		static {
			try {
					FileSystem fs = FileSystem.get(new Configuration());
					InputStream in = fs.open(new Path("/df.ser"));
					ObjectInputStream objReader = new ObjectInputStream(in);
					dictionary = (HashMap<String, Integer>) objReader.readObject();
					//System.out.println(dictionary);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		public static void printDict() {
			System.out.println(dictionary);
		}

		public static Integer getFrequency(String token) {
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
