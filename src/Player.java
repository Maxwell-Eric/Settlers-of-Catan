package jem;

import java.util.*;
import java.awt.Color;
import javax.swing.JOptionPane;

/**
 * Player class
 *
 * Creates players for the game
 *
 * @author  JEM CSC 478B
 */
public class Player implements Comparable<Player>
{
   /*************
    * Attributes
    *************/
   
   //List of the player's Settlments Cities and Roads
   private List<Settlement> settlements = new LinkedList<Settlement>();
   private List<City> cities = new LinkedList<>();
   private List<Road> roads = new LinkedList<>();
   
   //List of the player's Tile Resources
   private List<Tile.Resource> resources = new LinkedList<>();
   
   //List of the player's Development Cards
   private List<DevelopmentCard> developmentCards = new LinkedList<>();

   //Player's score
   private int score = 0;
   
   //Player's army size
   private int armySize = 0;
   
   
   //**************************
   private int longestRoad = 0;
   
   private boolean hasLongestRoad = false;
   //**********************************
   
   //True if the player has the largest army
   private boolean hasLargestArmy = false;
  
   //Count of resource in the resources list
   private int ore;
   private int brick;
   private int lumber;
   private int grain;
   private int wool;
   
   //**************************************************************
   private int[] numResources = new int[5];
   private int[] mtCost = {4,4,4,4,4};
   //**************************************************************
   
   //Player color to distinguish between players
   private PlayerManager.PlayerColor playerColor;
   
   
   /**************
    * Constructor
    **************/
   
   /**
    * R3.1.0   R3.1.1   R3.1.2   R3.1.3
    * Creates a player
    */
   public Player(PlayerManager.PlayerColor pc)
   {
      //Assign PlayerColor attribute
      playerColor = pc;
      
      //Adds the correct number of Roads, Cities and Settlements to each list
      getGamePieces();
      
      //Add resources for initial setup builds (enough for two Settlements and Roads)
      for(int i=0; i<4; ++i)
      {
         //Add four BRICK and LUMBER resources 
         addResource(Tile.Resource.BRICK);
         addResource(Tile.Resource.LUMBER);
         
         if(i<2 )
         {
            //Add two GRAIN and WOOL resources
            addResource(Tile.Resource.GRAIN);
            addResource(Tile.Resource.WOOL);   
         }
      }     
   }
   
   /*****************
    * Private Method
    *****************/
    
   /**
    * R3.1.0   R3.1.1   R3.1.2
    */
   private void getGamePieces()
   {  
      //Loop 15 times
      for(int i=0; i<15; ++i)
      {
         //Add Road to roads list
         roads.add(new Road(this));
         
         //For first 5 loops
         if(i<5)
         {
            //Add Settlement to settlements list
            settlements.add(new Settlement(this));
            
            //For first 4 loops
            if(i<4)
               
               //Add City to cities list
               cities.add(new City(this));
         }
      }
   }
   
   
   /**********
    * Methods
    **********/
    
   //************************************************************************8
   public int[] getNumResources()
   {
      return numResources;
   }
   
   public int[] getMTCost()
   {
      return mtCost;
   }
   
   public void setMTCost(int index, int cost)
   {
      mtCost[index] = cost;
   }
   //**************************************************************
   
   public int getLongestRoad(){
      return longestRoad;
   }
   
   public void setLongestRoad(int lr){
      longestRoad = lr;
   }
   
   public boolean getHasLongestRoad(){
      return hasLongestRoad;
   }
   
   public void setHasLongestRoadTrue(){
      score += 2;
      hasLongestRoad = true;
      JOptionPane.showMessageDialog(null, "You now have the Longest Road", "Settlers of Catan", JOptionPane.INFORMATION_MESSAGE);
   }
   
   public void setHasLongestRoadFalse(){
      score -=2;
      hasLongestRoad = false;
   }
   //********************************************************************
   
   /**
    * R2.2.0
    * Returns the Color associated with the Player
    *
    * @return        Color associated with the Player
    */
   public Color getColor()
   {
      return playerColor.getColor();
   }
   
   /**
    * R2.2.0
    * Returns the PlayerColor assocatiated with the Player. This is the identifier of the Player (instead of Player1, Player2, etc.)
    *
    * @return        PlayerColor of the Player
    */
   public PlayerManager.PlayerColor getPlayerColor()
   {
      return playerColor;
   }
   /**
    * R2.1.0   R2.21
    * Returns the Player's score
    *
    * @return        The score of the Player
    */
   public int getScore()
   {
      return score;
   }  
   
   /**
    * R6.0.0
    * Removes and returns the first Settlement in the  Player's settlements list so it may be added to the board
    *
    * @return        The first Settlement in the settlements list
    */
   public Settlement getSettlement()
   {
      return settlements.remove(0);
   }
   
   /**
    * R3.1.3   
    * Returns the number of Settlements available to be placed
    *
    * @return        The number of Settlements available to the Player
    */
   public int getNumberOfSettlements()
   {
      return settlements.size();
   }
   
   /**
    * R6.0.0
    * Removes and returns the first City in the  Player's cities list so it may be added to the board
    *
    * @return        The first City in the settlements list
    */
   public City getCity()
   {
      return cities.remove(0);
   }
   
