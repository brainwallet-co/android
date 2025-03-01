package com.brainwallet.presenter.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.brainwallet.R;
import com.brainwallet.presenter.entities.Partner;
import com.brainwallet.presenter.fragments.BuyPartnersAdapter;
import com.brainwallet.presenter.fragments.FragmentBuy;
import com.brainwallet.tools.manager.AnalyticsManager;
import com.brainwallet.tools.util.BRConstants;

import java.util.ArrayList;
import java.util.List;

public class BuyTabFragment extends Fragment {

    private RecyclerView mRecyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_buy_tab, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRecyclerView = view.findViewById(R.id.recycler_view);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        DividerItemDecoration itemDecor = new DividerItemDecoration(mRecyclerView.getContext(), DividerItemDecoration.VERTICAL);
        itemDecor.setDrawable(ContextCompat.getDrawable(mRecyclerView.getContext(), R.drawable.divider_white_shape));
        mRecyclerView.addItemDecoration(itemDecor);

        mRecyclerView.setAdapter(new com.brainwallet.presenter.fragments.BuyPartnersAdapter(getContext(), getPartners()));
        AnalyticsManager.logCustomEvent(BRConstants._20191105_DTBT);
    }

    private List<Partner> getPartners() {
        List<Partner> partners = new ArrayList<>();
        partners.add(new Partner(R.drawable.ic_moonpay_logo, R.string.BuyCenter_moonpay_Title, R.string.BuyCenter_moonpay_FinancialDetails, FragmentBuy.Partner.MOONPAY));
        return partners;
    }
}
