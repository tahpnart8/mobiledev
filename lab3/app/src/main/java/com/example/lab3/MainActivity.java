package com.example.lab3;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    EditText edtN;
    Button btnRandom;
    TextView tvTime, tvFails;
    Button[] btnCells = new Button[9];
    TextView[] xCells = new TextView[9];

    int[] numbers = new int[9];
    boolean[] done = new boolean[9];
    int fails = 0;
    int seconds = 0;
    boolean timerRunning = false;

    Handler handler = new Handler(Looper.getMainLooper());
    Runnable timerRunnable;
    Runnable[] hideXRunnables = new Runnable[9];
    Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtN = findViewById(R.id.edtN);
        btnRandom = findViewById(R.id.btnRandom);
        tvTime = findViewById(R.id.tvTime);
        tvFails = findViewById(R.id.tvFails);

        int[] btnIds = {R.id.btnCell0, R.id.btnCell1, R.id.btnCell2, R.id.btnCell3, R.id.btnCell4,
                R.id.btnCell5, R.id.btnCell6, R.id.btnCell7, R.id.btnCell8};
        int[] xIds = {R.id.xCell0, R.id.xCell1, R.id.xCell2, R.id.xCell3, R.id.xCell4,
                R.id.xCell5, R.id.xCell6, R.id.xCell7, R.id.xCell8};
        for (int i = 0; i < 9; i++) {
            btnCells[i] = findViewById(btnIds[i]);
            xCells[i] = findViewById(xIds[i]);
            final int idx = i;
            hideXRunnables[i] = () -> xCells[idx].setVisibility(View.GONE);
        }

        // dem giay
        timerRunnable = new Runnable() {
            @Override
            public void run() {
                seconds++;
                tvTime.setText("Time: " + seconds + "s");
                handler.postDelayed(timerRunnable, 1000);
            }
        };

        for (int i = 0; i < 9; i++) {
            final int index = i;
            btnCells[i].setOnClickListener(v -> onCellClick(index));
        }

        btnRandom.setOnClickListener(v -> startNewGame());
    }

    private void startNewGame() {
        int n;
        try {
            n = Integer.parseInt(edtN.getText().toString());
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Vui long nhap so nguyen", Toast.LENGTH_SHORT).show();
            return;
        }
        if (n < 8) {
            Toast.makeText(this, "n phai >= 8", Toast.LENGTH_SHORT).show();
            return;
        }

        if (timerRunning) {
            handler.removeCallbacks(timerRunnable);
        }

        // sinh 9 so phan biet trong [0, n]
        Set<Integer> set = new HashSet<>();
        while (set.size() < 9) {
            set.add(random.nextInt(n + 1));
        }
        int i = 0;
        for (int num : set) {
            numbers[i] = num;
            i++;
        }

        for (int j = 0; j < 9; j++) {
            done[j] = false;
            btnCells[j].setText(String.valueOf(numbers[j]));
            btnCells[j].setEnabled(true);
            btnCells[j].setAlpha(1f);
            xCells[j].setVisibility(View.GONE);
            handler.removeCallbacks(hideXRunnables[j]); // huy callback an-X mo coi cua van truoc
        }

        fails = 0;
        seconds = 0;
        tvFails.setText("Fails: 0");
        tvTime.setText("Time: 0s");

        handler.postDelayed(timerRunnable, 1000);
        timerRunning = true;
    }

    private void onCellClick(int index) {
        int currentMin = Integer.MAX_VALUE;
        for (int j = 0; j < 9; j++) {
            if (!done[j] && numbers[j] < currentMin) {
                currentMin = numbers[j];
            }
        }

        if (numbers[index] == currentMin) {
            done[index] = true;
            btnCells[index].setEnabled(false);
            btnCells[index].setAlpha(0.4f);
            xCells[index].setVisibility(View.GONE);

            boolean allDone = true;
            for (int j = 0; j < 9; j++) {
                if (!done[j]) {
                    allDone = false;
                    break;
                }
            }
            if (allDone) {
                handler.removeCallbacks(timerRunnable);
                timerRunning = false;
                Toast.makeText(this, "Hoan thanh!", Toast.LENGTH_SHORT).show();
            }
        } else {
            fails++;
            tvFails.setText("Fails: " + fails);
            xCells[index].setVisibility(View.VISIBLE);
            handler.removeCallbacks(hideXRunnables[index]); // huy lich an-X cu neu bam sai lien tuc
            handler.postDelayed(hideXRunnables[index], 2000);
        }
    }
}
