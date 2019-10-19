package android.support.v4.media;

import android.media.browse.MediaBrowser;
import android.media.browse.MediaBrowser.SubscriptionCallback;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import java.util.List;

@RequiresApi(24)
class MediaBrowserCompatApi24
{
  MediaBrowserCompatApi24() {}
  
  public static Object createSubscriptionCallback(SubscriptionCallback paramSubscriptionCallback)
  {
    return new SubscriptionCallbackProxy(paramSubscriptionCallback);
  }
  
  public static void subscribe(Object paramObject1, String paramString, Bundle paramBundle, Object paramObject2)
  {
    ((MediaBrowser)paramObject1).subscribe(paramString, paramBundle, (MediaBrowser.SubscriptionCallback)paramObject2);
  }
  
  public static void unsubscribe(Object paramObject1, String paramString, Object paramObject2)
  {
    ((MediaBrowser)paramObject1).unsubscribe(paramString, (MediaBrowser.SubscriptionCallback)paramObject2);
  }
  
  static abstract interface SubscriptionCallback
    extends MediaBrowserCompatApi21.SubscriptionCallback
  {
    public abstract void onChildrenLoaded(String paramString, List paramList, Bundle paramBundle);
    
    public abstract void onError(String paramString, Bundle paramBundle);
  }
  
  static class SubscriptionCallbackProxy<T extends MediaBrowserCompatApi24.SubscriptionCallback>
    extends MediaBrowserCompatApi21.SubscriptionCallbackProxy<T>
  {
    public SubscriptionCallbackProxy(MediaBrowserCompatApi24.SubscriptionCallback paramSubscriptionCallback)
    {
      super();
    }
    
    public void onChildrenLoaded(String paramString, List paramList, Bundle paramBundle)
    {
      ((MediaBrowserCompatApi24.SubscriptionCallback)mSubscriptionCallback).onChildrenLoaded(paramString, paramList, paramBundle);
    }
    
    public void onError(String paramString, Bundle paramBundle)
    {
      ((MediaBrowserCompatApi24.SubscriptionCallback)mSubscriptionCallback).onError(paramString, paramBundle);
    }
  }
}
