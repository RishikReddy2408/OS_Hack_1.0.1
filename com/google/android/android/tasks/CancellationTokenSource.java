package com.google.android.android.tasks;

public class CancellationTokenSource
{
  private final SpeechRecognizer this$0 = new SpeechRecognizer();
  
  public CancellationTokenSource() {}
  
  public void cancel()
  {
    this$0.cancel();
  }
  
  public CancellationToken getToken()
  {
    return this$0;
  }
}
