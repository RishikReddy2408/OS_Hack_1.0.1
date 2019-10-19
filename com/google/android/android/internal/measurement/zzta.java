package com.google.android.android.internal.measurement;

import java.io.PrintStream;
import java.util.Iterator;
import java.util.List;

final class zzta
  extends zzsx
{
  private final zzsy zzbrz = new zzsy();
  
  zzta() {}
  
  public final void printStackTrace(Throwable paramThrowable, PrintStream paramPrintStream)
  {
    paramThrowable.printStackTrace(paramPrintStream);
    paramThrowable = zzbrz.get(paramThrowable, false);
    if (paramThrowable == null) {
      return;
    }
    try
    {
      Iterator localIterator = paramThrowable.iterator();
      while (localIterator.hasNext())
      {
        Throwable localThrowable = (Throwable)localIterator.next();
        paramPrintStream.print("Suppressed: ");
        localThrowable.printStackTrace(paramPrintStream);
      }
      return;
    }
    catch (Throwable paramPrintStream)
    {
      throw paramPrintStream;
    }
  }
}
