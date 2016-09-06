import java.util.HashMap;
import java.util.Map;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.BufferedReader;
import java.io.FileReader;

// import Stemmer.Tokenizer;

public class Serializer {

   public static void main (String args[]) {

	   Serializer serializer = new Serializer();
	   serializer.serializeDictionary();
   }

   public void serializeDictionary(){

     Map<String, Integer> dictionary = new HashMap<String, Integer>();

     try {
        BufferedReader br = new BufferedReader(new FileReader("df.txt"));
         String line = br.readLine();

         while (line != null) {
             String[] fields = line.split(" ");
            //  String word = Tokenizer.tokenForString(fields[0]);
             String word = (fields[0]);
             Integer value = dictionary.get(word);
             if (value != null) {
               dictionary.put(word, value+Integer.parseInt(fields[1]));
             }
             else {
               dictionary.put(word, Integer.parseInt(fields[1]));
             }
             line = br.readLine();
         }
         br.close();
     } catch(Exception ex){
		   ex.printStackTrace();
	   }
     System.out.println(dictionary);

	   try{
  		FileOutputStream fout = new FileOutputStream("df.ser");
  		ObjectOutputStream oos = new ObjectOutputStream(fout);
  		oos.writeObject(dictionary);
  		oos.close();
  		System.out.println("Done");

	   }catch(Exception ex){
		   ex.printStackTrace();
	   }
   }
}
