package arraydiate;


import java.awt.*;

import javax.swing.*;

import java.awt.event.*;


/**
 *
 *  This program produces a 2D visualization of a radiation pattern for a
 *  linear antenna array of up to 10 elements.
 *
 * @author Nadia Elfarnawani
 */

public class ArraydiateMain {

	
	   
	public static void main(String[] args) {
		
		// set the application title
		System.setProperty("com.apple.mrj.application.apple.menu.about.name", "ArRaydiate");
		// take the menu bar off frame and move it to mac menu bar
		System.setProperty("apple.laf.useScreenMenuBar", "true");
				
		mainFrame = new JFrame("ArRaydiate");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	
		Toolkit tk = Toolkit.getDefaultToolkit();
	     
	    int screenHeight = tk.getScreenSize().height;
	    int  screenWidth = tk.getScreenSize().width;
	 
	    mainFrame.setResizable(false);
	    mainFrame.setSize(screenWidth -30, screenHeight - 30);

	      // set icon image and title of the frame
	    Image iconImage = tk.getImage("images/icon.gif");
	    mainFrame.setIconImage(iconImage);
	    
	      
	  
	      Container cp = mainFrame.getContentPane();
	      ant_array = new AntennaArray();    // initialize antenna array (2 elements)
	      ant_array.setArrayType("general");
	      
	      setToolBar();
	      
	      cp.add(toolBar, BorderLayout.NORTH);
	      
	      setMenuBar();
	      
	      mainFrame.setJMenuBar(menuBar);
	      
	      tabs = new JTabbedPane();     
	      polarPlotPanel = new PolarPlotPanel(ant_array.getArrayFactor());
	      tabs.addTab("Polar", polarPlotPanel);
	      rectPlotPanel = new RectPlotPanel(ant_array.getArrayFactor());
	      tabs.addTab("Rectangular", rectPlotPanel); 
	      instrumentPanel = new InstrumentPanel(ant_array, polarPlotPanel, rectPlotPanel);
	      instrumentPanel.disableAllSliders();
          instrumentPanel.resetSliders(INITIAL_NUM);
          
	      cp.add(instrumentPanel, BorderLayout.EAST);  
	    
	      cp.add(tabs, BorderLayout.CENTER);
	      
	      mainFrame.setVisible(true);
	       
	      // add window listener to exit program on close        
	      mainFrame.addWindowListener(new 
	           WindowAdapter(){
	              public void windowClosing(WindowEvent e){
	                  System.exit(0);
	              }
	           });       

	}
	
	
	   static JFrame mainFrame;
	   static InstrumentPanel instrumentPanel;
	   static PolarPlotPanel polarPlotPanel;
	   static RectPlotPanel rectPlotPanel;
	 
	   static JMenuBar  menuBar;
	   static JMenuItem linearItem,
	             		exitItem,
	             		dataItem,
	             		aboutItem;

	   static JComboBox chooseNonUniform,
	             		chooseLineColor,
	             		chooseGraphColor,
	             		chooseBackgrd;
	 
	   static JCheckBox dbCheckBox;
	   static AntennaArray ant_array;
	   
	   static JToolBar toolBar;
	   
	   static JFrame listFrame;
	   

