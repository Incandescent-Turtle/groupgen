package main;
import java.io.File; 
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import static main.GroupGenerator.*;

public class Shuffler {

	private static int timesShuffled = 0;

	//Forms even groups of specified size. If a group contains people that cant be together it will re-make the groups. If the groups are uneven they will be made as even as possible
	@SuppressWarnings("unchecked")
	public static void formGroups() throws FileNotFoundException 
	{
		ArrayList<ArrayList<String>> combinedGroupsList = new ArrayList<>();
		ArrayList<String> currentGroupList = new ArrayList<>();
		//checks to see if the button has been pushed. If it hasnt, it will take the names from the Text Area, allowing for the user to modify the class contents before first shuffle
		if(firstPush)
		{
			classList.clear();
			classList.addAll(Arrays.asList(groupsArea.getText().replaceAll("\\s+",":").split(":")));
		}
		Collections.shuffle(classList);
		
		int peopleInGroup = 0;
		//Iterating through classList to form groups, adding the groups to a big arrayList, which groupsList is then set to
		for (String name : classList)
		{
			peopleInGroup+=1;
			if(peopleInGroup==(int)spinner.getValue() || classList.indexOf(name)==classList.size()-1)
			{
				currentGroupList.add(name);
				peopleInGroup=0;
				combinedGroupsList.add((ArrayList<String>) currentGroupList.clone());
				currentGroupList.clear();
			} else {
				currentGroupList.add(name);
			}
		}
		System.out.println(combinedGroupsList);
		groupsList = combinedGroupsList;
		//make groups even
		disperseExtras();
		//ensure that the groups dont have any prohibited names
		checkParameters();
	}
	
	//Called upon button push. Uses group size and classList to create and display groups
	public static void printGroups() throws FileNotFoundException
	{
		groupsArea.setText("");
		//iterate through the groups and printing them
		for(ArrayList<String> group : groupsList)
		{
			groupsArea.append("Group " + (groupsList.indexOf(group)+1) + ": ");
			
			for(String name : group)
			{
				groupsArea.append(name + " ");
			}
			groupsArea.append(System.lineSeparator());
		}
	}
	//uses a Scanner to check each group of parameter names, and checks if they are contained within any group. All members of the parameter (a line in the text file) must be in a group 
	private static void checkParameters() throws FileNotFoundException
	{
		@SuppressWarnings("resource")
		Scanner parameterScanner = new Scanner(new File("parameters.txt"));
		//an arrayList holding parameter sets 
		ArrayList<String[]> parameterList = new ArrayList<String[]>();
		//adding parameter sets as arrays to parameterList
		while(parameterScanner.hasNext())
		{
			parameterList.add(parameterScanner.nextLine().split("\\s"));
		}
		//iterating through the groups, then the parameters to check if each group has any of the blacklisted groups
		for(ArrayList<String> group : groupsList)
		{
			//iterating through the paramaters to check if the group contians the names
			for(String[] parameterSet : parameterList)
			{
				int namesFound = 0;
				//iterates through each name in the parameter set
				for(String name : parameterSet)
				{
					if(group.contains(name))
					{
						namesFound++;
					}
				}
				//checking if the group contains all members of the parameter set
				if(namesFound == parameterSet.length)
				{
					System.out.println("Shuffled due to conflict (" + ++timesShuffled +")");
					formGroups();
					return;
				}
			}
		}
	}
	
	//called to make the groups even
	private static void disperseExtras() {
		//Checks to see if groups need dispersing
		if (lastGroup().size() < ((int)spinner.getValue())-1)
		{  
			System.out.println("Uneven Groups");
			System.out.println(lastGroup().size());
			//Checks if the last group is small enough to be dispersed among the other groups
			if(lastGroup().size() <= groupsList.size()-1 && (int)spinner.getValue() !=2)
			{
				System.out.println("Form Bigger groups");
				//Dispersing the names to the other groups
				for(String name : lastGroup())
				{
					groupsList.get((lastGroup().indexOf(name))).add(name);
				}
				//Removing the now empty last group
				groupsList.remove(groupsList.size()-1);
				//When the last group is too big to disperse among the bigger groups
			} else {
				System.out.println("Form smaller groups");
				int numberNeeded = ((int)spinner.getValue() - lastGroup().size());
				
				for(int i = 0; i < numberNeeded; i++)
				{
					lastGroup().add(groupsList.get(groupsList.size()-(i+1)).remove(0));
				}
			}
			//When nothing is dispersed
		} else {
			System.out.println("No dispersion");
		}
	}
	
	//helper method that returns the last group in groupsList
	private static ArrayList<String> lastGroup()
	{
		return groupsList.get(groupsList.size()-1);
	}
}
