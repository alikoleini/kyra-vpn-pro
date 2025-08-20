package ir.kyraserver.vpnpro;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {

 @Override
 protected void onCreate(Bundle savedInstanceState) {
 super.onCreate(savedInstanceState);
 setContentView(R.layout.activity_splash);

 ImageView logo = findViewById(R.id.logo);
 TextView websiteText = findViewById(R.id.websiteText);
 TextView supportText = findViewById(R.id.supportText);

 Animation fadeIn = AnimationUtils.loadAnimation(this, android.R.anim.fade_in);
 logo.startAnimation(fadeIn);
 websiteText.startAnimation(fadeIn);
 supportText.startAnimation(fadeIn);

 new Handler().postDelayed(new Runnable() {
 @Override
 public void run() {
 Intent intent = new Intent(SplashActivity.this, MainActivity.class);
 startActivity(intent);
 finish();
 }
 }, 3000);
 }
}
