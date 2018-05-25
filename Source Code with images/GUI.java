package jem;

import java.util.*;
import javax.swing.border.BevelBorder;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * R2.0.0
 *
 * GUI class
 * 
 * Displays the current Player's information and provides buttons with which the user may interact. There is a nested class that implements
 * ActonListener called GUIHandler which abstracts the commonalities of the buttons. Also, there is a nested class for each button which
 * extends GUIHandler, this gives each button its own ActionListener.
 *
 * @author  JEM CSC 478B
 */
public class GUI extends JPanel
{
   /*************
    * Attributes
    *************/
   
   //list of buttons for the user to interact with
   private List<JButton> buttons = new LinkedList<>();
   
   //DevelopmentCard deck
   private List<DevelopmentCard> developmentCards = new LinkedList<>();
   
   //PlayerManager controls the Players in the game
   private PlayerManager playerManager = new PlayerManager();
   
   //The Board
   private Board board;
   
   //The current Player
   private Player currentPlayer;
  
   //Pair of Dice to roll
   private Dice dice = new Dice();
  
   //Label which contains the current Player's information
   private JLabel playerLabel = new JLabel();
   
   //Label which displays the building cost information
   private JLabel buildingCostLabel = new JLabel();  
   
   //Scale of the native window
   private double scale;
   
   
   /***************
    * Contstructor
    ***************/
   
   /**
    * R2.0.0
    * Creates a gui for the Players to interact with
    *
    * @param   scale    scales the GUI to the native resolution
    */
   public GUI(double scale)
   {
      //Set the scale attribute  
      this.scale = scale;
      
      //Create Board and set attribute
      board = new Board(scale);
      
      //Create the DevelopmentCard deck
      populateDevelopmentCardDeck();
      
      //Set the layout to null
      setLayout(null);
      
      //Set the size of the GUI panel
      setSize((int)(500*scale),(int)(700*scale));
      
      //Place the GUI in the Game JFrame
      setLocation((int)(1000*scale),(int)(100*scale));
      
      //Create a border for visual purposes
      setBorder(new BevelBorder(BevelBorder.RAISED));
      
      //Place the playerLabel in the GUI
      playerLabel.setBounds((int)(190*scale), 0, (int)(300*scale), (int)(500*scale));
      playerLabel.setHorizontalAlignment(JLabel.CENTER);
      add(playerLabel);
      
      //Place the buildingCostLabel in the GUI and set its text
      buildingCostLabel.setBounds(0, (int)(500*scale), (int)(500*scale), (int)(200*scale));
      buildingCostLabel.setHorizontalAlignment(JLabel.CENTER);
      add(buildingCostLabel);
      buildingCostLabel.setText("<html><p style=font-size:"+18*scale+"px; text-align:center><u>Build Costs</u>"+
                                 "<p style=font-size:"+14*scale+"px>Road: 1 Brick, 1 Lumber<br>"+
                                 "Settlement: 1 Brick, 1 Lumber, 1 Grain, 1 Wool<br>City: 2 Grain, 3 Ore<br>"+
                                 "Development Card: 1 Wool, 1 Grain, 1 Ore");           
   }
   
   
   /******************
    * Private Methods
    ******************/
   
   /**
    * R7.0.0   R7.1.0 through R7.1.5
    * Creates the deck of DevelopmentCards
    */
   private void populateDevelopmentCardDeck()
   {
      for(int i=0; i<14; ++i)
      {
         //Create 14 KnightCards
         developmentCards.add(new KnightCard(this.board));
         
         if(i<5)
            
            //Create 5 VicortyPointCards
            developmentCards.add(new VictoryPointCard());
            
         //Create two MonopolyCards, RoadBuildingCards and YearOfPlentyCards
         if(i<2)
         {
            developmentCards.add(new MonopolyCard(playerManager.getPlayers()));
            developmentCards.add(new RoadBuildingCard(this));
            developmentCards.add(new YearOfPlentyCard());
         }
      }
      
      //Shuffle the deck to randomize the cards
      Collections.shuffle(developmentCards);
   }
   
   /**
    * R4.2.5   R3.2.0   R7.0.0   R8.0.0
    * Creates a an ActionListener for each button
    *
    * @return     list of GUIHandlers
    */
   private List<GUIHandler> getHandlers()
   {
      //Create a list of GUIHandlers to hold ActionListeners GUI buttons
      List<GUIHandler> handlers = new LinkedList<>();
      
      //Add a GUIHandler to the list for each button
      handlers.add(new RoadHandler());
      handlers.add(new SettlementHandler());
      handlers.add(new CityHandler());
      handlers.add(new BuyDevelopmentCardHandler());
      handlers.add(new PlayDevelopmentCardHandler());
      handlers.add(new TradeHandler());
      handlers.add(new MaratimeTradeHandler());
      handlers.add(new EndTurnHandler());
      
      //return list
      return handlers;
   }
   
   
   /**********
    * Methods
    **********/
    
    
   /**
    * R1.0.0   R7.8.0
    * Returns the GUI's Board attribute
    *
    * @return        The Board associated with the GUI
    */
   public Board getBoard()
   {
      return board;
   }
   
