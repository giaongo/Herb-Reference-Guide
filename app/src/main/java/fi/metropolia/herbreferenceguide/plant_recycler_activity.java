package fi.metropolia.herbreferenceguide;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class plant_recycler_activity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_recycler_layout);
        setContentView(R.layout.activity_item_display);

        TextView testingTv = findViewById(R.id.veggie_layout);

        testingTv.setOnClickListener(view -> {
            Intent intent = new Intent(plant_recycler_activity.this, ItemDisplayActivity.class);
            startActivity(intent);
        });

    }
}
