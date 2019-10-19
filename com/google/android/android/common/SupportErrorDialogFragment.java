package com.google.android.android.common;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.os.Bundle;
import android.support.v4.package_7.DialogFragment;
import android.support.v4.package_7.FragmentManager;
import com.google.android.android.common.internal.Preconditions;

public class SupportErrorDialogFragment
  extends DialogFragment
{
  private Dialog mDialog = null;
  private DialogInterface.OnCancelListener zaan = null;
  
  public SupportErrorDialogFragment() {}
  
  public static SupportErrorDialogFragment newInstance(Dialog paramDialog)
  {
    return newInstance(paramDialog, null);
  }
  
  public static SupportErrorDialogFragment newInstance(Dialog paramDialog, DialogInterface.OnCancelListener paramOnCancelListener)
  {
    SupportErrorDialogFragment localSupportErrorDialogFragment = new SupportErrorDialogFragment();
    paramDialog = (Dialog)Preconditions.checkNotNull(paramDialog, "Cannot display null dialog");
    paramDialog.setOnCancelListener(null);
    paramDialog.setOnDismissListener(null);
    mDialog = paramDialog;
    if (paramOnCancelListener != null) {
      zaan = paramOnCancelListener;
    }
    return localSupportErrorDialogFragment;
  }
  
  public void onCancel(DialogInterface paramDialogInterface)
  {
    if (zaan != null) {
      zaan.onCancel(paramDialogInterface);
    }
  }
  
  public Dialog onCreateDialog(Bundle paramBundle)
  {
    if (mDialog == null) {
      setShowsDialog(false);
    }
    return mDialog;
  }
  
  public void show(FragmentManager paramFragmentManager, String paramString)
  {
    super.show(paramFragmentManager, paramString);
  }
}
