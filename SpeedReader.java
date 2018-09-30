import java.awt.*;
import java.io.FileNotFoundException;

public class SpeedReader{

   /**
   * This method determines which position should be the "center".
   * e.g. the offeset to determine the breakpoint of the String.
   * @param int length: the length of a String.
   * @return int offset: the offset of the String.
   */
    public static int determineOffset(int length) {
        if (length <= 1 ) {
            return 1;
        }
        if (length <= 5) {
            return 2;
        }
        if (length <= 9) {
            return 3;
        }
        if (length <= 13) {
            return 4;
        }
        return 5;
    }

    public static void main(String[] args) throws InterruptedException, FileNotFoundException {
        
        //Check conditions
        if (args.length != 5) {
            System.out.println("Error: Improper Usage");
            System.out.println("Usage: SpeedReader <filename> <width> <height> <font size> <wpm>");

            return;
        }
        
        
        //Assign params to variables
        String file = args[0];
        int width = Integer.parseInt(args[1]);
        int height = Integer.parseInt(args[2]);
        int fontSize = Integer.parseInt(args[3]);
        int wpm = Integer.parseInt(args[4]);
        
        //Initialize viariables
        int wordWidth = 0;
        int wordHeight = 0;
        int offset = 0;
        String word = null;
        int sleepTime = 60000/wpm;

        //Set up drawing panel and construct associated objects
        DrawingPanel panel = new DrawingPanel(width, height);
        Graphics graphics = panel.getGraphics();
        Font wordFont = new Font("Courier", Font.BOLD, fontSize);
        graphics.setFont(wordFont);
        FontMetrics metrics = graphics.getFontMetrics(wordFont);

        WordGenerator gen = new WordGenerator(file);
  
        while (gen.hasNext()){
            word = gen.next();
           
            //Determine the position of the offset and pick the char at that position
            offset = determineOffset(word.length());
            char midChar = word.charAt(offset-1);
            
            //Change word's offset th char to a space
            char[] wordArr = word.toCharArray();
            wordArr[offset-1] = ' ';
            word = new String(wordArr);

            //Calculate the width of the word with from 0 to offset char
            wordWidth = metrics.charsWidth(word.toCharArray() , 0, offset);  
            int charWidth = metrics.charsWidth(word.toCharArray() , 0, 1); 
            wordHeight = metrics.getHeight();
            
            //Clear panel
            panel.clear();
            
            //Draw the word with without the offset th char
            graphics.drawString(word, (width/2 - wordWidth), height/2 + wordHeight/4);
            
            //Change color to red and draw the char at offset
            graphics.setColor(Color.RED);
            graphics.drawString(String.valueOf(midChar), width/2-charWidth, height/2 + wordHeight/4);
            
            //Set color back to black
            graphics.setColor(Color.BLACK);
            
            Thread.sleep(sleepTime);
        }
        System.out.println("The number of words read : " + gen.getWordCount());
        System.out.println("The number of sentences read : " + gen.getSentenceCount());
    }

}
