package edu.jsu.mcis;

import org.junit.*;
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
}