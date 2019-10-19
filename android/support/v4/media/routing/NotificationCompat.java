package android.support.v4.media.routing;

import android.app.Notification;
import android.app.Notification.Builder;
import android.app.Notification.DecoratedMediaCustomViewStyle;
import android.app.Notification.MediaStyle;
import android.app.PendingIntent;
import android.content.Context;
import android.content.res.Resources;
import android.media.session.MediaSession.Token;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.mediacompat.R.color;
import android.support.mediacompat.R.id;
import android.support.mediacompat.R.integer;
import android.support.mediacompat.R.layout;
import android.support.v4.media.session.MediaSessionCompat.Token;
import android.support.v4.package_7.BundleCompat;
import android.support.v4.package_7.NotificationBuilderWithBuilderAccessor;
import android.support.v4.package_7.NotificationCompat.Action;
import android.support.v4.package_7.NotificationCompat.Builder;
import android.support.v4.package_7.NotificationCompat.Style;
import android.widget.RemoteViews;
import java.util.ArrayList;

public class NotificationCompat
{
  private NotificationCompat() {}
  
  public class DecoratedMediaCustomViewStyle
    extends NotificationCompat.MediaStyle
  {
    public DecoratedMediaCustomViewStyle() {}
    
    private void setBackgroundColor(RemoteViews paramRemoteViews)
    {
      int i;
      if (mBuilder.getColor() != 0) {
        i = mBuilder.getColor();
      } else {
        i = mBuilder.mContext.getResources().getColor(R.color.notification_material_background_media_default_color);
      }
      paramRemoteViews.setInt(R.id.status_bar_latest_event_content, "setBackgroundColor", i);
    }
    
    public void apply(NotificationBuilderWithBuilderAccessor paramNotificationBuilderWithBuilderAccessor)
    {
      if (Build.VERSION.SDK_INT >= 24)
      {
        paramNotificationBuilderWithBuilderAccessor.getBuilder().setStyle(fillInMediaStyle(new Notification.DecoratedMediaCustomViewStyle()));
        return;
      }
      super.apply(paramNotificationBuilderWithBuilderAccessor);
    }
    
    int getBigContentViewLayoutResource(int paramInt)
    {
      if (paramInt <= 3) {
        return R.layout.notification_template_big_media_narrow_custom;
      }
      return R.layout.notification_template_big_media_custom;
    }
    
    int getContentViewLayoutResource()
    {
      if (mBuilder.getContentView() != null) {
        return R.layout.notification_template_media_custom;
      }
      return super.getContentViewLayoutResource();
    }
    
    public RemoteViews makeBigContentView(NotificationBuilderWithBuilderAccessor paramNotificationBuilderWithBuilderAccessor)
    {
      if (Build.VERSION.SDK_INT >= 24) {
        return null;
      }
      if (mBuilder.getBigContentView() != null) {
        paramNotificationBuilderWithBuilderAccessor = mBuilder.getBigContentView();
      } else {
        paramNotificationBuilderWithBuilderAccessor = mBuilder.getContentView();
      }
      if (paramNotificationBuilderWithBuilderAccessor == null) {
        return null;
      }
      RemoteViews localRemoteViews = generateBigContentView();
      buildIntoRemoteViews(localRemoteViews, paramNotificationBuilderWithBuilderAccessor);
      if (Build.VERSION.SDK_INT >= 21) {
        setBackgroundColor(localRemoteViews);
      }
      return localRemoteViews;
    }
    
    public RemoteViews makeContentView(NotificationBuilderWithBuilderAccessor paramNotificationBuilderWithBuilderAccessor)
    {
      if (Build.VERSION.SDK_INT >= 24) {
        return null;
      }
      paramNotificationBuilderWithBuilderAccessor = mBuilder.getContentView();
      int j = 0;
      int i;
      if (paramNotificationBuilderWithBuilderAccessor != null) {
        i = 1;
      } else {
        i = 0;
      }
      if (Build.VERSION.SDK_INT >= 21)
      {
        if ((i != 0) || (mBuilder.getBigContentView() != null)) {
          j = 1;
        }
        if (j != 0)
        {
          paramNotificationBuilderWithBuilderAccessor = generateContentView();
          if (i != 0) {
            buildIntoRemoteViews(paramNotificationBuilderWithBuilderAccessor, mBuilder.getContentView());
          }
          setBackgroundColor(paramNotificationBuilderWithBuilderAccessor);
          return paramNotificationBuilderWithBuilderAccessor;
        }
      }
      else
      {
        paramNotificationBuilderWithBuilderAccessor = generateContentView();
        if (i != 0)
        {
          buildIntoRemoteViews(paramNotificationBuilderWithBuilderAccessor, mBuilder.getContentView());
          return paramNotificationBuilderWithBuilderAccessor;
        }
      }
      return null;
    }
    
    public RemoteViews makeHeadsUpContentView(NotificationBuilderWithBuilderAccessor paramNotificationBuilderWithBuilderAccessor)
    {
      if (Build.VERSION.SDK_INT >= 24) {
        return null;
      }
      if (mBuilder.getHeadsUpContentView() != null) {
        paramNotificationBuilderWithBuilderAccessor = mBuilder.getHeadsUpContentView();
      } else {
        paramNotificationBuilderWithBuilderAccessor = mBuilder.getContentView();
      }
      if (paramNotificationBuilderWithBuilderAccessor == null) {
        return null;
      }
      RemoteViews localRemoteViews = generateBigContentView();
      buildIntoRemoteViews(localRemoteViews, paramNotificationBuilderWithBuilderAccessor);
      if (Build.VERSION.SDK_INT >= 21) {
        setBackgroundColor(localRemoteViews);
      }
      return localRemoteViews;
    }
  }
  
