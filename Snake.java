import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;


public class Snake  implements ActionListener ,Runnable{
	
	static byte direction = 1;
	static boolean  gameOver = false;
	static Integer xHead , yHead , dx , dy  , xFruit , yFruit , tailLength=1,score = 0 ,sleepTime = 200;
	static Integer X[],Y[];
	static JFrame frame;
	static JPanel panel ,panel_1;
	static JButton btnStart , btnStop;
	static JMenu mnLevel;
	static JMenuItem mntmEasy , mntmMedium , mntmHard; 
	static Graphics g;
	static JLabel label,lblNewLabel ,lblNewLabel_1,lblNewLabel_2;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new Snake();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Snake() {
		frame = new JFrame("Snake");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 1080, 720);
		frame.getContentPane().setLayout(null);
		
		panel = new JPanel();
		panel.setBackground(Color.YELLOW);
		panel.setBounds(12, 13, 1038, 54);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		lblNewLabel = new JLabel(" Score - 0");
		lblNewLabel.setBackground(Color.YELLOW);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel.setBounds(903, 13, 123, 28);
		panel.add(lblNewLabel);
		
		btnStart = new JButton("START");
		btnStart.addActionListener(this);			
		btnStart.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnStart.setBounds(29, 13, 105, 28);
		panel.add(btnStart);
		
		btnStop = new JButton("STOP");
		btnStop.addActionListener(this);
				
		btnStop.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnStop.setBounds(180, 13, 105, 28);
		panel.add(btnStop);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setFont(new Font("Times New Roman", Font.BOLD, 18));
		menuBar.setBounds(480, 13, 120, 28);
		panel.add(menuBar);
		
		mnLevel = new JMenu("DIFFICULTY -");
		mnLevel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		menuBar.add(mnLevel);
		
		mntmEasy = new JMenuItem("Easy");
		mntmEasy.addActionListener(this);
		mnLevel.add(mntmEasy);
		
		
		mntmMedium = new JMenuItem("Medium");
		mntmMedium.addActionListener(this);
		mnLevel.add(mntmMedium);
		
		mntmHard = new JMenuItem("Hard");
		mntmHard.addActionListener(this);		
		mnLevel.add(mntmHard);
		
		label = new JLabel("");
		label.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
		label.setBounds(612, 13, 105, 28);
		panel.add(label);
		
		
		
		initial();

			}
	
	public void initial( )
	{
		panel_1 = new JPanel();
		panel_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent ke) {
			
				int k = ke.getKeyCode();

				if(k == ke.VK_UP && direction != 4)
					direction = 1;
				if(k == ke.VK_LEFT && direction != 3)
					direction = 2;
				if(k == ke.VK_RIGHT && direction != 2)
					direction = 3;
				if(k == ke.VK_DOWN && direction != 1)
					direction = 4;
				//System.out.println("key");
			}
		});
		panel_1.setBackground(Color.BLACK);
		panel_1.setBounds(12, 80, 1038, 580);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setForeground(Color.YELLOW);
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 50));
		lblNewLabel_1.setBounds(355, 221, 330, 89);
		panel_1.add(lblNewLabel_1);

		frame.setVisible(true);

		g = panel_1.getGraphics();

		xHead = (int)(panel_1.getWidth()/2);
		yHead = (int)(panel_1.getHeight()/2);
		dx = 20;
		dy = 20;
		X = new Integer[500];
		Y = new Integer[500];
		X[0] = xHead;
		Y[0] = yHead;
		xFruit = (int)(Math.random()*panel_1.getWidth()-15);
		yFruit = (int)(Math.random()*panel_1.getHeight()-15);
		//System.out.println("11");

		g.setColor(Color.RED);
		g.fillRect(xHead, yHead , dx , dy );
		g.setColor(Color.yellow);
		g.fillOval(xFruit, yFruit , 15 , 15 );
		panel_1.setFocusable(true);
		
		lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 40));
		lblNewLabel_2.setForeground(Color.YELLOW);
		lblNewLabel_2.setBounds(409, 382, 244, 64);
		panel_1.add(lblNewLabel_2);
		panel_1.grabFocus();

	}
	
	private void move()
	{
		//System.out.println("move");
	while(gameOver)
	{
		g=panel_1.getGraphics();
		g.setColor(Color.black);
		g.fillRect(X[tailLength-1], Y[tailLength-1], dx, dy);
		if(direction == 1)
			yHead -= dy;
		if(direction == 2)
			xHead-=dx;
		if(direction == 3)
			xHead+=dx;
		if(direction == 4)
			yHead+=dy;
		
		if(xHead <=0 || xHead+dx>=panel_1.getWidth() || yHead <=0 || yHead+dy >=panel_1.getHeight()) {
			gameOver = false;
			lblNewLabel_1.setText("GAME OVER");
			lblNewLabel_2.setText(lblNewLabel.getText());
		}
		if((Math.abs((xHead+10)-(xFruit+7)) <= 16) && (Math.abs((yHead+10)-(yFruit+7)) <= 16 ))
		{
			g.fillOval(xFruit, yFruit, 15, 15);
			
			xFruit = (int)(Math.random()*(panel_1.getWidth()-15));
			yFruit = (int)(Math.random()*(panel_1.getHeight()-15));
			g.setColor(Color.yellow);
			g.fillOval(xFruit, yFruit, 15, 15);
			tailLength++;
			
			score+=10;
			lblNewLabel.setText(" Score - "+score);
		}
		g.setColor(Color.red);
		
		//boolean snakeIntersection = false;
		for(int i = tailLength-1;i>0;i--)
		{
			X[i] = X[i-1];
			Y[i] = Y[i-1];
			g.fillRect(X[i], Y[i], dx, dy);
			//System.out.println(""+X[i]+" "+Y[i]+" "+xHead+" "+yHead);
			if(X[i].equals(xHead) && Y[i].equals(yHead) ) {
								
					gameOver = false;
					lblNewLabel_1.setText("GAME OVER");
					lblNewLabel_2.setText(lblNewLabel.getText());			
			}
			
		}
		X[0] = xHead;
		Y[0] = yHead;
		g.fillRect(xHead, yHead, dx, dy);
		try {
		Thread.sleep(sleepTime);
		}
		catch(Exception e)
		{}
		}
	}
		


	
	public void run()
	{
			move();	
	}
	
	public void actionPerformed(ActionEvent ae) {
		
		if(btnStart == ae.getSource())
		{
			gameOver =true;
			try{
				Thread t = new Thread(this);
				reset();
				initial();
				t.start();
			}
			catch(Exception e)
			{}
		}
		
		if(btnStop == ae.getSource())
		{
			gameOver = false;
			lblNewLabel_1.setText("GAME OVER");
			lblNewLabel_2.setText(lblNewLabel.getText());
		}
		
		if(mntmEasy == ae.getSource())
		{
			sleepTime = 200;
			label.setText("EASY");
		}
		if(mntmMedium == ae.getSource())
		{
			sleepTime = 100;
			label.setText("MEDIUM");
		}
		if(mntmHard == ae.getSource())
		{
			sleepTime = 50;
			label.setText("HARD");
		}
	}
	public void reset()
	{
		score = 0;
		tailLength = 1;
		lblNewLabel.setText("Score - "+score);
		g = panel_1.getGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0, panel_1.getWidth(), panel_1.getHeight());
	}
}
