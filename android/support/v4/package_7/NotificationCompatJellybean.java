package android.support.v4.package_7;

import android.app.Notification;
import android.app.Notification.Builder;
import android.app.PendingIntent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.util.SparseArray;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@RequiresApi(16)
class NotificationCompatJellybean
{
  static final String EXTRA_ALLOW_GENERATED_REPLIES = "android.support.allowGeneratedReplies";
  static final String EXTRA_DATA_ONLY_REMOTE_INPUTS = "android.support.dataRemoteInputs";
  private static final String KEY_ACTION_INTENT = "actionIntent";
  private static final String KEY_ALLOWED_DATA_TYPES = "allowedDataTypes";
  private static final String KEY_ALLOW_FREE_FORM_INPUT = "allowFreeFormInput";
  private static final String KEY_CHOICES = "choices";
  private static final String KEY_DATA_ONLY_REMOTE_INPUTS = "dataOnlyRemoteInputs";
  private static final String KEY_EXTRAS = "extras";
  private static final String KEY_ICON = "icon";
  private static final String KEY_LABEL = "label";
  private static final String KEY_REMOTE_INPUTS = "remoteInputs";
  private static final String KEY_RESULT_KEY = "resultKey";
  private static final String KEY_TITLE = "title";
  public static final String TAG = "NotificationCompat";
  private static Class<?> sActionClass;
  private static Field sActionIconField;
  private static Field sActionIntentField;
  private static Field sActionTitleField;
  private static boolean sActionsAccessFailed;
  private static Field sActionsField;
  private static final Object sActionsLock = new Object();
  private static Field sExtrasField;
  private static boolean sExtrasFieldAccessFailed;
  private static final Object sExtrasLock = new Object();
  
  NotificationCompatJellybean() {}
  
  public static SparseArray buildActionExtrasMap(List paramList)
  {
    int j = paramList.size();
    Object localObject1 = null;
    int i = 0;
    while (i < j)
    {
      Bundle localBundle = (Bundle)paramList.get(i);
      Object localObject2 = localObject1;
      if (localBundle != null)
      {
        localObject2 = localObject1;
        if (localObject1 == null) {
          localObject2 = new SparseArray();
        }
        ((SparseArray)localObject2).put(i, localBundle);
      }
      i += 1;
      localObject1 = localObject2;
    }
    return localObject1;
  }
  
  private static boolean ensureActionReflectionReadyLocked()
  {
    if (sActionsAccessFailed) {
      return false;
    }
    if (sActionsField == null) {
      try
      {
        Object localObject = Class.forName("android.app.Notification$Action");
        sActionClass = (Class)localObject;
        localObject = sActionClass;
        localObject = ((Class)localObject).getDeclaredField("icon");
        sActionIconField = (Field)localObject;
        localObject = sActionClass;
        localObject = ((Class)localObject).getDeclaredField("title");
        sActionTitleField = (Field)localObject;
        localObject = sActionClass;
        localObject = ((Class)localObject).getDeclaredField("actionIntent");
        sActionIntentField = (Field)localObject;
        localObject = Notification.class.getDeclaredField("actions");
        sActionsField = (Field)localObject;
        localObject = sActionsField;
        ((Field)localObject).setAccessible(true);
      }
      catch (NoSuchFieldException localNoSuchFieldException)
      {
        Log.e("NotificationCompat", "Unable to access notification actions", localNoSuchFieldException);
        sActionsAccessFailed = true;
      }
      catch (ClassNotFoundException localClassNotFoundException)
      {
        Log.e("NotificationCompat", "Unable to access notification actions", localClassNotFoundException);
        sActionsAccessFailed = true;
      }
    }
    return true ^ sActionsAccessFailed;
  }
  
  private static RemoteInput fromBundle(Bundle paramBundle)
  {
    Object localObject = paramBundle.getStringArrayList("allowedDataTypes");
    HashSet localHashSet = new HashSet();
    if (localObject != null)
    {
      localObject = ((ArrayList)localObject).iterator();
      while (((Iterator)localObject).hasNext()) {
        localHashSet.add((String)((Iterator)localObject).next());
      }
    }
    return new RemoteInput(paramBundle.getString("resultKey"), paramBundle.getCharSequence("label"), paramBundle.getCharSequenceArray("choices"), paramBundle.getBoolean("allowFreeFormInput"), paramBundle.getBundle("extras"), localHashSet);
  }
  
