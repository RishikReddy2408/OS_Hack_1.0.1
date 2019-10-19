package com.google.android.android.dynamic;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.google.android.gms.common.annotation.KeepForSdk;

@SuppressLint({"NewApi"})
@KeepForSdk
public final class FragmentWrapper
  extends IFragmentWrapper.Stub
{
  private Fragment zzhy;
  
  private FragmentWrapper(Fragment paramFragment)
  {
    zzhy = paramFragment;
  }
  
  public static FragmentWrapper wrap(Fragment paramFragment)
  {
    if (paramFragment != null) {
      return new FragmentWrapper(paramFragment);
    }
    return null;
  }
  
  public final Bundle getArguments()
  {
    return zzhy.getArguments();
  }
  
  public final int getId()
  {
    return zzhy.getId();
  }
  
  public final boolean getRetainInstance()
  {
    return zzhy.getRetainInstance();
  }
  
  public final String getTag()
  {
    return zzhy.getTag();
  }
  
  public final int getTargetRequestCode()
  {
    return zzhy.getTargetRequestCode();
  }
  
  public final boolean getUserVisibleHint()
  {
    return zzhy.getUserVisibleHint();
  }
  
  public final boolean isAdded()
  {
    return zzhy.isAdded();
  }
  
  public final boolean isDetached()
  {
    return zzhy.isDetached();
  }
  
  public final boolean isHidden()
  {
    return zzhy.isHidden();
  }
  
  public final boolean isInLayout()
  {
    return zzhy.isInLayout();
  }
  
  public final boolean isRemoving()
  {
    return zzhy.isRemoving();
  }
  
  public final boolean isResumed()
  {
    return zzhy.isResumed();
  }
  
  public final boolean isVisible()
  {
    return zzhy.isVisible();
  }
  
  public final void refreshViews(IObjectWrapper paramIObjectWrapper)
  {
    paramIObjectWrapper = (View)ObjectWrapper.unwrap(paramIObjectWrapper);
    zzhy.unregisterForContextMenu(paramIObjectWrapper);
  }
  
  public final void register(IObjectWrapper paramIObjectWrapper)
  {
    paramIObjectWrapper = (View)ObjectWrapper.unwrap(paramIObjectWrapper);
    zzhy.registerForContextMenu(paramIObjectWrapper);
  }
  
  public final void setHasOptionsMenu(boolean paramBoolean)
  {
    zzhy.setHasOptionsMenu(paramBoolean);
  }
  
  public final void setMenuVisibility(boolean paramBoolean)
  {
    zzhy.setMenuVisibility(paramBoolean);
  }
  
  public final void setRetainInstance(boolean paramBoolean)
  {
    zzhy.setRetainInstance(paramBoolean);
  }
  
  public final void setUserVisibleHint(boolean paramBoolean)
  {
    zzhy.setUserVisibleHint(paramBoolean);
  }
  
  public final void startActivity(Intent paramIntent)
  {
    zzhy.startActivity(paramIntent);
  }
  
  public final void startActivityForResult(Intent paramIntent, int paramInt)
  {
    zzhy.startActivityForResult(paramIntent, paramInt);
  }
  
  public final IObjectWrapper zzad()
  {
    return ObjectWrapper.wrap(zzhy.getActivity());
  }
  
  public final IFragmentWrapper zzae()
  {
    return wrap(zzhy.getParentFragment());
  }
  
  public final IObjectWrapper zzaf()
  {
    return ObjectWrapper.wrap(zzhy.getResources());
  }
  
  public final IFragmentWrapper zzag()
  {
    return wrap(zzhy.getTargetFragment());
  }
  
  public final IObjectWrapper zzah()
  {
    return ObjectWrapper.wrap(zzhy.getView());
  }
}
