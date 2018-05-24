package Snake;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.imageio.IIOException;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Timer;

public class Panel extends JPanel implements KeyListener{
	Snake snake = new Snake();
    JTextArea textArea = new JTextArea(10,40);
    JTextField textField = new JTextField(12);
    JTextField userSpeed = new JTextField(12);
    JButton startButton = new JButton("Start Game");
    JButton settingsButton = new JButton("Settings");
    JButton backButton = new JButton("Back");
    JButton snakeOrange = new JButton("");
    JButton snakeGreen = new JButton("");
    JButton snakeRed = new JButton("");
    JButton snakeYellow = new JButton("");
    JButton snakeBlue = new JButton("");
    JButton foodOrange = new JButton("");
    JButton foodGreen = new JButton("");
    JButton foodRed = new JButton("");
    JButton foodYellow = new JButton("");
    JButton foodBlue = new JButton("");
    JButton bounds = new JButton("Easy Mode");

    JButton titleButton = new JButton("Title Screen");
    
    JScrollPane scrollPane = new JScrollPane(textArea);
    
    int score = 1;
    
    boolean up = false;
    boolean down = false;
    boolean right = false;
    boolean left = false;
    boolean buttonStart = false;
    boolean startClicked = false;
    boolean gameOver = false;
    boolean started = false;
    boolean settingsClicked = false;
    boolean easyMode = false;
    
    int previousVelocity = 0;
    static int cSnake = 1;
    static int cFood = 1;
    static int tick = 1;

    public Panel() {
        startButton.setFocusable(false);
        //instructionButton.setFocusable(false);
        addKeyListener(this);
        setFocusable(true);
    	setBackground(Color.BLACK);
    	printTimer();
    	add(startButton);
    	addActionListener();
    	add(settingsButton);
    	readFile();
    }
    


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics = (Graphics2D)g;
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setColor(Color.BLACK);
        Font titleFont = new Font("Courier", Font.BOLD, 64);
        Font settingsFont = new Font("", Font.BOLD, 32);
        Font scoreFont = new Font("", Font.BOLD, 24);
        graphics.setFont(titleFont);
        Color snakeC = Color.BLACK;
        Color foodC = Color.BLACK;
        
        if (cSnake == 1) 
        	snakeC = Color.GREEN;
        else if (cSnake == 2)
        	snakeC = Color.RED;
        else if (cSnake == 3)
        	snakeC = Color.BLUE;
        else if (cSnake == 4)
        	snakeC = Color.ORANGE;
        else if (cSnake == 5)
        	snakeC = Color.YELLOW;
         
        if (cFood == 1) 
        	foodC = Color.RED;
        else if (cFood == 2)
        	foodC = Color.ORANGE;
        else if (cFood == 3)
        	foodC = Color.BLUE;
        else if (cFood == 4)
        	foodC = Color.GREEN;
        else if (cFood == 5)
        	foodC = Color.YELLOW;

        
        if (startClicked) {
        	graphics.setColor(foodC);
        	
        	
        	snake.moveSnake(graphics);
        	snake.drawSnake(graphics);
        	for(int i = 1; i < snake.body.size(); i++) {
        		if (snake.body.get(0).xPos == snake.body.get(i).xPos && snake.body.get(0).yPos == snake.body.get(i).yPos) {
        			gameOver = true;
        			
        		}
        	}
        	if (easyMode == false) {
        		if (snake.body.get(0).xPos < 0 || snake.body.get(0).xPos > 1175 || snake.body.get(0).yPos < 0 || snake.body.get(0).yPos > 650) {
        			gameOver = true;
        			
        		}
        	}
        	else {
        		if (snake.body.get(0).xPos < 0)
        			snake.body.get(0).xPos = 1175;
        		else if (snake.body.get(0).xPos > 1175)
        			snake.body.get(0).xPos = 0;
        		else if (snake.body.get(0).yPos < 0)
        			snake.body.get(0).yPos = 650;
        		else if (snake.body.get(0).yPos > 650)
        			snake.body.get(0).yPos = 0;
        	}
        	
        	if (gameOver) {
        		graphics.setColor(Color.WHITE);
        		graphics.drawString("GAME OVER", 400, 300);
        	
        		add(titleButton);
        		titleButton.setSize(200,100);
        		titleButton.setLocation(500,500);
        	}
        
        	
        		if (snake.foodX == snake.body.get(0).xPos && snake.foodY == snake.body.get(0).yPos) {
        		snake.addTail();
        		snake.addFood();
        		score++;
        		}
        	
        	snake.startFood();
        	graphics.setColor(foodC);
        	if (gameOver == false)
        		graphics.fillRect(snake.foodX, snake.foodY, 25, 25);
        	graphics.setFont(scoreFont);
        	graphics.setColor(Color.WHITE);
        	graphics.drawString("Score: " + score, 1075, 25);
        	graphics.setColor(foodC);
        }
        
