package com.google.android.android.common;

import android.os.RemoteException;
import android.util.Log;
import com.google.android.android.common.internal.Parameter;
import com.google.android.android.common.internal.Preconditions;
import com.google.android.android.common.internal.Property;
import com.google.android.android.dynamic.IObjectWrapper;
import com.google.android.android.dynamic.ObjectWrapper;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

abstract class Type
  extends Parameter
{
  private int hashCode;
  
  protected Type(byte[] paramArrayOfByte)
  {
    boolean bool;
    if (paramArrayOfByte.length == 25) {
      bool = true;
    } else {
      bool = false;
    }
    Preconditions.checkArgument(bool);
    hashCode = Arrays.hashCode(paramArrayOfByte);
  }
  
  protected static byte[] encode(String paramString)
  {
    try
    {
      paramString = paramString.getBytes("ISO-8859-1");
      return paramString;
    }
    catch (UnsupportedEncodingException paramString)
    {
      throw new AssertionError(paramString);
    }
  }
  
  public boolean equals(Object paramObject)
  {
    if (paramObject != null)
    {
      if (!(paramObject instanceof Property)) {
        return false;
      }
      paramObject = (Property)paramObject;
      try
      {
        int i = paramObject.getType();
        int j = hashCode();
        if (i != j) {
          return false;
        }
        paramObject = paramObject.get();
        if (paramObject == null) {
          return false;
        }
        paramObject = ObjectWrapper.unwrap(paramObject);
        paramObject = (byte[])paramObject;
        boolean bool = Arrays.equals(getBytes(), paramObject);
        return bool;
      }
      catch (RemoteException paramObject)
      {
        Log.e("GoogleCertificates", "Failed to get Google certificates from remote", paramObject);
      }
    }
    return false;
  }
  
  public final IObjectWrapper get()
  {
    return ObjectWrapper.wrap(getBytes());
  }
  
  abstract byte[] getBytes();
  
  public final int getType()
  {
    return hashCode();
  }
  
  public int hashCode()
  {
    return hashCode;
  }
}
