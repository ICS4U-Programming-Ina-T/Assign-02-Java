import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * The StringStuff program implements an application that
 * uses a function to alter the contents of a string based
 * on the number characters present.
 *
 * @author  Ina Tolo
 * @version 1.0
 * @since   2022-4-1
 */

class StringStuff {
    /**
     * This function alters a string based on the numbers.  
     *
     * @param
     * @return
     */
    public static String blowup(char[] tempArray) {
        // declaring variables
        // char[] tempArray = new char[stringValue.length()];
        // System.out.println(tempArray);
        String newString = "";
        int charInt;

        // adds each element of string to a char array
        // https://www.geeksforgeeks.org/convert-a-string-to-character-array-in-java/
        // for (int i = 0; i < stringValue.length(); i++) {
        //     tempArray[i] = stringValue.charAt(i);
        // }

        // System.out.print(tempArray);

        for (int cursor = 0; cursor < tempArray.length - 1; cursor++) {
            if (tempArray[cursor] == (int) tempArray[cursor]) {
                charInt = (int) tempArray[cursor];
                for (int numCount = 0; numCount <= charInt; numCount++) {
                    newString += tempArray[cursor + 1];
                }
            } else {
                newString += tempArray[cursor];
            }
        }

        return newString;
    }

    /**
     * Main entry into the program.
     * 
     * @param args nothing passed in
     */
    public static void main(String[] args) {
        System.out.println("The strings in the input file will be reversed.");
        System.out.println();

        // declaring variables
        List<String> listOftrings = new ArrayList<String>();
        final String[] stringsArrayFile;
        final String[] alteredStringsArray;
        final BufferedWriter writer;
        final StringBuilder builder;
        final List<String> alteredList = new ArrayList<String>();
        String blowupStringUser;

        // reads contents of file into list
        try {
            listOftrings = Files.readAllLines(Paths.get("input.txt"));
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        // converts contents of list to an array
        stringsArrayFile = listOftrings.toArray(new String[0]);

        try {
            // calls function and passes each individual element in the array
            for (int loopCounter = 0; loopCounter
                < stringsArrayFile.length; loopCounter++) {
                char[] tempArray = new char[stringsArrayFile[loopCounter].length()];
                for (int i = 0; i < stringsArrayFile[loopCounter].length(); i++) {
                    tempArray[i] = stringsArrayFile[loopCounter].charAt(i);
                }
                System.out.println(tempArray);
                blowupStringUser = blowup(tempArray);
                // System.out.println(blowupStringUser);
                alteredList.add(blowupStringUser);
            }

            // converts list of reversed strings to an array
            alteredStringsArray = alteredList.toArray(new String[0]);

            // adds the new reversed strings to the output file
            builder = new StringBuilder();
            for (int formatCounter = 0; formatCounter
                < alteredStringsArray.length; ++formatCounter) {
                builder.append(alteredStringsArray[formatCounter]);
                if (formatCounter != alteredStringsArray.length - 1) {
                    builder.append("\n");
                }
            }

            writer =
                new BufferedWriter(new FileWriter(
                    "/home/ubuntu/environment"
                    + "/Assign/Assign-02/Assign-02"
                    + "-Java/output.txt"));
            writer.write(builder.toString());
            writer.close();

            // displays alert saying the strings have been reversed
            System.out.println("Done altering strings. Check the output file.");
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
