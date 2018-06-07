package kr.or.dgit.it.map_study;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class IntentMapActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_map);
    }

    public void mBtnViewMap(View view) {
        double latitude = 35.853131;
        double longitude = 128.5916963;
        String pos = String.format("geo:%f,%f?z=17", latitude, longitude);
        Uri uri = Uri.parse(pos);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);

    }
}
