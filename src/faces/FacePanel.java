package faces;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class FacePanel extends JPanel {
	
	BufferedImage img = null;
	
	ArrayList<BufferedImage> eyes = new ArrayList<BufferedImage>();
	ArrayList<BufferedImage> mouths = new ArrayList<BufferedImage>();
	ArrayList<BufferedImage> accessories = new ArrayList<BufferedImage>();
	
	BufferedImage theEye = null;
	BufferedImage theMouth = null;
	BufferedImage theAccessory = null;
	
	static final int GRID_SIZE = 32;
	
	FacePanel(){
		try {
		    img = ImageIO.read(new File("faces.png"));
		    int rows = img.getHeight() / GRID_SIZE;
		    for(int i = 0; i < rows; i++){
		    	eyes.add(img.getSubimage(0, GRID_SIZE * i, GRID_SIZE, GRID_SIZE));
		    	mouths.add(img.getSubimage(GRID_SIZE, GRID_SIZE * i, GRID_SIZE * 2, GRID_SIZE));
		    	accessories.add(img.getSubimage(GRID_SIZE * 3, GRID_SIZE * i, GRID_SIZE * 2, GRID_SIZE));
		    }
		    theEye = getRandomImage(eyes);
		    theMouth = getRandomImage(mouths);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void reface(){
	    theEye = getRandomImage(eyes);
	    theMouth = getRandomImage(mouths);
	    theAccessory = getRandomImage(accessories);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++){
				paintAFaceHere(g, GRID_SIZE * 2 * i, GRID_SIZE * 2* j, Math.random() > 0.8);
				reface();
			}
		}
	}
	
	private void paintAFaceHere(Graphics g, int x, int y, boolean flippedEye){

		g.drawImage(theEye, x, y, null);
		drawMirrored(g, theEye, x + GRID_SIZE, y);
		g.drawImage(theMouth, x, y + (int)Math.floor(GRID_SIZE*0.75), null);
		g.drawImage(theAccessory, x, y + (int)Math.floor(GRID_SIZE*0.5), null);
	}
	
	private void drawFlipped(Graphics g, BufferedImage img, int x, int y){
		g.drawImage(img, x, y + img.getHeight(), img.getWidth(), -img.getHeight(), null);
	}
	
	private void drawMirrored(Graphics g, BufferedImage img, int x, int y){
		g.drawImage(img, x + img.getWidth(), y, -img.getWidth(), img.getHeight(), null);
	}
	
	private BufferedImage getRandomImage(ArrayList<BufferedImage> images){
		int i = (int) Math.floor(Math.random()*images.size());
		return images.get(i);
	}
}
