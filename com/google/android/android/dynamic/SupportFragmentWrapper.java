package com.google.android.android.dynamic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.package_7.Fragment;
import android.view.View;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public final class SupportFragmentWrapper
  extends IFragmentWrapper.Stub
{
  private Fragment zzic;
  
  private SupportFragmentWrapper(Fragment paramFragment)
  {
    zzic = paramFragment;
  }
  
  public static SupportFragmentWrapper wrap(Fragment paramFragment)
  {
    if (paramFragment != null) {
      return new SupportFragmentWrapper(paramFragment);
    }
    return null;
  }
  
  public final Bundle getArguments()
  {
    return zzic.getArguments();
  }
  
  public final int getId()
  {
    return zzic.getId();
  }
  
  public final boolean getRetainInstance()
  {
    return zzic.getRetainInstance();
  }
  
  public final String getTag()
  {
    return zzic.getTag();
  }
  
  public final int getTargetRequestCode()
  {
    return zzic.getTargetRequestCode();
  }
  
  public final boolean getUserVisibleHint()
  {
    return zzic.getUserVisibleHint();
  }
  
  public final boolean isAdded()
  {
    return zzic.isAdded();
  }
  
  public final boolean isDetached()
  {
    return zzic.isDetached();
  }
  
  public final boolean isHidden()
  {
    return zzic.isHidden();
  }
  
  public final boolean isInLayout()
  {
    return zzic.isInLayout();
  }
  
  public final boolean isRemoving()
  {
    return zzic.isRemoving();
  }
  
  public final boolean isResumed()
  {
    return zzic.isResumed();
  }
  
  public final boolean isVisible()
  {
    return zzic.isVisible();
  }
  
  public final void refreshViews(IObjectWrapper paramIObjectWrapper)
  {
    paramIObjectWrapper = (View)ObjectWrapper.unwrap(paramIObjectWrapper);
    zzic.unregisterForContextMenu(paramIObjectWrapper);
  }
  
  public final void register(IObjectWrapper paramIObjectWrapper)
  {
    paramIObjectWrapper = (View)ObjectWrapper.unwrap(paramIObjectWrapper);
    zzic.registerForContextMenu(paramIObjectWrapper);
  }
  
  public final void setHasOptionsMenu(boolean paramBoolean)
  {
    zzic.setHasOptionsMenu(paramBoolean);
  }
  
  public final void setMenuVisibility(boolean paramBoolean)
  {
    zzic.setMenuVisibility(paramBoolean);
  }
  
  public final void setRetainInstance(boolean paramBoolean)
  {
    zzic.setRetainInstance(paramBoolean);
  }
  
  public final void setUserVisibleHint(boolean paramBoolean)
  {
    zzic.setUserVisibleHint(paramBoolean);
  }
  
  public final void startActivity(Intent paramIntent)
  {
    zzic.startActivity(paramIntent);
  }
  
  public final void startActivityForResult(Intent paramIntent, int paramInt)
  {
    zzic.startActivityForResult(paramIntent, paramInt);
  }
  
  public final IObjectWrapper zzad()
  {
    return ObjectWrapper.wrap(zzic.getActivity());
  }
  
  public final IFragmentWrapper zzae()
  {
    return wrap(zzic.getParentFragment());
  }
  
  public final IObjectWrapper zzaf()
  {
    return ObjectWrapper.wrap(zzic.getResources());
  }
  
  public final IFragmentWrapper zzag()
  {
    return wrap(zzic.getTargetFragment());
  }
  
  public final IObjectWrapper zzah()
  {
    return ObjectWrapper.wrap(zzic.getView());
  }
}
