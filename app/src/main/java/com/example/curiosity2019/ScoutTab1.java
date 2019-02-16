package com.example.curiosity2019;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ScoutTab1.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ScoutTab1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ScoutTab1 extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    //Button and Object Instances.....
    ImageView blueone;
    ImageView bluetwo;
    ImageView redone;
    ImageView redtwo;
    Button alllianceColor;
    Spinner startingPieces;
    Spinner teamList;
    RadioGroup startingPosition;
    EditText matchNumber;

    public ScoutTab1() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ScoutTab1.
     */
    // TODO: Rename and change types and number of parameters
    public static ScoutTab1 newInstance(String param1, String param2) {
        Log.d("Scout Tab 1", "New Tab Instance Created");
        ScoutTab1 fragment = new ScoutTab1();
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

        Log.d("Scout Tab 1", "On Create View Invoked");

        View rootView = inflater.inflate(R.layout.fragment_scout_tab1, container, false);

        //UpdateID
        mListener.updateTab1ID(rootView.getId());

       //Initialize All Button, Objects, etc
        blueone = rootView.findViewById(R.id.levelOneBlueBlock);
        bluetwo = rootView.findViewById(R.id.levelTwoBlueBlock);
        redone = rootView.findViewById(R.id.levelOneRedBlock);
        redtwo = rootView.findViewById(R.id.levelTwoRedBlock);

        startingPieces = rootView.findViewById(R.id.startingPieceSpinner);
        ArrayAdapter<CharSequence> startingPiecesAdapter = ArrayAdapter.createFromResource(this.getActivity(),R.array.starting_pieces, android.R.layout.simple_spinner_item);
        startingPiecesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        startingPieces.setAdapter(startingPiecesAdapter);

        teamList = rootView.findViewById(R.id.teamNumberSpinner);
        ArrayAdapter<CharSequence> teamListAdapter = ArrayAdapter.createFromResource(this.getActivity(),R.array.team_list, android.R.layout.simple_spinner_item);
        teamListAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        teamList.setAdapter(teamListAdapter);

        matchNumber = rootView.findViewById(R.id.matchNumberEditable);
        matchNumber.addTextChangedListener(new TextWatcher() {

            // the user's changes are saved here
            public void onTextChanged(CharSequence c, int start, int before, int count) {
                MatchNumberEntered(c.toString());
            }

            public void beforeTextChanged(CharSequence c, int start, int count, int after) {
                // this space intentionally left blank
            }

            public void afterTextChanged(Editable c) {
                // this one too
            }
        });

        alllianceColor = rootView.findViewById(R.id.allianceButtonColor);
        int tempColor = mListener.getAllianceColor();
        if(tempColor == Color.RED){
            alllianceColor.setText("RED"); //XML default does not seem to work? causes issues.....
        }
        else{
            alllianceColor.setText("BLUE"); //XML default does not seem to work? causes issues.....
        }
        UpdateAllianceColorForAll(tempColor);

        startingPosition = rootView.findViewById(R.id.startingPositionRBG);

        //Create Listeners for all buttons
        alllianceColor.setOnClickListener(this);
        startingPieces.setOnItemSelectedListener(this);
        teamList.setOnItemSelectedListener(this);

        // Inflate the layout for this fragment
        return rootView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.allianceButtonColor:
                AllianceColorClick();
            case R.id.startingPieceSpinner:
            default:
                break;
        }
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
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        switch (parent.getId()) {
            case R.id.startingPieceSpinner:
                StartingPiecesClick((String)parent.getItemAtPosition(position));
                break;
            case R.id.teamNumberSpinner:
                ScoutingTeam((String)parent.getItemAtPosition(position));
                break;
            default:
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

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
        public void updateAllianceColor(int color);
        public void updateStartingPiece(String piece);
        public void updateTab1ID(int id);
        public void updateScoutTeam(String team);
        public void updateMatchNumber(String matchNumber);
        public int  getAllianceColor();
    }

    public void AllianceColorClick(){
        Log.d("Scout Tab 1 :", "Alliance Color Click");
        //Check what color is currently selected
        //Button myButton = v.findViewById(R.id.allianceButtonColor);
        if(alllianceColor.getText() == "RED") {
            alllianceColor.setText("BLUE");
            mListener.updateAllianceColor(Color.BLUE);
        }
        else{
            alllianceColor.setText("RED");
            mListener.updateAllianceColor(Color.RED);
        }
    }

    public void StartingPiecesClick(String pieceSelected) {
        if(pieceSelected.equals("Hatch")){
            //alllianceColor.setText("HATCH");
        }
        else if(pieceSelected.equals("Cargo")){
            //alllianceColor.setText("CARGO");
        }
        else{
            //alllianceColor.setText("N");
        }
    }

    public void ScoutingTeam(String teamSelected) {
        //Send to Scout View to update Tool BAR
        mListener.updateScoutTeam(teamSelected);
    }

    public void MatchNumberEntered(String matchNumber) {
        mListener.updateMatchNumber(matchNumber);
    }

    public void UpdateAllianceColorForAll(int color){
        if(color == Color.BLUE) {
            //Button Pressed was RED ---> Change to BLUE
            //Update Master Color Object
            alllianceColor.setBackgroundColor(color);
            //startingPosition.set

            //Toggle Visible of Levels
            blueone.setVisibility(View.VISIBLE);
            bluetwo.setVisibility(View.VISIBLE);
            redone.setVisibility(View.INVISIBLE);
            redtwo.setVisibility(View.INVISIBLE);
        }
        else{
            //Button Pressed was BLUE ---> Change to RED
            //Update Master Color Object
            alllianceColor.setBackgroundColor(color);

            //Toggle Visible of Levels
            blueone.setVisibility(View.INVISIBLE);
            bluetwo.setVisibility(View.INVISIBLE);
            redone.setVisibility(View.VISIBLE);
            redtwo.setVisibility(View.VISIBLE);
        }
    }

}
