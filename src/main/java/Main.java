import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        long startTime = System.currentTimeMillis();
        RssReaderTimeCounter totalThreadTime = new RssReaderTimeCounter();
        final ArrayList<String> RssList = new ArrayList<>(Arrays.asList(
                "http://feeds.reuters.com/news/artsculture",
                "http://feeds.skynews.com/feeds/rss/world",
                "https://444.hu/feed",
                "http://www.ibrany.hu/rss.xml",
                "http://www.origo.hu/contentpartner/rss/origoall/origo.xml",
                "http://origo.hu/contentpartner/rss/365/origo.xml",
                "http://origo.hu/contentpartner/rss/filmklub/origo.xml",
                "http://www.szoftverbazis.hu/rss/index.xml",
                "http://www.origo.hu/contentpartner/rss/tafelspicc/origo.xml",
                "http://www.pravdareport.com/world/export-news.xml"
        ));
        ArrayList<RssReaderThread> threadList = new ArrayList<>();
        for (String rssLink: RssList) {
            RssReaderThread current = new RssReaderThread(rssLink, totalThreadTime);
            threadList.add(current);
            current.start();
        }
        for (RssReaderThread currentThread: threadList) {
            currentThread.join();
        }
        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Total time elapsed: " + totalTime);
        System.out.println("Total thread time elapsed: " + totalThreadTime.getCount());
    }
}
