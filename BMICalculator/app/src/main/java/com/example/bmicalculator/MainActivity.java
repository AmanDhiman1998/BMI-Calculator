package com.example.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
boolean standard = true,metric=false,both=false;
double weightinkg,heightinmeter;
double BMI = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void selectstandard(View view) {
        standard=true;metric=false;both=false;
        changetostandardvalues();
        changedtheselection((Button)findViewById(R.id.bothbutton),(Button)findViewById(R.id.metricbutton),(Button)findViewById(R.id.standardbutton) );
    }

    private void changetostandardvalues() {
        TextView T = (TextView)findViewById(R.id.heightvalue);
        T.setText("");
        T.setHint("In Inches");
        TextView T2 = (TextView)findViewById(R.id.weightvalue);
        T2.setText("");
        T2.setHint("In Pounds");
    }

    public void selectmetric(View view) {
        metric=true;standard=false;both=false;
        changetometricvalues();
        changedtheselection((Button)findViewById(R.id.bothbutton),(Button)findViewById(R.id.standardbutton),(Button)findViewById(R.id.metricbutton) );
    }

    private void changetometricvalues() {
        TextView T = (TextView)findViewById(R.id.heightvalue);
        T.setText("");
        T.setHint("In Centimeters");
        TextView T2 = (TextView)findViewById(R.id.weightvalue);
        T2.setText("");
        T2.setHint("In Kgs");
    }
    public void selectboth(View view) {
        both=true;metric=false;standard=false;
        changetobothvalues();
        changedtheselection((Button)findViewById(R.id.standardbutton),(Button)findViewById(R.id.metricbutton),(Button)findViewById(R.id.bothbutton) );
    }

    private void changetobothvalues() {
        TextView T = (TextView)findViewById(R.id.heightvalue);
        T.setText("");
        T.setHint("In Inches");
        TextView T2 = (TextView)findViewById(R.id.weightvalue);
        T2.setText("");
        T2.setHint("In Kgs");
    }

    public void computebmi(View view) {
        TextView T = (TextView)findViewById(R.id.heightvalue);
        TextView T2 = (TextView)findViewById(R.id.weightvalue);
        if(T==null||T2==null)
            return;
        double height = Double.parseDouble(T.getText().toString());
        double weight = Double.parseDouble(T2.getText().toString());
        if(metric==true){
            weightinkg = weight;
            heightinmeter = height/100;
        }
        else if(standard==true){
            weightinkg = (float) ((float)weight*0.453592);
            heightinmeter = (float) ((float)height*0.0254);
        }
        else{
            weightinkg = weight;
            heightinmeter = (float) ((float)height*0.0254);
        }

        BMI = (double)weightinkg/(heightinmeter*heightinmeter);
        BMI =Math.round(BMI*10.0)/10.0;
        result(BMI);
//        TextView T3 = (TextView)findViewById(R.id.calculatebmibutton);
//        T3.setText(BMI+"");

    }

    protected void result(double BMI){
        ImageView Image = (ImageView)findViewById(R.id.resultimage);
        if(BMI<18.5){
            Image.setBackgroundResource(R.drawable.underweightpic);
            show("Underweight");
        }
        else if(BMI>=18.5&&BMI<=24.9){
            Image.setBackgroundResource(R.drawable.normalpic);
            show("Normal Weight");
        }
        else if(BMI>=25&&BMI<=29.9){
            Image.setBackgroundResource(R.drawable.overweightpic);
            show("Overweight");
        }
        else{
            Image.setBackgroundResource(R.drawable.obesepic);
            show("Obesity");
        }
    }

    private void show(String showbmi) {
                TextView T3 = (TextView)findViewById(R.id.calculatebmibutton);
        T3.setText(showbmi +"  (BMI : "+BMI+")");
    }

    public void changedtheselection(Button selected , Button selected2, Button selection){
    selected2.setTextColor(Color.parseColor("#000000"));
    selected.setTextColor(Color.parseColor("#000000"));
    selection.setTextColor(Color.parseColor("#FFFFFF"));
    selected.setBackgroundColor(Color.parseColor("#CBCDD3"));
    selected2.setBackgroundColor(Color.parseColor("#CBCDD3"));
    selection.setBackgroundColor(Color.parseColor("#4C6DE3"));

}



}