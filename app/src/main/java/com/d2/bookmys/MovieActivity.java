package com.d2.bookmys;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import androidx.appcompat.app.AppCompatActivity;

public class MovieActivity extends AppCompatActivity {
    public Integer position = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //final String VIDEO_SAMPLE = "https://developers.google.com/training/images/tacoma_narrows.mp4";
        final String VIDEO_SAMPLE = "http://192.168.1.16/myPHP2/avengers.mp4";
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        String name, duration, genre, description, ratings;
        position = getIntent().getExtras().getInt("POSITION");
        HashMap<String, String> hashMap = (HashMap<String, String>) getIntent().getSerializableExtra("MOVIE_MAP");
        name = hashMap.get("name");
        duration = hashMap.get("duration");
        genre = hashMap.get("genre");
        description = hashMap.get("description");
        ratings = hashMap.get("ratings");
        Toast.makeText(this, "Position : " + position, Toast.LENGTH_LONG).show();
        TextView movie_name_tv = findViewById(R.id.movie_name);
        TextView duration_tv = findViewById(R.id.duration);
        TextView genre_tv = findViewById(R.id.genre);
        TextView Description_tv = findViewById(R.id.Description);
        TextView cast_tv = findViewById(R.id.cast_view);
        final EditText comment_add_ed = findViewById(R.id.movie_comment_add);
        Button comment_submit_bt = findViewById(R.id.comment_submit);

        movie_name_tv.setText(name);
        duration_tv.setText(duration);
        genre_tv.setText(genre);
        Description_tv.setText(description);

        comment_submit_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String comment = comment_add_ed.getText().toString();
                boolean res = submit_comment(comment);
                if (res) {
                    Log.d(">>>>>", "Comment Recorded !");
                } else {
                    Log.d(">>>>>", "Error Adding Comment !");
                }
            }
        });

        /*ImageView imageView = (ImageView) findViewById(R.id.movie_image_main);
        if(MainActivity.poss==0)
            imageView.setImageResource(R.drawable.av);
        else if(MainActivity.poss==1)
            imageView.setImageResource(R.drawable.cap);
        else if(MainActivity.poss==2)
            imageView.setImageResource(R.drawable.joker);
        else if(MainActivity.poss==3)
            imageView.setImageResource(R.drawable.jumanji);
        else
            imageView.setImageResource(R.drawable.av);*/

        final VideoView videoView = findViewById(R.id.movie_video_view);
        Uri uri = Uri.parse(VIDEO_SAMPLE);
        videoView.setVideoURI(uri);

        //videoView.setVisibility(VideoView.VISIBLE);
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                //videoView.setVisibility(VideoView.INVISIBLE);
                //videoView.setLayoutParams(new ConstraintLayout.LayoutParams(200, ViewGroup.LayoutParams.WRAP_CONTENT));
                videoView.start();
            }
        });

        /*Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }

    public boolean submit_comment(String comment) {
        Connection connection = get_connection();
        String sql = "INSERT INTO movie_comments(movie_id, comments) VALUES('" + position + 1 + "', '" + comment + "'" + ") WHERE movie_id = " + (position + 1);
        if (get_connection() == null) {
            Log.d(">>>>>", "Error Connecting to Database !");
            return false;
        } else {
            Log.d(">>>>>", "Successfully Connected to Database !");
        }
        try {
            Statement statement = connection.createStatement();
            boolean result = statement.execute(sql);
            return result;
        } catch (Exception e) {
            Log.d(">>>>>", "Error Executing Query !");
        }

        return false;
    }

    public Connection get_connection() {
        Connection connection;
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            //connection = DriverManager.getConnection("jdbc:jtds:sqlserver://" + MainActivity.host + ";" + "databaseName=" + "mydatabase" + ";user=" + "root" + ";password=" + "" + ";");
            connection = DriverManager.getConnection("jdbc:jtds:sqlserver://" + "192.168.1.16" + ":3306/mydatabase", "d2", "");
            Log.d(">>>>>", "Connected To Database");
            return connection;
        } catch (SQLException e1) {
            Log.d(">>>>>sql1", e1.getMessage());
            //e1.printStackTrace();
        } catch (ClassNotFoundException e2) {
            Log.d(">>>>>sql2", e2.getMessage());
        }
        return null;
    }

}
