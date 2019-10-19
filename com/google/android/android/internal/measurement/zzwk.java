package com.google.android.android.internal.measurement;

final class zzwk
  implements zzws
{
  private zzws[] zzcaq;
  
  zzwk(zzws... paramVarArgs)
  {
    zzcaq = paramVarArgs;
  }
  
  public final zzwr getText(Class paramClass)
  {
    zzws[] arrayOfZzws = zzcaq;
    int j = arrayOfZzws.length;
    int i = 0;
    while (i < j)
    {
      zzws localZzws = arrayOfZzws[i];
      if (localZzws.isAssignableFrom(paramClass)) {
        return localZzws.getText(paramClass);
      }
      i += 1;
    }
    paramClass = String.valueOf(paramClass.getName());
    if (paramClass.length() != 0) {
      paramClass = "No factory is available for message type: ".concat(paramClass);
    } else {
      paramClass = new String("No factory is available for message type: ");
    }
    throw new UnsupportedOperationException(paramClass);
  }
  
  public final boolean isAssignableFrom(Class paramClass)
  {
    zzws[] arrayOfZzws = zzcaq;
    int j = arrayOfZzws.length;
    int i = 0;
    while (i < j)
    {
      if (arrayOfZzws[i].isAssignableFrom(paramClass)) {
        return true;
      }
      i += 1;
    }
    return false;
  }
}