   /**
    * R5.2.4
    * Returns the GUI's PlayerManager attribute
    */
   public PlayerManager getPlayerManager()
   {
      return playerManager;
   }
    
   /**
    * R2.4.0 through 2.4.6
    * Builds the buttons of the GUI that the user interacts with
    */
   public void buildButtons()
   {
      //Create list of ActionListeners for each button
      List<GUIHandler> handlers = getHandlers();
      
      //Create integer to help place buttons correctly
      int i=0;
      
      //For each handler
      for(GUIHandler handler : handlers)
      {  
         //Create a button with the each button handler toString() method as text
         JButton button = new JButton(handler.toString());
         
         //Place the button in the GUI
         button.setBounds((int)(25*scale), (int)((25+(i++)*50)*scale), (int)(150*scale), (int)(50*scale));
         
         //Add the GUIHandler as the ActionListener
         button.addActionListener(handler);
         
         //Add the button to the buttons list
         buttons.add(button);
         
         //Add the button to the GUI
         add(button);
      }
   }
   
   /**
    * R5.1.0
    * Rolls the Dice and takes appropriate actions based on the roll
    */
   public void rollDice()
   {
      //Displays a message indicating it is time to roll the Dice, roll occurs when OK is pressed
      JOptionPane.showMessageDialog(this, "Roll The Dice!", "Settlers of Catan", JOptionPane.INFORMATION_MESSAGE);
      
      //Roll the dice
      dice.roll();
      
      //Display roll results
      JOptionPane.showMessageDialog(this, "You rolled "+dice.getRoll()+".", "Settlers of Catan", JOptionPane.INFORMATION_MESSAGE);
      
      //If a 7 is rolled
      if(dice.getValue() == null)
      {
         //Check the number of resources each player has and discard half rounded down if they have more than 7
         playerManager.checkResourcesSize(scale);
         
         //Set the Robber as the next GamePiece to be placed on the Board
         board.setGamePieceReadyToPlaceRobber();
         
         //Display Tile Locations where the Robber may be placed
         board.displayTileLocations();
      }
      
      else
         //If a 7 is not rolled, pay the Cities and Settlments on the corners of the Tile
         board.payResourceOnRoll(dice.getValue());
         
      //Update player information
      updatePlayerInfo();
   } 
   
   /**
    * R7.8.0
    * Hides the GUI buttons so the Player must finish completing the RoadBuilding sequence
    */
   public void hideButtons()
   {
      for(JButton button : buttons)
         button.setVisible(false);
   } 
   
   /** 
    * R7.8.0
    * Restores the GUI buttons after the RoadBuilding sequence is completed
    */
   public void restoreButtons()
   {
      for(JButton button : buttons)
         button.setVisible(true);
   }
   
   /**
    * R2.2.0   R2.2.1   R2.2.2   R3.1.3
    * Updates the current Player information 
    */
   public void updatePlayerInfo()
   {
      currentPlayer = playerManager.getCurrentPlayer();
      currentPlayer.updateResourcesInfo();
      playerLabel.setText(currentPlayer.getInformation(scale));
   }
   
   
   /************************************************
    * Nested Classes which Implement ActionListener
    ************************************************/
   
   /**
    * GUIHandler abstract class
    *
    * Abstracts the commonalities of the GUI button handlers
    */
   private abstract class GUIHandler implements ActionListener
   {
      /*******************
       * Abstrace Methods
       *******************/
       
      public abstract void performNextEvent();
      public abstract String toString();
      
      /*************************
       * ActionPerformed Method
       *************************/
       
