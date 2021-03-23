package me.inc.bookingapp.model.binding;

public class ServiceResponse<T> {

    private String status;
    private T data;

    public String getStatus() {
        return status;
    }

    public ServiceResponse() {
    }

    public ServiceResponse(String status, T data) {
        this.status = status;
        this.data = data;
    }

    public ServiceResponse<T> setStatus(String status) {
        this.status = status;
        return this;
    }

    public T getData() {
        return data;
    }

    public ServiceResponse<T> setData(T data) {
        this.data = data;
        return this;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ServiceResponse{");
        sb.append("status='").append(status).append('\'');
        sb.append(", data=").append(data);
        sb.append('}');
        return sb.toString();
    }
}
