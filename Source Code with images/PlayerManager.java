package jem;

import java.util.List;
import java.util.LinkedList;
import java.awt.Color;
import javax.swing.JOptionPane;

/**
 * PlayerManager class
 *
 * Creates the PlayerManger which manages the players in the game
 *
 * @author  JEM CSC478B
 */
public class PlayerManager
{
   /*************
    * Attributes
    *************/
    
   //List of the players in the game
   private List<Player> players = new LinkedList<>();
   
   //Number of players 
   private int numberOfPlayers;
   
   //Index of the current Player
   private int currentPlayerIndex = 0;
   
   
   /**************
    * Constructor
    **************/
    
   /**
    * R1.1.0
    * Creates the PlayerManger prompts user for the number of Players and creates Player list
    */
   public PlayerManager()
   {
      //Prompt user for number of Players
      getNumberOfPlayers();
      
      //Create the list of Players
      addPlayers();
   }
   
   
   /**********
    * Methods
    **********/
    
   /**
    * R2.1.0   R2.2.0   R2.2.1
    * Returns the current Player
    *
    * @return        current Player
    */
   public Player getCurrentPlayer()
   {
      return players.get(currentPlayerIndex);
   }
   
   /**
    * R2.4.5
    * Changes the current Player to the next player on the list
    *
    * @return     The next Player in the list
    */
   public Player getNextPlayer()
   {
      //Change current Player index
      currentPlayerIndex = (currentPlayerIndex + 1)%numberOfPlayers;
      
      //Return the new current player
      return players.get(currentPlayerIndex);
   }
   
   /**
    * R1.1.0 through R1.1.2
    * Creates a list of Players which has numberOfPlayers Players
    */
   private void addPlayers()
   {
      //Add a player until the list contains the correct number of Players
      for(int i=0; i<numberOfPlayers; ++i)
         players.add(new Player(PlayerColor.values()[i]));
   }
   
   /**
    * R4.1.0   R7.5.0   R8.1.0
    * Returnst the list of Players in the game
    *
    * @return     list of Players in the game
    */
   public List<Player> getPlayers()
   {
      return players;
   }
   
   /**
    * R1.0.0 through R1.1.2
    * Prompts the user for the number of players and sets attribute
    */
   private void getNumberOfPlayers()
   {
      
      Integer[] possibleNumberOfPlayers = {2, 3, 4};
   
      int itemIndex = JOptionPane.showOptionDialog(null, "Select Number of Players", "Settlers of Catan", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,possibleNumberOfPlayers,null);
      
      //If the user closes the window without a selection, the loop repeats
      if(itemIndex == -1)
         System.exit(0);
      
      //Return the item that the user selected
      numberOfPlayers = possibleNumberOfPlayers[itemIndex];
   }
    
   /**
    * R5.2.2
    * Checks the resource size of each player on a dice roll of 7
    * If a Player has more than 7 cards, they must remove half of their resources (rounded down)
    *
    * @param   scale    Scales the window requiring the Player to remove resources
    */
   public void checkResourcesSize(double scale)
   {
      //For each Player in the game
      for(Player player : players)
      {
         //Get number of resources for Player
         int numberOfResources = player.getResourcesSize();
         
         //If the Player has more than 7 resources
         if(numberOfResources > 7)
         {
            //Determine the number of resources the player must remove (half rounded down)
            int numberOfResourcesToRemove = numberOfResources/2;
            
            //Repeat loop for each resource that must be removed
            for(int i=0; i < numberOfResourcesToRemove; ++i)
            {
               //Update player resources information
               player.updateResourcesInfo();
               
               //Display SelectionBox which forces player to choose one of their Resources
               Tile.Resource resourceChoice = Game.showSelectionBox(null, player.getInformation(scale)+"<br><br>You have too many resources!<br>You must remove "+(numberOfResourcesToRemove-i)+" more resource(s)!<br>Choose Resource to Remove!", player.getResources());
               
               //Remove selected Resource from player
               player.getResources().remove(resourceChoice);
            }
         }
      }
   }
   
   /**
    * R9.6.0
    * Checks to see if the current Player's score is 10 or greater
    */
   public void checkForVictory()
   {
      //If the current Player's score is greater than 9
      if(getCurrentPlayer().getScore() > 9)
      {  
         //Display victory message
         JOptionPane.showMessageDialog(null, getCurrentPlayer().toString()+" PLAYER WINS!!", "Setters of Catan", JOptionPane.INFORMATION_MESSAGE);
         
         //Close Game
         System.exit(0);
      }      
   }
   
   /**
    * R9.5.0   R9.5.1
    * Checks to see if the current Player has the largest army
    *
    * @param   player   current Player
    */
   public void checkLargestArmy(Player player)
   {
      //If the Player's army size is 3 or more
      if(player.getArmySize() > 2 && !player.hasLargestArmy())
      {
         //For each player in the game
         for(Player p : players)
         
            //If the Player is not the current Player
            if(player != p)
            {
               //If the non current Player has the largest army
               if(p.hasLargestArmy())
               {
                  //Check to see if the current Player has a larger army
                  if(player.getArmySize() > p.getArmySize())
                  {
                     //If so, the current Player now has the larges army
                     player.setLargestArmyTrue();
                     
                     //Remove largest army status and points from Player who had larges army
                     p.setLargestArmyFalse();
                     return;
                  }
                  
                  //If the non current Player has a larger army
                  else
                     //End method
                     return;
               }  
            }
            //If no other Player has the larges army status, the current Player now has largest army
            player.setLargestArmyTrue();
      }
   }
   
   public void checkLongestRoad(){
      for(Player player : players){
         if(player.getLongestRoad() > 4 && player.getHasLongestRoad() == false){
            for(Player p : players){
               if(p.getHasLongestRoad() == true){
                  if(player.getLongestRoad() > p.getLongestRoad()){
                     player.setHasLongestRoadTrue();
                     p.setHasLongestRoadFalse();
                     return;
                  }
                  else
                     return;
               }
            }
            player.setHasLongestRoadTrue();
         } 
      }
   }
   
   /**
    * R1.1.0
    * 
    * PlayerColor enum
    *
    * Enum which has a distinct Color for each player.
    */            
   public enum PlayerColor 
   {
      //Each enum has a Color associated with it
      PURPLE(new Color(250, 50, 250)), ORANGE(new Color(250, 150, 50)), GREEN(new Color(0, 255, 65)), BLUE(new Color(0, 150, 250));
         
         /************
          * Attribute
          ************/    
        
         //Color associated with each PlayerColor enum               
         private Color color;
         
         /**************
          * Constructor
          **************/
          
         /**
          * R1.1.0 through R1.1.2
          * Creates a PlayerColor for each Player
          *
          * @param   c     Color associated with the corresponding PlayerColor enum
          */               
         PlayerColor(Color c)
         {
            color = c;
         }
         
         /**
          * R1.1.0 through R1.1.2
          * Returns the Color associated with the PlayerColor enum
          *
          * @return        Returns the Color
          */             
         public Color getColor()
         {
            return color;
         }
   }
}