package android.support.v4.media;

import android.media.browse.MediaBrowser.MediaItem;
import android.support.annotation.RequiresApi;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

@RequiresApi(21)
class ParceledListSliceAdapterApi21
{
  private static Constructor sConstructor;
  
  static
  {
    try
    {
      sConstructor = Class.forName("android.content.pm.ParceledListSlice").getConstructor(new Class[] { List.class });
      return;
    }
    catch (ClassNotFoundException|NoSuchMethodException localClassNotFoundException)
    {
      localClassNotFoundException.printStackTrace();
    }
  }
  
  ParceledListSliceAdapterApi21() {}
  
  static Object newInstance(List<MediaBrowser.MediaItem> paramList)
  {
    try
    {
      paramList = sConstructor.newInstance(new Object[] { paramList });
      return paramList;
    }
    catch (InstantiationException|IllegalAccessException|InvocationTargetException paramList)
    {
      paramList.printStackTrace();
    }
    return null;
  }
}