  public class MediaStyle
    extends NotificationCompat.Style
  {
    private static final int MAX_MEDIA_BUTTONS = 5;
    private static final int MAX_MEDIA_BUTTONS_IN_COMPACT = 3;
    int[] mActionsToShowInCompact = null;
    PendingIntent mCancelButtonIntent;
    boolean mShowCancelButton;
    MediaSessionCompat.Token mToken;
    
    public MediaStyle() {}
    
    public MediaStyle()
    {
      setBuilder(this$1);
    }
    
    private RemoteViews generateMediaActionButton(NotificationCompat.Action paramAction)
    {
      int i;
      if (paramAction.getActionIntent() == null) {
        i = 1;
      } else {
        i = 0;
      }
      RemoteViews localRemoteViews = new RemoteViews(mBuilder.mContext.getPackageName(), R.layout.notification_media_action);
      localRemoteViews.setImageViewResource(R.id.action0, paramAction.getIcon());
      if (i == 0) {
        localRemoteViews.setOnClickPendingIntent(R.id.action0, paramAction.getActionIntent());
      }
      if (Build.VERSION.SDK_INT >= 15) {
        localRemoteViews.setContentDescription(R.id.action0, paramAction.getTitle());
      }
      return localRemoteViews;
    }
    
    public static MediaSessionCompat.Token getMediaSession(Notification paramNotification)
    {
      paramNotification = android.support.v4.package_7.NotificationCompat.getExtras(paramNotification);
      if (paramNotification != null) {
        if (Build.VERSION.SDK_INT >= 21)
        {
          paramNotification = paramNotification.getParcelable("android.mediaSession");
          if (paramNotification != null) {
            return MediaSessionCompat.Token.fromToken(paramNotification);
          }
        }
        else
        {
          Object localObject = BundleCompat.getBinder(paramNotification, "android.mediaSession");
          if (localObject != null)
          {
            paramNotification = Parcel.obtain();
            paramNotification.writeStrongBinder((IBinder)localObject);
            paramNotification.setDataPosition(0);
            localObject = (MediaSessionCompat.Token)MediaSessionCompat.Token.CREATOR.createFromParcel(paramNotification);
            paramNotification.recycle();
            return localObject;
          }
        }
      }
      return null;
    }
    
    public void apply(NotificationBuilderWithBuilderAccessor paramNotificationBuilderWithBuilderAccessor)
    {
      if (Build.VERSION.SDK_INT >= 21)
      {
        paramNotificationBuilderWithBuilderAccessor.getBuilder().setStyle(fillInMediaStyle(new Notification.MediaStyle()));
        return;
      }
      if (mShowCancelButton) {
        paramNotificationBuilderWithBuilderAccessor.getBuilder().setOngoing(true);
      }
    }
    
    Notification.MediaStyle fillInMediaStyle(Notification.MediaStyle paramMediaStyle)
    {
      if (mActionsToShowInCompact != null) {
        paramMediaStyle.setShowActionsInCompactView(mActionsToShowInCompact);
      }
      if (mToken != null) {
        paramMediaStyle.setMediaSession((MediaSession.Token)mToken.getToken());
      }
      return paramMediaStyle;
    }
    
    RemoteViews generateBigContentView()
    {
      Object localObject = mBuilder;
      MediaStyle localMediaStyle = this;
      int j = Math.min(mActions.size(), 5);
      RemoteViews localRemoteViews = localMediaStyle.applyStandardTemplate(false, localMediaStyle.getBigContentViewLayoutResource(j), false);
      localRemoteViews.removeAllViews(R.id.media_actions);
      localObject = localMediaStyle;
      if (j > 0)
      {
        int i = 0;
        for (;;)
        {
          localObject = localMediaStyle;
          if (i >= j) {
            break;
          }
          localObject = mBuilder;
          localObject = localMediaStyle.generateMediaActionButton((NotificationCompat.Action)mActions.get(i));
          localRemoteViews.addView(R.id.media_actions, (RemoteViews)localObject);
          i += 1;
        }
      }
      if (mShowCancelButton)
      {
        localRemoteViews.setViewVisibility(R.id.cancel_action, 0);
        localRemoteViews.setInt(R.id.cancel_action, "setAlpha", mBuilder.mContext.getResources().getInteger(R.integer.cancel_button_image_alpha));
        localRemoteViews.setOnClickPendingIntent(R.id.cancel_action, mCancelButtonIntent);
        return localRemoteViews;
      }
      localRemoteViews.setViewVisibility(R.id.cancel_action, 8);
      return localRemoteViews;
    }
    
    RemoteViews generateContentView()
    {
      RemoteViews localRemoteViews1 = applyStandardTemplate(false, getContentViewLayoutResource(), true);
      int k = mBuilder.mActions.size();
      int i;
      if (mActionsToShowInCompact == null) {
        i = 0;
      } else {
        i = Math.min(mActionsToShowInCompact.length, 3);
      }
      localRemoteViews1.removeAllViews(R.id.media_actions);
      if (i > 0)
      {
        int j = 0;
        while (j < i) {
          if (j < k)
          {
            RemoteViews localRemoteViews2 = generateMediaActionButton((NotificationCompat.Action)mBuilder.mActions.get(mActionsToShowInCompact[j]));
            localRemoteViews1.addView(R.id.media_actions, localRemoteViews2);
            j += 1;
          }
          else
          {
            throw new IllegalArgumentException(String.format("setShowActionsInCompactView: action %d out of bounds (max %d)", new Object[] { Integer.valueOf(j), Integer.valueOf(k - 1) }));
          }
        }
      }
      if (mShowCancelButton)
      {
        localRemoteViews1.setViewVisibility(R.id.end_padder, 8);
        localRemoteViews1.setViewVisibility(R.id.cancel_action, 0);
        localRemoteViews1.setOnClickPendingIntent(R.id.cancel_action, mCancelButtonIntent);
        localRemoteViews1.setInt(R.id.cancel_action, "setAlpha", mBuilder.mContext.getResources().getInteger(R.integer.cancel_button_image_alpha));
        return localRemoteViews1;
      }
      localRemoteViews1.setViewVisibility(R.id.end_padder, 0);
      localRemoteViews1.setViewVisibility(R.id.cancel_action, 8);
      return localRemoteViews1;
    }
    
    int getBigContentViewLayoutResource(int paramInt)
    {
      if (paramInt <= 3) {
        return R.layout.notification_template_big_media_narrow;
      }
      return R.layout.notification_template_big_media;
    }
    
    int getContentViewLayoutResource()
    {
      return R.layout.notification_template_media;
    }
    
    public RemoteViews makeBigContentView(NotificationBuilderWithBuilderAccessor paramNotificationBuilderWithBuilderAccessor)
    {
      if (Build.VERSION.SDK_INT >= 21) {
        return null;
      }
      return generateBigContentView();
    }
    
    public RemoteViews makeContentView(NotificationBuilderWithBuilderAccessor paramNotificationBuilderWithBuilderAccessor)
    {
      if (Build.VERSION.SDK_INT >= 21) {
        return null;
      }
      return generateContentView();
    }
    
    public MediaStyle setCancelButtonIntent(PendingIntent paramPendingIntent)
    {
      mCancelButtonIntent = paramPendingIntent;
      return this;
    }
    
    public MediaStyle setMediaSession(MediaSessionCompat.Token paramToken)
    {
      mToken = paramToken;
      return this;
    }
    
    public MediaStyle setShowActionsInCompactView(int... paramVarArgs)
    {
      mActionsToShowInCompact = paramVarArgs;
      return this;
    }
    
    public MediaStyle setShowCancelButton(boolean paramBoolean)
    {
      if (Build.VERSION.SDK_INT < 21) {
        mShowCancelButton = paramBoolean;
      }
      return this;
    }
  }
}
