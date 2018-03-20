package io.github.mpao.baking.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
    private Activity activity;
    private Recipe recipe;
    private int position;
    private SimpleExoPlayer player;

    /*
     * check the @Nullable value returned by getActivity()
     * getActivity() in Fragment got the @Nullable annotation with support libraries 27.0.0
     * as reported here https://goo.gl/hQ7fPQ
     */
    @Override
    public void onAttach(Context context) {

        super.onAttach(context);
        if (context instanceof Activity) {
            this.activity = (Activity) context;
        }

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding  = DataBindingUtil.inflate(inflater, R.layout.fragment_content, container, false);
        // fragment created in portrait mode
        Intent intent = activity.getIntent();
        if( intent != null ){
            Recipe recipe = intent.getParcelableExtra(App.RECIPE_VALUE);
            int position  = intent.getIntExtra(App.STEP_INDEX, App.INVALID);
            update(recipe, position);
        }

        return binding.getRoot();

    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {

        super.onViewStateRestored(savedInstanceState);
        if(savedInstanceState != null){
            update(
                    savedInstanceState.getParcelable(App.RECIPE_VALUE),
                    savedInstanceState.getInt(App.STEP_INDEX)
            );
        }

    }

    /*
     * Release the player
     */
    @Override
    public void onStop() {

        super.onStop();
        releaseVideoPlayer();

    }

    @Override
    public void onDetach() {

        super.onDetach();
        this.activity = null;

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {

        super.onSaveInstanceState(outState);
        outState.putParcelable(App.RECIPE_VALUE, recipe);
        outState.putInt(App.STEP_INDEX, position);

    }

    /*
     * Set the value for the Fragment model, and show it in the view
     */
    public void update(Recipe recipe, int position){

        if(position != App.INVALID && recipe != null) {
            this.position = position;
            this.recipe = recipe;
            Step step = recipe.getSteps().get(position);
            setUpVideoPlayer(step);

            binding.setStep(step);
        }

    }

    /*
     * Set up and start the video
     */
    private void setUpVideoPlayer(Step step){

        if(player != null) {
            releaseVideoPlayer();
        }
        TrackSelector trackSelector = new DefaultTrackSelector();
        LoadControl loadControl = new DefaultLoadControl();
        player = ExoPlayerFactory.newSimpleInstance(getContext(), trackSelector, loadControl);
        binding.player.setPlayer(player);
        MediaSource mediaSource = new ExtractorMediaSource(
                Uri.parse(step.getVideoURL()),
                new DefaultDataSourceFactory(activity, activity.getString(R.string.app_name)),
                new DefaultExtractorsFactory(),
                null,
                null
        );
        player.prepare(mediaSource);
        player.setPlayWhenReady(true); //todo network error

    }

    /*
     * Release and destroy the video
     */
    private void releaseVideoPlayer(){

        player.stop();
        player.release();
        player = null;

    }

}
