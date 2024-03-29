package ser321.assign3.esandlin.client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import javax.swing.JTextField;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;



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

public class Client {

	private MessageGUI theView;
	private Message theModel;

	public Client(MessageGUI theView, Message theModel) {
		this.theView = theView;
		this.theModel = theModel;
		theView.setDateTextField(theView.getDateTextField());

		/*
		 * Tell the View that when ever the button is clicked to execute the
		 * actionPerformed method in the Listener inner class
		 */
		this.theView.addCypherListener(new Listener());
		this.theView.addDeleteListener(new Listener());
		this.theView.addSendListener(new Listener());
		this.theView.addReplyListener(new Listener());
	}

	/**
	 * @author ericsandlin
	 * listener class
	 *
	 */
	class Listener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			
			JTextField to, from, subject, date;

			/*
			 * Surround interactions with the view with a try block just in case
			 */
			try {

				to = theView.getToTextField();
				from = theView.getFromTextField();
				subject = theView.getSubjectTextField();
				date = theView.getDateTextField();

				/*
				 * This is for Deleting nodes
				 */
				if (e.getActionCommand().equals("Delete")) {
					DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) theView.messageTree
							.getSelectionPath().getLastPathComponent();

					if (selectedNode != theView.messageTree.getModel().getRoot()) {
						DefaultTreeModel model = (DefaultTreeModel) theView.messageTree.getModel();

						model.removeNodeFromParent(selectedNode);
						model.reload();
					}

					/*
					 * This is for creating new nodes
					 */
				} else if (e.getActionCommand().equals("Reply")) {

					DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) theView.messageTree
							.getSelectionPath().getLastPathComponent();

					DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(
							theView.getFromTextField().getText() + " " + theView.getDateTextField() + " ");

					selectedNode.add(new DefaultMutableTreeNode(theView.getSubjectTextField().getText()));

					// reload jtree model
					DefaultTreeModel model = (DefaultTreeModel) theView.messageTree.getModel();
					model.reload();

					/*
					 * todo
					 */
				} else if (e.getActionCommand().equals("Send Text")) {

					/*
					 * todo
					 */
				} else if (e.getActionCommand().equals("Send Cipher")) {

				}
				// firstNumber = theView.getFirstNumber();
				// secondNumber = theView.getSecondNumber();
				//
				// theModel.addTwoNumbers(firstNumber, secondNumber);
				//
				// theView.setCalcSolution(theModel.getCalculationValue());

			} catch (NumberFormatException ex) {
				System.out.println(ex);
				theView.displayErrorMessage("Something went wrong.");
			}
		}
	}
}

class Main {
	public static void main(String[] args) {
		
			try {
			// The OSName.java
			System.out.println(System.getProperty("os.name"));
			System.out.println(InetAddress.getLocalHost().getCanonicalHostName());

			// The MVC
			MessageGUI theView = new MessageGUI();
			Message theModel = new Message();
			Client theController = new Client(theView, theModel);
			theView.setVisible(true);
			
			String hostId = "$localhost";
			String regPort = "1099";
			if (args.length >= 2) {
				hostId = args[0];
				regPort = args[1];
			}

			System.out.println("Client obtained remote object reference to" + " the Server");
			BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));

			// System.setSecurityManager(new RMISecurityManager());
			// RMIClientGui rmiclient = new RMIClientGui(hostId, regPort);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}