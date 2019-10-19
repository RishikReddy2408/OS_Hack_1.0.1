package android.support.v4.os;

import android.os.Build.VERSION;
import android.os.LocaleList;
import android.support.annotation.RequiresApi;
import java.util.Locale;

public final class LocaleListCompat
{
  static final LocaleListInterface IMPL = new LocaleListCompatBaseImpl();
  private static final LocaleListCompat sEmptyLocaleList = new LocaleListCompat();
  
  static
  {
    if (Build.VERSION.SDK_INT >= 24)
    {
      IMPL = new LocaleListCompatApi24Impl();
      return;
    }
  }
  
  private LocaleListCompat() {}
  
  public static LocaleListCompat create(Locale... paramVarArgs)
  {
    LocaleListCompat localLocaleListCompat = new LocaleListCompat();
    localLocaleListCompat.setLocaleListArray(paramVarArgs);
    return localLocaleListCompat;
  }
  
  public static LocaleListCompat forLanguageTags(String paramString)
  {
    if ((paramString != null) && (!paramString.isEmpty()))
    {
      String[] arrayOfString = paramString.split(",");
      Locale[] arrayOfLocale = new Locale[arrayOfString.length];
      int i = 0;
      while (i < arrayOfLocale.length)
      {
        if (Build.VERSION.SDK_INT >= 21) {
          paramString = Locale.forLanguageTag(arrayOfString[i]);
        } else {
          paramString = LocaleHelper.forLanguageTag(arrayOfString[i]);
        }
        arrayOfLocale[i] = paramString;
        i += 1;
      }
      paramString = new LocaleListCompat();
      paramString.setLocaleListArray(arrayOfLocale);
      return paramString;
    }
    return getEmptyLocaleList();
  }
  
  public static LocaleListCompat getAdjustedDefault()
  {
    if (Build.VERSION.SDK_INT >= 24) {
      return wrap(LocaleList.getAdjustedDefault());
    }
    return create(new Locale[] { Locale.getDefault() });
  }
  
  public static LocaleListCompat getDefault()
  {
    if (Build.VERSION.SDK_INT >= 24) {
      return wrap(LocaleList.getDefault());
    }
    return create(new Locale[] { Locale.getDefault() });
  }
  
  public static LocaleListCompat getEmptyLocaleList()
  {
    return sEmptyLocaleList;
  }
  
  private void setLocaleList(LocaleList paramLocaleList)
  {
    int j = paramLocaleList.size();
    if (j > 0)
    {
      Locale[] arrayOfLocale = new Locale[j];
      int i = 0;
      while (i < j)
      {
        arrayOfLocale[i] = paramLocaleList.get(i);
        i += 1;
      }
      IMPL.setLocaleList(arrayOfLocale);
    }
  }
  
  private void setLocaleListArray(Locale... paramVarArgs)
  {
    IMPL.setLocaleList(paramVarArgs);
  }
  
  public static LocaleListCompat wrap(Object paramObject)
  {
    LocaleListCompat localLocaleListCompat = new LocaleListCompat();
    if ((paramObject instanceof LocaleList)) {
      localLocaleListCompat.setLocaleList((LocaleList)paramObject);
    }
    return localLocaleListCompat;
  }
  
  public boolean equals(Object paramObject)
  {
    return IMPL.equals(paramObject);
  }
  
  public Locale getFirstMatch(String[] paramArrayOfString)
  {
    return IMPL.getFirstMatch(paramArrayOfString);
  }
  
  public Locale getLocal(int paramInt)
  {
    return IMPL.getLocal(paramInt);
  }
  
  public int hashCode()
  {
    return IMPL.hashCode();
  }
  
  public int indexOf(Locale paramLocale)
  {
    return IMPL.indexOf(paramLocale);
  }
  
  public boolean isEmpty()
  {
    return IMPL.isEmpty();
  }
  
  public int size()
  {
    return IMPL.size();
  }
  
  public String toLanguageTags()
  {
    return IMPL.toLanguageTags();
  }
  
  public String toString()
  {
    return IMPL.toString();
  }
  
  public Object unwrap()
  {
    return IMPL.getLocaleList();
  }
  
  @RequiresApi(24)
  static class LocaleListCompatApi24Impl
    implements LocaleListInterface
  {
    private LocaleList mLocaleList = new LocaleList(new Locale[0]);
    
    LocaleListCompatApi24Impl() {}
    
    public boolean equals(Object paramObject)
    {
      return mLocaleList.equals(((LocaleListCompat)paramObject).unwrap());
    }
    
    public Locale getFirstMatch(String[] paramArrayOfString)
    {
      if (mLocaleList != null) {
        return mLocaleList.getFirstMatch(paramArrayOfString);
      }
      return null;
    }
    
    public Locale getLocal(int paramInt)
    {
      return mLocaleList.get(paramInt);
    }
    
    public Object getLocaleList()
    {
      return mLocaleList;
    }
    
    public int hashCode()
    {
      return mLocaleList.hashCode();
    }
    
    public int indexOf(Locale paramLocale)
    {
      return mLocaleList.indexOf(paramLocale);
    }
    
    public boolean isEmpty()
    {
      return mLocaleList.isEmpty();
    }
    
    public void setLocaleList(Locale... paramVarArgs)
    {
      mLocaleList = new LocaleList(paramVarArgs);
    }
    
    public int size()
    {
      return mLocaleList.size();
    }
    
    public String toLanguageTags()
    {
      return mLocaleList.toLanguageTags();
    }
    
    public String toString()
    {
      return mLocaleList.toString();
    }
  }
  
  static class LocaleListCompatBaseImpl
    implements LocaleListInterface
  {
    private LocaleListHelper mLocaleList = new LocaleListHelper(new Locale[0]);
    
    LocaleListCompatBaseImpl() {}
    
    public boolean equals(Object paramObject)
    {
      return mLocaleList.equals(((LocaleListCompat)paramObject).unwrap());
    }
    
    public Locale getFirstMatch(String[] paramArrayOfString)
    {
      if (mLocaleList != null) {
        return mLocaleList.getFirstMatch(paramArrayOfString);
      }
      return null;
    }
    
    public Locale getLocal(int paramInt)
    {
      return mLocaleList.get(paramInt);
    }
    
    public Object getLocaleList()
    {
      return mLocaleList;
    }
    
    public int hashCode()
    {
      return mLocaleList.hashCode();
    }
    
    public int indexOf(Locale paramLocale)
    {
      return mLocaleList.indexOf(paramLocale);
    }
    
    public boolean isEmpty()
    {
      return mLocaleList.isEmpty();
    }
    
    public void setLocaleList(Locale... paramVarArgs)
    {
      mLocaleList = new LocaleListHelper(paramVarArgs);
    }
    
    public int size()
    {
      return mLocaleList.size();
    }
    
    public String toLanguageTags()
    {
      return mLocaleList.toLanguageTags();
    }
    
    public String toString()
    {
      return mLocaleList.toString();
    }
  }
}