   /**
    * R3.1.3   
    * Returns the number of Cities available to be placed
    *
    * @return        The number of Cities available to the Player
    */
   public int getNumberOfCities()
   {
      return cities.size();
   }  
   
   /**
    * R6.0.0
    * Removes and returns the first Road in the  Player's roads list so it may be added to the board
    *
    * @return        The first Road in the settlements list
    */
   public Road getRoad()
   {
      return roads.remove(0);
   }
   
   /**
    * R3.1.3   
    * Returns the number of Roads available to be placed
    *
    * @return        The number of Roads available to the Player
    */
   public int getNumberOfRoads()
   {
      return roads.size();
   }
   
   /**
    * R3.1.0
    * Adds the Settlement to the Player's settlements list
    *
    * @param   settlement  The Settlement to be added to the list.
    */
   public void addSettlement(Settlement settlement)
   {
      settlements.add(settlement);
   }
   
   /**
    * R3.1.2
    * Adds the Road to the Player's roads list
    *
    * @param   road     The Road to be added to the list.
    */
   public void addRoad(Road road)
   {
      roads.add(road);
   }
   
   /**
    * R3.1.1
    * Adds the City to the Player's citiess list
    *
    * @param   city     The City to be added to the list.
    */
   public void addCity(City city)
   {
      cities.add(city);
   }
   
   /**
    * R9.0.0
    * Adds a point to the Player's score
    */
   public void addPoint()
   {
      ++score;
   }
   
   /**
    * R7.4.0
    * Increases the Player's army size by one
    */
   public void addKnight()
   {
      ++armySize;
   }
   
   /**
    * R9.5.0   R9.5.1
    * Returns the Player's army size (number of KnightDevelopmentCards played)
    *
    * @return     the Player's armySize attribute
    */
   public int getArmySize()
   {
      return armySize;
   }
   
   /**
    * R9.5.0   R9.5.1
    * Returns a boolean which tells if the Player has the largest army
    *
    * @return        the attribute hasLargestArmy, which is a boolean
    */
   public boolean hasLargestArmy()
   {
      return hasLargestArmy;
   }
   
   /**
    * R9.5.0   R9.5.1
    * Sets the attribute hasLargesArmy to true and adds two points to the Player's score
    */
   public void setLargestArmyTrue()
   {
      hasLargestArmy = true;
      score += 2;
   }
   
   /**
    * R9.5.1
    * Sets the attribute hasLargesArmy to false and subtracts two points from the Player's score
    */
   public void setLargestArmyFalse()
   {
      hasLargestArmy = false;
      score -= 2;
   }
   
   /**
    * R8.1.0
    * Returns the Player's list of resources
    *
    * @return        resources, the Player's list of resources.
    */
   public List<Tile.Resource> getResources()
   {
      return resources;
   }
   
   /**
    * R5.2.2
    * Returns the number of resources in the Player's resources list
    *
    * @return        The number of resources the Player has
    */
   public int getResourcesSize()
   {
      return resources.size();
   }
   
   /**
    * R4.1.5   R5.2.1   R5.2.4   R7.5.0   R7.6.0   R8.1.0
    * Adds the resource to the Player's resources list
    *
    * @param   r     The resource to be added to the resources list
    */
   public void addResource(Tile.Resource r)
   {
      resources.add(r);
   }
   
   /**
    * R5.2.2   R5.2.4   R6.0.0   R7.3.0   R8.1.0
    * Removes and returns the Resource from the players resources list
    *
    * @param      r     The resource to be removed from the resources list
    * @return           The resource that was removed from the resources list
    */
   public Tile.Resource removeResource(Tile.Resource r)
   {
      resources.remove(r);
      return r;
   }
   
   /**
    * R5.2.4   R7.4.0
    * Removes a random resource from the Player's resource list and returns it
    *
    * @return        A random resource from the Player's resource list
    */ 
   public Tile.Resource getRandomResource()
   {
      //shuffle the list to randomize the resources
      Collections.shuffle(resources);
      return resources.remove(0);
   }
   
   /**
    * R6.1.0
    * Returns true if the Player has enough resources to build a Road
    *
    * @return     boolean indicating whether the Player can afford a Road placement
    */
   public boolean hasRoadResources()
   {
      if(brick > 0 && lumber > 0)
         return true;
     
      else 
         return false;
   }
   
   /**
    * R6.1.0
    * Removes the resources required for a Road placement
    */
   public void removeRoadResources()
   {
      resources.remove(Tile.Resource.BRICK);
      resources.remove(Tile.Resource.LUMBER);
   }
   
   /**
    * R6.1.1
    * Returns true if the Player has enough resources to build a Settlement
    *
    * @return     boolean indicating whether the Player can afford a Settlement placement
    */
   public boolean hasSettlementResources()
   {
      if(brick > 0 && lumber > 0 && grain > 0 && wool > 0)
         return true;
         
      else
         return false;
   }
   
