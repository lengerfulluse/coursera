package basic.lambda;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by hengwei on 17/10/2016.
 */
public class ActionListenerTest {
    public static void main(String[] args) {
        JButton jButton = new JButton();
        // inner ActionLister class. Commonly used for interface
        // with only one function declaration.
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Click Detected by Anon Class");
            }
        });
        jButton.addActionListener(e->System.out.println("Click Detected by Lambda Class"));
        JFrame frame = new JFrame("Listener Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(jButton, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
    }
}
