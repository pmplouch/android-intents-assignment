package edu.ualr.intentsassignment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import edu.ualr.intentsassignment.model.Contact;

public class ContactInfoActivity extends AppCompatActivity {
    // TODO 03. Create a new layout file to define the GUI elements of the ContactInfoActivity.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_info_activity);
        final Contact c = getIntent().getParcelableExtra(ContactFormActivity.EXTRA_CONTACT);
        populateText(c);
        ChipGroup chipGroup = findViewById(R.id.chip_group);
        chipGroup.setOnCheckedChangeListener(new ChipGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(ChipGroup group, int checkedId) {
                int checkedChipId = group.getCheckedChipId();
                if(checkedChipId == R.id.callChip)
                {
                    dialPhone(c);
                }
                else if(checkedChipId == R.id.textChip)
                {
                    sendText(c);
                }
                else if(checkedChipId == R.id.emailChip)
                {
                    sendEmail(c);
                }
                else if(checkedChipId == R.id.mapChip)
                {
                    openMaps(c);
                }
                else if(checkedChipId == R.id.webChip)
                {
                    openWeb(c);
                }
            }
        });
    }
    // TODO 04. Define the basic skeleton of the ContactInfoActivity. Inflate the layout defined in the first step to display the GUI elements on screen.
    // TODO 07. Create a new method that reads the contact info coming from ContactFormActivity and use it to populate the several TextView elements in the layout.
    public void populateText(Contact c)
    {
        TextView name = findViewById(R.id.contact_name);
        name.setText(String.format("%s %s", c.getFirstName(), c.getLastName()));
        TextView phone = findViewById(R.id.phone_info);
        phone.setText(c.getPhoneNumber());
        TextView email = findViewById(R.id.email_info);
        email.setText(c.getEmailAddress());
        TextView address = findViewById(R.id.address_info);
        address.setText(c.getAddress());
        TextView website = findViewById(R.id.website_info);
        website.setText(c.getWebsite());
    }
    // TODO 08. Create a new method that invokes a Phone Dialer app, using as parameter the phone number included in the contact info received from ContactFormActivity in the previous step
   public void dialPhone(Contact c)
   {
       String phoneNumberUri = String.format("tel:%s", c.getPhoneNumber());
       Intent dial = new Intent(Intent.ACTION_DIAL, Uri.parse(phoneNumberUri));
       startActivity(dial);
   }
    // TODO 09. Create a new method that invokes a Messages app, using as parameter the phone number included in the contact info received from ContactFormActivity in the 7th step
    public void sendText(Contact c)
    {
        String phoneNumberUri = String.format("tel:%s", c.getPhoneNumber());
        Intent text = new Intent(Intent.ACTION_SENDTO, Uri.parse(phoneNumberUri));
        startActivity(text);
    }
    // TODO 10. Create a new method that invokes a Maps app, using as parameter the address included in the contact info received from ContactFormActivity in the 7th step
    public void openMaps(Contact c)
    {
        String place = c.getAddress();
        String placeUri = String.format("geo:0,0?q=(%s)", place);
        Intent geo = new Intent(Intent.ACTION_VIEW, Uri.parse(placeUri));
        startActivity(geo);
    }
    // TODO 11. Create a new method that invokes an Email app, using as parameter the email address included in the contact info received from ContactFormActivity in the 7th step
    public void sendEmail(Contact c)
    {
        String emailReceiver = c.getEmailAddress();
        Intent email = new Intent(Intent.ACTION_SEND);
        email.setType("text/plain");
        email.putExtra(Intent.EXTRA_EMAIL, emailReceiver);
        startActivity(email);
    }
    // TODO 12. Create a new method that invokes an Web Browser app, using as parameter the web url included in the contact info received from ContactFormActivity in the 7th step
    public void openWeb(Contact c)
    {
        String webUri = c.getWebsite();
        Intent website = new Intent(Intent.ACTION_VIEW, Uri.parse(webUri));
        startActivity(website);
    }
}
