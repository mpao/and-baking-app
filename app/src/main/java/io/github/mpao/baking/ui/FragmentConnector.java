package io.github.mpao.baking.ui;

import io.github.mpao.baking.entities.Step;

/**
 * Interface for fragments communication
 * https://developer.android.com/training/basics/fragments/communicating.html
 */
public interface FragmentConnector {

    void onElementSelected(Step step);

}