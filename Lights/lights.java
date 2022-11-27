import java.io.IOException;
import java.lang.Runtime;
import java.util.concurrent.TimeUnit;
import java.lang.InterruptedException;
import java.util.Scanner;
import java.util.Random;
/**
 * Write a description of class test2 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class test2
{
        public static void main(String[] args) {
            allOff();
            int x;
            boolean ra = false;
            boolean oa = false;
            boolean ga = false;
            boolean ba = false;
            Random r = new Random();
            for (int i = 0; i<500; i++){
                x = r.nextInt(4);
                if (x == 0 && ra == false){
                    redOn();
                    ra = true;
                }
                else if(x == 0 && ra == true){
                    redOff();
                    ra = false;
                }
                else if (x == 1 && oa == false){
                    orangeOn();
                    oa = true;
                }
                else if (x == 1 && oa == true){
                    orangeOff();
                    oa = false;
                }
                else if (x == 2 && ga == false){
                    greenOn();
                    ga = true;
                }
                else if (x == 2 && ga == true){
                    greenOff();
                    ga = false;
                }
                else if (x == 3 && ba == false){
                    blueOn();
                    ba = true;
                }
                else if (x==3 && ba == true){
                    blueOff();
                    ba = false;
                }
                System.out.println(x);
                pause(350);                
            }
            allOff();
        }       
                
        public static void pause(int x){
            try{
                 TimeUnit.MILLISECONDS.sleep(x);
                }
                catch(InterruptedException ex){
                    
                }
        }
        
        public static void redOn(){
            try{
                        String path = "redon.bat";
                        
                        Runtime rn = Runtime.getRuntime();
                        Process pr=rn.exec(path);
                        pr.waitFor();
                    }
                    catch (IOException ex){
                        System.out.println("Error");
                    }
                    catch (InterruptedException ex){}
        }
        public static void redOff(){
            try{
                        String path = "redoff.bat";
                        Runtime rn = Runtime.getRuntime();
                        Process pr=rn.exec(path);
                        pr.waitFor();
                    }
                    catch (IOException ex){
                        System.out.println("Error");
                    }
                    catch (InterruptedException ex){}
        }
        
        
        public static void orangeOn(){
            try{
                        String path = "orangeon.bat";
                        Runtime rn = Runtime.getRuntime();
                        Process pr=rn.exec(path);
                        pr.waitFor();
                    }
                    catch (IOException ex){
                        System.out.println("Error");
                    }
                    catch (InterruptedException ex){}
        }
        public static void orangeOff(){
            try{
                        String path = "orangeoff.bat";
                        Runtime rn = Runtime.getRuntime();
                        Process pr=rn.exec(path);
                        pr.waitFor();
                    }
                    catch (IOException ex){
                        System.out.println("Error");
                    }
                    catch (InterruptedException ex){}
        }
        
        public static void greenOn(){
            try{
                        String path = "greenon.bat";
                        Runtime rn = Runtime.getRuntime();
                        Process pr=rn.exec(path);
                        pr.waitFor();
                    }
                    catch (IOException ex){
                        System.out.println("Error");
                    }
                    catch (InterruptedException ex){}
        }
        public static void greenOff(){
            try{
                        String path = "greenoff.bat";
                        Runtime rn = Runtime.getRuntime();
                        Process pr=rn.exec(path);
                        pr.waitFor();
                    }
                    catch (IOException ex){
                        System.out.println("Error");
                    }
                    catch (InterruptedException ex){}
        }
        
        public static void blueOn(){
            try{
                        String path = "blueon.bat";
                        Runtime rn = Runtime.getRuntime();
                        Process pr=rn.exec(path);
                        pr.waitFor();
                    }
                    catch (IOException ex){
                        System.out.println("Error");
                    }
                    catch (InterruptedException ex){}
        }
        public static void blueOff(){
            try{
                        String path = "blueOff.bat";
                        Runtime rn = Runtime.getRuntime();
                        Process pr=rn.exec(path);
                        pr.waitFor();
                    }
                    catch (IOException ex){
                        System.out.println("Error");
                    }
                    catch (InterruptedException ex){}
        }
        
        public static void allOff(){
            redOff();
            orangeOff();
            greenOff();
            blueOff();
        }
    }

    

        
        
        
        
    

