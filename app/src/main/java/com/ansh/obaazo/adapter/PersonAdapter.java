package com.ansh.obaazo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.ansh.obaazo.R;
import com.ansh.obaazo.model.PersonInfo;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.PersonViewHolder> {
    private Context mContext;

    private ArrayList<PersonInfo> mList;

    public PersonAdapter(Context mContext, ArrayList<PersonInfo> mList) {
        this.mContext = mContext;
        if (mList != null && mList.size() != 0) {
            this.mList = mList;
        } else {
            this.mList = new ArrayList<>();
            addRoom();
        }
    }

    @NonNull
    @Override
    public PersonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PersonViewHolder(LayoutInflater.from(mContext).inflate(R.layout.row_person_details, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final PersonViewHolder holder, int position) {
        ((TextView) holder.itemView.findViewById(R.id.tv_leval_room_count)).setText("Room " + (holder.getAdapterPosition() + 1));
        holder.tvRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mList.remove(holder.getAdapterPosition());
                notifyDataSetChanged();
                // notifyItemRemoved(holder.getAdapterPosition());
            }
        });
        holder.tvRemove.setVisibility((holder.getAdapterPosition() == 0 ? View.GONE : View.VISIBLE));

        holder.itemView.findViewById(R.id.cv_card_main).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateUi(holder.getAdapterPosition());
            }
        });

        holder.itemView.findViewById(R.id.iv_a_plus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mList.get(holder.getAdapterPosition()).getNoOfAdult() < 3) {
                    if (validatePersonCount(holder.getAdapterPosition())) {
                        int noOfAdult = mList.get(holder.getAdapterPosition()).getNoOfAdult();
                        mList.get(holder.getAdapterPosition()).setNoOfAdult(noOfAdult + 1);
                        notifyItemChanged(holder.getAdapterPosition());
                    }
                } else {
                    Toast.makeText(mContext, "Max 3 Person Allow", Toast.LENGTH_SHORT).show();
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
        holder.itemView
                .findViewById(R.id.ll_details).setVisibility((mList.get(holder.getAdapterPosition()).getEdit()) ? View.VISIBLE : View.GONE);
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
            holder.rb1.setChecked(mChild.get(holder.getAdapterPosition()).equals(1));
            holder.rb2.setChecked(mChild.get(holder.getAdapterPosition()).equals(2));
            holder.rb3.setChecked(mChild.get(holder.getAdapterPosition()).equals(3));
            holder.rb4.setChecked(mChild.get(holder.getAdapterPosition()).equals(4));
            holder.rb5.setChecked(mChild.get(holder.getAdapterPosition()).equals(5));
            holder.rb6.setChecked(mChild.get(holder.getAdapterPosition()).equals(6));
            holder.rb7.setChecked(mChild.get(holder.getAdapterPosition()).equals(7));
            holder.rb8.setChecked(mChild.get(holder.getAdapterPosition()).equals(8));
            holder.rb9.setChecked(mChild.get(holder.getAdapterPosition()).equals(9));
            holder.rb10.setChecked(mChild.get(holder.getAdapterPosition()).equals(10));
            holder.rb11.setChecked(mChild.get(holder.getAdapterPosition()).equals(11));
            holder.rb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    mChild.set(holder.getAdapterPosition(), 1);
                }
            });
            holder.rb2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    mChild.set(holder.getAdapterPosition(), 2);
                }
            });
            holder.rb3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    mChild.set(holder.getAdapterPosition(), 3);

                }
            });
            holder.rb4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    mChild.set(holder.getAdapterPosition(), 4);

                }
            });
            holder.rb5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    mChild.set(holder.getAdapterPosition(), 5);

                }
            });
            holder.rb6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    mChild.set(holder.getAdapterPosition(), 6);

                }
            });
            holder.rb7.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    mChild.set(holder.getAdapterPosition(), 7);

                }
            });
            holder.rb8.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    mChild.set(holder.getAdapterPosition(), 8);

                }
            });
            holder.rb9.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    mChild.set(holder.getAdapterPosition(), 9);

                }
            });
            holder.rb10.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    mChild.set(holder.getAdapterPosition(), 10);

                }
            });
            holder.rb11.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    mChild.set(holder.getAdapterPosition(), 11);

                }
            });
        }

        @Override
        public int getItemCount() {
            return (mChild != null) ? mChild.size() : 0;
        }

        public class ChildViewHolder extends RecyclerView.ViewHolder {
            private TextView tvChild;
            private TextView tvAge;
            private ImageView ivRemove;
            private RadioButton rb1, rb2, rb3, rb4, rb5, rb6, rb7, rb8, rb9, rb10, rb11;

            public ChildViewHolder(View itemView) {
                super(itemView);
                tvChild = itemView.findViewById(R.id.tv_c_count);
                rb1 = itemView.findViewById(R.id.rb_1);
                rb2 = itemView.findViewById(R.id.rb_2);
                rb3 = itemView.findViewById(R.id.rb_3);
                rb4 = itemView.findViewById(R.id.rb_4);
                rb5 = itemView.findViewById(R.id.rb_5);
                rb6 = itemView.findViewById(R.id.rb_6);
                rb7 = itemView.findViewById(R.id.rb_7);
                rb8 = itemView.findViewById(R.id.rb_8);
                rb9 = itemView.findViewById(R.id.rb_9);
                rb10 = itemView.findViewById(R.id.rb_10);
                rb11 = itemView.findViewById(R.id.rb_11);
                // tvAge = itemView.findViewById(R.id.tv_age);
                //  ivRemove = itemView.findViewById(R.id.iv_remove);
            }
        }
    }

    public ArrayList<PersonInfo> getDetails() {
        return mList != null ? mList : new ArrayList<PersonInfo>();
    }
}
