package com.d2.bookmys;

import android.content.Intent;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.d2.bookmys.MoviesFragment.OnListFragmentInteractionListener;
import com.d2.bookmys.dummy.DummyContent.DummyItem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyMoviesRecyclerViewAdapter extends RecyclerView.Adapter<MyMoviesRecyclerViewAdapter.ViewHolder> {

    private final List<DummyItem> mValues;
    private final OnListFragmentInteractionListener mListener;

    public MyMoviesRecyclerViewAdapter(List<DummyItem> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_movies, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mItem = mValues.get(position);
        //MainActivity.poss = position;
        holder.mContentView.setText(mValues.get(position).content);
        if (position == 0)
            holder.movie_image.setImageResource(R.drawable.av);
        else if (position == 1)
            holder.movie_image.setImageResource(R.drawable.hulk);
        else if (position == 2)
            holder.movie_image.setImageResource(R.drawable.cap);
        else if (position == 3)
            holder.movie_image.setImageResource(R.drawable.joker);
        else if (position == 4)
            holder.movie_image.setImageResource(R.drawable.jumanji);
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(">>>>>", "Clicked");
                //MainActivity.poss = position;
                HashMap hashMap = getQuery(position + 1);
                Intent intent = new Intent(v.getContext(), MovieActivity.class);
                intent.putExtra("POSITION", position);
                if (hashMap == null) {
                    Log.d(">>>>>", "Query Failed !");
                } else {
                    intent.putExtra("MOVIE_MAP", hashMap);
                }
                v.getContext().startActivity(intent);
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mContentView, ratings;
        public DummyItem mItem;
        public ImageView movie_image;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mContentView = (TextView) view.findViewById(R.id.movie_name);
            ratings = (TextView) view.findViewById(R.id.movie_rating);
            movie_image = (ImageView) view.findViewById(R.id.movie_image);
            /*mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d(">>>>>", "Clicked");
                }
            });*/
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }

    public HashMap getQuery(int position_movie) {
        Connection connection = get_connection();
        String name, duration, genre, description, ratings;
        int movie_id;
        /*if(get_connection()==null){
            Log.d(">>>>>", "Error Connecting to Database !");
            return null;
        }
        else{
            Log.d(">>>>>", "Successfully Connected to Database !");
        }*/
        String sql = "SELECT * from movies WHERE movie_id = " + position_movie;
        try {
            /*Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            name=resultSet.getString("name");
            duration=resultSet.getString("duration");
            genre=resultSet.getString("genre");
            description=resultSet.getString("description");
            ratings=resultSet.getString("ratings");*/
            name = "Avengers";
            duration = "3hrs 1min";
            genre = "Action";
            description = "Some Description XYZ";
            ratings = "95%";
            HashMap<String, String> hashMap = new HashMap<String, String>();
            hashMap.put("name", name);
            hashMap.put("duration", duration);
            hashMap.put("genre", genre);
            hashMap.put("description", description);
            hashMap.put("ratings", ratings);
            return hashMap;
        } catch (Exception e) {
            Log.d(">>>>>", "Error Executing Query !");
        }

        return null;
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
