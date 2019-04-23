package com.abdo.hp.bank.ui.fragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abdo.hp.bank.R;
import com.abdo.hp.bank.adapter.CustomViewAdapter;
import com.abdo.hp.bank.ui.fragment.hom_fragment.ArticleFragment;
import com.abdo.hp.bank.ui.fragment.hom_fragment.RequestsFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * A simple {@link Fragment} subclass.
 */
public class ContentFragment extends Fragment {


    private static final String TAG_FRAGMENT = "Home";
    @BindView(R.id.Content_fragment_tab_layout_)
    TabLayout ContentFragmentTabLayout;
    @BindView(R.id.Content_fragment_View_Pager)
    ViewPager ContentFragmentViewPager;
    Unbinder unbinder;

    public ContentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_content, container, false);
        unbinder = ButterKnife.bind(this, view);



        ContentFragmentViewPager.setAdapter(new CustomViewAdapter(getActivity().getSupportFragmentManager()));

        ContentFragmentTabLayout.setupWithViewPager(ContentFragmentViewPager);

        return view;

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
