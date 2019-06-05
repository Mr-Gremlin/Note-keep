/*
1. Create a note taking console application
        a. It should be able to add and
        remove notes.
        b. It should be able to list all notes in
        a clear manner.
        c. It should be able to sort the
        notes alphabetically.
*/

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    //Container for active Note objects.
    static List<Note> notesList = new ArrayList<>();

    //Initialises notesdb object for read/write.
    static File notesdb = new File("src", "notesdb.txt");


    public static void main(String[] args) {

        String progName = "Note-Keep";

        //Formats and displays Program Name (according to typeWidth value)
        int sidelineLength = (typeWidth-progName.length()-2)/2;
        String sideLine = new String(new char[sidelineLength]).replace("\0", "-");
        String nameLine = sideLine + " " + progName + " " + sideLine;

        if(nameLine.length()!=typeWidth){nameLine += "-";}

        System.out.println(nameLine.length());
        println(nameLine);

        try {
            //Checks for existing notes data. If not found, creates it.
            if (notesdb.createNewFile()) {
                println("No existing data file found...\nNew data file created.");
            } else {
                println("Data file found.");
            }
        }
        catch (Exception IO){
            println("Error loading/creating data file.\n" +
                    "Please ensure existing data file is not in use and that root directory has write permissions.\n" +
                    "Program will exit.");
            System.exit(-1);
        }

        loadNotes();

        //noinspection InfiniteLoopStatement
        while(true) {
            Top();
        }

    }

    //-- General Utility----------------------------------------------------------------------------------------------------

    //Defines maximum spread of displayed text in the console.
    static int typeWidth = 60;

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
     * Calls print() without the need for the System class path.
     * Additionally limits line length by typeWidth value, automatically splitting into additional lines where needed.
     * @param text - string to print
     */
    @SuppressWarnings("Duplicates")
    static void print(String text)  {

        if(text.length()<typeWidth){
            System.out.print(text);
        }
        else
        {
            String tempString = "";

            for (String word : text.split("\\s")) {

                //Catches entries > typeWidth that DON'T contain any whitespace.
                //Splits via substrings and prints accordingly.
                int length = word.length();
                if(length>typeWidth) {
                    if (tempString.length() > 0) {
                        System.out.println(tempString);
                    }
                    for (int i = 0; i < length; i += typeWidth) {
                        //Creates a moving index according to typeWidth.
                        String slice = (word.substring(i, Math.min(length, i + typeWidth)));
                        if (slice.length() < typeWidth) {
                            tempString = slice;
                        } else {
                            System.out.println(slice);
                        }
                    }
                }
                //If the initial word length < typeWidth it is immediately assigned to tempString.
                else if(tempString.length()<1){
                    tempString += word;
                }

                //If adding the next word to tempString would make the string length exceed typeWidth, then the current
                //state of tempString is printed and overwritten with the next word, starting a new line.
                else if ((tempString + " " + word).length() > typeWidth) {
                    System.out.println(tempString);
                    tempString = word;
                }
                //The next word is appended to tempString as it won't exceed typeWidth.
                else{
                    tempString += " "+word;
                }

            }
            System.out.print(tempString);
        }

    }

    /**
     * Fulfills same purpose as print(), but for the println() method.
     * @param text - string to print
     */
    @SuppressWarnings("Duplicates")
    static void println(String text)  {
        if(text.length()<typeWidth){
            System.out.println(text);
        }
        else
        {
            String tempString = "";

            for (String word : text.split("\\s")) {

                //Catches entries > typeWidth that DON'T contain any whitespace.
                //Splits via substrings and prints accordingly.
                int length = word.length();
                if(length>typeWidth) {
                    if (tempString.length() > 0) {
                        System.out.println(tempString);
                    }
                    for (int i = 0; i < length; i += typeWidth) {
                        //Creates a moving index according to typeWidth.
                        String slice = (word.substring(i, Math.min(length, i + typeWidth)));
                        if (slice.length() < typeWidth) {
                            tempString = slice;
                        } else {
                            System.out.println(slice);
                        }
                    }
                }
                //If the initial word length < typeWidth it is immediately assigned to tempString.
                else if(tempString.length()<1){
                    tempString += word;
                }

                //If adding the next word to tempString would make the string length exceed typeWidth, then the current
                //state of tempString is printed and overwritten with the next word, starting a new line.
                else if ((tempString + " " + word).length() > typeWidth) {
                    System.out.println(tempString);
                    tempString = word;
                }
                //The next word is appended to tempString as it won't exceed typeWidth.
                else{
                    tempString += " "+word;
                }

            }
            System.out.println(tempString);
        }
    }

    /**
     * Prints specified number of blank lines.
     * @param number_of_lines - desired number of blank lines
     */
    static void lnbreak(int number_of_lines){
        for (int i = 1; i <= number_of_lines; i++) {
            print("\n");
        }
    }

    /**
     * Prints a line separator that fills the allocated typeWidth.
     */
    static void lnFill(String fill, int length){
        if((length == 0)||length>typeWidth){length = typeWidth;}
        for (int i = 1; i <= length; i++) {
            print(fill);

        }
        print("\n");
    }

