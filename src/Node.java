
/*
 * Huffman Algorithm for the DEI's Programming Contest, 2004
 * (c) Paulo Marques, 2004.
 * pmarques@dei.uc.pt
 *
 * Note: this program only process text characters:
 *       ('a'-'z' / 'A'-'Z'). Everything else is ignored.
 */


import java.util.*;

class Node
        implements Comparable<Object>
{
        private double     value;
        private char    content;
        private String tuple;
        private Node    left;
        private Node    right;
        public static HashMap<Character, String> encManager = new HashMap<Character, String>();
        public static HashMap<String, Character> decManager = new HashMap<String, Character>();
        public static HashMap<String, String> encManager2 = new HashMap<String, String>();
        public static HashMap<String, String> decManager2 = new HashMap<String, String>();

        public Node(char content, double value)
        {
                this.content  = content;
                this.value    = value;
        }
        
        public Node(String tuple, double value)
        {
                this.tuple  = tuple;
                this.value    = value;
        }

        public Node(Node left, Node right, boolean two)
        {
        	if (!two) {
                // Assumes that the left three is always the one that is lowest
                this.content  = (left.content < right.content) ? left.content : right.content;
                this.value    = left.value + right.value;
                this.left         = left;
                this.right    = right;
        	}
        	else if (two) {
        		// Assumes that the left three is always the one that is lowest
                this.tuple  = (left.tuple.compareTo(right.tuple) < 0) ? left.tuple : right.tuple;
                this.value    = left.value + right.value;
                this.left         = left;
                this.right    = right;
        	}
        }

        public int compareTo(Object arg)
        {
                Node other = (Node) arg;

                // Content value has priority and then the lowest letter
                if (this.value == other.value)
                        return this.content-other.content;
                else {
                        if (this.value > other.value)
                        	return 1;
                        else
                        	return -1;
                }
        }

        ////////////////

        private void printNode(String path, boolean check)
        {
        	if (!check) {
                if ((left==null) && (right==null)) {
                        encManager.put(content, path);
                        decManager.put(path, content);
                }

                if (left != null)
                        left.printNode(path + '0', false);          
                if (right != null)
                        right.printNode(path + '1', false);
        	}
        	else if (check) {
        		if ((left==null) && (right==null)) {
                        encManager2.put(tuple, path);
                        decManager2.put(path, tuple);
                }

                if (left != null)
                        left.printNode(path + '0', true);          
                if (right != null)
                        right.printNode(path + '1', true);
        	}
        }

        public static void printTree(Node tree, boolean check)
        {
                tree.printNode("", check);
        }
        
        public static HashMap<Character, String> getEncManager() {
        	return encManager;
        }
        
        public static HashMap<String, Character> getDecManager() {
        	return decManager;
        }
        
        public static HashMap<String, String> getEncManager2() {
        	return encManager2;
        }
        
        public static HashMap<String, String> getDecManager2() {
        	return decManager2;
        }
        
}

