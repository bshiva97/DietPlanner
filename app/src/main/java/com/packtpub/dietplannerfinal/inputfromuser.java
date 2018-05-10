package com.packtpub.dietplannerfinal;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link inputfromuser.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link inputfromuser#newInstance} factory method to
 * create an instance of this fragment.
 */
public class inputfromuser extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    Integer qty;

    ImageButton ib1,ib2,ib3;
    EditText et1;
    AutoCompleteTextView et;
    SessionRecord ss;
    DatabaseHelper db;
    String ab="";
    public static FoodItems fitems[]=new FoodItems[18];
    public static String names[]=new String[18];
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String tag = "name";
    public int i=0;
    Spinner select;
    Button add,submit;
    private ProgressDialog pDialog;
    public boolean internet=false;
    AlertDialog.Builder builder;
    AutoCompleteTextView autoCompleteTextView;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public inputfromuser() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment inputfromuser.
     */
    // TODO: Rename and change types and number of parameters
    public static inputfromuser newInstance(String param1, String param2) {
        inputfromuser fragment = new inputfromuser();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ss=new SessionRecord(getActivity());
        db=new DatabaseHelper(getActivity());
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_inputfromuser, container, false);
        final View mainview= inflater.inflate(R.layout.fragment_inputfromuser, container, false);
        select=(Spinner) mainview.findViewById(R.id.spinner);
        pDialog = new ProgressDialog(mainview.getContext());
        pDialog.setMessage("LOADING DATA.....PLEASE WAIT.");
        select.setPrompt("Select The Quantity You Ate: ");
        i=0;
        ib1=(ImageButton)mainview.findViewById(R.id.imageButton1);
        ib2=(ImageButton)mainview.findViewById(R.id.imageButton2);
        ib3=(ImageButton)mainview.findViewById(R.id.imageButton3);
        et=(AutoCompleteTextView) mainview.findViewById(R.id.autocomp1);
        et1=(EditText)mainview.findViewById(R.id.edittext1);
        select.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(internet) {
                    if (position == 0) {
                        et1.setVisibility(View.VISIBLE);
                        ib1.setVisibility(View.INVISIBLE);
                        ib2.setVisibility(View.INVISIBLE);
                        ib3.setVisibility(View.INVISIBLE);
                    }
                    if (position == 1) {
                        et1.setVisibility(View.INVISIBLE);
                        ib1.setVisibility(View.VISIBLE);
                        ib2.setVisibility(View.VISIBLE);
                        ib3.setVisibility(View.VISIBLE);
                    }
                }
                else
                    pDialog.show();
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        final Drawable highlight=getResources().getDrawable(R.drawable.highlight);
        add=(Button)mainview.findViewById(R.id.add);
        submit=(Button)mainview.findViewById(R.id.submit);
        ib1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                qty=101;
                ab= qty.toString();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    ib1.setBackground(highlight);
                    ib2.setBackground(null);
                    ib3.setBackground(null);
                }
            }
        });

        ib2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                qty=102;
                ab= qty.toString();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    ib2.setBackground(highlight);
                    ib1.setBackground(null);
                    ib3.setBackground(null);
                }
            }
        });

        ib3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                qty=103;
                ab= qty.toString();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    ib3.setBackground(highlight);
                    ib1.setBackground(null);
                    ib2.setBackground(null);
                }
            }
        });


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(internet)
                {
                    String ab1="";
                    ab = et.getText().toString();
                    if(!ab.equals(""))
                        qty=Integer.parseInt(ab);
                    FragmentTransaction ft=getFragmentManager().beginTransaction();
                    ft.replace(R.id.content,new DailyFragment());
                    ft.commit();
                }
                else
                    pDialog.show();  }
        });

        add.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(internet) {
                            String ab1 = "";
                            //ss=new SessionRecord(getContext());
                            ab1 = et.getText().toString();
                            if (!ab1.equals(""))
                                ab = ab1;
                            if (ss.isLoggedIn() && !ab.equals("")) {
                                EatEntry e = new EatEntry(ab, et1.getText().toString(), ss.getUs_id());
                                long rowId = db.addEatInformation(e);
                                if (rowId != -1) {
                                    Toast.makeText(getContext(), "data inserted", Toast.LENGTH_SHORT).show();

                                } else {
                                    Toast.makeText(getContext(), "data not  inserted", Toast.LENGTH_SHORT).show();
                                }

                            } else {
                                Toast.makeText(getContext(), "Please make sure you are logged in", Toast.LENGTH_SHORT).show();

                            }

                            FragmentTransaction ft = getFragmentManager().beginTransaction();
                            ft.replace(R.id.content, new inputfromuser());
                            ft.commit();
                        }
                        else
                            pDialog.show();
                    }
                });
     /*   DatabaseReference rootref = FirebaseDatabase.getInstance().getReferenceFromUrl
                ("https://my-minor-de81d.firebaseio.com/Items");
        final DatabaseReference demoref=rootref.child("Items");
     */
     DatabaseReference rootref=FirebaseDatabase.getInstance().getReference().child("Items");
     rootref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren()) {
                    FoodItems fi = dataSnapshot1.getValue(FoodItems.class);
                    names[i]=fi.name;
                    fitems[i]=fi;
                    i++;
                }
                computelogic();
            }

            @Override
            public  void onCancelled(DatabaseError databaseError) {

            }

        });
        et.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(internet)
                    return;
                else {
                    pDialog.show();
                }
            }
        });
        return mainview;
    }


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
    }
    public void computelogic()
    {
        internet=true;
        pDialog.dismiss();
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this.getContext(),android.R.layout.simple_dropdown_item_1line,names);
        et.setAdapter(arrayAdapter);
        et.showDropDown();
        Log.d("myname",fitems[4].name+" "+fitems[4].calories+" "+fitems[4].carbohydrate+" "+fitems[4].protien+" "+fitems[4].fat);
    }
}
