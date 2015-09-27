import org.jsoup.Jsoup;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;



public class ExtractFrom {
    private static final String USER_DIR = System.getProperty("user.dir");

    public static void main(String[] args) throws IOException {
		final String textDataRoot = USER_DIR + "/data/text/";
		final File htmlDataStore = new File(USER_DIR + "/data/html/");
		File[] htmlFiles = htmlDataStore.listFiles();
		for(File htmlFile : htmlFiles) {
			Scanner scanner = new Scanner(htmlFile);
            String htmlFileName = htmlFile.getName();
            int indexOfExtension = htmlFileName.indexOf(".");
            String textFileName = htmlFileName.substring(0, indexOfExtension) + ".txt";
			PrintStream out = new PrintStream(new File(textDataRoot + textFileName));
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