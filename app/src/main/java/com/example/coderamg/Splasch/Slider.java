package com.example.coderamg.Splasch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.coderamg.Activity.MainActivity;
import com.example.coderamg.R;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class Slider extends AppCompatActivity {

    private SliderAdapter onboardingAdapter;
    private LinearLayout linearLayout;
    private MaterialButton materialButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slider);

        linearLayout = findViewById(R.id.layoutonboarding);
        materialButton = findViewById(R.id.btnAction);
        setuponboardingItems();

        final ViewPager2 onboardingViewPager = findViewById(R.id.onboardingViewPager2);
        onboardingViewPager.setAdapter(onboardingAdapter);
        setOnboardingIndicators();
        setCurrentOnboardingIndicators(0);
        onboardingViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                setCurrentOnboardingIndicators(position);
            }
        });
        materialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onboardingViewPager.getCurrentItem() + 1 < onboardingAdapter.getItemCount()) {
                    onboardingViewPager.setCurrentItem(onboardingViewPager.getCurrentItem() + 1);
                } else {
                    startActivity(new Intent(getApplicationContext(),
                            MainActivity.class));
                    finish();

                }
            }
        });
    }

    private void setuponboardingItems() {
        List<SlideItem> slideItems = new ArrayList<>();

        SlideItem itemOne = new SlideItem();
        itemOne.setTitle("Welcome");
          itemOne.setDescription("Lorem Ipsum is simply dummy text of the Printhing and typesetting industry.");
        itemOne.setImage(R.raw.slider1);


        SlideItem itemTwo = new SlideItem();
        itemTwo.setTitle("World Ahmed Ghanam");
        itemTwo.setDescription("Lorem Ipsum is simply dummy text of the Printhing and typesetting industry.");
        itemTwo.setImage(R.raw.slider2);


        SlideItem itemThree = new SlideItem();
        itemThree.setTitle("aRE  YOU READE");
            itemThree.setDescription("Lorem Ipsum is simply dummy text of the Printhing and typesetting industry.");
        itemThree.setImage(R.raw.slider3);

        slideItems.add(itemOne);
        slideItems.add(itemTwo);
        slideItems.add(itemThree);

        onboardingAdapter = new SliderAdapter(slideItems);
    }// end setuponboardingItems()
    private void setOnboardingIndicators() {
        ImageView[] indicators = new ImageView[onboardingAdapter.getItemCount()];
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        );
        layoutParams.setMargins(8, 0, 8, 0);
        for (int i = 0; i < indicators.length; i++) {
            indicators[i] = new ImageView(getApplicationContext());
            indicators[i].setImageDrawable(ContextCompat.getDrawable(
                    getApplicationContext(),
                    R.drawable.onbararding_indicator_inactivaty
            ));
            indicators[i].setLayoutParams(layoutParams);
            linearLayout.addView(indicators[i]);
        }// end for
    }// end setOnboardingIndicators()

    @SuppressLint("SetTextI18n")
    private void setCurrentOnboardingIndicators(int index) {
        int childCount = linearLayout.getChildCount();
        for (int i = 0; i < childCount; i++) {
            ImageView imageView = (ImageView) linearLayout.getChildAt(i);
            if (i == index) {
                imageView.setImageDrawable(
                        ContextCompat.getDrawable(getApplicationContext(), R.drawable.onboarding_indicator_active)
                );
            } else {
                imageView.setImageDrawable(
                        ContextCompat.getDrawable(getApplicationContext(), R.drawable.onbararding_indicator_inactivaty)
                );
            }//end else
        }// end for
        if (index == onboardingAdapter.getItemCount() - 1) {
            materialButton.setText(R.string.start);
        } else {
            materialButton.setText(R.string.next);
        }// end else

    }// end setCurrentOnboardingIndicators()
}// end class