import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.ArrayList;
import javax.swing.JFrame;
/**
 * Game defines all the variables for the game.
 *
 * @author Michael Murphy
 * @version 1
 * @param display an array with all the data for every pixel in the game window.
 * @param Graphic an array list with all the textures stored in it.
 * @param run sets weather the game is running or not.
 * @param breakable decides if you can edit the map
 * @param player is the player in the game.
 * @param render is where the calculations for 3d happen.
 * @param therad, im not sure what this does, I am carrying it over from other 3d game examples
 * @param pic is the picture that is updated to the game window
 * @param map stores all the data for the maze map.
 */
public class Game extends JFrame implements Runnable

{
    private int[] display;
    private ArrayList<Graphic> graphic;
    private boolean run;
    public static boolean breakable=false;
    private Player player;
    private Render render;
    private Thread thread;
    private BufferedImage pic;
    
    private static int[][] map =
          {
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
			{1,0,0,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,0,1},
			{1,0,0,2,2,0,0,0,0,0,0,2,0,0,0,0,0,2,0,1},
			{1,2,0,2,2,0,2,2,0,2,2,2,0,2,2,2,0,2,0,1},
			{1,2,0,2,2,0,2,2,0,0,0,0,0,2,0,2,0,2,0,1},
			{1,2,0,2,2,0,2,2,0,2,2,2,2,2,0,0,0,2,0,1},
			{1,2,0,0,0,0,0,0,0,2,0,2,0,0,0,2,0,2,0,1},
			{1,2,0,2,2,2,2,2,2,2,0,2,0,0,2,2,0,2,0,1},
			{1,2,0,0,0,0,0,0,0,0,0,2,0,0,2,2,0,2,0,1},
			{1,2,0,2,2,2,2,2,0,2,2,2,0,0,2,2,0,2,0,1},
			{1,2,0,0,0,0,0,2,0,0,0,0,0,0,2,2,0,2,0,1},
			{1,2,0,2,0,2,2,2,2,2,0,2,2,2,2,2,0,2,0,1},
			{1,2,0,2,0,2,2,2,2,2,0,2,2,2,2,2,0,2,0,1},
			{1,2,0,2,0,0,0,0,0,0,0,0,0,0,0,0,0,2,0,1},
			{1,2,0,2,0,2,0,2,2,2,2,2,2,2,2,2,2,2,0,1},
			{1,2,0,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			{1,2,0,2,0,2,0,2,2,2,2,2,2,2,2,2,2,2,2,1},
			{1,2,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			{1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,0,3,1},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
		};
    
    
    
    
    
    
    		
    
    
    
    
    public Game(String type)
    {
        thread = new Thread(this);
        pic = new BufferedImage(800,600,BufferedImage.TYPE_INT_RGB);
        display = ((DataBufferInt)pic.getRaster().getDataBuffer()).getData();
        graphic = new ArrayList<Graphic>();
        graphic.add(Graphic.wood);
        graphic.add(Graphic.dirt);
        graphic.add(Graphic.stone);
        player = new Player(1,1,1,0,0,-.65);
        //fills the map array with empty spaces and allows placing and breaking if the game is in create mode
        if (type.equals("c"))
		{
		    breakable=true;
		   for (int i = 1; i<map.length-1; i++)
		   {
		      for (int j = 1; j<map[0].length-1; j++)
		      {
		         map[i][j]=0;
		         
		      }
		   }
		}
	render = new Render(map, graphic, map.length,map[0].length, 800, 600);
	addKeyListener(player);
	setSize(800,600);
	setResizable(false);
	setTitle("game");
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setBackground(Color.black);
	setFocusable( true );
	setLocationRelativeTo(null);
	setVisible(true);
	 
	start();
    }
    public void start() 
    {
		run = true;
		thread.start();
	}
    public void run()
    {
       long lastTime = System.nanoTime();
		final double ns = 1000000000.0 / 60.0;
		double delta = 0;
		requestFocus();
		while(run) 
		{
			long now = System.nanoTime();
			delta = delta + ((now-lastTime) / ns);
			lastTime = now;
			while (delta >= 1)
			{
				//updates the display array accordingly
				render.update(player, display);
				player.update(map);
				
				delta--;
			}
			draw();
		} 
    }

    
    public void draw() 
    {
		BufferStrategy bs = getBufferStrategy();
		if(bs == null) 
		{
			createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		g.drawImage(pic, 0, 0, pic.getWidth(), pic.getHeight(), null);
		bs.show();
	}
    public static void add(int x, int y, int block)
    {
        //sets the selected block to the blocktype if the map is in create mode.
        if (map[x][y] != 1 && breakable)
	    map[x][y]=block;     
    }
    public static void remove(int x, int y)
    {
        //removes the selected block if the map is in create mode.
        if (map[x][y] != 1 && breakable)
	    map[x][y]=0;
    }
    public  void unedit()
    {
      //disables the ability to break and place blocks, and teleports the player
      //to the inital spawn position
      breakable=(false); 
      player.teleport();
      requestFocus();
    }

}
