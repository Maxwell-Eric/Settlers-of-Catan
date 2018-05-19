 package jem;

 import java.util.*;
 import java.awt.Point;
 import java.awt.Graphics;
 import java.awt.Graphics2D;
 import java.awt.Image;
 import java.awt.event.ActionEvent;
 import javax.imageio.*;
 import javax.swing.JOptionPane;
 import java.io.*;
 import javax.swing.*;

/**
 * Board class
 *
 * This class displays the Resource Tiles and GamePieces placed on the board. It contains the lists Locations where 
 * the GamePieces can be placed. Buttons are placed at Locations where a GamePiece may be placed when a Player 
 * requests a build in the GUI.
 *
 * @author  JEM CSC 478B
 */
public class Board extends javax.swing.JPanel implements java.awt.event.ActionListener
{
   /************
    * Constants
    ************/
   
   //The distance between a Settlement Location and the closes Road Locations
   private final double DISTANCE_BETWEEN_SETTLEMENT_AND_ADJACENT_ROAD;
   
   //The distance between consecutive Settlement Locations
   private final double DISTANCE_BETWEEN_SETTLEMENTS;
   
   //The distance between consecutive Road Locations
   private final double DISTANCE_BETWEEN_ROADS;
   
   //The distance from a Tile center and the Settlement Locations on its corners
   private final double DISTANCE_BETWEEN_TILE_CENTERS_AND_SETTLEMENTS;
   
   
   /*************
    * Attributes
    *************/
   
   //The list of Tiles on the Board
   private List<Tile> tiles;
   
   //The list of Harbors
   private List<Harbor> harbors;
   
   //List of the Locations where a Settlement may be placed
   private List<Location> settlementLocations;
   
   //List of the Locations where a Road may be placed
   private List<Location> roadLocations;
   
   //List of buttons that are created when a Player may place a GamePiece
   private List<LocationButton> buttons = new LinkedList<LocationButton>();
   
   //The Robber on the Board
   private Robber theRobber = new Robber();
   
   //GamePiece which is ready to placed
   private GamePiece gamePieceReadyToPlace = null;
   
   //Scale of the native screen
   private double scale;
   
   
   /**************
    * Constructor
    **************/
    
   /**
    * R1.0.0   R1.4.0
    * Sets up the Board by placing Tiles, assigns constants based on the scale parameter, creates Location Lists based
    * on scale, and places the Robber on the desert Tile
    *
    * @param      scale    The scale to match the frame to the native screen size.
    */
   public Board(double scale)
   {
      //Componet layout is null
      setLayout(null);
      
      //Set the scale attribute to the paramater scale value
      this.scale = scale;
      
      //Set background to blue
      setBackground(java.awt.Color.BLUE);
      
      //Set the constant values based on the scale value
      DISTANCE_BETWEEN_SETTLEMENT_AND_ADJACENT_ROAD = 60 * scale;
      DISTANCE_BETWEEN_SETTLEMENTS = 110 * scale;
      DISTANCE_BETWEEN_ROADS = 110 * scale;
      DISTANCE_BETWEEN_TILE_CENTERS_AND_SETTLEMENTS = 110 * scale;
      
      //Create the SettlementLocations list roadLocations list based on the scale
      settlementLocations = new LocationList(scale, LocationList.SETTLEMENT_LOCATIONS);
      roadLocations = new LocationList(scale, LocationList.ROAD_LOCATIONS);
      
      //Create the list of Tiles
      tiles = new TileList(scale);
      
      //Create the list of Harbors
      harbors =  new HarborList(scale);
      
      //Place the Robber on the desert Tile for start of game
      placeRobberOnDesert();   
      
      //Update the game graphics 
      repaint();
   }
   
   
   /******************
    * Private Methods
    ******************/
   
   /**
    * R1.4.0
    * Places the Robber on the desert Tile
    */
   private void placeRobberOnDesert()
   {
      //Iterate through the tiles list
      for(Tile tile : tiles)
         
         //If the Tile has no resource, it is the desert Tile
         if(tile.getResource() == null)
         {
            //Place the Robber in the TileLocation
            theRobber.place(tile.getTileLocation());
            
            //Add the Robber to the board to make it visible
            add(theRobber);
         } 
   }
   
