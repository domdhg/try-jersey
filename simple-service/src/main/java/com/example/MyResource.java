package com.example;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.QueryParam;
import javax.ws.rs.BeanParam;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.PathSegment;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.GenericEntity;
import javax.activation.MimetypesFileTypeMap;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import com.example.NotificationMgr;


/**
 * Root resource (exposed at "domresource" path)
 */
@Path("/domresource/{versionId}")
public class MyResource {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Path("/testing")
    @Produces(MediaType.TEXT_PLAIN)
    //@Produces("application/json")
    public String getAIt(@PathParam("versionId") String versionId) {
        return "Got it for REAL !!!!! - version [" + versionId + "]";
    }
    
    @GET
    @Path("/notificationchannel")
    @Produces("application/json")
    public NotificationChannel getNotificationChannelJSON(@PathParam("versionId") String versionId) {
        
        String channelName = "CHANNEL-XYZ - version [" + versionId + "]";
        NotificationChannel toReturn = new NotificationChannel();
        toReturn.setName(channelName);
        toReturn.setVersion(Long.parseLong(versionId));
        ArrayList<String> someParams = new ArrayList<String>();
        someParams.add("Size");
        someParams.add("Expiry");
        someParams.add("CreationDateTime");
        toReturn.setParameters(someParams);
        
        return( toReturn );
    }
    /* Get that returns HTML 
       * Should execute when the caller says that it accepts text/HTML
    */
    @GET
    @Path("/testing")
    @Produces(MediaType.TEXT_HTML)
    public String getGreeting() {
        return "<html><body><h1>Hello "+ "DOM NAME"+"!</h1></body></html>";
    }
    
    
    /*
     * Simple single argument
     *
    @PUT
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public String setName(
            @DefaultValue("GLOAGUEN") @QueryParam("content") String content) {
        String theContent = new String(
                    "This is the content [" + content + "]");
        System.out.println(theContent);
        return( theContent );
    }
    */
    
    /*
      * This is asccepting a single string and a list of Strings.
    */
    /*@PUT
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public String setName(
            @DefaultValue("GLOAGUEN") @QueryParam("content") String content,
            @QueryParam("nameList") List<String> aNameList) {
        String theFullContent = new String(
                    "Content is [" + content + "]. The nameList is [" + aNameList.toString() + "]");
        System.out.println(theFullContent);
        
        Iterator itr = aNameList.iterator();
        while(itr.hasNext()) 
        {
         Object element = itr.next();
         System.out.print(element + " ");
        }        
        
        
        return( theFullContent );
    }
    */
     /*
      * This is asccepting a single string and a list of Strings.
    */
    @PUT
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/myconfig/notifications")
    public String setEnableNotifications(
            @DefaultValue("false") @QueryParam("enabled") String enabled)
    {
        String theFullContent = new String(
                    "EnableNotifications requested to be set to [" + enabled + "]." );
        System.out.println(theFullContent);
                
        if ( enabled.equals("true")== true || enabled.equals("false") == true)
        {
            NotificationMgr mgr = NotificationMgr.getInstance();
            mgr.setNotificationsEnabled(Boolean.parseBoolean(enabled));
        }
        return( theFullContent );
    }
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/myconfig/notifications/enabled")
    public String getEnableNotifications(
            )
    {   
        NotificationMgr mgr = NotificationMgr.getInstance();
        
        return( Boolean.toString(mgr.isNotificationsEnabled() ) );
    }
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/myconfig/notifications/channel")
    public String getNotificationsChannel(
            )
    {   
        NotificationMgr mgr = NotificationMgr.getInstance();
        if ( mgr.isNotificationsEnabled() )
        {
        return( "Channel123" );
        }
        else
        {
            return("");
        }
    }
    
    /*@Get
    @Produces("text/html")
    public String getA() {
        return "<html><body><h1>Hello "+ "GetA running "+"!</h1></body></html>";
    }*/
    @GET
    @Path("/customers/{id}")
    @Produces("text/plain")
    public String getCustomer(@PathParam("versionId") String versionId,
            @PathParam("id") String customerId) 
    {  
        String toReturn = "";
        toReturn = "Searching for customer [" + customerId + "] with Version [" + versionId + "]"; 
        return( toReturn );
    }
    
    /* example URL :  /customers/1/order/2/price/2000/weight/2 */
    @GET
    @Path("/order/{orderid}/{search:.*}")
    public String findItem(@PathParam("versionId") Long versionId,
                         @PathParam("orderid") Long orderId,
                         @PathParam("search") String searchString,
                         @PathParam("search") List<PathSegment> searchList) {
        String toReturn = "";
        toReturn = "Searching for Order [" + orderId + "] searchString [" + searchString + 
                "] searchList [" + searchList.toString() + "] with Version [" + versionId + "]"; 
        return( toReturn );
    }
    
    @GET
    @Path("/orderline/{orderLineId}")
    public String getOrderLine(@BeanParam MyBeanParam beanParam, 
            @PathParam("orderLineId") Long orderLineId,
            String entity) 
    {
        final String pathParam = beanParam.getPathParam(); // contains injected path parameter "p"
    
        String toReturn = "";
        toReturn = "getOrderLine : OrderLineId [" + orderLineId + "] MyBeanParam [" 
                + beanParam + "]"; 
        return( toReturn );
}
    @GET
    @Path("/beanParam")
    @Produces("text/plain")
    //@Produces("application/json")
    public Response getMyBeanParam(@BeanParam MyBeanParam beanParam) 
    {
        final String pathParam = beanParam.getPathParam(); // contains injected path parameter "p"
    
        System.out.println("MyBeanParam [" + beanParam + "]"); 
        
        
        GenericEntity< MyBeanParam > entity;
        entity  = new GenericEntity<MyBeanParam>( beanParam ) { };
        
        ResponseBuilder builder = Response.ok(entity);
        
        
        return( builder.build() );
        
    }
    
    // The following function proves how to return a Stringified version of a complex
    // object, as a plain/text response - not a JSON or XML.
    // The MessageBodyWriter interface must be implemented otherwise Jersey does not
    // how to turn the Complex into a plain text string it seems.
    @GET
    @Path("/beanParam2")
    @Produces("text/plain")
    public MyBeanParam getMyBeanParam2(@BeanParam MyBeanParam beanParam) 
    {
        final String pathParam = beanParam.getPathParam(); // contains injected path parameter "p"
    
        System.out.println("MyBeanParam [" + beanParam + "]"); 
        return( beanParam );
        
    }
}
