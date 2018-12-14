package com.example.android.mindvalley.mindvalley.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.mindvalley.R;
import com.example.android.mindvalley.mindvalley.adapter.CircleAdapter;
import com.example.android.mindvalley.mindvalley.room.PinDataBase;
import com.example.android.mindvalley.mindvalley.room.PinEntity;
import com.example.android.mindvalley.mindvalley.room.PinExecutor;
import com.squareup.picasso.Picasso;

public class FavoriteDetail extends AppCompatActivity {

    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String PROFILE = "profile";
    public static final String COVER = "cover";

    private ImageView cover;
    private ImageView circle;
    private TextView text;

    private PinDataBase pinDataBase;
    private PinEntity pinEntity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_detail);

        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.getSupportActionBar().setHomeButtonEnabled(true);

        pinDataBase = PinDataBase.getPinDataBase(getApplicationContext());

        cover = findViewById(R.id.cover);
        circle = findViewById(R.id.circle);
        text = findViewById(R.id.tv_saved_from);

        Intent intent = getIntent();
        if (intent == null){
            Toast.makeText(this, getString(R.string.sorry_something), Toast.LENGTH_SHORT).show();
            finish();
        }

        String id = intent.getStringExtra(ID);
        String name = intent.getStringExtra(NAME);
        String profile = intent.getStringExtra(PROFILE);
        String image = intent.getStringExtra(COVER);

        pinEntity = new PinEntity(id, name, profile, image);

        String save = getString(R.string.saved) + " " + name;
        text.setText(save);

        Picasso.get()
                .load(image)
                .error(R.drawable.cover)
                .placeholder(R.drawable.cover)
                .into(cover);

        Picasso.get()
                .load(profile)
                .transform( new CircleAdapter())
                .error(R.drawable.circle)
                .placeholder(R.drawable.circle)
                .into(circle);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.delete_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.delete_menu){
            deletePin();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void deletePin(){
        PinExecutor.getInstance().getDiskIo().execute(new Runnable() {
            @Override
            public void run() {
                pinDataBase.pinDao().deletePin(pinEntity);
                finish();
            }
        });
    }
}
