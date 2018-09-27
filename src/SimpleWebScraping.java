import java.io.IOException;
import java.net.URL;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;



public class SimpleWebScraping {
    public static void main(String[] args) {
        String url = "http://erdani.com/tdpl/hamlet.txt";
        System.out.println("Total word count: " + wordCount(url));
        System.out.println("Occurrences of the word \"prince\": " + oneWordCount(url, "prince"));
        System.out.println("Number of unique words in URL: " + uniqueWordCount(url));
    }

    /**
     * Retrieve contents from a URL and return them as a string.
     * @return the contents from the url as a string, or an empty string on error
     */
    public static String urlToString(final String url) {
        Scanner urlScanner;
        try {
            urlScanner = new Scanner(new URL(url).openStream(), "UTF-8");
        } catch (IOException e) {
            return "";
        }
        String contents = urlScanner.useDelimiter("\\A").next();
        urlScanner.close();
        return contents;
    }

    /**
     * @return number of distinct words on the entire site
     */
    public static int wordCount(String url) {
        String contents = urlToString(url);
        String[] wordCount = contents.split("[^a-z]");
        return wordCount.length;
    }

    /**
     * @return occurrence of a word
     */
    public static int oneWordCount(String url, String word) {
        int count = 0;
        word = word.toLowerCase();
        String contents = urlToString(url).toLowerCase();
        String[] contentArray = contents.split("[^a-z]");
        for (int i = 0; i < contentArray.length; i++) {
            if (contentArray[i].equals(word)) {
                count++;
            }
        }
        return count;
    }

    /**
     * Counts the number of unique words from web page given by URL.
     * @return a single number
     */
    public static int uniqueWordCount(String url) {
        String contents = urlToString(url).toLowerCase();
        contents = contents.replace("[^a-z]", "");
        String[] contentArray = contents.split(" ");
        List<String> uniqueWords = new ArrayList<>();
        uniqueWords.add(contentArray[0]);
        for (String i :contentArray) {
            if (uniqueWords.indexOf(i) == -1) {
                uniqueWords.add(i);
            }
        }
        return uniqueWords.size();
    }
}
