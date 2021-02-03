public class CodeUtil {

    public String encode(String s, String[] table)
    {
        StringBuilder result = new StringBuilder();
        for(Character c: s.toCharArray())
        {
            result.append(table[c-'A']);
            result.append("\n");
        }
        return result.toString();
    }


    public String decode(String s, BinaryNode<Character> root)
    {
        BinaryNode<Character> temp = root;
        StringBuilder result = new StringBuilder();
        for(Character c: s.toCharArray())
        {
            if(c == '0')
                temp = temp.left;
            else
                temp = temp.right;
            if(temp.value != '\0')
            {
                result.append(temp.value);
                temp = root;
            }
        }
        if(temp != root)
            throw new RuntimeException();

        return result.toString();
    }
}
