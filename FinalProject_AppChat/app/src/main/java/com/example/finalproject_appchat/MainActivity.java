package com.example.finalproject_appchat;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.cometchat.pro.core.CometChat;
import com.cometchat.pro.exceptions.CometChatException;
import com.cometchat.pro.models.User;
import com.example.finalproject_appchat.utils.Constants;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initCometchat();
        initViews();
    }

    private void initViews() {
        EditText userIdEditText = findViewById(R.id.userIdEditText);
        Button submitButton = findViewById(R.id.submitButton);
        submitButton.setOnClickListener(v -> CometChat.login(userIdEditText.getText().toString(), Constants.API_KEY, new CometChat.CallbackListener<User>() {
            @Override
            public void onSuccess(User user) {
                redirectToGroupListScreen();
            }
            @Override
            public void onError(CometChatException e) {
//                redirectToGroupListScreen();
            }
        }));
    }

    private void redirectToGroupListScreen() {
        GroupListActivity.start(this);
    }

    private void initCometchat() {
        CometChat.init(this, Constants.APP_ID, new CometChat.CallbackListener<String>() {
            @Override
            public void onSuccess(String successMessage) {

            }
            @Override
            public void onError(CometChatException e) {

            }
        });
    }
}