   /**
    * R6.1.1
    * Removes the resources required for a Settlement placement
    */
   public void removeSettlementResources()
   {
      resources.remove(Tile.Resource.BRICK);
      resources.remove(Tile.Resource.LUMBER);
      resources.remove(Tile.Resource.GRAIN);
      resources.remove(Tile.Resource.WOOL);
   }
   
   /**
    * R6.1.2
    * Returns true if the Player has enough resources to build a City
    *
    * @return     boolean indicating whether the Player can afford a City placement
    */
   public boolean hasCityResources()
   {
      if(ore > 2 && grain > 1)
         return true;
        
      else
         return false;
   }
   
   /**
    * R6.1.2
    * Removes the resources required for a City placement
    */
   public void removeCityResources()
   {
      resources.remove(Tile.Resource.GRAIN);
      resources.remove(Tile.Resource.GRAIN);
      resources.remove(Tile.Resource.ORE);
      resources.remove(Tile.Resource.ORE);
      resources.remove(Tile.Resource.ORE);
   }
   
   /**
    * R7.3.0
    * Returns true if the Player has enough resources to buy a ResourceCard
    *
    * @return     boolean indicating whether the Player can afford a ResourceCard
    */
   public boolean canAffordDevelopmentCard()
   {
      if(ore >0 && grain > 0 && wool >0)
         return true;
         
      else
         return false;
   }
   
   /**
    * R7.3.0
    * Removes the resources required for a City placement
    */
   public void payForDevelopmentCard()
   {
      resources.remove(Tile.Resource.ORE);
      resources.remove(Tile.Resource.GRAIN);
      resources.remove(Tile.Resource.WOOL);
   }
   
   /**
    * R7.3.0
    * Add the a DevelopmentCard to the Player's list
    *
    * @param   dc    The DevelopmentCard to be added
    */
   public void addDevelopmentCard(DevelopmentCard dc)
   {
      developmentCards.add(dc);
   }
   
   /**
    * R7.2.0
    * Returns the number of DevelopmentCards the Player has
    *
    * @return     the number of DevelopmentCards owned
    */
   public int getNumberOfDevelopmentCards()
   {
      return developmentCards.size();
   }
   
   /**
    * R7.2.0   
    * Creates a selection box with the Player's DevelopmentCards. Click one one to use it, or close box to cancel
    */
   public void chooseDevelopmentCard()
   {
      Collections.sort(developmentCards);
      int index = JOptionPane.showOptionDialog(null, "Choose a Development Card", "Settlers of Catan", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, developmentCards.toArray(),null);
      
      if(index != -1)
         developmentCards.remove(index).playCard();
   }
   
   /**
    * R1.1.0  R1.1.1   R1.1.2
    * Creates a String representation of the Player 
    */
   public String toString()
   {
      return playerColor.toString();
   }
   
   /**
    * R1.1.0   R1.1.1   R1.1.2
    * Used to organize Players and so they can be used as Comparable Objects
    */
   public int compareTo(Player player)
   {
      return this.getPlayerColor().compareTo(player.getPlayerColor());
   }
   
   /**
    * R2.2.0   R2.2.1   R2.2.2   R3.1.3
    * Displays the Player's information, such as number of GamePieces, resources, developmencards and army size
    *
    * @param   scale    This is the scale of the native resolution so the GUI can be viewed properly
    */
   public String getInformation(double scale)
   {
      return "<html><p style=font-size:"+24*scale+"px; text-align:center>"+playerColor+" Player<br>Score: "+score+"</p><br><br><p style=font-size:"+12*scale+"px>Ore: "+ore+"<br>Brick: "+brick+"<br>Lumber: "+lumber+
             "<br>Grain: "+grain+"<br>Wool: "+wool+"<br><br></p><p style=font-size:"+16*scale+"px>Settlements Remaining: "+settlements.size()+"<br>Cities Remaining: "+cities.size()+
             "<br>Road Remaining: "+roads.size()+"<br>Development Cards: "+developmentCards.size()+"<br>Army Size: "+armySize+"<br>Largest Army: "+hasLargestArmy+"<br>Longest Road: "+longestRoad+"<br>Have Longest Road: "+hasLongestRoad;
   }
   
   /**
    * R2.2.2
    * Updates the Player's resources to keep the GUI accurate
    */
   public void updateResourcesInfo()
   {
      //Set each attribute to zero
      ore = 0; brick = 0; lumber = 0; grain = 0; wool = 0;
     
      //for every Resource in the resources list
      for(Tile.Resource resource : resources)
      {
         //Determine action based on the current Resource
         switch (resource)
         {
            //If the resource is ore, increment the count by 1
            case ORE:
               ore += 1;
               break;
            
            //If the resource is brick, increment the count by 1
            case BRICK:
               brick += 1;
               break;
            
            //If the resource is lumber, increment the count by 1
            case LUMBER:
               lumber += 1;
               break;
            
            //If the resource is gain, increment the count by 1
            case GRAIN:
               grain += 1;
               break;
         
            //If the resource is wool, increment the count by 1
            case WOOL:
               wool += 1;
         }
      }
      numResources[0]= ore; numResources[1]= brick; numResources[2]= lumber; numResources[3]= grain; numResources[4]=wool;         
   }
}