//----------------------------------------------------------------------------------------------------------------------

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

    static void loadNotes(){
       try {
           Scanner scan = new Scanner(notesdb);
           List<String> unparsedNotes = new ArrayList<>();

           scan.useDelimiter("<NoteFin>");
           while(scan.hasNext()){
               unparsedNotes.add(scan.next());
           }

           for(String protoNote: unparsedNotes){
               String[] temp = protoNote.split("<End>");
               Note reCreated = new Note(temp[0],temp[1],temp[2]);
               notesList.add(reCreated);
            }
       }
       catch(Exception FileNotFound){
            println("Data file not found. Failed to load existing notes.");
       }
    }

    static void saveExit(){
        BufferedWriter noteWriter;
        try {
            //Attempts to initialise a FileWriter object to write to the data file.
            noteWriter = new BufferedWriter(new FileWriter(notesdb.getAbsolutePath()));

            String sectionEnd = "<End>";
            String endNote = "<NoteFin>";

            for(Note note: notesList){
                noteWriter.write(note.getTitle()+sectionEnd);
                noteWriter.write(note.getDateTime()+sectionEnd);
                noteWriter.write(note.getContent()+endNote);
            }
            noteWriter.close();
            System.exit(0);
        }
        catch(Exception IO){

            println("Failed to locate data file. Will be unable to store new notes.");
            println("Do you still wish to exit? [Y/N]");
            boolean validReply = false;
            do {
                switch (yesNo(getuserInput())) {
                    case 1:
                        System.exit(-2);
                        break;
                    case 0:
                        validReply = true;
                        break;
                    case -1:
                        println("Invalid response. Please enter [Y/N]...");
                }
            }while(!validReply);

        }


    }

        //METHOD - Export a note to defined location (as .txt)

        //METHOD - Escape dialogue

//-- Menus + Related Methods--------------------------------------------------------------------------------------------

    private static String mName;
    private static int option;

    static void Top(){
        mName = "Main Menu";
        String[] Options = {"New Note","Browse Notes","Find Note","Exit"};

        printMenu(mName,Options);

        //Calls for user input and evaluates against available options.
        boolean validSelection = false;
        do {
            print("Selection: ");
            //AT PRESENT ALL CASES HAVE PLACEHOLDERS FOR TESTING PURPOSES ONLY
            option = selector(getuserInput(), Options);
            switch (option) {
                case 0: //New Note
                    newNote();
                    validSelection = true;
                    break;
                case 1: //Browse
                    println(Options[option]);
                    for(Note note:notesList){
                        lnFill("=",0);
                        String[] noteInfo = note.view();
                        String noteHeader = noteInfo[0]+" - "+noteInfo[1];
                        println(noteHeader);
                        lnFill("+",noteHeader.length());
                        println(noteInfo[2]);
                    }
                    validSelection = true;
                    break;
                case 2: //Find
                    println(Options[option]);
                    validSelection = true;
                    break;
                case 3: //Exit
                    saveExit();
                case -1: //Invalid input, request valid input.
                    validSelection = false;
                    println("Invalid selection, please enter selection carefully.");
            }
        }while(!validSelection);
    }

    private static int selector(String userInput, String[] options){

        //Clears irrelevant whitespace from the user input.
        userInput = userInput.trim();
        //Ensures white space between words is consistent.
        userInput = userInput.replaceAll("\\s{2,}"," ");

        int optionIdentified=-1;

        for(int i=1; i <= options.length; i++) {

            //Does userInput correlate to an option number?
            if ((char)(i+'0') == userInput.charAt(0)) {
                optionIdentified = i - 1;
                break;
            }
            //Does userInput correlate to an option name?
            if (options[i - 1].toLowerCase().equals(userInput.toLowerCase())) {
                optionIdentified = i - 1;
                break;
            }
        }
        //If no option is identified.
        return optionIdentified;
    }

    static void Browse(){
        mName = "Browse Notes";
        String[] Options = {"Alphabetically","By Date","By size","Return"};

        printMenu(mName,Options);

    }

    /*MENUS

        TOP New Note; Browse Notes; Find note; Exit

            New Note -> Title -> Content -> Display it - Save/Edit/Clear and Cancel

            Browse Notes -> Alphabetically by Title; By Date -> Displays 10 at a time ->
            Modify Note; Next 10; Return to menu
            Modify Note - Export;Edit;Delete;Return

            Find -> Search by name or by date; Return to menu -> Displays matching, or failed search prompt
            Failed Search -> Search by name or by date; Return to menu...
            Displays Matching - Export/Edit/Delete/Return to find tool
         */

    private static void printMenu(String menu, String[] options){

        //Prints the Menu header and underlines it to make it distinct from the options.
        println("\n"+menu);
        for(int i=0; i < menu.length(); i++){
            print("=");
        }
        lnbreak(1);
        //Prints numbered options of the menu.
        int op = 0;
        for(String s: options){
            op += 1;
            if(op < options.length){
                print(op+". "+s+" | ");
            }else{
                print(op+". "+s);
            }
        }
        lnbreak(2);
    }

}