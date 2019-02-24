package jem;

import javax.swing.JOptionPane;

/**
 * KnightCard class
 *
 * Creates a Knight Card, which gives the player the ablity to move the robber.
 * The player also get an increase of one to their army size.
 *
 * @author  JEM CSC478B
 */
public class KnightCard extends DevelopmentCard
{
   /*************
    * attributes
    *************/
    
   //Board which the robber belongs to
   private Board board;
   
   
   /**************
    * Contructor
    **************/
  
   /**
    * R7.1.1
    * Creates a Knight development card
    *
    * @param   b  Board that owns robber
    */
   public KnightCard(Board b)
   {
      //Assign attribute
      board = b;
   }
   
   
   /********************************************************
    * Methods inherited from abstract class DevelopmentCard
    ********************************************************/
   
   /**
    * R7.4.0
    * Lets the player move the robber to a new position
    */
   @Override
   public void playCard()
   {
      //Display information message
      JOptionPane.showMessageDialog(null, "Move the Robber!", "Settlers of Catan", JOptionPane.INFORMATION_MESSAGE);
         
      //Set robber ready to place on board
      board.setGamePieceReadyToPlaceRobber();
      
      //Create buttons where the robber can be placed
      board.displayTileLocations();
      
      //Incriment the player's army size 
      player.addKnight();
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
      return "Knight Card";
   }
}