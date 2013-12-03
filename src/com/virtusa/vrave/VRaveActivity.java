package com.virtusa.vrave;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.MotionEventCompat;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

public class VRaveActivity extends FragmentActivity {
	boolean sendRaveOpen = false;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_vrave);
		
		final ImageView send = (ImageView) findViewById(R.id.imageView4);
		final ImageView settings = (ImageView) findViewById(R.id.imageView5);
		final TextView tv = (TextView) findViewById(R.id.textView2);
		settings.setVisibility(View.VISIBLE);
		send.setVisibility(View.GONE);
		tv.setText("View Rave");
		
		final TabHost tabs= (TabHost) findViewById(R.id.tabhost);
		tabs.setup();
		
		TabHost.TabSpec spec=tabs.newTabSpec("tag1");
		spec.setContent(R.id.fragment1);
		spec.setIndicator("View Raves");
		tabs.addTab(spec);
		spec=tabs.newTabSpec("tag2");
		spec.setContent(R.id.fragment2);
		spec.setIndicator("My Badges");
		tabs.addTab(spec);
		tabs.setCurrentTab(0);
		
		DisplayMetrics metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);

		final int height = metrics.heightPixels;
		
//		Display display = getWindowManager().getDefaultDisplay();
//		Point size = new Point();
//		display.getSize(size);
//		final int height = size.y;
		//final int height = display.getHeight();
		
		final RelativeLayout l = (RelativeLayout) findViewById(R.id.upperContainer);
		final LinearLayout l2 = (LinearLayout) findViewById(R.id.lenear);
		final ImageView iv2 = (ImageView) findViewById(R.id.imageView2);
		final RelativeLayout l3 = (RelativeLayout) findViewById(R.id.bottomContainer);
		l.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				int action = MotionEventCompat.getActionMasked(event);
				switch(action){
				case MotionEvent.ACTION_DOWN:
				if(!sendRaveOpen){
				
					//settings.setVisibility(View.GONE);
				send.setVisibility(View.VISIBLE);
				tv.setText("Send Rave");
					//v.setLayoutParams(params);
				ExpandAnimation anim  = new ExpandAnimation(l2,height, true,125);
				anim.setDuration(500);
				l.startAnimation(anim);
				sendRaveOpen = true;
				return true;
				}
				
				if(sendRaveOpen){
					send.setVisibility(View.GONE);
					tv.setText("View Rave");
					ExpandAnimation anim  = new ExpandAnimation(l2,height-25, false,125);
					anim.setDuration(500);
					l.startAnimation(anim);
					sendRaveOpen = false;
				return true;
				}
				
				}
				
				
				
			
				return true;
			}
			
			
		});
		
		
		
		
		
		send.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				
				Toast.makeText(getApplicationContext(), "Send clicked", Toast.LENGTH_SHORT).show();
				return true;
			}
		});
		
		l3.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				int action = MotionEventCompat.getActionMasked(event);
				switch(action){
				case MotionEvent.ACTION_UP:
					send.setVisibility(View.GONE);
					//settings.setVisibility(View.VISIBLE);
					tv.setText("View Rave");
				//	iv.setVisibility(View.VISIBLE);
					ExpandAnimation anim  = new ExpandAnimation(l2,height-25, false,125);
					anim.setDuration(500);
					l.startAnimation(anim);
					sendRaveOpen = false;
				return true;
				}
				return true;
			}
		});
		
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.vrave, menu);
		return true;
	}
	
	public class ExpandAnimation extends Animation {
	    private final int targetHeight;
	    private final View view;
	    private final boolean down;
	    private final int startHeight;

	    public ExpandAnimation(View view, int targetHeight, boolean down, int startHeight) {
	        this.view = view;
	        this.targetHeight = targetHeight;
	        this.down = down;
	        this.startHeight = startHeight;
	    }

	    @Override
	    protected void applyTransformation(float interpolatedTime, Transformation t) {
	        int newHeight;
	        if (down) {
	            newHeight = (int) ((targetHeight-startHeight) * interpolatedTime)+startHeight;
	        } else {
	            newHeight = (int) ((targetHeight) * (1 - interpolatedTime))+startHeight;
	        }
	        view.getLayoutParams().height = newHeight;
	        view.requestLayout();
	       
	    }

	    @Override
	    public void initialize(int width, int height, int parentWidth,
	            int parentHeight) {
	        super.initialize(width, height, parentWidth, parentHeight);
	    }

	    @Override
	    public boolean willChangeBounds() {
	        return true;
	    }
	}

}
