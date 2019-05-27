/*
1. Create a note taking console application
        a. It should be able to add and
        remove notes.
        b. It should be able to list all notes in
        a clear manner.
        c. It should be able to sort the
        notes alphabetically.
*/

public class Main {

    /**
     * Method of pure convenience to implement print without the need for the System class path.
     * @param text - string to print
     */
    public static void print(String text)  {
        System.out.print(text);
    }

    /**
     * Method of pure convenience to implement println without the need for the System class path.
     * @param text - string to print
     */
    public static void println(String text)  {
        System.out.println(text);
    }

    /**
     * Prints specified number of blank lines.
     * @param number_of_lines - desired number of blank lines
     */
    public static void lnbreak(int number_of_lines){
        for (int i = 1; i <= number_of_lines;i++){
            print("\n");
        }
    }

//----------------------------------------------------------------------------------------------------------------------

    public static void main(String[] args) {
        String progName = "------------ Note-Keep ------------";
        //Display Program Name
        println(progName);
        print("___________");
        //Check for existing notes file. Load or make if needed.

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