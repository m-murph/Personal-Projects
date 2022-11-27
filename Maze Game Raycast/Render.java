import java.util.ArrayList;
import java.awt.Color;  
/**
 * Render handles all the calculations for rendering 3d
 *
 * @author Michael Murphy (with much help from lodev.org/cgtutor/raycasting.html#Textured_Raycaster)
 * @version 3
 * @param map is the map of the maze
 * @param mapW is the width of the map
 * @param mapH is the height of the map
 * @param graphic is the list of textures
 * @param w is the width of the game window
 * @param h is the hight of the game window
 * @param darkGreen is the color dark green (used for making the "floor" look like grass)
 * @param blue the color blue (used for making the "sky" look blue)
 */
public class Render
{
    public int[][] map;
    public ArrayList<Graphic> graphic;
    public int mapW;
    public int mapH;
    public int w;
    public int h;
    private Color darkGreen;
    private Color blue;
  public Render(int[][] m, ArrayList<Graphic> graph, int wi, int hi,  int windowW, int windowH)
  {
     map = m;
     graphic = graph;
     mapW = wi;
     mapH= hi;
     w = windowW;
     h = windowH;
     darkGreen = new Color(0, 102, 0);
     blue = new Color(0,0,204);
  }
  public int[] update(Player player, int[] display)
  {
    // first 2 for loops fil top half of screen with blue and bottom half with green.
    
      for (int i=0; i<display.length/2; i++)
    {
      display[i] = Color.blue.getRGB();  
    }
    for (int i=display.length/2; i<display.length; i++)
    {
      display[i] = darkGreen.getRGB();   
    }
    //loop every x value of the screen
    for (int i = 0; i<w; i++)
    {
       // Makes it so 1 represents the right of the screen and -1 represents the left.
        double search = 2*i/(double)(w)-1;
       
       //calculate the direction of the ray
       double rayX = player.xVec + player.xPlane*search;
       double rayY = player.yVec + player.yPlane*search;
       int mapX = (int)player.xPos;
       int mapY = (int)player.yPos;
       double stepToBorderX = Math.sqrt(1+(rayY*rayY)/(rayX*rayX));
       double stepToBorderY = Math.sqrt(1+(rayX*rayX)/(rayY*rayY));
       int side = 0;
       // distance the ray has to travel until it reaches a border.
       double sideDistX;
       double sideDistY;
       
       double wallDist;
       //step X = 1 if ray has positive x component -1 if negive x component. same for stepY.
       int stepX;
       int stepY;
       // if the ray hit a wall.
       boolean hit = false;
       //calculate the distances to borders.
       if (rayX<0)
       {
          stepX=-1;
          sideDistX = (player.xPos - mapX) * stepToBorderX;
       }
       else
       {
         stepX =1;
         sideDistX = (mapX + 1.0 - player.xPos) * stepToBorderX;
       }
       if (rayY < 0)
       {
          stepY = -1;
          sideDistY = (player.yPos - mapY) * stepToBorderY;
        }
        else
        {
          stepY =1;
          sideDistY = (mapY + 1.0 - player.yPos)*stepToBorderY; 
        }
       //loop until the ray find a wall.
        while(!hit)
        {
           if (sideDistX < sideDistY)
           {
               sideDistX += stepToBorderX;
               mapX += stepX;
               side = 0;
           }
           else
           {
             sideDistY += stepToBorderY;
             mapY += stepY;
             side = 1;
           }
            
           if(map[mapX][mapY] > 0) 
           {
             hit = true; 
           }
        }
        //find the distance the ray has travled.
        if (side == 0)
        {
            wallDist = Math.abs((mapX - player.xPos + (1-stepX)/2)/rayX);
        }
        else
        {
           wallDist = Math.abs((mapY - player.yPos + (1-stepY)/2)/rayY); 
        }
        //the height of the block, depending on how far away it is from the player.
        int sHeight;
        if (wallDist>0)
        {
           sHeight = Math.abs((int)(h/wallDist)); 
        }
        else
        {
          sHeight = h;  
        }
        //find the lowest and highest pixel to change.
        int drawStart = -sHeight/2 + h/2;
        if(drawStart<0)
        {
           drawStart=0; 
        }
        int drawEnd = sHeight/2 + h/2;
        if (drawEnd>= h)
        {
          drawEnd = h - 1;  
        }
        //find the texture that the ray hit.
        int texNum = map[mapX][mapY] -1;
        double wallX;
        if(side==1)
        {
            wallX = (player.xPos + ((mapY - player.yPos + (1 - stepY) / 2) / rayY)*rayX);
    }
         else
         {
             wallX = (player.yPos + ((mapX - player.xPos + (1 - stepX) / 2) / rayX)*rayY);
            }
        wallX-=Math.floor(wallX);
        int graphX = (int)(wallX * (graphic.get(texNum).SIZE));
        graphX = graphic.get(texNum).SIZE - graphX - 1;  
        for (int j = drawStart; j<drawEnd; j++)
        {
           int graphY = (((j*2-h+sHeight) << 6) / sHeight)/2;
           int color;
           //System.out.println(graphX);
           // makes the walls on the y axis darker to distinguish x and y walls better.
           if (side==0)
           {
             color = graphic.get(texNum).display[graphX+(graphY*graphic.get(texNum).SIZE)];
             display[i+j*(w)] = color; 
            }
            else
            {
              color = (graphic.get(texNum).display[graphX+(graphY*graphic.get(texNum).SIZE)]>>1) & 8355711;  
              display[i+j*(w)] = color;  
            }
        }
      
   }
   return display;
 }
}
