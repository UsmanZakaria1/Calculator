package values.simplecalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.simplecalculator.R;
import com.google.android.material.button.MaterialButton;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView solution, result;
    MaterialButton add, mul, div, sub, eq;
    MaterialButton open_bracket, close_bracket, btc_C;
    MaterialButton one, two, three, four, five, six, seven, eight, nine, zero;
    MaterialButton dot, AC;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        result = findViewById(R.id.re_1);
        solution = findViewById(R.id.solution);
        assignID(btc_C, R.id.button_C);
        assignID(open_bracket, R.id.button_open);
        assignID(close_bracket, R.id.button_close);
        assignID(div, R.id.button_div);
        assignID(mul, R.id.button_mul);
        assignID(add, R.id.button_add);
        assignID(sub, R.id.button_sub);
        assignID(one, R.id.button_1);
        assignID(two, R.id.button_2);
        assignID(three, R.id.button_3);
        assignID(four, R.id.button_4);
        assignID(five, R.id.button_5);
        assignID(six, R.id.button_6);
        assignID(seven, R.id.button_7);
        assignID(eight, R.id.button_8);
        assignID(nine, R.id.button_9);
        assignID(zero, R.id.button_0);
        assignID(dot, R.id.button_dot);
        assignID(eq, R.id.button_eq);
        assignID(AC, R.id.button_AC);


    }

    public void assignID(MaterialButton btn, int id) {
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String button_text = button.getText().toString();
        String datacalulate = solution.getText().toString();
        if (button_text.equals("AC")) {
            solution.setText("");
            result.setText("0");
            return;

        }
        if (button_text.equals("=")) {
            solution.setText(result.getText());
            return;
        }
        if (button_text.equals("C")) {
            datacalulate = datacalulate.substring(0, datacalulate.length() - 1);
        } else {
            datacalulate = datacalulate + button_text;
        }


        solution.setText(datacalulate);
        String finalResult = getResult(datacalulate);



        if(!finalResult.equals("Err")){
            result.setText(finalResult);
        }

    }

    String getResult(String data){
        try{
            Context context  = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalResult =  context.evaluateString(scriptable,data,"Javascript",1,null).toString();
            if(finalResult.endsWith(".0")){
                finalResult = finalResult.replace(".0","");
            }
            return finalResult;
        }catch (Exception e){
            return "Error";
        }
    }

}