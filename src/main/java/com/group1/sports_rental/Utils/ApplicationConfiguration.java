package com.group1.sports_rental.Utils;

import java.util.Map;

public class ApplicationConfiguration {

    static ApplicationConfiguration instance = null;

    public ApplicationConfiguration()
    {
        Map<String, String> environmentMap = System.getenv();
        for (Map.Entry<String, String> entry : environmentMap.entrySet())
        {
            if (entry.getKey().equals("database.url"))
            {
                this.DATABASE_URL = entry.getValue();
            }
            if (entry.getKey().equals("database.username"))
            {
                this.DATABASE_USERNAME = entry.getValue();
            }
            if (entry.getKey().equals("database.password"))
            {
                this.DATABASE_PASSWORD = entry.getValue();
            }
            if (entry.getKey().equals("stripe.public.key"))
            {
                this.STRIPE_PUBLICKEY = entry.getValue();
            }
            if (entry.getKey().equals("stripe.secret.key"))
            {
                this.STRIPE_PRIVATEKEY = entry.getValue();
            }
        }
    }
    public static ApplicationConfiguration instance()
    {
        if (instance == null)
        {
            instance = new ApplicationConfiguration();
        }
        return instance;
    }

    private String DATABASE_URL;

    private String DATABASE_USERNAME;

    private String DATABASE_PASSWORD;

    private String STRIPE_PUBLICKEY;

    private String STRIPE_PRIVATEKEY;

    public String getDATABASE_URL() {
        return DATABASE_URL;
    }

    public void setDATABASE_URL(String DATABASE_URL) {
        this.DATABASE_URL = DATABASE_URL;
    }

    public String getDATABASE_USERNAME() {
        return DATABASE_USERNAME;
    }

    public void setDATABASE_USERNAME(String DATABASE_USERNAME) {
        this.DATABASE_USERNAME = DATABASE_USERNAME;
    }

    public String getDATABASE_PASSWORD() {
        return DATABASE_PASSWORD;
    }

    public void setDATABASE_PASSWORD(String DATABASE_PASSWORD) {
        this.DATABASE_PASSWORD = DATABASE_PASSWORD;
    }

    public String getSTRIPE_PUBLICKEY() {
        return STRIPE_PUBLICKEY;
    }

    public void setSTRIPE_PUBLICKEY(String STRIPE_PUBLICKEY) {
        this.STRIPE_PUBLICKEY = STRIPE_PUBLICKEY;
    }

    public String getSTRIPE_PRIVATEKEY() {
        return STRIPE_PRIVATEKEY;
    }

    public void setSTRIPE_PRIVATEKEY(String STRIPE_PRIVATEKEY) {
        this.STRIPE_PRIVATEKEY = STRIPE_PRIVATEKEY;
    }
}