  private static RemoteInput[] fromBundleArray(Bundle[] paramArrayOfBundle)
  {
    if (paramArrayOfBundle == null) {
      return null;
    }
    RemoteInput[] arrayOfRemoteInput = new RemoteInput[paramArrayOfBundle.length];
    int i = 0;
    while (i < paramArrayOfBundle.length)
    {
      arrayOfRemoteInput[i] = fromBundle(paramArrayOfBundle[i]);
      i += 1;
    }
    return arrayOfRemoteInput;
  }
  
  public static NotificationCompat.Action getAction(Notification paramNotification, int paramInt)
  {
    Object localObject1 = sActionsLock;
    for (;;)
    {
      try
      {
        Object localObject2 = getActionObjectsLocked(paramNotification);
        if (localObject2 != null)
        {
          localObject2 = localObject2[paramInt];
          paramNotification = getExtras(paramNotification);
          if (paramNotification == null) {
            break label141;
          }
          paramNotification = paramNotification.getSparseParcelableArray("android.support.actionExtras");
          if (paramNotification == null) {
            break label141;
          }
          paramNotification = paramNotification.get(paramInt);
          paramNotification = (Bundle)paramNotification;
          Object localObject3 = sActionIconField;
          paramInt = ((Field)localObject3).getInt(localObject2);
          localObject3 = sActionTitleField;
          localObject3 = ((Field)localObject3).get(localObject2);
          localObject3 = (CharSequence)localObject3;
          Field localField = sActionIntentField;
          localObject2 = localField.get(localObject2);
          localObject2 = (PendingIntent)localObject2;
          paramNotification = readAction(paramInt, (CharSequence)localObject3, (PendingIntent)localObject2, paramNotification);
          return paramNotification;
        }
      }
      catch (Throwable paramNotification) {}catch (IllegalAccessException paramNotification)
      {
        Log.e("NotificationCompat", "Unable to access notification actions", paramNotification);
        sActionsAccessFailed = true;
        return null;
      }
      throw paramNotification;
      label141:
      paramNotification = null;
    }
  }
  
  public static int getActionCount(Notification paramNotification)
  {
    Object localObject = sActionsLock;
    for (;;)
    {
      try
      {
        paramNotification = getActionObjectsLocked(paramNotification);
        if (paramNotification != null)
        {
          i = paramNotification.length;
          return i;
        }
      }
      catch (Throwable paramNotification)
      {
        throw paramNotification;
      }
      int i = 0;
    }
  }
  
  static NotificationCompat.Action getActionFromBundle(Bundle paramBundle)
  {
    Bundle localBundle = paramBundle.getBundle("extras");
    boolean bool;
    if (localBundle != null) {
      bool = localBundle.getBoolean("android.support.allowGeneratedReplies", false);
    } else {
      bool = false;
    }
    return new NotificationCompat.Action(paramBundle.getInt("icon"), paramBundle.getCharSequence("title"), (PendingIntent)paramBundle.getParcelable("actionIntent"), paramBundle.getBundle("extras"), fromBundleArray(getBundleArrayFromBundle(paramBundle, "remoteInputs")), fromBundleArray(getBundleArrayFromBundle(paramBundle, "dataOnlyRemoteInputs")), bool);
  }
  
