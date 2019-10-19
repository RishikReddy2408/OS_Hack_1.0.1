package com.google.firebase.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.ProviderInfo;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import com.google.android.android.common.internal.Preconditions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.annotations.PublicApi;

@PublicApi
public class FirebaseInitProvider
  extends ContentProvider
{
  public FirebaseInitProvider() {}
  
  public void attachInfo(Context paramContext, ProviderInfo paramProviderInfo)
  {
    Preconditions.checkNotNull(paramProviderInfo, "FirebaseInitProvider ProviderInfo cannot be null.");
    if (!"com.google.firebase.firebaseinitprovider".equals(authority))
    {
      super.attachInfo(paramContext, paramProviderInfo);
      return;
    }
    throw new IllegalStateException("Incorrect provider authority in manifest. Most likely due to a missing applicationId variable in application's build.gradle.");
  }
  
  public int delete(Uri paramUri, String paramString, String[] paramArrayOfString)
  {
    return 0;
  }
  
  public String getType(Uri paramUri)
  {
    return null;
  }
  
  public Uri insert(Uri paramUri, ContentValues paramContentValues)
  {
    return null;
  }
  
  public boolean onCreate()
  {
    if (FirebaseApp.initializeApp(getContext()) == null) {
      Log.i("FirebaseInitProvider", "FirebaseApp initialization unsuccessful");
    } else {
      Log.i("FirebaseInitProvider", "FirebaseApp initialization successful");
    }
    return false;
  }
  
  public Cursor query(Uri paramUri, String[] paramArrayOfString1, String paramString1, String[] paramArrayOfString2, String paramString2)
  {
    return null;
  }
  
  public int update(Uri paramUri, ContentValues paramContentValues, String paramString, String[] paramArrayOfString)
  {
    return 0;
  }
}
