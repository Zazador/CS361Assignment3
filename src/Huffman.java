//Name: Zach Zador
//email: zazador@gmail.com
//CSID: sakz
//UTEID: zaz78
//
//Name: Mike Schiller
//email: schillbs@gmail.com
//CSID: schiller
//UTEID: mds3428

/*
 * Huffman Algorithm for the DEI's Programming Contest, 2004
 * (c) Paulo Marques, 2004.
 * pmarques@dei.uc.pt
 *
 * Note: this program only process text characters:
 *       ('a'-'z' / 'A'-'Z'). Everything else is ignored.
 */

import java.util.ArrayList;
import java.util.TreeSet;

public class Huffman {

	public static ArrayList<String> tuples = new ArrayList<String>();

	static void processFile(ArrayList<Double> probManager, boolean two) {

		TreeSet<Node> trees = new TreeSet<Node>(); // List containing all trees
													// -- ORDERED!
		TreeSet<Node> trees2 = new TreeSet<Node>();

		if (!two) {
			// Build up the initial trees
			for (int i = 0; i < 'Z' - 'A' + 1; i++) {
				// System.out.println(probManager.get(i));
				// System.out.println((char) ('A' + i));
				if (probManager.get(i) > 0) {
					Node n = new Node((char) ('A' + i), probManager.get(i));
					trees.add(n);
				}
			}
		} else if (two) {
			String s;
			// Build up the initial trees
			for (int i = 0; i < 'Z' - 'A' + 1; i++) {
				for (int j = 0; j < 'Z' - 'A' + 1; j++) {
					if (probManager.get(j) > 0 && probManager.get(i) > 0) {
						s = new StringBuilder().append((char) ('A' + i))
								.append((char) ('A' + j)).toString();
						tuples.add(s);
						Node n = new Node(s, probManager.get(i)
								* probManager.get(j));
						trees2.add(n);
					}
				}
			}
		}

		System.out.println(trees.size());
		if (!two) {
			// Huffman algorithm
			while (trees.size() > 1) {
				Node tree1 = (Node) trees.first();
				trees.remove(tree1);
				Node tree2 = (Node) trees.first();
				trees.remove(tree2);

				Node merged = new Node(tree1, tree2, false);
				trees.add(merged);
			}
		} else if (two) {
			// Huffman algorithm for two letter symbols
			while (trees2.size() > 1) {
				Node tree1 = (Node) trees2.first();
				trees2.remove(tree1);
				Node tree2 = (Node) trees2.first();
				trees2.remove(tree2);

				Node merged = new Node(tree1, tree2, true);
				trees2.add(merged);
			}
		}

		if (!two) {
			// Print the resulting tree
			if (trees.size() > 0) {
				Node theTree = (Node) trees.first();
				Node.printTree(theTree, false);
			}
		} else if (two) {
			// Print the resulting tree of two letter symbols
			if (trees2.size() > 0) {
				Node theTree = (Node) trees2.first();
				Node.printTree(theTree, true);
			}
		} else
			System.out.println("The file didn't contain useful characters.");
	}

	public static ArrayList<String> getTuples() {
		return tuples;
	}
}