  /* Error */
  private static Object[] getActionObjectsLocked(Notification paramNotification)
  {
    // Byte code:
    //   0: getstatic 74	android/support/v4/package_7/NotificationCompatJellybean:sActionsLock	Ljava/lang/Object;
    //   3: astore_1
    //   4: aload_1
    //   5: monitorenter
    //   6: invokestatic 268	android/support/v4/package_7/NotificationCompatJellybean:ensureActionReflectionReadyLocked	()Z
    //   9: ifne +7 -> 16
    //   12: aload_1
    //   13: monitorexit
    //   14: aconst_null
    //   15: areturn
    //   16: getstatic 106	android/support/v4/package_7/NotificationCompatJellybean:sActionsField	Ljava/lang/reflect/Field;
    //   19: astore_2
    //   20: aload_2
    //   21: aload_0
    //   22: invokevirtual 233	java/lang/reflect/Field:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   25: astore_0
    //   26: aload_0
    //   27: checkcast 270	[Ljava/lang/Object;
    //   30: checkcast 270	[Ljava/lang/Object;
    //   33: astore_0
    //   34: aload_1
    //   35: monitorexit
    //   36: aload_0
    //   37: areturn
    //   38: astore_0
    //   39: ldc 50
    //   41: ldc -118
    //   43: aload_0
    //   44: invokestatic 144	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   47: pop
    //   48: iconst_1
    //   49: putstatic 104	android/support/v4/package_7/NotificationCompatJellybean:sActionsAccessFailed	Z
    //   52: aload_1
    //   53: monitorexit
    //   54: aconst_null
    //   55: areturn
    //   56: astore_0
    //   57: aload_1
    //   58: monitorexit
    //   59: aload_0
    //   60: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	61	0	paramNotification	Notification
    //   3	55	1	localObject	Object
    //   19	2	2	localField	Field
    // Exception table:
    //   from	to	target	type
    //   20	26	38	java/lang/IllegalAccessException
    //   6	14	56	java/lang/Throwable
    //   16	20	56	java/lang/Throwable
    //   20	26	56	java/lang/Throwable
    //   26	36	56	java/lang/Throwable
    //   39	54	56	java/lang/Throwable
    //   57	59	56	java/lang/Throwable
  }
  
