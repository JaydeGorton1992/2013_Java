//Import create new colors isntead using defaults
import java.awt.Color;

//Importing all awt
import java.awt.*;

//Importing Event and Listeners
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Importing Windows event and Listners
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

//Importing IO buffer reader and writer
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;

//javax swing layout, combo boxes, frame, scroll pane and text Area
import javax.swing.SpringLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * @author Jade Gorton Last Edited: 26/09/2013 1:51 PM Last Code Edited:
 * newFrame parameters & layout of variables into Logical Groupings Interface
 * with Low Coupling and High Cohesion and with initial ActionListener code
 * structure. Program has Clip Data Class control Clip info as object group data
 * together
 */
public final class Project_Java2 extends Frame implements ActionListener, WindowListener {

    /*
     * Declaration of int global variables handle various requirements, like handling
     * How many max entries can be stored in the text file
     * How many entries that currently being used
     * What entry being currently viewed
     * And variable handle size of text area for displaying entries 
     */
    int maxEntries = 100, numberOfEntries = 0, currentEntry = 0, maxGenre = 0;
    /*
     * ClipDataRecord is array that stores ClipInfo Object, New array size is of max entries
     * This Array is main array that manipulated This array, is where
     * Delete, Save, Read, new and write.
     */
    ClipDataRecord[] ClipInfo = new ClipDataRecord[maxEntries];
    /*
     * Initializing Global Variables for objects in Frame.
     * All these variables determine use of this program and how it runs
     * All functions run using these global variables store information
     * Functions themselves can run individually and not dependant on
     * Global variables to run
     */
    /*
     * Initializing Global Variable arrays for Jcombo boxes content in String.
     * These Strings are used when creating Jcombo Boxes
     * Function that uses these string would be locate a combo box
     * String entries would be used for determining what drop down items are in combo box
     * Items in Jcombo Box can be changed on runtime by changing default model of the combo box
     * Example useage would be when you select Category it changes items in Genre Combo Box
     */
    String[] CategoryArray = {"Comdey", "Blog", "Music"};
    String[] GenreArray = {"Pop", "Punk"};
    String[] AllGenreArray = {"Pop", "Punk"};
    /*
     * Initializing Global Variable arrays for Text Field in String.
     * Initializing Global Variable array for text Field Object
     * Variable TextField[] Holds TextField Objects in an Array
     * So TextFields instead refereed too using names is instead
     * Created using array Instead, This allowed quicker
     * Automation of locate a text field function by
     * Running function in For Loop Where length of loop
     * Is length of TextFieldArray. Also Reason storing TextFieldName
     * String values of name of Text Fields instead using label arrays
     * Because Labels and Text Fields two different things
     * And introducing Two before as one Array Lead to complications
     * And human Error so separation of name of text fields
     * And Labels that go with them allowed for quicker
     * Programming for Text Fields since Instead changing Values for length
     * Of text field array I just instead Added new Array element
     * In TextFieldName, However Information stored on TextFieldName
     * Is not used anywhere else beside determining Length It allowed for quicker
     * Development because I could visually see my plan for Text Field Elements
     * And I left String[] Because example of techniques I use
     * To speed up development also helped with error checking logically
     * When I had go through positioning Jcombo Boxes
     */
    String[] TextFieldName = {"ClipName", "Category", "Genre", "Uploader", "Hyperlink", "Find"};
    TextField[] TextFieldArray = new TextField[TextFieldName.length];
    /*
     * Intializing Global Variable arrays for Labels in string
     * Intializing Global Variable arrays for Label objects in labels array
     * LabelsArrayName has same purpose as TextFieldName Array, However
     * LabelsArrayName is used define labels caption
     * Having Labels in Array allows for easier possitioning and use similar to TextFieldArray
     */
    String[] LabelsArrayName = {"ClipName", "Category", "Genre", "Uploader", "Hyperlink"};
    Label[] LabelsArray = new Label[LabelsArrayName.length];
    /*
     * Intializing Global Variables for Buttons as varible names
     * Buttons where more diffiuclt Automate using Arrays
     * Since Buttons Events I assume need be Array Index
     * But could be more complicated than that maybe
     * I Just did not bother placing buttons in Array because buttons are not something that would change in complexity
     * If you wanted add more Text Fields
     * It was also quicker Place buttons as varibles because just was. Buttons where different since you had action &
     * Listener with them, so KISS keep it simple stupid! I decided not put buttons in array since allowed visually see
     * When creating events for buttons what you dealing with without need comments or some indication too what button 
     * Would be doing
     * Buttons are possitioned manually using formulas similar to Labels and Text Field for locating a text field and label
     */
    Button btnNew, btnSave, btnDel, btnFind, btnExit, btnFirst, btnPrev, btnNext, btnLast, btnAllGenre;
    /*
     * Intializing Global Variables for JComboBox as varible names
     * JComboBox like other frame objects such as Buttons
     * Are complicated, and where more difficult deal with
     * I decided store information in text file as JComboBox Item Index
     * And Just convereted from Index too Item, Because In my planing
     * Made more sense use numbers and convert from those numbers
     * Than was find method that does opposite for JComboBox
     * JComboBox took longest to develoup and finish not because
     * Of how compelx Jcombobox was, just how much more work was needed
     * Make them work. 
     */
    JComboBox jcbGenre, jcbCategory, jcbAllGenre;
    /*
     * Intializing Global Variables for JcbPossition
     * Creating 2d Array for Jcombo boxes for easy positioning using math functions instead manaual calculation.
     * This was just lazy Method, It was created quickly work out where Jcb should be I used this when i was still creating
     * Text Fields and program but had Jcb start testing code for combo boxes and needed quick code get it working and set up neat
     * This array can be replaced by using math when creating two Jcb category and genre.
     * By using math x* something replace x with 2 and 3 respectfully
     * This Array Holds X and Y position for Jcb Could been changed hold all Positions of Text fields latter alow more compelex control
     * Of the Arrangement but not needed for complexity of scope for this program
     */
    int[][] JcbPossition = new int[TextFieldArray.length][2];

