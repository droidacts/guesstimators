package org.xluz.droidacts.guesstimators.indoorRH;
/**
 * @author Cecil Cheung
 * Comments, requests, bug reports go to the project page "github.com/droidacts/guesstimators"
 */
import android.os.Bundle;
import android.app.*;
import android.content.*;
import android.view.*;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

public class MainActivity extends AppCompatActivity {
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
                aboutDialog.show();
                return true;
            default:
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Context appContext = getApplicationContext();
        appPrefs = PreferenceManager.getDefaultSharedPreferences(appContext);

        oneBarHandle = new SBhandler();
        SeekBar outBar = findViewById(R.id.SeekBar01);
        outBar.setOnSeekBarChangeListener(oneBarHandle);
        outBar = findViewById(R.id.SeekBar02);
        outBar.setOnSeekBarChangeListener(oneBarHandle);
        outBar = findViewById(R.id.SeekBar03);
        outBar.setOnSeekBarChangeListener(oneBarHandle);
    }

    @Override
    protected void onStart() {
        super.onStart();
        updateAllSeekbars();
    }

    private void updateAllSeekbars() {
        int p;
        SeekBar outBar = findViewById(R.id.SeekBar01);
        p = outBar.getProgress();
        oneBarHandle.onProgressChanged(outBar, p, true);
        outBar = findViewById(R.id.SeekBar02);
        p = outBar.getProgress();
        oneBarHandle.onProgressChanged(outBar, p, true);
        outBar = findViewById(R.id.SeekBar03);
        p = outBar.getProgress();
        oneBarHandle.onProgressChanged(outBar, p, true);
        oneBarHandle.onStopTrackingTouch(outBar);
    }

    private class SBhandler implements SeekBar.OnSeekBarChangeListener {
        /* Full range of the seekBar:
         * progress is set from 0 to 100, temperature ranges from 0 to 50 Celsius
         */
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            String Tunit = appPrefs.getString("PREFS_UNITS", "F");
            String TunitSym;

            if (seekBar == findViewById(R.id.SeekBar01)) {
                if (Tunit.compareTo("F") == 0) {    // Fahrenheit scale
                    TunitSym = getString(R.string.fahrenheit);
                    outT = (int) (progress * 9 / 10.0 + 32.5);
                } else {                         // Celsius scale
                    TunitSym = getString(R.string.celsius);
                    outT = progress / 2;
                }
                TextView tt = findViewById(R.id.TextView01);
                sb1 = progress;
                tt.setText(Integer.toString(outT) + TunitSym);
            } else if (seekBar == findViewById(R.id.SeekBar02)) {
                TextView tt = (TextView) findViewById(R.id.TextView02);
                outRH = progress;
                tt.setText(Integer.toString(progress) + "%");
            } else if (seekBar == findViewById(R.id.SeekBar03)) {
                if (Tunit.compareTo("F") == 0) {    // Fahrenheit scale
                    TunitSym = getString(R.string.fahrenheit);
                    inT = (int) (progress * 9 / 10.0 + 32.5);
                } else {                         // Celsius
                    TunitSym = getString(R.string.celsius);
                    inT = progress / 2;
                }
                TextView tt = findViewById(R.id.TextView03);
                sb3 = progress;
                tt.setText(Integer.toString(inT) + TunitSym);
            }
        }

        public void onStartTrackingTouch(SeekBar seekBar) {
            // TODO Auto-generated method stub

        }

        public void onStopTrackingTouch(SeekBar seekBar) {
            // values in seekBar divided by 2 gives temperatures in Celsius
            inRH = indoorRH.calcRHin(sb1 / 2, sb3 / 2, outRH);

            TextView tt = findViewById(R.id.TextView04);
            tt.setText(Integer.toString(inRH) + "%");
            ProgressBar outBar = findViewById(R.id.SeekBar04);
            outBar.setProgress(inRH);
        }
    }
}
