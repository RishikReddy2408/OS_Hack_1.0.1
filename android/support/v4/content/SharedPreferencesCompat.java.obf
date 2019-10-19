package android.support.v4.content;

import android.content.SharedPreferences.Editor;
import android.support.annotation.NonNull;

@Deprecated
public final class SharedPreferencesCompat
{
  private SharedPreferencesCompat() {}
  
  @Deprecated
  public static final class EditorCompat
  {
    private static EditorCompat sInstance;
    private final Helper mHelper = new Helper();
    
    private EditorCompat() {}
    
    @Deprecated
    public static EditorCompat getInstance()
    {
      if (sInstance == null) {
        sInstance = new EditorCompat();
      }
      return sInstance;
    }
    
    @Deprecated
    public void apply(@NonNull SharedPreferences.Editor paramEditor)
    {
      mHelper.apply(paramEditor);
    }
    
    private static class Helper
    {
      Helper() {}
      
      public void apply(@NonNull SharedPreferences.Editor paramEditor)
      {
        try
        {
          paramEditor.apply();
          return;
        }
        catch (AbstractMethodError localAbstractMethodError)
        {
          for (;;) {}
        }
        paramEditor.commit();
      }
    }
  }
}
