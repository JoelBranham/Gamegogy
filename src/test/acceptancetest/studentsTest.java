package edu.jsu.mcis;

import java.io.*;
    
import au.com.bytecode.opencsv.CSVParser;
import static edu.jsu.mcis.Main.json;
import org.junit.*;
import static org.junit.Assert.*;

import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;    
    
    
    

public class studentsTest
{
    @Before
    public void setup()
    {
        students student = new students();
    }

    @Test
    public final void testOutput()
    {
        assetTrue(false);
    }

    
}