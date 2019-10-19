package android.support.v4.media;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.BadParcelableException;
import android.os.Binder;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.os.RemoteException;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.v4.media.session.IMediaSession;
import android.support.v4.media.session.IMediaSession.Stub;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.MediaSessionCompat.Token;
import android.support.v4.os.ResultReceiver;
import android.support.v4.package_7.BundleCompat;
import android.support.v4.util.ArrayMap;
import android.support.v4.util.SimpleArrayMap;
import android.text.TextUtils;
import android.util.Log;
import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

public final class MediaBrowserCompat
{
  public static final String CUSTOM_ACTION_DOWNLOAD = "android.support.v4.media.action.DOWNLOAD";
  public static final String CUSTOM_ACTION_REMOVE_DOWNLOADED_FILE = "android.support.v4.media.action.REMOVE_DOWNLOADED_FILE";
  static final boolean DEBUG = Log.isLoggable("MediaBrowserCompat", 3);
  public static final String EXTRA_DOWNLOAD_PROGRESS = "android.media.browse.extra.DOWNLOAD_PROGRESS";
  public static final String EXTRA_MEDIA_ID = "android.media.browse.extra.MEDIA_ID";
  public static final String EXTRA_PAGE = "android.media.browse.extra.PAGE";
  public static final String EXTRA_PAGE_SIZE = "android.media.browse.extra.PAGE_SIZE";
  static final String TAG = "MediaBrowserCompat";
  private final MediaBrowserImpl mImpl;
  
  public MediaBrowserCompat(Context paramContext, ComponentName paramComponentName, ConnectionCallback paramConnectionCallback, Bundle paramBundle)
  {
    if (Build.VERSION.SDK_INT >= 26)
    {
      mImpl = new MediaBrowserImplApi24(paramContext, paramComponentName, paramConnectionCallback, paramBundle);
      return;
    }
    if (Build.VERSION.SDK_INT >= 23)
    {
      mImpl = new MediaBrowserImplApi23(paramContext, paramComponentName, paramConnectionCallback, paramBundle);
      return;
    }
    if (Build.VERSION.SDK_INT >= 21)
    {
      mImpl = new MediaBrowserImplApi21(paramContext, paramComponentName, paramConnectionCallback, paramBundle);
      return;
    }
    mImpl = new MediaBrowserImplBase(paramContext, paramComponentName, paramConnectionCallback, paramBundle);
  }
  
  public void connect()
  {
    mImpl.connect();
  }
  
  public void disconnect()
  {
    mImpl.disconnect();
  }
  
  public Bundle getExtras()
  {
    return mImpl.getExtras();
  }
  
  public void getItem(String paramString, ItemCallback paramItemCallback)
  {
    mImpl.getItem(paramString, paramItemCallback);
  }
  
  public String getRoot()
  {
    return mImpl.getRoot();
  }
  
  public ComponentName getServiceComponent()
  {
    return mImpl.getServiceComponent();
  }
  
  public MediaSessionCompat.Token getSessionToken()
  {
    return mImpl.getSessionToken();
  }
  
  public boolean isConnected()
  {
    return mImpl.isConnected();
  }
  
  public void search(String paramString, Bundle paramBundle, SearchCallback paramSearchCallback)
  {
    if (!TextUtils.isEmpty(paramString))
    {
      if (paramSearchCallback != null)
      {
        mImpl.search(paramString, paramBundle, paramSearchCallback);
        return;
      }
      throw new IllegalArgumentException("callback cannot be null");
    }
    throw new IllegalArgumentException("query cannot be empty");
  }
  
  public void sendCustomAction(String paramString, Bundle paramBundle, CustomActionCallback paramCustomActionCallback)
  {
    if (!TextUtils.isEmpty(paramString))
    {
      mImpl.sendCustomAction(paramString, paramBundle, paramCustomActionCallback);
      return;
    }
    throw new IllegalArgumentException("action cannot be empty");
  }
  
  public void subscribe(String paramString, Bundle paramBundle, SubscriptionCallback paramSubscriptionCallback)
  {
    if (!TextUtils.isEmpty(paramString))
    {
      if (paramSubscriptionCallback != null)
      {
        if (paramBundle != null)
        {
          mImpl.subscribe(paramString, paramBundle, paramSubscriptionCallback);
          return;
        }
        throw new IllegalArgumentException("options are null");
      }
      throw new IllegalArgumentException("callback is null");
    }
    throw new IllegalArgumentException("parentId is empty");
  }
  
  public void subscribe(String paramString, SubscriptionCallback paramSubscriptionCallback)
  {
    if (!TextUtils.isEmpty(paramString))
    {
      if (paramSubscriptionCallback != null)
      {
        mImpl.subscribe(paramString, null, paramSubscriptionCallback);
        return;
      }
      throw new IllegalArgumentException("callback is null");
    }
    throw new IllegalArgumentException("parentId is empty");
  }
  
  public void unsubscribe(String paramString)
  {
    if (!TextUtils.isEmpty(paramString))
    {
      mImpl.unsubscribe(paramString, null);
      return;
    }
    throw new IllegalArgumentException("parentId is empty");
  }
  
  public void unsubscribe(String paramString, SubscriptionCallback paramSubscriptionCallback)
  {
    if (!TextUtils.isEmpty(paramString))
    {
      if (paramSubscriptionCallback != null)
      {
        mImpl.unsubscribe(paramString, paramSubscriptionCallback);
        return;
      }
      throw new IllegalArgumentException("callback is null");
    }
    throw new IllegalArgumentException("parentId is empty");
  }
  
  private static class CallbackHandler
    extends Handler
  {
    private final WeakReference<MediaBrowserCompat.MediaBrowserServiceCallbackImpl> mCallbackImplRef;
    private WeakReference<Messenger> mCallbacksMessengerRef;
    
    CallbackHandler(MediaBrowserCompat.MediaBrowserServiceCallbackImpl paramMediaBrowserServiceCallbackImpl)
    {
      mCallbackImplRef = new WeakReference(paramMediaBrowserServiceCallbackImpl);
    }
    
    public void handleMessage(Message paramMessage)
    {
      MediaBrowserCompat.MediaBrowserServiceCallbackImpl localMediaBrowserServiceCallbackImpl;
      Messenger localMessenger;
      if (mCallbacksMessengerRef != null)
      {
        if (mCallbacksMessengerRef.get() == null) {
          return;
        }
        if (mCallbackImplRef.get() == null) {
          return;
        }
        localObject1 = paramMessage.getData();
        ((Bundle)localObject1).setClassLoader(MediaSessionCompat.class.getClassLoader());
        localMediaBrowserServiceCallbackImpl = (MediaBrowserCompat.MediaBrowserServiceCallbackImpl)mCallbackImplRef.get();
        localMessenger = (Messenger)mCallbacksMessengerRef.get();
        switch (what)
        {
        default: 
          break;
        }
      }
      try
      {
        localMediaBrowserServiceCallbackImpl.onLoadChildren(localMessenger, ((Bundle)localObject1).getString("data_media_item_id"), ((Bundle)localObject1).getParcelableArrayList("data_media_item_list"), ((Bundle)localObject1).getBundle("data_options"));
        return;
      }
      catch (BadParcelableException localBadParcelableException)
      {
        String str;
        Object localObject2;
        int i;
        for (;;) {}
      }
      localMediaBrowserServiceCallbackImpl.onConnectionFailed(localMessenger);
      return;
      str = ((Bundle)localObject1).getString("data_media_item_id");
      localObject2 = ((Bundle)localObject1).getParcelable("data_media_session_token");
      localObject2 = (MediaSessionCompat.Token)localObject2;
      localMediaBrowserServiceCallbackImpl.onServiceConnected(localMessenger, str, (MediaSessionCompat.Token)localObject2, ((Bundle)localObject1).getBundle("data_root_hints"));
      return;
      Object localObject1 = new StringBuilder();
      ((StringBuilder)localObject1).append("Unhandled message: ");
      ((StringBuilder)localObject1).append(paramMessage);
      ((StringBuilder)localObject1).append("\n  Client version: ");
      ((StringBuilder)localObject1).append(1);
      ((StringBuilder)localObject1).append("\n  Service version: ");
      i = arg1;
      ((StringBuilder)localObject1).append(i);
      Log.w("MediaBrowserCompat", ((StringBuilder)localObject1).toString());
      return;
      Log.e("MediaBrowserCompat", "Could not unparcel the data.");
      if (what == 1)
      {
        localMediaBrowserServiceCallbackImpl.onConnectionFailed(localMessenger);
        return;
      }
    }
    
    void setCallbacksMessenger(Messenger paramMessenger)
    {
      mCallbacksMessengerRef = new WeakReference(paramMessenger);
    }
  }
  
  public static class ConnectionCallback
  {
    ConnectionCallbackInternal mConnectionCallbackInternal;
    final Object mConnectionCallbackObj;
    
    public ConnectionCallback()
    {
      if (Build.VERSION.SDK_INT >= 21)
      {
        mConnectionCallbackObj = MediaBrowserCompatApi21.createConnectionCallback(new StubApi21());
        return;
      }
      mConnectionCallbackObj = null;
    }
    
    public void onConnected() {}
    
    public void onConnectionFailed() {}
    
    public void onConnectionSuspended() {}
    
    void setInternalConnectionCallback(ConnectionCallbackInternal paramConnectionCallbackInternal)
    {
      mConnectionCallbackInternal = paramConnectionCallbackInternal;
    }
    
    static abstract interface ConnectionCallbackInternal
    {
      public abstract void onConnected();
      
