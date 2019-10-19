package com.google.firebase.package_8;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v4.util.SimpleArrayMap;
import android.util.Log;
import com.google.android.android.tasks.Task;
import com.google.android.android.tasks.Tasks;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.concurrent.GuardedBy;

final class zzat
{
  private static int zzcf;
  private static PendingIntent zzcr;
  private final Context context;
  private final zzan zzan;
  @GuardedBy("responseCallbacks")
  private final SimpleArrayMap<String, com.google.android.gms.tasks.TaskCompletionSource<Bundle>> zzcs = new SimpleArrayMap();
  private Messenger zzct;
  private Messenger zzcu;
  private Position zzcv;
  
  public zzat(Context paramContext, zzan paramZzan)
  {
    context = paramContext;
    zzan = paramZzan;
    zzct = new Messenger(new zzau(this, Looper.getMainLooper()));
  }
  
  private final Bundle doInBackground(Bundle paramBundle)
    throws IOException
  {
    String str = zzah();
    Object localObject1 = new com.google.android.android.tasks.TaskCompletionSource();
    localObject2 = zzcs;
    for (;;)
    {
      try
      {
        zzcs.put(str, localObject1);
        if (zzan.zzac() != 0)
        {
          localObject2 = new Intent();
          ((Intent)localObject2).setPackage("com.google.android.gms");
          if (zzan.zzac() == 2) {
            ((Intent)localObject2).setAction("com.google.iid.TOKEN_REQUEST");
          } else {
            ((Intent)localObject2).setAction("com.google.android.c2dm.intent.REGISTER");
          }
          ((Intent)localObject2).putExtras(paramBundle);
          setAlarm(context, (Intent)localObject2);
          paramBundle = new StringBuilder(String.valueOf(str).length() + 5);
          paramBundle.append("|ID|");
          paramBundle.append(str);
          paramBundle.append("|");
          ((Intent)localObject2).putExtra("kid", paramBundle.toString());
          if (Log.isLoggable("FirebaseInstanceId", 3))
          {
            paramBundle = String.valueOf(((Intent)localObject2).getExtras());
            localObject3 = new StringBuilder(String.valueOf(paramBundle).length() + 8);
            ((StringBuilder)localObject3).append("Sending ");
            ((StringBuilder)localObject3).append(paramBundle);
            Log.d("FirebaseInstanceId", ((StringBuilder)localObject3).toString());
          }
          ((Intent)localObject2).putExtra("google.messenger", zzct);
          if ((zzcu != null) || (zzcv != null))
          {
            paramBundle = Message.obtain();
            obj = localObject2;
            if (zzcu != null) {
              localObject3 = zzcu;
            }
          }
        }
      }
      catch (Throwable paramBundle)
      {
        Object localObject3;
        throw paramBundle;
      }
      try
      {
        ((Messenger)localObject3).send(paramBundle);
      }
      catch (RemoteException paramBundle) {}
    }
    localObject3 = zzcv;
    ((Position)localObject3).send(paramBundle);
    break label342;
    if (Log.isLoggable("FirebaseInstanceId", 3)) {
      Log.d("FirebaseInstanceId", "Messenger failed, fallback to startService");
    }
    if (zzan.zzac() == 2) {
      context.sendBroadcast((Intent)localObject2);
    } else {
      context.startService((Intent)localObject2);
    }
    try
    {
      try
      {
        label342:
        paramBundle = ((com.google.android.android.tasks.TaskCompletionSource)localObject1).getTask();
        localObject1 = TimeUnit.MILLISECONDS;
        paramBundle = Tasks.await(paramBundle, 30000L, (TimeUnit)localObject1);
        localObject1 = (Bundle)paramBundle;
        paramBundle = zzcs;
        try
        {
          zzcs.remove(str);
          return localObject1;
        }
        catch (Throwable localThrowable1)
        {
          throw localThrowable1;
        }
        Log.w("FirebaseInstanceId", "No response");
      }
      catch (Throwable localThrowable3) {}catch (ExecutionException paramBundle)
      {
        throw new IOException(paramBundle);
      }
    }
    catch (InterruptedException paramBundle)
    {
      for (;;) {}
    }
    catch (TimeoutException paramBundle)
    {
      for (;;) {}
    }
    throw new IOException("TIMEOUT");
    paramBundle = zzcs;
    try
    {
      zzcs.remove(localThrowable1);
      throw localThrowable3;
    }
    catch (Throwable localThrowable2)
    {
      throw localThrowable2;
    }
    throw new IOException("MISSING_INSTANCEID_SERVICE");
  }
  
