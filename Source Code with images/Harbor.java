package jem;
import java.awt.Image;
import java.awt.Point;
import java.awt.Graphics;
import java.awt.Graphics2D;

public abstract class Harbor
{
   protected Point point;
   protected Image image;
   
   public abstract void setPlayerMTCost(Player p);
   
   public Image getImage()
   {
      return image;
   }
   
   public Point getPoint()
   {
      return point;
   }
}