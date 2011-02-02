package org.xluz.droidacts.guesstimators.indoorRH;
/*
Copyright (c) 2011 Cecil Cheung
This software is released under the GNU General Public License version 2.
See, for example, "http://opensource.org/licenses/gpl-2.0.php".
*/
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.*;
import android.content.*;
import android.view.*;
import android.widget.*;

/**
 * @author Cecil Cheung
 * Comments, requests, bug reports go to the project page "http://code.google.com/p/guesstimators"
 */
public class mainForm extends Activity {
	SharedPreferences appPrefs;
	SeekBar.OnSeekBarChangeListener oneBarHandle;
	int sb1, sb3, outT, outRH, inT, inRH;
	
    @Override
	public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.defmenu0, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()) {
		case R.id.Menu0prefs:
			Intent itt = new Intent(this, PrefsForm.class);
			startActivityForResult(itt, 1);
			// update displayed values
			updateAllSeekbars();
			return true;
		case R.id.Menu0about:
			AlertDialog.Builder aboutDialog = new AlertDialog.Builder(this);
			aboutDialog.setTitle(R.string.msg_about);
			aboutDialog.setMessage(R.string.msg_desc);
			aboutDialog.setPositiveButton("OK", null);
//			aboutDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//		           public void onClick(DialogInterface dialog, int id) {
//		                //MyActivity.this.finish();
//		           }
//		       });
			aboutDialog.show();
			return true;
		default:
		}
		return super.onOptionsItemSelected(item);
	}

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Context appContext = getApplicationContext();
        appPrefs = PreferenceManager.getDefaultSharedPreferences(appContext);
     
        oneBarHandle = new SBhandler();
        SeekBar outBar = (SeekBar)findViewById(R.id.SeekBar01);
        outBar.setOnSeekBarChangeListener(oneBarHandle);
        outBar = (SeekBar)findViewById(R.id.SeekBar02);
        outBar.setOnSeekBarChangeListener(oneBarHandle);
        outBar = (SeekBar)findViewById(R.id.SeekBar03);
        outBar.setOnSeekBarChangeListener(oneBarHandle);     
    }
    
    @Override
	protected void onStart() {
		super.onStart();
        updateAllSeekbars();
 	}

	private void updateAllSeekbars() {
		int p;
		SeekBar outBar = (SeekBar)findViewById(R.id.SeekBar01);
        p = outBar.getProgress();
        oneBarHandle.onProgressChanged(outBar, p, true);
        outBar = (SeekBar)findViewById(R.id.SeekBar02);
        p = outBar.getProgress();
        oneBarHandle.onProgressChanged(outBar, p, true);
        outBar = (SeekBar)findViewById(R.id.SeekBar03);
        p = outBar.getProgress();
        oneBarHandle.onProgressChanged(outBar, p, true);
        oneBarHandle.onStopTrackingTouch(outBar);
	}

	private class SBhandler implements SeekBar.OnSeekBarChangeListener {
		/* Full range of the seekBar:
		 * progress is set from 0 to 100, temperature ranges from 0 to 50 Celsius
		 */
		public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
			String Tunit=appPrefs.getString("PREFS_UNITS","F");
			
			if(seekBar==findViewById(R.id.SeekBar01)) {
				if(Tunit.compareTo("F")==0) {    // Fahrenheit scale
					outT = (int)(progress*9/10.0+32.5); 
				} else {                         // Celsius scale
					outT = progress/2;
				}
				TextView tt=(TextView)findViewById(R.id.TextView01);
				sb1 = progress;
				tt.setText(Integer.toString(outT)+Tunit);
			} else 
			if(seekBar==findViewById(R.id.SeekBar02)) {
				TextView tt=(TextView)findViewById(R.id.TextView02);
				outRH = progress;
				tt.setText(Integer.toString(progress)+"%");
			} else 
			if(seekBar==findViewById(R.id.SeekBar03)) {
				if(Tunit.compareTo("F")==0) {    // Fahrenheit scale
					inT = (int)(progress*9/10.0+32.5); 
				} else {                         // Celsius
					inT = progress/2;
				}
				TextView tt=(TextView)findViewById(R.id.TextView03);
				sb3 = progress;
				tt.setText(Integer.toString(inT)+Tunit);
			}
		}

		public void onStartTrackingTouch(SeekBar seekBar) {
			// TODO Auto-generated method stub
			
		}

		public void onStopTrackingTouch(SeekBar seekBar) {
			// values in seekBar divided by 2 gives temperatures in Celsius
			inRH = indoorRH.calcRHin(sb1/2, sb3/2, outRH);
			
			TextView tt=(TextView)findViewById(R.id.TextView04);
			tt.setText(Integer.toString(inRH)+"%");
	        ProgressBar outBar = (ProgressBar)findViewById(R.id.SeekBar04);
	        outBar.setProgress(inRH);
		}
    
    };
}
