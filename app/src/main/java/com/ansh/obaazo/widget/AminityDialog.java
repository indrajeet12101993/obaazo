package com.ansh.obaazo.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ansh.obaazo.R;

public class AminityDialog extends Dialog {
    private String title;
    private ClickListener listener;
    private Context mContext;
    private String[] mAmnity;


    public AminityDialog(@NonNull Context context) {
        super(context, R.style.My_DialogC);
        this.mContext = context;

    }

    public AminityDialog(@NonNull Context context, String[] mAmnity, ClickListener listener) {
        super(context, R.style.My_DialogC);
        this.mContext = context;
        this.listener = listener;
        this.mAmnity = mAmnity;

    }


    public AminityDialog(@NonNull Context context, String title, String[] mAmnity) {
        super(context, R.style.My_DialogC);
        this.mContext = context;
        this.title = title;
        this.mAmnity = mAmnity;


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_aminity);


        findViewById(R.id.btn_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        TextView tvTitle = findViewById(R.id.tv_title);
        tvTitle.setText(title);
        RecyclerView rvAminity = findViewById(R.id.rv_amenties);
        rvAminity.setLayoutManager(new LinearLayoutManager(mContext));

        AminityAdapter adapter = new AminityAdapter();
        rvAminity.setAdapter(adapter);

    }


    public interface ClickListener {
        void onClick(String date, String time);
    }

    private class AminityAdapter extends RecyclerView.Adapter<AminityAdapter.ViewHolder> {
        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.row_aminity, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.tvAminity.setText(mAmnity[holder.getAdapterPosition()]);

        }

        @Override
        public int getItemCount() {
            return (mAmnity != null ? mAmnity.length : 0);
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            private TextView tvAminity;

            public ViewHolder(View itemView) {
                super(itemView);
                tvAminity = itemView.findViewById(R.id.tv_aminity);
            }
        }
    }
}
