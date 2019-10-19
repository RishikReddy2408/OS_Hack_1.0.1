package com.google.android.android.internal.measurement;

import java.io.IOException;
import java.util.List;
import java.util.Map;

abstract interface zzxi
{
  public abstract void add(List paramList)
    throws IOException;
  
  public abstract void addSection(List paramList)
    throws IOException;
  
  public abstract void blur(List paramList)
    throws IOException;
  
  public abstract void copyTo(List paramList)
    throws IOException;
  
  public abstract Object get(zzxj paramZzxj, zzuz paramZzuz)
    throws IOException;
  
  public abstract void getBytes(List paramList)
    throws IOException;
  
  public abstract void getNumber(List paramList)
    throws IOException;
  
  public abstract int getTag();
  
  public abstract void initBook(List paramList)
    throws IOException;
  
  public abstract void intersect(List paramList)
    throws IOException;
  
  public abstract void offset(List paramList)
    throws IOException;
  
  public abstract void processWays(List paramList)
    throws IOException;
  
  public abstract Object read(zzxj paramZzxj, zzuz paramZzuz)
    throws IOException;
  
  public abstract void read(List paramList)
    throws IOException;
  
  public abstract void readDocument(List paramList, zzxj paramZzxj, zzuz paramZzuz)
    throws IOException;
  
  public abstract double readDouble()
    throws IOException;
  
  public abstract float readFloat()
    throws IOException;
  
  public abstract void readFromParcel(List paramList)
    throws IOException;
  
  public abstract String readString()
    throws IOException;
  
  public abstract void readStringList(List paramList)
    throws IOException;
  
  public abstract void replace(List paramList)
    throws IOException;
  
  public abstract void replaceAll(List paramList)
    throws IOException;
  
  public abstract void toFile(List paramList)
    throws IOException;
  
  public abstract void toFile(List paramList, zzxj paramZzxj, zzuz paramZzuz)
    throws IOException;
  
  public abstract void upgradeDatabase(List paramList)
    throws IOException;
  
  public abstract void writeValue(Map paramMap, zzwm paramZzwm, zzuz paramZzuz)
    throws IOException;
  
  public abstract long zzuh()
    throws IOException;
  
  public abstract long zzui()
    throws IOException;
  
  public abstract int zzuj()
    throws IOException;
  
  public abstract long zzuk()
    throws IOException;
  
  public abstract int zzul()
    throws IOException;
  
  public abstract boolean zzum()
    throws IOException;
  
  public abstract String zzun()
    throws IOException;
  
  public abstract zzud zzuo()
    throws IOException;
  
  public abstract int zzup()
    throws IOException;
  
  public abstract int zzuq()
    throws IOException;
  
  public abstract int zzur()
    throws IOException;
  
  public abstract long zzus()
    throws IOException;
  
  public abstract int zzut()
    throws IOException;
  
  public abstract long zzuu()
    throws IOException;
  
  public abstract int zzve()
    throws IOException;
  
  public abstract boolean zzvf()
    throws IOException;
}
