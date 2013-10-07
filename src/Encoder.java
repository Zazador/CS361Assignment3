import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


public class Encoder {
	
	static ArrayList<Integer> freqManager;
	static int totalSum;
	
	public static void main(String[] args) throws FileNotFoundException {
		File file1 = new File(args[0]);
		int freq = Integer.parseInt(args[1]);
		
		Scanner scan = new Scanner(file1);
		while (scan.hasNextLine()) {
			int curLine = Integer.parseInt(scan.nextLine());
			freqManager.add(curLine);
		}
		totalSum = getFreqSize(freqManager);
		scan.close();

	}
	
	public static int getFreqSize (ArrayList<Integer> frequencies) {
		int sum = 0;
		
		for (int i = 0; i < frequencies.size(); i++) {
			sum += frequencies.get(i);
		}
		return sum;		
	}

}
