package android.support.v4.media;

import android.content.Context;
import android.media.browse.MediaBrowser.MediaItem;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.service.media.MediaBrowserService;
import android.service.media.MediaBrowserService.Result;
import android.support.annotation.RequiresApi;
import android.util.Log;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RequiresApi(24)
class MediaBrowserServiceCompatApi24
{
  private static final String PAGE_KEY = "MBSCompatApi24";
  private static Field sResultFlags;
  
  static
  {
    try
    {
      Field localField = MediaBrowserService.Result.class.getDeclaredField("mFlags");
      sResultFlags = localField;
      localField = sResultFlags;
      localField.setAccessible(true);
      return;
    }
    catch (NoSuchFieldException localNoSuchFieldException)
    {
      Log.w("MBSCompatApi24", localNoSuchFieldException);
    }
  }
  
  MediaBrowserServiceCompatApi24() {}
  
  public static Object createService(Context paramContext, ServiceCompatProxy paramServiceCompatProxy)
  {
    return new MediaBrowserServiceAdaptor(paramContext, paramServiceCompatProxy);
  }
  
  public static Bundle getBrowserRootHints(Object paramObject)
  {
    return ((MediaBrowserService)paramObject).getBrowserRootHints();
  }
  
  public static void notifyChildrenChanged(Object paramObject, String paramString, Bundle paramBundle)
  {
    ((MediaBrowserService)paramObject).notifyChildrenChanged(paramString, paramBundle);
  }
  
  static class MediaBrowserServiceAdaptor
    extends MediaBrowserServiceCompatApi23.MediaBrowserServiceAdaptor
  {
    MediaBrowserServiceAdaptor(Context paramContext, MediaBrowserServiceCompatApi24.ServiceCompatProxy paramServiceCompatProxy)
    {
      super(paramServiceCompatProxy);
    }
    
    public void onLoadChildren(String paramString, MediaBrowserService.Result paramResult, Bundle paramBundle)
    {
      ((MediaBrowserServiceCompatApi24.ServiceCompatProxy)mServiceProxy).onLoadChildren(paramString, new MediaBrowserServiceCompatApi24.ResultWrapper(paramResult), paramBundle);
    }
  }
  
  static class ResultWrapper
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
    
    public void sendResult(List paramList, int paramInt)
    {
      try
      {
        Field localField = MediaBrowserServiceCompatApi24.sResultFlags;
        MediaBrowserService.Result localResult = mResultObj;
        localField.setInt(localResult, paramInt);
      }
      catch (IllegalAccessException localIllegalAccessException)
      {
        Log.w("MBSCompatApi24", localIllegalAccessException);
      }
      mResultObj.sendResult(parcelListToItemList(paramList));
    }
  }
  
  public static abstract interface ServiceCompatProxy
    extends MediaBrowserServiceCompatApi23.ServiceCompatProxy
  {
    public abstract void onLoadChildren(String paramString, MediaBrowserServiceCompatApi24.ResultWrapper paramResultWrapper, Bundle paramBundle);
  }
}
