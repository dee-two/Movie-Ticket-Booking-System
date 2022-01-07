package com.d2.bookmys;

import android.os.Bundle;
import android.widget.Toast;

import com.d2.bookmys.dummy.DummyContent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class SuccessActivity extends AppCompatActivity {

    public class moviesFragment implements MoviesFragment.OnListFragmentInteractionListener
    {

        public void onListFragmentInteraction(DummyContent.DummyItem item)
        {

        }

        public void asd() {
            MoviesFragment moviesFragment = new MoviesFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.fragment_container, moviesFragment, "tag");
            fragmentTransaction.commit();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_success);
        Toast.makeText(this, "Successfully Logged In !", Toast.LENGTH_LONG).show();
        new moviesFragment().asd();
    }

}
