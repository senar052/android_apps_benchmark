package upm.tfg.draw;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private boolean esquina1 = false; // up and left
    private boolean esquina2 = false; // up and right
    private boolean esquina3 = false; // down and left
    private boolean esquina4 = false; // down and
    private static final int max_time = 10000;
    private long activatedAt = Long.MAX_VALUE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.figure);
        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                int x = (int)event.getX();
                int y = (int)event.getY();
                //System.out.println("X: " + x);
                //System.out.println("Y: " + y);
                switch(event.getAction()) {
                    case MotionEvent.ACTION_MOVE:
                        //Log.d("TAG", "moving: (" + x + ", " + y + ")");
                        if( ( x >= 267 && x <=315 ) && ( y >= 445 && y <=493 )){
                            if (!esquina2 || !esquina3 || !esquina4)
                                activatedAt = System.currentTimeMillis();

                            System.out.println("esquina1");
                            esquina1=true;

                        }

                        if ( ( x >= 736 && x <=790 ) && ( y >= 448 && y <=496 )){
                            if (!esquina1 || !esquina3 || !esquina4)
                                activatedAt = System.currentTimeMillis();
                            System.out.println("esquina2");
                            esquina2=true;
                        }

                        if( ( x >= 264 && x <=315 ) && ( y >= 908 && y <=970 )){
                            if (!esquina2 || !esquina1 || !esquina4)
                                activatedAt = System.currentTimeMillis();
                            System.out.println("esquina3");
                            esquina3=true;
                        }

                        if ( ( x >= 739 && x <=790 ) && ( y >= 905 && y <=973 )){
                            if (!esquina2 || !esquina3 || !esquina1)
                                activatedAt = System.currentTimeMillis();
                            System.out.println("esquina4");
                            esquina4=true;
                        }
                        break;
                }
                long activeFor = System.currentTimeMillis() - activatedAt;
                if (activeFor <= max_time && esquina1 && esquina2 && esquina3 && esquina4){
                    Log.d("Draw: ", "figure completed");
                    Toast.makeText(getApplicationContext(), "Figure completed", Toast.LENGTH_LONG).show();
                    esquina1 = false;
                    esquina2 = false;
                    esquina3 = false;
                    esquina4 = false;
                }

                if (activeFor > max_time && (esquina1 || esquina2 || esquina3 || esquina4) ){
                   // Log.d("Draw: ", "you need to be faster");
                    esquina1 = false;
                    esquina2 = false;
                    esquina3 = false;
                    esquina4 = false;
                }

                return true;
            }
        });





    }


}
