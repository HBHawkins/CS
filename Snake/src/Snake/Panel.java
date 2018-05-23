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

    JButton titleButton = new JButton("Title Screen");
    
    JScrollPane scrollPane = new JScrollPane(textArea);
    
    
    
    boolean up = false;
    boolean down = false;
    boolean right = false;
    boolean left = false;
    boolean buttonStart = false;
    boolean startClicked = false;
    boolean gameOver = false;
    boolean started = false;
    boolean settingsClicked = false;
    
    
    int previousVelocity = 0;
    int tick = 1;

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
    }
    


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics = (Graphics2D)g;
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setColor(Color.BLACK);
        Font titleFont = new Font("Courier", Font.BOLD, 64);
        graphics.setFont(titleFont);
        

        
        if (startClicked) {
        	graphics.setColor(Color.RED);
        	snake.moveSnake(graphics);
        	snake.drawSnake(graphics);
        	for(int i = 1; i < snake.body.size(); i++) {
        		if (snake.body.get(0).xPos == snake.body.get(i).xPos && snake.body.get(0).yPos == snake.body.get(i).yPos) {
        			gameOver = true;
        		}
        	}
        	if (snake.body.get(0).xPos < 0 || snake.body.get(0).xPos > 1175 || snake.body.get(0).yPos < 0 || snake.body.get(0).yPos > 650) {
        		gameOver = true;
        	}
        	
        	if (gameOver) {
        		graphics.setColor(Color.RED);
        		graphics.drawString("GAME OVER", 400, 300);
        	
        		add(titleButton);
        		titleButton.setSize(200,100);
        		titleButton.setLocation(500,500);
        	}
        
        	if (snake.foodX == snake.body.get(0).xPos && snake.foodY == snake.body.get(0).yPos) {
        		snake.addTail();
        		snake.addFood();
        	}
        	snake.startFood();
        	graphics.setColor(Color.RED);
        	if (gameOver == false)
        		graphics.fillRect(snake.foodX, snake.foodY, 25, 25);
        }
        
        else if (settingsClicked) {
        	startClicked = false;
        	graphics.setColor(Color.WHITE);
        	graphics.drawString("Settings", 450, 100);
        }
        else {
        	graphics.setColor(Color.WHITE);
        	graphics.drawString("Snake", 500, 100);
        	startButton.setSize(200, 100);
        	startButton.setLocation(500,400);
        	settingsButton.setSize(100,50);
        	settingsButton.setLocation(550,550);
        }
    }
    
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == 37) {
			snake.currentVelocity = 1;
			System.out.println("left");
			repaint();
		}
		if (e.getKeyCode() == 38) {
			snake.currentVelocity = 2;
			System.out.println("up");
			repaint();
		}
		if (e.getKeyCode() == 39) {
			snake.currentVelocity = 3;
			System.out.println("right");
			repaint();
		}
		if (e.getKeyCode() == 40) {
			snake.currentVelocity = 4;
			System.out.println("down");
			repaint();
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
                System.out.println("start");
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
                add(titleButton);
                
                
                repaint();
            }
        });
        titleButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                startClicked = false;
                remove(titleButton);
                gameOver = false;
                add(startButton);
                snake.resetSnake();
                snake.currentVelocity = 0;
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
