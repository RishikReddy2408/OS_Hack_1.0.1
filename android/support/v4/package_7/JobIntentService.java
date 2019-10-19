package android.support.v4.package_7;

import android.app.Service;
import android.app.job.JobInfo;
import android.app.job.JobInfo.Builder;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobServiceEngine;
import android.app.job.JobWorkItem;
import android.content.ComponentName;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.IBinder;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.support.annotation.RequiresApi;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class JobIntentService
  extends Service
{
  static final boolean DEBUG = false;
  static final String PAGE_KEY = "JobIntentService";
  static final HashMap<ComponentName, android.support.v4.app.JobIntentService.WorkEnqueuer> sClassWorkEnqueuer = new HashMap();
  static final Object sLock = new Object();
  final ArrayList<android.support.v4.app.JobIntentService.CompatWorkItem> mCompatQueue;
  WorkEnqueuer mCompatWorkEnqueuer;
  CommandProcessor mCurProcessor;
  boolean mDestroyed = false;
  boolean mInterruptIfStopped = false;
  CompatJobEngine mJobImpl;
  boolean mStopped = false;
  
  public JobIntentService()
  {
    if (Build.VERSION.SDK_INT >= 26)
    {
      mCompatQueue = null;
      return;
    }
    mCompatQueue = new ArrayList();
  }
  
  public static void enqueueWork(Context paramContext, ComponentName paramComponentName, int paramInt, Intent paramIntent)
  {
    if (paramIntent != null)
    {
      Object localObject = sLock;
      try
      {
        paramContext = getWorkEnqueuer(paramContext, paramComponentName, true, paramInt);
        paramContext.ensureJobId(paramInt);
        paramContext.enqueueWork(paramIntent);
        return;
      }
      catch (Throwable paramContext)
      {
        throw paramContext;
      }
    }
    throw new IllegalArgumentException("work must not be null");
  }
  
  public static void enqueueWork(Context paramContext, Class paramClass, int paramInt, Intent paramIntent)
  {
    enqueueWork(paramContext, new ComponentName(paramContext, paramClass), paramInt, paramIntent);
  }
  
  static WorkEnqueuer getWorkEnqueuer(Context paramContext, ComponentName paramComponentName, boolean paramBoolean, int paramInt)
  {
    throw new Runtime("d2j fail translate: java.lang.RuntimeException: fail exe a6 = a5\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.exec(BaseAnalyze.java:92)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.exec(BaseAnalyze.java:1)\n\tat com.googlecode.dex2jar.ir.ts.Cfg.dfs(Cfg.java:255)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.analyze0(BaseAnalyze.java:75)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.analyze(BaseAnalyze.java:69)\n\tat com.googlecode.dex2jar.ir.ts.UnSSATransformer.transform(UnSSATransformer.java:274)\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:163)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\nCaused by: java.lang.NullPointerException\n");
  }
  
  GenericWorkItem dequeueWork()
  {
    if (mJobImpl != null) {
      return mJobImpl.dequeueWork();
    }
    ArrayList localArrayList = mCompatQueue;
    try
    {
      if (mCompatQueue.size() > 0)
      {
        GenericWorkItem localGenericWorkItem = (GenericWorkItem)mCompatQueue.remove(0);
        return localGenericWorkItem;
      }
      return null;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  boolean doStopCurrentWork()
  {
    if (mCurProcessor != null) {
      mCurProcessor.cancel(mInterruptIfStopped);
    }
    mStopped = true;
    return onStopCurrentWork();
  }
  
  void ensureProcessorRunningLocked(boolean paramBoolean)
  {
    if (mCurProcessor == null)
    {
      mCurProcessor = new CommandProcessor();
      if ((mCompatWorkEnqueuer != null) && (paramBoolean)) {
        mCompatWorkEnqueuer.serviceProcessingStarted();
      }
      mCurProcessor.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }
  }
  
  public boolean isStopped()
  {
    return mStopped;
  }
  
  public IBinder onBind(Intent paramIntent)
  {
    if (mJobImpl != null) {
      return mJobImpl.compatGetBinder();
    }
    return null;
  }
  
  public void onCreate()
  {
    super.onCreate();
    if (Build.VERSION.SDK_INT >= 26)
    {
      mJobImpl = new JobServiceEngineImpl();
      mCompatWorkEnqueuer = null;
      return;
    }
    mJobImpl = null;
    mCompatWorkEnqueuer = getWorkEnqueuer(this, new ComponentName(this, getClass()), false, 0);
  }
  
  public void onDestroy()
  {
    super.onDestroy();
    if (mCompatQueue != null)
    {
      ArrayList localArrayList = mCompatQueue;
      try
      {
        mDestroyed = true;
        mCompatWorkEnqueuer.serviceProcessingFinished();
        return;
      }
      catch (Throwable localThrowable)
      {
        throw localThrowable;
      }
    }
  }
  
  protected abstract void onHandleWork(Intent paramIntent);
  
  public int onStartCommand(Intent paramIntent, int paramInt1, int paramInt2)
  {
    if (mCompatQueue != null)
    {
      mCompatWorkEnqueuer.serviceStartReceived();
      ArrayList localArrayList1 = mCompatQueue;
      try
      {
        ArrayList localArrayList2 = mCompatQueue;
        if (paramIntent == null) {
          paramIntent = new Intent();
        }
        localArrayList2.add(new CompatWorkItem(paramIntent, paramInt2));
        ensureProcessorRunningLocked(true);
        return 3;
      }
      catch (Throwable paramIntent)
      {
        throw paramIntent;
      }
    }
    return 2;
  }
  
  public boolean onStopCurrentWork()
  {
    return true;
  }
  
  void processorFinished()
  {
    if (mCompatQueue != null)
    {
      ArrayList localArrayList = mCompatQueue;
      try
      {
        mCurProcessor = null;
        if ((mCompatQueue != null) && (mCompatQueue.size() > 0)) {
          ensureProcessorRunningLocked(false);
        } else if (!mDestroyed) {
          mCompatWorkEnqueuer.serviceProcessingFinished();
        }
        return;
      }
      catch (Throwable localThrowable)
      {
        throw localThrowable;
      }
    }
  }
  
  public void setInterruptIfStopped(boolean paramBoolean)
  {
    mInterruptIfStopped = paramBoolean;
  }
  
  final class CommandProcessor
    extends AsyncTask<Void, Void, Void>
  {
    CommandProcessor() {}
    
    protected Void doInBackground(Void... paramVarArgs)
    {
      for (;;)
      {
        paramVarArgs = dequeueWork();
        if (paramVarArgs == null) {
          break;
        }
        onHandleWork(paramVarArgs.getIntent());
        paramVarArgs.complete();
      }
      return null;
    }
    
    protected void onCancelled(Void paramVoid)
    {
      processorFinished();
    }
    
    protected void onPostExecute(Void paramVoid)
    {
      processorFinished();
    }
  }
  
  abstract interface CompatJobEngine
  {
    public abstract IBinder compatGetBinder();
    
    public abstract JobIntentService.GenericWorkItem dequeueWork();
  }
  
  final class CompatWorkEnqueuer
    extends JobIntentService.WorkEnqueuer
  {
    private final Context mContext;
    private final PowerManager.WakeLock mLaunchWakeLock;
    boolean mLaunchingService;
    private final PowerManager.WakeLock mRunWakeLock;
    boolean mServiceProcessing;
    
    CompatWorkEnqueuer(ComponentName paramComponentName)
    {
      super(paramComponentName);
      mContext = this$1.getApplicationContext();
      this$1 = (PowerManager)this$1.getSystemService("power");
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(paramComponentName.getClassName());
      localStringBuilder.append(":launch");
      mLaunchWakeLock = this$1.newWakeLock(1, localStringBuilder.toString());
      mLaunchWakeLock.setReferenceCounted(false);
      localStringBuilder = new StringBuilder();
      localStringBuilder.append(paramComponentName.getClassName());
      localStringBuilder.append(":run");
      mRunWakeLock = this$1.newWakeLock(1, localStringBuilder.toString());
      mRunWakeLock.setReferenceCounted(false);
    }
    
    void enqueueWork(Intent paramIntent)
    {
      paramIntent = new Intent(paramIntent);
      paramIntent.setComponent(mComponentName);
      if (mContext.startService(paramIntent) != null) {
        try
        {
          if (!mLaunchingService)
          {
            mLaunchingService = true;
            if (!mServiceProcessing) {
              mLaunchWakeLock.acquire(60000L);
            }
          }
          return;
        }
        catch (Throwable paramIntent)
        {
          throw paramIntent;
        }
      }
    }
    
    public void serviceProcessingFinished()
    {
      try
      {
        if (mServiceProcessing)
        {
          if (mLaunchingService) {
            mLaunchWakeLock.acquire(60000L);
          }
          mServiceProcessing = false;
          mRunWakeLock.release();
        }
        return;
      }
      catch (Throwable localThrowable)
      {
        throw localThrowable;
      }
    }
    
    public void serviceProcessingStarted()
    {
      try
      {
        if (!mServiceProcessing)
        {
          mServiceProcessing = true;
          mRunWakeLock.acquire(600000L);
          mLaunchWakeLock.release();
        }
        return;
      }
      catch (Throwable localThrowable)
      {
        throw localThrowable;
      }
    }
    
    public void serviceStartReceived()
    {
      try
      {
        mLaunchingService = false;
        return;
      }
      catch (Throwable localThrowable)
      {
        throw localThrowable;
      }
    }
  }
  
  final class CompatWorkItem
    implements JobIntentService.GenericWorkItem
  {
    final Intent mIntent;
    final int mStartId;
    
    CompatWorkItem(Intent paramIntent, int paramInt)
    {
      mIntent = paramIntent;
      mStartId = paramInt;
    }
    
    public void complete()
    {
      stopSelf(mStartId);
    }
    
    public Intent getIntent()
    {
      return mIntent;
    }
  }
  
  abstract interface GenericWorkItem
  {
    public abstract void complete();
    
    public abstract Intent getIntent();
  }
  
  @RequiresApi(26)
  final class JobServiceEngineImpl
    extends JobServiceEngine
    implements JobIntentService.CompatJobEngine
  {
    static final boolean DEBUG = false;
    static final String PAGE_KEY = "JobServiceEngineImpl";
    final Object mLock = new Object();
    JobParameters mParams;
    
    JobServiceEngineImpl()
    {
      super();
    }
    
    public IBinder compatGetBinder()
    {
      return getBinder();
    }
    
    public JobIntentService.GenericWorkItem dequeueWork()
    {
      Object localObject = mLock;
      try
      {
        if (mParams == null) {
          return null;
        }
        JobWorkItem localJobWorkItem = mParams.dequeueWork();
        if (localJobWorkItem != null)
        {
          localJobWorkItem.getIntent().setExtrasClassLoader(getClassLoader());
          return new WrapperWorkItem(localJobWorkItem);
        }
        return null;
      }
      catch (Throwable localThrowable)
      {
        throw localThrowable;
      }
    }
    
    public boolean onStartJob(JobParameters paramJobParameters)
    {
      mParams = paramJobParameters;
      ensureProcessorRunningLocked(false);
      return true;
    }
    
    public boolean onStopJob(JobParameters paramJobParameters)
    {
      boolean bool = doStopCurrentWork();
      paramJobParameters = mLock;
      try
      {
        mParams = null;
        return bool;
      }
      catch (Throwable localThrowable)
      {
        throw localThrowable;
      }
    }
    
    final class WrapperWorkItem
      implements JobIntentService.GenericWorkItem
    {
      final JobWorkItem mJobWork;
      
      WrapperWorkItem(JobWorkItem paramJobWorkItem)
      {
        mJobWork = paramJobWorkItem;
      }
      
      public void complete()
      {
        Object localObject = mLock;
        try
        {
          if (mParams != null) {
            mParams.completeWork(mJobWork);
          }
          return;
        }
        catch (Throwable localThrowable)
        {
          throw localThrowable;
        }
      }
      
      public Intent getIntent()
      {
        return mJobWork.getIntent();
      }
    }
  }
  
  @RequiresApi(26)
  final class JobWorkEnqueuer
    extends JobIntentService.WorkEnqueuer
  {
    private final JobInfo mJobInfo;
    private final JobScheduler mJobScheduler;
    
    JobWorkEnqueuer(ComponentName paramComponentName, int paramInt)
    {
      super(paramComponentName);
      ensureJobId(paramInt);
      mJobInfo = new JobInfo.Builder(paramInt, mComponentName).setOverrideDeadline(0L).build();
      mJobScheduler = ((JobScheduler)this$1.getApplicationContext().getSystemService("jobscheduler"));
    }
    
    void enqueueWork(Intent paramIntent)
    {
      mJobScheduler.enqueue(mJobInfo, new JobWorkItem(paramIntent));
    }
  }
  
  abstract class WorkEnqueuer
  {
    final ComponentName mComponentName;
    boolean mHasJobId;
    int mJobId;
    
    WorkEnqueuer(ComponentName paramComponentName)
    {
      mComponentName = paramComponentName;
    }
    
    abstract void enqueueWork(Intent paramIntent);
    
    void ensureJobId(int paramInt)
    {
      if (!mHasJobId)
      {
        mHasJobId = true;
        mJobId = paramInt;
        return;
      }
      if (mJobId == paramInt) {
        return;
      }
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Given job ID ");
      localStringBuilder.append(paramInt);
      localStringBuilder.append(" is different than previous ");
      localStringBuilder.append(mJobId);
      throw new IllegalArgumentException(localStringBuilder.toString());
    }
    
    public void serviceProcessingFinished() {}
    
    public void serviceProcessingStarted() {}
    
    public void serviceStartReceived() {}
  }
}
