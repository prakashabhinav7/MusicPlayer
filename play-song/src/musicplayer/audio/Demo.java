package musicplayer.audio;
import java.applet.Applet;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import java.applet.AudioClip;
import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

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
	
	
	URL url = getClass().getResource("/Dangerous.wav");    
	AudioClip clip = Applet.newAudioClip(url);
	
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

    public Demo() throws ParserConfigurationException, SAXException, IOException,FileNotFoundException {
    	
    	
    	URL url = getClass().getResource("/Users/dipit/Documents/java/play-song/resource");

    	Vector<String> toppings = new Vector<>();
    	
    	File inputFile = new File("/Users/dipit/Documents/test.xml");
        DocumentBuilderFactory dbFactory 
           = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(inputFile);
        doc.getDocumentElement().normalize();
        System.out.println("Root element :" 
           + doc.getDocumentElement().getNodeName());
        
        NodeList nList = doc.getElementsByTagName("title");
        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            System.out.println("\nCurrent Element :" 
               + nNode.getTextContent());
            
      	  toppings.add(nNode.getTextContent());
      	  
          
        }
        
        
        System.out.println(toppings);        
    	combobox = new JComboBox(toppings);


        frame.setSize(400,400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        frame.add(combobox);
        
        JButton b1 = new JButton();
        b1.addActionListener(new ActionListener() {


			@Override
			public void actionPerformed(java.awt.event.ActionEvent e) {
				
				clip.play();
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
        String x = String.valueOf(combobox.getSelectedItem());
        System.out.println(x);

        
        JButton b2 = new JButton();
        b2.addActionListener(new ActionListener() {
        	  public void actionPerformed(ActionEvent evt) {
          		  }

      			@Override
      			public void actionPerformed(java.awt.event.ActionEvent e) {				
      				clip.stop();
      			}});
 
       
        	
        frame.setSize(500,500);     
        b2.setSize(400,400);
        b2.setVisible(true);
        b2.setText("Stop");
        frame.add(b2);
              
        frame.setVisible(true);
        
    }

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, UnsupportedAudioFileException, LineUnavailableException,FileNotFoundException ,InterruptedException {
        Demo d = new Demo();
    }
}