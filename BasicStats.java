import java.io.File;
import java.util.Scanner;
import java.util.HashMap;
import java.util.TreeMap;
import java.io.FileNotFoundException;
import java.io.PrintStream;

public class BasicStats {

	final static File textDataStore = 
	new File("/home/vijay/Misc_Programming/lang/freinds_analysis/data/text/");
	final static String joey = "Joey:";
	final static String chandler = "Chandler:";
	final static String ross = "Ross:";
	final static String monica = "Monica:";
	final static String rachel = "Rachel:";
	final static String phoebe = "Phoebe:";

	static TreeMap<String, Integer> joeyMap = new TreeMap<String, Integer>();
	static TreeMap<String, Integer> chandlerMap = new TreeMap<String, Integer>();
	static TreeMap<String, Integer> rossMap = new TreeMap<String, Integer>();
	static TreeMap<String, Integer> rachelMap = new TreeMap<String, Integer>();
	static TreeMap<String, Integer> phoebeMap = new TreeMap<String, Integer>();
	static TreeMap<String, Integer> monicaMap = new TreeMap<String, Integer>();


	public static void main(String[] args) throws FileNotFoundException {
		File[] textFiles = textDataStore.listFiles();
		for(File textFile : textFiles) {
			String fileKey = textFile.getName();
			Scanner scanner = new Scanner(textFile);
			while(scanner.hasNextLine()) {
				String line = scanner.nextLine();
				if(line.startsWith(joey)) {
					joeyMap = updateMap(joeyMap, fileKey);
				} else if(line.startsWith(chandler)) {
					chandlerMap = updateMap(chandlerMap, fileKey);
				} else if(line.startsWith(ross)) {
					rossMap = updateMap(rossMap, fileKey);
				} else if(line.startsWith(rachel)) {
					rachelMap = updateMap(rachelMap, fileKey);
				} else if(line.startsWith(phoebe)) {
					phoebeMap = updateMap(phoebeMap, fileKey);
				} else if(line.startsWith(monica)) {
					monicaMap = updateMap(monicaMap, fileKey);
				} else {
					continue;
				}
			}
		}

		// System.out.print(joey);
		// print(joeyMap, System.out);
		// System.out.print(chandler);
		// print(chandlerMap, System.out);
		// System.out.print(ross);
		// print(rossMap, System.out);
		// System.out.print(rachel);
		// print(rachelMap, System.out);
		// System.out.print(phoebe);
		// print(phoebeMap, System.out);
		// System.out.print(joey);
		// print(monicaMap, System.out);

		System.out.println("How many times does Joey speak?\n" + dialogCountTotal(joeyMap));
		System.out.println("How many times does Chandler speak?\n" + dialogCountTotal(chandlerMap));
		System.out.println("How many times does Ross speak?\n" + dialogCountTotal(rossMap));
		System.out.println("How many times does Monica speak?\n" + dialogCountTotal(monicaMap));
		System.out.println("How many times does Phoebe speak?\n" + dialogCountTotal(phoebeMap));
		System.out.println("How many times does Rachel speak?\n" + dialogCountTotal(rachelMap));

		seasonStats("1");
		seasonStats("2");
		seasonStats("3");
		seasonStats("4");
		seasonStats("5");
		seasonStats("6");
		seasonStats("7");
	}

	public static void seasonStats(String seasonPrefix) {
		System.out.println("Season#" + seasonPrefix + " Stats.");
		System.out.println("how many times does Joey speak in Season " + seasonPrefix + "?\n" + dialogCountTotalPerSeason(joeyMap, seasonPrefix));
		System.out.println("how many times does Chandler speak in Season " + seasonPrefix + "?\n" + dialogCountTotalPerSeason(chandlerMap, seasonPrefix));
		System.out.println("how many times does Ross speak in Season " + seasonPrefix + "?\n" + dialogCountTotalPerSeason(rossMap, seasonPrefix));
		System.out.println("how many times does Monica speak in Season " + seasonPrefix + "?\n" + dialogCountTotalPerSeason(monicaMap, seasonPrefix));
		System.out.println("how many times does Phoebe speak in Season " + seasonPrefix + "?\n" + dialogCountTotalPerSeason(phoebeMap, seasonPrefix));
		System.out.println("how many times does Rachel speak in Season " + seasonPrefix + "?\n" + dialogCountTotalPerSeason(rachelMap, seasonPrefix));
	}

	public static TreeMap<String, Integer> 
	updateMap(TreeMap<String, Integer> map, String key) {
		int count = map.get(key) == null ? 0 : map.get(key);
		map.put(key, ++count);
		return map;
	}

	public static void print(TreeMap<String, Integer> map, PrintStream out) {
		out.println(map.toString());
	}

	public static int dialogCountTotal(TreeMap<String, Integer> map) {
		int count  = 0;
		for (String key : map.keySet()) {
			count += map.get(key);
		}
		return count;
	}

	public static int dialogCountTotalPerSeason(
		TreeMap<String, Integer> map,
		String seasonPrefix) {
		int count = 0;
		int episodeCount = 0;
		for (String key : map.keySet()) {
			if(!key.startsWith(seasonPrefix)) continue;
			episodeCount++;
			count += map.get(key);
		}
		System.out.print("In " + episodeCount + " episodes, ");		
		return count;
	}
}