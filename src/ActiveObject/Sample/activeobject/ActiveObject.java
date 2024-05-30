package ActiveObject.Sample.activeobject;

public interface ActiveObject {
    public abstract Result<String> makeString(int count, char filChar);
    public abstract void displayString(String string);
}
