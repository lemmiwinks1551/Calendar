package com.example.customcalendar;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CalendarViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    // ViewHolder - класс, содержащий ссылки на все View, которые находятся в одном элементе RecyclerView элементы
    // Через ViewHolder заполняется RecyclerVIew
    // TODO: 13.07.2022 Сделать через binding
    public final TextView dayOfMonth;
    private final CalendarAdapter.OnItemListener onItemListener;

    public CalendarViewHolder(@NonNull View itemView, CalendarAdapter.OnItemListener onItemListener) {
        super(itemView);
        dayOfMonth = itemView.findViewById(R.id.cellDayText);
        this.onItemListener = onItemListener;
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        // Вызывает onItemClick класса MainActivity
        onItemListener.onItemClick(getAdapterPosition(), (String) dayOfMonth.getText());
    }
}
