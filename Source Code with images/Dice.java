package jem;

/**
 * R5.0.0
 *
 * Dice class
 *
 * Creates a class which simulates two dice being rolled.
 * 
 * @author  JEM CSC478B
 */
public class Dice 
{
   /*************
    * Attributes
    *************/
    
   //Integer value of first die on roll
   private int die1;
   
   //Integer value of second die on roll
   private int die2;
   
   //Converted roll value to NumberToknes.Value for resource payment
   private NumberTokens.Value value;
   
   
   /**************
    * Constructor
    **************/
   
   /**
    * R5.1.0
    * Creates a pair of dice
    */
   public Dice()
   {}


   /**********
    * Methods
    **********/
    
   /**
    * R5.1.0
    * Creates a random number between 1 and 6 for each die and assigns the appropriate NumberTokens.Value
    */
   public void roll()
   {
      //Get randome number between 1 and 6 for each die
      die1 = (int)(Math.random()*6) + 1;
      die2 = (int)(Math.random()*6) + 1;
      
      //If the sum of the dice is less than 7
      if(die1 + die2 < 7)
      
         //The NumberTokens.Value is the value of the enum at the index: sum of the dice minus 2.
         value = NumberTokens.Value.values()[die1 + die2 - 2];
      
      //If the sum of the die is greater than 7
      else if(die1 + die2 > 7)
      
         //The NumberTokens.Value is the value of the enum at the index: sum of the dice minus 3.
         value = NumberTokens.Value.values()[die1 + die2 - 3];
         
      else
         //If a seven is rolled, the dice do not have a NumberTokens.value.
         value = null;
   }
   
   /**
    * R5.1.0
    * Returns a string which diplays the value of each die and the roll total.
    *
    * @return        String telling the roll values
    */
   public String getRoll()
   {
      return die1+die2 + "               "+die1+" AND "+die2;
   }
   
   /**
    * R5.1.0
    * Returns the value that corresponds to the NumberTokens.Value.
    *
    * @return        The NumberTokens.Value of the dice roll
    */
   public NumberTokens.Value getValue()
   {
      return value;
   }
}