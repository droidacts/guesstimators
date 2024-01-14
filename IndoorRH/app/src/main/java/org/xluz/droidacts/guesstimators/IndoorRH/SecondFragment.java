package org.xluz.droidacts.guesstimators.IndoorRH;

import android.os.Bundle;
import androidx.preference.PreferenceFragmentCompat;

public class SecondFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.defprefs, rootKey);
    }

}
