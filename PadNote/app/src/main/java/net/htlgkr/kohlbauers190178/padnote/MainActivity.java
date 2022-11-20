package net.htlgkr.kohlbauers190178.padnote;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                default:
                    fragTransaction.replace(R.id.cnstrntMain, MainFragment.newInstance("", ""), "MainFragment");
                    break;
            }
            fragTransaction.commit();
        });


        getSupportFragmentManager().beginTransaction().replace(R.id.cnstrntMain, new MainFragment()).commit();

    }


}