package kr.or.dgit.it.map_study;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView list = findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager lm = new LinearLayoutManager(this);
        list.setLayoutManager(lm);

        String[] titles = getResources().getStringArray(R.array.titles);
        String[] clses = getResources().getStringArray(R.array.classes);
        List<Item> items = new ArrayList<>();
        for(int i = 0 ; i<titles.length;i++){
            items.add(new Item(titles[i],clses[i]));
        }

        ListAdapter adapter = new ListAdapter(items);

        list.setAdapter(adapter);
    }
    class Item{
        String title;
        String className;

        public Item(String title, String className) {
            this.title = title;
            this.className = className;
        }
    }

    private class ListAdapter extends RecyclerView.Adapter{

        private class ViewHolder extends RecyclerView.ViewHolder{
            private TextView titleTv;

            public ViewHolder(View itemView) {
                super(itemView);
                titleTv = itemView.findViewById(android.R.id.text1);
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int position = getAdapterPosition();
                        Item item = items.get(position);
                        Intent intent = new Intent();
                        intent.setClassName(getPackageName(), getPackageName()+"."+item.className);
                        intent.putExtra("title",item.title);
                        startActivity(intent);
                    }
                });
            }
        }

        List<Item> items;

        public ListAdapter(List<Item> items) {
            this.items = items;
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1,parent,false);

            return new ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            ViewHolder viewHolder = (ViewHolder) holder;
            viewHolder.titleTv.setText(items.get(position).title);
        }

        @Override
        public int getItemCount() {
            return items.size();
        }
    }

}
