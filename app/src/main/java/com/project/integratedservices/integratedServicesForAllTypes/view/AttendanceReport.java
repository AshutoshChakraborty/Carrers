package com.project.integratedservices.integratedServicesForAllTypes.view;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.kizitonwose.calendarview.CalendarView;
import com.kizitonwose.calendarview.model.CalendarDay;
import com.kizitonwose.calendarview.model.DayOwner;
import com.kizitonwose.calendarview.model.InDateStyle;
import com.kizitonwose.calendarview.model.OutDateStyle;
import com.kizitonwose.calendarview.model.ScrollMode;
import com.kizitonwose.calendarview.ui.DayBinder;
import com.project.integratedservices.R;
import com.project.integratedservices.integratedServicesForAllTypes.viewModel.IntegratedServicesViewModel;
import com.project.integratedservices.repository.integratedServicesForAllTypes.request.AttendanceDetailsRequestPojo;
import com.project.integratedservices.repository.integratedServicesForAllTypes.response.AttendanceDetailsResponsePojo;
import com.project.supportClasses.CustomSpinner;
import com.project.supportClasses.DayViewContainer;
import com.project.supportClasses.Misc;
import com.project.supportClasses.SharedPref;

import org.threeten.bp.LocalDate;
import org.threeten.bp.YearMonth;
import org.threeten.bp.format.DateTimeFormatter;
import org.threeten.bp.temporal.WeekFields;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static com.project.supportClasses.SharedPref.AGENT_ID;

public class AttendanceReport extends AppCompatActivity implements SpinnerAdapterAttendance.OnItemSelectListener {


    private AppCompatImageView ivBack;
    private AppCompatTextView subHeader;
    private AppCompatTextView tvMonth, tvYearHeader, tvMonthHeader, tvNoOfDaysPresent;
    private CustomSpinner spAttendance;
    private SpinnerAdapterAttendance spinnerAdapterAttendance;
    private List<String> monthList = new ArrayList<>();
    private CalendarView calendarView;
    private IntegratedServicesViewModel integratedServicesViewModel;
    private int year;
    private List<AttendanceDetailsResponsePojo> attendanceDetails = new ArrayList<>();
    private List<LocalDate> localDates = new ArrayList<>();
    private LinearLayoutCompat llBottom;
    private String agentCode;
    private int selectedMonthNo = -1;

//    private LocalDate startDate = LocalDate.of(2019, 11, 20);
//    private LocalDate endDate = LocalDate.of(2019, 11, 25);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_report);

        init();


        if(getIntent().hasExtra("Agent_Code"))
        {
            agentCode = getIntent().getStringExtra("Agent_Code");
        }

        ivBack.setOnClickListener(v -> onBackPressed());
        tvMonth.setOnClickListener(v -> spAttendance.performClick());


        YearMonth yearMonth = YearMonth.now();
        tvYearHeader.setText(String.valueOf(yearMonth.getYear()));
        tvMonthHeader.setText(String.valueOf(yearMonth.getMonth()));

        year = yearMonth.getYear();


        tvMonth.setText(yearMonth.getMonth().toString().toUpperCase());
        if (Misc.isNetworkAvailable(this)) {
            AttendanceDetailsRequestPojo requestPojo = new AttendanceDetailsRequestPojo();
            requestPojo.setMonth(String.valueOf(getMonthNo(yearMonth.getMonth().toString())));
            requestPojo.setYear(String.valueOf(year));
            requestPojo.setAgentCode(agentCode);
//            requestPojo.setAgentCode(SharedPref.getInstance(this).getData(AGENT_ID));
            integratedServicesViewModel.getAttendanceRequest(requestPojo);


        } else {
            Toast.makeText(this, getString(R.string.internet_unavailable), Toast.LENGTH_SHORT).show();
        }
        //////////////////////////////////////////////////

