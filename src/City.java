package jem;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;


/**
 * City class
 *
 * Creates a City, which can be placed on the board
 * 
 * @author JEM CSC478B
 */
public class City extends GamePiece
{
   
   /**************
    * Contructor
    **************/
   
   /**
    * R3.1.1
    * Creates a city
    *
    * @param   p    This is the player who owns the city
    */
   public City(Player p)
   {  
      //set component size
      setSize(30,30);
      
      //set player attribute 
      player = p;
      
      try
      {  
         //Create image based on PlayerColor of player passed to the constructor.
         switch (player.getPlayerColor())
         {
            case PURPLE:
               image = ImageIO.read(getClass().getResourceAsStream("/images/purpleCity.png"));
               break;
            
            case ORANGE:
               image = ImageIO.read(getClass().getResourceAsStream("/images/orangeCity.png"));
               break;
               
            case GREEN:
               image = ImageIO.read(getClass().getResourceAsStream("/images/greenCity.png"));
               break;
               
            case BLUE:
               image = ImageIO.read(getClass().getResourceAsStream("/images/blueCity.png"));
         }
      }
      
      //Catch exceptions and display error message
      catch(Exception e)
      {
         JOptionPane.showMessageDialog(null, "Can not create image.", "Settler's of Catan", JOptionPane.ERROR_MESSAGE);
      }
   }
   
   
   /********************************************************
    * Methods inherited from abstract class GamePiece
    ********************************************************/
   
   /**
    * R3.1.1 
    * This method adds the city back to the player's inventory if not used after being ready to place
    * on the board by pressing the Build City Button on the GUI.
    */
   @Override
   public void addToPlayerInventory()
   {
      player.addCity(this);
   }
   
   /**
    * R3.2.1   R9.2.0
    * This method removes the settlement at the current location,places the city on the board,
    * adds it to the location passed as a parameter, and gives the player a point for 
    * upgrading settlement
    *
    *  @param location   Location of the button that was pressed to add a city.
    */ 
   @Override
   public void place(Location location)
   {
      //Set point to place on board
      setLocation(location.getPoint());
      
      //If there is a game piece currently at the location, add it to the owner's inventory(it must be the player's settlement)
      location.getGamePiece().addToPlayerInventory();
      
      //Set this city to be the gamepiece at the location passed as a parameter
      location.setGamePiece(this);
      
      //Give the player a point for upgrading from a Settlment to a City
      player.addPoint();
      
      //Take resources required to build city
      player.removeCityResources();
   }
   
   /**
    * R5.2.1 
    * Player who owns city recieves two resources if touching tile which produces
    * resources on roll.
    * 
    * @param   resource    Resource to be added to the players resources list
    */
   @Override  
   public void payResourcesToPlayer(Tile.Resource resource)
   {
      player.addResource(resource);
      player.addResource(resource);
   }
}
