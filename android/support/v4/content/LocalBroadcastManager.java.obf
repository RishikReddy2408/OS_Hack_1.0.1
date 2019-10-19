package android.support.v4.content;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.util.Log;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public final class LocalBroadcastManager
{
  private static final boolean DEBUG = false;
  static final int MSG_EXEC_PENDING_BROADCASTS = 1;
  private static final String TAG = "LocalBroadcastManager";
  private static LocalBroadcastManager mInstance;
  private static final Object mLock = new Object();
  private final HashMap<String, ArrayList<ReceiverRecord>> mActions = new HashMap();
  private final Context mAppContext;
  private final Handler mHandler;
  private final ArrayList<BroadcastRecord> mPendingBroadcasts = new ArrayList();
  private final HashMap<BroadcastReceiver, ArrayList<ReceiverRecord>> mReceivers = new HashMap();
  
  private LocalBroadcastManager(Context paramContext)
  {
    mAppContext = paramContext;
    mHandler = new Handler(paramContext.getMainLooper())
    {
      public void handleMessage(Message paramAnonymousMessage)
      {
        if (what != 1)
        {
          super.handleMessage(paramAnonymousMessage);
          return;
        }
        LocalBroadcastManager.this.executePendingBroadcasts();
      }
    };
  }
  
  private void executePendingBroadcasts()
  {
    synchronized (mReceivers)
    {
      int i;
      BroadcastRecord[] arrayOfBroadcastRecord;
      do
      {
        i = mPendingBroadcasts.size();
        if (i <= 0) {
          return;
        }
        arrayOfBroadcastRecord = new BroadcastRecord[i];
        mPendingBroadcasts.toArray(arrayOfBroadcastRecord);
        mPendingBroadcasts.clear();
        i = 0;
      } while (i >= arrayOfBroadcastRecord.length);
      ??? = arrayOfBroadcastRecord[i];
      int k = receivers.size();
      int j = 0;
      while (j < k)
      {
        ReceiverRecord localReceiverRecord = (ReceiverRecord)receivers.get(j);
        if (!dead) {
          receiver.onReceive(mAppContext, intent);
        }
        j += 1;
      }
      i += 1;
    }
  }
  
  @NonNull
  public static LocalBroadcastManager getInstance(@NonNull Context paramContext)
  {
    synchronized (mLock)
    {
      if (mInstance == null) {
        mInstance = new LocalBroadcastManager(paramContext.getApplicationContext());
      }
      paramContext = mInstance;
      return paramContext;
    }
  }
  
  public void registerReceiver(@NonNull BroadcastReceiver paramBroadcastReceiver, @NonNull IntentFilter paramIntentFilter)
  {
    synchronized (mReceivers)
    {
      ReceiverRecord localReceiverRecord = new ReceiverRecord(paramIntentFilter, paramBroadcastReceiver);
      Object localObject2 = (ArrayList)mReceivers.get(paramBroadcastReceiver);
      Object localObject1 = localObject2;
      if (localObject2 == null)
      {
        localObject1 = new ArrayList(1);
        mReceivers.put(paramBroadcastReceiver, localObject1);
      }
      ((ArrayList)localObject1).add(localReceiverRecord);
      int i = 0;
      while (i < paramIntentFilter.countActions())
      {
        localObject2 = paramIntentFilter.getAction(i);
        localObject1 = (ArrayList)mActions.get(localObject2);
        paramBroadcastReceiver = (BroadcastReceiver)localObject1;
        if (localObject1 == null)
        {
          paramBroadcastReceiver = new ArrayList(1);
          mActions.put(localObject2, paramBroadcastReceiver);
        }
        paramBroadcastReceiver.add(localReceiverRecord);
        i += 1;
      }
      return;
    }
  }
  
  public boolean sendBroadcast(@NonNull Intent paramIntent)
  {
    for (;;)
    {
      Object localObject1;
      int k;
      synchronized (mReceivers)
      {
        String str1 = paramIntent.getAction();
        String str2 = paramIntent.resolveTypeIfNeeded(mAppContext.getContentResolver());
        Uri localUri = paramIntent.getData();
        String str3 = paramIntent.getScheme();
        Set localSet = paramIntent.getCategories();
        if ((paramIntent.getFlags() & 0x8) != 0)
        {
          i = 1;
          if (i != 0)
          {
            localObject1 = new StringBuilder();
            ((StringBuilder)localObject1).append("Resolving type ");
            ((StringBuilder)localObject1).append(str2);
            ((StringBuilder)localObject1).append(" scheme ");
            ((StringBuilder)localObject1).append(str3);
            ((StringBuilder)localObject1).append(" of intent ");
            ((StringBuilder)localObject1).append(paramIntent);
            Log.v("LocalBroadcastManager", ((StringBuilder)localObject1).toString());
          }
          ArrayList localArrayList = (ArrayList)mActions.get(paramIntent.getAction());
          if (localArrayList != null)
          {
            if (i == 0) {
              break label521;
            }
            localObject1 = new StringBuilder();
            ((StringBuilder)localObject1).append("Action list: ");
            ((StringBuilder)localObject1).append(localArrayList);
            Log.v("LocalBroadcastManager", ((StringBuilder)localObject1).toString());
            break label521;
            if (j >= localArrayList.size()) {
              break label618;
            }
            Object localObject3 = (ReceiverRecord)localArrayList.get(j);
            if (i != 0)
            {
              localObject1 = new StringBuilder();
              ((StringBuilder)localObject1).append("Matching against filter ");
              ((StringBuilder)localObject1).append(filter);
              Log.v("LocalBroadcastManager", ((StringBuilder)localObject1).toString());
            }
            if (broadcasting)
            {
              if (i == 0) {
                break label529;
              }
              Log.v("LocalBroadcastManager", "  Filter's target already added");
              break label529;
            }
            IntentFilter localIntentFilter = filter;
            localObject1 = localObject2;
            k = localIntentFilter.match(str1, str2, str3, localUri, localSet, "LocalBroadcastManager");
            if (k < 0) {
              break label539;
            }
            if (i != 0)
            {
              localObject2 = new StringBuilder();
              ((StringBuilder)localObject2).append("  Filter matched!  match=0x");
              ((StringBuilder)localObject2).append(Integer.toHexString(k));
              Log.v("LocalBroadcastManager", ((StringBuilder)localObject2).toString());
            }
            if (localObject1 != null) {
              break label532;
            }
            localObject2 = new ArrayList();
            ((ArrayList)localObject2).add(localObject3);
            broadcasting = true;
            break label611;
            localObject3 = new StringBuilder();
            ((StringBuilder)localObject3).append("  Filter did not match: ");
            ((StringBuilder)localObject3).append((String)localObject1);
            Log.v("LocalBroadcastManager", ((StringBuilder)localObject3).toString());
            break label611;
            if (i < ((ArrayList)localObject2).size())
            {
              getbroadcasting = false;
              i += 1;
              continue;
            }
            mPendingBroadcasts.add(new BroadcastRecord(paramIntent, (ArrayList)localObject2));
            if (!mHandler.hasMessages(1)) {
              mHandler.sendEmptyMessage(1);
            }
            return true;
          }
          return false;
        }
      }
      int i = 0;
      continue;
      label521:
      Object localObject2 = null;
      int j = 0;
      continue;
      label529:
      break label611;
      label532:
      localObject2 = localObject1;
      continue;
      label539:
      if (i != 0)
      {
        switch (k)
        {
        default: 
          localObject1 = "unknown reason";
          break;
        case -1: 
          localObject1 = "type";
          break;
        case -2: 
          localObject1 = "data";
          break;
        case -3: 
          localObject1 = "action";
          break;
        case -4: 
          localObject1 = "category";
          break;
        }
      }
      else
      {
        label611:
        j += 1;
        continue;
        label618:
        if (localObject2 != null) {
          i = 0;
        }
      }
    }
  }
  
  public void sendBroadcastSync(@NonNull Intent paramIntent)
  {
    if (sendBroadcast(paramIntent)) {
      executePendingBroadcasts();
    }
  }
  
  public void unregisterReceiver(@NonNull BroadcastReceiver paramBroadcastReceiver)
  {
    for (;;)
    {
      int i;
      int j;
      int k;
      synchronized (mReceivers)
      {
        ArrayList localArrayList1 = (ArrayList)mReceivers.remove(paramBroadcastReceiver);
        if (localArrayList1 == null) {
          return;
        }
        i = localArrayList1.size() - 1;
        if (i >= 0)
        {
          ReceiverRecord localReceiverRecord1 = (ReceiverRecord)localArrayList1.get(i);
          dead = true;
          j = 0;
          if (j >= filter.countActions()) {
            break label203;
          }
          String str = filter.getAction(j);
          ArrayList localArrayList2 = (ArrayList)mActions.get(str);
          if (localArrayList2 == null) {
            break label196;
          }
          k = localArrayList2.size() - 1;
          if (k >= 0)
          {
            ReceiverRecord localReceiverRecord2 = (ReceiverRecord)localArrayList2.get(k);
            if (receiver == paramBroadcastReceiver)
            {
              dead = true;
              localArrayList2.remove(k);
            }
          }
          else
          {
            if (localArrayList2.size() > 0) {
              break label196;
            }
            mActions.remove(str);
            break label196;
          }
        }
        else
        {
          return;
        }
      }
      k -= 1;
      continue;
      label196:
      j += 1;
      continue;
      label203:
      i -= 1;
    }
  }
  
  private static final class BroadcastRecord
  {
    final Intent intent;
    final ArrayList<LocalBroadcastManager.ReceiverRecord> receivers;
    
    BroadcastRecord(Intent paramIntent, ArrayList<LocalBroadcastManager.ReceiverRecord> paramArrayList)
    {
      intent = paramIntent;
      receivers = paramArrayList;
    }
  }
  
  private static final class ReceiverRecord
  {
    boolean broadcasting;
    boolean dead;
    final IntentFilter filter;
    final BroadcastReceiver receiver;
    
    ReceiverRecord(IntentFilter paramIntentFilter, BroadcastReceiver paramBroadcastReceiver)
    {
      filter = paramIntentFilter;
      receiver = paramBroadcastReceiver;
    }
    
    public String toString()
    {
      StringBuilder localStringBuilder = new StringBuilder(128);
      localStringBuilder.append("Receiver{");
      localStringBuilder.append(receiver);
      localStringBuilder.append(" filter=");
      localStringBuilder.append(filter);
      if (dead) {
        localStringBuilder.append(" DEAD");
      }
      localStringBuilder.append("}");
      return localStringBuilder.toString();
    }
  }
}
