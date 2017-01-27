package zup.com.views.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import zup.com.fragments.AboutActorsFragment;
import zup.com.fragments.AboutMovieFragment;


public class MovieDetailsAdapter extends FragmentPagerAdapter {
    private static int NUM_ITEMS = 2;
    private static String jsonMovie;

    public MovieDetailsAdapter(FragmentManager fragmentManager, String movieDetailsJson) {
        super(fragmentManager);
        jsonMovie = movieDetailsJson;
    }

    @Override
    public int getCount() {
        return NUM_ITEMS;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return AboutMovieFragment.newInstance(jsonMovie);
            case 1:
                return AboutActorsFragment.newInstance(jsonMovie);
            default:
                return null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if(position == 0){
            return "Movie Details";
        } else {
            return "Movie Cast";
        }
    }

}