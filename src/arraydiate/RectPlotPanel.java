/*
   @(#)RectPlotPanel.java
*/

package arraydiate;

import java.awt.*;

import javax.swing.*;

import java.util.*;

/**
 * The <code>RectPlotPanel</code> is a subclass of <code>JPanel</code>.  * It is responsible for the construction of the rectangular plot of  * the array
 * factor of an antenna array.  This class contains the method
 * <tt>plot</tt> to plot the data points of the specified instance
 * of the <code>ArrayFactor</code> object.
 *
 * @author Nadia Elfarnawani
 */

public class RectPlotPanel extends JPanel
{
/**
	 * 
	 */
	
	// Instance Variables
    private static RectGraph rect;     // rectangular grid
    private ArrayFactor af;            // AF that contains data to plot       
    private Color lineColor;           // color of plot line
    private Color graphColor;          // color of polar grid
    private int unit;                  // decibel or linear
    private double centerX;
    private double centerY;
    

    public RectPlotPanel (ArrayFactor af)
    {   
    
        setBackground(Color.black);
        graphColor = Color.gray;
        lineColor = Color.blue;
        this.af = af;
        setCenterX(getWidth() / 2.0);
        setCenterY(getHeight() / 2.0); 
        rect = new RectGraph(centerX, centerY, 8.*getWidth() / 9., getHeight() / 3.);
        unit = LINEAR;        

        repaint();
        
        JLabel title = new JLabel("Radiation Pattern");
        title.setForeground(Color.cyan);
        add(title);
    }
        
    public void paintComponent(Graphics g)
    {
        super.paintComponent (g);
        Graphics2D g2 = (Graphics2D) g;
        setCenterX(getWidth() / 2.0);
        setCenterY((getHeight() / 2.0)); 
        rect.setCenterX(centerX);
        rect.setCenterY(centerY);
        rect.setWidth(8.*getWidth() / 9.);
        rect.setHeight(getHeight() / 3.);         
        g2.setColor(graphColor);
        rect.paintRectGraph (g2);
        if (af.size() != 0)
        {
            try{
                if (unit != DB)
                   rect.drawLabels("linear",  g2);
                else if (unit == DB)
                   rect.drawLabels("db", g2);
            } catch (Exception e) {}
        }
        g.setColor(lineColor);
        plot(g2, af);
    }

  
   
    public double getCenterX()
    {
         return centerX;
    }

    public double getCenterY()
    {
         return centerY;
    }

    public void setCenterX(double x)
    {
         centerX = x;
    }

    public void setCenterY(double y)
    {
         centerY = y;
    }    

    public void setBackgrd(Color c)
    {
        setBackground(c);
        repaint();
       
    }
 
    public void setGraphColor(Color c)
    {

        graphColor = c;
        repaint();
    
    }

    public void 
    setLineColor(Color c)
    {
       lineColor = c;
        repaint();
    }


    // throw no such type exception
    public void 
    setDecibel(boolean b)
    {
        if (b == false)
            unit = LINEAR;
        else unit = DB;
    }

    public void 
    plot(Graphics2D g, ArrayFactor af)
    {   
         ArrayList<DataPoint> temp = new ArrayList<DataPoint>();
         DataPoint temp_dp, d1, d2;
        
         int centerX = (int)this.centerX;
         int centerY = (int)(this.centerY + rect.getHeight() / 2);
         double magnitude, angle;
         double x, y;

   
         for(int i = 0; i < af.size(); i++)
         {
              
               d1 = (DataPoint)af.get(i);
               
               angle = d1.getAngle();
               if (angle <= Math.PI)
               { 
                   x = (angle)/(Math.PI) * (rect.getWidth()/2.);
                   magnitude = d1.getMagnitude() / af.getMaxAF();  // normalize to fit within plot boundaries
                   if(unit == DB)
                   {    magnitude = 10*Math.log(d1.getMagnitude() / af.getMaxAF()) + 40.0;
                   		if (magnitude > 0.0)
                   			y = magnitude*(rect.getHeight() / 40.0);
                   		else y = 0.0;
                   } else y = magnitude*rect.getHeight();

                   temp_dp = new DataPoint(x, y);
                   temp.add(i, temp_dp);
               }

         }
         
         g.setStroke(new BasicStroke(5));  // set stroke thickness
  
         // plot right side (0 to PI)
         for(int i = 0; i < temp.size(); i++)
         {
              try{
	              d1 = (DataPoint)temp.get(i);
	              d2 = (DataPoint)temp.get(i+1);
	            
		          g.drawLine((int)d1.getX() + centerX, centerY - (int)d1.getY(), (int)d2.getX() + centerX, centerY - (int)d2.getY());
	 
             } catch (Exception e) {
            	 // do nothing
             }
        
         }
         
         // plot left side (0 to -PI)
         for(int i = 0; i < temp.size(); i++)
         {
              try{
	              d1 = (DataPoint)temp.get(i);
	              d2 = (DataPoint)temp.get(i+1);
              
	              g.drawLine(centerX - (int)d1.getX(), centerY - (int)d1.getY(), centerX - (int)d2.getX(), centerY - (int)d2.getY());
	              
              } catch (Exception e) { }
         }    
        
    }
    
    private static final int LINEAR = 0;
    private static final int DB = 1;
    
    private static final long serialVersionUID = 2049204334584147097L;

}    