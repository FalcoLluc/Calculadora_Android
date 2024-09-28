package com.example.entrega_calculadora;

import static java.lang.String.format;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CalculadoraActivity extends AppCompatActivity {
    private String nums;
    private String operacionPendiente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_calculadora);

        this.nums="";
        this.operacionPendiente="";

        //Nou
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void onClickOperator(View v){
        // Convertir el View a Button
        Button buttonClicked = (Button) v;

        if(!this.operacionPendiente.isEmpty()) {
            // Obtener el texto del botón
            getResult();
        }
        nums=nums+" ";
        this.operacionPendiente= buttonClicked.getText().toString();
    }

    public void onClickNum(View v){
        // Convertir el View a Button
        Button buttonClicked = (Button) v;

        // Obtener el texto del botón
        String buttonText = buttonClicked.getText().toString();
        nums=nums+buttonText;
        updateTextView();
    }

    public void onClickResult(View v){
        getResult();
    }

    public void onClickClc(View v){
        this.nums="";
        this.operacionPendiente="";
        updateTextView();
    }

    public void onClickTrigono(View v){
        this.nums="";
        updateTextView();
        // Convertir el View a Button
        Button buttonClicked = (Button) v;

        // Obtener el texto del botón
        this.operacionPendiente= buttonClicked.getText().toString();
    }

    public double getResult(){
        double result=0;
        try{
            if(!this.operacionPendiente.isEmpty()){
                String[] numsArray=this.nums.split(" ");
                double num1=Double.parseDouble(numsArray[0]);
                double num2=Double.parseDouble(numsArray[numsArray.length-1]);
                Switch switchRadGrados = findViewById(R.id.degreesSwitch);
                boolean isDegrees = switchRadGrados.isChecked();
                switch(this.operacionPendiente){
                    default:
                        break;
                    case "+":
                        result=num1+num2;
                        break;
                    case "-":
                        result=num1-num2;
                        break;
                    case "x":
                        result=num1*num2;
                        break;
                    case "/":
                        result=num1/num2;
                        break;
                    case "Cos":
                        if(isDegrees){
                            result=Math.cos(num1*Math.PI/180);
                        }
                        else {
                            result = Math.cos(num1);
                        }
                        break;
                    case "Sin":
                        if(isDegrees){
                            result=Math.sin(num1*Math.PI/180);
                        }
                        else {
                            result = Math.sin(num1);
                        }
                        break;
                    case "Tan":
                        if(isDegrees){
                            result=Math.tan(num1*Math.PI/180);
                        }
                        else {
                            result = Math.tan(num1);
                        }
                        break;
                }
                this.nums= format("%.2f", result);
                this.operacionPendiente="";
                updateTextView();

            }
        }catch(Exception e){
            //
        }
        return result;
    }

    public void updateTextView(){
        TextView miTextView = findViewById(R.id.textViewResults);

        String[] numsArray=this.nums.split(" ");
        miTextView.setText(numsArray[numsArray.length-1]);
    }
}