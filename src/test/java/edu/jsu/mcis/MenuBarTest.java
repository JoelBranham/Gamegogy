package edu.jsu.mcis;

import org.junit.*;

import javafx.event.ActionEvent;


import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import static org.junit.Assert.*;

public class MenuBarTest{

    @Test
    public void testMenuBarHasItems()
    {
        menubar bar = new menubar();
        assertTrue(bar.bar.getMenuCount()>0);
    }
    @Test
    public void testOnlineStatusDefaultIsOffline()
    {
        menubar bar = new menubar();
        assertEquals(true,bar.getOfflineButtonStatus());
        assertEquals(false,bar.getOnlineStatus());
    }
    @Test
    public void testWebStatusDefaultisOff()
    {
        menubar bar = new menubar();
        assertEquals(false,bar.getOnlineStatus());
    }
    @Test 
    public void testClickOnWebButtonOfflineShouldBeFalse()
    {
        menubar bar = new menubar();
        bar.setWebButtonStatus(true);
        assertEquals(true,bar.getWebButtonStatus());
        assertEquals(false,bar.getOfflineButtonStatus());
        assertEquals(true,bar.getOnlineStatus());
    }
    @Test
    public void testClickOnOfflineButtonWebShouldBeFalse()
    {
        menubar bar = new menubar();
        bar.setOfflineButtonStatus(true);
        assertEquals(true,bar.getOfflineButtonStatus());
        assertEquals(false,bar.getWebButtonStatus());
        assertEquals(false,bar.getOnlineStatus());
        
    }
}