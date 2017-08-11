package com.ptit.trongthien.vnexpress_v1.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.ArraySet;

import com.ptit.trongthien.vnexpress_v1.model.entity.Account;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by TrongThien on 7/21/2017.
 */

public class AccountData extends MyDatabase {
    public AccountData(Context context) {
        super(context);
    }

    public Set<Account> readAccount() {
        Set<Account> accounts = new HashSet<>();
        openData();
        Cursor cursor = database.query(TABLE_NAME, null, null, null, null, null, null);
        int idIndex = cursor.getColumnIndex(ID);
        int usernameIndex = cursor.getColumnIndex(USERNAME);
        int passwordIndex = cursor.getColumnIndex(PASSWORD);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int id = cursor.getInt(idIndex);
            String username = cursor.getString(usernameIndex);
            String password = cursor.getString(passwordIndex);

            Account account = new Account(id, username, password);
            accounts.add(account);
            cursor.moveToNext();
        }
        closeData();
        return accounts;
    }

    public long insertAccount(Account account) {
        ContentValues values = new ContentValues();
        values.put(USERNAME, account.getUsername());
        values.put(PASSWORD, account.getPassword());
        openData();
        long id = database.insert(TABLE_NAME, null, values);
        return id;
    }
}
