package jem;

import java.util.*;
import javax.swing.JOptionPane;


/**
 * MonopolyCard class
 *
 * This class creates a monopoly card. When the card is played, the user selects a resource,
 * and every other player must give all of their resources of that type to the user.
 *
 * @author  JEM CSC478B
 */
public class MonopolyCard extends DevelopmentCard
{
   /*************
    * attributes
    *************/
   
   //List of players who are not the owner of the card
   private List<Player> players;
   
   //Number of resources taken from Players
   private int count = 0;
   
   
   /**************
    * Contructor
    **************/
    
   /**
    * R7.1.4
    * Creates a Monopoly Development Card
    *
    * @param   p  The list of Players in the game
    */
   public MonopolyCard(List<Player> p)
   {
      players = p;
   }
   
  
   /********************************************************
    * Methods inherited from abstract class DevelopmentCard
    ********************************************************/
   
   /**
    * R7.5.0
    * Lets the player choose a resource card, every other player must give all
    * resources of this type to them
    */
   @Override
   public void playCard()
   {
      //Player selects a resource
      Tile.Resource resource = Game.showSelectionBox(null, "Choose a Resource", Tile.Resource.values());
      
      //Create list iterator
      ListIterator<Tile.Resource> iterator;
      
      //For all players
      for(Player p : players)
         
         //Players who are not the owner of the card
         if(player != p)
         {
            //Assign iterator to the player's resources list
            iterator = p.getResources().listIterator();
            
            //While there are resources
            while(iterator.hasNext())
            {
               //If the iterator's current resource is the type chosen
               if(iterator.next() == resource)
               {
                  //Remove it from the current player, and add it to the owner of the card
                  player.addResource(resource);
                  iterator.remove();
                  
                  //Increment number of resources taken
                  ++count;
               }
            }    
         }
   //Display number of resources taken      
   JOptionPane.showMessageDialog(null, "You stole "+count+" "+resource+"!", "Settlers of Catan", JOptionPane.INFORMATION_MESSAGE);
   }
     
   /**
    * R7.2.0
    * Gives a string representation of the object so the user can select the correct development card.
    *
    * @return     String representation of the DevelopmentCard
    */
   @Override
   public String toString()
   {
      return "Monopoly Card";
   }
}