      public abstract void onConnectionFailed();
      
      public abstract void onConnectionSuspended();
    }
    
    private class StubApi21
      implements MediaBrowserCompatApi21.ConnectionCallback
    {
      StubApi21() {}
      
      public void onConnected()
      {
        if (mConnectionCallbackInternal != null) {
          mConnectionCallbackInternal.onConnected();
        }
        MediaBrowserCompat.ConnectionCallback.this.onConnected();
      }
      
      public void onConnectionFailed()
      {
        if (mConnectionCallbackInternal != null) {
          mConnectionCallbackInternal.onConnectionFailed();
        }
        MediaBrowserCompat.ConnectionCallback.this.onConnectionFailed();
      }
      
      public void onConnectionSuspended()
      {
        if (mConnectionCallbackInternal != null) {
          mConnectionCallbackInternal.onConnectionSuspended();
        }
        MediaBrowserCompat.ConnectionCallback.this.onConnectionSuspended();
      }
    }
  }
  
  public static abstract class CustomActionCallback
  {
    public CustomActionCallback() {}
    
    public void onError(String paramString, Bundle paramBundle1, Bundle paramBundle2) {}
    
    public void onProgressUpdate(String paramString, Bundle paramBundle1, Bundle paramBundle2) {}
    
    public void onResult(String paramString, Bundle paramBundle1, Bundle paramBundle2) {}
  }
  
  private static class CustomActionResultReceiver
    extends ResultReceiver
  {
    private final String mAction;
    private final MediaBrowserCompat.CustomActionCallback mCallback;
    private final Bundle mExtras;
    
    CustomActionResultReceiver(String paramString, Bundle paramBundle, MediaBrowserCompat.CustomActionCallback paramCustomActionCallback, Handler paramHandler)
    {
      super();
      mAction = paramString;
      mExtras = paramBundle;
      mCallback = paramCustomActionCallback;
    }
    
    protected void onReceiveResult(int paramInt, Bundle paramBundle)
    {
      if (mCallback == null) {
        return;
      }
      switch (paramInt)
      {
      default: 
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("Unknown result code: ");
        localStringBuilder.append(paramInt);
        localStringBuilder.append(" (extras=");
        localStringBuilder.append(mExtras);
        localStringBuilder.append(", resultData=");
        localStringBuilder.append(paramBundle);
        localStringBuilder.append(")");
        Log.w("MediaBrowserCompat", localStringBuilder.toString());
        return;
      case 1: 
        mCallback.onProgressUpdate(mAction, mExtras, paramBundle);
        return;
      case 0: 
        mCallback.onResult(mAction, mExtras, paramBundle);
        return;
      }
      mCallback.onError(mAction, mExtras, paramBundle);
    }
  }
  
  public static abstract class ItemCallback
  {
    final Object mItemCallbackObj;
    
    public ItemCallback()
    {
      if (Build.VERSION.SDK_INT >= 23)
      {
        mItemCallbackObj = MediaBrowserCompatApi23.createItemCallback(new StubApi23());
        return;
      }
      mItemCallbackObj = null;
    }
    
    public void onError(String paramString) {}
    
    public void onItemLoaded(MediaBrowserCompat.MediaItem paramMediaItem) {}
    
    private class StubApi23
      implements MediaBrowserCompatApi23.ItemCallback
    {
      StubApi23() {}
      
      public void onError(String paramString)
      {
        MediaBrowserCompat.ItemCallback.this.onError(paramString);
      }
      
      public void onItemLoaded(Parcel paramParcel)
      {
        if (paramParcel == null)
        {
          onItemLoaded(null);
          return;
        }
        paramParcel.setDataPosition(0);
        MediaBrowserCompat.MediaItem localMediaItem = (MediaBrowserCompat.MediaItem)MediaBrowserCompat.MediaItem.CREATOR.createFromParcel(paramParcel);
        paramParcel.recycle();
        onItemLoaded(localMediaItem);
      }
    }
  }
  
  private static class ItemReceiver
    extends ResultReceiver
  {
    private final MediaBrowserCompat.ItemCallback mCallback;
    private final String mMediaId;
    
    ItemReceiver(String paramString, MediaBrowserCompat.ItemCallback paramItemCallback, Handler paramHandler)
    {
      super();
      mMediaId = paramString;
      mCallback = paramItemCallback;
    }
    
    protected void onReceiveResult(int paramInt, Bundle paramBundle)
    {
      if (paramBundle != null) {
        paramBundle.setClassLoader(MediaBrowserCompat.class.getClassLoader());
      }
      if ((paramInt == 0) && (paramBundle != null) && (paramBundle.containsKey("media_item")))
      {
        paramBundle = paramBundle.getParcelable("media_item");
        if ((paramBundle != null) && (!(paramBundle instanceof MediaBrowserCompat.MediaItem)))
        {
          mCallback.onError(mMediaId);
          return;
        }
        mCallback.onItemLoaded((MediaBrowserCompat.MediaItem)paramBundle);
        return;
      }
      mCallback.onError(mMediaId);
    }
  }
  
  static abstract interface MediaBrowserImpl
  {
    public abstract void connect();
    
    public abstract void disconnect();
    
    public abstract Bundle getExtras();
    
    public abstract void getItem(String paramString, MediaBrowserCompat.ItemCallback paramItemCallback);
    
    public abstract String getRoot();
    
    public abstract ComponentName getServiceComponent();
    
    public abstract MediaSessionCompat.Token getSessionToken();
    
    public abstract boolean isConnected();
    
    public abstract void search(String paramString, Bundle paramBundle, MediaBrowserCompat.SearchCallback paramSearchCallback);
    
    public abstract void sendCustomAction(String paramString, Bundle paramBundle, MediaBrowserCompat.CustomActionCallback paramCustomActionCallback);
    
    public abstract void subscribe(String paramString, Bundle paramBundle, MediaBrowserCompat.SubscriptionCallback paramSubscriptionCallback);
    
    public abstract void unsubscribe(String paramString, MediaBrowserCompat.SubscriptionCallback paramSubscriptionCallback);
  }
  
