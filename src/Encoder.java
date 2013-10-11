import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;


public class Encoder {
	
	static int totalSum;
	static int k;
	private static final File testText = new File("testText.txt");
	private static final File testTextEncl = new File("testText.encl");
	
	public static void main(String[] args) throws FileNotFoundException {
		File file1 = new File(args[0]);
		k = Integer.parseInt(args[1]);
		//int freq = Integer.parseInt(args[1]);
		ArrayList<Integer> freqManager = new ArrayList<Integer>();
		
		Scanner scan = new Scanner(file1);
		while (scan.hasNextLine()) {
			int curLine = Integer.parseInt(scan.nextLine());
			freqManager.add(curLine);
			System.out.println(curLine);
		}
		totalSum = getFreqSize(freqManager);
		
		ArrayList<Double> probManager = new ArrayList<Double>();
		for (int i = 0; i < freqManager.size(); i++) {
			double temp = (double) freqManager.get(i)/ (double) totalSum;
			probManager.add(temp);
			System.out.println(probManager.get(i));
		}
		scan.close();

		Huffman.processFile(freqManager);
		try {
			stepThree(probManager);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			encode();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void encode() throws IOException {
		BufferedWriter out = new BufferedWriter(new FileWriter(testTextEncl));
		HashMap<Character, String> encManager = Node.getEncManager();
		String s, value;
		
		Scanner scan = new Scanner(testText);
		System.out.println("testText.txt: ");
		while (scan.hasNextLine()) {
			s = scan.nextLine();
			System.out.print(s);
			value = encManager.get(s.charAt(0));
			out.write(value);
			out.newLine();
		}
		System.out.println();
		out.close();
		
		Scanner scan2 = new Scanner(testTextEncl);
		System.out.println("testText.encl: ");
		while (scan2.hasNextLine()) {
			s = scan2.nextLine();
			System.out.print(s);
		}
		System.out.println();
	}
	
	public static void stepThree(ArrayList<Double> probManager) throws IOException {
		//PrintWriter out = new PrintWriter("testText");
		BufferedWriter out = new BufferedWriter(new FileWriter(testText));
		int i, j, ran;
		Random rand = new Random();
		double newRan, counter = 0;
		
		for (i = 0; i < k; i++) {
			ran = rand.nextInt(totalSum - 1);
			newRan = (double) ran/ (double) totalSum;
			
			for (j = 0; j < probManager.size(); j++) {
				double temp = (double) (probManager.get(j) + counter);
				if (newRan >= counter && newRan <= temp) {
					int letter = 65 + j;
					char ch = (char) letter;
					out.write(ch);
					out.newLine();
					break;
				}
				counter += probManager.get(j);
			}
			counter = 0;
			
		}
		
		out.close();
		
	}
	
	
	
	public static int getFreqSize (ArrayList<Integer> frequencies) {
		int sum = 0;
		
		for (int i = 0; i < frequencies.size(); i++) {
			sum += frequencies.get(i);
		}
		System.out.println(sum);
		return sum;		
	}

}
