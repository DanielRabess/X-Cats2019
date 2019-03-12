package com.example.curiosity2019;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    Button button;
    Button buttonTab;

    final static String tbaKey = "U0rx6iHZYLFx1InrycvsfhYuxgRQPORyDM07f4Ekz2fHfftxJWAbIpzMD9SIl1sd";
    final static String tbaHeader = "X-TBA-Auth-Key";
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

    //Both the event name and event code in a string
    List<String> eventInfo;
    //just the event name for spinner
    List<String> eventList;
    //just the event code for saving
    List<String> eventCode;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Prevents keyboard from popping up on activity start
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        //InputMethodManager mgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        //mgr.hideSoftInputFromWindow(curEditText.getWindowToken(), 0);

        sharedPreferences = this.getSharedPreferences(
                getString(R.string.app_name), Context.MODE_PRIVATE);
    }

    @Override
    protected void onResume(){
        super.onResume();

        Gson gson = new Gson();
        String jsonEventList = sharedPreferences.getString("jsonEventList","empty");
        String jsonEventCodes = sharedPreferences.getString("jsonEventCodes","empty");

        if (jsonEventList.equalsIgnoreCase("empty") || jsonEventCodes.equalsIgnoreCase("empty") ) {
            eventTeamRetrieval();
        }
        else {

            Type type = new TypeToken<List<String>>() {}.getType();

            eventList = gson.fromJson(jsonEventList, type);
            eventCode = gson.fromJson(jsonEventCodes, type);

            updateEventSpinner();
        }

        eventTeamRetrieval();
    }

    private void eventTeamRetrieval() {

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .header(tbaHeader, tbaKey)
                .url("http://www.thebluealliance.com/api/v3/team/frc191/events/2018/simple")
                .build();
        Log.d("parsedEventList", "Preparing to make Client call for teams");
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()){
                    throw new IOException("Unexpected code " + response);
                }
                else {
                    eventInfo = parseResponse(response.body().byteStream());
                    Log.d("parsedEventList", eventInfo.toString());

                    parseEventList();

                    MainActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            updateEventSpinner();
                        }
                    });

                    updateSavedTeamInfo();
                }
            }
        });
    }

    private void parseEventList() {
        if (!eventInfo.isEmpty()) {

            eventList = new ArrayList<>();
            eventCode = new ArrayList<>();

            for(int i=0;i<eventInfo.size();i++) {

                String[] split = eventInfo.get(i).split(":!:");

                if (split.length == 2) {
                    eventList.add(split[0]);
                    eventCode.add(split[1]);
                }
            }
        }
    }

    private List<String> parseResponse(InputStream response) throws IOException {
        JsonReader reader = new JsonReader(new InputStreamReader(response, StandardCharsets.UTF_8));
        try {
            return readMessagesArray(reader);
        } finally {
            reader.close();
        }
    }

    public List<String> readMessagesArray(JsonReader reader) throws IOException {
        List<String> messages = new ArrayList<>();

        reader.beginArray();
        while (reader.hasNext()) {
            messages.add(readMessage(reader));
        }
        reader.endArray();
        return messages;
    }

    public String readMessage(JsonReader reader) throws IOException {

        String eventName = null;
        String eventCode = null;

        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("name")) {
                eventName = reader.nextString();
            } else if (name.equals("event_code")) {
                eventCode = reader.nextString();
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return eventName + ":!:" + eventCode;
    }

    private void updateEventSpinner() {
        Spinner eventSpinner = findViewById(R.id.spinnerEvent);

        ArrayAdapter<String> eventAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, eventList);
        eventAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        eventSpinner.setAdapter(eventAdapter);
    }

    private void updateSavedTeamInfo() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String jsonEventList = gson.toJson(eventList);
        String jsonEventCodes = gson.toJson(eventCode);

        editor.putString("jsonEventList", jsonEventList);
        editor.putString("jsonEventCodes", jsonEventCodes);

        editor.commit();
    }

    public void loginClick(View view) {

        String userName = userNameValidation();

        if (userName == null)
            return;

        String[] eventSelected = eventValidation();


        if( eventSelected == null)
            return;

        Log.d("LoginClick","Selected Event:" + eventSelected[0] + " Code: "+ eventCode.get(Integer.parseInt(eventSelected[1])));

        SharedPreferences.Editor editor = sharedPreferences.edit();

        if(!eventSelected[0].equalsIgnoreCase(sharedPreferences.getString("selectedEvent","nada"))){
            //if the newly selected event is different than the previously saved one, we need to throw away our team list
            editor.putString("jsonTeamList","empty");;

        }

        editor.putString("selectedEvent", eventSelected[0]);
        editor.putString("selectedEventCode", eventCode.get(Integer.parseInt(eventSelected[1])));
        editor.putString("userName",userName);
        editor.commit();

        int check = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (check == PackageManager.PERMISSION_GRANTED) {
            //We have permission ... dont ask again
        }
        else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 43);
        }
//        button = (Button)findViewById(R.id.buttonLogin);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this, Home.class));
//            }
//        });

        startActivity(new Intent(MainActivity.this, Home.class));
    }

    private String[] eventValidation() {
        Spinner eventSpinner= (Spinner) findViewById(R.id.spinnerEvent);
        int eventPosition = eventSpinner.getSelectedItemPosition();

        if (eventSpinner.getSelectedItem().toString().contains("Select an Event")){

            Toast.makeText(this, "Please select an event from the list!", Toast.LENGTH_LONG).show();
            return null;
        }

        if(eventList != null)
            return new String[]{eventList.get(eventPosition), String.valueOf(eventPosition)};
        else {
            eventCode = new ArrayList<>();
            eventCode.add("nyut");
            String[] teamNumList = getResources().getStringArray(R.array.testEventList);
            return new String[]{teamNumList[0],String.valueOf(0)};
        }
    }

    private String userNameValidation() {

        EditText userNameEditText = findViewById(R.id.userNameField);
        String userName = String.valueOf(userNameEditText.getText());

        if (userName.isEmpty()){
            Toast.makeText(this, "Please enter your username!", Toast.LENGTH_LONG).show();
            return null;
        }

        return userName;
    }

    @Override
    public void onBackPressed() {
        if (true) {

        } else {
            super.onBackPressed();
        }
    }
}
