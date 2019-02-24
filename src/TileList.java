package jem;

import java.util.*;
import java.awt.Point;

/**
 * TileList class
 *
 * Creates a linked list of Tiles with the appropriate number of each tile type in a random
 * order, adds number tokens to the non desert tiles, and adds locations to all tile centers.
 */
public class TileList extends LinkedList<Tile>
{
   /**************
    * Constructor
    **************/
    
   /**
    * R1.2.0 through 1.2.7
    * Creates a linked list of Tiles with number tokens and locations included
    *
    * @param   scale    Scales the visual parts of the game to the native screen size
    */
   public TileList(double scale)
   {
      //Create the appropriate number of each tile and add to the TileList
      getTiles();
      
      //Shuffle the tiles to randomize
      Collections.shuffle(this);
      
      //Adds number tokens to all non desert tiles
      addNumberTokens();
      
      //Create list of tile center points
      List<Point> tileCenters = getTileCenters(scale);
      
      //Add locations to the tile centers so they can hold the robber game piece
      for(Tile tile : this)
         tile.setTileLocation(new Location(tileCenters.remove(0))); 
   }
   
   
   /**********
    * Methods
    **********/
    
   /**
    * R1.2.0 through 1.2.6
    * Creates the appropriate number of each tile
    */
   private void getTiles()
   {
      for(int i=0; i<4; ++i)
      {
         //Add three of each tile type
         if(i<3)
         {
            add(new Tile(Tile.Resource.ORE));
            add(new Tile(Tile.Resource.BRICK));
         }
         //Add four of each tile type
         add(new Tile(Tile.Resource.LUMBER));
         add(new Tile(Tile.Resource.GRAIN));
         add(new Tile(Tile.Resource.WOOL));
      }
      //Add desert Tile
      add(new Tile());  
   }
   
   /**
    * R1.3.2
    * Adds number token to each resource producing tile
    */
   private void addNumberTokens()
   {
      //Create Number Token list
      List<NumberTokens.NumberToken> numberTokens = new NumberTokens();
      
      //For every tile in the linked list of tiles
      for(Tile tile : this)
      {
         //If the tile is not the desert tile
         if(tile.getResource() != null)
            //Remove the top number token and set it as the tile attribute
            tile.setNumberToken(numberTokens.remove(0));
      }
   }
   
   /**
    * R1.0.0
    * Creates a linked list of points where the tile centers should be placed
    *
    * @param   scale    Scales the visual parts of the game to the native screen size
    *
    * @return           List of TileCenter points on the Board
    */
   private List<Point> getTileCenters(double scale)
   {
      //Create linked list to tile center points
      List<Point> tileCenters = new LinkedList<>();
      
      //Create three rows of tiles from the top down
      for(int i=0;i<3;++i)
      {
         //There are three tiles on top row, then four, then five
         for(int j=0;j<3+i;++j)
         {
            //The tile centers start at point(308, 150)(scaled) and move one tile width in the x direction
            //and place the next. At the end of the row, the tiles start a half of a tile with
            //further to the left and 150 pixels down.
            tileCenters.add(new Point((int)((308+(172*j-86*i))*scale),(int)((150+150*i)*scale)));
            
            //Repeat above process, except that the these tiles start on the bottom and creates
            //two rows moving up
            if(i<2)
               tileCenters.add(new Point((int)((308+(172*j-86*i))*scale),(int)((750-150*i)*scale)));
         }
      }
      //Return tile centers list
      return tileCenters;
   }
}