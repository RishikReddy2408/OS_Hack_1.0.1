package com.google.android.android.common;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.util.Log;
import com.google.android.android.common.internal.Preconditions;
import com.google.android.android.common.wrappers.PackageManagerWrapper;
import com.google.android.android.common.wrappers.Wrappers;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.ShowFirstParty;
import javax.annotation.CheckReturnValue;

@CheckReturnValue
@KeepForSdk
@ShowFirstParty
public class GoogleSignatureVerifier
{
  private static GoogleSignatureVerifier zzal;
  private final Context mContext;
  private volatile String zzam;
  
  private GoogleSignatureVerifier(Context paramContext)
  {
    mContext = paramContext.getApplicationContext();
  }
  
  public static boolean backup(PackageInfo paramPackageInfo, boolean paramBoolean)
  {
    if ((paramPackageInfo != null) && (signatures != null))
    {
      if (paramBoolean) {
        paramPackageInfo = create(paramPackageInfo, DataProvider.TAG);
      } else {
        paramPackageInfo = create(paramPackageInfo, new Type[] { DataProvider.TAG[0] });
      }
      if (paramPackageInfo != null) {
        return true;
      }
    }
    return false;
  }
  
  private final Message create(String paramString)
  {
    if (paramString == null) {
      return Message.toString("null pkg");
    }
    if (paramString.equals(zzam)) {
      return Message.getInstance();
    }
    Object localObject = mContext;
    try
    {
      localObject = Wrappers.packageManager((Context)localObject).getPackageInfo(paramString, 64);
      localObject = toString((PackageInfo)localObject);
      if (!zzac) {
        return localObject;
      }
      zzam = paramString;
      return localObject;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      for (;;) {}
      return localNameNotFoundException;
    }
    paramString = String.valueOf(paramString);
    if (paramString.length() != 0) {
      paramString = "no pkg ".concat(paramString);
    } else {
      paramString = new String("no pkg ");
    }
    return Message.toString(paramString);
  }
  
  private final Message create(String paramString, int paramInt)
  {
    Object localObject = mContext;
    try
    {
      localObject = toString(Wrappers.packageManager((Context)localObject).getPackageInfo(paramString, 64, paramInt));
      return localObject;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      for (;;) {}
    }
    paramString = String.valueOf(paramString);
    if (paramString.length() != 0) {
      paramString = "no pkg ".concat(paramString);
    } else {
      paramString = new String("no pkg ");
    }
    return Message.toString(paramString);
  }
  
  private static Type create(PackageInfo paramPackageInfo, Type... paramVarArgs)
  {
    if (signatures == null) {
      return null;
    }
    if (signatures.length != 1)
    {
      Log.w("GoogleSignatureVerifier", "Package has more than one signature.");
      return null;
    }
    paramPackageInfo = signatures;
    int i = 0;
    paramPackageInfo = new Sha256Hash(paramPackageInfo[0].toByteArray());
    while (i < paramVarArgs.length)
    {
      if (paramVarArgs[i].equals(paramPackageInfo)) {
        return paramVarArgs[i];
      }
      i += 1;
    }
    return null;
  }
  
  public static GoogleSignatureVerifier getInstance(Context paramContext)
  {
    Preconditions.checkNotNull(paramContext);
    try
    {
      if (zzal == null)
      {
        Profile.init(paramContext);
        zzal = new GoogleSignatureVerifier(paramContext);
      }
      return zzal;
    }
    catch (Throwable paramContext)
    {
      throw paramContext;
    }
  }
  
  private final Message toString(PackageInfo paramPackageInfo)
  {
    boolean bool = GooglePlayServicesUtilLight.honorsDebugCertificates(mContext);
    if (paramPackageInfo == null) {
      return Message.toString("null pkg");
    }
    if (signatures.length != 1) {
      return Message.toString("single cert required");
    }
    Sha256Hash localSha256Hash = new Sha256Hash(signatures[0].toByteArray());
    String str = packageName;
    Message localMessage2 = Profile.read(str, localSha256Hash, bool);
    Message localMessage1 = localMessage2;
    if (zzac)
    {
      localMessage1 = localMessage2;
      if (applicationInfo != null)
      {
        localMessage1 = localMessage2;
        if ((applicationInfo.flags & 0x2) != 0) {
          if (bool)
          {
            localMessage1 = localMessage2;
            if (!readzzac) {}
          }
          else
          {
            localMessage1 = Message.toString("debuggable release cert app rejected");
          }
        }
      }
    }
    return localMessage1;
  }
  
  public boolean isGooglePublicSignedPackage(PackageInfo paramPackageInfo)
  {
    if (paramPackageInfo == null) {
      return false;
    }
    if (backup(paramPackageInfo, false)) {
      return true;
    }
    if (backup(paramPackageInfo, true))
    {
      if (GooglePlayServicesUtilLight.honorsDebugCertificates(mContext)) {
        return true;
      }
      Log.w("GoogleSignatureVerifier", "Test-keys aren't accepted on this build.");
    }
    return false;
  }
  
  public boolean isPackageGoogleSigned(String paramString)
  {
    paramString = create(paramString);
    paramString.delete();
    return zzac;
  }
  
  public boolean isUidGoogleSigned(int paramInt)
  {
    String[] arrayOfString = Wrappers.packageManager(mContext).getPackagesForUid(paramInt);
    if ((arrayOfString != null) && (arrayOfString.length != 0))
    {
      Object localObject1 = null;
      int j = arrayOfString.length;
      int i = 0;
      for (;;)
      {
        localObject2 = localObject1;
        if (i >= j) {
          break;
        }
        Message localMessage = create(arrayOfString[i], paramInt);
        localObject1 = localMessage;
        localObject2 = localObject1;
        if (zzac) {
          break;
        }
        i += 1;
      }
    }
    Object localObject2 = Message.toString("no pkgs");
    ((Message)localObject2).delete();
    return zzac;
  }
}
