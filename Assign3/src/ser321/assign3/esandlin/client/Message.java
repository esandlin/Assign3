package ser321.assign3.esandlin.client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Copyright (c) 2015 Tim Lindquist, Software Engineering, Arizona State
 * University at the Polytechnic campus
 * <p/>
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation version 2 of the License.
 * <p/>
 * This program is distributed in the hope that it will be useful, but without
 * any warranty or fitness for a particular purpose.
 * <p/>
 * Please review the GNU General Public License at:
 * http://www.gnu.org/licenses/gpl-2.0.html see also:
 * https://www.gnu.org/licenses/gpl-faq.html so you are aware of the terms and
 * your rights with regard to this software. Or, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301,USA
 * <p/>
 * Purpose: Sample Java Swing controller class. FolderBrowserGUI constructs the
 * view components for a sample GUI. This class is extends the GUI to provide
 * the control functionality. When the user does a tree node selection, this
 * valueChanged is called, but virtue of being a TreeSelectionListener and
 * adding itself as a listerner. FolderBrowser defines the call-backs for the
 * JButton as well. It contains sample control functions that respond to button
 * clicks and tree selects. This software is meant to run on Debian Wheezy Linux
 * <p/>
 * Ser321 Principles of Distributed Software Systems see
 * http://pooh.poly.asu.edu/Ser321
 * 
 * @author Tim Lindquist (Tim.Lindquist@asu.edu) CIDSE - Software Engineering,
 *         IAFSE, ASU at the Polytechnic campus
 * @file FolderBrowserGUI.java
 * @date July, 2015
 **/

public class Message implements java.io.Serializable {

	private static final long serialVersionUID = -8353937001320001715L;

	/**
	 * Create the file and the PrintWriter that will write to the file
	 * @param fileName
	 * @return
	 */
	private static PrintWriter createFile(String fileName) {
		try {
			/*
			 * Creates a File object that allows you to work with files on the
			 * hardrive
			 */
			File listOfNames = new File(fileName);
			/*
			 * FileWriter is used to write streams of characters to a file
			 * BufferedWriter gathers a bunch of characters and then writes them
			 * all at one time (Speeds up the Program) PrintWriter is used to
			 * write characters to the console, file
			 */
			PrintWriter infoToWrite = new PrintWriter(new BufferedWriter(new FileWriter(listOfNames)));
			return infoToWrite;
		}
		// You have to catch this when you call FileWriter
		catch (IOException e) {
			System.out.println("An I/O Error Occurred");
			System.exit(0);
		}
		return null;
	}


	/**
	 * 
	 */
	private static void getFileInfo() {
		System.out.println("Info Written to File\n");
		// Open a new connection to the file
		File listOfNames = new File("ServerClient");
		try {
			/*
			 *  FileReader reads character files
			 *  BufferedReader reads as many characters as possible
			 */			
			BufferedReader getInfo = new BufferedReader(new FileReader(listOfNames));
			// Reads a whole line from the file and saves it in a String
			String custInfo = getInfo.readLine();
			// readLine returns null when the end of the file is reached
			while (custInfo != null) {
				// System.out.println(custInfo);
				// Break lines into pieces
				String[] indivCustData = custInfo.split(" ");
				// Convert the String into an integer with parseInt
				int custAge = Integer.parseInt(indivCustData[2]);
				System.out.print("Customer " + indivCustData[0] + " is " + custAge + "\n");
				custInfo = getInfo.readLine();
			}
			getInfo.close();
		}	
		// Can be thrown by FileReader
		catch (FileNotFoundException e) {
			System.out.println("Couldn't Find the File");
			System.exit(0);
		}
		catch (IOException e) {
			System.out.println("An I/O Error Occurred");
			System.exit(0);
		}
	}
}