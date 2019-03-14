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

    Button cargoMinusButtonL3;
    Button cargoPlusButtonL3;
    Button hatchMinusButtonL3;
    Button hatchPlusButtonL3;

    Button cargoMinusButtonL2;
    Button cargoPlusButtonL2;
    Button hatchMinusButtonL2;
    Button hatchPlusButtonL2;

    Button cargoMinusButtonL1;
    Button cargoPlusButtonL1;
    Button hatchMinusButtonL1;
    Button hatchPlusButtonL1;

    Button cargoship_cargoPlusButton;
    Button cargoship_cargoMinusButton;
    Button cargoship_hatchPlusButton;
    Button cargoship_hatchMinusButton;


    RadioButton auto;
    RadioButton camera;
    RadioButton unknown;

    TextView cargoLabelLeftL3;
    TextView hatchLabelLeftL3;
    TextView cargoLabelLeftL2;
    TextView hatchLabelLeftL2;
    TextView cargoLabelLeftL1;
    TextView hatchLabelLeftL1;

    TextView cargoshipCargoText;
    TextView cagoshipHatchText;

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



        cargoship_cargoPlusButton = rootView.findViewById(R.id.cargoPlusButtonCargoShip);
        cargoship_cargoMinusButton = rootView.findViewById(R.id.cargoMinusButtonCargoShip);
        cargoship_hatchPlusButton = rootView.findViewById(R.id.hatchPlusButtonCargoShip);
        cargoship_hatchMinusButton = rootView.findViewById(R.id.hatchMinusButtonCargoShip);

        auto = rootView.findViewById(R.id.autoRadioButton);
        camera = rootView.findViewById(R.id.cameraRadioButton);
        unknown = rootView.findViewById(R.id.unknownRadioButton);

        int tempColor = mListener.getAllianceColor();

        cargoMinusButtonL3 = rootView.findViewById(R.id.cargoMinusButtonL3);
        cargoPlusButtonL3 = rootView.findViewById(R.id.cargoPlusButtonL3);
        hatchMinusButtonL3 = rootView.findViewById(R.id.hatchMinusButtonL3);
        hatchPlusButtonL3 = rootView.findViewById(R.id.hatchPlusButtonL3);

        cargoMinusButtonL2 = rootView.findViewById(R.id.cargoMinusButtonL2);
        cargoPlusButtonL2 = rootView.findViewById(R.id.cargoPlusButtonL2);
        hatchMinusButtonL2 = rootView.findViewById(R.id.hatchMinusButtonL2);
        hatchPlusButtonL2 = rootView.findViewById(R.id.hatchPlusButtonL2);

        cargoMinusButtonL1 = rootView.findViewById(R.id.cargoMinusButtonL1);
        cargoPlusButtonL1 = rootView.findViewById(R.id.cargoPlusButtonL1);
        hatchMinusButtonL1 = rootView.findViewById(R.id.hatchMinusButtonL1);
        hatchPlusButtonL1 = rootView.findViewById(R.id.hatchPlusButtonL1);

        cargoLabelLeftL3 = rootView.findViewById(R.id.cargoLabelLeftL3);
        hatchLabelLeftL3 = rootView.findViewById(R.id.hatchLabelLeftL3);
        cargoLabelLeftL2 = rootView.findViewById(R.id.cargoLabelLeftL2);
        hatchLabelLeftL2 = rootView.findViewById(R.id.hatchLabelLeftL2);
        cargoLabelLeftL1 = rootView.findViewById(R.id.cargoLabelLeftL1);
        hatchLabelLeftL1 = rootView.findViewById(R.id.hatchLabelLeftL1);

        cargoshipCargoText = rootView.findViewById(R.id.cargoLabelCargoShip);
        cagoshipHatchText = rootView.findViewById(R.id.hatchLabelCargoShip);

        updateCargoShipStrings();
        updateRocketShipStrings();


        cargoMinusButtonL3.setOnClickListener(this);
        cargoPlusButtonL3.setOnClickListener(this);
        hatchMinusButtonL3.setOnClickListener(this);
        hatchPlusButtonL3.setOnClickListener(this);

        cargoMinusButtonL2.setOnClickListener(this);
        cargoPlusButtonL2.setOnClickListener(this);
        hatchMinusButtonL2.setOnClickListener(this);
        hatchPlusButtonL2.setOnClickListener(this);

        cargoMinusButtonL1.setOnClickListener(this);
        cargoPlusButtonL1.setOnClickListener(this);
        hatchMinusButtonL1.setOnClickListener(this);
        hatchPlusButtonL1.setOnClickListener(this);

        cargoship_cargoPlusButton.setOnClickListener(this);
        cargoship_cargoMinusButton.setOnClickListener(this);
        cargoship_hatchPlusButton.setOnClickListener(this);
        cargoship_hatchMinusButton.setOnClickListener(this);

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
            case R.id.hatchPlusButtonCargoShip:
                mListener.updateSSCargoShipHatchMake(incrementCargoItemCount(mListener.getSSCargoShipHatchMake()));
                mListener.updateActionTimer();
                updateCargoShipStrings();
                break;
            case R.id.hatchMinusButtonCargoShip:
                mListener.updateSSCargoShipHatchMake( decrementCargoItemCount(mListener.getSSCargoShipHatchMake()));
                updateCargoShipStrings();
                break;
            case R.id.cargoPlusButtonCargoShip:
                mListener.updateSSCargoShipCargoMake(incrementCargoItemCount(mListener.getSSCargoShipCargoMake()));
                mListener.updateActionTimer();
                updateCargoShipStrings();
                break;
            case R.id.cargoMinusButtonCargoShip:
                mListener.updateSSCargoShipCargoMake(decrementCargoItemCount(mListener.getSSCargoShipCargoMake()));
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
            case R.id.cargoMinusButtonL3:
                mListener.updateSSLevelThreeCargo(decrementRocketItemCount(mListener.getSSLevelThreeCargoCount()));
                updateRocketShipStrings();
                break;
            case R.id.cargoPlusButtonL3:
                mListener.updateSSLevelThreeCargo(incrementRocketItemCount(mListener.getSSLevelThreeCargoCount()));
                updateRocketShipStrings();
                break;
            case R.id.hatchMinusButtonL3:
                mListener.updateSSLevelThreeHatch(decrementRocketItemCount(mListener.getSSLevelThreeHatchCount()));
                updateRocketShipStrings();
                break;
            case R.id.hatchPlusButtonL3:
                mListener.updateSSLevelThreeHatch(incrementRocketItemCount(mListener.getSSLevelThreeHatchCount()));
                updateRocketShipStrings();
                break;
            case R.id.cargoMinusButtonL2:
                mListener.updateSSLevelTwoCargo(decrementRocketItemCount(mListener.getSSLevelTwoCargoCount()));
                updateRocketShipStrings();
                break;
            case R.id.cargoPlusButtonL2:
                mListener.updateSSLevelTwoCargo(incrementRocketItemCount(mListener.getSSLevelTwoCargoCount()));
                updateRocketShipStrings();
                break;
            case R.id.hatchMinusButtonL2:
                mListener.updateSSLevelTwoHatch(decrementRocketItemCount(mListener.getSSLevelTwoHatchCount()));
                updateRocketShipStrings();
                break;
            case R.id.hatchPlusButtonL2:
                mListener.updateSSLevelTwoHatch(incrementRocketItemCount(mListener.getSSLevelTwoHatchCount()));
                updateRocketShipStrings();
                break;
            case R.id.cargoMinusButtonL1:
                mListener.updateSSLevelOneCargo(decrementRocketItemCount(mListener.getSSLevelOneCargoCount()));
                updateRocketShipStrings();
                break;
            case R.id.cargoPlusButtonL1:
                mListener.updateSSLevelOneCargo(incrementRocketItemCount(mListener.getSSLevelOneCargoCount()));
                updateRocketShipStrings();
                break;
            case R.id.hatchMinusButtonL1:
                mListener.updateSSLevelOneHatch(decrementRocketItemCount(mListener.getSSLevelOneHatchCount()));
                updateRocketShipStrings();
                break;
            case R.id.hatchPlusButtonL1:
                mListener.updateSSLevelOneHatch(incrementRocketItemCount(mListener.getSSLevelOneHatchCount()));
                updateRocketShipStrings();
                break;
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

        void updateSSRsMisses(int value);

        void updateSSCargoShipHatchMake(int value);

        void updateSSCargoShipHatchAtt(int value);

        void updateSSCargoShipCargoMake(int value);

        void updateSSCargoShipCargoAtt(int value);

        int getAllianceColor();

        void updateSSMovement(String movement);

        void updateSSLevelThreeCargo(int value);
        void updateSSLevelThreeHatch(int value);

        void updateSSLevelTwoCargo(int value);
        void updateSSLevelTwoHatch(int value);

        void updateSSLevelOneCargo(int value);
        void updateSSLevelOneHatch(int value);

        int getSSLevelThreeCargoCount();
        int getSSLevelThreeHatchCount();
        int getSSLevelTwoCargoCount();
        int getSSLevelTwoHatchCount();
        int getSSLevelOneCargoCount();
        int getSSLevelOneHatchCount();

        void updateActionTimer();
        void decrementActionCount();
    }

    public void updateCargoShipStrings() {
        //Update Counters.....
        int cs_hmake = mListener.getSSCargoShipHatchMake();

        int cs_cmake = mListener.getSSCargoShipCargoMake();

        cargoshipCargoText.setText(String.valueOf(cs_cmake));
        cagoshipHatchText.setText(String.valueOf(cs_hmake));

        //cargoship_cargoText.setText(cs_cmake + " / " + cs_catt);
        //cargoship_hatchText.setText(cs_hmake + " / " + cs_hatt);

    }

    private void updateRocketShipStrings() {
        //Update text in the hatch and cargo circles

        int levelThreeHatchCount = mListener.getSSLevelThreeHatchCount();
        int levelThreeCargoCount = mListener.getSSLevelThreeCargoCount();
        int levelTwoHatchCount = mListener.getSSLevelTwoHatchCount();
        int levelTwoCargoCount = mListener.getSSLevelTwoCargoCount();
        int levelOneHatchCount = mListener.getSSLevelOneHatchCount();
        int levelOneCargoCount = mListener.getSSLevelOneCargoCount();

        hatchLabelLeftL3.setText(String.valueOf(levelThreeHatchCount));
        cargoLabelLeftL3.setText(String.valueOf(levelThreeCargoCount));
        hatchLabelLeftL2.setText(String.valueOf(levelTwoHatchCount));
        cargoLabelLeftL2.setText(String.valueOf(levelTwoCargoCount));
        hatchLabelLeftL1.setText(String.valueOf(levelOneHatchCount));
        cargoLabelLeftL1.setText(String.valueOf(levelOneCargoCount));
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

    public int incrementRocketItemCount(int originalCount) {
        if(originalCount < 4) {
            return originalCount + 1;
        }
        else {
            // Cannot have more than 4 hatches or cargo on any level of the rocket
            return 4;
        }
    }

    public int decrementRocketItemCount(int originalCount) {
        if(originalCount > 0) {
            return originalCount - 1;
        }
        else {
            return 0;
        }
    }

    public int incrementCargoItemCount(int originalCount) {
        if(originalCount < 12) {
            return originalCount + 1;
        }
        else{
            return 12;
        }
    }

    public int decrementCargoItemCount(int originalCount){
        if(originalCount > 0){
            return originalCount - 1;
        }
        else{
            return 0;
        }
    }

}
