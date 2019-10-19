package com.google.android.android.measurement.internal;

import android.text.TextUtils;
import com.google.android.android.common.internal.Preconditions;

final class class_4
{
  private final zzbt zzadj;
  private long zzadt;
  private String zzafw;
  private String zzafx;
  private String zzafy;
  private String zzafz;
  private long zzaga;
  private long zzagb;
  private long zzagc;
  private long zzagd;
  private String zzage;
  private long zzagf;
  private boolean zzagg;
  private long zzagh;
  private boolean zzagi;
  private boolean zzagj;
  private String zzagk;
  private long zzagl;
  private long zzagm;
  private long zzagn;
  private long zzago;
  private long zzagp;
  private long zzagq;
  private String zzagr;
  private boolean zzags;
  private long zzagt;
  private long zzagu;
  private String zzts;
  private final String zztt;
  
  class_4(zzbt paramZzbt, String paramString)
  {
    Preconditions.checkNotNull(paramZzbt);
    Preconditions.checkNotEmpty(paramString);
    zzadj = paramZzbt;
    zztt = paramString;
    zzadj.zzgn().zzaf();
  }
  
  public final void addSuffix(long paramLong)
  {
    zzadj.zzgn().zzaf();
    boolean bool2 = zzags;
    boolean bool1;
    if (zzagd != paramLong) {
      bool1 = true;
    } else {
      bool1 = false;
    }
    zzags = (bool2 | bool1);
    zzagd = paramLong;
  }
  
  public final void blur(boolean paramBoolean)
  {
    zzadj.zzgn().zzaf();
    boolean bool;
    if (zzagj != paramBoolean) {
      bool = true;
    } else {
      bool = false;
    }
    zzags = bool;
    zzagj = paramBoolean;
  }
  
  public final void checkForConnection(long paramLong)
  {
    zzadj.zzgn().zzaf();
    boolean bool2 = zzags;
    boolean bool1;
    if (zzadt != paramLong) {
      bool1 = true;
    } else {
      bool1 = false;
    }
    zzags = (bool2 | bool1);
    zzadt = paramLong;
  }
  
  public final void findAll(long paramLong)
  {
    zzadj.zzgn().zzaf();
    boolean bool2 = zzags;
    boolean bool1;
    if (zzagb != paramLong) {
      bool1 = true;
    } else {
      bool1 = false;
    }
    zzags = (bool2 | bool1);
    zzagb = paramLong;
  }
  
  public final String getAppInstanceId()
  {
    zzadj.zzgn().zzaf();
    return zzafw;
  }
  
  public final String getFirebaseInstanceId()
  {
    zzadj.zzgn().zzaf();
    return zzafz;
  }
  
  public final String getGmpAppId()
  {
    zzadj.zzgn().zzaf();
    return zzafx;
  }
  
  public final boolean isMeasurementEnabled()
  {
    zzadj.zzgn().zzaf();
    return zzagg;
  }
  
  public final void refreshScreen(long paramLong)
  {
    zzadj.zzgn().zzaf();
    boolean bool2 = zzags;
    boolean bool1;
    if (zzagu != paramLong) {
      bool1 = true;
    } else {
      bool1 = false;
    }
    zzags = (bool2 | bool1);
    zzagu = paramLong;
  }
  
  public final void removeOldest(long paramLong)
  {
    zzadj.zzgn().zzaf();
    boolean bool2 = zzags;
    boolean bool1;
    if (zzagc != paramLong) {
      bool1 = true;
    } else {
      bool1 = false;
    }
    zzags = (bool2 | bool1);
    zzagc = paramLong;
  }
  
  public final void setAppVersion(String paramString)
  {
    zzadj.zzgn().zzaf();
    zzags |= zzfk.verifySignature(zzts, paramString) ^ true;
    zzts = paramString;
  }
  
  public final void setMeasurementEnabled(boolean paramBoolean)
  {
    zzadj.zzgn().zzaf();
    boolean bool2 = zzags;
    boolean bool1;
    if (zzagg != paramBoolean) {
      bool1 = true;
    } else {
      bool1 = false;
    }
    zzags = (bool2 | bool1);
    zzagg = paramBoolean;
  }
  
