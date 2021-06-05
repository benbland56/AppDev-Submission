package com.example.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.spotify.android.appremote.api.ConnectionParams;
import com.spotify.android.appremote.api.Connector;
import com.spotify.android.appremote.api.SpotifyAppRemote;
import com.spotify.android.appremote.api.error.AuthenticationFailedException;
import com.spotify.android.appremote.api.error.CouldNotFindSpotifyApp;
import com.spotify.android.appremote.api.error.LoggedOutException;
import com.spotify.android.appremote.api.error.NotLoggedInException;
import com.spotify.android.appremote.api.error.OfflineModeException;
import com.spotify.android.appremote.api.error.SpotifyConnectionTerminatedException;
import com.spotify.android.appremote.api.error.SpotifyDisconnectedException;
import com.spotify.android.appremote.api.error.SpotifyRemoteServiceException;
import com.spotify.android.appremote.api.error.UnsupportedFeatureVersionException;
import com.spotify.android.appremote.api.error.UserNotAuthorizedException;
import com.spotify.protocol.client.CallResult;
import com.spotify.protocol.types.Track;

import java.util.ArrayList;

//TODO add media control buttons

public class Media extends AppCompatActivity {
    private static final String CLIENT_ID = "8b2d9113a2224ed99459796ff069a64e";
    private static final String REDIRECT_URI = "http://localhost:8888/";

    private SpotifyAppRemote mSpotifyAppRemote;
    private TextView textView;
    private ImageView play;
    private ImageView albumImage;
    private ImageView nextTrack;
    private ImageView prevTrack;
    private boolean connected = false;
    private Track currentTrack;

    private ArrayList<String> errorMessages = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media);


        //defines textview
        textView = findViewById(R.id.textView4);

        //defines ImageView of Album art
        albumImage = findViewById(R.id.albumImage);

        // play button
        play = findViewById(R.id.play);
        play.setImageResource(R.drawable.ic_pause_black_60dp);

                play.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onPlayPause();
                    }
                });




        //next track button
        nextTrack = findViewById(R.id.next);
        nextTrack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                play.setImageResource(R.drawable.ic_pause_black_60dp);

                mSpotifyAppRemote.getPlayerApi().skipNext();
            }
        });

        // previous track button
        prevTrack = findViewById(R.id.prev);
        prevTrack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                play.setImageResource(R.drawable.ic_pause_black_60dp);

                mSpotifyAppRemote.getPlayerApi().skipPrevious();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        ConnectionParams connectionParams =
                new ConnectionParams.Builder(CLIENT_ID)
                        .setRedirectUri(REDIRECT_URI)
                        .showAuthView(true)
                        .build();

        SpotifyAppRemote.connect(this, connectionParams,
                new Connector.ConnectionListener() {

                    public void onConnected(SpotifyAppRemote spotifyAppRemote) {
                        mSpotifyAppRemote = spotifyAppRemote;
                        Log.d("mSpotify", "Connected to Spotify");

                        // Now you can start interacting with App Remote
                        connected();

                    }

                    public void onFailure(Throwable error) {
                            if (error instanceof SpotifyRemoteServiceException) {
                                errorMessages.add("Spotify remote disconnected ");
                            } else if (error instanceof IllegalStateException) {
                                errorMessages.add("UnExpected Error ");
                            } else if (error instanceof NotLoggedInException) {
                                errorMessages.add("You are note logged in to Spotify ");
                            } else if (error instanceof AuthenticationFailedException) {
                                errorMessages.add("Spotify Authentication error ");

                            } else if (error instanceof CouldNotFindSpotifyApp) {
                                errorMessages.add("Spotify App not Found, Please Install it");

                            } else if (error instanceof LoggedOutException) {
                                errorMessages.add("You are not logged into Spotify ");

                            } else if (error instanceof OfflineModeException) {
                                errorMessages.add("you are offline ");

                            } else if (error instanceof UserNotAuthorizedException) {
                                errorMessages.add("You do not have permissiosn to use Spotify ");

                            } else if (error instanceof UnsupportedFeatureVersionException) {
                                errorMessages.add("Unsupported Feature ");

                            } else if (error instanceof SpotifyDisconnectedException) {
                                errorMessages.add("App disconnected from Spotify ");

                            } else if (error instanceof SpotifyConnectionTerminatedException) {
                                errorMessages.add("Spotify connection terminated ");
                        }
                        errorToast();
                    }

                });
    }



    @Override
    protected void onStop() {
        super.onStop();
        SpotifyAppRemote.disconnect(mSpotifyAppRemote);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSpotifyAppRemote.getPlayerApi().pause();
        SpotifyAppRemote.disconnect(mSpotifyAppRemote);
    }

    //checks if media is paused and plays if it is
    public void onPlayPause(){
        mSpotifyAppRemote.getPlayerApi().getPlayerState().setResultCallback(playerState -> {
           if(playerState.isPaused) {
               mSpotifyAppRemote.getPlayerApi().resume();
               play.setImageResource(R.drawable.ic_pause_black_60dp);
           } else {
               mSpotifyAppRemote.getPlayerApi().pause();
               play.setImageResource(R.drawable.ic_play_arrow_black_60dp);
           }
        });

    }
    private void errorToast() {
        for(String strings: errorMessages) {
            Toast.makeText(this, strings, Toast.LENGTH_LONG).show();
                   }

    }


    private void connected() {
            mSpotifyAppRemote.getPlayerApi().play("spotify:playlist:37i9dQZF1DX2sUQwD7tbmL");
            mSpotifyAppRemote.getPlayerApi()
                    .subscribeToPlayerState()
                    .setEventCallback(playerState -> {

                        final Track track = playerState.track;
                        if (track != null) {
                           // trackArtist = track.album +"  " + track.name
                            textView.setText(track.name);
                            currentTrack = track;

                            mSpotifyAppRemote.getImagesApi().getImage(track.imageUri).setResultCallback(new CallResult.ResultCallback<Bitmap>() {

                                @Override
                                public void onResult(Bitmap bitmap) {
                                    albumImage.setImageBitmap(bitmap);
                                }
                            });
                        }
                    });

    }


}