  @RequiresApi(21)
  static class MediaBrowserImplApi21
    implements MediaBrowserCompat.MediaBrowserImpl, MediaBrowserCompat.MediaBrowserServiceCallbackImpl, MediaBrowserCompat.ConnectionCallback.ConnectionCallbackInternal
  {
    protected final Object mBrowserObj;
    protected Messenger mCallbacksMessenger;
    final Context mContext;
    protected final MediaBrowserCompat.CallbackHandler mHandler = new MediaBrowserCompat.CallbackHandler(this);
    private MediaSessionCompat.Token mMediaSessionToken;
    protected final Bundle mRootHints;
    protected MediaBrowserCompat.ServiceBinderWrapper mServiceBinderWrapper;
    private final ArrayMap<String, MediaBrowserCompat.Subscription> mSubscriptions = new ArrayMap();
    
    public MediaBrowserImplApi21(Context paramContext, ComponentName paramComponentName, MediaBrowserCompat.ConnectionCallback paramConnectionCallback, Bundle paramBundle)
    {
      mContext = paramContext;
      Bundle localBundle = paramBundle;
      if (paramBundle == null) {
        localBundle = new Bundle();
      }
      localBundle.putInt("extra_client_version", 1);
      mRootHints = new Bundle(localBundle);
      paramConnectionCallback.setInternalConnectionCallback(this);
      mBrowserObj = MediaBrowserCompatApi21.createBrowser(paramContext, paramComponentName, mConnectionCallbackObj, mRootHints);
    }
    
    public void connect()
    {
      MediaBrowserCompatApi21.connect(mBrowserObj);
    }
    
    public void disconnect()
    {
      MediaBrowserCompat.ServiceBinderWrapper localServiceBinderWrapper;
      Messenger localMessenger;
      if ((mServiceBinderWrapper != null) && (mCallbacksMessenger != null))
      {
        localServiceBinderWrapper = mServiceBinderWrapper;
        localMessenger = mCallbacksMessenger;
      }
      try
      {
        localServiceBinderWrapper.unregisterCallbackMessenger(localMessenger);
      }
      catch (RemoteException localRemoteException)
      {
        for (;;) {}
      }
      Log.i("MediaBrowserCompat", "Remote error unregistering client messenger.");
      MediaBrowserCompatApi21.disconnect(mBrowserObj);
    }
    
    public Bundle getExtras()
    {
      return MediaBrowserCompatApi21.getExtras(mBrowserObj);
    }
    
    public void getItem(final String paramString, final MediaBrowserCompat.ItemCallback paramItemCallback)
    {
      MediaBrowserCompat.ServiceBinderWrapper localServiceBinderWrapper;
      Messenger localMessenger;
      if (!TextUtils.isEmpty(paramString)) {
        if (paramItemCallback != null)
        {
          if (!MediaBrowserCompatApi21.isConnected(mBrowserObj))
          {
            Log.i("MediaBrowserCompat", "Not connected, unable to retrieve the MediaItem.");
            mHandler.post(new Runnable()
            {
              public void run()
              {
                paramItemCallback.onError(paramString);
              }
            });
            return;
          }
          if (mServiceBinderWrapper == null)
          {
            mHandler.post(new Runnable()
            {
              public void run()
              {
                paramItemCallback.onError(paramString);
              }
            });
            return;
          }
          localObject = new MediaBrowserCompat.ItemReceiver(paramString, paramItemCallback, mHandler);
          localServiceBinderWrapper = mServiceBinderWrapper;
          localMessenger = mCallbacksMessenger;
        }
      }
      try
      {
        localServiceBinderWrapper.getMediaItem(paramString, (ResultReceiver)localObject, localMessenger);
        return;
      }
      catch (RemoteException localRemoteException)
      {
        for (;;) {}
      }
      Object localObject = new StringBuilder();
      ((StringBuilder)localObject).append("Remote error getting media item: ");
      ((StringBuilder)localObject).append(paramString);
      Log.i("MediaBrowserCompat", ((StringBuilder)localObject).toString());
      mHandler.post(new Runnable()
      {
        public void run()
        {
          paramItemCallback.onError(paramString);
        }
      });
      return;
      throw new IllegalArgumentException("cb is null");
      throw new IllegalArgumentException("mediaId is empty");
    }
    
    public String getRoot()
    {
      return MediaBrowserCompatApi21.getRoot(mBrowserObj);
    }
    
    public ComponentName getServiceComponent()
    {
      return MediaBrowserCompatApi21.getServiceComponent(mBrowserObj);
    }
    
    public MediaSessionCompat.Token getSessionToken()
    {
      if (mMediaSessionToken == null) {
        mMediaSessionToken = MediaSessionCompat.Token.fromToken(MediaBrowserCompatApi21.getSessionToken(mBrowserObj));
      }
      return mMediaSessionToken;
    }
    
    public boolean isConnected()
    {
      return MediaBrowserCompatApi21.isConnected(mBrowserObj);
    }
    
    public void onConnected()
    {
      Object localObject1 = MediaBrowserCompatApi21.getExtras(mBrowserObj);
      if (localObject1 == null) {
        return;
      }
      Object localObject2 = BundleCompat.getBinder((Bundle)localObject1, "extra_messenger");
      Messenger localMessenger;
      if (localObject2 != null)
      {
        mServiceBinderWrapper = new MediaBrowserCompat.ServiceBinderWrapper((IBinder)localObject2, mRootHints);
        mCallbacksMessenger = new Messenger(mHandler);
        mHandler.setCallbacksMessenger(mCallbacksMessenger);
        localObject2 = mServiceBinderWrapper;
        localMessenger = mCallbacksMessenger;
      }
      try
      {
        ((MediaBrowserCompat.ServiceBinderWrapper)localObject2).registerCallbackMessenger(localMessenger);
      }
      catch (RemoteException localRemoteException)
      {
        for (;;) {}
      }
      Log.i("MediaBrowserCompat", "Remote error registering client messenger.");
      localObject1 = IMediaSession.Stub.asInterface(BundleCompat.getBinder((Bundle)localObject1, "extra_session_binder"));
      if (localObject1 != null)
      {
        mMediaSessionToken = MediaSessionCompat.Token.fromToken(MediaBrowserCompatApi21.getSessionToken(mBrowserObj), (IMediaSession)localObject1);
        return;
      }
    }
    
    public void onConnectionFailed() {}
    
    public void onConnectionFailed(Messenger paramMessenger) {}
    
    public void onConnectionSuspended()
    {
      mServiceBinderWrapper = null;
      mCallbacksMessenger = null;
      mMediaSessionToken = null;
      mHandler.setCallbacksMessenger(null);
    }
    
    public void onLoadChildren(Messenger paramMessenger, String paramString, List paramList, Bundle paramBundle)
    {
      if (mCallbacksMessenger != paramMessenger) {
        return;
      }
      paramMessenger = (MediaBrowserCompat.Subscription)mSubscriptions.get(paramString);
      if (paramMessenger == null)
      {
        if (MediaBrowserCompat.DEBUG)
        {
          paramMessenger = new StringBuilder();
          paramMessenger.append("onLoadChildren for id that isn't subscribed id=");
          paramMessenger.append(paramString);
          Log.d("MediaBrowserCompat", paramMessenger.toString());
        }
      }
      else
      {
        paramMessenger = paramMessenger.getCallback(mContext, paramBundle);
        if (paramMessenger != null)
        {
          if (paramBundle == null)
          {
            if (paramList == null)
            {
              paramMessenger.onError(paramString);
              return;
            }
            paramMessenger.onChildrenLoaded(paramString, paramList);
            return;
          }
          if (paramList == null)
          {
            paramMessenger.onError(paramString, paramBundle);
            return;
          }
          paramMessenger.onChildrenLoaded(paramString, paramList, paramBundle);
        }
      }
    }
    
    public void onServiceConnected(Messenger paramMessenger, String paramString, MediaSessionCompat.Token paramToken, Bundle paramBundle) {}
    
    public void search(final String paramString, final Bundle paramBundle, final MediaBrowserCompat.SearchCallback paramSearchCallback)
    {
      if (isConnected())
      {
        if (mServiceBinderWrapper == null)
        {
          Log.i("MediaBrowserCompat", "The connected service doesn't support search.");
          mHandler.post(new Runnable()
          {
            public void run()
            {
              paramSearchCallback.onError(paramString, paramBundle);
            }
          });
          return;
        }
        MediaBrowserCompat.SearchResultReceiver localSearchResultReceiver = new MediaBrowserCompat.SearchResultReceiver(paramString, paramBundle, paramSearchCallback, mHandler);
        Object localObject = mServiceBinderWrapper;
        Messenger localMessenger = mCallbacksMessenger;
        try
        {
          ((MediaBrowserCompat.ServiceBinderWrapper)localObject).search(paramString, paramBundle, localSearchResultReceiver, localMessenger);
          return;
        }
        catch (RemoteException localRemoteException)
        {
          localObject = new StringBuilder();
          ((StringBuilder)localObject).append("Remote error searching items with query: ");
          ((StringBuilder)localObject).append(paramString);
          Log.i("MediaBrowserCompat", ((StringBuilder)localObject).toString(), localRemoteException);
          mHandler.post(new Runnable()
          {
            public void run()
            {
              paramSearchCallback.onError(paramString, paramBundle);
            }
          });
          return;
        }
      }
      throw new IllegalStateException("search() called while not connected");
    }
    
    public void sendCustomAction(final String paramString, final Bundle paramBundle, final MediaBrowserCompat.CustomActionCallback paramCustomActionCallback)
    {
      if (isConnected())
      {
        if (mServiceBinderWrapper == null)
        {
          Log.i("MediaBrowserCompat", "The connected service doesn't support sendCustomAction.");
          if (paramCustomActionCallback != null) {
            mHandler.post(new Runnable()
            {
              public void run()
              {
                paramCustomActionCallback.onError(paramString, paramBundle, null);
              }
            });
          }
        }
        MediaBrowserCompat.CustomActionResultReceiver localCustomActionResultReceiver = new MediaBrowserCompat.CustomActionResultReceiver(paramString, paramBundle, paramCustomActionCallback, mHandler);
        Object localObject = mServiceBinderWrapper;
        Messenger localMessenger = mCallbacksMessenger;
        try
        {
          ((MediaBrowserCompat.ServiceBinderWrapper)localObject).sendCustomAction(paramString, paramBundle, localCustomActionResultReceiver, localMessenger);
          return;
        }
        catch (RemoteException localRemoteException)
        {
          localObject = new StringBuilder();
          ((StringBuilder)localObject).append("Remote error sending a custom action: action=");
          ((StringBuilder)localObject).append(paramString);
          ((StringBuilder)localObject).append(", extras=");
          ((StringBuilder)localObject).append(paramBundle);
          Log.i("MediaBrowserCompat", ((StringBuilder)localObject).toString(), localRemoteException);
          if (paramCustomActionCallback == null) {
            return;
          }
        }
        mHandler.post(new Runnable()
        {
          public void run()
          {
            paramCustomActionCallback.onError(paramString, paramBundle, null);
          }
        });
      }
      else
      {
        paramCustomActionCallback = new StringBuilder();
        paramCustomActionCallback.append("Cannot send a custom action (");
        paramCustomActionCallback.append(paramString);
        paramCustomActionCallback.append(") with ");
        paramCustomActionCallback.append("extras ");
        paramCustomActionCallback.append(paramBundle);
        paramCustomActionCallback.append(" because the browser is not connected to the ");
        paramCustomActionCallback.append("service.");
        throw new IllegalStateException(paramCustomActionCallback.toString());
      }
    }
    
    public void subscribe(String paramString, Bundle paramBundle, MediaBrowserCompat.SubscriptionCallback paramSubscriptionCallback)
    {
      Object localObject2 = (MediaBrowserCompat.Subscription)mSubscriptions.get(paramString);
      Object localObject1 = localObject2;
      if (localObject2 == null)
      {
        localObject1 = new MediaBrowserCompat.Subscription();
        mSubscriptions.put(paramString, localObject1);
      }
      MediaBrowserCompat.SubscriptionCallback.access$100(paramSubscriptionCallback, (MediaBrowserCompat.Subscription)localObject1);
      if (paramBundle == null) {
        paramBundle = null;
      } else {
        paramBundle = new Bundle(paramBundle);
      }
      ((MediaBrowserCompat.Subscription)localObject1).putCallback(mContext, paramBundle, paramSubscriptionCallback);
      if (mServiceBinderWrapper == null)
      {
        MediaBrowserCompatApi21.subscribe(mBrowserObj, paramString, MediaBrowserCompat.SubscriptionCallback.access$200(paramSubscriptionCallback));
        return;
      }
      localObject1 = mServiceBinderWrapper;
      try
      {
        paramSubscriptionCallback = MediaBrowserCompat.SubscriptionCallback.access$000(paramSubscriptionCallback);
        localObject2 = mCallbacksMessenger;
        ((MediaBrowserCompat.ServiceBinderWrapper)localObject1).addSubscription(paramString, paramSubscriptionCallback, paramBundle, (Messenger)localObject2);
        return;
      }
      catch (RemoteException paramBundle)
      {
        for (;;) {}
      }
      paramBundle = new StringBuilder();
      paramBundle.append("Remote error subscribing media item: ");
      paramBundle.append(paramString);
      Log.i("MediaBrowserCompat", paramBundle.toString());
    }
    
    public void unsubscribe(String paramString, MediaBrowserCompat.SubscriptionCallback paramSubscriptionCallback)
    {
      MediaBrowserCompat.Subscription localSubscription = (MediaBrowserCompat.Subscription)mSubscriptions.get(paramString);
      if (localSubscription == null) {
        return;
      }
      if (mServiceBinderWrapper == null)
      {
        if (paramSubscriptionCallback == null)
        {
          MediaBrowserCompatApi21.unsubscribe(mBrowserObj, paramString);
        }
        else
        {
          localObject1 = localSubscription.getCallbacks();
          localObject2 = localSubscription.getOptionsList();
          i = ((List)localObject1).size() - 1;
          while (i >= 0)
          {
            if (((List)localObject1).get(i) == paramSubscriptionCallback)
            {
              ((List)localObject1).remove(i);
              ((List)localObject2).remove(i);
            }
            i -= 1;
          }
          if (((List)localObject1).size() == 0) {
            MediaBrowserCompatApi21.unsubscribe(mBrowserObj, paramString);
          }
        }
      }
      else if (paramSubscriptionCallback == null)
      {
        localObject1 = mServiceBinderWrapper;
        localObject2 = mCallbacksMessenger;
        for (;;) {}
      }
      try
      {
        ((MediaBrowserCompat.ServiceBinderWrapper)localObject1).removeSubscription(paramString, null, (Messenger)localObject2);
      }
      catch (RemoteException localRemoteException)
      {
        Object localObject3;
        IBinder localIBinder;
        Messenger localMessenger;
        for (;;) {}
      }
      Object localObject1 = localSubscription.getCallbacks();
      Object localObject2 = localSubscription.getOptionsList();
      int i = ((List)localObject1).size();
      i -= 1;
      label181:
      if (i >= 0)
      {
        localObject3 = ((List)localObject1).get(i);
        if (localObject3 == paramSubscriptionCallback)
        {
          localObject3 = mServiceBinderWrapper;
          localIBinder = MediaBrowserCompat.SubscriptionCallback.access$000(paramSubscriptionCallback);
          localMessenger = mCallbacksMessenger;
          ((MediaBrowserCompat.ServiceBinderWrapper)localObject3).removeSubscription(paramString, localIBinder, localMessenger);
          ((List)localObject1).remove(i);
          ((List)localObject2).remove(i);
        }
        i -= 1;
        break label181;
        localObject1 = new StringBuilder();
        ((StringBuilder)localObject1).append("removeSubscription failed with RemoteException parentId=");
        ((StringBuilder)localObject1).append(paramString);
        Log.d("MediaBrowserCompat", ((StringBuilder)localObject1).toString());
      }
      if ((localSubscription.isEmpty()) || (paramSubscriptionCallback == null))
      {
        mSubscriptions.remove(paramString);
        return;
      }
    }
  }
  