        integratedServicesViewModel.getAttendanceDetailsLiveData().observe(this, attendanceDetailsResponsePojos -> {
            if (attendanceDetailsResponsePojos.size() > 0) {
                if (attendanceDetailsResponsePojos.get(0).getStatus().equals("Successful")) {
                    if (llBottom.getVisibility() == View.GONE)
                        llBottom.setVisibility(View.VISIBLE);

                    attendanceDetails.clear();
                    attendanceDetails.addAll(attendanceDetailsResponsePojos);

                    tvNoOfDaysPresent.setText(getResources().getString(R.string.no_of_days_present) + " " + attendanceDetails.size());

                    localDates.clear();

                    for (AttendanceDetailsResponsePojo pojo : attendanceDetails) {
                        localDates.add(converToLocalDate(pojo.getAttendanceDate()));
                    }

                    //testing calender view
                    calendarView.setDayBinder(new DayBinder<DayViewContainer>() {

                        @Override
                        public DayViewContainer create(View view) {
                            return new DayViewContainer(view);
                        }

                        @Override
                        public void bind(DayViewContainer dayViewContainer, CalendarDay calendarDay) {
                            AppCompatTextView textView =
                                    dayViewContainer.getView().findViewById(R.id.calendarDayText);
                            textView.setText(Integer.toString(calendarDay.getDate().getDayOfMonth()));

                            if (calendarDay.getOwner() == DayOwner.THIS_MONTH) {
                                textView.setTextColor(Color.BLACK);
                            } else {
                                textView.setTextColor(Color.GRAY);
                            }

                            for (LocalDate date : localDates) {
                                if (calendarDay.getDate().isEqual(date)) {
                                    textView.setTextColor(Color.WHITE);
                                    textView.setBackgroundDrawable(getDrawable(R.drawable.middle));
                                }
                            }


//                            if(calendarDay.getDate().isAfter(startDate) && calendarDay.getDate().isBefore(endDate))
//                            {
//                                textView.setTextColor(Color.WHITE);
//                                textView.setBackgroundDrawable(getDrawable(R.drawable.middle));
//                            }
//                            if(calendarDay.getDate().equals(startDate)) {
//                                textView.setBackgroundDrawable(getDrawable(R.drawable.start));
//                                textView.setTextColor(Color.WHITE);
//                            }
//                            if(calendarDay.getDate().equals(endDate)) {
//                                textView.setBackgroundDrawable(getDrawable(R.drawable.end));
//                                textView.setTextColor(Color.WHITE);
//                            }
                        }
                    });
                    YearMonth yearMonth1 = YearMonth.of(yearMonth.getYear(),selectedMonthNo);
                    calendarView.setup(yearMonth1.minusMonths(0), yearMonth1.plusMonths(0), WeekFields.of(Locale.getDefault()).getFirstDayOfWeek());
                    calendarView.setOrientation(RecyclerView.HORIZONTAL);
                    calendarView.setScrollMode(ScrollMode.PAGED);
                    calendarView.setInDateStyle(InDateStyle.ALL_MONTHS);
                    calendarView.setOutDateStyle(OutDateStyle.END_OF_ROW);
                    calendarView.setMaxRowCount(6);
                    calendarView.setHasBoundaries(true);
                } else {
                    llBottom.setVisibility(View.VISIBLE);
//                    Toast.makeText(this, attendanceDetailsResponsePojos.get(0).getStatus(), Toast.LENGTH_SHORT).show();

                    calendarView.setDayBinder(new DayBinder<DayViewContainer>() {

                        @Override
                        public DayViewContainer create(View view) {
                            return new DayViewContainer(view);
                        }

                        @Override
                        public void bind(DayViewContainer dayViewContainer, CalendarDay calendarDay) {
                            AppCompatTextView textView =
                                    dayViewContainer.getView().findViewById(R.id.calendarDayText);
                            textView.setText(Integer.toString(calendarDay.getDate().getDayOfMonth()));

                            if (calendarDay.getOwner() == DayOwner.THIS_MONTH) {
                                textView.setTextColor(Color.BLACK);
                            } else {
                                textView.setTextColor(Color.GRAY);
                            }

                        }
                    });
                    YearMonth yearMonth1 =  YearMonth.of(yearMonth.getYear(),selectedMonthNo);
                    calendarView.setup(yearMonth1.minusMonths(0), yearMonth1.plusMonths(0), WeekFields.of(Locale.getDefault()).getFirstDayOfWeek());
                    calendarView.setOrientation(RecyclerView.HORIZONTAL);
                    calendarView.setScrollMode(ScrollMode.PAGED);
                    calendarView.setInDateStyle(InDateStyle.ALL_MONTHS);
                    calendarView.setOutDateStyle(OutDateStyle.END_OF_ROW);
                    calendarView.setMaxRowCount(6);
                    calendarView.setHasBoundaries(true);
                }
            } else {
                Toast.makeText(this, getResources().getString(R.string.no_records_found), Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void init() {
        integratedServicesViewModel = ViewModelProviders.of(this).get(IntegratedServicesViewModel.class);
        ivBack = findViewById(R.id.iv_drawer_menu);
        subHeader = findViewById(R.id.textView2);
        spAttendance = findViewById(R.id.spAttendance);
        tvMonth = findViewById(R.id.tvMonth);
        calendarView = findViewById(R.id.calendarView);
        tvYearHeader = findViewById(R.id.tvYearHeader);
        tvMonthHeader = findViewById(R.id.tvMonthHeader);
        tvNoOfDaysPresent = findViewById(R.id.tvNoOfDaysPresent);
        llBottom = findViewById(R.id.llBottom);


        subHeader.setVisibility(View.VISIBLE);
        subHeader.setText(getResources().getString(R.string.attendance_report));
        ivBack.setImageResource(R.drawable.ic_left_arrow);

        monthList.add("JANUARY");
        monthList.add("FEBRUARY");
        monthList.add("MARCH");
        monthList.add("APRIL");
        monthList.add("MAY");
        monthList.add("JUNE");
        monthList.add("JULY");
        monthList.add("AUGUST");
        monthList.add("SEPTEMBER");
        monthList.add("OCTOBER");
        monthList.add("NOVEMBER");
        monthList.add("DECEMBER");

        spinnerAdapterAttendance = new SpinnerAdapterAttendance(this, monthList, this);
        spAttendance.setAdapter(spinnerAdapterAttendance);
        spAttendance.setDropDownWidth(900);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        subHeader.setVisibility(View.GONE);
    }


    @Override
    public <E> void selectedMonth(E selectedItem) {
        tvMonth.setText(selectedItem.toString());
        tvMonthHeader.setText(selectedItem.toString());
        spAttendance.onDetachedFromWindow();


        ////fetch attendance record for the month
        if (Misc.isNetworkAvailable(this)) {
            AttendanceDetailsRequestPojo requestPojo = new AttendanceDetailsRequestPojo();
            requestPojo.setMonth(String.valueOf(getMonthNo(selectedItem.toString())));
            requestPojo.setYear(String.valueOf(year));
            requestPojo.setAgentCode(agentCode);
//            requestPojo.setAgentCode(SharedPref.getInstance(this).getData(AGENT_ID));
            integratedServicesViewModel.getAttendanceRequest(requestPojo);


        } else {
            Toast.makeText(this, getString(R.string.internet_unavailable), Toast.LENGTH_SHORT).show();
        }
    }

    private int getMonthNo(String monthName) {
        Date date;
        try {
            date = new SimpleDateFormat("MMMM").parse(monthName);

            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            System.out.println("@@@ Month - " + cal.get(Calendar.MONTH) + 1);
            selectedMonthNo = cal.get(Calendar.MONTH) + 1;
            return (cal.get(Calendar.MONTH) + 1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return -1;
    }

    private LocalDate converToLocalDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        //convert String to LocalDate
        LocalDate localDate = LocalDate.parse(date, formatter);

        return localDate;
    }
}
