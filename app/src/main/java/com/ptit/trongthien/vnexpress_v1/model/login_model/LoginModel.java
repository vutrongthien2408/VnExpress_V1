package com.ptit.trongthien.vnexpress_v1.model.login_model;

import android.content.Context;

import com.ptit.trongthien.vnexpress_v1.database.AccountData;
import com.ptit.trongthien.vnexpress_v1.model.entity.Account;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by TrongThien on 7/21/2017.
 */

public class LoginModel {

    public LoginModel() {
    }
    //lay du lieu tu database
    public Set<Account> getAccountData(Context context) {

        AccountData accountData = new AccountData(context);
        Set<Account> accounts = accountData.readAccount();
        return accounts;
    }

}
