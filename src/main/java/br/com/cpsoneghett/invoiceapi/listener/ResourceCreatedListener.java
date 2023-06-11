package br.com.cpsoneghett.invoiceapi.listener;

import br.com.cpsoneghett.invoiceapi.event.ResourceCreatedEvent;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Component
public class ResourceCreatedListener<T> implements ApplicationListener<ResourceCreatedEvent> {

    @Override
    public void onApplicationEvent(ResourceCreatedEvent event) {
        HttpServletResponse response = event.getResponse();
        T id = (T) event.getCode();

        addHeaderLocation(response, id);
    }

    private void addHeaderLocation(HttpServletResponse response, T id) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(id).toUri();
        response.setHeader("Location", uri.toASCIIString());
    }
}
