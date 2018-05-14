package by.alex.web.site.model;

import java.util.Properties;

public class ContactsMessages implements Messages {
    private String life = "";
    private String mts = "";
    private String velcom = "";
    private String email = "";
    private String boss = "";
    private String unp = "";

    public ContactsMessages() {
    }

    public String getLife() {
        return life;
    }

    public void setLife(String life) {
        this.life = life;
    }

    public String getMts() {
        return mts;
    }

    public void setMts(String mts) {
        this.mts = mts;
    }

    public String getVelcom() {
        return velcom;
    }

    public void setVelcom(String velcom) {
        this.velcom = velcom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBoss() {
        return boss;
    }

    public void setBoss(String boss) {
        this.boss = boss;
    }

    public String getUnp() {
        return unp;
    }

    public void setUnp(String unp) {
        this.unp = unp;
    }

    @Override
    public void read(Properties properties) {
        life = properties.getProperty("contacts.life", "");
        mts = properties.getProperty("contacts.mts", "");
        velcom = properties.getProperty("contacts.velcom", "");
        email = properties.getProperty("contacts.email", "");
        boss = properties.getProperty("contacts.boss", "");
        unp = properties.getProperty("contacts.unp", "");
    }

    public void write(Properties properties) {
        properties.setProperty("contacts.life", life);
        properties.setProperty("contacts.mts", mts);
        properties.setProperty("contacts.velcom", velcom);
        properties.setProperty("contacts.email", email);
        properties.setProperty("contacts.boss", boss);
        properties.setProperty("contacts.unp", unp);
    }
}
