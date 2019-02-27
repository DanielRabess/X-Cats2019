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
import android.widget.ImageView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ScoutTab4.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ScoutTab4#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ScoutTab4 extends Fragment {
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

        int tempColor = mListener.getAllianceColor();

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
    }


    //****************************************************
    public void UpdateAllianceColorForAll(int color){
        Log.d("Scout Tab 4 :", "Alliance Color Update");
        if(color == Color.BLUE) {
            //Button Pressed was RED ---> Change to BLUE
            //Update Master Color Object

            //radio1.setButtonTintList(ColorStateList.valueOf(Color.BLUE));
            //radio2.setButtonTintList(ColorStateList.valueOf(Color.BLUE));
            //radio3.setButtonTintList(ColorStateList.valueOf(Color.BLUE));
            //radio4.setButtonTintList(ColorStateList.valueOf(Color.BLUE));
            //radio5.setButtonTintList(ColorStateList.valueOf(Color.BLUE));

            //startingPosition.set

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

            //radio1.setButtonTintList(ColorStateList.valueOf(Color.RED));
            //radio2.setButtonTintList(ColorStateList.valueOf(Color.RED));
            //radio3.setButtonTintList(ColorStateList.valueOf(Color.RED));
            //radio4.setButtonTintList(ColorStateList.valueOf(Color.RED));
            //radio5.setButtonTintList(ColorStateList.valueOf(Color.RED));

            //Toggle Visible of Levels
            blue1.setVisibility(View.INVISIBLE);
            blue2.setVisibility(View.INVISIBLE);
            blue3.setVisibility(View.INVISIBLE);
            red1.setVisibility(View.VISIBLE);
            red2.setVisibility(View.VISIBLE);
            red3.setVisibility(View.VISIBLE);
        }
    }
}
