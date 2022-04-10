import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * The StringStuff program implements an application that
 * uses a function to alter the contents of a string and
 * returns a version of the original string passed as
 * as an argument.
 *
 * @author  Ina Tolo
 * @version 1.0
 * @since   2022-4-1
 */

class StringStuff {
    /**
     * Declaring constant for console formatting.
     */
    private static final String CONSOLE_SEPARATOR =
        "--------------------------";

    /**
     * This function alters the arrangement of a string.
     *
     * @param tempArray accepted
     * @return newString to main function
     */
    public static String blowup(char[] tempArray) {
        // declaring variables
        String newString = "";
        int charInt;

        // determines which character(s) in the array needs to be altered
        for (int cursor = 0; cursor < tempArray.length - 1; cursor++) {
            try {
                charInt = Integer.parseInt(String.valueOf(tempArray[cursor]));

                // repeats character that is after an integer
                for (int numCount = 0; numCount < charInt; numCount++) {
                    newString += tempArray[cursor + 1];
                }

                // checks last character in array
                if (cursor == tempArray.length - 2) {
                    try {
                        charInt =
                            Integer.parseInt(
                                String.valueOf(tempArray[cursor + 1]));
                    } catch (NumberFormatException exception) {
                        newString += tempArray[cursor + 1];
                    }
                }

            } catch (NumberFormatException newException) {
                newString += tempArray[cursor];

                // checks last character in array
                if (cursor == tempArray.length - 2) {
                    try {
                        charInt =
                            Integer.parseInt(
                                String.valueOf(tempArray[cursor + 1]));
                    } catch (NumberFormatException exception) {
                        newString += tempArray[cursor + 1];
                    }
                }
            }
        }

        // checks if a single character is altered
        if (tempArray.length == 1) {
            try {
                charInt =
                    Integer.parseInt(String.valueOf(tempArray[0]));
            } catch (NumberFormatException exception) {
                newString += tempArray[0];
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
        // declaring variables
        List<String> listOftrings = new ArrayList<String>();
        final String[] stringsArrayFile;
        final String[] alteredStringsArray;
        final BufferedWriter writer;
        final StringBuilder builder;
        final List<String> alteredList = new ArrayList<String>();
        String blowupStringUser;

        System.out.println("The strings in the input file will be altered.");
        System.out.println();

        // reads contents of file into list
        try {
            listOftrings = Files.readAllLines(Paths.get("input.txt"));
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        // converts contents of list to an array
        stringsArrayFile = listOftrings.toArray(new String[0]);

        try {
            // calls function and passes each element in the array
            for (int loopCounter = 0; loopCounter
                < stringsArrayFile.length; loopCounter++) {
                final char[] tempArray =
                    new char[stringsArrayFile[loopCounter].length()];
                for (int i = 0; i
                    < stringsArrayFile[loopCounter].length(); i++) {
                    tempArray[i] = stringsArrayFile[loopCounter].charAt(i);
                }
                blowupStringUser = blowup(tempArray);
                alteredList.add(blowupStringUser);
            }

            // converts list of altered strings to an array
            alteredStringsArray = alteredList.toArray(new String[0]);

            // displays message that alerts user file is done being altered
            System.out.println("Done altering strings."
                + " Look below and check the output file.");
            System.out.println(CONSOLE_SEPARATOR);

            // adds the new altered strings to the output file
            builder = new StringBuilder();
            for (int formatCounter = 0; formatCounter
                < alteredStringsArray.length; ++formatCounter) {
                builder.append(alteredStringsArray[formatCounter]);
                if (formatCounter != alteredStringsArray.length - 1) {
                    System.out.println(alteredStringsArray[formatCounter]);
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

            System.out.println(CONSOLE_SEPARATOR);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
