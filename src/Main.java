/*
1. Create a note taking console application
        a. It should be able to add and
        remove notes.
        b. It should be able to list all notes in
        a clear manner.
        c. It should be able to sort the
        notes alphabetically.
*/

import java.io.File;
import java.util.Scanner;

public class Main {

    /**
     * Reads user input from System. Utilises regex to ensure that the input doesn't consist solely of whitespace.
     * Prompts user for a 'valid' entry if this is the case.
     * @return - String of user input.
     */
    static String getuserInput() {

        Scanner input = new Scanner(System.in);
        String userinput;

        while(true) {
            userinput = input.nextLine();
            if (userinput.matches("\\s*\\S+.*")) {
                break;
            } else {
                println("Please enter valid input...");
            }
        }

        input = null;
        return userinput;
    }

    /**
     * Method of pure convenience to call print() without the need for the System class path.
     * @param text - string to print
     */
    static void print(Object text)  {
        System.out.print(text);
    }

    /**
     * Method of pure convenience to call println() without the need for the System class path.
     * @param text - string to print
     */
    static void println(Object text)  {
        System.out.println(text);
    }

    /**
     * Prints specified number of blank lines.
     * @param number_of_lines - desired number of blank lines
     */
    static void lnbreak(int number_of_lines){
        for (int i = 1; i <= number_of_lines;i++){
            print("\n");
        }
    }

//----------------------------------------------------------------------------------------------------------------------

    public static void main(String[] args) {

        String progName = "\n------------ Note-Keep ------------\n";
        //Display Program Name
        println(progName);


        //Initialises notesdb object for read/write.
        File notesdb = new File("src", "notesdb.txt");

        try {
            //Checks for existing notes data. If not found, creates it.
            if (notesdb.createNewFile()) {
                println("No existing data file found...\nNew data file created.");
            } else {
                println("Data file found.");
            }
        }
        catch (Exception e){
            println("Error loading/creating data file. Notes may not save.");
        }

        //HASHMAP GENERATED FROM NOTES DATA. USE SCANNER.

        while(true) {
            Menus.Top();
        }

        /*MENUS

        TOP New Note; Browse Notes; Find note; Exit

            New Note -> Title -> Content -> Display it - Save/Edit/Clear and Cancel

            Browse Notes -> Alphabetically by Title; By Date; By Size -> Displays 10 at a time ->
            Modify Note; Next 10; Return to menu
            Modify Note - Export;Edit;Delete;Return

            Find -> Search by name or by date; Return to menu -> Displays matching, or failed search prompt
            Failed Search -> Search by name or by date; Return to menu...
            Displays Matching - Export/Edit/Delete/Return to find tool
         */
    }

        //METHOD - Load/Save notes

        //METHOD - Export a note to defined location (as .txt)

        //METHOD - Escape dialogue
}