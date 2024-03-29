package com.google.android.android.common;

import android.accounts.Account;
import android.content.Intent;
import android.os.Bundle;
import com.google.android.android.common.internal.Preconditions;
import java.util.ArrayList;

public final class AccountPicker
{
  private AccountPicker() {}
  
  public static Intent newChooseAccountIntent(Account paramAccount, ArrayList paramArrayList, String[] paramArrayOfString1, boolean paramBoolean, String paramString1, String paramString2, String[] paramArrayOfString2, Bundle paramBundle)
  {
    Intent localIntent = new Intent();
    Preconditions.checkArgument(true, "We only support hostedDomain filter for account chip styled account picker");
    localIntent.setAction("com.google.android.gms.common.account.CHOOSE_ACCOUNT");
    localIntent.setPackage("com.google.android.gms");
    localIntent.putExtra("allowableAccounts", paramArrayList);
    localIntent.putExtra("allowableAccountTypes", paramArrayOfString1);
    localIntent.putExtra("addAccountOptions", paramBundle);
    localIntent.putExtra("selectedAccount", paramAccount);
    localIntent.putExtra("alwaysPromptForAccount", paramBoolean);
    localIntent.putExtra("descriptionTextOverride", paramString1);
    localIntent.putExtra("authTokenType", paramString2);
    localIntent.putExtra("addAccountRequiredFeatures", paramArrayOfString2);
    localIntent.putExtra("setGmsCoreAccount", false);
    localIntent.putExtra("overrideTheme", 0);
    localIntent.putExtra("overrideCustomTheme", 0);
    localIntent.putExtra("hostedDomainFilter", null);
    return localIntent;
  }
}
