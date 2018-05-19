package jem;

import java.awt.Point;
import java.awt.Image;
import java.util.List;
import java.util.LinkedList;
import java.util.Collections;
import javax.imageio.ImageIO;
import java.io.IOException;
import javax.swing.JOptionPane;

/**
 * Tile class
 *
 * Creates resource tiles. Tiles have a resource type, a location to hold the robber,
 * and a number token.
 *
 * @author  JEM CSC478B
 */

public class Tile
{
   /**
    * R1.2.1 through R1.2.5   R5.2.1
    * Enum of possible resources that tiles can have
    */  
   public enum Resource{ORE, BRICK, LUMBER, GRAIN, WOOL}
   
   
   /*************
    * attributes
    *************/
    
   //Location has a point and can hold the robber
   private Location location;
   
   //Image of the tile
   private Image image;
   
   //Enum Resource that the tile produces
   private Resource resource;
   
   //Number token placed on the tile
   private NumberTokens.NumberToken numberToken;
   
   
   /**************
    * Contructors
    **************/
   
   /**
    * R1.2.6   R1.4.0   R5.3.2
    * Creates Desert tile which has no resource 
    */
   public Tile()
   {
      try
      {
         //Try to create tile image
         image = ImageIO.read(getClass().getResourceAsStream("/images/desertTile.png"));
      }
      catch(Exception e)
      {
         //Display error message if image not created
         JOptionPane.showMessageDialog(null, "Can not create Image", "Settlers of Catan", JOptionPane.ERROR_MESSAGE);
      }
   }
   
   /**
    * R1.2.1 through 1.2.5    R5.2.1   R5.3.2
    * Creates resource tile based on the resource passed as a parameter
    *
    * @param   r  Resource produced by the tile
    */
   public Tile(Resource r)
   {
      //Assign the tile resource based on parameter
      resource = r;
      
      try
      { 
         //Create image based on resource parameter
         switch (r)
         {
            case ORE:
               image = ImageIO.read(getClass().getResourceAsStream("/images/mountainTile.png"));
               break;
            
            case BRICK:
               image = ImageIO.read(getClass().getResourceAsStream("/images/hillTile.png"));
               break;
               
            case GRAIN:
               image = ImageIO.read(getClass().getResourceAsStream("/images/fieldTile.png"));
               break;
               
            case LUMBER:
               image = ImageIO.read(getClass().getResourceAsStream("/images/forestTile.png"));
               break;
               
            case WOOL:
               image = ImageIO.read(getClass().getResourceAsStream("/images/pastureTile.png"));
               break; 
         }
      }
      
      catch(Exception e)
      {
         //Display error message if image not created
         JOptionPane.showMessageDialog(null, "Can not create Image", "Settlers of Catan", JOptionPane.ERROR_MESSAGE);
      }
   }
   
   
   /**********
    * Methods
    **********/
    
   /**
    * R1.0.0   R5.2.1   R5.3.2  
    * Assigns a Location object to the tile. The location has a point where the tile will
    * be placed and a spot to hold the robber when placed on the tile.
    *
    * @param   l  Location held by the tile
    */ 
   public void setTileLocation(Location l)
   {
      location = l;
   }
   
   /**
    * R1.0.0   R5.2.1   R5.3.2
    * Returns the attribute tile location
    *
    * @return        Location of the Tile
    */
   public Location getTileLocation()
   {
      return location;
   }
   
   /**
    * R5.2.1
    * Returns the resource associated with the Tile
    *
    * @return        The resource which the Tile pays out
    */
   public Resource getResource()
   {
      return resource;
   }
   
   /**
    * R5.2.1
    * Pays players the tile resource associated with the tile, when the dice roll matches the
    * number token value and the player has a city or settlement touching the tile.
    *
    * @param   player   Player with a settlement/city touching the tile when tile pays resources
    */
   public void payPlayer(Player player)
   {
      player.addResource(resource);
   }
   
   /**
    * R1.3.2   R5.2.1
    * Sets the number token of the tile
    *
    * @param   nt    Number token associated with the tile
    */
   public void setNumberToken(NumberTokens.NumberToken nt)
   {
      numberToken = nt;
   }
   
   /**
    * R1.3.2   R5.2.1
    * Returns the number token of the Tile
    *
    * @return        The NumberToken on the Tile
    */
   public NumberTokens.NumberToken getNumberToken()
   {
      return numberToken;
   }
   
   /**
    * R1.0.0   R1.2.1 through R1.2.6
    * Returns the image of the tile
    *
    * @param   scale    Scale of the images in the game based on screen resolution
    *
    * @return           The image of the Tile
    */
   public Image getImage(double scale)
   {
      return image.getScaledInstance((int)(image.getWidth(null)*scale),(int)(image.getHeight(null)*scale), Image.SCALE_SMOOTH);
   }
   
   /**
    * R1.3.2
    * Returns image of number token placed on the tile
    *
    * @return           Image of the NumberToken
    */
   public Image getNumberTokenImage()
   {
      return numberToken.getImage();
   }
   
   /**
    * R5.2.1
    * Returns the enum value associated with the number tile on the tile
    */
   public NumberTokens.Value getValue()
   {
      return numberToken.getValue();
   }
}