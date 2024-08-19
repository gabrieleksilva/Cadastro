package com.example.cadastro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.cadastro.databinding.ActivityMainBinding;
import com.example.cadastro.models.States;
import com.example.cadastro.models.User;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupView();
        listenEvents();
    }

    private void listenEvents() {
        binding.confirmButton.setOnClickListener(onConfirmButton());
        binding.cleanButton.setOnClickListener(onCleanButton());
    }

    @NonNull
    private View.OnClickListener onCleanButton() {
        return view -> {
            binding.fullNameEditText.getText().clear();
            binding.phoneEditText.getText().clear();
            binding.emailEditText.getText().clear();
            binding.cityEditText.getText().clear();
            binding.checkbox.setChecked(false);
            binding.genderRadioGroup.clearCheck();
            binding.stateSpinner.setSelection(0);
        };
    }

    @NonNull
    private View.OnClickListener onConfirmButton() {
        return view -> {
            String name = binding.fullNameEditText.getText().toString();
            String phone = binding.phoneEditText.getText().toString();
            String email = binding.emailEditText.getText().toString();
            String city = binding.cityEditText.getText().toString();
            Boolean shouldJoinMailingList = binding.checkbox.isChecked();
            User.Gender gender = getGenderFromRadioGroup();
            States state = getStateFromSpinner();

            User user = new User(
                    name,phone,email,shouldJoinMailingList,gender,city,state
            );

            Toast.makeText(MainActivity.this,user.toString(),Toast.LENGTH_LONG).show();
        };
    }

    private User.Gender getGenderFromRadioGroup() {
        RadioButton radioButton = (RadioButton) findViewById(binding.genderRadioGroup.getCheckedRadioButtonId());
        String genderValue = radioButton.getText().toString();

        return User.Gender.getGenderByValue(genderValue);
    }

    private States getStateFromSpinner() {
        return States.getStateByDescription((String) binding.stateSpinner.getSelectedItem());
    }

    private void setupView() {
        bindView();
        setStatesOnSpinner();
    }

    private void setStatesOnSpinner() {
        List<String> states = new ArrayList();

        for (States state : States.values()) {
            states.add(state.getDescription());
        }

        ArrayAdapter<String> statesArrayAdapter = new ArrayAdapter<String>(
                MainActivity.this,
                androidx.constraintlayout.widget.R.layout.support_simple_spinner_dropdown_item,
                states
        );
        statesArrayAdapter.setDropDownViewResource(androidx.constraintlayout.widget.R.layout.support_simple_spinner_dropdown_item);
        binding.stateSpinner.setAdapter(statesArrayAdapter);
    }

    private void bindView() {
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}