   /**
    * R6.4.0
    * Places LocationButton at Location if the currentGamePiece may be placed there
    *
    * @param   location    Location where the GamePiece may be placed
    */
   private void addButton(Location location)
   {
      //Create new LocationButton
      LocationButton button = new LocationButton();
      
      //Set the Location of the button
      button.setButtonLocation(location);
      
      //Add action listener to the button
      button.addActionListener(this);
      
      //Set button size
      button.setSize(20, 20);
      
      //Set the point where the button will be placed on the Board
      button.setLocation(location.getPoint().x - 10, location.getPoint().y - 10);
      
      //Add the button to the buttons list
      buttons.add(button);
      
      //Add the button to the Board          
      add(button);
   }
   
   
   /**********
    * Methods
    **********/

   /**
    * R5.2.3
    * Sets the Robber to be the next GamePiece placed
    */
   public void setGamePieceReadyToPlaceRobber()
   {
      gamePieceReadyToPlace = theRobber;
   }
   
   /**
    * R6.0.0
    * Sets the parameter gamePiece to be the next GamePiece placed
    *
    * @param   gamePiece   The next GamePiece to be placed on the Board
    */
   public void setGamePieceReadyToPlace(GamePiece gamePiece)
   {
      gamePieceReadyToPlace = gamePiece;
   }
   
   /**
    * R3.0.0
    * Returns the GamePiece which is ready to be placed
    *
    * @return     The GamePiece which is ready to be placed
    */
   public GamePiece getGamePieceReadyToPlace()
   {
      return gamePieceReadyToPlace;
   }
   
   /**
    * R5.2.3   R7.4.0
    * Returns a boolean indicating whether the currentGamePiece is the Robber
    *
    * @return     boolean indicating whether the next GamePiece to be placed is the Robber
    */
   public boolean gamePieceIsRobber()
   {
      if(gamePieceReadyToPlace == theRobber)
         return true;
         
      else
         return false;
   }
   
   /**
    * R6.4.0
    * Clears the buttons list so a new list can be add on next GamePiece placement
    */        
   public void clearButtons()
   {
      //Iterate though the buttons list
      for(LocationButton button : buttons)
         
         //Remove each button from the board
         remove(button);
      
      //Clear the buttons list  
      buttons.clear();        
   }
   
   /**
    * R5.2.3   R7.4.0   R6.4.0
    * Displays LocationButtons on the Tile Locations so the Robber may be placed
    */
   public void displayTileLocations()
   {
      //For each Tile in the tiles list
      for(Tile tile : tiles)
         
         //If the Tile doesnt have the Robber
         if(tile.getTileLocation().getGamePiece() == null)
         
            //Add a LocationButton to the Tile center
            addButton(tile.getTileLocation());
   }
   
   /**
    * R4.1.3   R6.4.0
    * Displays LocationButtons on Road Locations where a Road may be placed during the setup phase
    *
    * @param   player   The current player
    */
   public void displaySetupRoadLocations(Player player)
   {
      //Iterate through the settlementLocations
      for(Location settlementLocation : settlementLocations)
         
         //If there is a Settlement and the current Player owns it
         if(settlementLocation.getGamePiece() != null && settlementLocation.getGamePiece().getPlayer() == player)
            
            //Iterate through the roadLocations
            for(Location roadLocation1 : roadLocations)
               
               //If the RoadLocation is adjacent to the Settlement
               if(settlementLocation.getPoint().distance(roadLocation1.getPoint()) < DISTANCE_BETWEEN_SETTLEMENT_AND_ADJACENT_ROAD)
               {
                  //Create a boolean to determine whether you should add a button, assume you should 
                  boolean addButton = true;
                  
                  //Iterate through the roadLocations
                  for(Location roadLocation2 : roadLocations)
                     
                     //If there is already a road next to the Settlement
                     if(settlementLocation.getPoint().distance(roadLocation2.getPoint()) < DISTANCE_BETWEEN_ROADS && roadLocation2.getGamePiece() != null)
                        
                        //Set add button to false
                        addButton = false;
                  
                  //If you should add a button      
                  if(addButton)
                  
                     //Add a LocationButton at the Road Location
                     addButton(roadLocation1);
               } 
   }
   
   /**
    * R6.1.0   R6.4.0
    * Displays LocationButtons where a Road may be placed when a Player requests to build a Road
    *
    * @param   player   The current Player
    */
   public void displayRoadLocations(Player player)
   { 
      //Iterate through the roadLocations
      for(Location roadLocation : roadLocations)
      
         //If the Road Location has a Road and it is the current Player's road
         if(roadLocation.getGamePiece() != null && roadLocation.getGamePiece().getPlayer() == player)
         
            //Iterate through the roadLocations again
            for(Location roadLocation2 : roadLocations)
               
               //If the Road Location is empty and adjacent to the Road Location with the Player's Road
               if(roadLocation.getPoint().distance(roadLocation2.getPoint()) < DISTANCE_BETWEEN_ROADS && roadLocation2.getGamePiece() == null)
               
                  //Add a LocationButton                
                  addButton(roadLocation2); 
   }
   
