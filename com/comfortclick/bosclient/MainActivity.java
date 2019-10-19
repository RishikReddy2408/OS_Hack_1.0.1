package com.comfortclick.bosclient;

import android.app.Activity;
import android.app.Dialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnCreateContextMenuListener;
import android.view.Window;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.comfortclick.bosclient.helpers.ProfileAdapter;
import com.comfortclick.bosclient.profiles.Controller;
import com.comfortclick.bosclient.profiles.Profile;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class MainActivity
  extends Activity
{
  private static final int CONTEXTMENU_CONNECT = 2;
  private static final int CONTEXTMENU_DELETEITEM = 0;
  private static final int CONTEXTMENU_EDITITEM = 1;
  private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
  private static Profile mCurrentProfile;
  private static SharedPreferences mSettings;
  public ArrayList<Profile> ProfileList = new ArrayList();
  private boolean mAutoLogin;
  private ListView mFavList;
  private boolean mPanelMode;
  
  public MainActivity() {}
  
  private void connectToPrivacyPolicy()
  {
    startActivityForResult(new Intent(getApplicationContext(), PrivacyPolicyActivity.class), 0);
  }
  
  private void createNotificationChannel()
  {
    if (Build.VERSION.SDK_INT >= 26)
    {
      NotificationChannel localNotificationChannel = new NotificationChannel("D1", "Default", 4);
      localNotificationChannel.setDescription("Notifications form bOS.");
      ((NotificationManager)getSystemService(NotificationManager.class)).createNotificationChannel(localNotificationChannel);
    }
  }
  
  private Profile getProfileFromID(String paramString)
  {
    Iterator localIterator = ProfileList.iterator();
    while (localIterator.hasNext())
    {
      Profile localProfile = (Profile)localIterator.next();
      if (accessID.equals(paramString)) {
        return localProfile;
      }
    }
    return null;
  }
  
  private void importControllers(String paramString1, String paramString2)
  {
    RetrieveJsonTask localRetrieveJsonTask = new RetrieveJsonTask();
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(getString(2131492931));
    localStringBuilder.append("WebService/BOSService.asmx/GetControllersJson");
    localRetrieveJsonTask.execute(new String[] { localStringBuilder.toString(), paramString1, paramString2 });
  }
  
  private void initListView()
  {
    mFavList = ((ListView)findViewById(2131165324));
    refreshFavListItems();
    mFavList.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener()
    {
      public void onCreateContextMenu(ContextMenu paramAnonymousContextMenu, View paramAnonymousView, ContextMenu.ContextMenuInfo paramAnonymousContextMenuInfo)
      {
        paramAnonymousContextMenu.add(0, 2, 0, getString(2131492868));
        paramAnonymousContextMenu.add(0, 1, 1, getString(2131492875));
        paramAnonymousContextMenu.add(0, 0, 2, getString(2131492870));
      }
    });
    mFavList.setOnItemClickListener(new AdapterView.OnItemClickListener()
    {
      public void onItemClick(AdapterView paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
      {
        paramAnonymousAdapterView = (Profile)mFavList.getAdapter().getItem(paramAnonymousInt);
        connect(paramAnonymousAdapterView);
      }
    });
  }
  
  private void loadSettings()
  {
    if (!mSettings.getBoolean("firstRun", false))
    {
      mSettings.edit().putBoolean("firstRun", true).apply();
      if (Build.VERSION.SDK_INT >= 19) {
        mSettings.edit().putBoolean("pref_hwaccel_key", true).apply();
      }
    }
    if ((!mSettings.getBoolean("pref_prevent_screen_lock_key", false)) && (!mPanelMode) && (!mAutoLogin)) {
      getWindow().clearFlags(128);
    } else {
      getWindow().addFlags(128);
    }
    ProfileList = com.comfortclick.bosclient.helpers.ProfileSettings.loadProfiles(getApplicationContext());
    initListView();
    invalidateOptionsMenu();
  }
  
  private void showImportLogin()
  {
    final Dialog localDialog = new Dialog(this);
    localDialog.setContentView(2131296284);
    localDialog.setTitle(2131492886);
    localDialog.setCancelable(true);
    final EditText localEditText1 = (EditText)localDialog.findViewById(2131165418);
    final EditText localEditText2 = (EditText)localDialog.findViewById(2131165417);
    ((RelativeLayout)localDialog.findViewById(2131165301)).requestFocus();
    ((Button)localDialog.findViewById(2131165233)).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        MainActivity.this.importControllers(localEditText1.getText().toString(), localEditText2.getText().toString());
        localDialog.hide();
      }
    });
    ((Button)localDialog.findViewById(2131165231)).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        localDialog.cancel();
      }
    });
    localDialog.show();
  }
  
  private void showProfileSettings(Profile paramProfile, boolean paramBoolean1, boolean paramBoolean2, int paramInt)
  {
    new ProfileSettings().showProfileSettings(this, paramProfile, paramBoolean1, paramBoolean2, paramInt);
  }
  
  private void startprefActivity()
  {
    Intent localIntent = new Intent("android.intent.action.MAIN");
    localIntent.setClass(this, PreferencesActivity.class);
    startActivityForResult(localIntent, 1);
  }
  
  public void connect(Profile paramProfile)
  {
    mCurrentProfile = paramProfile;
    paramProfile = mCurrentProfile;
    try
    {
      long l = new Date().getTime();
      lastTime = l;
      paramProfile = getApplicationContext();
      localObject1 = ProfileList;
      com.comfortclick.bosclient.helpers.ProfileSettings.saveProfiles(paramProfile, (ArrayList)localObject1);
      paramProfile = new Intent(getApplicationContext(), WebActivity.class);
      localObject1 = new Gson();
      localObject2 = mCurrentProfile;
      paramProfile.putExtra("profile", ((Gson)localObject1).toJson(localObject2));
      startActivityForResult(paramProfile, 0);
      return;
    }
    catch (Exception paramProfile)
    {
      Object localObject1 = getApplicationContext();
      Object localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append("Error: ");
      ((StringBuilder)localObject2).append(paramProfile.getMessage());
      Toast.makeText((Context)localObject1, ((StringBuilder)localObject2).toString(), 0).show();
    }
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    if ((paramInt2 != 1) && (paramInt2 != 2))
    {
      loadSettings();
      return;
    }
    showProfileSettings(mCurrentProfile, false, true, paramInt2);
  }
  
  public boolean onContextItemSelected(MenuItem paramMenuItem)
  {
    Object localObject = (AdapterView.AdapterContextMenuInfo)paramMenuItem.getMenuInfo();
    localObject = (Profile)mFavList.getAdapter().getItem(position);
    switch (paramMenuItem.getItemId())
    {
    default: 
      return false;
    case 2: 
      connect((Profile)localObject);
      return true;
    case 1: 
      showProfileSettings((Profile)localObject, false, false, -1);
      return true;
    }
    ProfileList.remove(localObject);
    com.comfortclick.bosclient.helpers.ProfileSettings.saveProfiles(getApplicationContext(), ProfileList);
    refreshFavListItems();
    return true;
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    startService(new Intent(this, NotificationServiceListener.class));
    createNotificationChannel();
    mSettings = PreferenceManager.getDefaultSharedPreferences(this);
    setContentView(2131296286);
    loadSettings();
    mPanelMode = mSettings.getBoolean("pref_enable_panel_mode_key", false);
    mAutoLogin = mSettings.getBoolean("pref_enable_auto_login_key", false);
    if (((mPanelMode) || (mAutoLogin)) && (ProfileList.size() > 0)) {
      connect((Profile)ProfileList.get(0));
    }
    paramBundle = getIntent();
    if (paramBundle.getStringExtra("NotificationServerID") != null)
    {
      paramBundle = getProfileFromID(paramBundle.getStringExtra("NotificationServerID"));
      if (paramBundle != null) {
        connect(paramBundle);
      }
    }
    paramBundle = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
    if (paramBundle.getString("pref_device_name_key", "").equalsIgnoreCase(""))
    {
      paramBundle = paramBundle.edit();
      paramBundle.putString("pref_device_name_key", Build.MODEL);
      paramBundle.apply();
    }
  }
  
  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    paramMenu.clear();
    getMenuInflater().inflate(2131361792, paramMenu);
    return true;
  }
  
  public void onNewIntent(Intent paramIntent)
  {
    if (paramIntent.getStringExtra("NotificationServerID") != null)
    {
      paramIntent = getProfileFromID(paramIntent.getStringExtra("NotificationServerID"));
      if (paramIntent != null) {
        connect(paramIntent);
      }
    }
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    switch (paramMenuItem.getItemId())
    {
    default: 
      break;
    case 2131165306: 
    case 2131165308: 
      return super.onOptionsItemSelected(paramMenuItem);
    case 2131165311: 
      startprefActivity();
      return true;
    case 2131165310: 
      connectToPrivacyPolicy();
      return true;
    case 2131165309: 
      showImportLogin();
      return true;
    case 2131165307: 
      finish();
      return true;
    }
    showProfileSettings(new Profile(Boolean.valueOf(false), "", "", "", ""), true, false, -1);
    return true;
  }
  
  public void onResume()
  {
    super.onResume();
    refreshFavListItems();
  }
  
  public void refreshFavListItems()
  {
    Collections.sort(ProfileList, new Comparator()
    {
      public int compare(Profile paramAnonymousProfile1, Profile paramAnonymousProfile2)
      {
        return (int)(lastTime - lastTime);
      }
    });
    ProfileAdapter localProfileAdapter = new ProfileAdapter(this, 2131296285, ProfileList);
    mFavList.setAdapter(localProfileAdapter);
  }
  
  class RetrieveJsonTask
    extends AsyncTask<String, Void, String>
  {
    RetrieveJsonTask() {}
    
    private void refreshProfiles(String paramString)
    {
      try
      {
        Object localObject = new Gson();
        paramString = ((Gson)localObject).fromJson(paramString, new TypeToken() {}.getType());
        paramString = (List)paramString;
        Iterator localIterator1 = paramString.iterator();
        for (;;)
        {
          boolean bool = localIterator1.hasNext();
          if (!bool) {
            break;
          }
          paramString = localIterator1.next();
          Controller localController = (Controller)paramString;
          localObject = null;
          paramString = ProfileList;
          Iterator localIterator2 = paramString.iterator();
          do
          {
            do
            {
              bool = localIterator2.hasNext();
              paramString = (String)localObject;
              if (!bool) {
                break;
              }
              paramString = localIterator2.next();
              paramString = (Profile)paramString;
            } while (manual);
            String str1 = accessID;
            String str2 = AccessID;
            bool = str1.equals(str2);
          } while (!bool);
          if (paramString == null)
          {
            paramString = new Profile(localController);
            localObject = ProfileList;
            ((ArrayList)localObject).add(paramString);
          }
          else
          {
            paramString.update(localController);
          }
        }
        paramString = MainActivity.this;
        paramString = paramString.getApplicationContext();
        localObject = ProfileList;
        com.comfortclick.bosclient.helpers.ProfileSettings.saveProfiles(paramString, (ArrayList)localObject);
        paramString = MainActivity.this;
        paramString.refreshFavListItems();
        return;
      }
      catch (Exception paramString)
      {
        for (;;) {}
      }
      Toast.makeText(getApplicationContext(), getString(2131492879), 1).show();
    }
    
    protected String doInBackground(String... paramVarArgs)
    {
      String str1 = "";
      Object localObject2 = paramVarArgs[0];
      localObject1 = str1;
      try
      {
        localObject2 = new URL((String)localObject2).openConnection();
        localObject2 = (HttpURLConnection)localObject2;
        localObject1 = str1;
        ((HttpURLConnection)localObject2).setReadTimeout(15000);
        localObject1 = str1;
        ((HttpURLConnection)localObject2).setConnectTimeout(15000);
        localObject1 = str1;
        ((HttpURLConnection)localObject2).setRequestMethod("POST");
        localObject1 = str1;
        ((HttpURLConnection)localObject2).setDoInput(true);
        localObject1 = str1;
        ((HttpURLConnection)localObject2).setDoOutput(true);
        localObject1 = str1;
        Object localObject3 = ((HttpURLConnection)localObject2).getOutputStream();
        localObject1 = str1;
        BufferedWriter localBufferedWriter = new BufferedWriter(new OutputStreamWriter((OutputStream)localObject3, "UTF-8"));
        localObject1 = str1;
        StringBuilder localStringBuilder = new StringBuilder();
        localObject1 = str1;
        localStringBuilder.append("username=");
        String str2 = paramVarArgs[1];
        localObject1 = str1;
        localStringBuilder.append(str2);
        localObject1 = str1;
        localStringBuilder.append("&password=");
        paramVarArgs = paramVarArgs[2];
        localObject1 = str1;
        localStringBuilder.append(paramVarArgs);
        localObject1 = str1;
        localStringBuilder.append("");
        localObject1 = str1;
        localBufferedWriter.write(localStringBuilder.toString());
        localObject1 = str1;
        localBufferedWriter.flush();
        localObject1 = str1;
        localBufferedWriter.close();
        localObject1 = str1;
        ((OutputStream)localObject3).close();
        localObject1 = str1;
        paramVarArgs = ((HttpURLConnection)localObject2).getInputStream();
        localObject1 = str1;
        localObject2 = new BufferedReader(new InputStreamReader(paramVarArgs));
        for (paramVarArgs = str1;; paramVarArgs = ((StringBuilder)localObject3).toString())
        {
          localObject1 = paramVarArgs;
          str1 = ((BufferedReader)localObject2).readLine();
          localObject1 = paramVarArgs;
          if (str1 == null) {
            break;
          }
          localObject1 = paramVarArgs;
          localObject3 = new StringBuilder();
          localObject1 = paramVarArgs;
          ((StringBuilder)localObject3).append(paramVarArgs);
          localObject1 = paramVarArgs;
          ((StringBuilder)localObject3).append(str1);
          localObject1 = paramVarArgs;
        }
        return localObject1;
      }
      catch (Exception paramVarArgs)
      {
        Log.d(paramVarArgs.getMessage(), paramVarArgs.toString());
      }
    }
    
    protected void onPostExecute(String paramString)
    {
      if (paramString.isEmpty())
      {
        Toast.makeText(getApplicationContext(), getString(2131492878), 1).show();
        return;
      }
      refreshProfiles(paramString);
    }
  }
}
