package edu.jsu.mcis;

import javax.swing.*;
import java.awt.event.*;

public class menubar extends JMenu implements ActionListener{
    public JMenuBar bar;
    private JMenu menu;
    private JRadioButton offlineButton;
    private JRadioButton webButton;
    
    public boolean getOnlineStatus()
    {
        boolean value = false;
        if (offlineButton.isSelected() && !webButton.isSelected())
            value = false;
        else if (!offlineButton.isSelected() && webButton.isSelected())
            value = true;
        return value;
    }
    public Boolean getOfflineButtonStatus()
    {
        return this.offlineButton.isSelected();
    }
    public Boolean getWebButtonStatus()
    {
        return this.webButton.isSelected();
    }
    public void setOfflineButtonStatus(Boolean value)
    {
        this.offlineButton.setSelected(value);
        this.webButton.setSelected(!value);
    }
    public void setWebButtonStatus(Boolean value)
    {
        this.webButton.setSelected(value);
        this.offlineButton.setSelected(!value);
    }

    public menubar(){
        bar = new JMenuBar();
        menu = new JMenu("Menu");
        offlineButton = new JRadioButton("Offline");
        webButton = new JRadioButton("Webservice");
        offlineButton.setSelected(true);
        webButton.setSelected(false);
        

        offlineButton.addActionListener(this);
        menu.add(offlineButton);
        webButton.addActionListener(this);
        menu.add(webButton);
        bar.add(menu);
        
    }
    @Override
    public void actionPerformed(ActionEvent e){
       if(e.getActionCommand().equals("Offline")){
           //menu.setSelected(false);
            webButton.setSelected(false);
            offlineButton.setSelected(true);
       }
       else if(e.getActionCommand().equals("Webservice")){
           webButton.setSelected(true);
           offlineButton.setSelected(false);
       }

    }
}