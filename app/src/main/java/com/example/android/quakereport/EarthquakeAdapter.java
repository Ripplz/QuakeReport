package com.example.android.quakereport;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * Created by DELL on 11/20/2017.
 * An {@link EarthquakeAdapter} knows how to create a list item layout for each earthquake
 * in the data source (a list of {@link EarthQuake} objects).
 *
 * These list item layouts will be provided to an adapter view like ListView
 * to be displayed to the user.
 */

public class EarthquakeAdapter extends ArrayAdapter<EarthQuake> {

    /**
     * Constructs a new {@link EarthquakeAdapter}.
     *
     * @param context of the app.
     * @param earthquakes is the list of earthquakes, which is the data source of the adapter.
     */
    public EarthquakeAdapter(Activity context, ArrayList<EarthQuake> earthquakes) {
        super(context, 0, earthquakes);
    }

    /**
     * Returns a list item view that displays information about the earthquake at the given position
     * in the list of earthquakes.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Check if there is an existing list item view (called convertView) that we can reuse,
        // otherwise, if convertView is null, then inflate a new list item layout.
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.earthquake_list_view, parent, false);
        }

        // Find the earthquake at the given position in the list of earthquakes
        EarthQuake currentEarthquake = getItem(position);

        // Find the TextView that displays the earthquake magnitude and display the
        // appropriate magnitude of the current earthquake.
        TextView magnitudeView = listItemView.findViewById(R.id.earthquake_listview_magnitude);
        magnitudeView.setText(formatDecimal(currentEarthquake.getMagnitude()));

        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) magnitudeView.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(currentEarthquake.getMagnitude());

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);

        // Find the TextView that displays the earthquake primary location
        TextView primaryLocation = listItemView.findViewById(R.id.earthquake_listview_primary_location);

        // Find the TextView that displays the earthquake offset location
        TextView offsetLocation = listItemView.findViewById(R.id.earthquake_listview_location_offset);

        // Get the raw location from the Earthquake object
        String location = currentEarthquake.getLocation();

        // Check if the location has an offset. If yes, split it into the primary location
        // and offset. Else, set the offset to "Near the'
        if (location.contains("of")) {
            String[] locations = location.split("of");
            offsetLocation.setText(locations[0] + "of");
            primaryLocation.setText(locations[1]);
        } else {
            // display the appropriate location of the current earthquake.
            offsetLocation.setText("Near the");
            primaryLocation.setText(location);
        }

        // Create a new Date object from the time in milliseconds of the earthquake
        Date dateObject = new Date(currentEarthquake.getTimeInMilliseconds());


        // Find the TextView that displays the earthquake date and display the
        // appropriate date of the current earthquake.
        TextView dateView = listItemView.findViewById(R.id.earthquake_listview_date);
        // Format the date string
        String formattedDate = formatDate(dateObject);
        dateView.setText(formattedDate);

        // Find the TextView that displays the earthquake time and display the
        // appropriate time of the current earthquake.
        TextView timeView = listItemView.findViewById(R.id.earthquake_listview_time);
        // Format the time string
        String formattedTime = formatTime(dateObject);
        timeView.setText(formattedTime);

        // Return the list item view that is now showing the appropriate data
        return listItemView;
    }

    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy", Locale.UK);
        return dateFormat.format(dateObject);
    }

    private String formatTime(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("h:mm a", Locale.UK);
        return dateFormat.format(dateObject);
    }

    private String formatDecimal(double magnitude) {
        DecimalFormat magnitudeFormat = new DecimalFormat("0.0");
        return magnitudeFormat.format(magnitude);
    }

    private int getMagnitudeColor(double magnitude) {
        int magnitudeFloor = (int) Math.floor(magnitude);
        int backgroundResourceId;
        switch ((int) magnitudeFloor) {
            case 0:
            case 1:
                backgroundResourceId = R.color.magnitude1;
                break;
            case 2:
                backgroundResourceId = R.color.magnitude2;
                break;
            case 3:
                backgroundResourceId = R.color.magnitude3;
                break;
            case 4:
                backgroundResourceId = R.color.magnitude4;
                break;
            case 5:
                backgroundResourceId = R.color.magnitude5;
                break;
            case 6:
                backgroundResourceId = R.color.magnitude6;
                break;
            case 7:
                backgroundResourceId = R.color.magnitude7;
                break;
            case 8:
                backgroundResourceId = R.color.magnitude8;
                break;
            case 9:
                backgroundResourceId = R.color.magnitude9;
                break;
            default:
                backgroundResourceId = R.color.magnitude10plus;
                break;
        }

        return ContextCompat.getColor(getContext(), backgroundResourceId);
    }
}
