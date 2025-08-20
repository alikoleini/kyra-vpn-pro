package ir.kyraserver.vpnpro;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ImageView;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;
import androidx.activity.result.ActivityResultLauncher;

public class MainActivity extends AppCompatActivity {

 private EditText configInput;
 private Button connectButton;
 private Button scanButton;
 private TextView statusText;
 private ImageView statusIcon;
 private boolean isConnected = false;

 private ActivityResultLauncher<ScanOptions> barcodeLauncher = registerForActivityResult(new ScanContract(),
 result -> {
 if(result.getContents() != null) {
 configInput.setText(result.getContents());
 }
 });

 @Override
 protected void onCreate(Bundle savedInstanceState) {
 super.onCreate(savedInstanceState);
 setContentView(R.layout.activity_main);

 configInput = findViewById(R.id.configInput);
 connectButton = findViewById(R.id.connectButton);
 scanButton = findViewById(R.id.scanButton);
 statusText = findViewById(R.id.statusText);
 statusIcon = findViewById(R.id.statusIcon);

 connectButton.setOnClickListener(new View.OnClickListener() {
 @Override
 public void onClick(View v) {
 toggleConnection();
 }
 });

 scanButton.setOnClickListener(new View.OnClickListener() {
 @Override
 public void onClick(View v) {
 scanQRCode();
 }
 });
 }

 private void toggleConnection() {
 String config = configInput.getText().toString().trim();
 
 if (config.isEmpty()) {
 Toast.makeText(this, "لطفا کانفیگ V2Ray را وارد کنید", Toast.LENGTH_SHORT).show();
 return;
 }

 if (!isConnected) {
 isConnected = true;
 connectButton.setText("قطع اتصال");
 connectButton.setBackgroundColor(getResources().getColor(android.R.color.holo_red_dark));
 statusText.setText("وضعیت: متصل");
 statusText.setTextColor(getResources().getColor(android.R.color.holo_green_dark));
 statusIcon.setImageResource(android.R.drawable.presence_online);
 Toast.makeText(this, "به سرور متصل شد", Toast.LENGTH_SHORT).show();
 } else {
 isConnected = false;
 connectButton.setText("اتصال");
 connectButton.setBackgroundColor(getResources().getColor(android.R.color.holo_green_dark));
 statusText.setText("وضعیت: قطع");
 statusText.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
 statusIcon.setImageResource(android.R.drawable.presence_offline);
 Toast.makeText(this, "اتصال قطع شد", Toast.LENGTH_SHORT).show();
 }
 }

 private void scanQRCode() {
 ScanOptions options = new ScanOptions();
 options.setPrompt("QR Code V2Ray را اسکن کنید");
 options.setBeepEnabled(false);
 options.setOrientationLocked(true);
 barcodeLauncher.launch(options);
 }
}
