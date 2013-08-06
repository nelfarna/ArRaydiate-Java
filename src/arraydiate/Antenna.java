package arraydiate;

/**
 * The <code>Antenna</code> class represents a single antenna within the antenna array.
 * 
 * @author Nadia Elfarnawani
 */

public class Antenna
{

   private double amplitude;

   public Antenna (double amplitude)
   {
       this.amplitude = amplitude;
   }

   public void setAmplitude ( double amplitude )
   {
       this.amplitude = amplitude;
   }

   public double getAmplitude ()
   {
       return amplitude;
   }

 
}