    /**
     * Entry point to the class and application.
     *
     * @param args Array of String arguments.
     */
    public static void main(String[] args) {
        Frame myFrame = new Project_Java2();
        myFrame.setSize(670, 350);
        myFrame.setLocation(400, 200);
        myFrame.setResizable(false);
        myFrame.setVisible(true);
    }

    /**
     * Constructor which sets up the screen layout and listeners. Sets up Screen
     * layout but also calling all functions that run within Screen
     */
    public Project_Java2() {
        setTitle("YouTube Clip Tracker");
        Color myColor = new Color(86, 131, 185);
        setBackground(myColor);
        SpringLayout myLayout = new SpringLayout();
        setLayout(myLayout);
        LocateLabels(myLayout);
        LocateTextFields(myLayout);
        LocateButtons(myLayout);
        LocateComboBoxs(myLayout);
        this.addWindowListener(this);


    }

    /**
     * Controlling method for adding multiple labels to the screen This method
     * calls Locate a label IT method that calls other methods will requiring
     * only spring layout This simplifies constructor for Project_Java2() Locate
     * labels runs array for y Position of Labels This function inputs Label
     * Layout, Labels Object, Caption of the array, x position, y position
     */
    public void LocateLabels(SpringLayout myLabelLayout) {
        for (int x = 0; x < LabelsArray.length; x++) {
            LabelsArray[x] = LocateALabel(myLabelLayout, LabelsArray[x], LabelsArrayName[x], 30, (45 * x) + 25);
        }
    }

    /**
     * Method with low coupling and high cohesion for adding individual labels:
     * - reduces overall code, especially in the LocateLabels method. - makes
     * this method re-usable with minimal adjustment as it is moved from one
     * program to another. This function requires Label Layout, Labels Object,
     * Caption of the array, x position, y position Returns myLabel the label
     * Object This function controls creation of new Label by organize code in
     * reusable Manner Code creates new labels on screen for naming text fields
     * combo boxes
     */
    public Label LocateALabel(SpringLayout myLabelLayout, Label myLabel, String LabelCaption, int x, int y) {
        myLabel = new Label(LabelCaption);
        add(myLabel);
        myLabelLayout.putConstraint(SpringLayout.WEST, myLabel, x, SpringLayout.WEST, this);
        myLabelLayout.putConstraint(SpringLayout.NORTH, myLabel, y, SpringLayout.NORTH, this);
        return myLabel;
    }

