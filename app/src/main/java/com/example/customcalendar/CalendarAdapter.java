package com.example.customcalendar;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

class CalendarAdapter extends RecyclerView.Adapter<CalendarViewHolder> {
    // Адаптер работает с ViewHolder`ом
    private final ArrayList<String> daysOfMonth;
    private final OnItemListener onItemListener;

    public CalendarAdapter(ArrayList<String> daysOfMonth, OnItemListener onItemListener) {
        this.daysOfMonth = daysOfMonth;
        this.onItemListener = onItemListener;
    }

    @NonNull
    @Override
    public CalendarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Возвращает объект ViewHolder, который будет хранить данные по одному объекту
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.calendar_cell, parent, false);

        // Выравнивает элементы по высоте
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = (int) (parent.getHeight() * 0.166666666);
        return new CalendarViewHolder(view, onItemListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CalendarViewHolder holder, int position) {
        // выполняет привязку объекта ViewHolder к объекту по определенной позиции.
        LocalDate nowDate = LocalDate.now();
        if (Objects.equals(daysOfMonth.get(position), String.valueOf(nowDate.getDayOfMonth()))) {
            holder.dayOfMonth.setBackgroundColor(Color.RED);
        }
        holder.dayOfMonth.setText(daysOfMonth.get(position));
        // TODO: 13.07.2022 Добавить логику выбора из БД и раскраску дней
    }

    @Override
    public int getItemCount() {
        // возвращает количество объектов в списке
        return daysOfMonth.size();
    }

    public interface OnItemListener {
        // Подключаем интерфейс onItemListener
        void onItemClick(int position, String dayText);
    }
}
