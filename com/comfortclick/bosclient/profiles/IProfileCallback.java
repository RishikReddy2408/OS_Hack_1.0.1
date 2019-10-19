package com.comfortclick.bosclient.profiles;

public abstract interface IProfileCallback
{
  public abstract void connectToAddress(String paramString);
  
  public abstract void disconnect();
  
  public abstract String getCloudUrl();
  
  public abstract void updateProfile(Profile paramProfile);
}
