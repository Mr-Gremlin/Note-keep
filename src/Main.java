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
import java.util.ArrayList;
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

    static ArrayList<Note> notesList = new ArrayList<>();

    public static void main(String[] args) {

        String progName = "\n---------------------- Note-Keep ----------------------\n";
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
            println("Error loading/creating data file.\n" +
                    "Please ensure existing data file is not in use and that root directory has write permissions.\n" +
                    "Program will exit.");
            System.exit(-1);
        }



        while(true) {
            Menus.Top();
        }

    }

    static void newNote(){

        Note aNote = null;
        String tempTitle;
        String tempContent;
        boolean shouldCont = false;

        print("Note Title: ");
        tempTitle = getuserInput();
        lnbreak(1);

        do {
            println(tempTitle + "... Is this correct? [Y/N]");
            switch (yesNo(getuserInput())) {
                case 1:
                    aNote = new Note(tempTitle);
                    shouldCont = true;
                    break;
                case 0:
                    println("Retype your title.");
                    print("Note Title: ");
                    tempTitle = getuserInput();
                    lnbreak(1);
                    shouldCont = false;
                    break;
                case -1:
                    println("Invalid response. Please enter [Y/N]...");
                    shouldCont = false;
            }
        }while(!shouldCont);

        print("Enter Content: ");
        tempContent = getuserInput();
        lnbreak(1);


        println("Note Preview");
        println("------------");

        aNote.setContent(tempContent);
        for(String s: aNote.view()){
            println(s);
        }
        notesList.add(aNote);
    }

    /**
     *Simple user validation check for prompts.
     * @param userInput - Expects 'Y' or 'N'
     * @return - 1 for yes, 0 for no, -1 for invalid input
     */
    static int yesNo(String userInput){
        userInput = userInput.strip().toLowerCase();
        if(userInput.charAt(0)=='y'){
            return 1;
        }else if(userInput.charAt(0)=='n'){
            return 0;
        }else{
            return -1;
        }
    }
        //METHOD - Load/Save notes

        //METHOD - Export a note to defined location (as .txt)

        //METHOD - Escape dialogue
}