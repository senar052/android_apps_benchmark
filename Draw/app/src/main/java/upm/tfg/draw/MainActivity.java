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



public class MainActivity extends AppCompatActivity {

    private ImageView imageView;

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
                //int pixel = ((BitmapDrawable)imageView.getDrawable()).getBitmap().getPixel(x,y);
                view.setDrawingCacheEnabled(true);
                Bitmap imgbmp = Bitmap.createBitmap(view.getDrawingCache());
                int pixel = imgbmp.getPixel(x,y);
                view.setDrawingCacheEnabled(false);
                int rojo= Color.red(pixel);
                int verde= Color.green(pixel);
                int azul= Color.blue(pixel);

                if(azul == 255 && verde == 0 && rojo == 0) {
                    Log.d(MainActivity.class.getSimpleName(), "IN!");


                }
                System.out.println("blue: " + azul + " red: " + rojo + " green: "+ verde);
                System.out.println("X: " + x);
                System.out.println("Y: " + y);
                return false;
            }
        });




    }


}
