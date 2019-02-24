 package jem;

import java.util.*;
import javax.swing.JOptionPane;
import java.awt.Color;
import java.awt.Component;
import java.awt.Toolkit;
import java.awt.Dimension;
import javax.swing.*;

/**
 * Game class
 *
 * Creates a frame for the Board and GUI that scales to the screen's native image
 * Sets the initial Settlements and Roads for each player
 * Static methods create a JOptionPane for selecting items from a list
 */
public class Game extends javax.swing.JFrame implements java.awt.event.ActionListener
{

   
   /*************
    * Attributes
    *************/
   
   //Help menu items
   private JMenuBar menu = new JMenuBar();
   private JMenu help = new JMenu("Help");
   private JMenuItem overView = new JMenuItem("Over View");
   private JMenuItem setUp = new JMenuItem("Set Up");
   private JMenuItem rollPhase = new JMenuItem("Dice Roll");
   private JMenuItem building = new JMenuItem("Building");
   private JMenuItem devCards = new JMenuItem("Development Cards");
   private JMenuItem trade = new JMenuItem("Trading Resources");
    
   //List of players
   private List<Player> players;
   
   //Dimensions of the screen
   private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
   
   //The scale which adjusts the images to the screen 
   private double scale = screenSize.getHeight()/1000;;
   
   //The GUI
   private GUI gui = new GUI(scale);
   
   //The game Board
   private Board board;
   
   //The game PlayerManager
   private PlayerManager playerManager;
   
   
   /**************
    * Constructor
    **************/
    
   /**
    * R1.5.0
    * Creates a JFrame which will hold the GUI and the Board
    */
   public Game()
   {
      buildMenu();
   
      //Sets the size of the JFrame to match the screen resolution
      setSize((int)screenSize.getWidth(), (int)(1000*scale));
      
      //Set Game to exit when closed
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      
      //Add the GUI to the JFrame
      add(gui);
      
      //Add the Board to the JFrame
      add(board = gui.getBoard());
      
      //Get a refrence to the GUI PlayerManager
      playerManager = gui.getPlayerManager();
      
      //The players in the game
      players = playerManager.getPlayers();
      
      //Set the JFrame to be visible
      setVisible(true); 
   }
   
   /*****************
    * Private Method
    *****************/
    
   /**
    * R4.2.0
    * Creates the help menu
    */
   private void buildMenu()
   {
      //add action listeners to the menu buttons
      setUp.addActionListener(this);
      overView.addActionListener(this);
      rollPhase.addActionListener(this);
      building.addActionListener(this);
      devCards.addActionListener(this);
      trade.addActionListener(this);
      
      //Add menu items to the menu
      help.add(overView);
      help.add(setUp);
      help.add(rollPhase);
      help.add(building);
      help.add(devCards);
      help.add(trade);
      menu.add(help);
      menu.setBackground(Color.CYAN);
      setJMenuBar(menu);
   }
   
   /**************
    * Main Method
    **************/
    
   /**
    * R4.0.0   R4.1.0 through 4.1.5
    * Creates a game and allows players to set initial Settlements and Roads
    */
   public static void main(String[] args)
   {
      Game game = new Game();
      game.placeInitialSettlements();
   }

   
   /**********
    * Methods
    **********/
    
   /**
    * R5.2.4
    * Returns the current player from the PlayerManager
    */
   public Player getCurrentPlayer()
   {
      return playerManager.getCurrentPlayer();
   }
   
   /**
    * R3.1.3   R2.1.0   R2.2.1   R2.2.2
    * Returns the game GUI so it can be updated from the Board class
    */
   public GUI getGUI()
   {
      return gui;
   }
   
