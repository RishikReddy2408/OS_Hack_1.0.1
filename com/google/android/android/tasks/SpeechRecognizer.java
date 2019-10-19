package com.google.android.android.tasks;

import com.google.android.gms.tasks.zzu;

final class SpeechRecognizer
  extends CancellationToken
{
  private final zzu<Void> mainHandler = new AbstractDataSource();
  
  SpeechRecognizer() {}
  
  public final void cancel()
  {
    mainHandler.trySetResult(null);
  }
  
  public final boolean isCancellationRequested()
  {
    return mainHandler.isComplete();
  }
  
  public final CancellationToken onCanceledRequested(OnTokenCanceledListener paramOnTokenCanceledListener)
  {
    mainHandler.addOnSuccessListener(new NowPlayingFragment.10(this, paramOnTokenCanceledListener));
    return this;
  }
}
