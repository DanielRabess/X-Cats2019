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
    private Button leftShipL3Button;
    private ImageView cargoCircleLeftL3;
    private ImageView hatchCircleLeftL3;

    Button rocketship_cargoMake;
    Button rocketship_cargoMiss;
    Button rocketship_hatchMake;
    Button rocketship_hatchMiss;

    TextView rocketship_cargoText;
    TextView rocketship_hatchText;

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

//            cargoCircleLeftL3 = (Drawable) findViewById(R.id.);

            leftShipL3Button.setOnClickListener(this);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_scout_tab3, container, false);

        leftShipL3Button = (Button) view.findViewById(R.id.leftShipL3Button);
        cargoCircleLeftL3 = (ImageView) view.findViewById(R.id.cargoCircleLeftL3);
        hatchCircleLeftL3 = (ImageView) view.findViewById(R.id.hatchCircleLeftL3);

        rocketship_cargoMake = view.findViewById(R.id.rocketship_hmake);
        rocketship_cargoMiss = view.findViewById(R.id.rocketship_hmiss);
        rocketship_hatchMake = view.findViewById(R.id.rocketship_cmake);
        rocketship_hatchMiss = view.findViewById(R.id.rocketship_cmiss);

        rocketship_cargoText = view.findViewById(R.id.rscargoMMtextView);
        rocketship_hatchText = view.findViewById(R.id.rshatchMMtextView);

        updateCargoShipStrings();

        rocketship_cargoMake.setOnClickListener(this);
        rocketship_cargoMiss.setOnClickListener(this);
        rocketship_hatchMake.setOnClickListener(this);
        rocketship_hatchMiss.setOnClickListener(this);




//        leftShipL3Button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                cargoCircleLeftL3.setImageResource(R.drawable.small_cargo_orange_circle);
//                hatchCircleLeftL3.setImageResource(R.drawable.small_hatch_yellow_circle);
//            }
//        });

        return view;
    }

    private void updateCargoShipStrings() {
        //Update Counters.....
        int rs_hmake = mListener.getConCargoShipHatchMake();
        int rs_hatt = mListener.getConCargoShipHatchAttempted();
        int rs_cmake = mListener.getConCargoShipCargoMake();
        int rs_catt = mListener.getConCargoShipCargoAttempted();

        rocketship_cargoText.setText(rs_cmake + " / " + rs_catt);
        rocketship_hatchText.setText(rs_hmake + " / " + rs_hatt);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.leftShipL3Button:
                createGamePieceSelectionPopupWindow(view);
                cargoCircleLeftL3.setImageResource(R.drawable.small_cargo_orange_circle);
                hatchCircleLeftL3.setImageResource(R.drawable.small_hatch_yellow_circle);
                break;
            case R.id.rocketship_hmake:
                mListener.updateConCargoShipHatchMake(mListener.getConCargoShipHatchMake()+1);
                mListener.updateConCargoShipHatchAtt( mListener.getConCargoShipHatchAttempted()+1);
                updateCargoShipStrings();
                break;
            case R.id.rocketship_hmiss:
                mListener.updateConCargoShipHatchAtt( mListener.getConCargoShipHatchAttempted()+1);
                updateCargoShipStrings();
                break;
            case R.id.rocketship_cmake:
                mListener.updateConCargoShipCargoMake(mListener.getConCargoShipCargoMake()+1);
                mListener.updateConCargoShipCargoAtt(mListener.getConCargoShipCargoAttempted()+1);
                updateCargoShipStrings();
                break;
            case R.id.rocketship_cmiss:
                mListener.updateConCargoShipCargoAtt(mListener.getConCargoShipCargoAttempted()+1);
                updateCargoShipStrings();
                break;
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

        void updateConLevelThreeCargo(boolean value);
        void updateConLevelThreeHatch(boolean value);

        void updateConLevelTwoCargo(boolean value);
        void updateConLevelTwoHatch(boolean value);

        void updateConLevelOneCargo(boolean value);
        void updateConLevelOneHatch(boolean value);

        void updateConRsMisses(int value);

        void updateConCargoShipHatchMake(int value);
        void updateConCargoShipHatchAtt(int value);

        void updateConCargoShipCargoMake(int value);
        void updateConCargoShipCargoAtt(int value);

        int getConCargoShipHatchMake();
        int getConCargoShipHatchAttempted();
        int getConCargoShipCargoMake();
        int getConCargoShipCargoAttempted();

    }
}
