package org.xluz.droidacts.guesstimators.indoorRH;
/*
  Preference activity using fragment to host a preference xml

Copyright (c) 2014 Cecil Cheung
This software is released under the GNU General Public License version 2.
See, for example, "http://opensource.org/licenses/gpl-2.0.php".
*/
import java.util.List;
import android.preference.PreferenceActivity;

public class PrefsFormF extends PreferenceActivity {

	@Override
	public void onBuildHeaders(List<Header> target) {
		loadHeadersFromResource(R.xml.prefsheader, target);
		super.onBuildHeaders(target);
	}

}
