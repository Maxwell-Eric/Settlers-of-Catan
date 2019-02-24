package jem;

/**
 * LocationButton Class
 *
 * Creates a button with a Location where a GamePiece can be placed. When pressed, the current game piece of the 
 * board is placed at the location.
 *
 * @author  JEM CSC 478B
 */
public class LocationButton extends javax.swing.JButton
{
   /*************
    * Attributes
    *************/
   
   //Location to set game piece
   Location location;
   
   
   /**************
    * Constructor
    **************/
    
   /**
    * R6.4.0
    * Creates a JButton at a Location where a GamePiece may be placed.
    */
   public LocationButton()
   {}
    
   
   /**********
    * Methods
    **********/
   
   /**
    * R6.4.0
    * Sets the Location of the LocationButton
    *
    * @param   l  Location of the LocationButton
    */
   public void setButtonLocation(Location l)
   {
      location = l;
   }
   
   /**
    * R6.4.0
    * Returns the Location attribute of the button
    *
    * @return     Location of the Button
    */
   public Location getButtonLocation()
   {
      return location;
   }
}