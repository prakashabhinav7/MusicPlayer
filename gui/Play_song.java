import org.w3c.dom.*;
import org.xml.sax.SAXException;

import musicplayer.audio.Audio;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.xml.parsers.*;
import java.io.*;
import  sun.audio.*;    //import the sun.audio package
import  java.io.*;



public class Play_song {
	private static Audio audio;
	
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, UnsupportedAudioFileException, LineUnavailableException, InterruptedException{

	         File inputFile = new File("/Users/dipit/Documents/test.xml");
	         DocumentBuilderFactory dbFactory 
	            = DocumentBuilderFactory.newInstance();
	         DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	         Document doc = dBuilder.parse(inputFile);
	         doc.getDocumentElement().normalize();
	         System.out.println("Root element :" 
	            + doc.getDocumentElement().getNodeName());
	         
	         NodeList nList = doc.getElementsByTagName("from");
	         for (int temp = 0; temp < nList.getLength(); temp++) {
	             Node nNode = nList.item(temp);
	             System.out.println("\nCurrent Element :" 
	                + nNode.getTextContent());
	             
	   	     for (int i=0 ;i<2;i++ )
	   	     {
	             
	         FileInputStream in = new FileInputStream(nNode.getTextContent());
	         
	   	     AudioStream as = new AudioStream(in); 
	   	     AudioPlayer.player.start(as); 
	   	     System.out.println("Test");
//	   	     AudioPlayer.player.sleep(audio.getTotalSecs());;
	         Thread.sleep(Audio.getTotalSecs());
	         System.out.println(Audio.getTotalSecs());
	   	     }}
	         
	         

	      


	   // Create an AudioStream object from the input stream.
	              

	   // Use the static class member "player" from class AudioPlayer to play
	   // clip.
	                

//	   // Similarly, to stop the audio.
//	      AudioPlayer.player.stop(as); 
	     
	     
	}
	


}
