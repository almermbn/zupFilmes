package zup.com.zupfilmes;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.jorgecastillo.FillableLoader;
import com.github.jorgecastillo.listener.OnStateChangeListener;


public class SplashFragment extends Fragment implements OnStateChangeListener, ResettableView {

    FillableLoader fillableLoader;
    private View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.content_splash_screen, container, false);
        fillableLoader = (FillableLoader) rootView.findViewById((R.id.fillableLoader));
        return rootView;
    }

    @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupFillableLoader();
    }

    private void setupFillableLoader() {
        fillableLoader.setSvgPath(Paths.ZUP_PATH);
        this.reset();
    }

    @Override public void reset() {
        fillableLoader.reset();

        fillableLoader.postDelayed(new Runnable() {
            @Override public void run() {
                fillableLoader.start();
            }
        }, 250);
    }

    @Override
    public void onStateChange(int state) {
    }
}