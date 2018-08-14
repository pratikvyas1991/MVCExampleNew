package com.facerec.tasol.mvcexample.view.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.facerec.tasol.mvcexample.R;
import com.facerec.tasol.mvcexample.adapter.UserAdapter;
import com.facerec.tasol.mvcexample.model.UserModel;
import com.facerec.tasol.mvcexample.presenter.UserPresenter;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    LinearLayout lnrPullData,lnrPushData;
    EditText etEmail,etName,etAge;
    Button btnAddData,btnPullData,btnPush;
    RecyclerView rvUserData;
    UserPresenter userPresenter;
    UserAdapter userAdapter;
    LinearLayoutManager layoutManager;
    private List<UserModel> userData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userPresenter =new UserPresenter(MainActivity.this);
        initViews();
        setActions();
    }

    private void setActions() {
        btnPullData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lnrPullData.setVisibility(View.VISIBLE);
                lnrPushData.setVisibility(View.GONE);
                userData = userPresenter.getUserModel();
                if(userData!=null && userData.size()>0){
                    userAdapter = new UserAdapter(userData,MainActivity.this);
                    rvUserData.setAdapter(userAdapter);
                }else {
                    Toast.makeText(MainActivity.this,"No Data",Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnPush.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lnrPushData.setVisibility(View.VISIBLE);
                lnrPullData.setVisibility(View.GONE);
            }
        });

        btnAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDatatoDb();
            }
        });
    }

    private void addDatatoDb() {
        String email = etEmail.getText().toString();
        String name = etName.getText().toString();
        String age = etAge.getText().toString();
        UserModel model =new UserModel();
        model.setmUserEmail(email);
        model.setmUserName(name);
        model.setmUserAge(age);
        userPresenter.addUser(model);
        Toast.makeText(MainActivity.this,"Data Added",Toast.LENGTH_SHORT).show();
        lnrPushData.setVisibility(View.GONE);
    }

    private void initViews() {
        lnrPullData = (LinearLayout)findViewById(R.id.lnrPullData);
        lnrPushData = (LinearLayout)findViewById(R.id.lnrPushData);
        etEmail= (EditText) findViewById(R.id.etEmail);
        etName= (EditText) findViewById(R.id.etName);
        etAge = (EditText) findViewById(R.id.etAge);
        btnAddData= (Button) findViewById(R.id.btnAddData);
        btnPullData= (Button) findViewById(R.id.btnPullData);
        btnPush= (Button) findViewById(R.id.btnPush);
        rvUserData= (RecyclerView) findViewById(R.id.rvUserData);
        layoutManager =new LinearLayoutManager(MainActivity.this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvUserData.setLayoutManager(layoutManager);

    }

}
