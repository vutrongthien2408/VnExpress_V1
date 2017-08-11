package com.ptit.trongthien.vnexpress_v1.model.register_model;

import android.content.Context;

import com.ptit.trongthien.vnexpress_v1.database.AccountData;
import com.ptit.trongthien.vnexpress_v1.model.entity.Account;

import java.util.Set;

/**
 * Created by TrongThien on 7/21/2017.
 */

public class RegisterModel {
    public RegisterModel() {
    }

    // lay du lieu tu database
    public Set<Account> getAccount(Context context) {
        AccountData accountData = new AccountData(context);
        Set<Account> accounts = accountData.readAccount();
        return accounts;
    }

    // them account vao database
    public void insertAccount(Account account, Context context) {
        AccountData accountData = new AccountData(context);
        accountData.insertAccount(account);
    }
}
