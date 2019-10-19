package android.support.v4.media;

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
      Object localObject = Class.forName("android.content.pm.ParceledListSlice");
      localObject = ((Class)localObject).getConstructor(new Class[] { List.class });
      sConstructor = (Constructor)localObject;
      return;
    }
    catch (ClassNotFoundException|NoSuchMethodException localClassNotFoundException)
    {
      ((ReflectiveOperationException)localClassNotFoundException).printStackTrace();
    }
  }
  
  ParceledListSliceAdapterApi21() {}
  
  static Object newInstance(List paramList)
  {
    Constructor localConstructor = sConstructor;
    try
    {
      paramList = localConstructor.newInstance(new Object[] { paramList });
      return paramList;
    }
    catch (InstantiationException|IllegalAccessException|InvocationTargetException paramList)
    {
      ((ReflectiveOperationException)paramList).printStackTrace();
    }
    return null;
  }
}
