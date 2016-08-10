package benmu.com.banshee;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.BansheeView;

public class MainActivity extends AppCompatActivity {

    @BansheeView(R.id.tv1)
    TextView tv1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        benmu.com.bansheeview.BansheeView.inject(this);
        tv1.setText("啊哈哈  策划那个狗狗呢了");
    }
}
