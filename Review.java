//1. It starts off with you grabbing the file name then it starts to break it down getting rid of the punctuation, then it will take that new string and find the sentiment value of it. It will take that output and add it to the totalVal where it will print the total sentiment value.
//2. a) The ratings don't make sense, since the method won't play out, but if it did it seems to make sense, since the review coorelate to positive or negative sentimental value due to it's either good or harsh words. 
//2. b) I feel coding in actual words that give more sensual values and have more of variety of words that could be good and bad could narrow down to the specifics of the rating.
//3. His code is wrong due to the fact if it's less than 0 it would go through the entire problem and it would spit out "4321" and that doesn't give the user the correct review it needs so all he needs to do to create correct code is add two statements of </> connected with an && symbol so the whole problem would make sure both statements are true before executing.
import java.util.Scanner;
import java.io.File;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Random;
import java.io.*;

/**
 * Class that contains helper methods for the Review Lab
 **/
public class Review {
  
  private static HashMap<String, Double> sentiment = new HashMap<String, Double>();
  private static ArrayList<String> posAdjectives = new ArrayList<String>();
  private static ArrayList<String> negAdjectives = new ArrayList<String>();
 
  
  private static final String SPACE = " ";
  
  static{
    try {
      Scanner input = new Scanner(new File("cleanSentiment.csv"));
      while(input.hasNextLine()){
        String[] temp = input.nextLine().split(",");
        sentiment.put(temp[0],Double.parseDouble(temp[1]));
        //System.out.println("added "+ temp[0]+", "+temp[1]);
      }
      input.close();
    }
    catch(Exception e){
      System.out.println("Error reading or parsing cleanSentiment.csv");
    }
  
  
  //read in the positive adjectives in postiveAdjectives.txt
     try {
      Scanner input = new Scanner(new File("positiveAdjectives.txt"));
      while(input.hasNextLine()){
        String temp = input.nextLine().trim();
        System.out.println(temp);
        posAdjectives.add(temp);
      }
      input.close();
    }
    catch(Exception e){
      System.out.println("Error reading or parsing postitiveAdjectives.txt\n" + e);
    }   
 
  //read in the negative adjectives in negativeAdjectives.txt
     try {
      Scanner input = new Scanner(new File("negativeAdjectives.txt"));
      while(input.hasNextLine()){
        negAdjectives.add(input.nextLine().trim());
      }
      input.close();
    }
    catch(Exception e){
      System.out.println("Error reading or parsing negativeAdjectives.txt");
    }   
  }
  
  /** 
   * returns a string containing all of the text in fileName (including punctuation), 
   * with words separated by a single space 
   */
  public static String textToString( String fileName )
  {  
    String temp = "";
    try {
      Scanner input = new Scanner(new File(fileName));
      
      //add 'words' in the file to the string, separated by a single space
      while(input.hasNext()){
        temp = temp + input.next() + " ";
      }
      input.close();
      
    }
    catch(Exception e){
      System.out.println("Unable to locate " + fileName);
    }
    //make sure to remove any additional space that may have been added at the end of the string.
    //totalSentiment("SimpleReview.txt");
    return temp.trim();
  }
     /**
   * Returns the word after removing any beginning or ending punctuation
   */
  public static String removePunctuation( String word )
  {
    while(word.length() > 0 && !Character.isAlphabetic(word.charAt(0)))
    {
      word = word.substring(1);
    }
    while(word.length() > 0 && !Character.isAlphabetic(word.charAt(word.length()-1)))
    {
      word = word.substring(0, word.length()-1);
    }
    
    return word;
  }

  
  public static double totalSentiment(String fileName)
  {
   //open file
   //pick each word -> when I find a space, all the stuff before was a word. But I've got to remove punctuation.
   //look up sentiment value
   //add to total
   //return value
      String file = textToString(fileName);
      String word = "";
      String space = " ";
      double totalVal = 0;
      for(int i = 0; i < file.length(); i++)
      {
         removePunctuation(fileName);
         String str = file.substring(i, i+1);
         if(str.equals(space))
         {
            totalVal += sentimentVal(word);
            word = "";
         }
         else
         {
            word += str;
         }
      }
      return totalVal;
      
      
  }
  
  public static int starRating(String fileName);
  {
      String rate = totalSentiment(fileName);
         if(rate > 10)
         {
            return 4;
         }
         else if(rate < 10 && rate > 5)
         {
            return 3;
         }
         else if(rate < 5 && rate > 0)
         {
            return 2;
         }
         else if(rate <= 0 && rate > -5)
         {
            return 1;
         }
         else
         {
            return 0;
         }
      
  }
 
  /**
   * @returns the sentiment value of word as a number between -1 (very negative) to 1 (very positive sentiment) 
   */
  public static double sentimentVal( String word )
  {
    try
    {
      return sentiment.get(word.toLowerCase());
    }
    catch(Exception e)
    {
      return 0;
    }
  }
  
  /**
   * Returns the ending punctuation of a string, or the empty string if there is none 
   */
  public static String getPunctuation( String word )
  { 
    String punc = "";
    for(int i=word.length()-1; i >= 0; i--){
      if(!Character.isLetterOrDigit(word.charAt(i))){
        punc = punc + word.charAt(i);
      } else {
        return punc;
      }
    }
    return punc;
  }
  
  /** 
   * Randomly picks a positive adjective from the positiveAdjectives.txt file and returns it.
   */
  public static String randomPositiveAdj()
  {
    int index = (int)(Math.random() * posAdjectives.size());
    return posAdjectives.get(index);
  }
  
  /** 
   * Randomly picks a negative adjective from the negativeAdjectives.txt file and returns it.
   */
  public static String randomNegativeAdj()
  {
    int index = (int)(Math.random() * negAdjectives.size());
    return negAdjectives.get(index);
    
  }
  
  /** 
   * Randomly picks a positive or negative adjective and returns it.
   */
  public static String randomAdjective()
  {
    boolean positive = Math.random() < .5;
    if(positive){
      return randomPositiveAdj();
    } else {
      return randomNegativeAdj();
    }
    
    
  }
}