        else if (settingsClicked) {
        	
        	startClicked = false;
        	graphics.setColor(Color.WHITE);
        	graphics.drawString("Settings", 450, 100);
        	backButton.setSize(100, 100);
        	backButton.setLocation(1088, 563);
        	graphics.setFont(settingsFont);
        	graphics.drawString("Snake Color", 500, 200);
        	graphics.drawString("Food Color", 500, 440);
        	bounds.setBounds(990, 10, 200, 100);
        	snakeOrange.setBounds(350, 250, 100, 100);
        	snakeOrange.setBackground(Color.ORANGE);
        	snakeRed.setBounds(450, 250, 100, 100);
        	snakeRed.setBackground(Color.RED);
        	snakeGreen.setBounds(550, 250, 100, 100);
        	snakeGreen.setBackground(Color.GREEN);
        	snakeYellow.setBounds(650, 250, 100, 100);
        	snakeYellow.setBackground(Color.YELLOW);
        	snakeBlue.setBounds(750, 250, 100, 100);
        	snakeBlue.setBackground(Color.BLUE);
        	foodOrange.setBounds(350, 500, 100, 100);
        	foodOrange.setBackground(Color.ORANGE);
        	foodRed.setBounds(450, 500, 100, 100);
        	foodRed.setBackground(Color.RED);
        	foodGreen.setBounds(550, 500, 100, 100);
        	foodGreen.setBackground(Color.GREEN);
        	foodYellow.setBounds(650, 500, 100, 100);
        	foodYellow.setBackground(Color.YELLOW);
        	foodBlue.setBounds(750, 500, 100, 100);
        	foodBlue.setBackground(Color.BLUE);
        	
        	
        }
        else {
        	graphics.setColor(Color.WHITE);
        	graphics.drawString("Snake", 500, 100);
        	graphics.setFont(settingsFont);
        	if (easyMode) {
        		graphics.drawString("Easy", 1075, 30);
        	}
        	else graphics.drawString("Normal", 1075, 30);
        	graphics.setFont(titleFont);
        	graphics.setColor(snakeC);
        	graphics.fillRect(450, 225, 200, 25);
        	graphics.setColor(foodC);
        	graphics.fillRect(700, 225, 25, 25);
        	startButton.setSize(200, 100);
        	startButton.setLocation(500,400);
        	settingsButton.setSize(100,50);
        	settingsButton.setLocation(550,550);
        }
    }
    
	@Override
	public void keyPressed(KeyEvent e) {
		if (gameOver == false) {
			if (snake.currentVelocity != 3) {
				if (e.getKeyCode() == 37) {
					snake.currentVelocity = 1;
					System.out.println("left");
					repaint();
				}
			}
			if (snake.currentVelocity != 4) {
				if (e.getKeyCode() == 38) {
					snake.currentVelocity = 2;
					System.out.println("up");
					repaint();
				}
			}
			if (snake.currentVelocity != 1) {
				if (e.getKeyCode() == 39) {
					snake.currentVelocity = 3;
					System.out.println("right");
					repaint();
				}
			}
			if (snake.currentVelocity != 2) {
				if (e.getKeyCode() == 40) {
					snake.currentVelocity = 4;
					System.out.println("down");
					repaint();
				}
			}
		}
	}
	public void readFile() {
	try{
		FileReader fr = new FileReader("\\\\ITSERVER\\usersub\\hhawkins\\My Documents\\New folder\\Snake\\src\\resources\\highscores.txt");
		BufferedReader br = new BufferedReader(fr);
		int highscore = 0;
		String eachLine;
		while((eachLine = br.readLine()) != null){
	     	highscore = Integer.valueOf(eachLine);
	     	System.out.print(highscore);
		}
	}
	catch(IOException e) {
		System.out.println("Error");
	}
	}
	
	
	
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
	
	}
	
	public void addActionListener(){ 
        startButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                startClicked = true;
                remove(startButton);
                gameOver = false;
                remove(settingsButton);
                System.out.println("start");
                repaint();
            }
        });
        bounds.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                easyMode = true;
                add(startButton);
                add(settingsButton);
                settingsClicked = false;
                remove(backButton);
                remove(snakeOrange);
                remove(snakeRed);
                remove(snakeBlue);
                remove(snakeGreen);
                remove(snakeYellow);
                remove(foodOrange);
                remove(foodRed);
                remove(foodBlue);
                remove(foodGreen);
                remove(foodYellow);
                remove(bounds);
                System.out.println("easy");
                repaint();
            }
        });
        backButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                add(startButton);
                add(settingsButton);
                settingsClicked = false;
                remove(backButton);
                remove(snakeOrange);
                remove(snakeRed);
                remove(snakeBlue);
                remove(snakeGreen);
                remove(snakeYellow);
                remove(foodOrange);
                remove(foodRed);
                remove(foodBlue);
                remove(foodGreen);
                remove(foodYellow);
                remove(bounds);
                repaint();
            }
        });
        settingsButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                startClicked = false;
                remove(startButton);
                remove(settingsButton);
                gameOver = false;
                settingsClicked = true;
                
                add(backButton);
                add(bounds);
                add(snakeOrange);
                add(snakeRed);
                add(snakeBlue);
                add(snakeGreen);
                add(snakeYellow);
                add(foodOrange);
                add(foodRed);
                add(foodBlue);
                add(foodGreen);
                add(foodYellow);
                repaint();
            }
        });
        titleButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                startClicked = false;
                remove(titleButton);
                gameOver = false;
                add(startButton);
                add(settingsButton);
                snake.resetSnake();
                snake.currentVelocity = 0;
                score = 1;
                repaint();
            }
        });
        snakeOrange.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	cSnake = 4;
                repaint();
            }
        });
        snakeRed.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	cSnake = 2;
                repaint();
            }
        });
        snakeBlue.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	cSnake = 3;
                repaint();
            }
        });
        snakeGreen.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	cSnake = 1;
                repaint();
            }
        });
        snakeYellow.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	cSnake = 5;
                repaint();
            }
        });
        foodOrange.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	cFood = 2;
                repaint();
            }
        });
        foodRed.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	cFood = 1;
                repaint();
            }
        });
        foodBlue.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	cFood = 3;
                repaint();
            }
        });
        foodGreen.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	cFood = 4;
                repaint();
            }
        });
        foodYellow.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	cFood = 5;
                repaint();
            }
        });
	}
	
	public void printTimer() {
    	Timer time = new Timer(1, new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			if (tick % 10 == 0 && gameOver == false) {	
    				repaint();
    			}
    			tick++;
    		}
    	});
    	time.start();
    }
       
}
