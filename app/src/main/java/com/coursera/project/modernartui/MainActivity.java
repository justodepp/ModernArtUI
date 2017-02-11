package com.coursera.project.modernartui;

import android.support.v7.app.ActionBarActivity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.LayerDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SeekBar;


public class MainActivity extends ActionBarActivity {
	
	private SeekBar mSeekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        mSeekBar = (SeekBar) findViewById(R.id.seekBar1);
        
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
        	@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				// TODO Auto-generated method stub
                if (progress <= 50) {
                    setProgressBarColor(mSeekBar, Color.rgb(
                            255 - (255 / 100 * (100 - progress * 2)),
                                                255, 0));
                } else {
                    setProgressBarColor(mSeekBar, Color.rgb(255,
                            255 - (255 / 100 * (progress - 50) * 2), 0));
                }			
			}
        	
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
        });        

    }
    
    public void setProgressBarColor(SeekBar bar, int newColor) {
        LayerDrawable ld = (LayerDrawable) bar.getProgressDrawable();
        ClipDrawable d1 = (ClipDrawable) ld.findDrawableByLayerId(R.id.progressshape);
        d1.setColorFilter(newColor, PorterDuff.Mode.SRC_IN);
 
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id){
            case R.id.action_settings:
                showMenu();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    
    public void showMenu(){
        Log.d("showDialogMenu()", "Method was called.");
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //Setting Dialog Title
        builder.setTitle(R.string.action_settings);
        //Setting Dialog Message
        builder.setMessage(R.string.message);
        //create Cancel button on AlertDialogMenu
        builder.setNeutralButton("Not Now", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                dialog.cancel();
            }
        });

        //create Web Site button on AlertDialogMenu
        builder.setPositiveButton("Visit MoMA Site", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int item) {
                Uri uri = Uri.parse("http://www.moma.org");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
