package net.htlgkr.kohlbauers190178.padnote;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

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
                    showEditPopUp();
                    break;
                default:
                    fragTransaction.addToBackStack("").replace(R.id.cntsrtntMain, MainFragment.newInstance("", ""), "MainFragment");
                    break;
            }
            fragTransaction.commit();
        });


        getSupportFragmentManager().beginTransaction().addToBackStack("").replace(R.id.cntsrtntMain, new MainFragment()).commit();

    }

    ArrayList<EditNotesPopUpFragment> popups = new ArrayList<>();

    private void showEditPopUp() {

        /*
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final View editPopUp = getLayoutInflater().inflate(R.layout.fragment_edit_notes_pop_up, null);

        builder.setView(editPopUp);
        builder.create().show();*/
        for (EditNotesPopUpFragment editNotesPopUpFragment : popups) {
            editNotesPopUpFragment.dismiss();
        }

        EditNotesPopUpFragment editNotesPopUpFragment = new EditNotesPopUpFragment();

        popups.add(editNotesPopUpFragment);

        editNotesPopUpFragment.show(getSupportFragmentManager(), "Edit Popup");
    }
}