	   static JTabbedPane tabs;
	
	
	   /**
	    * Initializes the Toolbar and its content
	    * 
	    */ 
	   static void setToolBar () {
		
	      toolBar= new JToolBar("Tools", JToolBar.HORIZONTAL);
	      Font boxFont = new Font("Ariel", Font.PLAIN, 12);
	      Font labelFont = new Font("Ariel", Font.PLAIN, 10);
	      
	      Dimension comboBoxSize = new Dimension (140, 20);

	        
	        dbCheckBox = new JCheckBox("Decibel");
	        dbCheckBox.setFont(boxFont);
	        dbCheckBox.setEnabled(true);
	        dbCheckBox.addActionListener(
	        new ActionListener()
	        {

	           public void actionPerformed (ActionEvent evt)
	           {
	                 if(dbCheckBox.isSelected())
	                 {
	                      polarPlotPanel.setDecibel(true);
	                      rectPlotPanel.setDecibel(true);
	                 }
	                 else {
	                	 polarPlotPanel.setDecibel(false);
	                	 rectPlotPanel.setDecibel(false);   
	                 }           
	                 ant_array.computeAF(1);
	                 polarPlotPanel.repaint();
	                 rectPlotPanel.repaint();
	           }
	        });
	        
	        
	  
	      
	        toolBar.add(dbCheckBox);

	        Box box = Box.createHorizontalBox();
	        JLabel lineColorLabel = new JLabel("Plot Color: ");
	        lineColorLabel.setFont(labelFont);

	        chooseLineColor = new JComboBox();
	        chooseLineColor.setFont(boxFont);
	        chooseLineColor.setEditable(false);
	        
	        chooseLineColor.addItem(new ImageIcon(ArraydiateMain.class.getResource("/bluebar.gif"), "blue"));
	        chooseLineColor.addItem(new ImageIcon(ArraydiateMain.class.getResource("/whitebar.gif"), "white"));
	        chooseLineColor.addItem(new ImageIcon(ArraydiateMain.class.getResource("/blackbar.gif"), "black")); 
	        chooseLineColor.addItem(new ImageIcon(ArraydiateMain.class.getResource("/magentabar.gif"), "magenta"));
	        chooseLineColor.addItem(new ImageIcon(ArraydiateMain.class.getResource("/yellowbar.gif"), "yellow"));
	        chooseLineColor.addItem(new ImageIcon(ArraydiateMain.class.getResource("/redbar.gif"), "redbar"));
	        chooseLineColor.addItem(new ImageIcon(ArraydiateMain.class.getResource("/graybar.gif"), "gray")); 
	        chooseLineColor.setMaximumSize(comboBoxSize);

	        chooseLineColor.addActionListener( new
	            ActionListener()
	            {
	                public void actionPerformed(ActionEvent evt)
	                {
	                     ImageIcon img = (ImageIcon)chooseLineColor.getSelectedItem();
	                     String color = img.getDescription();
	                  
	                     Color c = Color.blue; // default
	                     
	                     if(color.equals("gray")) c = Color.lightGray;
	                     if(color.equals("black")) c = Color.black;
	                     if(color.equals("white")) c = Color.white;
	                     if(color.equals("magenta")) c = Color.magenta;
	                     if(color.equals("yellow")) c = Color.yellow;
	                     if(color.equals("red")) c = Color.red;
	                     
	                     polarPlotPanel.setLineColor(c);
	                     rectPlotPanel.setLineColor(c);
	                     
	              }
	            });

	        box.add(Box.createHorizontalStrut(20));
	        box.add(lineColorLabel);
	        box.add(Box.createHorizontalStrut(5));
	        box.add(chooseLineColor);
	        box.add(Box.createHorizontalStrut(15));

	        JLabel graphColorLabel = new JLabel ("Graph Color: ");
	        graphColorLabel.setFont(labelFont);

	        chooseGraphColor = new JComboBox();
	        chooseGraphColor.setFont(boxFont);
	        chooseGraphColor.setEditable(false);

	        chooseGraphColor.addItem(new ImageIcon(ArraydiateMain.class.getResource("/graybar.gif"), "gray"));
	        chooseGraphColor.addItem(new ImageIcon(ArraydiateMain.class.getResource("/whitebar.gif"), "white"));
	        chooseGraphColor.addItem(new ImageIcon(ArraydiateMain.class.getResource("/blackbar.gif"), "black"));
	         
	        chooseGraphColor.setMaximumSize(comboBoxSize);

	        chooseGraphColor.addActionListener( new
	            ActionListener()
	            {
	                public void actionPerformed(ActionEvent evt)
	                {
	                     ImageIcon img = (ImageIcon)chooseGraphColor.getSelectedItem();
	                     String color = img.getDescription();
	                     Color c = Color.gray; // default
	                     if(color.equals("black")) c = Color.black;
	                     if(color.equals("white")) c = Color.white;
	                     
	                     polarPlotPanel.setGraphColor(c);
	                     rectPlotPanel.setGraphColor(c);
	                     
	                }
	            });

	        Box box2 = Box.createHorizontalBox();
	        box2.add(Box.createHorizontalStrut(10));
	        box2.add(graphColorLabel);
	        box2.add(Box.createHorizontalStrut(5));
	        box2.add(chooseGraphColor);
	        box2.add(Box.createHorizontalStrut(15));        

	        JLabel backgrdLabel = new JLabel ("Background Color: ");
	        backgrdLabel.setFont(labelFont);

	        chooseBackgrd = new JComboBox();
	        chooseBackgrd.setFont(boxFont);
	        chooseBackgrd.setEditable(false);
	        
	        chooseBackgrd.addItem(new ImageIcon(ArraydiateMain.class.getResource("/blackbar.gif"), "black"));
	        chooseBackgrd.addItem(new ImageIcon(ArraydiateMain.class.getResource("/whitebar.gif"), "white"));
	        chooseBackgrd.addItem(new ImageIcon(ArraydiateMain.class.getResource("/graybar.gif"), "gray")); 
	        chooseBackgrd.setMaximumSize(comboBoxSize);

	        chooseBackgrd.addActionListener( new
	            ActionListener()
	            {
	                public void actionPerformed(ActionEvent evt)
	                {
	                     ImageIcon img = (ImageIcon)chooseBackgrd.getSelectedItem();
	                     String color = img.getDescription();
	                     Color c = Color.black; // default
	                     if(color.equals("gray")) c = Color.DARK_GRAY;
	                     if(color.equals("white")) c = Color.white;
	                     
	                     polarPlotPanel.setBackgrd(c);
	                     rectPlotPanel.setBackgrd(c);
                         
	                   
	                }
	            });

	            
	          
	        Box box5 = Box.createHorizontalBox();
	        box5.add(Box.createHorizontalStrut(10));
	        box5.add(backgrdLabel);
	        box5.add(Box.createHorizontalStrut(5));
	        box5.add(chooseBackgrd);
	        box5.add(Box.createHorizontalStrut(15));
	       

	        toolBar.add(box);
	        toolBar.addSeparator();
	        toolBar.add(box2);
	        toolBar.addSeparator();
	        toolBar.add(box5);
	        
	}
	