      /**
       * R2.0.0
       * Performs the actions common to all the GUI buttons
       *
       * @param   e     The ActionEvent
       */
      @Override
      public void actionPerformed(ActionEvent e)
      {
         //If the Robber is the next GamePiece to be placed
         if(board.gamePieceIsRobber())
         
            //Do not allow any other actions
            JOptionPane.showMessageDialog(GUI.this, "You Must Place The Robber!!", "CATAN", JOptionPane.ERROR_MESSAGE);
      
         else
         {
            //Clear the list buttons in the Board
            board.clearButtons();
            
            //If there is a GamePiece which is ready to be placed
            if(board.getGamePieceReadyToPlace() != null)
            {
               //Remove it and return it to the Player's inventory (the user has chose not to place it)
               board.getGamePieceReadyToPlace().addToPlayerInventory();
               board.setGamePieceReadyToPlace(null);
            }
            
            //Perform the next event (determined by the Handler of the button pressed)
            performNextEvent();
         }
         
         //Update player information
         updatePlayerInfo();
         
         //Update the graphics
         getParent().repaint();
      }
   }
   
   /**
    * R3.2.0
    * Implements the performNextEvent() and toString() methods for the Build Road button
    */ 
   private class RoadHandler extends GUIHandler
   {
      /**
       * R3.2.0
       * Allows the Player to build a Road 
       */
      @Override
      public void performNextEvent()
      {
         //If the Player has a Road available AND has the required Resources
         if(currentPlayer.getNumberOfRoads() > 0 && currentPlayer.hasRoadResources())
         {
            //Get a road from the Player's roads list, set it as the next GamePiece to be placed
            board.setGamePieceReadyToPlace(currentPlayer.getRoad());
            
            //Display LocationButtons on legal Road Locations
            board.displayRoadLocations(currentPlayer); 
         }
         
         else
            //Display message if a Road may not be built by the Player
            JOptionPane.showMessageDialog(board, "You are out Roads or resources!", "Settlers of Catan", JOptionPane.ERROR_MESSAGE);
      }
      
      /**
       * R3.2.0  
       * Returns a String representation of the button
       */ 
      @Override 
      public String toString()
      {
         return "Build Road";
      }
   }
   
   /**
    * R3.2.0
    * Implements the performNextEvent() and toString() methods for the Build Settlement button
    */   
   private class SettlementHandler extends GUIHandler
   {
      /**
       * R3.2.0
       * Allows the Player to build a Settlement
       */
      @Override
      public void performNextEvent()
      {
         //If the Player has a Settlement available AND has the reuqired Resources
         if(currentPlayer.getNumberOfSettlements() > 0  && currentPlayer.hasSettlementResources())
         {
            //Get a Settlment from the Player and set it as the next GamePiece to be placed
            board.setGamePieceReadyToPlace(currentPlayer.getSettlement());
            
            //Display LocationButtons where the Settlment may be placed
            board.displaySettlementLocations(currentPlayer);
         }
         
         else
            //Display error message if Settlement is unable to be built
            JOptionPane.showMessageDialog(board, "You are out Settlements or resources!", "Settlers of Catan", JOptionPane.ERROR_MESSAGE);
      }
      
      /**
       * R3.2.0
       * Return a string representation of the button
       */
      @Override
      public String toString()
      {
         return "Build Settlement";
      }
   }
   
   /**
    * R3.2.0
    * Implements the performNextEvent() and toString() methods for the Build City button
    */ 
   private class CityHandler extends GUIHandler
   {
      /**
       * R3.2.0
       * Allows the Player to build a City
       */
      @Override
      public void performNextEvent()
      {
         //If the Player has Cities available AND has the required resources
         if(currentPlayer.getNumberOfCities() > 0 && currentPlayer.hasCityResources())
         {
            //Get a City from the Player and set it as the next GamePiece to be placed
            board.setGamePieceReadyToPlace(currentPlayer.getCity());
            
            //Display LocationButtons where the City may be placed
            board.displayCityLocations(currentPlayer);
         }
         
         else
            //If the player is out of Cities or Resources, display message
            JOptionPane.showMessageDialog(board, "You are out Cities or resources!", "Settlers of Catan", JOptionPane.ERROR_MESSAGE);
      }
      
      /**
       * R3.2.0
       * Returns a string representation of the button
       */
      @Override
      public String toString()
      {
         return "Build City";
      }
   }
   
   /**
    * R3.3.0
    * Implements the performNextEvent() and toString() methods for the Buy Development Card button
    */ 
   private class BuyDevelopmentCardHandler extends GUIHandler
   {
      /** 
       * R7.3.0
       * Allows the Player to buy a DevelopmentCard
       */
      @Override  
      public void performNextEvent()
      {
         //If there are DevelopmentCards available in the deck, and the Player has the required Resources
         if(developmentCards.size() > 0 && currentPlayer.canAffordDevelopmentCard())
         {
            //Remove the required Resources from the Player's resources list
            currentPlayer.payForDevelopmentCard();
            
            //Get the DevelopmentCard from the top of the list and set the Player as the owner
            developmentCards.get(0).setPlayer(currentPlayer);
            
            //Remove the DevelopmentCard from the deck and add it to the Player's list
            currentPlayer.addDevelopmentCard(developmentCards.remove(0));
            
            //Display message indicating a development card was bought
            JOptionPane.showMessageDialog(GUI.this, "You bought a Development Card!", "Settlers of Catan", JOptionPane.INFORMATION_MESSAGE);
         }
         
         else
            //If there are no DevelopmentCards in the deck, or if the Player doesnt have the require resources, display error message
            JOptionPane.showMessageDialog(null, "You dont have the resources, or there are no more Development Cards", "Settlers of Catan", JOptionPane.ERROR_MESSAGE);
      }
  
