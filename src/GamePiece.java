package jem;

import java.awt.Image;
import java.awt.Point;
import java.awt.Graphics;
import java.awt.Graphics2D;


/**
 * GamePiece abstract class
 *
 * Abstracts the commonalities of each game piece and encapulates 
 * the functionality.
 *
 * @author   JEM CSC478B
 */
public abstract class GamePiece extends javax.swing.JComponent
{
   /*************
    * attributes
    *************/
    
   //image displayed 
   protected Image image;
   
   //Owner of the game piece
   protected Player player;
   
   
   /**********
    * Methods
    **********/
   
   /**
    * R6.0.0   R5.2.3   R7.4.0
    * Places the game piece on the board, adds it to the location passed as a parameter
    *
    * @param location   This is the Location of the button was pressed to add a city.
    */ 
   public abstract void place(Location location);
   
   /**
    * R3.0.0
    * Adds game piece to player inventory if not used
    */
   public void addToPlayerInventory(){}
   
   /**
    * R5.2.1
    * Pays resources to appropriate game pieces during roll phase
    *
    * @param   resource    The resource to be payed to the player
    */
   public void payResourcesToPlayer(Tile.Resource resource){}

   /**
    * R3.0.0   R5.2.1
    * Returns owner of the GamePiece
    *
    * @return        Player who owns the GamePiece
    */
   public Player getPlayer()
   {
      return player;
   }
   
   /**
    * R3.2.1
    * Places the game piece center at location instead of at the top left corner
    *
    * @param   point    The point on the board where the center of the GamePiece should be
    */  
   @Override
   public void setLocation(Point point)
   {
      super.setLocation (point.x-getWidth()/2, point.y-getHeight()/2);
   }
   
   /**
    * R3.2.1
    * Paints the game piece image on the component
    */
   @Override
   protected void paintComponent(Graphics gx)
   {
      Graphics2D gx2D = (Graphics2D) gx;
      gx2D.drawImage(image, 0, 0, null);
   }
}
