package ActiveObject.A12_3b.searcher;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SearcherImpl extends Searcher {
    private final ExecutorService service = Executors.newSingleThreadExecutor();
    public void shutdown() {
        service.shutdown();
    }
    @Override
    public void search(String word, Display display) {
        class SearchRequest implements Runnable {
            @Override
            public void run() {
                System.out.print("search(" + word + ")");
                for (int i = 0; i < 50; i++) {
                    System.out.print(".");
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                    }
                }
                System.out.println("found.");
                String url = "http://somewhere/" + word + ".html"; // dummy URL
                display.display("word = " + word + ", URL = " + url);
            }
        }
        service.execute(new SearchRequest());
    }
}
