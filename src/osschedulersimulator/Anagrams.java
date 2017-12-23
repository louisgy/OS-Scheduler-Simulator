// Author : Georgy Louis
// May 22,2017

package osschedulersimulator;

public class Anagrams implements Bound {

    private String prefix;
    private String word;

    public Anagrams(String prefix, String word) {
        this.prefix = prefix;
        this.word = word;
    }

    @Override
    public void work() {
        writeAnagrams(prefix, word);
    }

    public void writeAnagrams(String prefix, String word) {
        if (word.length() <= 1) {
//            System.out.println(prefix + word + "\n");
        
        } else {
            for (int i = 0; i < word.length(); i++) {
                String cur = word.substring(i, i + 1);
                String before = word.substring(0, i); // letters before cur
                String after = word.substring(i + 1); // letters after cur
                writeAnagrams(prefix + cur, before + after);
            }
        }
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }
    
    

}