   /**
    * R4.2.0
    * Creates JOptionPanes which display information in the help menu.
    */
   public void actionPerformed(java.awt.event.ActionEvent event)
   {
      //Get the help menu item that was pressed
      Object source = event.getSource();
      
      //If the over view menu item was pressed
      if(source == overView)
         JOptionPane.showMessageDialog(this,"<html>The game board consists of 19 hexagonal resource tiles. Each tile is associated with one resource, except the desert tile, which does not produce a resource."+
                                            "<br>Mountains produce Ore, Hills produce Brick, Forests produce Lumber, Fields produce Grain and Valleys produce Wool. Each resource tile, which is not the desert, has a number in the middle."+
                                            "<br>The desert begins with the robber. To begin each turn, the current player ‘rolls’ the dice. If the value of the dice roll is equal to the number in tile, that tile produces resources for that round."+
                                            "<br>Players receive one resource for each settlement and two resources for each city on the corners of that tile. Players will use these resources to build roads, settlements and cities."+
                                            "<br>Development cards may also be purchased with these resources. Each player owns 5 settlements, 4 cities and 15 roads. The winner is the first to score 10 victory points."+
                                            "<br>Victory points can be earned in a variety of ways, which will be covered through the user manual in the appropriate sections."+
                                            "<br>The current player’s score, resources, game pieces, development cards and build costs may be referenced on the GUI. ", "Settlers of Catan", JOptionPane.INFORMATION_MESSAGE);
      
      //If the set up help menu item was pressed         
      if(source == setUp)
         JOptionPane.showMessageDialog(this,"<html>To start the game, a board will appear and inform the user that it is time for the first player to set their initial settlement with an adjacent road."+
                                            "<br>Buttons will appear on the game board where settlements may be placed, which are at the corners of the tiles. After you place your settlement, you must place a road, which is adjacent to the settlement you just placed."+
                                            "<br>Roads are placed along the edges of the tiles. Buttons will appear where you may place a road. After the player places a road, the next player will place a settlement and a road."+ 
                                            "<br>Settlements may NOT be placed in adjacent resource tile corners. In the image below, you can see that buttons are not available for settlement locations which are one road length away from other settlements."+
                                            "<br>This process continues until each player has one settlement and road on the board. Then the process is repeated except that the player order is reversed for the second round."+
                                            "<br>Once each player has two settlements and adjacent roads, the first player will roll the dice (click the OK button on pop up screen when ready to roll) and normal play will begin.", "Settlers of Catan", JOptionPane.INFORMATION_MESSAGE);
      
      //If the dice roll help menu item was pressed                                    
      if(source == rollPhase)
         JOptionPane.showMessageDialog(this,"<html>Each turn begins by rolling the dice, which is clicking the OK button on the pop up window after the last player finishes their turn. When any number other than a seven is rolled,"+
                                            "<br>each resource tile with a number that matches the roll produces resources to players with settlements or cities on the corners of the tile. Players get one resource for each settlement and two resources for each city."+
                                            "<br>The resource tile which contains the robber cannot produce any resources. If a 7 is rolled, each player with more than seven resources MUST remove half of their resources, rounded down."+
                                            "<br>Next, the player who rolled the 7 must move the robber from the tile is currently on to a new tile. Buttons will appear at the center of the tiles when the robber is to be moved. After the robber is moved,"+
                                            "<br>the current player may steal a random resource from any player with a settlement or city on a corner of the tile by selecting the color of the player in the pop up window.", "Settlers of Catan", JOptionPane.INFORMATION_MESSAGE);
      
      //If the building help menu item was pressed                            
      if(source == building)
         JOptionPane.showMessageDialog(this,"<html><b>Roads cost 1 brick and 1 lumber to build. They may be built along tile edges which are adjacent to edges which already have one of the current player’s roads."+
                                            "<br>Buttons will pop up when you click the ‘Build Road’ button on the GUI."+
                                            "<br><br>Settlements cost 1 brick, 1 lumber, 1 grain and 1 wool to build. They may be built at tile corners which have one of the player’s roads in an adjacent tile edge."+
                                            "<br>Also, they must be at least two corners away from any other settlements. When you request to build a settlement, buttons will appear in places where builds may take place."+
                                            "<br>Settlements are worth 1 victory point."+
                                            "<br><br>Cities cost 3 ore and 2 grain resources to build. Cities may only be placed where a settlement currently exists."+
                                            "<br>The button to place a city will not appear until you hover the mouse over the settlement."+
                                            "<br>When you build a city, the settlement that you build on will be placed back into your inventory so it is available to place again."+
                                            "<br>Cities are worth 2 victory points. ", "Settlers of Catan", JOptionPane.INFORMATION_MESSAGE);
      
      //If the development cards help menu item was pressed                         
      if(source == devCards)
         JOptionPane.showMessageDialog(this,"<html>Development cards can be purchased for 1 ore, 1 grain and 1 wool. To buy a development card, click the ‘Buy Dev Card’ button."+
                                            "There are five distinct types of development cards."+
                                            "<br><br>    1.	Victory Point: When played, these add one to your score. There are 5 in the deck."+
                                            "<br><br>    2.	Year of Plenty: When played, you may choose two resources of any type. There are 2 in the deck."+
                                            "<br><br>    3.	Road Building: When played, the player must place 2 roads on the board."+
                                            "<br>           Locations where roads may be placed will have buttons. There are 2 in the deck."+
                                            "<br><br>    4.	Monopoly Card: When played, the current player will choose a resource type."+
                                            "<br>           Every other player must give the current player all their resources of that type. There are 2 in the deck."+
                                            "<br><br>    5.	Knight Card: When played, the current player may move the robber and steal a resource from a player with a settlement"+
                                            "<br>           or a city on the corner of the tile where the robber is moved. Players with more than 7 cards do not have to remove resources."+
                                            "<br>           There are 14 in the deck."+
                                            "<br>           •	Army Size: The number of knight’s a player has played."+
                                            "<br>           •	Largest Army: The first player to play 3 knights receives the ‘Largest Army’ title, which counts for 2 victory points."+
                                            "<br>              For another player to take the ‘Largest Army’ title, they must surpass the size of the current ‘Largest Army.’"+
                                            "<br><br>To use a development card, click the ‘Use Dev Card’ button.  A window will pop up showing your development cards."+
                                            "<br>To use one, click on the appropriate button. You can close the window without using a card.", "Settlers of Catan", JOptionPane.INFORMATION_MESSAGE);
      
      //If the trade help menu item pressed   
      if(source == trade)
         JOptionPane.showMessageDialog(this,"<html>Trading resources is very useful. When you trade, you can build more and can avoid having to remove resources when a 7 is rolled."+
                                            "<br>To trade, click the ‘Trade’ button.  A window listing your resources will pop up. Click the resource you want to offer in the trade."+
                                            "<br>You may offer more than one resource in the trade."+
                                            "<br><br>Choose which resource you are requesting in the trade. You may request more than one resource."+
                                            "<br>When you are finished requesting resources, each player who has the requested resources will be offered the trade."+
                                            "<br>Each player with the requested resources can choose to accept or reject the offer.", "Settlers of Catan", JOptionPane.INFORMATION_MESSAGE);

   }
   
