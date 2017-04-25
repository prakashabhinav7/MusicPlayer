package musicplayer.audio;
import java.applet.Applet;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import java.applet.AudioClip;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBuilder;
import javafx.scene.control.MenuItem;
import javafx.scene.control.MenuItemBuilder;
import javafx.scene.control.Slider;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextBuilder;
import javafx.stage.FileChooser;
import javafx.stage.FileChooserBuilder;
import javafx.stage.Stage;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import java.util.*;
import java.util.List;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
public class Demo {

	private boolean isPlaying=false;
	private Text nowPlayingText;
	private Audio audio;
	private Sound1 sound1;
	private String name;
	 private Slider playbackSlider;
	 Point p = new Point(200,200);
    JFrame frame = new JFrame("");
    JPanel box = new JPanel();
    JPanel Hbox = new JPanel();

  

//    JLabel label1 = new JLabel("", SwingConstants.LEFT); 
//    JLabel label2 = new JLabel("", JLabel.CENTER); 
//    JLabel label3 = new JLabel("", JLabel.CENTER);
//    JLabel label4 = new JLabel("", JLabel.CENTER);
//    JLabel label5 = new JLabel("", JLabel.CENTER);
//    JLabel label6 = new JLabel("", JLabel.CENTER);

   
//    AutoCompleteDecorator decorator;
    JComboBox combobox;

    public Demo() throws ParserConfigurationException, SAXException, IOException,FileNotFoundException, XPathExpressionException, LineUnavailableException {
    	
    	URL url = getClass().getResource("/Users/dipit/Documents/java/play-song/resource");
		Clip clip = AudioSystem.getClip();

    	Vector<String> song_titles = new Vector<>();
    	Vector<String> song_path = new Vector<>();
    	
    	File inputFile = new File("/Users/dipit/Documents/MusicPlayer/play-song/src/test.xml");
        DocumentBuilderFactory dbFactory 
           = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(inputFile);

        
        XPathFactory xPathfactory = XPathFactory.newInstance();
        XPath xpath = xPathfactory.newXPath();        
//        NodeList nodeList = (NodeList) 

        
        combobox = new JComboBox(song_titles);

        frame.setSize(400,400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());
        



//        panel.setLayout(null);


//        statusLabel.setSize(400,200);
//        frame.add(statusLabel);


//		JLabel statuslabel = new JLabel("aaa");
//	    panel.add(statuslabel);
//	    Dimension size = statuslabel.getPreferredSize();
//	    statuslabel.setBounds(100, 100, size.width/10, size.height/10);
		
		
		
//        frame.getContentPane().setBackground( Color.white );
        
        doc.getDocumentElement().normalize();
        System.out.println("Root element :" 
           + doc.getDocumentElement().getNodeName());
        NodeList nList = doc.getElementsByTagName("title");
        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
//            System.out.println("\nCurrent Element :" 
//               + nNode.getTextContent());
            
      	  song_titles.add(nNode.getTextContent());
      	  
        }
      	NodeList nList1 = doc.getElementsByTagName("from");
        for (int temp1 = 0; temp1 < nList1.getLength(); temp1++) {
            Node nNode1 = nList1.item(temp1);
//            System.out.println("\nCurrent Element :" 
//               + nNode1.getTextContent());
            
      	  song_path.add(nNode1.getTextContent());
        }
          
        
        
      System.out.println(song_path);
                
