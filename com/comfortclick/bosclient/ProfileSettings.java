package com.comfortclick.bosclient;

import android.app.Dialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.comfortclick.bosclient.profiles.Controller;
import com.comfortclick.bosclient.profiles.Profile;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class ProfileSettings
{
  MainActivity mActivity;
  
  public ProfileSettings() {}
  
  private String appendPrefix(String paramString)
  {
    Object localObject;
    if ((paramString != null) && (!paramString.isEmpty()))
    {
      localObject = paramString;
      if (!paramString.startsWith("http"))
      {
        localObject = new StringBuilder();
        ((StringBuilder)localObject).append("https://");
        ((StringBuilder)localObject).append(paramString);
        localObject = ((StringBuilder)localObject).toString();
      }
      if (!((String)localObject).endsWith("/"))
      {
        paramString = new StringBuilder();
        paramString.append((String)localObject);
        paramString.append("/");
        return paramString.toString();
      }
    }
    else
    {
      return "";
    }
    return localObject;
  }
  
  public void showProfileSettings(final MainActivity paramMainActivity, final Profile paramProfile, final boolean paramBoolean1, final boolean paramBoolean2, int paramInt)
  {
    mActivity = paramMainActivity;
    final Dialog localDialog = new Dialog(paramMainActivity);
    localDialog.setContentView(2131296302);
    localDialog.setCancelable(true);
    Object localObject = (LinearLayout)localDialog.findViewById(2131165316);
    final CheckBox localCheckBox1 = (CheckBox)localDialog.findViewById(2131165242);
    final TextView localTextView6 = (TextView)localDialog.findViewById(2131165402);
    final TextView localTextView1 = (TextView)localDialog.findViewById(2131165268);
    final TextView localTextView2 = (TextView)localDialog.findViewById(2131165271);
    paramMainActivity = (TextView)localDialog.findViewById(2131165272);
    final TextView localTextView3 = (TextView)localDialog.findViewById(2131165269);
    final TextView localTextView4 = (TextView)localDialog.findViewById(2131165273);
    final TextView localTextView5 = (TextView)localDialog.findViewById(2131165270);
    final CheckBox localCheckBox2 = (CheckBox)localDialog.findViewById(2131165243);
    Button localButton1 = (Button)localDialog.findViewById(2131165235);
    Button localButton2 = (Button)localDialog.findViewById(2131165234);
    final RelativeLayout localRelativeLayout = (RelativeLayout)localDialog.findViewById(2131165346);
    if (paramBoolean1) {
      localDialog.setTitle(2131492865);
    } else {
      localDialog.setTitle(profileName);
    }
    localCheckBox1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
    {
      public void onCheckedChanged(CompoundButton paramAnonymousCompoundButton, boolean paramAnonymousBoolean)
      {
        if (localCheckBox1.isChecked())
        {
          localTextView1.setVisibility(8);
          localTextView6.setVisibility(8);
          val$layoutMore.setVisibility(0);
          localRelativeLayout.requestFocus();
          return;
        }
        localTextView1.setVisibility(0);
        localTextView6.setVisibility(0);
        val$layoutMore.setVisibility(8);
      }
    });
    if (manual)
    {
      localTextView1.setVisibility(8);
      localTextView6.setVisibility(8);
      ((View)localObject).setVisibility(0);
    }
    else
    {
      localTextView1.setVisibility(0);
      localTextView6.setVisibility(0);
      ((View)localObject).setVisibility(8);
    }
    localCheckBox1.setChecked(manual);
    localTextView1.setText(accessID);
    localTextView2.setText(profileName);
    paramMainActivity.setText(serverAddress);
    localTextView3.setText(localAddress);
    localTextView4.setText(username);
    localTextView5.setText(password);
    localCheckBox2.setChecked(rememberMe);
    if ((!localCheckBox1.isChecked()) && (!paramBoolean1) && (!paramBoolean2))
    {
      localRelativeLayout.requestFocus();
    }
    else
    {
      localObject = paramMainActivity;
      if (paramInt == 2)
      {
        Toast.makeText(mActivity, mActivity.getString(2131492876), 1).show();
        if (localTextView3.getText().toString().isEmpty())
        {
          localTextView3.requestFocus();
        }
        else if (((TextView)localObject).getText().toString().isEmpty())
        {
          ((View)localObject).requestFocus();
        }
        else if (paramInt == 1)
        {
          Toast.makeText(mActivity, mActivity.getString(2131492877), 1).show();
          if (localTextView4.getText().toString().isEmpty()) {
            localTextView4.requestFocus();
          } else if (localTextView5.getText().toString().isEmpty()) {
            localTextView5.requestFocus();
          } else {
            localTextView4.requestFocus();
          }
        }
        else if (localCheckBox1.isChecked())
        {
          localRelativeLayout.requestFocus();
        }
      }
    }
    localButton1.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        paramProfilemanual = localCheckBox1.isChecked();
        paramProfileusername = localTextView4.getText().toString(); //getst the username from the user.
        paramProfilepassword = localTextView5.getText().toString();  //gets the password from the user
        paramProfilerememberMe = localCheckBox2.isChecked();
        if (paramProfilemanual)
        {
          paramProfileprofileName = localTextView2.getText().toString();
          paramProfileserverAddress = ProfileSettings.this.appendPrefix(paramMainActivity.getText().toString());
          localObject = paramProfile;
          paramAnonymousView = this;
          localAddress = ProfileSettings.this.appendPrefix(localTextView3.getText().toString());
          if (paramBoolean1) {
            this$0.mActivity.ProfileList.add(paramProfile);
          }
          paramAnonymousView = this;
          localDialog.dismiss();
          if (paramBoolean2)
          {
            this$0.mActivity.connect(paramProfile);
            return;
          }
          com.comfortclick.bosclient.helpers.ProfileSettings.saveProfiles(this$0.mActivity, this$0.mActivity.ProfileList);
          this$0.mActivity.refreshFavListItems();
          return;
        }
        paramProfileaccessID = localTextView1.getText().toString();
        paramAnonymousView = new ProfileSettings.RetrieveControllerJsonTask(ProfileSettings.this);
        Object localObject = new StringBuilder();
        ((StringBuilder)localObject).append(mActivity.getString(2131492931));
        ((StringBuilder)localObject).append("WebService/BOSService.asmx/GetControllerJson");
        paramAnonymousView = (ProfileSettings.RetrieveControllerJsonTask)paramAnonymousView.execute(new String[] { ((StringBuilder)localObject).toString(), paramProfileaccessID });
        dialog = localDialog;
        profile = paramProfile;
        addingProfile = paramBoolean1;
        connect = paramBoolean2;
      }
    });
    localButton2.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        localDialog.cancel();
      }
    });
    localDialog.show();
  }
  
  class RetrieveControllerJsonTask
    extends AsyncTask<String, Void, String>
  {
    public boolean addingProfile;
    public boolean connect;
    public Dialog dialog;
    public Profile profile;
    
    RetrieveControllerJsonTask() {}
    
    protected String doInBackground(String... paramVarArgs)
    {
      String str = "";
      Object localObject2 = paramVarArgs[0];
      localObject1 = str;
      try
      {
        localObject2 = new URL((String)localObject2).openConnection();
        localObject2 = (HttpURLConnection)localObject2;
        localObject1 = str;
        ((HttpURLConnection)localObject2).setReadTimeout(15000);
        localObject1 = str;
        ((HttpURLConnection)localObject2).setConnectTimeout(15000);
        localObject1 = str;
        ((HttpURLConnection)localObject2).setRequestMethod("POST");
        localObject1 = str;
        ((HttpURLConnection)localObject2).setDoInput(true);
        localObject1 = str;
        ((HttpURLConnection)localObject2).setDoOutput(true);
        localObject1 = str;
        Object localObject3 = ((HttpURLConnection)localObject2).getOutputStream();
        localObject1 = str;
        BufferedWriter localBufferedWriter = new BufferedWriter(new OutputStreamWriter((OutputStream)localObject3, "UTF-8"));
        localObject1 = str;
        StringBuilder localStringBuilder = new StringBuilder();
        localObject1 = str;
        localStringBuilder.append("accessID=");
        paramVarArgs = paramVarArgs[1];
        localObject1 = str;
        localStringBuilder.append(paramVarArgs);
        localObject1 = str;
        localBufferedWriter.write(localStringBuilder.toString());
        localObject1 = str;
        localBufferedWriter.flush();
        localObject1 = str;
        localBufferedWriter.close();
        localObject1 = str;
        ((OutputStream)localObject3).close();
        localObject1 = str;
        localObject2 = new BufferedReader(new InputStreamReader(((HttpURLConnection)localObject2).getInputStream()));
        for (paramVarArgs = str;; paramVarArgs = ((StringBuilder)localObject3).toString())
        {
          localObject1 = paramVarArgs;
          str = ((BufferedReader)localObject2).readLine();
          localObject1 = paramVarArgs;
          if (str == null) {
            break;
          }
          localObject1 = paramVarArgs;
          localObject3 = new StringBuilder();
          localObject1 = paramVarArgs;
          ((StringBuilder)localObject3).append(paramVarArgs);
          localObject1 = paramVarArgs;
          ((StringBuilder)localObject3).append(str);
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
      try
      {
        Object localObject1 = new Gson();
        Object localObject2 = new TypeToken() {}.getType();
        boolean bool = paramString.isEmpty();
        if (bool)
        {
          paramString = mActivity;
          localObject1 = mActivity;
          Toast.makeText(paramString, ((Context)localObject1).getString(2131492880), 1).show();
          return;
        }
        paramString = ((Gson)localObject1).fromJson(paramString, (Type)localObject2);
        paramString = (Controller)paramString;
        profile.accessID = AccessID;
        profile.profileName = ServerName;
        localObject1 = profile;
        localObject2 = ProfileSettings.this;
        String str = PublicAddress;
        localObject2 = ((ProfileSettings)localObject2).appendPrefix(str);
        serverAddress = ((String)localObject2);
        localObject1 = profile;
        localObject2 = ProfileSettings.this;
        str = LocalAddress;
        localObject2 = ((ProfileSettings)localObject2).appendPrefix(str);
        localAddress = ((String)localObject2);
        profile.publicKey = PublicKey;
        profile.gatewayAddress = GatewayAddress;
        if (addingProfile)
        {
          paramString = mActivity.ProfileList;
          localObject1 = profile;
          paramString.add(localObject1);
        }
        paramString = dialog;
        paramString.hide();
        if (connect)
        {
          paramString = mActivity;
          localObject1 = profile;
          paramString.connect((Profile)localObject1);
          return;
        }
        paramString = mActivity;
        localObject1 = mActivity.ProfileList;
        com.comfortclick.bosclient.helpers.ProfileSettings.saveProfiles(paramString, (ArrayList)localObject1);
        paramString = mActivity;
        paramString.refreshFavListItems();
        return;
      }
      catch (Exception paramString)
      {
        for (;;) {}
      }
      Toast.makeText(mActivity, mActivity.getString(2131492880), 1).show();
    }
  }
}
