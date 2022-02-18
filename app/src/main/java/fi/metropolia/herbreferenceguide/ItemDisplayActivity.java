package fi.metropolia.herbreferenceguide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.io.IOException;
import java.io.InputStream;

public class ItemDisplayActivity extends AppCompatActivity {
    private ImageView plantImg;
    private AssetManager assetManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_display);


        //Event click listener fo the floating button
        FloatingActionButton fab = findViewById(R.id.add_icon);
        fab.setOnClickListener(view -> Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());

        plantImg = findViewById(R.id.imageView_Carrot);
        assetManager = this.getAssets();
        InputStream is = null;
        try {
            is = assetManager.open("Img/Herbs/oregano.jpg");
        } catch (IOException e){
            e.printStackTrace();
        }
        Bitmap bitmap = BitmapFactory.decodeStream(is);
        plantImg.setImageBitmap(bitmap);
    }
}