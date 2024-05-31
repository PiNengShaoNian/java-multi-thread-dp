package ActiveObject.A12_2a.activeobject;

class DisplayStringRequest extends MethodRequest<Object> {
    private final String string;
    public DisplayStringRequest(Servant servant, String string) {
        super(servant, null);
        this.string = string;
    }
    @Override
    public void execute() {
        servant.displayString(string);
    }
}
