
/*
 * Huffman Algorithm for the DEI's Programming Contest, 2004
 * (c) Paulo Marques, 2004.
 * pmarques@dei.uc.pt
 *
 * Note: this program only process text characters:
 *       ('a'-'z' / 'A'-'Z'). Everything else is ignored.
 */

import java.io.*;
import java.util.*;

class Node
        implements Comparable
{
        private double     value;
        private char    content;
        private Node    left;
        private Node    right;
        public static HashMap<Character, String> encManager = new HashMap<Character, String>();

        public Node(char content, int value)
        {
                this.content  = content;
                this.value    = value;
        }

        public Node(Node left, Node right)
        {
                // Assumes that the left three is always the one that is lowest
                this.content  = (left.content < right.content) ? left.content : right.content;
                this.value    = left.value + right.value;
                this.left         = left;
                this.right    = right;
        }

        public int compareTo(Object arg)
        {
                Node other = (Node) arg;

                // Content value has priority and then the lowest letter
                if (this.value == other.value)
                        return this.content-other.content;
                else
                        return (int) (this.value-other.value);
        }

        ////////////////

        private void printNode(String path)
        {
                if ((left==null) && (right==null)) {
                	System.out.println(content + " " + path);
                        encManager.put(content, path);
                }

                if (left != null)
                        left.printNode(path + '0');          
                if (right != null)
                        right.printNode(path + '1');
        }

        public static void printTree(Node tree)
        {
                tree.printNode("");
        }
        
        public static HashMap<Character, String> getEncManager() {
        	return encManager;
        }
}

