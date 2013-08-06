/*
@(#)RectGraph.java
*/

package arraydiate;

import java.awt.geom.*;
import java.awt.*;


/**
 *  The <code>RectGraph</code> class represents the grid for the 
 *  rectangular plot.
 *  
 *  @author Nadia Elfarnawani
 */ 
public class RectGraph
{
  
    private double centerX;
    private double centerY;
    private double width;
    private double height;

    /**
     *  Initializes the dimensions of the rectangular grid
     */
    public RectGraph(double centerX, double centerY, double width, double height)
    {
          this.centerX = centerX;
          this.centerY = centerY;
          this.width = width;
          this.height = height;
    }

    /**
     * Draws the rectangular grid and labels
     * @param Graphics g
     */
    public void 
    paintRectGraph (Graphics g)
    {
        Graphics2D g2 = (Graphics2D)g;

        Line2D xLine1 = new Line2D.Double(centerX - (width / 2), centerY, 
                                          centerX + (width / 2), centerY);
        Line2D xLine2 = new Line2D.Double(centerX - (width / 2), centerY - (height / 4), 
                                          centerX + (width / 2), centerY - (height / 4));
        Line2D xLine3 = new Line2D.Double(centerX - (width / 2), centerY + (height / 4), 
                                          centerX + (width / 2), centerY + (height / 4));
        Line2D yAxis  = new Line2D.Double(centerX, centerY - (height / 2), centerX, centerY + (height/2));
        Line2D yLine1 = new Line2D.Double(centerX - (width / 3), centerY - (height / 2), 
                                          centerX - (width /3), centerY + (height/2));
        Line2D yLine2 = new Line2D.Double(centerX - (width / 6), centerY - (height / 2), 
                                          centerX - (width / 6), centerY + (height/2));
        Line2D yLine3 = new Line2D.Double(centerX + (width / 3), centerY - (height / 2),
                                          centerX + (width / 3), centerY + (height/2));
        Line2D yLine4 = new Line2D.Double(centerX + (width / 6), centerY - (height / 2), 
                                          centerX + (width / 6), centerY + (height/2));

        Line2D yLine5 = new Line2D.Double(centerX - (width / 4), centerY - (height / 2), 
                                          centerX - (width /4), centerY + (height/2));
        Line2D yLine6 = new Line2D.Double(centerX - (width / 12), centerY - (height / 2), 
                                          centerX - (width / 12), centerY + (height/2));
        Line2D yLine7 = new Line2D.Double(centerX + (width / 4), centerY - (height / 2), 
                                          centerX + (width / 4), centerY + (height/2));
        Line2D yLine8 = new Line2D.Double(centerX + (width / 12), centerY - (height / 2),
                                          centerX + (width / 12), centerY + (height/2));    
        Line2D yLine9 = new Line2D.Double(centerX - (5*width / 12), centerY - (height / 2), 
                                          centerX - (5*width / 12), centerY + (height/2));
        Line2D yLine10 = new Line2D.Double(centerX + (5*width / 12), centerY - (height / 2), 
                                           centerX + (5*width / 12), centerY + (height/2));       
       
       
        Rectangle2D rectangle = new Rectangle2D.Double(centerX - (width / 2), centerY - (height / 2),
                                                       width, height);

   
        g2.draw(xLine1);
        g2.draw(xLine2);
        g2.draw(xLine3);

        g2.draw(yAxis);

        g2.draw(yLine1);
        g2.draw(yLine2);
        g2.draw(yLine3);
        g2.draw(yLine4);
        g2.draw(yLine5);
        g2.draw(yLine6);
        g2.draw(yLine7);
        g2.draw(yLine8);
        g2.draw(yLine9);
        g2.draw(yLine10);

        g2.draw(rectangle);
   
        drawXAxisLabels(g, RADIANS);
    /*    
              g.drawString("0", (int)centerX - (int)(width / 2) - 10, (int)centerY - (int)(height / 2) + 15);    
              g.drawString("-10", (int)centerX - (int)(width / 2) - 10, (int)(centerY) + 15);
              g.drawString("-20", (int)centerX - (int)(width / 2) - 10, (int)(centerY - (int)(height / 4)) + 15);
              g.drawString("-30", (int)centerX - (int)(width / 2) - 10, (int)(centerY + (int)(height / 4)) + 15);
      */  
     

    }


    /**
     * Gets the width of the rectangular graph
     * @return width of the graph
     */

    public double 
    getWidth()
    {
         return width;
    }


    /**
     * Gets the height of the rectangular graph
     * @return height of the graph
     */

    public double 
    getHeight()
    {
        return height;
    }

    /**
     *  Gets the center on the x plane of the graph
     *  @return center of graph on x plane
     */
    public double 
    getCenterX()
    {
        return centerX;
    }

    /**
     *  Gets the center on the y plane of the graph
     *  @return center of graph on y plane
     */

    public double 
    getCenterY()
    {
        return centerY;
    }

    /**
     * Sets the width of the graph
     * @param w - width of the graph
     */
    public void 
    setWidth(double w)
    {
        width = w;
    }
 
