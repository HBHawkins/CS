package Snake;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class Snake {
	
	static ArrayList<Body> body = new ArrayList<Body>();
	
	static int tailX = 0;
	static int tailY = 0;
	
	int foodX = 0;
	int foodY = 0;
	
	boolean started = false;
	boolean gameOver = false;
	
	int currentVelocity = 0;

	public void resetSnake() {
		body.clear();
		body.add(new Body("first"));
	}
	
	public Snake() {
		body.add(new Body("first"));

	}
	
	public void addTail() {
		body.add(new Body());
	}
	
	
	public void drawSnake(Graphics2D graphics) {
		Color snakeC = Color.BLACK;
		Color foodC = Color.BLACK;
		if (Panel.cSnake == 1) 
        	snakeC = Color.GREEN;
        else if (Panel.cSnake == 2)
        	snakeC = Color.RED;
        else if (Panel.cSnake == 3)
        	snakeC = Color.BLUE;
        else if (Panel.cSnake == 4)
        	snakeC = Color.ORANGE;
        else if (Panel.cSnake == 5)
        	snakeC = Color.YELLOW;
         

		graphics.setColor(snakeC);
		for (int i = 0; i < body.size(); i++) {
			graphics.fillRect(body.get(i).xPos, body.get(i).yPos, 25, 25);			
		}
		
	}

	public void addFood() {
		foodX = Body.randomX();
		foodY = Body.randomY();
	}
	
	public void startFood() {
		if (started == false) {
			foodX = Body.randomX();
			foodY = Body.randomY();
			started = true;
		}
	}
	
	public void moveSnake(Graphics2D graphics) {
		if (!gameOver)
			
		 {
			if (currentVelocity > 0) {
				if (currentVelocity == 1) {
					for (int i = body.size() - 1; i > 0; i--) {
						body.get(i).xPos = body.get(i-1).xPos;
						body.get(i).yPos = body.get(i-1).yPos;
					}
					body.get(0).xPos -= 25;
				}
				if (currentVelocity == 2) {
					for (int i = body.size() - 1; i > 0; i--) {
						body.get(i).xPos = body.get(i-1).xPos;
						body.get(i).yPos = body.get(i-1).yPos;
					}
					body.get(0).yPos -= 25;
				}
				if (currentVelocity == 3) {
					for (int i = body.size() - 1; i > 0; i--) {
						body.get(i).xPos = body.get(i-1).xPos;
						body.get(i).yPos = body.get(i-1).yPos;
					}
					body.get(0).xPos += 25;
				}
				if (currentVelocity == 4) {
					for (int i = body.size() - 1; i > 0; i--) {
						body.get(i).xPos = body.get(i-1).xPos;
						body.get(i).yPos = body.get(i-1).yPos;
					}
					body.get(0).yPos += 25;
				}
			}
		}	
	}
	
	
	
}