   /**
    * R4.0.0   R4.1.0 through 4.1.5
    * Allows players to place initial Settlements and Roads
    */
   private void placeInitialSettlements()
   {
      //Call set inital pieces, which allows each player to place a Settlement and a Road
      setInitialPieces();
      
      //Pay the initial Settlements a resource for each resource tile they touch
      gui.getBoard().payInitialSettlements();
      
      //Reverse Player order
      Collections.reverse(players);
      
      //Each player sets a Settlement and a Road
      setInitialPieces();
      
      //Reverse Player order back to the orignal order
      Collections.reverse(players);
      
      //First player rolls dice
      gui.rollDice();
      
      //Build buttons on the GUI for the users to interact with
      gui.buildButtons();
      
      //Repaint to show buttons
      repaint(); 
   }
   
   /**
    * R4.1.1   R4.1.3
    * Players take turns placing a Settlement and a Road which is adjacent to the Settlement
    */
   private void setInitialPieces()
   {
      for(Player player : players)
      {  
         //Set background color to match the current player
         gui.setBackground(player.getColor());
         
         //Update gui player information
         gui.updatePlayerInfo();
         
         //Instruct Player to place Settlement and adjacent Road
         JOptionPane.showMessageDialog(null, player.getPlayerColor()+" Player, place a settlement and an adjacent road", "Settlers of Catan", JOptionPane.INFORMATION_MESSAGE);
         
         //Get a Settlmemnt from the current player and set it ready to place
         gui.getBoard().setGamePieceReadyToPlace(player.getSettlement());
         
         //Display possible Locations to place Settlement with buttons
         gui.getBoard().displaySetupSettlementLocations();
         
         //Repaint to show buttons
         repaint();
  
         //Wait for the player to place the Settlment
         while(board.getGamePieceReadyToPlace() != null)
         {
            try{Thread.sleep(25);}catch(Exception e){}
         }
        
         //Update Player information
         gui.updatePlayerInfo();
         
         //Clear the Settlement Buttons
         board.clearButtons();
         
         //Get a Road from the current player and set it ready to place
         board.setGamePieceReadyToPlace(player.getRoad());
         
         //Display possible Locations to place the Road
         board.displaySetupRoadLocations(getCurrentPlayer());
         
         //Repaint to view buttons
         repaint();
         
         //Wait for the player to place the Road
         while(board.getGamePieceReadyToPlace() != null)
         {
            try{Thread.sleep(25);}catch(Exception e){}
         }
         
         //Clear the Road placement buttons
         board.clearButtons();
         
         //Get the next player in the player manager
         playerManager.getNextPlayer();
      }
   } 
   
