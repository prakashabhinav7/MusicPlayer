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
//import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
public class Demo {
	
	
	
	
//	private Clip clip;
	
	
	
	private boolean isPlaying=false;
	private Text nowPlayingText;
	private Audio audio;
	private Sound1 sound1;
	private String name;
	 private Slider playbackSlider;
    JFrame frame = new JFrame("");
//    AutoCompleteDecorator decorator;
    JComboBox combobox;

    public Demo() throws ParserConfigurationException, SAXException, IOException,FileNotFoundException, XPathExpressionException, LineUnavailableException {
    	
    	URL url = getClass().getResource("/Users/dipit/Documents/java/play-song/resource");
		Clip clip = AudioSystem.getClip();

    	Vector<String> toppings = new Vector<>();
    	
    	File inputFile = new File("/Users/dipit/Documents/MusicPlayer/test.xml");
        DocumentBuilderFactory dbFactory 
           = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(inputFile);

        
        XPathFactory xPathfactory = XPathFactory.newInstance();
        XPath xpath = xPathfactory.newXPath();        
//        NodeList nodeList = (NodeList) 
        
        
        combobox = new JComboBox(toppings);

        frame.setSize(400,400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        
        doc.getDocumentElement().normalize();
        System.out.println("Root element :" 
           + doc.getDocumentElement().getNodeName());
        
//        
//        System.out.println("Child element :" 
//                + doc.
        
        NodeList nList = doc.getElementsByTagName("title");
        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            System.out.println("\nCurrent Element :" 
               + nNode.getTextContent());
            
      	  toppings.add(nNode.getTextContent());
      	  
          
        }
        
//      System.out.println(toppings);        

        
        
        frame.add(combobox);
        
        JButton b1 = new JButton();
        b1.addActionListener(new ActionListener() {


			@Override
			public void actionPerformed(java.awt.event.ActionEvent e) {
				
		        String x = '\'' +  String.valueOf(combobox.getSelectedItem())+'\'' ;

//		        System.out.println(x);
		        
		        NodeList nodeList;
				try {
					nodeList = (NodeList)xpath.compile("/playlist/song[@title="+x+"]").evaluate(doc, XPathConstants.NODESET);
			    	Vector<String> song_vector = new Vector<>();
//			    	song_vector.add(nodeList.item(0).getTextContent().length());
					System.out.println(nodeList.item(0).getTextContent());
					System.out.println(nodeList.item(0).getTextContent().split("\n")[2]);
					
					String songPath=nodeList.item(0).getTextContent().split("\n")[2].replaceAll(" ", "");
					System.out.println("2");

//					URI uri = new URI(songPath);
//					System.out.println(uri);
//
//					
////					URL url = URLEncoder.encode(s);
//					System.out.println("test"+url);
					
			        clip.open(AudioSystem.getAudioInputStream(new File(songPath)));
			        clip.start();
			        

					
//					AudioClip clip = Applet.newAudioClip(url);
//					clip.play();

					
//			    	System.out.println(song_vector.get(1));

				} catch (XPathExpressionException | LineUnavailableException | IOException | UnsupportedAudioFileException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				

//				clip.stop();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				isPlaying=true;
			}});
  
        frame.setSize(500,500);     
        b1.setSize(400,400);
        b1.setVisible(true);
        b1.setText("Play");

        frame.add(b1);
        frame.setVisible(true);
        

   
        
        

//
//        for (int i = 0; i < nodeList.getLength(); i++) {
//            Node nNode = nodeList.item(i);
////            System.out.println("\nCurrent Element :" 
////               + nNode.getTextContent());
//        }
        
//        System.out.println(nodeList.item(0).getTextContent());

        
        
    	
        
//        System.out.println(x);

        
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
              
        frame.setVisible(true);
        
    }

    
    
    
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, UnsupportedAudioFileException, LineUnavailableException,FileNotFoundException ,InterruptedException, XPathExpressionException {
        Demo d = new Demo();
    }
}