import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
/**
 * This stores all the immages that get used as graphics for the game.
 *
 * @author Mihcael Murphy
 * @version 1
 * @param wood is the texture for the unbreakable block around the map.
 * @param stone is the "you win" block.
 * @param dirt is the brick block.
 * @param display is the array of all the pixels in the game window
 * @param SIZE is the size of the picture.
 * @param loc is the location of an immage.
 */
public class Graphic
{
  public static Graphic wood = new Graphic("ketchup3.png", 64);
  public static Graphic stone = new Graphic("stone.png", 64);
  public static Graphic dirt = new Graphic("brick.png", 64);  
  public int[] display;
  public final int SIZE;
  private String loc;
  public Graphic(String location, int size)
  {
     SIZE=size;
     display = new int[SIZE*SIZE];
     loc = location;
     load();
    }
   //load() is a Borrowed method from stackoverflow.com
    private void load()
    {    
       try 
       {
         BufferedImage image = ImageIO.read(getClass().getResourceAsStream(loc));  
         int width = image.getWidth();
         int height = image.getHeight();
         image.getRGB(0,0,width,height,display,0,width);
    }
    catch (IOException e)
    {
        e.printStackTrace();
    }
  }

}
