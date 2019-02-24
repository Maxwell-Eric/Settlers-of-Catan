package jem;

/**
 * R7.0.0
 *
 * DevelopmentCard abstract class
 *
 * Abstracts commonalities and encapsulates the functionality of development cards
 *
 * @author  JEM CSC478B
 */
public abstract class DevelopmentCard implements Comparable<DevelopmentCard>
{
   /*************
    * attributes
    *************/
    
   //Player who owns the car
   protected Player player;
   
   /*******************
    * Abstract Methods
    *******************/
    
    /**
    * R7.4.0 through R7.8.0
    * Plays card so the owner can recieve the effects.
    */
   public abstract void playCard();
   
   /**
    * R7.2.0
    * Gives a string representation of the object so the user can select the correct development card. 
    */
   @Override
   public abstract String toString();
   
 
   /***********
    * Methods
    ***********/
    
   /**
    * R7.3.1
    * Assigns player attribute so the playCard() method knows which player
    * is playing the card.
    *
    * @param   p  player who owns the card
    */
   public void setPlayer(Player p)
   {
      player = p;
   }
   
   
   /**
    * R7.2.0
    * Gives the ability to sort development cards for organization purposes
    *
    * @param   dc    The development card being compared
    *
    * @return        integer telling the order of the development cards
    */
   public int compareTo(DevelopmentCard dc)
   {
      return this.toString().compareTo(dc.toString());
   }
}