   /* R4.1.3   R6.4.0
    * Displays LocationButtons on Settlement Locations where a Road may be placed during the setup phase
    *
    * @param   player   The current player
    */ 
   public void displaySetupSettlementLocations()
   {
      //Iterate through the settlementLocations list
      for(Location settlementLocation : settlementLocations)
         
         //If the Settlement Location is empty
         if(settlementLocation.getGamePiece() == null)
         {
            //Create a boolean which determines if a button may be placed, assume it to be true
            boolean addButton = true;
            
            //Iterate through the settlmentLocations list again
            for(Location settlementLocation2 : settlementLocations)
               
               //If the Settlement Locations are adjacent and the current Settlement Location has a GamePiece
               if(settlementLocation.getPoint().distance(settlementLocation2.getPoint()) < DISTANCE_BETWEEN_SETTLEMENTS && settlementLocation2.getGamePiece() != null)
                 
                  //Set boolean to false, a Settlment may not be placed
                  addButton = false;  
            
            //If a Settlement may be placed
            if(addButton)
            
               //Add a LocationButton to the Settlement Location
               addButton(settlementLocation);
         } 
   }
   
   /**
    * R6.2.0   R6.4.0
    * Displays LocationButtons where a Settlement may be placed when a Player requests to build a Road
    *
    * @param   player   The current Player
    */
   public void displaySettlementLocations(Player player)
   {
      //Iterate through the roadLocations
      for(Location roadLocation : roadLocations)
         
         //If the Location has a Road and the Road belongs to the current Player
         if(roadLocation.getGamePiece() != null && roadLocation.getGamePiece().getPlayer() == player)
         
            //Iterate through the settlmentLocations
            for(Location settlementLocation1 : settlementLocations)
            
               //If the Settlement Location is adjacent to the Road owned by the player and there is not a GamePiece at the Location
               if(settlementLocation1.getPoint().distance(roadLocation.getPoint()) < DISTANCE_BETWEEN_SETTLEMENT_AND_ADJACENT_ROAD && settlementLocation1.getGamePiece() == null)
               {
                  //Create Boolean and assume a Settlement can be placed
                  boolean addButton = true;
                  
                  //Iterate through the Settlement Locations again
                  for(Location settlementLocation2 : settlementLocations)
                     
                     //If there is a Settlement in an adjacent Settlement Location
                     if(settlementLocation1.getPoint().distance(settlementLocation2.getPoint()) < DISTANCE_BETWEEN_SETTLEMENTS && settlementLocation2.getGamePiece() != null)
                        
                        //Set boolean to false
                        addButton = false;  
                  
                  //If a Settlement is allowed to be placed
                  if(addButton)
                  
                     //Add a LocationButton at the Settlement Location
                     addButton(settlementLocation1);
               }  
   }
   
   /**
    * R6.3.0   R6.4.0
    * Displays LocationButtons where a Settlement may be placed when a Player requests to build a Road
    *
    * @param   player   The current Player
    */
   public void displayCityLocations(Player player)
   {
      //Iterate through the settlementLocations
      for(Location settlementLocation : settlementLocations)
      {
         //Check if each Location has a Settlement owned by the current Player
         GamePiece gamePiece = settlementLocation.getGamePiece();
         if (gamePiece != null && gamePiece.getPlayer() == player && gamePiece instanceof Settlement)
            
            //If so, add a LocationButton 
            addButton(settlementLocation);
      }
   }
   
   /**
    * R4.1.5
    * Pays your initial Settlement one Resource for every Tile it touches
    */
   public void payInitialSettlements()
   {
      //Iterate through the SettlementLocations
      for(Location settlementLocation : settlementLocations)
         
         //If there is a Settlement at the Location
         if(settlementLocation.getGamePiece() != null)
            
            //Iterate through the Tiles
            for(Tile tile : tiles)
               
               //If the Settlement is on the tile corner AND it is not the desert tile
               if(tile.getTileLocation().getPoint().distance(settlementLocation.getPoint()) < DISTANCE_BETWEEN_TILE_CENTERS_AND_SETTLEMENTS && tile.getResource() != null)
                  
                  //Add one resource from the Tile to the Player who owns the Settlement
                  settlementLocation.getGamePiece().getPlayer().addResource(tile.getResource()); 
   }
   
