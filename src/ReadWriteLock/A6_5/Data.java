package ReadWriteLock.A6_5;

import ReadWriteLock.Sample.ReadWriteLock;

public class Data {
    private final char[] buffer;
    private final ReadWriteLock lock = new ReadWriteLock();

    public Data(int size) {
        this.buffer = new char[size];
        for (int i = 0; i < buffer.length; i++) {
            buffer[i] = '*';
        }
    }

    public char[] read() throws InterruptedException {
        try {
            lock.readLock();
            return doRead();
        } finally {
            lock.readUnlock();
        }
    }

    public void write(char c) throws InterruptedException {
//   1.锁的获取位置：
//   第一个代码片段： lock.writeLock()调用是在try块之外。这意味着如果lock.writeLock()抛出异常，这个异常会直接传递给调用者，不会进入try块执行doWrite(c)。
//   第二个代码片段： lock.writeLock()调用在try块之内。如果lock.writeLock()抛出异常，finally块仍然会执行并尝试调用lock.writeUnlock()。
//   然而，在这种情况下，通常不会有问题，因为异常发生时锁并未成功获取。
//
//   2.异常处理机制：
//   第一个代码片段： 如果lock.writeLock()抛出异常，异常会直接传递出去，不会执行doWrite(c)，也不会进入finally块。
//   第二个代码片段： 如果lock.writeLock()抛出异常，异常会在try块中被捕获并进入finally块，finally块会尝试调用lock.writeUnlock()。由于锁未成功获取，通常不会有实际影响，但这种情况可能会增加代码的复杂性和理解难度。
        try {
            lock.writeLock();
            doWrite(c);
        } finally {
            lock.writeUnlock();
        }
    }

    private char[] doRead() {
        char[] newbuf = new char[buffer.length];
        for (int i = 0; i < buffer.length; i++) {
            newbuf[i] = buffer[i];
        }
        slowly();
        return newbuf;
    }

    private void doWrite(char c) {
        for (int i = 0; i < buffer.length; i++) {
            buffer[i] = c;
            slowly();
        }
    }

    private void slowly() {
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
        }
    }
}
