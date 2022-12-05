package id.putraprima.skorbola;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private static final int HOME_REQUEST_CODE = 1;
    private static final int AWAY_REQUEST_CODE = 2;
    private static final String TAG = MainActivity.class.getCanonicalName();

    private String hometeam;
    private String awayteam;
    private EditText inphome;
    private EditText inpaway;
    private ImageView logohome;
    private ImageView logoaway;
    private Button buttomteam;
    private Uri imghome;
    private Uri imgaway;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inphome = findViewById(R.id.home_team);
        inpaway = findViewById(R.id.away_team);
        logohome = findViewById(R.id.home_logo);
        logoaway = findViewById(R.id.away_logo);
        buttomteam = findViewById(R.id.btn_team);

        buttomteam.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view){
                hometeam = inphome.getText().toString();
                awayteam = inpaway.getText().toString();
                Intent intent = new Intent(MainActivity.this, MatchActivity.class);
                intent.putExtra("namahome",hometeam);
                intent.putExtra("namaway", awayteam);
                intent.putExtra("imghome", imghome.toString());
                intent.putExtra("imgaway", imgaway.toString());
                startActivity(intent);
            }
        });

        logohome.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivityForResult(new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI), HOME_REQUEST_CODE);
            }
        });

        logoaway.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivityForResult(new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI), AWAY_REQUEST_CODE);
            }
        });

        //TODO
        //Fitur Main Activity
        //1. Validasi Input Home Team
        //2. Validasi Input Away Team
        //3. Ganti Logo Home Team
        //4. Ganti Logo Away Team
        //5. Next Button Pindah Ke MatchActivity
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_CANCELED){
            Log.d(TAG, "Pilih gambar dicancel");
            return;
        }
        else if(requestCode == HOME_REQUEST_CODE){
            if(data != null){
                try {
                    Uri imageUri = data.getData();
                    imghome = imageUri;
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                    logohome.setImageBitmap(bitmap);
                }
                catch (IOException error){
                    Toast.makeText(this, "Can't load image", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, error.getMessage());
                }
            }
        }
        else if(requestCode == AWAY_REQUEST_CODE){
            if(data != null){
                try {
                    Uri imageUri = data.getData();
                    imgaway = imageUri;
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                    logoaway.setImageBitmap(bitmap);
                }
                catch (IOException error){
                    Toast.makeText(this, "Can't load image", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, error.getMessage());
                }
            }
        }
    }
}