        frame.add(combobox);

        
        JButton b1 = new JButton();
        b1.addActionListener(new ActionListener() {


			@Override
			public void actionPerformed(java.awt.event.ActionEvent e) {
				
		        String x = '\'' +  String.valueOf(combobox.getSelectedItem())+'\'' ;

		        
		        NodeList nodeList;
				try {
					;
					nodeList = (NodeList)xpath.compile("/playlist/song[@title="+x+"]").evaluate(doc, XPathConstants.NODESET);
					System.out.println(nodeList.item(0).getTextContent());
					String songPath=nodeList.item(0).getTextContent().split("\n")[2].replaceAll(" ", "");
					String Album = nodeList.item(0).getTextContent().split("\n")[3].replaceAll(" ", "");
					String Artist =  nodeList.item(0).getTextContent().split("\n")[4].replaceAll(" ", "");
					String year = nodeList.item(0).getTextContent().split("\n")[5].replaceAll(" ", "");
					
					clip.close();
					
					clip.open(AudioSystem.getAudioInputStream(new File(songPath)));
					
			        clip.start();
			        JLabel artist = new JLabel(Artist);
			        JLabel album = new JLabel(Album);
			        JLabel Year = new JLabel(year);
//			        JLabel nl1 = new JLabel("<html><br></html>");

			        JLabel a1 = new JLabel("null");
			        JLabel a2 = new JLabel("null");
			        JLabel a3 = new JLabel("null");
			        JLabel image= new JLabel();
			        ImageIcon imageIcon = new ImageIcon(new ImageIcon("/Users/dipit/Desktop/closer.jpg").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));

			        image.setIcon(imageIcon);
			        
			        //			        Dimension maximumSize = new Dimension((30,30));
//					image.size
			        a1.setText("<html>Artist:</html>");
			        a2.setText("<html><br>Album:</html>");
			        a3.setText("<html><br>Year::</html>");
			        Hbox.removeAll();
			        Hbox.setLayout(new BoxLayout(Hbox, BoxLayout.X_AXIS));
			        Hbox.add(image);

			        
			        box.removeAll();
			        
			        box.setLayout(new BoxLayout(box, BoxLayout.Y_AXIS));
			        box.add(a1);
			        box.add(artist);
//			        box.add(nl1);
			        
			        box.add(a2);
			        box.add(album);
			        
			        
			        box.add(a3);
			        box.add(Year);
			        
			        
			        
			        frame.add(box);
			        frame.add(Hbox);
			        frame.setVisible(true);
			        
			        

				} catch (XPathExpressionException | LineUnavailableException | IOException | UnsupportedAudioFileException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			}});
  
        frame.setSize(500,500);     
        b1.setSize(400,400);
        b1.setVisible(true);
        b1.setText("Play");

        frame.add(b1);
        frame.setVisible(true);
    
        
//      Stop Button  
        
        JButton b2 = new JButton();
        b2.addActionListener(new ActionListener() {
        	  public void actionPerformed(ActionEvent evt) {
          		  }

      			@Override
      			public void actionPerformed(java.awt.event.ActionEvent e) {		
      				
      				clip.stop();
      				clip.close();
      			}});
             	
        frame.setSize(500,500);     
        b2.setSize(400,400);
        b2.setVisible(true);
        b2.setText("Stop");
        frame.add(b2);
              
   
        
//        Loop Button
        JButton b3 = new JButton();
        b3.addActionListener(new ActionListener() {
        	  public void actionPerformed(ActionEvent evt) throws LineUnavailableException, IOException, UnsupportedAudioFileException {
        		  
        		 
          		  }

      			@Override
      			public void actionPerformed(java.awt.event.ActionEvent e) {
      				 for(int i=0;i<song_path.size();i++)
           		  {
           			  try {
               			  clip.close();
						clip.open(AudioSystem.getAudioInputStream(new File(song_path.get(i))));
						System.out.println(clip.getMicrosecondLength());
   			            clip.start();
   	      				System.out.println(clip.getMicrosecondPosition());
   			            Thread.sleep(clip.getMicrosecondLength()/1000);


					} catch (LineUnavailableException | IOException | UnsupportedAudioFileException | InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
           			  
           		  }
      				
//      			
      			}});
        
        frame.setSize(500,500);     
        b3.setSize(400,400);
        b3.setVisible(true);
        b3.setText("Repeat");
        frame.add(b3);
        

              
        frame.setVisible(true);
        
        JButton b4 = new JButton();
        b4.addActionListener(new ActionListener() {
        	  public void actionPerformed(ActionEvent evt) {
          		  }

      			@Override
      			public void actionPerformed(java.awt.event.ActionEvent e) {		
      				
      				clip.stop();
      				clip.close();
      			}});
             	
        frame.setSize(500,500);     
        b4.setSize(400,400);
        b4.setVisible(true);
        b4.setText("Next");
        frame.add(b4);
        frame.setVisible(true);
//        label.setDefaultLocale(null);        

       
        
        
        }
    
    
    
    
    
    
    

    
    
    
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, UnsupportedAudioFileException, LineUnavailableException,FileNotFoundException ,InterruptedException, XPathExpressionException {
        Demo d = new Demo();
    }
}