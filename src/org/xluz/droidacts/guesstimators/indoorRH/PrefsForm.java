package org.xluz.droidacts.guesstimators.indoorRH;
/*
Copyright (c) 2011 Cecil Cheung
This software is released under the GNU General Public License version 2.
See, for example, "http://opensource.org/licenses/gpl-2.0.php".
*/
import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * @author Cecil Cheung
 * Comments, requests, bug reports go to the project page "http://code.google.com/p/guesstimators"
 */
public class PrefsForm extends PreferenceActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.defprefs);
	}

}
