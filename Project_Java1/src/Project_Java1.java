import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.SpringLayout;
import javax.swing.JComboBox;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;


/**
 * @author Mark O'Reilly
 * 
 * Interface with Low Coupling and High Cohesion
 *    and with initial ActionListener code structure.
 */

public final class Project_Java1 extends Frame implements ActionListener, WindowListener
{
    int maxEntries = 100, numberOfEntries = 0, currentEntry = 0;  //Global variable to define the size of the arrays //Global variable to remember how many entries are in the 3 arrays //Global variable to remember which entry in the arrays we are currently looking at / dealing with 

    //Declaration of an array of objects for storing the PC/IP data in memory -
    //              based on class: PCDataRecord, the array is called: PCInfo
    PCDataRecord[] PCInfo = new PCDataRecord[maxEntries];

    //Declaration of the various screen objects - lables, textfields and buttons
    Label lblPCName, lblPCID, lblIP, lblFind;
    TextField txtPCName, txtPCID, txtIP, txtFind;
    Button btnNew, btnSave, btnDel, btnFind, btnExit, btnFirst, btnPrev, btnNext, btnLast;
    JComboBox jcbGenre, jcbCategory, jcbAllGenre;    
    String[] CategoryArray = {"Comdey", "Blog", "Music"};
    String[] GenreArray = {"Pop", "Punk"};
    String[] AllGenreArray= {"Pop", "Punk"};
    /**
    * Entry point to the class and application.
    * @param args Array of String arguments.
    */
    public static void main(String[] args)
    {
        Frame myFrame = new Project_Java1();
        myFrame.setSize(470,250);
        myFrame.setLocation(400, 200);
        myFrame.setResizable(false);
        myFrame.setVisible(true);
    }

    
    /**
    * Constructor which sets up the
    * screen layout and listeners.
    */
    public Project_Java1()
    {
        setTitle("IP Address Solution");
        setBackground(Color.yellow);

        SpringLayout myLayout = new SpringLayout();
        setLayout(myLayout);
        
        LocateLabels(myLayout);
        LocateTextFields(myLayout);
        LocateButtons(myLayout);
        LocateComboBoxs(myLayout);
        this.addWindowListener(this);
        
        
    }

    
    /**
    * Controlling method for adding multiple labels 
    *    to the screen
    */
    public void LocateLabels(SpringLayout myLabelLayout)
    {
        lblPCName = LocateALabel(myLabelLayout, lblPCName, "PC Name", 30, 25);
        lblPCID = LocateALabel(myLabelLayout, lblPCID, "PC ID", 30, 50);
        lblIP = LocateALabel(myLabelLayout, lblIP, "IP", 30, 75);
        lblFind = LocateALabel(myLabelLayout, lblFind, "Search", 30, 120);
    }

        
    /**
    * Method with low coupling and high cohesion 
    *    for adding individual labels:
    *    - reduces overall code, especially in the
    *         LocateLabels method.
    *    - makes this method re-usable with minimal
    *         adjustment as it is moved from one
    *         program to another.
    */
    public Label LocateALabel(SpringLayout myLabelLayout, Label myLabel, String  LabelCaption, int x, int y)
    {
        myLabel = new Label(LabelCaption);
        add(myLabel);        
        myLabelLayout.putConstraint(SpringLayout.WEST, myLabel, x, SpringLayout.WEST, this);
        myLabelLayout.putConstraint(SpringLayout.NORTH, myLabel, y, SpringLayout.NORTH, this);
        return myLabel;
    }
   

    /**
    * Controlling method for adding multiple textboxes 
    *    to the screen
    */
    public void LocateTextFields(SpringLayout myTextFieldLayout)
    {
        txtPCName  = LocateATextField(myTextFieldLayout, txtPCName, 20, 130, 25);
        txtPCID = LocateATextField(myTextFieldLayout, txtPCID, 20, 130, 50);
        txtIP = LocateATextField(myTextFieldLayout, txtIP, 20, 130, 75);
        txtFind = LocateATextField(myTextFieldLayout, txtFind, 20, 130, 120);
    }

        
    /**
    * Method with low coupling and high cohesion 
    *    for adding individual textboxes:
    *    - reduces overall code, especially in the
    *         LocateTextFields method.
    *    - makes this method re-usable with minimal
    *         adjustment as it is moved from one
    *         program to another.
    */
    public TextField LocateATextField(SpringLayout myTextFieldLayout, TextField myTextField, int width, int x, int y)
    {
        myTextField = new TextField(width);
        add(myTextField);        
        myTextFieldLayout.putConstraint(SpringLayout.WEST, myTextField, x, SpringLayout.WEST, this);
        myTextFieldLayout.putConstraint(SpringLayout.NORTH, myTextField, y, SpringLayout.NORTH, this);
        return myTextField;
    }


