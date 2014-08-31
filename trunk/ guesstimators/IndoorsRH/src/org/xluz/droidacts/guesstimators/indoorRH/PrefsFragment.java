package org.xluz.droidacts.guesstimators.indoorRH;
/*
  Fragment class to host a preference xml
 
Copyright (c) 2014 Cecil Cheung
This software is released under the GNU General Public License version 2.
See, for example, "http://opensource.org/licenses/gpl-2.0.php".
*/
import android.os.Bundle;
import android.preference.PreferenceFragment;

public class PrefsFragment extends PreferenceFragment {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.defprefs);
	}

}
