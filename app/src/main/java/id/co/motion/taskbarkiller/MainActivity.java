package id.co.motion.taskbarkiller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hideSystemUi();

        Button bKill = (Button) findViewById(R.id.kill);
        Button bCall = (Button) findViewById(R.id.call);

        bKill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideSystemUi();
            }
        });

        bCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSystemUi();
            }
        });
    }

    /*============================================================================================*/
    // Method

    public static void showSystemUi() {
        try {
            Process proc = Runtime.getRuntime().exec(new String[]{"su", "-c", "pm enable com.android.systemui"});
            proc.waitFor();
            proc = Runtime.getRuntime().exec(new String[]{"su", "-c", "am startservice -n com.android.systemui/.SystemUIService"});
            proc.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void hideSystemUi() {
        try {
            Process proc = Runtime.getRuntime().exec(new String[]{"su", "-c", "service call activity 42 s16 com.android.systemui"});
            proc.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
