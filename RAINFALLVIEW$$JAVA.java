<!-----------#RainfallView.java---------------!>

package com.example.rainfallview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;

public class RainfallView extends View {

    private Paint paint;
    private ArrayList<Raindrop> raindrops;
    private Random random;
    private int width, height;

    // Constructor
    public RainfallView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(Color.BLUE); // Raindrop color
        paint.setStrokeWidth(5f);
        raindrops = new ArrayList<>();
        random = new Random();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;

        // Generate initial raindrops
        for (int i = 0; i < 100; i++) {
            raindrops.add(new Raindrop(random.nextInt(width), random.nextInt(height), random.nextInt(15) + 5));
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (Raindrop drop : raindrops) {
            // Draw each raindrop
            canvas.drawLine(drop.x, drop.y, drop.x, drop.y + drop.length, paint);

            // Move raindrop downward
            drop.y += drop.speed;

            // Reset position if it goes off-screen
            if (drop.y > height) {
                drop.y = -drop.length;
                drop.x = random.nextInt(width);
            }
        }

        // Invalidate view to redraw
        invalidate();
    }

    // Raindrop class to represent each drop
    private static class Raindrop {
        int x, y, length, speed;

        Raindrop(int x, int y, int length) {
            this.x = x;
            this.y = y;
            this.length = length;
            this.speed = new Random().nextInt(10) + 5; // Random speed
        }
    }
}

<!----------------#activity_main.xml----------------!>

Use RainfallView In activity_main.xml

<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="#000000">

    <com.example.rainfallview.RainfallView
        android:id="@+id/rainfallView"
        android:layout_width="match_parent"
        android:layout_height="match_parent" />
</RelativeLayout>

<!----------------#MainActivity------------------!>

Use RainfallView In MainActivity

package com.example.rainfallview;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // Without Any Xml Id By Only Java Source 
    RainfallView rainP = new RainfallView(this); 
    layout_name.addView(rainP); // Replace With Actual Layout Id | Layout Names($$layout_name$$)------!
    
    }
}
