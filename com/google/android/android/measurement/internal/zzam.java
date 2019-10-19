package com.google.android.android.measurement.internal;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build.VERSION;
import com.google.android.gms.common.util.VisibleForTesting;
import java.io.File;

@VisibleForTesting
final class zzam
  extends SQLiteOpenHelper
{
  zzam(zzal paramZzal, Context paramContext, String paramString)
  {
    super(paramContext, paramString, null, 1);
  }
  
  public final SQLiteDatabase getWritableDatabase()
    throws SQLiteException
  {
    try
    {
      localSQLiteDatabase = super.getWritableDatabase();
      return localSQLiteDatabase;
    }
    catch (SQLiteDatabaseLockedException localSQLiteDatabaseLockedException)
    {
      SQLiteDatabase localSQLiteDatabase;
      throw localSQLiteDatabaseLockedException;
    }
    catch (SQLiteException localSQLiteException2)
    {
      for (;;) {}
    }
    zzals.zzgo().zzjd().zzbx("Opening the local database failed, dropping and recreating it");
    if (!zzals.getContext().getDatabasePath("google_app_measurement_local.db").delete()) {
      zzals.zzgo().zzjd().append("Failed to delete corrupted local db file", "google_app_measurement_local.db");
    }
    try
    {
      localSQLiteDatabase = super.getWritableDatabase();
      return localSQLiteDatabase;
    }
    catch (SQLiteException localSQLiteException1)
    {
      zzals.zzgo().zzjd().append("Failed to open local database. Events will bypass local storage", localSQLiteException1);
      return null;
    }
  }
  
  public final void onCreate(SQLiteDatabase paramSQLiteDatabase)
  {
    Marker.setImage(zzals.zzgo(), paramSQLiteDatabase);
  }
  
  public final void onDowngrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2) {}
  
  public final void onOpen(SQLiteDatabase paramSQLiteDatabase)
  {
    if (Build.VERSION.SDK_INT < 15)
    {
      Object localObject2 = null;
      try
      {
        Object localObject1 = paramSQLiteDatabase.rawQuery("PRAGMA journal_mode=memory", null);
        try
        {
          ((Cursor)localObject1).moveToFirst();
          if (localObject1 == null) {
            break label61;
          }
          ((Cursor)localObject1).close();
        }
        catch (Throwable localThrowable2)
        {
          paramSQLiteDatabase = (SQLiteDatabase)localObject1;
          localObject1 = localThrowable2;
        }
        if (paramSQLiteDatabase == null) {
          break label59;
        }
      }
      catch (Throwable localThrowable1)
      {
        paramSQLiteDatabase = localThrowable2;
      }
      paramSQLiteDatabase.close();
      label59:
      throw localThrowable1;
    }
    label61:
    Marker.update(zzals.zzgo(), paramSQLiteDatabase, "messages", "create table if not exists messages ( type INTEGER NOT NULL, entry BLOB NOT NULL)", "type,entry", null);
  }
  
  public final void onUpgrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2) {}
}
