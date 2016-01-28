/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.WebApplicationException;
import java.io.IOException;
import java.io.InputStream;
import java.io.DataOutputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import javax.ws.rs.core.MultivaluedMap;

/**
 *
 * @author admin
 */
@Provider
@Produces
public class MyBeanParamMessageBodyWriter implements MessageBodyWriter<MyBeanParam> 
{   
    @Override
    public boolean isWriteable(Class type, Type genericType, 
            Annotation[] annotations, MediaType mediaType) {
        System.out.println("isWriteable called...DOM");

        return true;
    }
    @Override
    public long getSize(MyBeanParam myBeanParam, Class<?> type, Type genericType,
                        Annotation[] annotations, MediaType mediaType) {
        // deprecated by JAX-RS 2.0 and ignored by Jersey runtime

        return myBeanParam.toString().length();
    }
    
    @Override
    public void writeTo(MyBeanParam myBeanParam,
                        Class<?> type,
                        Type genericType,
                        Annotation[] annotations,
                        MediaType mediaType,
                        MultivaluedMap<String, Object> httpHeaders,
                        OutputStream entityStream)

                        throws IOException, WebApplicationException {

        StringBuilder sb = new StringBuilder();
        sb.append(myBeanParam.toString());
        DataOutputStream dos = new DataOutputStream(entityStream);
        dos.writeUTF(sb.toString());
    }
}
