package WordScoring;

import java.util.HashMap;
import java.util.Map;

import java.io.InputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.BufferedReader;
import java.io.FileReader;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.Path;

public class WordScorer {
		private static HashMap<String, Double> scoreDictionary = null;
		private static HashMap<String, Integer> frequencyDictionary = null;

		private static HashMap<String, Double> getScoreDictionary() {
			if (scoreDictionary == null) {
				try {
					FileSystem fs = FileSystem.get(new Configuration());
					FileStatus[] files = fs.listStatus(new Path("/tmp2"));
					for(int i = 1; i < files.length; i++){
						FileStatus file = files[i];
						InputStream in = fs.open(new Path("/tmp2/" + file.getPath().getName()));
						BufferedReader br = new BufferedReader(new InputStreamReader(in));
						String line = br.readLine();
						scoreDictionary = new HashMap<String,Double> ();
						while (line != null) {
								String[] fields = line.split("\t");
								String word = fields[0];
								Double prevValue = scoreDictionary.get(word);
								Double newValue = Double.parseDouble(fields[1]);
								if (prevValue != null) {
									scoreDictionary.put(word, prevValue+newValue);
								}
								else {
									scoreDictionary.put(word, newValue);
								}
								line = br.readLine();
						}
						br.close();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return scoreDictionary;
		}

		private static HashMap<String, Integer> getFrequencyDictionary() {
			if (frequencyDictionary == null) {
				try {
						FileSystem fs = FileSystem.get(new Configuration());
						InputStream in = fs.open(new Path("/df.ser"));
						ObjectInputStream objReader = new ObjectInputStream(in);
						frequencyDictionary = (HashMap<String, Integer>) objReader.readObject();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return frequencyDictionary;
		}

		public static Integer getFrequency(String token) {
				HashMap<String, Integer> dict = getFrequencyDictionary();
				Integer value = dict.get(token);
				if (value != null) {
					return value;
				}
				else {
					return 0;
				}
		 }

    public static Double getScoreForToken(String token) {
				HashMap<String, Double> dict = getScoreDictionary();
        return dict.get(token);
    }
}
