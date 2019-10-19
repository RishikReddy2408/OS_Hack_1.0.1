package com.google.firebase.package_8;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.util.Log;
import com.google.firebase.FirebaseApp;
import java.io.IOException;

final class zzay
  implements Runnable
{
  private final zzan zzan;
  private final zzba zzaq;
  private final long zzdh;
  private final PowerManager.WakeLock zzdi;
  private final FirebaseInstanceId zzdj;
  
  zzay(FirebaseInstanceId paramFirebaseInstanceId, zzan paramZzan, zzba paramZzba, long paramLong)
  {
    zzdj = paramFirebaseInstanceId;
    zzan = paramZzan;
    zzaq = paramZzba;
    zzdh = paramLong;
    zzdi = ((PowerManager)getContext().getSystemService("power")).newWakeLock(1, "fiid-sync");
    zzdi.setReferenceCounted(false);
  }
  
  private final boolean zzam()
  {
    FirebaseInstanceId localFirebaseInstanceId = zzdj;
    try
    {
      boolean bool = localFirebaseInstanceId.rm();
      if (!bool)
      {
        localFirebaseInstanceId = zzdj;
        localFirebaseInstanceId.requestData();
      }
      return true;
    }
    catch (IOException localIOException)
    {
      String str = String.valueOf(localIOException.getMessage());
      if (str.length() != 0) {
        str = "Build channel failed: ".concat(str);
      } else {
        str = new String("Build channel failed: ");
      }
      Log.e("FirebaseInstanceId", str);
    }
    return false;
  }
  
  private final boolean zzan()
  {
    throw new Runtime("d2j fail translate: java.lang.RuntimeException: fail exe a13 = a12\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.exec(BaseAnalyze.java:92)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.exec(BaseAnalyze.java:1)\n\tat com.googlecode.dex2jar.ir.ts.Cfg.dfs(Cfg.java:255)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.analyze0(BaseAnalyze.java:75)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.analyze(BaseAnalyze.java:69)\n\tat com.googlecode.dex2jar.ir.ts.UnSSATransformer.transform(UnSSATransformer.java:274)\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:163)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\nCaused by: java.lang.NullPointerException\n");
  }
  
  final Context getContext()
  {
    return zzdj.getMainActivity().getApplicationContext();
  }
  
  public final void run()
  {
    zzdi.acquire();
    try
    {
      zzdj.blur(true);
      boolean bool = zzdj.isNetworkAvailable();
      if (!bool)
      {
        zzdj.blur(false);
        zzdi.release();
        return;
      }
      bool = zzao();
      if (!bool)
      {
        new zzaz(this).zzap();
        zzdi.release();
        return;
      }
      bool = zzam();
      if (bool)
      {
        bool = zzan();
        if (bool)
        {
          bool = zzaq.doRun(zzdj);
          if (bool)
          {
            zzdj.blur(false);
            break label127;
          }
        }
      }
      zzdj.blur(zzdh);
      label127:
      zzdi.release();
      return;
    }
    catch (Throwable localThrowable)
    {
      zzdi.release();
      throw localThrowable;
    }
  }
  
  final boolean zzao()
  {
    Object localObject = (ConnectivityManager)getContext().getSystemService("connectivity");
    if (localObject != null) {
      localObject = ((ConnectivityManager)localObject).getActiveNetworkInfo();
    } else {
      localObject = null;
    }
    return (localObject != null) && (((NetworkInfo)localObject).isConnected());
  }
}
