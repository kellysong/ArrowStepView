package com.sjl.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.sjl.arrowstepview.R;
import com.sjl.stepview.widget.ArrowStepView;

import java.util.Arrays;
import java.util.List;

public class ArrowStepViewActivity extends AppCompatActivity {

    private ArrowStepView arrowStepView, arrowStepView2, arrowStepView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.arrow_step_view_activity);
        arrowStepView = (ArrowStepView) findViewById(R.id.step_view);
        arrowStepView2 = (ArrowStepView) findViewById(R.id.step_view2);
        arrowStepView3 = (ArrowStepView) findViewById(R.id.step_view3);
        initSteps(arrowStepView, arrowStepView2, arrowStepView3);

    }

    private void initSteps(ArrowStepView... arrowStepViews) {
        List<String> steps = Arrays.asList(new String[]{"选择金额", "支付", "贴卡充值"});
        for (ArrowStepView arrowStepView : arrowStepViews) {
            arrowStepView.setSteps(steps);
        }
    }


    public void onClick(View view) {
        if (view.getId() == R.id.next) {
            int nextStep = arrowStepView.getCurrentStep() + 1;
            if (nextStep > arrowStepView.getStepCount()) {
                nextStep = 1;
            }
            arrowStepView.selectedCurrentStep(nextStep);
            arrowStepView2.selectedCurrentStep(nextStep);
            arrowStepView3.selectedCurrentStep(nextStep);
        }
    }


}
