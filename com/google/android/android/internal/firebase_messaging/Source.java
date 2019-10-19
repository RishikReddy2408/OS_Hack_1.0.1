package com.google.android.android.internal.firebase_messaging;

abstract class Source
{
  private static final Throwable[] CRLF = new Throwable[0];
  
  Source() {}
  
  public abstract void read(Throwable paramThrowable1, Throwable paramThrowable2);
}
