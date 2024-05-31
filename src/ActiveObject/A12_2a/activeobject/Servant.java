package ActiveObject.A12_2a.activeobject;

import java.math.BigInteger;

public class Servant implements ActiveObject {
    @Override
    public Result<String> makeString(int count, char filChar) {
        char[] buffer = new char[count];
        for (int i = 0; i < count; i++) {
            buffer[i] = filChar;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
            }
        }
        return new RealResult<>(new String(buffer));
    }

    @Override
    public void displayString(String string) {
        try {
            System.out.println("displayString: " + string);
            Thread.sleep(10);
        } catch (InterruptedException e) {
        }
    }
    public Result<String> add(String x, String y) {
        String retValue = null;
        try {
            BigInteger bigX = new BigInteger(x);
            BigInteger bigY = new BigInteger(y);
            BigInteger bigZ = bigX.add(bigY);
            retValue = bigZ.toString();
        } catch (NumberFormatException e) {
        }
        return new RealResult<>(retValue);
    }
}
