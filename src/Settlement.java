package jem;


import javax.imageio.ImageIO;
import javax.swing.JOptionPane;


/**
 * Settlement class
 *
 * Creates a Settlement, which can be placed on the board.
 * 
 * @author JEM CSC478B
 */
public class Settlement extends GamePiece
{
   /**************
    * Constructor
    **************/
   
   /**
    * R3.1.0
    * Creates a settlement
    *
    * @param   p    This is the player who owns the settlement
    */
   public Settlement(Player p)
   {
      //Set component size;
      setSize(30,30);
      
      //set player attribute, this is the owner of the settlement
      player = p;
      
      try
      {
         //Create image based on PlayerColor of player passed to the constructor.
         switch (player.getPlayerColor())
         {
            case PURPLE:
               image = ImageIO.read(getClass().getResourceAsStream("/images/purpleSettlement.png"));
               break;
            
            case ORANGE:
               image = ImageIO.read(getClass().getResourceAsStream("/images/orangeSettlement.png"));
               break;
               
            case GREEN:
               image = ImageIO.read(getClass().getResourceAsStream("/images/greenSettlement.png"));
               break;
               
            case BLUE:
               image = ImageIO.read(getClass().getResourceAsStream("/images/blueSettlement.png"));
         }
      }
      catch(Exception e)
      {
         //Display error if image not created
         JOptionPane.showMessageDialog(null, "Can not create image.", "Settler's of Catan", JOptionPane.ERROR_MESSAGE);
      }
   }
   
   
   /**************************************************
    * Methods inherited from abstract class GamePiece
    **************************************************/
   
   /**
    * R3.1.0 
    * Adds the city back to the player's inventory if not used after being ready to place
    * on the board by pressing the Build Settlement Button on the GUI.
    */
   @Override
   public void addToPlayerInventory()
   {
      player.addSettlement(this);
   }
   
   /**
    * R3.2.1   R9.1.0
    * Places the settlement on the board, adds it to the location passed as a parameter
    * and adds a point to the player's score
    *
    * @param location   Location of the button that was pressed to add a city.
    */ 
   @Override
   public void place(Location location)
   {
      //Set point to place on board
      setLocation(location.getPoint());
      
      //Set this settlement to be the gamepiece at the location passed as a parameter
      location.setGamePiece(this);
      
      //Give the player a point for placing settlement
      player.addPoint();
      
      //Take resources required to build settlement
      player.removeSettlementResources();
   }
   
   /**
    * R5.2.1 
    * Player who owns settlement recieves one resource if touching resource producing
    * tile on during dice phase.
    * 
    * @param   resource    Resource to be added to the players resources list
    */
   @Override
   public void payResourcesToPlayer(Tile.Resource resource)
   {
      player.addResource(resource);
   }
}
