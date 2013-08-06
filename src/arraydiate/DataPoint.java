/*
  @(#)DataPoint.java
*/

// package array;
package arraydiate;


import java.awt.*;
import java.text.*;

/**
 * The <code>DataPoint</code> class represents a data point (or location) for  * the array factor in 2-D space.  It specifies the magnitude value (both
 * linear and dB) and the angle at which this value occurs in the
 * array factor. 
 * 
 * @author Nadia Elfarnawani
 */

public class DataPoint extends Point
{
  /**
	 * 
	 */
	
	// Instance variables
    private double magnitude, magnitude_DB, absMag_DB;
    private double angle;

  // Constructors:

    /**
     * Constructs a point at the origin (0,0) and initializes all variables
     * to 0.0
     */
    public DataPoint()
    {
        setLocation(0, 0); 
        magnitude = 0.0;         /* value of magnitude of af */
        magnitude_DB = 0.0;      /* value in linear decibel  */
        angle = 0.0;             /* angle at which value occurs */
        absMag_DB = 0.0;         /* absolute value of the decibel value */
    }

    /**
     * Constructs and initializes a point at the location of x and y (both
     *  integers)
     */
    public 
    DataPoint (int x, int y)
    { 
        super(x, y);
    }

    /** 
     * Constructs and initializes a point at the location of x and y (both
     * doubles) 
     */
    public 
    DataPoint (double x, double y)
    {
        setLocation(x, y);
    }

    /** 
     * Constructs and initializes a point at the location represented by a
     * point. 
     */
    public 
    DataPoint (DataPoint dp)
    {
        super(dp);
    }
     
    /**
     *  Sets the magnitude value for this point in the af
     *  @param  magnitude - the value of the magnitude at this point
     */
    public void 
    setMagnitude (double magnitude)
    {
        this.magnitude = magnitude;
    }
 
    /**
     *  Gets the magnitude value for this point in the af
     *  @return the value of the magnitude at this point
     */
    public double 
    getMagnitude ()
    {
        return magnitude;
    }

    /**
     *  Sets the decibel magnitude value for this point in the af
     *  @param  magnitude - the value of the magnitude in dB
     */
    public void 
    setMagnitude_DB (double magnitude)
    {
        this.magnitude_DB = magnitude;
    }
 
    /**
     *  Gets the decibel magnitude value for this point in the af
     *  @return the value of the magnitude at this point
     */
    public double 
    getMagnitude_DB ()
    {
        return magnitude_DB;
    }
  
    /**
     *  Sets the decibel magnitude value for this point in the af
     *  @param  val - the absolute value the magnitude in dB
     */
    public void 
    setAbsMagnitude_DB(double val)
    {
        absMag_DB = val;
    }
 
    /**
     *  Gets the absolute value of the dB magnitude for this point in the af
     *  @return  the value absolute value of the magnitude at this point
     */
    public double 
    getAbsMagnitude_DB()
    {
        return absMag_DB;
    }   
    
    /**
     *  Sets the angle for this point in the af
     *  @param  angle - the value of the angle in radians
     */
    public void 
    setAngle ( double angle )
    {
        this.angle = angle;
    }
 
    /**
     *  Gets the angle for this point in the af
     *  @return  the value of the angle (radians) at this point
     */
    public double 
    getAngle ()
    {
        return angle;
    }

    /**
     *  Returns a string representation of this point.  The string includes
     *  information about the magnitude value, magnitude value in dB, and the 
     *  angle at this point of the af.  The location (x, y) is not included
     *  in the string.
     */
    public String 
    toString()
    {   
       // double magnitude_DB;   
        NumberFormat formatter = NumberFormat.getNumberInstance();
        formatter.setMaximumFractionDigits(3);
        formatter.setMinimumFractionDigits(3);
        String angString1 = formatter.format(angle);
        String angString2 = formatter.format(Math.toDegrees(angle));
        String magString1 = formatter.format(magnitude);
        String magString2 = formatter.format(magnitude_DB);

        return angString1 + " rad (" + angString2 + " deg) : " + magString1 + " " + magString2 + " dB ";
    }  

    private static final long serialVersionUID = -8710426066521623359L;
    
}