      /** 
       * R7.3.0
       * Return a string Representation of the button
       */
      @Override
      public String toString()
      {
         return "Buy Dev Card";
      }
   }
   
   /**
    * R7.2.0
    * Implements the performNextEvent() and toString methods for the Play Development Card button
    */
   private class PlayDevelopmentCardHandler extends GUIHandler
   {
      /**
       * R7.2.0
       * Allows the Player to choose and play a DevelopmentCard from their DevelopmentCards list
       */
      @Override
      public void performNextEvent()
      {
         //If the current Player has a DevelopmentCard
         if(currentPlayer.getNumberOfDevelopmentCards() > 0)
            
            //Allow the Player to choose on of thier DevelopmentCards from a selection box
            currentPlayer.chooseDevelopmentCard();
            
         else
            //If the player does not have a DevelopmentCard, display and error message
            JOptionPane.showMessageDialog(null, "You dont have any Development Cards!", "Settlers of Catan", JOptionPane.ERROR_MESSAGE);
      }
      
      /**
       * R7.2.0
       * Returns a string representation of the Use Development Card button
       */
      @Override
      public String toString()
      {
         return "Use Dev Card";
      }
   }
   
   /**
    * R8.0.0
    * Implements the performNextEvent() and toString() methods for the Trade button
    */
   private class TradeHandler extends GUIHandler
   {
   
      /**
       * R8.0.0
       * Allows the Player to trade Resources with other Players
       */
      @Override
      public void performNextEvent()
      {
         //Create a list of Resources for the trade offer and trade request
         List<Tile.Resource> tradeOffer = new LinkedList<>();
         List<Tile.Resource> tradeRequest = new LinkedList<>();
         
         //Integer representing the choice from the JOptionPane
         int choice;
         
         //Enter loop
         do
         {
            //Sort the Player's resource for organizational purposes
            Collections.sort(currentPlayer.getResources());
            
            //Show a selection box asking which Resource the Player would like to offer and return the index of the selection
            int index = JOptionPane.showOptionDialog(null, "Choose a Resource to offer:", "Settlers of Catan", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, currentPlayer.getResources().toArray(),null);
      
            //If the player chose a Resource
            if(index != JOptionPane.CLOSED_OPTION)
               
               //Add it to the tradeOffer list
               tradeOffer.add(currentPlayer.getResources().remove(index));
           
            else{
               for(Tile.Resource tr : tradeOffer)
                  currentPlayer.addResource(tr);
               return;
            }
            //Ask the Player if they want to offer more resources
            choice = JOptionPane.showConfirmDialog(null, "You are offering "+tradeOffer+".  Would you like to add another resource?", "Settlers of Catan", JOptionPane.YES_NO_OPTION);
         }
         //If the Player wishes to offer more Resources, re-enter the loop
         while(choice == JOptionPane.YES_OPTION);
         
         //Enter Loop
         do
         {
            //Show a selection box asking which Resource the Player would like to request and return the index of the selection
            int index = JOptionPane.showOptionDialog(null, "Request a resource:", "Settlers of Catan", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, Tile.Resource.values(),null);
      
            //If the Player chose a Resource
            if(index != JOptionPane.CLOSED_OPTION)
               
               //Add it to the tradeRequest list
               tradeRequest.add(Tile.Resource.values()[index]);
               
            else{
               for(Tile.Resource tr : tradeOffer)
                  currentPlayer.addResource(tr);
               return;
            }
            
            //Ask the Player if they want to request more resources
            choice = JOptionPane.showConfirmDialog(null, "You are requesting "+tradeRequest+".  Would you like to request another resource?", "Settlers of Catan", JOptionPane.YES_NO_OPTION);
         }
         //If the player wishes to request more Resources, re-enter the loop
         while(choice == JOptionPane.YES_OPTION);
         
         //For every Player in the game
         for(Player p : playerManager.getPlayers())
         {
            //If the Player is not the current Player
            if(currentPlayer != p)
            {
               //Create a temporay list of Resources
               List<Tile.Resource> temp = new LinkedList<>();
               
               //Assume that the player has the Requested Resources
               boolean hasResourcesToTrade = true;
               
               //For every Resource in the tradeRequest list
               for(Tile.Resource resource : tradeRequest)
               {   
                  //If the Player has the requested Resource
                  if(p.getResources().contains(resource))
                  {
                     //Remove the requested Resource from the Player's list and add it to the temporary list
                     p.getResources().remove(resource);
                     temp.add(resource);
                  }            
                  else
                     //If the player does not have the requested Resource, set the hasResourcesToTrade boolean to false
                     hasResourcesToTrade = false;     
               }  
               
               //Add the resources in the temp list back to the Player's resource list
               for(Tile.Resource resource : temp)
                  p.addResource(resource);
               
               //Update the Player's resource information
               p.updateResourcesInfo();
               
               //Assume the Player doesnot want to trade Resources
               int tradeResponse = -1;
               
               //If the player has the requested resources
               if(hasResourcesToTrade)
               
                  //Display a selection box offering the trade to the Player
                  tradeResponse = JOptionPane.showConfirmDialog(null, p.getInformation(scale)+"<br><br>Trade your "+tradeRequest+" for "+tradeOffer, "Settlers of Catan", JOptionPane.INFORMATION_MESSAGE);
               
               //If the player accepst the trade offer
               if(tradeResponse == JOptionPane.YES_OPTION)
               {
                  //Remove the resources from the Player's Resource list and add it to the Current Player's Resourcs
                  for(Tile.Resource resource : tradeRequest)
                     currentPlayer.addResource(p.removeResource(resource));
                  
                  //Add the resources offered from the current Player to the Player
                  for(Tile.Resource resource : tradeOffer)
                     p.addResource(resource);
                  
                  //End the trade, it had been completed
                  return;
               }
            }
         }
         
         //If the trade is refused, add the tradeOffer list of Resources back to the current Player's Resource list
         for(Tile.Resource resource : tradeOffer)
            currentPlayer.addResource(resource);
      }
      
