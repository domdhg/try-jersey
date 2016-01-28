/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example;

import javax.ws.rs.QueryParam;
import javax.ws.rs.PathParam;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Encoded;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class MyBeanParam 
{
    // I use this Class to demonstrate how 
    // an instance can be created from Params on the URL request.
    // and to see how a complext Class instance
    // can be returned in plain text (beanParam2)
    // or in JSON (not done yet)
    //
    private String cost;
    
    @PathParam("orderline")
    private String pathParam;
 
    @MatrixParam("m")
    @Encoded
    @DefaultValue("default")
    private String matrixParam;
 
    @HeaderParam("header")
    private String headerParam;
 
    private String quantity;
 
    public MyBeanParam()
    {
        cost = "";
        pathParam = "";
        matrixParam = "";
        headerParam = "";
        quantity = "";
    }
    public MyBeanParam(@QueryParam("quantity") String quantity,
            @QueryParam("cost") String aCost) {
        this.quantity = quantity;
        this.cost = aCost;
    }
 
    public String getQuantity() {
        return quantity;
    }
    
     public String getPathParam() {
        return pathParam;
    }
   
    public String toString()
    {
        return( "MyBeanParam : pathParam [" + pathParam 
                + "] quantity [" + quantity + "] Cost [" + cost + "]");
    }
}