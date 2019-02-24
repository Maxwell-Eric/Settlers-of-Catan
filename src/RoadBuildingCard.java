package jem;

import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.Timer;

/**
 * RoadBuildingCard class
 *
 * Creates a Road Building development card. When played, it allows the player to place
 * two roads on the board free of resource charges.
 *
 * @author  JEM CSC478B
 */
public class RoadBuildingCard extends DevelopmentCard
{
   /*************
    * attributes
    *************/
   
   //GUI of the game, used to hide buttons so player must complete the DevelopmentCard action
   private GUI gui;
   
   //Timer sends action event every .2 seconds to see if a road has been placed by the player.
   private Timer timer;
   
   //counts the number of times a road has been placed, used to make sure two roads get placed.
   private int count;
   
   //board where the roads will be placed.
   private Board board;
   
   
   /***************
    * Constructor
    ***************/
    
   /**
    * R7.1.3
    * Creates a Road Building development card.
    *
    * @param g  GUI that created the card 
    */
   public RoadBuildingCard(GUI g)
   {
      //assign paramter to attribute
      gui = g;
      
      //Assign GUI board to attruibute
      board = gui.getBoard();
   }
   
   
   /********************************************************
    * Methods inherited from abstract class DevelopmentCard
    ********************************************************/
   
   /**
    * R7.8.0
    * Forces the player to place two roads on the board
    */
   @Override
   public void playCard()
   {
      //Display information message
      JOptionPane.showMessageDialog(null, "Place 2 roads!", "Settlers of Catan", JOptionPane.INFORMATION_MESSAGE);
      
      //Hide the gui buttons so the player must follow sequence   
      gui.hideButtons();
      
      //Display leagle road locations for player
      board.displayRoadLocations(player);
      
      //Add resource cost of road, as the road should be placed free of charge
      player.addResource(Tile.Resource.BRICK);
      player.addResource(Tile.Resource.LUMBER);
      
      //Set a player's road ready to place on board
      board.setGamePieceReadyToPlace(player.getRoad());
      
      
      //Create actionlistener that will be attached to the timer. Anonymous inner class
      ActionListener checkForRoadPlaced = new ActionListener() 
      {
         /**
          * R7.8.0
          * Gets a new road for placement on the board after first road placed.
          * Turns off timer and replaces buttons on GUI after 2nd road placed.
          */
         public void actionPerformed(ActionEvent e) 
         {
            //Check to see if the player has placed a road   
            if(board.getGamePieceReadyToPlace() == null)
            {
               //If this is the 2nd road placed
               if(count == 1)
               {
                  //Stop timer with ActionListener
                  timer.stop();
                  
                  //Restore buttons on GUI to resume game
                  gui.restoreButtons();
                  gui.getParent().repaint();
               }
               
               //If this is the first road placed
               else
               {
                  //Increment count
                  ++count;
                  
                  //Display buttons on leagle road placement locations
                  board.displayRoadLocations(player);
                  
                  //Paint to update graphics
                  gui.getParent().repaint();
                  
                  //Add the resource cost of a road, it is placed free of charge
                  player.addResource(Tile.Resource.BRICK);
                  player.addResource(Tile.Resource.LUMBER);
                  
                  //Set a player road ready to place on board
                  board.setGamePieceReadyToPlace(player.getRoad());
               }
            }
         }
            
      };
      
      //Create timer object with checkForRoadPlaced ActionListener attached
      timer = new Timer(200, checkForRoadPlaced);
      
      //Start timer, calls checkForRoadPlaced every .2s.
      timer.start();
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
      return "Road Buiding";
   }
}