package io.github.mpao.baking.ui;

import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import io.github.mpao.baking.R;
import io.github.mpao.baking.databinding.FragmentContentBinding;
import io.github.mpao.baking.di.App;
import io.github.mpao.baking.entities.Recipe;
import io.github.mpao.baking.entities.Step;

public class ContentFragment extends Fragment {

    private FragmentContentBinding binding;
    private Step step;
    private SimpleExoPlayer player;

    public static ContentFragment newInstance(Recipe recipe, int position){

        ContentFragment fragment = new ContentFragment();
        Bundle args = new Bundle();
        args.putParcelable(App.RECIPE_VALUE, recipe);
        args.putInt(App.STEP_INDEX, position);
        fragment.setArguments(args);
        return fragment;

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding  = DataBindingUtil.inflate(inflater, R.layout.fragment_content, container, false);
        Recipe recipe = (Recipe)getArguments().getParcelable(App.RECIPE_VALUE);
        step = savedInstanceState == null ?
                recipe.getSteps().get(getArguments().getInt(App.STEP_INDEX)):
                savedInstanceState.getParcelable(App.RECIPE_VALUE);
        binding.setStep(step);

        //todo: navigation bug, onCreateView is executed twice
        if(player == null) {
            TrackSelector trackSelector = new DefaultTrackSelector();
            LoadControl loadControl = new DefaultLoadControl();
            player = ExoPlayerFactory.newSimpleInstance(getContext(), trackSelector, loadControl);
            binding.player.setPlayer(player);

            MediaSource mediaSource = new ExtractorMediaSource(
                    Uri.parse(step.getVideoURL()),
                    new DefaultDataSourceFactory(getActivity(), getActivity().getString(R.string.app_name)),
                    new DefaultExtractorsFactory(),
                    null,
                    null
            );

            if(savedInstanceState != null)
                player.seekTo(savedInstanceState.getLong("player"));
            player.prepare(mediaSource);
            player.setPlayWhenReady(false);
        }

        return binding.getRoot();

    }

    /*
     * Save Recipe information
     */
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(App.RECIPE_VALUE, step);
        outState.putLong("player", player.getCurrentPosition());
    }

    /*
     * Release the player
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        player.stop();
        player.release();
        player = null;
    }

}
