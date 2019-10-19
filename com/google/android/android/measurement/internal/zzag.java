package com.google.android.android.measurement.internal;

import android.os.IInterface;
import android.os.RemoteException;
import java.util.List;

public abstract interface zzag
  extends IInterface
{
  public abstract void chmod(ApplicationInfo paramApplicationInfo)
    throws RemoteException;
  
  public abstract void createShortcut(ApplicationInfo paramApplicationInfo)
    throws RemoteException;
  
  public abstract byte[] get(zzad paramZzad, String paramString)
    throws RemoteException;
  
  public abstract String getAbsoluteUrl(ApplicationInfo paramApplicationInfo)
    throws RemoteException;
  
  public abstract List getFromLocationName(ApplicationInfo paramApplicationInfo, boolean paramBoolean)
    throws RemoteException;
  
  public abstract List getFromLocationName(String paramString1, String paramString2, ApplicationInfo paramApplicationInfo)
    throws RemoteException;
  
  public abstract List getFromLocationName(String paramString1, String paramString2, String paramString3)
    throws RemoteException;
  
  public abstract List getFromLocationName(String paramString1, String paramString2, String paramString3, boolean paramBoolean)
    throws RemoteException;
  
  public abstract List getFromLocationName(String paramString1, String paramString2, boolean paramBoolean, ApplicationInfo paramApplicationInfo)
    throws RemoteException;
  
  public abstract void getPackageInfo(ComponentInfo paramComponentInfo, ApplicationInfo paramApplicationInfo)
    throws RemoteException;
  
  public abstract void handleResult(ApplicationInfo paramApplicationInfo)
    throws RemoteException;
  
  public abstract void handleResult(zzad paramZzad, ApplicationInfo paramApplicationInfo)
    throws RemoteException;
  
  public abstract void handleResult(zzad paramZzad, String paramString1, String paramString2)
    throws RemoteException;
  
  public abstract void setPresence(long paramLong, String paramString1, String paramString2, String paramString3)
    throws RemoteException;
  
  public abstract void toggleState(ComponentInfo paramComponentInfo)
    throws RemoteException;
  
  public abstract void write(zzfh paramZzfh, ApplicationInfo paramApplicationInfo)
    throws RemoteException;
}
