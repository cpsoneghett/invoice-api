package br.com.cpsoneghett.invoiceapi.exception;

public class InvoiceError {

    private String userMessage;
    private String devMessage;

    public InvoiceError(String userMessage, String devMessage) {
        this.userMessage = userMessage;
        this.devMessage = devMessage;
    }

    public String getUserMessage() {
        return userMessage;
    }

    public String getDevMessage() {
        return devMessage;
    }

}
