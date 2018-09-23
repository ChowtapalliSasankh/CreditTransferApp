package com.example.home.token;

/**
 * Created by home on 9/20/2018.
 */

public class Model
{
    String name;
    String email;

    public Model(String name, String email)
    {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }
}
