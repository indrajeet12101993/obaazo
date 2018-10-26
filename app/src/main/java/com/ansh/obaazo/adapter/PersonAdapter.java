package com.ansh.obaazo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ansh.obaazo.R;
import com.ansh.obaazo.model.PersonInfo;

import java.util.ArrayList;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.PersonViewHolder> {
    private Context mContext;

    private ArrayList<PersonInfo> mList;

    public PersonAdapter(Context mContext, ArrayList<PersonInfo> mList) {
        this.mContext = mContext;
        this.mList = mList;
        addRoom();
    }

    @NonNull
    @Override
    public PersonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PersonViewHolder(LayoutInflater.from(mContext).inflate(R.layout.row_person_details, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final PersonViewHolder holder, int position) {
        holder.tvRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mList.remove(holder.getAdapterPosition());
                notifyItemRemoved(holder.getAdapterPosition());
            }
        });

        holder.itemView.findViewById(R.id.cv_card_main).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateUi(holder.getAdapterPosition());
            }
        });

        holder.itemView.findViewById(R.id.iv_a_plus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validatePersonCount(holder.getAdapterPosition())) {
                    int noOfAdult = mList.get(holder.getAdapterPosition()).getNoOfAdult();
                    mList.get(holder.getAdapterPosition()).setNoOfAdult(noOfAdult + 1);
                    notifyItemChanged(holder.getAdapterPosition());
                }
            }
        });
        holder.itemView.findViewById(R.id.iv_a_minus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mList.get(holder.getAdapterPosition()).getNoOfAdult() > 1) {
                    int noOfAdult = mList.get(holder.getAdapterPosition()).getNoOfAdult();
                    mList.get(holder.getAdapterPosition()).setNoOfAdult(noOfAdult - 1);
                    notifyItemChanged(holder.getAdapterPosition());
                }

            }
        });
        holder.itemView.findViewById(R.id.iv_c_plus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validatePersonCount(holder.getAdapterPosition())) {
                    mList.get(holder.getAdapterPosition()).getChild().add(5);
                    notifyItemChanged(holder.getAdapterPosition());
                }

            }
        });
        holder.itemView.findViewById(R.id.iv_c_minus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mList.get(holder.getAdapterPosition()).getChild().size() != 0) {
                    mList.get(holder.getAdapterPosition()).getChild().remove(holder.getAdapterPosition());
                    notifyItemChanged(holder.getAdapterPosition());
                }
            }
        });


        holder.tvAdultCount.setText("" + mList.get(holder.getAdapterPosition()).getNoOfAdult());
        holder.tvChildCount.setText("" + mList.get(holder.getAdapterPosition()).getChild().size());
        holder.itemView.findViewById(R.id.ll_details).setVisibility((mList.get(holder.getAdapterPosition()).getEdit()) ? View.VISIBLE : View.GONE);
        holder.itemView.findViewById(R.id.tv_details).setVisibility(!(mList.get(holder.getAdapterPosition()).getEdit()) ? View.VISIBLE : View.GONE);

        holder.rvChild.setLayoutManager(new LinearLayoutManager(mContext));
        ChildAdapter childAdapter = new ChildAdapter(mContext, mList.get(holder.getAdapterPosition()).getChild());
        holder.rvChild.setAdapter(childAdapter);


    }

    @Override
    public int getItemCount() {
        return (mList != null) ? mList.size() : 0;
    }

    public class PersonViewHolder extends RecyclerView.ViewHolder {
        private TextView tvChildCount;
        private TextView tvAdultCount;
        private TextView tvRemove;
        private RecyclerView rvChild;

        public PersonViewHolder(View itemView) {
            super(itemView);
            tvRemove = itemView.findViewById(R.id.tv_remove);
            tvAdultCount = itemView.findViewById(R.id.tv_adult_count);
            tvChildCount = itemView.findViewById(R.id.tv_child_count);
            rvChild = itemView.findViewById(R.id.rv_child_details);
        }
    }

    private void validateUi(int position) {
        for (int i = 0; i < mList.size(); i++) {
            mList.get(i).setEdit(i == position);
        }
        notifyDataSetChanged();
    }


    public void addRoom() {
        PersonInfo personInfo = new PersonInfo();
        personInfo.setEdit(true);
        mList.add(personInfo);
        validateUi(mList.size() - 1);
    }

    public boolean validatePersonCount(int position) {
        return mList.get(position).getChild().size() + mList.get(position).getNoOfAdult() != 4;
    }

    public class ChildAdapter extends RecyclerView.Adapter<ChildAdapter.ChildViewHolder> {
        private Context mContext;
        private ArrayList<Integer> mChild;

        public ChildAdapter(Context mContext, ArrayList<Integer> child) {
            this.mContext = mContext;
            this.mChild = child;
        }

        @NonNull
        @Override
        public ChildAdapter.ChildViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ChildViewHolder(LayoutInflater.from(mContext).inflate(R.layout.row_child_details, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull final ChildAdapter.ChildViewHolder holder, int position) {
       /* holder.ivRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                info.getChild().remove(holder.getAdapterPosition());
                notifyDataSetChanged();
            }
        });

        holder.tvAge.setText(info.getChild().get(holder.getAdapterPosition()));
        holder.tvChild.setText(holder.getAdapterPosition() + 1 + " Child's");*/

        }

        @Override
        public int getItemCount() {
            return (mChild != null) ? mChild.size() : 0;
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
