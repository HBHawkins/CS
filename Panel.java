package UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Timer;


public class Panel extends JPanel {

    JTextArea textArea = new JTextArea(10,40);
    JTextField textField = new JTextField(12);
    JTextField userSpeed = new JTextField(12);
    
    JButton instructionButton = new JButton("Instructions");
    JButton startButton = new JButton("Start");
    JButton submitButton = new JButton("Submit");
    JButton homeScreen = new JButton("Back");
    JButton addText = new JButton("Add Text");
    JButton wpm250 = new JButton("250 WPM");
    JButton wpm350 = new JButton("350 WPM");
    JButton wpm450 = new JButton("450 WPM");
    
    JScrollPane scrollPane = new JScrollPane(textArea);
    

    
    
    boolean clicked = false;
    boolean mouseHover = false;
    boolean startClicked = false;
    boolean instructionClicked = false;
    boolean homeScreenButton = false;
    boolean submit = false;
    boolean wpm1 = false;
    boolean wpm2 = false;
    boolean wpm3 = false;
    boolean b = false;
    
    String input;
    String userText;
	String printed = "";
    
    int speed = 1;
	int currentSpot = 0;
	int j = 0;
	int tick = 1;
	boolean bryan = true;

    public Panel() {
        add(instructionButton);
        add(startButton);

        startButton.setFocusable(false);
        instructionButton.setFocusable(false);
        addActionListener();
    	setBackground(Color.LIGHT_GRAY);
    	printTimer();
    	
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics = (Graphics2D)g;
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setColor(Color.BLACK);
        
        Font titleFont = new Font("Courier", Font.BOLD, 64);
        Font instructions = new Font("", Font.BOLD, 24);
        
        
        graphics.setFont(titleFont);
      
        if(instructionClicked) { //instruction page
        	graphics.setFont(titleFont);
        	graphics.drawString("Instructions", 175, 75);
        	graphics.setFont(instructions);
        	graphics.drawString("1. Type or paste text into the \"Enter text\" field.", 100, 200);
        	graphics.drawString("2. Click the \"Submit\" button.", 100, 300);
        	graphics.drawString("3. Click on the words per minute you want to read at.", 100, 400);
            add(homeScreen);
        	homeScreen.setSize(150,75);
        	homeScreen.setLocation(630,485);
        }
        else if (startClicked) { //start page
        	graphics.setFont(instructions);
        	graphics.drawString("Enter Text:", 200, 50);
            add(homeScreen);
        	homeScreen.setSize(150,75);
        	homeScreen.setLocation(630,485);
        	add(submitButton);
        	submitButton.setSize(200,100);
        	submitButton.setLocation(300,400);
        	add(addText);
        	addText.setSize(100,20);
        	addText.setLocation(490,33);
        	scrollPane.setLocation(180,210);
        	textField.setLocation(330,33);
        	graphics.drawString("Current Text", 330, 180);
        }
        else if (submit) { //WPM page
        	add(wpm250);
        	wpm250.setSize(200,100);
        	wpm250.setLocation(300,100);
        	add(wpm350);
        	wpm350.setSize(200,100);
        	wpm350.setLocation(300,250);
        	add(wpm450);
        	wpm450.setSize(200,100);
        	wpm450.setLocation(300,400);
        	wpm250.setFocusable(false);
        	wpm350.setFocusable(false);
        	wpm450.setFocusable(false);
        	userSpeed.setLocation(500,250);
        	
        }
        else if (wpm1 || wpm2 || wpm3) {
        	String letter;
        	int offset = 0;
        	if (printed.length() == 2) {
        		letter = printed.charAt(0)+"";
        	}
        		
        	else if (printed.length() == 3 || printed.length() == 4 ) {
        		letter = printed.charAt(1)+"";
        		offset = 38;
        	}
        	else if (printed.length() == 5 || printed.length() == 6 ) {
        		letter = printed.charAt(2)+"";
        		offset = 76;
        	}
        	else if (printed.length() == 7 || printed.length() == 8 ) {
        		letter = printed.charAt(3)+"";
        		offset = 114;
        	}
        	else {
        		letter = printed.charAt(4)+"";
        		offset = 152;
        	}
        	graphics.drawString(printed, 385 - offset, 300);        	
        	graphics.setColor(Color.RED);
        	graphics.drawString(letter, 385, 300);
        	graphics.setColor(Color.BLACK);
        	graphics.setFont(titleFont);
        	graphics.fillRect(400, 0, 5, 250);
        	graphics.fillRect(400, 325, 5, 300);
        }

        else { //default page
        	graphics.drawString("Fast Text", 225, 125);
        	graphics.drawString("Reader", 275, 175);
        	instructionButton.setSize(150,75);
        	instructionButton.setLocation(325,450);
        	startButton.setSize(300,150);
        	startButton.setLocation(250,250);

        }
        


    }
       
