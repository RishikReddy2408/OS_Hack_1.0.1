package android.support.v4.package_7;

import android.app.Notification;
import android.app.Notification.Action;
import android.app.Notification.Action.Builder;
import android.app.Notification.BigPictureStyle;
import android.app.Notification.BigTextStyle;
import android.app.Notification.Builder;
import android.app.Notification.DecoratedCustomViewStyle;
import android.app.Notification.InboxStyle;
import android.app.Notification.MessagingStyle;
import android.app.Notification.MessagingStyle.Message;
import android.app.PendingIntent;
import android.app.RemoteInput.Builder;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.media.AudioAttributes.Builder;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.SystemClock;
import android.support.annotation.ColorInt;
import android.support.annotation.RestrictTo;
import android.support.compat.R.color;
import android.support.compat.R.dimen;
import android.support.compat.R.drawable;
import android.support.compat.R.id;
import android.support.compat.R.integer;
import android.support.compat.R.layout;
import android.support.compat.R.string;
import android.support.v4.text.BidiFormatter;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.TextAppearanceSpan;
import android.util.SparseArray;
import android.widget.RemoteViews;
import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class NotificationCompat
{
  public static final int BADGE_ICON_LARGE = 2;
  public static final int BADGE_ICON_NONE = 0;
  public static final int BADGE_ICON_SMALL = 1;
  public static final String CATEGORY_ALARM = "alarm";
  public static final String CATEGORY_CALL = "call";
  public static final String CATEGORY_EMAIL = "email";
  public static final String CATEGORY_ERROR = "err";
  public static final String CATEGORY_EVENT = "event";
  public static final String CATEGORY_MESSAGE = "msg";
  public static final String CATEGORY_PROGRESS = "progress";
  public static final String CATEGORY_PROMO = "promo";
  public static final String CATEGORY_RECOMMENDATION = "recommendation";
  public static final String CATEGORY_REMINDER = "reminder";
  public static final String CATEGORY_SERVICE = "service";
  public static final String CATEGORY_SOCIAL = "social";
  public static final String CATEGORY_STATUS = "status";
  public static final String CATEGORY_SYSTEM = "sys";
  public static final String CATEGORY_TRANSPORT = "transport";
  @ColorInt
  public static final int COLOR_DEFAULT = 0;
  public static final int DEFAULT_ALL = -1;
  public static final int DEFAULT_LIGHTS = 4;
  public static final int DEFAULT_SOUND = 1;
  public static final int DEFAULT_VIBRATE = 2;
  public static final String EXTRA_AUDIO_CONTENTS_URI = "android.audioContents";
  public static final String EXTRA_BACKGROUND_IMAGE_URI = "android.backgroundImageUri";
  public static final String EXTRA_BIG_TEXT = "android.bigText";
  public static final String EXTRA_COMPACT_ACTIONS = "android.compactActions";
  public static final String EXTRA_CONVERSATION_TITLE = "android.conversationTitle";
  public static final String EXTRA_INFO_TEXT = "android.infoText";
  public static final String EXTRA_LARGE_ICON = "android.largeIcon";
  public static final String EXTRA_LARGE_ICON_BIG = "android.largeIcon.big";
  public static final String EXTRA_MEDIA_SESSION = "android.mediaSession";
  public static final String EXTRA_MESSAGES = "android.messages";
  public static final String EXTRA_PEOPLE = "android.people";
  public static final String EXTRA_PICTURE = "android.picture";
  public static final String EXTRA_PROGRESS = "android.progress";
  public static final String EXTRA_PROGRESS_INDETERMINATE = "android.progressIndeterminate";
  public static final String EXTRA_PROGRESS_MAX = "android.progressMax";
  public static final String EXTRA_REMOTE_INPUT_HISTORY = "android.remoteInputHistory";
  public static final String EXTRA_SELF_DISPLAY_NAME = "android.selfDisplayName";
  public static final String EXTRA_SHOW_CHRONOMETER = "android.showChronometer";
  public static final String EXTRA_SHOW_WHEN = "android.showWhen";
  public static final String EXTRA_SMALL_ICON = "android.icon";
  public static final String EXTRA_SUB_TEXT = "android.subText";
  public static final String EXTRA_SUMMARY_TEXT = "android.summaryText";
  public static final String EXTRA_TEMPLATE = "android.template";
  public static final String EXTRA_TEXT = "android.text";
  public static final String EXTRA_TEXT_LINES = "android.textLines";
  public static final String EXTRA_TITLE = "android.title";
  public static final String EXTRA_TITLE_BIG = "android.title.big";
  public static final int FLAG_AUTO_CANCEL = 16;
  public static final int FLAG_FOREGROUND_SERVICE = 64;
  public static final int FLAG_GROUP_SUMMARY = 512;
  @Deprecated
  public static final int FLAG_HIGH_PRIORITY = 128;
  public static final int FLAG_INSISTENT = 4;
  public static final int FLAG_LOCAL_ONLY = 256;
  public static final int FLAG_NO_CLEAR = 32;
  public static final int FLAG_ONGOING_EVENT = 2;
  public static final int FLAG_ONLY_ALERT_ONCE = 8;
  public static final int FLAG_SHOW_LIGHTS = 1;
  public static final int GROUP_ALERT_ALL = 0;
  public static final int GROUP_ALERT_CHILDREN = 2;
  public static final int GROUP_ALERT_SUMMARY = 1;
  public static final int PRIORITY_DEFAULT = 0;
  public static final int PRIORITY_HIGH = 1;
  public static final int PRIORITY_LOW = -1;
  public static final int PRIORITY_MAX = 2;
  public static final int PRIORITY_MIN = -2;
  public static final int STREAM_DEFAULT = -1;
  public static final int VISIBILITY_PRIVATE = 0;
  public static final int VISIBILITY_PUBLIC = 1;
  public static final int VISIBILITY_SECRET = -1;
  
  public NotificationCompat() {}
  
  public static Action getAction(Notification paramNotification, int paramInt)
  {
    if (Build.VERSION.SDK_INT >= 20) {
      return getActionCompatFromAction(actions[paramInt]);
    }
    int i = Build.VERSION.SDK_INT;
    Object localObject = null;
    if (i >= 19)
    {
      Notification.Action localAction = actions[paramInt];
      SparseArray localSparseArray = extras.getSparseParcelableArray("android.support.actionExtras");
      paramNotification = localObject;
      if (localSparseArray != null) {
        paramNotification = (Bundle)localSparseArray.get(paramInt);
      }
      return NotificationCompatJellybean.readAction(icon, title, actionIntent, paramNotification);
    }
    if (Build.VERSION.SDK_INT >= 16) {
      return NotificationCompatJellybean.getAction(paramNotification, paramInt);
    }
    return null;
  }
  
  static Action getActionCompatFromAction(Notification.Action paramAction)
  {
    android.app.RemoteInput[] arrayOfRemoteInput1 = paramAction.getRemoteInputs();
    boolean bool = false;
    Object localObject;
    if (arrayOfRemoteInput1 == null)
    {
      localObject = null;
    }
    else
    {
      RemoteInput[] arrayOfRemoteInput = new RemoteInput[arrayOfRemoteInput1.length];
      int i = 0;
      for (;;)
      {
        localObject = arrayOfRemoteInput;
        if (i >= arrayOfRemoteInput1.length) {
          break;
        }
        localObject = arrayOfRemoteInput1[i];
        arrayOfRemoteInput[i] = new RemoteInput(((android.app.RemoteInput)localObject).getResultKey(), ((android.app.RemoteInput)localObject).getLabel(), ((android.app.RemoteInput)localObject).getChoices(), ((android.app.RemoteInput)localObject).getAllowFreeFormInput(), ((android.app.RemoteInput)localObject).getExtras(), null);
        i += 1;
      }
    }
    if (Build.VERSION.SDK_INT >= 24)
    {
      if ((paramAction.getExtras().getBoolean("android.support.allowGeneratedReplies")) || (paramAction.getAllowGeneratedReplies())) {
        bool = true;
      }
    }
    else {
      bool = paramAction.getExtras().getBoolean("android.support.allowGeneratedReplies");
    }
    return new Action(icon, title, actionIntent, paramAction.getExtras(), (RemoteInput[])localObject, null, bool);
  }
  
  public static int getActionCount(Notification paramNotification)
  {
    if (Build.VERSION.SDK_INT >= 19)
    {
      if (actions != null) {
        return actions.length;
      }
    }
    else if (Build.VERSION.SDK_INT >= 16) {
      return NotificationCompatJellybean.getActionCount(paramNotification);
    }
    return 0;
  }
  
  public static int getBadgeIconType(Notification paramNotification)
  {
    if (Build.VERSION.SDK_INT >= 26) {
      return paramNotification.getBadgeIconType();
    }
    return 0;
  }
  
  public static String getCategory(Notification paramNotification)
  {
    if (Build.VERSION.SDK_INT >= 21) {
      return category;
    }
    return null;
  }
  
  public static String getChannelId(Notification paramNotification)
  {
    if (Build.VERSION.SDK_INT >= 26) {
      return paramNotification.getChannelId();
    }
    return null;
  }
  
  public static Bundle getExtras(Notification paramNotification)
  {
    if (Build.VERSION.SDK_INT >= 19) {
      return extras;
    }
    if (Build.VERSION.SDK_INT >= 16) {
      return NotificationCompatJellybean.getExtras(paramNotification);
    }
    return null;
  }
  
  public static String getGroup(Notification paramNotification)
  {
    if (Build.VERSION.SDK_INT >= 20) {
      return paramNotification.getGroup();
    }
    if (Build.VERSION.SDK_INT >= 19) {
      return extras.getString("android.support.groupKey");
    }
    if (Build.VERSION.SDK_INT >= 16) {
      return NotificationCompatJellybean.getExtras(paramNotification).getString("android.support.groupKey");
    }
    return null;
  }
  
  public static int getGroupAlertBehavior(Notification paramNotification)
  {
    if (Build.VERSION.SDK_INT >= 26) {
      return paramNotification.getGroupAlertBehavior();
    }
    return 0;
  }
  
  public static boolean getLocalOnly(Notification paramNotification)
  {
    if (Build.VERSION.SDK_INT >= 20)
    {
      if ((flags & 0x100) != 0) {
        return true;
      }
    }
    else
    {
      if (Build.VERSION.SDK_INT >= 19) {
        return extras.getBoolean("android.support.localOnly");
      }
      if (Build.VERSION.SDK_INT >= 16) {
        return NotificationCompatJellybean.getExtras(paramNotification).getBoolean("android.support.localOnly");
      }
    }
    return false;
  }
  
  static Notification[] getNotificationArrayFromBundle(Bundle paramBundle, String paramString)
  {
    Parcelable[] arrayOfParcelable = paramBundle.getParcelableArray(paramString);
    if ((!(arrayOfParcelable instanceof Notification[])) && (arrayOfParcelable != null))
    {
      Notification[] arrayOfNotification = new Notification[arrayOfParcelable.length];
      int i = 0;
      while (i < arrayOfParcelable.length)
      {
        arrayOfNotification[i] = ((Notification)arrayOfParcelable[i]);
        i += 1;
      }
      paramBundle.putParcelableArray(paramString, arrayOfNotification);
      return arrayOfNotification;
    }
    return (Notification[])arrayOfParcelable;
  }
  
  public static String getShortcutId(Notification paramNotification)
  {
    if (Build.VERSION.SDK_INT >= 26) {
      return paramNotification.getShortcutId();
    }
    return null;
  }
  
  public static String getSortKey(Notification paramNotification)
  {
    if (Build.VERSION.SDK_INT >= 20) {
      return paramNotification.getSortKey();
    }
    if (Build.VERSION.SDK_INT >= 19) {
      return extras.getString("android.support.sortKey");
    }
    if (Build.VERSION.SDK_INT >= 16) {
      return NotificationCompatJellybean.getExtras(paramNotification).getString("android.support.sortKey");
    }
    return null;
  }
  
  public static long getTimeoutAfter(Notification paramNotification)
  {
    if (Build.VERSION.SDK_INT >= 26) {
      return paramNotification.getTimeoutAfter();
    }
    return 0L;
  }
  
  public static boolean isGroupSummary(Notification paramNotification)
  {
    if (Build.VERSION.SDK_INT >= 20)
    {
      if ((flags & 0x200) != 0) {
        return true;
      }
    }
    else
    {
      if (Build.VERSION.SDK_INT >= 19) {
        return extras.getBoolean("android.support.isGroupSummary");
      }
      if (Build.VERSION.SDK_INT >= 16) {
        return NotificationCompatJellybean.getExtras(paramNotification).getBoolean("android.support.isGroupSummary");
      }
    }
    return false;
  }
  
  public class Action
  {
    public PendingIntent actionIntent;
    public int icon;
    private boolean mAllowGeneratedReplies;
    private final RemoteInput[] mDataOnlyRemoteInputs;
    final Bundle mExtras;
    private final RemoteInput[] mRemoteInputs;
    public CharSequence title;
    
    public Action(CharSequence paramCharSequence, PendingIntent paramPendingIntent)
    {
      this(paramCharSequence, paramPendingIntent, new Bundle(), null, null, true);
    }
    
    Action(CharSequence paramCharSequence, PendingIntent paramPendingIntent, Bundle paramBundle, RemoteInput[] paramArrayOfRemoteInput1, RemoteInput[] paramArrayOfRemoteInput2, boolean paramBoolean)
    {
      icon = this$1;
      title = NotificationCompat.Builder.limitCharSequenceLength(paramCharSequence);
      actionIntent = paramPendingIntent;
      if (paramBundle == null) {
        paramBundle = new Bundle();
      }
      mExtras = paramBundle;
      mRemoteInputs = paramArrayOfRemoteInput1;
      mDataOnlyRemoteInputs = paramArrayOfRemoteInput2;
      mAllowGeneratedReplies = paramBoolean;
    }
    
    public PendingIntent getActionIntent()
    {
      return actionIntent;
    }
    
    public boolean getAllowGeneratedReplies()
    {
      return mAllowGeneratedReplies;
    }
    
    public RemoteInput[] getDataOnlyRemoteInputs()
    {
      return mDataOnlyRemoteInputs;
    }
    
    public Bundle getExtras()
    {
      return mExtras;
    }
    
    public int getIcon()
    {
      return icon;
    }
    
    public RemoteInput[] getRemoteInputs()
    {
      return mRemoteInputs;
    }
    
    public CharSequence getTitle()
    {
      return title;
    }
    
    public final class Builder
    {
      private boolean mAllowGeneratedReplies = true;
      private final Bundle mExtras;
      private final int mIcon;
      private final PendingIntent mIntent;
      private ArrayList<android.support.v4.app.RemoteInput> mRemoteInputs;
      private final CharSequence mTitle;
      
      public Builder(CharSequence paramCharSequence, PendingIntent paramPendingIntent)
      {
        this(paramCharSequence, paramPendingIntent, new Bundle(), null, true);
      }
      
      private Builder(CharSequence paramCharSequence, PendingIntent paramPendingIntent, Bundle paramBundle, RemoteInput[] paramArrayOfRemoteInput, boolean paramBoolean)
      {
        mIcon = this$1;
        mTitle = NotificationCompat.Builder.limitCharSequenceLength(paramCharSequence);
        mIntent = paramPendingIntent;
        mExtras = paramBundle;
        if (paramArrayOfRemoteInput == null) {
          paramCharSequence = null;
        } else {
          paramCharSequence = new ArrayList(Arrays.asList(paramArrayOfRemoteInput));
        }
        mRemoteInputs = paramCharSequence;
        mAllowGeneratedReplies = paramBoolean;
      }
      
      public Builder()
      {
        this(title, actionIntent, new Bundle(mExtras), this$1.getRemoteInputs(), this$1.getAllowGeneratedReplies());
      }
      
      public Builder addExtras(Bundle paramBundle)
      {
        if (paramBundle != null) {
          mExtras.putAll(paramBundle);
        }
        return this;
      }
      
      public Builder addRemoteInput(RemoteInput paramRemoteInput)
      {
        if (mRemoteInputs == null) {
          mRemoteInputs = new ArrayList();
        }
        mRemoteInputs.add(paramRemoteInput);
        return this;
      }
      
      public NotificationCompat.Action build()
      {
        Object localObject1 = new ArrayList();
        ArrayList localArrayList = new ArrayList();
        if (mRemoteInputs != null)
        {
          localObject2 = mRemoteInputs.iterator();
          while (((Iterator)localObject2).hasNext())
          {
            RemoteInput localRemoteInput = (RemoteInput)((Iterator)localObject2).next();
            if (localRemoteInput.isDataOnly()) {
              ((List)localObject1).add(localRemoteInput);
            } else {
              localArrayList.add(localRemoteInput);
            }
          }
        }
        boolean bool = ((List)localObject1).isEmpty();
        Object localObject2 = null;
        if (bool) {
          localObject1 = null;
        } else {
          localObject1 = (RemoteInput[])((List)localObject1).toArray(new RemoteInput[((List)localObject1).size()]);
        }
        if (!localArrayList.isEmpty()) {
          for (;;)
          {
            localObject2 = (RemoteInput[])localArrayList.toArray(new RemoteInput[localArrayList.size()]);
          }
        }
        return new NotificationCompat.Action(mIcon, mTitle, mIntent, mExtras, (RemoteInput[])localObject2, (RemoteInput[])localObject1, mAllowGeneratedReplies);
      }
      
      public Builder extend(NotificationCompat.Action.Extender paramExtender)
      {
        paramExtender.extend(this);
        return this;
      }
      
      public Bundle getExtras()
      {
        return mExtras;
      }
      
      public Builder setAllowGeneratedReplies(boolean paramBoolean)
      {
        mAllowGeneratedReplies = paramBoolean;
        return this;
      }
    }
    
    public abstract interface Extender
    {
      public abstract NotificationCompat.Action.Builder extend(NotificationCompat.Action.Builder paramBuilder);
    }
    
    public final class WearableExtender
      implements NotificationCompat.Action.Extender
    {
      private static final int DEFAULT_FLAGS = 1;
      private static final String EXTRA_WEARABLE_EXTENSIONS = "android.wearable.EXTENSIONS";
      private static final int FLAG_AVAILABLE_OFFLINE = 1;
      private static final int FLAG_HINT_DISPLAY_INLINE = 4;
      private static final int FLAG_HINT_LAUNCHES_ACTIVITY = 2;
      private static final String KEY_CANCEL_LABEL = "cancelLabel";
      private static final String KEY_CONFIRM_LABEL = "confirmLabel";
      private static final String KEY_FLAGS = "flags";
      private static final String KEY_IN_PROGRESS_LABEL = "inProgressLabel";
      private CharSequence mCancelLabel;
      private CharSequence mConfirmLabel;
      private int mFlags = 1;
      private CharSequence mInProgressLabel;
      
      public WearableExtender() {}
      
      public WearableExtender()
      {
        this$1 = this$1.getExtras().getBundle("android.wearable.EXTENSIONS");
        if (this$1 != null)
        {
          mFlags = this$1.getInt("flags", 1);
          mInProgressLabel = this$1.getCharSequence("inProgressLabel");
          mConfirmLabel = this$1.getCharSequence("confirmLabel");
          mCancelLabel = this$1.getCharSequence("cancelLabel");
        }
      }
      
      private void setFlag(int paramInt, boolean paramBoolean)
      {
        if (paramBoolean)
        {
          mFlags = (paramInt | mFlags);
          return;
        }
        mFlags = (paramInt & mFlags);
      }
      
      public WearableExtender clone()
      {
        WearableExtender localWearableExtender = new WearableExtender();
        mFlags = mFlags;
        mInProgressLabel = mInProgressLabel;
        mConfirmLabel = mConfirmLabel;
        mCancelLabel = mCancelLabel;
        return localWearableExtender;
      }
      
      public NotificationCompat.Action.Builder extend(NotificationCompat.Action.Builder paramBuilder)
      {
        Bundle localBundle = new Bundle();
        if (mFlags != 1) {
          localBundle.putInt("flags", mFlags);
        }
        if (mInProgressLabel != null) {
          localBundle.putCharSequence("inProgressLabel", mInProgressLabel);
        }
        if (mConfirmLabel != null) {
          localBundle.putCharSequence("confirmLabel", mConfirmLabel);
        }
        if (mCancelLabel != null) {
          localBundle.putCharSequence("cancelLabel", mCancelLabel);
        }
        paramBuilder.getExtras().putBundle("android.wearable.EXTENSIONS", localBundle);
        return paramBuilder;
      }
      
      public CharSequence getCancelLabel()
      {
        return mCancelLabel;
      }
      
      public CharSequence getConfirmLabel()
      {
        return mConfirmLabel;
      }
      
      public boolean getHintDisplayActionInline()
      {
        return (mFlags & 0x4) != 0;
      }
      
      public boolean getHintLaunchesActivity()
      {
        return (mFlags & 0x2) != 0;
      }
      
      public CharSequence getInProgressLabel()
      {
        return mInProgressLabel;
      }
      
      public boolean isAvailableOffline()
      {
        return (mFlags & 0x1) != 0;
      }
      
      public WearableExtender setAvailableOffline(boolean paramBoolean)
      {
        setFlag(1, paramBoolean);
        return this;
      }
      
      public WearableExtender setCancelLabel(CharSequence paramCharSequence)
      {
        mCancelLabel = paramCharSequence;
        return this;
      }
      
      public WearableExtender setConfirmLabel(CharSequence paramCharSequence)
      {
        mConfirmLabel = paramCharSequence;
        return this;
      }
      
      public WearableExtender setHintDisplayActionInline(boolean paramBoolean)
      {
        setFlag(4, paramBoolean);
        return this;
      }
      
      public WearableExtender setHintLaunchesActivity(boolean paramBoolean)
      {
        setFlag(2, paramBoolean);
        return this;
      }
      
      public WearableExtender setInProgressLabel(CharSequence paramCharSequence)
      {
        mInProgressLabel = paramCharSequence;
        return this;
      }
    }
  }
  
  @Retention(RetentionPolicy.SOURCE)
  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  public @interface BadgeIconType {}
  
  public class BigPictureStyle
    extends NotificationCompat.Style
  {
    private Bitmap mBigLargeIcon;
    private boolean mBigLargeIconSet;
    private Bitmap mPicture;
    
    public BigPictureStyle() {}
    
    public BigPictureStyle()
    {
      setBuilder(this$1);
    }
    
    public void apply(NotificationBuilderWithBuilderAccessor paramNotificationBuilderWithBuilderAccessor)
    {
      if (Build.VERSION.SDK_INT >= 16)
      {
        paramNotificationBuilderWithBuilderAccessor = new Notification.BigPictureStyle(paramNotificationBuilderWithBuilderAccessor.getBuilder()).setBigContentTitle(mBigContentTitle).bigPicture(mPicture);
        if (mBigLargeIconSet) {
          paramNotificationBuilderWithBuilderAccessor.bigLargeIcon(mBigLargeIcon);
        }
        if (mSummaryTextSet) {
          paramNotificationBuilderWithBuilderAccessor.setSummaryText(mSummaryText);
        }
      }
    }
    
    public BigPictureStyle bigLargeIcon(Bitmap paramBitmap)
    {
      mBigLargeIcon = paramBitmap;
      mBigLargeIconSet = true;
      return this;
    }
    
    public BigPictureStyle bigPicture(Bitmap paramBitmap)
    {
      mPicture = paramBitmap;
      return this;
    }
    
    public BigPictureStyle setBigContentTitle(CharSequence paramCharSequence)
    {
      mBigContentTitle = NotificationCompat.Builder.limitCharSequenceLength(paramCharSequence);
      return this;
    }
    
    public BigPictureStyle setSummaryText(CharSequence paramCharSequence)
    {
      mSummaryText = NotificationCompat.Builder.limitCharSequenceLength(paramCharSequence);
      mSummaryTextSet = true;
      return this;
    }
  }
  
  public class BigTextStyle
    extends NotificationCompat.Style
  {
    private CharSequence mBigText;
    
    public BigTextStyle() {}
    
    public BigTextStyle()
    {
      setBuilder(this$1);
    }
    
    public void apply(NotificationBuilderWithBuilderAccessor paramNotificationBuilderWithBuilderAccessor)
    {
      if (Build.VERSION.SDK_INT >= 16)
      {
        paramNotificationBuilderWithBuilderAccessor = new Notification.BigTextStyle(paramNotificationBuilderWithBuilderAccessor.getBuilder()).setBigContentTitle(mBigContentTitle).bigText(mBigText);
        if (mSummaryTextSet) {
          paramNotificationBuilderWithBuilderAccessor.setSummaryText(mSummaryText);
        }
      }
    }
    
    public BigTextStyle bigText(CharSequence paramCharSequence)
    {
      mBigText = NotificationCompat.Builder.limitCharSequenceLength(paramCharSequence);
      return this;
    }
    
    public BigTextStyle setBigContentTitle(CharSequence paramCharSequence)
    {
      mBigContentTitle = NotificationCompat.Builder.limitCharSequenceLength(paramCharSequence);
      return this;
    }
    
    public BigTextStyle setSummaryText(CharSequence paramCharSequence)
    {
      mSummaryText = NotificationCompat.Builder.limitCharSequenceLength(paramCharSequence);
      mSummaryTextSet = true;
      return this;
    }
  }
  
  public class Builder
  {
    private static final int MAX_CHARSEQUENCE_LENGTH = 5120;
    @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
    public ArrayList<android.support.v4.app.NotificationCompat.Action> mActions = new ArrayList();
    int mBadgeIcon = 0;
    RemoteViews mBigContentView;
    String mCategory;
    String mChannelId;
    int mColor = 0;
    boolean mColorized;
    boolean mColorizedSet;
    CharSequence mContentInfo;
    PendingIntent mContentIntent;
    CharSequence mContentText;
    CharSequence mContentTitle;
    RemoteViews mContentView;
    Bundle mExtras;
    PendingIntent mFullScreenIntent;
    int mGroupAlertBehavior = 0;
    String mGroupKey;
    boolean mGroupSummary;
    RemoteViews mHeadsUpContentView;
    Bitmap mLargeIcon;
    boolean mLocalOnly = false;
    Notification mNotification = new Notification();
    int mNumber;
    @Deprecated
    public ArrayList<String> mPeople;
    int mPriority;
    int mProgress;
    boolean mProgressIndeterminate;
    int mProgressMax;
    Notification mPublicVersion;
    CharSequence[] mRemoteInputHistory;
    String mShortcutId;
    boolean mShowWhen = true;
    String mSortKey;
    NotificationCompat.Style mStyle;
    CharSequence mSubText;
    RemoteViews mTickerView;
    long mTimeout;
    boolean mUseChronometer;
    int mVisibility = 0;
    
    public Builder()
    {
      this(null);
    }
    
    public Builder(String paramString)
    {
      mChannelId = paramString;
      mNotification.when = System.currentTimeMillis();
      mNotification.audioStreamType = -1;
      mPriority = 0;
      mPeople = new ArrayList();
    }
    
    protected static CharSequence limitCharSequenceLength(CharSequence paramCharSequence)
    {
      if (paramCharSequence == null) {
        return paramCharSequence;
      }
      CharSequence localCharSequence = paramCharSequence;
      if (paramCharSequence.length() > 5120) {
        localCharSequence = paramCharSequence.subSequence(0, 5120);
      }
      return localCharSequence;
    }
    
    private void setFlag(int paramInt, boolean paramBoolean)
    {
      if (paramBoolean)
      {
        localNotification = mNotification;
        flags = (paramInt | flags);
        return;
      }
      Notification localNotification = mNotification;
      flags = (paramInt & flags);
    }
    
    public Builder addAction(int paramInt, CharSequence paramCharSequence, PendingIntent paramPendingIntent)
    {
      mActions.add(new NotificationCompat.Action(paramInt, paramCharSequence, paramPendingIntent));
      return this;
    }
    
    public Builder addAction(NotificationCompat.Action paramAction)
    {
      mActions.add(paramAction);
      return this;
    }
    
    public Builder addExtras(Bundle paramBundle)
    {
      if (paramBundle != null)
      {
        if (mExtras == null)
        {
          mExtras = new Bundle(paramBundle);
          return this;
        }
        mExtras.putAll(paramBundle);
      }
      return this;
    }
    
    public Builder addPerson(String paramString)
    {
      mPeople.add(paramString);
      return this;
    }
    
    public Notification build()
    {
      return new NotificationCompatBuilder(this).build();
    }
    
    public Builder extend(NotificationCompat.Extender paramExtender)
    {
      paramExtender.extend(this);
      return this;
    }
    
    public RemoteViews getBigContentView()
    {
      return mBigContentView;
    }
    
    public int getColor()
    {
      return mColor;
    }
    
    public RemoteViews getContentView()
    {
      return mContentView;
    }
    
    public Bundle getExtras()
    {
      if (mExtras == null) {
        mExtras = new Bundle();
      }
      return mExtras;
    }
    
    public RemoteViews getHeadsUpContentView()
    {
      return mHeadsUpContentView;
    }
    
    public Notification getNotification()
    {
      return build();
    }
    
    public int getPriority()
    {
      return mPriority;
    }
    
    public long getWhenIfShowing()
    {
      if (mShowWhen) {
        return mNotification.when;
      }
      return 0L;
    }
    
    public Builder setAutoCancel(boolean paramBoolean)
    {
      setFlag(16, paramBoolean);
      return this;
    }
    
    public Builder setBadgeIconType(int paramInt)
    {
      mBadgeIcon = paramInt;
      return this;
    }
    
    public Builder setCategory(String paramString)
    {
      mCategory = paramString;
      return this;
    }
    
    public Builder setChannelId(String paramString)
    {
      mChannelId = paramString;
      return this;
    }
    
    public Builder setColor(int paramInt)
    {
      mColor = paramInt;
      return this;
    }
    
    public Builder setColorized(boolean paramBoolean)
    {
      mColorized = paramBoolean;
      mColorizedSet = true;
      return this;
    }
    
    public Builder setContent(RemoteViews paramRemoteViews)
    {
      mNotification.contentView = paramRemoteViews;
      return this;
    }
    
    public Builder setContentInfo(CharSequence paramCharSequence)
    {
      mContentInfo = limitCharSequenceLength(paramCharSequence);
      return this;
    }
    
    public Builder setContentIntent(PendingIntent paramPendingIntent)
    {
      mContentIntent = paramPendingIntent;
      return this;
    }
    
    public Builder setContentText(CharSequence paramCharSequence)
    {
      mContentText = limitCharSequenceLength(paramCharSequence);
      return this;
    }
    
    public Builder setContentTitle(CharSequence paramCharSequence)
    {
      mContentTitle = limitCharSequenceLength(paramCharSequence);
      return this;
    }
    
    public Builder setCustomBigContentView(RemoteViews paramRemoteViews)
    {
      mBigContentView = paramRemoteViews;
      return this;
    }
    
    public Builder setCustomContentView(RemoteViews paramRemoteViews)
    {
      mContentView = paramRemoteViews;
      return this;
    }
    
    public Builder setCustomHeadsUpContentView(RemoteViews paramRemoteViews)
    {
      mHeadsUpContentView = paramRemoteViews;
      return this;
    }
    
    public Builder setDefaults(int paramInt)
    {
      mNotification.defaults = paramInt;
      if ((paramInt & 0x4) != 0)
      {
        Notification localNotification = mNotification;
        flags |= 0x1;
      }
      return this;
    }
    
    public Builder setDeleteIntent(PendingIntent paramPendingIntent)
    {
      mNotification.deleteIntent = paramPendingIntent;
      return this;
    }
    
    public Builder setExtras(Bundle paramBundle)
    {
      mExtras = paramBundle;
      return this;
    }
    
    public Builder setFullScreenIntent(PendingIntent paramPendingIntent, boolean paramBoolean)
    {
      mFullScreenIntent = paramPendingIntent;
      setFlag(128, paramBoolean);
      return this;
    }
    
    public Builder setGroup(String paramString)
    {
      mGroupKey = paramString;
      return this;
    }
    
    public Builder setGroupAlertBehavior(int paramInt)
    {
      mGroupAlertBehavior = paramInt;
      return this;
    }
    
    public Builder setGroupSummary(boolean paramBoolean)
    {
      mGroupSummary = paramBoolean;
      return this;
    }
    
    public Builder setLargeIcon(Bitmap paramBitmap)
    {
      mLargeIcon = paramBitmap;
      return this;
    }
    
    public Builder setLights(int paramInt1, int paramInt2, int paramInt3)
    {
      mNotification.ledARGB = paramInt1;
      mNotification.ledOnMS = paramInt2;
      mNotification.ledOffMS = paramInt3;
      if ((mNotification.ledOnMS != 0) && (mNotification.ledOffMS != 0)) {
        paramInt1 = 1;
      } else {
        paramInt1 = 0;
      }
      mNotification.flags = (paramInt1 | mNotification.flags & 0xFFFFFFFE);
      return this;
    }
    
    public Builder setLocalOnly(boolean paramBoolean)
    {
      mLocalOnly = paramBoolean;
      return this;
    }
    
    public Builder setNumber(int paramInt)
    {
      mNumber = paramInt;
      return this;
    }
    
    public Builder setOngoing(boolean paramBoolean)
    {
      setFlag(2, paramBoolean);
      return this;
    }
    
    public Builder setOnlyAlertOnce(boolean paramBoolean)
    {
      setFlag(8, paramBoolean);
      return this;
    }
    
    public Builder setPriority(int paramInt)
    {
      mPriority = paramInt;
      return this;
    }
    
    public Builder setProgress(int paramInt1, int paramInt2, boolean paramBoolean)
    {
      mProgressMax = paramInt1;
      mProgress = paramInt2;
      mProgressIndeterminate = paramBoolean;
      return this;
    }
    
    public Builder setPublicVersion(Notification paramNotification)
    {
      mPublicVersion = paramNotification;
      return this;
    }
    
    public Builder setRemoteInputHistory(CharSequence[] paramArrayOfCharSequence)
    {
      mRemoteInputHistory = paramArrayOfCharSequence;
      return this;
    }
    
    public Builder setShortcutId(String paramString)
    {
      mShortcutId = paramString;
      return this;
    }
    
    public Builder setShowWhen(boolean paramBoolean)
    {
      mShowWhen = paramBoolean;
      return this;
    }
    
    public Builder setSmallIcon(int paramInt)
    {
      mNotification.icon = paramInt;
      return this;
    }
    
    public Builder setSmallIcon(int paramInt1, int paramInt2)
    {
      mNotification.icon = paramInt1;
      mNotification.iconLevel = paramInt2;
      return this;
    }
    
    public Builder setSortKey(String paramString)
    {
      mSortKey = paramString;
      return this;
    }
    
    public Builder setSound(Uri paramUri)
    {
      mNotification.sound = paramUri;
      mNotification.audioStreamType = -1;
      if (Build.VERSION.SDK_INT >= 21) {
        mNotification.audioAttributes = new AudioAttributes.Builder().setContentType(4).setUsage(5).build();
      }
      return this;
    }
    
    public Builder setSound(Uri paramUri, int paramInt)
    {
      mNotification.sound = paramUri;
      mNotification.audioStreamType = paramInt;
      if (Build.VERSION.SDK_INT >= 21) {
        mNotification.audioAttributes = new AudioAttributes.Builder().setContentType(4).setLegacyStreamType(paramInt).build();
      }
      return this;
    }
    
    public Builder setStyle(NotificationCompat.Style paramStyle)
    {
      if (mStyle != paramStyle)
      {
        mStyle = paramStyle;
        if (mStyle != null) {
          mStyle.setBuilder(this);
        }
      }
      return this;
    }
    
    public Builder setSubText(CharSequence paramCharSequence)
    {
      mSubText = limitCharSequenceLength(paramCharSequence);
      return this;
    }
    
    public Builder setTicker(CharSequence paramCharSequence)
    {
      mNotification.tickerText = limitCharSequenceLength(paramCharSequence);
      return this;
    }
    
    public Builder setTicker(CharSequence paramCharSequence, RemoteViews paramRemoteViews)
    {
      mNotification.tickerText = limitCharSequenceLength(paramCharSequence);
      mTickerView = paramRemoteViews;
      return this;
    }
    
    public Builder setTimeoutAfter(long paramLong)
    {
      mTimeout = paramLong;
      return this;
    }
    
    public Builder setUsesChronometer(boolean paramBoolean)
    {
      mUseChronometer = paramBoolean;
      return this;
    }
    
    public Builder setVibrate(long[] paramArrayOfLong)
    {
      mNotification.vibrate = paramArrayOfLong;
      return this;
    }
    
    public Builder setVisibility(int paramInt)
    {
      mVisibility = paramInt;
      return this;
    }
    
    public Builder setWhen(long paramLong)
    {
      mNotification.when = paramLong;
      return this;
    }
  }
  
  public final class CarExtender
    implements NotificationCompat.Extender
  {
    private static final String EXTRA_CAR_EXTENDER = "android.car.EXTENSIONS";
    private static final String EXTRA_COLOR = "app_color";
    private static final String EXTRA_CONVERSATION = "car_conversation";
    private static final String EXTRA_LARGE_ICON = "large_icon";
    private static final String KEY_AUTHOR = "author";
    private static final String KEY_MESSAGES = "messages";
    private static final String KEY_ON_READ = "on_read";
    private static final String KEY_ON_REPLY = "on_reply";
    private static final String KEY_PARTICIPANTS = "participants";
    private static final String KEY_REMOTE_INPUT = "remote_input";
    private static final String KEY_TEXT = "text";
    private static final String KEY_TIMESTAMP = "timestamp";
    private int mColor = 0;
    private Bitmap mLargeIcon;
    private UnreadConversation mUnreadConversation;
    
    public CarExtender() {}
    
    public CarExtender()
    {
      if (Build.VERSION.SDK_INT < 21) {
        return;
      }
      if (NotificationCompat.getExtras(this$1) == null) {
        this$1 = null;
      } else {
        this$1 = NotificationCompat.getExtras(this$1).getBundle("android.car.EXTENSIONS");
      }
      if (this$1 != null)
      {
        mLargeIcon = ((Bitmap)this$1.getParcelable("large_icon"));
        mColor = this$1.getInt("app_color", 0);
        mUnreadConversation = getUnreadConversationFromBundle(this$1.getBundle("car_conversation"));
      }
    }
    
    private static Bundle getBundleForUnreadConversation(UnreadConversation paramUnreadConversation)
    {
      Bundle localBundle1 = new Bundle();
      Object localObject = paramUnreadConversation.getParticipants();
      int i = 0;
      if ((localObject != null) && (paramUnreadConversation.getParticipants().length > 1)) {
        localObject = paramUnreadConversation.getParticipants()[0];
      } else {
        localObject = null;
      }
      Parcelable[] arrayOfParcelable = new Parcelable[paramUnreadConversation.getMessages().length];
      while (i < arrayOfParcelable.length)
      {
        Bundle localBundle2 = new Bundle();
        localBundle2.putString("text", paramUnreadConversation.getMessages()[i]);
        localBundle2.putString("author", (String)localObject);
        arrayOfParcelable[i] = localBundle2;
        i += 1;
      }
      localBundle1.putParcelableArray("messages", arrayOfParcelable);
      localObject = paramUnreadConversation.getRemoteInput();
      if (localObject != null) {
        localBundle1.putParcelable("remote_input", (Parcelable)new RemoteInput.Builder(((RemoteInput)localObject).getResultKey()).setLabel(((RemoteInput)localObject).getLabel()).setChoices(((RemoteInput)localObject).getChoices()).setAllowFreeFormInput(((RemoteInput)localObject).getAllowFreeFormInput()).addExtras(((RemoteInput)localObject).getExtras()).build());
      }
      localBundle1.putParcelable("on_reply", paramUnreadConversation.getReplyPendingIntent());
      localBundle1.putParcelable("on_read", paramUnreadConversation.getReadPendingIntent());
      localBundle1.putStringArray("participants", paramUnreadConversation.getParticipants());
      localBundle1.putLong("timestamp", paramUnreadConversation.getLatestTimestamp());
      return localBundle1;
    }
    
    private static UnreadConversation getUnreadConversationFromBundle(Bundle paramBundle)
    {
      RemoteInput localRemoteInput = null;
      if (paramBundle == null) {
        return null;
      }
      Object localObject = paramBundle.getParcelableArray("messages");
      String[] arrayOfString1;
      if (localObject != null)
      {
        arrayOfString1 = new String[localObject.length];
        int j = 0;
        int i = 0;
        while (i < arrayOfString1.length)
        {
          if (!(localObject[i] instanceof Bundle))
          {
            i = j;
            break label89;
          }
          arrayOfString1[i] = ((Bundle)localObject[i]).getString("text");
          if (arrayOfString1[i] == null)
          {
            i = j;
            break label89;
          }
          i += 1;
        }
        i = 1;
        label89:
        if (i == 0) {
          return null;
        }
      }
      else
      {
        arrayOfString1 = null;
      }
      localObject = (PendingIntent)paramBundle.getParcelable("on_read");
      PendingIntent localPendingIntent = (PendingIntent)paramBundle.getParcelable("on_reply");
      android.app.RemoteInput localRemoteInput1 = (android.app.RemoteInput)paramBundle.getParcelable("remote_input");
      String[] arrayOfString2 = paramBundle.getStringArray("participants");
      if (arrayOfString2 != null)
      {
        if (arrayOfString2.length != 1) {
          return null;
        }
        if (localRemoteInput1 != null) {
          localRemoteInput = new RemoteInput(localRemoteInput1.getResultKey(), localRemoteInput1.getLabel(), localRemoteInput1.getChoices(), localRemoteInput1.getAllowFreeFormInput(), localRemoteInput1.getExtras(), null);
        }
        return new UnreadConversation(arrayOfString1, localRemoteInput, localPendingIntent, (PendingIntent)localObject, arrayOfString2, paramBundle.getLong("timestamp"));
      }
      return null;
    }
    
    public NotificationCompat.Builder extend(NotificationCompat.Builder paramBuilder)
    {
      if (Build.VERSION.SDK_INT < 21) {
        return paramBuilder;
      }
      Bundle localBundle = new Bundle();
      if (mLargeIcon != null) {
        localBundle.putParcelable("large_icon", mLargeIcon);
      }
      if (mColor != 0) {
        localBundle.putInt("app_color", mColor);
      }
      if (mUnreadConversation != null) {
        localBundle.putBundle("car_conversation", getBundleForUnreadConversation(mUnreadConversation));
      }
      paramBuilder.getExtras().putBundle("android.car.EXTENSIONS", localBundle);
      return paramBuilder;
    }
    
    public int getColor()
    {
      return mColor;
    }
    
    public Bitmap getLargeIcon()
    {
      return mLargeIcon;
    }
    
    public UnreadConversation getUnreadConversation()
    {
      return mUnreadConversation;
    }
    
    public CarExtender setColor(int paramInt)
    {
      mColor = paramInt;
      return this;
    }
    
    public CarExtender setLargeIcon(Bitmap paramBitmap)
    {
      mLargeIcon = paramBitmap;
      return this;
    }
    
    public CarExtender setUnreadConversation(UnreadConversation paramUnreadConversation)
    {
      mUnreadConversation = paramUnreadConversation;
      return this;
    }
    
    public class UnreadConversation
    {
      private final long mLatestTimestamp;
      private final String[] mParticipants;
      private final PendingIntent mReadPendingIntent;
      private final RemoteInput mRemoteInput;
      private final PendingIntent mReplyPendingIntent;
      
      UnreadConversation(RemoteInput paramRemoteInput, PendingIntent paramPendingIntent1, PendingIntent paramPendingIntent2, String[] paramArrayOfString, long paramLong)
      {
        mRemoteInput = paramRemoteInput;
        mReadPendingIntent = paramPendingIntent2;
        mReplyPendingIntent = paramPendingIntent1;
        mParticipants = paramArrayOfString;
        mLatestTimestamp = paramLong;
      }
      
      public long getLatestTimestamp()
      {
        return mLatestTimestamp;
      }
      
      public String[] getMessages()
      {
        return NotificationCompat.CarExtender.this;
      }
      
      public String getParticipant()
      {
        if (mParticipants.length > 0) {
          return mParticipants[0];
        }
        return null;
      }
      
      public String[] getParticipants()
      {
        return mParticipants;
      }
      
      public PendingIntent getReadPendingIntent()
      {
        return mReadPendingIntent;
      }
      
      public RemoteInput getRemoteInput()
      {
        return mRemoteInput;
      }
      
      public PendingIntent getReplyPendingIntent()
      {
        return mReplyPendingIntent;
      }
      
      public class Builder
      {
        private long mLatestTimestamp;
        private final List<String> mMessages = new ArrayList();
        private PendingIntent mReadPendingIntent;
        private RemoteInput mRemoteInput;
        private PendingIntent mReplyPendingIntent;
        
        public Builder() {}
        
        public Builder addMessage(String paramString)
        {
          mMessages.add(paramString);
          return this;
        }
        
        public NotificationCompat.CarExtender.UnreadConversation build()
        {
          String[] arrayOfString = (String[])mMessages.toArray(new String[mMessages.size()]);
          String str = NotificationCompat.CarExtender.UnreadConversation.this;
          RemoteInput localRemoteInput = mRemoteInput;
          PendingIntent localPendingIntent1 = mReplyPendingIntent;
          PendingIntent localPendingIntent2 = mReadPendingIntent;
          long l = mLatestTimestamp;
          return new NotificationCompat.CarExtender.UnreadConversation(arrayOfString, localRemoteInput, localPendingIntent1, localPendingIntent2, new String[] { str }, l);
        }
        
        public Builder setLatestTimestamp(long paramLong)
        {
          mLatestTimestamp = paramLong;
          return this;
        }
        
        public Builder setReadPendingIntent(PendingIntent paramPendingIntent)
        {
          mReadPendingIntent = paramPendingIntent;
          return this;
        }
        
        public Builder setReplyAction(PendingIntent paramPendingIntent, RemoteInput paramRemoteInput)
        {
          mRemoteInput = paramRemoteInput;
          mReplyPendingIntent = paramPendingIntent;
          return this;
        }
      }
    }
  }
  
  public class DecoratedCustomViewStyle
    extends NotificationCompat.Style
  {
    private static final int MAX_ACTION_BUTTONS = 3;
    
    public DecoratedCustomViewStyle() {}
    
    private RemoteViews createRemoteViews(RemoteViews paramRemoteViews, boolean paramBoolean)
    {
      int i = R.layout.notification_template_custom_big;
      int m = 1;
      int k = 0;
      RemoteViews localRemoteViews1 = applyStandardTemplate(true, i, false);
      localRemoteViews1.removeAllViews(R.id.actions);
      if ((paramBoolean) && (mBuilder.mActions != null))
      {
        int n = Math.min(mBuilder.mActions.size(), 3);
        if (n > 0)
        {
          i = 0;
          for (;;)
          {
            j = m;
            if (i >= n) {
              break;
            }
            RemoteViews localRemoteViews2 = generateActionButton((NotificationCompat.Action)mBuilder.mActions.get(i));
            localRemoteViews1.addView(R.id.actions, localRemoteViews2);
            i += 1;
          }
        }
      }
      int j = 0;
      if (j != 0) {
        i = k;
      } else {
        i = 8;
      }
      localRemoteViews1.setViewVisibility(R.id.actions, i);
      localRemoteViews1.setViewVisibility(R.id.action_divider, i);
      buildIntoRemoteViews(localRemoteViews1, paramRemoteViews);
      return localRemoteViews1;
    }
    
    private RemoteViews generateActionButton(NotificationCompat.Action paramAction)
    {
      int i;
      if (actionIntent == null) {
        i = 1;
      } else {
        i = 0;
      }
      Object localObject = mBuilder.mContext.getPackageName();
      int j;
      if (i != 0) {
        j = R.layout.notification_action_tombstone;
      } else {
        j = R.layout.notification_action;
      }
      localObject = new RemoteViews((String)localObject, j);
      ((RemoteViews)localObject).setImageViewBitmap(R.id.action_image, createColoredBitmap(paramAction.getIcon(), mBuilder.mContext.getResources().getColor(R.color.notification_action_color_filter)));
      ((RemoteViews)localObject).setTextViewText(R.id.action_text, title);
      if (i == 0) {
        ((RemoteViews)localObject).setOnClickPendingIntent(R.id.action_container, actionIntent);
      }
      if (Build.VERSION.SDK_INT >= 15) {
        ((RemoteViews)localObject).setContentDescription(R.id.action_container, title);
      }
      return localObject;
    }
    
    public void apply(NotificationBuilderWithBuilderAccessor paramNotificationBuilderWithBuilderAccessor)
    {
      if (Build.VERSION.SDK_INT >= 24) {
        paramNotificationBuilderWithBuilderAccessor.getBuilder().setStyle(new Notification.DecoratedCustomViewStyle());
      }
    }
    
    public RemoteViews makeBigContentView(NotificationBuilderWithBuilderAccessor paramNotificationBuilderWithBuilderAccessor)
    {
      if (Build.VERSION.SDK_INT >= 24) {
        return null;
      }
      RemoteViews localRemoteViews = mBuilder.getBigContentView();
      paramNotificationBuilderWithBuilderAccessor = localRemoteViews;
      if (localRemoteViews == null) {
        paramNotificationBuilderWithBuilderAccessor = mBuilder.getContentView();
      }
      if (paramNotificationBuilderWithBuilderAccessor == null) {
        return null;
      }
      return createRemoteViews(paramNotificationBuilderWithBuilderAccessor, true);
    }
    
    public RemoteViews makeContentView(NotificationBuilderWithBuilderAccessor paramNotificationBuilderWithBuilderAccessor)
    {
      if (Build.VERSION.SDK_INT >= 24) {
        return null;
      }
      if (mBuilder.getContentView() == null) {
        return null;
      }
      return createRemoteViews(mBuilder.getContentView(), false);
    }
    
    public RemoteViews makeHeadsUpContentView(NotificationBuilderWithBuilderAccessor paramNotificationBuilderWithBuilderAccessor)
    {
      if (Build.VERSION.SDK_INT >= 24) {
        return null;
      }
      RemoteViews localRemoteViews = mBuilder.getHeadsUpContentView();
      if (localRemoteViews != null) {
        paramNotificationBuilderWithBuilderAccessor = localRemoteViews;
      } else {
        paramNotificationBuilderWithBuilderAccessor = mBuilder.getContentView();
      }
      if (localRemoteViews == null) {
        return null;
      }
      return createRemoteViews(paramNotificationBuilderWithBuilderAccessor, true);
    }
  }
  
  public abstract interface Extender
  {
    public abstract NotificationCompat.Builder extend(NotificationCompat.Builder paramBuilder);
  }
  
  @Retention(RetentionPolicy.SOURCE)
  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  public @interface GroupAlertBehavior {}
  
  public class InboxStyle
    extends NotificationCompat.Style
  {
    private ArrayList<CharSequence> mTexts = new ArrayList();
    
    public InboxStyle() {}
    
    public InboxStyle()
    {
      setBuilder(this$1);
    }
    
    public InboxStyle addLine(CharSequence paramCharSequence)
    {
      mTexts.add(NotificationCompat.Builder.limitCharSequenceLength(paramCharSequence));
      return this;
    }
    
    public void apply(NotificationBuilderWithBuilderAccessor paramNotificationBuilderWithBuilderAccessor)
    {
      if (Build.VERSION.SDK_INT >= 16)
      {
        paramNotificationBuilderWithBuilderAccessor = new Notification.InboxStyle(paramNotificationBuilderWithBuilderAccessor.getBuilder()).setBigContentTitle(mBigContentTitle);
        if (mSummaryTextSet) {
          paramNotificationBuilderWithBuilderAccessor.setSummaryText(mSummaryText);
        }
        Iterator localIterator = mTexts.iterator();
        while (localIterator.hasNext()) {
          paramNotificationBuilderWithBuilderAccessor.addLine((CharSequence)localIterator.next());
        }
      }
    }
    
    public InboxStyle setBigContentTitle(CharSequence paramCharSequence)
    {
      mBigContentTitle = NotificationCompat.Builder.limitCharSequenceLength(paramCharSequence);
      return this;
    }
    
    public InboxStyle setSummaryText(CharSequence paramCharSequence)
    {
      mSummaryText = NotificationCompat.Builder.limitCharSequenceLength(paramCharSequence);
      mSummaryTextSet = true;
      return this;
    }
  }
  
  public class MessagingStyle
    extends NotificationCompat.Style
  {
    public static final int MAXIMUM_RETAINED_MESSAGES = 25;
    CharSequence mConversationTitle;
    List<android.support.v4.app.NotificationCompat.MessagingStyle.Message> mMessages = new ArrayList();
    
    MessagingStyle() {}
    
    public MessagingStyle() {}
    
    public static MessagingStyle extractMessagingStyleFromNotification(Notification paramNotification)
    {
      paramNotification = NotificationCompat.getExtras(paramNotification);
      if ((paramNotification != null) && (!paramNotification.containsKey("android.selfDisplayName"))) {
        return null;
      }
      try
      {
        MessagingStyle localMessagingStyle = new MessagingStyle();
        localMessagingStyle.restoreFromCompatExtras(paramNotification);
        return localMessagingStyle;
      }
      catch (ClassCastException paramNotification) {}
      return null;
    }
    
    private Message findLatestIncomingMessage()
    {
      int i = mMessages.size() - 1;
      while (i >= 0)
      {
        Message localMessage = (Message)mMessages.get(i);
        if (!TextUtils.isEmpty(localMessage.getSender())) {
          return localMessage;
        }
        i -= 1;
      }
      if (!mMessages.isEmpty()) {
        return (Message)mMessages.get(mMessages.size() - 1);
      }
      return null;
    }
    
    private boolean hasMessagesWithoutSender()
    {
      int i = mMessages.size() - 1;
      while (i >= 0)
      {
        if (((Message)mMessages.get(i)).getSender() == null) {
          return true;
        }
        i -= 1;
      }
      return false;
    }
    
    private TextAppearanceSpan makeFontColorSpan(int paramInt)
    {
      return new TextAppearanceSpan(null, 0, 0, ColorStateList.valueOf(paramInt), null);
    }
    
    private CharSequence makeMessageLine(Message paramMessage)
    {
      BidiFormatter localBidiFormatter = BidiFormatter.getInstance();
      SpannableStringBuilder localSpannableStringBuilder = new SpannableStringBuilder();
      int j;
      if (Build.VERSION.SDK_INT >= 21) {
        j = 1;
      } else {
        j = 0;
      }
      int i;
      if (j != 0) {
        i = -16777216;
      } else {
        i = -1;
      }
      Object localObject2 = paramMessage.getSender();
      int k = i;
      if (TextUtils.isEmpty(paramMessage.getSender()))
      {
        if (NotificationCompat.this == null) {
          localObject1 = "";
        } else {
          localObject1 = NotificationCompat.this;
        }
        k = i;
        localObject2 = localObject1;
        if (j != 0)
        {
          k = i;
          localObject2 = localObject1;
          if (mBuilder.getColor() != 0)
          {
            k = mBuilder.getColor();
            localObject2 = localObject1;
          }
        }
      }
      Object localObject1 = localBidiFormatter.unicodeWrap((CharSequence)localObject2);
      localSpannableStringBuilder.append((CharSequence)localObject1);
      localSpannableStringBuilder.setSpan(makeFontColorSpan(k), localSpannableStringBuilder.length() - ((CharSequence)localObject1).length(), localSpannableStringBuilder.length(), 33);
      if (paramMessage.getText() == null) {
        paramMessage = "";
      } else {
        paramMessage = paramMessage.getText();
      }
      localSpannableStringBuilder.append("  ").append(localBidiFormatter.unicodeWrap((CharSequence)paramMessage));
      return localSpannableStringBuilder;
    }
    
    public void addCompatExtras(Bundle paramBundle)
    {
      super.addCompatExtras(paramBundle);
      if (NotificationCompat.this != null) {
        paramBundle.putCharSequence("android.selfDisplayName", NotificationCompat.this);
      }
      if (mConversationTitle != null) {
        paramBundle.putCharSequence("android.conversationTitle", mConversationTitle);
      }
      if (!mMessages.isEmpty()) {
        paramBundle.putParcelableArray("android.messages", Message.getBundleArrayForMessages(mMessages));
      }
    }
    
    public MessagingStyle addMessage(Message paramMessage)
    {
      mMessages.add(paramMessage);
      if (mMessages.size() > 25) {
        mMessages.remove(0);
      }
      return this;
    }
    
    public MessagingStyle addMessage(CharSequence paramCharSequence1, long paramLong, CharSequence paramCharSequence2)
    {
      mMessages.add(new Message(paramCharSequence1, paramLong, paramCharSequence2));
      if (mMessages.size() > 25) {
        mMessages.remove(0);
      }
      return this;
    }
    
    public void apply(NotificationBuilderWithBuilderAccessor paramNotificationBuilderWithBuilderAccessor)
    {
      Object localObject2;
      if (Build.VERSION.SDK_INT >= 24)
      {
        localObject1 = new Notification.MessagingStyle(NotificationCompat.this).setConversationTitle(mConversationTitle);
        localObject2 = mMessages.iterator();
        while (((Iterator)localObject2).hasNext())
        {
          Message localMessage = (Message)((Iterator)localObject2).next();
          Notification.MessagingStyle.Message localMessage1 = new Notification.MessagingStyle.Message(localMessage.getText(), localMessage.getTimestamp(), localMessage.getSender());
          if (localMessage.getDataMimeType() != null) {
            localMessage1.setData(localMessage.getDataMimeType(), localMessage.getDataUri());
          }
          ((Notification.MessagingStyle)localObject1).addMessage(localMessage1);
        }
        ((Notification.MessagingStyle)localObject1).setBuilder(paramNotificationBuilderWithBuilderAccessor.getBuilder());
        return;
      }
      Object localObject1 = findLatestIncomingMessage();
      if (mConversationTitle != null) {
        paramNotificationBuilderWithBuilderAccessor.getBuilder().setContentTitle(mConversationTitle);
      } else if (localObject1 != null) {
        paramNotificationBuilderWithBuilderAccessor.getBuilder().setContentTitle(((Message)localObject1).getSender());
      }
      if (localObject1 != null)
      {
        localObject2 = paramNotificationBuilderWithBuilderAccessor.getBuilder();
        if (mConversationTitle != null) {
          localObject1 = makeMessageLine((Message)localObject1);
        } else {
          localObject1 = ((Message)localObject1).getText();
        }
        ((Notification.Builder)localObject2).setContentText((CharSequence)localObject1);
      }
      if (Build.VERSION.SDK_INT >= 16)
      {
        localObject2 = new SpannableStringBuilder();
        int i;
        if ((mConversationTitle == null) && (!hasMessagesWithoutSender())) {
          i = 0;
        } else {
          i = 1;
        }
        int j = mMessages.size() - 1;
        while (j >= 0)
        {
          localObject1 = (Message)mMessages.get(j);
          if (i != 0) {
            localObject1 = makeMessageLine((Message)localObject1);
          } else {
            localObject1 = ((Message)localObject1).getText();
          }
          if (j != mMessages.size() - 1) {
            ((SpannableStringBuilder)localObject2).insert(0, "\n");
          }
          ((SpannableStringBuilder)localObject2).insert(0, (CharSequence)localObject1);
          j -= 1;
        }
        new Notification.BigTextStyle(paramNotificationBuilderWithBuilderAccessor.getBuilder()).setBigContentTitle(null).bigText((CharSequence)localObject2);
      }
    }
    
    public CharSequence getConversationTitle()
    {
      return mConversationTitle;
    }
    
    public List getMessages()
    {
      return mMessages;
    }
    
    public CharSequence getUserDisplayName()
    {
      return NotificationCompat.this;
    }
    
    protected void restoreFromCompatExtras(Bundle paramBundle)
    {
      mMessages.clear();
      mUserDisplayName = paramBundle.getString("android.selfDisplayName");
      mConversationTitle = paramBundle.getString("android.conversationTitle");
      paramBundle = paramBundle.getParcelableArray("android.messages");
      if (paramBundle != null) {
        mMessages = Message.getMessagesFromBundleArray(paramBundle);
      }
    }
    
    public MessagingStyle setConversationTitle(CharSequence paramCharSequence)
    {
      mConversationTitle = paramCharSequence;
      return this;
    }
    
    public final class Message
    {
      static final String KEY_DATA_MIME_TYPE = "type";
      static final String KEY_DATA_URI = "uri";
      static final String KEY_EXTRAS_BUNDLE = "extras";
      static final String KEY_SENDER = "sender";
      static final String KEY_TEXT = "text";
      static final String KEY_TIMESTAMP = "time";
      private String mDataMimeType;
      private Uri mDataUri;
      private Bundle mExtras = new Bundle();
      private final CharSequence mSender;
      private final long mTimestamp;
      
      public Message(long paramLong, CharSequence paramCharSequence)
      {
        mTimestamp = paramLong;
        mSender = paramCharSequence;
      }
      
      static Bundle[] getBundleArrayForMessages(List paramList)
      {
        Bundle[] arrayOfBundle = new Bundle[paramList.size()];
        int j = paramList.size();
        int i = 0;
        while (i < j)
        {
          arrayOfBundle[i] = ((Message)paramList.get(i)).toBundle();
          i += 1;
        }
        return arrayOfBundle;
      }
      
      static Message getMessageFromBundle(Bundle paramBundle)
      {
        Message localMessage;
        try
        {
          boolean bool = paramBundle.containsKey("text");
          if (bool)
          {
            bool = paramBundle.containsKey("time");
            if (!bool) {
              return null;
            }
            localMessage = new Message(paramBundle.getCharSequence("text"), paramBundle.getLong("time"), paramBundle.getCharSequence("sender"));
            bool = paramBundle.containsKey("type");
            if (bool)
            {
              bool = paramBundle.containsKey("uri");
              if (bool) {
                localMessage.setData(paramBundle.getString("type"), (Uri)paramBundle.getParcelable("uri"));
              }
            }
            bool = paramBundle.containsKey("extras");
            if (bool)
            {
              localMessage.getExtras().putAll(paramBundle.getBundle("extras"));
              return localMessage;
            }
          }
          else
          {
            return null;
          }
        }
        catch (ClassCastException paramBundle)
        {
          return null;
        }
        return localMessage;
      }
      
      static List getMessagesFromBundleArray(Parcelable[] paramArrayOfParcelable)
      {
        ArrayList localArrayList = new ArrayList(paramArrayOfParcelable.length);
        int i = 0;
        while (i < paramArrayOfParcelable.length)
        {
          if ((paramArrayOfParcelable[i] instanceof Bundle))
          {
            Message localMessage = getMessageFromBundle((Bundle)paramArrayOfParcelable[i]);
            if (localMessage != null) {
              localArrayList.add(localMessage);
            }
          }
          i += 1;
        }
        return localArrayList;
      }
      
      private Bundle toBundle()
      {
        Bundle localBundle = new Bundle();
        if (NotificationCompat.MessagingStyle.this != null) {
          localBundle.putCharSequence("text", NotificationCompat.MessagingStyle.this);
        }
        localBundle.putLong("time", mTimestamp);
        if (mSender != null) {
          localBundle.putCharSequence("sender", mSender);
        }
        if (mDataMimeType != null) {
          localBundle.putString("type", mDataMimeType);
        }
        if (mDataUri != null) {
          localBundle.putParcelable("uri", mDataUri);
        }
        if (mExtras != null) {
          localBundle.putBundle("extras", mExtras);
        }
        return localBundle;
      }
      
      public String getDataMimeType()
      {
        return mDataMimeType;
      }
      
      public Uri getDataUri()
      {
        return mDataUri;
      }
      
      public Bundle getExtras()
      {
        return mExtras;
      }
      
      public CharSequence getSender()
      {
        return mSender;
      }
      
      public CharSequence getText()
      {
        return NotificationCompat.MessagingStyle.this;
      }
      
      public long getTimestamp()
      {
        return mTimestamp;
      }
      
      public Message setData(String paramString, Uri paramUri)
      {
        mDataMimeType = paramString;
        mDataUri = paramUri;
        return this;
      }
    }
  }
  
  @Retention(RetentionPolicy.SOURCE)
  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  public @interface NotificationVisibility {}
  
  @Retention(RetentionPolicy.SOURCE)
  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  public @interface StreamType {}
  
  public abstract class Style
  {
    CharSequence mBigContentTitle;
    @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
    protected NotificationCompat.Builder mBuilder;
    CharSequence mSummaryText;
    boolean mSummaryTextSet = false;
    
    public Style() {}
    
    private int calculateTopPadding()
    {
      Resources localResources = mBuilder.mContext.getResources();
      int i = localResources.getDimensionPixelSize(R.dimen.notification_top_pad);
      int j = localResources.getDimensionPixelSize(R.dimen.notification_top_pad_large_text);
      float f = (constrain(getConfigurationfontScale, 1.0F, 1.3F) - 1.0F) / 0.29999995F;
      return Math.round((1.0F - f) * i + f * j);
    }
    
    private static float constrain(float paramFloat1, float paramFloat2, float paramFloat3)
    {
      if (paramFloat1 < paramFloat2) {
        return paramFloat2;
      }
      if (paramFloat1 > paramFloat3) {
        return paramFloat3;
      }
      return paramFloat1;
    }
    
    private Bitmap createColoredBitmap(int paramInt1, int paramInt2, int paramInt3)
    {
      Drawable localDrawable = mBuilder.mContext.getResources().getDrawable(paramInt1);
      if (paramInt3 == 0) {
        paramInt1 = localDrawable.getIntrinsicWidth();
      } else {
        paramInt1 = paramInt3;
      }
      int i = paramInt3;
      if (paramInt3 == 0) {
        i = localDrawable.getIntrinsicHeight();
      }
      Bitmap localBitmap = Bitmap.createBitmap(paramInt1, i, Bitmap.Config.ARGB_8888);
      localDrawable.setBounds(0, 0, paramInt1, i);
      if (paramInt2 != 0) {
        localDrawable.mutate().setColorFilter(new PorterDuffColorFilter(paramInt2, PorterDuff.Mode.SRC_IN));
      }
      localDrawable.draw(new Canvas(localBitmap));
      return localBitmap;
    }
    
    private Bitmap createIconWithBackground(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
    {
      int j = R.drawable.notification_icon_background;
      int i = paramInt4;
      if (paramInt4 == 0) {
        i = 0;
      }
      Bitmap localBitmap = createColoredBitmap(j, i, paramInt2);
      Canvas localCanvas = new Canvas(localBitmap);
      Drawable localDrawable = mBuilder.mContext.getResources().getDrawable(paramInt1).mutate();
      localDrawable.setFilterBitmap(true);
      paramInt1 = (paramInt2 - paramInt3) / 2;
      paramInt2 = paramInt3 + paramInt1;
      localDrawable.setBounds(paramInt1, paramInt1, paramInt2, paramInt2);
      localDrawable.setColorFilter(new PorterDuffColorFilter(-1, PorterDuff.Mode.SRC_ATOP));
      localDrawable.draw(localCanvas);
      return localBitmap;
    }
    
    private void hideNormalContent(RemoteViews paramRemoteViews)
    {
      paramRemoteViews.setViewVisibility(R.id.title, 8);
      paramRemoteViews.setViewVisibility(R.id.text2, 8);
      paramRemoteViews.setViewVisibility(R.id.text, 8);
    }
    
    public void addCompatExtras(Bundle paramBundle) {}
    
    public void apply(NotificationBuilderWithBuilderAccessor paramNotificationBuilderWithBuilderAccessor) {}
    
    public RemoteViews applyStandardTemplate(boolean paramBoolean1, int paramInt, boolean paramBoolean2)
    {
      Resources localResources = mBuilder.mContext.getResources();
      RemoteViews localRemoteViews = new RemoteViews(mBuilder.mContext.getPackageName(), paramInt);
      paramInt = mBuilder.getPriority();
      int k = 0;
      if (paramInt < -1) {
        paramInt = 1;
      } else {
        paramInt = 0;
      }
      if ((Build.VERSION.SDK_INT >= 16) && (Build.VERSION.SDK_INT < 21)) {
        if (paramInt != 0)
        {
          localRemoteViews.setInt(R.id.notification_background, "setBackgroundResource", R.drawable.notification_bg_low);
          localRemoteViews.setInt(R.id.icon, "setBackgroundResource", R.drawable.notification_template_icon_low_bg);
        }
        else
        {
          localRemoteViews.setInt(R.id.notification_background, "setBackgroundResource", R.drawable.notification_bg);
          localRemoteViews.setInt(R.id.icon, "setBackgroundResource", R.drawable.notification_template_icon_bg);
        }
      }
      Object localObject;
      if (mBuilder.mLargeIcon != null)
      {
        if (Build.VERSION.SDK_INT >= 16)
        {
          localRemoteViews.setViewVisibility(R.id.icon, 0);
          localRemoteViews.setImageViewBitmap(R.id.icon, mBuilder.mLargeIcon);
        }
        else
        {
          localRemoteViews.setViewVisibility(R.id.icon, 8);
        }
        if ((paramBoolean1) && (mBuilder.mNotification.icon != 0))
        {
          paramInt = localResources.getDimensionPixelSize(R.dimen.notification_right_icon_size);
          i = localResources.getDimensionPixelSize(R.dimen.notification_small_icon_background_padding);
          if (Build.VERSION.SDK_INT >= 21)
          {
            localObject = createIconWithBackground(mBuilder.mNotification.icon, paramInt, paramInt - i * 2, mBuilder.getColor());
            localRemoteViews.setImageViewBitmap(R.id.right_icon, (Bitmap)localObject);
          }
          else
          {
            localRemoteViews.setImageViewBitmap(R.id.right_icon, createColoredBitmap(mBuilder.mNotification.icon, -1));
          }
          localRemoteViews.setViewVisibility(R.id.right_icon, 0);
        }
      }
      else if ((paramBoolean1) && (mBuilder.mNotification.icon != 0))
      {
        localRemoteViews.setViewVisibility(R.id.icon, 0);
        if (Build.VERSION.SDK_INT >= 21)
        {
          paramInt = localResources.getDimensionPixelSize(R.dimen.notification_large_icon_width);
          i = localResources.getDimensionPixelSize(R.dimen.notification_big_circle_margin);
          j = localResources.getDimensionPixelSize(R.dimen.notification_small_icon_size_as_large);
          localObject = createIconWithBackground(mBuilder.mNotification.icon, paramInt - i, j, mBuilder.getColor());
          localRemoteViews.setImageViewBitmap(R.id.icon, (Bitmap)localObject);
        }
        else
        {
          localRemoteViews.setImageViewBitmap(R.id.icon, createColoredBitmap(mBuilder.mNotification.icon, -1));
        }
      }
      if (mBuilder.mContentTitle != null) {
        localRemoteViews.setTextViewText(R.id.title, mBuilder.mContentTitle);
      }
      if (mBuilder.mContentText != null)
      {
        localRemoteViews.setTextViewText(R.id.text, mBuilder.mContentText);
        paramInt = 1;
      }
      else
      {
        paramInt = 0;
      }
      if ((Build.VERSION.SDK_INT < 21) && (mBuilder.mLargeIcon != null)) {
        i = 1;
      } else {
        i = 0;
      }
      if (mBuilder.mContentInfo != null)
      {
        localRemoteViews.setTextViewText(R.id.info, mBuilder.mContentInfo);
        localRemoteViews.setViewVisibility(R.id.info, 0);
      }
      for (;;)
      {
        j = 1;
        paramInt = 1;
        break label664;
        if (mBuilder.mNumber <= 0) {
          break;
        }
        paramInt = localResources.getInteger(R.integer.status_bar_notification_info_maxnum);
        if (mBuilder.mNumber > paramInt)
        {
          localRemoteViews.setTextViewText(R.id.info, localResources.getString(R.string.status_bar_notification_info_overflow));
        }
        else
        {
          localObject = NumberFormat.getIntegerInstance();
          localRemoteViews.setTextViewText(R.id.info, ((NumberFormat)localObject).format(mBuilder.mNumber));
        }
        localRemoteViews.setViewVisibility(R.id.info, 0);
      }
      localRemoteViews.setViewVisibility(R.id.info, 8);
      int j = paramInt;
      paramInt = i;
      label664:
      if ((mBuilder.mSubText != null) && (Build.VERSION.SDK_INT >= 16))
      {
        localRemoteViews.setTextViewText(R.id.text, mBuilder.mSubText);
        if (mBuilder.mContentText != null)
        {
          localRemoteViews.setTextViewText(R.id.text2, mBuilder.mContentText);
          localRemoteViews.setViewVisibility(R.id.text2, 0);
          i = 1;
        }
        else
        {
          localRemoteViews.setViewVisibility(R.id.text2, 8);
        }
      }
      else
      {
        i = 0;
      }
      if ((i != 0) && (Build.VERSION.SDK_INT >= 16))
      {
        if (paramBoolean2)
        {
          float f = localResources.getDimensionPixelSize(R.dimen.notification_subtext_size);
          localRemoteViews.setTextViewTextSize(R.id.text, 0, f);
        }
        localRemoteViews.setViewPadding(R.id.line1, 0, 0, 0, 0);
      }
      if (mBuilder.getWhenIfShowing() != 0L)
      {
        if ((mBuilder.mUseChronometer) && (Build.VERSION.SDK_INT >= 16))
        {
          localRemoteViews.setViewVisibility(R.id.chronometer, 0);
          localRemoteViews.setLong(R.id.chronometer, "setBase", mBuilder.getWhenIfShowing() + (SystemClock.elapsedRealtime() - System.currentTimeMillis()));
          localRemoteViews.setBoolean(R.id.chronometer, "setStarted", true);
        }
        else
        {
          localRemoteViews.setViewVisibility(R.id.time, 0);
          localRemoteViews.setLong(R.id.time, "setTime", mBuilder.getWhenIfShowing());
        }
        paramInt = 1;
      }
      int i = R.id.right_side;
      if (paramInt != 0) {
        paramInt = 0;
      } else {
        paramInt = 8;
      }
      localRemoteViews.setViewVisibility(i, paramInt);
      i = R.id.line3;
      if (j != 0) {
        paramInt = k;
      } else {
        paramInt = 8;
      }
      localRemoteViews.setViewVisibility(i, paramInt);
      return localRemoteViews;
    }
    
    public Notification build()
    {
      if (mBuilder != null) {
        return mBuilder.build();
      }
      return null;
    }
    
    public void buildIntoRemoteViews(RemoteViews paramRemoteViews1, RemoteViews paramRemoteViews2)
    {
      hideNormalContent(paramRemoteViews1);
      paramRemoteViews1.removeAllViews(R.id.notification_main_column);
      paramRemoteViews1.addView(R.id.notification_main_column, paramRemoteViews2.clone());
      paramRemoteViews1.setViewVisibility(R.id.notification_main_column, 0);
      if (Build.VERSION.SDK_INT >= 21) {
        paramRemoteViews1.setViewPadding(R.id.notification_main_column_container, 0, calculateTopPadding(), 0, 0);
      }
    }
    
    public Bitmap createColoredBitmap(int paramInt1, int paramInt2)
    {
      return createColoredBitmap(paramInt1, paramInt2, 0);
    }
    
    public RemoteViews makeBigContentView(NotificationBuilderWithBuilderAccessor paramNotificationBuilderWithBuilderAccessor)
    {
      return null;
    }
    
    public RemoteViews makeContentView(NotificationBuilderWithBuilderAccessor paramNotificationBuilderWithBuilderAccessor)
    {
      return null;
    }
    
    public RemoteViews makeHeadsUpContentView(NotificationBuilderWithBuilderAccessor paramNotificationBuilderWithBuilderAccessor)
    {
      return null;
    }
    
    protected void restoreFromCompatExtras(Bundle paramBundle) {}
    
    public void setBuilder(NotificationCompat.Builder paramBuilder)
    {
      if (mBuilder != paramBuilder)
      {
        mBuilder = paramBuilder;
        if (mBuilder != null) {
          mBuilder.setStyle(this);
        }
      }
    }
  }
  
  public final class WearableExtender
    implements NotificationCompat.Extender
  {
    private static final int DEFAULT_CONTENT_ICON_GRAVITY = 8388613;
    private static final int DEFAULT_FLAGS = 1;
    private static final int DEFAULT_GRAVITY = 80;
    private static final String EXTRA_WEARABLE_EXTENSIONS = "android.wearable.EXTENSIONS";
    private static final int FLAG_BIG_PICTURE_AMBIENT = 32;
    private static final int FLAG_CONTENT_INTENT_AVAILABLE_OFFLINE = 1;
    private static final int FLAG_HINT_AVOID_BACKGROUND_CLIPPING = 16;
    private static final int FLAG_HINT_CONTENT_INTENT_LAUNCHES_ACTIVITY = 64;
    private static final int FLAG_HINT_HIDE_ICON = 2;
    private static final int FLAG_HINT_SHOW_BACKGROUND_ONLY = 4;
    private static final int FLAG_START_SCROLL_BOTTOM = 8;
    private static final String KEY_ACTIONS = "actions";
    private static final String KEY_BACKGROUND = "background";
    private static final String KEY_BRIDGE_TAG = "bridgeTag";
    private static final String KEY_CONTENT_ACTION_INDEX = "contentActionIndex";
    private static final String KEY_CONTENT_ICON = "contentIcon";
    private static final String KEY_CONTENT_ICON_GRAVITY = "contentIconGravity";
    private static final String KEY_CUSTOM_CONTENT_HEIGHT = "customContentHeight";
    private static final String KEY_CUSTOM_SIZE_PRESET = "customSizePreset";
    private static final String KEY_DISMISSAL_ID = "dismissalId";
    private static final String KEY_DISPLAY_INTENT = "displayIntent";
    private static final String KEY_FLAGS = "flags";
    private static final String KEY_GRAVITY = "gravity";
    private static final String KEY_HINT_SCREEN_TIMEOUT = "hintScreenTimeout";
    private static final String KEY_PAGES = "pages";
    public static final int SCREEN_TIMEOUT_LONG = -1;
    public static final int SCREEN_TIMEOUT_SHORT = 0;
    public static final int SIZE_DEFAULT = 0;
    public static final int SIZE_FULL_SCREEN = 5;
    public static final int SIZE_LARGE = 4;
    public static final int SIZE_MEDIUM = 3;
    public static final int SIZE_SMALL = 2;
    public static final int SIZE_XSMALL = 1;
    public static final int UNSET_ACTION_INDEX = -1;
    private ArrayList<android.support.v4.app.NotificationCompat.Action> mActions = new ArrayList();
    private Bitmap mBackground;
    private String mBridgeTag;
    private int mContentActionIndex = -1;
    private int mContentIcon;
    private int mContentIconGravity = 8388613;
    private int mCustomContentHeight;
    private int mCustomSizePreset = 0;
    private String mDismissalId;
    private PendingIntent mDisplayIntent;
    private int mFlags = 1;
    private int mGravity = 80;
    private int mHintScreenTimeout;
    private ArrayList<Notification> mPages = new ArrayList();
    
    public WearableExtender() {}
    
    public WearableExtender()
    {
      this$1 = NotificationCompat.getExtras(this$1);
      if (this$1 != null) {
        this$1 = this$1.getBundle("android.wearable.EXTENSIONS");
      } else {
        this$1 = null;
      }
      if (this$1 != null)
      {
        Object localObject = this$1.getParcelableArrayList("actions");
        if ((Build.VERSION.SDK_INT >= 16) && (localObject != null))
        {
          NotificationCompat.Action[] arrayOfAction = new NotificationCompat.Action[((ArrayList)localObject).size()];
          int i = 0;
          while (i < arrayOfAction.length)
          {
            if (Build.VERSION.SDK_INT >= 20) {
              arrayOfAction[i] = NotificationCompat.getActionCompatFromAction((Notification.Action)((ArrayList)localObject).get(i));
            } else if (Build.VERSION.SDK_INT >= 16) {
              arrayOfAction[i] = NotificationCompatJellybean.getActionFromBundle((Bundle)((ArrayList)localObject).get(i));
            }
            i += 1;
          }
          Collections.addAll(mActions, (NotificationCompat.Action[])arrayOfAction);
        }
        mFlags = this$1.getInt("flags", 1);
        mDisplayIntent = ((PendingIntent)this$1.getParcelable("displayIntent"));
        localObject = NotificationCompat.getNotificationArrayFromBundle(this$1, "pages");
        if (localObject != null) {
          Collections.addAll(mPages, (Object[])localObject);
        }
        mBackground = ((Bitmap)this$1.getParcelable("background"));
        mContentIcon = this$1.getInt("contentIcon");
        mContentIconGravity = this$1.getInt("contentIconGravity", 8388613);
        mContentActionIndex = this$1.getInt("contentActionIndex", -1);
        mCustomSizePreset = this$1.getInt("customSizePreset", 0);
        mCustomContentHeight = this$1.getInt("customContentHeight");
        mGravity = this$1.getInt("gravity", 80);
        mHintScreenTimeout = this$1.getInt("hintScreenTimeout");
        mDismissalId = this$1.getString("dismissalId");
        mBridgeTag = this$1.getString("bridgeTag");
      }
    }
    
    private static Notification.Action getActionFromActionCompat(NotificationCompat.Action paramAction)
    {
      Notification.Action.Builder localBuilder = new Notification.Action.Builder(paramAction.getIcon(), paramAction.getTitle(), paramAction.getActionIntent());
      Bundle localBundle;
      if (paramAction.getExtras() != null) {
        localBundle = new Bundle(paramAction.getExtras());
      } else {
        localBundle = new Bundle();
      }
      localBundle.putBoolean("android.support.allowGeneratedReplies", paramAction.getAllowGeneratedReplies());
      if (Build.VERSION.SDK_INT >= 24) {
        localBuilder.setAllowGeneratedReplies(paramAction.getAllowGeneratedReplies());
      }
      localBuilder.addExtras(localBundle);
      paramAction = paramAction.getRemoteInputs();
      if (paramAction != null)
      {
        paramAction = RemoteInput.fromCompat(paramAction);
        int j = paramAction.length;
        int i = 0;
        while (i < j)
        {
          localBuilder.addRemoteInput(paramAction[i]);
          i += 1;
        }
      }
      return localBuilder.build();
    }
    
    private void setFlag(int paramInt, boolean paramBoolean)
    {
      if (paramBoolean)
      {
        mFlags = (paramInt | mFlags);
        return;
      }
      mFlags = (paramInt & mFlags);
    }
    
    public WearableExtender addAction(NotificationCompat.Action paramAction)
    {
      mActions.add(paramAction);
      return this;
    }
    
    public WearableExtender addActions(List paramList)
    {
      mActions.addAll(paramList);
      return this;
    }
    
    public WearableExtender addPage(Notification paramNotification)
    {
      mPages.add(paramNotification);
      return this;
    }
    
    public WearableExtender addPages(List paramList)
    {
      mPages.addAll(paramList);
      return this;
    }
    
    public WearableExtender clearActions()
    {
      mActions.clear();
      return this;
    }
    
    public WearableExtender clearPages()
    {
      mPages.clear();
      return this;
    }
    
    public WearableExtender clone()
    {
      WearableExtender localWearableExtender = new WearableExtender();
      mActions = new ArrayList(mActions);
      mFlags = mFlags;
      mDisplayIntent = mDisplayIntent;
      mPages = new ArrayList(mPages);
      mBackground = mBackground;
      mContentIcon = mContentIcon;
      mContentIconGravity = mContentIconGravity;
      mContentActionIndex = mContentActionIndex;
      mCustomSizePreset = mCustomSizePreset;
      mCustomContentHeight = mCustomContentHeight;
      mGravity = mGravity;
      mHintScreenTimeout = mHintScreenTimeout;
      mDismissalId = mDismissalId;
      mBridgeTag = mBridgeTag;
      return localWearableExtender;
    }
    
    public NotificationCompat.Builder extend(NotificationCompat.Builder paramBuilder)
    {
      Bundle localBundle = new Bundle();
      if (!mActions.isEmpty()) {
        if (Build.VERSION.SDK_INT >= 16)
        {
          ArrayList localArrayList = new ArrayList(mActions.size());
          Iterator localIterator = mActions.iterator();
          while (localIterator.hasNext())
          {
            NotificationCompat.Action localAction = (NotificationCompat.Action)localIterator.next();
            if (Build.VERSION.SDK_INT >= 20) {
              localArrayList.add(getActionFromActionCompat(localAction));
            } else if (Build.VERSION.SDK_INT >= 16) {
              localArrayList.add(NotificationCompatJellybean.getBundleForAction(localAction));
            }
          }
          localBundle.putParcelableArrayList("actions", localArrayList);
        }
        else
        {
          localBundle.putParcelableArrayList("actions", null);
        }
      }
      if (mFlags != 1) {
        localBundle.putInt("flags", mFlags);
      }
      if (mDisplayIntent != null) {
        localBundle.putParcelable("displayIntent", mDisplayIntent);
      }
      if (!mPages.isEmpty()) {
        localBundle.putParcelableArray("pages", (Parcelable[])mPages.toArray(new Notification[mPages.size()]));
      }
      if (mBackground != null) {
        localBundle.putParcelable("background", mBackground);
      }
      if (mContentIcon != 0) {
        localBundle.putInt("contentIcon", mContentIcon);
      }
      if (mContentIconGravity != 8388613) {
        localBundle.putInt("contentIconGravity", mContentIconGravity);
      }
      if (mContentActionIndex != -1) {
        localBundle.putInt("contentActionIndex", mContentActionIndex);
      }
      if (mCustomSizePreset != 0) {
        localBundle.putInt("customSizePreset", mCustomSizePreset);
      }
      if (mCustomContentHeight != 0) {
        localBundle.putInt("customContentHeight", mCustomContentHeight);
      }
      if (mGravity != 80) {
        localBundle.putInt("gravity", mGravity);
      }
      if (mHintScreenTimeout != 0) {
        localBundle.putInt("hintScreenTimeout", mHintScreenTimeout);
      }
      if (mDismissalId != null) {
        localBundle.putString("dismissalId", mDismissalId);
      }
      if (mBridgeTag != null) {
        localBundle.putString("bridgeTag", mBridgeTag);
      }
      paramBuilder.getExtras().putBundle("android.wearable.EXTENSIONS", localBundle);
      return paramBuilder;
    }
    
    public List getActions()
    {
      return mActions;
    }
    
    public Bitmap getBackground()
    {
      return mBackground;
    }
    
    public String getBridgeTag()
    {
      return mBridgeTag;
    }
    
    public int getContentAction()
    {
      return mContentActionIndex;
    }
    
    public int getContentIcon()
    {
      return mContentIcon;
    }
    
    public int getContentIconGravity()
    {
      return mContentIconGravity;
    }
    
    public boolean getContentIntentAvailableOffline()
    {
      return (mFlags & 0x1) != 0;
    }
    
    public int getCustomContentHeight()
    {
      return mCustomContentHeight;
    }
    
    public int getCustomSizePreset()
    {
      return mCustomSizePreset;
    }
    
    public String getDismissalId()
    {
      return mDismissalId;
    }
    
    public PendingIntent getDisplayIntent()
    {
      return mDisplayIntent;
    }
    
    public int getGravity()
    {
      return mGravity;
    }
    
    public boolean getHintAmbientBigPicture()
    {
      return (mFlags & 0x20) != 0;
    }
    
    public boolean getHintAvoidBackgroundClipping()
    {
      return (mFlags & 0x10) != 0;
    }
    
    public boolean getHintContentIntentLaunchesActivity()
    {
      return (mFlags & 0x40) != 0;
    }
    
    public boolean getHintHideIcon()
    {
      return (mFlags & 0x2) != 0;
    }
    
    public int getHintScreenTimeout()
    {
      return mHintScreenTimeout;
    }
    
    public boolean getHintShowBackgroundOnly()
    {
      return (mFlags & 0x4) != 0;
    }
    
    public List getPages()
    {
      return mPages;
    }
    
    public boolean getStartScrollBottom()
    {
      return (mFlags & 0x8) != 0;
    }
    
    public WearableExtender setBackground(Bitmap paramBitmap)
    {
      mBackground = paramBitmap;
      return this;
    }
    
    public WearableExtender setBridgeTag(String paramString)
    {
      mBridgeTag = paramString;
      return this;
    }
    
    public WearableExtender setContentAction(int paramInt)
    {
      mContentActionIndex = paramInt;
      return this;
    }
    
    public WearableExtender setContentIcon(int paramInt)
    {
      mContentIcon = paramInt;
      return this;
    }
    
    public WearableExtender setContentIconGravity(int paramInt)
    {
      mContentIconGravity = paramInt;
      return this;
    }
    
    public WearableExtender setContentIntentAvailableOffline(boolean paramBoolean)
    {
      setFlag(1, paramBoolean);
      return this;
    }
    
    public WearableExtender setCustomContentHeight(int paramInt)
    {
      mCustomContentHeight = paramInt;
      return this;
    }
    
    public WearableExtender setCustomSizePreset(int paramInt)
    {
      mCustomSizePreset = paramInt;
      return this;
    }
    
    public WearableExtender setDismissalId(String paramString)
    {
      mDismissalId = paramString;
      return this;
    }
    
    public WearableExtender setDisplayIntent(PendingIntent paramPendingIntent)
    {
      mDisplayIntent = paramPendingIntent;
      return this;
    }
    
    public WearableExtender setGravity(int paramInt)
    {
      mGravity = paramInt;
      return this;
    }
    
    public WearableExtender setHintAmbientBigPicture(boolean paramBoolean)
    {
      setFlag(32, paramBoolean);
      return this;
    }
    
    public WearableExtender setHintAvoidBackgroundClipping(boolean paramBoolean)
    {
      setFlag(16, paramBoolean);
      return this;
    }
    
    public WearableExtender setHintContentIntentLaunchesActivity(boolean paramBoolean)
    {
      setFlag(64, paramBoolean);
      return this;
    }
    
    public WearableExtender setHintHideIcon(boolean paramBoolean)
    {
      setFlag(2, paramBoolean);
      return this;
    }
    
    public WearableExtender setHintScreenTimeout(int paramInt)
    {
      mHintScreenTimeout = paramInt;
      return this;
    }
    
    public WearableExtender setHintShowBackgroundOnly(boolean paramBoolean)
    {
      setFlag(4, paramBoolean);
      return this;
    }
    
    public WearableExtender setStartScrollBottom(boolean paramBoolean)
    {
      setFlag(8, paramBoolean);
      return this;
    }
  }
}
