package main;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.ObjectInputStream.GetField;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JSpinner.DefaultEditor;
import javax.swing.JTextArea;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

//Contains main method. Calls upon Frame's static method that initilizes all frame components thus creating the window
public class GroupGenerator
{
	/*
	 * Static Variables
	 */
	//An arrayList with the first string of every line of classList.txt
	public static ArrayList<String> classList;
	//A Text Area to display names and groups
	public static JTextArea groupsArea = newGroupsArea();
	//A Spinner representing the group size
	public static JSpinner spinner = newGroupSizeSpinner();
	//a label next to the above spinner, saying "Group Size:"
	public static JLabel label = newGroupSizeLabel();
	//a button that reads "Generate Groups" and when clicked forms groups and prints them to the GUI
	public static JButton button = newGenerateButton();
	//To determine whether or not the button has been pressed previously
	public static boolean firstPush = true;
	//An arrayList of all of the groups
	public static ArrayList<ArrayList<String>> groupsList;
	//Creates a frame and places all components onto it
	public static JFrame frame = createFrame();
	
	//Called upon program start
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException, FileNotFoundException {
		UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		//Retrieves names from classList.txt and fills the classList arrayList
		setClassList();
		//Displays shuffled names, not in groups
		displayNames();
	}
	//Creates a new frame instance and adds the components
	private static JFrame createFrame() 
	{
		JFrame frame = new JFrame();
		frame.setTitle("Group Generator");
		frame.setBounds(100, 100, 1122, 708);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		//Adding components
		frame.getContentPane().add(GroupGenerator.groupsArea);
		frame.getContentPane().add(GroupGenerator.label);
		frame.getContentPane().add(GroupGenerator.spinner);
		frame.getContentPane().add(GroupGenerator.button);
		frame.setVisible(true);
		return frame;
	}
	//Uses a Scanner to read classList.txt and fills the classList arrayList with all the first strings on each line (should be first name)
	private static void setClassList() throws FileNotFoundException  
	{
		ArrayList<String> namesList = new ArrayList<>();
		Scanner scanner = new Scanner(new File("classList.txt"));
		while(scanner.hasNext())
		{
			String next = scanner.next();
			namesList.add(next);
			if(scanner.hasNextLine()) scanner.nextLine();
		}
		scanner.close();
		Collections.shuffle(namesList);
		classList = namesList;
	}
	//Helper method to create the "Group Size:" text
	private static JLabel newGroupSizeLabel() 
	{
		JLabel label = new JLabel();
		label.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label.setBounds(900,565,200,100);
		label.setText("Group Size:");
		return label;
	}
	//Helper method to create the spinner that sets the group size
	private static JSpinner newGroupSizeSpinner()
	{
		JSpinner spinner = new JSpinner(new SpinnerNumberModel(2,2,200,1));
		spinner.setBounds(1011, 600, 55, 33);
		((DefaultEditor)spinner.getEditor()).getTextField().setEditable(false);
		return spinner;
	}
	//Helper method to create the button that reads "Generate Groups" and when clicked sets the arrayList groupsList then prints the groups to the GUI (also sets firstPush to false after the first click)
	private static JButton newGenerateButton() 
	{
		JButton button = new JButton();
		button.setFont(new Font("Tahoma", Font.PLAIN, 40));
		button.setBounds(371,563,429,53);
		button.setText("Generate Groups");
		//Listens for button press, tells the textarea (groupsArea) that the button was clicked
		button.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				try {
					Shuffler.formGroups();
					Shuffler.printGroups();
					firstPush=false;
					
					
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
			}
		});
		return button;
	}
	//Helper method to create the text area in which names and groups are displayed
	private static JTextArea newGroupsArea() 
	{
		JTextArea textArea = new JTextArea();
		textArea.setBounds(20, 16, 1070, 521);
		textArea.setFont(new Font("Tahoma", Font.PLAIN, 23));
		return textArea;
	}
	//Iterated through the classList arrayList to display shuffled names to the screen, the names aren't in groups. Also adds a numbered list to the console 
	private static void displayNames() throws FileNotFoundException
	{
		int round=0;
		for(String name : classList)
		{
			if(round >=3)
			{
				groupsArea.append(System.lineSeparator());
				round=0;
			}
			round++;
			groupsArea.append(name + "\t");
			System.out.println(classList.indexOf(name)+1 + ". " + name);
		}
	}
}