    public void addActionListener(){ 
        instructionButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                instructionClicked = true;
                instructionButton.setVisible(false);
                startButton.setVisible(false);
                homeScreen.setVisible(true);
                
                repaint();
            }
        });
        
        startButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                startClicked = true;
                startButton.setVisible(false);
                instructionButton.setVisible(false);
                homeScreen.setVisible(true);
                add(scrollPane);
                textArea.setEditable(false);
                textArea.setLineWrap(true);
                textArea.setWrapStyleWord(true);
                
                add(textField);
                
                repaint();
            }
        });
        
        homeScreen.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                homeScreenButton = true;
                homeScreen.setVisible(false);
                instructionButton.setVisible(true);
                instructionClicked = false;
                startButton.setVisible(true);
                startClicked = false;
                submit = false;
                remove(scrollPane);
                remove(textField);
                remove(addText);
                remove(submitButton);
                remove(wpm250);
                remove(wpm350);
                remove(wpm450);
                repaint();
            }
        });
        
        addText.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	String text = textField.getText();
            	textArea.append(text);
                remove(addText);
                repaint();
            }
        });
        
        submitButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	submit = true;
            	startClicked = false;
            	userText = textArea.getText();
            	wpm250.setVisible(true);
            	wpm350.setVisible(true);
            	wpm450.setVisible(true);
           
            	
            	remove(scrollPane);
                remove(textField);
                remove(addText);
                remove(submitButton);
                repaint();
            }
        });
        
        wpm250.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	wpm1 = true;
            	wpm2 = false;
            	wpm3 = false;
            	submit = false;
            	speed = 180;
            	remove(wpm250);
            	remove(wpm350);
            	remove(wpm450);
            	remove(homeScreen);
            	repaint();
            }
        });
        
        wpm350.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	wpm1 = false;
            	wpm2 = true;
            	wpm3 = false;
            	submit = false;
            	speed = 120;
            	remove(wpm250);
            	remove(wpm350);
            	remove(wpm450);
            	remove(homeScreen);
            	repaint();
            }
        });
        
        wpm450.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	wpm1 = false;
            	wpm2 = false;
            	wpm3 = true;
            	submit = false;
            	speed = 90;
            	remove(wpm250);
            	remove(wpm350);
            	remove(wpm450);
            	remove(homeScreen);
            	repaint();
            }
        });
        
        
    }    
    
    public void printTimer() {
    	Timer time = new Timer(1, new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			if (tick % speed == 0 && (wpm1 || wpm2 || wpm3)) {
    				boolean go = true;
    				for (int i = currentSpot + 1; i < userText.length() && go; i++) {
    					if (userText.charAt(i) == ' ') {
    						printed = userText.substring(currentSpot, i + 1);
    						currentSpot = i;
    						go = false;
    					}
    				}
    			repaint();
    			}
    			tick++;
    		}
    	});
    	time.start();
    }
}

/*
On a cold day in April of 1984, a man named Winston Smith returns to his home, a dilapidated apartment building called Victory Mansions. Thin, frail, and thirty-nine years old, it is painful for him to trudge up the stairs because he has a varicose ulcer above his right ankle. The elevator is always out of service so he does not try to use it. As he climbs the staircase, he is greeted on each landing by a poster depicting an enormous face, underscored by the words “BIG BROTHER IS WATCHING YOU.”
*/
