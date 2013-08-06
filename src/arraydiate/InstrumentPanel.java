package arraydiate;

import java.awt.*;

import javax.swing.*;

import java.awt.event.*;

import javax.swing.border.EmptyBorder;
import javax.swing.event.*;


/**
 *  Constructs the instrument panel for controlling the radiation pattern
 *  
 *  @author Nadia Elfarnawani
 */

public class InstrumentPanel extends JPanel
{   
    
	private JTextField[] texts = new JTextField[MAX_ELEMENTS];
    private JLabel[] antIcons = new JLabel[MAX_ELEMENTS];
    private JSlider[] ampSlider = new JSlider[MAX_ELEMENTS];
    private JSlider phaseSlider;
    private JSlider numSlider;
    private JTextField numText;
    private JTextField phaseText;
    private JSlider spaceSlider;
    private JTextField spacingText;
    private Box[] box = new Box[10];
    private JLabel[] numLabel = new JLabel[10];
    private JLabel phaseLabel;
    private JLabel phaseMarker1, phaseMarker2;
    private JLabel spacingLabel;
    private JLabel spacingMarker1, spacingMarker2;
    private JComboBox chooseNumber;  
    
    private JButton resetButton;
    
    private ImageIcon antIconOn = null; 
    private ImageIcon antIconOff = null;

    private Component c1, c2;
    private AntennaArray ant_array;

