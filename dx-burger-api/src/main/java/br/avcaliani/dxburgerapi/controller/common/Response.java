package br.avcaliani.dxburgerapi.controller.common;

import lombok.Data;

import java.util.Objects;

/**
 * Generic Response Template.
 *
 * @param <T> Response Data Type.
 * @author Anthony Vilarim Caliani
 * @since 19.2.0
 */
@Data
public class Response<T> {

    private T data;
    private String error;
    private int status;

    /**
     * Success Constructor.
     *
     * @param data Response Data.
     */
    public Response(T data) {
        this.status = 200;
        this.data = data;
    }

    /**
     * Success Constructor passing Status Code.
     *
     * @param data Response Data.
     * @param status Response Status Code.
     */
    public Response(T data, int status) {
        this.status = status;
        this.data = data;
    }

    /**
     * Error Constructor.
     *
     * @param error Error Message.
     * @param status Error Status Code.
     */
    public Response(Throwable error, int status) {
        this.status = status;
        this.error = error != null ? error.getMessage() : null;
    }

    /**
     * Full Constructor.
     *
     * @param data Response Data.
     * @param error Response Error Message.
     * @param status Response Status Code.
     */
    public Response(T data, String error, int status) {
        this.status = status;
        this.data = data;
        this.error = error;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Response<?> response = (Response<?>) o;
        return status == response.status &&
                Objects.equals(data, response.data) &&
                Objects.equals(error, response.error);
    }

    @Override
    public int hashCode() {
        return Objects.hash(data, error, status);
    }

    @Override
    public String toString() {
        return "Response{data=" + data + ", error='" + error + '\'' + ", status=" + status + '}';
    }
}