    /**
     * Controlling method for adding multiple textboxes to the screen This
     * method calls Locate a Text Field IT method that calls other methods will
     * requiring only spring layout This simplifies constructor for
     * Project_Java2() Locate labels runs array for y Position of Labels This
     * function inputs Text Field Layout, Text Field Object, width, x position,
     * y position This function also controls JcbPossition array This method can
     * not be fully used by another program Until For loop is removed. However
     * for historic purposes it was left in. Because it was one methods I use
     * Speed up process of creating Because I had code already done just needed
     * Positioning and Quicker Input Position here than was search where Jcb
     * where and change their. This was just get quick visual of Jcb I just did
     * not found need remove this code for time being because how quickly was
     * created
     */
    public void LocateTextFields(SpringLayout myTextFieldLayout) {
        for (int x = 0; x < TextFieldArray.length; x++) {
            if (x == 1 || x == 2) {
                JcbPossition[x][0] = (x * 45) + 25;
                JcbPossition[x][1] = 30;
            } else {
                TextFieldArray[x] = LocateATextField(myTextFieldLayout, TextFieldArray[x], 20, 110, (45 * x) + 25);
            }
        }
    }

    /**
     * Method with low coupling and high cohesion for adding individual
     * textboxes: - reduces overall code, especially in the LocateTextFields
     * method. - makes this method re-usable with minimal adjustment as it is
     * moved from one program to another. This function requires Text Field
     * Layout, Text Field Object, width, x position, y position Returns
     * myTextField the Text Field Object This function controls creation of new
     * Text Field by organize code in reusable Manner Code creates new labels on
     * screen for naming text fields combo boxes
     */
    public TextField LocateATextField(SpringLayout myTextFieldLayout, TextField myTextField, int width, int x, int y) {
        myTextField = new TextField(width);
        add(myTextField);
        myTextFieldLayout.putConstraint(SpringLayout.WEST, myTextField, x, SpringLayout.WEST, this);
        myTextFieldLayout.putConstraint(SpringLayout.NORTH, myTextField, y, SpringLayout.NORTH, this);
        return myTextField;
    }

    /**
     * Controlling method for adding multiple buttons to the screen This method
     * calls Locate a Button IT method that calls other methods will requiring
     * only spring layout This simplifies constructor for Project_Java2() Locate
     * labels runs array for y Position of Labels This function inputs Button
     * Layout, Button Object, Caption of the Button, x position, y position,
     * height, width This example of placing objects manually, these buttons
     * required multiple past and changing position of buttons manually Was
     * proven difficult and time consuming, working out how place buttons in
     * array with events would been faster However Code for buttons was already
     * here just needed add new buttons and move them around
     */
    public void LocateButtons(SpringLayout myButtonLayout) {
        btnNew = LocateAButton(myButtonLayout, btnNew, "New", 320, 25, 80, 25);
        btnSave = LocateAButton(myButtonLayout, btnSave, "Save", 320, 50, 80, 25);
        btnDel = LocateAButton(myButtonLayout, btnDel, "Delete", 320, 75, 80, 25);
        btnFind = LocateAButton(myButtonLayout, btnFind, "Find", 30, (45 * 5) + 25, 80, 25);
        btnExit = LocateAButton(myButtonLayout, btnExit, "Exit", 320, 290, 80, 25);
        btnFirst = LocateAButton(myButtonLayout, btnFirst, "|<", 140, 290, 30, 25);
        btnPrev = LocateAButton(myButtonLayout, btnPrev, "<", 180, 290, 30, 25);
        btnNext = LocateAButton(myButtonLayout, btnNext, ">", 220, 290, 30, 25);
        btnLast = LocateAButton(myButtonLayout, btnLast, ">|", 260, 290, 30, 25);
        btnAllGenre = LocateAButton(myButtonLayout, btnAllGenre, "Find All in Genre", 320, (45 * 5) + 25, 130, 25);
    }

