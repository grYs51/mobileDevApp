package com.example.mobiledevapp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import androidx.preference.SwitchPreferenceCompat;

import static com.example.mobiledevapp.MainActivity.settings;

public class SettingsActivity extends AppCompatActivity {


    public boolean[] listOptions;
    public static final String EXTRA_REPLY = "com.example.smalltalkapp.extra.REPLY";
    private static Activity msettings;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Intent intent = getIntent();
        listOptions = intent.getBooleanArrayExtra(MainActivity.EXTRA_MESSAGE);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.settings, new SettingsFragment())
                .commit();

        setTheme(R.style.SettingsFragmentStyle);


        //Toolbar
        Toolbar myToolbar = findViewById(R.id.toolbar_settings); //Shows name of app in the toolbar.
        myToolbar.setTitleTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimaryDark));
        setSupportActionBar(myToolbar);


        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);   //show back button
    }

    public static class SettingsFragment extends PreferenceFragmentCompat {
        public static boolean DarkTheme;

        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey);


            Preference button = findPreference(getString(R.string.reset));
            button.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    MainActivity.counter = 0;
                    SharedPreferences.Editor editor = settings.edit();
                    editor.putInt("counter", MainActivity.counter);
                    editor.commit();
                    MainActivity.tvCounter.setText(Integer.toString(MainActivity.counter));
                    return true;
                }
            });


            final SwitchPreferenceCompat darkswitch = findPreference("darkTheme");
            darkswitch.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {
                    darkswitch.setChecked(settings.getBoolean("DarkTheme", false));

                    if (darkswitch.isChecked()) {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                        darkswitch.setChecked(false);

                        DarkTheme = false;

                        SharedPreferences.Editor editor = settings.edit();
                        editor.putBoolean("DarkTheme", DarkTheme);
                        editor.commit();
                    } else {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                        darkswitch.setChecked(true);

                        DarkTheme = true;

                        SharedPreferences.Editor editor = settings.edit();
                        editor.putBoolean("DarkTheme", DarkTheme);
                        editor.commit();
                    }

                    return false;
                }
            });


        }
    }


    @Override
    public boolean onSupportNavigateUp() {
        Intent replyIntent = new Intent();
        replyIntent.putExtra(EXTRA_REPLY, listOptions);
        setResult(RESULT_OK, replyIntent);
        finish();
        return true;
    }

}