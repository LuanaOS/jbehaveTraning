package client.model;

public class Envelope<T> {

    private T data;

    public Envelope() {
    }

    public Envelope(T data) {
        this.data = data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public T getData() {
        return this.data;
    }

    @Override
    public String toString() {
        return "RequestEnvelope{" + "data=" + data + '}';
    }
}
