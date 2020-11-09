package edu.ualr.intentsassignment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

import edu.ualr.intentsassignment.model.Contact;

public class ContactFormActivity extends AppCompatActivity {
    // TODO 01. Create a new layout file to define the GUI elements of the ContactFormActivity.
    // TODO 02. Define the basic skeleton of the ContactFormActivity. Inflate the layout defined in the first step to display the GUI elements on screen.
    public static final String EXTRA_CONTACT = "contact";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_form_layout);
        MaterialButton button = findViewById(R.id.saveButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onButtonClicked();
            }
        });
    }
    // TODO 06. Create a new method that reads the values in the several EditText elements of the layout and then uses the Contact class to send those data to ContactInfoActivity
    protected void onButtonClicked()
    {
        Intent intent = new Intent(this, ContactInfoActivity.class);
        Contact contact = new Contact();
        contact.setFirstName(findViewById(R.id.firstInputEditText).toString());
        contact.setLastName(findViewById(R.id.lastInputEditText).toString());
        contact.setPhoneNumber(findViewById(R.id.phoneInputEditText).toString());
        contact.setAddress(findViewById(R.id.addressInputEditText).toString());
        contact.setEmailAddress(findViewById(R.id.emailInputEditText).toString());
        contact.setWebsite(findViewById(R.id.websiteInputEditText).toString());
        intent.putExtra(EXTRA_CONTACT, contact);
        startActivity(intent);
    }
}
