package org.xluz.droidacts.guesstimators.IndoorRH;
/** This is the main UI fragment
 *
 *  Copyright (c) 2024 Cecil Cheung, PhD
 *  This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0.
 *  See, for example, "http://mozilla.org/MPL/2.0/".
 */
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;
import android.content.SharedPreferences;
import org.xluz.droidacts.guesstimators.IndoorRH.databinding.FragmentFirstBinding;

public class FirstFragment extends Fragment {
    SharedPreferences appPrefs;
    SeekBar.OnSeekBarChangeListener oneBarHandle;
    int sb1, sb3, outT, outRH, inT, inRH;
    private FragmentFirstBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        oneBarHandle = new SBhandler();
        binding.SeekBar01.setOnSeekBarChangeListener(oneBarHandle);
        binding.SeekBar02.setOnSeekBarChangeListener(oneBarHandle);
        binding.SeekBar03.setOnSeekBarChangeListener(oneBarHandle);
        appPrefs = PreferenceManager.getDefaultSharedPreferences(this.getContext());
    }

    @Override
    public void onStart() {
        super.onStart();
        updateAllSeekbars();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
   private void updateAllSeekbars() {
        int p;

        p = binding.SeekBar01.getProgress();
        oneBarHandle.onProgressChanged(binding.SeekBar01, p, true);
        p = binding.SeekBar02.getProgress();
        oneBarHandle.onProgressChanged(binding.SeekBar02, p, true);
        p = binding.SeekBar03.getProgress();
        oneBarHandle.onProgressChanged(binding.SeekBar03, p, true);
        oneBarHandle.onStopTrackingTouch(binding.SeekBar03);
    }

    private class SBhandler implements SeekBar.OnSeekBarChangeListener {
        /* Full range of the seekBar:
         * progress is set from 0 to 100, temperature ranges from 0 to 50 Celsius
         */
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            String Tunit = appPrefs.getString("PREFS_UNITS", "F");
            String TunitSym;

            if (seekBar == binding.SeekBar01) {
                if (Tunit.compareTo("F") == 0) {    // Fahrenheit scale
                    TunitSym = getString(R.string.fahrenheit);
                    outT = (int) (progress * 9 / 10.0 + 32.5);
                } else {                         // Celsius scale
                    TunitSym = getString(R.string.celsius);
                    outT = progress / 2;
                }
                sb1 = progress;
                binding.TextView01.setText(Integer.toString(outT) + TunitSym);
            } else if (seekBar == binding.SeekBar02) {
                outRH = progress;
                binding.TextView02.setText(Integer.toString(progress) + "%");
            } else if (seekBar == binding.SeekBar03) {
                if (Tunit.compareTo("F") == 0) {    // Fahrenheit scale
                    TunitSym = getString(R.string.fahrenheit);
                    inT = (int) (progress * 9 / 10.0 + 32.5);
                } else {                         // Celsius
                    TunitSym = getString(R.string.celsius);
                    inT = progress / 2;
                }
                sb3 = progress;
                binding.TextView03.setText(Integer.toString(inT) + TunitSym);
            }
        }

        public void onStartTrackingTouch(SeekBar seekBar) {
            // TODO Auto-generated method stub
        }

        public void onStopTrackingTouch(SeekBar seekBar) {
            // values in seekBar divided by 2 gives temperatures in Celsius
            inRH = indoorRH.calcRHin(sb1 / 2, sb3 / 2, outRH);

            binding.TextView04.setText(Integer.toString(inRH) + "%");
            binding.SeekBar04.setProgress(inRH);
        }
    }
}