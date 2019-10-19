package android.support.v13.view;

import android.app.Activity;
import android.os.Build.VERSION;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.view.DragAndDropPermissions;
import android.view.DragEvent;

public final class DragAndDropPermissionsCompat
{
  private Object mDragAndDropPermissions;
  
  private DragAndDropPermissionsCompat(Object paramObject)
  {
    mDragAndDropPermissions = paramObject;
  }
  
  @Nullable
  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  public static DragAndDropPermissionsCompat request(Activity paramActivity, DragEvent paramDragEvent)
  {
    if (Build.VERSION.SDK_INT >= 24)
    {
      paramActivity = paramActivity.requestDragAndDropPermissions(paramDragEvent);
      if (paramActivity != null) {
        return new DragAndDropPermissionsCompat(paramActivity);
      }
    }
    return null;
  }
  
  public void release()
  {
    if (Build.VERSION.SDK_INT >= 24) {
      ((DragAndDropPermissions)mDragAndDropPermissions).release();
    }
  }
}
