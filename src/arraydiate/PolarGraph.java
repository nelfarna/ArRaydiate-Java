/*
  @(#)PolarGraph.java
*/

package arraydiate;

import java.awt.geom.*;
import java.awt.*;

/**
 *  The <code>PolarGraph</code> class represents the grid for the 
 *  polar plot.
 *  
 *  @author Nadia Elfarnawani
 */ 
public class PolarGraph
{
           
    private double centerX;
    private double centerY; 
    private double maxRadius;      

    /**
     *  Initializes the dimensions of the polar grid.
     */
    public PolarGraph (double centerX, double centerY, double radius)
    {
        this.centerX = centerX;
        this.centerY = centerY;
        maxRadius = radius;
    }

    /**
     * Draws the polar grid and labels
     * @param Graphics g
     */
    public void paintPolarGraph (Graphics g)
    {
    	
        Graphics2D g2 = (Graphics2D)g;

        Ellipse2D outerCircle = new Ellipse2D.Double();
        outerCircle.setFrameFromCenter(centerX, centerY, 
                            centerX + maxRadius, centerY + maxRadius);

        
        Ellipse2D midCircle1 = new Ellipse2D.Double();
        midCircle1.setFrameFromCenter(centerX, centerY, centerX + maxRadius*0.75, centerY + maxRadius*0.75);

        Ellipse2D midCircle2 = new Ellipse2D.Double();
        midCircle2.setFrameFromCenter(centerX, centerY, centerX + maxRadius*0.25, centerY + maxRadius*0.25);

        
        Ellipse2D midCircle3 = new Ellipse2D.Double();
        midCircle3.setFrameFromCenter(centerX, centerY, centerX + maxRadius*0.5, centerY + maxRadius*0.5);

        
        // The variables below each indicate a pixel point 
        // on the screen that lies at a specific angle (degrees) from
        // the designated center of the grid

        double x_30_deg = maxRadius*Math.sin(Math.PI/6); 
        double y_30_deg = maxRadius*Math.cos(Math.PI/6); 
        double x_60_deg = maxRadius*Math.sin(Math.PI/3); 
        double y_60_deg = maxRadius*Math.cos(Math.PI/3); 
 


        // Create grid lines for each angle

        Line2D axis90 = new Line2D.Double(centerX-maxRadius, centerY, centerX + maxRadius, centerY);

        Line2D axis0 = new Line2D.Double(centerX, centerY + maxRadius, centerX, centerY - maxRadius);

        Line2D axis30 = new Line2D.Double(centerX - x_30_deg, centerY - y_30_deg, centerX + x_30_deg, centerY + y_30_deg);

        Line2D axis60 = new Line2D.Double(centerX - x_60_deg, centerY - y_60_deg, centerX + x_60_deg, centerY + y_60_deg);


        Line2D axis120 = new Line2D.Double(centerX - x_60_deg, centerY + y_60_deg, centerX + x_60_deg, centerY - y_60_deg);

        Line2D axis150 = new Line2D.Double(centerX - x_30_deg, centerY + y_30_deg, centerX + x_30_deg, centerY - y_30_deg);
        

        // Display labels that represent the angles of the grid              
    
        g.drawString("90", (int)(centerX) - 3, (int)(centerY) -5 - (int)maxRadius);
        g.drawString("270", (int)(centerX) - 10, (int)(centerY) + 12 + (int)maxRadius);
        g.drawString("0", (int)(centerX) + 5 + (int)maxRadius, (int)(centerY));
        g.drawString("60", (int)(centerX - 3 + x_30_deg), (int)(centerY - 5 - y_30_deg));
        g.drawString("30", (int)(centerX + x_60_deg), (int)(centerY - y_60_deg));
        g.drawString("330", (int)(centerX + 3 + x_60_deg), (int)(centerY + 3 + y_60_deg));
        g.drawString("300", (int)(centerX + 3 + x_30_deg), (int)(centerY + 3 +y_30_deg));
        g.drawString("270", (int)(centerX - 23 - x_30_deg), (int)(centerY + 10 + y_30_deg));
        g.drawString("210", (int)(centerX - 23 - x_60_deg), (int)(centerY + 10 + y_60_deg));
        g.drawString("180", (int)(centerX) - 20 - (int)maxRadius, (int)(centerY));
        g.drawString("150", (int)(centerX - 20 - x_60_deg), (int)(centerY - 5 - y_60_deg));
        g.drawString("120", (int)(centerX - 10 - x_30_deg), (int)(centerY - 5 - y_30_deg));

       
        // Display circles of the grid
 
        g2.draw(outerCircle);
        g2.draw(midCircle1);
        g2.draw(midCircle2);
        g2.draw(midCircle3);


        // Display grid lines for each angle
         
        g2.draw(axis0); 
        g2.draw(axis30);
        g2.draw(axis60);
        g2.draw(axis90);
        g2.draw(axis120);
        g2.draw(axis150);

        

     }

    /**
     * Sets the center x of the graph
     * @param x - center x of the graph
     */

    public void 
    setCenterX(double x)
    {
        centerX = x;
    }

    /**
     * Sets the center y of the graph
     * @param y - center y of the graph
     */

    public void 
    setCenterY(double y)
    {
        centerY = y;
    }

    /**
     * Gets the center x of the graph
     * @return center x of the graph
     */

    public double
    getCenterX()
    {
        return centerX;
    }

    /**
     * Gets the center y of the graph
     * @return center y of the graph
     */
    
    public double 
    getCenterY()
    {
        return centerY;
    }
    
    /**
     * Gets the radius of the polar graph
     * @return the radius of the polar graph
     */

    public double 
    getRadius()
    {
         return maxRadius;
    }

    public void
    setRadius(double radius)
    {
        maxRadius = radius;
    }

    /**
     * Draws the labels on the graph
     * @param unitType - String representing dB or linear
     * @param Graphics g
     */

     public void 
     drawLabels (String unitType, Graphics g)
     {
          if(unitType.equals("db"))
          {
              g.drawString("0", (int)centerX - 15, (int)centerY - (int)maxRadius + 15);    
              g.drawString("-10", (int)centerX - 15, (int)(centerY - (int)maxRadius*(0.75)) + 15);
              g.drawString("-20", (int)centerX - 15, (int)(centerY - (int)maxRadius*(0.50)) + 15);
              g.drawString("-30", (int)centerX - 15, (int)(centerY - (int)maxRadius*(0.25)) + 15);
          }
          else 
          {
              g.drawString("1.0", (int)centerX - 15, (int)centerY - (int)maxRadius + 15);    
              g.drawString("0.75", (int)centerX - 15, (int)(centerY - (int)maxRadius*(0.75)) + 15);
              g.drawString("0.50", (int)centerX - 15, (int)(centerY - (int)maxRadius*(0.50)) + 15);
              g.drawString("0.25", (int)centerX - 15, (int)(centerY - (int)maxRadius*(0.25)) + 15);
          }

     }
  
}
