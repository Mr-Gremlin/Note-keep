import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;

/**
 * The Note class enables the creation of 'Notes' that store Title, Content, and creation DateTime information.
 * It is able to handle recreation of previous notes from saved data and additionally has methods to sort collections of
 * Note objects according to Title or DateTime.
 */

public class Note{

    private String noteTitle;
    private String noteContent;
    private LocalDateTime createdDateTime;

    //Expected default constructor - for creation of new notes. Omits content for later addition.
    Note(String title){
        this.noteTitle = title;
        this.createdDateTime = LocalDateTime.now();
    }

    //Constructor for note objects being recreated from saved data.
    Note(String loadedTitle, String loadedDateTime, String loadedContent){
        this.noteTitle = loadedTitle;
        this.createdDateTime = LocalDateTime.parse(loadedDateTime);
        this.noteContent = loadedContent;
    }

    public void setTitle(String newTitle){
        this.noteTitle = newTitle;
    }

    public String getTitle(){
        return this.noteTitle;
    }

    public void setContent(String newContent){
        this.noteContent = newContent;
    }

    public String getContent(){
        return this.noteContent;
    }

    public LocalDateTime getDateTime(){
        return this.createdDateTime;
    }

    //View is intended to be the main method for retrieving note info as printable strings.
    public String[] view(){
        String[] printableNote = new String[3];

        printableNote[0] = this.noteTitle;

        //Ensures DateTime is in a readable format.
        DateTimeFormatter readableFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        printableNote[1] = this.createdDateTime.format(readableFormat);

        printableNote[2] = this.noteContent;

        return printableNote;
    }

/*-- Sort Methods ------------------------------------------------------------------------------------------------------

    Utilises Comparator wrappers to create 2 non-conflicting overrides of the same comparison method for 2 different
    use cases.
*/

    public static void sortByName(List<Note> listOfNotes){
        listOfNotes.sort(new Comparator<Note>() {
            @Override
            public int compare(Note o1, Note o2) {
                return o1.getTitle().compareTo(o2.getTitle());
            }
        });
    }

    public static void sortByDate(List<Note> listOfNotes){
        listOfNotes.sort(new Comparator<Note>() {
            @Override
            public int compare(Note o1, Note o2) {
                return o1.getDateTime().compareTo(o2.getDateTime());
            }
        });
    }

}
