package rbst;

import static org.junit.Assert.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

/**
 * Dictionary creator and tester for spell check on the song "If I ruled the World, by Nas ft. Lauryn Hill
 */
public class DictionaryCreatorTester
{
	private RedBlackTree<String> root;

	/**
	 * Construct tree method which takes a dictionary defined in the URL and makes a dictionary from it
	 */
	@Before
	public void constructTree() 
	{
		root = new RedBlackTree<String>();
		URL url;
		try 
		{
			long startTime = System.currentTimeMillis();
			url = new URL("http://www.math.sjsu.edu/~foster/dictionary.txt");
			Scanner s = new Scanner(url.openStream());
			while(s.hasNext())
			{
				String line = s.nextLine();
				root.insert(line);
			}
			long endTime = System.currentTimeMillis();
			System.out.println("Time taken to construct Tree : " + (endTime - startTime) + "(ms)");
		} 
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	/**
	 * Looks at each word of song method checks if it exists in the Red Black Tree
	 * If it does not it will notify the user
	 */
	@Test
	public void checkForSpellErrors()
	{
		File f = new File("data/If I ruled the World -Nas,Lauryn Hill.txt");
		try 
		{
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			String line = "";
			long startTime = System.currentTimeMillis();
			while((line = br.readLine()) != null)
			{
				line = line.toLowerCase();
				String[] arrOfWords = line.split("\\W+");
				for(String lyric : arrOfWords)
				{
					if(root.lookup(lyric) == null)
						System.out.println( "Are you sure you meant " + lyric);
				}
			}
			long endTime = System.currentTimeMillis();
			System.out.println("Time taken to spell check : " + (endTime - startTime) + "(ms)");
			br.close();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	
}
