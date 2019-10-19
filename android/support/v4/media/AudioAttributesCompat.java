package android.support.v4.media;

import android.media.AudioAttributes;
import android.media.AudioAttributes.Builder;
import android.os.Build.VERSION;
import android.support.annotation.RestrictTo;
import android.util.SparseIntArray;
import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Arrays;

public class AudioAttributesCompat
{
  public static final int CONTENT_TYPE_MOVIE = 3;
  public static final int CONTENT_TYPE_MUSIC = 2;
  public static final int CONTENT_TYPE_SONIFICATION = 4;
  public static final int CONTENT_TYPE_SPEECH = 1;
  public static final int CONTENT_TYPE_UNKNOWN = 0;
  private static final int FLAG_ALL = 1023;
  private static final int FLAG_ALL_PUBLIC = 273;
  public static final int FLAG_AUDIBILITY_ENFORCED = 1;
  private static final int FLAG_BEACON = 8;
  private static final int FLAG_BYPASS_INTERRUPTION_POLICY = 64;
  private static final int FLAG_BYPASS_MUTE = 128;
  private static final int FLAG_DEEP_BUFFER = 512;
  public static final int FLAG_HW_AV_SYNC = 16;
  private static final int FLAG_HW_HOTWORD = 32;
  private static final int FLAG_LOW_LATENCY = 256;
  private static final int FLAG_SCO = 4;
  private static final int FLAG_SECURE = 2;
  private static final String PAGE_KEY = "AudioAttributesCompat";
  private static final int[] SDK_USAGES = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 16 };
  private static final int SUPPRESSIBLE_CALL = 2;
  private static final int SUPPRESSIBLE_NOTIFICATION = 1;
  private static final SparseIntArray SUPPRESSIBLE_USAGES = new SparseIntArray();
  public static final int USAGE_ALARM = 4;
  public static final int USAGE_ASSISTANCE_ACCESSIBILITY = 11;
  public static final int USAGE_ASSISTANCE_NAVIGATION_GUIDANCE = 12;
  public static final int USAGE_ASSISTANCE_SONIFICATION = 13;
  public static final int USAGE_ASSISTANT = 16;
  public static final int USAGE_GAME = 14;
  public static final int USAGE_MEDIA = 1;
  public static final int USAGE_NOTIFICATION = 5;
  public static final int USAGE_NOTIFICATION_COMMUNICATION_DELAYED = 9;
  public static final int USAGE_NOTIFICATION_COMMUNICATION_INSTANT = 8;
  public static final int USAGE_NOTIFICATION_COMMUNICATION_REQUEST = 7;
  public static final int USAGE_NOTIFICATION_EVENT = 10;
  public static final int USAGE_NOTIFICATION_RINGTONE = 6;
  public static final int USAGE_UNKNOWN = 0;
  private static final int USAGE_VIRTUAL_SOURCE = 15;
  public static final int USAGE_VOICE_COMMUNICATION = 2;
  public static final int USAGE_VOICE_COMMUNICATION_SIGNALLING = 3;
  private static boolean sForceLegacyBehavior;
  private AudioAttributesCompatApi21.Wrapper mAudioAttributesWrapper;
  int mContentType = 0;
  int mFlags = 0;
  Integer mLegacyStream;
  int mUsage = 0;
  
  static
  {
    SUPPRESSIBLE_USAGES.put(5, 1);
    SUPPRESSIBLE_USAGES.put(6, 2);
    SUPPRESSIBLE_USAGES.put(7, 2);
    SUPPRESSIBLE_USAGES.put(8, 1);
    SUPPRESSIBLE_USAGES.put(9, 1);
    SUPPRESSIBLE_USAGES.put(10, 1);
  }
  
  private AudioAttributesCompat() {}
  
  public static void setForceLegacyBehavior(boolean paramBoolean)
  {
    sForceLegacyBehavior = paramBoolean;
  }
  
  static int toVolumeStreamType(boolean paramBoolean, int paramInt1, int paramInt2)
  {
    if ((paramInt1 & 0x1) == 1)
    {
      if (paramBoolean) {
        return 1;
      }
      return 7;
    }
    if ((paramInt1 & 0x4) == 4)
    {
      if (paramBoolean) {
        return 0;
      }
      return 6;
    }
    switch (paramInt2)
    {
    default: 
      break;
    case 15: 
      if (paramBoolean) {
        break label220;
      }
      return 3;
    case 13: 
      return 1;
    case 11: 
      return 10;
    case 6: 
      return 2;
    case 5: 
    case 7: 
    case 8: 
    case 9: 
    case 10: 
      return 5;
    case 4: 
      return 4;
    case 3: 
      if (paramBoolean) {
        return 0;
      }
      return 8;
    case 2: 
      return 0;
    case 1: 
    case 12: 
    case 14: 
    case 16: 
      return 3;
    }
    if (paramBoolean)
    {
      return Integer.MIN_VALUE;
      label220:
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Unknown usage value ");
      localStringBuilder.append(paramInt2);
      localStringBuilder.append(" in audio attributes");
      throw new IllegalArgumentException(localStringBuilder.toString());
    }
    return 3;
  }
  
  static int toVolumeStreamType(boolean paramBoolean, AudioAttributesCompat paramAudioAttributesCompat)
  {
    return toVolumeStreamType(paramBoolean, paramAudioAttributesCompat.getFlags(), paramAudioAttributesCompat.getUsage());
  }
  
  private static int usageForStreamType(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      break;
    case 9: 
      return 0;
    case 10: 
      return 11;
    case 8: 
      return 3;
    case 6: 
      return 2;
    case 5: 
      return 5;
    case 4: 
      return 4;
    case 3: 
      return 1;
    case 2: 
      return 6;
    case 1: 
    case 7: 
      return 13;
    }
    return 2;
  }
  
  static String usageToString(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      break;
    case 15: 
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("unknown usage ");
      localStringBuilder.append(paramInt);
      return new String(localStringBuilder.toString());
    case 16: 
      return new String("USAGE_ASSISTANT");
    case 14: 
      return new String("USAGE_GAME");
    case 13: 
      return new String("USAGE_ASSISTANCE_SONIFICATION");
    case 12: 
      return new String("USAGE_ASSISTANCE_NAVIGATION_GUIDANCE");
    case 11: 
      return new String("USAGE_ASSISTANCE_ACCESSIBILITY");
    case 10: 
      return new String("USAGE_NOTIFICATION_EVENT");
    case 9: 
      return new String("USAGE_NOTIFICATION_COMMUNICATION_DELAYED");
    case 8: 
      return new String("USAGE_NOTIFICATION_COMMUNICATION_INSTANT");
    case 7: 
      return new String("USAGE_NOTIFICATION_COMMUNICATION_REQUEST");
    case 6: 
      return new String("USAGE_NOTIFICATION_RINGTONE");
    case 5: 
      return new String("USAGE_NOTIFICATION");
    case 4: 
      return new String("USAGE_ALARM");
    case 3: 
      return new String("USAGE_VOICE_COMMUNICATION_SIGNALLING");
    case 2: 
      return new String("USAGE_VOICE_COMMUNICATION");
    case 1: 
      return new String("USAGE_MEDIA");
    }
    return new String("USAGE_UNKNOWN");
  }
  
  public static AudioAttributesCompat wrap(Object paramObject)
  {
    if ((Build.VERSION.SDK_INT >= 21) && (!sForceLegacyBehavior))
    {
      AudioAttributesCompat localAudioAttributesCompat = new AudioAttributesCompat();
      mAudioAttributesWrapper = AudioAttributesCompatApi21.Wrapper.wrap((AudioAttributes)paramObject);
      return localAudioAttributesCompat;
    }
    return null;
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {
      return true;
    }
    if (paramObject != null)
    {
      if (getClass() != paramObject.getClass()) {
        return false;
      }
      paramObject = (AudioAttributesCompat)paramObject;
      if ((Build.VERSION.SDK_INT >= 21) && (!sForceLegacyBehavior) && (mAudioAttributesWrapper != null)) {
        return mAudioAttributesWrapper.unwrap().equals(paramObject.unwrap());
      }
      if ((mContentType == paramObject.getContentType()) && (mFlags == paramObject.getFlags()) && (mUsage == paramObject.getUsage())) {
        if (mLegacyStream != null)
        {
          if (mLegacyStream.equals(mLegacyStream)) {
            return true;
          }
        }
        else if (mLegacyStream == null) {
          return true;
        }
      }
      return false;
    }
    return false;
  }
  
  public int getContentType()
  {
    if ((Build.VERSION.SDK_INT >= 21) && (!sForceLegacyBehavior) && (mAudioAttributesWrapper != null)) {
      return mAudioAttributesWrapper.unwrap().getContentType();
    }
    return mContentType;
  }
  
  public int getFlags()
  {
    if ((Build.VERSION.SDK_INT >= 21) && (!sForceLegacyBehavior) && (mAudioAttributesWrapper != null)) {
      return mAudioAttributesWrapper.unwrap().getFlags();
    }
    int j = mFlags;
    int k = getLegacyStreamType();
    int i;
    if (k == 6)
    {
      i = j | 0x4;
    }
    else
    {
      i = j;
      if (k == 7) {
        i = j | 0x1;
      }
    }
    return i & 0x111;
  }
  
  public int getLegacyStreamType()
  {
    if (mLegacyStream != null) {
      return mLegacyStream.intValue();
    }
    if ((Build.VERSION.SDK_INT >= 21) && (!sForceLegacyBehavior)) {
      return AudioAttributesCompatApi21.toLegacyStreamType(mAudioAttributesWrapper);
    }
    return toVolumeStreamType(false, mFlags, mUsage);
  }
  
  public int getUsage()
  {
    if ((Build.VERSION.SDK_INT >= 21) && (!sForceLegacyBehavior) && (mAudioAttributesWrapper != null)) {
      return mAudioAttributesWrapper.unwrap().getUsage();
    }
    return mUsage;
  }
  
  public int getVolumeControlStream()
  {
    if ((Build.VERSION.SDK_INT >= 26) && (!sForceLegacyBehavior) && (unwrap() != null)) {
      return ((AudioAttributes)unwrap()).getVolumeControlStream();
    }
    return toVolumeStreamType(true, this);
  }
  
  public int hashCode()
  {
    if ((Build.VERSION.SDK_INT >= 21) && (!sForceLegacyBehavior) && (mAudioAttributesWrapper != null)) {
      return mAudioAttributesWrapper.unwrap().hashCode();
    }
    return Arrays.hashCode(new Object[] { Integer.valueOf(mContentType), Integer.valueOf(mFlags), Integer.valueOf(mUsage), mLegacyStream });
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder("AudioAttributesCompat:");
    if (unwrap() != null)
    {
      localStringBuilder.append(" audioattributes=");
      localStringBuilder.append(unwrap());
    }
    else
    {
      if (mLegacyStream != null)
      {
        localStringBuilder.append(" stream=");
        localStringBuilder.append(mLegacyStream);
        localStringBuilder.append(" derived");
      }
      localStringBuilder.append(" usage=");
      localStringBuilder.append(usageToString());
      localStringBuilder.append(" content=");
      localStringBuilder.append(mContentType);
      localStringBuilder.append(" flags=0x");
      localStringBuilder.append(Integer.toHexString(mFlags).toUpperCase());
    }
    return localStringBuilder.toString();
  }
  
  public Object unwrap()
  {
    if (mAudioAttributesWrapper != null) {
      return mAudioAttributesWrapper.unwrap();
    }
    return null;
  }
  
  String usageToString()
  {
    return usageToString(mUsage);
  }
  
  @Retention(RetentionPolicy.SOURCE)
  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  public static @interface AttributeContentType {}
  
  @Retention(RetentionPolicy.SOURCE)
  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  public static @interface AttributeUsage {}
  
  private static abstract class AudioManagerHidden
  {
    public static final int STREAM_ACCESSIBILITY = 10;
    public static final int STREAM_BLUETOOTH_SCO = 6;
    public static final int STREAM_SYSTEM_ENFORCED = 7;
    public static final int STREAM_TTS = 9;
    
    private AudioManagerHidden() {}
  }
  
  public static class Builder
  {
    private Object mAAObject;
    private int mContentType = 0;
    private int mFlags = 0;
    private Integer mLegacyStream;
    private int mUsage = 0;
    
    public Builder() {}
    
    public Builder(AudioAttributesCompat paramAudioAttributesCompat)
    {
      mUsage = mUsage;
      mContentType = mContentType;
      mFlags = mFlags;
      mLegacyStream = mLegacyStream;
      mAAObject = paramAudioAttributesCompat.unwrap();
    }
    
    public AudioAttributesCompat build()
    {
      if ((!AudioAttributesCompat.sForceLegacyBehavior) && (Build.VERSION.SDK_INT >= 21))
      {
        if (mAAObject != null) {
          return AudioAttributesCompat.wrap(mAAObject);
        }
        localObject = new AudioAttributes.Builder().setContentType(mContentType).setFlags(mFlags).setUsage(mUsage);
        if (mLegacyStream != null) {
          ((AudioAttributes.Builder)localObject).setLegacyStreamType(mLegacyStream.intValue());
        }
        return AudioAttributesCompat.wrap(((AudioAttributes.Builder)localObject).build());
      }
      Object localObject = new AudioAttributesCompat(null);
      mContentType = mContentType;
      mFlags = mFlags;
      mUsage = mUsage;
      mLegacyStream = mLegacyStream;
      AudioAttributesCompat.access$202((AudioAttributesCompat)localObject, null);
      return localObject;
    }
    
    public Builder setContentType(int paramInt)
    {
      switch (paramInt)
      {
      default: 
        mUsage = 0;
        return this;
      }
      mContentType = paramInt;
      return this;
    }
    
    public Builder setFlags(int paramInt)
    {
      mFlags = (paramInt & 0x3FF | mFlags);
      return this;
    }
    
    public Builder setLegacyStreamType(int paramInt)
    {
      if (paramInt != 10)
      {
        mLegacyStream = Integer.valueOf(paramInt);
        mUsage = AudioAttributesCompat.usageForStreamType(paramInt);
        return this;
      }
      throw new IllegalArgumentException("STREAM_ACCESSIBILITY is not a legacy stream type that was used for audio playback");
    }
    
    public Builder setUsage(int paramInt)
    {
      switch (paramInt)
      {
      default: 
        mUsage = 0;
        return this;
      case 16: 
        if ((!AudioAttributesCompat.sForceLegacyBehavior) && (Build.VERSION.SDK_INT > 25))
        {
          mUsage = paramInt;
          return this;
        }
        mUsage = 12;
        return this;
      }
      mUsage = paramInt;
      return this;
    }
  }
}
