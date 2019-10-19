package android.support.v7.widget;

import android.content.Context;
import android.content.res.Resources.Theme;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.widget.SpinnerAdapter;

public abstract interface ThemedSpinnerAdapter
  extends SpinnerAdapter
{
  @Nullable
  public abstract Resources.Theme getDropDownViewTheme();
  
  public abstract void setDropDownViewTheme(@Nullable Resources.Theme paramTheme);
  
  public static final class Helper
  {
    private final Context mContext;
    private LayoutInflater mDropDownInflater;
    private final LayoutInflater mInflater;
    
    public Helper(@NonNull Context paramContext)
    {
      mContext = paramContext;
      mInflater = LayoutInflater.from(paramContext);
    }
    
    @NonNull
    public LayoutInflater getDropDownViewInflater()
    {
      if (mDropDownInflater != null) {
        return mDropDownInflater;
      }
      return mInflater;
    }
    
    @Nullable
    public Resources.Theme getDropDownViewTheme()
    {
      if (mDropDownInflater == null) {
        return null;
      }
      return mDropDownInflater.getContext().getTheme();
    }
    
    public void setDropDownViewTheme(@Nullable Resources.Theme paramTheme)
    {
      if (paramTheme == null)
      {
        mDropDownInflater = null;
        return;
      }
      if (paramTheme == mContext.getTheme())
      {
        mDropDownInflater = mInflater;
        return;
      }
      mDropDownInflater = LayoutInflater.from(new ContextThemeWrapper(mContext, paramTheme));
    }
  }
}