    public InstrumentPanel (AntennaArray aa, Component cp1, Component cp2)
    {
    	this.c1 = cp1;
        this.c2 = cp2;
 
        ant_array = aa;
        
        setBorder(new EmptyBorder(20, 20, 20, 20) );
        JPanel imgpnl = new JPanel();  
        
        JLabel imglbl = new JLabel();  
        
        try
        {
	        imglbl.setIcon(new ImageIcon(getClass().getResource("/antenna_array.jpg")));  
	        imgpnl.add(imglbl);  
        }
        catch (Exception e){}
        
        Font labelFont = new Font("Ariel", Font.ITALIC, 12);
        Box sliderBox = Box.createHorizontalBox();
  
        Box infoBox = Box.createHorizontalBox();
        
        JLabel infoLabel = new JLabel("");
        
        infoLabel.setFont (labelFont);
        
        infoBox.add(imgpnl);
        infoBox.add(infoLabel);

        setBorder (BorderFactory.createRaisedBevelBorder());

        Dimension textSize = new Dimension (50, 20);
       
           

        
        Box numberBox = Box.createHorizontalBox();
        JLabel elementNumLabel = new JLabel("No. of Antennas: ");
        elementNumLabel.setFont (labelFont);
        

        chooseNumber = new JComboBox();
        chooseNumber.setEnabled(false);
        chooseNumber.setFont(labelFont);
        chooseNumber.setEditable(false);
        chooseNumber.addItem("2");
        chooseNumber.addItem("3");
        chooseNumber.addItem("4");
        chooseNumber.addItem("5");
        chooseNumber.addItem("6");
        chooseNumber.addItem("7");
        chooseNumber.addItem("8");
        chooseNumber.addItem("9");
        chooseNumber.addItem("10");

        chooseNumber.setMaximumSize(new Dimension(63, 22));

        chooseNumber.addActionListener(new 
            ActionListener()             {
                 public void actionPerformed(ActionEvent evt)
                 {
                     try{
                       int new_num = Integer.parseInt((String)chooseNumber.getSelectedItem());
                       int old_num = ant_array.getElementNumber();
                       ant_array.setElementNumber(new_num);

                       if(new_num > old_num)
                       {
                           for(int n = old_num; n < new_num; n++)
                           {                        
                                                              
                                setAmpSlider(n);
                           }   
                       }
                           
                       if (new_num < old_num)
                       {

                           for(int m = old_num - 1; m >= new_num; m--)
                           {
                               
                                disableAmpSlider(m);
                           }
                       }   
           
                     } catch (Exception e) {}
                 }
        });

        numberBox.add(Box.createHorizontalStrut(5));
        numberBox.add(elementNumLabel);
        numberBox.add(Box.createHorizontalStrut(5));
        numberBox.add(chooseNumber);
        numberBox.add(Box.createHorizontalStrut(5));

       
        /*
         * Amplitude sliders (max 10 antennas)
         */
        
         
        try
        {
        	antIconOn = new ImageIcon(getClass().getResource("/ant_icon.gif"));
        	antIconOff = new ImageIcon(getClass().getResource("/ant_icon_gray.gif"));
        }
        catch (Exception ex)
        {
        	
        }
        
       
        
        for (int i = 0; i < 10; i++) 
        {
        	
            box[i] = Box.createVerticalBox();
            
            antIcons[i] = new JLabel();
            antIcons[i].setIcon(antIconOff);
            antIcons[i].setHorizontalAlignment(JLabel.RIGHT);
            antIcons[i].setAlignmentX(Component.CENTER_ALIGNMENT);
            ampSlider[i] = new JSlider(SwingConstants.VERTICAL, MIN_AMPLITUDE, MAX_AMPLITUDE, 0); 
            ampSlider[i].setMajorTickSpacing(200);
            ampSlider[i].setMinorTickSpacing(50);
            ampSlider[i].setPaintTicks(true);
            ampSlider[i].setToolTipText("Amplitude of Element " + (i+1));

            texts[i] = new JTextField ("0.0", 3);
            texts[i].setFont(new Font("Ariel", Font.PLAIN, 11));
            numLabel[i] = new JLabel(String.valueOf(i+1), SwingConstants.LEFT);
            numLabel[i].setFont(labelFont);
          
 
            ampSlider[i].addChangeListener(new SliderListener(texts[i], aa, i, c1, c2));
     
            ampSlider[i].setEnabled(false);
            texts[i].setEnabled(false);
            texts[i].setMaximumSize(textSize);
            texts[i].addActionListener(new TextListener(aa, i));

            box[i].add(antIcons[i]);
            box[i].add(texts[i]);
            box[i].add(ampSlider[i]);
            box[i].add(numLabel[i]);
            
            sliderBox.add(box[i]);
            sliderBox.add(Box.createHorizontalStrut(13));
          
        }
         
         JLabel amplitudeLabel = new JLabel("Relative Currents (Amplitudes)");
         amplitudeLabel.setFont (labelFont);
         amplitudeLabel.setBackground(Color.white);
         amplitudeLabel.setOpaque(true);
        
        
         Box amplitudeBox = Box.createHorizontalBox();
         amplitudeBox.add(amplitudeLabel);

         Box phaseSliderBox = Box.createVerticalBox();
         Box phaseBox = Box.createHorizontalBox();
        

         phaseSlider = new JSlider(SwingConstants.HORIZONTAL, MIN_DEGREES, MAX_DEGREES, 0);

         phaseSlider.setMajorTickSpacing(36);
         phaseSlider.setMinorTickSpacing(6);
         phaseSlider.setPaintTicks(true);
         phaseSlider.setEnabled(false);

         phaseLabel = new JLabel("Phase Difference ( \u03B2 ) / Steering Angle");   
         phaseLabel.setFont(labelFont);
         phaseLabel.setBackground(Color.white);
         phaseLabel.setOpaque(true);
         phaseLabel.setHorizontalAlignment(SwingConstants.LEFT);
         
         Box phaseLabelBox = Box.createHorizontalBox();
         phaseLabelBox.add(phaseLabel);
        
         phaseMarker1 = new JLabel("-180\u00B0");                                
         phaseMarker2 = new JLabel("+180\u00B0");
         Box phaseMarkerBox = Box.createHorizontalBox();
         phaseMarkerBox.add(Box.createHorizontalStrut(49));
         phaseMarkerBox.add(phaseMarker1);
         phaseMarkerBox.add(Box.createHorizontalGlue());
         phaseMarkerBox.add(phaseMarker2);

         phaseText = new JTextField ("0", 4);
         phaseText.setEnabled(false);
         phaseText.setMaximumSize( textSize );
         phaseText.addActionListener(new TextListener(aa, 1));
 
         phaseSlider.addChangeListener(new SliderListener(phaseText, aa, 1, c1, c2));
         phaseSlider.setToolTipText("Vary the phase difference (steering angle) between the antenna elements.");

       
         phaseSliderBox.add(phaseLabelBox);
         
         phaseBox.add(phaseText);

         phaseBox.add(phaseSlider);
         
         phaseSliderBox.add(phaseBox);

         phaseSliderBox.add(phaseMarkerBox);
   


         Box spaceSliderBox = Box.createVerticalBox(); 
         Box spaceBox = Box.createHorizontalBox();

         spaceSlider = new JSlider(SwingConstants.HORIZONTAL, MIN_SPACING, MAX_SPACING, SP_INITIAL_VALUE);

         spaceSlider.setMajorTickSpacing(50);
         spaceSlider.setMinorTickSpacing(10);
         spaceSlider.setPaintTicks(true);
         spaceSlider.setEnabled(false);      
         spaceSlider.setToolTipText("Vary the spacing between the antenna elements.");
    
         spacingLabel = new JLabel ("Spacing Between Antennas (Measured in Wavelengths: \u03BB )");                
         spacingLabel.setFont(labelFont);
         spacingLabel.setBackground(Color.white);
         spacingLabel.setOpaque(true);
         
         Box spacingLabelBox = Box.createHorizontalBox();
         spacingLabelBox.add(spacingLabel);

         Box spacingMarkerBox = Box.createHorizontalBox();
 
         spacingMarker1 = new JLabel("0\u03BB");                        
         spacingMarker2 = new JLabel("2.5\u03BB");                      
         spacingMarkerBox.add(Box.createHorizontalStrut(59));
         spacingMarkerBox.add(spacingMarker1);
         spacingMarkerBox.add(Box.createHorizontalGlue());
         spacingMarkerBox.add(spacingMarker2);

    
         spacingText = new JTextField("0.50", 4);
         spacingText.setEnabled(false);
         spacingText.setMaximumSize (textSize);
         spacingText.addActionListener(new TextListener(aa, 1));

         spaceSlider.addChangeListener(new SliderListener(spacingText, aa, 1, c1, c2));

         spaceSliderBox.add(spacingLabelBox);
         spaceBox.add(spacingText);         
         spaceBox.add(spaceSlider);
         spaceSliderBox.add(spaceBox);
         spaceSliderBox.add(spacingMarkerBox);   

         phaseSlider.setValue(0);
         spaceSlider.setValue(SP_INITIAL_VALUE);
         phaseText.setText("0.0");
         spacingText.setText("0.50");
 
 
         resetButton = new JButton("Reset");
   
         resetButton.addActionListener(new 
               ActionListener()
               {
                   public void actionPerformed(ActionEvent evt)
                   {
                        ant_array.resetAmplitudes(0.0);
                       // disableAllSliders();
                        int num = Integer.parseInt((String)chooseNumber.getSelectedItem());                         
                        resetSliders(num);
                   }
               });
         resetButton.setEnabled(false);
         resetButton.setToolTipText("Restore Default");

         Box buttonBox = Box.createHorizontalBox();
         buttonBox.add(Box.createHorizontalStrut(80));
         buttonBox.add(resetButton, BorderLayout.CENTER);
         
         Box comboBox = Box.createVerticalBox();
         comboBox.add(Box.createVerticalStrut(20));
         comboBox.add(phaseSliderBox);
         comboBox.add(Box.createVerticalStrut(20));
         comboBox.add(spaceSliderBox); 
         comboBox.add(Box.createVerticalStrut(20));
         comboBox.add(buttonBox);

         Box finalBox = Box.createVerticalBox();
         
         finalBox.add(infoBox);
         finalBox.add(Box.createVerticalStrut(10));
         finalBox.add(numberBox);
         finalBox.add(Box.createVerticalStrut(20));
         finalBox.add(sliderBox);
         finalBox.add(Box.createVerticalStrut(5));
         finalBox.add(amplitudeBox);
         finalBox.add(Box.createVerticalStrut(40));
         finalBox.add(comboBox);
         

      
         add(finalBox, BorderLayout.CENTER);
      

     }

