package com.pandora.modular.purchase.fragment;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;
import com.pandora.R;
import com.pandora.core.base.BaseFragment;
import com.pandora.core.globle.PandoraContants;
import com.pandora.core.utils.LogUtils;
import com.pandora.core.base.OnFinishListener;
import com.pandora.modular.main.activity.MainActivity;
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

    private PurchaseBean mPurchaseBean;

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
            public void onSuccess(String resultJson) {
                LogUtils.e("purchase" + resultJson);
                if (!TextUtils.isEmpty(resultJson)) {
                    Gson gson = new Gson();
                    mPurchaseBean = gson.fromJson(resultJson, PurchaseBean.class);
//                    Toast.makeText(PurchaseFragment.this.getContext(), mPurchaseBean.getIsOk(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError() {

            }
        });
    }

    @OnClick(R.id.tv_contact_customer)
    public void contactClick(View view) {
        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(PandoraContants.qqUrl)));
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(PurchaseFragment.this.getContext(), "请检查是否安装QQ", Toast.LENGTH_SHORT).show();
        }
    }


    public interface OnPurchaseFinishListener extends OnFinishListener {
    }

}
