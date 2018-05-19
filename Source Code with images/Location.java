package jem;

import java.awt.Point;

/**
 * Location class
 *  
 * Creates Locations, which allow the placement of game pieces
 *
 * @author  JEM CSC 478B
 */
public class Location
{
   /*************
    * Attributes
    *************/
   
   //Point of the location on the board
   private Point point;
   
   //Gamepiece which exists at the location
   private GamePiece gamePiece;
   
   //Index of the location, used to orient roads along tile edges
   private int index;
   
   //**************************************
   private boolean marked = false;
   
   
   /**************
    * Constructor
    **************/
    
   /**
    * R3.2.3   R5.2.3   R6.0.0   
    * Creates a location at the point passed as a parameter
    *
    * @param   p  Point of the location
    */
   public Location(Point p)
   {
      //Assign the attribute point
      point = p;
   }
   
   /**
    * R5.2.3   R6.0.0
    * Returns the point of the Location
    *
    * @return     The point of the Location on the Board
    */
   public Point getPoint()
   {
      return point;
   }
   
   /**
    * R5.2.3   R6.0.0
    * Sets the gamepiece at the current location
    *
    * @param   gp    GamePiece being placed at the Location
    */
   public void setGamePiece(GamePiece gp)
   {
      gamePiece = gp;
   }
   
   /**
    * R5.2.3   R6.0.0
    * Removes the gamepiece at the Location
    */
   public void clearGamePiece()
   {
      gamePiece = null;
   }
   
   /**
    * R5.2.1   R5.2.3   R6.0.0
    * Returns the gamepiece at the Location
    *
    * @return     GamePiece at the Location
    */
   public GamePiece getGamePiece()
   {
      return gamePiece;
   }
   
   /**
    * R3.2.3
    * Sets the index of the Location
    *
    * @param   i  index of the Location in the LocationList
    */
   public void setIndex(int i)
   {
      index = i;
   }
   
   //*****************************************
   
   public void setMarked(boolean m){
      marked = m;
   }
   
   public boolean getMarked(){
      return marked;
   }
   
   //******************************************
   
   /**
    * R3.2.3
    * Returns the index at the Location
    *
    * @return     Index of the Location in the LocationList
    */
   public int getIndex()
   {
      return index;
   }
}