package Snake;

public class Body {
	
	int xPos;
	int yPos;
	
    public static int randomX() {
    	int num = (int)(Math.random()*47);
    	return (num * 25);
    }
    
    public static int randomY() {
    	int num = (int)(Math.random()*26);
    	return (num * 25);
    }
	
    public Body(String one) {
    	xPos = randomX();
    	yPos = randomY();
    }
    
	public Body() {
		xPos = Snake.body.get(Snake.body.size()-1).xPos;
		yPos = Snake.body.get(Snake.body.size()-1).yPos;
	}

}
