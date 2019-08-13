package org.xluz.droidacts.guesstimators.indoorRH;
/*
  Fragment class to host a preference xml
 
Copyright (c) 2014, 2019 Cecil Cheung
This software is released under the GNU General Public License version 2.
See, for example, "http://opensource.org/licenses/gpl-2.0.php".
*/
import android.os.Bundle;
import androidx.preference.PreferenceFragmentCompat;

public class PrefsFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.defprefs, rootKey);
    }

}
