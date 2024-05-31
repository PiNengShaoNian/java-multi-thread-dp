package ActiveObject.A12_3a.activeobject;

class RealResult<T> implements Result<T> {
    private final T resultValue;
    public RealResult(T resultValue) {
        this.resultValue = resultValue;
    }
    @Override
    public T getResultValue() {
        return resultValue;
    }
}
