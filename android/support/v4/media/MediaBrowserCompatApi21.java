package android.support.v4.media;

import android.content.ComponentName;
import android.content.Context;
import android.media.browse.MediaBrowser;
import android.media.browse.MediaBrowser.ConnectionCallback;
import android.media.browse.MediaBrowser.MediaItem;
import android.media.browse.MediaBrowser.SubscriptionCallback;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import java.util.List;

@RequiresApi(21)
class MediaBrowserCompatApi21
{
  static final String NULL_MEDIA_ITEM_ID = "android.support.v4.media.MediaBrowserCompat.NULL_MEDIA_ITEM";
  
  MediaBrowserCompatApi21() {}
  
  public static void connect(Object paramObject)
  {
    ((MediaBrowser)paramObject).connect();
  }
  
  public static Object createBrowser(Context paramContext, ComponentName paramComponentName, Object paramObject, Bundle paramBundle)
  {
    return new MediaBrowser(paramContext, paramComponentName, (MediaBrowser.ConnectionCallback)paramObject, paramBundle);
  }
  
  public static Object createConnectionCallback(ConnectionCallback paramConnectionCallback)
  {
    return new ConnectionCallbackProxy(paramConnectionCallback);
  }
  
  public static Object createSubscriptionCallback(SubscriptionCallback paramSubscriptionCallback)
  {
    return new SubscriptionCallbackProxy(paramSubscriptionCallback);
  }
  
  public static void disconnect(Object paramObject)
  {
    ((MediaBrowser)paramObject).disconnect();
  }
  
  public static Bundle getExtras(Object paramObject)
  {
    return ((MediaBrowser)paramObject).getExtras();
  }
  
  public static String getRoot(Object paramObject)
  {
    return ((MediaBrowser)paramObject).getRoot();
  }
  
  public static ComponentName getServiceComponent(Object paramObject)
  {
    return ((MediaBrowser)paramObject).getServiceComponent();
  }
  
  public static Object getSessionToken(Object paramObject)
  {
    return ((MediaBrowser)paramObject).getSessionToken();
  }
  
  public static boolean isConnected(Object paramObject)
  {
    return ((MediaBrowser)paramObject).isConnected();
  }
  
  public static void subscribe(Object paramObject1, String paramString, Object paramObject2)
  {
    ((MediaBrowser)paramObject1).subscribe(paramString, (MediaBrowser.SubscriptionCallback)paramObject2);
  }
  
  public static void unsubscribe(Object paramObject, String paramString)
  {
    ((MediaBrowser)paramObject).unsubscribe(paramString);
  }
  
  static abstract interface ConnectionCallback
  {
    public abstract void onConnected();
    
    public abstract void onConnectionFailed();
    
    public abstract void onConnectionSuspended();
  }
  
  static class ConnectionCallbackProxy<T extends MediaBrowserCompatApi21.ConnectionCallback>
    extends MediaBrowser.ConnectionCallback
  {
    protected final T mConnectionCallback;
    
    public ConnectionCallbackProxy(MediaBrowserCompatApi21.ConnectionCallback paramConnectionCallback)
    {
      mConnectionCallback = paramConnectionCallback;
    }
    
    public void onConnected()
    {
      mConnectionCallback.onConnected();
    }
    
    public void onConnectionFailed()
    {
      mConnectionCallback.onConnectionFailed();
    }
    
    public void onConnectionSuspended()
    {
      mConnectionCallback.onConnectionSuspended();
    }
  }
  
  static class MediaItem
  {
    MediaItem() {}
    
    public static Object getDescription(Object paramObject)
    {
      return ((MediaBrowser.MediaItem)paramObject).getDescription();
    }
    
    public static int getFlags(Object paramObject)
    {
      return ((MediaBrowser.MediaItem)paramObject).getFlags();
    }
  }
  
  static abstract interface SubscriptionCallback
  {
    public abstract void onChildrenLoaded(String paramString, List paramList);
    
    public abstract void onError(String paramString);
  }
  
  static class SubscriptionCallbackProxy<T extends MediaBrowserCompatApi21.SubscriptionCallback>
    extends MediaBrowser.SubscriptionCallback
  {
    protected final T mSubscriptionCallback;
    
    public SubscriptionCallbackProxy(MediaBrowserCompatApi21.SubscriptionCallback paramSubscriptionCallback)
    {
      mSubscriptionCallback = paramSubscriptionCallback;
    }
    
    public void onChildrenLoaded(String paramString, List paramList)
    {
      mSubscriptionCallback.onChildrenLoaded(paramString, paramList);
    }
    
    public void onError(String paramString)
    {
      mSubscriptionCallback.onError(paramString);
    }
  }
}