  @RequiresApi(23)
  static class MediaBrowserImplApi23
    extends MediaBrowserCompat.MediaBrowserImplApi21
  {
    public MediaBrowserImplApi23(Context paramContext, ComponentName paramComponentName, MediaBrowserCompat.ConnectionCallback paramConnectionCallback, Bundle paramBundle)
    {
      super(paramComponentName, paramConnectionCallback, paramBundle);
    }
    
    public void getItem(String paramString, MediaBrowserCompat.ItemCallback paramItemCallback)
    {
      if (mServiceBinderWrapper == null)
      {
        MediaBrowserCompatApi23.getItem(mBrowserObj, paramString, mItemCallbackObj);
        return;
      }
      super.getItem(paramString, paramItemCallback);
    }
  }
  
  @RequiresApi(26)
  static class MediaBrowserImplApi24
    extends MediaBrowserCompat.MediaBrowserImplApi23
  {
    public MediaBrowserImplApi24(Context paramContext, ComponentName paramComponentName, MediaBrowserCompat.ConnectionCallback paramConnectionCallback, Bundle paramBundle)
    {
      super(paramComponentName, paramConnectionCallback, paramBundle);
    }
    
    public void subscribe(String paramString, Bundle paramBundle, MediaBrowserCompat.SubscriptionCallback paramSubscriptionCallback)
    {
      if (paramBundle == null)
      {
        MediaBrowserCompatApi21.subscribe(mBrowserObj, paramString, MediaBrowserCompat.SubscriptionCallback.access$200(paramSubscriptionCallback));
        return;
      }
      MediaBrowserCompatApi24.subscribe(mBrowserObj, paramString, paramBundle, MediaBrowserCompat.SubscriptionCallback.access$200(paramSubscriptionCallback));
    }
    
    public void unsubscribe(String paramString, MediaBrowserCompat.SubscriptionCallback paramSubscriptionCallback)
    {
      if (paramSubscriptionCallback == null)
      {
        MediaBrowserCompatApi21.unsubscribe(mBrowserObj, paramString);
        return;
      }
      MediaBrowserCompatApi24.unsubscribe(mBrowserObj, paramString, MediaBrowserCompat.SubscriptionCallback.access$200(paramSubscriptionCallback));
    }
  }
  
  static class MediaBrowserImplBase
    implements MediaBrowserCompat.MediaBrowserImpl, MediaBrowserCompat.MediaBrowserServiceCallbackImpl
  {
    static final int CONNECT_STATE_CONNECTED = 3;
    static final int CONNECT_STATE_CONNECTING = 2;
    static final int CONNECT_STATE_DISCONNECTED = 1;
    static final int CONNECT_STATE_DISCONNECTING = 0;
    static final int CONNECT_STATE_SUSPENDED = 4;
    final MediaBrowserCompat.ConnectionCallback mCallback;
    Messenger mCallbacksMessenger;
    final Context mContext;
    private Bundle mExtras;
    final MediaBrowserCompat.CallbackHandler mHandler = new MediaBrowserCompat.CallbackHandler(this);
    private MediaSessionCompat.Token mMediaSessionToken;
    final Bundle mRootHints;
    private String mRootId;
    MediaBrowserCompat.ServiceBinderWrapper mServiceBinderWrapper;
    final ComponentName mServiceComponent;
    MediaServiceConnection mServiceConnection;
    int mState = 1;
    private final ArrayMap<String, MediaBrowserCompat.Subscription> mSubscriptions = new ArrayMap();
    
    public MediaBrowserImplBase(Context paramContext, ComponentName paramComponentName, MediaBrowserCompat.ConnectionCallback paramConnectionCallback, Bundle paramBundle)
    {
      if (paramContext != null)
      {
        if (paramComponentName != null)
        {
          if (paramConnectionCallback != null)
          {
            mContext = paramContext;
            mServiceComponent = paramComponentName;
            mCallback = paramConnectionCallback;
            if (paramBundle == null) {
              paramContext = null;
            } else {
              paramContext = new Bundle(paramBundle);
            }
            mRootHints = paramContext;
            return;
          }
          throw new IllegalArgumentException("connection callback must not be null");
        }
        throw new IllegalArgumentException("service component must not be null");
      }
      throw new IllegalArgumentException("context must not be null");
    }
    
    private static String getStateLabel(int paramInt)
    {
      switch (paramInt)
      {
      default: 
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("UNKNOWN/");
        localStringBuilder.append(paramInt);
        return localStringBuilder.toString();
      case 4: 
        return "CONNECT_STATE_SUSPENDED";
      case 3: 
        return "CONNECT_STATE_CONNECTED";
      case 2: 
        return "CONNECT_STATE_CONNECTING";
      case 1: 
        return "CONNECT_STATE_DISCONNECTED";
      }
      return "CONNECT_STATE_DISCONNECTING";
    }
    
    private boolean isCurrent(Messenger paramMessenger, String paramString)
    {
      if ((mCallbacksMessenger == paramMessenger) && (mState != 0) && (mState != 1)) {
        return true;
      }
      if ((mState != 0) && (mState != 1))
      {
        paramMessenger = new StringBuilder();
        paramMessenger.append(paramString);
        paramMessenger.append(" for ");
        paramMessenger.append(mServiceComponent);
        paramMessenger.append(" with mCallbacksMessenger=");
        paramMessenger.append(mCallbacksMessenger);
        paramMessenger.append(" this=");
        paramMessenger.append(this);
        Log.i("MediaBrowserCompat", paramMessenger.toString());
      }
      return false;
    }
    
    public void connect()
    {
      if ((mState != 0) && (mState != 1))
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("connect() called while neigther disconnecting nor disconnected (state=");
        localStringBuilder.append(getStateLabel(mState));
        localStringBuilder.append(")");
        throw new IllegalStateException(localStringBuilder.toString());
      }
      mState = 2;
      mHandler.post(new Runnable()
      {
        public void run()
        {
          if (mState == 0) {
            return;
          }
          Object localObject1 = MediaBrowserCompat.MediaBrowserImplBase.this;
          1 local1 = this;
          mState = 2;
          if ((MediaBrowserCompat.DEBUG) && (this$0.mServiceConnection != null))
          {
            localObject1 = new StringBuilder();
            ((StringBuilder)localObject1).append("mServiceConnection should be null. Instead it is ");
            ((StringBuilder)localObject1).append(this$0.mServiceConnection);
            throw new RuntimeException(((StringBuilder)localObject1).toString());
          }
          local1 = this;
          Context localContext;
          Object localObject3;
          if (this$0.mServiceBinderWrapper == null) {
            if (this$0.mCallbacksMessenger == null)
            {
              localObject2 = new Intent("android.media.browse.MediaBrowserService");
              ((Intent)localObject2).setComponent(this$0.mServiceComponent);
              this$0.mServiceConnection = new MediaBrowserCompat.MediaBrowserImplBase.MediaServiceConnection(this$0);
              localContext = this$0.mContext;
              localObject3 = this$0;
              localObject1 = local1;
              localObject3 = mServiceConnection;
            }
          }
          try
          {
            bool = localContext.bindService((Intent)localObject2, (ServiceConnection)localObject3, 1);
          }
          catch (Exception localException)
          {
            boolean bool;
            for (;;) {}
          }
          Object localObject2 = new StringBuilder();
          ((StringBuilder)localObject2).append("Failed binding to service ");
          ((StringBuilder)localObject2).append(this$0.mServiceComponent);
          Log.e("MediaBrowserCompat", ((StringBuilder)localObject2).toString());
          bool = false;
          localObject1 = local1;
          if (!bool)
          {
            this$0.forceCloseConnection();
            localObject1 = this$0;
            mCallback.onConnectionFailed();
            localObject1 = local1;
          }
          if (MediaBrowserCompat.DEBUG)
          {
            Log.d("MediaBrowserCompat", "connect...");
            this$0.dump();
            return;
            localObject1 = new StringBuilder();
            ((StringBuilder)localObject1).append("mCallbacksMessenger should be null. Instead it is ");
            ((StringBuilder)localObject1).append(this$0.mCallbacksMessenger);
            throw new RuntimeException(((StringBuilder)localObject1).toString());
            localObject1 = new StringBuilder();
            ((StringBuilder)localObject1).append("mServiceBinderWrapper should be null. Instead it is ");
            ((StringBuilder)localObject1).append(this$0.mServiceBinderWrapper);
            throw new RuntimeException(((StringBuilder)localObject1).toString());
          }
        }
      });
    }
    