  private final void doInBackground(Message paramMessage)
  {
    if ((paramMessage != null) && ((obj instanceof Intent)))
    {
      Object localObject1 = (Intent)obj;
      ((Intent)localObject1).setExtrasClassLoader(new zzl.zza());
      if (((Intent)localObject1).hasExtra("google.messenger"))
      {
        localObject1 = ((Intent)localObject1).getParcelableExtra("google.messenger");
        if ((localObject1 instanceof Position)) {
          zzcv = ((Position)localObject1);
        }
        if ((localObject1 instanceof Messenger)) {
          zzcu = ((Messenger)localObject1);
        }
      }
      Object localObject3 = (Intent)obj;
      paramMessage = ((Intent)localObject3).getAction();
      if (!"com.google.android.c2dm.intent.REGISTRATION".equals(paramMessage))
      {
        if (Log.isLoggable("FirebaseInstanceId", 3))
        {
          paramMessage = String.valueOf(paramMessage);
          if (paramMessage.length() != 0) {
            paramMessage = "Unexpected response action: ".concat(paramMessage);
          } else {
            paramMessage = new String("Unexpected response action: ");
          }
          Log.d("FirebaseInstanceId", paramMessage);
        }
      }
      else
      {
        localObject1 = ((Intent)localObject3).getStringExtra("registration_id");
        paramMessage = (Message)localObject1;
        if (localObject1 == null) {
          paramMessage = ((Intent)localObject3).getStringExtra("unregistered");
        }
        if (paramMessage == null)
        {
          localObject1 = ((Intent)localObject3).getStringExtra("error");
          if (localObject1 == null)
          {
            paramMessage = String.valueOf(((Intent)localObject3).getExtras());
            localObject1 = new StringBuilder(String.valueOf(paramMessage).length() + 49);
            ((StringBuilder)localObject1).append("Unexpected response, no error or registration id ");
            ((StringBuilder)localObject1).append(paramMessage);
            Log.w("FirebaseInstanceId", ((StringBuilder)localObject1).toString());
            return;
          }
          if (Log.isLoggable("FirebaseInstanceId", 3))
          {
            paramMessage = String.valueOf(localObject1);
            if (paramMessage.length() != 0) {
              paramMessage = "Received InstanceID error ".concat(paramMessage);
            } else {
              paramMessage = new String("Received InstanceID error ");
            }
            Log.d("FirebaseInstanceId", paramMessage);
          }
          if (((String)localObject1).startsWith("|"))
          {
            paramMessage = ((String)localObject1).split("\\|");
            if ((paramMessage.length > 2) && ("ID".equals(paramMessage[1])))
            {
              String str = paramMessage[2];
              localObject1 = paramMessage[3];
              paramMessage = (Message)localObject1;
              if (((String)localObject1).startsWith(":")) {
                paramMessage = ((String)localObject1).substring(1);
              }
              put(str, ((Intent)localObject3).putExtra("error", paramMessage).getExtras());
              return;
            }
            paramMessage = String.valueOf(localObject1);
            if (paramMessage.length() != 0) {
              paramMessage = "Unexpected structured response ".concat(paramMessage);
            } else {
              paramMessage = new String("Unexpected structured response ");
            }
            Log.w("FirebaseInstanceId", paramMessage);
            return;
          }
          paramMessage = zzcs;
          int i = 0;
          try
          {
            while (i < zzcs.size())
            {
              put((String)zzcs.keyAt(i), ((Intent)localObject3).getExtras());
              i += 1;
            }
            return;
          }
          catch (Throwable localThrowable)
          {
            throw localThrowable;
          }
        }
        Object localObject2 = Pattern.compile("\\|ID\\|([^|]+)\\|:?+(.*)").matcher(paramMessage);
        if (!((Matcher)localObject2).matches())
        {
          if (Log.isLoggable("FirebaseInstanceId", 3))
          {
            paramMessage = String.valueOf(paramMessage);
            if (paramMessage.length() != 0) {
              paramMessage = "Unexpected response string: ".concat(paramMessage);
            } else {
              paramMessage = new String("Unexpected response string: ");
            }
            Log.d("FirebaseInstanceId", paramMessage);
          }
        }
        else
        {
          paramMessage = ((Matcher)localObject2).group(1);
          localObject2 = ((Matcher)localObject2).group(2);
          localObject3 = ((Intent)localObject3).getExtras();
          ((Bundle)localObject3).putString("registration_id", (String)localObject2);
          put(paramMessage, (Bundle)localObject3);
        }
      }
    }
    else
    {
      Log.w("FirebaseInstanceId", "Dropping invalid message");
    }
  }
  
