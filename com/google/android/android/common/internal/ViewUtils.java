package com.google.android.android.common.internal;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public class ViewUtils
{
  private ViewUtils() {}
  
  public static String getXmlAttributeString(String paramString1, String paramString2, Context paramContext, AttributeSet paramAttributeSet, boolean paramBoolean1, boolean paramBoolean2, String paramString3)
  {
    if (paramAttributeSet == null) {
      paramString1 = null;
    } else {
      paramString1 = paramAttributeSet.getAttributeValue(paramString1, paramString2);
    }
    paramAttributeSet = paramString1;
    String str1;
    String str2;
    if (paramString1 != null)
    {
      paramAttributeSet = paramString1;
      if (paramString1.startsWith("@string/"))
      {
        paramAttributeSet = paramString1;
        if (paramBoolean1)
        {
          str1 = paramString1.substring(8);
          str2 = paramContext.getPackageName();
          paramAttributeSet = new TypedValue();
        }
      }
    }
    try
    {
      paramContext = paramContext.getResources();
      int i = String.valueOf(str2).length();
      int j = String.valueOf(str1).length();
      StringBuilder localStringBuilder = new StringBuilder(i + 8 + j);
      localStringBuilder.append(str2);
      localStringBuilder.append(":string/");
      localStringBuilder.append(str1);
      paramContext.getValue(localStringBuilder.toString(), paramAttributeSet, true);
    }
    catch (Resources.NotFoundException paramContext)
    {
      for (;;) {}
    }
    paramContext = new StringBuilder(String.valueOf(paramString2).length() + 30 + String.valueOf(paramString1).length());
    paramContext.append("Could not find resource for ");
    paramContext.append(paramString2);
    paramContext.append(": ");
    paramContext.append(paramString1);
    Log.w(paramString3, paramContext.toString());
    if (string != null)
    {
      paramAttributeSet = string.toString();
    }
    else
    {
      paramContext = String.valueOf(paramAttributeSet);
      paramAttributeSet = new StringBuilder(String.valueOf(paramString2).length() + 28 + String.valueOf(paramContext).length());
      paramAttributeSet.append("Resource ");
      paramAttributeSet.append(paramString2);
      paramAttributeSet.append(" was not a string: ");
      paramAttributeSet.append(paramContext);
      Log.w(paramString3, paramAttributeSet.toString());
      paramAttributeSet = paramString1;
    }
    if ((paramBoolean2) && (paramAttributeSet == null))
    {
      paramString1 = new StringBuilder(String.valueOf(paramString2).length() + 33);
      paramString1.append("Required XML attribute \"");
      paramString1.append(paramString2);
      paramString1.append("\" missing");
      Log.w(paramString3, paramString1.toString());
      return paramAttributeSet;
    }
    return paramAttributeSet;
  }
}