    /**
     * Method with low coupling and high cohesion for adding individual buttons:
     * - reduces overall code, especially in the LocateButtons method. - makes
     * this method re-usable with minimal adjustment as it is moved from one
     * program to another. This function requires Button Layout, Button Object,
     * Button Caption, x position, y position, width, height Returns myTextField
     * the Button Object This function controls creation of new Text Field by
     * organize code in reusable Manner Code creates new labels on screen for
     * naming text fields combo boxes Buttons have Action Listener on them these
     * actions are later used for event when button is clicked And will do
     * majority of functionality of the program Finding particular Video title,
     * Finding next previous entry, finding last first, saving new delete
     */
    public Button LocateAButton(SpringLayout myButtonLayout, Button myButton, String ButtonCaption, int x, int y, int w, int h) {
        myButton = new Button(ButtonCaption);
        add(myButton);
        myButton.addActionListener(this);
        myButtonLayout.putConstraint(SpringLayout.WEST, myButton, x, SpringLayout.WEST, this);
        myButtonLayout.putConstraint(SpringLayout.NORTH, myButton, y, SpringLayout.NORTH, this);
        myButton.setPreferredSize(new Dimension(w, h));
        return myButton;
    }

    /**
     * Controlling method for adding multiple buttons to the screen This method
     * calls Locate a Button IT method that calls other methods will requiring
     * only spring layout This simplifies constructor for Project_Java2() Locate
     * JComboBoxes This function inputs comboBox Layout, JComboBox Object,
     * Caption of the JComboBO, x position, y position, Items in JComboBOx Code
     * for ComboBox needed be created from scratch and not used previous in any
     * other projects this code now can be used for other Programs later
     */
    public void LocateComboBoxs(SpringLayout myComboBoxLayout) {
        jcbGenre = LocateAComboBox(myComboBoxLayout, jcbGenre, "genre", 35 + 75, JcbPossition[2][0], GenreArray);
        jcbCategory = LocateAComboBox(myComboBoxLayout, jcbCategory, "category", 35 + 75, JcbPossition[1][0], CategoryArray);
        jcbAllGenre = LocateAComboBox(myComboBoxLayout, jcbAllGenre, "All in Genres", 380 + 75, (45 * 5) + 25, GenreArray);
    }

    /**
     * Method with low coupling and high cohesion for adding individual buttons:
     * - reduces overall code, especially in the LocateButtons method. - makes
     * this method re-usable with minimal adjustment as it is moved from one
     * program to another. This function requires JComboBOx Layout, JComboBox
     * Object, JComboBox Caption, x position, y position, Items Array String
     * Returns JComboBox Object This function controls creation of new JComboBox
     * by organize code in reusable
     */
    public JComboBox LocateAComboBox(SpringLayout myComboBoxLayout, JComboBox myComboBox, String ButtonCaption, int x, int y, String[] comboArray) {
        myComboBox = new JComboBox(comboArray);
        myComboBox.setBounds(0, 0, 0, 0);
        myComboBox.setSelectedIndex(0);
        add(myComboBox);
        myComboBox.addActionListener(this);
        myComboBoxLayout.putConstraint(SpringLayout.WEST, myComboBox, x, SpringLayout.WEST, this);
        myComboBoxLayout.putConstraint(SpringLayout.NORTH, myComboBox, y, SpringLayout.NORTH, this);
        myComboBox.setPreferredSize(new Dimension(165, 23));
        return myComboBox;
    }

