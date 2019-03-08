package com.example.curiosity2019;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ScoutTab2.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ScoutTab2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ScoutTab2 extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    Button cargoship_cargoMake;
    Button cargoship_cargoMiss;
    Button cargoship_hatchMake;
    Button cagroship_hatchMiss;

    RadioButton auto;
    RadioButton camera;
    RadioButton unknown;

    TextView cargoship_cargoText;
    TextView cargoship_hatchText;



    public ScoutTab2() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ScoutTab2.
     */
    // TODO: Rename and change types and number of parameters
    public static ScoutTab2 newInstance(String param1, String param2) {
        ScoutTab2 fragment = new ScoutTab2();
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
        // Inflate the layout for this fragment

        View rootView =inflater.inflate(R.layout.fragment_scout_tab2, container, false);

        cargoship_cargoMake = rootView.findViewById(R.id.cargoship_hmake);
        cargoship_cargoMiss = rootView.findViewById(R.id.cargoship_hmiss);
        cargoship_hatchMake = rootView.findViewById(R.id.cargoship_cmake);
        cagroship_hatchMiss = rootView.findViewById(R.id.cargoship_cmiss);

        cargoship_cargoText = rootView.findViewById(R.id.cargoMMtextView);
        cargoship_hatchText = rootView.findViewById(R.id.hatchMMtextView);

        auto = rootView.findViewById(R.id.autoRadioButton);
        camera = rootView.findViewById(R.id.cameraRadioButton);
        unknown = rootView.findViewById(R.id.unknownRadioButton);

        int tempColor = mListener.getAllianceColor();


       updateCargoShipStrings();

        cargoship_cargoMake.setOnClickListener(this);
        cargoship_cargoMiss.setOnClickListener(this);
        cargoship_hatchMake.setOnClickListener(this);
        cagroship_hatchMiss.setOnClickListener(this);

        auto.setOnClickListener(this);
        camera.setOnClickListener(this);
        unknown.setOnClickListener(this);

        UpdateAllianceColorForAll(tempColor);

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
            case R.id.cargoship_hmake:
                mListener.updateSSCargoShipHatchMake(mListener.getSSCargoShipHatchMake()+1);
                mListener.updateSSCargoShipHatchAtt( mListener.getSSCargoShipHatchAttempted()+1);
                updateCargoShipStrings();
                break;
            case R.id.cargoship_hmiss:
                mListener.updateSSCargoShipHatchAtt( mListener.getSSCargoShipHatchAttempted()+1);
                updateCargoShipStrings();
                break;
            case R.id.cargoship_cmake:
                mListener.updateSSCargoShipCargoMake(mListener.getSSCargoShipCargoMake()+1);
                mListener.updateSSCargoShipCargoAtt(mListener.getSSCargoShipCargoAttempted()+1);
                updateCargoShipStrings();
                break;
            case R.id.cargoship_cmiss:
                mListener.updateSSCargoShipCargoAtt(mListener.getSSCargoShipCargoAttempted()+1);
                updateCargoShipStrings();
                break;
            case R.id.autoRadioButton:
                mListener.updateSSMovement("Auto");
                break;
            case R.id.cameraRadioButton:
                mListener.updateSSMovement("Camera");
                break;
            case R.id.unknownRadioButton:
                mListener.updateSSMovement("Unknown");
            default:
                break;
        }
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

        int getSSCargoShipHatchMake();
        int getSSCargoShipHatchAttempted();
        int getSSCargoShipCargoMake();
        int getSSCargoShipCargoAttempted();

        void updateSSCargoShipHatchMake(int value);

        void updateSSCargoShipHatchAtt(int value);

        void updateSSCargoShipCargoMake(int value);

        void updateSSCargoShipCargoAtt(int value);

        int getAllianceColor();

        void updateSSMovement(String movement);
    }

    public void updateCargoShipStrings() {
        //Update Counters.....
        int cs_hmake = mListener.getSSCargoShipHatchMake();
        int cs_hatt = mListener.getSSCargoShipHatchAttempted();
        int cs_cmake = mListener.getSSCargoShipCargoMake();
        int cs_catt = mListener.getSSCargoShipCargoAttempted();

        cargoship_cargoText.setText(cs_cmake + " / " + cs_catt);
        cargoship_hatchText.setText(cs_hmake + " / " + cs_hatt);

    }

    public void UpdateAllianceColorForAll(int color){
        Log.d("Scout Tab 2 :", "Alliance Color Update");
        if(color == Color.BLUE) {
            //Button Pressed was RED ---> Change to BLUE
            //Update Master Color Object

            auto.setButtonTintList(ColorStateList.valueOf(Color.BLUE));
            camera.setButtonTintList(ColorStateList.valueOf(Color.BLUE));
            unknown.setButtonTintList(ColorStateList.valueOf(Color.BLUE));

        }
        else{
            //Button Pressed was BLUE ---> Change to RED
            //Update Master Color Object

            auto.setButtonTintList(ColorStateList.valueOf(Color.RED));
            camera.setButtonTintList(ColorStateList.valueOf(Color.RED));
            unknown.setButtonTintList(ColorStateList.valueOf(Color.RED));

        }
    }

}
