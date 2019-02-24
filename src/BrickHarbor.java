package jem;

import java.awt.Point;
import java.awt.Image;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class BrickHarbor extends Harbor
{

   public BrickHarbor(Point p, double scale)
   {
      point = p;
      
      scale = scale*.5;
    
      try
      {
         Image rawImage = ImageIO.read(getClass().getResourceAsStream("/images/hillTile.png"));
         image = rawImage.getScaledInstance((int)(rawImage.getWidth(null)*scale),(int)(rawImage.getHeight(null)*scale), Image.SCALE_SMOOTH);
      }
      
      catch(Exception e)
      {
         //Display error message if image not created
         JOptionPane.showMessageDialog(null, "Can not create Image", "Settlers of Catan", JOptionPane.ERROR_MESSAGE);
      }
   }
   
   public void setPlayerMTCost(Player p)
   {
      p.setMTCost(1, 2);
   }
}
