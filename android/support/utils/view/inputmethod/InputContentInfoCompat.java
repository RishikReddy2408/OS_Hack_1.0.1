package android.support.utils.view.inputmethod;

import android.content.ClipDescription;
import android.net.Uri;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.inputmethod.InputContentInfo;

public final class InputContentInfoCompat
{
  private final InputContentInfoCompatImpl mImpl;
  
  public InputContentInfoCompat(Uri paramUri1, ClipDescription paramClipDescription, Uri paramUri2)
  {
    if (Build.VERSION.SDK_INT >= 25)
    {
      mImpl = new InputContentInfoCompatApi25Impl(paramUri1, paramClipDescription, paramUri2);
      return;
    }
    mImpl = new InputContentInfoCompatBaseImpl(paramUri1, paramClipDescription, paramUri2);
  }
  
  private InputContentInfoCompat(InputContentInfoCompatImpl paramInputContentInfoCompatImpl)
  {
    mImpl = paramInputContentInfoCompatImpl;
  }
  
  public static InputContentInfoCompat wrap(Object paramObject)
  {
    if (paramObject == null) {
      return null;
    }
    if (Build.VERSION.SDK_INT < 25) {
      return null;
    }
    return new InputContentInfoCompat(new InputContentInfoCompatApi25Impl());
  }
  
  public Uri getContentUri()
  {
    return mImpl.getContentUri();
  }
  
  public ClipDescription getDescription()
  {
    return mImpl.getDescription();
  }
  
  public Uri getLinkUri()
  {
    return mImpl.getLinkUri();
  }
  
  public void releasePermission()
  {
    mImpl.releasePermission();
  }
  
  public void requestPermission()
  {
    mImpl.requestPermission();
  }
  
  public Object unwrap()
  {
    return mImpl.getInputContentInfo();
  }
  
  @RequiresApi(25)
  final class InputContentInfoCompatApi25Impl
    implements InputContentInfoCompat.InputContentInfoCompatImpl
  {
    @NonNull
    final InputContentInfo mObject;
    
    InputContentInfoCompatApi25Impl(ClipDescription paramClipDescription, Uri paramUri)
    {
      mObject = new InputContentInfo(this$1, paramClipDescription, paramUri);
    }
    
    InputContentInfoCompatApi25Impl()
    {
      mObject = ((InputContentInfo)this$1);
    }
    
    public Uri getContentUri()
    {
      return mObject.getContentUri();
    }
    
    public ClipDescription getDescription()
    {
      return mObject.getDescription();
    }
    
    public Object getInputContentInfo()
    {
      return mObject;
    }
    
    public Uri getLinkUri()
    {
      return mObject.getLinkUri();
    }
    
    public void releasePermission()
    {
      mObject.releasePermission();
    }
    
    public void requestPermission()
    {
      mObject.requestPermission();
    }
  }
  
  final class InputContentInfoCompatBaseImpl
    implements InputContentInfoCompat.InputContentInfoCompatImpl
  {
    @NonNull
    private final ClipDescription mDescription;
    @Nullable
    private final Uri mLinkUri;
    
    InputContentInfoCompatBaseImpl(ClipDescription paramClipDescription, Uri paramUri)
    {
      mDescription = paramClipDescription;
      mLinkUri = paramUri;
    }
    
    public Uri getContentUri()
    {
      return InputContentInfoCompat.this;
    }
    
    public ClipDescription getDescription()
    {
      return mDescription;
    }
    
    public Object getInputContentInfo()
    {
      return null;
    }
    
    public Uri getLinkUri()
    {
      return mLinkUri;
    }
    
    public void releasePermission() {}
    
    public void requestPermission() {}
  }
  
  abstract interface InputContentInfoCompatImpl
  {
    public abstract Uri getContentUri();
    
    public abstract ClipDescription getDescription();
    
    public abstract Object getInputContentInfo();
    
    public abstract Uri getLinkUri();
    
    public abstract void releasePermission();
    
    public abstract void requestPermission();
  }
}