	   /**
	    * Initializes the Menu Bar
	    * 
	    */ 
	static void setMenuBar () {
		
		menuBar = new JMenuBar();
	      
	      JMenu fileMenu = new JMenu("File");
	      fileMenu.setMnemonic ('F');
	    
	      JMenu helpMenu = new JMenu("About");
	      helpMenu.setMnemonic ('A');     

	      exitItem = fileMenu.add("Exit");
	      exitItem.addActionListener (new
	           ActionListener () {
	              public void actionPerformed (ActionEvent evt) {
	                  System.exit(0);
	              }
	           });
	       

	      aboutItem = helpMenu.add("About");

	      aboutItem.addActionListener (new
	           ActionListener () {
	               String aboutMsg = "ArRaydiate Software - Radiation Pattern Synthesis for Linear Arrays\n\n\n" + 
	            		   "An antenna array consists of several identical antennas connected\n" + 
	                       "and assembled to form a single antenna. In a linear array, the antenna \n" +
	            		   "elements are positioned along a straight line and have uniform separation. \n" + 
	                       "Antenna arrays produce improved radiation patterns that a single antenna \n" + "" +
	                       "cannot achieve. \n\n" + 
	                       "This program generates a 2D visualization of a radiation pattern for a \n" + 
	                       "linear antenna array of up to 10 elements. The direction of radiation for \n" + 
	                       "the main beam(s) can be steered by changing the phase difference \u03B2. The \n" +
	                       "beamwidth of the main lobe and the quantity of grating lobes are affected by \n" +
	                       "changing the element spacing (lower spacing = larger beamwidth and less grating \n" + 
	                       "lobes). On the polar graph, the antenna array is positioned at the center of the \n" + 
	                       "graph, and the earth's plane is represented by the 0¡ to 180¡ line. A radiation \n" +
	                       "beam that points toward 90¡ is pointing toward the sky. ";
	               
	               public void actionPerformed (ActionEvent evt) 
	               {
	            	   ImageIcon icon = new ImageIcon(getClass().getResource("/antenna_array.jpg"));
	            	   
	            	   JOptionPane.showMessageDialog(mainFrame, aboutMsg, "About", JOptionPane.INFORMATION_MESSAGE, icon);
	
	               }
	           });

	      
	        

	     // menuBar.add (fileMenu);
	    
	      menuBar.add (helpMenu);

	}
	
	
	
	private static final int INITIAL_NUM = 2;

	
	
}
