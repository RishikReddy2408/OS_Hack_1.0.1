package com.google.android.android.common.internal;

import com.google.android.gms.common.annotation.KeepForSdk;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

@KeepForSdk
public class LibraryVersion
{
  private static final GmsLogger zzel = new GmsLogger("LibraryVersion", "");
  private static LibraryVersion zzem = new LibraryVersion();
  private ConcurrentHashMap<String, String> zzen = new ConcurrentHashMap();
  
  protected LibraryVersion() {}
  
  public static LibraryVersion getInstance()
  {
    return zzem;
  }
  
  public String getVersion(String paramString)
  {
    Preconditions.checkNotEmpty(paramString, "Please provide a valid libraryName");
    if (zzen.containsKey(paramString)) {
      return (String)zzen.get(paramString);
    }
    Object localObject1 = new Properties();
    GmsLogger localGmsLogger = null;
    Object localObject3 = null;
    try
    {
      localObject4 = String.format("/%s.properties", new Object[] { paramString });
      localObject4 = com.google.android.gms.common.internal.LibraryVersion.class.getResourceAsStream((String)localObject4);
      int i;
      if (localObject4 != null)
      {
        ((Properties)localObject1).load((InputStream)localObject4);
        localObject1 = ((Properties)localObject1).getProperty("version", null);
        localGmsLogger = zzel;
        try
        {
          i = String.valueOf(paramString).length();
          int j = String.valueOf(localObject1).length();
          localObject3 = new StringBuilder(i + 12 + j);
          ((StringBuilder)localObject3).append(paramString);
          ((StringBuilder)localObject3).append(" version is ");
          ((StringBuilder)localObject3).append((String)localObject1);
          localGmsLogger.v("LibraryVersion", ((StringBuilder)localObject3).toString());
        }
        catch (IOException localIOException1)
        {
          break label237;
        }
      }
      else
      {
        localObject4 = zzel;
        localObject1 = String.valueOf(paramString);
        i = ((String)localObject1).length();
        if (i != 0) {
          localObject1 = "Failed to get app version for libraryName: ".concat((String)localObject1);
        } else {
          localObject1 = new String("Failed to get app version for libraryName: ");
        }
        ((GmsLogger)localObject4).append("LibraryVersion", (String)localObject1);
        localObject1 = localIOException1;
      }
    }
    catch (IOException localIOException2)
    {
      localObject1 = localObject3;
      label237:
      Object localObject4 = zzel;
      localObject3 = String.valueOf(paramString);
      if (((String)localObject3).length() != 0) {
        localObject3 = "Failed to get app version for libraryName: ".concat((String)localObject3);
      } else {
        localObject3 = new String("Failed to get app version for libraryName: ");
      }
      ((GmsLogger)localObject4).create("LibraryVersion", (String)localObject3, localIOException2);
    }
    Object localObject2 = localObject1;
    if (localObject1 == null)
    {
      localObject2 = "UNKNOWN";
      zzel.d("LibraryVersion", ".properties file is dropped during release process. Failure to read app version isexpected druing Google internal testing where locally-built libraries are used");
    }
    zzen.put(paramString, localObject2);
    return localObject2;
  }
}
