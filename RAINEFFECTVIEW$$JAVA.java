<!-----------#RainEffectView.java---------------!>


package com.example.raineffect;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import java.util.ArrayList;
import java.util.Random;

public class RainEffectView extends View {
    private static final int NUM_DROPS = 150; // Total number of raindrops
    private ArrayList<RainDrop> rainDrops; // List to store raindrops
    private Paint paint; // Paint object to draw raindrops
    private Random random; // Random object to create randomness

    public RainEffectView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RainEffectView(Context context) {
        super(context);
        init();
    }

    private void init() {
        rainDrops = new ArrayList<>();
        paint = new Paint();
        paint.setColor(Color.CYAN); // Color of the raindrops
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        random = new Random();

        // Initialize raindrops
        for (int i = 0; i < NUM_DROPS; i++) {
            rainDrops.add(new RainDrop(random.nextInt(1000), random.nextInt(2000)));
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Draw each raindrop and update its position
        for (RainDrop drop : rainDrops) {
            canvas.drawLine(drop.x, drop.y, drop.x, drop.y + drop.length, paint);
            drop.y += drop.speed;

            // Reset raindrop position if it goes out of the screen
            if (drop.y > getHeight()) {
                drop.y = 0;
                drop.x = random.nextInt(getWidth());
            }
        }

        // Trigger the next frame
        postInvalidate();
    }

    // Inner class for raindrop properties
    private class RainDrop {
        int x, y; // Position
        int speed; // Falling speed
        int length; // Length of the raindrop

        public RainDrop(int x, int y) {
            this.x = x;
            this.y = y;
            this.speed = random.nextInt(15) + 5; // Speed between 5 and 20
            this.length = random.nextInt(30) + 10; // Length between 10 and 40
        }
    }
}


<!----------------#activity_main.xml----------------!>

Use RainEffectView In activity_main.xml

<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <com.example.raineffect.RainEffectView
        android:id="@+id/rainView"
        android:layout_width="match_parent"
        android:layout_height="match_parent" />
</RelativeLayout>


<!----------------#MainActivity------------------!>

Use RainEffectView In MainActivity

package com.example.raineffect;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // Without Any Xml Id By Only Java Source 
    RainEffectView rainP = new RainEffectView(this); 
    layout_name.addView(rainP); // Replace With Actual Layout Id | Layout Names($$layout_name$$)------!
       
    }
}

