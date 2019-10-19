package android.support.v4.content.pm;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageItemInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutInfo.Builder;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.IconCompat;
import android.text.TextUtils;
import java.util.Arrays;

public class ShortcutInfoCompat
{
  private ComponentName mActivity;
  private Context mContext;
  private CharSequence mDisabledMessage;
  private IconCompat mIcon;
  private Intent[] mIntents;
  private boolean mIsAlwaysBadged;
  private CharSequence mLabel;
  private CharSequence mLongLabel;
  private String packageName;
  
  private ShortcutInfoCompat() {}
  
  Intent addToIntent(Intent paramIntent)
  {
    paramIntent.putExtra("android.intent.extra.shortcut.INTENT", mIntents[(mIntents.length - 1)]).putExtra("android.intent.extra.shortcut.NAME", mLabel.toString());
    if (mIcon != null)
    {
      Object localObject3 = null;
      Object localObject4 = null;
      PackageManager localPackageManager;
      Object localObject1;
      if (mIsAlwaysBadged)
      {
        localPackageManager = mContext.getPackageManager();
        localObject1 = localObject4;
        if (mActivity != null) {
          localObject1 = mActivity;
        }
      }
      try
      {
        localObject1 = localPackageManager.getActivityIcon((ComponentName)localObject1);
        localObject3 = localObject1;
        if (localObject1 == null) {
          localObject3 = mContext.getApplicationInfo().loadIcon(localPackageManager);
        }
        mIcon.addToShortcutIntent(paramIntent, (Drawable)localObject3);
        return paramIntent;
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
        for (;;)
        {
          Object localObject2 = localObject4;
        }
      }
    }
    return paramIntent;
  }
  
  public ComponentName getActivity()
  {
    return mActivity;
  }
  
  public CharSequence getDisabledMessage()
  {
    return mDisabledMessage;
  }
  
  public String getId()
  {
    return packageName;
  }
  
  public Intent getIntent()
  {
    return mIntents[(mIntents.length - 1)];
  }
  
  public Intent[] getIntents()
  {
    return (Intent[])Arrays.copyOf(mIntents, mIntents.length);
  }
  
  public CharSequence getLongLabel()
  {
    return mLongLabel;
  }
  
  public CharSequence getShortLabel()
  {
    return mLabel;
  }
  
  public ShortcutInfo toShortcutInfo()
  {
    ShortcutInfo.Builder localBuilder = new ShortcutInfo.Builder(mContext, packageName).setShortLabel(mLabel).setIntents(mIntents);
    if (mIcon != null) {
      localBuilder.setIcon(mIcon.toIcon());
    }
    if (!TextUtils.isEmpty(mLongLabel)) {
      localBuilder.setLongLabel(mLongLabel);
    }
    if (!TextUtils.isEmpty(mDisabledMessage)) {
      localBuilder.setDisabledMessage(mDisabledMessage);
    }
    if (mActivity != null) {
      localBuilder.setActivity(mActivity);
    }
    return localBuilder.build();
  }
  
  public static class Builder
  {
    private final ShortcutInfoCompat mInfo = new ShortcutInfoCompat(null);
    
    public Builder(Context paramContext, String paramString)
    {
      ShortcutInfoCompat.access$102(mInfo, paramContext);
      ShortcutInfoCompat.access$202(mInfo, paramString);
    }
    
    public ShortcutInfoCompat build()
    {
      if (!TextUtils.isEmpty(mInfo.mLabel))
      {
        if ((mInfo.mIntents != null) && (mInfo.mIntents.length != 0)) {
          return mInfo;
        }
        throw new IllegalArgumentException("Shortcut much have an intent");
      }
      throw new IllegalArgumentException("Shortcut much have a non-empty label");
    }
    
    public Builder setActivity(ComponentName paramComponentName)
    {
      ShortcutInfoCompat.access$802(mInfo, paramComponentName);
      return this;
    }
    
    public Builder setAlwaysBadged()
    {
      ShortcutInfoCompat.access$902(mInfo, true);
      return this;
    }
    
    public Builder setDisabledMessage(CharSequence paramCharSequence)
    {
      ShortcutInfoCompat.access$502(mInfo, paramCharSequence);
      return this;
    }
    
    public Builder setIcon(IconCompat paramIconCompat)
    {
      ShortcutInfoCompat.access$702(mInfo, paramIconCompat);
      return this;
    }
    
    public Builder setIntent(Intent paramIntent)
    {
      return setIntents(new Intent[] { paramIntent });
    }
    
    public Builder setIntents(Intent[] paramArrayOfIntent)
    {
      ShortcutInfoCompat.access$602(mInfo, paramArrayOfIntent);
      return this;
    }
    
    public Builder setLongLabel(CharSequence paramCharSequence)
    {
      ShortcutInfoCompat.access$402(mInfo, paramCharSequence);
      return this;
    }
    
    public Builder setShortLabel(CharSequence paramCharSequence)
    {
      ShortcutInfoCompat.access$302(mInfo, paramCharSequence);
      return this;
    }
  }
}
