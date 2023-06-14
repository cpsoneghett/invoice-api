package br.com.cpsoneghett.invoiceapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class InvoiceApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(InvoiceApiApplication.class, args);
    }

}
