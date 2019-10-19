package android.support.v4.package_7;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import java.util.ArrayList;
import java.util.Iterator;

public final class TaskStackBuilder
  implements Iterable<Intent>
{
  private static final TaskStackBuilderBaseImpl IMPL = new TaskStackBuilderBaseImpl();
  private static final String TAG = "TaskStackBuilder";
  private final ArrayList<Intent> mIntents = new ArrayList();
  private final Context mSourceContext;
  
  static
  {
    if (Build.VERSION.SDK_INT >= 16)
    {
      IMPL = new TaskStackBuilderApi16Impl();
      return;
    }
  }
  
  private TaskStackBuilder(Context paramContext)
  {
    mSourceContext = paramContext;
  }
  
  public static TaskStackBuilder create(Context paramContext)
  {
    return new TaskStackBuilder(paramContext);
  }
  
  public static TaskStackBuilder from(Context paramContext)
  {
    return create(paramContext);
  }
  
  public TaskStackBuilder addNextIntent(Intent paramIntent)
  {
    mIntents.add(paramIntent);
    return this;
  }
  
  public TaskStackBuilder addNextIntentWithParentStack(Intent paramIntent)
  {
    ComponentName localComponentName2 = paramIntent.getComponent();
    ComponentName localComponentName1 = localComponentName2;
    if (localComponentName2 == null) {
      localComponentName1 = paramIntent.resolveActivity(mSourceContext.getPackageManager());
    }
    if (localComponentName1 != null) {
      addParentStack(localComponentName1);
    }
    addNextIntent(paramIntent);
    return this;
  }
  
  public TaskStackBuilder addParentStack(Activity paramActivity)
  {
    Object localObject1;
    if ((paramActivity instanceof SupportParentable)) {
      localObject1 = ((SupportParentable)paramActivity).getSupportParentActivityIntent();
    } else {
      localObject1 = null;
    }
    Object localObject2 = localObject1;
    if (localObject1 == null) {
      localObject2 = NavUtils.getParentActivityIntent(paramActivity);
    }
    if (localObject2 != null)
    {
      localObject1 = ((Intent)localObject2).getComponent();
      paramActivity = (Activity)localObject1;
      if (localObject1 == null) {
        paramActivity = ((Intent)localObject2).resolveActivity(mSourceContext.getPackageManager());
      }
      addParentStack(paramActivity);
      addNextIntent((Intent)localObject2);
    }
    return this;
  }
  
  public TaskStackBuilder addParentStack(ComponentName paramComponentName)
  {
    int i = mIntents.size();
    Object localObject = mSourceContext;
    try
    {
      for (paramComponentName = NavUtils.getParentActivityIntent((Context)localObject, paramComponentName); paramComponentName != null; paramComponentName = NavUtils.getParentActivityIntent((Context)localObject, paramComponentName.getComponent()))
      {
        localObject = mIntents;
        ((ArrayList)localObject).add(i, paramComponentName);
        localObject = mSourceContext;
      }
      return this;
    }
    catch (PackageManager.NameNotFoundException paramComponentName)
    {
      Log.e("TaskStackBuilder", "Bad ComponentName while traversing activity parent metadata");
      throw new IllegalArgumentException(paramComponentName);
    }
  }
  
  public TaskStackBuilder addParentStack(Class paramClass)
  {
    return addParentStack(new ComponentName(mSourceContext, paramClass));
  }
  
  public Intent editIntentAt(int paramInt)
  {
    return (Intent)mIntents.get(paramInt);
  }
  
  public Intent getIntent(int paramInt)
  {
    return editIntentAt(paramInt);
  }
  
  public int getIntentCount()
  {
    return mIntents.size();
  }
  
  public Intent[] getIntents()
  {
    Intent[] arrayOfIntent = new Intent[mIntents.size()];
    if (arrayOfIntent.length == 0) {
      return arrayOfIntent;
    }
    arrayOfIntent[0] = new Intent((Intent)mIntents.get(0)).addFlags(268484608);
    int i = 1;
    while (i < arrayOfIntent.length)
    {
      arrayOfIntent[i] = new Intent((Intent)mIntents.get(i));
      i += 1;
    }
    return arrayOfIntent;
  }
  
  public PendingIntent getPendingIntent(int paramInt1, int paramInt2)
  {
    return getPendingIntent(paramInt1, paramInt2, null);
  }
  
  public PendingIntent getPendingIntent(int paramInt1, int paramInt2, Bundle paramBundle)
  {
    if (!mIntents.isEmpty())
    {
      Intent[] arrayOfIntent = (Intent[])mIntents.toArray(new Intent[mIntents.size()]);
      arrayOfIntent[0] = new Intent(arrayOfIntent[0]).addFlags(268484608);
      return IMPL.getPendingIntent(mSourceContext, arrayOfIntent, paramInt1, paramInt2, paramBundle);
    }
    throw new IllegalStateException("No intents added to TaskStackBuilder; cannot getPendingIntent");
  }
  
  public Iterator iterator()
  {
    return mIntents.iterator();
  }
  
  public void startActivities()
  {
    startActivities(null);
  }
  
  public void startActivities(Bundle paramBundle)
  {
    if (!mIntents.isEmpty())
    {
      Intent[] arrayOfIntent = (Intent[])mIntents.toArray(new Intent[mIntents.size()]);
      arrayOfIntent[0] = new Intent(arrayOfIntent[0]).addFlags(268484608);
      if (!ContextCompat.startActivities(mSourceContext, arrayOfIntent, paramBundle))
      {
        paramBundle = new Intent(arrayOfIntent[(arrayOfIntent.length - 1)]);
        paramBundle.addFlags(268435456);
        mSourceContext.startActivity(paramBundle);
      }
    }
    else
    {
      throw new IllegalStateException("No intents added to TaskStackBuilder; cannot startActivities");
    }
  }
  
  public abstract interface SupportParentable
  {
    public abstract Intent getSupportParentActivityIntent();
  }
  
  @RequiresApi(16)
  class TaskStackBuilderApi16Impl
    extends TaskStackBuilder.TaskStackBuilderBaseImpl
  {
    TaskStackBuilderApi16Impl() {}
    
    public PendingIntent getPendingIntent(Context paramContext, Intent[] paramArrayOfIntent, int paramInt1, int paramInt2, Bundle paramBundle)
    {
      paramArrayOfIntent[0] = new Intent(paramArrayOfIntent[0]).addFlags(268484608);
      return PendingIntent.getActivities(paramContext, paramInt1, paramArrayOfIntent, paramInt2, paramBundle);
    }
  }
  
  class TaskStackBuilderBaseImpl
  {
    TaskStackBuilderBaseImpl() {}
    
    public PendingIntent getPendingIntent(Context paramContext, Intent[] paramArrayOfIntent, int paramInt1, int paramInt2, Bundle paramBundle)
    {
      paramArrayOfIntent[0] = new Intent(paramArrayOfIntent[0]).addFlags(268484608);
      return PendingIntent.getActivities(paramContext, paramInt1, paramArrayOfIntent, paramInt2);
    }
  }
}
