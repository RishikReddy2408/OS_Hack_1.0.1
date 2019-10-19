package com.google.android.android.common.images;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.ParcelFileDescriptor;
import android.os.ResultReceiver;
import android.os.SystemClock;
import android.support.v4.util.LruCache;
import android.util.Log;
import android.widget.ImageView;
import com.google.android.android.common.internal.Asserts;
import com.google.android.android.internal.base.Credentials;
import com.google.android.android.internal.base.DisplayImageOptions;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.common.images.zaa;
import com.google.android.gms.common.images.zab;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class ImageManager
{
  private static final Object zamg = new Object();
  private static HashSet<Uri> zamh = new HashSet();
  private static ImageManager zami;
  private final Context mContext;
  private final Handler mHandler;
  private final ExecutorService zamj;
  private final zaa zamk;
  private final DisplayImageOptions zaml;
  private final Map<zaa, com.google.android.gms.common.images.ImageManager.ImageReceiver> zamm;
  private final Map<Uri, com.google.android.gms.common.images.ImageManager.ImageReceiver> zamn;
  private final Map<Uri, Long> zamo;
  
  private ImageManager(Context paramContext, boolean paramBoolean)
  {
    mContext = paramContext.getApplicationContext();
    mHandler = new Credentials(Looper.getMainLooper());
    zamj = Executors.newFixedThreadPool(4);
    zamk = null;
    zaml = new DisplayImageOptions();
    zamm = new HashMap();
    zamn = new HashMap();
    zamo = new HashMap();
  }
  
  public static ImageManager create(Context paramContext)
  {
    if (zami == null) {
      zami = new ImageManager(paramContext, false);
    }
    return zami;
  }
  
  private final void flush(ImageLoader paramImageLoader)
  {
    Asserts.checkMainThread("ImageManager.loadImage() must be called in the main thread");
    new zac(paramImageLoader).run();
  }
  
  private final Bitmap get(Job paramJob)
  {
    if (zamk == null) {
      return null;
    }
    return (Bitmap)zamk.get(paramJob);
  }
  
  public final void loadImage(ImageView paramImageView, int paramInt)
  {
    flush(new Layer(paramImageView, paramInt));
  }
  
  public final void loadImage(ImageView paramImageView, Uri paramUri)
  {
    flush(new Layer(paramImageView, paramUri));
  }
  
  public final void loadImage(ImageView paramImageView, Uri paramUri, int paramInt)
  {
    paramImageView = new Layer(paramImageView, paramUri);
    zamw = paramInt;
    flush(paramImageView);
  }
  
  public final void loadImage(OnImageLoadedListener paramOnImageLoadedListener, Uri paramUri)
  {
    flush(new Image(paramOnImageLoadedListener, paramUri));
  }
  
  public final void loadImage(OnImageLoadedListener paramOnImageLoadedListener, Uri paramUri, int paramInt)
  {
    paramOnImageLoadedListener = new Image(paramOnImageLoadedListener, paramUri);
    zamw = paramInt;
    flush(paramOnImageLoadedListener);
  }
  
  @KeepName
  final class ImageReceiver
    extends ResultReceiver
  {
    private final Uri mUri;
    private final ArrayList<zaa> zamp;
    
    ImageReceiver(Uri paramUri)
    {
      super();
      mUri = paramUri;
      zamp = new ArrayList();
    }
    
    public final void Refresh(ImageLoader paramImageLoader)
    {
      Asserts.checkMainThread("ImageReceiver.addImageRequest() must be called in the main thread");
      zamp.add(paramImageLoader);
    }
    
    public final void onCancelled(ImageLoader paramImageLoader)
    {
      Asserts.checkMainThread("ImageReceiver.removeImageRequest() must be called in the main thread");
      zamp.remove(paramImageLoader);
    }
    
    public final void onReceiveResult(int paramInt, Bundle paramBundle)
    {
      paramBundle = (ParcelFileDescriptor)paramBundle.getParcelable("com.google.android.gms.extra.fileDescriptor");
      ImageManager.getExecutor(ImageManager.this).execute(new ImageManager.zab(ImageManager.this, mUri, paramBundle));
    }
    
    public final void zace()
    {
      Intent localIntent = new Intent("com.google.android.gms.common.images.LOAD_IMAGE");
      localIntent.putExtra("com.google.android.gms.extras.uri", mUri);
      localIntent.putExtra("com.google.android.gms.extras.resultReceiver", this);
      localIntent.putExtra("com.google.android.gms.extras.priority", 3);
      ImageManager.getImage(ImageManager.this).sendBroadcast(localIntent);
    }
  }
  
  public abstract interface OnImageLoadedListener
  {
    public abstract void onImageLoaded(Uri paramUri, Drawable paramDrawable, boolean paramBoolean);
  }
  
  final class zaa
    extends LruCache<zab, Bitmap>
  {}
  
  final class zab
    implements Runnable
  {
    private final Uri mUri;
    private final ParcelFileDescriptor zamr;
    
    public zab(Uri paramUri, ParcelFileDescriptor paramParcelFileDescriptor)
    {
      mUri = paramUri;
      zamr = paramParcelFileDescriptor;
    }
    
    public final void run()
    {
      Asserts.checkNotMainThread("LoadBitmapFromDiskRunnable can't be executed in the main thread");
      Object localObject2 = zamr;
      boolean bool = false;
      Object localObject1 = null;
      if (localObject2 != null)
      {
        localObject2 = zamr;
        try
        {
          localObject2 = BitmapFactory.decodeFileDescriptor(((ParcelFileDescriptor)localObject2).getFileDescriptor());
          localObject1 = localObject2;
        }
        catch (OutOfMemoryError localOutOfMemoryError)
        {
          String str = String.valueOf(mUri);
          StringBuilder localStringBuilder = new StringBuilder(String.valueOf(str).length() + 34);
          localStringBuilder.append("OOM while loading bitmap for uri: ");
          localStringBuilder.append(str);
          Log.e("ImageManager", localStringBuilder.toString(), localOutOfMemoryError);
          bool = true;
        }
        ParcelFileDescriptor localParcelFileDescriptor = zamr;
        try
        {
          localParcelFileDescriptor.close();
        }
        catch (IOException localIOException)
        {
          Log.e("ImageManager", "closed failed", localIOException);
        }
      }
      else
      {
        localObject1 = null;
        bool = false;
      }
      Object localObject3 = new CountDownLatch(1);
      ImageManager.getHandler(ImageManager.this).post(new ImageManager.zad(ImageManager.this, mUri, (Bitmap)localObject1, bool, (CountDownLatch)localObject3));
      try
      {
        ((CountDownLatch)localObject3).await();
        return;
      }
      catch (InterruptedException localInterruptedException)
      {
        for (;;) {}
      }
      localObject1 = String.valueOf(mUri);
      localObject3 = new StringBuilder(String.valueOf(localObject1).length() + 32);
      ((StringBuilder)localObject3).append("Latch interrupted while posting ");
      ((StringBuilder)localObject3).append((String)localObject1);
      Log.w("ImageManager", ((StringBuilder)localObject3).toString());
    }
  }
  
  final class zac
    implements Runnable
  {
    private final ImageLoader zams;
    
    public zac(ImageLoader paramImageLoader)
    {
      zams = paramImageLoader;
    }
    
    public final void run()
    {
      Asserts.checkMainThread("LoadImageRunnable must be executed on the main thread");
      Object localObject1 = (ImageManager.ImageReceiver)ImageManager.get(ImageManager.this).get(zams);
      if (localObject1 != null)
      {
        ImageManager.get(ImageManager.this).remove(zams);
        ((ImageManager.ImageReceiver)localObject1).onCancelled(zams);
      }
      Job localJob = zams.zamu;
      if (key == null)
      {
        zams.loadImage(ImageManager.getImage(ImageManager.this), ImageManager.build(ImageManager.this), true);
        return;
      }
      localObject1 = ImageManager.get(ImageManager.this, localJob);
      if (localObject1 != null)
      {
        zams.load(ImageManager.getImage(ImageManager.this), (Bitmap)localObject1, true);
        return;
      }
      localObject1 = (Long)ImageManager.put(ImageManager.this).get(key);
      if (localObject1 != null)
      {
        if (SystemClock.elapsedRealtime() - ((Long)localObject1).longValue() < 3600000L)
        {
          zams.loadImage(ImageManager.getImage(ImageManager.this), ImageManager.build(ImageManager.this), true);
          return;
        }
        ImageManager.put(ImageManager.this).remove(key);
      }
      zams.loadImage(ImageManager.getImage(ImageManager.this), ImageManager.build(ImageManager.this));
      Object localObject2 = (ImageManager.ImageReceiver)ImageManager.getCache(ImageManager.this).get(key);
      localObject1 = localObject2;
      if (localObject2 == null)
      {
        localObject1 = new ImageManager.ImageReceiver(ImageManager.this, key);
        ImageManager.getCache(ImageManager.this).put(key, localObject1);
      }
      ((ImageManager.ImageReceiver)localObject1).Refresh(zams);
      if (!(zams instanceof Image)) {
        ImageManager.get(ImageManager.this).put(zams, localObject1);
      }
      localObject2 = ImageManager.zacc();
      try
      {
        if (!ImageManager.zacd().contains(key))
        {
          ImageManager.zacd().add(key);
          ((ImageManager.ImageReceiver)localObject1).zace();
        }
        return;
      }
      catch (Throwable localThrowable)
      {
        throw localThrowable;
      }
    }
  }
  
  final class zad
    implements Runnable
  {
    private final Bitmap mBitmap;
    private final Uri mUri;
    private final CountDownLatch zadq;
    private boolean zamt;
    
    public zad(Uri paramUri, Bitmap paramBitmap, boolean paramBoolean, CountDownLatch paramCountDownLatch)
    {
      mUri = paramUri;
      mBitmap = paramBitmap;
      zamt = paramBoolean;
      zadq = paramCountDownLatch;
    }
    
    public final void run()
    {
      Asserts.checkMainThread("OnBitmapLoadedRunnable must be executed in the main thread");
      int i;
      if (mBitmap != null) {
        i = 1;
      } else {
        i = 0;
      }
      if (ImageManager.writeFile(ImageManager.this) != null)
      {
        if (zamt)
        {
          ImageManager.writeFile(ImageManager.this).evictAll();
          System.gc();
          zamt = false;
          ImageManager.getHandler(ImageManager.this).post(this);
          return;
        }
        if (i != 0) {
          ImageManager.writeFile(ImageManager.this).put(new Job(mUri), mBitmap);
        }
      }
      Object localObject = (ImageManager.ImageReceiver)ImageManager.getCache(ImageManager.this).remove(mUri);
      if (localObject != null)
      {
        localObject = ImageManager.ImageReceiver.access$getFiles((ImageManager.ImageReceiver)localObject);
        int k = ((ArrayList)localObject).size();
        int j = 0;
        while (j < k)
        {
          ImageLoader localImageLoader = (ImageLoader)((ArrayList)localObject).get(j);
          if (i != 0)
          {
            localImageLoader.load(ImageManager.getImage(ImageManager.this), mBitmap, false);
          }
          else
          {
            ImageManager.put(ImageManager.this).put(mUri, Long.valueOf(SystemClock.elapsedRealtime()));
            localImageLoader.loadImage(ImageManager.getImage(ImageManager.this), ImageManager.build(ImageManager.this), false);
          }
          if (!(localImageLoader instanceof Image)) {
            ImageManager.get(ImageManager.this).remove(localImageLoader);
          }
          j += 1;
        }
      }
      zadq.countDown();
      localObject = ImageManager.zacc();
      try
      {
        ImageManager.zacd().remove(mUri);
        return;
      }
      catch (Throwable localThrowable)
      {
        throw localThrowable;
      }
    }
  }
}
