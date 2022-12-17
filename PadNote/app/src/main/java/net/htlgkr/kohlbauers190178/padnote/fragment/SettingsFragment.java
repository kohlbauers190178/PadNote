package net.htlgkr.kohlbauers190178.padnote.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.switchmaterial.SwitchMaterial;

import net.htlgkr.kohlbauers190178.padnote.R;
import net.htlgkr.kohlbauers190178.padnote.viewmodel.FragmentStateViewModel;
import net.htlgkr.kohlbauers190178.padnote.viewmodel.SettingsViewModel;

import java.time.format.DateTimeFormatter;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SettingsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SettingsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SettingsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SettingsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SettingsFragment newInstance(String param1, String param2) {
        SettingsFragment fragment = new SettingsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    private FloatingActionButton btnSettingsDone;

    private SwitchMaterial swtch24hourFormat;
    private SwitchMaterial swtchUSDateFormat;
    private SwitchMaterial swtchShowExpiredNotes;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    SettingsViewModel settingsViewModel;
    FragmentStateViewModel fragmentStateViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        btnSettingsDone = view.findViewById(R.id.btnSettingsDone);
        swtch24hourFormat = view.findViewById(R.id.swtch24hourFormat);
        swtchUSDateFormat = view.findViewById(R.id.swtchUSDateFormat);
        swtchShowExpiredNotes = view.findViewById(R.id.swtchShowExpiredNotes);

        settingsViewModel = new ViewModelProvider(requireActivity()).get(SettingsViewModel.class);
        fragmentStateViewModel = new ViewModelProvider(requireActivity()).get(FragmentStateViewModel.class);

        settingsViewModel.loadSettings(getContext());


        swtch24hourFormat.setChecked(settingsViewModel.TWNTYFOURHOURFORMAT);

        swtchUSDateFormat.setChecked(settingsViewModel.USDATEFORMAT);

        swtchShowExpiredNotes.setChecked(settingsViewModel.SHOWEXPIREDNOTES);


        btnSettingsDone.setOnClickListener(v -> {

            if (swtch24hourFormat.isChecked()) {
                settingsViewModel.TWNTYFOURHOURFORMAT = true;
                settingsViewModel.currentTimeFormatter = DateTimeFormatter.ofPattern(settingsViewModel.TIMEPATTERN_24HOURFORMAT);
            } else {
                settingsViewModel.TWNTYFOURHOURFORMAT = false;
                settingsViewModel.currentTimeFormatter = DateTimeFormatter.ofPattern(settingsViewModel.TIMEPATTERN_12HOURFORMAT);
            }

            if (swtchUSDateFormat.isChecked()) {
                settingsViewModel.USDATEFORMAT = true;
                settingsViewModel.currentDateFormatter = DateTimeFormatter.ofPattern(settingsViewModel.DATEPATTERN_US);
            } else {
                settingsViewModel.USDATEFORMAT = false;
                settingsViewModel.currentDateFormatter = DateTimeFormatter.ofPattern(settingsViewModel.DATEPATTERN_EUROPEAN);
            }

            settingsViewModel.SHOWEXPIREDNOTES = swtchShowExpiredNotes.isChecked();

            settingsViewModel.saveSettings(getContext());
            fragmentStateViewModel.showMain();

        });


        return view;
    }

    /*public void processLoadedSettings(Map<String, Boolean> settings) {

        for (Map.Entry<String, Boolean> setting : settings.entrySet()) {

            switch (setting.getKey()) {
                case SettingsConstants.SETTING_24HOURFORMAT:
                    swtch24hourFormat.setChecked(setting.getValue());
                    break;
                case SettingsConstants.SETTING_USDATEFORMAT:
                    swtchUSDateFormat.setChecked(setting.getValue());
                    break;
                case SettingsConstants.SETTING_SHOWEXPIREDNOTE:
                    switchShowExpiredNotes.setChecked(setting.getValue());
                    break;
            }

        }
    }*/

}