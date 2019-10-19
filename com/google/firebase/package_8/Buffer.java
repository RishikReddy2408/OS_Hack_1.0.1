package com.google.firebase.package_8;

import android.util.Base64;
import com.google.android.android.common.internal.Objects;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

final class Buffer
{
  private final KeyPair zzbr;
  private final long zzbs;
  
  Buffer(KeyPair paramKeyPair, long paramLong)
  {
    zzbr = paramKeyPair;
    zzbs = paramLong;
  }
  
  private final String encode()
  {
    return Base64.encodeToString(zzbr.getPublic().getEncoded(), 11);
  }
  
  private final String write()
  {
    return Base64.encodeToString(zzbr.getPrivate().getEncoded(), 11);
  }
  
  public final boolean equals(Object paramObject)
  {
    if (!(paramObject instanceof Buffer)) {
      return false;
    }
    paramObject = (Buffer)paramObject;
    return (zzbs == zzbs) && (zzbr.getPublic().equals(zzbr.getPublic())) && (zzbr.getPrivate().equals(zzbr.getPrivate()));
  }
  
  final long getCreationTime()
  {
    return zzbs;
  }
  
  final KeyPair getKeyPair()
  {
    return zzbr;
  }
  
  public final int hashCode()
  {
    return Objects.hashCode(new Object[] { zzbr.getPublic(), zzbr.getPrivate(), Long.valueOf(zzbs) });
  }
}
