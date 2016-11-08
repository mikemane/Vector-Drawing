import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JToolBar;


public class JToolBarExample extends JFrame {

    public JToolBarExample() {
        setLayout(new GridLayout(2, 1));
        JToolBar jtb = new JToolBar();
        getContentPane().add(jtb);
        jtb.add(new JButton("Button 1"));
        jtb.add(new JButton("Button 2"));
        getContentPane().add(new JButton("Button 3"));
        setSize(75, 75);
        setVisible(true);
    }


    public static void main(String[] args) {
        new JToolBarExample();
    }

}
