/**
 * @author  Raul Aguilar
 * @class   CS 220 2525
 * @date    October 24, 2018
 */
import java.io.*;
import java.util.Scanner;

public class Parser {
    public static final char NO_COMMAND = 'N';
    public static final char A_COMMAND = 'A';
    public static final char C_COMMAND = 'C';
    public static final char L_COMMAND = 'L';

    private Scanner inputFile = new Scanner(System.in);
    private int lineNumber;
    private String rawLine;
    private String cleanLine;
    private char commandType;
    private String symbol;
    private String destMnemonic;
    private String compMnemonic;
    private String jumpMnemonic;

    /**
     * Opens input file and prepares to parse
     * If files can't be opened, ends program with error message
     * @param inFileName the name of the asm file
     */
    public void Parser(String inFileName) {
        BufferedReader in;
        try {
            in = new BufferedReader(new FileReader(inFileName));
        } catch (FileNotFoundException e) {
            System.out.println("File could not be opened. Ending program.");
            System.exit(0);
        }
    }

    /**
     * Reads raw line from file and stripes line of whitespace and comments
     * @param rawLine String of a single line from file before cleaned
     * @return String of clean line without comments and whitespace
     */
    private static String cleanLine (String rawLine) {
        String clean;
        int commentLocation;

        // remove whitespace
        clean = rawLine.replaceAll(" ","");
        clean = clean.replaceAll("\t","");

        // remove comments
        commentLocation = clean.indexOf("//");
        if(commentLocation != -1) {
            clean = clean.substring(0,commentLocation);
        }

        return clean;
    }

    /**
     * Guesses which command type it is from cleaned line
     * @param clean String clean line without comments or whitespace
     * @return Char corresponding to guessed command type
     */
    private static char parseCommandType(String clean) {

        if( clean == null || clean.length() == 0) {
            return NO_COMMAND; //N
        }

        char first = clean.charAt(0);
        if (first == '(') {
            return L_COMMAND; // L
        } else if (first == '@') {
            return A_COMMAND; // A
        } else {
            return C_COMMAND; // C
        }
    }
}