    public void disconnect()
    {
      mState = 0;
      mHandler.post(new Runnable()
      {
        public void run()
        {
          Messenger localMessenger;
          if (mCallbacksMessenger != null)
          {
            localObject = mServiceBinderWrapper;
            localMessenger = mCallbacksMessenger;
          }
          try
          {
            ((MediaBrowserCompat.ServiceBinderWrapper)localObject).disconnect(localMessenger);
          }
          catch (RemoteException localRemoteException)
          {
            int i;
            for (;;) {}
          }
          Object localObject = new StringBuilder();
          ((StringBuilder)localObject).append("RemoteException during connect for ");
          ((StringBuilder)localObject).append(mServiceComponent);
          Log.w("MediaBrowserCompat", ((StringBuilder)localObject).toString());
          i = mState;
          forceCloseConnection();
          if (i != 0) {
            mState = i;
          }
          if (MediaBrowserCompat.DEBUG)
          {
            Log.d("MediaBrowserCompat", "disconnect...");
            dump();
            return;
          }
        }
      });
    }
    
    void dump()
    {
      Log.d("MediaBrowserCompat", "MediaBrowserCompat...");
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("  mServiceComponent=");
      localStringBuilder.append(mServiceComponent);
      Log.d("MediaBrowserCompat", localStringBuilder.toString());
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("  mCallback=");
      localStringBuilder.append(mCallback);
      Log.d("MediaBrowserCompat", localStringBuilder.toString());
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("  mRootHints=");
      localStringBuilder.append(mRootHints);
      Log.d("MediaBrowserCompat", localStringBuilder.toString());
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("  mState=");
      localStringBuilder.append(getStateLabel(mState));
      Log.d("MediaBrowserCompat", localStringBuilder.toString());
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("  mServiceConnection=");
      localStringBuilder.append(mServiceConnection);
      Log.d("MediaBrowserCompat", localStringBuilder.toString());
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("  mServiceBinderWrapper=");
      localStringBuilder.append(mServiceBinderWrapper);
      Log.d("MediaBrowserCompat", localStringBuilder.toString());
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("  mCallbacksMessenger=");
      localStringBuilder.append(mCallbacksMessenger);
      Log.d("MediaBrowserCompat", localStringBuilder.toString());
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("  mRootId=");
      localStringBuilder.append(mRootId);
      Log.d("MediaBrowserCompat", localStringBuilder.toString());
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("  mMediaSessionToken=");
      localStringBuilder.append(mMediaSessionToken);
      Log.d("MediaBrowserCompat", localStringBuilder.toString());
    }
    
    void forceCloseConnection()
    {
      if (mServiceConnection != null) {
        mContext.unbindService(mServiceConnection);
      }
      mState = 1;
      mServiceConnection = null;
      mServiceBinderWrapper = null;
      mCallbacksMessenger = null;
      mHandler.setCallbacksMessenger(null);
      mRootId = null;
      mMediaSessionToken = null;
    }
    
    public Bundle getExtras()
    {
      if (isConnected()) {
        return mExtras;
      }
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("getExtras() called while not connected (state=");
      localStringBuilder.append(getStateLabel(mState));
      localStringBuilder.append(")");
      throw new IllegalStateException(localStringBuilder.toString());
    }
    
    public void getItem(final String paramString, final MediaBrowserCompat.ItemCallback paramItemCallback)
    {
      MediaBrowserCompat.ServiceBinderWrapper localServiceBinderWrapper;
      Messenger localMessenger;
      if (!TextUtils.isEmpty(paramString)) {
        if (paramItemCallback != null)
        {
          if (!isConnected())
          {
            Log.i("MediaBrowserCompat", "Not connected, unable to retrieve the MediaItem.");
            mHandler.post(new Runnable()
            {
              public void run()
              {
                paramItemCallback.onError(paramString);
              }
            });
            return;
          }
          localObject = new MediaBrowserCompat.ItemReceiver(paramString, paramItemCallback, mHandler);
          localServiceBinderWrapper = mServiceBinderWrapper;
          localMessenger = mCallbacksMessenger;
        }
      }
      try
      {
        localServiceBinderWrapper.getMediaItem(paramString, (ResultReceiver)localObject, localMessenger);
        return;
      }
      catch (RemoteException localRemoteException)
      {
        for (;;) {}
      }
      Object localObject = new StringBuilder();
      ((StringBuilder)localObject).append("Remote error getting media item: ");
      ((StringBuilder)localObject).append(paramString);
      Log.i("MediaBrowserCompat", ((StringBuilder)localObject).toString());
      mHandler.post(new Runnable()
      {
        public void run()
        {
          paramItemCallback.onError(paramString);
        }
      });
      return;
      throw new IllegalArgumentException("cb is null");
      throw new IllegalArgumentException("mediaId is empty");
    }
    
    public String getRoot()
    {
      if (isConnected()) {
        return mRootId;
      }
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("getRoot() called while not connected(state=");
      localStringBuilder.append(getStateLabel(mState));
      localStringBuilder.append(")");
      throw new IllegalStateException(localStringBuilder.toString());
    }
    
    public ComponentName getServiceComponent()
    {
      if (isConnected()) {
        return mServiceComponent;
      }
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("getServiceComponent() called while not connected (state=");
      localStringBuilder.append(mState);
      localStringBuilder.append(")");
      throw new IllegalStateException(localStringBuilder.toString());
    }
    
    public MediaSessionCompat.Token getSessionToken()
    {
      if (isConnected()) {
        return mMediaSessionToken;
      }
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("getSessionToken() called while not connected(state=");
      localStringBuilder.append(mState);
      localStringBuilder.append(")");
      throw new IllegalStateException(localStringBuilder.toString());
    }
    
    public boolean isConnected()
    {
      return mState == 3;
    }
    
