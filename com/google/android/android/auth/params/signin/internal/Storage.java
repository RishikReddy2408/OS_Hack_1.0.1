package com.google.android.android.auth.params.signin.internal;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import com.google.android.android.auth.params.signin.GoogleSignInAccount;
import com.google.android.android.auth.params.signin.GoogleSignInOptions;
import com.google.android.android.common.internal.Preconditions;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import javax.annotation.concurrent.GuardedBy;
import org.json.JSONException;

public class Storage
{
  private static final Lock zaaj = new ReentrantLock();
  @GuardedBy("sLk")
  private static Storage zaak;
  private final Lock zaal = new ReentrantLock();
  @GuardedBy("mLk")
  private final SharedPreferences zaam;
  
  private Storage(Context paramContext)
  {
    zaam = paramContext.getSharedPreferences("com.google.android.gms.signin", 0);
  }
  
  private final void delete(String paramString)
  {
    zaal.lock();
    try
    {
      zaam.edit().remove(paramString).apply();
      zaal.unlock();
      return;
    }
    catch (Throwable paramString)
    {
      zaal.unlock();
      throw paramString;
    }
  }
  
  public static Storage getInstance(Context paramContext)
  {
    Preconditions.checkNotNull(paramContext);
    zaaj.lock();
    try
    {
      Storage localStorage = zaak;
      if (localStorage == null) {
        zaak = new Storage(paramContext.getApplicationContext());
      }
      paramContext = zaak;
      zaaj.unlock();
      return paramContext;
    }
    catch (Throwable paramContext)
    {
      zaaj.unlock();
      throw paramContext;
    }
  }
  
  private static String open(String paramString1, String paramString2)
  {
    StringBuilder localStringBuilder = new StringBuilder(String.valueOf(paramString1).length() + 1 + String.valueOf(paramString2).length());
    localStringBuilder.append(paramString1);
    localStringBuilder.append(":");
    localStringBuilder.append(paramString2);
    return localStringBuilder.toString();
  }
  
  private final void put(String paramString1, String paramString2)
  {
    zaal.lock();
    try
    {
      zaam.edit().putString(paramString1, paramString2).apply();
      zaal.unlock();
      return;
    }
    catch (Throwable paramString1)
    {
      zaal.unlock();
      throw paramString1;
    }
  }
  
  private final GoogleSignInAccount search(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return null;
    }
    paramString = toString(open("googleSignInAccount", paramString));
    if (paramString != null) {}
    try
    {
      paramString = GoogleSignInAccount.search(paramString);
      return paramString;
    }
    catch (JSONException paramString) {}
    return null;
    return null;
  }
  
  private final String toString(String paramString)
  {
    zaal.lock();
    try
    {
      paramString = zaam.getString(paramString, null);
      zaal.unlock();
      return paramString;
    }
    catch (Throwable paramString)
    {
      zaal.unlock();
      throw paramString;
    }
  }
  
  private final GoogleSignInOptions update(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return null;
    }
    paramString = toString(open("googleSignInOptions", paramString));
    if (paramString != null) {}
    try
    {
      paramString = GoogleSignInOptions.update(paramString);
      return paramString;
    }
    catch (JSONException paramString) {}
    return null;
    return null;
  }
  
  public void clear()
  {
    zaal.lock();
    try
    {
      zaam.edit().clear().apply();
      zaal.unlock();
      return;
    }
    catch (Throwable localThrowable)
    {
      zaal.unlock();
      throw localThrowable;
    }
  }
  
  public final void delete()
  {
    String str = toString("defaultGoogleSignInAccount");
    delete("defaultGoogleSignInAccount");
    if (!TextUtils.isEmpty(str))
    {
      delete(open("googleSignInAccount", str));
      delete(open("googleSignInOptions", str));
    }
  }
  
  public GoogleSignInAccount getSavedDefaultGoogleSignInAccount()
  {
    return search(toString("defaultGoogleSignInAccount"));
  }
  
  public GoogleSignInOptions getSavedDefaultGoogleSignInOptions()
  {
    return update(toString("defaultGoogleSignInAccount"));
  }
  
  public String getSavedRefreshToken()
  {
    return toString("refreshToken");
  }
  
  public void saveDefaultGoogleSignInAccount(GoogleSignInAccount paramGoogleSignInAccount, GoogleSignInOptions paramGoogleSignInOptions)
  {
    Preconditions.checkNotNull(paramGoogleSignInAccount);
    Preconditions.checkNotNull(paramGoogleSignInOptions);
    put("defaultGoogleSignInAccount", paramGoogleSignInAccount.json());
    Preconditions.checkNotNull(paramGoogleSignInAccount);
    Preconditions.checkNotNull(paramGoogleSignInOptions);
    String str = paramGoogleSignInAccount.json();
    put(open("googleSignInAccount", str), paramGoogleSignInAccount.toJsonString());
    put(open("googleSignInOptions", str), paramGoogleSignInOptions.toJsonString());
  }
}
