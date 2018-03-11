package io.github.mpao.baking.ui;

import io.github.mpao.baking.entities.Recipe;

/**
 * Interface for fragments communication
 * https://developer.android.com/training/basics/fragments/communicating.html
 * Why two params ? Detail activity get the recipe object from the main activity,
 * and pass it to two different fragment: Step and Detail. I want also the
 * information of the ingredients and this is a way to get it
 */
public interface FragmentConnector {

    void onElementSelected(Recipe recipe, int position);

}