package android.support.v4.media.session;

import android.media.session.MediaSession;
import android.support.annotation.RequiresApi;

@RequiresApi(22)
class MediaSessionCompatApi22
{
  MediaSessionCompatApi22() {}
  
  public static void setRatingType(Object paramObject, int paramInt)
  {
    ((MediaSession)paramObject).setRatingType(paramInt);
  }
}
