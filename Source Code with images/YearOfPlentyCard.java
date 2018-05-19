package jem;


/**
 * YearOfPlentyCard class
 * 
 * Creates a Year of Plenty Development Card. When played, gives the player who activates
 * it two resources of their choice.
 * 
 * @author JEM CSC478B
 */
public class YearOfPlentyCard extends DevelopmentCard
{
   /**************
    * Contructor
    **************/

   /**
    * R7.1.5
    * Creates a Year of Plenty Development Card
    */
   public YearOfPlentyCard()
   {}
   
   
   /********************************************************
    * Methods inherited from abstract class DevelopmentCard
    ********************************************************/
   
   /**
    * R7.6.0
    * Adds two resources of the players choosing.
    */
   @Override
   public void playCard()
   {
      for(int i=0; i<2; ++i)
      {
         //Display resource options to choose from
         Tile.Resource resource = Game.showSelectionBox(null, "Choose a Resource!", Tile.Resource.values());
         
         //Add the chosen resource to the players resources
         player.addResource(resource);
      }
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
      return "Year of Plenty";
   }
}