    /**
     * Sets the height of the graph
     * @param h - height of the graph
     */
    public void 
    setHeight(double h)
    {
        height = h;
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
     * Draws the labels on the x axis
     * @param Graphics g
     * @param which - RADIANS or DEGREES
     */
    private void
    drawXAxisLabels(Graphics g, int which)
    {
        if(which == RADIANS)
        {
    
            g.drawString("0", (int)(centerX) - 3, (int)(centerY + (height/2)) + 15);
            g.drawString("\u03C0", (int)(centerX) + (int)(width / 2) - 10, (int)(centerY + (height/2)) + 15);
            g.drawString("\u03C0" + "/2", (int)(centerX) + (int)(width / 4) - 10 , (int)(centerY + (height/2)) + 15);
            g.drawString("\u03C0" + "/6", (int)(centerX) + (int)(width / 12) - 10, (int)(centerY + (height/2)) + 15);
            g.drawString("3" + "\u03C0" + "/4", (int)(centerX) + (int)(5*width / 12) - 10, (int)(centerY + (height/2)) + 15);
            g.drawString("2" + "\u03C0" + "/3", (int)(centerX) + (int)(width / 3) - 10, (int)(centerY + (height/2)) + 15);
            g.drawString("\u03C0" + "/3", (int)(centerX) + (int)(width / 6) - 10, (int)(centerY + (height/2)) + 15); 
            g.drawString("-3" + "\u03C0" + "/4", (int)(centerX) - (int)(5*width / 12) - 10, (int)(centerY + (height/2)) + 15);
            g.drawString("-2" + "\u03C0" + "/3", (int)(centerX) - (int)(width / 3) - 10, (int)(centerY + (height/2)) + 15);
            g.drawString("-\u03C0" + "/2", (int)(centerX) - (int)(width / 4) - 10, (int)(centerY + (height/2)) + 15);
            g.drawString("-\u03C0" + "/3", (int)(centerX) - (int)(width / 6) - 10, (int)(centerY + (height/2)) + 15); 
            g.drawString("-\u03C0" + "/6", (int)(centerX) - (int)(width / 12) - 10, (int)(centerY + (height/2)) + 15);         
            g.drawString("-\u03C0", (int)(centerX) - (int)(width / 2) - 10, (int)(centerY + (height/2)) + 15);
        }
        if(which == DEGREES)
        {
            g.drawString("0", (int)(centerX) - 10, (int)(centerY + (height/2)) + 15);
            g.drawString("180", (int)(centerX) + (int)(width / 2) - 10, (int)(centerY + (height/2)) + 15);
            g.drawString("90", (int)(centerX) + (int)(width / 4) - 10 , (int)(centerY + (height/2)) + 15);
            g.drawString("30", (int)(centerX) + (int)(width / 12) - 10, (int)(centerY + (height/2)) + 15);
            g.drawString("150", (int)(centerX) + (int)(5*width / 12) - 10, (int)(centerY + (height/2)) + 15);
            g.drawString("120", (int)(centerX) + (int)(width / 3) - 10, (int)(centerY + (height/2)) + 15);
            g.drawString("60", (int)(centerX) + (int)(width / 6) - 10, (int)(centerY + (height/2)) + 15); 
            g.drawString("-150", (int)(centerX) - (int)(5*width / 12) - 10, (int)(centerY + (height/2)) + 15);
            g.drawString("-120", (int)(centerX) - (int)(width / 3) - 10, (int)(centerY + (height/2)) + 15);
            g.drawString("-90", (int)(centerX) - (int)(width / 4) - 10, (int)(centerY + (height/2)) + 15);
            g.drawString("-60", (int)(centerX) - (int)(width / 6) - 10, (int)(centerY + (height/2)) + 15); 
            g.drawString("-30", (int)(centerX) - (int)(width / 12) - 10, (int)(centerY + (height/2)) + 15);         
            g.drawString("-180", (int)(centerX) - (int)(width / 2) - 10, (int)(centerY + (height/2)) + 15);
        }
    }
    
   public void 
   drawLabels (String unitType, Graphics g)
   {
          if(unitType.equals("db"))
          {
              g.drawString("0", (int)centerX - (int)(width / 2) - 10, (int)centerY - (int)(height / 2) + 15);    
              g.drawString("-20", (int)centerX - (int)(width / 2) - 10, (int)(centerY) + 15);
              g.drawString("-10", (int)centerX - (int)(width / 2) - 10, (int)(centerY - (int)(height / 4)) + 15);
              g.drawString("-30", (int)centerX - (int)(width / 2) - 10, (int)(centerY + (int)(height / 4)) + 15);
          } 
          else
          {
                g.drawString("1.0", (int)centerX - (int)(width / 2) - 10, (int)centerY - (int)(height / 2) + 15);    
              g.drawString("0.50", (int)centerX - (int)(width / 2) - 10, (int)(centerY) + 15);
              g.drawString("0.75", (int)centerX - (int)(width / 2) - 10, (int)(centerY - (int)(height / 4)) + 15);
              g.drawString("0.25", (int)centerX - (int)(width / 2) - 10, (int)(centerY + (int)(height / 4)) + 15);
          }
   }
 
    private static final int RADIANS = 1;
    private static final int DEGREES = 2;  
          

}