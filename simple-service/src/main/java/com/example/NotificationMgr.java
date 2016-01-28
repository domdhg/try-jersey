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
public class NotificationMgr 
{
    
    static NotificationMgr _notificationMgr = null;
    
    boolean notificationsEnabled;
    
    private NotificationMgr()
            {
                notificationsEnabled = false;
            }

    public boolean isNotificationsEnabled() {
        return notificationsEnabled;
    }

    public void setNotificationsEnabled(boolean notificationsEnabled) {
        this.notificationsEnabled = notificationsEnabled;
    }
    
    public static NotificationMgr getInstance()
    {
        if ( _notificationMgr == null )
        {
            _notificationMgr = new NotificationMgr();
            
        }
        return( _notificationMgr );
    }
    
}
