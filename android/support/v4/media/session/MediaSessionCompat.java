package android.support.v4.media.session;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.media.AudioManager;
import android.media.MediaMetadataEditor;
import android.media.Rating;
import android.media.RemoteControlClient;
import android.media.RemoteControlClient.MetadataEditor;
import android.media.RemoteControlClient.OnMetadataUpdateListener;
import android.media.RemoteControlClient.OnPlaybackPositionUpdateListener;
import android.net.Uri;
import android.os.BadParcelableException;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.Message;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.os.ResultReceiver;
import android.os.SystemClock;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.v4.media.MediaDescriptionCompat;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.MediaMetadataCompat.Builder;
import android.support.v4.media.RatingCompat;
import android.support.v4.media.VolumeProviderCompat;
import android.support.v4.media.VolumeProviderCompat.Callback;
import android.support.v4.package_7.BundleCompat;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.ViewConfiguration;
import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MediaSessionCompat
{
  static final String ACTION_ARGUMENT_CAPTIONING_ENABLED = "android.support.v4.media.session.action.ARGUMENT_CAPTIONING_ENABLED";
  static final String ACTION_ARGUMENT_EXTRAS = "android.support.v4.media.session.action.ARGUMENT_EXTRAS";
  static final String ACTION_ARGUMENT_MEDIA_ID = "android.support.v4.media.session.action.ARGUMENT_MEDIA_ID";
  static final String ACTION_ARGUMENT_QUERY = "android.support.v4.media.session.action.ARGUMENT_QUERY";
  static final String ACTION_ARGUMENT_RATING = "android.support.v4.media.session.action.ARGUMENT_RATING";
  static final String ACTION_ARGUMENT_REPEAT_MODE = "android.support.v4.media.session.action.ARGUMENT_REPEAT_MODE";
  static final String ACTION_ARGUMENT_SHUFFLE_MODE = "android.support.v4.media.session.action.ARGUMENT_SHUFFLE_MODE";
  static final String ACTION_ARGUMENT_SHUFFLE_MODE_ENABLED = "android.support.v4.media.session.action.ARGUMENT_SHUFFLE_MODE_ENABLED";
  static final String ACTION_ARGUMENT_URI = "android.support.v4.media.session.action.ARGUMENT_URI";
  public static final String ACTION_FLAG_AS_INAPPROPRIATE = "android.support.v4.media.session.action.FLAG_AS_INAPPROPRIATE";
  public static final String ACTION_FOLLOW = "android.support.v4.media.session.action.FOLLOW";
  static final String ACTION_PLAY_FROM_URI = "android.support.v4.media.session.action.PLAY_FROM_URI";
  static final String ACTION_PREPARE = "android.support.v4.media.session.action.PREPARE";
  static final String ACTION_PREPARE_FROM_MEDIA_ID = "android.support.v4.media.session.action.PREPARE_FROM_MEDIA_ID";
  static final String ACTION_PREPARE_FROM_SEARCH = "android.support.v4.media.session.action.PREPARE_FROM_SEARCH";
  static final String ACTION_PREPARE_FROM_URI = "android.support.v4.media.session.action.PREPARE_FROM_URI";
  static final String ACTION_SET_CAPTIONING_ENABLED = "android.support.v4.media.session.action.SET_CAPTIONING_ENABLED";
  static final String ACTION_SET_RATING = "android.support.v4.media.session.action.SET_RATING";
  static final String ACTION_SET_REPEAT_MODE = "android.support.v4.media.session.action.SET_REPEAT_MODE";
  static final String ACTION_SET_SHUFFLE_MODE = "android.support.v4.media.session.action.SET_SHUFFLE_MODE";
  static final String ACTION_SET_SHUFFLE_MODE_ENABLED = "android.support.v4.media.session.action.SET_SHUFFLE_MODE_ENABLED";
  public static final String ACTION_SKIP_AD = "android.support.v4.media.session.action.SKIP_AD";
  public static final String ACTION_UNFOLLOW = "android.support.v4.media.session.action.UNFOLLOW";
  public static final String ARGUMENT_MEDIA_ATTRIBUTE = "android.support.v4.media.session.ARGUMENT_MEDIA_ATTRIBUTE";
  public static final String ARGUMENT_MEDIA_ATTRIBUTE_VALUE = "android.support.v4.media.session.ARGUMENT_MEDIA_ATTRIBUTE_VALUE";
  static final String EXTRA_BINDER = "android.support.v4.media.session.EXTRA_BINDER";
  public static final int FLAG_HANDLES_MEDIA_BUTTONS = 1;
  public static final int FLAG_HANDLES_QUEUE_COMMANDS = 4;
  public static final int FLAG_HANDLES_TRANSPORT_CONTROLS = 2;
  private static final int MAX_BITMAP_SIZE_IN_DP = 320;
  public static final int MEDIA_ATTRIBUTE_ALBUM = 1;
  public static final int MEDIA_ATTRIBUTE_ARTIST = 0;
  public static final int MEDIA_ATTRIBUTE_PLAYLIST = 2;
  static final String TAG = "MediaSessionCompat";
  static int sMaxBitmapSize;
  private final ArrayList<OnActiveChangeListener> mActiveListeners = new ArrayList();
  private final MediaControllerCompat mController;
  private final MediaSessionImpl mImpl;
  
  private MediaSessionCompat(Context paramContext, MediaSessionImpl paramMediaSessionImpl)
  {
    mImpl = paramMediaSessionImpl;
    if ((Build.VERSION.SDK_INT >= 21) && (!MediaSessionCompatApi21.hasCallback(paramMediaSessionImpl.getMediaSession()))) {
      setCallback(new Callback() {});
    }
    mController = new MediaControllerCompat(paramContext, this);
  }
  
  public MediaSessionCompat(Context paramContext, String paramString)
  {
    this(paramContext, paramString, null, null);
  }
  
  public MediaSessionCompat(Context paramContext, String paramString, ComponentName paramComponentName, PendingIntent paramPendingIntent)
  {
    if (paramContext != null)
    {
      if (!TextUtils.isEmpty(paramString))
      {
        ComponentName localComponentName1 = paramComponentName;
        if (paramComponentName == null)
        {
          ComponentName localComponentName2 = MediaButtonReceiver.getMediaButtonReceiverComponent(paramContext);
          paramComponentName = localComponentName2;
          localComponentName1 = paramComponentName;
          if (localComponentName2 == null)
          {
            Log.w("MediaSessionCompat", "Couldn't find a unique registered media button receiver in the given context.");
            localComponentName1 = paramComponentName;
          }
        }
        paramComponentName = paramPendingIntent;
        if (localComponentName1 != null)
        {
          paramComponentName = paramPendingIntent;
          if (paramPendingIntent == null)
          {
            paramComponentName = new Intent("android.intent.action.MEDIA_BUTTON");
            paramComponentName.setComponent(localComponentName1);
            paramComponentName = PendingIntent.getBroadcast(paramContext, 0, paramComponentName, 0);
          }
        }
        if (Build.VERSION.SDK_INT >= 21)
        {
          mImpl = new MediaSessionImplApi21(paramContext, paramString);
          setCallback(new Callback() {});
          mImpl.setMediaButtonReceiver(paramComponentName);
        }
        else if (Build.VERSION.SDK_INT >= 19)
        {
          mImpl = new MediaSessionImplApi19(paramContext, paramString, localComponentName1, paramComponentName);
        }
        else if (Build.VERSION.SDK_INT >= 18)
        {
          mImpl = new MediaSessionImplApi18(paramContext, paramString, localComponentName1, paramComponentName);
        }
        else
        {
          mImpl = new MediaSessionImplBase(paramContext, paramString, localComponentName1, paramComponentName);
        }
        mController = new MediaControllerCompat(paramContext, this);
        if (sMaxBitmapSize == 0) {
          sMaxBitmapSize = (int)TypedValue.applyDimension(1, 320.0F, paramContext.getResources().getDisplayMetrics());
        }
      }
      else
      {
        throw new IllegalArgumentException("tag must not be null or empty");
      }
    }
    else {
      throw new IllegalArgumentException("context must not be null");
    }
  }
  
  public static MediaSessionCompat fromMediaSession(Context paramContext, Object paramObject)
  {
    if ((paramContext != null) && (paramObject != null) && (Build.VERSION.SDK_INT >= 21)) {
      return new MediaSessionCompat(paramContext, new MediaSessionImplApi21(paramObject));
    }
    return null;
  }
  
  private static PlaybackStateCompat getStateWithUpdatedPosition(PlaybackStateCompat paramPlaybackStateCompat, MediaMetadataCompat paramMediaMetadataCompat)
  {
    PlaybackStateCompat localPlaybackStateCompat = paramPlaybackStateCompat;
    if (paramPlaybackStateCompat != null)
    {
      long l1 = paramPlaybackStateCompat.getPosition();
      long l2 = -1L;
      if (l1 == -1L) {
        return paramPlaybackStateCompat;
      }
      if ((paramPlaybackStateCompat.getState() != 3) && (paramPlaybackStateCompat.getState() != 4))
      {
        localPlaybackStateCompat = paramPlaybackStateCompat;
        if (paramPlaybackStateCompat.getState() != 5) {}
      }
      else
      {
        l1 = paramPlaybackStateCompat.getLastPositionUpdateTime();
        localPlaybackStateCompat = paramPlaybackStateCompat;
        if (l1 > 0L)
        {
          long l4 = SystemClock.elapsedRealtime();
          long l3 = (paramPlaybackStateCompat.getPlaybackSpeed() * (float)(l4 - l1)) + paramPlaybackStateCompat.getPosition();
          l1 = l2;
          if (paramMediaMetadataCompat != null)
          {
            l1 = l2;
            if (paramMediaMetadataCompat.containsKey("android.media.metadata.DURATION")) {
              l1 = paramMediaMetadataCompat.getLong("android.media.metadata.DURATION");
            }
          }
          if ((l1 < 0L) || (l3 <= l1)) {
            if (l3 < 0L) {
              l1 = 0L;
            } else {
              l1 = l3;
            }
          }
          localPlaybackStateCompat = new PlaybackStateCompat.Builder(paramPlaybackStateCompat).setState(paramPlaybackStateCompat.getState(), l1, paramPlaybackStateCompat.getPlaybackSpeed(), l4).build();
        }
      }
    }
    return localPlaybackStateCompat;
  }
  
  public void addOnActiveChangeListener(OnActiveChangeListener paramOnActiveChangeListener)
  {
    if (paramOnActiveChangeListener != null)
    {
      mActiveListeners.add(paramOnActiveChangeListener);
      return;
    }
    throw new IllegalArgumentException("Listener may not be null");
  }
  
  public String getCallingPackage()
  {
    return mImpl.getCallingPackage();
  }
  
  public MediaControllerCompat getController()
  {
    return mController;
  }
  
  public Object getMediaSession()
  {
    return mImpl.getMediaSession();
  }
  
  public Object getRemoteControlClient()
  {
    return mImpl.getRemoteControlClient();
  }
  
  public Token getSessionToken()
  {
    return mImpl.getSessionToken();
  }
  
  public boolean isActive()
  {
    return mImpl.isActive();
  }
  
  public void release()
  {
    mImpl.release();
  }
  
  public void removeOnActiveChangeListener(OnActiveChangeListener paramOnActiveChangeListener)
  {
    if (paramOnActiveChangeListener != null)
    {
      mActiveListeners.remove(paramOnActiveChangeListener);
      return;
    }
    throw new IllegalArgumentException("Listener may not be null");
  }
  
  public void sendSessionEvent(String paramString, Bundle paramBundle)
  {
    if (!TextUtils.isEmpty(paramString))
    {
      mImpl.sendSessionEvent(paramString, paramBundle);
      return;
    }
    throw new IllegalArgumentException("event cannot be null or empty");
  }
  
  public void setActive(boolean paramBoolean)
  {
    mImpl.setActive(paramBoolean);
    Iterator localIterator = mActiveListeners.iterator();
    while (localIterator.hasNext()) {
      ((OnActiveChangeListener)localIterator.next()).onActiveChanged();
    }
  }
  
  public void setCallback(Callback paramCallback)
  {
    setCallback(paramCallback, null);
  }
  
  public void setCallback(Callback paramCallback, Handler paramHandler)
  {
    MediaSessionImpl localMediaSessionImpl = mImpl;
    if (paramHandler == null) {
      paramHandler = new Handler();
    }
    localMediaSessionImpl.setCallback(paramCallback, paramHandler);
  }
  
  public void setCaptioningEnabled(boolean paramBoolean)
  {
    mImpl.setCaptioningEnabled(paramBoolean);
  }
  
  public void setExtras(Bundle paramBundle)
  {
    mImpl.setExtras(paramBundle);
  }
  
  public void setFlags(int paramInt)
  {
    mImpl.setFlags(paramInt);
  }
  
  public void setMediaButtonReceiver(PendingIntent paramPendingIntent)
  {
    mImpl.setMediaButtonReceiver(paramPendingIntent);
  }
  
  public void setMetadata(MediaMetadataCompat paramMediaMetadataCompat)
  {
    mImpl.setMetadata(paramMediaMetadataCompat);
  }
  
  public void setPlaybackState(PlaybackStateCompat paramPlaybackStateCompat)
  {
    mImpl.setPlaybackState(paramPlaybackStateCompat);
  }
  
  public void setPlaybackToLocal(int paramInt)
  {
    mImpl.setPlaybackToLocal(paramInt);
  }
  
  public void setPlaybackToRemote(VolumeProviderCompat paramVolumeProviderCompat)
  {
    if (paramVolumeProviderCompat != null)
    {
      mImpl.setPlaybackToRemote(paramVolumeProviderCompat);
      return;
    }
    throw new IllegalArgumentException("volumeProvider may not be null!");
  }
  
  public void setQueue(List paramList)
  {
    mImpl.setQueue(paramList);
  }
  
  public void setQueueTitle(CharSequence paramCharSequence)
  {
    mImpl.setQueueTitle(paramCharSequence);
  }
  
  public void setRatingType(int paramInt)
  {
    mImpl.setRatingType(paramInt);
  }
  
  public void setRepeatMode(int paramInt)
  {
    mImpl.setRepeatMode(paramInt);
  }
  
  public void setSessionActivity(PendingIntent paramPendingIntent)
  {
    mImpl.setSessionActivity(paramPendingIntent);
  }
  
  public void setShuffleMode(int paramInt)
  {
    mImpl.setShuffleMode(paramInt);
  }
  
  public void setShuffleModeEnabled(boolean paramBoolean)
  {
    mImpl.setShuffleModeEnabled(paramBoolean);
  }
  
  public static abstract class Callback
  {
    private CallbackHandler mCallbackHandler = null;
    final Object mCallbackObj;
    private boolean mMediaPlayPauseKeyPending;
    private WeakReference<MediaSessionCompat.MediaSessionImpl> mSessionImpl;
    
    public Callback()
    {
      if (Build.VERSION.SDK_INT >= 24)
      {
        mCallbackObj = MediaSessionCompatApi24.createCallback(new StubApi24());
        return;
      }
      if (Build.VERSION.SDK_INT >= 23)
      {
        mCallbackObj = MediaSessionCompatApi23.createCallback(new StubApi23());
        return;
      }
      if (Build.VERSION.SDK_INT >= 21)
      {
        mCallbackObj = MediaSessionCompatApi21.createCallback(new StubApi21());
        return;
      }
      mCallbackObj = null;
    }
    
    private void handleMediaPlayPauseKeySingleTapIfPending()
    {
      if (!mMediaPlayPauseKeyPending) {
        return;
      }
      int k = 0;
      mMediaPlayPauseKeyPending = false;
      mCallbackHandler.removeMessages(1);
      Object localObject = (MediaSessionCompat.MediaSessionImpl)mSessionImpl.get();
      if (localObject == null) {
        return;
      }
      localObject = ((MediaSessionCompat.MediaSessionImpl)localObject).getPlaybackState();
      long l;
      if (localObject == null) {
        l = 0L;
      } else {
        l = ((PlaybackStateCompat)localObject).getActions();
      }
      int i;
      if ((localObject != null) && (((PlaybackStateCompat)localObject).getState() == 3)) {
        i = 1;
      } else {
        i = 0;
      }
      int j;
      if ((0x204 & l) != 0L) {
        j = 1;
      } else {
        j = 0;
      }
      if ((l & 0x202) != 0L) {
        k = 1;
      }
      if ((i != 0) && (k != 0))
      {
        onPause();
        return;
      }
      if ((i == 0) && (j != 0)) {
        onPlay();
      }
    }
    
    private void setSessionImpl(MediaSessionCompat.MediaSessionImpl paramMediaSessionImpl, Handler paramHandler)
    {
      mSessionImpl = new WeakReference(paramMediaSessionImpl);
      if (mCallbackHandler != null) {
        mCallbackHandler.removeCallbacksAndMessages(null);
      }
      mCallbackHandler = new CallbackHandler(paramHandler.getLooper());
    }
    
    public void onAddQueueItem(MediaDescriptionCompat paramMediaDescriptionCompat) {}
    
    public void onAddQueueItem(MediaDescriptionCompat paramMediaDescriptionCompat, int paramInt) {}
    
    public void onCommand(String paramString, Bundle paramBundle, ResultReceiver paramResultReceiver) {}
    
    public void onCustomAction(String paramString, Bundle paramBundle) {}
    
    public void onFastForward() {}
    
    public boolean onMediaButtonEvent(Intent paramIntent)
    {
      MediaSessionCompat.MediaSessionImpl localMediaSessionImpl = (MediaSessionCompat.MediaSessionImpl)mSessionImpl.get();
      if (localMediaSessionImpl != null)
      {
        if (mCallbackHandler == null) {
          return false;
        }
        paramIntent = (KeyEvent)paramIntent.getParcelableExtra("android.intent.extra.KEY_EVENT");
        if (paramIntent != null)
        {
          if (paramIntent.getAction() != 0) {
            return false;
          }
          int i = paramIntent.getKeyCode();
          if ((i != 79) && (i != 85))
          {
            handleMediaPlayPauseKeySingleTapIfPending();
            return false;
          }
          if (paramIntent.getRepeatCount() > 0)
          {
            handleMediaPlayPauseKeySingleTapIfPending();
            return true;
          }
          if (mMediaPlayPauseKeyPending)
          {
            mCallbackHandler.removeMessages(1);
            mMediaPlayPauseKeyPending = false;
            paramIntent = localMediaSessionImpl.getPlaybackState();
            long l;
            if (paramIntent == null) {
              l = 0L;
            } else {
              l = paramIntent.getActions();
            }
            if ((l & 0x20) == 0L) {
              break label165;
            }
            onSkipToNext();
            return true;
          }
          mMediaPlayPauseKeyPending = true;
          mCallbackHandler.sendEmptyMessageDelayed(1, ViewConfiguration.getDoubleTapTimeout());
          return true;
        }
      }
      return false;
      label165:
      return true;
    }
    
    public void onPause() {}
    
    public void onPlay() {}
    
    public void onPlayFromMediaId(String paramString, Bundle paramBundle) {}
    
    public void onPlayFromSearch(String paramString, Bundle paramBundle) {}
    
    public void onPlayFromUri(Uri paramUri, Bundle paramBundle) {}
    
    public void onPrepare() {}
    
    public void onPrepareFromMediaId(String paramString, Bundle paramBundle) {}
    
    public void onPrepareFromSearch(String paramString, Bundle paramBundle) {}
    
    public void onPrepareFromUri(Uri paramUri, Bundle paramBundle) {}
    
    public void onRemoveQueueItem(MediaDescriptionCompat paramMediaDescriptionCompat) {}
    
    public void onRemoveQueueItemAt(int paramInt) {}
    
    public void onRewind() {}
    
    public void onSeekTo(long paramLong) {}
    
    public void onSetCaptioningEnabled(boolean paramBoolean) {}
    
    public void onSetRating(RatingCompat paramRatingCompat) {}
    
    public void onSetRating(RatingCompat paramRatingCompat, Bundle paramBundle) {}
    
    public void onSetRepeatMode(int paramInt) {}
    
    public void onSetShuffleMode(int paramInt) {}
    
    public void onSetShuffleModeEnabled(boolean paramBoolean) {}
    
    public void onSkipToNext() {}
    
    public void onSkipToPrevious() {}
    
    public void onSkipToQueueItem(long paramLong) {}
    
    public void onStop() {}
    
    private class CallbackHandler
      extends Handler
    {
      private static final int MSG_MEDIA_PLAY_PAUSE_KEY_DOUBLE_TAP_TIMEOUT = 1;
      
      CallbackHandler(Looper paramLooper)
      {
        super();
      }
      
      public void handleMessage(Message paramMessage)
      {
        if (what == 1) {
          MediaSessionCompat.Callback.this.handleMediaPlayPauseKeySingleTapIfPending();
        }
      }
    }
    
    @RequiresApi(21)
    private class StubApi21
      implements MediaSessionCompatApi21.Callback
    {
      StubApi21() {}
      
      public void onCommand(String paramString, Bundle paramBundle, ResultReceiver paramResultReceiver)
      {
        try
        {
          boolean bool = paramString.equals("android.support.v4.media.session.command.GET_EXTRA_BINDER");
          Object localObject = null;
          MediaSessionCompat.Callback localCallback = null;
          if (bool)
          {
            paramString = MediaSessionCompat.Callback.this;
            paramString = mSessionImpl.get();
            paramString = (MediaSessionCompat.MediaSessionImplApi21)paramString;
            if (paramString == null) {
              return;
            }
            paramBundle = new Bundle();
            paramString = paramString.getSessionToken().getExtraBinder();
            if (paramString == null) {
              paramString = localCallback;
            } else {
              paramString = paramString.asBinder();
            }
            paramString = (IBinder)paramString;
            BundleCompat.putBinder(paramBundle, "android.support.v4.media.session.EXTRA_BINDER", paramString);
            paramResultReceiver.send(0, paramBundle);
            return;
          }
          bool = paramString.equals("android.support.v4.media.session.command.ADD_QUEUE_ITEM");
          if (bool)
          {
            paramBundle.setClassLoader(MediaDescriptionCompat.class.getClassLoader());
            paramString = MediaSessionCompat.Callback.this;
            paramBundle = paramBundle.getParcelable("android.support.v4.media.session.command.ARGUMENT_MEDIA_DESCRIPTION");
            paramBundle = (MediaDescriptionCompat)paramBundle;
            paramString.onAddQueueItem(paramBundle);
            return;
          }
          bool = paramString.equals("android.support.v4.media.session.command.ADD_QUEUE_ITEM_AT");
          if (bool)
          {
            paramBundle.setClassLoader(MediaDescriptionCompat.class.getClassLoader());
            paramString = MediaSessionCompat.Callback.this;
            paramResultReceiver = paramBundle.getParcelable("android.support.v4.media.session.command.ARGUMENT_MEDIA_DESCRIPTION");
            paramResultReceiver = (MediaDescriptionCompat)paramResultReceiver;
            paramString.onAddQueueItem(paramResultReceiver, paramBundle.getInt("android.support.v4.media.session.command.ARGUMENT_INDEX"));
            return;
          }
          bool = paramString.equals("android.support.v4.media.session.command.REMOVE_QUEUE_ITEM");
          if (bool)
          {
            paramBundle.setClassLoader(MediaDescriptionCompat.class.getClassLoader());
            paramString = MediaSessionCompat.Callback.this;
            paramBundle = paramBundle.getParcelable("android.support.v4.media.session.command.ARGUMENT_MEDIA_DESCRIPTION");
            paramBundle = (MediaDescriptionCompat)paramBundle;
            paramString.onRemoveQueueItem(paramBundle);
            return;
          }
          bool = paramString.equals("android.support.v4.media.session.command.REMOVE_QUEUE_ITEM_AT");
          if (bool)
          {
            paramString = MediaSessionCompat.Callback.this;
            paramString = mSessionImpl.get();
            paramResultReceiver = (MediaSessionCompat.MediaSessionImplApi21)paramString;
            if (paramResultReceiver == null) {
              return;
            }
            paramString = MediaSessionCompat.MediaSessionImplApi21.access$200(paramResultReceiver);
            if (paramString == null) {
              return;
            }
            int i = paramBundle.getInt("android.support.v4.media.session.command.ARGUMENT_INDEX", -1);
            paramString = localObject;
            if (i >= 0)
            {
              int j = MediaSessionCompat.MediaSessionImplApi21.access$200(paramResultReceiver).size();
              paramString = localObject;
              if (i < j)
              {
                paramString = MediaSessionCompat.MediaSessionImplApi21.access$200(paramResultReceiver).get(i);
                paramString = (MediaSessionCompat.QueueItem)paramString;
              }
            }
            if (paramString == null) {
              return;
            }
            paramBundle = MediaSessionCompat.Callback.this;
            paramString = (MediaSessionCompat.QueueItem)paramString;
            paramBundle.onRemoveQueueItem(paramString.getDescription());
            return;
          }
          localCallback = MediaSessionCompat.Callback.this;
          localCallback.onCommand(paramString, paramBundle, paramResultReceiver);
          return;
        }
        catch (BadParcelableException paramString)
        {
          for (;;) {}
        }
        Log.e("MediaSessionCompat", "Could not unparcel the extra data.");
        return;
      }
      
      public void onCustomAction(String paramString, Bundle paramBundle)
      {
        if (paramString.equals("android.support.v4.media.session.action.PLAY_FROM_URI"))
        {
          paramString = (Uri)paramBundle.getParcelable("android.support.v4.media.session.action.ARGUMENT_URI");
          paramBundle = (Bundle)paramBundle.getParcelable("android.support.v4.media.session.action.ARGUMENT_EXTRAS");
          onPlayFromUri(paramString, paramBundle);
          return;
        }
        if (paramString.equals("android.support.v4.media.session.action.PREPARE"))
        {
          onPrepare();
          return;
        }
        if (paramString.equals("android.support.v4.media.session.action.PREPARE_FROM_MEDIA_ID"))
        {
          paramString = paramBundle.getString("android.support.v4.media.session.action.ARGUMENT_MEDIA_ID");
          paramBundle = paramBundle.getBundle("android.support.v4.media.session.action.ARGUMENT_EXTRAS");
          onPrepareFromMediaId(paramString, paramBundle);
          return;
        }
        if (paramString.equals("android.support.v4.media.session.action.PREPARE_FROM_SEARCH"))
        {
          paramString = paramBundle.getString("android.support.v4.media.session.action.ARGUMENT_QUERY");
          paramBundle = paramBundle.getBundle("android.support.v4.media.session.action.ARGUMENT_EXTRAS");
          onPrepareFromSearch(paramString, paramBundle);
          return;
        }
        if (paramString.equals("android.support.v4.media.session.action.PREPARE_FROM_URI"))
        {
          paramString = (Uri)paramBundle.getParcelable("android.support.v4.media.session.action.ARGUMENT_URI");
          paramBundle = paramBundle.getBundle("android.support.v4.media.session.action.ARGUMENT_EXTRAS");
          onPrepareFromUri(paramString, paramBundle);
          return;
        }
        boolean bool;
        if (paramString.equals("android.support.v4.media.session.action.SET_CAPTIONING_ENABLED"))
        {
          bool = paramBundle.getBoolean("android.support.v4.media.session.action.ARGUMENT_CAPTIONING_ENABLED");
          onSetCaptioningEnabled(bool);
          return;
        }
        int i;
        if (paramString.equals("android.support.v4.media.session.action.SET_REPEAT_MODE"))
        {
          i = paramBundle.getInt("android.support.v4.media.session.action.ARGUMENT_REPEAT_MODE");
          onSetRepeatMode(i);
          return;
        }
        if (paramString.equals("android.support.v4.media.session.action.SET_SHUFFLE_MODE_ENABLED"))
        {
          bool = paramBundle.getBoolean("android.support.v4.media.session.action.ARGUMENT_SHUFFLE_MODE_ENABLED");
          onSetShuffleModeEnabled(bool);
          return;
        }
        if (paramString.equals("android.support.v4.media.session.action.SET_SHUFFLE_MODE"))
        {
          i = paramBundle.getInt("android.support.v4.media.session.action.ARGUMENT_SHUFFLE_MODE");
          onSetShuffleMode(i);
          return;
        }
        if (paramString.equals("android.support.v4.media.session.action.SET_RATING"))
        {
          paramBundle.setClassLoader(RatingCompat.class.getClassLoader());
          paramString = (RatingCompat)paramBundle.getParcelable("android.support.v4.media.session.action.ARGUMENT_RATING");
          paramBundle = paramBundle.getBundle("android.support.v4.media.session.action.ARGUMENT_EXTRAS");
          onSetRating(paramString, paramBundle);
          return;
        }
        MediaSessionCompat.Callback.this.onCustomAction(paramString, paramBundle);
      }
      
      public void onFastForward()
      {
        MediaSessionCompat.Callback.this.onFastForward();
      }
      
      public boolean onMediaButtonEvent(Intent paramIntent)
      {
        return MediaSessionCompat.Callback.this.onMediaButtonEvent(paramIntent);
      }
      
      public void onPause()
      {
        MediaSessionCompat.Callback.this.onPause();
      }
      
      public void onPlay()
      {
        MediaSessionCompat.Callback.this.onPlay();
      }
      
      public void onPlayFromMediaId(String paramString, Bundle paramBundle)
      {
        MediaSessionCompat.Callback.this.onPlayFromMediaId(paramString, paramBundle);
      }
      
      public void onPlayFromSearch(String paramString, Bundle paramBundle)
      {
        MediaSessionCompat.Callback.this.onPlayFromSearch(paramString, paramBundle);
      }
      
      public void onRewind()
      {
        MediaSessionCompat.Callback.this.onRewind();
      }
      
      public void onSeekTo(long paramLong)
      {
        MediaSessionCompat.Callback.this.onSeekTo(paramLong);
      }
      
      public void onSetRating(Object paramObject)
      {
        onSetRating(RatingCompat.fromRating(paramObject));
      }
      
      public void onSetRating(Object paramObject, Bundle paramBundle)
      {
        onSetRating(RatingCompat.fromRating(paramObject), paramBundle);
      }
      
      public void onSkipToNext()
      {
        MediaSessionCompat.Callback.this.onSkipToNext();
      }
      
      public void onSkipToPrevious()
      {
        MediaSessionCompat.Callback.this.onSkipToPrevious();
      }
      
      public void onSkipToQueueItem(long paramLong)
      {
        MediaSessionCompat.Callback.this.onSkipToQueueItem(paramLong);
      }
      
      public void onStop()
      {
        MediaSessionCompat.Callback.this.onStop();
      }
    }
    
    @RequiresApi(23)
    private class StubApi23
      extends MediaSessionCompat.Callback.StubApi21
      implements MediaSessionCompatApi23.Callback
    {
      StubApi23()
      {
        super();
      }
      
      public void onPlayFromUri(Uri paramUri, Bundle paramBundle)
      {
        MediaSessionCompat.Callback.this.onPlayFromUri(paramUri, paramBundle);
      }
    }
    
    @RequiresApi(24)
    private class StubApi24
      extends MediaSessionCompat.Callback.StubApi23
      implements MediaSessionCompatApi24.Callback
    {
      StubApi24()
      {
        super();
      }
      
      public void onPrepare()
      {
        MediaSessionCompat.Callback.this.onPrepare();
      }
      
      public void onPrepareFromMediaId(String paramString, Bundle paramBundle)
      {
        MediaSessionCompat.Callback.this.onPrepareFromMediaId(paramString, paramBundle);
      }
      
      public void onPrepareFromSearch(String paramString, Bundle paramBundle)
      {
        MediaSessionCompat.Callback.this.onPrepareFromSearch(paramString, paramBundle);
      }
      
      public void onPrepareFromUri(Uri paramUri, Bundle paramBundle)
      {
        MediaSessionCompat.Callback.this.onPrepareFromUri(paramUri, paramBundle);
      }
    }
  }
  
  static abstract interface MediaSessionImpl
  {
    public abstract String getCallingPackage();
    
    public abstract Object getMediaSession();
    
    public abstract PlaybackStateCompat getPlaybackState();
    
    public abstract Object getRemoteControlClient();
    
    public abstract MediaSessionCompat.Token getSessionToken();
    
    public abstract boolean isActive();
    
    public abstract void release();
    
    public abstract void sendSessionEvent(String paramString, Bundle paramBundle);
    
    public abstract void setActive(boolean paramBoolean);
    
    public abstract void setCallback(MediaSessionCompat.Callback paramCallback, Handler paramHandler);
    
    public abstract void setCaptioningEnabled(boolean paramBoolean);
    
    public abstract void setExtras(Bundle paramBundle);
    
    public abstract void setFlags(int paramInt);
    
    public abstract void setMediaButtonReceiver(PendingIntent paramPendingIntent);
    
    public abstract void setMetadata(MediaMetadataCompat paramMediaMetadataCompat);
    
    public abstract void setPlaybackState(PlaybackStateCompat paramPlaybackStateCompat);
    
    public abstract void setPlaybackToLocal(int paramInt);
    
    public abstract void setPlaybackToRemote(VolumeProviderCompat paramVolumeProviderCompat);
    
    public abstract void setQueue(List paramList);
    
    public abstract void setQueueTitle(CharSequence paramCharSequence);
    
    public abstract void setRatingType(int paramInt);
    
    public abstract void setRepeatMode(int paramInt);
    
    public abstract void setSessionActivity(PendingIntent paramPendingIntent);
    
    public abstract void setShuffleMode(int paramInt);
    
    public abstract void setShuffleModeEnabled(boolean paramBoolean);
  }
  
  @RequiresApi(18)
  static class MediaSessionImplApi18
    extends MediaSessionCompat.MediaSessionImplBase
  {
    private static boolean sIsMbrPendingIntentSupported;
    
    MediaSessionImplApi18(Context paramContext, String paramString, ComponentName paramComponentName, PendingIntent paramPendingIntent)
    {
      super(paramString, paramComponentName, paramPendingIntent);
    }
    
    int getRccTransportControlFlagsFromActions(long paramLong)
    {
      int j = super.getRccTransportControlFlagsFromActions(paramLong);
      int i = j;
      if ((paramLong & 0x100) != 0L) {
        i = j | 0x100;
      }
      return i;
    }
    
    void registerMediaButtonEventReceiver(PendingIntent paramPendingIntent, ComponentName paramComponentName)
    {
      if (sIsMbrPendingIntentSupported) {}
      try
      {
        mAudioManager.registerMediaButtonEventReceiver(paramPendingIntent);
      }
      catch (NullPointerException localNullPointerException)
      {
        for (;;) {}
      }
      Log.w("MediaSessionCompat", "Unable to register media button event receiver with PendingIntent, falling back to ComponentName.");
      sIsMbrPendingIntentSupported = false;
      if (!sIsMbrPendingIntentSupported)
      {
        super.registerMediaButtonEventReceiver(paramPendingIntent, paramComponentName);
        return;
      }
    }
    
    public void setCallback(MediaSessionCompat.Callback paramCallback, Handler paramHandler)
    {
      super.setCallback(paramCallback, paramHandler);
      if (paramCallback == null)
      {
        mRcc.setPlaybackPositionUpdateListener(null);
        return;
      }
      paramCallback = new RemoteControlClient.OnPlaybackPositionUpdateListener()
      {
        public void onPlaybackPositionUpdate(long paramAnonymousLong)
        {
          postToHandler(18, Long.valueOf(paramAnonymousLong));
        }
      };
      mRcc.setPlaybackPositionUpdateListener(paramCallback);
    }
    
    void setRccState(PlaybackStateCompat paramPlaybackStateCompat)
    {
      long l3 = paramPlaybackStateCompat.getPosition();
      long l1 = l3;
      float f = paramPlaybackStateCompat.getPlaybackSpeed();
      long l5 = paramPlaybackStateCompat.getLastPositionUpdateTime();
      long l6 = SystemClock.elapsedRealtime();
      long l2 = l1;
      if (paramPlaybackStateCompat.getState() == 3)
      {
        long l4 = 0L;
        l2 = l1;
        if (l3 > 0L)
        {
          l1 = l4;
          if (l5 > 0L)
          {
            l2 = l6 - l5;
            l1 = l2;
            if (f > 0.0F)
            {
              l1 = l2;
              if (f != 1.0F) {
                l1 = ((float)l2 * f);
              }
            }
          }
          l2 = l3 + l1;
        }
      }
      mRcc.setPlaybackState(getRccStateFromState(paramPlaybackStateCompat.getState()), l2, f);
    }
    
    void unregisterMediaButtonEventReceiver(PendingIntent paramPendingIntent, ComponentName paramComponentName)
    {
      if (sIsMbrPendingIntentSupported)
      {
        mAudioManager.unregisterMediaButtonEventReceiver(paramPendingIntent);
        return;
      }
      super.unregisterMediaButtonEventReceiver(paramPendingIntent, paramComponentName);
    }
  }
  
  @RequiresApi(19)
  static class MediaSessionImplApi19
    extends MediaSessionCompat.MediaSessionImplApi18
  {
    MediaSessionImplApi19(Context paramContext, String paramString, ComponentName paramComponentName, PendingIntent paramPendingIntent)
    {
      super(paramString, paramComponentName, paramPendingIntent);
    }
    
    RemoteControlClient.MetadataEditor buildRccMetadata(Bundle paramBundle)
    {
      RemoteControlClient.MetadataEditor localMetadataEditor = super.buildRccMetadata(paramBundle);
      long l;
      if (mState == null) {
        l = 0L;
      } else {
        l = mState.getActions();
      }
      if ((l & 0x80) != 0L) {
        localMetadataEditor.addEditableKey(268435457);
      }
      if (paramBundle == null) {
        return localMetadataEditor;
      }
      if (paramBundle.containsKey("android.media.metadata.YEAR")) {
        localMetadataEditor.putLong(8, paramBundle.getLong("android.media.metadata.YEAR"));
      }
      if (paramBundle.containsKey("android.media.metadata.RATING"))
      {
        Parcelable localParcelable = paramBundle.getParcelable("android.media.metadata.RATING");
        ((MediaMetadataEditor)localMetadataEditor).putObject(101, localParcelable);
      }
      if (paramBundle.containsKey("android.media.metadata.USER_RATING"))
      {
        paramBundle = paramBundle.getParcelable("android.media.metadata.USER_RATING");
        ((MediaMetadataEditor)localMetadataEditor).putObject(268435457, paramBundle);
      }
      return localMetadataEditor;
    }
    
    int getRccTransportControlFlagsFromActions(long paramLong)
    {
      int j = super.getRccTransportControlFlagsFromActions(paramLong);
      int i = j;
      if ((paramLong & 0x80) != 0L) {
        i = j | 0x200;
      }
      return i;
    }
    
    public void setCallback(MediaSessionCompat.Callback paramCallback, Handler paramHandler)
    {
      super.setCallback(paramCallback, paramHandler);
      if (paramCallback == null)
      {
        mRcc.setMetadataUpdateListener(null);
        return;
      }
      paramCallback = new RemoteControlClient.OnMetadataUpdateListener()
      {
        public void onMetadataUpdate(int paramAnonymousInt, Object paramAnonymousObject)
        {
          if ((paramAnonymousInt == 268435457) && ((paramAnonymousObject instanceof Rating))) {
            postToHandler(19, RatingCompat.fromRating(paramAnonymousObject));
          }
        }
      };
      mRcc.setMetadataUpdateListener(paramCallback);
    }
  }
  
  @RequiresApi(21)
  static class MediaSessionImplApi21
    implements MediaSessionCompat.MediaSessionImpl
  {
    boolean mCaptioningEnabled;
    private boolean mDestroyed = false;
    private final RemoteCallbackList<IMediaControllerCallback> mExtraControllerCallbacks = new RemoteCallbackList();
    private MediaMetadataCompat mMetadata;
    private PlaybackStateCompat mPlaybackState;
    private List<MediaSessionCompat.QueueItem> mQueue;
    int mRatingType;
    int mRepeatMode;
    private final Object mSessionObj;
    int mShuffleMode;
    boolean mShuffleModeEnabled;
    private final MediaSessionCompat.Token mToken;
    
    public MediaSessionImplApi21(Context paramContext, String paramString)
    {
      mSessionObj = MediaSessionCompatApi21.createSession(paramContext, paramString);
      mToken = new MediaSessionCompat.Token(MediaSessionCompatApi21.getSessionToken(mSessionObj), new ExtraSession());
    }
    
    public MediaSessionImplApi21(Object paramObject)
    {
      mSessionObj = MediaSessionCompatApi21.verifySession(paramObject);
      mToken = new MediaSessionCompat.Token(MediaSessionCompatApi21.getSessionToken(mSessionObj), new ExtraSession());
    }
    
    public String getCallingPackage()
    {
      if (Build.VERSION.SDK_INT < 24) {
        return null;
      }
      return MediaSessionCompatApi24.getCallingPackage(mSessionObj);
    }
    
    public Object getMediaSession()
    {
      return mSessionObj;
    }
    
    public PlaybackStateCompat getPlaybackState()
    {
      return mPlaybackState;
    }
    
    public Object getRemoteControlClient()
    {
      return null;
    }
    
    public MediaSessionCompat.Token getSessionToken()
    {
      return mToken;
    }
    
    public boolean isActive()
    {
      return MediaSessionCompatApi21.isActive(mSessionObj);
    }
    
    public void release()
    {
      mDestroyed = true;
      MediaSessionCompatApi21.release(mSessionObj);
    }
    
    public void sendSessionEvent(String paramString, Bundle paramBundle)
    {
      int i;
      if (Build.VERSION.SDK_INT < 23) {
        i = mExtraControllerCallbacks.beginBroadcast() - 1;
      }
      for (;;)
      {
        IMediaControllerCallback localIMediaControllerCallback;
        if (i >= 0) {
          localIMediaControllerCallback = (IMediaControllerCallback)mExtraControllerCallbacks.getBroadcastItem(i);
        }
        try
        {
          localIMediaControllerCallback.onEvent(paramString, paramBundle);
          i -= 1;
          continue;
          mExtraControllerCallbacks.finishBroadcast();
          MediaSessionCompatApi21.sendSessionEvent(mSessionObj, paramString, paramBundle);
          return;
        }
        catch (RemoteException localRemoteException)
        {
          for (;;) {}
        }
      }
    }
    
    public void setActive(boolean paramBoolean)
    {
      MediaSessionCompatApi21.setActive(mSessionObj, paramBoolean);
    }
    
    public void setCallback(MediaSessionCompat.Callback paramCallback, Handler paramHandler)
    {
      Object localObject2 = mSessionObj;
      Object localObject1;
      if (paramCallback == null) {
        localObject1 = null;
      } else {
        localObject1 = mCallbackObj;
      }
      MediaSessionCompatApi21.setCallback(localObject2, localObject1, paramHandler);
      if (paramCallback != null) {
        paramCallback.setSessionImpl(this, paramHandler);
      }
    }
    
    public void setCaptioningEnabled(boolean paramBoolean)
    {
      if (mCaptioningEnabled != paramBoolean)
      {
        mCaptioningEnabled = paramBoolean;
        int i = mExtraControllerCallbacks.beginBroadcast() - 1;
        for (;;)
        {
          IMediaControllerCallback localIMediaControllerCallback;
          if (i >= 0) {
            localIMediaControllerCallback = (IMediaControllerCallback)mExtraControllerCallbacks.getBroadcastItem(i);
          }
          try
          {
            localIMediaControllerCallback.onCaptioningEnabledChanged(paramBoolean);
            i -= 1;
            continue;
            mExtraControllerCallbacks.finishBroadcast();
            return;
          }
          catch (RemoteException localRemoteException)
          {
            for (;;) {}
          }
        }
      }
    }
    
    public void setExtras(Bundle paramBundle)
    {
      MediaSessionCompatApi21.setExtras(mSessionObj, paramBundle);
    }
    
    public void setFlags(int paramInt)
    {
      MediaSessionCompatApi21.setFlags(mSessionObj, paramInt);
    }
    
    public void setMediaButtonReceiver(PendingIntent paramPendingIntent)
    {
      MediaSessionCompatApi21.setMediaButtonReceiver(mSessionObj, paramPendingIntent);
    }
    
    public void setMetadata(MediaMetadataCompat paramMediaMetadataCompat)
    {
      mMetadata = paramMediaMetadataCompat;
      Object localObject = mSessionObj;
      if (paramMediaMetadataCompat == null) {
        paramMediaMetadataCompat = null;
      } else {
        paramMediaMetadataCompat = paramMediaMetadataCompat.getMediaMetadata();
      }
      MediaSessionCompatApi21.setMetadata(localObject, paramMediaMetadataCompat);
    }
    
    public void setPlaybackState(PlaybackStateCompat paramPlaybackStateCompat)
    {
      mPlaybackState = paramPlaybackStateCompat;
      int i = mExtraControllerCallbacks.beginBroadcast() - 1;
      for (;;)
      {
        Object localObject;
        if (i >= 0) {
          localObject = (IMediaControllerCallback)mExtraControllerCallbacks.getBroadcastItem(i);
        }
        try
        {
          ((IMediaControllerCallback)localObject).onPlaybackStateChanged(paramPlaybackStateCompat);
          i -= 1;
          continue;
          mExtraControllerCallbacks.finishBroadcast();
          localObject = mSessionObj;
          if (paramPlaybackStateCompat == null) {
            paramPlaybackStateCompat = null;
          } else {
            paramPlaybackStateCompat = paramPlaybackStateCompat.getPlaybackState();
          }
          MediaSessionCompatApi21.setPlaybackState(localObject, paramPlaybackStateCompat);
          return;
        }
        catch (RemoteException localRemoteException)
        {
          for (;;) {}
        }
      }
    }
    
    public void setPlaybackToLocal(int paramInt)
    {
      MediaSessionCompatApi21.setPlaybackToLocal(mSessionObj, paramInt);
    }
    
    public void setPlaybackToRemote(VolumeProviderCompat paramVolumeProviderCompat)
    {
      MediaSessionCompatApi21.setPlaybackToRemote(mSessionObj, paramVolumeProviderCompat.getVolumeProvider());
    }
    
    public void setQueue(List paramList)
    {
      mQueue = paramList;
      if (paramList != null)
      {
        ArrayList localArrayList = new ArrayList();
        Iterator localIterator = paramList.iterator();
        for (;;)
        {
          paramList = localArrayList;
          if (!localIterator.hasNext()) {
            break;
          }
          localArrayList.add(((MediaSessionCompat.QueueItem)localIterator.next()).getQueueItem());
        }
      }
      paramList = null;
      MediaSessionCompatApi21.setQueue(mSessionObj, paramList);
    }
    
    public void setQueueTitle(CharSequence paramCharSequence)
    {
      MediaSessionCompatApi21.setQueueTitle(mSessionObj, paramCharSequence);
    }
    
    public void setRatingType(int paramInt)
    {
      if (Build.VERSION.SDK_INT < 22)
      {
        mRatingType = paramInt;
        return;
      }
      MediaSessionCompatApi22.setRatingType(mSessionObj, paramInt);
    }
    
    public void setRepeatMode(int paramInt)
    {
      if (mRepeatMode != paramInt)
      {
        mRepeatMode = paramInt;
        int i = mExtraControllerCallbacks.beginBroadcast() - 1;
        for (;;)
        {
          IMediaControllerCallback localIMediaControllerCallback;
          if (i >= 0) {
            localIMediaControllerCallback = (IMediaControllerCallback)mExtraControllerCallbacks.getBroadcastItem(i);
          }
          try
          {
            localIMediaControllerCallback.onRepeatModeChanged(paramInt);
            i -= 1;
            continue;
            mExtraControllerCallbacks.finishBroadcast();
            return;
          }
          catch (RemoteException localRemoteException)
          {
            for (;;) {}
          }
        }
      }
    }
    
    public void setSessionActivity(PendingIntent paramPendingIntent)
    {
      MediaSessionCompatApi21.setSessionActivity(mSessionObj, paramPendingIntent);
    }
    
    public void setShuffleMode(int paramInt)
    {
      if (mShuffleMode != paramInt)
      {
        mShuffleMode = paramInt;
        int i = mExtraControllerCallbacks.beginBroadcast() - 1;
        for (;;)
        {
          IMediaControllerCallback localIMediaControllerCallback;
          if (i >= 0) {
            localIMediaControllerCallback = (IMediaControllerCallback)mExtraControllerCallbacks.getBroadcastItem(i);
          }
          try
          {
            localIMediaControllerCallback.onShuffleModeChanged(paramInt);
            i -= 1;
            continue;
            mExtraControllerCallbacks.finishBroadcast();
            return;
          }
          catch (RemoteException localRemoteException)
          {
            for (;;) {}
          }
        }
      }
    }
    
    public void setShuffleModeEnabled(boolean paramBoolean)
    {
      if (mShuffleModeEnabled != paramBoolean)
      {
        mShuffleModeEnabled = paramBoolean;
        int i = mExtraControllerCallbacks.beginBroadcast() - 1;
        for (;;)
        {
          IMediaControllerCallback localIMediaControllerCallback;
          if (i >= 0) {
            localIMediaControllerCallback = (IMediaControllerCallback)mExtraControllerCallbacks.getBroadcastItem(i);
          }
          try
          {
            localIMediaControllerCallback.onShuffleModeChangedDeprecated(paramBoolean);
            i -= 1;
            continue;
            mExtraControllerCallbacks.finishBroadcast();
            return;
          }
          catch (RemoteException localRemoteException)
          {
            for (;;) {}
          }
        }
      }
    }
    
    class ExtraSession
      extends IMediaSession.Stub
    {
      ExtraSession() {}
      
      public void addQueueItem(MediaDescriptionCompat paramMediaDescriptionCompat)
      {
        throw new AssertionError();
      }
      
      public void addQueueItemAt(MediaDescriptionCompat paramMediaDescriptionCompat, int paramInt)
      {
        throw new AssertionError();
      }
      
      public void adjustVolume(int paramInt1, int paramInt2, String paramString)
      {
        throw new AssertionError();
      }
      
      public void fastForward()
        throws RemoteException
      {
        throw new AssertionError();
      }
      
      public Bundle getExtras()
      {
        throw new AssertionError();
      }
      
      public long getFlags()
      {
        throw new AssertionError();
      }
      
      public PendingIntent getLaunchPendingIntent()
      {
        throw new AssertionError();
      }
      
      public MediaMetadataCompat getMetadata()
      {
        throw new AssertionError();
      }
      
      public String getPackageName()
      {
        throw new AssertionError();
      }
      
      public PlaybackStateCompat getPlaybackState()
      {
        return MediaSessionCompat.getStateWithUpdatedPosition(mPlaybackState, mMetadata);
      }
      
      public List getQueue()
      {
        return null;
      }
      
      public CharSequence getQueueTitle()
      {
        throw new AssertionError();
      }
      
      public int getRatingType()
      {
        return mRatingType;
      }
      
      public int getRepeatMode()
      {
        return mRepeatMode;
      }
      
      public int getShuffleMode()
      {
        return mShuffleMode;
      }
      
      public String getTag()
      {
        throw new AssertionError();
      }
      
      public ParcelableVolumeInfo getVolumeAttributes()
      {
        throw new AssertionError();
      }
      
      public boolean isCaptioningEnabled()
      {
        return mCaptioningEnabled;
      }
      
      public boolean isShuffleModeEnabledDeprecated()
      {
        return mShuffleModeEnabled;
      }
      
      public boolean isTransportControlEnabled()
      {
        throw new AssertionError();
      }
      
      public void next()
        throws RemoteException
      {
        throw new AssertionError();
      }
      
      public void pause()
        throws RemoteException
      {
        throw new AssertionError();
      }
      
      public void play()
        throws RemoteException
      {
        throw new AssertionError();
      }
      
      public void playFromMediaId(String paramString, Bundle paramBundle)
        throws RemoteException
      {
        throw new AssertionError();
      }
      
      public void playFromSearch(String paramString, Bundle paramBundle)
        throws RemoteException
      {
        throw new AssertionError();
      }
      
      public void playFromUri(Uri paramUri, Bundle paramBundle)
        throws RemoteException
      {
        throw new AssertionError();
      }
      
      public void prepare()
        throws RemoteException
      {
        throw new AssertionError();
      }
      
      public void prepareFromMediaId(String paramString, Bundle paramBundle)
        throws RemoteException
      {
        throw new AssertionError();
      }
      
      public void prepareFromSearch(String paramString, Bundle paramBundle)
        throws RemoteException
      {
        throw new AssertionError();
      }
      
      public void prepareFromUri(Uri paramUri, Bundle paramBundle)
        throws RemoteException
      {
        throw new AssertionError();
      }
      
      public void previous()
        throws RemoteException
      {
        throw new AssertionError();
      }
      
      public void rate(RatingCompat paramRatingCompat)
        throws RemoteException
      {
        throw new AssertionError();
      }
      
      public void rateWithExtras(RatingCompat paramRatingCompat, Bundle paramBundle)
        throws RemoteException
      {
        throw new AssertionError();
      }
      
      public void registerCallbackListener(IMediaControllerCallback paramIMediaControllerCallback)
      {
        if (!mDestroyed) {
          mExtraControllerCallbacks.register(paramIMediaControllerCallback);
        }
      }
      
      public void removeQueueItem(MediaDescriptionCompat paramMediaDescriptionCompat)
      {
        throw new AssertionError();
      }
      
      public void removeQueueItemAt(int paramInt)
      {
        throw new AssertionError();
      }
      
      public void rewind()
        throws RemoteException
      {
        throw new AssertionError();
      }
      
      public void seekTo(long paramLong)
        throws RemoteException
      {
        throw new AssertionError();
      }
      
      public void sendCommand(String paramString, Bundle paramBundle, MediaSessionCompat.ResultReceiverWrapper paramResultReceiverWrapper)
      {
        throw new AssertionError();
      }
      
      public void sendCustomAction(String paramString, Bundle paramBundle)
        throws RemoteException
      {
        throw new AssertionError();
      }
      
      public boolean sendMediaButton(KeyEvent paramKeyEvent)
      {
        throw new AssertionError();
      }
      
      public void setCaptioningEnabled(boolean paramBoolean)
        throws RemoteException
      {
        throw new AssertionError();
      }
      
      public void setRepeatMode(int paramInt)
        throws RemoteException
      {
        throw new AssertionError();
      }
      
      public void setShuffleMode(int paramInt)
        throws RemoteException
      {
        throw new AssertionError();
      }
      
      public void setShuffleModeEnabledDeprecated(boolean paramBoolean)
        throws RemoteException
      {
        throw new AssertionError();
      }
      
      public void setVolumeTo(int paramInt1, int paramInt2, String paramString)
      {
        throw new AssertionError();
      }
      
      public void skipToQueueItem(long paramLong)
      {
        throw new AssertionError();
      }
      
      public void stop()
        throws RemoteException
      {
        throw new AssertionError();
      }
      
      public void unregisterCallbackListener(IMediaControllerCallback paramIMediaControllerCallback)
      {
        mExtraControllerCallbacks.unregister(paramIMediaControllerCallback);
      }
    }
  }
  
  static class MediaSessionImplBase
    implements MediaSessionCompat.MediaSessionImpl
  {
    static final int RCC_PLAYSTATE_NONE = 0;
    final AudioManager mAudioManager;
    volatile MediaSessionCompat.Callback mCallback;
    boolean mCaptioningEnabled;
    private final Context mContext;
    final RemoteCallbackList<IMediaControllerCallback> mControllerCallbacks = new RemoteCallbackList();
    boolean mDestroyed = false;
    Bundle mExtras;
    int mFlags;
    private MessageHandler mHandler;
    boolean mIsActive = false;
    private boolean mIsMbrRegistered = false;
    private boolean mIsRccRegistered = false;
    int mLocalStream;
    final Object mLock = new Object();
    private final ComponentName mMediaButtonReceiverComponentName;
    private final PendingIntent mMediaButtonReceiverIntent;
    MediaMetadataCompat mMetadata;
    final String mPackageName;
    List<MediaSessionCompat.QueueItem> mQueue;
    CharSequence mQueueTitle;
    int mRatingType;
    final RemoteControlClient mRcc;
    int mRepeatMode;
    PendingIntent mSessionActivity;
    int mShuffleMode;
    boolean mShuffleModeEnabled;
    PlaybackStateCompat mState;
    private final MediaSessionStub mStub;
    final String mTag;
    private final MediaSessionCompat.Token mToken;
    private VolumeProviderCompat.Callback mVolumeCallback = new VolumeProviderCompat.Callback()
    {
      public void onVolumeChanged(VolumeProviderCompat paramAnonymousVolumeProviderCompat)
      {
        if (mVolumeProvider != paramAnonymousVolumeProviderCompat) {
          return;
        }
        paramAnonymousVolumeProviderCompat = new ParcelableVolumeInfo(mVolumeType, mLocalStream, paramAnonymousVolumeProviderCompat.getVolumeControl(), paramAnonymousVolumeProviderCompat.getMaxVolume(), paramAnonymousVolumeProviderCompat.getCurrentVolume());
        sendVolumeInfoChanged(paramAnonymousVolumeProviderCompat);
      }
    };
    VolumeProviderCompat mVolumeProvider;
    int mVolumeType;
    
    public MediaSessionImplBase(Context paramContext, String paramString, ComponentName paramComponentName, PendingIntent paramPendingIntent)
    {
      if (paramComponentName != null)
      {
        mContext = paramContext;
        mPackageName = paramContext.getPackageName();
        mAudioManager = ((AudioManager)paramContext.getSystemService("audio"));
        mTag = paramString;
        mMediaButtonReceiverComponentName = paramComponentName;
        mMediaButtonReceiverIntent = paramPendingIntent;
        mStub = new MediaSessionStub();
        mToken = new MediaSessionCompat.Token(mStub);
        mRatingType = 0;
        mVolumeType = 1;
        mLocalStream = 3;
        mRcc = new RemoteControlClient(paramPendingIntent);
        return;
      }
      throw new IllegalArgumentException("MediaButtonReceiver component may not be null.");
    }
    
    private void sendCaptioningEnabled(boolean paramBoolean)
    {
      int i = mControllerCallbacks.beginBroadcast() - 1;
      for (;;)
      {
        IMediaControllerCallback localIMediaControllerCallback;
        if (i >= 0) {
          localIMediaControllerCallback = (IMediaControllerCallback)mControllerCallbacks.getBroadcastItem(i);
        }
        try
        {
          localIMediaControllerCallback.onCaptioningEnabledChanged(paramBoolean);
          i -= 1;
          continue;
          mControllerCallbacks.finishBroadcast();
          return;
        }
        catch (RemoteException localRemoteException)
        {
          for (;;) {}
        }
      }
    }
    
    private void sendEvent(String paramString, Bundle paramBundle)
    {
      int i = mControllerCallbacks.beginBroadcast() - 1;
      for (;;)
      {
        IMediaControllerCallback localIMediaControllerCallback;
        if (i >= 0) {
          localIMediaControllerCallback = (IMediaControllerCallback)mControllerCallbacks.getBroadcastItem(i);
        }
        try
        {
          localIMediaControllerCallback.onEvent(paramString, paramBundle);
          i -= 1;
          continue;
          mControllerCallbacks.finishBroadcast();
          return;
        }
        catch (RemoteException localRemoteException)
        {
          for (;;) {}
        }
      }
    }
    
    private void sendExtras(Bundle paramBundle)
    {
      int i = mControllerCallbacks.beginBroadcast() - 1;
      for (;;)
      {
        IMediaControllerCallback localIMediaControllerCallback;
        if (i >= 0) {
          localIMediaControllerCallback = (IMediaControllerCallback)mControllerCallbacks.getBroadcastItem(i);
        }
        try
        {
          localIMediaControllerCallback.onExtrasChanged(paramBundle);
          i -= 1;
          continue;
          mControllerCallbacks.finishBroadcast();
          return;
        }
        catch (RemoteException localRemoteException)
        {
          for (;;) {}
        }
      }
    }
    
    private void sendMetadata(MediaMetadataCompat paramMediaMetadataCompat)
    {
      int i = mControllerCallbacks.beginBroadcast() - 1;
      for (;;)
      {
        IMediaControllerCallback localIMediaControllerCallback;
        if (i >= 0) {
          localIMediaControllerCallback = (IMediaControllerCallback)mControllerCallbacks.getBroadcastItem(i);
        }
        try
        {
          localIMediaControllerCallback.onMetadataChanged(paramMediaMetadataCompat);
          i -= 1;
          continue;
          mControllerCallbacks.finishBroadcast();
          return;
        }
        catch (RemoteException localRemoteException)
        {
          for (;;) {}
        }
      }
    }
    
    private void sendQueue(List paramList)
    {
      int i = mControllerCallbacks.beginBroadcast() - 1;
      for (;;)
      {
        IMediaControllerCallback localIMediaControllerCallback;
        if (i >= 0) {
          localIMediaControllerCallback = (IMediaControllerCallback)mControllerCallbacks.getBroadcastItem(i);
        }
        try
        {
          localIMediaControllerCallback.onQueueChanged(paramList);
          i -= 1;
          continue;
          mControllerCallbacks.finishBroadcast();
          return;
        }
        catch (RemoteException localRemoteException)
        {
          for (;;) {}
        }
      }
    }
    
    private void sendQueueTitle(CharSequence paramCharSequence)
    {
      int i = mControllerCallbacks.beginBroadcast() - 1;
      for (;;)
      {
        IMediaControllerCallback localIMediaControllerCallback;
        if (i >= 0) {
          localIMediaControllerCallback = (IMediaControllerCallback)mControllerCallbacks.getBroadcastItem(i);
        }
        try
        {
          localIMediaControllerCallback.onQueueTitleChanged(paramCharSequence);
          i -= 1;
          continue;
          mControllerCallbacks.finishBroadcast();
          return;
        }
        catch (RemoteException localRemoteException)
        {
          for (;;) {}
        }
      }
    }
    
    private void sendRepeatMode(int paramInt)
    {
      int i = mControllerCallbacks.beginBroadcast() - 1;
      for (;;)
      {
        IMediaControllerCallback localIMediaControllerCallback;
        if (i >= 0) {
          localIMediaControllerCallback = (IMediaControllerCallback)mControllerCallbacks.getBroadcastItem(i);
        }
        try
        {
          localIMediaControllerCallback.onRepeatModeChanged(paramInt);
          i -= 1;
          continue;
          mControllerCallbacks.finishBroadcast();
          return;
        }
        catch (RemoteException localRemoteException)
        {
          for (;;) {}
        }
      }
    }
    
    private void sendSessionDestroyed()
    {
      int i = mControllerCallbacks.beginBroadcast() - 1;
      for (;;)
      {
        IMediaControllerCallback localIMediaControllerCallback;
        if (i >= 0) {
          localIMediaControllerCallback = (IMediaControllerCallback)mControllerCallbacks.getBroadcastItem(i);
        }
        try
        {
          localIMediaControllerCallback.onSessionDestroyed();
          i -= 1;
          continue;
          mControllerCallbacks.finishBroadcast();
          mControllerCallbacks.kill();
          return;
        }
        catch (RemoteException localRemoteException)
        {
          for (;;) {}
        }
      }
    }
    
    private void sendShuffleMode(int paramInt)
    {
      int i = mControllerCallbacks.beginBroadcast() - 1;
      for (;;)
      {
        IMediaControllerCallback localIMediaControllerCallback;
        if (i >= 0) {
          localIMediaControllerCallback = (IMediaControllerCallback)mControllerCallbacks.getBroadcastItem(i);
        }
        try
        {
          localIMediaControllerCallback.onShuffleModeChanged(paramInt);
          i -= 1;
          continue;
          mControllerCallbacks.finishBroadcast();
          return;
        }
        catch (RemoteException localRemoteException)
        {
          for (;;) {}
        }
      }
    }
    
    private void sendShuffleModeEnabled(boolean paramBoolean)
    {
      int i = mControllerCallbacks.beginBroadcast() - 1;
      for (;;)
      {
        IMediaControllerCallback localIMediaControllerCallback;
        if (i >= 0) {
          localIMediaControllerCallback = (IMediaControllerCallback)mControllerCallbacks.getBroadcastItem(i);
        }
        try
        {
          localIMediaControllerCallback.onShuffleModeChangedDeprecated(paramBoolean);
          i -= 1;
          continue;
          mControllerCallbacks.finishBroadcast();
          return;
        }
        catch (RemoteException localRemoteException)
        {
          for (;;) {}
        }
      }
    }
    
    private void sendState(PlaybackStateCompat paramPlaybackStateCompat)
    {
      int i = mControllerCallbacks.beginBroadcast() - 1;
      for (;;)
      {
        IMediaControllerCallback localIMediaControllerCallback;
        if (i >= 0) {
          localIMediaControllerCallback = (IMediaControllerCallback)mControllerCallbacks.getBroadcastItem(i);
        }
        try
        {
          localIMediaControllerCallback.onPlaybackStateChanged(paramPlaybackStateCompat);
          i -= 1;
          continue;
          mControllerCallbacks.finishBroadcast();
          return;
        }
        catch (RemoteException localRemoteException)
        {
          for (;;) {}
        }
      }
    }
    
    void adjustVolume(int paramInt1, int paramInt2)
    {
      if (mVolumeType == 2)
      {
        if (mVolumeProvider != null) {
          mVolumeProvider.onAdjustVolume(paramInt1);
        }
      }
      else {
        mAudioManager.adjustStreamVolume(mLocalStream, paramInt1, paramInt2);
      }
    }
    
    RemoteControlClient.MetadataEditor buildRccMetadata(Bundle paramBundle)
    {
      RemoteControlClient.MetadataEditor localMetadataEditor = mRcc.editMetadata(true);
      if (paramBundle == null) {
        return localMetadataEditor;
      }
      Bitmap localBitmap2;
      Bitmap localBitmap1;
      if (paramBundle.containsKey("android.media.metadata.ART"))
      {
        localBitmap2 = (Bitmap)paramBundle.getParcelable("android.media.metadata.ART");
        localBitmap1 = localBitmap2;
        if (localBitmap2 != null) {
          localBitmap1 = localBitmap2.copy(localBitmap2.getConfig(), false);
        }
        localMetadataEditor.putBitmap(100, localBitmap1);
      }
      else if (paramBundle.containsKey("android.media.metadata.ALBUM_ART"))
      {
        localBitmap2 = (Bitmap)paramBundle.getParcelable("android.media.metadata.ALBUM_ART");
        localBitmap1 = localBitmap2;
        if (localBitmap2 != null) {
          localBitmap1 = localBitmap2.copy(localBitmap2.getConfig(), false);
        }
        localMetadataEditor.putBitmap(100, localBitmap1);
      }
      if (paramBundle.containsKey("android.media.metadata.ALBUM")) {
        localMetadataEditor.putString(1, paramBundle.getString("android.media.metadata.ALBUM"));
      }
      if (paramBundle.containsKey("android.media.metadata.ALBUM_ARTIST")) {
        localMetadataEditor.putString(13, paramBundle.getString("android.media.metadata.ALBUM_ARTIST"));
      }
      if (paramBundle.containsKey("android.media.metadata.ARTIST")) {
        localMetadataEditor.putString(2, paramBundle.getString("android.media.metadata.ARTIST"));
      }
      if (paramBundle.containsKey("android.media.metadata.AUTHOR")) {
        localMetadataEditor.putString(3, paramBundle.getString("android.media.metadata.AUTHOR"));
      }
      if (paramBundle.containsKey("android.media.metadata.COMPILATION")) {
        localMetadataEditor.putString(15, paramBundle.getString("android.media.metadata.COMPILATION"));
      }
      if (paramBundle.containsKey("android.media.metadata.COMPOSER")) {
        localMetadataEditor.putString(4, paramBundle.getString("android.media.metadata.COMPOSER"));
      }
      if (paramBundle.containsKey("android.media.metadata.DATE")) {
        localMetadataEditor.putString(5, paramBundle.getString("android.media.metadata.DATE"));
      }
      if (paramBundle.containsKey("android.media.metadata.DISC_NUMBER")) {
        localMetadataEditor.putLong(14, paramBundle.getLong("android.media.metadata.DISC_NUMBER"));
      }
      if (paramBundle.containsKey("android.media.metadata.DURATION")) {
        localMetadataEditor.putLong(9, paramBundle.getLong("android.media.metadata.DURATION"));
      }
      if (paramBundle.containsKey("android.media.metadata.GENRE")) {
        localMetadataEditor.putString(6, paramBundle.getString("android.media.metadata.GENRE"));
      }
      if (paramBundle.containsKey("android.media.metadata.TITLE")) {
        localMetadataEditor.putString(7, paramBundle.getString("android.media.metadata.TITLE"));
      }
      if (paramBundle.containsKey("android.media.metadata.TRACK_NUMBER")) {
        localMetadataEditor.putLong(0, paramBundle.getLong("android.media.metadata.TRACK_NUMBER"));
      }
      if (paramBundle.containsKey("android.media.metadata.WRITER")) {
        localMetadataEditor.putString(11, paramBundle.getString("android.media.metadata.WRITER"));
      }
      return localMetadataEditor;
    }
    
    public String getCallingPackage()
    {
      return null;
    }
    
    public Object getMediaSession()
    {
      return null;
    }
    
    public PlaybackStateCompat getPlaybackState()
    {
      Object localObject = mLock;
      try
      {
        PlaybackStateCompat localPlaybackStateCompat = mState;
        return localPlaybackStateCompat;
      }
      catch (Throwable localThrowable)
      {
        throw localThrowable;
      }
    }
    
    int getRccStateFromState(int paramInt)
    {
      switch (paramInt)
      {
      default: 
        return -1;
      case 10: 
      case 11: 
        return 6;
      case 9: 
        return 7;
      case 7: 
        return 9;
      case 6: 
      case 8: 
        return 8;
      case 5: 
        return 5;
      case 4: 
        return 4;
      case 3: 
        return 3;
      case 2: 
        return 2;
      case 1: 
        return 1;
      }
      return 0;
    }
    
    int getRccTransportControlFlagsFromActions(long paramLong)
    {
      if ((1L & paramLong) != 0L) {
        j = 32;
      } else {
        j = 0;
      }
      int i = j;
      if ((0x2 & paramLong) != 0L) {
        i = j | 0x10;
      }
      int j = i;
      if ((0x4 & paramLong) != 0L) {
        j = i | 0x4;
      }
      i = j;
      if ((0x8 & paramLong) != 0L) {
        i = j | 0x2;
      }
      j = i;
      if ((0x10 & paramLong) != 0L) {
        j = i | 0x1;
      }
      i = j;
      if ((0x20 & paramLong) != 0L) {
        i = j | 0x80;
      }
      j = i;
      if ((0x40 & paramLong) != 0L) {
        j = i | 0x40;
      }
      i = j;
      if ((paramLong & 0x200) != 0L) {
        i = j | 0x8;
      }
      return i;
    }
    
    public Object getRemoteControlClient()
    {
      return null;
    }
    
    public MediaSessionCompat.Token getSessionToken()
    {
      return mToken;
    }
    
    public boolean isActive()
    {
      return mIsActive;
    }
    
    void postToHandler(int paramInt)
    {
      postToHandler(paramInt, null);
    }
    
    void postToHandler(int paramInt1, int paramInt2)
    {
      postToHandler(paramInt1, null, paramInt2);
    }
    
    void postToHandler(int paramInt, Object paramObject)
    {
      postToHandler(paramInt, paramObject, null);
    }
    
    void postToHandler(int paramInt1, Object paramObject, int paramInt2)
    {
      Object localObject = mLock;
      try
      {
        if (mHandler != null) {
          mHandler.post(paramInt1, paramObject, paramInt2);
        }
        return;
      }
      catch (Throwable paramObject)
      {
        throw paramObject;
      }
    }
    
    void postToHandler(int paramInt, Object paramObject, Bundle paramBundle)
    {
      Object localObject = mLock;
      try
      {
        if (mHandler != null) {
          mHandler.post(paramInt, paramObject, paramBundle);
        }
        return;
      }
      catch (Throwable paramObject)
      {
        throw paramObject;
      }
    }
    
    void registerMediaButtonEventReceiver(PendingIntent paramPendingIntent, ComponentName paramComponentName)
    {
      mAudioManager.registerMediaButtonEventReceiver(paramComponentName);
    }
    
    public void release()
    {
      mIsActive = false;
      mDestroyed = true;
      update();
      sendSessionDestroyed();
    }
    
    public void sendSessionEvent(String paramString, Bundle paramBundle)
    {
      sendEvent(paramString, paramBundle);
    }
    
    void sendVolumeInfoChanged(ParcelableVolumeInfo paramParcelableVolumeInfo)
    {
      int i = mControllerCallbacks.beginBroadcast() - 1;
      for (;;)
      {
        IMediaControllerCallback localIMediaControllerCallback;
        if (i >= 0) {
          localIMediaControllerCallback = (IMediaControllerCallback)mControllerCallbacks.getBroadcastItem(i);
        }
        try
        {
          localIMediaControllerCallback.onVolumeInfoChanged(paramParcelableVolumeInfo);
          i -= 1;
          continue;
          mControllerCallbacks.finishBroadcast();
          return;
        }
        catch (RemoteException localRemoteException)
        {
          for (;;) {}
        }
      }
    }
    
    public void setActive(boolean paramBoolean)
    {
      if (paramBoolean == mIsActive) {
        return;
      }
      mIsActive = paramBoolean;
      if (update())
      {
        setMetadata(mMetadata);
        setPlaybackState(mState);
      }
    }
    
    public void setCallback(MediaSessionCompat.Callback paramCallback, Handler paramHandler)
    {
      mCallback = paramCallback;
      if (paramCallback != null)
      {
        paramCallback = paramHandler;
        if (paramHandler == null) {
          paramCallback = new Handler();
        }
        paramHandler = mLock;
        try
        {
          if (mHandler != null) {
            mHandler.removeCallbacksAndMessages(null);
          }
          mHandler = new MessageHandler(paramCallback.getLooper());
          mCallback.setSessionImpl(this, paramCallback);
          return;
        }
        catch (Throwable paramCallback)
        {
          throw paramCallback;
        }
      }
    }
    
    public void setCaptioningEnabled(boolean paramBoolean)
    {
      if (mCaptioningEnabled != paramBoolean)
      {
        mCaptioningEnabled = paramBoolean;
        sendCaptioningEnabled(paramBoolean);
      }
    }
    
    public void setExtras(Bundle paramBundle)
    {
      mExtras = paramBundle;
      sendExtras(paramBundle);
    }
    
    public void setFlags(int paramInt)
    {
      Object localObject = mLock;
      try
      {
        mFlags = paramInt;
        update();
        return;
      }
      catch (Throwable localThrowable)
      {
        throw localThrowable;
      }
    }
    
    public void setMediaButtonReceiver(PendingIntent paramPendingIntent) {}
    
    public void setMetadata(MediaMetadataCompat paramMediaMetadataCompat)
    {
      MediaMetadataCompat localMediaMetadataCompat = paramMediaMetadataCompat;
      if (paramMediaMetadataCompat != null) {
        localMediaMetadataCompat = new MediaMetadataCompat.Builder(paramMediaMetadataCompat, MediaSessionCompat.sMaxBitmapSize).build();
      }
      paramMediaMetadataCompat = mLock;
      try
      {
        mMetadata = localMediaMetadataCompat;
        sendMetadata(localMediaMetadataCompat);
        if (!mIsActive) {
          return;
        }
        if (localMediaMetadataCompat == null) {
          paramMediaMetadataCompat = null;
        } else {
          paramMediaMetadataCompat = localMediaMetadataCompat.getBundle();
        }
        buildRccMetadata(paramMediaMetadataCompat).apply();
        return;
      }
      catch (Throwable localThrowable)
      {
        throw localThrowable;
      }
    }
    
    public void setPlaybackState(PlaybackStateCompat paramPlaybackStateCompat)
    {
      Object localObject = mLock;
      try
      {
        mState = paramPlaybackStateCompat;
        sendState(paramPlaybackStateCompat);
        if (!mIsActive) {
          return;
        }
        if (paramPlaybackStateCompat == null)
        {
          mRcc.setPlaybackState(0);
          mRcc.setTransportControlFlags(0);
          return;
        }
        setRccState(paramPlaybackStateCompat);
        mRcc.setTransportControlFlags(getRccTransportControlFlagsFromActions(paramPlaybackStateCompat.getActions()));
        return;
      }
      catch (Throwable paramPlaybackStateCompat)
      {
        throw paramPlaybackStateCompat;
      }
    }
    
    public void setPlaybackToLocal(int paramInt)
    {
      if (mVolumeProvider != null) {
        mVolumeProvider.setCallback(null);
      }
      mVolumeType = 1;
      sendVolumeInfoChanged(new ParcelableVolumeInfo(mVolumeType, mLocalStream, 2, mAudioManager.getStreamMaxVolume(mLocalStream), mAudioManager.getStreamVolume(mLocalStream)));
    }
    
    public void setPlaybackToRemote(VolumeProviderCompat paramVolumeProviderCompat)
    {
      if (paramVolumeProviderCompat != null)
      {
        if (mVolumeProvider != null) {
          mVolumeProvider.setCallback(null);
        }
        mVolumeType = 2;
        mVolumeProvider = paramVolumeProviderCompat;
        sendVolumeInfoChanged(new ParcelableVolumeInfo(mVolumeType, mLocalStream, mVolumeProvider.getVolumeControl(), mVolumeProvider.getMaxVolume(), mVolumeProvider.getCurrentVolume()));
        paramVolumeProviderCompat.setCallback(mVolumeCallback);
        return;
      }
      throw new IllegalArgumentException("volumeProvider may not be null");
    }
    
    public void setQueue(List paramList)
    {
      mQueue = paramList;
      sendQueue(paramList);
    }
    
    public void setQueueTitle(CharSequence paramCharSequence)
    {
      mQueueTitle = paramCharSequence;
      sendQueueTitle(paramCharSequence);
    }
    
    public void setRatingType(int paramInt)
    {
      mRatingType = paramInt;
    }
    
    void setRccState(PlaybackStateCompat paramPlaybackStateCompat)
    {
      mRcc.setPlaybackState(getRccStateFromState(paramPlaybackStateCompat.getState()));
    }
    
    public void setRepeatMode(int paramInt)
    {
      if (mRepeatMode != paramInt)
      {
        mRepeatMode = paramInt;
        sendRepeatMode(paramInt);
      }
    }
    
    public void setSessionActivity(PendingIntent paramPendingIntent)
    {
      Object localObject = mLock;
      try
      {
        mSessionActivity = paramPendingIntent;
        return;
      }
      catch (Throwable paramPendingIntent)
      {
        throw paramPendingIntent;
      }
    }
    
    public void setShuffleMode(int paramInt)
    {
      if (mShuffleMode != paramInt)
      {
        mShuffleMode = paramInt;
        sendShuffleMode(paramInt);
      }
    }
    
    public void setShuffleModeEnabled(boolean paramBoolean)
    {
      if (mShuffleModeEnabled != paramBoolean)
      {
        mShuffleModeEnabled = paramBoolean;
        sendShuffleModeEnabled(paramBoolean);
      }
    }
    
    void setVolumeTo(int paramInt1, int paramInt2)
    {
      if (mVolumeType == 2)
      {
        if (mVolumeProvider != null) {
          mVolumeProvider.onSetVolumeTo(paramInt1);
        }
      }
      else {
        mAudioManager.setStreamVolume(mLocalStream, paramInt1, paramInt2);
      }
    }
    
    void unregisterMediaButtonEventReceiver(PendingIntent paramPendingIntent, ComponentName paramComponentName)
    {
      mAudioManager.unregisterMediaButtonEventReceiver(paramComponentName);
    }
    
    boolean update()
    {
      if (mIsActive)
      {
        if ((!mIsMbrRegistered) && ((mFlags & 0x1) != 0))
        {
          registerMediaButtonEventReceiver(mMediaButtonReceiverIntent, mMediaButtonReceiverComponentName);
          mIsMbrRegistered = true;
        }
        else if ((mIsMbrRegistered) && ((mFlags & 0x1) == 0))
        {
          unregisterMediaButtonEventReceiver(mMediaButtonReceiverIntent, mMediaButtonReceiverComponentName);
          mIsMbrRegistered = false;
        }
        if ((!mIsRccRegistered) && ((mFlags & 0x2) != 0))
        {
          mAudioManager.registerRemoteControlClient(mRcc);
          mIsRccRegistered = true;
          return true;
        }
        if ((mIsRccRegistered) && ((mFlags & 0x2) == 0))
        {
          mRcc.setPlaybackState(0);
          mAudioManager.unregisterRemoteControlClient(mRcc);
          mIsRccRegistered = false;
        }
      }
      else
      {
        if (mIsMbrRegistered)
        {
          unregisterMediaButtonEventReceiver(mMediaButtonReceiverIntent, mMediaButtonReceiverComponentName);
          mIsMbrRegistered = false;
        }
        if (mIsRccRegistered)
        {
          mRcc.setPlaybackState(0);
          mAudioManager.unregisterRemoteControlClient(mRcc);
          mIsRccRegistered = false;
        }
      }
      return false;
    }
    
    private static final class Command
    {
      public final String command;
      public final Bundle extras;
      public final ResultReceiver stub;
      
      public Command(String paramString, Bundle paramBundle, ResultReceiver paramResultReceiver)
      {
        command = paramString;
        extras = paramBundle;
        stub = paramResultReceiver;
      }
    }
    
    class MediaSessionStub
      extends IMediaSession.Stub
    {
      MediaSessionStub() {}
      
      public void addQueueItem(MediaDescriptionCompat paramMediaDescriptionCompat)
      {
        postToHandler(25, paramMediaDescriptionCompat);
      }
      
      public void addQueueItemAt(MediaDescriptionCompat paramMediaDescriptionCompat, int paramInt)
      {
        postToHandler(26, paramMediaDescriptionCompat, paramInt);
      }
      
      public void adjustVolume(int paramInt1, int paramInt2, String paramString)
      {
        adjustVolume(paramInt1, paramInt2);
      }
      
      public void fastForward()
        throws RemoteException
      {
        postToHandler(16);
      }
      
      public Bundle getExtras()
      {
        Object localObject = mLock;
        try
        {
          Bundle localBundle = mExtras;
          return localBundle;
        }
        catch (Throwable localThrowable)
        {
          throw localThrowable;
        }
      }
      
      public long getFlags()
      {
        Object localObject = mLock;
        try
        {
          long l = mFlags;
          return l;
        }
        catch (Throwable localThrowable)
        {
          throw localThrowable;
        }
      }
      
      public PendingIntent getLaunchPendingIntent()
      {
        Object localObject = mLock;
        try
        {
          PendingIntent localPendingIntent = mSessionActivity;
          return localPendingIntent;
        }
        catch (Throwable localThrowable)
        {
          throw localThrowable;
        }
      }
      
      public MediaMetadataCompat getMetadata()
      {
        return mMetadata;
      }
      
      public String getPackageName()
      {
        return mPackageName;
      }
      
      public PlaybackStateCompat getPlaybackState()
      {
        Object localObject = mLock;
        try
        {
          PlaybackStateCompat localPlaybackStateCompat = mState;
          MediaMetadataCompat localMediaMetadataCompat = mMetadata;
          return MediaSessionCompat.getStateWithUpdatedPosition(localPlaybackStateCompat, localMediaMetadataCompat);
        }
        catch (Throwable localThrowable)
        {
          throw localThrowable;
        }
      }
      
      public List getQueue()
      {
        Object localObject = mLock;
        try
        {
          List localList = mQueue;
          return localList;
        }
        catch (Throwable localThrowable)
        {
          throw localThrowable;
        }
      }
      
      public CharSequence getQueueTitle()
      {
        return mQueueTitle;
      }
      
      public int getRatingType()
      {
        return mRatingType;
      }
      
      public int getRepeatMode()
      {
        return mRepeatMode;
      }
      
      public int getShuffleMode()
      {
        return mShuffleMode;
      }
      
      public String getTag()
      {
        return mTag;
      }
      
      public ParcelableVolumeInfo getVolumeAttributes()
      {
        Object localObject1 = mLock;
        try
        {
          int m = mVolumeType;
          int n = mLocalStream;
          Object localObject2 = mVolumeProvider;
          int i;
          int j;
          int k;
          if (m == 2)
          {
            i = ((VolumeProviderCompat)localObject2).getVolumeControl();
            j = ((VolumeProviderCompat)localObject2).getMaxVolume();
            k = ((VolumeProviderCompat)localObject2).getCurrentVolume();
          }
          else
          {
            j = mAudioManager.getStreamMaxVolume(n);
            localObject2 = mAudioManager;
            k = ((AudioManager)localObject2).getStreamVolume(n);
            i = 2;
          }
          return new ParcelableVolumeInfo(m, n, i, j, k);
        }
        catch (Throwable localThrowable)
        {
          throw localThrowable;
        }
      }
      
      public boolean isCaptioningEnabled()
      {
        return mCaptioningEnabled;
      }
      
      public boolean isShuffleModeEnabledDeprecated()
      {
        return mShuffleModeEnabled;
      }
      
      public boolean isTransportControlEnabled()
      {
        return (mFlags & 0x2) != 0;
      }
      
      public void next()
        throws RemoteException
      {
        postToHandler(14);
      }
      
      public void pause()
        throws RemoteException
      {
        postToHandler(12);
      }
      
      public void play()
        throws RemoteException
      {
        postToHandler(7);
      }
      
      public void playFromMediaId(String paramString, Bundle paramBundle)
        throws RemoteException
      {
        postToHandler(8, paramString, paramBundle);
      }
      
      public void playFromSearch(String paramString, Bundle paramBundle)
        throws RemoteException
      {
        postToHandler(9, paramString, paramBundle);
      }
      
      public void playFromUri(Uri paramUri, Bundle paramBundle)
        throws RemoteException
      {
        postToHandler(10, paramUri, paramBundle);
      }
      
      public void prepare()
        throws RemoteException
      {
        postToHandler(3);
      }
      
      public void prepareFromMediaId(String paramString, Bundle paramBundle)
        throws RemoteException
      {
        postToHandler(4, paramString, paramBundle);
      }
      
      public void prepareFromSearch(String paramString, Bundle paramBundle)
        throws RemoteException
      {
        postToHandler(5, paramString, paramBundle);
      }
      
      public void prepareFromUri(Uri paramUri, Bundle paramBundle)
        throws RemoteException
      {
        postToHandler(6, paramUri, paramBundle);
      }
      
      public void previous()
        throws RemoteException
      {
        postToHandler(15);
      }
      
      public void rate(RatingCompat paramRatingCompat)
        throws RemoteException
      {
        postToHandler(19, paramRatingCompat);
      }
      
      public void rateWithExtras(RatingCompat paramRatingCompat, Bundle paramBundle)
        throws RemoteException
      {
        postToHandler(31, paramRatingCompat, paramBundle);
      }
      
      public void registerCallbackListener(IMediaControllerCallback paramIMediaControllerCallback)
      {
        if (mDestroyed) {}
        try
        {
          paramIMediaControllerCallback.onSessionDestroyed();
          return;
        }
        catch (Exception paramIMediaControllerCallback) {}
        mControllerCallbacks.register(paramIMediaControllerCallback);
        return;
      }
      
      public void removeQueueItem(MediaDescriptionCompat paramMediaDescriptionCompat)
      {
        postToHandler(27, paramMediaDescriptionCompat);
      }
      
      public void removeQueueItemAt(int paramInt)
      {
        postToHandler(28, paramInt);
      }
      
      public void rewind()
        throws RemoteException
      {
        postToHandler(17);
      }
      
      public void seekTo(long paramLong)
        throws RemoteException
      {
        postToHandler(18, Long.valueOf(paramLong));
      }
      
      public void sendCommand(String paramString, Bundle paramBundle, MediaSessionCompat.ResultReceiverWrapper paramResultReceiverWrapper)
      {
        postToHandler(1, new MediaSessionCompat.MediaSessionImplBase.Command(paramString, paramBundle, MediaSessionCompat.ResultReceiverWrapper.access$400(paramResultReceiverWrapper)));
      }
      
      public void sendCustomAction(String paramString, Bundle paramBundle)
        throws RemoteException
      {
        postToHandler(20, paramString, paramBundle);
      }
      
      public boolean sendMediaButton(KeyEvent paramKeyEvent)
      {
        int i = mFlags;
        boolean bool = true;
        if ((i & 0x1) == 0) {
          bool = false;
        }
        if (bool) {
          postToHandler(21, paramKeyEvent);
        }
        return bool;
      }
      
      public void setCaptioningEnabled(boolean paramBoolean)
        throws RemoteException
      {
        postToHandler(29, Boolean.valueOf(paramBoolean));
      }
      
      public void setRepeatMode(int paramInt)
        throws RemoteException
      {
        postToHandler(23, paramInt);
      }
      
      public void setShuffleMode(int paramInt)
        throws RemoteException
      {
        postToHandler(30, paramInt);
      }
      
      public void setShuffleModeEnabledDeprecated(boolean paramBoolean)
        throws RemoteException
      {
        postToHandler(24, Boolean.valueOf(paramBoolean));
      }
      
      public void setVolumeTo(int paramInt1, int paramInt2, String paramString)
      {
        setVolumeTo(paramInt1, paramInt2);
      }
      
      public void skipToQueueItem(long paramLong)
      {
        postToHandler(11, Long.valueOf(paramLong));
      }
      
      public void stop()
        throws RemoteException
      {
        postToHandler(13);
      }
      
      public void unregisterCallbackListener(IMediaControllerCallback paramIMediaControllerCallback)
      {
        mControllerCallbacks.unregister(paramIMediaControllerCallback);
      }
    }
    
    class MessageHandler
      extends Handler
    {
      private static final int KEYCODE_MEDIA_PAUSE = 127;
      private static final int KEYCODE_MEDIA_PLAY = 126;
      private static final int MSG_ADD_QUEUE_ITEM = 25;
      private static final int MSG_ADD_QUEUE_ITEM_AT = 26;
      private static final int MSG_ADJUST_VOLUME = 2;
      private static final int MSG_COMMAND = 1;
      private static final int MSG_CUSTOM_ACTION = 20;
      private static final int MSG_FAST_FORWARD = 16;
      private static final int MSG_MEDIA_BUTTON = 21;
      private static final int MSG_NEXT = 14;
      private static final int MSG_PAUSE = 12;
      private static final int MSG_PLAY = 7;
      private static final int MSG_PLAY_MEDIA_ID = 8;
      private static final int MSG_PLAY_SEARCH = 9;
      private static final int MSG_PLAY_URI = 10;
      private static final int MSG_PREPARE = 3;
      private static final int MSG_PREPARE_MEDIA_ID = 4;
      private static final int MSG_PREPARE_SEARCH = 5;
      private static final int MSG_PREPARE_URI = 6;
      private static final int MSG_PREVIOUS = 15;
      private static final int MSG_RATE = 19;
      private static final int MSG_RATE_EXTRA = 31;
      private static final int MSG_REMOVE_QUEUE_ITEM = 27;
      private static final int MSG_REMOVE_QUEUE_ITEM_AT = 28;
      private static final int MSG_REWIND = 17;
      private static final int MSG_SEEK_TO = 18;
      private static final int MSG_SET_CAPTIONING_ENABLED = 29;
      private static final int MSG_SET_REPEAT_MODE = 23;
      private static final int MSG_SET_SHUFFLE_MODE = 30;
      private static final int MSG_SET_SHUFFLE_MODE_ENABLED = 24;
      private static final int MSG_SET_VOLUME = 22;
      private static final int MSG_SKIP_TO_ITEM = 11;
      private static final int MSG_STOP = 13;
      
      public MessageHandler(Looper paramLooper)
      {
        super();
      }
      
      private void onMediaButtonEvent(KeyEvent paramKeyEvent, MediaSessionCompat.Callback paramCallback)
      {
        if (paramKeyEvent != null)
        {
          if (paramKeyEvent.getAction() != 0) {
            return;
          }
          long l;
          if (mState == null) {
            l = 0L;
          } else {
            l = mState.getActions();
          }
          int i = paramKeyEvent.getKeyCode();
          if (i != 79) {
            switch (i)
            {
            default: 
              switch (i)
              {
              default: 
                return;
              case 127: 
                if ((l & 0x2) == 0L) {
                  break;
                }
                paramCallback.onPause();
                return;
              case 126: 
                if ((l & 0x4) == 0L) {
                  break;
                }
                paramCallback.onPlay();
                return;
              }
              break;
            case 90: 
              if ((l & 0x40) == 0L) {
                break;
              }
              paramCallback.onFastForward();
              return;
            case 89: 
              if ((l & 0x8) == 0L) {
                break;
              }
              paramCallback.onRewind();
              return;
            case 88: 
              if ((l & 0x10) == 0L) {
                break;
              }
              paramCallback.onSkipToPrevious();
              return;
            case 87: 
              if ((l & 0x20) == 0L) {
                break;
              }
              paramCallback.onSkipToNext();
              return;
            case 86: 
              if ((l & 1L) == 0L) {
                break;
              }
              paramCallback.onStop();
              return;
            }
          } else {
            Log.w("MediaSessionCompat", "KEYCODE_MEDIA_PLAY_PAUSE and KEYCODE_HEADSETHOOK are handled already");
          }
        }
      }
      
      public void handleMessage(Message paramMessage)
      {
        MediaSessionCompat.Callback localCallback = mCallback;
        if (localCallback == null) {
          return;
        }
        switch (what)
        {
        default: 
          
        case 31: 
          localCallback.onSetRating((RatingCompat)obj, paramMessage.getData());
          return;
        case 30: 
          localCallback.onSetShuffleMode(arg1);
          return;
        case 29: 
          localCallback.onSetCaptioningEnabled(((Boolean)obj).booleanValue());
          return;
        case 28: 
          if (mQueue != null)
          {
            if ((arg1 >= 0) && (arg1 < mQueue.size())) {
              paramMessage = (MediaSessionCompat.QueueItem)mQueue.get(arg1);
            } else {
              paramMessage = null;
            }
            if (paramMessage != null)
            {
              localCallback.onRemoveQueueItem(paramMessage.getDescription());
              return;
            }
          }
          break;
        case 27: 
          localCallback.onRemoveQueueItem((MediaDescriptionCompat)obj);
          return;
        case 26: 
          localCallback.onAddQueueItem((MediaDescriptionCompat)obj, arg1);
          return;
        case 25: 
          localCallback.onAddQueueItem((MediaDescriptionCompat)obj);
          return;
        case 24: 
          localCallback.onSetShuffleModeEnabled(((Boolean)obj).booleanValue());
          return;
        case 23: 
          localCallback.onSetRepeatMode(arg1);
          return;
        case 22: 
          setVolumeTo(arg1, 0);
          return;
        case 21: 
          paramMessage = (KeyEvent)obj;
          Intent localIntent = new Intent("android.intent.action.MEDIA_BUTTON");
          localIntent.putExtra("android.intent.extra.KEY_EVENT", paramMessage);
          if (!localCallback.onMediaButtonEvent(localIntent))
          {
            onMediaButtonEvent(paramMessage, localCallback);
            return;
          }
          break;
        case 20: 
          localCallback.onCustomAction((String)obj, paramMessage.getData());
          return;
        case 19: 
          localCallback.onSetRating((RatingCompat)obj);
          return;
        case 18: 
          localCallback.onSeekTo(((Long)obj).longValue());
          return;
        case 17: 
          localCallback.onRewind();
          return;
        case 16: 
          localCallback.onFastForward();
          return;
        case 15: 
          localCallback.onSkipToPrevious();
          return;
        case 14: 
          localCallback.onSkipToNext();
          return;
        case 13: 
          localCallback.onStop();
          return;
        case 12: 
          localCallback.onPause();
          return;
        case 11: 
          localCallback.onSkipToQueueItem(((Long)obj).longValue());
          return;
        case 10: 
          localCallback.onPlayFromUri((Uri)obj, paramMessage.getData());
          return;
        case 9: 
          localCallback.onPlayFromSearch((String)obj, paramMessage.getData());
          return;
        case 8: 
          localCallback.onPlayFromMediaId((String)obj, paramMessage.getData());
          return;
        case 7: 
          localCallback.onPlay();
          return;
        case 6: 
          localCallback.onPrepareFromUri((Uri)obj, paramMessage.getData());
          return;
        case 5: 
          localCallback.onPrepareFromSearch((String)obj, paramMessage.getData());
          return;
        case 4: 
          localCallback.onPrepareFromMediaId((String)obj, paramMessage.getData());
          return;
        case 3: 
          localCallback.onPrepare();
          return;
        case 2: 
          adjustVolume(arg1, 0);
          return;
        case 1: 
          paramMessage = (MediaSessionCompat.MediaSessionImplBase.Command)obj;
          localCallback.onCommand(command, extras, stub);
        }
      }
      
      public void post(int paramInt)
      {
        post(paramInt, null);
      }
      
      public void post(int paramInt, Object paramObject)
      {
        obtainMessage(paramInt, paramObject).sendToTarget();
      }
      
      public void post(int paramInt1, Object paramObject, int paramInt2)
      {
        obtainMessage(paramInt1, paramInt2, 0, paramObject).sendToTarget();
      }
      
      public void post(int paramInt, Object paramObject, Bundle paramBundle)
      {
        paramObject = obtainMessage(paramInt, paramObject);
        paramObject.setData(paramBundle);
        paramObject.sendToTarget();
      }
    }
  }
  
  public static abstract interface OnActiveChangeListener
  {
    public abstract void onActiveChanged();
  }
  
  public static final class QueueItem
    implements Parcelable
  {
    public static final Parcelable.Creator<QueueItem> CREATOR = new Parcelable.Creator()
    {
      public MediaSessionCompat.QueueItem createFromParcel(Parcel paramAnonymousParcel)
      {
        return new MediaSessionCompat.QueueItem(paramAnonymousParcel);
      }
      
      public MediaSessionCompat.QueueItem[] newArray(int paramAnonymousInt)
      {
        return new MediaSessionCompat.QueueItem[paramAnonymousInt];
      }
    };
    public static final int UNKNOWN_ID = -1;
    private final MediaDescriptionCompat mDescription;
    private final long mId;
    private Object mItem;
    
    QueueItem(Parcel paramParcel)
    {
      mDescription = ((MediaDescriptionCompat)MediaDescriptionCompat.CREATOR.createFromParcel(paramParcel));
      mId = paramParcel.readLong();
    }
    
    public QueueItem(MediaDescriptionCompat paramMediaDescriptionCompat, long paramLong)
    {
      this(null, paramMediaDescriptionCompat, paramLong);
    }
    
    private QueueItem(Object paramObject, MediaDescriptionCompat paramMediaDescriptionCompat, long paramLong)
    {
      if (paramMediaDescriptionCompat != null)
      {
        if (paramLong != -1L)
        {
          mDescription = paramMediaDescriptionCompat;
          mId = paramLong;
          mItem = paramObject;
          return;
        }
        throw new IllegalArgumentException("Id cannot be QueueItem.UNKNOWN_ID");
      }
      throw new IllegalArgumentException("Description cannot be null.");
    }
    
    public static QueueItem fromQueueItem(Object paramObject)
    {
      if ((paramObject != null) && (Build.VERSION.SDK_INT >= 21)) {
        return new QueueItem(paramObject, MediaDescriptionCompat.fromMediaDescription(MediaSessionCompatApi21.QueueItem.getDescription(paramObject)), MediaSessionCompatApi21.QueueItem.getQueueId(paramObject));
      }
      return null;
    }
    
    public static List fromQueueItemList(List paramList)
    {
      if ((paramList != null) && (Build.VERSION.SDK_INT >= 21))
      {
        ArrayList localArrayList = new ArrayList();
        paramList = paramList.iterator();
        while (paramList.hasNext()) {
          localArrayList.add(fromQueueItem(paramList.next()));
        }
        return localArrayList;
      }
      return null;
    }
    
    public int describeContents()
    {
      return 0;
    }
    
    public MediaDescriptionCompat getDescription()
    {
      return mDescription;
    }
    
    public long getQueueId()
    {
      return mId;
    }
    
    public Object getQueueItem()
    {
      if ((mItem == null) && (Build.VERSION.SDK_INT >= 21))
      {
        mItem = MediaSessionCompatApi21.QueueItem.createItem(mDescription.getMediaDescription(), mId);
        return mItem;
      }
      return mItem;
    }
    
    public String toString()
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("MediaSession.QueueItem {Description=");
      localStringBuilder.append(mDescription);
      localStringBuilder.append(", Id=");
      localStringBuilder.append(mId);
      localStringBuilder.append(" }");
      return localStringBuilder.toString();
    }
    
    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      mDescription.writeToParcel(paramParcel, paramInt);
      paramParcel.writeLong(mId);
    }
  }
  
  static final class ResultReceiverWrapper
    implements Parcelable
  {
    public static final Parcelable.Creator<ResultReceiverWrapper> CREATOR = new Parcelable.Creator()
    {
      public MediaSessionCompat.ResultReceiverWrapper createFromParcel(Parcel paramAnonymousParcel)
      {
        return new MediaSessionCompat.ResultReceiverWrapper(paramAnonymousParcel);
      }
      
      public MediaSessionCompat.ResultReceiverWrapper[] newArray(int paramAnonymousInt)
      {
        return new MediaSessionCompat.ResultReceiverWrapper[paramAnonymousInt];
      }
    };
    private ResultReceiver mResultReceiver;
    
    ResultReceiverWrapper(Parcel paramParcel)
    {
      mResultReceiver = ((ResultReceiver)ResultReceiver.CREATOR.createFromParcel(paramParcel));
    }
    
    public ResultReceiverWrapper(ResultReceiver paramResultReceiver)
    {
      mResultReceiver = paramResultReceiver;
    }
    
    public int describeContents()
    {
      return 0;
    }
    
    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      mResultReceiver.writeToParcel(paramParcel, paramInt);
    }
  }
  
  @Retention(RetentionPolicy.SOURCE)
  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  public static @interface SessionFlags {}
  
  public static final class Token
    implements Parcelable
  {
    public static final Parcelable.Creator<Token> CREATOR = new Parcelable.Creator()
    {
      public MediaSessionCompat.Token createFromParcel(Parcel paramAnonymousParcel)
      {
        if (Build.VERSION.SDK_INT >= 21) {
          paramAnonymousParcel = paramAnonymousParcel.readParcelable(null);
        } else {
          paramAnonymousParcel = paramAnonymousParcel.readStrongBinder();
        }
        return new MediaSessionCompat.Token(paramAnonymousParcel);
      }
      
      public MediaSessionCompat.Token[] newArray(int paramAnonymousInt)
      {
        return new MediaSessionCompat.Token[paramAnonymousInt];
      }
    };
    private final IMediaSession mExtraBinder;
    private final Object mInner;
    
    Token(Object paramObject)
    {
      this(paramObject, null);
    }
    
    Token(Object paramObject, IMediaSession paramIMediaSession)
    {
      mInner = paramObject;
      mExtraBinder = paramIMediaSession;
    }
    
    public static Token fromToken(Object paramObject)
    {
      return fromToken(paramObject, null);
    }
    
    public static Token fromToken(Object paramObject, IMediaSession paramIMediaSession)
    {
      if ((paramObject != null) && (Build.VERSION.SDK_INT >= 21)) {
        return new Token(MediaSessionCompatApi21.verifyToken(paramObject), paramIMediaSession);
      }
      return null;
    }
    
    public int describeContents()
    {
      return 0;
    }
    
    public boolean equals(Object paramObject)
    {
      if (this == paramObject) {
        return true;
      }
      if (!(paramObject instanceof Token)) {
        return false;
      }
      paramObject = (Token)paramObject;
      if (mInner == null) {
        return mInner == null;
      }
      if (mInner == null) {
        return false;
      }
      return mInner.equals(mInner);
    }
    
    public IMediaSession getExtraBinder()
    {
      return mExtraBinder;
    }
    
    public Object getToken()
    {
      return mInner;
    }
    
    public int hashCode()
    {
      if (mInner == null) {
        return 0;
      }
      return mInner.hashCode();
    }
    
    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      if (Build.VERSION.SDK_INT >= 21)
      {
        paramParcel.writeParcelable((Parcelable)mInner, paramInt);
        return;
      }
      paramParcel.writeStrongBinder((IBinder)mInner);
    }
  }
}
