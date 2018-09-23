package com.ansh.obaazo.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ansh.obaazo.R;
import com.ansh.obaazo.model.BookingInfo;
import com.ansh.obaazo.utils.AppConstant;
import com.ansh.obaazo.utils.PreferencesUtils;
import com.google.gson.Gson;

public class ActivitySelect extends BaseActivity {
    private RecyclerView rvChildDetails;
    private BookingInfo info = new BookingInfo();
    private ChildAdapter childAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_select;
    }

    @Override
    protected void initView() {

        rvChildDetails = findViewById(R.id.rv_child_details);
        rvChildDetails.setLayoutManager(new LinearLayoutManager(this));
        childAdapter = new ChildAdapter();
        rvChildDetails.setAdapter(childAdapter);
        rvChildDetails.setNestedScrollingEnabled(false);

    }

    @Override
    protected void initListener() {
        findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        findViewById(R.id.iv_r_minus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (info.getRoomCount() > 1) {
                    info.roomCount--;
                }
                ((TextView) findViewById(R.id.tv_room_count)).setText("" + info.roomCount);
            }
        });

        findViewById(R.id.iv_r_plus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (info.roomCount < 4) {
                    info.roomCount++;
                }
                ((TextView) findViewById(R.id.tv_room_count)).setText("" + info.roomCount);
            }
        });

        findViewById(R.id.iv_a_minus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (info.adultCount > 1) {
                    info.adultCount--;
                }
                ((TextView) findViewById(R.id.tv_adult_count)).setText("" + info.adultCount);
            }
        });

        findViewById(R.id.iv_a_plus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (info.adultCount < 4) {
                    info.adultCount++;
                }
                ((TextView) findViewById(R.id.tv_adult_count)).setText("" + info.adultCount);
            }
        });

        findViewById(R.id.iv_c_minus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (info.getChild().size() > 0) {
                    info.getChild().remove(info.getChild().size() - 1);
                }
                ((TextView) findViewById(R.id.tv_child_count)).setText("" + info.getChild().size());
            }
        });

        findViewById(R.id.iv_c_plus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (info.getChild().size() < 4) {
                    info.getChild().add("12 years");
                    childAdapter.notifyDataSetChanged();
                }
                ((TextView) findViewById(R.id.tv_child_count)).setText("" + info.getChild().size());
            }
        });


        findViewById(R.id.btn_done).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PreferencesUtils.putString(AppConstant.BOOKING_DETAILS, new Gson().toJson(info));
                onBackPressed();
            }
        });


    }

    @Override
    protected void bindDataWithUi() {

    }

    public void notifyChild() {

    }

    private class ChildAdapter extends RecyclerView.Adapter<ChildAdapter.ChildViewHolder> {
        @NonNull
        @Override
        public ChildViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ChildViewHolder(LayoutInflater.from(ActivitySelect.this).inflate(R.layout.row_child_details, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull final ChildViewHolder holder, int position) {
            holder.ivRemove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    info.getChild().remove(holder.getAdapterPosition());
                    notifyDataSetChanged();
                }
            });

            holder.tvAge.setText(info.getChild().get(holder.getAdapterPosition()));
            holder.tvChild.setText(holder.getAdapterPosition() + 1 + " Child's");

        }

        @Override
        public int getItemCount() {
            return info.getChild().size();
        }

        public class ChildViewHolder extends RecyclerView.ViewHolder {
            private TextView tvChild;
            private TextView tvAge;
            private ImageView ivRemove;

            public ChildViewHolder(View itemView) {
                super(itemView);
                tvChild = itemView.findViewById(R.id.tv_c_count);
                tvAge = itemView.findViewById(R.id.tv_age);
                ivRemove = itemView.findViewById(R.id.iv_remove);
            }
        }
    }
}