   /**
    * R5.2.1
    * Pays Tile Resources to Cities and Settlements if the Dice roll is equal to the NumberToken of the Tile
    *
    * @param   value    The NumberToken.Value of the Dice roll
    */
   public void payResourceOnRoll(NumberTokens.Value value)
   {
      //Iterate through the tiles list
      for(Tile tile : tiles)
         
            //If the NumberToken.Value of the Tile is equal to the Dice roll and the Tile does not contain the Robber
            if(tile.getNumberToken() != null && tile.getValue() == value && tile.getTileLocation().getGamePiece() != theRobber)
               
               //Iterate through the settlementLocations
               for(Location settlementLocation : settlementLocations)
               
                  //If the Settlement Location is on one of the corners of the Tile and there is a Settlement
                  if(tile.getTileLocation().getPoint().distance(settlementLocation.getPoint()) < DISTANCE_BETWEEN_TILE_CENTERS_AND_SETTLEMENTS && settlementLocation.getGamePiece() != null)
                     
                     //Pay the approriate resources to the owner of the Settlments
                     settlementLocation.getGamePiece().payResourcesToPlayer(tile.getResource());
   }
   
   /**
    * R5.2.4   R7.4.0
    * When the Robber is moved, allows the current Player to steal a random Resource from one of the Players with a Settlement or City on a corner of the tile
    *
    * @param      tileLocation   The Tile Location where the Robber was moved 
    */
   private void stealResource(Location tileLocation)
   {
      //List of Players who own Settlements or Cities at the corners of the Tile
      List<Player> possibleTargets = new LinkedList<>();
      
      //Iterate through the settlementLocations
      for(Location settlementLocation : settlementLocations)
         
         //If the Settlement Location is on the corner of the Tile AND there is a GamePiece there AND the Player who owns it has resources
         if(tileLocation.getPoint().distance(settlementLocation.getPoint()) < DISTANCE_BETWEEN_TILE_CENTERS_AND_SETTLEMENTS && settlementLocation.getGamePiece() != null && settlementLocation.getGamePiece().getPlayer().getResourcesSize() > 0)
            
            //If the list of possible targets does not already contain the Player
            if(!(possibleTargets.contains(settlementLocation.getGamePiece().getPlayer())))
            
               //Add the Player to the list of possible targets
               possibleTargets.add(settlementLocation.getGamePiece().getPlayer());
    
      //If there is a possible target
      if(possibleTargets.size() > 0)
      { 
         //Get the current Player
         Player currentPlayer = ((Game)SwingUtilities.getWindowAncestor(this)).getCurrentPlayer();
         
         //Display a selection box with the possible targets so the player can choose the target
         Player target = Game.showSelectionBox(null, "Choose Your Target", possibleTargets);
         
         //Get a Resource from the target
         Tile.Resource stolenResource = target.getRandomResource();
         
         //Display stolen Resource
         JOptionPane.showMessageDialog(null, "You stole "+stolenResource+"!", "Settlers of Catan", JOptionPane.INFORMATION_MESSAGE);
         
         //Remove a random resource from the target and add it to the current player
         currentPlayer.addResource(stolenResource);
      }
   }
   
   //**************************************************************************************
   /**
    * Checks to see if any player has built a settlement on a harbor location. If so, it updates the cost of maritime
    * trades.
    *
    * @param     point  Point at which a settlement has been built.
    */
   private void checkForHarbor(Point point)
   {
      for(Harbor harbor : harbors)
      {
         if (harbor.getPoint().distance(point) < 60*scale)
            harbor.setPlayerMTCost(gamePieceReadyToPlace.getPlayer());
      }
   }
   //*************************************************************************************
   
   public void countRoads(int count, Location rl){
   
      rl.setMarked(true);  
      if(count > rl.getGamePiece().getPlayer().getLongestRoad())
         rl.getGamePiece().getPlayer().setLongestRoad(count);
     
      List<Location> nextLocations = new LinkedList<>();
      for(Location rl2 : roadLocations)
         if(!rl2.getMarked() && rl != rl2 && rl.getPoint().distance(rl2.getPoint()) < 110*scale && rl2.getGamePiece() != null && rl2.getGamePiece().getPlayer() == rl.getGamePiece().getPlayer())
            nextLocations.add(rl2);
      
      for(Location nl : nextLocations)
         nl.setMarked(true);
      
      for(Location nl : nextLocations)
         countRoads(count + 1, nl);
   }
  
