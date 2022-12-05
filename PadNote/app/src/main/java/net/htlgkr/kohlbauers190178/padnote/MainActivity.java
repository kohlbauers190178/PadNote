package net.htlgkr.kohlbauers190178.padnote;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.WindowManager;

import net.htlgkr.kohlbauers190178.padnote.fragment.EditNotesFragment;
import net.htlgkr.kohlbauers190178.padnote.fragment.MainFragment;
import net.htlgkr.kohlbauers190178.padnote.fragment.NewNoteFragment;
import net.htlgkr.kohlbauers190178.padnote.fragment.SettingsFragment;
import net.htlgkr.kohlbauers190178.padnote.viewmodel.FragmentStateViewModel;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        FragmentStateViewModel viewModel = new ViewModelProvider(this).get(FragmentStateViewModel.class);
        viewModel.state.observe(this, state -> {
            FragmentTransaction fragTransaction = getSupportFragmentManager().beginTransaction();
            switch (state) {
                case FragmentStateViewModel.SHOW_NEW_NOTE:
                    fragTransaction.addToBackStack("").replace(R.id.cnstrntMain, NewNoteFragment.newInstance("", ""), "New Note");
                    fragTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                    break;
                case FragmentStateViewModel.SHOW_EDIT_POPUP:
                    fragTransaction.addToBackStack("").replace(R.id.cnstrntMain, EditNotesFragment.newInstance("", "", ""), "Edit Note");
                    fragTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                    break;
                case FragmentStateViewModel.SHOW_SETTINGS:
                    fragTransaction.addToBackStack("").replace(R.id.cnstrntMain, SettingsFragment.newInstance("", ""), "Settings");
                    fragTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                    break;
                default:
                    fragTransaction.replace(R.id.cnstrntMain, MainFragment.newInstance("", ""), "MainFragment");
                    break;
            }
            fragTransaction.commit();
        });


        getSupportFragmentManager().beginTransaction().replace(R.id.cnstrntMain, new MainFragment()).commit();

    }


}