package liquid.sapmletest.com.rxjavapartone;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.jakewharton.rxbinding.view.RxView;

import java.util.concurrent.TimeUnit;

import rx.android.schedulers.AndroidSchedulers;


public class RxIndexActivity extends AppCompatActivity {

    Button hitMeBtn;
    TextView myRankTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_index);

        hitMeBtn = (Button) findViewById(R.id.hit_me_btn);
        myRankTxt = (TextView) findViewById(R.id.my_rank_txt);
        Typeface typeface = Typeface.createFromAsset(getAssets(),
                "fonts/cartoon_freak.otf");
        myRankTxt.setTypeface(typeface);

        RxView.clicks(hitMeBtn)
                .buffer(1000, TimeUnit.MILLISECONDS)
                //.filter(a -> a.size() >= 4)
                .map(a -> {return a.size();})
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(o -> {

                    if (o == 0){
                        myRankTxt.setText("Waiting");
                    }
                    else if (o < 3) {
                        //Toast.makeText(this, "Looser!!", Toast.LENGTH_SHORT).show();
                        myRankTxt.setText("Loser");
                    } else if (o <= 4) {
                        //Toast.makeText(this, "Good!!", Toast.LENGTH_SHORT).show();
                        myRankTxt.setText("Nice");
                    } else if (o < 6) {
                        //Toast.makeText(this, "Good!!", Toast.LENGTH_SHORT).show();
                        myRankTxt.setText("Wow");
                    }else if (o > 6) {
                        //Toast.makeText(this, "Expert!!", Toast.LENGTH_SHORT).show();
                        myRankTxt.setText("Expert");
                    }

                });


    }
}
