package com.hch.hooney.companyscheduler.Recycler.Work;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.hch.hooney.companyscheduler.DAO.DAO;
import com.hch.hooney.companyscheduler.MainActivity;
import com.hch.hooney.companyscheduler.R;

import java.util.ArrayList;

/**
 * Created by hooney on 2018. 2. 2..
 */

public class WorkAdapter extends RecyclerView.Adapter {
    private Context context;
    private ArrayList<Boolean> list;

    // Allows to remember the last item shown on screen
    private int lastPosition = -1;

    public WorkAdapter(Context context, ArrayList<Boolean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_work,parent,false);
        WorkHolder holder = new WorkHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        WorkHolder hold = (WorkHolder) holder;
        Work item = DAO.wList.get(position);

        hold.w_title.setText(item.getSpotName());

        hold.w_BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //GPS 받기
                //아이템에 저장하기
                //DAO.wList.get(position).setGpsX();
                //DAO.wList.get(position).setGpxY();

                list.set(position, true);
                v.setBackgroundColor(context.getResources().getColor(R.color.MainColor));

            }
        });

        setAnimation(hold.itemView, position);
    }

    @Override
    public int getItemCount() {
        return DAO.wList.size();
    }

    private void setAnimation(View viewToAnimate, int position) {
        // 새로 보여지는 뷰라면 애니메이션을 해줍니다
        if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.fade_in);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }
}