    /**
     * Function that Handel Creation of newFrame for Pop up for All genre
     * located in Text This creates TextArea, new Frame, new Window, also
     * Includes Scroll Area This function handles code for pop up window for
     * displaying all clips in Genre. This function could be further split up
     * into other functions reduce size and also re usability This function
     * creates new frame, with new heading also Label and Text area with scroll
     * bar This function could been split into 3 functions create frame, content
     * and scroll bar As said before size of parameters could be reduce by
     * splitting function into 3 separate functions This functions creates new
     * frame with heading, Title and panel Function could be split by creating
     * frame, adding panel, Creating scroll pane for text in panel When frame
     * created It now child of current frame so when parent frame is closed This
     * frame also closes, you can close this frame without closing parent frame.
     * This code itself was also reused by previous project, It was then changed
     * suit needs of this program Reason Why I separated Text from within new
     * frame area was because I could reuse this Code for similar purposes but
     * also made it easier see and understand code much more effectively
     * Separating content from Code and replacing it with parameter reduces
     * overall size of the code for this function But also allows for quicker
     * debugging and also able be concise with my code Not separating code made
     * for large input parameters more difficult enter later on but however
     * Allow read both calling of function and function it self easier too read
     * than what it was pervious
     */
    public void newFrame(SpringLayout myLayout, Font font, String RFTitle, String LabelTitle, String ScrollText, int ScrollTextH, int ScrollTextW,
            int ScrollPreferredHeight, int ScrollPreferredWidth, Color BgroundColor) {

        /*
         * Heading Varible add Label for title
         * I was going to add, varible parameters change possitioning of it, but saw that it was too
         * Much work. So i decided Leave it how it was and not change it.
         * I could replaced with function parameter that accepts objects or an array of objects
         * However having label as simple heading works
         * We define new label called variable Heading and it equals new level label title
         * We also Assign a font
         */

        Label Heading = new Label(LabelTitle);
        Heading.setFont(font);

        /*
         * This creates new JFrame that hold information of results and display it into screen
         * This opens new window that when parent is closed end program thus closing this window also.
         * This code very similar to origional constructor for Project_java2
         * This code Creates new JFrame, Sets the size,
         * Sets Frame location on screen
         * Sets that not resizable
         * Sets that it is visible
         * Sets Title Two RFTitle parameter 
         * Sets close function Dispose of frame and Keep main open.        
         */

        JFrame reportFrame = new JFrame();
        reportFrame.setSize(580, 350);
        reportFrame.setLocation(400, 200);
        reportFrame.setResizable(false);
        reportFrame.setVisible(true);
        reportFrame.setTitle(RFTitle);
        reportFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);



        /*
         * Defining new Panel and adding it to the frame.
         * Adding panel into reportFrame
         * Setting it layout and also back ground Color
         */


        Panel panel = new Panel();
        reportFrame.add(panel);
        panel.setLayout(myLayout);
        panel.setBackground(BgroundColor);

        /*
         * Setting the design elements to the Jtext area
         * Setting prefered size
         * Setting text area as non-editable 
         * setting it background to white, and also 
         */

        JTextArea txtaResults = new JTextArea(ScrollText, 5, 10);
        txtaResults.setPreferredSize(new Dimension(ScrollTextH, ScrollTextW));
        txtaResults.setEditable(false);
        txtaResults.setBackground(Color.WHITE);
        txtaResults.setLineWrap(true);

        /*
         * Adding txt Area into panel
         */

        panel.add(txtaResults);

        /*
         * Creating new scroll pane
         * With txt area results within, and setting vertical and horizontal scrollbar always
         * Setting dimensions of Scroll Pane using parameters Scroll Preferred Height and SCroll Preferred Width
         */

        JScrollPane sp = new JScrollPane(txtaResults,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        sp.setPreferredSize(new Dimension(ScrollPreferredHeight, ScrollPreferredWidth));

        /*
         * Setting constraints for myLayout for possition pixels by top right hand coner by west and north 
         * increasing number goes down and right decreasing goes up and left
         * Positioning scrol pane within panel
         */

        myLayout.putConstraint(SpringLayout.WEST, sp, 20, SpringLayout.WEST, panel);
        myLayout.putConstraint(SpringLayout.NORTH, sp, 40, SpringLayout.NORTH, panel);
        
        /*
         * Setting constraints for myLayout for possition pixels by top right hand coner by west and north 
         * increasing number goes down and right decreasing goes up and left
         * Positioning Heading within panel
         */
        
        myLayout.putConstraint(SpringLayout.WEST, Heading, 125, SpringLayout.WEST, panel);
        myLayout.putConstraint(SpringLayout.NORTH, Heading, 5, SpringLayout.NORTH, panel);

        /*
         * Adding scroll pane and heading to the pannel
         * Adding Text Area in scroll pane then adding scroll pane into the panel
         * Heading gets added into the panel, with it possitioning and Font
         */
        
        panel.add(sp);
        panel.add(Heading);
    }

    /**
     * This function goes through number of entries in array for all genre, by going through how many are in genre, and but also going through
     * How many are in the array for max number of entries, and goes through each item until matches selected genre
     * once matches genre it stores value in text adding it to previous value of text
     * it adds clips name, uploader and hyperlink to collection of all clips in select genre in find all genre combo box
     * it goes through each entry then adds clips thatt match genre selected with genre stored, and  stores information 
     * In text String that goes into text area variable as parameter
     * once this array goes through all elements of array it stores max number of entries that have that genre so increase text area 
     * Height, as global variable, but also returns text
     * The text returns results:
     * And Number of entries that have genre category selected
     * @return 
     */
    
