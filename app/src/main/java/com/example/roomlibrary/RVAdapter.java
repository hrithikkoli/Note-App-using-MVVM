package com.example.roomlibrary;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roomlibrary.databinding.SingleRowBinding;

public class RVAdapter extends ListAdapter<Expense,RVAdapter.viewHolder> {
    public RVAdapter(){
        super(CAllBACK);
    }
    private static final DiffUtil.ItemCallback<Expense> CAllBACK = new DiffUtil.ItemCallback<Expense>() {
        @Override
        public boolean areItemsTheSame(@NonNull Expense oldItem, @NonNull Expense newItem) {
            return oldItem.getId()==newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Expense oldItem, @NonNull Expense newItem) {
            return oldItem.getTitle().equals(newItem.getTitle()) &&
                    oldItem.getPrice().equals(newItem.getPrice());
        }
    };
    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_row,parent,false);
        return new viewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        Expense expense = getItem(position);
        holder.binding.txtTitle.setText(expense.getTitle());
        holder.binding.txtPrice.setText(expense.getPrice());

    }
    public Expense getExpense(int position){
        return  getItem(position);
    }

    public class viewHolder extends RecyclerView.ViewHolder{
        SingleRowBinding binding;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            binding = SingleRowBinding.bind(itemView);
        }
    }
}
