package Future.A9_3a.Content;

import java.io.DataInputStream;
import java.io.EOFException;
import java.net.URL;

class SyncContentImpl implements Content {
    private byte[] contentBytes;
    public SyncContentImpl(String urlStr) {
        System.out.println(Thread.currentThread().getName() + ": Getting " + urlStr);
        try {
            URL url = new URL(urlStr);
            DataInputStream in = new DataInputStream(url.openStream());
            byte[] buffer = new byte[1];
            int index = 0;
            try {
                while (true) {
                    int c = in.readUnsignedByte();
                    if (buffer.length <= index) {
                        byte[] largerBuffer = new byte[buffer.length * 2];
                        System.arraycopy(buffer, 0, largerBuffer, 0, index);
                        buffer = largerBuffer;
                        // System.out.println("Enlarging buffer to " + buffer.length);
                    }
                    buffer[index++] = (byte)c;
                    // System.out.print("Getting " + index + " bytes from " + urlstr);
                }
            } catch (EOFException e) {
            } finally {
                in.close();
            }
            contentBytes = new byte[index];
            System.arraycopy(buffer, 0, contentBytes, 0, index);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public byte[] getBytes() {
        return contentBytes;
    }
}
