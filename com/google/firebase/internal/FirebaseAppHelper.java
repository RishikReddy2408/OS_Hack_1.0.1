package com.google.firebase.internal;

import com.google.android.android.tasks.Task;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.firebase.FirebaseApiNotAvailableException;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseApp.IdTokenListener;

@Deprecated
@KeepForSdk
public class FirebaseAppHelper
{
  public FirebaseAppHelper() {}
  
  public static void addIdTokenListener(FirebaseApp paramFirebaseApp, FirebaseApp.IdTokenListener paramIdTokenListener)
  {
    paramFirebaseApp.addIdTokenListener(paramIdTokenListener);
  }
  
  public static Task getToken(FirebaseApp paramFirebaseApp, boolean paramBoolean)
  {
    return paramFirebaseApp.getToken(paramBoolean);
  }
  
  public static String getUid(FirebaseApp paramFirebaseApp)
    throws FirebaseApiNotAvailableException
  {
    return paramFirebaseApp.getUid();
  }
  
  public static void removeIdTokenListener(FirebaseApp paramFirebaseApp, FirebaseApp.IdTokenListener paramIdTokenListener)
  {
    paramFirebaseApp.removeIdTokenListener(paramIdTokenListener);
  }
}
