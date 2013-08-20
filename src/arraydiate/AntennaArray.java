package arraydiate;

import java.util.*;

/**
 *  The <code>AntennaArray</code> class represents an array of antennas. 
 *  It also possesses the features of an antenna array including the phase
 *  difference, the spacing between elements, the number of elements in the
 *  array, and the array factor for the array.
 *  
 *  @author Nadia Elfarnawani
 */

public class AntennaArray extends ArrayList<Antenna>
{

	
	// Instance variables
     private double phase;        
     private double spacing;
     private int num;           // number of elements
     private String arrayType;  // nonuniform

     private double beamWidth;  
     private double directivity;
 
     private ArrayFactor afList; // array factor points


    /**
     *  Constructs and initializes the antenna array, initializes
     *  its variables including the array factor and the amplitudes.
     */ 
     public AntennaArray ()
     {
        
        afList = new ArrayFactor();
        num = INIT_NUM;
        phase = INIT_PHASE;
        spacing = INIT_SPACING;
        arrayType = "";             // default  
        resetAmplitudes(INIT_AMP);       // initializes 2 antennas with amplitudes
          
     }    

     /**
      *  Sets the value of the phase difference for the antenna array
      *  @param   phase - the phase value in degrees
      *
      */

      public 
      void setPhase ( double phase )
      {
          this.phase = phase*Math.PI / 180.0;
         
      }


     /**
      *  Gets the value of the phase difference for the antenna array
      *  @return   phase - the phase value in radians
      *
      */

      public 
      double getPhase ()
      {
          return phase;
      }

      /**
       *  Sets the spacing between the elements of the array
       *  @param   spacing - value of the displacement in meters
       *
       */

      public void 
      setSpacing ( double spacing )
      {
          this.spacing = spacing;
      }


      /**
       *  Gets the spacing between the elements of the array
       *  @return   spacing - value of the displacement in meters
       *
       */

      public double 
      getSpacing ()
      {
          return spacing;
      }

     /**
      *  Sets the number of elements in the array.  It adds or removes 
      *  antenna objects as needed.
      *  @param   n - number of elements
      */
      public void 
      setElementNumber ( int n )
      {
          if(n < num)
          {
              for(int i = n; i < num; i++)
              { 
                   removeAntenna();
              }
          }
          if(n > num)
          {
              for (int i = num; i < n; i++)
              {
                   
                   addAntenna(INIT_AMP);
              }
          }
              
          num = n;
      }

     /**
      *  Gets the number of elements in the array
      *  @return   n - number of elements
      */
      public int 
      getElementNumber ()
      {
          return num;

      }

     /**
      *  Searches the antennas for the one with the largest amplitude and
      *  returns this value.
      *  @returns  the value of the largest amplitude in the array
      */
      public double 
      getMaxAmp()
      {
            double temp = 0.0;
            for(int i = 0; i < num; i++)
            {
                Antenna a = (Antenna)get(i);
                if(a.getAmplitude() > temp)
                {
                    temp = a.getAmplitude();
                }
            }
            return temp;
      }

      /**
      *  Searches the antennas for the one with the smallest amplitude and
      *  returns this value.
      *  @return  the value of the smallest amplitude in the array
      */
      public double 
      getMinAmp()
      {
            double temp = MAXAMP;
            for(int i = 0; i < num; i++)
            {
                Antenna a = (Antenna)get(i);
                if(a.getAmplitude() < temp)
                {
                    temp = a.getAmplitude();
                }
            }
            return temp;
      }

     /**
      *  Sets the array type (i.e. uniform, binomial, chebyshev, etc)
      *  @param   arrayType - string representing array type
      */  
      public void 
      setArrayType(String arrayType)
      {
          this.arrayType = arrayType;
      }

     /**
      *  Gets the array type (i.e. uniform, binomial, chebyshev, etc)
      *  @return   arrayType - string representing array type
      */   
      public String 
      getArrayType()
      {
          return arrayType;
      }

      /**
      * Clears the antenna array
      */
 
      public void 
      clearAntennaArray()
      {
          clear();
      }


     /**
      *  Changes the amplitude of a particular element in the array
      *  @param   amplitude - the value of the amplitude
      *  @param   index - index of the element of the array to change
      */ 
      public void 
      changeAmplitude ( double amplitude, int index )
      {
          try{
                Antenna a = (Antenna)get(index);
                a.setAmplitude ( amplitude );
          }catch (IndexOutOfBoundsException e) {
             System.out.println("changing amplitude - index out of bounds");
             
          }
      }


     /**
      * Resets all the amplitudes of the elements in a given array to the
      * value val
      * @param  val - the value of amplitude to set all antennas
      */
      public void 
      resetAmplitudes (double val)
      {
          clear();
         // num = INIT_NUM;
          for(int i = 0; i < num; i++)  // clear antennas from arraylist, then recreate
          {
              Antenna a = new Antenna(val);
              add(i, a);
          }
      }

     
     /**
      * Adds an antenna element to the array with a particular amplitude
      * @param    val - value of the amplitude of the antenna to be added
      */
     
      public void 
      addAntenna(double val) 
      {
          Antenna a = new Antenna(val);
          add(a);
      }


     
     /**
      * Adds an antenna element to the array at a particular index
      * @param    index - index of location to insert antenna object
      * @param    amplitude - amplitude value of the antenna to be inserted
      */
     
      public void 
      addAntenna(int index, double amplitude)
      {
           Antenna a = new Antenna(amplitude);
           add(index, a);
      }


     /**
      * Removes the last antenna element from the antenna array 
      */
      public void 
      removeAntenna()
      {
          remove(size() - 1);
      }

     

/****************************************************************************/
/* Methods compute(), getBeamWidth(), and getDirectivity() are unfinished
   and cannot be used */

      public void compute ()
      {   
    	  double angle_3dB = Math.acos((1 / K*spacing)*(-phase + 2.782 / 10)); 
        
// add here exception ...div by 0 possible

        directivity = 1;
       
        double angleMaxima = Math.acos (phase / (-K*spacing));
        beamWidth = 2*Math.abs(angleMaxima - angle_3dB);       
     }


      public double getDirectivity()
      {
         return directivity;
      }

   

     public double getBeamWidth(double maxAngle)
     {
         double angle1, angle2;
      //   double maxAngle = afList.getMaxAngle();
         angle1 = Math.acos(K*spacing*Math.cos(maxAngle) - 2.782/num);
         angle2 = Math.acos(K*spacing*Math.cos(maxAngle) + 2.782/num); 
         return angle1 - angle2;
     }    

/******************************************************************************/

    /**
     * Gets the array factor for this antenna array
     * @return  the array factor list for this antenna array
     */

     public ArrayFactor getArrayFactor()
     {
         return afList;
     }

    /**
     * Computes the array factor for this antenna array
     * @param  delta - the segment size to divide the angle width of the 
     * array factor for computation
     */
     public void computeAF(int delta)
     {
         afList.computeArrayFactor(num, phase, spacing, this, getMaxAmp(), getMinAmp(), delta);
     }
 
    
    private static final double MAXAMP = 20.0;  // Max amplitude possible for an antenna
    private static final double INIT_AMP = 1.0;  // Max amplitude possible for an antenna
    private static final int INIT_NUM = 2;      // initial # of elements
    private static final double INIT_PHASE = 0.0;  // initial phase difference
    private static final double INIT_SPACING = 0.5; // initial spacing between elements
    private static final double K = 2*Math.PI;
   
    
    private static final long serialVersionUID = 6650162817190710620L;

}
