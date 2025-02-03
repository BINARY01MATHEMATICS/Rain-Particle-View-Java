<!-----------#RainParticleView.java---------------!>

package com.example.RainParticleView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import java.util.ArrayList;
import java.util.Random;

public class RainParticleView extends View {
    private Paint paint;
    private ArrayList<Drop> drops;
    private int width, height;
    
    public RainParticleView(Context context) {
        super(context);
        init();
    }

    public RainParticleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(Color.BLUE);
        drops = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            drops.add(new Drop());
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
        for (Drop drop : drops) {
            drop.update();
            canvas.drawCircle(drop.x, drop.y, drop.radius, paint);
        }
        invalidate();
    }

    private class Drop {
        float x, y, radius;
        float speed;

        Drop() {
            Random random = new Random();
            x = random.nextFloat() * width;
            y = random.nextFloat() * height;
            radius = random.nextFloat() * 5 + 5;
            speed = random.nextFloat() * 10 + 5;
        }

        void update() {
            y += speed;
            if (y > height) {
                y = 0;
                x = new Random().nextFloat() * width;
            }
        }
    }
}

 


<!----------------#activity_main.xml----------------!>

Use RainParticleView In activity_main.xml

 
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <com.example.RainParticleView
        android:id="@+id/raindrop_view"
        android:layout_width="match_parent"
        android:layout_height="match_parent"/>

</RelativeLayout>

<!----------------#MainActivity------------------!>

Use RainParticleView In MainActivity

package com.example.RainParticleView;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
private RainParticleView rainParticleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rainParticleView = findViewById(R.id.raindrop_view); // From activity_main.xml Id
        
    // Without Any Xml Id By Only Java Source 
    RainParticleView rainP = new RainParticleView(this); 
    layout_name.addView(rainP); // Replace With Actual Layout Id | Layout Names($$layout_name$$)------!
        
    }
}
 
 