      /**
       * R8.0.0
       * Returns a string representation of the Trade button
       */
      @Override
      public String toString()
      {
         return "Player Trade";
      }
   }
   
   private class MaratimeTradeHandler extends GUIHandler
   {
      @Override
      public void performNextEvent()
      {
         LinkedList<Tile.Resource> trades = new LinkedList<>();
         LinkedList<String> strTrades = new LinkedList<>();
         
         for(int i=0; i < 5; ++i)
            if(currentPlayer.getNumResources()[i] >= currentPlayer.getMTCost()[i])
            {
               trades.add(Tile.Resource.values()[i]);
               strTrades.add("Trade "+currentPlayer.getMTCost()[i]+" "+Tile.Resource.values()[i]);
            }  
         
         int itemIndex = JOptionPane.showOptionDialog(null,"Maritime Trade", "Settlers of Catan", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,strTrades.toArray(),null);
         
         if(itemIndex == -1)
            return;
            
         Tile.Resource res = trades.get(itemIndex);
         for(int j=0; j<currentPlayer.getMTCost()[res.ordinal()]; ++j)
            currentPlayer.removeResource(trades.get(itemIndex));
         
         currentPlayer.addResource(Game.showSelectionBox(null, "What do you want?", Tile.Resource.values()));
     
      }
      
      public String toString()
      {
         return "Maritime Trade";
      }
   }
   
   /**
    * R2.4.5
    * Implements the performNextEvent() and toString() methods for the End Turn button
    */
   private class EndTurnHandler extends GUIHandler
   {
      /**
       * R2.4.5
       * Ends the turn for the current Player and begins the turn for the next Player
       */
      @Override
      public void performNextEvent()
      {
         //Checks to see if the Player now has the larges army
         playerManager.checkLargestArmy(currentPlayer);
         
         //************************
         
         board.updateLongestRoads();
         playerManager.checkLongestRoad();
      
         //************************
         
         //Checks to see if the Player has scored 10 points to win the game
         playerManager.checkForVictory();
         
         //The next Player takes control of the Game
         currentPlayer = playerManager.getNextPlayer();
         
         //Change the background color of the GUI to match the new current Player
         setBackground(currentPlayer.getColor());
         
         //Update Player information to ensure accuracy
         updatePlayerInfo();
         
         //The new Player rolls the dice
         rollDice();
      }
      
      /**
       * R2.4.5
       * Returns a string reprsentation of the End Turn button
       */
      @Override
      public String toString()
      {
         return "End Turn";
      }
   }   
}
