package ponggame;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.regex.Pattern;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
 

public class Test extends JFrame implements KeyListener, Runnable{
	
	/**
	 * Test of the game [*Main Class]
	 */
	
	private static final long serialVersionUID = 1L;
	
	
	private static Image  image;
	private Graphics g;
	private static final String TITLE  = "P";	
	private static final int    WIDTH  = 800;		  // - Width  size for window - //
	private static final int    HEIGHT = 460;		  // - Height size for window - //
	private String servername = "servername" , clientname = "clientname";
 
        public Test(){

	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		this.setVisible(true);
		this.setTitle(TITLE);
		this.setSize(WIDTH,HEIGHT);
		this.setResizable(false);
		this.addKeyListener(this);
	} 
	
	public static void main(String[] args){
		Toolkit tk = Toolkit.getDefaultToolkit();
		image = tk.getImage("pong.png"); // - Set background texture of main menu - //
		Test newT = new Test();
		newT.run();

	}
	

	  ///////////////
	 // - Paint - //
	///////////////
	
	private Image createImage(){
		
	    BufferedImage bufferedImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	    g = bufferedImage.createGraphics();
	    g.fillRect(0, 0, WIDTH, HEIGHT);
	    g.drawImage(image,0, 0, this);
	    return bufferedImage;
	    
	}
	@Override
	public void paint(Graphics g){
		g.drawImage(createImage(), 0, 20, this);
	}
	

	  /////////////////////
	 // - KeyListener - //
	/////////////////////
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
		int    keyCode = arg0.getKeyCode();
		String portAdd = "1024";
		String ipAdd   = "127.0.0.1";
		
		// - Create a Server - //
		if(keyCode==KeyEvent.VK_S){
				// - Input Dialog for get a nick name for server player - //
                    servername = JOptionPane.showInputDialog(null, "Nick name:", "Enter server name:", 1);
                    servername+="";

                    // - Alert Message - //
                    if(servername.length()>10 || servername.length()<3 || servername.startsWith("null")){
                        JOptionPane.showMessageDialog(null, "Enter name as a right format!", "Error!", 1);

                    } 
                    // - Create a server - //
                else{
                        PongServer myServer = new PongServer(servername,portAdd);
                        Thread myServerT = new Thread(myServer);
                        myServerT.start();
                        this.setVisible(false);
                    }			
		}
                
		// - Create a Client - //
		if(keyCode==KeyEvent.VK_C){
		
                    clientname = JOptionPane.showInputDialog(null, "Nick name:", "Enter server name:", 1);
                    clientname += "";
                    if(clientname.length()>10 || clientname.length()<3 || clientname.startsWith("null")){
                            JOptionPane.showMessageDialog(null, "Enter name as a right format!", "Error!", 1);
                    }
                    // - Start a Client - //
                else{
                            PongClient myClient = new PongClient(clientname, portAdd, ipAdd);
                            Thread myClientT = new Thread(myClient);
                            myClientT.start();
                            this.setVisible(false);
                    }		
		}
}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
