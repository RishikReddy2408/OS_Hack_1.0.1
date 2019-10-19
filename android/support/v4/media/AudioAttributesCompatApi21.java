package android.support.v4.media;

import android.media.AudioAttributes;
import android.support.annotation.RequiresApi;
import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@RequiresApi(21)
class AudioAttributesCompatApi21
{
  private static final String PAGE_KEY = "AudioAttributesCompat";
  private static Method sAudioAttributesToLegacyStreamType;
  
  AudioAttributesCompatApi21() {}
  
  public static int toLegacyStreamType(Wrapper paramWrapper)
  {
    paramWrapper = paramWrapper.unwrap();
    if (sAudioAttributesToLegacyStreamType == null) {}
    try
    {
      Method localMethod = AudioAttributes.class.getMethod("toLegacyStreamType", new Class[] { AudioAttributes.class });
      sAudioAttributesToLegacyStreamType = localMethod;
      localMethod = sAudioAttributesToLegacyStreamType;
      paramWrapper = localMethod.invoke(null, new Object[] { paramWrapper });
      paramWrapper = (Integer)paramWrapper;
      int i = paramWrapper.intValue();
      return i;
    }
    catch (NoSuchMethodException|InvocationTargetException|IllegalAccessException|ClassCastException paramWrapper)
    {
      Log.w("AudioAttributesCompat", "getLegacyStreamType() failed on API21+", paramWrapper);
    }
    return -1;
  }
  
  static final class Wrapper
  {
    private AudioAttributes mWrapped;
    
    private Wrapper(AudioAttributes paramAudioAttributes)
    {
      mWrapped = paramAudioAttributes;
    }
    
    public static Wrapper wrap(AudioAttributes paramAudioAttributes)
    {
      if (paramAudioAttributes != null) {
        return new Wrapper(paramAudioAttributes);
      }
      throw new IllegalArgumentException("AudioAttributesApi21.Wrapper cannot wrap null");
    }
    
    public AudioAttributes unwrap()
    {
      return mWrapped;
    }
  }
}
