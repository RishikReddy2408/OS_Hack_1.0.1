package com.google.android.android.internal.measurement;

import java.io.IOException;

public final class zzzj
{
  public static final int[] zzcax = new int[0];
  private static final int zzcfn = 11;
  private static final int zzcfo = 12;
  private static final int zzcfp = 16;
  private static final int zzcfq = 26;
  public static final long[] zzcfr = new long[0];
  private static final float[] zzcfs = new float[0];
  private static final double[] zzcft = new double[0];
  private static final boolean[] zzcfu = new boolean[0];
  public static final String[] zzcfv = new String[0];
  private static final byte[][] zzcfw = new byte[0][];
  public static final byte[] zzcfx = new byte[0];
  
  public static final int add(zzyx paramZzyx, int paramInt)
    throws IOException
  {
    int j = paramZzyx.getPosition();
    paramZzyx.zzao(paramInt);
    int i = 1;
    while (paramZzyx.zzug() == paramInt)
    {
      paramZzyx.zzao(paramInt);
      i += 1;
    }
    paramZzyx.readTag(j, paramInt);
    return i;
  }
}