    public void resetSliders (int nEnable)
    {
       int i = 2;
         
       chooseNumber.setEnabled(true);
       phaseSlider.setEnabled(true);
       phaseText.setEnabled(true);
       spaceSlider.setEnabled(true);
       spacingText.setEnabled(true);
       resetButton.setEnabled(true);
 
       chooseNumber.setSelectedItem(String.valueOf(nEnable));
       phaseSlider.setValue(0);
       spaceSlider.setValue(SP_INITIAL_VALUE);
       phaseText.setText("0.0");
       spacingText.setText("0.50");
       
       for(int j = 0; j < nEnable; j++)
       {
    	 antIcons[i].setIcon(antIconOn);
         ampSlider[j].setEnabled(true);  
         ampSlider[j].setValue(AMP_INITIAL_VALUE);   
       
         
         texts[j].setEnabled(true);
   
          i = j;
       }

       for (int k = i+1; k < MAX_ELEMENTS; k++)
       {
    	   antIcons[k].setIcon(antIconOff);
           ampSlider[k].setValue(0);
           texts[k].setText("0.0");
           ampSlider[k].setEnabled(false);
           texts[k].setEnabled(false);

       }

    }
    
  
    public void setAmpSlider(int index)
    {
         
        
     
    	try{       
	        if(ant_array.getArrayType().equals("uniform"))
	        {
	                
	            double prev_val = ampSlider[0].getValue();                        
	            ampSlider[index].setValue((int)prev_val);        
	
	        }
	        else
	        {
	            ampSlider[index].setValue(AMP_INITIAL_VALUE);
	            
	        }
    	} catch (Exception e) {
    		System.out.println(e.toString());
    	}
		antIcons[index].setIcon(antIconOn);
		ampSlider[index].setEnabled(true);
		texts[index].setEnabled(true);
            
    }
                 