    public String DisplayAllGenreText() {
        String text = "Results: \n" + "";
        int index = 0;

        // While there are more entries to check and the 'search' entry has not been found... 
        while (index < numberOfEntries + 1) {
            //If the current entry is equal to the 'search' entry...
            if (jcbAllGenre.getSelectedIndex() == ClipInfo[index].getGenre()) {
                jcbGenre.getItemAt(1);
                text += "\n Clip | \n Clip Name: " + ClipInfo[index].getClipName()
                        + "\n Clip Uploader: " + ClipInfo[index].getUploader()
                        + "\n Clip Hyperlink: " + ClipInfo[index].getHyperlink()
                        + "\n Clip Category: " + jcbCategory.getItemAt(ClipInfo[index].getCategory()) + "\n | Clip \n";
                maxGenre += 1;
            }

            index++;
        }
        return text;
    }

    /*
     * This contains all action prefored events
     * All events called by event.get sourced and checking if event from a btn action listener or any listener.
     * To the event that taken placed on action preformed.
     * All events are written here because Implimented is required for code have some sort function associated with it.
     * So all window events are empty because we won't be using most of them.
     * All events Are included in action preformed
     */
    
    public void actionPerformed(ActionEvent e) {
        
        /*
         * Event when pressed on btnAllGenre, calls series of statments thta run create new pop up frame that displays
         * All Clips in a genre.
         * This action event displays, and creates new fonts, new string called text run function get all clips
         * In a genre, and create new spring layout,
         * But also, runs new frame creat new frame with new layout, font, Name of the frame, heading of the text, and text of the items
         * Including size, of the text
         * Height of the scroll pane is increased based on number of entries found,
         * This makes sure that all entries are displayed without excess space, This however can be made void because
         * This does not take into account length of variables and size of strings etcer. and is only by set ammount. meaning
         * Really long names and etcer would exceed number of pixels I have defined amd thus would not correctly display all genre in clips
         * This can be avoided by setting max size for text area but could not be done due to time constraints for this project
         */
        
        if (e.getSource() == btnAllGenre) {
            Font font = new Font("Verdana", Font.BOLD, 15);
            String text = DisplayAllGenreText();
            Color verylightblue = new Color(92, 184, 230);
            SpringLayout myLayout2 = new SpringLayout();
            newFrame(myLayout2, font, "Results", "Results for all clips in a genre: " + jcbAllGenre.getSelectedItem() + "\n", text, 500, 100 * maxGenre, 525, 250, verylightblue);
            maxGenre = 0;
        }
        
        /*
         * Button First entry
         * Displays First entry by setting current entry to 0
         * Calls Display Entry with current entry changed
         */

        if (e.getSource() == btnFirst) {
            currentEntry = 0;
            displayEntry(currentEntry);
        }
        
        /*
         * Button Previous entry
         * Displays Previous entry by minusing current entry by 1
         * It will only minus current entry by one until entry is first entry 0
         * Calls Display Entry with current entry changed
         */
        
        if (e.getSource() == btnPrev) {
            if (currentEntry > 0) {
                currentEntry--;
                displayEntry(currentEntry);
            }
        }

        /*
         * Button Next entry
         * Displays Next entry by plusing current entry by 1
         * It will only plus current entry by one until entry is last entry (numberOfEntries)
         * Calls Display Entry with current entry changed
         */
        
        if (e.getSource() == btnNext) {
            if (currentEntry <= numberOfEntries - 1) {
                currentEntry++;
                displayEntry(currentEntry);
            }
        }

        /*
         * Button Last entry
         * Displays Last entry by setting current entry to numberOfEntries
         * Calls Display Entry with current entry changed
         */
        
        if (e.getSource() == btnLast) {
            currentEntry = numberOfEntries;
            displayEntry(currentEntry);
        }

        /*
         * Button new entry
         * Displays new entry by setting current entry to numberOfEntries + 1
         * Then current entry = new number of entries
         * Creates new ClipInfo at current entry creating empty clip record
         * ClipDataRecord could be simplifyed into using constructor
         * ClipDataRecord(); create new blank record usding default entry
         * Calls Display Entry with current entry changed With new empty record
         */
        
        if (e.getSource() == btnNew) {
            if (numberOfEntries < maxEntries) {
                numberOfEntries++;
                currentEntry = numberOfEntries;
                ClipInfo[currentEntry] = new ClipDataRecord("", 0, 0, "", "");
                displayEntry(currentEntry);
            }

        }

        /*
         * Button Save entry
         * Saves Current entry by calling saveEntry with parameter currentEntry
         */
        
        if (e.getSource() == btnSave) {
            saveEntry(currentEntry);
        }

        /*
         * Button Delete entry
         * Delete Current entry by using delete algorithm
         * Code was already placed in Del because how small it was
         * This does however mean that, won't be able us this in other programs later on
         * But changing delete into function would be easy
         * Parameters would require Current Entry, max number of entries, and data object manipluating
         * Data would be array 
         * and result of new array would be return statment return the array, then whatever code do with new Array
         * Be however difficult handel creating as function dealing with swaping of index values only complication I can see
         */
        
        if (e.getSource() == btnDel) {
            
            /*
             * For each element above current entry, and less than max number of entries - 1
             * Rewrite over previous Clip Info, by using current index by setting current index values as object index + 1
             * Continue untill all entries are shifted up.
             * Move all the later entries up one line in the arrays, covering over the current entry in the process
             */
            
            for (int index = currentEntry; index <= numberOfEntries - 1; index++) {
                ClipInfo[index].setClipInfo(ClipInfo[index + 1].getClipName(), ClipInfo[index + 1].getCategory(), ClipInfo[index + 1].getGenre());
            }
            
            /*
             * Reduce the size of the arrays and if the current entry is past the last entry
             * Display the last entry by making current entry as new number of entries because removed on you minus
             * number of entries by one.
             */
            
            numberOfEntries--;
            if (currentEntry >= numberOfEntries) {
                currentEntry = numberOfEntries;
            }
            //Finally Display Last entry
            displayEntry(currentEntry);
        }
       
        /*
         * Button Find entry
         * Displays First Search entry with that name
         * Setting input and text in ClipInfo Array means that finding clip by clip name is case insensitive
         * Algorithm Simply goes through each entry untill you found clip once found end loop
         * Error checking when creating program just making sure found = true if it does
         * Then display current entry by changing current entry into index - 1
         * Calls Display Entry with current entry changed
         */
        
        if (e.getSource() == btnFind) {
            boolean found = false;
            int index = 0;
            // While there are more entries to check and the 'search' entry has not been found... 
            while (index < numberOfEntries + 1 && found == false) {
                //If the current entry is equal to the 'search' entry...
                if (ClipInfo[index].getClipName().toLowerCase().equals(TextFieldArray[5].getText().toLowerCase())) {
                    //Set found = true
                    found = true;
                }
                index++;
            }
            //If the entry was found, then display it.
            if (found) {
                currentEntry = index - 1;
                displayEntry(currentEntry);
            }
        }

        /*
         * Button Exit entry
         * Writes to the file and exits the program
         * Nothing to complicated by calling System.exit()
         */
        
        if (e.getSource() == btnExit) {
            writeFile();
            System.exit(0);

        }

    }

