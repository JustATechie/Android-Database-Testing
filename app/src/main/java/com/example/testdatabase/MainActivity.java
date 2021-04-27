package com.example.testdatabase;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.StrictMode;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.core.app.ActivityCompat;

import java.sql.*;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private String ConnectionURL = "jdbc:postgresql://queenie.db.elephantsql.com:5432/fgnvsllc";
    private String user = "fgnvsllc";
    private String password = "6hfH9vzFBDuDBy5W0B_g1pjgZ_CNq_Ij";
    private String classes = "org.postgresql.Driver";

    private Connection conn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET}, PackageManager.PERMISSION_GRANTED);

        textView = findViewById(R.id.TestArticleTitle);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try {
            Class.forName(classes);
            conn = DriverManager.getConnection(ConnectionURL, user, password);
            textView.setText("SUCCESS");

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            textView.setText("ERROR");
        }


    }

    public void sqlButton(View view) throws SQLException {
        if(conn != null) {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM articles WHERE articles.id='2';");

            while(rs.next()) {
                textView.setText(rs.getString(2));
            }

            rs.close();
            st.close();

        } else {
            textView.setText("Connection is null!");
        }

    }


}