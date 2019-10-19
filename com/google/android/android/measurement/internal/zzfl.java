package com.google.android.android.measurement.internal;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

final class zzfl
  extends SSLSocketFactory
{
  private final SSLSocketFactory zzaum;
  
  zzfl()
  {
    this(HttpsURLConnection.getDefaultSSLSocketFactory());
  }
  
  private zzfl(SSLSocketFactory paramSSLSocketFactory)
  {
    zzaum = paramSSLSocketFactory;
  }
  
  private final SSLSocket createSocket(SSLSocket paramSSLSocket)
  {
    return new zzfm(this, paramSSLSocket);
  }
  
  public final Socket createSocket()
    throws IOException
  {
    return createSocket((SSLSocket)zzaum.createSocket());
  }
  
  public final Socket createSocket(String paramString, int paramInt)
    throws IOException
  {
    return createSocket((SSLSocket)zzaum.createSocket(paramString, paramInt));
  }
  
  public final Socket createSocket(String paramString, int paramInt1, InetAddress paramInetAddress, int paramInt2)
    throws IOException
  {
    return createSocket((SSLSocket)zzaum.createSocket(paramString, paramInt1, paramInetAddress, paramInt2));
  }
  
  public final Socket createSocket(InetAddress paramInetAddress, int paramInt)
    throws IOException
  {
    return createSocket((SSLSocket)zzaum.createSocket(paramInetAddress, paramInt));
  }
  
  public final Socket createSocket(InetAddress paramInetAddress1, int paramInt1, InetAddress paramInetAddress2, int paramInt2)
    throws IOException
  {
    return createSocket((SSLSocket)zzaum.createSocket(paramInetAddress1, paramInt1, paramInetAddress2, paramInt2));
  }
  
  public final Socket createSocket(Socket paramSocket, String paramString, int paramInt, boolean paramBoolean)
    throws IOException
  {
    return createSocket((SSLSocket)zzaum.createSocket(paramSocket, paramString, paramInt, paramBoolean));
  }
  
  public final String[] getDefaultCipherSuites()
  {
    return zzaum.getDefaultCipherSuites();
  }
  
  public final String[] getSupportedCipherSuites()
  {
    return zzaum.getSupportedCipherSuites();
  }
}
