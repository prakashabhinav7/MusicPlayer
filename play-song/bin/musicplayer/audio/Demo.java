package musicplayer.audio;
import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import java.util.*;
import java.util.List;
//import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import java.awt.*;
import java.io.File;
import java.io.IOException;
public class Demo {

    JFrame frame = new JFrame("");
//    AutoCompleteDecorator decorator;
    JComboBox combobox;

    public Demo() throws ParserConfigurationException, SAXException, IOException {
    	Vector<String> toppings = new Vector<>();
    	Vector<String> toppings1 = new Vector<>();


//        List<String> toppings = new ArrayList<String>();
//    	ArrayList<String> toppings = new ArrayList<>(list.size());


    	
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
        frame.setVisible(true);
    }

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        Demo d = new Demo();
    }
}