    /**
     * Respond to Window events, such as clicking the Close ( X ) button
     * Exact functionality as btnExit, because want write to file when you saved all entries
     * Instead of having save function write to HD when you click save, it instead is done here on system.exit because
     * Preformance, saving to ram is much quicker than writing to disk every time user press save
     * Much effective save everything once program closes
     * However not having alternative save method will cause problems because what happens if program crashes they lose all information
     * Just made so having timer that saves in intervals would be effective counter measure
     */
    
    public void windowClosing(WindowEvent we) {
        writeFile();
        System.exit(0);
    }

    public void windowIconified(WindowEvent we) {
    }
    
    /**
     * 
     *  
     */
    
    public void windowOpened(WindowEvent we) {
        readFile("ClipsData.txt");
        currentEntry = 0;
        displayEntry(currentEntry);
    }

    public void windowClosed(WindowEvent we) {
    }

    public void windowDeiconified(WindowEvent we) {
    }

    public void windowActivated(WindowEvent we) {
    }

    public void windowDeactivated(WindowEvent we) {
    }


    /**
     * Display the numbered entry requested
     */
    public void displayEntry(int index) {
        TextFieldArray[0].setText(ClipInfo[index].getClipName());
        jcbCategory.setSelectedIndex(ClipInfo[index].getCategory());
        jcbGenre.setSelectedIndex(ClipInfo[index].getGenre());
        TextFieldArray[3].setText(ClipInfo[index].getUploader());
        TextFieldArray[4].setText(ClipInfo[index].getHyperlink());
    }

