package android.support.v4.media.session;

import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.os.SystemClock;
import android.support.annotation.RestrictTo;
import android.text.TextUtils;
import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class PlaybackStateCompat
  implements Parcelable
{
  public static final long ACTION_FAST_FORWARD = 64L;
  public static final long ACTION_PAUSE = 2L;
  public static final long ACTION_PLAY = 4L;
  public static final long ACTION_PLAY_FROM_MEDIA_ID = 1024L;
  public static final long ACTION_PLAY_FROM_SEARCH = 2048L;
  public static final long ACTION_PLAY_FROM_URI = 8192L;
  public static final long ACTION_PLAY_PAUSE = 512L;
  public static final long ACTION_PREPARE = 16384L;
  public static final long ACTION_PREPARE_FROM_MEDIA_ID = 32768L;
  public static final long ACTION_PREPARE_FROM_SEARCH = 65536L;
  public static final long ACTION_PREPARE_FROM_URI = 131072L;
  public static final long ACTION_REWIND = 8L;
  public static final long ACTION_SEEK_TO = 256L;
  public static final long ACTION_SET_CAPTIONING_ENABLED = 1048576L;
  public static final long ACTION_SET_RATING = 128L;
  public static final long ACTION_SET_REPEAT_MODE = 262144L;
  public static final long ACTION_SET_SHUFFLE_MODE_ENABLED = 524288L;
  public static final long ACTION_SKIP_TO_NEXT = 32L;
  public static final long ACTION_SKIP_TO_PREVIOUS = 16L;
  public static final long ACTION_SKIP_TO_QUEUE_ITEM = 4096L;
  public static final long ACTION_STOP = 1L;
  public static final Parcelable.Creator<PlaybackStateCompat> CREATOR = new Parcelable.Creator()
  {
    public PlaybackStateCompat createFromParcel(Parcel paramAnonymousParcel)
    {
      return new PlaybackStateCompat(paramAnonymousParcel);
    }
    
    public PlaybackStateCompat[] newArray(int paramAnonymousInt)
    {
      return new PlaybackStateCompat[paramAnonymousInt];
    }
  };
  public static final int ERROR_CODE_ACTION_ABORTED = 10;
  public static final int ERROR_CODE_APP_ERROR = 1;
  public static final int ERROR_CODE_AUTHENTICATION_EXPIRED = 3;
  public static final int ERROR_CODE_CONCURRENT_STREAM_LIMIT = 5;
  public static final int ERROR_CODE_CONTENT_ALREADY_PLAYING = 8;
  public static final int ERROR_CODE_END_OF_QUEUE = 11;
  public static final int ERROR_CODE_NOT_AVAILABLE_IN_REGION = 7;
  public static final int ERROR_CODE_NOT_SUPPORTED = 2;
  public static final int ERROR_CODE_PARENTAL_CONTROL_RESTRICTED = 6;
  public static final int ERROR_CODE_PREMIUM_ACCOUNT_REQUIRED = 4;
  public static final int ERROR_CODE_SKIP_LIMIT_REACHED = 9;
  public static final int ERROR_CODE_UNKNOWN_ERROR = 0;
  private static final int KEYCODE_MEDIA_PAUSE = 127;
  private static final int KEYCODE_MEDIA_PLAY = 126;
  public static final long PLAYBACK_POSITION_UNKNOWN = -1L;
  public static final int REPEAT_MODE_ALL = 2;
  public static final int REPEAT_MODE_GROUP = 3;
  public static final int REPEAT_MODE_NONE = 0;
  public static final int REPEAT_MODE_ONE = 1;
  public static final int SHUFFLE_MODE_ALL = 1;
  public static final int SHUFFLE_MODE_GROUP = 2;
  public static final int SHUFFLE_MODE_NONE = 0;
  public static final int STATE_BUFFERING = 6;
  public static final int STATE_CONNECTING = 8;
  public static final int STATE_ERROR = 7;
  public static final int STATE_FAST_FORWARDING = 4;
  public static final int STATE_NONE = 0;
  public static final int STATE_PAUSED = 2;
  public static final int STATE_PLAYING = 3;
  public static final int STATE_REWINDING = 5;
  public static final int STATE_SKIPPING_TO_NEXT = 10;
  public static final int STATE_SKIPPING_TO_PREVIOUS = 9;
  public static final int STATE_SKIPPING_TO_QUEUE_ITEM = 11;
  public static final int STATE_STOPPED = 1;
  final long mActions;
  final long mActiveItemId;
  final long mBufferedPosition;
  List<CustomAction> mCustomActions;
  final int mErrorCode;
  final CharSequence mErrorMessage;
  final Bundle mExtras;
  final long mPosition;
  final float mSpeed;
  final int mState;
  private Object mStateObj;
  final long mUpdateTime;
  
  PlaybackStateCompat(int paramInt1, long paramLong1, long paramLong2, float paramFloat, long paramLong3, int paramInt2, CharSequence paramCharSequence, long paramLong4, List paramList, long paramLong5, Bundle paramBundle)
  {
    mState = paramInt1;
    mPosition = paramLong1;
    mBufferedPosition = paramLong2;
    mSpeed = paramFloat;
    mActions = paramLong3;
    mErrorCode = paramInt2;
    mErrorMessage = paramCharSequence;
    mUpdateTime = paramLong4;
    mCustomActions = new ArrayList(paramList);
    mActiveItemId = paramLong5;
    mExtras = paramBundle;
  }
  
  PlaybackStateCompat(Parcel paramParcel)
  {
    mState = paramParcel.readInt();
    mPosition = paramParcel.readLong();
    mSpeed = paramParcel.readFloat();
    mUpdateTime = paramParcel.readLong();
    mBufferedPosition = paramParcel.readLong();
    mActions = paramParcel.readLong();
    mErrorMessage = ((CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(paramParcel));
    mCustomActions = paramParcel.createTypedArrayList(CustomAction.CREATOR);
    mActiveItemId = paramParcel.readLong();
    mExtras = paramParcel.readBundle();
    mErrorCode = paramParcel.readInt();
  }
  
  public static PlaybackStateCompat fromPlaybackState(Object paramObject)
  {
    Bundle localBundle = null;
    if ((paramObject != null) && (Build.VERSION.SDK_INT >= 21))
    {
      Object localObject2 = PlaybackStateCompatApi21.getCustomActions(paramObject);
      if (localObject2 != null)
      {
        localObject1 = new ArrayList(((List)localObject2).size());
        localObject2 = ((List)localObject2).iterator();
        while (((Iterator)localObject2).hasNext()) {
          ((List)localObject1).add(CustomAction.fromCustomAction(((Iterator)localObject2).next()));
        }
      }
      else
      {
        localObject1 = null;
      }
      if (Build.VERSION.SDK_INT >= 22) {
        localBundle = PlaybackStateCompatApi22.getExtras(paramObject);
      }
      Object localObject1 = new PlaybackStateCompat(PlaybackStateCompatApi21.getState(paramObject), PlaybackStateCompatApi21.getPosition(paramObject), PlaybackStateCompatApi21.getBufferedPosition(paramObject), PlaybackStateCompatApi21.getPlaybackSpeed(paramObject), PlaybackStateCompatApi21.getActions(paramObject), 0, PlaybackStateCompatApi21.getErrorMessage(paramObject), PlaybackStateCompatApi21.getLastPositionUpdateTime(paramObject), (List)localObject1, PlaybackStateCompatApi21.getActiveQueueItemId(paramObject), localBundle);
      mStateObj = paramObject;
      return localObject1;
    }
    return null;
  }
  
  public static int toKeyCode(long paramLong)
  {
    if (paramLong == 4L) {
      return 126;
    }
    if (paramLong == 2L) {
      return 127;
    }
    if (paramLong == 32L) {
      return 87;
    }
    if (paramLong == 16L) {
      return 88;
    }
    if (paramLong == 1L) {
      return 86;
    }
    if (paramLong == 64L) {
      return 90;
    }
    if (paramLong == 8L) {
      return 89;
    }
    if (paramLong == 512L) {
      return 85;
    }
    return 0;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public long getActions()
  {
    return mActions;
  }
  
  public long getActiveQueueItemId()
  {
    return mActiveItemId;
  }
  
  public long getBufferedPosition()
  {
    return mBufferedPosition;
  }
  
  public List getCustomActions()
  {
    return mCustomActions;
  }
  
  public int getErrorCode()
  {
    return mErrorCode;
  }
  
  public CharSequence getErrorMessage()
  {
    return mErrorMessage;
  }
  
  public Bundle getExtras()
  {
    return mExtras;
  }
  
  public long getLastPositionUpdateTime()
  {
    return mUpdateTime;
  }
  
  public float getPlaybackSpeed()
  {
    return mSpeed;
  }
  
  public Object getPlaybackState()
  {
    if ((mStateObj == null) && (Build.VERSION.SDK_INT >= 21))
    {
      Object localObject = null;
      if (mCustomActions != null)
      {
        ArrayList localArrayList = new ArrayList(mCustomActions.size());
        Iterator localIterator = mCustomActions.iterator();
        for (;;)
        {
          localObject = localArrayList;
          if (!localIterator.hasNext()) {
            break;
          }
          localArrayList.add(((CustomAction)localIterator.next()).getCustomAction());
        }
      }
      if (Build.VERSION.SDK_INT >= 22) {
        mStateObj = PlaybackStateCompatApi22.newInstance(mState, mPosition, mBufferedPosition, mSpeed, mActions, mErrorMessage, mUpdateTime, localObject, mActiveItemId, mExtras);
      } else {
        mStateObj = PlaybackStateCompatApi21.newInstance(mState, mPosition, mBufferedPosition, mSpeed, mActions, mErrorMessage, mUpdateTime, localObject, mActiveItemId);
      }
    }
    return mStateObj;
  }
  
  public long getPosition()
  {
    return mPosition;
  }
  
  public int getState()
  {
    return mState;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder("PlaybackState {");
    localStringBuilder.append("state=");
    localStringBuilder.append(mState);
    localStringBuilder.append(", position=");
    localStringBuilder.append(mPosition);
    localStringBuilder.append(", buffered position=");
    localStringBuilder.append(mBufferedPosition);
    localStringBuilder.append(", speed=");
    localStringBuilder.append(mSpeed);
    localStringBuilder.append(", updated=");
    localStringBuilder.append(mUpdateTime);
    localStringBuilder.append(", actions=");
    localStringBuilder.append(mActions);
    localStringBuilder.append(", error code=");
    localStringBuilder.append(mErrorCode);
    localStringBuilder.append(", error message=");
    localStringBuilder.append(mErrorMessage);
    localStringBuilder.append(", custom actions=");
    localStringBuilder.append(mCustomActions);
    localStringBuilder.append(", active item id=");
    localStringBuilder.append(mActiveItemId);
    localStringBuilder.append("}");
    return localStringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeInt(mState);
    paramParcel.writeLong(mPosition);
    paramParcel.writeFloat(mSpeed);
    paramParcel.writeLong(mUpdateTime);
    paramParcel.writeLong(mBufferedPosition);
    paramParcel.writeLong(mActions);
    TextUtils.writeToParcel(mErrorMessage, paramParcel, paramInt);
    paramParcel.writeTypedList(mCustomActions);
    paramParcel.writeLong(mActiveItemId);
    paramParcel.writeBundle(mExtras);
    paramParcel.writeInt(mErrorCode);
  }
  
  @Retention(RetentionPolicy.SOURCE)
  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  public static @interface Actions {}
  
  public static final class Builder
  {
    private long mActions;
    private long mActiveItemId = -1L;
    private long mBufferedPosition;
    private final List<PlaybackStateCompat.CustomAction> mCustomActions = new ArrayList();
    private int mErrorCode;
    private CharSequence mErrorMessage;
    private Bundle mExtras;
    private long mPosition;
    private float mRate;
    private int mState;
    private long mUpdateTime;
    
    public Builder() {}
    
    public Builder(PlaybackStateCompat paramPlaybackStateCompat)
    {
      mState = mState;
      mPosition = mPosition;
      mRate = mSpeed;
      mUpdateTime = mUpdateTime;
      mBufferedPosition = mBufferedPosition;
      mActions = mActions;
      mErrorCode = mErrorCode;
      mErrorMessage = mErrorMessage;
      if (mCustomActions != null) {
        mCustomActions.addAll(mCustomActions);
      }
      mActiveItemId = mActiveItemId;
      mExtras = mExtras;
    }
    
    public Builder addCustomAction(PlaybackStateCompat.CustomAction paramCustomAction)
    {
      if (paramCustomAction != null)
      {
        mCustomActions.add(paramCustomAction);
        return this;
      }
      throw new IllegalArgumentException("You may not add a null CustomAction to PlaybackStateCompat.");
    }
    
    public Builder addCustomAction(String paramString1, String paramString2, int paramInt)
    {
      return addCustomAction(new PlaybackStateCompat.CustomAction(paramString1, paramString2, paramInt, null));
    }
    
    public PlaybackStateCompat build()
    {
      return new PlaybackStateCompat(mState, mPosition, mBufferedPosition, mRate, mActions, mErrorCode, mErrorMessage, mUpdateTime, mCustomActions, mActiveItemId, mExtras);
    }
    
    public Builder setActions(long paramLong)
    {
      mActions = paramLong;
      return this;
    }
    
    public Builder setActiveQueueItemId(long paramLong)
    {
      mActiveItemId = paramLong;
      return this;
    }
    
    public Builder setBufferedPosition(long paramLong)
    {
      mBufferedPosition = paramLong;
      return this;
    }
    
    public Builder setErrorMessage(int paramInt, CharSequence paramCharSequence)
    {
      mErrorCode = paramInt;
      mErrorMessage = paramCharSequence;
      return this;
    }
    
    public Builder setErrorMessage(CharSequence paramCharSequence)
    {
      mErrorMessage = paramCharSequence;
      return this;
    }
    
    public Builder setExtras(Bundle paramBundle)
    {
      mExtras = paramBundle;
      return this;
    }
    
    public Builder setState(int paramInt, long paramLong, float paramFloat)
    {
      return setState(paramInt, paramLong, paramFloat, SystemClock.elapsedRealtime());
    }
    
    public Builder setState(int paramInt, long paramLong1, float paramFloat, long paramLong2)
    {
      mState = paramInt;
      mPosition = paramLong1;
      mUpdateTime = paramLong2;
      mRate = paramFloat;
      return this;
    }
  }
  
  public static final class CustomAction
    implements Parcelable
  {
    public static final Parcelable.Creator<CustomAction> CREATOR = new Parcelable.Creator()
    {
      public PlaybackStateCompat.CustomAction createFromParcel(Parcel paramAnonymousParcel)
      {
        return new PlaybackStateCompat.CustomAction(paramAnonymousParcel);
      }
      
      public PlaybackStateCompat.CustomAction[] newArray(int paramAnonymousInt)
      {
        return new PlaybackStateCompat.CustomAction[paramAnonymousInt];
      }
    };
    private final String mAction;
    private Object mCustomActionObj;
    private final Bundle mExtras;
    private final int mIcon;
    private final CharSequence mName;
    
    CustomAction(Parcel paramParcel)
    {
      mAction = paramParcel.readString();
      mName = ((CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(paramParcel));
      mIcon = paramParcel.readInt();
      mExtras = paramParcel.readBundle();
    }
    
    CustomAction(String paramString, CharSequence paramCharSequence, int paramInt, Bundle paramBundle)
    {
      mAction = paramString;
      mName = paramCharSequence;
      mIcon = paramInt;
      mExtras = paramBundle;
    }
    
    public static CustomAction fromCustomAction(Object paramObject)
    {
      if ((paramObject != null) && (Build.VERSION.SDK_INT >= 21))
      {
        CustomAction localCustomAction = new CustomAction(PlaybackStateCompatApi21.CustomAction.getAction(paramObject), PlaybackStateCompatApi21.CustomAction.getName(paramObject), PlaybackStateCompatApi21.CustomAction.getIcon(paramObject), PlaybackStateCompatApi21.CustomAction.getExtras(paramObject));
        mCustomActionObj = paramObject;
        return localCustomAction;
      }
      return null;
    }
    
    public int describeContents()
    {
      return 0;
    }
    
    public String getAction()
    {
      return mAction;
    }
    
    public Object getCustomAction()
    {
      if ((mCustomActionObj == null) && (Build.VERSION.SDK_INT >= 21))
      {
        mCustomActionObj = PlaybackStateCompatApi21.CustomAction.newInstance(mAction, mName, mIcon, mExtras);
        return mCustomActionObj;
      }
      return mCustomActionObj;
    }
    
    public Bundle getExtras()
    {
      return mExtras;
    }
    
    public int getIcon()
    {
      return mIcon;
    }
    
    public CharSequence getName()
    {
      return mName;
    }
    
    public String toString()
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Action:mName='");
      localStringBuilder.append(mName);
      localStringBuilder.append(", mIcon=");
      localStringBuilder.append(mIcon);
      localStringBuilder.append(", mExtras=");
      localStringBuilder.append(mExtras);
      return localStringBuilder.toString();
    }
    
    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      paramParcel.writeString(mAction);
      TextUtils.writeToParcel(mName, paramParcel, paramInt);
      paramParcel.writeInt(mIcon);
      paramParcel.writeBundle(mExtras);
    }
    
    public static final class Builder
    {
      private final String mAction;
      private Bundle mExtras;
      private final int mIcon;
      private final CharSequence mName;
      
      public Builder(String paramString, CharSequence paramCharSequence, int paramInt)
      {
        if (!TextUtils.isEmpty(paramString))
        {
          if (!TextUtils.isEmpty(paramCharSequence))
          {
            if (paramInt != 0)
            {
              mAction = paramString;
              mName = paramCharSequence;
              mIcon = paramInt;
              return;
            }
            throw new IllegalArgumentException("You must specify an icon resource id to build a CustomAction.");
          }
          throw new IllegalArgumentException("You must specify a name to build a CustomAction.");
        }
        throw new IllegalArgumentException("You must specify an action to build a CustomAction.");
      }
      
      public PlaybackStateCompat.CustomAction build()
      {
        return new PlaybackStateCompat.CustomAction(mAction, mName, mIcon, mExtras);
      }
      
      public Builder setExtras(Bundle paramBundle)
      {
        mExtras = paramBundle;
        return this;
      }
    }
  }
  
  @Retention(RetentionPolicy.SOURCE)
  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  public static @interface ErrorCode {}
  
  @Retention(RetentionPolicy.SOURCE)
  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  public static @interface MediaKeyAction {}
  
  @Retention(RetentionPolicy.SOURCE)
  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  public static @interface RepeatMode {}
  
  @Retention(RetentionPolicy.SOURCE)
  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  public static @interface ShuffleMode {}
  
  @Retention(RetentionPolicy.SOURCE)
  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  public static @interface State {}
}
