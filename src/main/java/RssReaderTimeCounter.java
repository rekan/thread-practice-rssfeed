public class RssReaderTimeCounter {

    private long count = 0;

    public void add(long time) {
        count += time;
    }

    public long getCount() {
        return count;
    }
}
