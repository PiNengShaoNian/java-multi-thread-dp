package ActiveObject.A12_3a.activeobject;

public class Proxy implements ActiveObject {
    private final SchedulerThread scheduler;
    private final Servant servant;
    public Proxy(SchedulerThread scheduler, Servant servant) {
        this.scheduler = scheduler;
        this.servant = servant;
    }
    @Override
    public Result<String> search(String word) {
        FutureResult<String> future = new FutureResult<>();
        scheduler.invoke(new SearchRequest(servant, future, word));
        return future;
    }
}
