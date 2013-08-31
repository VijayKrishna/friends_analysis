import java.util.*;
import java.net.*;
import java.io.*;
import org.xml.sax.InputSource;
import org.jsoup.*;

public class ExtractFrom {
	public static void main(String[] args) throws MalformedURLException, IOException {
		final String textDataRoot = "/home/vijay/Misc_Programming" 
				+ "/lang/freinds_analysis/data/text/";
		final File htmlDataStore = new File("/home/vijay/Misc_Programming/lang/freinds_analysis/data/html/");
		File[] htmlFiles = htmlDataStore.listFiles();
		for(File htmlFile : htmlFiles) {
			Scanner scanner = new Scanner(htmlFile);
			
			String name = htmlFile.getName() + ".txt";
			PrintStream out = new PrintStream(new File(textDataRoot + name));
   			extractText(scanner, out);
		}  		
	}

	public static void extractText(Scanner scanner, PrintStream out) throws IOException {
		String line;
		int count = 0;
		while (scanner.hasNextLine()) {
		  line = scanner.nextLine();
		  count += 1;
		  String text = Jsoup.parse(line).text();
		  if(text.equals("")) continue;
		  out.append(text + "\n");
		}
		System.out.println(count);
	}
}