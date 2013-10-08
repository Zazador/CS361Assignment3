import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.TreeSet;

public class Huffman
{
//        public static void main(String[] args)
//                throws IOException
//        {
//                StringBuffer fileContents = new StringBuffer();
//
//                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//
//                String line = null;
//                while ((line = br.readLine()) != null)
//                        fileContents.append("\n").append(line);
//
//                processFile(fileContents.toString());
//        }

        static void processFile(ArrayList<Integer> probManager)
        {
//                int[] frequency = new int['Z'-'A'+1];           // Frequency table of each letter
                TreeSet<Node> trees     = new TreeSet<Node>();  // List containing all trees -- ORDERED!
//
//                // Build the frequency table of each letter
//                for (int i=0; i<fileContents.length(); i++)
//                {
//                        char ch = Character.toUpperCase(fileContents.charAt(i));
//                        if ((ch >= 'A') && (ch <= 'Z'))
//                                ++frequency[ch - 'A'];
//                }

                // Build up the initial trees
                for (int i=0; i<'Z'-'A'+1; i++)
                {
                        if (probManager.get(i) > 0)
                        {
                                Node n = new Node((char)('A'+i), probManager.get(i));
                                trees.add(n);
                        }
                }

                // Huffman algoritm
                while (trees.size() > 1)
                {
                        Node tree1 = (Node) trees.first();
                        trees.remove(tree1);
                        Node tree2 = (Node) trees.first();
                        trees.remove(tree2);

                        Node merged = new Node(tree1, tree2);
                        trees.add(merged);
                }

                // Print the resulting tree
                if (trees.size() > 0)
                {
                        Node theTree = (Node) trees.first();
                        Node.printTree(theTree);
                }
                else
                        System.out.println("The file didn't contain useful characters.");
        }
}