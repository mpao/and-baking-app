package io.github.mpao.baking.di;

import javax.inject.Singleton;
import dagger.Component;
import io.github.mpao.baking.ui.ListFragment;

/**
 * Dagger graph
 */
@Singleton
@Component(modules = {
        ContextModule.class,
        RetrofitModule.class
})

public interface Graph {

    void inject(ListFragment listFragment);

}