  public final void trim(long paramLong)
  {
    boolean bool1 = false;
    if (paramLong >= 0L) {
      bool2 = true;
    } else {
      bool2 = false;
    }
    Preconditions.checkArgument(bool2);
    zzadj.zzgn().zzaf();
    boolean bool2 = zzags;
    if (zzaga != paramLong) {
      bool1 = true;
    }
    zzags = (bool1 | bool2);
    zzaga = paramLong;
  }
  
  public final void tryEmit(long paramLong)
  {
    zzadj.zzgn().zzaf();
    boolean bool2 = zzags;
    boolean bool1;
    if (zzagf != paramLong) {
      bool1 = true;
    } else {
      bool1 = false;
    }
    zzags = (bool2 | bool1);
    zzagf = paramLong;
  }
  
  public final void updateButton(boolean paramBoolean)
  {
    zzadj.zzgn().zzaf();
    boolean bool;
    if (zzagi != paramBoolean) {
      bool = true;
    } else {
      bool = false;
    }
    zzags = bool;
    zzagi = paramBoolean;
  }
  
  public final void writeLong(long paramLong)
  {
    zzadj.zzgn().zzaf();
    boolean bool2 = zzags;
    boolean bool1;
    if (zzagt != paramLong) {
      bool1 = true;
    } else {
      bool1 = false;
    }
    zzags = (bool2 | bool1);
    zzagt = paramLong;
  }
  
  public final void zzaa(long paramLong)
  {
    zzadj.zzgn().zzaf();
    boolean bool2 = zzags;
    boolean bool1;
    if (zzagl != paramLong) {
      bool1 = true;
    } else {
      bool1 = false;
    }
    zzags = (bool2 | bool1);
    zzagl = paramLong;
  }
  
  public final void zzab(long paramLong)
  {
    zzadj.zzgn().zzaf();
    boolean bool2 = zzags;
    boolean bool1;
    if (zzagm != paramLong) {
      bool1 = true;
    } else {
      bool1 = false;
    }
    zzags = (bool2 | bool1);
    zzagm = paramLong;
  }
  
  public final void zzac(long paramLong)
  {
    zzadj.zzgn().zzaf();
    boolean bool2 = zzags;
    boolean bool1;
    if (zzagn != paramLong) {
      bool1 = true;
    } else {
      bool1 = false;
    }
    zzags = (bool2 | bool1);
    zzagn = paramLong;
  }
  
  public final void zzad(long paramLong)
  {
    zzadj.zzgn().zzaf();
    boolean bool2 = zzags;
    boolean bool1;
    if (zzago != paramLong) {
      bool1 = true;
    } else {
      bool1 = false;
    }
    zzags = (bool2 | bool1);
    zzago = paramLong;
  }
  
  public final void zzae(long paramLong)
  {
    zzadj.zzgn().zzaf();
    boolean bool2 = zzags;
    boolean bool1;
    if (zzagq != paramLong) {
      bool1 = true;
    } else {
      bool1 = false;
    }
    zzags = (bool2 | bool1);
    zzagq = paramLong;
  }
  
  public final void zzaf(long paramLong)
  {
    zzadj.zzgn().zzaf();
    boolean bool2 = zzags;
    boolean bool1;
    if (zzagp != paramLong) {
      bool1 = true;
    } else {
      bool1 = false;
    }
    zzags = (bool2 | bool1);
    zzagp = paramLong;
  }
  
  public final void zzag(long paramLong)
  {
    zzadj.zzgn().zzaf();
    boolean bool2 = zzags;
    boolean bool1;
    if (zzagh != paramLong) {
      bool1 = true;
    } else {
      bool1 = false;
    }
    zzags = (bool2 | bool1);
    zzagh = paramLong;
  }
  
  public final String zzak()
  {
    zzadj.zzgn().zzaf();
    return zzts;
  }
  
  public final String zzal()
  {
    zzadj.zzgn().zzaf();
    return zztt;
  }
  
  public final void zzam(String paramString)
  {
    zzadj.zzgn().zzaf();
    zzags |= zzfk.verifySignature(zzafw, paramString) ^ true;
    zzafw = paramString;
  }
  
