package districtwisedetailsofwbbyamit.example.districtdetails;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    private RelativeLayout home,about,share;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        home = findViewById(R.id.home_button);

        share = findViewById(R.id.share_app);
        about = findViewById(R.id.about_us_app);

        // Bella Ciaoo

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(MainActivity.this,HomeActivity.class);
                startActivity(intent1);
            }
        });

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent (  Intent.ACTION_SEND);
                sendIntent.putExtra ( Intent.EXTRA_TEXT,
                        "\n\nI found an nice android app in Google PlayStore about West Bengal All District Details:-\n" +
                                "\n\nhttps://play.google.com/store/apps/details?id=districtwisedetailsofwbbyamit.example.districtdetails");
                sendIntent.putExtra ( Intent.EXTRA_SUBJECT,R.string.app_name );
                sendIntent.setType ( "text/plain" );
                Intent shareIntent = Intent.createChooser ( sendIntent,String.valueOf(R.string.app_name) );
                startActivity ( shareIntent );
            }
        });
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}