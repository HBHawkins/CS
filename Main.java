package UI;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

    public class Main {

        public static void main(String[] args) {

            JFrame window = new JFrame("Fast Text Reader");
            window.setSize(800,600);
            window.setContentPane(new Panel());
            window.setResizable(false);
            window.setLocationRelativeTo(null);
            window.setVisible(true);
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
    }