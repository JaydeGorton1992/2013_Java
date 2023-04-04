import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


class Animation2 extends JFrame implements ActionListener
{

    JPanel panel;
    Timer timer;
    int x, y;

    public Animation2 ()
    {
        super ();
        setDefaultCloseOperation (EXIT_ON_CLOSE);
        timer = new Timer (0, this);

    }


    public void run ()
    {

        panel = new JPanel ()
        {
            @Override
            public void paintComponent (Graphics g)  //The JPanel paint method we are overriding.
            {
                int w = getWidth();
        int h = getHeight(); 

for(int ss=0;ss==10;ss++){
    timer.start();
}
x = 0;
y = 0;
        Color randomColor = new Color(125, 125, 125);
                g.setColor(randomColor);
                g.fillRect(x, y, x+5, y+5);
                //timer.start();
                g.setColor(randomColor);
                g.fillRect(x+5, y+5, x, y);

            }       
        }
        ;
        panel.setPreferredSize (new Dimension (200, 200)); //Setting the panel size

        getContentPane ().add (panel); //Adding panel to frame.
        pack ();
        setVisible (true);
        timer.start (); //This starts the animation.
    }


    public void actionPerformed (ActionEvent e)
    {
        x++;
        y++;
        if (x == 1)
        timer.start();
        panel.repaint ();  
        panel.revalidate (); 
    }


    public static void main (String[] args)
    {
        new Animation ().run (); //Start our new application.
    }
}