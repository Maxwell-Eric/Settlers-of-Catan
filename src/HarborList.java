package jem;

import java.util.*;
import java.awt.Point;

public class HarborList extends LinkedList<Harbor>
{
   
   public HarborList(double scale)
   {
      List<Point> points = new LinkedList<>();
      points.add(new Point(265,75)); points.add(new Point(523,75)); points.add(new Point(781,225)); points.add(new Point(136,300)); points.add(new Point(910,450)); points.add(new Point(136,600)); points.add(new Point(781,675)); points.add(new Point(265,825)); points.add(new Point(523,825));
      for(Point point : points)
      {
         point.x = (int)(point.x*scale);
         point.y = (int)(point.y*scale);
      }
      
      Collections.shuffle(points);
      
      
      add(new OreHarbor(points.remove(0), scale));
      add(new BrickHarbor(points.remove(0), scale));
      add(new LumberHarbor(points.remove(0), scale));
      add(new GrainHarbor(points.remove(0), scale));
      add(new WoolHarbor(points.remove(0), scale));
      for(int i=0; i<4; ++i)
         add(new GeneralHarbor(points.remove(0), scale));
      
         
   }
}
