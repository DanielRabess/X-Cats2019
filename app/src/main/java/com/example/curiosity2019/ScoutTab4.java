package com.example.curiosity2019;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.app.AlertDialog;
import android.content.DialogInterface;

import com.example.curiosity2019.MatchData.ScoutMatchData;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ScoutTab4.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ScoutTab4#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ScoutTab4 extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;


    ImageView red1;
    ImageView red2;
    ImageView red3;
    ImageView blue1;
    ImageView blue2;
    ImageView blue3;

    RadioButton lv0;
    RadioButton lv1;
    RadioButton lv2;
    RadioButton lv3;

    RadioButton alone;
    RadioButton assisted;
    RadioButton partner;

    RadioButton yes;
    RadioButton no;

    Button resultButton;
    Button saveMatch;

    EditText comments;
    EditText points;

    public ScoutTab4() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ScoutTab4.
     */
    // TODO: Rename and change types and number of parameters
    public static ScoutTab4 newInstance(String param1, String param2) {
        ScoutTab4 fragment = new ScoutTab4();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_scout_tab4, container, false);


        //Initialize All Button, Objects, etc
        blue1 = rootView.findViewById(R.id.levelOneBlueBlock);
        blue2 = rootView.findViewById(R.id.levelTwoBlueBlock);
        blue3 = rootView.findViewById(R.id.levelThreeBlueBlock);
        red1 = rootView.findViewById(R.id.levelOneRedBlock);
        red2 = rootView.findViewById(R.id.levelTwoRedBlock);
        red3 = rootView.findViewById(R.id.levelThreeRedBlock);

        lv0 = rootView.findViewById(R.id.lvlnone);
        lv1 = rootView.findViewById(R.id.lvl1);
        lv2 = rootView.findViewById(R.id.lvl2);
        lv3 = rootView.findViewById(R.id.lvl3);

        alone = rootView.findViewById(R.id.climbed_aloneRB);
        assisted = rootView.findViewById(R.id.was_assistedRB);
        partner = rootView.findViewById(R.id.partner_assistRB);

        yes = rootView.findViewById(R.id.radioyes);
        no = rootView.findViewById(R.id.radiono);

        resultButton = rootView.findViewById(R.id.resultButton);
        saveMatch = rootView.findViewById(R.id.savematchbn);

        comments = rootView.findViewById(R.id.commentText);
        points = rootView.findViewById(R.id.pointsText);

        //Edit Text Listeners....
        comments.addTextChangedListener(new TextWatcher() {

            // the user's changes are saved here
            public void onTextChanged(CharSequence c, int start, int before, int count) {
                malfunctionCommentEntered(c.toString());
            }

            public void beforeTextChanged(CharSequence c, int start, int count, int after) {
                // this space intentionally left blank
            }

            public void afterTextChanged(Editable c) {
                // this one too
            }
        });

        points.addTextChangedListener(new TextWatcher() {

            // the user's changes are saved here
            public void onTextChanged(CharSequence c, int start, int before, int count) {
                pointsEntered(c.toString());
            }

            public void beforeTextChanged(CharSequence c, int start, int count, int after) {
                // this space intentionally left blank
            }

            public void afterTextChanged(Editable c) {
                // this one too
            }
        });

        //On Click Listeners...
        lv0.setOnClickListener(this);
        lv1.setOnClickListener(this);
        lv2.setOnClickListener(this);
        lv3.setOnClickListener(this);

        alone.setOnClickListener(this);
        assisted.setOnClickListener(this);
        partner.setOnClickListener(this);

        yes.setOnClickListener(this);
        no.setOnClickListener(this);

        resultButton = rootView.findViewById(R.id.resultButton);
        saveMatch = rootView.findViewById(R.id.savematchbn);

        String currentRes = mListener.getResult();
        resultButton.setText(currentRes);
        if(currentRes.equals("lose")){
            setLoseResult();
        }
        else{
            setWinResult();
        }
        int tempColor = mListener.getAllianceColor();

        resultButton.setOnClickListener(this);
        saveMatch.setOnClickListener(this);

        UpdateAllianceColorForAll(tempColor);

        // Inflate the layout for this fragment
        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.resultButton:
                toggleResult();
                break;
            case R.id.savematchbn:
                saveMatch();
                break;
            case R.id.lvlnone:
                mListener.updateClimbedLevel("none");
                break;
            case R.id.lvl1:
                mListener.updateClimbedLevel("one");
                break;
            case R.id.lvl2:
                mListener.updateClimbedLevel("two");
                break;
            case R.id.lvl3:
                mListener.updateClimbedLevel("three");
                break;
            case R.id.climbed_aloneRB:
                mListener.updateClimbingMethod("unassisted");
                break;
            case R.id.was_assistedRB:
                mListener.updateClimbingMethod("assistedPartner");
                break;
            case R.id.partner_assistRB:
                mListener.updateClimbingMethod("wasAssisted");
                break;
            case R.id.radioyes:
                mListener.updateMalfunction("yes");
                break;
            case R.id.radiono:
                mListener.updateMalfunction("no");
                break;
            default:
                break;
        }
    }

    private void saveMatch() {
        // Build an AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // Set a title for alert dialog
        builder.setTitle("Save Match Confirmation");

        // Ask the final question
        builder.setMessage("Are you sure you're ready to save the match?");

        // Set the alert dialog yes button click listener
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Do something when user clicked the Yes button
                // Set the TextView visibility GONE
               //tv.setVisibility(View.GONE);

                mListener.calculateAverageTime();
                mListener.exportMatchToFile();
                startActivity(new Intent(getActivity(), Home.class));
            }
        });

        // Set the alert dialog no button click listener
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Do something when No button clicked
                //Toast.makeText(getApplicationContext(),
                //       "No Button Clicked",Toast.LENGTH_SHORT).show();
            }
        });

        AlertDialog dialog = builder.create();
        // Display the alert dialog on interface
        dialog.show();
    }

    private void toggleResult() {
        System.out.println("mListener.getAllianceColor() "+mListener.getAllianceColor());
        System.out.println("R.color.blue "+R.color.blue);
        System.out.println("R.color.red "+R.color.red);


        if(resultButton.getText().equals("lose")){
            //update text to win
            setWinResult();
        }
        else if(resultButton.getText().equals("win")){
            setTieResult();
        }
        else{
            //updat text to lose
            setLoseResult();
        }
    }

    private void setWinResult()
    {
        resultButton.setText("win");
        mListener.updateResults("win");
        if(mListener.getAllianceColorEnum().equals(ScoutMatchData.AllianceColorEnum.BLUE)) {
            resultButton.setBackgroundColor(Color.BLUE);
            resultButton.setTextColor(Color.WHITE);
        } else if(mListener.getAllianceColorEnum().equals(ScoutMatchData.AllianceColorEnum.RED)) {
            resultButton.setBackgroundColor(Color.RED);
            resultButton.setTextColor(Color.BLACK);
        }
    }

    private void setLoseResult() {
        resultButton.setText("lose");
        mListener.updateResults("lose");
        if(mListener.getAllianceColorEnum().equals(ScoutMatchData.AllianceColorEnum.BLUE)) {
            resultButton.setBackgroundColor(Color.RED);
            resultButton.setTextColor(Color.BLACK);
        } else if(mListener.getAllianceColorEnum().equals(ScoutMatchData.AllianceColorEnum.RED)) {
            resultButton.setBackgroundColor(Color.BLUE);
            resultButton.setTextColor(Color.WHITE);
        }
    }

    private void setTieResult() {
        resultButton.setText("tie");
        mListener.updateResults("tie");
        resultButton.setBackgroundColor(Color.LTGRAY);
        resultButton.setTextColor(Color.BLACK);
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
        public int  getAllianceColor();
        public ScoutMatchData.AllianceColorEnum getAllianceColorEnum();
        public void exportMatchToFile();
        public void updateClimbedLevel(String level);
        public void updateClimbingMethod(String method);
        public void updateMalfunction(String mal);

        public void updateMalfunctionComment(String comment);
        public void updatePoints(String points);

        public String getResult();

        public void updateResults(String res);

        void calculateAverageTime();
    }


    //****************************************************
    public void UpdateAllianceColorForAll(int color){
        Log.d("Scout Tab 4 :", "Alliance Color Update");
        if(color == Color.BLUE) {
            //Button Pressed was RED ---> Change to BLUE
            //Update Master Color Object

            lv0.setButtonTintList(ColorStateList.valueOf(Color.BLUE));
            lv1.setButtonTintList(ColorStateList.valueOf(Color.BLUE));
            lv2.setButtonTintList(ColorStateList.valueOf(Color.BLUE));
            lv3.setButtonTintList(ColorStateList.valueOf(Color.BLUE));

            alone.setButtonTintList(ColorStateList.valueOf(Color.BLUE));
            assisted.setButtonTintList(ColorStateList.valueOf(Color.BLUE));
            partner.setButtonTintList(ColorStateList.valueOf(Color.BLUE));

            yes.setButtonTintList(ColorStateList.valueOf(Color.BLUE));
            no.setButtonTintList(ColorStateList.valueOf(Color.BLUE));

            comments.setTextColor(color);
            comments.setHighlightColor(color);
            comments.setBackgroundTintList(ColorStateList.valueOf(Color.BLUE));

            points.setTextColor(color);
            points.setHighlightColor(color);
            points.setBackgroundTintList(ColorStateList.valueOf(Color.BLUE));

            //Toggle Visible of Levels
            blue1.setVisibility(View.VISIBLE);
            blue2.setVisibility(View.VISIBLE);
            blue3.setVisibility(View.VISIBLE);
            red1.setVisibility(View.INVISIBLE);
            red2.setVisibility(View.INVISIBLE);
            red3.setVisibility(View.INVISIBLE);
        }
        else{
            //Button Pressed was BLUE ---> Change to RED
            //Update Master Color Object

            lv0.setButtonTintList(ColorStateList.valueOf(Color.RED));
            lv1.setButtonTintList(ColorStateList.valueOf(Color.RED));
            lv2.setButtonTintList(ColorStateList.valueOf(Color.RED));
            lv3.setButtonTintList(ColorStateList.valueOf(Color.RED));

            alone.setButtonTintList(ColorStateList.valueOf(Color.RED));
            assisted.setButtonTintList(ColorStateList.valueOf(Color.RED));
            partner.setButtonTintList(ColorStateList.valueOf(Color.RED));

            yes.setButtonTintList(ColorStateList.valueOf(Color.RED));
            no.setButtonTintList(ColorStateList.valueOf(Color.RED));

            comments.setTextColor(color);
            comments.setHighlightColor(color);
            comments.setBackgroundTintList(ColorStateList.valueOf(Color.RED));

            points.setTextColor(color);
            points.setHighlightColor(color);
            points.setBackgroundTintList(ColorStateList.valueOf(Color.RED));


            //Toggle Visible of Levels
            blue1.setVisibility(View.INVISIBLE);
            blue2.setVisibility(View.INVISIBLE);
            blue3.setVisibility(View.INVISIBLE);
            red1.setVisibility(View.VISIBLE);
            red2.setVisibility(View.VISIBLE);
            red3.setVisibility(View.VISIBLE);
        }
    }

    public void exportMatch(){
        mListener.exportMatchToFile();
    }

    public void malfunctionCommentEntered(String comment){
        mListener.updateMalfunctionComment(comment);
    }

    public void pointsEntered(String points){
        mListener.updatePoints(points);
    }
}
