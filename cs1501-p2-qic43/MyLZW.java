/*************************************************************************
 *  Compilation:  javac MyLZW.java
 *  Execution:    java MyLZW - option < input.txt > output.lzw   (compress)
 *  Execution:    java MyLZW + option < input.lzw > output.txt (expand)
 *  Dependencies: BinaryIn.java BinaryOut.java
 *
 *  Compress or expand binary input from standard input using LZW.
 *
 *  WARNING: STARTING WITH ORACLE JAVA 6, UPDATE 7 the SUBSTRING
 *  METHOD TAKES TIME AND SPACE LINEAR IN THE SIZE OF THE EXTRACTED
 *  SUBSTRING (INSTEAD OF CONSTANT SPACE AND TIME AS IN EARLIER
 *  IMPLEMENTATIONS).
 *
 *  See <a href = "http://java-performance.info/changes-to-string-java-1-7-0_06/">this article</a>
 *  for more details.
 *
 *************************************************************************/

public class MyLZW {
    private static final int R = 256;        // number of input chars
    private static final int MIN_WIDTH = 9;
    private static final int MAX_WIDTH = 16;
    private static final double THRESHOLD = 1.1;
    private static int W = MIN_WIDTH;         // codeword width [9, 16]
    private static int L = (int) Math.pow(2, W);       // number of codewords = 2^W
    private static int mode = 1; // 1 for Do Nothing mode, 2 for Reset mode, 3 for Moniter mode.


    public static void compress() { 
        String input = BinaryStdIn.readString();
        TST<Integer> st = new TST<Integer>();
        for (int i = 0; i < R; i++)
            st.put("" + (char) i, i);
        int code = R+1;  // R is codeword for EOF

        // record mode
        BinaryStdOut.write(mode);

        int uncompressedSize = 0; // the size of the uncompressed data that has been processed
        int compressedSize = 0; // the size of the compressed data that has been generated
        double oldRatio = 0.0; // ratio is defined as uncompressedSize / compressedSize
        double newRatio = 0.0; // ratio is defined as uncompressedSize / compressedSize

        while (input.length() > 0) {
            String s = st.longestPrefixOf(input);  // Find max prefix match s.
            BinaryStdOut.write(st.get(s), W);      // Print s's encoding.
            int t = s.length();

            // W bits have just been compressed.
            compressedSize += W;
            uncompressedSize += (t * 16); // each char takes 16 bits and we have string of t characters

            if (t < input.length() && code < L) {   // Add s to symbol table.
                st.put(input.substring(0, t + 1), code++);
                oldRatio = (double) uncompressedSize / compressedSize;
            } else if (t < input.length() && code >= L) {
                // Codebook is filled up
                if (W < MAX_WIDTH) {
                    W++;
                    L = (int) Math.pow(2, W);
                    st.put(input.substring(0, t + 1), code++);
                } else if (W == MAX_WIDTH) {
                    if (mode == 2) {
                        // Reset mode
                        st = new TST<Integer>();
                        for (int i = 0; i < R; i++) {
                            st.put("" + (char) i, i);
                        }
                        W = MIN_WIDTH;
                        L = (int) Math.pow(2, W);
                        code = R+1;
                        // add the new codeword
                        st.put(input.substring(0, t + 1), code++);
                    } else if (mode == 3) {
                        // moniter mode
                        newRatio = (double) uncompressedSize / compressedSize;
                        if (oldRatio / newRatio > THRESHOLD) {
                            st = new TST<Integer>();
                            for (int i = 0; i < R; i++) {
                                st.put("" + (char) i, i);
                            }
                            W = MIN_WIDTH;
                            L = (int) Math.pow(2, W);
                            code = R+1;
                            // add the new codeword
                            st.put(input.substring(0, t + 1), code++);
                            oldRatio = 0.0;
                        }
                    }
                }
            }
            input = input.substring(t);            // Scan past s in input.
        }
        BinaryStdOut.write(R, W);
        BinaryStdOut.close();
    } 


    public static void expand() {
        String[] st = new String[(int) Math.pow(2, MAX_WIDTH)];
        int i; // next available codeword value

        // initialize symbol table with all 1-character strings
        for (i = 0; i < R; i++)
            st[i] = "" + (char) i;
        st[i++] = "";                        // (unused) lookahead for EOF

        // First int we stored is the mode, either 1, 2 or 3
        // Get the mode for when the file is compressed
        mode = BinaryStdIn.readInt();
        if (mode < 1 || mode > 3) {
            System.err.println("Please make sure compression mode is n, r, or m");
            return;
        }

        int codeword = BinaryStdIn.readInt(W);
        if (codeword == R) return;           // expanded message is empty string
        String val = st[codeword];

        int uncompressedSize = 0; // the size of the uncompressed data that has been generated
        int compressedSize = 0; // the size of the compressed data that has been processed
        double oldRatio = 0.0; // ratio is defined as uncompressedSize / compressedSize
        double newRatio = 0.0; // ratio is defined as uncompressedSize / compressedSize

        while (true) {
            BinaryStdOut.write(val);
            compressedSize += W; // We've just processed W bit of data from compressed file
            uncompressedSize += (val.length() * 16); // each char takes 16 bits and we have just uncompressed val.length() characters

            if (i >= L) {
                // if i is greater than max number of codewords under current W
                if (W < MAX_WIDTH) {
                    W++;
                    L = (int) Math.pow(2, W);
                    oldRatio = (double) uncompressedSize / compressedSize;
                } else if (W == MAX_WIDTH) {
                    if (mode == 2) {
                        // reset mode
                        st = new String[(int) Math.pow(2, MAX_WIDTH)];
                        for (i = 0; i < R; i++) {
                            st[i] = "" + (char) i;
                        }
                        st[i++] = "";
                        W = MIN_WIDTH;
                        L = (int) Math.pow(2, W);
                        i = R+1;
                    } else if (mode == 3) {
                        // moniter mode
                        newRatio = (double) uncompressedSize / compressedSize;
                        if (oldRatio / newRatio > THRESHOLD) {
                            // reset codebook
                            st = new String[(int) Math.pow(2, MAX_WIDTH)];
                            for (i = 0; i < R; i++) {
                                st[i] = "" + (char) i;
                            }
                            st[i++] = "";
                            W = MIN_WIDTH;
                            L = (int) Math.pow(2, W);
                            i = R+1;
                            oldRatio = 0.0;
                        }
                    }
                }
            }

            codeword = BinaryStdIn.readInt(W);
            if (codeword == R) break;
            String s = st[codeword];
            if (i == codeword) s = val + val.charAt(0);   // special case hack
            if (i < L) {
                st[i++] = val + s.charAt(0);
                oldRatio = (double) uncompressedSize / compressedSize;
            }
            val = s;
        }
        BinaryStdOut.close();
    }



    public static void main(String[] args) {
        if(args.length >= 2){ // mode specified
            if(args[1].equals("n")) {
                mode = 1;
            } else if (args[1].equals("r")) {
                mode = 2;
            } else if (args[1].equals("m")) {
                mode = 3;
            } else {
                System.err.println("Please make sure mode is n, r, or m. Now choose default do-nothing mode.");
            }
        }

        if      (args[0].equals("-")) compress();
        else if (args[0].equals("+")) expand();
        else throw new IllegalArgumentException("Illegal command line argument");
    }

}
