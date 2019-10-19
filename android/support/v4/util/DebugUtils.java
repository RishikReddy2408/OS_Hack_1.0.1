package android.support.v4.util;

import android.support.annotation.RestrictTo;

@RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
public class DebugUtils
{
  public DebugUtils() {}
  
  public static void buildShortClassTag(Object paramObject, StringBuilder paramStringBuilder)
  {
    if (paramObject == null)
    {
      paramStringBuilder.append("null");
      return;
    }
    String str2 = paramObject.getClass().getSimpleName();
    String str1 = str2;
    if ((str2 == null) || (str2.length() <= 0))
    {
      str2 = paramObject.getClass().getName();
      str1 = str2;
      int i = str2.lastIndexOf('.');
      if (i > 0) {
        str1 = str2.substring(i + 1);
      }
    }
    paramStringBuilder.append(str1);
    paramStringBuilder.append('{');
    paramStringBuilder.append(Integer.toHexString(System.identityHashCode(paramObject)));
  }
}
