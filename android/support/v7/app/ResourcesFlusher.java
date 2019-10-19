package android.support.v7.app;

import android.content.res.Resources;
import android.os.Build.VERSION;
import android.util.Log;
import android.util.LongSparseArray;
import java.lang.reflect.Field;
import java.util.Map;

class ResourcesFlusher
{
  private static final String PAGE_KEY = "ResourcesFlusher";
  private static Field sDrawableCacheField;
  private static boolean sDrawableCacheFieldFetched;
  private static Field sResourcesImplField;
  private static boolean sResourcesImplFieldFetched;
  private static Class sThemedResourceCacheClazz;
  private static boolean sThemedResourceCacheClazzFetched;
  private static Field sThemedResourceCache_mUnthemedEntriesField;
  private static boolean sThemedResourceCache_mUnthemedEntriesFieldFetched;
  
  ResourcesFlusher() {}
  
  static boolean flush(Resources paramResources)
  {
    if (Build.VERSION.SDK_INT >= 24) {
      return flushNougats(paramResources);
    }
    if (Build.VERSION.SDK_INT >= 23) {
      return flushMarshmallows(paramResources);
    }
    if (Build.VERSION.SDK_INT >= 21) {
      return flushLollipops(paramResources);
    }
    return false;
  }
  
  private static boolean flushLollipops(Resources paramResources)
  {
    if (!sDrawableCacheFieldFetched)
    {
      try
      {
        Field localField1 = Resources.class.getDeclaredField("mDrawableCache");
        sDrawableCacheField = localField1;
        localField1 = sDrawableCacheField;
        localField1.setAccessible(true);
      }
      catch (NoSuchFieldException localNoSuchFieldException)
      {
        Log.e("ResourcesFlusher", "Could not retrieve Resources#mDrawableCache field", localNoSuchFieldException);
      }
      sDrawableCacheFieldFetched = true;
    }
    if (sDrawableCacheField != null)
    {
      Field localField2 = sDrawableCacheField;
      try
      {
        paramResources = localField2.get(paramResources);
        paramResources = (Map)paramResources;
      }
      catch (IllegalAccessException paramResources)
      {
        Log.e("ResourcesFlusher", "Could not retrieve value from Resources#mDrawableCache", paramResources);
        paramResources = null;
      }
      if (paramResources != null)
      {
        paramResources.clear();
        return true;
      }
    }
    return false;
  }
  
  private static boolean flushMarshmallows(Resources paramResources)
  {
    if (!sDrawableCacheFieldFetched)
    {
      try
      {
        Field localField1 = Resources.class.getDeclaredField("mDrawableCache");
        sDrawableCacheField = localField1;
        localField1 = sDrawableCacheField;
        localField1.setAccessible(true);
      }
      catch (NoSuchFieldException localNoSuchFieldException)
      {
        Log.e("ResourcesFlusher", "Could not retrieve Resources#mDrawableCache field", localNoSuchFieldException);
      }
      sDrawableCacheFieldFetched = true;
    }
    if (sDrawableCacheField != null)
    {
      Field localField2 = sDrawableCacheField;
      try
      {
        paramResources = localField2.get(paramResources);
      }
      catch (IllegalAccessException paramResources)
      {
        Log.e("ResourcesFlusher", "Could not retrieve value from Resources#mDrawableCache", paramResources);
      }
    }
    else
    {
      paramResources = null;
    }
    if (paramResources == null) {
      return false;
    }
    return (paramResources != null) && (flushThemedResourcesCache(paramResources));
  }
  
  private static boolean flushNougats(Resources paramResources)
  {
    if (!sResourcesImplFieldFetched)
    {
      try
      {
        Field localField1 = Resources.class.getDeclaredField("mResourcesImpl");
        sResourcesImplField = localField1;
        localField1 = sResourcesImplField;
        localField1.setAccessible(true);
      }
      catch (NoSuchFieldException localNoSuchFieldException1)
      {
        Log.e("ResourcesFlusher", "Could not retrieve Resources#mResourcesImpl field", localNoSuchFieldException1);
      }
      sResourcesImplFieldFetched = true;
    }
    if (sResourcesImplField == null) {
      return false;
    }
    Field localField2 = sResourcesImplField;
    try
    {
      paramResources = localField2.get(paramResources);
    }
    catch (IllegalAccessException paramResources)
    {
      Log.e("ResourcesFlusher", "Could not retrieve value from Resources#mResourcesImpl", paramResources);
      paramResources = null;
    }
    if (paramResources == null) {
      return false;
    }
    if (!sDrawableCacheFieldFetched)
    {
      try
      {
        localField2 = paramResources.getClass().getDeclaredField("mDrawableCache");
        sDrawableCacheField = localField2;
        localField2 = sDrawableCacheField;
        localField2.setAccessible(true);
      }
      catch (NoSuchFieldException localNoSuchFieldException2)
      {
        Log.e("ResourcesFlusher", "Could not retrieve ResourcesImpl#mDrawableCache field", localNoSuchFieldException2);
      }
      sDrawableCacheFieldFetched = true;
    }
    if (sDrawableCacheField != null)
    {
      Field localField3 = sDrawableCacheField;
      try
      {
        paramResources = localField3.get(paramResources);
      }
      catch (IllegalAccessException paramResources)
      {
        Log.e("ResourcesFlusher", "Could not retrieve value from ResourcesImpl#mDrawableCache", paramResources);
      }
    }
    else
    {
      paramResources = null;
    }
    return (paramResources != null) && (flushThemedResourcesCache(paramResources));
  }
  
  private static boolean flushThemedResourcesCache(Object paramObject)
  {
    if (!sThemedResourceCacheClazzFetched)
    {
      try
      {
        Class localClass = Class.forName("android.content.res.ThemedResourceCache");
        sThemedResourceCacheClazz = localClass;
      }
      catch (ClassNotFoundException localClassNotFoundException)
      {
        Log.e("ResourcesFlusher", "Could not find ThemedResourceCache class", localClassNotFoundException);
      }
      sThemedResourceCacheClazzFetched = true;
    }
    if (sThemedResourceCacheClazz == null) {
      return false;
    }
    if (!sThemedResourceCache_mUnthemedEntriesFieldFetched)
    {
      Object localObject = sThemedResourceCacheClazz;
      try
      {
        localObject = ((Class)localObject).getDeclaredField("mUnthemedEntries");
        sThemedResourceCache_mUnthemedEntriesField = (Field)localObject;
        localObject = sThemedResourceCache_mUnthemedEntriesField;
        ((Field)localObject).setAccessible(true);
      }
      catch (NoSuchFieldException localNoSuchFieldException)
      {
        Log.e("ResourcesFlusher", "Could not retrieve ThemedResourceCache#mUnthemedEntries field", localNoSuchFieldException);
      }
      sThemedResourceCache_mUnthemedEntriesFieldFetched = true;
    }
    if (sThemedResourceCache_mUnthemedEntriesField == null) {
      return false;
    }
    Field localField = sThemedResourceCache_mUnthemedEntriesField;
    try
    {
      paramObject = localField.get(paramObject);
      paramObject = (LongSparseArray)paramObject;
    }
    catch (IllegalAccessException paramObject)
    {
      Log.e("ResourcesFlusher", "Could not retrieve value from ThemedResourceCache#mUnthemedEntries", paramObject);
      paramObject = null;
    }
    if (paramObject != null)
    {
      paramObject.clear();
      return true;
    }
    return false;
  }
}
