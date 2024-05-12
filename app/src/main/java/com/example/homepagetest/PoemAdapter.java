package com.example.homepagetest;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PoemAdapter extends RecyclerView.Adapter<PoemAdapter.PoemViewHolder> {
    private List<Poem> poems;
    private Context context;

    public PoemAdapter(List<Poem> poems, Context context) {
        this.poems = poems;
        this.context = context;
    }

    @NonNull
    @Override
    public PoemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_poem, parent, false);
        return new PoemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PoemViewHolder holder, int position) {
        final Poem poem = poems.get(position);
        holder.titleTextView.setText(poem.getTitle());
        holder.authorTextView.setText(poem.getAuthor());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PoemDetails.class);
                intent.putExtra("selected_poem", poem);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return poems.size();
    }

    public static class PoemViewHolder extends RecyclerView.ViewHolder {
        public TextView titleTextView;
        public TextView authorTextView;

        public PoemViewHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.title_text_view);
            authorTextView = itemView.findViewById(R.id.author_text_view);
        }
    }
}
