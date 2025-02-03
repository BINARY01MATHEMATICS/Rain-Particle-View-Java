<!-----------#RainParticleView.java---------------!>

package com.example.rainparticle;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;

public class RainParticleView extends View {
    private ArrayList<RainDrop> rainDrops;
    private Paint paint;
    private Random random;
    private int width, height;

    public RainParticleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        rainDrops = new ArrayList<>();
        paint = new Paint();
        paint.setColor(Color.BLUE); // Color of the raindrops
        paint.setStyle(Paint.Style.FILL);
        random = new Random();

        // Create initial raindrops
        for (int i = 0; i < 100; i++) {
            rainDrops.add(new RainDrop(random.nextInt(800), random.nextInt(600), random.nextInt(5) + 5));
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (RainDrop drop : rainDrops) {
            canvas.drawCircle(drop.x, drop.y, drop.size, paint);
            drop.y += drop.speed; // Move the raindrop down
            if (drop.y > height) {
                drop.y = 0; // Reset to the top
                drop.x = random.nextInt(width); // Randomize x position
            }
        }
        invalidate(); // Request to redraw the view
    }

    private class RainDrop {
        float x, y;
        float size;
        float speed;

        RainDrop(float x, float y, float size) {
            this.x = x;
            this.y = y;
            this.size = size;
            this.speed = random.nextInt(10) + 5; // Random speed
        }
    }
}

<!----------------#activity_main.xml----------------!>

Use RainParticleView In activity_main.xml

<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <com.example.rainparticle.RainParticleView
        android:id="@+id/rainParticleView"
        android:layout_width="match_parent"
        android:layout_height="match_parent" />
</RelativeLayout>


<!----------------#MainActivity------------------!>

Use RainParticleView In MainActivity

package com.example.rainparticle;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // Without Any Xml Id By Only Java Source 
    RainParticleView rainP = new RainParticleView(this); 
    layout_name.addView(rainP); // Replace With Actual Layout Id | Layout Names($$layout_name$$)------!
        
    }
}

