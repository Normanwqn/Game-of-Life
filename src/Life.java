import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
//Event Interfaces
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;

//Main Class implements the Listener
public class Life implements MouseListener, ActionListener, Runnable{
	//ActionListner -> Monitors the activities of the Button
	Board current = new Board();  //Create a new Board
	final int w = current.getLife()[0].length;  //Getting the width of the board
	final int h = current.getLife().length;  //Get the height of the board
	//Boolean[][] initial = new Boolean[h][w];
	JFrame frame = new JFrame("Conway's Life"); //Setting the title
	//Declare the panel that is going to be added
	LifePanel panel = new LifePanel(current); // Creating a Life-panel object
	Container south = new Container(); //Creating a container
	JButton step = new JButton("Step"); 
	JButton start = new JButton("Start");
	JButton stop = new JButton("Stop");
	boolean running = false;  //Stores the status of the program, whether it is running or not
	//Constructor of the Main Life class
	public Life() {
		randomise(); //Randomizing the Board
		frame.setSize(800, 800); //Setting the size of the frame 
		//Border Layout
		frame.setLayout(new BorderLayout()); //Choosing a "BorderLayout"
		panel.addMouseListener(this); //
		//Container, adding the buttons
		south.setLayout(new GridLayout(1,3));
		south.add(step); //Adding the step button
		step.addActionListener(this);
		south.add(start); //Adding the start button 
		start.addActionListener(this);
		south.add(stop); //Adding the stop button
		stop.addActionListener(this); //Enable the functions
		frame.add(south, BorderLayout.SOUTH); //Adding the south layout to the "Frame"
		frame.add(panel, BorderLayout.CENTER);//Adding the panel to the frame
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//Setting how the program ends
		//Setting the frame visible
		frame.setVisible(true);
	}
	public void randomise() {
		//Randomizing each cell 
		Random random = new Random();
		for (int i = 0; i < h; i++) {
			for (int j = 0; j <w; j++) {
				if (random.nextBoolean()) {
					//nextboolean is a function which generates a random boolean value
					//Using the random Class
					current.setLife(i, j, random.nextBoolean());
				}
			}
		}
	}
	public static void main(String[] args) {	
		//Creating itself in the main method
		new Life();
		
	}
	public void step() {
		Board a = new Board(current);
		for (int i = 0; i < a.getLife().length; i++) {
			for (int j = 0; j < a.getLife()[i].length; j++) {
				current.setLife(i, j, a.isAlive(i, j));
			}
		}
		frame.repaint();
	}
	@Override
	//Leave it empty, it is mandatory to override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		//Figure out the which cell needs to be changed
		double width = (double) panel.getWidth() / current.getLife()[0].length;
		double height = (double) panel.getHeight() / current.getLife().length;
		int col = Math.min(current.getLife()[0].length-1, (int) (e.getX() / width)); // avoiding array index out of bound
		int row = Math.min(current.getLife().length-1, (int) (e.getY() / height));
		current.setLife(row, col, ! current.getValue(row, col)); //Flipping the value
		frame.repaint();
		
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource().equals(step)) {
			step();
		}
		//Logic of the start button
		if (e.getSource().equals(start)) { //If the start button is triggered
			//Separate Thread is needed 
			if (running == false) {
				running = true;
				Thread t = new Thread(this); //Add the program to the Thread
				//A feature of the runnable interface
				t.start(); //Start the Program
			}
		}
		//If the stop button is triggered
		if (e.getSource().equals(stop)) {
			running = false;
		}
	}
	@Override
	//A part of the runnable interface
	public void run() {
		// TODO Auto-generated method stub
		//Something that is used to start a new thread of execution
		while (running) {
			step();
			try {
				//Setting the refresh rate
				Thread.sleep(100);
			} catch (InterruptedException e) {
				//Catch an error. Not as important
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
