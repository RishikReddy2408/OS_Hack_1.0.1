package com.google.android.android.measurement.internal;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build.VERSION;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;

public final class zzat
  extends zzez
{
  private final SSLSocketFactory zzamq;
  
  public zzat(zzfa paramZzfa)
  {
    super(paramZzfa);
    if (Build.VERSION.SDK_INT < 19) {
      paramZzfa = new zzfl();
    } else {
      paramZzfa = null;
    }
    zzamq = paramZzfa;
  }
  
  private static byte[] fetch(HttpURLConnection paramHttpURLConnection)
    throws IOException
  {
    Object localObject;
    try
    {
      localObject = new ByteArrayOutputStream();
      InputStream localInputStream = paramHttpURLConnection.getInputStream();
      paramHttpURLConnection = localInputStream;
      try
      {
        byte[] arrayOfByte = new byte['?'];
        for (;;)
        {
          int i = localInputStream.read(arrayOfByte);
          if (i <= 0) {
            break;
          }
          ((ByteArrayOutputStream)localObject).write(arrayOfByte, 0, i);
        }
        localObject = ((ByteArrayOutputStream)localObject).toByteArray();
        if (localInputStream == null) {
          return localObject;
        }
        localInputStream.close();
        return localObject;
      }
      catch (Throwable localThrowable1) {}
      if (paramHttpURLConnection == null) {
        break label74;
      }
    }
    catch (Throwable localThrowable2)
    {
      paramHttpURLConnection = null;
    }
    paramHttpURLConnection.close();
    label74:
    throw localThrowable2;
    return localObject;
  }
  
  protected final HttpURLConnection createConnection(URL paramURL)
    throws IOException
  {
    paramURL = paramURL.openConnection();
    if ((paramURL instanceof HttpURLConnection))
    {
      if ((zzamq != null) && ((paramURL instanceof HttpsURLConnection))) {
        ((HttpsURLConnection)paramURL).setSSLSocketFactory(zzamq);
      }
      paramURL = (HttpURLConnection)paramURL;
      paramURL.setDefaultUseCaches(false);
      paramURL.setConnectTimeout(60000);
      paramURL.setReadTimeout(61000);
      paramURL.setInstanceFollowRedirects(false);
      paramURL.setDoInput(true);
      return paramURL;
    }
    throw new IOException("Failed to obtain HTTP connection");
  }
  
  public final boolean zzfb()
  {
    zzcl();
    Object localObject = (ConnectivityManager)getContext().getSystemService("connectivity");
    try
    {
      localObject = ((ConnectivityManager)localObject).getActiveNetworkInfo();
    }
    catch (SecurityException localSecurityException)
    {
      for (;;) {}
    }
    localObject = null;
    return (localObject != null) && (((NetworkInfo)localObject).isConnected());
  }
  
  protected final boolean zzgt()
  {
    return false;
  }
}
