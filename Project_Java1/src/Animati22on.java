import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


class Animati22on extends JFrame implements ActionListener
{

    JPanel panel;
    Timer timer;
    int x, y, xpos, ypos;
    

    public Animati22on ()
    {
        super ();
        setDefaultCloseOperation (EXIT_ON_CLOSE);
        timer = new Timer (0, this);

    }


    public void run ()
    {

        panel = new JPanel ()
        {
        int xpos = 0;
        int ypos = 0;
            @Override
            public void paintComponent (Graphics g)  //The JPanel paint method we are overriding.
            {
                int w = getWidth();
        int h = getHeight(); 
        int G = 252;
        int R = 255;
        int B = 0;

                for(int y=0;y<=h;y+=5){
                    //timer.stop();
        for(int x=0;x<=w;x+=5){
 
for(int ss=0;ss==10;ss++){
    timer.start();
}
if (xpos == x){
Color randomColor = new Color(R, G, B);
xpos++;        
g.setColor(randomColor);
        g.fillRect(xpos, ypos, xpos+50, ypos+50);
    
        }
        xpos++;
        System.out.println(xpos);

        }
        
        }
       xpos = 0;
       ypos = 0;
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
        new Animati22on ().run (); //Start our new application.
    }
}