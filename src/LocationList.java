package jem;

import java.awt.Point;
import java.util.LinkedList;


/**
 * LocationList class
 *
 * This class creates two linked lists of Locations to place Game Pieces. The list options are settlement locations and road locations.
 * These lists contain points on the board and allow placement of corresponding game pieces.
 * 
 * @author  JEM CSC478B
 */
public class LocationList extends LinkedList<Location>
{
   /************
    * Constants
    ************/
    
   //Creates a list of settlement Locations when passed to the constructor
   public static final int SETTLEMENT_LOCATIONS = 1;
   
   //Creates a list of road Locations when passed to the constructor
   public static final int ROAD_LOCATIONS = 2;
   
   /**************
    * Constructor
    **************/
    
   /**
    * R3.2.2   R3.2.3
    * Creates a linked list of Locations to place Game Pieces. 
    *
    * @param   scale             Scales the location points based on the native resolution
    * @param   locationChoice    Creates a list for roads or settlements based on the constant passed
    */
   public LocationList(double scale, int locationChoice)
   {  
      //Create list based on the type of locations needed
      switch (locationChoice)
      {
         //Create the settlement location list
         case SETTLEMENT_LOCATIONS:  
            
            //Algorithm which determines the point of each tile corner based on the scale: See pseudo-code document
            int count = 7;
            for(int i=0; i<3; ++i,count = count+2)
               for(int j=0;j<count;++j)
               {
                  add(new Location(new Point( (int)((222+86*(j-i))*scale), (int)(((50+150*i) + (j%2==0 ? 50 : 0))*scale))));
                  add(new Location(new Point( (int)((222+86*(j-i))*scale), (int)(((800-150*i) + (j%2==0 ? 0 : 50))*scale))));
               }
            break;
            
         //Create the road location list
         case ROAD_LOCATIONS:
         
            //Algorithm which determines the center point for each tile edge: See pseudo-code document
            for(int i=0; i<3; ++i)
               for(int j=0; j<6+2*i; ++j)
               {
                  add(new Location(new Point((int)((265+86*(j-i))*scale),(int)((75+150*i)*scale))));
                  add(new Location(new Point((int)((265+86*(j-i))*scale),(int)((825-150*i)*scale))));
               }
         
            for(int i=0;i<3;++i)
               for(int j=0;j<4+i;++j)
               {
                  add(new Location(new Point((int)((222+172*j-86*i)*scale),(int)((150+150*i)*scale))));
                  if(i<2)
                     add(new Location(new Point((int)((222+172*j-86*i)*scale),(int)((750-150*i)*scale))));
               }
               
            //Starting road index is zero
            int i=0;
            
            //Add a road location index to each road location to determine how the road should be rotated to align with tile edge.
            for(Location location : this)
               location.setIndex(i++);
      }
   }
}