package com.abdo.hp.bank.helper;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;


public class HelperMethod {


    public static void replace(Fragment fragment, int id, FragmentTransaction fragmentTransaction) {
        FragmentTransaction transaction = fragmentTransaction;
            transaction.replace(id, fragment);
            transaction.addToBackStack(null);
            transaction.commit();

    }



}
