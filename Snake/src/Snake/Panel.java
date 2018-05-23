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

    JTextArea textArea = new JTextArea(10,40);
    JTextField textField = new JTextField(12);
    JTextField userSpeed = new JTextField(12);
    
    JScrollPane scrollPane = new JScrollPane(textArea);
    
    Snake snake = new Snake();
    
    boolean up = false;
    boolean down = false;
    boolean right = false;
    boolean left = false;
   
    boolean gameOver = false;
    
    
    int previousVelocity = 0;
    int tick = 1;

    public Panel() {
        //startButton.setFocusable(false);
        //instructionButton.setFocusable(false);
        addKeyListener(this);
        setFocusable(true);
    	setBackground(Color.BLACK);
    	printTimer();
    }
    


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics = (Graphics2D)g;
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setColor(Color.BLACK);
        Font titleFont = new Font("Courier", Font.BOLD, 64);
        graphics.setFont(titleFont);
        graphics.setColor(Color.RED);
        
        snake.moveSnake(graphics);
        snake.drawSnake(graphics);
        
        if (snake.foodX == snake.body.get(0).xPos && snake.foodY == snake.body.get(0).yPos) {
        	snake.addTail();
        	snake.addFood();
        }
        snake.startFood();
        graphics.setColor(Color.RED);
        graphics.fillRect(snake.foodX, snake.foodY, 25, 25);        
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
	
	public void printTimer() {
    	Timer time = new Timer(1, new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			if (tick % 75 == 0 && gameOver == false) {	
    				repaint();
    			}
    			tick++;
    		}
    	});
    	time.start();
    }
       
}