package id.putraprima.skorbola;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MatchActivity extends AppCompatActivity {

    private String hometeam;
    private String awayteam;
    private String pemenang;
    private int skorhome;
    private int skoraway;
    private TextView tvskorhome;
    private TextView tvskoraway;
    private TextView tvhome;
    private TextView tvaway;
    private ImageView logohome;
    private ImageView logoaway;
    private Button addHome;
    private Button addAway;
    private Button cekhasil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);

        tvhome = findViewById(R.id.txt_home);
        tvaway = findViewById(R.id.txt_away);
        skorhome = findViewById(R.id.score_home);
        skoraway = findViewById(R.id.score_away);
        logohome = findViewById(R.id.home_logo);
        logoaway = findViewById(R.id.away_logo);
        addHome = findViewById(R.id.btn_add_home);
        addAway = findViewById(R.id.btn_add_away);
        cekhasil = findViewById(R.id.btn_result);

        skorhome = 0;
        skoraway = 0;
        tvskorhome.setText(String.valueOf(skorhome));
        tvskoraway.setText(String.valueOf(skoraway));

        Bundle bundle = getIntent().getExtras();
        hometeam = bundle.getString("namahome");
        tvhome.setText(hometeam);
        awayteam = bundle.getString("namaaway");
        tvaway.setText(awayteam);
        logohome.setImageURI(Uri.parse(bundle.getString("homeImg")));
        logoaway.setImageURI(Uri.parse(bundle.getString("awayImg")));

        addHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                skorhome += 1;
                tvskorhome.setText(String.valueOf(skorhome));
            }
        });

        addAway.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                skoraway += 1;
                tvskoraway.setText(String.valueOf(skoraway));
            }
        });

        cekhasil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pemenang = "empty";
                if(skorhome > skoraway){
                    pemenang = hometeam;
                }
                else if (skorhome == skoraway){
                    pemenang = "draw";
                }
                else {
                    pemenang = awayteam;
                }

                Intent intent = new Intent(MatchActivity.this, ResultActivity.class);
                intent.putExtra("winner", pemenang);
                startActivity(intent);
            }
        });

        //TODO
        //1.Menampilkan detail match sesuai data dari main activity
        //2.Tombol add score menambahkan satu angka dari angka 0, setiap kali di tekan
        //3.Tombol Cek Result menghitung pemenang dari kedua tim dan mengirim nama pemenang ke ResultActivity, jika seri di kirim text "Draw"
    }
}
