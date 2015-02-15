package com.example.borja.simplecalculator;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class CalculatorActivity extends ActionBarActivity implements ICalculatorView{

    TextView screen;
    CalculatorViewModel viewModel;

    static final String BUNDLE_VIEWMODEL_STATE = "Calculator state";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        screen = (TextView) findViewById(R.id.calculatorScreen);
        this.screen.setText("0");
        viewModel = new CalculatorViewModel(new CalculatorModel(), this);

        if(savedInstanceState != null) {
            viewModel.setState(savedInstanceState.getString(BUNDLE_VIEWMODEL_STATE));
            showScreen(viewModel.screen);

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_calculator, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        String viewModelState = viewModel.getState();
        outState.putString(BUNDLE_VIEWMODEL_STATE, viewModelState);
    }

    @Override
    public void showScreen(String value) {
        this.screen.setText(value);
    }

    public void numberPressed(View view){
        Button button = (Button)view;
        char key = button.getText().charAt(0);
        viewModel.onNumericKeyPressed(key);
        showScreen(viewModel.screen);
    }

    public void operatorPressed(View view){
        Button button = (Button)view;
        char key = button.getText().charAt(0);
        viewModel.onOperatorKeyPressed(key);
    }

    public void pointPressed(View view){
        viewModel.onDecimalPointPressed();
        showScreen(viewModel.screen);
    }

    public void equalPressed(View view){
        viewModel.onEqualKeyPressed();
        showScreen(viewModel.screen);
    }
}
