package zup.com.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;

import org.json.JSONException;
import org.json.JSONObject;

import me.leolin.shortcutbadger.ShortcutBadger;
import zup.com.activities.MovieDetailsActivity;
import zup.com.views.widgets.LoadingTask;
import zup.com.zupfilmes.R;

public class FindFragment extends Fragment {

    LoadingTask progress;
    EditText movieText;
    String searchMovie = "";
    RequestQueue queue;
    int badgeCount = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.find_fragment, container, false);
        getActivity().setTitle("Procurar");

        movieText = (EditText) rootView.findViewById(R.id.etFilme);

        queue = Volley.newRequestQueue(rootView.getContext());

        progress = LoadingTask.setupLoading(this.getContext(), "", "Procurando...", false);

        Button button = (Button) rootView.findViewById(R.id.findButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                searchMovie = movieText.getText().toString();
                searchMovie = searchMovie.replaceAll("\\s+","+");

                progress.show();

                if(isNetworkAvailable()){

                    String url ="http://www.omdbapi.com/?t="+searchMovie+"&y=&plot=full&r=json";

                    StringRequest strRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response)
                                {
                                    try {
                                        JSONObject movieData = new JSONObject(response);
                                        if(movieData.get("Response").equals("True")){

                                            setNotification();

                                            Intent intent = new Intent(getActivity().getBaseContext(), MovieDetailsActivity.class);
                                            intent.putExtra("movie", response);
                                            intent.putExtra("isEdit", false);

                                            getActivity().startActivity(intent);
                                        } else {
                                            Toast.makeText(getContext(), movieData.get("Error").toString(), Toast.LENGTH_SHORT).show();
                                        }

                                        progress.dismiss();

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                        progress.dismiss();
                                        Toast.makeText(getContext(), "Ops! Não foi possível procurar pelo seu filme :(", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            },
                            new Response.ErrorListener()
                            {
                                @Override
                                public void onErrorResponse(VolleyError error)
                                {
                                    progress.dismiss();
                                    Toast.makeText(getContext(), error.toString(), Toast.LENGTH_SHORT).show();
                                }
                            });
                    strRequest.setRetryPolicy(new DefaultRetryPolicy(
                            35000,
                            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                    queue.add(strRequest);
                } else {
                    progress.dismiss();
                    Toast.makeText(getContext(), "Please verify your internet connection and try again.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return rootView;
    }

    private void setNotification(){
        badgeCount++;
        ShortcutBadger.applyCount(getContext(), badgeCount);

        AHBottomNavigation bottomNavigation = (AHBottomNavigation) getActivity().findViewById(R.id.bottom_navigation);
        bottomNavigation.setNotification(badgeCount, 1);
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager manager =
                (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        boolean isAvailable = false;
        if (networkInfo != null && networkInfo.isConnected()) {
            isAvailable = true;
        }
        return isAvailable;
    }
}
