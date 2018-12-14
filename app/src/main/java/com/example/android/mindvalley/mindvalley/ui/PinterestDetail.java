package com.example.android.mindvalley.mindvalley.ui;

import android.content.Intent;
import android.support.design.internal.SnackbarContentLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.mindvalley.R;
import com.example.android.mindvalley.mindvalley.adapter.CircleAdapter;
import com.example.android.mindvalley.mindvalley.room.PinDataBase;
import com.example.android.mindvalley.mindvalley.room.PinEntity;
import com.example.android.mindvalley.mindvalley.room.PinExecutor;
import com.squareup.picasso.Picasso;

import static com.example.android.mindvalley.R.color.colorAccent;

public class PinterestDetail extends AppCompatActivity {

    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String PROFILE = "profile";
    public static final String IMAGE = "image";

    private ImageView cover;
    private ImageView pinIt;
    private ImageView circle;
    private TextView name;

    private PinDataBase pinDataBase;
    private PinEntity pinEntity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pinterest_detail);

        pinDataBase = PinDataBase.getPinDataBase(getApplicationContext());

        Intent intent = getIntent();
        if (intent == null){
            Toast.makeText(this, getString(R.string.sorry_something), Toast.LENGTH_SHORT).show();
            finish();
        }

        name = findViewById(R.id.tv_saved_from);
        cover = findViewById(R.id.cover);
        pinIt = findViewById(R.id.pinit);
        circle = findViewById(R.id.circle);

        String id = intent.getStringExtra(ID);
        String nameSave = intent.getStringExtra(NAME);
        String profile = intent.getStringExtra(PROFILE);
        String image = intent.getStringExtra(IMAGE);

        Picasso.get()
                .load(profile)
                .transform(new CircleAdapter())
                .error(R.drawable.circle)
                .placeholder(R.drawable.circle)
                .into(circle);

        Picasso.get()
                .load(image)
                .error(R.drawable.cover)
                .placeholder(R.drawable.cover)
                .into(cover);

        final String save = getString(R.string.saved) + " " + nameSave;
        name.setText(save);

        pinEntity = new PinEntity(id, nameSave, profile, image);

        pinIt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Snackbar snackbar = Snackbar.make(v, getString(R.string.favorite), Snackbar.LENGTH_LONG);
                View view = snackbar.getView();
                TextView textView = view.findViewById(android.support.design.R.id.snackbar_text);
                textView.setTextColor(getResources().getColor(colorAccent));
                snackbar.show();

                PinExecutor.getInstance().getDiskIo().execute(new Runnable() {
                    @Override
                    public void run() {
                        pinDataBase.pinDao().insertPin(pinEntity);
                    }
                });
            }
        });


    }


}
