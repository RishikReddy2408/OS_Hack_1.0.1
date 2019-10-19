package android.support.v4.os;

import android.os.Build.VERSION;
import android.os.Trace;

public final class TraceCompat
{
  private TraceCompat() {}
  
  public static void beginSection(String paramString)
  {
    if (Build.VERSION.SDK_INT >= 18) {
      Trace.beginSection(paramString);
    }
  }
  
  public static void endSection()
  {
    if (Build.VERSION.SDK_INT >= 18) {
      Trace.endSection();
    }
  }
}
