package com.digitalcreative.warungskripsi.Boundary;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;

import com.digitalcreative.warungskripsi.R;

import java.util.Calendar;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class GetDate_BookingHere extends Fragment {
    Button test;
    String jam, pembimbing;
    CalendarView calendarView;

    public GetDate_BookingHere() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_get_date__booking_here, container, false);
        //Init
        calendarView = view.findViewById(R.id.calendeView);
        test = view.findViewById(R.id.test);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day) {
                //Ambil data tanggal bulan hari
                String getDate = processDate(year, month+1, day);
                String getJam = processJam();

                //Kirim data
                sendTheData(getDate);
            }
        });

        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


              //sendTheData();
            }
        });
        return view;
    }

    private String processJam() {
        
        return null;
    }

    private String processDate(int year, int i, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, i, day);
        int day_of_week = calendar.get(Calendar.DAY_OF_WEEK);
        int months =  calendar.get(Calendar.MONTH);
        String day_of_month = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
        String years = String.valueOf(calendar.get(Calendar.YEAR));

        String hari = generateDays(day_of_week);
        String bulan = generateMonth(months);

        String date = hari +", " +day_of_month +" " +bulan +" " +years;

        return date;
    }

    private String generateDays(int day_of_week) {
        String hasil = null;
        switch (day_of_week){
            case 1 : hasil = "Minggu";break;
            case 2 : hasil = "Senin";break;
            case 3 : hasil = "Selasa";break;
            case 4 : hasil = "Rabu";break;
            case 5 : hasil = "Kamis";break;
            case 6 : hasil = "Jum'at";break;
            case 7 : hasil = "Sabtu";break;
            default:
                System.out.println("Hari Tidak Ditemukan");break;
        }
        return hasil;
    }

    private String generateMonth(int month) {
        String hasil = null;
        switch (month) {
            case 1:
                hasil = "Januari";
                break;
            case 2:
                hasil = "Februari";
                break;
            case 3:
                hasil = "Maret";
                break;
            case 4:
                hasil = "April";
                break;
            case 5:
                hasil = "Mei";
                break;
            case 6:
                hasil = "Juni";
                break;
            case 7:
                hasil = "Juli";
                break;
            case 8:
                hasil = "Agustus";
                break;
            case 9:
                hasil = "September";
                break;
            case 10:
                hasil = "Oktober";
                break;
            case 11:
                hasil = "November";
                break;
            case 12:
                hasil = "Desember";
                break;
            default:
                System.out.println("Hari Tidak Ditemukan");
                break;
        }
        return hasil;
    }

    private void sendTheData(String hari){
        Intent intent = new Intent(getContext(), GetDate_BookingHere.class);
        intent.putExtra("jam", jam);
        intent.putExtra("tanggal", hari);
        intent.putExtra("pembimbing", pembimbing);

        getTargetFragment().onActivityResult(getTargetRequestCode(), RESULT_OK, intent);
        getFragmentManager().popBackStack();
    }

}
