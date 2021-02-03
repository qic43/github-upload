import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class TreeUtil {


    /**
     * eading a representation of a Huffman tree from a file and transfer it into a tree
     * @param file the file that store the tree information
     * @return the root node of the tree
     * @throws FileNotFoundException
     */
    public BinaryNode<Character> buildTree(String file) throws FileNotFoundException {
        Scanner sc = new Scanner(new FileInputStream(file));
        BinaryNode<Character> root = new BinaryNode<>('\0');
        sc.nextLine();
        buildTril(sc, root);
        buildTril(sc, root);
        sc.close();
        return root;
    }

    private void buildTril(Scanner sc, BinaryNode<Character> parent)
    {
        String line = sc.nextLine();
        if(line.equals("I"))
        {
            BinaryNode<Character> node = new BinaryNode<>('\0');
            if(parent.left == null)
            {
                parent.left = node;
            }else
            {
                parent.right = node;
            }
            buildTril(sc, node);
            buildTril(sc, node);
        }else
        {
            Character c = line.charAt(2);
            BinaryNode<Character> node = new BinaryNode<>(c);
            if(parent.left == null)
            {
                parent.left = node;
            }else
            {
                parent.right = node;
            }
        }
    }


    public int getLetterNUm(String file) throws FileNotFoundException {
        Scanner sc = new Scanner(new FileInputStream(file));
        int num = 0;
        while (sc.hasNext())
        {
            if(!sc.nextLine().trim().equals("I"))
                num++;
        }
        sc.close();
        return num;
    }

    public String[] buildTable(BinaryNode<Character> root, int letterNUm)
    {
        String[] table = new String[letterNUm];
        StringBuilder sb = new StringBuilder();
        inOrder1(root.left, sb, table, true);
        sb = new StringBuilder();
        inOrder1(root.right, sb, table, false);
        return table;
    }



    private void inOrder1(BinaryNode<Character> node, StringBuilder sb, String[] table, boolean isLeft)
    {
        if(node != null)
        {
            if(node.value != '\0')
            {
                if(isLeft)
                {
                    table[node.value - 'A'] = sb.toString() + "0";
                }else
                {
                    table[node.value - 'A'] = sb.toString() + "1";
                }
            }else
            {
                if(isLeft)
                {
                    String s = sb.toString();
                    sb.append("0");
                    inOrder1(node.left, sb, table, true);
                    sb = new StringBuilder(s);
                    sb.append("0");
                    inOrder1(node.right, sb, table, false);
                    sb = new StringBuilder(s);

                }else
                {
                    String s = sb.toString();
                    sb.append("1");
                    inOrder1(node.left, sb, table, true);
                    sb = new StringBuilder(s);
                    sb.append("1");
                    inOrder1(node.right, sb, table, false);
                    sb = new StringBuilder(s);
                }
            }
        }
    }
}
