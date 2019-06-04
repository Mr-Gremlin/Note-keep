class Menus extends Main{
    private static String mname;
    private static int option;

    static void Top(){
        mname = "Main Menu";
        String[] Options = {"New Note","Browse Notes","Find Note","Exit"};

        printMenu(mname,Options);

        //Calls for user input and evaluates against available options.

        boolean validSelection = false;
        do {
            print("Selection: ");
            //AT PRESENT ALL CASES HAVE PLACEHOLDERS FOR TESTING PURPOSES ONLY
            option = selector(getuserInput(), Options);
            switch (option) {
                case 0:
                    println(Options[option]);
                    validSelection = true;
                    break;
                case 1:
                    println(Options[option]);
                    validSelection = true;
                    break;
                case 2:
                    println(Options[option]);
                    validSelection = true;
                    break;
                case 3:
                    System.exit(0);
                case -1:
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
        mname = "Browse Notes";
        String[] Options = {"Alphabetically","By Date","By size","Return"};

        printMenu(mname,Options);

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

    private static void printMenu(String menu, String[] options){

        //Prints the Menu header and underlines it to make it distinct from the options.
        println("\n"+menu);
        for(int i=0; i < menu.length(); i++){
            print("-");
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
