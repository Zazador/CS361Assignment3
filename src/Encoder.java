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
	private static final File testTextEnc1 = new File("testText.enc1");
	private static final File testTextDec1 = new File("testText.dec1");
	private static final File testText2 = new File("testText2.txt");
	private static final File testTextEnc2 = new File("testText.enc2");
	private static final File testTextDec2 = new File("testText.dec2");

	public static void main(String[] args) throws FileNotFoundException {
		File file1 = new File(args[0]);
		k = Integer.parseInt(args[1]);
		ArrayList<Integer> freqManager = new ArrayList<Integer>();

		Scanner scan = new Scanner(file1);
		while (scan.hasNextLine()) {
			int curLine = Integer.parseInt(scan.nextLine());
			freqManager.add(curLine);
		}
		totalSum = getFreqSize(freqManager);

		ArrayList<Double> probManager = new ArrayList<Double>();
		double entropy = 0;
		for (int i = 0; i < freqManager.size(); i++) {
			if (freqManager.get(i) > 0)
				entropy += ((1/(double) freqManager.get(i)) * (Math.log((1/(double)freqManager.get(i))) / Math.log(2)));
			double temp = (double) freqManager.get(i) / (double) totalSum;
			probManager.add(temp);
		}
		entropy *= -1;
		System.out.println("Entropy = " + entropy);
		scan.close();

		Huffman.processFile(probManager, false);
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

		try {
			decode();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		entropy = 0;
		ArrayList<Double> probManager2 = new ArrayList<Double>();
			for (int i = 0; i < freqManager.size(); i++) {
				if (freqManager.get(i) > 0)
					entropy += ((1/(double) freqManager.get(i)) * (Math.log((1/(double)freqManager.get(i))) / Math.log(2)));
				double temp = (double) probManager.get(i)
						* (double) probManager.get(i);
				probManager2.add(temp);
		}
			
			entropy *= -1;
			System.out.println("Entropy = " + entropy);

		Huffman.processFile(probManager2, true);

		try {
			stepThreeRedux(probManager);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			encode2();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			decode2();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void encode() throws IOException {
		BufferedWriter out = new BufferedWriter(new FileWriter(testTextEnc1));
		HashMap<Character, String> encManager = Node.getEncManager();
		String s, value;
		double bitCount = 0, charCount = 0;

		Scanner scan = new Scanner(testText);
		System.out.println("testText.txt: ");
		while (scan.hasNextLine()) {
			s = scan.nextLine();
			System.out.print(s);
			value = encManager.get(s.charAt(0));
			out.write(value);
			out.newLine();
			bitCount += value.length();
			charCount++;
		}
		System.out.println();
		System.out.println("Bits per symbol = " + (bitCount/charCount));
		out.close();

		Scanner scan2 = new Scanner(testTextEnc1);
		System.out.println("testText.enc1: ");
		while (scan2.hasNextLine()) {
			s = scan2.nextLine();
			System.out.print(s);
		}
		System.out.println();
	}

	public static void decode() throws IOException {
		BufferedWriter out = new BufferedWriter(new FileWriter(testTextDec1));
		HashMap<String, Character> decManager = Node.getDecManager();
		String s;
		char value;

		Scanner scan = new Scanner(testTextEnc1);
		while (scan.hasNextLine()) {
			s = scan.nextLine();
			value = decManager.get(s);
			out.write(value);
			out.newLine();
		}
		out.close();

		Scanner scan2 = new Scanner(testTextDec1);
		System.out.println("testText.dec1: ");
		while (scan2.hasNextLine()) {
			s = scan2.nextLine();
			System.out.print(s);
		}
		System.out.println();
	}

	public static void stepThree(ArrayList<Double> probManager)
			throws IOException {
		BufferedWriter out = new BufferedWriter(new FileWriter(testText));
		int i, j, ran;
		Random rand = new Random();
		double newRan, counter = 0;

		for (i = 0; i < k; i++) {
			ran = rand.nextInt(totalSum - 1);
			newRan = (double) ran / (double) totalSum;

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

	public static void stepThreeRedux(ArrayList<Double> probManager)
			throws IOException {
		BufferedWriter out = new BufferedWriter(new FileWriter(testText2));
		int i, j, ran;
		Random rand = new Random();
		double newRan, counter = 0;
		ArrayList<String> tuples = Huffman.getTuples();

		for (i = 0; i < k; i++) {
			ran = rand.nextInt(totalSum - 1);
			newRan = (double) ran / (double) totalSum;

			for (j = 0; j < probManager.size(); j++) {
				double temp = (double) (probManager.get(j) + counter);
				if (newRan >= counter && newRan <= temp) {
					out.write(tuples.get(j));
					out.newLine();
					break;
				}
				counter += probManager.get(j);
			}
			counter = 0;
		}
		out.close();
	}

	public static void encode2() throws IOException {
		BufferedWriter out = new BufferedWriter(new FileWriter(testTextEnc2));
		HashMap<String, String> encManager = Node.getEncManager2();
		String s, value;

		Scanner scan = new Scanner(testText2);
		System.out.println("testText2.txt: ");
		while (scan.hasNextLine()) {
			s = scan.nextLine();
			System.out.print(s);
			value = encManager.get(s);
			out.write(value);
			out.newLine();
		}
		System.out.println();
		out.close();

		Scanner scan2 = new Scanner(testTextEnc2);
		System.out.println("testText.enc2: ");
		while (scan2.hasNextLine()) {
			s = scan2.nextLine();
			System.out.print(s);
		}
		System.out.println();
	}

	public static void decode2() throws IOException {
		BufferedWriter out = new BufferedWriter(new FileWriter(testTextDec2));
		HashMap<String, String> decManager = Node.getDecManager2();
		String s, value;

		Scanner scan = new Scanner(testTextEnc2);
		while (scan.hasNextLine()) {
			s = scan.nextLine();
			value = decManager.get(s);
			out.write(value);
			out.newLine();
		}
		out.close();

		Scanner scan2 = new Scanner(testTextDec2);
		System.out.println("testText.dec2: ");
		while (scan2.hasNextLine()) {
			s = scan2.nextLine();
			System.out.print(s);
		}
		System.out.println();
	}

	public static int getFreqSize(ArrayList<Integer> frequencies) {
		int sum = 0;

		for (int i = 0; i < frequencies.size(); i++) {
			sum += frequencies.get(i);
		}
		return sum;
	}

}
