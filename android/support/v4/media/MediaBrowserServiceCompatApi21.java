package android.support.v4.media;

import android.content.Context;
import android.content.Intent;
import android.media.browse.MediaBrowser.MediaItem;
import android.media.session.MediaSession.Token;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.service.media.MediaBrowserService;
import android.service.media.MediaBrowserService.BrowserRoot;
import android.service.media.MediaBrowserService.Result;
import android.support.annotation.RequiresApi;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RequiresApi(21)
class MediaBrowserServiceCompatApi21
{
  MediaBrowserServiceCompatApi21() {}
  
  public static Object createService(Context paramContext, ServiceCompatProxy paramServiceCompatProxy)
  {
    return new MediaBrowserServiceAdaptor(paramContext, paramServiceCompatProxy);
  }
  
  public static void notifyChildrenChanged(Object paramObject, String paramString)
  {
    ((MediaBrowserService)paramObject).notifyChildrenChanged(paramString);
  }
  
  public static IBinder onBind(Object paramObject, Intent paramIntent)
  {
    return ((MediaBrowserService)paramObject).onBind(paramIntent);
  }
  
  public static void onCreate(Object paramObject)
  {
    ((MediaBrowserService)paramObject).onCreate();
  }
  
  public static void setSessionToken(Object paramObject1, Object paramObject2)
  {
    ((MediaBrowserService)paramObject1).setSessionToken((MediaSession.Token)paramObject2);
  }
  
  static class BrowserRoot
  {
    final Bundle mExtras;
    final String mRootId;
    
    BrowserRoot(String paramString, Bundle paramBundle)
    {
      mRootId = paramString;
      mExtras = paramBundle;
    }
  }
  
  static class MediaBrowserServiceAdaptor
    extends MediaBrowserService
  {
    final MediaBrowserServiceCompatApi21.ServiceCompatProxy mServiceProxy;
    
    MediaBrowserServiceAdaptor(Context paramContext, MediaBrowserServiceCompatApi21.ServiceCompatProxy paramServiceCompatProxy)
    {
      attachBaseContext(paramContext);
      mServiceProxy = paramServiceCompatProxy;
    }
    
    public MediaBrowserService.BrowserRoot onGetRoot(String paramString, int paramInt, Bundle paramBundle)
    {
      MediaBrowserServiceCompatApi21.ServiceCompatProxy localServiceCompatProxy = mServiceProxy;
      if (paramBundle == null) {
        paramBundle = null;
      } else {
        paramBundle = new Bundle(paramBundle);
      }
      paramString = localServiceCompatProxy.onGetRoot(paramString, paramInt, paramBundle);
      if (paramString == null) {
        return null;
      }
      return new MediaBrowserService.BrowserRoot(mRootId, mExtras);
    }
    
    public void onLoadChildren(String paramString, MediaBrowserService.Result paramResult)
    {
      mServiceProxy.onLoadChildren(paramString, new MediaBrowserServiceCompatApi21.ResultWrapper(paramResult));
    }
  }
  
  static class ResultWrapper<T>
  {
    MediaBrowserService.Result mResultObj;
    
    ResultWrapper(MediaBrowserService.Result paramResult)
    {
      mResultObj = paramResult;
    }
    
    public void detach()
    {
      mResultObj.detach();
    }
    
    List parcelListToItemList(List paramList)
    {
      if (paramList == null) {
        return null;
      }
      ArrayList localArrayList = new ArrayList();
      paramList = paramList.iterator();
      while (paramList.hasNext())
      {
        Parcel localParcel = (Parcel)paramList.next();
        localParcel.setDataPosition(0);
        localArrayList.add(MediaBrowser.MediaItem.CREATOR.createFromParcel(localParcel));
        localParcel.recycle();
      }
      return localArrayList;
    }
    
    public void sendResult(Object paramObject)
    {
      if ((paramObject instanceof List))
      {
        mResultObj.sendResult(parcelListToItemList((List)paramObject));
        return;
      }
      if ((paramObject instanceof Parcel))
      {
        paramObject = (Parcel)paramObject;
        paramObject.setDataPosition(0);
        mResultObj.sendResult(MediaBrowser.MediaItem.CREATOR.createFromParcel(paramObject));
        paramObject.recycle();
        return;
      }
      mResultObj.sendResult(null);
    }
  }
  
  public static abstract interface ServiceCompatProxy
  {
    public abstract MediaBrowserServiceCompatApi21.BrowserRoot onGetRoot(String paramString, int paramInt, Bundle paramBundle);
    
    public abstract void onLoadChildren(String paramString, MediaBrowserServiceCompatApi21.ResultWrapper paramResultWrapper);
  }
}
