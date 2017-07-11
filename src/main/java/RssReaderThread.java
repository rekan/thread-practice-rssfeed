import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class RssReaderThread extends Thread {

    private String rssURL;

    private long elapsedTime;
    private RssReaderTimeCounter totalThreadCounter;

    public RssReaderThread(String rssURL, RssReaderTimeCounter totalThreadCounter) {

        this.rssURL = rssURL;
        this.totalThreadCounter = totalThreadCounter;
    }

    public void run() {

        long startTime = System.currentTimeMillis();

        URL rss = null;
        try {
            rss = new URL( this.rssURL );
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(rss.openStream()));

            String inputLine;
            int countLines = 0;
            while ((inputLine = in.readLine()) != null) {
                // System.out.println(inputLine);
                countLines++;
            }
            System.out.println( getName() + "Thread / RSS:" + this.rssURL);
            System.out.println( getName() + "Thread / RSS line count:" + countLines);
            in.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        long stopTime = System.currentTimeMillis();
        elapsedTime = stopTime - startTime;
        totalThreadCounter.add(elapsedTime);
        System.out.println( getName() + "Thread / Elapsed time: " + elapsedTime + " ms");

    }
}