  private static Bundle[] getBundleArrayFromBundle(Bundle paramBundle, String paramString)
  {
    Object localObject = paramBundle.getParcelableArray(paramString);
    if ((!(localObject instanceof Bundle[])) && (localObject != null))
    {
      localObject = (Bundle[])Arrays.copyOf((Object[])localObject, localObject.length, [Landroid.os.Bundle.class);
      paramBundle.putParcelableArray(paramString, (Parcelable[])localObject);
      return localObject;
    }
    return (Bundle[])localObject;
  }
  
  static Bundle getBundleForAction(NotificationCompat.Action paramAction)
  {
    Bundle localBundle2 = new Bundle();
    localBundle2.putInt("icon", paramAction.getIcon());
    localBundle2.putCharSequence("title", paramAction.getTitle());
    localBundle2.putParcelable("actionIntent", paramAction.getActionIntent());
    Bundle localBundle1;
    if (paramAction.getExtras() != null) {
      localBundle1 = new Bundle(paramAction.getExtras());
    } else {
      localBundle1 = new Bundle();
    }
    localBundle1.putBoolean("android.support.allowGeneratedReplies", paramAction.getAllowGeneratedReplies());
    localBundle2.putBundle("extras", localBundle1);
    localBundle2.putParcelableArray("remoteInputs", toBundleArray(paramAction.getRemoteInputs()));
    return localBundle2;
  }
  
  public static Bundle getExtras(Notification paramNotification)
  {
    Object localObject3 = sExtrasLock;
    try
    {
      if (sExtrasFieldAccessFailed) {
        return null;
      }
      if (sExtrasField == null) {}
      try
      {
        Object localObject1 = Notification.class.getDeclaredField("extras");
        boolean bool = Bundle.class.isAssignableFrom(((Field)localObject1).getType());
        if (!bool)
        {
          Log.e("NotificationCompat", "Notification.extras field is not of type Bundle");
          sExtrasFieldAccessFailed = true;
          return null;
        }
        ((Field)localObject1).setAccessible(true);
        sExtrasField = (Field)localObject1;
        localObject1 = sExtrasField;
        localObject1 = ((Field)localObject1).get(paramNotification);
        Object localObject2 = (Bundle)localObject1;
        localObject1 = localObject2;
        if (localObject2 == null)
        {
          localObject1 = new Bundle();
          localObject2 = sExtrasField;
          ((Field)localObject2).set(paramNotification, localObject1);
        }
        return localObject1;
      }
      catch (NoSuchFieldException paramNotification)
      {
        Log.e("NotificationCompat", "Unable to access notification extras", paramNotification);
      }
      catch (IllegalAccessException paramNotification)
      {
        Log.e("NotificationCompat", "Unable to access notification extras", paramNotification);
      }
      sExtrasFieldAccessFailed = true;
      return null;
    }
    catch (Throwable paramNotification)
    {
      throw paramNotification;
    }
  }
  
  public static NotificationCompat.Action readAction(int paramInt, CharSequence paramCharSequence, PendingIntent paramPendingIntent, Bundle paramBundle)
  {
    RemoteInput[] arrayOfRemoteInput1;
    RemoteInput[] arrayOfRemoteInput2;
    boolean bool;
    if (paramBundle != null)
    {
      arrayOfRemoteInput1 = fromBundleArray(getBundleArrayFromBundle(paramBundle, "android.support.remoteInputs"));
      arrayOfRemoteInput2 = fromBundleArray(getBundleArrayFromBundle(paramBundle, "android.support.dataRemoteInputs"));
      bool = paramBundle.getBoolean("android.support.allowGeneratedReplies");
    }
    else
    {
      arrayOfRemoteInput1 = null;
      arrayOfRemoteInput2 = null;
      bool = false;
    }
    return new NotificationCompat.Action(paramInt, paramCharSequence, paramPendingIntent, paramBundle, arrayOfRemoteInput1, arrayOfRemoteInput2, bool);
  }
  
  private static Bundle toBundle(RemoteInput paramRemoteInput)
  {
    Bundle localBundle = new Bundle();
    localBundle.putString("resultKey", paramRemoteInput.getResultKey());
    localBundle.putCharSequence("label", paramRemoteInput.getLabel());
    localBundle.putCharSequenceArray("choices", paramRemoteInput.getChoices());
    localBundle.putBoolean("allowFreeFormInput", paramRemoteInput.getAllowFreeFormInput());
    localBundle.putBundle("extras", paramRemoteInput.getExtras());
    Object localObject = paramRemoteInput.getAllowedDataTypes();
    if ((localObject != null) && (!((Set)localObject).isEmpty()))
    {
      paramRemoteInput = new ArrayList(((Set)localObject).size());
      localObject = ((Set)localObject).iterator();
      while (((Iterator)localObject).hasNext()) {
        paramRemoteInput.add((String)((Iterator)localObject).next());
      }
      localBundle.putStringArrayList("allowedDataTypes", paramRemoteInput);
    }
    return localBundle;
  }
  
  private static Bundle[] toBundleArray(RemoteInput[] paramArrayOfRemoteInput)
  {
    if (paramArrayOfRemoteInput == null) {
      return null;
    }
    Bundle[] arrayOfBundle = new Bundle[paramArrayOfRemoteInput.length];
    int i = 0;
    while (i < paramArrayOfRemoteInput.length)
    {
      arrayOfBundle[i] = toBundle(paramArrayOfRemoteInput[i]);
      i += 1;
    }
    return arrayOfBundle;
  }
  
  public static Bundle writeActionAndGetExtras(Notification.Builder paramBuilder, NotificationCompat.Action paramAction)
  {
    paramBuilder.addAction(paramAction.getIcon(), paramAction.getTitle(), paramAction.getActionIntent());
    paramBuilder = new Bundle(paramAction.getExtras());
    if (paramAction.getRemoteInputs() != null) {
      paramBuilder.putParcelableArray("android.support.remoteInputs", toBundleArray(paramAction.getRemoteInputs()));
    }
    if (paramAction.getDataOnlyRemoteInputs() != null) {
      paramBuilder.putParcelableArray("android.support.dataRemoteInputs", toBundleArray(paramAction.getDataOnlyRemoteInputs()));
    }
    paramBuilder.putBoolean("android.support.allowGeneratedReplies", paramAction.getAllowGeneratedReplies());
    return paramBuilder;
  }
}
