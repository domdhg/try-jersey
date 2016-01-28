/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example;

/**
 *
 * @author admin
 */

import java.util.List;;
import java.util.ArrayList;

public class NotificationChannel {
    
    private String name;
    private long  version;
    private List<String> parameters;
    
    public NotificationChannel()
    {
        name = "";
        version = -1;
        parameters = null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public List<String> getParameters() {
        return parameters;
    }

    public void setParameters(List<String> parameters) {
        this.parameters = parameters;
    }
    
}
