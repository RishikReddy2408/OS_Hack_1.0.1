package com.google.android.android.measurement.internal;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import java.util.Calendar;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public final class class_6
  extends zzcp
{
  private long zzahz;
  private String zzaia;
  private Boolean zzaib;
  
  class_6(zzbt paramZzbt)
  {
    super(paramZzbt);
  }
  
  public final boolean getIcon(Context paramContext)
  {
    if (zzaib == null)
    {
      zzgr();
      zzaib = Boolean.valueOf(false);
    }
    try
    {
      paramContext = paramContext.getPackageManager();
      if (paramContext != null)
      {
        paramContext.getPackageInfo("com.google.android.gms", 128);
        zzaib = Boolean.valueOf(true);
      }
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      for (;;) {}
    }
    return zzaib.booleanValue();
  }
  
  protected final boolean zzgt()
  {
    Object localObject1 = Calendar.getInstance();
    zzahz = TimeUnit.MINUTES.convert(((Calendar)localObject1).get(15) + ((Calendar)localObject1).get(16), TimeUnit.MILLISECONDS);
    Object localObject2 = Locale.getDefault();
    localObject1 = ((Locale)localObject2).getLanguage().toLowerCase(Locale.ENGLISH);
    localObject2 = ((Locale)localObject2).getCountry().toLowerCase(Locale.ENGLISH);
    StringBuilder localStringBuilder = new StringBuilder(String.valueOf(localObject1).length() + 1 + String.valueOf(localObject2).length());
    localStringBuilder.append((String)localObject1);
    localStringBuilder.append("-");
    localStringBuilder.append((String)localObject2);
    zzaia = localStringBuilder.toString();
    return false;
  }
  
  public final long zzis()
  {
    zzcl();
    return zzahz;
  }
  
  public final String zzit()
  {
    zzcl();
    return zzaia;
  }
}