    /**
     * Copy the numbered entry requested from screen to the array and then
     * calling to save the whole array into the data file.
     */
    public void saveEntry(int index) {
        ClipInfo[index] = new ClipDataRecord(TextFieldArray[0].getText(), jcbCategory.getSelectedIndex(), jcbGenre.getSelectedIndex(), TextFieldArray[3].getText(), TextFieldArray[4].getText());
        writeFile();
    }

    /**
     * Read in the data from the data file - Genre.txt - one line at a time and
     * store in the 3 arrays Remember the number of entries read in, in the
     * global variable - numberOfEntries
     */
    public void readFile(String File) {
        try {
            FileInputStream fstream = new FileInputStream(File);
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            int index = 0;  // i is used as the line counter
            String line;
            //Read File  Puts next line into buffer and checks if it is null (in which case means there's no more data left)
            while ((line = br.readLine()) != null) {

                //Splits the line of data from txt and puts it in a temporary array - temp
                String[] temp = line.split(",");
                ClipInfo[index] = new ClipDataRecord(temp[0], Integer.parseInt(temp[1]), Integer.parseInt(temp[2]), temp[3], temp[4]);
                index++;  //Used to go to next line
            }
            numberOfEntries = index - 1;

        } catch (Exception e) {
            System.err.println("Error Reading File: " + e.getMessage());
            ClipInfo[0] = new ClipDataRecord("New File", 0, 0, "New File", "New File");
        }
    }

    /**
     * Write the data back out to the data file - one line at a time Note: I use
     * a data file with a different name while I am initially developing, so as
     * not to accidently kill my input file
     */
    public void writeFile() {
        try {
            PrintWriter outFile = new PrintWriter(new FileWriter("ClipsData.txt"));
            for (int m = 0; m < numberOfEntries + 1; m++) {
                outFile.println(ClipInfo[m].getClipName() + "," + ClipInfo[m].getCategory() + ","
                        + ClipInfo[m].getGenre() + "," + ClipInfo[m].getUploader() + "," + ClipInfo[m].getHyperlink());//(String strName, String strCategory, String strGenre,
            }
            outFile.close();
        } catch (Exception e) {
            System.err.println("Error Writing File: " + e.getMessage());
        }

    }
}

class ClipDataRecord {
    //Declaration of 3 Strings for storing the PC/IP data in memory for EACH PC Record

    String ClipName = new String();
    int Category;
    int Genre;
    String Uploader = new String();
    String Hyperlink = new String();

    public ClipDataRecord() {
        ClipName = "";
        Category = 0;
        Genre = 0;
        Uploader = "";
        Hyperlink = "";
    }

    public ClipDataRecord(String strName, int intCategory, int intGenre,
            String strUploader, String strHyperlink) {
        ClipName = strName;
        Category = intCategory;
        Genre = intGenre;
        Uploader = strUploader;
        Hyperlink = strHyperlink;
    }

    public void setClipInfo(String Name, int CategoryIndex, int GenreIndex) {
        ClipName = Name;
        Category = CategoryIndex;
        Genre = GenreIndex;
    }

    public void setClipName(String Name) {
        ClipName = Name;
    }

    public void setCategory(int CategoryIndex) {
        Category = CategoryIndex;
    }

    public void setGenre(int GenreIndex) {
        Genre = GenreIndex;
    }

    public String getClipName() {
        return ClipName;
    }

    public int getCategory() {
        return Category;
    }

    public int getGenre() {
        return Genre;
    }

    public String getUploader() {
        return Uploader;
    }

    public String getHyperlink() {
        return Hyperlink;
    }
}