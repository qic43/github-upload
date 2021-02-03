import java.io.FileNotFoundException;
import java.util.Scanner;

public class Assig4 {
    public static void main(String[] args) throws FileNotFoundException {
        TreeUtil treeUtil = new TreeUtil();
        CodeUtil codeUtil = new CodeUtil();
        BinaryNode<Character> root = treeUtil.buildTree(args[0]);
        int letterNUm = treeUtil.getLetterNUm(args[0]);
        String[] table = treeUtil.buildTable(root, letterNUm);


        System.out.println("The Huffman Tree has been restored");

        Scanner sc = new Scanner(System.in);
        int choice = -1;
        while (choice != 3)
        {
            prompt();
            choice = sc.nextInt();
            switch (choice)
            {
                case 1: {
                    System.out.println("Enter a String from the following characters: ");
                    for(int i = 0; i < table.length; i++)
                    {
                        System.out.print((char)('A' + i));
                    }
                    System.out.println();
                    String s = sc.next();
                    try {
                        String encode = codeUtil.encode(s, table);
                        System.out.println("Huffman String:");
                        System.out.print(encode);
                    }catch (Exception e)
                    {
                        System.out.println("There was an error in your text string");
                    }
                    break;
                }
                case 2: {
                    System.out.println("Here is the encoding table:");
                    for(int i = 0; i < table.length; i++)
                    {
                        Character c = (char)('A' + i);
                        System.out.println(c + ": " + table[i]);
                    }
                    System.out.println("Please enter a Huffman string (one line, no spaces)");
                    String s = sc.next();
                    try {
                        String decode = codeUtil.decode(s, root);
                        System.out.println("Text string:");
                        System.out.println(decode);
                    }catch (Exception e)
                    {
                        System.out.println("There was an error in your Huffman string");
                    }
                    break;
                }
            }

        }
        sc.close();
        System.out.println("Good-bye");

    }

    public static void prompt()
    {
        System.out.println("\n" +
                "Please choose from the following:\n" +
                "1) Encode a text string\n" +
                "2) Decode a Huffman string\n" +
                "3) Quit");
    }
}
