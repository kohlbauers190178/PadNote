package net.htlgkr.kohlbauers190178.padnote;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyViewModel viewModel = new ViewModelProvider(this).get(MyViewModel.class);
        viewModel.state.observe(this, state -> {
            FragmentTransaction fragTransaction = getSupportFragmentManager().beginTransaction();
            switch (state) {
                case MyViewModel.SHOW_NEW_NOTE:
                    fragTransaction.addToBackStack("").replace(R.id.cntsrtntMain, NewNoteFragment.newInstance("", ""), "New Note");
                    break;
                case MyViewModel.SHOW_SAVE_NOTE:
                    fragTransaction.addToBackStack("").replace(R.id.cntsrtntMain, SaveNoteFragment.newInstance("", ""), "Save Note");
                    break;
                case MyViewModel.SHOW_EDIT_POPUP:
                    //showEditPopUp();
                    break;
                default:
                    fragTransaction.addToBackStack("").replace(R.id.cntsrtntMain, MainFragment.newInstance("", ""), "MainFragment");
                    break;
            }
            fragTransaction.commit();
        });


        getSupportFragmentManager().beginTransaction().addToBackStack("").replace(R.id.cntsrtntMain, new MainFragment()).commit();

    }


}