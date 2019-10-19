package android.support.v4.media;

import android.app.Service;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Binder;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.v4.media.session.IMediaSession;
import android.support.v4.media.session.MediaSessionCompat.Token;
import android.support.v4.os.ResultReceiver;
import android.support.v4.package_7.BundleCompat;
import android.support.v4.util.ArrayMap;
import android.support.v4.util.Pair;
import android.support.v4.util.SimpleArrayMap;
import android.text.TextUtils;
import android.util.Log;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public abstract class MediaBrowserServiceCompat
  extends Service
{
  static final boolean DEBUG = Log.isLoggable("MBServiceCompat", 3);
  private static final float EPSILON = 1.0E-5F;
  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  public static final String KEY_MEDIA_ITEM = "media_item";
  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  public static final String KEY_SEARCH_RESULTS = "search_results";
  static final int RESULT_ERROR = -1;
  static final int RESULT_FLAG_ON_LOAD_ITEM_NOT_IMPLEMENTED = 2;
  static final int RESULT_FLAG_ON_SEARCH_NOT_IMPLEMENTED = 4;
  static final int RESULT_FLAG_OPTION_NOT_HANDLED = 1;
  static final int RESULT_OK = 0;
  static final int RESULT_PROGRESS_UPDATE = 1;
  public static final String SERVICE_INTERFACE = "android.media.browse.MediaBrowserService";
  static final String TAG = "MBServiceCompat";
  final ArrayMap<IBinder, ConnectionRecord> mConnections = new ArrayMap();
  ConnectionRecord mCurConnection;
  final ServiceHandler mHandler = new ServiceHandler();
  private MediaBrowserServiceImpl mImpl;
  MediaSessionCompat.Token mSession;
  
  public MediaBrowserServiceCompat() {}
  
  void addSubscription(String paramString, ConnectionRecord paramConnectionRecord, IBinder paramIBinder, Bundle paramBundle)
  {
    throw new Runtime("d2j fail translate: java.lang.RuntimeException: fail exe a7 = a6\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.exec(BaseAnalyze.java:92)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.exec(BaseAnalyze.java:1)\n\tat com.googlecode.dex2jar.ir.ts.Cfg.dfs(Cfg.java:255)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.analyze0(BaseAnalyze.java:75)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.analyze(BaseAnalyze.java:69)\n\tat com.googlecode.dex2jar.ir.ts.UnSSATransformer.transform(UnSSATransformer.java:274)\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:163)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\nCaused by: java.lang.NullPointerException\n");
  }
  
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
  
  public void dump(FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString) {}
  
  public final Bundle getBrowserRootHints()
  {
    return mImpl.getBrowserRootHints();
  }
  
  public MediaSessionCompat.Token getSessionToken()
  {
    return mSession;
  }
  
  boolean isValidPackage(String paramString, int paramInt)
  {
    if (paramString == null) {
      return false;
    }
    String[] arrayOfString = getPackageManager().getPackagesForUid(paramInt);
    int i = arrayOfString.length;
    paramInt = 0;
    while (paramInt < i)
    {
      if (arrayOfString[paramInt].equals(paramString)) {
        return true;
      }
      paramInt += 1;
    }
    return false;
  }
  
  public void notifyChildrenChanged(String paramString)
  {
    if (paramString != null)
    {
      mImpl.notifyChildrenChanged(paramString, null);
      return;
    }
    throw new IllegalArgumentException("parentId cannot be null in notifyChildrenChanged");
  }
  
  public void notifyChildrenChanged(String paramString, Bundle paramBundle)
  {
    if (paramString != null)
    {
      if (paramBundle != null)
      {
        mImpl.notifyChildrenChanged(paramString, paramBundle);
        return;
      }
      throw new IllegalArgumentException("options cannot be null in notifyChildrenChanged");
    }
    throw new IllegalArgumentException("parentId cannot be null in notifyChildrenChanged");
  }
  
  public IBinder onBind(Intent paramIntent)
  {
    return mImpl.onBind(paramIntent);
  }
  
  public void onCreate()
  {
    super.onCreate();
    if (Build.VERSION.SDK_INT >= 26) {
      mImpl = new MediaBrowserServiceImplApi24();
    } else if (Build.VERSION.SDK_INT >= 23) {
      mImpl = new MediaBrowserServiceImplApi23();
    } else if (Build.VERSION.SDK_INT >= 21) {
      mImpl = new MediaBrowserServiceImplApi21();
    } else {
      mImpl = new MediaBrowserServiceImplBase();
    }
    mImpl.onCreate();
  }
  
  public void onCustomAction(String paramString, Bundle paramBundle, Result paramResult)
  {
    paramResult.sendError(null);
  }
  
  public abstract BrowserRoot onGetRoot(String paramString, int paramInt, Bundle paramBundle);
  
  public abstract void onLoadChildren(String paramString, Result paramResult);
  
  public void onLoadChildren(String paramString, Result paramResult, Bundle paramBundle)
  {
    paramResult.setFlags(1);
    onLoadChildren(paramString, paramResult);
  }
  
  public void onLoadItem(String paramString, Result paramResult)
  {
    paramResult.setFlags(2);
    paramResult.sendResult(null);
  }
  
  public void onSearch(String paramString, Bundle paramBundle, Result paramResult)
  {
    paramResult.setFlags(4);
    paramResult.sendResult(null);
  }
  
  void performCustomAction(String paramString, Bundle paramBundle, ConnectionRecord paramConnectionRecord, final ResultReceiver paramResultReceiver)
  {
    paramResultReceiver = new Result(paramString)
    {
      void onErrorSent(Bundle paramAnonymousBundle)
      {
        paramResultReceiver.send(-1, paramAnonymousBundle);
      }
      
      void onProgressUpdateSent(Bundle paramAnonymousBundle)
      {
        paramResultReceiver.send(1, paramAnonymousBundle);
      }
      
      void onResultSent(Bundle paramAnonymousBundle)
      {
        paramResultReceiver.send(0, paramAnonymousBundle);
      }
    };
    mCurConnection = paramConnectionRecord;
    onCustomAction(paramString, paramBundle, paramResultReceiver);
    mCurConnection = null;
    if (paramResultReceiver.isDone()) {
      return;
    }
    paramConnectionRecord = new StringBuilder();
    paramConnectionRecord.append("onCustomAction must call detach() or sendResult() or sendError() before returning for action=");
    paramConnectionRecord.append(paramString);
    paramConnectionRecord.append(" extras=");
    paramConnectionRecord.append(paramBundle);
    throw new IllegalStateException(paramConnectionRecord.toString());
  }
  
  void performLoadChildren(final String paramString, final ConnectionRecord paramConnectionRecord, final Bundle paramBundle)
  {
    Result local1 = new Result(paramString)
    {
      void onResultSent(List paramAnonymousList)
      {
        if (mConnections.get(paramConnectionRecordcallbacks.asBinder()) != paramConnectionRecord)
        {
          if (MediaBrowserServiceCompat.DEBUG)
          {
            paramAnonymousList = new StringBuilder();
            paramAnonymousList.append("Not sending onLoadChildren result for connection that has been disconnected. pkg=");
            paramAnonymousList.append(paramConnectionRecordpkg);
            paramAnonymousList.append(" id=");
            paramAnonymousList.append(paramString);
            Log.d("MBServiceCompat", paramAnonymousList.toString());
          }
        }
        else
        {
          List localList = paramAnonymousList;
          if ((getFlags() & 0x1) != 0) {
            localList = applyOptions(paramAnonymousList, paramBundle);
          }
          paramAnonymousList = paramConnectionRecordcallbacks;
          String str = paramString;
          Bundle localBundle = paramBundle;
          try
          {
            paramAnonymousList.onLoadChildren(str, localList, localBundle);
            return;
          }
          catch (RemoteException paramAnonymousList)
          {
            for (;;) {}
          }
          paramAnonymousList = new StringBuilder();
          paramAnonymousList.append("Calling onLoadChildren() failed for id=");
          paramAnonymousList.append(paramString);
          paramAnonymousList.append(" package=");
          paramAnonymousList.append(paramConnectionRecordpkg);
          Log.w("MBServiceCompat", paramAnonymousList.toString());
          return;
        }
      }
    };
    mCurConnection = paramConnectionRecord;
    if (paramBundle == null) {
      onLoadChildren(paramString, local1);
    } else {
      onLoadChildren(paramString, local1, paramBundle);
    }
    mCurConnection = null;
    if (local1.isDone()) {
      return;
    }
    paramBundle = new StringBuilder();
    paramBundle.append("onLoadChildren must call detach() or sendResult() before returning for package=");
    paramBundle.append(pkg);
    paramBundle.append(" id=");
    paramBundle.append(paramString);
    throw new IllegalStateException(paramBundle.toString());
  }
  
  void performLoadItem(String paramString, ConnectionRecord paramConnectionRecord, final ResultReceiver paramResultReceiver)
  {
    paramResultReceiver = new Result(paramString)
    {
      void onResultSent(MediaBrowserCompat.MediaItem paramAnonymousMediaItem)
      {
        if ((getFlags() & 0x2) != 0)
        {
          paramResultReceiver.send(-1, null);
          return;
        }
        Bundle localBundle = new Bundle();
        localBundle.putParcelable("media_item", paramAnonymousMediaItem);
        paramResultReceiver.send(0, localBundle);
      }
    };
    mCurConnection = paramConnectionRecord;
    onLoadItem(paramString, paramResultReceiver);
    mCurConnection = null;
    if (paramResultReceiver.isDone()) {
      return;
    }
    paramConnectionRecord = new StringBuilder();
    paramConnectionRecord.append("onLoadItem must call detach() or sendResult() before returning for id=");
    paramConnectionRecord.append(paramString);
    throw new IllegalStateException(paramConnectionRecord.toString());
  }
  
  void performSearch(String paramString, Bundle paramBundle, ConnectionRecord paramConnectionRecord, final ResultReceiver paramResultReceiver)
  {
    paramResultReceiver = new Result(paramString)
    {
      void onResultSent(List paramAnonymousList)
      {
        if (((getFlags() & 0x4) == 0) && (paramAnonymousList != null))
        {
          Bundle localBundle = new Bundle();
          localBundle.putParcelableArray("search_results", (Parcelable[])paramAnonymousList.toArray(new MediaBrowserCompat.MediaItem[0]));
          paramResultReceiver.send(0, localBundle);
          return;
        }
        paramResultReceiver.send(-1, null);
      }
    };
    mCurConnection = paramConnectionRecord;
    onSearch(paramString, paramBundle, paramResultReceiver);
    mCurConnection = null;
    if (paramResultReceiver.isDone()) {
      return;
    }
    paramBundle = new StringBuilder();
    paramBundle.append("onSearch must call detach() or sendResult() before returning for query=");
    paramBundle.append(paramString);
    throw new IllegalStateException(paramBundle.toString());
  }
  
  boolean removeSubscription(String paramString, ConnectionRecord paramConnectionRecord, IBinder paramIBinder)
  {
    boolean bool = false;
    if (paramIBinder == null) {
      return subscriptions.remove(paramString) != null;
    }
    List localList = (List)subscriptions.get(paramString);
    if (localList != null)
    {
      Iterator localIterator = localList.iterator();
      while (localIterator.hasNext()) {
        if (paramIBinder == nextfirst)
        {
          localIterator.remove();
          bool = true;
        }
      }
      if (localList.size() == 0)
      {
        subscriptions.remove(paramString);
        return bool;
      }
    }
    else
    {
      return false;
    }
    return bool;
  }
  
  public void setSessionToken(MediaSessionCompat.Token paramToken)
  {
    if (paramToken != null)
    {
      if (mSession == null)
      {
        mSession = paramToken;
        mImpl.setSessionToken(paramToken);
        return;
      }
      throw new IllegalStateException("The session token has already been set.");
    }
    throw new IllegalArgumentException("Session token may not be null.");
  }
  
  public static final class BrowserRoot
  {
    public static final String EXTRA_OFFLINE = "android.service.media.extra.OFFLINE";
    public static final String EXTRA_RECENT = "android.service.media.extra.RECENT";
    public static final String EXTRA_SUGGESTED = "android.service.media.extra.SUGGESTED";
    @Deprecated
    public static final String EXTRA_SUGGESTION_KEYWORDS = "android.service.media.extra.SUGGESTION_KEYWORDS";
    private final Bundle mExtras;
    private final String mRootId;
    
    public BrowserRoot(String paramString, Bundle paramBundle)
    {
      if (paramString != null)
      {
        mRootId = paramString;
        mExtras = paramBundle;
        return;
      }
      throw new IllegalArgumentException("The root id in BrowserRoot cannot be null. Use null for BrowserRoot instead.");
    }
    
    public Bundle getExtras()
    {
      return mExtras;
    }
    
    public String getRootId()
    {
      return mRootId;
    }
  }
  
  private static class ConnectionRecord
  {
    MediaBrowserServiceCompat.ServiceCallbacks callbacks;
    String pkg;
    MediaBrowserServiceCompat.BrowserRoot root;
    Bundle rootHints;
    HashMap<String, List<Pair<IBinder, Bundle>>> subscriptions = new HashMap();
    
    ConnectionRecord() {}
  }
  
  static abstract interface MediaBrowserServiceImpl
  {
    public abstract Bundle getBrowserRootHints();
    
    public abstract void notifyChildrenChanged(String paramString, Bundle paramBundle);
    
    public abstract IBinder onBind(Intent paramIntent);
    
    public abstract void onCreate();
    
    public abstract void setSessionToken(MediaSessionCompat.Token paramToken);
  }
  
  @RequiresApi(21)
  class MediaBrowserServiceImplApi21
    implements MediaBrowserServiceCompat.MediaBrowserServiceImpl, MediaBrowserServiceCompatApi21.ServiceCompatProxy
  {
    Messenger mMessenger;
    final List<Bundle> mRootExtrasList = new ArrayList();
    Object mServiceObj;
    
    MediaBrowserServiceImplApi21() {}
    
    public Bundle getBrowserRootHints()
    {
      if (mMessenger == null) {
        return null;
      }
      if (mCurConnection != null)
      {
        if (mCurConnection.rootHints == null) {
          return null;
        }
        return new Bundle(mCurConnection.rootHints);
      }
      throw new IllegalStateException("This should be called inside of onLoadChildren, onLoadItem or onSearch methods");
    }
    
    public void notifyChildrenChanged(final String paramString, final Bundle paramBundle)
    {
      if (mMessenger == null)
      {
        MediaBrowserServiceCompatApi21.notifyChildrenChanged(mServiceObj, paramString);
        return;
      }
      mHandler.post(new Runnable()
      {
        public void run()
        {
          Iterator localIterator = mConnections.keySet().iterator();
          while (localIterator.hasNext())
          {
            Object localObject1 = (IBinder)localIterator.next();
            localObject1 = (MediaBrowserServiceCompat.ConnectionRecord)mConnections.get(localObject1);
            Object localObject2 = (List)subscriptions.get(paramString);
            if (localObject2 != null)
            {
              localObject2 = ((List)localObject2).iterator();
              while (((Iterator)localObject2).hasNext())
              {
                Pair localPair = (Pair)((Iterator)localObject2).next();
                if (MediaBrowserCompatUtils.hasDuplicatedItems(paramBundle, (Bundle)second)) {
                  performLoadChildren(paramString, (MediaBrowserServiceCompat.ConnectionRecord)localObject1, (Bundle)second);
                }
              }
            }
          }
        }
      });
    }
    
    public IBinder onBind(Intent paramIntent)
    {
      return MediaBrowserServiceCompatApi21.onBind(mServiceObj, paramIntent);
    }
    
    public void onCreate()
    {
      mServiceObj = MediaBrowserServiceCompatApi21.createService(MediaBrowserServiceCompat.this, this);
      MediaBrowserServiceCompatApi21.onCreate(mServiceObj);
    }
    
    public MediaBrowserServiceCompatApi21.BrowserRoot onGetRoot(String paramString, int paramInt, Bundle paramBundle)
    {
      Object localObject;
      if ((paramBundle != null) && (paramBundle.getInt("extra_client_version", 0) != 0))
      {
        paramBundle.remove("extra_client_version");
        mMessenger = new Messenger(mHandler);
        Bundle localBundle = new Bundle();
        localBundle.putInt("extra_service_version", 1);
        BundleCompat.putBinder(localBundle, "extra_messenger", mMessenger.getBinder());
        if (mSession != null)
        {
          localObject = mSession.getExtraBinder();
          if (localObject == null) {
            localObject = null;
          } else {
            localObject = ((IInterface)localObject).asBinder();
          }
          BundleCompat.putBinder(localBundle, "extra_session_binder", (IBinder)localObject);
          localObject = localBundle;
        }
        else
        {
          mRootExtrasList.add(localBundle);
          localObject = localBundle;
        }
      }
      else
      {
        localObject = null;
      }
      paramBundle = onGetRoot(paramString, paramInt, paramBundle);
      if (paramBundle == null) {
        return null;
      }
      if (localObject == null)
      {
        paramString = paramBundle.getExtras();
      }
      else
      {
        paramString = (String)localObject;
        if (paramBundle.getExtras() != null)
        {
          ((Bundle)localObject).putAll(paramBundle.getExtras());
          paramString = (String)localObject;
        }
      }
      return new MediaBrowserServiceCompatApi21.BrowserRoot(paramBundle.getRootId(), paramString);
    }
    
    public void onLoadChildren(String paramString, final MediaBrowserServiceCompatApi21.ResultWrapper paramResultWrapper)
    {
      paramResultWrapper = new MediaBrowserServiceCompat.Result(paramString)
      {
        public void detach()
        {
          paramResultWrapper.detach();
        }
        
        void onResultSent(List paramAnonymousList)
        {
          if (paramAnonymousList != null)
          {
            ArrayList localArrayList = new ArrayList();
            Iterator localIterator = paramAnonymousList.iterator();
            for (;;)
            {
              paramAnonymousList = localArrayList;
              if (!localIterator.hasNext()) {
                break;
              }
              paramAnonymousList = (MediaBrowserCompat.MediaItem)localIterator.next();
              Parcel localParcel = Parcel.obtain();
              paramAnonymousList.writeToParcel(localParcel, 0);
              localArrayList.add(localParcel);
            }
          }
          paramAnonymousList = null;
          paramResultWrapper.sendResult(paramAnonymousList);
        }
      };
      onLoadChildren(paramString, paramResultWrapper);
    }
    
    public void setSessionToken(final MediaSessionCompat.Token paramToken)
    {
      mHandler.postOrRun(new Runnable()
      {
        public void run()
        {
          if (!mRootExtrasList.isEmpty())
          {
            IMediaSession localIMediaSession = paramToken.getExtraBinder();
            if (localIMediaSession != null)
            {
              Iterator localIterator = mRootExtrasList.iterator();
              while (localIterator.hasNext()) {
                BundleCompat.putBinder((Bundle)localIterator.next(), "extra_session_binder", localIMediaSession.asBinder());
              }
            }
            mRootExtrasList.clear();
          }
          MediaBrowserServiceCompatApi21.setSessionToken(mServiceObj, paramToken.getToken());
        }
      });
    }
  }
  
  @RequiresApi(23)
  class MediaBrowserServiceImplApi23
    extends MediaBrowserServiceCompat.MediaBrowserServiceImplApi21
    implements MediaBrowserServiceCompatApi23.ServiceCompatProxy
  {
    MediaBrowserServiceImplApi23()
    {
      super();
    }
    
    public void onCreate()
    {
      mServiceObj = MediaBrowserServiceCompatApi23.createService(MediaBrowserServiceCompat.this, this);
      MediaBrowserServiceCompatApi21.onCreate(mServiceObj);
    }
    
    public void onLoadItem(String paramString, final MediaBrowserServiceCompatApi21.ResultWrapper paramResultWrapper)
    {
      paramResultWrapper = new MediaBrowserServiceCompat.Result(paramString)
      {
        public void detach()
        {
          paramResultWrapper.detach();
        }
        
        void onResultSent(MediaBrowserCompat.MediaItem paramAnonymousMediaItem)
        {
          if (paramAnonymousMediaItem == null)
          {
            paramResultWrapper.sendResult(null);
            return;
          }
          Parcel localParcel = Parcel.obtain();
          paramAnonymousMediaItem.writeToParcel(localParcel, 0);
          paramResultWrapper.sendResult(localParcel);
        }
      };
      onLoadItem(paramString, paramResultWrapper);
    }
  }
  
  @RequiresApi(26)
  class MediaBrowserServiceImplApi24
    extends MediaBrowserServiceCompat.MediaBrowserServiceImplApi23
    implements MediaBrowserServiceCompatApi24.ServiceCompatProxy
  {
    MediaBrowserServiceImplApi24()
    {
      super();
    }
    
    public Bundle getBrowserRootHints()
    {
      if (mCurConnection != null)
      {
        if (mCurConnection.rootHints == null) {
          return null;
        }
        return new Bundle(mCurConnection.rootHints);
      }
      return MediaBrowserServiceCompatApi24.getBrowserRootHints(mServiceObj);
    }
    
    public void notifyChildrenChanged(String paramString, Bundle paramBundle)
    {
      if (paramBundle == null)
      {
        MediaBrowserServiceCompatApi21.notifyChildrenChanged(mServiceObj, paramString);
        return;
      }
      MediaBrowserServiceCompatApi24.notifyChildrenChanged(mServiceObj, paramString, paramBundle);
    }
    
    public void onCreate()
    {
      mServiceObj = MediaBrowserServiceCompatApi24.createService(MediaBrowserServiceCompat.this, this);
      MediaBrowserServiceCompatApi21.onCreate(mServiceObj);
    }
    
    public void onLoadChildren(String paramString, final MediaBrowserServiceCompatApi24.ResultWrapper paramResultWrapper, Bundle paramBundle)
    {
      paramResultWrapper = new MediaBrowserServiceCompat.Result(paramString)
      {
        public void detach()
        {
          paramResultWrapper.detach();
        }
        
        void onResultSent(List paramAnonymousList)
        {
          if (paramAnonymousList != null)
          {
            ArrayList localArrayList = new ArrayList();
            Iterator localIterator = paramAnonymousList.iterator();
            for (;;)
            {
              paramAnonymousList = localArrayList;
              if (!localIterator.hasNext()) {
                break;
              }
              paramAnonymousList = (MediaBrowserCompat.MediaItem)localIterator.next();
              Parcel localParcel = Parcel.obtain();
              paramAnonymousList.writeToParcel(localParcel, 0);
              localArrayList.add(localParcel);
            }
          }
          paramAnonymousList = null;
          paramResultWrapper.sendResult(paramAnonymousList, getFlags());
        }
      };
      onLoadChildren(paramString, paramResultWrapper, paramBundle);
    }
  }
  
  class MediaBrowserServiceImplBase
    implements MediaBrowserServiceCompat.MediaBrowserServiceImpl
  {
    private Messenger mMessenger;
    
    MediaBrowserServiceImplBase() {}
    
    public Bundle getBrowserRootHints()
    {
      if (mCurConnection != null)
      {
        if (mCurConnection.rootHints == null) {
          return null;
        }
        return new Bundle(mCurConnection.rootHints);
      }
      throw new IllegalStateException("This should be called inside of onLoadChildren, onLoadItem or onSearch methods");
    }
    
    public void notifyChildrenChanged(final String paramString, final Bundle paramBundle)
    {
      mHandler.post(new Runnable()
      {
        public void run()
        {
          Iterator localIterator = mConnections.keySet().iterator();
          while (localIterator.hasNext())
          {
            Object localObject1 = (IBinder)localIterator.next();
            localObject1 = (MediaBrowserServiceCompat.ConnectionRecord)mConnections.get(localObject1);
            Object localObject2 = (List)subscriptions.get(paramString);
            if (localObject2 != null)
            {
              localObject2 = ((List)localObject2).iterator();
              while (((Iterator)localObject2).hasNext())
              {
                Pair localPair = (Pair)((Iterator)localObject2).next();
                if (MediaBrowserCompatUtils.hasDuplicatedItems(paramBundle, (Bundle)second)) {
                  performLoadChildren(paramString, (MediaBrowserServiceCompat.ConnectionRecord)localObject1, (Bundle)second);
                }
              }
            }
          }
        }
      });
    }
    
    public IBinder onBind(Intent paramIntent)
    {
      if ("android.media.browse.MediaBrowserService".equals(paramIntent.getAction())) {
        return mMessenger.getBinder();
      }
      return null;
    }
    
    public void onCreate()
    {
      mMessenger = new Messenger(mHandler);
    }
    
    public void setSessionToken(final MediaSessionCompat.Token paramToken)
    {
      mHandler.post(new Runnable()
      {
        public void run()
        {
          Iterator localIterator = mConnections.values().iterator();
          while (localIterator.hasNext())
          {
            MediaBrowserServiceCompat.ConnectionRecord localConnectionRecord = (MediaBrowserServiceCompat.ConnectionRecord)localIterator.next();
            Object localObject1 = callbacks;
            Object localObject2 = root;
            try
            {
              localObject2 = ((MediaBrowserServiceCompat.BrowserRoot)localObject2).getRootId();
              MediaSessionCompat.Token localToken = paramToken;
              MediaBrowserServiceCompat.BrowserRoot localBrowserRoot = root;
              ((MediaBrowserServiceCompat.ServiceCallbacks)localObject1).onConnect((String)localObject2, localToken, localBrowserRoot.getExtras());
            }
            catch (RemoteException localRemoteException)
            {
              for (;;) {}
            }
            localObject1 = new StringBuilder();
            ((StringBuilder)localObject1).append("Connection for ");
            ((StringBuilder)localObject1).append(pkg);
            ((StringBuilder)localObject1).append(" is no longer valid.");
            Log.w("MBServiceCompat", ((StringBuilder)localObject1).toString());
            localIterator.remove();
          }
        }
      });
    }
  }
  
  public static class Result<T>
  {
    private final Object mDebug;
    private boolean mDetachCalled;
    private int mFlags;
    private boolean mSendErrorCalled;
    private boolean mSendProgressUpdateCalled;
    private boolean mSendResultCalled;
    
    Result(Object paramObject)
    {
      mDebug = paramObject;
    }
    
    private void checkExtraFields(Bundle paramBundle)
    {
      if (paramBundle == null) {
        return;
      }
      if (paramBundle.containsKey("android.media.browse.extra.DOWNLOAD_PROGRESS"))
      {
        float f = paramBundle.getFloat("android.media.browse.extra.DOWNLOAD_PROGRESS");
        if ((f >= -1.0E-5F) && (f <= 1.00001F)) {
          return;
        }
        throw new IllegalArgumentException("The value of the EXTRA_DOWNLOAD_PROGRESS field must be a float number within [0.0, 1.0].");
      }
    }
    
    public void detach()
    {
      if (!mDetachCalled)
      {
        if (!mSendResultCalled)
        {
          if (!mSendErrorCalled)
          {
            mDetachCalled = true;
            return;
          }
          localStringBuilder = new StringBuilder();
          localStringBuilder.append("detach() called when sendError() had already been called for: ");
          localStringBuilder.append(mDebug);
          throw new IllegalStateException(localStringBuilder.toString());
        }
        localStringBuilder = new StringBuilder();
        localStringBuilder.append("detach() called when sendResult() had already been called for: ");
        localStringBuilder.append(mDebug);
        throw new IllegalStateException(localStringBuilder.toString());
      }
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("detach() called when detach() had already been called for: ");
      localStringBuilder.append(mDebug);
      throw new IllegalStateException(localStringBuilder.toString());
    }
    
    int getFlags()
    {
      return mFlags;
    }
    
    boolean isDone()
    {
      return (mDetachCalled) || (mSendResultCalled) || (mSendErrorCalled);
    }
    
    void onErrorSent(Bundle paramBundle)
    {
      paramBundle = new StringBuilder();
      paramBundle.append("It is not supported to send an error for ");
      paramBundle.append(mDebug);
      throw new UnsupportedOperationException(paramBundle.toString());
    }
    
    void onProgressUpdateSent(Bundle paramBundle)
    {
      paramBundle = new StringBuilder();
      paramBundle.append("It is not supported to send an interim update for ");
      paramBundle.append(mDebug);
      throw new UnsupportedOperationException(paramBundle.toString());
    }
    
    void onResultSent(Object paramObject) {}
    
    public void sendError(Bundle paramBundle)
    {
      if ((!mSendResultCalled) && (!mSendErrorCalled))
      {
        mSendErrorCalled = true;
        onErrorSent(paramBundle);
        return;
      }
      paramBundle = new StringBuilder();
      paramBundle.append("sendError() called when either sendResult() or sendError() had already been called for: ");
      paramBundle.append(mDebug);
      throw new IllegalStateException(paramBundle.toString());
    }
    
    public void sendProgressUpdate(Bundle paramBundle)
    {
      if ((!mSendResultCalled) && (!mSendErrorCalled))
      {
        checkExtraFields(paramBundle);
        mSendProgressUpdateCalled = true;
        onProgressUpdateSent(paramBundle);
        return;
      }
      paramBundle = new StringBuilder();
      paramBundle.append("sendProgressUpdate() called when either sendResult() or sendError() had already been called for: ");
      paramBundle.append(mDebug);
      throw new IllegalStateException(paramBundle.toString());
    }
    
    public void sendResult(Object paramObject)
    {
      if ((!mSendResultCalled) && (!mSendErrorCalled))
      {
        mSendResultCalled = true;
        onResultSent(paramObject);
        return;
      }
      paramObject = new StringBuilder();
      paramObject.append("sendResult() called when either sendResult() or sendError() had already been called for: ");
      paramObject.append(mDebug);
      throw new IllegalStateException(paramObject.toString());
    }
    
    void setFlags(int paramInt)
    {
      mFlags = paramInt;
    }
  }
  
  @Retention(RetentionPolicy.SOURCE)
  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  private static @interface ResultFlags {}
  
  private class ServiceBinderImpl
  {
    ServiceBinderImpl() {}
    
    public void addSubscription(final String paramString, final IBinder paramIBinder, final Bundle paramBundle, final MediaBrowserServiceCompat.ServiceCallbacks paramServiceCallbacks)
    {
      mHandler.postOrRun(new Runnable()
      {
        public void run()
        {
          Object localObject = paramServiceCallbacks.asBinder();
          localObject = (MediaBrowserServiceCompat.ConnectionRecord)mConnections.get(localObject);
          if (localObject == null)
          {
            localObject = new StringBuilder();
            ((StringBuilder)localObject).append("addSubscription for callback that isn't registered id=");
            ((StringBuilder)localObject).append(paramString);
            Log.w("MBServiceCompat", ((StringBuilder)localObject).toString());
            return;
          }
          addSubscription(paramString, (MediaBrowserServiceCompat.ConnectionRecord)localObject, paramIBinder, paramBundle);
        }
      });
    }
    
    public void connect(final String paramString, final int paramInt, final Bundle paramBundle, final MediaBrowserServiceCompat.ServiceCallbacks paramServiceCallbacks)
    {
      if (isValidPackage(paramString, paramInt))
      {
        mHandler.postOrRun(new Runnable()
        {
          public void run()
          {
            Object localObject2 = paramServiceCallbacks.asBinder();
            mConnections.remove(localObject2);
            Object localObject3 = new MediaBrowserServiceCompat.ConnectionRecord();
            pkg = paramString;
            rootHints = paramBundle;
            Object localObject4 = paramServiceCallbacks;
            Object localObject1 = this;
            callbacks = ((MediaBrowserServiceCompat.ServiceCallbacks)localObject4);
            root = this$1.this$0.onGetRoot(paramString, paramInt, paramBundle);
            if (root == null)
            {
              localObject2 = new StringBuilder();
              ((StringBuilder)localObject2).append("No root for client ");
              ((StringBuilder)localObject2).append(paramString);
              ((StringBuilder)localObject2).append(" from service ");
              ((StringBuilder)localObject2).append(localObject1.getClass().getName());
              Log.i("MBServiceCompat", ((StringBuilder)localObject2).toString());
              localObject2 = paramServiceCallbacks;
            }
            try
            {
              ((MediaBrowserServiceCompat.ServiceCallbacks)localObject2).onConnectFailed();
              return;
            }
            catch (RemoteException localRemoteException1)
            {
              for (;;) {}
            }
            localObject2 = new StringBuilder();
            ((StringBuilder)localObject2).append("Calling onConnectFailed() failed. Ignoring. pkg=");
            ((StringBuilder)localObject2).append(paramString);
            Log.w("MBServiceCompat", ((StringBuilder)localObject2).toString());
            return;
            localObject4 = this$1.this$0.mConnections;
            try
            {
              ((SimpleArrayMap)localObject4).put(localObject2, localObject3);
              if (this$1.this$0.mSession == null) {
                return;
              }
              localObject4 = paramServiceCallbacks;
              Object localObject5 = root;
              localObject5 = ((MediaBrowserServiceCompat.BrowserRoot)localObject5).getRootId();
              localObject1 = this$1.this$0.mSession;
              localObject3 = root;
              ((MediaBrowserServiceCompat.ServiceCallbacks)localObject4).onConnect((String)localObject5, (MediaSessionCompat.Token)localObject1, ((MediaBrowserServiceCompat.BrowserRoot)localObject3).getExtras());
              return;
            }
            catch (RemoteException localObject3)
            {
              for (;;)
              {
                localObject1 = this;
                localRemoteException2 = ???;
              }
            }
            localObject3 = new StringBuilder();
            ((StringBuilder)localObject3).append("Calling onConnect() failed. Dropping client. pkg=");
            ((StringBuilder)localObject3).append(paramString);
            Log.w("MBServiceCompat", ((StringBuilder)localObject3).toString());
            this$1.this$0.mConnections.remove(localObject2);
            return;
          }
        });
        return;
      }
      paramBundle = new StringBuilder();
      paramBundle.append("Package/uid mismatch: uid=");
      paramBundle.append(paramInt);
      paramBundle.append(" package=");
      paramBundle.append(paramString);
      throw new IllegalArgumentException(paramBundle.toString());
    }
    
    public void disconnect(final MediaBrowserServiceCompat.ServiceCallbacks paramServiceCallbacks)
    {
      mHandler.postOrRun(new Runnable()
      {
        public void run()
        {
          Object localObject = paramServiceCallbacks.asBinder();
          localObject = (MediaBrowserServiceCompat.ConnectionRecord)mConnections.remove(localObject);
        }
      });
    }
    
    public void getMediaItem(final String paramString, final ResultReceiver paramResultReceiver, final MediaBrowserServiceCompat.ServiceCallbacks paramServiceCallbacks)
    {
      if (!TextUtils.isEmpty(paramString))
      {
        if (paramResultReceiver == null) {
          return;
        }
        mHandler.postOrRun(new Runnable()
        {
          public void run()
          {
            Object localObject = paramServiceCallbacks.asBinder();
            localObject = (MediaBrowserServiceCompat.ConnectionRecord)mConnections.get(localObject);
            if (localObject == null)
            {
              localObject = new StringBuilder();
              ((StringBuilder)localObject).append("getMediaItem for callback that isn't registered id=");
              ((StringBuilder)localObject).append(paramString);
              Log.w("MBServiceCompat", ((StringBuilder)localObject).toString());
              return;
            }
            performLoadItem(paramString, (MediaBrowserServiceCompat.ConnectionRecord)localObject, paramResultReceiver);
          }
        });
      }
    }
    
    public void registerCallbacks(final MediaBrowserServiceCompat.ServiceCallbacks paramServiceCallbacks, final Bundle paramBundle)
    {
      mHandler.postOrRun(new Runnable()
      {
        public void run()
        {
          IBinder localIBinder = paramServiceCallbacks.asBinder();
          mConnections.remove(localIBinder);
          MediaBrowserServiceCompat.ConnectionRecord localConnectionRecord = new MediaBrowserServiceCompat.ConnectionRecord();
          callbacks = paramServiceCallbacks;
          rootHints = paramBundle;
          mConnections.put(localIBinder, localConnectionRecord);
        }
      });
    }
    
    public void removeSubscription(final String paramString, final IBinder paramIBinder, final MediaBrowserServiceCompat.ServiceCallbacks paramServiceCallbacks)
    {
      mHandler.postOrRun(new Runnable()
      {
        public void run()
        {
          Object localObject = paramServiceCallbacks.asBinder();
          localObject = (MediaBrowserServiceCompat.ConnectionRecord)mConnections.get(localObject);
          if (localObject == null)
          {
            localObject = new StringBuilder();
            ((StringBuilder)localObject).append("removeSubscription for callback that isn't registered id=");
            ((StringBuilder)localObject).append(paramString);
            Log.w("MBServiceCompat", ((StringBuilder)localObject).toString());
            return;
          }
          if (!removeSubscription(paramString, (MediaBrowserServiceCompat.ConnectionRecord)localObject, paramIBinder))
          {
            localObject = new StringBuilder();
            ((StringBuilder)localObject).append("removeSubscription called for ");
            ((StringBuilder)localObject).append(paramString);
            ((StringBuilder)localObject).append(" which is not subscribed");
            Log.w("MBServiceCompat", ((StringBuilder)localObject).toString());
          }
        }
      });
    }
    
    public void search(final String paramString, final Bundle paramBundle, final ResultReceiver paramResultReceiver, final MediaBrowserServiceCompat.ServiceCallbacks paramServiceCallbacks)
    {
      if (!TextUtils.isEmpty(paramString))
      {
        if (paramResultReceiver == null) {
          return;
        }
        mHandler.postOrRun(new Runnable()
        {
          public void run()
          {
            Object localObject = paramServiceCallbacks.asBinder();
            localObject = (MediaBrowserServiceCompat.ConnectionRecord)mConnections.get(localObject);
            if (localObject == null)
            {
              localObject = new StringBuilder();
              ((StringBuilder)localObject).append("search for callback that isn't registered query=");
              ((StringBuilder)localObject).append(paramString);
              Log.w("MBServiceCompat", ((StringBuilder)localObject).toString());
              return;
            }
            performSearch(paramString, paramBundle, (MediaBrowserServiceCompat.ConnectionRecord)localObject, paramResultReceiver);
          }
        });
      }
    }
    
    public void sendCustomAction(final String paramString, final Bundle paramBundle, final ResultReceiver paramResultReceiver, final MediaBrowserServiceCompat.ServiceCallbacks paramServiceCallbacks)
    {
      if (!TextUtils.isEmpty(paramString))
      {
        if (paramResultReceiver == null) {
          return;
        }
        mHandler.postOrRun(new Runnable()
        {
          public void run()
          {
            Object localObject = paramServiceCallbacks.asBinder();
            localObject = (MediaBrowserServiceCompat.ConnectionRecord)mConnections.get(localObject);
            if (localObject == null)
            {
              localObject = new StringBuilder();
              ((StringBuilder)localObject).append("sendCustomAction for callback that isn't registered action=");
              ((StringBuilder)localObject).append(paramString);
              ((StringBuilder)localObject).append(", extras=");
              ((StringBuilder)localObject).append(paramBundle);
              Log.w("MBServiceCompat", ((StringBuilder)localObject).toString());
              return;
            }
            performCustomAction(paramString, paramBundle, (MediaBrowserServiceCompat.ConnectionRecord)localObject, paramResultReceiver);
          }
        });
      }
    }
    
    public void unregisterCallbacks(final MediaBrowserServiceCompat.ServiceCallbacks paramServiceCallbacks)
    {
      mHandler.postOrRun(new Runnable()
      {
        public void run()
        {
          IBinder localIBinder = paramServiceCallbacks.asBinder();
          mConnections.remove(localIBinder);
        }
      });
    }
  }
  
  private static abstract interface ServiceCallbacks
  {
    public abstract IBinder asBinder();
    
    public abstract void onConnect(String paramString, MediaSessionCompat.Token paramToken, Bundle paramBundle)
      throws RemoteException;
    
    public abstract void onConnectFailed()
      throws RemoteException;
    
    public abstract void onLoadChildren(String paramString, List paramList, Bundle paramBundle)
      throws RemoteException;
  }
  
  private static class ServiceCallbacksCompat
    implements MediaBrowserServiceCompat.ServiceCallbacks
  {
    final Messenger mCallbacks;
    
    ServiceCallbacksCompat(Messenger paramMessenger)
    {
      mCallbacks = paramMessenger;
    }
    
    private void sendRequest(int paramInt, Bundle paramBundle)
      throws RemoteException
    {
      Message localMessage = Message.obtain();
      what = paramInt;
      arg1 = 1;
      localMessage.setData(paramBundle);
      mCallbacks.send(localMessage);
    }
    
    public IBinder asBinder()
    {
      return mCallbacks.getBinder();
    }
    
    public void onConnect(String paramString, MediaSessionCompat.Token paramToken, Bundle paramBundle)
      throws RemoteException
    {
      Bundle localBundle = paramBundle;
      if (paramBundle == null) {
        localBundle = new Bundle();
      }
      localBundle.putInt("extra_service_version", 1);
      paramBundle = new Bundle();
      paramBundle.putString("data_media_item_id", paramString);
      paramBundle.putParcelable("data_media_session_token", paramToken);
      paramBundle.putBundle("data_root_hints", localBundle);
      sendRequest(1, paramBundle);
    }
    
    public void onConnectFailed()
      throws RemoteException
    {
      sendRequest(2, null);
    }
    
    public void onLoadChildren(String paramString, List paramList, Bundle paramBundle)
      throws RemoteException
    {
      Bundle localBundle = new Bundle();
      localBundle.putString("data_media_item_id", paramString);
      localBundle.putBundle("data_options", paramBundle);
      if (paramList != null)
      {
        if ((paramList instanceof ArrayList)) {
          paramString = (ArrayList)paramList;
        } else {
          paramString = new ArrayList(paramList);
        }
        localBundle.putParcelableArrayList("data_media_item_list", paramString);
      }
      sendRequest(3, localBundle);
    }
  }
  
  private final class ServiceHandler
    extends Handler
  {
    private final MediaBrowserServiceCompat.ServiceBinderImpl mServiceBinderImpl = new MediaBrowserServiceCompat.ServiceBinderImpl(MediaBrowserServiceCompat.this);
    
    ServiceHandler() {}
    
    public void handleMessage(Message paramMessage)
    {
      Object localObject = paramMessage.getData();
      switch (what)
      {
      default: 
        localObject = new StringBuilder();
        ((StringBuilder)localObject).append("Unhandled message: ");
        ((StringBuilder)localObject).append(paramMessage);
        ((StringBuilder)localObject).append("\n  Service version: ");
        ((StringBuilder)localObject).append(1);
        ((StringBuilder)localObject).append("\n  Client version: ");
        ((StringBuilder)localObject).append(arg1);
        Log.w("MBServiceCompat", ((StringBuilder)localObject).toString());
        return;
      case 9: 
        mServiceBinderImpl.sendCustomAction(((Bundle)localObject).getString("data_custom_action"), ((Bundle)localObject).getBundle("data_custom_action_extras"), (ResultReceiver)((Bundle)localObject).getParcelable("data_result_receiver"), new MediaBrowserServiceCompat.ServiceCallbacksCompat(replyTo));
        return;
      case 8: 
        mServiceBinderImpl.search(((Bundle)localObject).getString("data_search_query"), ((Bundle)localObject).getBundle("data_search_extras"), (ResultReceiver)((Bundle)localObject).getParcelable("data_result_receiver"), new MediaBrowserServiceCompat.ServiceCallbacksCompat(replyTo));
        return;
      case 7: 
        mServiceBinderImpl.unregisterCallbacks(new MediaBrowserServiceCompat.ServiceCallbacksCompat(replyTo));
        return;
      case 6: 
        mServiceBinderImpl.registerCallbacks(new MediaBrowserServiceCompat.ServiceCallbacksCompat(replyTo), ((Bundle)localObject).getBundle("data_root_hints"));
        return;
      case 5: 
        mServiceBinderImpl.getMediaItem(((Bundle)localObject).getString("data_media_item_id"), (ResultReceiver)((Bundle)localObject).getParcelable("data_result_receiver"), new MediaBrowserServiceCompat.ServiceCallbacksCompat(replyTo));
        return;
      case 4: 
        mServiceBinderImpl.removeSubscription(((Bundle)localObject).getString("data_media_item_id"), BundleCompat.getBinder((Bundle)localObject, "data_callback_token"), new MediaBrowserServiceCompat.ServiceCallbacksCompat(replyTo));
        return;
      case 3: 
        mServiceBinderImpl.addSubscription(((Bundle)localObject).getString("data_media_item_id"), BundleCompat.getBinder((Bundle)localObject, "data_callback_token"), ((Bundle)localObject).getBundle("data_options"), new MediaBrowserServiceCompat.ServiceCallbacksCompat(replyTo));
        return;
      case 2: 
        mServiceBinderImpl.disconnect(new MediaBrowserServiceCompat.ServiceCallbacksCompat(replyTo));
        return;
      }
      mServiceBinderImpl.connect(((Bundle)localObject).getString("data_package_name"), ((Bundle)localObject).getInt("data_calling_uid"), ((Bundle)localObject).getBundle("data_root_hints"), new MediaBrowserServiceCompat.ServiceCallbacksCompat(replyTo));
    }
    
    public void postOrRun(Runnable paramRunnable)
    {
      if (Thread.currentThread() == getLooper().getThread())
      {
        paramRunnable.run();
        return;
      }
      post(paramRunnable);
    }
    
    public boolean sendMessageAtTime(Message paramMessage, long paramLong)
    {
      Bundle localBundle = paramMessage.getData();
      localBundle.setClassLoader(MediaBrowserCompat.class.getClassLoader());
      localBundle.putInt("data_calling_uid", Binder.getCallingUid());
      return super.sendMessageAtTime(paramMessage, paramLong);
    }
  }
}
