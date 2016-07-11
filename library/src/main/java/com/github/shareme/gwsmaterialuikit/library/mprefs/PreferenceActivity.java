/*
 * The MIT License (MIT)
 * Copyright (c) 2015 Ferran Negre Pizarro
 * Modifications Copyright(C) 2016 Fred Grott(GrottWorkShop)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 *
 */
package com.github.shareme.gwsmaterialuikit.library.mprefs;

import android.annotation.TargetApi;
import android.app.FragmentTransaction;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.github.shareme.gwsmaterialuikit.library.R;

@SuppressWarnings("unused")
public abstract class PreferenceActivity extends AppCompatPreferenceActivity {

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public void setPreferenceFragment(PreferenceFragment preferenceFragment) {

        //First check if it's already loaded (configuration change) so we don't overlap fragments
        if(getFragmentManager()
                .findFragmentByTag("com.fnp.materialpreferences.MainFragment") != null){
            return;
        }

        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            fragmentTransaction.replace(R.id.content, preferenceFragment,
                    "com.fnp.materialpreferences.MainFragment");
        }else{
            fragmentTransaction.replace(android.R.id.content, preferenceFragment,
                    "com.fnp.materialpreferences.MainFragment");
        }
        fragmentTransaction.commit();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Do not add custom layout for lollipop devices or we lose the widgets animation
        // (app compat bug?)
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            setContentView(R.layout.mp_activity_settings);
            Toolbar toolbar = (Toolbar) findViewById(R.id.mp_toolbar);
            setSupportActionBar(toolbar);
        }

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.HONEYCOMB
                        && getFragmentManager().getBackStackEntryCount() > 0) {
                    getFragmentManager().popBackStack();
                } else {
                    finish();
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