    /**
    * Controlling method for adding multiple buttons 
    *    to the screen
    */
    public void LocateButtons(SpringLayout myButtonLayout)
    {
        btnNew = LocateAButton(myButtonLayout, btnNew, "New", 320, 25, 80, 25);
        btnSave = LocateAButton(myButtonLayout, btnSave, "Save", 320, 50, 80, 25);
        btnDel = LocateAButton(myButtonLayout, btnDel, "Delete", 320, 75, 80, 25);
        btnFind = LocateAButton(myButtonLayout, btnFind, "Find", 320, 100, 80, 25);
        btnExit = LocateAButton(myButtonLayout, btnExit, "Exit", 320, 170, 80, 25);
        btnFirst = LocateAButton(myButtonLayout, btnFirst, "|<", 140, 170, 30, 25);
        btnPrev = LocateAButton(myButtonLayout, btnPrev, "<", 180, 170, 30, 25);
        btnNext = LocateAButton(myButtonLayout, btnNext, ">", 220, 170, 30, 25);
        btnLast = LocateAButton(myButtonLayout, btnLast, ">|", 260, 170, 30, 25);
    }

        
    /**
    * Method with low coupling and high cohesion 
    *    for adding individual buttons:
    *    - reduces overall code, especially in the
    *         LocateButtons method.
    *    - makes this method re-usable with minimal
    *         adjustment as it is moved from one
    *         program to another.
    */
    public Button LocateAButton(SpringLayout myButtonLayout, Button myButton, String  ButtonCaption, int x, int y, int w, int h)
    {    
        myButton = new Button(ButtonCaption);
        add(myButton);
        myButton.addActionListener(this);
        myButtonLayout.putConstraint(SpringLayout.WEST, myButton, x, SpringLayout.WEST, this);
        myButtonLayout.putConstraint(SpringLayout.NORTH, myButton, y, SpringLayout.NORTH, this);
        myButton.setPreferredSize(new Dimension(w,h));
        return myButton;
    }

        public void LocateComboBoxs(SpringLayout myComboBoxLayout)
    {
        jcbGenre = LocateAComboBox(myComboBoxLayout, jcbGenre, "genre", 295, 50, CategoryArray);
        jcbCategory = LocateAComboBox(myComboBoxLayout, jcbCategory, "category", 295, 75, GenreArray);
        jcbAllGenre = LocateAComboBox(myComboBoxLayout, jcbAllGenre, "category", 295, 120, AllGenreArray);
    }

        
    /**
    * Method with low coupling and high cohesion 
    *    for adding individual buttons:
    *    - reduces overall code, especially in the
    *         LocateButtons method.
    *    - makes this method re-usable with minimal
    *         adjustment as it is moved from one
    *         program to another.
    */
        
    public JComboBox LocateAComboBox(SpringLayout myComboBoxLayout, JComboBox myComboBox, String  ButtonCaption, int x, int y, String[] comboArray)
    {    
    myComboBox = new JComboBox(comboArray);
    myComboBox.setBounds(0, 0, 0, 0);
    //myComboBox.addItem(comboArray);
    myComboBox.setSelectedIndex(0);
    add(myComboBox);
    myComboBoxLayout.putConstraint(SpringLayout.WEST, myComboBox, x, SpringLayout.WEST, this);
    myComboBoxLayout.putConstraint(SpringLayout.NORTH, myComboBox, y, SpringLayout.NORTH, this);
    myComboBox.setPreferredSize(new Dimension(20,23));
    return myComboBox;
    }
    
    /**
    * Respond to user action events, such as clicking the New button
    */

   public void actionPerformed(ActionEvent e)
    {
        // BUTTON FIRST
        if(e.getSource() == btnFirst)
        {
            currentEntry = 0;
            displayEntry(currentEntry);
        }

        // BUTTON PREVIOUS
        if(e.getSource() == btnPrev)
        {
           if(currentEntry > 0)
           {
                currentEntry--;
                displayEntry(currentEntry);
           }
        }

        // BUTTON NEXT
        if (e.getSource()== btnNext)
        {
            if(currentEntry < numberOfEntries - 1)
            {
                currentEntry++;
                displayEntry(currentEntry);
            }
        }

        // BUTTON LAST
        if(e.getSource() == btnLast)
        {
            currentEntry = numberOfEntries - 1;
            displayEntry(currentEntry);
        }

        // BUTTON NEW
        if(e.getSource() == btnNew)
        {
            if (numberOfEntries < maxEntries - 1)
            {
                numberOfEntries++;
                currentEntry = numberOfEntries - 1;
                PCInfo[currentEntry] = new PCDataRecord("","","");
                displayEntry(currentEntry);
            }
        }

        // BUTTON SAVE
        if(e.getSource() == btnSave)
        {
            saveEntry(currentEntry);
        }

        // BUTTON DELETE
        if(e.getSource()== btnDel)
        {
            //Move all the later entries up one line in the arrays, covering over the current entry in the process
            for (int index = currentEntry; index < numberOfEntries-1; index++)
            {
                PCInfo[index].setPCInfo(PCInfo[index+1].getPCName(), PCInfo[index+1].getPCID(), PCInfo[index+1].getIPAddress());
            }
            //Reduce the size of the arrays and if the current entry is past the last entry, desplay the last entry
            numberOfEntries--;
            if (currentEntry > numberOfEntries - 1)
            {
                currentEntry = numberOfEntries - 1;
            }
            displayEntry(currentEntry);
        }

        // BUTTON FIND
        if(e.getSource() == btnFind)
        {   
            boolean found = false;
            int index = 0;
            // While there are more entries to check and the 'search' entry has not been found... 
            while (index < numberOfEntries && found == false)
            {
                //If the current entry is equal to the 'search' entry...
                if (PCInfo[index].getPCName().equals( txtFind.getText()))
                {
                    //Set found = true
                    found = true;
                }
                //Move to the next entry
                index++;
            }
            //If the entry was found, then display it.
            if (found) 
            {
                currentEntry = index - 1;
                displayEntry(currentEntry);
            }
        }
        
        // BUTTON EXIT
        if(e.getSource() == btnExit)
        {
            writeFile();
            System.exit(0);
        }

    }

