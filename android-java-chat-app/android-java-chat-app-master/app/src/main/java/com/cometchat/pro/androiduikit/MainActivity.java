package com.cometchat.pro.androiduikit;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import com.cometchat.pro.core.CometChat;
import com.cometchat.pro.exceptions.CometChatException;
import com.cometchat.pro.models.User;
import java.util.ArrayList;
import com.cometchat.pro.androiduikit.constants.AppConfig;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import screen.unified.CometChatUnified;

import utils.Utils;


public class MainActivity extends AppCompatActivity {

    private String TAG = "MainActivity";

    private MaterialButton loginBtn;

    private MaterialCardView superhero1;

    private MaterialCardView superhero2;

    private MaterialCardView superhero3;

    private MaterialCardView superhero4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (CometChat.getLoggedInUser()!=null)
        {
//            startActivity(new Intent(MainActivity.this,SelectActivity.class));
            startActivity(new Intent(MainActivity.this,CometChatUnified.class));
        }
        loginBtn = findViewById(R.id.login);
        superhero1 = findViewById(R.id.superhero1);
        superhero2 = findViewById(R.id.superhero2);
        superhero3 = findViewById(R.id.superhero3);
        superhero4 = findViewById(R.id.superhero4);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
                public void onClick(View view) {
                    startActivity(new Intent(MainActivity.this,LoginActivity.class));
                }
            });

        superhero1.setOnClickListener(view -> {
                findViewById(R.id.superhero1_progressbar).setVisibility(View.VISIBLE);
                login("superhero1");
        });
        superhero2.setOnClickListener(view -> {
                findViewById(R.id.superhero2_progressbar).setVisibility(View.VISIBLE);
                login("superhero2");
        });
        superhero3.setOnClickListener(view -> {
                findViewById(R.id.superhero3_progressbar).setVisibility(View.VISIBLE);
                login("superhero3");
        });
        superhero4.setOnClickListener(view -> {
                findViewById(R.id.superhero4_progressbar).setVisibility(View.VISIBLE);
                login("superhero4");
        });

    }

    private void login(String uid) {
        CometChat.login(uid, AppConfig.AppDetails.API_KEY, new CometChat.CallbackListener<User>() {
            @Override
            public void onSuccess(User user) {
                startActivity(new Intent(MainActivity.this, SelectActivity.class));
//                startActivity(new Intent(MainActivity.this,CometChatUnified.class));
                finish();
            }

            @Override
            public void onError(CometChatException e) {
                String str = uid+"_progressbar";
                int id = getResources().getIdentifier(str,"id",getPackageName());
                findViewById(id).setVisibility(View.GONE);
                Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
