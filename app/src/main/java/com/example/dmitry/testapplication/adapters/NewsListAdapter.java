package com.example.dmitry.testapplication.adapters;

import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dmitry.testapplication.R;
import com.example.dmitry.testapplication.app.ApplicationBase;
import com.example.dmitry.testapplication.models.ModelNewsTitle;
import com.example.dmitry.testapplication.ui.activities.MainActivity;
import com.example.dmitry.testapplication.utils.FontHelper;
import com.example.dmitry.testapplication.utils.Utils;

import java.util.ArrayList;

import javax.inject.Inject;

public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.ViewHolder> {

    static final String NO_TITLE = "NO TITLE";
    Listener listener;
    ArrayList<ModelNewsTitle> items = new ArrayList<>();

    public NewsListAdapter(Listener listener, ArrayList<ModelNewsTitle> items) {
        this.listener = listener;
        this.items = items;
    }

    @Override
    public NewsListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_news, parent, false));
    }

    @Override
    public void onBindViewHolder(NewsListAdapter.ViewHolder holder, final int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClickNews(items.get(position).id);
            }
        });
        String title = Html.fromHtml(items.get(position).text).toString().trim();
        if (title.length() == 0) title = NO_TITLE;
        holder.tvTitle.setText(title);
        holder.tvIcon.setText(title.substring(0, 1));
        holder.tvDate.setText(Utils.getDateString(items.get(position).publicationDate));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final TextView tvTitle, tvIcon, tvDate;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setClickable(true);
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            tvIcon = (TextView) itemView.findViewById(R.id.tvIcon);
            tvDate = (TextView) itemView.findViewById(R.id.tvDate);
        }
    }

    public interface Listener {
        void onClickNews(String id);
    }
}
