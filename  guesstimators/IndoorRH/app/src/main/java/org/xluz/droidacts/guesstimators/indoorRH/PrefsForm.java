package org.xluz.droidacts.guesstimators.indoorRH;
/*
  Preference activity class
  
Copyright (c) 2011, 2019 Cecil Cheung
This software is released under the GNU General Public License version 2.
See, for example, "http://opensource.org/licenses/gpl-2.0.php".
*/
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;


public class PrefsForm extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prefsframe);
//        getSupportFragmentManager()
//                .beginTransaction()
//                .replace(R.id.frame0, new PrefsFragment())
//                .commit();

    }


}
