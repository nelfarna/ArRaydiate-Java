
/*
 @(#)ArrayFactor.java
*/
package arraydiate;

import java.util.*;

/**
 *   The <code>ArrayFactor</code> class is an <code>ArrayList</code> of  
 *   type <code>DataPoint</code> that represents each point of data in 
 *   the array factor.
 *   
 *    
 *   @author Nadia Elfarnawani
 */    
public class ArrayFactor extends ArrayList<DataPoint>
{

  /**
	 * 
	 */
	
	// Instance variables
    private double angleMaxima;
    private double maxAF_DB, maxAF;


  /**
   * Constructs and initializes the array factor
   */
    public ArrayFactor ()
    {
        angleMaxima = 0.0;
        maxAF = maxAF_DB = 0.0;
    }

  /**
   * Sets the angle at which the maximum value of the AF occurs
   * @param angle - the value to set the angle of the max magnitude
   */    
    
    public void 
    setMaxAngle ( double angle )
    {
         angleMaxima = angle;
    }

  /**
   * Gets the angle at which the maximum value of the AF occurs
   * @return the angle of the maximum value in the array factor list
   */
     public double 
     getMaxAngle ()
     {
         return angleMaxima;
     }

  /**
   * Gets the value of the maximum magnitude of the array factor
   * @return - the maximum value of the array factor
   */
     public double 
     getMaxAF()
     {
         return maxAF;
     }

  /**
   * Gets the value of the maximum magnitude (in dB) of the af
   * @return  the maximum value(in dB) of the af
   */  
     public double 
     getMaxAF_DB()
     {
          return maxAF_DB;
     }

  /**
   * Clears the array factor list of all data points.
   */
      
     public void 
     clearAF()
     {
          super.clear();
     }

  
     /**
      *  Calculates and sets the value of the maximum of the array factor.
      *  This value is then used to normalize the plot, so that the maximum value 
      *  always remains within the plots boundaries.
      */ 
     
      public void 
      setMaxAF()
      {
          double maxAF_DB, maxAF, temp1, temp2;
          maxAF = 0.0;
          maxAF_DB = 0.0;

          for(int n = 0; n < this.size(); n++)
          {
               DataPoint data = (DataPoint)this.get(n);
               temp1 = data.getAbsMagnitude_DB();
               temp2 = data.getMagnitude();
               if (temp1 > maxAF_DB)
                    maxAF_DB = temp1;
               if (temp2 > maxAF)
                    maxAF = temp2;     
          }
                            
          this.maxAF_DB = maxAF_DB;
          this.maxAF = maxAF;
     }          

    /**
      *  Computes the array factor (AF) of the antenna array.  It calculates 
      *  the AF at each increment (+ delta) of the angle, theta, and saves
      *  the value in the arraylist dataSet.
      *  @param   num - number of elements in antenna array
      *  @param   phase - the phase difference of the antenna array
      *  @param   spacing - the displacement between the elements of the array
      *  @param   antennas - the list of antennas and their amplitudes
      *  @param   maxAmp - maximum possible amplitude an antenna may have
      *  @param   minAmp - minimum possible amplitude an antenna may have 
      *  @param   delta -  angle segment size
      */
      public void
      computeArrayFactor (int num, double phase, double spacing, 
                          AntennaArray antennas, double maxAmp, double minAmp, int delta)
      {
          DataPoint data;
          double d = Math.toRadians ( delta );
          double real, imag, magnitude, magnitude_DB, absMag_DB;
          double prevMax = 0.0;
          clearAF();
        
          
          for(double theta = 0.0; theta <= 2*PI; theta += d) 
          {   
                         
              real = realAF(theta, num, phase, spacing, antennas);
              imag = imagAF(theta, num, phase, spacing,antennas);
          
         // Compute Array Factor in decibel form, create data point with               
         // magnitude and angle values, and store it in the array list   

              magnitude = Math.sqrt(real*real + imag*imag);
            

       
              if(magnitude > prevMax)
              {
                  prevMax = magnitude;
                  angleMaxima = theta;
              }

              data = new DataPoint();
         
              data.setMagnitude(magnitude);


              data.setAngle(theta);   
              add(data); 

                        
          }
          setMaxAF();
    }

      /**
       *  Computes the real part of the array factor
       *  @param theta - the angle of a particular point of the array factor
       *  @param num - number of elements
       *  @param phase - phase difference of the antenna array
       *  @param spacing - displacement between the elements
       *  @param antennas - array of antennas and their amplitudes
       */ 
      public double 
      realAF (double theta, int num, double phase, double spacing, AntennaArray antennas)
      {
          double real = 0;
        

          for(int n = 1; n <= num; n++)
          {
             try{
               Antenna a = (Antenna)antennas.get(n-1);
               real += a.getAmplitude()*Math.cos((n-1)*(K*spacing*Math.cos(theta) + phase));
             } catch (IndexOutOfBoundsException e) {}
          }
          return real;
      }     


       
     /**
       *  Computes the imaginary part of the array factor
       *  @param theta - the angle of a particular point of the array factor
       *  @param num - number of antenna elements
       *  @param phase - phase difference of the antenna array
       *  @param spacing - displacement between the elements
       *  @param antennas - array of antennas and their amplitudes
       */ 

      public double 
      imagAF (double theta, int num, double phase, double spacing, AntennaArray antennas)
      {
        double imag = 0;
        
        for(int n = 1; n <= num; n++)
        {
           try{
            Antenna a = (Antenna)antennas.get(n-1);
            imag += a.getAmplitude()* Math.sin((n-1)*(K*spacing*Math.cos(theta) + phase));
           } catch (IndexOutOfBoundsException e){}
        }
        return imag;
    }     

    private static final double K = 2*Math.PI;
    private static final double PI = Math.PI;
    
    private static final long serialVersionUID = -1959974229576433060L;
}