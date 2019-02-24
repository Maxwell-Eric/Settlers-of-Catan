package jem;

import java.awt.Image;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import java.util.LinkedList;
import java.util.Collections;

/**
 * NumberTokens class
 *
 * Creates a linked list of 18 number tokens in a random order, except that the 
 * EIGHT and SIX tokens are placed in the list stratigically to avoid being in 
 * adjacent tiles.
 *
 * @author  JEM CSC478B
 */

public class NumberTokens extends LinkedList<NumberTokens.NumberToken>
{
   /**
    * R1.3.1   R5.2.1
    * Enum of possible number token values. Used to match dice rolls.
    */
   public enum Value{TWO, THREE, FOUR, FIVE, SIX, EIGHT, NINE, TEN, ELEVEN, TWELVE};
   
   
   /**************
    * Contructor
    **************/
    
   /**
    * R1.3.0   R1.3.1   R1.3.4
    * Creates a random linked list of the appropriate number tokens. The SIX and EIGHT tokens
    * are placed to avoid being placed on adjacent tiles. The NumberToken class is a nested 
    * class. The NumberToken constructor is private.
    */ 
   
   public NumberTokens()
   {
      //Create two of each number token (Number class nested below)
      for(int i=0; i<2; ++i)
         {
            add(new NumberToken(Value.THREE));
            add(new NumberToken(Value.FOUR));
            add(new NumberToken(Value.FIVE));
            
            add(new NumberToken(Value.NINE));
            add(new NumberToken(Value.TEN));
            add(new NumberToken(Value.ELEVEN));
         }
         //add one of each number token 
         add(new NumberToken(Value.TWO)); 
         add(new NumberToken(Value.TWELVE));
         
         //Shuffle the current collection
         Collections.shuffle(this);
         
         //Add the SIX and EIGHT number tokens to specific indexes in the list
         add(0, new NumberToken(Value.EIGHT));
         add(4, new NumberToken(Value.SIX));
         add(14, new NumberToken(Value.SIX) );
         add(17, new NumberToken(Value.EIGHT));
   }
   
   /**
    * R1.3.0   R1.3.1
    * 
    * NumberToken class
    *
    * Creates individual NumberToken, which will be added to NumberTokens list
    *
    * @author JEM CSC478B
    */
   public class NumberToken
   {
      /*************
       * attributes
       *************/
       
      //Number image
      private Image image;
      
      //Enum value assinged to the Number Token
      private Value value;
      
      
      /**************
       * Contructor
       **************/
       
      /**
       * R1.3.0   R1.3.1
       * Creates a number token
       *
       * @param   v  Enum value 
       */
      private NumberToken(Value v)
      {
         //Set value attribute to enum value passed
         value = v;
         
         //Path used to create image
         String path = null;
         
         //Choose image based on enum Value passed
         switch(v)
         {
            
            case TWO:
               path = "/images/numbers/Two.png";
               break;
            
            case THREE:
               path = "/images/numbers/Three.png";
               break;
               
            case FOUR:
               path = "/images/numbers/Four.png";
               break;
               
            case FIVE:
               path = "/images/numbers/Five.png";              
               break;
               
            case SIX:
               path = "/images/numbers/Six.png";
               break;
            
            case EIGHT:
               path = "/images/numbers/Eight.png";
               break;
               
            case NINE:
               path = "/images/numbers/Nine.png";
               break;
               
            case TEN:
               path = "/images/numbers/Ten.png";
               break;
               
            case ELEVEN:
               path = "/images/numbers/Eleven.png";
               break;
               
            case TWELVE:
               path = "/images/numbers/Twelve.png";
         }
         try
         {
            image = ImageIO.read(getClass().getResourceAsStream(path));
         }
     
         catch(Exception e)
         {
            //Display error message if image not created
            JOptionPane.showMessageDialog(null, "Can not create image.", "Settler's of Catan", JOptionPane.ERROR_MESSAGE);
         } 
      }
                  
      
      /**********
       * Methods
       **********/  
      
      /**
       * R1.3.1   R1.3.2
       * Returns number token image
       *
       * @return     Image of the NumberToken
       */
      public Image getImage()
      {
         return image;
      }
      
      /**
       * R5.2.1
       * Returns the enum Value associated with the NumberToken
       *
       * @return     The enum Value of the NumberToken
       */
      public Value getValue()
      {
         return value;
      }
   }
}
