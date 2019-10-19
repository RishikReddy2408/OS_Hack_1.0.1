package android.support.v4.media.session;

import android.app.PendingIntent;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.os.RemoteException;
import android.support.v4.media.MediaDescriptionCompat;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.RatingCompat;
import android.text.TextUtils;
import android.view.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public abstract interface IMediaSession
  extends IInterface
{
  public abstract void addQueueItem(MediaDescriptionCompat paramMediaDescriptionCompat)
    throws RemoteException;
  
  public abstract void addQueueItemAt(MediaDescriptionCompat paramMediaDescriptionCompat, int paramInt)
    throws RemoteException;
  
  public abstract void adjustVolume(int paramInt1, int paramInt2, String paramString)
    throws RemoteException;
  
  public abstract void fastForward()
    throws RemoteException;
  
  public abstract Bundle getExtras()
    throws RemoteException;
  
  public abstract long getFlags()
    throws RemoteException;
  
  public abstract PendingIntent getLaunchPendingIntent()
    throws RemoteException;
  
  public abstract MediaMetadataCompat getMetadata()
    throws RemoteException;
  
  public abstract String getPackageName()
    throws RemoteException;
  
  public abstract PlaybackStateCompat getPlaybackState()
    throws RemoteException;
  
  public abstract List getQueue()
    throws RemoteException;
  
  public abstract CharSequence getQueueTitle()
    throws RemoteException;
  
  public abstract int getRatingType()
    throws RemoteException;
  
  public abstract int getRepeatMode()
    throws RemoteException;
  
  public abstract int getShuffleMode()
    throws RemoteException;
  
  public abstract String getTag()
    throws RemoteException;
  
  public abstract ParcelableVolumeInfo getVolumeAttributes()
    throws RemoteException;
  
  public abstract boolean isCaptioningEnabled()
    throws RemoteException;
  
  public abstract boolean isShuffleModeEnabledDeprecated()
    throws RemoteException;
  
  public abstract boolean isTransportControlEnabled()
    throws RemoteException;
  
  public abstract void next()
    throws RemoteException;
  
  public abstract void pause()
    throws RemoteException;
  
  public abstract void play()
    throws RemoteException;
  
  public abstract void playFromMediaId(String paramString, Bundle paramBundle)
    throws RemoteException;
  
  public abstract void playFromSearch(String paramString, Bundle paramBundle)
    throws RemoteException;
  
  public abstract void playFromUri(Uri paramUri, Bundle paramBundle)
    throws RemoteException;
  
  public abstract void prepare()
    throws RemoteException;
  
  public abstract void prepareFromMediaId(String paramString, Bundle paramBundle)
    throws RemoteException;
  
  public abstract void prepareFromSearch(String paramString, Bundle paramBundle)
    throws RemoteException;
  
  public abstract void prepareFromUri(Uri paramUri, Bundle paramBundle)
    throws RemoteException;
  
  public abstract void previous()
    throws RemoteException;
  
  public abstract void rate(RatingCompat paramRatingCompat)
    throws RemoteException;
  
  public abstract void rateWithExtras(RatingCompat paramRatingCompat, Bundle paramBundle)
    throws RemoteException;
  
  public abstract void registerCallbackListener(IMediaControllerCallback paramIMediaControllerCallback)
    throws RemoteException;
  
  public abstract void removeQueueItem(MediaDescriptionCompat paramMediaDescriptionCompat)
    throws RemoteException;
  
  public abstract void removeQueueItemAt(int paramInt)
    throws RemoteException;
  
  public abstract void rewind()
    throws RemoteException;
  
  public abstract void seekTo(long paramLong)
    throws RemoteException;
  
  public abstract void sendCommand(String paramString, Bundle paramBundle, MediaSessionCompat.ResultReceiverWrapper paramResultReceiverWrapper)
    throws RemoteException;
  
  public abstract void sendCustomAction(String paramString, Bundle paramBundle)
    throws RemoteException;
  
  public abstract boolean sendMediaButton(KeyEvent paramKeyEvent)
    throws RemoteException;
  
  public abstract void setCaptioningEnabled(boolean paramBoolean)
    throws RemoteException;
  
  public abstract void setRepeatMode(int paramInt)
    throws RemoteException;
  
  public abstract void setShuffleMode(int paramInt)
    throws RemoteException;
  
  public abstract void setShuffleModeEnabledDeprecated(boolean paramBoolean)
    throws RemoteException;
  
  public abstract void setVolumeTo(int paramInt1, int paramInt2, String paramString)
    throws RemoteException;
  
  public abstract void skipToQueueItem(long paramLong)
    throws RemoteException;
  
  public abstract void stop()
    throws RemoteException;
  
  public abstract void unregisterCallbackListener(IMediaControllerCallback paramIMediaControllerCallback)
    throws RemoteException;
  
  public static abstract class Stub
    extends Binder
    implements IMediaSession
  {
    private static final String DESCRIPTOR = "android.support.v4.media.session.IMediaSession";
    static final int TRANSACTION_addQueueItem = 41;
    static final int TRANSACTION_addQueueItemAt = 42;
    static final int TRANSACTION_adjustVolume = 11;
    static final int TRANSACTION_fastForward = 22;
    static final int TRANSACTION_getExtras = 31;
    static final int TRANSACTION_getFlags = 9;
    static final int TRANSACTION_getLaunchPendingIntent = 8;
    static final int TRANSACTION_getMetadata = 27;
    static final int TRANSACTION_getPackageName = 6;
    static final int TRANSACTION_getPlaybackState = 28;
    static final int TRANSACTION_getQueue = 29;
    static final int TRANSACTION_getQueueTitle = 30;
    static final int TRANSACTION_getRatingType = 32;
    static final int TRANSACTION_getRepeatMode = 37;
    static final int TRANSACTION_getShuffleMode = 47;
    static final int TRANSACTION_getTag = 7;
    static final int TRANSACTION_getVolumeAttributes = 10;
    static final int TRANSACTION_isCaptioningEnabled = 45;
    static final int TRANSACTION_isShuffleModeEnabledDeprecated = 38;
    static final int TRANSACTION_isTransportControlEnabled = 5;
    static final int TRANSACTION_next = 20;
    static final int TRANSACTION_pause = 18;
    static final int TRANSACTION_play = 13;
    static final int TRANSACTION_playFromMediaId = 14;
    static final int TRANSACTION_playFromSearch = 15;
    static final int TRANSACTION_playFromUri = 16;
    static final int TRANSACTION_prepare = 33;
    static final int TRANSACTION_prepareFromMediaId = 34;
    static final int TRANSACTION_prepareFromSearch = 35;
    static final int TRANSACTION_prepareFromUri = 36;
    static final int TRANSACTION_previous = 21;
    static final int TRANSACTION_rate = 25;
    static final int TRANSACTION_rateWithExtras = 51;
    static final int TRANSACTION_registerCallbackListener = 3;
    static final int TRANSACTION_removeQueueItem = 43;
    static final int TRANSACTION_removeQueueItemAt = 44;
    static final int TRANSACTION_rewind = 23;
    static final int TRANSACTION_seekTo = 24;
    static final int TRANSACTION_sendCommand = 1;
    static final int TRANSACTION_sendCustomAction = 26;
    static final int TRANSACTION_sendMediaButton = 2;
    static final int TRANSACTION_setCaptioningEnabled = 46;
    static final int TRANSACTION_setRepeatMode = 39;
    static final int TRANSACTION_setShuffleMode = 48;
    static final int TRANSACTION_setShuffleModeEnabledDeprecated = 40;
    static final int TRANSACTION_setVolumeTo = 12;
    static final int TRANSACTION_skipToQueueItem = 17;
    static final int TRANSACTION_stop = 19;
    static final int TRANSACTION_unregisterCallbackListener = 4;
    
    public Stub()
    {
      attachInterface(this, "android.support.v4.media.session.IMediaSession");
    }
    
    public static IMediaSession asInterface(IBinder paramIBinder)
    {
      if (paramIBinder == null) {
        return null;
      }
      IInterface localIInterface = paramIBinder.queryLocalInterface("android.support.v4.media.session.IMediaSession");
      if ((localIInterface != null) && ((localIInterface instanceof IMediaSession))) {
        return (IMediaSession)localIInterface;
      }
      return new Proxy(paramIBinder);
    }
    
    public IBinder asBinder()
    {
      return this;
    }
    
    public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2)
      throws RemoteException
    {
      throw new Runtime("d2j fail translate: java.lang.RuntimeException: can not merge I and Z\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.copyTypes(TypeTransformer.java:311)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.fixTypes(TypeTransformer.java:226)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:207)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\n");
    }
    
    private static class Proxy
      implements IMediaSession
    {
      private IBinder mRemote;
      
      Proxy(IBinder paramIBinder)
      {
        mRemote = paramIBinder;
      }
      
      public void addQueueItem(MediaDescriptionCompat paramMediaDescriptionCompat)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
          if (paramMediaDescriptionCompat != null)
          {
            localParcel1.writeInt(1);
            paramMediaDescriptionCompat.writeToParcel(localParcel1, 0);
          }
          else
          {
            localParcel1.writeInt(0);
          }
          mRemote.transact(41, localParcel1, localParcel2, 0);
          localParcel2.readException();
          localParcel2.recycle();
          localParcel1.recycle();
          return;
        }
        catch (Throwable paramMediaDescriptionCompat)
        {
          localParcel2.recycle();
          localParcel1.recycle();
          throw paramMediaDescriptionCompat;
        }
      }
      
      public void addQueueItemAt(MediaDescriptionCompat paramMediaDescriptionCompat, int paramInt)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
          if (paramMediaDescriptionCompat != null)
          {
            localParcel1.writeInt(1);
            paramMediaDescriptionCompat.writeToParcel(localParcel1, 0);
          }
          else
          {
            localParcel1.writeInt(0);
          }
          localParcel1.writeInt(paramInt);
          mRemote.transact(42, localParcel1, localParcel2, 0);
          localParcel2.readException();
          localParcel2.recycle();
          localParcel1.recycle();
          return;
        }
        catch (Throwable paramMediaDescriptionCompat)
        {
          localParcel2.recycle();
          localParcel1.recycle();
          throw paramMediaDescriptionCompat;
        }
      }
      
      public void adjustVolume(int paramInt1, int paramInt2, String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
          localParcel1.writeInt(paramInt1);
          localParcel1.writeInt(paramInt2);
          localParcel1.writeString(paramString);
          mRemote.transact(11, localParcel1, localParcel2, 0);
          localParcel2.readException();
          localParcel2.recycle();
          localParcel1.recycle();
          return;
        }
        catch (Throwable paramString)
        {
          localParcel2.recycle();
          localParcel1.recycle();
          throw paramString;
        }
      }
      
      public IBinder asBinder()
      {
        return mRemote;
      }
      
      public void fastForward()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
          mRemote.transact(22, localParcel1, localParcel2, 0);
          localParcel2.readException();
          localParcel2.recycle();
          localParcel1.recycle();
          return;
        }
        catch (Throwable localThrowable)
        {
          localParcel2.recycle();
          localParcel1.recycle();
          throw localThrowable;
        }
      }
      
      public Bundle getExtras()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
          mRemote.transact(31, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int i = localParcel2.readInt();
          Bundle localBundle;
          if (i != 0) {
            localBundle = (Bundle)Bundle.CREATOR.createFromParcel(localParcel2);
          } else {
            localBundle = null;
          }
          localParcel2.recycle();
          localParcel1.recycle();
          return localBundle;
        }
        catch (Throwable localThrowable)
        {
          localParcel2.recycle();
          localParcel1.recycle();
          throw localThrowable;
        }
      }
      
      public long getFlags()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
          mRemote.transact(9, localParcel1, localParcel2, 0);
          localParcel2.readException();
          long l = localParcel2.readLong();
          localParcel2.recycle();
          localParcel1.recycle();
          return l;
        }
        catch (Throwable localThrowable)
        {
          localParcel2.recycle();
          localParcel1.recycle();
          throw localThrowable;
        }
      }
      
      public String getInterfaceDescriptor()
      {
        return "android.support.v4.media.session.IMediaSession";
      }
      
      public PendingIntent getLaunchPendingIntent()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
          mRemote.transact(8, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int i = localParcel2.readInt();
          PendingIntent localPendingIntent;
          if (i != 0) {
            localPendingIntent = (PendingIntent)PendingIntent.CREATOR.createFromParcel(localParcel2);
          } else {
            localPendingIntent = null;
          }
          localParcel2.recycle();
          localParcel1.recycle();
          return localPendingIntent;
        }
        catch (Throwable localThrowable)
        {
          localParcel2.recycle();
          localParcel1.recycle();
          throw localThrowable;
        }
      }
      
      public MediaMetadataCompat getMetadata()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
          mRemote.transact(27, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int i = localParcel2.readInt();
          MediaMetadataCompat localMediaMetadataCompat;
          if (i != 0) {
            localMediaMetadataCompat = (MediaMetadataCompat)MediaMetadataCompat.CREATOR.createFromParcel(localParcel2);
          } else {
            localMediaMetadataCompat = null;
          }
          localParcel2.recycle();
          localParcel1.recycle();
          return localMediaMetadataCompat;
        }
        catch (Throwable localThrowable)
        {
          localParcel2.recycle();
          localParcel1.recycle();
          throw localThrowable;
        }
      }
      
      public String getPackageName()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
          mRemote.transact(6, localParcel1, localParcel2, 0);
          localParcel2.readException();
          String str = localParcel2.readString();
          localParcel2.recycle();
          localParcel1.recycle();
          return str;
        }
        catch (Throwable localThrowable)
        {
          localParcel2.recycle();
          localParcel1.recycle();
          throw localThrowable;
        }
      }
      
      public PlaybackStateCompat getPlaybackState()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
          mRemote.transact(28, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int i = localParcel2.readInt();
          PlaybackStateCompat localPlaybackStateCompat;
          if (i != 0) {
            localPlaybackStateCompat = (PlaybackStateCompat)PlaybackStateCompat.CREATOR.createFromParcel(localParcel2);
          } else {
            localPlaybackStateCompat = null;
          }
          localParcel2.recycle();
          localParcel1.recycle();
          return localPlaybackStateCompat;
        }
        catch (Throwable localThrowable)
        {
          localParcel2.recycle();
          localParcel1.recycle();
          throw localThrowable;
        }
      }
      
      public List getQueue()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
          mRemote.transact(29, localParcel1, localParcel2, 0);
          localParcel2.readException();
          ArrayList localArrayList = localParcel2.createTypedArrayList(MediaSessionCompat.QueueItem.CREATOR);
          localParcel2.recycle();
          localParcel1.recycle();
          return localArrayList;
        }
        catch (Throwable localThrowable)
        {
          localParcel2.recycle();
          localParcel1.recycle();
          throw localThrowable;
        }
      }
      
      public CharSequence getQueueTitle()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
          mRemote.transact(30, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int i = localParcel2.readInt();
          CharSequence localCharSequence;
          if (i != 0) {
            localCharSequence = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(localParcel2);
          } else {
            localCharSequence = null;
          }
          localParcel2.recycle();
          localParcel1.recycle();
          return localCharSequence;
        }
        catch (Throwable localThrowable)
        {
          localParcel2.recycle();
          localParcel1.recycle();
          throw localThrowable;
        }
      }
      
      public int getRatingType()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
          mRemote.transact(32, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int i = localParcel2.readInt();
          localParcel2.recycle();
          localParcel1.recycle();
          return i;
        }
        catch (Throwable localThrowable)
        {
          localParcel2.recycle();
          localParcel1.recycle();
          throw localThrowable;
        }
      }
      
      public int getRepeatMode()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
          mRemote.transact(37, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int i = localParcel2.readInt();
          localParcel2.recycle();
          localParcel1.recycle();
          return i;
        }
        catch (Throwable localThrowable)
        {
          localParcel2.recycle();
          localParcel1.recycle();
          throw localThrowable;
        }
      }
      
      public int getShuffleMode()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
          mRemote.transact(47, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int i = localParcel2.readInt();
          localParcel2.recycle();
          localParcel1.recycle();
          return i;
        }
        catch (Throwable localThrowable)
        {
          localParcel2.recycle();
          localParcel1.recycle();
          throw localThrowable;
        }
      }
      
      public String getTag()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
          mRemote.transact(7, localParcel1, localParcel2, 0);
          localParcel2.readException();
          String str = localParcel2.readString();
          localParcel2.recycle();
          localParcel1.recycle();
          return str;
        }
        catch (Throwable localThrowable)
        {
          localParcel2.recycle();
          localParcel1.recycle();
          throw localThrowable;
        }
      }
      
      public ParcelableVolumeInfo getVolumeAttributes()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
          mRemote.transact(10, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int i = localParcel2.readInt();
          ParcelableVolumeInfo localParcelableVolumeInfo;
          if (i != 0) {
            localParcelableVolumeInfo = (ParcelableVolumeInfo)ParcelableVolumeInfo.CREATOR.createFromParcel(localParcel2);
          } else {
            localParcelableVolumeInfo = null;
          }
          localParcel2.recycle();
          localParcel1.recycle();
          return localParcelableVolumeInfo;
        }
        catch (Throwable localThrowable)
        {
          localParcel2.recycle();
          localParcel1.recycle();
          throw localThrowable;
        }
      }
      
      public boolean isCaptioningEnabled()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
          IBinder localIBinder = mRemote;
          boolean bool = false;
          localIBinder.transact(45, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int i = localParcel2.readInt();
          if (i != 0) {
            bool = true;
          }
          localParcel2.recycle();
          localParcel1.recycle();
          return bool;
        }
        catch (Throwable localThrowable)
        {
          localParcel2.recycle();
          localParcel1.recycle();
          throw localThrowable;
        }
      }
      
      public boolean isShuffleModeEnabledDeprecated()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
          IBinder localIBinder = mRemote;
          boolean bool = false;
          localIBinder.transact(38, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int i = localParcel2.readInt();
          if (i != 0) {
            bool = true;
          }
          localParcel2.recycle();
          localParcel1.recycle();
          return bool;
        }
        catch (Throwable localThrowable)
        {
          localParcel2.recycle();
          localParcel1.recycle();
          throw localThrowable;
        }
      }
      
      public boolean isTransportControlEnabled()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
          IBinder localIBinder = mRemote;
          boolean bool = false;
          localIBinder.transact(5, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int i = localParcel2.readInt();
          if (i != 0) {
            bool = true;
          }
          localParcel2.recycle();
          localParcel1.recycle();
          return bool;
        }
        catch (Throwable localThrowable)
        {
          localParcel2.recycle();
          localParcel1.recycle();
          throw localThrowable;
        }
      }
      
      public void next()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
          mRemote.transact(20, localParcel1, localParcel2, 0);
          localParcel2.readException();
          localParcel2.recycle();
          localParcel1.recycle();
          return;
        }
        catch (Throwable localThrowable)
        {
          localParcel2.recycle();
          localParcel1.recycle();
          throw localThrowable;
        }
      }
      
      public void pause()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
          mRemote.transact(18, localParcel1, localParcel2, 0);
          localParcel2.readException();
          localParcel2.recycle();
          localParcel1.recycle();
          return;
        }
        catch (Throwable localThrowable)
        {
          localParcel2.recycle();
          localParcel1.recycle();
          throw localThrowable;
        }
      }
      
      public void play()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
          mRemote.transact(13, localParcel1, localParcel2, 0);
          localParcel2.readException();
          localParcel2.recycle();
          localParcel1.recycle();
          return;
        }
        catch (Throwable localThrowable)
        {
          localParcel2.recycle();
          localParcel1.recycle();
          throw localThrowable;
        }
      }
      
      public void playFromMediaId(String paramString, Bundle paramBundle)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
          localParcel1.writeString(paramString);
          if (paramBundle != null)
          {
            localParcel1.writeInt(1);
            paramBundle.writeToParcel(localParcel1, 0);
          }
          else
          {
            localParcel1.writeInt(0);
          }
          mRemote.transact(14, localParcel1, localParcel2, 0);
          localParcel2.readException();
          localParcel2.recycle();
          localParcel1.recycle();
          return;
        }
        catch (Throwable paramString)
        {
          localParcel2.recycle();
          localParcel1.recycle();
          throw paramString;
        }
      }
      
      public void playFromSearch(String paramString, Bundle paramBundle)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
          localParcel1.writeString(paramString);
          if (paramBundle != null)
          {
            localParcel1.writeInt(1);
            paramBundle.writeToParcel(localParcel1, 0);
          }
          else
          {
            localParcel1.writeInt(0);
          }
          mRemote.transact(15, localParcel1, localParcel2, 0);
          localParcel2.readException();
          localParcel2.recycle();
          localParcel1.recycle();
          return;
        }
        catch (Throwable paramString)
        {
          localParcel2.recycle();
          localParcel1.recycle();
          throw paramString;
        }
      }
      
      public void playFromUri(Uri paramUri, Bundle paramBundle)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
          if (paramUri != null)
          {
            localParcel1.writeInt(1);
            paramUri.writeToParcel(localParcel1, 0);
          }
          else
          {
            localParcel1.writeInt(0);
          }
          if (paramBundle != null)
          {
            localParcel1.writeInt(1);
            paramBundle.writeToParcel(localParcel1, 0);
          }
          else
          {
            localParcel1.writeInt(0);
          }
          mRemote.transact(16, localParcel1, localParcel2, 0);
          localParcel2.readException();
          localParcel2.recycle();
          localParcel1.recycle();
          return;
        }
        catch (Throwable paramUri)
        {
          localParcel2.recycle();
          localParcel1.recycle();
          throw paramUri;
        }
      }
      
      public void prepare()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
          mRemote.transact(33, localParcel1, localParcel2, 0);
          localParcel2.readException();
          localParcel2.recycle();
          localParcel1.recycle();
          return;
        }
        catch (Throwable localThrowable)
        {
          localParcel2.recycle();
          localParcel1.recycle();
          throw localThrowable;
        }
      }
      
      public void prepareFromMediaId(String paramString, Bundle paramBundle)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
          localParcel1.writeString(paramString);
          if (paramBundle != null)
          {
            localParcel1.writeInt(1);
            paramBundle.writeToParcel(localParcel1, 0);
          }
          else
          {
            localParcel1.writeInt(0);
          }
          mRemote.transact(34, localParcel1, localParcel2, 0);
          localParcel2.readException();
          localParcel2.recycle();
          localParcel1.recycle();
          return;
        }
        catch (Throwable paramString)
        {
          localParcel2.recycle();
          localParcel1.recycle();
          throw paramString;
        }
      }
      
      public void prepareFromSearch(String paramString, Bundle paramBundle)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
          localParcel1.writeString(paramString);
          if (paramBundle != null)
          {
            localParcel1.writeInt(1);
            paramBundle.writeToParcel(localParcel1, 0);
          }
          else
          {
            localParcel1.writeInt(0);
          }
          mRemote.transact(35, localParcel1, localParcel2, 0);
          localParcel2.readException();
          localParcel2.recycle();
          localParcel1.recycle();
          return;
        }
        catch (Throwable paramString)
        {
          localParcel2.recycle();
          localParcel1.recycle();
          throw paramString;
        }
      }
      
      public void prepareFromUri(Uri paramUri, Bundle paramBundle)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
          if (paramUri != null)
          {
            localParcel1.writeInt(1);
            paramUri.writeToParcel(localParcel1, 0);
          }
          else
          {
            localParcel1.writeInt(0);
          }
          if (paramBundle != null)
          {
            localParcel1.writeInt(1);
            paramBundle.writeToParcel(localParcel1, 0);
          }
          else
          {
            localParcel1.writeInt(0);
          }
          mRemote.transact(36, localParcel1, localParcel2, 0);
          localParcel2.readException();
          localParcel2.recycle();
          localParcel1.recycle();
          return;
        }
        catch (Throwable paramUri)
        {
          localParcel2.recycle();
          localParcel1.recycle();
          throw paramUri;
        }
      }
      
      public void previous()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
          mRemote.transact(21, localParcel1, localParcel2, 0);
          localParcel2.readException();
          localParcel2.recycle();
          localParcel1.recycle();
          return;
        }
        catch (Throwable localThrowable)
        {
          localParcel2.recycle();
          localParcel1.recycle();
          throw localThrowable;
        }
      }
      
      public void rate(RatingCompat paramRatingCompat)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
          if (paramRatingCompat != null)
          {
            localParcel1.writeInt(1);
            paramRatingCompat.writeToParcel(localParcel1, 0);
          }
          else
          {
            localParcel1.writeInt(0);
          }
          mRemote.transact(25, localParcel1, localParcel2, 0);
          localParcel2.readException();
          localParcel2.recycle();
          localParcel1.recycle();
          return;
        }
        catch (Throwable paramRatingCompat)
        {
          localParcel2.recycle();
          localParcel1.recycle();
          throw paramRatingCompat;
        }
      }
      
      public void rateWithExtras(RatingCompat paramRatingCompat, Bundle paramBundle)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
          if (paramRatingCompat != null)
          {
            localParcel1.writeInt(1);
            paramRatingCompat.writeToParcel(localParcel1, 0);
          }
          else
          {
            localParcel1.writeInt(0);
          }
          if (paramBundle != null)
          {
            localParcel1.writeInt(1);
            paramBundle.writeToParcel(localParcel1, 0);
          }
          else
          {
            localParcel1.writeInt(0);
          }
          mRemote.transact(51, localParcel1, localParcel2, 0);
          localParcel2.readException();
          localParcel2.recycle();
          localParcel1.recycle();
          return;
        }
        catch (Throwable paramRatingCompat)
        {
          localParcel2.recycle();
          localParcel1.recycle();
          throw paramRatingCompat;
        }
      }
      
      public void registerCallbackListener(IMediaControllerCallback paramIMediaControllerCallback)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
          if (paramIMediaControllerCallback != null) {
            paramIMediaControllerCallback = paramIMediaControllerCallback.asBinder();
          } else {
            paramIMediaControllerCallback = null;
          }
          localParcel1.writeStrongBinder(paramIMediaControllerCallback);
          mRemote.transact(3, localParcel1, localParcel2, 0);
          localParcel2.readException();
          localParcel2.recycle();
          localParcel1.recycle();
          return;
        }
        catch (Throwable paramIMediaControllerCallback)
        {
          localParcel2.recycle();
          localParcel1.recycle();
          throw paramIMediaControllerCallback;
        }
      }
      
      public void removeQueueItem(MediaDescriptionCompat paramMediaDescriptionCompat)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
          if (paramMediaDescriptionCompat != null)
          {
            localParcel1.writeInt(1);
            paramMediaDescriptionCompat.writeToParcel(localParcel1, 0);
          }
          else
          {
            localParcel1.writeInt(0);
          }
          mRemote.transact(43, localParcel1, localParcel2, 0);
          localParcel2.readException();
          localParcel2.recycle();
          localParcel1.recycle();
          return;
        }
        catch (Throwable paramMediaDescriptionCompat)
        {
          localParcel2.recycle();
          localParcel1.recycle();
          throw paramMediaDescriptionCompat;
        }
      }
      
      public void removeQueueItemAt(int paramInt)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
          localParcel1.writeInt(paramInt);
          mRemote.transact(44, localParcel1, localParcel2, 0);
          localParcel2.readException();
          localParcel2.recycle();
          localParcel1.recycle();
          return;
        }
        catch (Throwable localThrowable)
        {
          localParcel2.recycle();
          localParcel1.recycle();
          throw localThrowable;
        }
      }
      
      public void rewind()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
          mRemote.transact(23, localParcel1, localParcel2, 0);
          localParcel2.readException();
          localParcel2.recycle();
          localParcel1.recycle();
          return;
        }
        catch (Throwable localThrowable)
        {
          localParcel2.recycle();
          localParcel1.recycle();
          throw localThrowable;
        }
      }
      
      public void seekTo(long paramLong)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
          localParcel1.writeLong(paramLong);
          mRemote.transact(24, localParcel1, localParcel2, 0);
          localParcel2.readException();
          localParcel2.recycle();
          localParcel1.recycle();
          return;
        }
        catch (Throwable localThrowable)
        {
          localParcel2.recycle();
          localParcel1.recycle();
          throw localThrowable;
        }
      }
      
      public void sendCommand(String paramString, Bundle paramBundle, MediaSessionCompat.ResultReceiverWrapper paramResultReceiverWrapper)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
          localParcel1.writeString(paramString);
          if (paramBundle != null)
          {
            localParcel1.writeInt(1);
            paramBundle.writeToParcel(localParcel1, 0);
          }
          else
          {
            localParcel1.writeInt(0);
          }
          if (paramResultReceiverWrapper != null)
          {
            localParcel1.writeInt(1);
            paramResultReceiverWrapper.writeToParcel(localParcel1, 0);
          }
          else
          {
            localParcel1.writeInt(0);
          }
          mRemote.transact(1, localParcel1, localParcel2, 0);
          localParcel2.readException();
          localParcel2.recycle();
          localParcel1.recycle();
          return;
        }
        catch (Throwable paramString)
        {
          localParcel2.recycle();
          localParcel1.recycle();
          throw paramString;
        }
      }
      
      public void sendCustomAction(String paramString, Bundle paramBundle)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
          localParcel1.writeString(paramString);
          if (paramBundle != null)
          {
            localParcel1.writeInt(1);
            paramBundle.writeToParcel(localParcel1, 0);
          }
          else
          {
            localParcel1.writeInt(0);
          }
          mRemote.transact(26, localParcel1, localParcel2, 0);
          localParcel2.readException();
          localParcel2.recycle();
          localParcel1.recycle();
          return;
        }
        catch (Throwable paramString)
        {
          localParcel2.recycle();
          localParcel1.recycle();
          throw paramString;
        }
      }
      
      public boolean sendMediaButton(KeyEvent paramKeyEvent)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
          boolean bool = true;
          if (paramKeyEvent != null)
          {
            localParcel1.writeInt(1);
            paramKeyEvent.writeToParcel(localParcel1, 0);
          }
          else
          {
            localParcel1.writeInt(0);
          }
          mRemote.transact(2, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int i = localParcel2.readInt();
          if (i == 0) {
            bool = false;
          }
          localParcel2.recycle();
          localParcel1.recycle();
          return bool;
        }
        catch (Throwable paramKeyEvent)
        {
          localParcel2.recycle();
          localParcel1.recycle();
          throw paramKeyEvent;
        }
      }
      
      public void setCaptioningEnabled(boolean paramBoolean)
        throws RemoteException
      {
        throw new Runtime("d2j fail translate: java.lang.RuntimeException: can not merge I and Z\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.copyTypes(TypeTransformer.java:311)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.fixTypes(TypeTransformer.java:226)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:207)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\n");
      }
      
      public void setRepeatMode(int paramInt)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
          localParcel1.writeInt(paramInt);
          mRemote.transact(39, localParcel1, localParcel2, 0);
          localParcel2.readException();
          localParcel2.recycle();
          localParcel1.recycle();
          return;
        }
        catch (Throwable localThrowable)
        {
          localParcel2.recycle();
          localParcel1.recycle();
          throw localThrowable;
        }
      }
      
      public void setShuffleMode(int paramInt)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
          localParcel1.writeInt(paramInt);
          mRemote.transact(48, localParcel1, localParcel2, 0);
          localParcel2.readException();
          localParcel2.recycle();
          localParcel1.recycle();
          return;
        }
        catch (Throwable localThrowable)
        {
          localParcel2.recycle();
          localParcel1.recycle();
          throw localThrowable;
        }
      }
      
      public void setShuffleModeEnabledDeprecated(boolean paramBoolean)
        throws RemoteException
      {
        throw new Runtime("d2j fail translate: java.lang.RuntimeException: can not merge I and Z\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.copyTypes(TypeTransformer.java:311)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.fixTypes(TypeTransformer.java:226)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:207)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\n");
      }
      
      public void setVolumeTo(int paramInt1, int paramInt2, String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
          localParcel1.writeInt(paramInt1);
          localParcel1.writeInt(paramInt2);
          localParcel1.writeString(paramString);
          mRemote.transact(12, localParcel1, localParcel2, 0);
          localParcel2.readException();
          localParcel2.recycle();
          localParcel1.recycle();
          return;
        }
        catch (Throwable paramString)
        {
          localParcel2.recycle();
          localParcel1.recycle();
          throw paramString;
        }
      }
      
      public void skipToQueueItem(long paramLong)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
          localParcel1.writeLong(paramLong);
          mRemote.transact(17, localParcel1, localParcel2, 0);
          localParcel2.readException();
          localParcel2.recycle();
          localParcel1.recycle();
          return;
        }
        catch (Throwable localThrowable)
        {
          localParcel2.recycle();
          localParcel1.recycle();
          throw localThrowable;
        }
      }
      
      public void stop()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
          mRemote.transact(19, localParcel1, localParcel2, 0);
          localParcel2.readException();
          localParcel2.recycle();
          localParcel1.recycle();
          return;
        }
        catch (Throwable localThrowable)
        {
          localParcel2.recycle();
          localParcel1.recycle();
          throw localThrowable;
        }
      }
      
      public void unregisterCallbackListener(IMediaControllerCallback paramIMediaControllerCallback)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
          if (paramIMediaControllerCallback != null) {
            paramIMediaControllerCallback = paramIMediaControllerCallback.asBinder();
          } else {
            paramIMediaControllerCallback = null;
          }
          localParcel1.writeStrongBinder(paramIMediaControllerCallback);
          mRemote.transact(4, localParcel1, localParcel2, 0);
          localParcel2.readException();
          localParcel2.recycle();
          localParcel1.recycle();
          return;
        }
        catch (Throwable paramIMediaControllerCallback)
        {
          localParcel2.recycle();
          localParcel1.recycle();
          throw paramIMediaControllerCallback;
        }
      }
    }
  }
}
