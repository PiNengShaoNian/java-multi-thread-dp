package Future.A9_3a.Content;

public class Retriever {
    public static Content retrieve(final String urlStr) {
        final AsyncContentImpl future = new AsyncContentImpl();

        new Thread() {
            @Override
            public void run() {
                future.setContent(new SyncContentImpl(urlStr));
            }
        }.start();

        return future;
    }
}
