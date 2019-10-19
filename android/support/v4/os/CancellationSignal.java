package android.support.v4.os;

import android.os.Build.VERSION;

public final class CancellationSignal
{
  private boolean mCancelInProgress;
  private Object mCancellationSignalObj;
  private boolean mIsCanceled;
  private OnCancelListener mOnCancelListener;
  
  public CancellationSignal() {}
  
  private void waitForCancelFinishedLocked()
  {
    while (mCancelInProgress) {
      try
      {
        wait();
      }
      catch (InterruptedException localInterruptedException)
      {
        for (;;) {}
      }
    }
  }
  
  /* Error */
  public void cancel()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 32	android/support/v4/os/CancellationSignal:mIsCanceled	Z
    //   6: ifeq +6 -> 12
    //   9: aload_0
    //   10: monitorexit
    //   11: return
    //   12: aload_0
    //   13: iconst_1
    //   14: putfield 32	android/support/v4/os/CancellationSignal:mIsCanceled	Z
    //   17: aload_0
    //   18: iconst_1
    //   19: putfield 24	android/support/v4/os/CancellationSignal:mCancelInProgress	Z
    //   22: aload_0
    //   23: getfield 34	android/support/v4/os/CancellationSignal:mOnCancelListener	Landroid/support/v4/os/CancellationSignal$OnCancelListener;
    //   26: astore_2
    //   27: aload_0
    //   28: getfield 36	android/support/v4/os/CancellationSignal:mCancellationSignalObj	Ljava/lang/Object;
    //   31: astore_3
    //   32: aload_0
    //   33: monitorexit
    //   34: aload_2
    //   35: ifnull +16 -> 51
    //   38: aload_2
    //   39: invokeinterface 39 1 0
    //   44: goto +7 -> 51
    //   47: astore_2
    //   48: goto +27 -> 75
    //   51: aload_3
    //   52: ifnull +43 -> 95
    //   55: getstatic 45	android/os/Build$VERSION:SDK_INT	I
    //   58: istore_1
    //   59: iload_1
    //   60: bipush 16
    //   62: if_icmplt +33 -> 95
    //   65: aload_3
    //   66: checkcast 47	android/os/CancellationSignal
    //   69: invokevirtual 49	android/os/CancellationSignal:cancel	()V
    //   72: goto +23 -> 95
    //   75: aload_0
    //   76: monitorenter
    //   77: aload_0
    //   78: iconst_0
    //   79: putfield 24	android/support/v4/os/CancellationSignal:mCancelInProgress	Z
    //   82: aload_0
    //   83: invokevirtual 52	java/lang/Object:notifyAll	()V
    //   86: aload_0
    //   87: monitorexit
    //   88: aload_2
    //   89: athrow
    //   90: astore_2
    //   91: aload_0
    //   92: monitorexit
    //   93: aload_2
    //   94: athrow
    //   95: aload_0
    //   96: monitorenter
    //   97: aload_0
    //   98: iconst_0
    //   99: putfield 24	android/support/v4/os/CancellationSignal:mCancelInProgress	Z
    //   102: aload_0
    //   103: invokevirtual 52	java/lang/Object:notifyAll	()V
    //   106: aload_0
    //   107: monitorexit
    //   108: return
    //   109: astore_2
    //   110: aload_0
    //   111: monitorexit
    //   112: aload_2
    //   113: athrow
    //   114: astore_2
    //   115: aload_0
    //   116: monitorexit
    //   117: aload_2
    //   118: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	119	0	this	CancellationSignal
    //   58	5	1	i	int
    //   26	13	2	localOnCancelListener	OnCancelListener
    //   47	42	2	localThrowable1	Throwable
    //   90	4	2	localThrowable2	Throwable
    //   109	4	2	localThrowable3	Throwable
    //   114	4	2	localThrowable4	Throwable
    //   31	35	3	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   38	44	47	java/lang/Throwable
    //   55	59	47	java/lang/Throwable
    //   65	72	47	java/lang/Throwable
    //   77	88	90	java/lang/Throwable
    //   91	93	90	java/lang/Throwable
    //   97	108	109	java/lang/Throwable
    //   110	112	109	java/lang/Throwable
    //   2	11	114	java/lang/Throwable
    //   12	34	114	java/lang/Throwable
    //   115	117	114	java/lang/Throwable
  }
  
  public Object getCancellationSignalObject()
  {
    if (Build.VERSION.SDK_INT < 16) {
      return null;
    }
    try
    {
      if (mCancellationSignalObj == null)
      {
        mCancellationSignalObj = new android.os.CancellationSignal();
        if (mIsCanceled) {
          ((android.os.CancellationSignal)mCancellationSignalObj).cancel();
        }
      }
      Object localObject = mCancellationSignalObj;
      return localObject;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  public boolean isCanceled()
  {
    try
    {
      boolean bool = mIsCanceled;
      return bool;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  public void setOnCancelListener(OnCancelListener paramOnCancelListener)
  {
    try
    {
      waitForCancelFinishedLocked();
      if (mOnCancelListener == paramOnCancelListener) {
        return;
      }
      mOnCancelListener = paramOnCancelListener;
      if ((mIsCanceled) && (paramOnCancelListener != null))
      {
        paramOnCancelListener.onCancel();
        return;
      }
      return;
    }
    catch (Throwable paramOnCancelListener)
    {
      throw paramOnCancelListener;
    }
  }
  
  public void throwIfCanceled()
  {
    if (!isCanceled()) {
      return;
    }
    throw new OperationCanceledException();
  }
  
  public static abstract interface OnCancelListener
  {
    public abstract void onCancel();
  }
}
