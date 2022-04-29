package net.phipe.androidfacebookapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.share.Sharer;
import com.facebook.share.model.ShareContent;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.MessageDialog;
import com.facebook.share.widget.ShareButton;
import com.facebook.share.widget.ShareDialog;

import java.util.Arrays;

public class HomeActivity extends AppCompatActivity {

    TextView txt;
    private static final String EMAIL = "email";
    CallbackManager callbackManager;
    ShareDialog shareDialog;
    LoginButton loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        txt = findViewById(R.id.txtContent);

        callbackManager = CallbackManager.Factory.create();
        shareDialog = new ShareDialog(this);
        // this part is optional
        shareDialog.registerCallback(callbackManager, new FacebookCallback<Sharer.Result>() {
            @Override
            public void onSuccess(Sharer.Result result) {
                //MessageDialog.show(HomeActivity.this, content);
                Toast.makeText(HomeActivity.this, "jaló", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(@NonNull FacebookException e) {
                Toast.makeText(HomeActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancel() {
                Toast.makeText(HomeActivity.this, "cancelado", Toast.LENGTH_SHORT).show();
            }
        });

        ShareLinkContent linkContent = new ShareLinkContent.Builder()
                .build();

        ShareButton btn = (ShareButton) findViewById(R.id.btnShare);
        btn.setShareContent(linkContent);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ShareDialog.canShow(ShareLinkContent.class)) {
                    shareDialog.show(linkContent);
                }
            }
        });

        loginButton = (LoginButton) findViewById(R.id.HomeLoginButton);
        loginButton.setReadPermissions(Arrays.asList(EMAIL));
        // If you are using in a fragment, call loginButton.setFragment(this);

        callbackManager = CallbackManager.Factory.create();

        // Callback registration
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                System.out.println(loginResult.toString());
            }

            @Override
            public void onCancel() {
                Toast.makeText(HomeActivity.this, "cancelaste el login", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException exception) {
                Toast.makeText(HomeActivity.this, exception.getMessage(), Toast.LENGTH_SHORT).show();
                System.out.println(exception.toString());
            }
        });
    }
}