   /**
    * Used in several requirememts when the user must select from a list of items
    * Creates a selection Box where the user must select one of the options from a list.
    *
    * @param   parentComponent   The component which the box will appear in. If null is selected, the box will appear in the middle of the screen
    * @param   message           Tells what the user is selecting
    * @param   list              The list of comparable objects the user must choose from
    * 
    * @return                    The item selected from the list.
    */
   public static <T extends Comparable<? super T>> T showSelectionBox(Component parentComponent,String message, List<T> list)
   { 
      //The list with no repeating comparable objects
      List<T> nonrepeatingList = new LinkedList<>();
      
      //For every item in the list
      for(T item : list)
      {
         //If the list conatins an instance of the item, do not add it again
         if(nonrepeatingList.contains(item))
            continue;
               
         else
            //If the list doesnt contain an instance of the item, add it to the non repeating list
            nonrepeatingList.add(item);
      }
      //Sort the nonrepeating list of objects
      Collections.sort(nonrepeatingList);
      
      //Index of the item selected by user
      int itemIndex;
      do
      { 
         //Display JOptionPane option dialog and assign the index of the selection  
         itemIndex = JOptionPane.showOptionDialog(parentComponent, message, "Settlers of Catan", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,nonrepeatingList.toArray(),null);
      }
      //If the user closes the window without a selection, the loop repeats
      while(itemIndex == -1);
      
      //Return the item that the user selected
      return nonrepeatingList.get(itemIndex);
   }
   
   /**
    * Used in several requirements when the user must choose from a list of enums or an array
    * Creates a selection box where the user must select one of the options from an array (usually from an enum).
    *
    * @param   parentComponent   The component which the box will appear in. If null is selected, the box will appear in the middle of the screen
    * @param   message           Tells what the user is selecting
    * @param   array             The array of comparable objects (enums) the user must choose from
    * 
    * @return                    The item selected from the array.
    */
   public static <T extends Comparable<? super T>> T showSelectionBox(Component parentComponent,String message, T[] array)  
   {
      //Index of the item selected
      int itemIndex;
      do
      {   
         //Display JOptionPane option dialog and assign the index of the selection
         itemIndex = JOptionPane.showOptionDialog(parentComponent, message, "Settlers of Catan", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,array,null);
      }
      //If the user closes the box without a selection, the loop repeats
      while(itemIndex == -1);
      
      //Return the item that the user selected
      return array[itemIndex];
   }
}
