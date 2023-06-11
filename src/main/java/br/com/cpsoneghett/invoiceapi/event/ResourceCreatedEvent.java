package br.com.cpsoneghett.invoiceapi.event;


import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationEvent;

public class ResourceCreatedEvent<T> extends ApplicationEvent {

    private HttpServletResponse response;
    private T code;

    public ResourceCreatedEvent(Object source, HttpServletResponse response, T code) {
        super(source);
        this.response = response;
        this.code = code;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public T getCode() {
        return code;
    }
}
