import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
* The WordGenerator Class which read Strings from file and count the 
* word number and sentence number.
*
* @author  [katticar] [jinannan]
* @version 1.0
* @since   2017-09-12
*/
public class WordGenerator {
    private Scanner text;
    private int wordCount;
    private int sentenceCount;

    /* Constructor */
    public WordGenerator(String filename) throws FileNotFoundException {
        this.text = new Scanner(new File(filename));
        this.wordCount = 0;
        this.sentenceCount = 0;
    }

    /* @see java.util.Scanner hasNext() */
    public boolean hasNext() {
        return this.text.hasNext();
    }

   /**
   * This is the next method which read the next String from the file.
   * Is also calculated the already read words and sentences at the same time.
   *
   * @param Nothing.
   * @return nextWord: the next String that Scanner reads.
   */
    public String next(){
        this.wordCount++;
        String nextWord = this.text.next();
        if (nextWord.endsWith(".") || nextWord.endsWith("?") || nextWord.endsWith("!")) {
            this.sentenceCount++;
        }
        return nextWord;

    }
    
   /*Getter for wordCount*/
    public int getWordCount() {
        return this.wordCount;
    }
        
   /*Getter for sentenceCount*/
    public int getSentenceCount() {
        return this.sentenceCount;
    }
    
}
