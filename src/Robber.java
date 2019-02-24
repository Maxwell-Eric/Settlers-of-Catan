package jem;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/**
 * Robber class
 *
 * Creates the robber for the board
 *
 * @author  JEM CSC478B 
 */
public class Robber extends GamePiece
{
   /*************
    * attributes
    *************/
    
   //Current location of the Robber
   Location currentLocation;
   
   
   /**************
    * Constructor
    **************/
   
   /**
    * R1.4.0
    * Creates a Robber, which can be placed on the board
    */
   public Robber()
   {
      //Set size of the component
      setSize(30,30);
      try
      {
         //Create image
         image = ImageIO.read(getClass().getResourceAsStream("/images/robber.png"));
      }
      catch(Exception e)
      {
         //Display error message if image not created
         JOptionPane.showMessageDialog(null, "Can't create image", "Settlers of Catan", JOptionPane.ERROR_MESSAGE);
      }
   }
   
   
   /**************************************************
    * Methods inherited from abstract class GamePiece
    **************************************************/
   
   /**
    * R5.2.3   R7.4.0
    * Clears the robber's current location, places the Robber on the board,
    * and adds it to the location passed as a parameter
    *
    * @param   newLocation    Location where the Robber is being moved
    */
  @Override
   public void place(Location newLocation)
   {
      //Clear the robber's current location if it has one (location is null before placed on desert)
      if(currentLocation != null)
         currentLocation.clearGamePiece();
      
      //Set point to place on board
      setLocation(newLocation.getPoint());
      
      //Set the robber to be the gamepiece at the location passed as a parameter
      newLocation.setGamePiece(this);
      
      //Change the current location of the robber
      currentLocation = newLocation;
   } 
}
