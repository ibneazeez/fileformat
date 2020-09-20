import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
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
		InputStream inputStream = classloader.getResourceAsStream("small.in");
		// nputStream inputStream= classloader.getResourceAsStream("/resource");
		// inputStream.

		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_16));
		
		int headerSize = 0;
		List<List<String>> lines = new ArrayList<List<String>>();
		for (String line; (line = reader.readLine()) != null;) {
			line =line.trim().replaceAll("\\s{2,}", " "); // Remove double spaces to single place
			
			if (line.length() > 0) {
				List<String> listOfString = Arrays.asList(line.split(" "));
				Collections.sort(listOfString);
				lines.add(listOfString);
				if (headerSize < listOfString.size()) {
					headerSize = listOfString.size();
				}
			}
		}

		// CSV WRITER
		csvWriter(headerSize, lines);
		xmlWriter(headerSize, lines);
	}

	private static void csvWriter(int headerSize, List<List<String>> lines) throws FileNotFoundException, IOException {
		// UTF 16 to handle Chinese character
		BufferedWriter csvWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("filename.csv"), StandardCharsets.UTF_16));
		for (int i = 0; i < headerSize; i++) {
			csvWriter.write("Word " + i + ",");
		}
		csvWriter.newLine();
		for (ListIterator<List<String>> sentIterator = lines.listIterator(); sentIterator.hasNext();) {
			List<String> sentence = (List<String>) sentIterator.next();
			csvWriter.write("sentence " + sentIterator.nextIndex() + ",");
			for (Iterator<String> wordIterator = sentence.iterator(); wordIterator.hasNext();) {
				String word = wordIterator.next();
				csvWriter.write(word);
				if (wordIterator.hasNext()) {
					if (word.length() == 0)
						continue;
					csvWriter.write(",");
				}
			}
			csvWriter.newLine();
			
		}

		csvWriter.close();
	}

	
	
	
	private static void xmlWriter(int headerSize, List<List<String>> lines) throws FileNotFoundException, IOException {
		BufferedWriter csvWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("filename.xml"), StandardCharsets.UTF_16));
		/*
		 * for (int i = 0; i < headerSize; i++) { csvWriter.write("Word " + i + ","); }
		 */
		csvWriter.write("<text>");
		for (ListIterator<List<String>> sentIterator = lines.listIterator(); sentIterator.hasNext();) {
			List<String> sentence = (List<String>) sentIterator.next();
			csvWriter.write("<sentence>");
			for (Iterator<String> wordIterator = sentence.iterator(); wordIterator.hasNext();) {
				String word = wordIterator.next();
				csvWriter.write("<word>"+word+"</word>");
				
			}
			csvWriter.write("</sentence>");
			csvWriter.newLine();
			
		}
		csvWriter.write("</text>");
		csvWriter.close();
	}
}
