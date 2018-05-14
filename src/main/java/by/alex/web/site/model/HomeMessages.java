package by.alex.web.site.model;

import java.util.Properties;

public class HomeMessages implements Messages {
    private String header = "";
    private String message = "";
    private String phone = "";
    private String discountsHeader = "";
    private String discountsMessage = "";

    public HomeMessages() {

    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDiscountsHeader() {
        return discountsHeader;
    }

    public void setDiscountsHeader(String disqountsHeader) {
        this.discountsHeader = disqountsHeader;
    }

    public String getDiscountsMessage() {
        return discountsMessage;
    }

    public void setDiscountsMessage(String discountsMessage) {
        this.discountsMessage = discountsMessage;
    }

    @Override
    public void read(Properties properties) {
        header = properties.getProperty("home.header", "");
        message = properties.getProperty("home.message", "");
        phone = properties.getProperty("home.phone", "");
        discountsHeader = properties.getProperty("home.discounts.header", "");
        discountsMessage = properties.getProperty("home.discounts.message", "");
    }

    public void write(Properties properties) {
        properties.setProperty("home.header", header);
        properties.setProperty("home.message", message);
        properties.setProperty("home.phone", phone);
        properties.setProperty("home.discounts.header", discountsHeader);
        properties.setProperty("home.discounts.message", discountsMessage);
    }
}
