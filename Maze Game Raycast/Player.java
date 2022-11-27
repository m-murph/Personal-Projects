import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
/**
 * Player has and caluclates all the actions the player can do.
 *
 * @author Michael Murphy
 * @version 1
 * @param xPos is the players x position in the maze
 * @param yPos is the players y position in the maze
 * @param xVec is the x component for a vector that points in the same direction as the player
 * @param yVec is the y component of that vector
 * @param xPlane is the x component of a vector that borders the players field of view
 * @param yPlane is the y componentt of that vector
 * @param left is true when the player is rotating left
 * @param right is true when rotating right
 * @param foward is true when moving fowrward
 * @param back is true when moving back
 * @param SPEED defines the players movement speed
 * @param RSPEED defines the players rotation speed
 */
public class Player implements KeyListener
{
   public double xPos; 
   public double yPos;
   public double xVec;
   public double yVec;
   public double xPlane;
   public double yPlane;
   public boolean left;
   public boolean right;
   public boolean forward;
   public boolean back;
   public final double SPEED = 0.08;
   public final double RSPEED = 0.045;
   public Player(double x, double y, double xv, double yv, double xp, double yp)
   {
       xPos=x;
       yPos=y;
       xVec=xv;
       yVec=yv;
       xPlane = xp;
       yPlane = yp;
       
    }
   public void keyPressed(KeyEvent key) {
		if((key.getKeyCode() == KeyEvent.VK_LEFT))
			left = true;
		if((key.getKeyCode() == KeyEvent.VK_RIGHT))
			right = true;
		if((key.getKeyCode() == KeyEvent.VK_UP))
			forward = true;
		if((key.getKeyCode() == KeyEvent.VK_DOWN))
			back = true;
		
	}
	public void keyReleased(KeyEvent key) {
		if((key.getKeyCode() == KeyEvent.VK_LEFT))
			left = false;
		if((key.getKeyCode() == KeyEvent.VK_RIGHT))
			right = false;
		if((key.getKeyCode() == KeyEvent.VK_UP))
			forward = false;
		if((key.getKeyCode() == KeyEvent.VK_DOWN))
			back = false;
		if((key.getKeyCode() == KeyEvent.VK_A))
		    {    
		      //removes a block 1 space infront of you
		        if (yVec>=0 && xVec>-0.65 && xVec<0.65)
		      
		      Game.remove((int)(xPos),(int)(yPos)+1);
		
		  
		      else if (yVec<=0 && xVec>-0.65 && xVec<0.65)
		      
		      Game.remove((int)(xPos),(int)(yPos)-1);
		      
		  
		      else if (xVec>=0)
		      
		      Game.remove((int)(xPos)+1,(int)(yPos));
		      
		  
		      else if (yVec<=0)
		      {
		      Game.remove((int)(xPos)-1,(int)(yPos));
		      
		  }
		  else if (yVec>0)
		  {
		      Game.remove((int)(xPos)-1,(int)(yPos));
		      
		  }
		  }
		if (key.getKeyCode() == KeyEvent.VK_1) 
		{
		    //adds a brick block 1 space infront of you
		      if (yVec>=0 && xVec>-0.65 && xVec<0.65)
		      
		      Game.add((int)(xPos),(int)(yPos)+1,2);
		
		  
		      else if (yVec<=0 && xVec>-0.65 && xVec<0.65)
		      
		      Game.add((int)(xPos),(int)(yPos)-1,2);
		      
		  
		      else if (xVec>=0)
		      
		      Game.add((int)(xPos)+1,(int)(yPos),2);
		      
		  
		      else if (yVec<=0)
		      {
		      Game.add((int)(xPos)-1,(int)(yPos),2);
		      
		  }
		  else if (yVec>0)
		  {
		      Game.add((int)(xPos)-1,(int)(yPos),2);
		      
		  }
		}
		//xneg ypos
		if (key.getKeyCode() == KeyEvent.VK_2) 
		{
		     //adds a finish block 1 space infront of you 
		    if (yVec>=0 && xVec>-0.65 && xVec<0.65)
		      
		      Game.add((int)(xPos),(int)(yPos)+1,3);
		
		  
		      else if (yVec<=0 && xVec>-0.65 && xVec<0.65)
		      
		      Game.add((int)(xPos),(int)(yPos)-1,3);
		      
		  
		      else if (xVec>=0)
		      
		      Game.add((int)(xPos)+1,(int)(yPos),3);
		      
		  
		      else if (yVec<=0)
		      {
		      Game.add((int)(xPos)-1,(int)(yPos),3);

		  }
		  else if (yVec>0)
		  {
		      Game.add((int)(xPos)-1,(int)(yPos),3);
		      
		  }
		}
		if (key.getKeyCode() == KeyEvent.VK_M)
		{
		    
		    GUI.openPopUpMenu();
		}
	}
	public void keyTyped(KeyEvent a)
	{
	   //needed for key listener. 
	   } 
	   public void update(int[][] map)
	   {
	      
	       if (forward)
	    {
	          
	          if (map[(int)(xPos + xVec * SPEED)][(int)(yPos)] == 0)
	          {
	              xPos+=xVec*SPEED;
	              
	          }
	          if (map[(int)(xPos)][(int)(yPos + yVec * SPEED)]==0)
	          {
	              yPos+=yVec*SPEED;
	              
	          }
	            /*
	               * Takes the direction vector and uses the speed constant to 
	               * index the x and y position respectivly.
	               * 
	               * if statements check to see if there is a block in the way.
	               */
	   }
	   if (back)
	   {
	       if (map[(int)(xPos - xVec*SPEED)][(int)(yPos)]==0)
	       {
	           xPos-=xVec*SPEED;
	       }
	       if (map[(int)xPos][(int)(yPos - yVec*SPEED)]==0)
	       {
	         yPos -= yVec*SPEED;  
	       }
	   }
	   if (right)
	   {
	     double oldxVec = xVec;
	     xVec=xVec*Math.cos(-RSPEED) - yVec*Math.sin(-RSPEED);
	     yVec = oldxVec*Math.sin(-RSPEED) + yVec*Math.cos(-RSPEED);
	     double oldxPlane = xPlane;
	     xPlane = xPlane*Math.cos(-RSPEED) - yPlane*Math.sin(-RSPEED);
	     yPlane = oldxPlane*Math.sin(-RSPEED) + yPlane*Math.cos(-RSPEED);
	     /*
	      * this takes the position vector and uses a rotation matrix to ajust the 
	      * x and y vector.
	      * 
	      */
	   }
	   if (left)
	   {
	     double oldxVec=xVec;
		xVec=xVec*Math.cos(RSPEED) - yVec*Math.sin(RSPEED);
		yVec=oldxVec*Math.sin(RSPEED) + yVec*Math.cos(RSPEED);
		double oldxPlane = xPlane;
		xPlane=xPlane*Math.cos(RSPEED) - yPlane*Math.sin(RSPEED);
		yPlane=oldxPlane*Math.sin(RSPEED) + yPlane*Math.cos(RSPEED);  
	       
	       
	   }
}
public void teleport()
{
   xPos=1.5;
   yPos=1.5;
   
   //puts the player at the inital spawn point.
}

}
