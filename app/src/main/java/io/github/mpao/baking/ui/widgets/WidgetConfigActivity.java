package io.github.mpao.baking.ui.widgets;

import android.appwidget.AppWidgetManager;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import io.github.mpao.baking.R;
import io.github.mpao.baking.viewmodels.MainViewModel;

/*
 * https://developer.android.com/guide/topics/appwidgets/index.html#Configuring
 * If you would like the user to configure settings when he or she adds a new App Widget,
 * you can create an App Widget configuration Activity. This Activity will be automatically launched by the
 * App Widget host and allows the user to configure available settings for the App Widget at create-time
 */
public class WidgetConfigActivity extends AppCompatActivity {

    private int widgetId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.config_widget);

        // get the widget ID
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            widgetId = extras.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
        }

        // get data from viewmodel
        MainViewModel viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        if( savedInstanceState == null) {
            viewModel.init();
        }
        this.observeData(viewModel);

    }

    /*
     * Choose recipe for the widget
     */
    private void observeData(MainViewModel viewModel){

        if(viewModel.getData()==null){
            viewModel.init();
        }
        viewModel.getData().observe(this, list ->{

            if( list != null) {
                ListView listView = findViewById(R.id.list);
                listView.setAdapter(new ArrayAdapter<>(
                        this,
                        android.R.layout.simple_list_item_1,
                        android.R.id.text1,
                        list)
                );
                listView.setOnItemClickListener(
                        (adapterView, view, i, l) -> {
                            // save the value of the recipe ID
                            if( widgetId != AppWidgetManager.INVALID_APPWIDGET_ID) {
                                SharedPreferences widgets = getSharedPreferences(getString(R.string.widgets), MODE_PRIVATE);
                                widgets.edit().putInt(String.valueOf(widgetId), list.get(i).getId()).apply();
                                // launch the widget
                                BakingWidgetProvider provider = new BakingWidgetProvider();
                                provider.setUp(this, widgetId);
                                Intent resultValue = new Intent();
                                resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetId);
                                setResult(RESULT_OK, resultValue);
                            }
                            finish();
                        }
                );
            }

        });

    }

}
