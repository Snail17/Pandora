package com.pandora.modular.purchase.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.pandora.R;
import com.pandora.core.base.BaseFragment;
import com.pandora.modular.purchase.bean.PurchaseBean;
import com.pandora.modular.purchase.bean.PurchaseVO;
import com.pandora.modular.purchase.model.PurchaseModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class PurchaseFragment extends BaseFragment {


    @BindView(R.id.btn_card_month)
    Button monthCard;
    @BindView(R.id.btn_card_quarterly)
    Button quarterlyCard;
    @BindView(R.id.btn_card_year)
    Button yearCard;
    @BindView(R.id.btn_card_forever)
    Button introduceCard;

    public PurchaseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View ret = inflater.inflate(R.layout.fragment_purchase, container, false);
        ButterKnife.bind(this, ret);
        return ret;
    }


    @OnClick({R.id.btn_card_month, R.id.btn_card_quarterly, R.id.btn_card_year, R.id.btn_card_forever})
    public void btnClick(View view) {
        PurchaseVO purchaseVO = new PurchaseVO("CARD", "", "", "");
        new PurchaseModel().getData(purchaseVO, new OnPurchaseFinishListener() {
            @Override
            public void success(PurchaseBean purchaseBean) {
                if (purchaseBean != null) {
                    Toast.makeText(PurchaseFragment.this.getContext(), purchaseBean.getIsOk(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void error() {

            }
        });
    }

    public interface OnPurchaseFinishListener {
        void success(PurchaseBean purchaseBean);

        void error();
    }

}
