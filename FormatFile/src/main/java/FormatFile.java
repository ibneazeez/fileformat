import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class FormatFile {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		InputStream inputStream= classloader.getResourceAsStream("small.in");
		//nputStream inputStream= classloader.getResourceAsStream("/resource");
		//inputStream.
		
		
		
		
		BufferedWriter writer = null;
	
		InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_16);
		BufferedReader reader = new BufferedReader(streamReader);
		 writer = new BufferedWriter( new OutputStreamWriter(new FileOutputStream("filename.csv"), StandardCharsets.UTF_16));
		 int headerSize=0;
		 List<List<String>> lines = new ArrayList<List<String>>();
		 for (String line; (line = reader.readLine()) != null;) {
		    //System.out.println(line);
		    List<String> listOfString = Arrays.asList(line.split(" "));
		    Collections.sort(listOfString);
		    lines.add(listOfString);
		    if(headerSize < listOfString.size()) {
		    	headerSize= listOfString.size();
		    }
		   // ;
		}
		 
		 for(int i=0;i<headerSize;i++) {
			 writer.write("Word "+ i +",");
		 }
		 writer.newLine();
		 for (ListIterator<List<String>> sentIterator = lines.listIterator(); sentIterator.hasNext();) {
			List<String> sentence = (List<String>) sentIterator.next();
			writer.write("sentence "+ sentIterator.nextIndex()+",");	
			for (Iterator<String> wordIterator = sentence.iterator(); wordIterator.hasNext();) {
				String word =  wordIterator.next();
				writer.write(word);
				if(wordIterator.hasNext()) {
					if(word.length()==0) continue;
					writer.write(",");	
				}
			}
			 writer.newLine();
		}
		 
		writer.close();
	}

}
