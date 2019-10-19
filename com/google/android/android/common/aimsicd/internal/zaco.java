package com.google.android.android.common.aimsicd.internal;

import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.google.android.android.common.aimsicd.PendingResult;
import com.google.android.android.common.aimsicd.Status;
import com.google.android.android.internal.base.Credentials;

final class zaco
  extends Credentials
{
  public zaco(zacm paramZacm, Looper paramLooper)
  {
    super(paramLooper);
  }
  
  public final void handleMessage(Message paramMessage)
  {
    switch (what)
    {
    default: 
      int i = what;
      paramMessage = new StringBuilder(70);
      paramMessage.append("TransformationResultHandler received unknown message type: ");
      paramMessage.append(i);
      Log.e("TransformedResultImpl", paramMessage.toString());
      return;
    case 1: 
      localObject = (RuntimeException)obj;
      paramMessage = String.valueOf(((Exception)localObject).getMessage());
      if (paramMessage.length() != 0) {
        paramMessage = "Runtime exception on the transformation worker thread: ".concat(paramMessage);
      } else {
        paramMessage = new String("Runtime exception on the transformation worker thread: ");
      }
      Log.e("TransformedResultImpl", paramMessage);
      throw ((Throwable)localObject);
    }
    Object localObject = (PendingResult)obj;
    paramMessage = zacm.getBuffers(zakv);
    if (localObject == null) {}
    try
    {
      zacm.sendError(zacm.loadView(zakv), new Status(13, "Transform returned null"));
      break label210;
      if ((localObject instanceof zacd)) {
        zacm.sendError(zacm.loadView(zakv), ((zacd)localObject).getStatus());
      } else {
        zacm.loadView(zakv).onResultReceived((PendingResult)localObject);
      }
      label210:
      return;
    }
    catch (Throwable localThrowable)
    {
      for (;;) {}
    }
    throw ((Throwable)localObject);
  }
}
