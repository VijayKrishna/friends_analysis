import java.io.File;
import java.util.Scanner;
import java.lang.StringBuffer;
import java.io.FileNotFoundException;
import net.sf.classifier4J.summariser.SimpleSummariser;


public class SeasonSummarizer {

	final static File textDataStore = 
	new File("/home/vijay/Misc_Programming/lang/freinds_analysis/data/text/");
	File[] textFiles = textDataStore.listFiles();

	public static void main(String[] args) throws FileNotFoundException {
		SeasonSummarizer seasonSummarizer = new SeasonSummarizer();
		Content season1 = seasonSummarizer.getSeasonContent(args[0]);
		String summary = seasonSummarizer.summarize(season1);
		System.out.println(summary);
	}

	public Content getSeasonContent(String seasonPrefix) 
	throws FileNotFoundException {	
		StringBuffer seasonText = new StringBuffer();
		int lineCount = 0;
		for(File textFile : textFiles) {
			if(!textFile.getName().startsWith(seasonPrefix)) continue;
			String fileKey = textFile.getName();
			Scanner scanner = new Scanner(textFile);
			while(scanner.hasNextLine()) {
				String line = scanner.nextLine();
				if(line.contains(":")) {
					line = line.substring(line.indexOf(":"));
				}
				lineCount ++;
				seasonText.append(line);
			}
		}

		return new Content(seasonText.toString(), lineCount);
	}

	public String summarize(Content content) {
		SimpleSummariser simpleSummarizer = new SimpleSummariser();
		return simpleSummarizer.summarise(content.getText(), 10);
	}
}

class Content {
	private final String text;
	private final int lineCount;

	public Content(String text, int lineCount) {
		this.text = text;
		this.lineCount = lineCount;
	}

	public String getText() {
		return this.text;
	}

	public int getLineCount() {
		return this.lineCount;
	}
}