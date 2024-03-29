package android.arch.lifecycle;

import android.annotation.SuppressLint;
import android.app.Application;

public class AndroidViewModel
  extends ViewModel
{
  @SuppressLint({"StaticFieldLeak"})
  private Application mApplication;
  
  public AndroidViewModel(Application paramApplication)
  {
    mApplication = paramApplication;
  }
  
  public Application getApplication()
  {
    return mApplication;
  }
}
