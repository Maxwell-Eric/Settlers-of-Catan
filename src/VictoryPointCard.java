package jem;

import javax.swing.JOptionPane;

/**
 * VicortyPointCard class
 *
 * Creates a Victory Point development card. When played, the player will earn one vicorty point.
 *
 * @author JEM CSC478B
 */
public class VictoryPointCard extends DevelopmentCard
{
   /***************
    * Constructor
    ***************/

   /**
    * R7.1.2
    * Creates a Victory Point card.
    */
   public VictoryPointCard()
   {}
   
   
   /********************************************************
    * Methods inherited from abstract class DevelopmentCard
    ********************************************************/
   
   /**
    * R7.7.0
    * Gives the player who uses it one point.
    */
   @Override
   public void playCard()
   {
      //Add a point to the Player's score
      player.addPoint();
      
      //Display information message
      JOptionPane.showMessageDialog(null, "You earned a Vicotry Point!", "Settlers of Catan", JOptionPane.INFORMATION_MESSAGE);
   }
   
   /**
    * R7.2.0
    * Gives a string representation of the DevelopmentCard to organize and so the user can select the correct development card.
    *
    * @return     The string representation of the card.
    */
   @Override
   public String toString()
   {
      return "Victory Point";
   }
}