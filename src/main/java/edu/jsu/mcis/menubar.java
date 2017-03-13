package edu.jsu.mcis;

import javax.swing.*;
import java.awt.event.*;

public class menubar extends JMenu implements ActionListener{
    public JMenuBar bar;
    private JMenu menu;
    private JRadioButton oButton;
    private JRadioButton wButton;

    public menubar(){
        bar = new JMenuBar();
        menu = new JMenu("Menu");
        oButton = new JRadioButton("Offline");
        wButton = new JRadioButton("Webservice");
        oButton.setSelected(true);
        wButton.setSelected(false);
        

        oButton.addActionListener(this);
        menu.add(oButton);
        wButton.addActionListener(this);
        menu.add(wButton);
        bar.add(menu);
        
    }
    @Override
    public void actionPerformed(ActionEvent e){
       if(e.getActionCommand().equals("Offline")){
            wButton.setSelected(false);
            oButton.setSelected(true);
       }
       else if(e.getActionCommand().equals("Webservice")){
           wButton.setSelected(true);
           oButton.setSelected(false);
       }

    }
}