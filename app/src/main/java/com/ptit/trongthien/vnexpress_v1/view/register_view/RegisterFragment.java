package com.ptit.trongthien.vnexpress_v1.view.register_view;


import android.os.Bundle;
import android.support.annotation.Nullable;

import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ptit.trongthien.vnexpress_v1.presenter.register_presenter.RegisterPresenterImp;
import com.ptit.trongthien.vnexpress_v1.R;
import com.ptit.trongthien.vnexpress_v1.view.MainActivity;

/**
 * Created by TrongThien on 7/21/2017.
 */

public class RegisterFragment extends Fragment implements RegisterView, View.OnClickListener {
    public static final String TAG_REGISTER = "RegisterFragment";
    private EditText edtUsername, edtPassword, edtConfirmPassword;
    private Button btnRegister;
    private RegisterPresenterImp registerPresenterImp;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_register, container, false);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        registerPresenterImp = new RegisterPresenterImp(this, getActivity());
        initView();
    }

    private void initView() {
        edtUsername = (EditText) getActivity().findViewById(R.id.edtUsernameRegister);
        edtPassword = (EditText) getActivity().findViewById(R.id.edtPasswordRegister);
        edtConfirmPassword = (EditText) getActivity().findViewById(R.id.edtConfirmPassword);
        btnRegister = (Button) getActivity().findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(this);
    }

    @Override
    public void registerSuccess(String username) {
        Toast.makeText(getActivity(), "Register success", Toast.LENGTH_SHORT).show();
        MainActivity mainActivity = (MainActivity) getActivity();
        mainActivity.changeFragmentStack(mainActivity.getLoginFragment());
    }

    @Override
    public void registerFail(String username) {
        Toast.makeText(getActivity(), username + " da duoc dang ky", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {
        String username = edtUsername.getText().toString();
        String password = edtPassword.getText().toString();
        String confirmPass = edtConfirmPassword.getText().toString();

        switch (view.getId()) {
            case R.id.btnRegister:
                if (username.equals("") || password.equals("") || confirmPass.equals("")) {
                    Toast.makeText(getActivity(), "Chua nhap du du lieu", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (password.length() < 6) {
                    Toast.makeText(getActivity(), "Mat khau phai lon hon 6 ky tu", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!password.equals(confirmPass)) {
                    Toast.makeText(getActivity(), "Mat khau xac nhan ko trung khop", Toast.LENGTH_SHORT).show();
                    return;
                }
                registerPresenterImp.checkRegister(username, password);
                break;
        }
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        MainActivity mainActivity = (MainActivity) getActivity();
//        mainActivity.changeFragmentStack(mainActivity.getLoginFragment());
//        return super.onOptionsItemSelected(item);
//    }
}
