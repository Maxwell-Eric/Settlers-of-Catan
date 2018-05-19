package jem;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import java.io.IOException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.util.List;


/**
 * Road class
 *
 * The class creates a road, which can be placed on the board.
 * 
 * @author JEM CSC478B
 */
public class Road extends GamePiece
{
   /************
    * Attribute
    ************/
    
   //Index of the roadLocation where the Road is placed (used to rotate the Road)
   private int roadLocationIndex;
   
   
   /**************
    * Contructor
    **************/
   
   /**
    * R3.1.2
    * Creates a road
    *
    * @param   p    This is the player who owns the road
    */

   
   public Road(Player p)
   {
      //Set component size
      setSize(100, 100);
      
      //Set player attruibute, this is the owner of the road
      player = p;
      
      try
      {
         //Create image based on PlayerColor of player passed to the constructor.
         switch (player.getPlayerColor())
         {
            case PURPLE:
               image = ImageIO.read(getClass().getResourceAsStream("/images/purpleRoad.png"));
               break;
            
            case ORANGE:
               image = ImageIO.read(getClass().getResourceAsStream("/images/orangeRoad.png"));
               break;
               
            case GREEN:
               image = ImageIO.read(getClass().getResourceAsStream("/images/greenRoad.png"));
               break;
               
            case BLUE:
               image = ImageIO.read(getClass().getResourceAsStream("/images/blueRoad.png"));
         }
      }
      
      catch(Exception e)
      {
         //Display error message if image not created
         JOptionPane.showMessageDialog(null, "Can not create image.", "Settler's of Catan", JOptionPane.ERROR_MESSAGE);
      }
   }
   
   
   /********************************************************
    * Methods inherited from abstract class GamePiece
    ********************************************************/
    
   /**
    * R3.1.2
    * Adds the road back to the player's inventory if not used after being ready to place
    * on the board by pressing the Build Road Button on the GUI.
    */
   @Override
   public void addToPlayerInventory()
   {
      player.addRoad(this);
   }
   
   /**
    * R3.2.1
    * Places the road on the board, and adds it to the location passed as a parameter
    *
    * @param   location Location of the button that was pressed to add road
    */
   @Override
   public void place(Location location)
   {
      //Set road location index, which rotates road to align with tile edge
      roadLocationIndex = location.getIndex();
      
      //Set this road to be the gamepiece at the location passed as a parameter
      setLocation(location.getPoint());
      
      //Set this road to be the gamepiece at the location passed as a parameter
      location.setGamePiece(this);
      
      //Take resources required for road
      player.removeRoadResources();
   }
   
   
   /****************************
    * Method to Paint Component
    ****************************/
    
   /**
    * 3.2.3
    * Rotates road image to align with tile edge depending on road location index
    */
   @Override
   protected void paintComponent(Graphics gx) 
   { 
      Graphics2D gx2D = (Graphics2D) gx;
      
      for(int i=0; i<12; ++i)
      {
         //If the road is placed at one of these indexes
         if(roadLocationIndex == 1 + 4*i || roadLocationIndex == 2 + 4*i)
  
            //Rotate -60 degrees
            gx2D.rotate(-Math.PI/3, getWidth()/2, getHeight()/2); 
       
         //If the road is placed at one of these indexes
         else if(roadLocationIndex == 4*i || roadLocationIndex == 3 + 4*i)
         
            //Rotate 60 degrees
            gx2D.rotate(Math.PI/3, 50, 50);
       }
       
       //Draw image in center of the component
       gx2D.drawImage(image, 46, 15, null);
    }
}
