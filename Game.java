
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.lang.String;
import javax.swing.JLabel;

class ball extends Thread
{
    JPanel barea;
    JLabel end;
    int x= (int)(Math.random()*1000);
    int y= 0;
    int dx = 1;
    int dy = 1;
    static Integer X_rect,Y_rect,X_width, Y_width;
    Color cl;
    static boolean status;
    static int count=0,i=2,level=1;
    ball(JPanel j,JLabel l,Integer X,Integer Y,Integer X_w, Integer Y_w)
    {
    	status=true;
    	X_rect = X;
    	Y_rect = Y;
    	X_width = X_w;
    	Y_width = Y_w;
        barea = j;
        end = l;
        Graphics g=j.getGraphics();
        cl = new Color((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255));
        g.setColor(cl);
        g.fillOval(x, y, 50, 50);
        
        start();
    }

    public void run()
    {
        try{
            while(status){
                move();
                Thread.sleep(5);
            }
            Graphics g = barea.getGraphics();
            g.fillOval(x, y, 50, 50);
        }
        catch(Exception e)
        {}
    }
    public void move()
    {
        Graphics g= barea.getGraphics();
        //g.setColor(new Color(0,0,0));
        g.fillOval(x,y,50,50);
        
        x += dx;
        y += dy;
        
        g.setColor(cl);
        g.fillOval(x,y,50,50);
        //g.fillRect(10, 10, 20, 20);
        Dimension d= barea.getSize();

        if((x+50>X_rect) && (x<X_rect+X_width) && (y+50>Y_rect-1)) {
        	dy=-dy;
        	count++;
        	if(count%i==0) {
        		end.setText("     Level "+(level++));
        		try {
        			Thread.sleep(1000);
        		}
        		catch(Exception e) {
        			
        		}
        		end.setText(null);
        		new ball(barea,end,X_rect,Y_rect,X_width, Y_width);
        		i=2*i;
        		
        	}
        }
        else {
        if(x<0)
            dx=-dx;
        if(y<0)
            dy=-dy;
        if(x+50>d.width)
            dx=-dx;
        if(y+50>d.height-10) {
        	dx=0;
        	dy=0;
			g.setColor(Color.black);
			g.fillOval(x,y,50,50);
			end.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 50));
			end.setText("    GAME OVER");
			level=1;
			i=2;
			//System.out.println(""+X_rect+" "+Y_rect+" "+x+" "+y);
			status=false;
            
        }
        }
	}
	
	public static void set(int x,int y)
	{
		X_rect = x;
		Y_rect = y;
	}
}


public class Game
{

	private JFrame frmMyGame;
	private JPanel panel_1;
    Integer X_rect,Y_rect,X_width,Y_width;
	Graphics g;
	ball b;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Game window = new Game();
					window.frmMyGame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

	/**
	 * Create the application.
	 */
	public Game() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMyGame = new JFrame();
		frmMyGame.setTitle("My Game");
		frmMyGame.getContentPane().setFont(new Font("Times New Roman", Font.BOLD, 18));
		frmMyGame.getContentPane().setLayout(null);
	
		JPanel panel = new JPanel();
		panel.setBackground(Color.YELLOW);
		panel.setBounds(12, 13, 1038, 45);
		frmMyGame.getContentPane().add(panel);
		panel.setLayout(null);
		
		panel_1 = new JPanel();
		//panel_1.setFocusable(true);
		panel_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent ar) {
				int k = ar.getKeyCode();
				Graphics g = panel_1.getGraphics();
				g.setColor(Color.black);
				g.fillRect (X_rect, Y_rect, X_width, Y_width);
				if(k == ar.VK_LEFT && X_rect-20>0) {
					X_rect-=10 ;
					if(X_rect<0)
						X_rect=0;
				}
				else
					if(k == ar.VK_RIGHT  ) {
						X_rect+=10;
						if(X_rect+X_width>panel_1.getWidth())
							X_rect=panel_1.getWidth()-X_width;
					}
				
				
				ball.set(X_rect,Y_rect);
				g.setColor(Color.MAGENTA);
				g.fillRect(X_rect, Y_rect, X_width, Y_width);
	
				//System.out.println(""+X_rect+" "+Y_rect);


			}
			@Override
			public void keyTyped(KeyEvent ar) {
				int k = ar.getKeyCode();
				Graphics g = panel_1.getGraphics();
				g.setColor(Color.black);
				g.fillRect (X_rect, Y_rect, X_width, Y_width);
				if(k == ar.VK_LEFT && X_rect-20>0) {
					X_rect-=20 ;
					if(X_rect<0)
						X_rect=0;
				}
				else
					if(k == ar.VK_RIGHT  ) {
						X_rect+=20;
						if(X_rect+X_width>panel_1.getWidth())
							X_rect=panel_1.getWidth()-X_width;
					}
				
				
				ball.set(X_rect,Y_rect);
				g.setColor(Color.MAGENTA);
				g.fillRect(X_rect, Y_rect, X_width, Y_width);
	
				//System.out.println(""+X_rect+" "+Y_rect);


			}
		});
		panel_1.setBackground(Color.BLACK);
		panel_1.setBounds(12, 59, 1038, 601);
		frmMyGame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblGameOver = new JLabel("");
		lblGameOver.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 60));
		lblGameOver.setForeground(Color.YELLOW);
		lblGameOver.setBounds(321, 193, 381, 50);
		panel_1.add(lblGameOver);
		
		JButton btnStart = new JButton("START");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			lblGameOver.setText(null);
			Graphics g = panel_1.getGraphics();
			g.setColor(Color.MAGENTA);
			g.fillRect(X_rect, Y_rect, X_width, Y_width);
			panel_1.grabFocus();
			 b = new ball(panel_1,lblGameOver,X_rect,Y_rect,X_width, Y_width);
	       
			}
		});
		btnStart.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnStart.setBounds(388, 5, 105, 33);
		panel.add(btnStart);
		
		JButton btnStop = new JButton("STOP");
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
			System.exit(0);
			}
		});
		btnStop.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnStop.setBounds(559, 5, 105, 33);
		panel.add(btnStop);

        X_rect = new Integer((int) (panel_1.getWidth()/2));
        Y_rect = new Integer((int) (panel_1.getHeight()-20));
        X_width = new Integer(100);
        Y_width = new Integer(20);
        //System.out.println(X_rect+" "+Y_rect);
        
		
		frmMyGame.setBounds(100, 100, 1080, 720);
		frmMyGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
		
	
}
