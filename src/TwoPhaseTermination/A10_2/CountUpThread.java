package TwoPhaseTermination.A10_2;

public class CountUpThread extends Thread {
    // 计数值
    private long counter = 0;

    // 终止请求
    public void shutdownRequest() {
        interrupt();
    }

    // 线程体
    public void run() {
        try {
            while (!isInterrupted()) {
                doWork();
            }
        } catch (InterruptedException e) {
        } finally {
            doShutdown();
        }
    }

    // 操作
    private void doWork() throws InterruptedException {
        counter++;
        System.out.println("doWork: counter = " + counter);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
        }
    }

    // 终止请求
    private void doShutdown() {
        System.out.println("doShutdown: counter = " + counter);
    }
}
