package Snake;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Interface {

	static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

	
	   public static void main(String[] args) {
		   

		   JFrame window = new JFrame("Snake");
		       //window.setSize((int) screenSize.getWidth(),(int)screenSize.getHeight());
		   	   window.setSize(1205,704);
		       window.setContentPane(new Panel());
		       window.setResizable(false);
		       window.setLocationRelativeTo(null);
		       window.setVisible(true);
		       window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		   
	   }
}