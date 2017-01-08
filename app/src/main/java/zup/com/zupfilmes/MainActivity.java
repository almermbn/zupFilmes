package zup.com.zupfilmes;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;

public class MainActivity extends AppCompatActivity implements AHBottomNavigation.OnTabSelectedListener {

    AHBottomNavigation bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        bottomNavigation = (AHBottomNavigation) findViewById(R.id.bottom_navigation);
        bottomNavigation.setOnTabSelectedListener(this);
        this.createNavItens();
    }

    private void createNavItens (){
        AHBottomNavigationItem findItem = new AHBottomNavigationItem("Procurar", R.drawable.busca);
        AHBottomNavigationItem listItem = new AHBottomNavigationItem("Meus Filmes", R.drawable.lista);


        bottomNavigation.addItem(findItem);
        bottomNavigation.addItem(listItem);

        bottomNavigation.setAccentColor(Color.parseColor("#a4c139"));
        bottomNavigation.setDefaultBackgroundColor(Color.parseColor("#000000"));

        bottomNavigation.setCurrentItem(0);

    }

    @Override
    public boolean onTabSelected(int position, boolean wasSelected) {
        if(position == 0){
            FindFragment findFragment = new FindFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.content_main, findFragment).commit();
        } else if (position == 1) {
            MovieListFragment listFragment = new MovieListFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.content_main, listFragment).commit();
        }
        return true;
    }
}
