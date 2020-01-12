import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class LifePanel extends JPanel {
	Board current;
	//Changes depend s on the height of the screen
	double width;
	double height;

	public LifePanel(Board in) {
		current = in;
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g); //Inherited 
		//Resizing the screen
		setBackground(Color.BLACK);
		width = (double) this.getWidth() / current.getLife()[0].length;
		height = (double) this.getHeight() / current.getLife().length;
		//Drawing Squares
		g.setColor(Color.WHITE); //Setting 
		for (int row = 0; row < current.getLife().length; row ++) {
			for (int col = 0; col < current.getLife()[0].length; col++) {
				if (current.getValue(row, col)) {
					g.fillRect((int) Math.round((col*width)), (int) Math.round((row*height)), (int) width, (int) height);
					//
				}
			}
		}
		//Drawing the gridlines
		g.setColor(new Color(64,64,64)); //Grey
		for (int x = 0; x <= current.getLife()[0].length; x++) {
			g.drawLine((int) Math.round(x*width), 0, (int) Math.round(x*width), this.getHeight());
		}
		for (int y = 0; y <= current.getLife().length; y++) {
			g.drawLine(0, (int) Math.round(y*height), this.getWidth(), (int) Math.round(y*height));
		}
		//DrawLine
	}
	/*public void setCells(Board current) {
		for (int row = 0; row < current.getLife().length; row ++) {
			for (int col = 0; col < current.getLife()[0].length; col++) {
				if (current.getValue(row, col)) {
					g.fillRect((int) Math.round((col*width)), (int) Math.round((row*height)), (int) width, (int) height);
					//
				}
			}
		}
	}*/
}
