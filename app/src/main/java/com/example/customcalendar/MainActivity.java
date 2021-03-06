package com.example.customcalendar;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements CalendarAdapter.OnItemListener {
    private TextView monthYearText;
    private RecyclerView calendarRecyclerView;
    private LocalDate selectedDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String LOG = "Calendar";
        Log.e(LOG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Вызываем метод, который инициализирует View
        initWidgets();

        // Получить сегодняшню дату yyyy-MM-dd
        selectedDate = LocalDate.now();


        // Вызываем метод, который устанавливает название месяца, создает и устанавливает адаптер и менеджер
        setMonthView();
    }

    private void initWidgets() {
        // Инициировать view
        calendarRecyclerView = findViewById(R.id.calendarRecyclerView);
        monthYearText = findViewById(R.id.monthYearTV);
    }

    private void setMonthView() {
        // Устанавливаем название месяца в TextView
        monthYearText.setText(monthYearFromDate());
        ArrayList<String> daysInMonth = daysInMonthArray(selectedDate);

        // Создаем CalendarAdapter, передаем количество дней в месяце и listener
        CalendarAdapter calendarAdapter = new CalendarAdapter(daysInMonth, this);

        // Создаем layoutManager и устанавливает способ отображения элементов в нем
        // GridLayoutManager упорядочивает элементы в виде грида со столлбцами и строками (7 элементов в ряд)
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 7);

        // Устанавливаем в RecyclerView менеджера и адаптер
        calendarRecyclerView.setLayoutManager(layoutManager);
        calendarRecyclerView.setAdapter(calendarAdapter);
    }

    private ArrayList<String> daysInMonthArray(LocalDate date) {
        // Считаеи и возвращаем массив дней в месяце
        ArrayList<String> daysInMonthArray = new ArrayList<>();

        // Получаем месяц
        YearMonth yearMonth = YearMonth.from(date);

        // Получаем длину месяца
        int daysInMonth = yearMonth.lengthOfMonth();

        // Получаем первый день текущего месяца
        LocalDate firstOfMonth = selectedDate.withDayOfMonth(1);

        // Получаем день недели первого дня месяца
        int dayOfWeek = firstOfMonth.getDayOfWeek().getValue() - 1;

        // Заполняем массив для отображения в RecyclerView
        // Учитываем пустые дни (дни прошлого месяца
        // TODO: 12.07.2022 Добавить дни прошлого и будущего месяцев
        for (int i = 1; i <= 42; i++) {
            if (i <= dayOfWeek || i > daysInMonth + dayOfWeek) {
                daysInMonthArray.add("");
            } else {
                daysInMonthArray.add(String.valueOf(i - dayOfWeek));
            }
        }
        return daysInMonthArray;
    }

    private String monthYearFromDate() {
        // Метод форматирует название месяца и год для отображения во View
        Date date = Date.from(selectedDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        String month = new SimpleDateFormat("LLLL", Locale.getDefault()).format(date);
        String year = String.valueOf(selectedDate.getYear());
        return month + " " + year;
    }

    public void previousMonthAction(View view) {
        // Обработка нажатия на кнопку Предыдущий месяц
        // Вычитаем один месяц из текущего
        selectedDate = selectedDate.minusMonths(1);
        CalendarAdapter.month--;
        setMonthView();
    }

    public void nextMonthAction(View view) {
        // Обработка нажатия на кнопку Следующий месяц
        // Добавляем один месяц к текущему
        selectedDate = selectedDate.plusMonths(1);
        CalendarAdapter.month++;
        setMonthView();
    }

    @Override
    public void onItemClick(int position, String dayText) {
        // Обработка клика по дню, если ячейка не пустая
        if (!dayText.equals("")) {
            // TODO: 12.07.2022 Реализовать логику перехода на экран выбранной даты
            String message = "Selected Date " + dayText;
            Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        }
    }
}