/*
   @(#)PolarPlotPanel.java
*/

package arraydiate;

import java.awt.*;
import javax.swing.*;

import java.util.*;


/**
 * The <code>PolarPlotPanel</code> is a subclass of <code>JPanel</code>.  
 * It is responsible for the construction of the polar plot of the array
 * factor of an antenna array.  This class contains the method
 * <tt>plot</tt> to plot the data points of the specified instance
 * of the <code>ArrayFactor</code> object.
 *
 * @author Nadia Elfarnawani
 */

public class PolarPlotPanel extends JPanel
{
/**
	 * 
	 */
	
	// Instance Variables
    private static PolarGraph polar;   // the polar grid
    
    private ArrayFactor af;            // AF that contains data to plot
    
    private Color lineColor;           // color of plot line
    private Color graphColor;          // color of polar grid
    private int unit;                  // decibel or linear
    private double centerX;
    private double centerY;
    private double radius;
    
  

    public PolarPlotPanel (ArrayFactor af)
    {   
    
        setBackground(Color.black);
        graphColor = Color.gray;
        lineColor = Color.blue;
        this.af = af;
        setCenterX(getWidth() / 2.0);
        setCenterY(getHeight() / 2.0);
        
        
        polar = new PolarGraph(centerX, centerY, 4.*getWidth()/9.);
       
        unit = LINEAR;        
        repaint();
        
        JLabel title = new JLabel("Radiation Pattern");
        title.setForeground(Color.cyan);
        add(title);

    }
        
    public void 
    paintComponent(Graphics g)
    {
        super.paintComponent (g);
        Graphics2D g2 = (Graphics2D) g;
        setCenterX(getWidth() / 2.0);
        setCenterY((getHeight() / 2.0)+9); 
        polar.setCenterX(centerX);
        polar.setCenterY(centerY); 
        polar.setRadius(4*getHeight() / 9.);        
        g2.setColor(graphColor);
        polar.paintPolarGraph (g2);

        if (af.size() != 0)
        {
            try{
                if (unit != DB)
                   polar.drawLabels("linear",  g2);
                else if (unit == DB)
                   polar.drawLabels("db", g2);
            } catch (Exception e) {}
        }


        g2.setColor(lineColor);
        plot(g2, af);
    }

    public void
    setRadius(double radius)
    {
         this.radius = radius;
    }

    public double 
    getRadius()
    {
         return radius;
    }   

    public double 
    getCenterX()
    {
         return centerX;
    }

    public double 
    getCenterY()
    {
         return centerY;
    }

    public void 
    setCenterX(double x)
    {
         centerX = x;
    }

    public void 
    setCenterY(double y)
    {
         centerY = y;
    }    

    public void 
    setBackgrd(Color c)
    {
        setBackground(c);
        repaint();
       
    }
 
    public void 
    setGraphColor(Color c)
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
         DataPoint temp_dp, d1;
         DataPoint p1, p2;
         int centerX = (int)this.centerX;
         int centerY = (int)this.centerY;
         double magnitude, magnitude_DB, angle;
         double x, y;

 
         for(int i = 0; i < af.size(); i++)
         {
               temp_dp = new DataPoint();
               d1 = (DataPoint)af.get(i);
               angle = d1.getAngle();
               magnitude = d1.getMagnitude() / af.getMaxAF();
               if (unit == DB)
               {	
                   magnitude = 10*Math.log(magnitude) + 40.0;
           
                   if (magnitude > 0.0){
	               magnitude *= polar.getRadius() / 40.0;
                   } else magnitude = 0.0;
               } else magnitude *= polar.getRadius();

    //           magnitude_DB = d1.getAbsMagnitude_DB();
    //           magnitude_DB = (polar.getRadius() / //af.getMaxAF_DB())*magnitude_DB;
                   x = Math.sin(angle - Math.PI/2)*magnitude;
                   y = Math.cos(angle - Math.PI/2)*magnitude;
          
                   temp_dp.setLocation(x, y);
                   temp.add(i, temp_dp);
                   
              
  
         }

         for(int i = 0; i < af.size(); i++)
         {
              try{
	              p1 = (DataPoint)temp.get(i);
	              p2 = (DataPoint)temp.get(i+1);
	
	
	              g.setStroke(new BasicStroke(5));
	              g.drawLine((int)p1.getX() + centerX, (int)p1.getY() + centerY, (int)p2.getX() + centerX, (int)p2.getY() + centerY);
	            

           
             } catch (Exception e) {
            	 // do nothing
             }
        
         }      
    }

    
    
    private static final int LINEAR = 0;
    private static final int DB = 1;
    
    private static final long serialVersionUID = -1946016085652110693L;

}    