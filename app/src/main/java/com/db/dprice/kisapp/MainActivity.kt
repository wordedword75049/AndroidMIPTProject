package com.db.dprice.kisapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val openProfileBtn: Button = findViewById(R.id.open_profile_btn)

        openProfileBtn.setOnClickListener {
            val profileActivityIntent = Intent(applicationContext, ProfileActivity::class.java)
            startActivity(profileActivityIntent)
        }

        val sendEmailBtn: Button = findViewById(R.id.send_email_btn)

        sendEmailBtn.setOnClickListener {
            val mailIntent = Intent(Intent.ACTION_SEND)

            mailIntent.type = "plain/text";
            mailIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf("to@email.com"));
            mailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
            mailIntent.putExtra(Intent.EXTRA_TEXT, "Best regards, sent from KISApp");

            startActivity(Intent.createChooser(mailIntent, "Send mail..."));


        }
    }
}