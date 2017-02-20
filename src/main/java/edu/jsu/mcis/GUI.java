package testui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;


public class GUI extends JPanel{
    JFrame frame;
    JLabel[] labels;
    JComboBox[] combos;
    private final int numOfLabels = 20;
    private final int numOfCombos = 3;
    
    String[] testCourses = {"Course1","Course2","Course3"};//remove later
    String[] testColumns = {"Col1","Col2","Col3"};//remove later
    public GUI() throws IOException
    {
       
        BufferedImage img = ImageIO.read(new File("board.jpg"));
        BufferedImage img2 = ImageIO.read(new File("black.png"));
        JLabel bg = new JLabel(new ImageIcon(img));
        bg.setBounds(0,100,700,700);
        JLabel bg1 = new JLabel(new ImageIcon(img2));
        bg1.setBounds(175,70,100,24);
        
        
        
        frame = new JFrame();
        frame.setName("Gamegogy");
        frame.setTitle("Gamegogy");
        frame.setSize(new Dimension(700,700));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setResizable(false);
        
        
        
        labels = new JLabel[numOfLabels];
        for (int k=0;k<labels.length;k++)
        {
            labels[k] = new JLabel();
            frame.add(labels[k]);
            if (k>2)
            labels[k].setForeground(java.awt.Color.white);
            labels[k].setFont(new Font("Serif", Font.BOLD,20));
        }
        frame.add(bg);
       // frame.add(bg1);
        
       
        labels[0].setText("Gamegogy");
        labels[0].setBounds(330,10,100,32);
        labels[1].setText("Course");
        labels[1].setBounds(100,64,70,32);
        labels[2].setText("Column");
        labels[2].setBounds(450,64,70,32);
        labels[3].setText("Term:");
        labels[3].setBounds(100,200,70,32);
        labels[4].setText("Enrollment:");
        labels[4].setBounds(450,200,200,32);
        labels[5].setText("ID:");
        labels[5].setBounds(100,400,70,32);
        labels[6].setText("Name:");
        labels[6].setBounds(100,440,70,32);
        labels[7].setText("Email:");
        labels[7].setBounds(100,480,70,32);
        labels[8].setText("Score:");
        labels[8].setBounds(100,520,70,32);
        
        labels[9].setText("111169");
        labels[9].setBounds(175,400,400,32);
        labels[10].setText("Otis Pate");
        labels[10].setBounds(175,440,400,32);
        labels[11].setText("opate@jsu.edu");
        labels[11].setBounds(175,480,400,32);
        labels[12].setText("99");
        labels[12].setBounds(175,520,400,32);
        labels[13].setText("Summer");
        labels[13].setBounds(175,200,150,32);
        labels[14].setText("15");
        labels[14].setBounds(575,200,200,32);
        
        combos = new JComboBox[numOfCombos];
        combos[0] = new JComboBox(testCourses);
        combos[0].setBounds(175,70,100,24);
        //combos[0].setBackground(Color.red);
        //combos[0].setForeground(Color.red);
        frame.add(combos[0]);
        combos[1] = new JComboBox(testColumns);
        combos[1].setBounds(575,68,100,24);
        combos[0].updateUI();
        frame.add(combos[1]);

      
       
        
        
        frame.setVisible(true);//must be last
    }
}
