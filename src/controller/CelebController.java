package controller;

import javax.swing.*;
import java.util.ArrayList;
import model.*;

public class CelebController
{
	private ArrayList<Celebrity> celebrityList;
	private Celebrity currentCelebrity;
	
	public CelebController()
	{
		celebrityList = new ArrayList<Celebrity>();
	}
	
	public void start()
	{
		
	}
	
	public int getCelebrityListSize()
	{
		return celebrityList.size();
	}
	
	public void play()
	{
		if(celebrityList != null && celebrityList.size() > 0)
		{
			this.currentCelebrity = celebrityList.get(0);
		}
	}
	
	public String checkGuess(String guess)
	{
		String response = "";
		
		if(guess.equalsIgnoreCase(currentCelebrity.getName()))
		{
			response = "Correct!\n";
			if(celebrityList.size() != 0)
			{
				response += getRandomCelebrity().getClue();
			}
			else
			{
				response += "Game over. You win!";
			}
		}
		else
		{
			response += "WRONG! try again :P\n" + currentCelebrity.getClue();
		}
		
		return response;
	}
	
	public String getClue()
	{
		return currentCelebrity.getClue();
	}
	
	public Celebrity getRandomCelebrity()
	{
		int randomIndex = (int)(Math.random() * celebrityList.size());
		currentCelebrity = celebrityList.remove(randomIndex);
		
		return currentCelebrity;
	}
	
	public void addCelebrity(String name, String clue, String type)
	{
		Celebrity toBeAdded = new Celebrity(name, clue);
		celebrityList.add(toBeAdded);
	}
	
	public boolean processGuess(String guess)
	{
		boolean matches = false;
		
		if(guess.trim().equalsIgnoreCase(currentCelebrity.getName()))
		{
			matches = true;
			celebrityList.remove(0);
			if(celebrityList.size() > 0)
			{
				currentCelebrity = celebrityList.get(0);
			}
		}
		
		return matches;
	}
	
	public boolean validateCelebrity(String name)
	{
		boolean validCelebrity = false;
		
		if(name.trim().length() >= 4)
		{
			validCelebrity = true;
		}
		
		return validCelebrity;
	}
	
	public boolean validateClue(String clue, String type)
	{
		boolean validClue = false;
		
		if(clue.trim().length() >= 10)
		{
			validClue = true;
			
			if(type.equalsIgnoreCase("literature"))
			{
				String[] temp = clue.split(",");
				if(temp.length > 1)
				{
					validClue = true;
				}
				else
				{
					validClue = false;
				}
			}
		}
		
		return validClue;
	}
	
	public String sendClue()
	{
		return currentCelebrity.getClue();
	}
	
	public String sendAnswer()
	{
		return currentCelebrity.getName();
	}
}
