package android.support.v4.media.session;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.RequiresApi;

@RequiresApi(23)
class MediaSessionCompatApi23
{
  MediaSessionCompatApi23() {}
  
  public static Object createCallback(Callback paramCallback)
  {
    return new CallbackProxy(paramCallback);
  }
  
  public static abstract interface Callback
    extends MediaSessionCompatApi21.Callback
  {
    public abstract void onPlayFromUri(Uri paramUri, Bundle paramBundle);
  }
  
  static class CallbackProxy<T extends MediaSessionCompatApi23.Callback>
    extends MediaSessionCompatApi21.CallbackProxy<T>
  {
    public CallbackProxy(T paramT)
    {
      super();
    }
    
    public void onPlayFromUri(Uri paramUri, Bundle paramBundle)
    {
      ((MediaSessionCompatApi23.Callback)mCallback).onPlayFromUri(paramUri, paramBundle);
    }
  }
}
