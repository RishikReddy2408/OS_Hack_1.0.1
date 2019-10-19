package android.support.v4.app;

import android.app.Notification;
import android.app.Notification.Action.Builder;
import android.app.Notification.Builder;
import android.app.PendingIntent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.RestrictTo;
import android.text.TextUtils;
import android.util.SparseArray;
import android.widget.RemoteViews;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
class NotificationCompatBuilder
  implements NotificationBuilderWithBuilderAccessor
{
  private final List<Bundle> mActionExtrasList = new ArrayList();
  private RemoteViews mBigContentView;
  private final Notification.Builder mBuilder;
  private final NotificationCompat.Builder mBuilderCompat;
  private RemoteViews mContentView;
  private final Bundle mExtras = new Bundle();
  private int mGroupAlertBehavior;
  private RemoteViews mHeadsUpContentView;
  
  NotificationCompatBuilder(NotificationCompat.Builder paramBuilder)
  {
    mBuilderCompat = paramBuilder;
    if (Build.VERSION.SDK_INT >= 26) {
      mBuilder = new Notification.Builder(mContext, mChannelId);
    } else {
      mBuilder = new Notification.Builder(mContext);
    }
    Object localObject1 = mNotification;
    Object localObject2 = mBuilder.setWhen(when).setSmallIcon(icon, iconLevel).setContent(contentView).setTicker(tickerText, mTickerView).setVibrate(vibrate).setLights(ledARGB, ledOnMS, ledOffMS);
    boolean bool;
    if ((flags & 0x2) != 0) {
      bool = true;
    } else {
      bool = false;
    }
    localObject2 = ((Notification.Builder)localObject2).setOngoing(bool);
    if ((flags & 0x8) != 0) {
      bool = true;
    } else {
      bool = false;
    }
    localObject2 = ((Notification.Builder)localObject2).setOnlyAlertOnce(bool);
    if ((flags & 0x10) != 0) {
      bool = true;
    } else {
      bool = false;
    }
    localObject2 = ((Notification.Builder)localObject2).setAutoCancel(bool).setDefaults(defaults).setContentTitle(mContentTitle).setContentText(mContentText).setContentInfo(mContentInfo).setContentIntent(mContentIntent).setDeleteIntent(deleteIntent);
    PendingIntent localPendingIntent = mFullScreenIntent;
    if ((flags & 0x80) != 0) {
      bool = true;
    } else {
      bool = false;
    }
    ((Notification.Builder)localObject2).setFullScreenIntent(localPendingIntent, bool).setLargeIcon(mLargeIcon).setNumber(mNumber).setProgress(mProgressMax, mProgress, mProgressIndeterminate);
    if (Build.VERSION.SDK_INT < 21) {
      mBuilder.setSound(sound, audioStreamType);
    }
    if (Build.VERSION.SDK_INT >= 16)
    {
      mBuilder.setSubText(mSubText).setUsesChronometer(mUseChronometer).setPriority(mPriority);
      localObject2 = mActions.iterator();
      while (((Iterator)localObject2).hasNext()) {
        addAction((NotificationCompat.Action)((Iterator)localObject2).next());
      }
      if (mExtras != null) {
        mExtras.putAll(mExtras);
      }
      if (Build.VERSION.SDK_INT < 20)
      {
        if (mLocalOnly) {
          mExtras.putBoolean("android.support.localOnly", true);
        }
        if (mGroupKey != null)
        {
          mExtras.putString("android.support.groupKey", mGroupKey);
          if (mGroupSummary) {
            mExtras.putBoolean("android.support.isGroupSummary", true);
          } else {
            mExtras.putBoolean("android.support.useSideChannel", true);
          }
        }
        if (mSortKey != null) {
          mExtras.putString("android.support.sortKey", mSortKey);
        }
      }
      mContentView = mContentView;
      mBigContentView = mBigContentView;
    }
    if (Build.VERSION.SDK_INT >= 19)
    {
      mBuilder.setShowWhen(mShowWhen);
      if ((Build.VERSION.SDK_INT < 21) && (mPeople != null) && (!mPeople.isEmpty())) {
        mExtras.putStringArray("android.people", (String[])mPeople.toArray(new String[mPeople.size()]));
      }
    }
    if (Build.VERSION.SDK_INT >= 20)
    {
      mBuilder.setLocalOnly(mLocalOnly).setGroup(mGroupKey).setGroupSummary(mGroupSummary).setSortKey(mSortKey);
      mGroupAlertBehavior = mGroupAlertBehavior;
    }
    if (Build.VERSION.SDK_INT >= 21)
    {
      mBuilder.setCategory(mCategory).setColor(mColor).setVisibility(mVisibility).setPublicVersion(mPublicVersion).setSound(sound, audioAttributes);
      localObject1 = mPeople.iterator();
      while (((Iterator)localObject1).hasNext())
      {
        localObject2 = (String)((Iterator)localObject1).next();
        mBuilder.addPerson((String)localObject2);
      }
      mHeadsUpContentView = mHeadsUpContentView;
    }
    if (Build.VERSION.SDK_INT >= 24)
    {
      mBuilder.setExtras(mExtras).setRemoteInputHistory(mRemoteInputHistory);
      if (mContentView != null) {
        mBuilder.setCustomContentView(mContentView);
      }
      if (mBigContentView != null) {
        mBuilder.setCustomBigContentView(mBigContentView);
      }
      if (mHeadsUpContentView != null) {
        mBuilder.setCustomHeadsUpContentView(mHeadsUpContentView);
      }
    }
    if (Build.VERSION.SDK_INT >= 26)
    {
      mBuilder.setBadgeIconType(mBadgeIcon).setShortcutId(mShortcutId).setTimeoutAfter(mTimeout).setGroupAlertBehavior(mGroupAlertBehavior);
      if (mColorizedSet) {
        mBuilder.setColorized(mColorized);
      }
      if (!TextUtils.isEmpty(mChannelId)) {
        mBuilder.setSound(null).setDefaults(0).setLights(0, 0, 0).setVibrate(null);
      }
    }
  }
  
  private void addAction(NotificationCompat.Action paramAction)
  {
    if (Build.VERSION.SDK_INT >= 20)
    {
      Notification.Action.Builder localBuilder = new Notification.Action.Builder(paramAction.getIcon(), paramAction.getTitle(), paramAction.getActionIntent());
      Object localObject;
      if (paramAction.getRemoteInputs() != null)
      {
        localObject = RemoteInput.fromCompat(paramAction.getRemoteInputs());
        int j = localObject.length;
        int i = 0;
        while (i < j)
        {
          localBuilder.addRemoteInput(localObject[i]);
          i += 1;
        }
      }
      if (paramAction.getExtras() != null) {
        localObject = new Bundle(paramAction.getExtras());
      } else {
        localObject = new Bundle();
      }
      ((Bundle)localObject).putBoolean("android.support.allowGeneratedReplies", paramAction.getAllowGeneratedReplies());
      if (Build.VERSION.SDK_INT >= 24) {
        localBuilder.setAllowGeneratedReplies(paramAction.getAllowGeneratedReplies());
      }
      localBuilder.addExtras((Bundle)localObject);
      mBuilder.addAction(localBuilder.build());
      return;
    }
    if (Build.VERSION.SDK_INT >= 16) {
      mActionExtrasList.add(NotificationCompatJellybean.writeActionAndGetExtras(mBuilder, paramAction));
    }
  }
  
  private void removeSoundAndVibration(Notification paramNotification)
  {
    sound = null;
    vibrate = null;
    defaults &= 0xFFFFFFFE;
    defaults &= 0xFFFFFFFD;
  }
  
  public Notification build()
  {
    NotificationCompat.Style localStyle = mBuilderCompat.mStyle;
    if (localStyle != null) {
      localStyle.apply(this);
    }
    Object localObject;
    if (localStyle != null) {
      localObject = localStyle.makeContentView(this);
    } else {
      localObject = null;
    }
    Notification localNotification = buildInternal();
    if (localObject != null) {
      contentView = ((RemoteViews)localObject);
    } else if (mBuilderCompat.mContentView != null) {
      contentView = mBuilderCompat.mContentView;
    }
    if ((Build.VERSION.SDK_INT >= 16) && (localStyle != null))
    {
      localObject = localStyle.makeBigContentView(this);
      if (localObject != null) {
        bigContentView = ((RemoteViews)localObject);
      }
    }
    if ((Build.VERSION.SDK_INT >= 21) && (localStyle != null))
    {
      localObject = mBuilderCompat.mStyle.makeHeadsUpContentView(this);
      if (localObject != null) {
        headsUpContentView = ((RemoteViews)localObject);
      }
    }
    if ((Build.VERSION.SDK_INT >= 16) && (localStyle != null))
    {
      localObject = NotificationCompat.getExtras(localNotification);
      if (localObject != null) {
        localStyle.addCompatExtras((Bundle)localObject);
      }
    }
    return localNotification;
  }
  
  protected Notification buildInternal()
  {
    if (Build.VERSION.SDK_INT >= 26) {
      return mBuilder.build();
    }
    Object localObject1;
    if (Build.VERSION.SDK_INT >= 24)
    {
      localObject1 = mBuilder.build();
      if (mGroupAlertBehavior != 0)
      {
        if ((((Notification)localObject1).getGroup() != null) && ((flags & 0x200) != 0) && (mGroupAlertBehavior == 2)) {
          removeSoundAndVibration((Notification)localObject1);
        }
        if ((((Notification)localObject1).getGroup() != null) && ((flags & 0x200) == 0) && (mGroupAlertBehavior == 1)) {
          removeSoundAndVibration((Notification)localObject1);
        }
      }
      return localObject1;
    }
    if (Build.VERSION.SDK_INT >= 21)
    {
      mBuilder.setExtras(mExtras);
      localObject1 = mBuilder.build();
      if (mContentView != null) {
        contentView = mContentView;
      }
      if (mBigContentView != null) {
        bigContentView = mBigContentView;
      }
      if (mHeadsUpContentView != null) {
        headsUpContentView = mHeadsUpContentView;
      }
      if (mGroupAlertBehavior != 0)
      {
        if ((((Notification)localObject1).getGroup() != null) && ((flags & 0x200) != 0) && (mGroupAlertBehavior == 2)) {
          removeSoundAndVibration((Notification)localObject1);
        }
        if ((((Notification)localObject1).getGroup() != null) && ((flags & 0x200) == 0) && (mGroupAlertBehavior == 1)) {
          removeSoundAndVibration((Notification)localObject1);
        }
      }
      return localObject1;
    }
    if (Build.VERSION.SDK_INT >= 20)
    {
      mBuilder.setExtras(mExtras);
      localObject1 = mBuilder.build();
      if (mContentView != null) {
        contentView = mContentView;
      }
      if (mBigContentView != null) {
        bigContentView = mBigContentView;
      }
      if (mGroupAlertBehavior != 0)
      {
        if ((((Notification)localObject1).getGroup() != null) && ((flags & 0x200) != 0) && (mGroupAlertBehavior == 2)) {
          removeSoundAndVibration((Notification)localObject1);
        }
        if ((((Notification)localObject1).getGroup() != null) && ((flags & 0x200) == 0) && (mGroupAlertBehavior == 1)) {
          removeSoundAndVibration((Notification)localObject1);
        }
      }
      return localObject1;
    }
    if (Build.VERSION.SDK_INT >= 19)
    {
      localObject1 = NotificationCompatJellybean.buildActionExtrasMap(mActionExtrasList);
      if (localObject1 != null) {
        mExtras.putSparseParcelableArray("android.support.actionExtras", (SparseArray)localObject1);
      }
      mBuilder.setExtras(mExtras);
      localObject1 = mBuilder.build();
      if (mContentView != null) {
        contentView = mContentView;
      }
      if (mBigContentView != null) {
        bigContentView = mBigContentView;
      }
      return localObject1;
    }
    if (Build.VERSION.SDK_INT >= 16)
    {
      localObject1 = mBuilder.build();
      Object localObject2 = NotificationCompat.getExtras((Notification)localObject1);
      Bundle localBundle = new Bundle(mExtras);
      Iterator localIterator = mExtras.keySet().iterator();
      while (localIterator.hasNext())
      {
        String str = (String)localIterator.next();
        if (((Bundle)localObject2).containsKey(str)) {
          localBundle.remove(str);
        }
      }
      ((Bundle)localObject2).putAll(localBundle);
      localObject2 = NotificationCompatJellybean.buildActionExtrasMap(mActionExtrasList);
      if (localObject2 != null) {
        NotificationCompat.getExtras((Notification)localObject1).putSparseParcelableArray("android.support.actionExtras", (SparseArray)localObject2);
      }
      if (mContentView != null) {
        contentView = mContentView;
      }
      if (mBigContentView != null) {
        bigContentView = mBigContentView;
      }
      return localObject1;
    }
    return mBuilder.getNotification();
  }
  
  public Notification.Builder getBuilder()
  {
    return mBuilder;
  }
}
