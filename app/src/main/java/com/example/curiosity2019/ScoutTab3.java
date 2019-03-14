package com.example.curiosity2019;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Layout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ScoutTab3.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ScoutTab3#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ScoutTab3 extends Fragment implements View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ImageView cargoCircleLeftL3;
    private ImageView hatchCircleLeftL3;

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

    TextView cargoLabelLeftL3;
    TextView hatchLabelLeftL3;
    TextView cargoLabelLeftL2;
    TextView hatchLabelLeftL2;
    TextView cargoLabelLeftL1;
    TextView hatchLabelLeftL1;

    Button cargoship_cargoPlusButton;
    Button cargoship_cargoMinusButton;
    Button cargoship_hatchPlusButton;
    Button cargoship_hatchMinusButton;

    TextView cargoshipCargoText;
    TextView cagoshipHatchText;



    PopupWindow gamePieceSelectionPopupWindow;

    private OnFragmentInteractionListener mListener;

    public ScoutTab3() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ScoutTab3.
     */
    // TODO: Rename and change types and number of parameters
    public static ScoutTab3 newInstance(String param1, String param2) {
        ScoutTab3 fragment = new ScoutTab3();
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
        final View view = inflater.inflate(R.layout.fragment_scout_tab3, container, false);

        cargoCircleLeftL3 = (ImageView) view.findViewById(R.id.cargoCircleLeftL3);
        hatchCircleLeftL3 = (ImageView) view.findViewById(R.id.hatchCircleLeftL3);
        cargoCircleLeftL3.setImageResource(R.drawable.small_cargo_orange_circle);
        hatchCircleLeftL3.setImageResource(R.drawable.small_hatch_yellow_circle);

        cargoship_cargoPlusButton = view.findViewById(R.id.cargoPlusButtonCargoShip);
        cargoship_cargoMinusButton = view.findViewById(R.id.cargoMinusButtonCargoShip);
        cargoship_hatchPlusButton = view.findViewById(R.id.hatchPlusButtonCargoShip);
        cargoship_hatchMinusButton = view.findViewById(R.id.hatchMinusButtonCargoShip);

        cargoshipCargoText = view.findViewById(R.id.cargoLabelCargoShip);
        cagoshipHatchText = view.findViewById(R.id.hatchLabelCargoShip);

        cargoMinusButtonL3 = view.findViewById(R.id.cargoMinusButtonL3);
        cargoPlusButtonL3 = view.findViewById(R.id.cargoPlusButtonL3);
        hatchMinusButtonL3 = view.findViewById(R.id.hatchMinusButtonL3);
        hatchPlusButtonL3 = view.findViewById(R.id.hatchPlusButtonL3);

        cargoMinusButtonL2 = view.findViewById(R.id.cargoMinusButtonL2);
        cargoPlusButtonL2 = view.findViewById(R.id.cargoPlusButtonL2);
        hatchMinusButtonL2 = view.findViewById(R.id.hatchMinusButtonL2);
        hatchPlusButtonL2 = view.findViewById(R.id.hatchPlusButtonL2);

        cargoMinusButtonL1 = view.findViewById(R.id.cargoMinusButtonL1);
        cargoPlusButtonL1 = view.findViewById(R.id.cargoPlusButtonL1);
        hatchMinusButtonL1 = view.findViewById(R.id.hatchMinusButtonL1);
        hatchPlusButtonL1 = view.findViewById(R.id.hatchPlusButtonL1);

        cargoLabelLeftL3 = view.findViewById(R.id.cargoLabelLeftL3);
        hatchLabelLeftL3 = view.findViewById(R.id.hatchLabelLeftL3);
        cargoLabelLeftL2 = view.findViewById(R.id.cargoLabelLeftL2);
        hatchLabelLeftL2 = view.findViewById(R.id.hatchLabelLeftL2);
        cargoLabelLeftL1 = view.findViewById(R.id.cargoLabelLeftL1);
        hatchLabelLeftL1 = view.findViewById(R.id.hatchLabelLeftL1);

        updateCargoShipStrings();
        updateRocketShipStrings();

        cargoship_cargoPlusButton.setOnClickListener(this);
        cargoship_cargoMinusButton.setOnClickListener(this);
        cargoship_hatchPlusButton.setOnClickListener(this);
        cargoship_hatchMinusButton.setOnClickListener(this);

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


        return view;
    }

    private void updateCargoShipStrings() {
        //Update Counters.....
        int rs_hmake = mListener.getConCargoShipHatchMake();

        int rs_cmake = mListener.getConCargoShipCargoMake();

        cargoshipCargoText.setText(String.valueOf(rs_cmake));
        cagoshipHatchText.setText(String.valueOf(rs_hmake));
    }

    private void updateRocketShipStrings() {
        //Update text in the hatch and cargo circles

        int levelThreeHatchCount = mListener.getConLevelThreeHatchCount();
        int levelThreeCargoCount = mListener.getConLevelThreeCargoCount();
        int levelTwoHatchCount = mListener.getConLevelTwoHatchCount();
        int levelTwoCargoCount = mListener.getConLevelTwoCargoCount();
        int levelOneHatchCount = mListener.getConLevelOneHatchCount();
        int levelOneCargoCount = mListener.getConLevelOneCargoCount();

        hatchLabelLeftL3.setText(String.valueOf(levelThreeHatchCount));
        cargoLabelLeftL3.setText(String.valueOf(levelThreeCargoCount));
        hatchLabelLeftL2.setText(String.valueOf(levelTwoHatchCount));
        cargoLabelLeftL2.setText(String.valueOf(levelTwoCargoCount));
        hatchLabelLeftL1.setText(String.valueOf(levelOneHatchCount));
        cargoLabelLeftL1.setText(String.valueOf(levelOneCargoCount));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.hatchPlusButtonCargoShip:
                mListener.updateConCargoShipHatchMake(incrementCargoItemCount(mListener.getConCargoShipHatchMake()));
                updateCargoShipStrings();
                break;
            case R.id.hatchMinusButtonCargoShip:
                mListener.updateConCargoShipHatchMake( decrementCargoItemCount(mListener.getConCargoShipHatchMake()));
                updateCargoShipStrings();
                break;
            case R.id.cargoPlusButtonCargoShip:
                mListener.updateConCargoShipCargoMake(incrementCargoItemCount(mListener.getConCargoShipCargoMake()));
                updateCargoShipStrings();
                break;
            case R.id.cargoMinusButtonCargoShip:
                mListener.updateConCargoShipCargoMake(decrementCargoItemCount(mListener.getConCargoShipCargoMake()));
                updateCargoShipStrings();
                break;
            case R.id.cargoMinusButtonL3:
                mListener.updateConLevelThreeCargo(decrementRocketItemCount(mListener.getConLevelThreeCargoCount()));
                updateRocketShipStrings();
                break;
            case R.id.cargoPlusButtonL3:
                mListener.updateConLevelThreeCargo(incrementRocketItemCount(mListener.getConLevelThreeCargoCount()));
                mListener.updateActionTimer();
                updateRocketShipStrings();
                break;
            case R.id.hatchMinusButtonL3:
                mListener.updateConLevelThreeHatch(decrementRocketItemCount(mListener.getConLevelThreeHatchCount()));
                updateRocketShipStrings();
                break;
            case R.id.hatchPlusButtonL3:
                mListener.updateConLevelThreeHatch(incrementRocketItemCount(mListener.getConLevelThreeHatchCount()));
                mListener.updateActionTimer();
                updateRocketShipStrings();
                break;
            case R.id.cargoMinusButtonL2:
                mListener.updateConLevelTwoCargo(decrementRocketItemCount(mListener.getConLevelTwoCargoCount()));
                updateRocketShipStrings();
                break;
            case R.id.cargoPlusButtonL2:
                mListener.updateConLevelTwoCargo(incrementRocketItemCount(mListener.getConLevelTwoCargoCount()));
                mListener.updateActionTimer();
                updateRocketShipStrings();
                break;
            case R.id.hatchMinusButtonL2:
                mListener.updateConLevelTwoHatch(decrementRocketItemCount(mListener.getConLevelTwoHatchCount()));
                updateRocketShipStrings();
                break;
            case R.id.hatchPlusButtonL2:
                mListener.updateConLevelTwoHatch(incrementRocketItemCount(mListener.getConLevelTwoHatchCount()));
                mListener.updateActionTimer();
                updateRocketShipStrings();
                break;
            case R.id.cargoMinusButtonL1:
                mListener.updateConLevelOneCargo(decrementRocketItemCount(mListener.getConLevelOneCargoCount()));
                updateRocketShipStrings();
                break;
            case R.id.cargoPlusButtonL1:
                mListener.updateConLevelOneCargo(incrementRocketItemCount(mListener.getConLevelOneCargoCount()));
                mListener.updateActionTimer();
                updateRocketShipStrings();
                break;
            case R.id.hatchMinusButtonL1:
                mListener.updateConLevelOneHatch(decrementRocketItemCount(mListener.getConLevelOneHatchCount()));
                updateRocketShipStrings();
                break;
            case R.id.hatchPlusButtonL1:
                mListener.updateConLevelOneHatch(incrementRocketItemCount(mListener.getConLevelOneHatchCount()));
                mListener.updateActionTimer();
                updateRocketShipStrings();
                break;
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


    public void createGamePieceSelectionPopupWindow(View contentView) {
//        gamePieceSelectionPopupWindow = new PopupWindow(contentView, 200, 75, true);

        gamePieceSelectionPopupWindow = new PopupWindow(getLayoutInflater().inflate(R.layout.popup_rocket_item_selection, null), 400, 200);
        gamePieceSelectionPopupWindow.showAtLocation(contentView, Gravity.CENTER, 0, 0);



        System.out.print("Should be launching popup");
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

        void updateConLevelThreeCargo(int value);
        void updateConLevelThreeHatch(int value);

        void updateConLevelTwoCargo(int value);
        void updateConLevelTwoHatch(int value);

        void updateConLevelOneCargo(int value);
        void updateConLevelOneHatch(int value);

        void updateConRsMisses(int value);

        void updateConCargoShipHatchMake(int value);
        void updateConCargoShipHatchAtt(int value);

        void updateConCargoShipCargoMake(int value);
        void updateConCargoShipCargoAtt(int value);

        int getConCargoShipHatchMake();
        int getConCargoShipHatchAttempted();
        int getConCargoShipCargoMake();
        int getConCargoShipCargoAttempted();

        void updateActionTimer();
        void decrementActionCount();

        //FIXME: cocneanu finish implementing these:
        int getConLevelThreeCargoCount();
        int getConLevelThreeHatchCount();
        int getConLevelTwoCargoCount();
        int getConLevelTwoHatchCount();
        int getConLevelOneCargoCount();
        int getConLevelOneHatchCount();


    }
}