   public void updateLongestRoads(){
      List<Location> startLocations = roadStartLocations();
      for(Location sl : startLocations)
      {
         countRoads(1, sl);
         for(Location rl : roadLocations)
            rl.setMarked(false);
      }
   }
   
 
   public List<Location> roadStartLocations(){
      List<Location> startRoads = new LinkedList<>();
      for(Location rl : roadLocations)
         if(rl.getGamePiece() != null){
            int count = 0;
            Location first = null;
            Location second = null;
            for(int i=0; i<roadLocations.size(); ++i)
               if(roadLocations.get(i).getPoint().distance(rl.getPoint()) < 110*scale && roadLocations.get(i) != rl && roadLocations.get(i).getGamePiece() != null && roadLocations.get(i).getGamePiece().getPlayer() == rl.getGamePiece().getPlayer()){
                  ++count;
                  if(first == null)
                     first = roadLocations.get(i);
               
                  else
                     second = roadLocations.get(i);
               }
            
            if(count == 0 || count == 1)
               startRoads.add(rl);
         
            else if(count == 2 && first.getPoint().distance(second.getPoint()) < 110*scale)
               startRoads.add(rl);
         }  
      return startRoads;
   }
   
   //******************************************************************************************
   
   /**
    * R5.2.3   R6.0.0
    * Adds a GamePiece to the Location where the LocationButton was clicked and adds the Gamepiece to the
    * Board so it is visible
    *
    * @param   e     The action event
    */
   public void actionPerformed(ActionEvent e)
   {
      //Remove the buttons from the Board
      for(LocationButton button : buttons)
         button.setVisible(false);
      
      //Get the Location where the LocationButton was pressed
      Location eventLocation = ((LocationButton)e.getSource()).getButtonLocation();
      
      //If there is a settlement at the Location (when placing a City)
      if(eventLocation.getGamePiece() != null)
         
         //remove it from the Board
         remove(eventLocation.getGamePiece());
      
      //Place the GamePiece at the Location where the button was pressed
      gamePieceReadyToPlace.place(eventLocation);
      
      //Add the current GamePiece to the Board so it is visible
      add(gamePieceReadyToPlace);
      
      //********************
      if(gamePieceReadyToPlace instanceof Settlement)
         checkForHarbor(eventLocation.getPoint());
      //*********************
      
      //If the GamePiece added to the Board was the Robber
      if(gamePieceReadyToPlace == theRobber) 
      
         //Activate the stealResource event  
         stealResource(eventLocation);
      
      //Remove the current GamePiece, it is no longer the next GamePiece to be placed
      gamePieceReadyToPlace = null;
      
      //Update the current Player information
      ((Game)SwingUtilities.getWindowAncestor(this)).getGUI().updatePlayerInfo();
      
      //Update graphics
      ((Game)SwingUtilities.getWindowAncestor(this)).repaint();
   }
   
   /**
    * R1.0.0
    * Paints each Tile in the tiles list on Board in proper locations
    */
   @Override
   public void 
   paintComponent(Graphics gx)
   {
      //Call the super method
      super.paintComponent(gx);
      
      //Create 2D graphics 
      Graphics2D gx2D = (Graphics2D) gx;
      
      for(Harbor harbor : harbors)
      {
         Point point = harbor.getPoint();
         gx2D.drawImage(harbor.getImage(), point.x-harbor.getImage().getWidth(null)/2, point.y-harbor.getImage().getHeight(null)/2,null);
         
      }
      //For each tile in the tiles list
      for(Tile tile : tiles)
      {  
         //Get the center point of the current tile
         Point point = tile.getTileLocation().getPoint();
         
         //Get the image of the tile
         Image tileImage = tile.getImage(scale);
         
         //Draw the tile image at the tile center point
         gx2D.drawImage(tileImage, point.x-tileImage.getWidth(null)/2, point.y-tileImage.getHeight(null)/2,null);
         
         //If the tile has a NumberToken
         if(tile.getNumberToken() != null)
         {
            //Get the image of the NumberToken
            Image tokenImage = tile.getNumberTokenImage();
            
            //Draw the Image of the NumberToken at the Tile center
            gx2D.drawImage(tokenImage, point.x-tokenImage.getWidth(null)/2, point.y-tokenImage.getHeight(null)/2,null);
         }      
      }  
   }
}   
