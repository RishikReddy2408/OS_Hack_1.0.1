package com.google.android.android.measurement.internal;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.text.TextUtils;
import java.io.File;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public final class Marker
{
  private static Set getValues(SQLiteDatabase paramSQLiteDatabase, String paramString)
  {
    HashSet localHashSet = new HashSet();
    StringBuilder localStringBuilder = new StringBuilder(String.valueOf(paramString).length() + 22);
    localStringBuilder.append("SELECT * FROM ");
    localStringBuilder.append(paramString);
    localStringBuilder.append(" LIMIT 0");
    paramSQLiteDatabase = paramSQLiteDatabase.rawQuery(localStringBuilder.toString(), null);
    try
    {
      Collections.addAll(localHashSet, paramSQLiteDatabase.getColumnNames());
      paramSQLiteDatabase.close();
      return localHashSet;
    }
    catch (Throwable paramString)
    {
      paramSQLiteDatabase.close();
      throw paramString;
    }
  }
  
  private static boolean remove(zzap paramZzap, SQLiteDatabase paramSQLiteDatabase, String paramString)
  {
    boolean bool;
    if (paramZzap != null)
    {
      Object localObject = null;
      SQLiteDatabase localSQLiteDatabase = null;
      try
      {
        paramSQLiteDatabase = paramSQLiteDatabase.query("SQLITE_MASTER", new String[] { "name" }, "name=?", new String[] { paramString }, null, null, null);
        try
        {
          bool = paramSQLiteDatabase.moveToFirst();
          if (paramSQLiteDatabase == null) {
            break label128;
          }
          paramSQLiteDatabase.close();
          return bool;
        }
        catch (Throwable paramZzap)
        {
          break label106;
        }
        catch (SQLiteException localSQLiteException1) {}
        localSQLiteDatabase = paramSQLiteDatabase;
      }
      catch (Throwable paramZzap)
      {
        paramSQLiteDatabase = localSQLiteDatabase;
      }
      catch (SQLiteException localSQLiteException2)
      {
        paramSQLiteDatabase = localObject;
      }
      paramZzap.zzjg().append("Error querying for table", paramString, localSQLiteException2);
      if (paramSQLiteDatabase != null)
      {
        paramSQLiteDatabase.close();
        return false;
        label106:
        if (paramSQLiteDatabase != null) {
          paramSQLiteDatabase.close();
        }
        throw paramZzap;
      }
    }
    else
    {
      throw new IllegalArgumentException("Monitor must not be null");
      label128:
      return bool;
    }
    return false;
  }
  
  static void setImage(zzap paramZzap, SQLiteDatabase paramSQLiteDatabase)
  {
    if (paramZzap != null)
    {
      paramSQLiteDatabase = new File(paramSQLiteDatabase.getPath());
      if (!paramSQLiteDatabase.setReadable(false, false)) {
        paramZzap.zzjg().zzbx("Failed to turn off database read permission");
      }
      if (!paramSQLiteDatabase.setWritable(false, false)) {
        paramZzap.zzjg().zzbx("Failed to turn off database write permission");
      }
      if (!paramSQLiteDatabase.setReadable(true, true)) {
        paramZzap.zzjg().zzbx("Failed to turn on database read permission for owner");
      }
      if (!paramSQLiteDatabase.setWritable(true, true)) {
        paramZzap.zzjg().zzbx("Failed to turn on database write permission for owner");
      }
    }
    else
    {
      throw new IllegalArgumentException("Monitor must not be null");
    }
  }
  
  static void update(zzap paramZzap, SQLiteDatabase paramSQLiteDatabase, String paramString1, String paramString2, String paramString3, String[] paramArrayOfString)
    throws SQLiteException
  {
    if (paramZzap != null)
    {
      if (!remove(paramZzap, paramSQLiteDatabase, paramString1)) {
        paramSQLiteDatabase.execSQL(paramString2);
      }
      if (paramZzap != null) {}
      try
      {
        paramString2 = getValues(paramSQLiteDatabase, paramString1);
        String[] arrayOfString = paramString3.split(",");
        int k = arrayOfString.length;
        int j = 0;
        int i = 0;
        while (i < k)
        {
          paramString3 = arrayOfString[i];
          bool = paramString2.remove(paramString3);
          if (bool)
          {
            i += 1;
          }
          else
          {
            i = String.valueOf(paramString1).length();
            j = String.valueOf(paramString3).length();
            paramSQLiteDatabase = new StringBuilder(i + 35 + j);
            paramSQLiteDatabase.append("Table ");
            paramSQLiteDatabase.append(paramString1);
            paramSQLiteDatabase.append(" is missing required column: ");
            paramSQLiteDatabase.append(paramString3);
            throw new SQLiteException(paramSQLiteDatabase.toString());
          }
        }
        if (paramArrayOfString != null)
        {
          i = j;
          while (i < paramArrayOfString.length)
          {
            paramString3 = paramArrayOfString[i];
            bool = paramString2.remove(paramString3);
            if (!bool)
            {
              paramString3 = paramArrayOfString[(i + 1)];
              paramSQLiteDatabase.execSQL(paramString3);
            }
            i += 2;
          }
        }
        boolean bool = paramString2.isEmpty();
        if (bool) {
          return;
        }
        paramZzap.zzjg().append("Table has extra columns. table, columns", paramString1, TextUtils.join(", ", paramString2));
        return;
      }
      catch (SQLiteException paramSQLiteDatabase)
      {
        paramZzap.zzjd().append("Failed to verify columns on table that was just created", paramString1);
        throw paramSQLiteDatabase;
      }
      paramSQLiteDatabase = new IllegalArgumentException("Monitor must not be null");
      throw paramSQLiteDatabase;
    }
    throw new IllegalArgumentException("Monitor must not be null");
  }
}