  private final Bundle get(Bundle paramBundle)
    throws IOException
  {
    Bundle localBundle = doInBackground(paramBundle);
    if ((localBundle != null) && (localBundle.containsKey("google.messenger")))
    {
      paramBundle = doInBackground(paramBundle);
      if ((paramBundle != null) && (paramBundle.containsKey("google.messenger"))) {
        return null;
      }
    }
    else
    {
      return localBundle;
    }
    return paramBundle;
  }
  
  private final void put(String paramString, Bundle paramBundle)
  {
    SimpleArrayMap localSimpleArrayMap = zzcs;
    try
    {
      com.google.android.android.tasks.TaskCompletionSource localTaskCompletionSource = (com.google.android.android.tasks.TaskCompletionSource)zzcs.remove(paramString);
      if (localTaskCompletionSource == null)
      {
        paramString = String.valueOf(paramString);
        if (paramString.length() != 0) {
          paramString = "Missing callback for ".concat(paramString);
        } else {
          paramString = new String("Missing callback for ");
        }
        Log.w("FirebaseInstanceId", paramString);
        return;
      }
      localTaskCompletionSource.setResult(paramBundle);
      return;
    }
    catch (Throwable paramString)
    {
      throw paramString;
    }
  }
  
  private static void setAlarm(Context paramContext, Intent paramIntent)
  {
    try
    {
      if (zzcr == null)
      {
        Intent localIntent = new Intent();
        localIntent.setPackage("com.google.example.invalidpackage");
        zzcr = PendingIntent.getBroadcast(paramContext, 0, localIntent, 0);
      }
      paramIntent.putExtra("app", zzcr);
      return;
    }
    catch (Throwable paramContext)
    {
      throw paramContext;
    }
  }
  
  private static String zzah()
  {
    try
    {
      int i = zzcf;
      zzcf = i + 1;
      String str = Integer.toString(i);
      return str;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  final Bundle execute(Bundle paramBundle)
    throws IOException
  {
    if (zzan.zzaf() >= 12000000)
    {
      Object localObject = zzab.get(context).readMessage(1, paramBundle);
      try
      {
        localObject = Tasks.await((Task)localObject);
        return (Bundle)localObject;
      }
      catch (InterruptedException|ExecutionException localInterruptedException)
      {
        if (Log.isLoggable("FirebaseInstanceId", 3))
        {
          String str = String.valueOf(localInterruptedException);
          StringBuilder localStringBuilder = new StringBuilder(String.valueOf(str).length() + 22);
          localStringBuilder.append("Error making request: ");
          localStringBuilder.append(str);
          Log.d("FirebaseInstanceId", localStringBuilder.toString());
        }
        if (((((Exception)localInterruptedException).getCause() instanceof zzal)) && (((zzal)((Exception)localInterruptedException).getCause()).getErrorCode() == 4)) {
          return get(paramBundle);
        }
        return null;
      }
    }
    return get(paramBundle);
  }
}
