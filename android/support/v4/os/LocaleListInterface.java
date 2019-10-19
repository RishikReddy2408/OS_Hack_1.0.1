package android.support.v4.os;

import android.support.annotation.RestrictTo;
import java.util.Locale;

@RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
abstract interface LocaleListInterface
{
  public abstract boolean equals(Object paramObject);
  
  public abstract Locale getFirstMatch(String[] paramArrayOfString);
  
  public abstract Locale getLocal(int paramInt);
  
  public abstract Object getLocaleList();
  
  public abstract int hashCode();
  
  public abstract int indexOf(Locale paramLocale);
  
  public abstract boolean isEmpty();
  
  public abstract void setLocaleList(Locale... paramVarArgs);
  
  public abstract int size();
  
  public abstract String toLanguageTags();
  
  public abstract String toString();
}
