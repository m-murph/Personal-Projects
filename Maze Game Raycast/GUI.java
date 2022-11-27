import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Dimension;
import javax.swing.WindowConstants;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.event.KeyListener;
/**
 * GUI holds all the menus and buttons used in the game. it also contains the main method.
 * @author Michael Muphy
 * @version 1
 * @param menu is the game menu that first pops up.
 * @param popup is the popup menu that you can use durring editing.
 * @param playing starts the pre-made maze
 * @param creating starts a blank maze and allows for editing
 * 
 */

public class GUI
{
    private static JFrame menu;
    private static JFrame popup;
    private static Game playing;
    private static Game creating;
    public static void main(String[] args)
    {
       GUI began = new GUI(); 
       began.start();
    }
    
    public  void start()
    {
        menu = new JFrame("Menu");
        
        
        menu.setDefaultCloseOperation(menu.EXIT_ON_CLOSE);
        menu.setPreferredSize(new Dimension(500,200));
        
        menu.setLayout( new GridBagLayout());
        
        JButton play = new JButton("Solve the maze");
        play.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent a)
            {
              menu.dispose();
              playing = new Game("p"); 
              menu.setFocusable( false );
              
            }
        });
        JButton create = new JButton("Create a maze");
        create.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent a)
            {
              menu.dispose();
              creating = new Game("c");  
              menu.setFocusable( false );
              
            }
        });
        
        
        java.net.URL picloc = getClass().getResource("title.png");
        JLabel pic = new JLabel(new ImageIcon(picloc));
        menu.add(pic);
        menu.add(new JLabel("     "));
        menu.add(play);
        menu.add(new JLabel("     "));
        menu.add(create);
        menu.pack();
        menu.setVisible(true);
    }
    
    
    public static void openPopUpMenu()
    {
       popup = new JFrame("Map Edit Menu");
       popup.add(new JLabel("Menu Options:  "));
       popup.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
       popup.setPreferredSize(new Dimension(450,100));
       popup.setLayout( new FlowLayout());
       JButton playMaze = new JButton("Play This Maze");
       playMaze.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent a)
            {
              creating.unedit();
              
              popup.setVisible(false);
              
            }
        });
        JButton edit = new JButton("Return to Editing");
        edit.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent b)
            {
              creating.breakable=true;
              
              popup.setVisible(false);
              
            }
        });
       JButton cancel = new JButton("Cancel");
       cancel.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent c)
            {
                
                popup.setVisible(false);
            }
        });
       popup.add(playMaze);
       menu.add(new JLabel("  "));
       popup.add(edit);
       menu.add(new JLabel("  "));
       popup.add(cancel);
       popup.pack();
       popup.setVisible(true);
    }
}
