package jasi2169.netguard.keygen;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.security.MessageDigest;
import java.util.Locale;

/*
 * Created By Jasi2169
 * 20/Nov/2018
 * Android Studio v3.1.4
 * */

public class LetsKeygen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lets_keygen);
    }

    public void generate(View v){

        EditText et = (EditText) findViewById(R.id.editText);
        String getuserinput = et.getText().toString();
        if(getuserinput.equals("") || getuserinput == null){
            Toast.makeText(this, "Challenge Word should not be null!", Toast.LENGTH_SHORT).show();
            return;
        }

        String challenge = getuserinput;
        String salt = (Build.VERSION.SDK_INT < Build.VERSION_CODES.O ? "NetGuard2" : "NetGuard3");

        TextView tv2 = (TextView) findViewById(R.id.textView2);

        String generatedserial = md5(challenge,salt);

        tv2.setText("Serial :- "+ generatedserial.toUpperCase(Locale.US));

        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("serial",generatedserial);
        clipboard.setPrimaryClip(clip);

        Toast.makeText(this,"Key copied to clipboard!",Toast.LENGTH_LONG).show();

    }

    public static String md5(String text, String salt) {
        // MD5
        try {
            byte[] bytes = MessageDigest.getInstance("MD5").digest((text + salt).getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder();
            for (byte b : bytes)
                sb.append(String.format("%02X", b));
            return sb.toString();
        }catch (Exception e){
            return "Exception :- "+e.toString();
        }
    }
}
