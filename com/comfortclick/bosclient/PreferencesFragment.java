package com.comfortclick.bosclient;

import android.app.Activity;
import android.app.Fragment;
import android.content.ComponentName;
import android.content.ContextWrapper;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.PreferenceCategory;
import android.preference.PreferenceFragment;
import android.preference.PreferenceGroup;
import android.preference.PreferenceManager;
import android.preference.TwoStatePreference;
import android.view.Window;
import android.widget.Toast;

public class PreferencesFragment
  extends PreferenceFragment
  implements SharedPreferences.OnSharedPreferenceChangeListener
{
  private static SharedPreferences mPrefs;
  
  public PreferencesFragment() {}
  
  private void initSummary(Preference paramPreference)
  {
    if ((paramPreference instanceof PreferenceCategory))
    {
      paramPreference = (PreferenceCategory)paramPreference;
      int i = 0;
      while (i < paramPreference.getPreferenceCount())
      {
        initSummary(paramPreference.getPreference(i));
        i += 1;
      }
    }
    updatePrefSummary(paramPreference);
  }
  
  private void updatePrefSummary(Preference paramPreference)
  {
    if ((paramPreference instanceof EditTextPreference)) {
      paramPreference.setSummary(((EditTextPreference)paramPreference).getText());
    }
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    addPreferencesFromResource(2131689474);
    mPrefs = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
    getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    if (mPrefs.getString("pref_device_name_key", "").equalsIgnoreCase(""))
    {
      paramBundle = mPrefs.edit();
      paramBundle.putString("pref_device_name_key", Build.MODEL);
      paramBundle.apply();
    }
    paramBundle = (EditTextPreference)getPreferenceManager().findPreference("pref_device_name_key");
    String str = mPrefs.getString("pref_device_name_key", "");
    paramBundle.setText(str);
    paramBundle.setSummary(str);
    int i = 0;
    while (i < getPreferenceScreen().getPreferenceCount())
    {
      initSummary(getPreferenceScreen().getPreference(i));
      i += 1;
    }
  }
  
  public void onPause()
  {
    super.onPause();
    getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
  }
  
  public void onResume()
  {
    super.onResume();
    getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
  }
  
  public void onSharedPreferenceChanged(SharedPreferences paramSharedPreferences, String paramString)
  {
    paramSharedPreferences = findPreference(paramString);
    if ((paramSharedPreferences instanceof EditTextPreference))
    {
      EditTextPreference localEditTextPreference = (EditTextPreference)paramSharedPreferences;
      SharedPreferences.Editor localEditor;
      if (localEditTextPreference.getKey().equalsIgnoreCase("pref_device_name_key"))
      {
        localEditor = mPrefs.edit();
        String str = mPrefs.getString("pref_device_name_key", "");
        if (str.equalsIgnoreCase("")) {
          localEditor.putString(str, Build.MODEL);
        } else {
          localEditor.putString(str, localEditTextPreference.getText());
        }
        localEditor.apply();
      }
      else if (localEditTextPreference.getKey().equalsIgnoreCase("pref_set_dimming_time_key")) //sets the time for which the lights have to be dimmed according to the user.
      {
        if (Integer.parseInt(localEditTextPreference.getText()) <= 0)
        {
          Toast.makeText(getActivity(), getString(2131492872), 1).show();
          return;
        }
        localEditor = mPrefs.edit();
        localEditor.putString("pref_set_dimming_time_key", localEditTextPreference.getText());
        localEditor.apply();
      }
      else if (localEditTextPreference.getKey().equalsIgnoreCase("pref_set_dimming_value_key")) //Sets tha amount to which the lights have to be dimmed.
      {
        int i = Integer.parseInt(localEditTextPreference.getText());
        if ((i > 0) && (i <= 100))
        {
          localEditor = mPrefs.edit();
          localEditor.putString("pref_set_dimming_value_key", localEditTextPreference.getText());
          localEditor.apply();
        }
        else
        {
          Toast.makeText(getActivity(), getString(2131492871), 1).show();
          return;
        }
      }
      paramSharedPreferences.setSummary(localEditTextPreference.getText());
    }
    else if ((paramSharedPreferences instanceof CheckBoxPreference))
    {
      paramSharedPreferences = (CheckBoxPreference)paramSharedPreferences;
      if (paramSharedPreferences.getKey().equalsIgnoreCase("pref_enable_panel_mode_key")) {
        if (Boolean.valueOf(paramSharedPreferences.isChecked()).booleanValue())
        {
          getActivity().getWindow().addFlags(128);
          paramSharedPreferences = new ComponentName(getActivity(), StartAtBoot.class);
          getActivity().getPackageManager().setComponentEnabledSetting(paramSharedPreferences, 1, 1);
        }
        else
        {
          getActivity().getWindow().clearFlags(128);
          paramSharedPreferences = new ComponentName(getActivity(), StartAtBoot.class);
          getActivity().getPackageManager().setComponentEnabledSetting(paramSharedPreferences, 2, 1);
        }
      }
    }
    updatePrefSummary(findPreference(paramString));
  }
}
