import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


class Animation extends JFrame implements ActionListener
{

    JPanel panel;
    Timer timer;
    int x, y;

    public Animation ()
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
        int G = 0;
        int R = 0;
        int B = 0;
                for(int y=0;y<=h;y+=5){
                    //timer.stop();
        for(int x=0;x<=w;x+=5){
       
R = R + (int)(((Math.random()*(6)-2)));
G = G + (int)(((Math.random()*(6)-2)));
B = B + (int)(((Math.random()*(6)-2)));

B = (B <= 0) ? 0 : B;
B = (B >= 255) ? 255 : B;

G = (G <= 0) ? 0 : G;
G = (G >= 255) ? 255 : G;

R = (R <= 0) ? 0 : R;
R = (R >= 255) ? 255 : R;
for(int ss=0;ss==10;ss++){
    timer.start();
}
Color randomColor = new Color(R, G, B);
        g.setColor(randomColor);
        g.fillRect(x, y, x+5, y+5);
        //timer.start();
        g.setColor(randomColor);
        g.fillRect(x+5, y+5, x, y);

        }
        
        }
       
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