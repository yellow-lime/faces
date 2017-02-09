package faces;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Main extends JFrame{
	
	FacePanel facePanel;
	
	Main(){
		this.setLayout(new GridLayout(1, 2));
		this.setTitle("Faces");
		this.setSize(new Dimension(800, 600));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		facePanel = new FacePanel();
		this.add(facePanel);
		
		JButton btnDispersion = new JButton("Reface");
		btnDispersion.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) {
            	facePanel.reface();
            	facePanel.repaint();
            }
        });
		this.add(btnDispersion);
		
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		Main main = new Main();
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		facePanel.paint(g);
	}
}
