package com.hch.hooney.companyscheduler.Recycler.Work;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.hch.hooney.companyscheduler.R;

/**
 * Created by hooney on 2018. 2. 2..
 */

public class WorkHolder extends RecyclerView.ViewHolder {

    public TextView w_title;
    public Button w_BTN;

    public WorkHolder(View itemView) {
        super(itemView);

        w_title = (TextView) itemView.findViewById(R.id.item_work_title);
        w_BTN = (Button) itemView.findViewById(R.id.item_work_BTN);
    }
}