    /**
    * Respond to Window events, such as clicking the Close ( X ) button
    */
    
    public void windowClosing(WindowEvent we)
    {
        writeFile();
        System.exit(0);
    }

    public void windowIconified(WindowEvent we)
    {
    }

    public void windowOpened(WindowEvent we)
    {
        readFile("IPAddresses.txt");
        displayEntry(currentEntry);
    }

    public void windowClosed(WindowEvent we)
    {
    }

    public void windowDeiconified(WindowEvent we)
    {
    }

    public void windowActivated(WindowEvent we)
    {
    }

    public void windowDeactivated(WindowEvent we)
    {
    }


    /**
    * Display the numbered entry requested
    */

   public void displayEntry(int index)
    {
        txtPCName.setText(PCInfo[index].getPCName());
        txtPCID.setText(PCInfo[index].getPCID());
        txtIP.setText(PCInfo[index].getIPAddress());
    }

    
    /**
    * Copy the numbered entry requested from screen to the array and then calling to save the whole array into the data file.
    */

    public void saveEntry(int index)
    {
        PCInfo[index].setPCInfo(txtPCName.getText(),txtPCID.getText(),txtIP.getText()); 
        writeFile();
    }

        
    /**
    * Read in the data from the data file - IPAddress.txt - one line at a time and store in the 3 arrays
    * Remember the number of entries read in, in the gobal variable - numberOfEntries
    */

    public void readFile(String File)
    {
        try
        {
            FileInputStream fstream = new FileInputStream(File);
            try (DataInputStream in = new DataInputStream(fstream)) {
                BufferedReader br = new BufferedReader(new InputStreamReader(in));
                int index = 0;  // i is used as the line counter
                String line;
                //Read File  Puts next line into buffer and checks if it is null (in which case means there's no more data left)
                while ((line = br.readLine()) != null)
                {
                    //Splits the line of data from txt and puts it in a temporary array - temp
                    String[] temp = line.split(",");
                    PCInfo[index] = new PCDataRecord(temp[0],temp[1],temp[2]); //Saves each record into its respective PCDataRecord object.
                    index++;  //Used to go to next line
                }
                numberOfEntries = index;
            }
        }
        catch (Exception e)
        {
            System.err.println("Error Reading File: " + e.getMessage());
        }
    }

    
    /**
    * Write the data back out to the data file - one line at a time
    * Note: I use a data file with a different name while I am initially developing, 
    *                                                   so as not to accidently kill my input file
    */

    public void writeFile()
    {
        try
        {
            try (PrintWriter out = new PrintWriter(new FileWriter("IPAddresses_New.txt"))) {
                for(int m = 0; m < numberOfEntries; m++){
                    out.println(PCInfo[m].getPCName() + "," + PCInfo[m].getPCID() + "," + PCInfo[m].getIPAddress());
                }
            }
        }
        catch (Exception e)
        {
          System.err.println("Error Writing File: " + e.getMessage());
        }
    }    
    
}

class PCDataRecord 
{
    //Declaration of 3 Strings for storing the PC/IP data in memory for EACH PC Record
    String PCName = new String();   
    String PCID = new String();
    String IPAddress = new String();
    
    public PCDataRecord()
    {    
        PCName = "Name";
        PCID = "PC ID";
        IPAddress = "IP Address";
    }

    public PCDataRecord(String Name, String ID, String IP)
    {    
        PCName = Name;
        PCID = ID;
        IPAddress = IP;
    }

    public void setPCInfo(String Name, String ID, String IP)
    {    
        PCName = Name;
        PCID = ID;
        IPAddress = IP;
    }

    public void setPCName(String Name)
    {    
        PCName = Name;
    }

    public void setPCID(String ID)
    {    
        PCID = ID;
    }

    public void setIPAddress(String IP)
    {    
        IPAddress = IP;
    }

    public String getPCName()
    {    
        return PCName;
    }

    public String getPCID()
    {    
        return PCID;
    }

    public String getIPAddress()
    {    
        return IPAddress;
    }

}