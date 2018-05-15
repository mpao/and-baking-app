package io.github.mpao.baking.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
    protected int position;
    private SimpleExoPlayer player;
    private SharedPreferences save;
    public static final String VIDEO_POS = "video_position";

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
        save = App.save;
        // fragment created in portrait mode
        Intent intent = activity.getIntent();
        if( intent != null ){
            Recipe recipe = intent.getParcelableExtra(App.RECIPE_VALUE);
            int position  = intent.getIntExtra(App.STEP_INDEX, App.INVALID);
            update(recipe, position);
        }

        // set up nav button only for portrait mode
        try {
            binding.next.setOnClickListener(view -> navButtonClick(1));
            binding.prev.setOnClickListener(view -> navButtonClick(-1));
        }catch (NullPointerException e){
            // layout w720dp without nav buttons
            // do nothing
        }

        return binding.getRoot();

    }

    /*
     * Move between the step of the recipe w/ the nav button
     */
    private void navButtonClick(int shift){

        App.save.edit().clear().apply();
        update(recipe, position + shift); //todo see RecipeActivity.onElementSelected

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
    public void onDestroy() {

        super.onDestroy();
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
        // IMPORTANT NOTE:
        // why dont i use the bundles for saving the video position ?
        // well, because the application use both bundle and intent for
        // saving the fragment state depending on the device orientation
        // I DONT want that any other classes ( ie RecipeActivity ) know about
        // the player and is state, and the only way is to use a shared pref for
        // the intent case. At this point, I used this shared pref for all the cases,
        // since there is this opportunity, and cost 3 lines of code
        save.edit().putLong(VIDEO_POS, player.getCurrentPosition()).apply();

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

            // set the button visibility
            try{
                int nextState = position == recipe.getSteps().size()-1 ? View.INVISIBLE : View.VISIBLE;
                int prevState = position == 0 ? View.INVISIBLE : View.VISIBLE;
                binding.next.setVisibility(nextState);
                binding.prev.setVisibility(prevState);
            }catch (NullPointerException e){
                // layout w720dp without nav buttons
                // do nothing
            }

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
        long pos = save.getLong(VIDEO_POS, 0);
        player.seekTo(pos);
        player.prepare(mediaSource);
        player.setPlayWhenReady(true);

    }

    /*
     * Release and destroy the video
     */
    private void releaseVideoPlayer(){

        if(player != null) {
            player.stop();
            player.release();
        }
        player = null;

    }

}