    public void onConnectionFailed(Messenger paramMessenger)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("onConnectFailed for ");
      localStringBuilder.append(mServiceComponent);
      Log.e("MediaBrowserCompat", localStringBuilder.toString());
      if (!isCurrent(paramMessenger, "onConnectFailed")) {
        return;
      }
      if (mState != 2)
      {
        paramMessenger = new StringBuilder();
        paramMessenger.append("onConnect from service while mState=");
        paramMessenger.append(getStateLabel(mState));
        paramMessenger.append("... ignoring");
        Log.w("MediaBrowserCompat", paramMessenger.toString());
        return;
      }
      forceCloseConnection();
      mCallback.onConnectionFailed();
    }
    
    public void onLoadChildren(Messenger paramMessenger, String paramString, List paramList, Bundle paramBundle)
    {
      if (!isCurrent(paramMessenger, "onLoadChildren")) {
        return;
      }
      if (MediaBrowserCompat.DEBUG)
      {
        paramMessenger = new StringBuilder();
        paramMessenger.append("onLoadChildren for ");
        paramMessenger.append(mServiceComponent);
        paramMessenger.append(" id=");
        paramMessenger.append(paramString);
        Log.d("MediaBrowserCompat", paramMessenger.toString());
      }
      paramMessenger = (MediaBrowserCompat.Subscription)mSubscriptions.get(paramString);
      if (paramMessenger == null)
      {
        if (MediaBrowserCompat.DEBUG)
        {
          paramMessenger = new StringBuilder();
          paramMessenger.append("onLoadChildren for id that isn't subscribed id=");
          paramMessenger.append(paramString);
          Log.d("MediaBrowserCompat", paramMessenger.toString());
        }
      }
      else
      {
        paramMessenger = paramMessenger.getCallback(mContext, paramBundle);
        if (paramMessenger != null)
        {
          if (paramBundle == null)
          {
            if (paramList == null)
            {
              paramMessenger.onError(paramString);
              return;
            }
            paramMessenger.onChildrenLoaded(paramString, paramList);
            return;
          }
          if (paramList == null)
          {
            paramMessenger.onError(paramString, paramBundle);
            return;
          }
          paramMessenger.onChildrenLoaded(paramString, paramList, paramBundle);
        }
      }
    }
    
    public void onServiceConnected(Messenger paramMessenger, String paramString, MediaSessionCompat.Token paramToken, Bundle paramBundle)
    {
      if (!isCurrent(paramMessenger, "onConnect")) {
        return;
      }
      if (mState != 2)
      {
        paramMessenger = new StringBuilder();
        paramMessenger.append("onConnect from service while mState=");
        paramMessenger.append(getStateLabel(mState));
        paramMessenger.append("... ignoring");
        Log.w("MediaBrowserCompat", paramMessenger.toString());
        return;
      }
      mRootId = paramString;
      mMediaSessionToken = paramToken;
      mExtras = paramBundle;
      mState = 3;
      if (MediaBrowserCompat.DEBUG)
      {
        Log.d("MediaBrowserCompat", "ServiceCallbacks.onConnect...");
        dump();
      }
      mCallback.onConnected();
      paramMessenger = mSubscriptions;
      try
      {
        paramMessenger = paramMessenger.entrySet().iterator();
        boolean bool = paramMessenger.hasNext();
        if (!bool) {
          return;
        }
        paramString = paramMessenger.next();
        paramToken = (Map.Entry)paramString;
        paramString = paramToken.getKey();
        paramString = (String)paramString;
        paramToken = paramToken.getValue();
        paramBundle = (MediaBrowserCompat.Subscription)paramToken;
        paramToken = paramBundle.getCallbacks();
        paramBundle = paramBundle.getOptionsList();
        int i = 0;
        for (;;)
        {
          int j = paramToken.size();
          if (i >= j) {
            break;
          }
          MediaBrowserCompat.ServiceBinderWrapper localServiceBinderWrapper = mServiceBinderWrapper;
          Object localObject1 = paramToken.get(i);
          localObject1 = (MediaBrowserCompat.SubscriptionCallback)localObject1;
          localObject1 = MediaBrowserCompat.SubscriptionCallback.access$000((MediaBrowserCompat.SubscriptionCallback)localObject1);
          Object localObject2 = paramBundle.get(i);
          localObject2 = (Bundle)localObject2;
          Messenger localMessenger = mCallbacksMessenger;
          localServiceBinderWrapper.addSubscription(paramString, (IBinder)localObject1, (Bundle)localObject2, localMessenger);
          i += 1;
        }
      }
      catch (RemoteException paramMessenger)
      {
        for (;;) {}
      }
      Log.d("MediaBrowserCompat", "addSubscription failed with RemoteException.");
      return;
    }
    
    public void search(final String paramString, final Bundle paramBundle, final MediaBrowserCompat.SearchCallback paramSearchCallback)
    {
      if (isConnected())
      {
        MediaBrowserCompat.SearchResultReceiver localSearchResultReceiver = new MediaBrowserCompat.SearchResultReceiver(paramString, paramBundle, paramSearchCallback, mHandler);
        Object localObject = mServiceBinderWrapper;
        Messenger localMessenger = mCallbacksMessenger;
        try
        {
          ((MediaBrowserCompat.ServiceBinderWrapper)localObject).search(paramString, paramBundle, localSearchResultReceiver, localMessenger);
          return;
        }
        catch (RemoteException localRemoteException)
        {
          localObject = new StringBuilder();
          ((StringBuilder)localObject).append("Remote error searching items with query: ");
          ((StringBuilder)localObject).append(paramString);
          Log.i("MediaBrowserCompat", ((StringBuilder)localObject).toString(), localRemoteException);
          mHandler.post(new Runnable()
          {
            public void run()
            {
              paramSearchCallback.onError(paramString, paramBundle);
            }
          });
          return;
        }
      }
      paramString = new StringBuilder();
      paramString.append("search() called while not connected (state=");
      paramString.append(getStateLabel(mState));
      paramString.append(")");
      throw new IllegalStateException(paramString.toString());
    }
    
    public void sendCustomAction(final String paramString, final Bundle paramBundle, final MediaBrowserCompat.CustomActionCallback paramCustomActionCallback)
    {
      if (isConnected())
      {
        MediaBrowserCompat.CustomActionResultReceiver localCustomActionResultReceiver = new MediaBrowserCompat.CustomActionResultReceiver(paramString, paramBundle, paramCustomActionCallback, mHandler);
        Object localObject = mServiceBinderWrapper;
        Messenger localMessenger = mCallbacksMessenger;
        try
        {
          ((MediaBrowserCompat.ServiceBinderWrapper)localObject).sendCustomAction(paramString, paramBundle, localCustomActionResultReceiver, localMessenger);
          return;
        }
        catch (RemoteException localRemoteException)
        {
          localObject = new StringBuilder();
          ((StringBuilder)localObject).append("Remote error sending a custom action: action=");
          ((StringBuilder)localObject).append(paramString);
          ((StringBuilder)localObject).append(", extras=");
          ((StringBuilder)localObject).append(paramBundle);
          Log.i("MediaBrowserCompat", ((StringBuilder)localObject).toString(), localRemoteException);
          if (paramCustomActionCallback == null) {
            return;
          }
        }
        mHandler.post(new Runnable()
        {
          public void run()
          {
            paramCustomActionCallback.onError(paramString, paramBundle, null);
          }
        });
      }
      else
      {
        paramCustomActionCallback = new StringBuilder();
        paramCustomActionCallback.append("Cannot send a custom action (");
        paramCustomActionCallback.append(paramString);
        paramCustomActionCallback.append(") with ");
        paramCustomActionCallback.append("extras ");
        paramCustomActionCallback.append(paramBundle);
        paramCustomActionCallback.append(" because the browser is not connected to the ");
        paramCustomActionCallback.append("service.");
        throw new IllegalStateException(paramCustomActionCallback.toString());
      }
    }
    
    public void subscribe(String paramString, Bundle paramBundle, MediaBrowserCompat.SubscriptionCallback paramSubscriptionCallback)
    {
      Object localObject2 = (MediaBrowserCompat.Subscription)mSubscriptions.get(paramString);
      Object localObject1 = localObject2;
      if (localObject2 == null)
      {
        localObject1 = new MediaBrowserCompat.Subscription();
        mSubscriptions.put(paramString, localObject1);
      }
      if (paramBundle == null) {
        paramBundle = null;
      } else {
        paramBundle = new Bundle(paramBundle);
      }
      ((MediaBrowserCompat.Subscription)localObject1).putCallback(mContext, paramBundle, paramSubscriptionCallback);
      if (isConnected())
      {
        localObject1 = mServiceBinderWrapper;
        try
        {
          paramSubscriptionCallback = MediaBrowserCompat.SubscriptionCallback.access$000(paramSubscriptionCallback);
          localObject2 = mCallbacksMessenger;
          ((MediaBrowserCompat.ServiceBinderWrapper)localObject1).addSubscription(paramString, paramSubscriptionCallback, paramBundle, (Messenger)localObject2);
          return;
        }
        catch (RemoteException paramBundle)
        {
          for (;;) {}
        }
        paramBundle = new StringBuilder();
        paramBundle.append("addSubscription failed with RemoteException parentId=");
        paramBundle.append(paramString);
        Log.d("MediaBrowserCompat", paramBundle.toString());
        return;
      }
    }
    
    public void unsubscribe(String paramString, MediaBrowserCompat.SubscriptionCallback paramSubscriptionCallback)
    {
      MediaBrowserCompat.Subscription localSubscription = (MediaBrowserCompat.Subscription)mSubscriptions.get(paramString);
      if (localSubscription == null) {
        return;
      }
      if (paramSubscriptionCallback == null) {}
      try
      {
        bool = isConnected();
        if (!bool) {
          break label204;
        }
        localObject1 = mServiceBinderWrapper;
        localObject2 = mCallbacksMessenger;
        ((MediaBrowserCompat.ServiceBinderWrapper)localObject1).removeSubscription(paramString, null, (Messenger)localObject2);
      }
      catch (RemoteException localRemoteException)
      {
        boolean bool;
        Object localObject1;
        Object localObject2;
        int i;
        for (;;) {}
      }
      localObject1 = localSubscription.getCallbacks();
      localObject2 = localSubscription.getOptionsList();
      i = ((List)localObject1).size();
      i -= 1;
      while (i >= 0)
      {
        Object localObject3 = ((List)localObject1).get(i);
        if (localObject3 == paramSubscriptionCallback)
        {
          bool = isConnected();
          if (bool)
          {
            localObject3 = mServiceBinderWrapper;
            IBinder localIBinder = MediaBrowserCompat.SubscriptionCallback.access$000(paramSubscriptionCallback);
            Messenger localMessenger = mCallbacksMessenger;
            ((MediaBrowserCompat.ServiceBinderWrapper)localObject3).removeSubscription(paramString, localIBinder, localMessenger);
          }
          ((List)localObject1).remove(i);
          ((List)localObject2).remove(i);
        }
        i -= 1;
        continue;
        localObject1 = new StringBuilder();
        ((StringBuilder)localObject1).append("removeSubscription failed with RemoteException parentId=");
        ((StringBuilder)localObject1).append(paramString);
        Log.d("MediaBrowserCompat", ((StringBuilder)localObject1).toString());
      }
      label204:
      if ((localSubscription.isEmpty()) || (paramSubscriptionCallback == null))
      {
        mSubscriptions.remove(paramString);
        return;
      }
    }
    
    private class MediaServiceConnection
      implements ServiceConnection
    {
      MediaServiceConnection() {}
      
      private void postOrRun(Runnable paramRunnable)
      {
        if (Thread.currentThread() == mHandler.getLooper().getThread())
        {
          paramRunnable.run();
          return;
        }
        mHandler.post(paramRunnable);
      }
      
      boolean isCurrent(String paramString)
      {
        if ((mServiceConnection == this) && (mState != 0) && (mState != 1)) {
          return true;
        }
        if ((mState != 0) && (mState != 1))
        {
          StringBuilder localStringBuilder = new StringBuilder();
          localStringBuilder.append(paramString);
          localStringBuilder.append(" for ");
          localStringBuilder.append(mServiceComponent);
          localStringBuilder.append(" with mServiceConnection=");
          localStringBuilder.append(mServiceConnection);
          localStringBuilder.append(" this=");
          localStringBuilder.append(this);
          Log.i("MediaBrowserCompat", localStringBuilder.toString());
        }
        return false;
      }
      
      public void onServiceConnected(final ComponentName paramComponentName, final IBinder paramIBinder)
      {
        postOrRun(new Runnable()
        {
          public void run()
          {
            if (MediaBrowserCompat.DEBUG)
            {
              localObject = new StringBuilder();
              ((StringBuilder)localObject).append("MediaServiceConnection.onServiceConnected name=");
              ((StringBuilder)localObject).append(paramComponentName);
              ((StringBuilder)localObject).append(" binder=");
              ((StringBuilder)localObject).append(paramIBinder);
              Log.d("MediaBrowserCompat", ((StringBuilder)localObject).toString());
              dump();
            }
            if (!isCurrent("onServiceConnected")) {
              return;
            }
            mServiceBinderWrapper = new MediaBrowserCompat.ServiceBinderWrapper(paramIBinder, mRootHints);
            mCallbacksMessenger = new Messenger(mHandler);
            mHandler.setCallbacksMessenger(mCallbacksMessenger);
            mState = 2;
            if (MediaBrowserCompat.DEBUG) {}
            try
            {
              Log.d("MediaBrowserCompat", "ServiceCallbacks.onConnect...");
              localObject = MediaBrowserCompat.MediaBrowserImplBase.this;
              ((MediaBrowserCompat.MediaBrowserImplBase)localObject).dump();
              localObject = mServiceBinderWrapper;
              Context localContext = mContext;
              Messenger localMessenger = mCallbacksMessenger;
              ((MediaBrowserCompat.ServiceBinderWrapper)localObject).connect(localContext, localMessenger);
              return;
            }
            catch (RemoteException localRemoteException)
            {
              for (;;) {}
            }
            Object localObject = new StringBuilder();
            ((StringBuilder)localObject).append("RemoteException during connect for ");
            ((StringBuilder)localObject).append(mServiceComponent);
            Log.w("MediaBrowserCompat", ((StringBuilder)localObject).toString());
            if (MediaBrowserCompat.DEBUG)
            {
              Log.d("MediaBrowserCompat", "ServiceCallbacks.onConnect...");
              dump();
              return;
            }
          }
        });
      }
      
      public void onServiceDisconnected(final ComponentName paramComponentName)
      {
        postOrRun(new Runnable()
        {
          public void run()
          {
            if (MediaBrowserCompat.DEBUG)
            {
              StringBuilder localStringBuilder = new StringBuilder();
              localStringBuilder.append("MediaServiceConnection.onServiceDisconnected name=");
              localStringBuilder.append(paramComponentName);
              localStringBuilder.append(" this=");
              localStringBuilder.append(this);
              localStringBuilder.append(" mServiceConnection=");
              localStringBuilder.append(mServiceConnection);
              Log.d("MediaBrowserCompat", localStringBuilder.toString());
              dump();
            }
            if (!isCurrent("onServiceDisconnected")) {
              return;
            }
            mServiceBinderWrapper = null;
            mCallbacksMessenger = null;
            mHandler.setCallbacksMessenger(null);
            mState = 4;
            mCallback.onConnectionSuspended();
          }
        });
      }
    }
  }
  
  static abstract interface MediaBrowserServiceCallbackImpl
  {
    public abstract void onConnectionFailed(Messenger paramMessenger);
    
    public abstract void onLoadChildren(Messenger paramMessenger, String paramString, List paramList, Bundle paramBundle);
    
    public abstract void onServiceConnected(Messenger paramMessenger, String paramString, MediaSessionCompat.Token paramToken, Bundle paramBundle);
  }
  
  public static class MediaItem
    implements Parcelable
  {
    public static final Parcelable.Creator<MediaItem> CREATOR = new Parcelable.Creator()
    {
      public MediaBrowserCompat.MediaItem createFromParcel(Parcel paramAnonymousParcel)
      {
        return new MediaBrowserCompat.MediaItem(paramAnonymousParcel);
      }
      
      public MediaBrowserCompat.MediaItem[] newArray(int paramAnonymousInt)
      {
        return new MediaBrowserCompat.MediaItem[paramAnonymousInt];
      }
    };
    public static final int FLAG_BROWSABLE = 1;
    public static final int FLAG_PLAYABLE = 2;
    private final MediaDescriptionCompat mDescription;
    private final int mFlags;
    
    MediaItem(Parcel paramParcel)
    {
      mFlags = paramParcel.readInt();
      mDescription = ((MediaDescriptionCompat)MediaDescriptionCompat.CREATOR.createFromParcel(paramParcel));
    }
    
    public MediaItem(MediaDescriptionCompat paramMediaDescriptionCompat, int paramInt)
    {
      if (paramMediaDescriptionCompat != null)
      {
        if (!TextUtils.isEmpty(paramMediaDescriptionCompat.getMediaId()))
        {
          mFlags = paramInt;
          mDescription = paramMediaDescriptionCompat;
          return;
        }
        throw new IllegalArgumentException("description must have a non-empty media id");
      }
      throw new IllegalArgumentException("description cannot be null");
    }
    
    public static MediaItem fromMediaItem(Object paramObject)
    {
      if ((paramObject != null) && (Build.VERSION.SDK_INT >= 21))
      {
        int i = MediaBrowserCompatApi21.MediaItem.getFlags(paramObject);
        return new MediaItem(MediaDescriptionCompat.fromMediaDescription(MediaBrowserCompatApi21.MediaItem.getDescription(paramObject)), i);
      }
      return null;
    }
    
    public static List fromMediaItemList(List paramList)
    {
      if ((paramList != null) && (Build.VERSION.SDK_INT >= 21))
      {
        ArrayList localArrayList = new ArrayList(paramList.size());
        paramList = paramList.iterator();
        while (paramList.hasNext()) {
          localArrayList.add(fromMediaItem(paramList.next()));
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
    
    public int getFlags()
    {
      return mFlags;
    }
    
    public String getMediaId()
    {
      return mDescription.getMediaId();
    }
    
    public boolean isBrowsable()
    {
      return (mFlags & 0x1) != 0;
    }
    
    public boolean isPlayable()
    {
      return (mFlags & 0x2) != 0;
    }
    
    public String toString()
    {
      StringBuilder localStringBuilder = new StringBuilder("MediaItem{");
      localStringBuilder.append("mFlags=");
      localStringBuilder.append(mFlags);
      localStringBuilder.append(", mDescription=");
      localStringBuilder.append(mDescription);
      localStringBuilder.append('}');
      return localStringBuilder.toString();
    }
    
    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      paramParcel.writeInt(mFlags);
      mDescription.writeToParcel(paramParcel, paramInt);
    }
    
    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
    public static @interface Flags {}
  }
  
  public static abstract class SearchCallback
  {
    public SearchCallback() {}
    
    public void onError(String paramString, Bundle paramBundle) {}
    
    public void onSearchResult(String paramString, Bundle paramBundle, List paramList) {}
  }
  
  private static class SearchResultReceiver
    extends ResultReceiver
  {
    private final MediaBrowserCompat.SearchCallback mCallback;
    private final Bundle mExtras;
    private final String mQuery;
    
    SearchResultReceiver(String paramString, Bundle paramBundle, MediaBrowserCompat.SearchCallback paramSearchCallback, Handler paramHandler)
    {
      super();
      mQuery = paramString;
      mExtras = paramBundle;
      mCallback = paramSearchCallback;
    }
    
    protected void onReceiveResult(int paramInt, Bundle paramBundle)
    {
      if (paramBundle != null) {
        paramBundle.setClassLoader(MediaBrowserCompat.class.getClassLoader());
      }
      if ((paramInt == 0) && (paramBundle != null) && (paramBundle.containsKey("search_results")))
      {
        Parcelable[] arrayOfParcelable = paramBundle.getParcelableArray("search_results");
        paramBundle = null;
        if (arrayOfParcelable != null)
        {
          ArrayList localArrayList = new ArrayList();
          int i = arrayOfParcelable.length;
          paramInt = 0;
          for (;;)
          {
            paramBundle = localArrayList;
            if (paramInt >= i) {
              break;
            }
            localArrayList.add((MediaBrowserCompat.MediaItem)arrayOfParcelable[paramInt]);
            paramInt += 1;
          }
        }
        mCallback.onSearchResult(mQuery, mExtras, paramBundle);
        return;
      }
      mCallback.onError(mQuery, mExtras);
    }
  }
  
  private static class ServiceBinderWrapper
  {
    private Messenger mMessenger;
    private Bundle mRootHints;
    
    public ServiceBinderWrapper(IBinder paramIBinder, Bundle paramBundle)
    {
      mMessenger = new Messenger(paramIBinder);
      mRootHints = paramBundle;
    }
    
    private void sendRequest(int paramInt, Bundle paramBundle, Messenger paramMessenger)
      throws RemoteException
    {
      Message localMessage = Message.obtain();
      what = paramInt;
      arg1 = 1;
      localMessage.setData(paramBundle);
      replyTo = paramMessenger;
      mMessenger.send(localMessage);
    }
    
    void addSubscription(String paramString, IBinder paramIBinder, Bundle paramBundle, Messenger paramMessenger)
      throws RemoteException
    {
      Bundle localBundle = new Bundle();
      localBundle.putString("data_media_item_id", paramString);
      BundleCompat.putBinder(localBundle, "data_callback_token", paramIBinder);
      localBundle.putBundle("data_options", paramBundle);
      sendRequest(3, localBundle, paramMessenger);
    }
    
    void connect(Context paramContext, Messenger paramMessenger)
      throws RemoteException
    {
      Bundle localBundle = new Bundle();
      localBundle.putString("data_package_name", paramContext.getPackageName());
      localBundle.putBundle("data_root_hints", mRootHints);
      sendRequest(1, localBundle, paramMessenger);
    }
    
    void disconnect(Messenger paramMessenger)
      throws RemoteException
    {
      sendRequest(2, null, paramMessenger);
    }
    
    void getMediaItem(String paramString, ResultReceiver paramResultReceiver, Messenger paramMessenger)
      throws RemoteException
    {
      Bundle localBundle = new Bundle();
      localBundle.putString("data_media_item_id", paramString);
      localBundle.putParcelable("data_result_receiver", paramResultReceiver);
      sendRequest(5, localBundle, paramMessenger);
    }
    
    void registerCallbackMessenger(Messenger paramMessenger)
      throws RemoteException
    {
      Bundle localBundle = new Bundle();
      localBundle.putBundle("data_root_hints", mRootHints);
      sendRequest(6, localBundle, paramMessenger);
    }
    
    void removeSubscription(String paramString, IBinder paramIBinder, Messenger paramMessenger)
      throws RemoteException
    {
      Bundle localBundle = new Bundle();
      localBundle.putString("data_media_item_id", paramString);
      BundleCompat.putBinder(localBundle, "data_callback_token", paramIBinder);
      sendRequest(4, localBundle, paramMessenger);
    }
    
    void search(String paramString, Bundle paramBundle, ResultReceiver paramResultReceiver, Messenger paramMessenger)
      throws RemoteException
    {
      Bundle localBundle = new Bundle();
      localBundle.putString("data_search_query", paramString);
      localBundle.putBundle("data_search_extras", paramBundle);
      localBundle.putParcelable("data_result_receiver", paramResultReceiver);
      sendRequest(8, localBundle, paramMessenger);
    }
    
    void sendCustomAction(String paramString, Bundle paramBundle, ResultReceiver paramResultReceiver, Messenger paramMessenger)
      throws RemoteException
    {
      Bundle localBundle = new Bundle();
      localBundle.putString("data_custom_action", paramString);
      localBundle.putBundle("data_custom_action_extras", paramBundle);
      localBundle.putParcelable("data_result_receiver", paramResultReceiver);
      sendRequest(9, localBundle, paramMessenger);
    }
    
    void unregisterCallbackMessenger(Messenger paramMessenger)
      throws RemoteException
    {
      sendRequest(7, null, paramMessenger);
    }
  }
  
  private static class Subscription
  {
    private final List<MediaBrowserCompat.SubscriptionCallback> mCallbacks = new ArrayList();
    private final List<Bundle> mOptionsList = new ArrayList();
    
    public Subscription() {}
    
    public MediaBrowserCompat.SubscriptionCallback getCallback(Context paramContext, Bundle paramBundle)
    {
      if (paramBundle != null) {
        paramBundle.setClassLoader(paramContext.getClassLoader());
      }
      int i = 0;
      while (i < mOptionsList.size())
      {
        if (MediaBrowserCompatUtils.areSameOptions((Bundle)mOptionsList.get(i), paramBundle)) {
          return (MediaBrowserCompat.SubscriptionCallback)mCallbacks.get(i);
        }
        i += 1;
      }
      return null;
    }
    
    public List getCallbacks()
    {
      return mCallbacks;
    }
    
    public List getOptionsList()
    {
      return mOptionsList;
    }
    
    public boolean isEmpty()
    {
      return mCallbacks.isEmpty();
    }
    
    public void putCallback(Context paramContext, Bundle paramBundle, MediaBrowserCompat.SubscriptionCallback paramSubscriptionCallback)
    {
      if (paramBundle != null) {
        paramBundle.setClassLoader(paramContext.getClassLoader());
      }
      int i = 0;
      while (i < mOptionsList.size())
      {
        if (MediaBrowserCompatUtils.areSameOptions((Bundle)mOptionsList.get(i), paramBundle))
        {
          mCallbacks.set(i, paramSubscriptionCallback);
          return;
        }
        i += 1;
      }
      mCallbacks.add(paramSubscriptionCallback);
      mOptionsList.add(paramBundle);
    }
  }
  
  public static abstract class SubscriptionCallback
  {
    private final Object mSubscriptionCallbackObj;
    WeakReference<MediaBrowserCompat.Subscription> mSubscriptionRef;
    private final IBinder mToken;
    
    public SubscriptionCallback()
    {
      if (Build.VERSION.SDK_INT >= 26)
      {
        mSubscriptionCallbackObj = MediaBrowserCompatApi24.createSubscriptionCallback(new StubApi24());
        mToken = null;
        return;
      }
      if (Build.VERSION.SDK_INT >= 21)
      {
        mSubscriptionCallbackObj = MediaBrowserCompatApi21.createSubscriptionCallback(new StubApi21());
        mToken = new Binder();
        return;
      }
      mSubscriptionCallbackObj = null;
      mToken = new Binder();
    }
    
    private void setSubscription(MediaBrowserCompat.Subscription paramSubscription)
    {
      mSubscriptionRef = new WeakReference(paramSubscription);
    }
    
    public void onChildrenLoaded(String paramString, List paramList) {}
    
    public void onChildrenLoaded(String paramString, List paramList, Bundle paramBundle) {}
    
    public void onError(String paramString) {}
    
    public void onError(String paramString, Bundle paramBundle) {}
    
    private class StubApi21
      implements MediaBrowserCompatApi21.SubscriptionCallback
    {
      StubApi21() {}
      
      List applyOptions(List paramList, Bundle paramBundle)
      {
        if (paramList == null) {
          return null;
        }
        int i = paramBundle.getInt("android.media.browse.extra.PAGE", -1);
        int m = paramBundle.getInt("android.media.browse.extra.PAGE_SIZE", -1);
        if ((i == -1) && (m == -1)) {
          return paramList;
        }
        int k = m * i;
        int j = k + m;
        if ((i >= 0) && (m >= 1) && (k < paramList.size()))
        {
          i = j;
          if (j > paramList.size()) {
            i = paramList.size();
          }
          return paramList.subList(k, i);
        }
        return Collections.EMPTY_LIST;
      }
      
      public void onChildrenLoaded(String paramString, List paramList)
      {
        if (mSubscriptionRef == null) {
          localObject = null;
        } else {
          localObject = (MediaBrowserCompat.Subscription)mSubscriptionRef.get();
        }
        if (localObject == null)
        {
          MediaBrowserCompat.SubscriptionCallback.this.onChildrenLoaded(paramString, MediaBrowserCompat.MediaItem.fromMediaItemList(paramList));
          return;
        }
        paramList = MediaBrowserCompat.MediaItem.fromMediaItemList(paramList);
        List localList = ((MediaBrowserCompat.Subscription)localObject).getCallbacks();
        Object localObject = ((MediaBrowserCompat.Subscription)localObject).getOptionsList();
        int i = 0;
        while (i < localList.size())
        {
          Bundle localBundle = (Bundle)((List)localObject).get(i);
          if (localBundle == null) {
            MediaBrowserCompat.SubscriptionCallback.this.onChildrenLoaded(paramString, paramList);
          } else {
            onChildrenLoaded(paramString, applyOptions(paramList, localBundle), localBundle);
          }
          i += 1;
        }
      }
      
      public void onError(String paramString)
      {
        MediaBrowserCompat.SubscriptionCallback.this.onError(paramString);
      }
    }
    
    private class StubApi24
      extends MediaBrowserCompat.SubscriptionCallback.StubApi21
      implements MediaBrowserCompatApi24.SubscriptionCallback
    {
      StubApi24()
      {
        super();
      }
      
      public void onChildrenLoaded(String paramString, List paramList, Bundle paramBundle)
      {
        MediaBrowserCompat.SubscriptionCallback.this.onChildrenLoaded(paramString, MediaBrowserCompat.MediaItem.fromMediaItemList(paramList), paramBundle);
      }
      
      public void onError(String paramString, Bundle paramBundle)
      {
        MediaBrowserCompat.SubscriptionCallback.this.onError(paramString, paramBundle);
      }
    }
  }
}