    public void disableAmpSlider(int index)
    {
        
           ampSlider[index].setValue(0);
           texts[index].setText(String.valueOf(0.0));
     
           ampSlider[index].setEnabled(false);
           texts[index].setEnabled(false);

         
           
           antIcons[index].setIcon(antIconOff);
    }

    public void disableAllSliders ()
    {
       chooseNumber.setSelectedItem("2");
       chooseNumber.setEnabled(false);
       phaseSlider.setValue(0);
       spaceSlider.setValue(SP_INITIAL_VALUE);
       phaseText.setText("0.0");
       spacingText.setText("0.50");

       phaseSlider.setEnabled(false);
       phaseText.setEnabled(false);
       spaceSlider.setEnabled(false);
       spacingText.setEnabled(false);
       resetButton.setEnabled(false);

       for(int j = 0; j < ant_array.getElementNumber(); j++)
       {
    	  antIcons[j].setIcon(antIconOn);
          ampSlider[j].setValue(0);
          ampSlider[j].setEnabled(false);
          texts[j].setEnabled(false);
     
       }
      
    }

    private class SliderListener implements ChangeListener
    {
        JTextField text; 
        AntennaArray ant_array;
        int index;
        Component c1, c2;


        SliderListener ( JTextField text, AntennaArray aa, int sliderNum, Component c1, Component c2)
        {  
            this.text = text;
            ant_array = aa;
            index = sliderNum;
            this.c1 = c1;
            this.c2 = c2; 
       
        }
 
        public void stateChanged(ChangeEvent event)
        {
            JSlider source = (JSlider)event.getSource();
            double value = source.getValue();
            String type = ant_array.getArrayType();
        
            if(source == ampSlider[index])
            {  
                if(type.equals("uniform"))
                {
                    
                    for(int i = 0; i < ant_array.getElementNumber(); i++)
                    { 
                        ant_array.changeAmplitude((value/100.0), i);
                        texts[i].setText(String.valueOf(value/100.0));
                        ampSlider[i].setValue((int)value);
                     
                    }
                }else {
                   ampSlider[index].setEnabled(true);
            
                   ant_array.changeAmplitude((value / 100.0), index);
                   text.setText(String.valueOf(value / 100.0));
                }
          
            }
            else if (source == phaseSlider)
            {
                
                ant_array.setPhase(value);
                text.setText(String.valueOf(value));
        
            }
            else if (source == spaceSlider)
            {
                ant_array.setSpacing(value / 100.0);
                text.setText(String.valueOf(value / 100.0));
        
            }
          
            ant_array.computeAF(1);
            c1.repaint();
            c2.repaint();
         }
     }  
 
     private class TextListener implements ActionListener
     {
         AntennaArray ant_array;
         int index;
     

         TextListener(AntennaArray aa, int sliderNum)
         {
            ant_array = aa;
            index = sliderNum;
         
         }

         public void actionPerformed(ActionEvent event)
         {
             JTextField text = (JTextField)event.getSource();
             try{
                double value = Double.parseDouble(text.getText()); 
                
                if(text == numText)
                {
                   ant_array.setElementNumber((int)value);
                   numSlider.setValue((int)value);
                }             

                if(text == spacingText)
                {
                   ant_array.setSpacing(value);
                   value = 100.0*value;
                   spaceSlider.setValue((int)value);
                }
                if(text == phaseText)
                {
                   value = value % MAX_DEGREES;
                   ant_array.setPhase(value);
                   phaseSlider.setValue((int)value);
                }
               if(text == texts[index])
               {
                   ant_array.changeAmplitude(value, index);
                   value = 100.0*value;
                   ampSlider[index].setValue((int)value);
               }
           }catch (Exception e) {
            Toolkit tk = Toolkit.getDefaultToolkit();
            tk.beep();
           }
          }
       }

    
    private static final int MAX_DEGREES = 180;
    private static final int MIN_DEGREES = -180;

    private static final int MAX_AMPLITUDE = 2000;
    private static final int MIN_AMPLITUDE = 0;
    private static final int AMP_INITIAL_VALUE = 100;
    
    private static final int MAX_ELEMENTS = 10;
    private static final int MAX_SPACING = 250;
    private static final int MIN_SPACING = 10;
    private static final int SP_INITIAL_VALUE = 50;
    
    private static final long serialVersionUID = 8465620186088062463L;
             
}




