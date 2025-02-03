<!-----------#RainView.java---------------!>

package com.example.rainview;
 
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;

public class RainView extends View {
    private Paint paint;
    private ArrayList<RainDrop> rainDrops;
    private int width, height;
    private Random random;

    // RainDrop class representing individual drops 
    private static class RainDrop {
        float x, y, length;
        float speed;

        // Constructor to initialize the raindrop properties
        RainDrop(float x, float y, float length, float speed) {
            this.x = x;
            this.y = y;
            this.length = length;
            this.speed = speed;
        }

        void fall() {
            y += speed; // update the Y position of the raindrop
        }

        boolean isOutOfView(int height) {
            return y > height; // check if the raindrop is out of the screen view
        }
    }

    public RainView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        paint.setColor(Color.BLUE); // Set the color of the raindrops
        paint.setStrokeWidth(4); // Set the width of the raindrops
        rainDrops = new ArrayList<>();
        random = new Random();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
        createRainDrops(100); // Create initial raindrops
    }

    private void createRainDrops(int numDrops) {
        for (int i = 0; i < numDrops; i++) {
            float x = random.nextFloat() * width; // Random X position
            float length = 10 + random.nextFloat() * 20; // Random length between 10 and 30
            float speed = 5 + random.nextFloat() * 10; // Random speed between 5 and 15
            rainDrops.add(new RainDrop(x, random.nextFloat() * height, length, speed));
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // Draw each raindrop
        for (RainDrop drop : rainDrops) {
            drop.fall();
            if (drop.isOutOfView(height)) {
                drop.y = 0; // Reset to the top
                drop.x = random.nextFloat() * width; // Random new X position
            }
            canvas.drawLine(drop.x, drop.y, drop.x, drop.y + drop.length, paint); // Draw the drop
        }
        invalidate(); // Redraw continuously
    }
}
 

<!----------------#activity_main.xml----------------!>

Use RainView In activity_main.xml
 
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <com.example.rainview.RainView
        android:id="@+id/rain_view"
        android:layout_width="match_parent"
        android:layout_height="match_parent"/>
</RelativeLayout>
 

<!----------------#MainActivity------------------!>

Use RainView In MainActivity

package com.example.rainview;
 
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Set the content to your layout XML file
        
        // Without Any Xml Id By Only Java Source 
    RainView rainP = new RainView(this); 
    layout_name.addView(rainP); // Replace With Actual Layout Id | Layout Names($$layout_name$$)------!
        
    }
}
 
