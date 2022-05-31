package com.example.rosheta.data.source.local;

import com.example.rosheta.R;
import com.example.rosheta.data.models.OnboardingItem;

import java.util.ArrayList;

public class OnboardingData {
    public static ArrayList<OnboardingItem> Items() {
        ArrayList<OnboardingItem> onboardingItemList = new ArrayList<>();
        OnboardingItem itemPlayOnline1 = new OnboardingItem();
        itemPlayOnline1.setTitle("Search for the nearest doctors");
        itemPlayOnline1.setDescription("Our app can send you to ,\nthe nearest doctor you need to \ntake an appointment with him.");
        itemPlayOnline1.setImage(R.drawable.onboarding_1);

        OnboardingItem itemPlayOnline2 = new OnboardingItem();
        itemPlayOnline2.setTitle("Search for the \npharmacies");
        itemPlayOnline2.setDescription("Our app can send you everywhere,\nfor the nearest pharmacy to buy your medicines.");
        itemPlayOnline2.setImage(R.drawable.onboarding_2);

        OnboardingItem itemPlayOnline3 = new OnboardingItem();
        itemPlayOnline3.setTitle("Remember your medicines");
        itemPlayOnline3.setDescription("Our app can notify you, \nwith your medicines time to save your life!");
        itemPlayOnline3.setImage(R.drawable.onboarding_3);

        onboardingItemList.add(itemPlayOnline1);
        onboardingItemList.add(itemPlayOnline2);
        onboardingItemList.add(itemPlayOnline3);

        return onboardingItemList;

    }


}
