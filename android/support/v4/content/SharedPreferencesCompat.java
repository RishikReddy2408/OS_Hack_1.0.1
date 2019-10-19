package android.support.v4.content;

import android.content.SharedPreferences.Editor;

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
    
    public static EditorCompat getInstance()
    {
      if (sInstance == null) {
        sInstance = new EditorCompat();
      }
      return sInstance;
    }
    
    public void apply(SharedPreferences.Editor paramEditor)
    {
      mHelper.apply(paramEditor);
    }
    
    private static class Helper
    {
      Helper() {}
      
      public void apply(SharedPreferences.Editor paramEditor)
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