  public final void zzan(String paramString)
  {
    zzadj.zzgn().zzaf();
    String str = paramString;
    if (TextUtils.isEmpty(paramString)) {
      str = null;
    }
    zzags |= zzfk.verifySignature(zzafx, str) ^ true;
    zzafx = str;
  }
  
  public final void zzao(String paramString)
  {
    zzadj.zzgn().zzaf();
    String str = paramString;
    if (TextUtils.isEmpty(paramString)) {
      str = null;
    }
    zzags |= zzfk.verifySignature(zzagk, str) ^ true;
    zzagk = str;
  }
  
  public final void zzap(String paramString)
  {
    zzadj.zzgn().zzaf();
    zzags |= zzfk.verifySignature(zzafy, paramString) ^ true;
    zzafy = paramString;
  }
  
  public final void zzaq(String paramString)
  {
    zzadj.zzgn().zzaf();
    zzags |= zzfk.verifySignature(zzafz, paramString) ^ true;
    zzafz = paramString;
  }
  
  public final void zzar(String paramString)
  {
    zzadj.zzgn().zzaf();
    zzags |= zzfk.verifySignature(zzage, paramString) ^ true;
    zzage = paramString;
  }
  
  public final void zzas(String paramString)
  {
    zzadj.zzgn().zzaf();
    zzags |= zzfk.verifySignature(zzagr, paramString) ^ true;
    zzagr = paramString;
  }
  
  public final void zzgv()
  {
    zzadj.zzgn().zzaf();
    zzags = false;
  }
  
  public final String zzgw()
  {
    zzadj.zzgn().zzaf();
    return zzagk;
  }
  
  public final String zzgx()
  {
    zzadj.zzgn().zzaf();
    return zzafy;
  }
  
  public final long zzgy()
  {
    zzadj.zzgn().zzaf();
    return zzagb;
  }
  
  public final long zzgz()
  {
    zzadj.zzgn().zzaf();
    return zzagc;
  }
  
  public final long zzha()
  {
    zzadj.zzgn().zzaf();
    return zzagd;
  }
  
  public final String zzhb()
  {
    zzadj.zzgn().zzaf();
    return zzage;
  }
  
  public final long zzhc()
  {
    zzadj.zzgn().zzaf();
    return zzadt;
  }
  
  public final long zzhd()
  {
    zzadj.zzgn().zzaf();
    return zzagf;
  }
  
  public final long zzhe()
  {
    zzadj.zzgn().zzaf();
    return zzaga;
  }
  
  public final long zzhf()
  {
    zzadj.zzgn().zzaf();
    return zzagt;
  }
  
  public final long zzhg()
  {
    zzadj.zzgn().zzaf();
    return zzagu;
  }
  
  public final void zzhh()
  {
    zzadj.zzgn().zzaf();
    long l2 = zzaga + 1L;
    long l1 = l2;
    if (l2 > 2147483647L)
    {
      zzadj.zzgo().zzjg().append("Bundle index overflow. appId", zzap.zzbv(zztt));
      l1 = 0L;
    }
    zzags = true;
    zzaga = l1;
  }
  
  public final long zzhi()
  {
    zzadj.zzgn().zzaf();
    return zzagl;
  }
  
  public final long zzhj()
  {
    zzadj.zzgn().zzaf();
    return zzagm;
  }
  
  public final long zzhk()
  {
    zzadj.zzgn().zzaf();
    return zzagn;
  }
  
  public final long zzhl()
  {
    zzadj.zzgn().zzaf();
    return zzago;
  }
  
  public final long zzhm()
  {
    zzadj.zzgn().zzaf();
    return zzagq;
  }
  
  public final long zzhn()
  {
    zzadj.zzgn().zzaf();
    return zzagp;
  }
  
  public final String zzho()
  {
    zzadj.zzgn().zzaf();
    return zzagr;
  }
  
  public final String zzhp()
  {
    zzadj.zzgn().zzaf();
    String str = zzagr;
    zzas(null);
    return str;
  }
  
  public final long zzhq()
  {
    zzadj.zzgn().zzaf();
    return zzagh;
  }
  
  public final boolean zzhr()
  {
    zzadj.zzgn().zzaf();
    return zzagi;
  }
  
  public final boolean zzhs()
  {
    zzadj.zzgn().zzaf();
    return zzagj;
  }
}
