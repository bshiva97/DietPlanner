package com.packtpub.dietplannerfinal;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link aftlogin.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link aftlogin#newInstance} factory method to
 * create an instance of this fragment.
 */
public class aftlogin extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    ImageButton btn;
    AutoCompleteTextView et;
    String[] sugg={"Male","Female"};
    TextView t;
    SessionRecord ss;
    Spinner s1;
    ArrayAdapter<CharSequence> aa;
    String choice;
    Spinner s2;
    ArrayAdapter<CharSequence> ab;
    String choice1;
    person_dimen pd;
    EditText et1;
    EditText et2;
    EditText et3;
    String weight;
    String height;
    String sex;
    String age;
    double w;
    double h;
    double a;
    double p;
    Button btnsub;
    DatabaseHelper db_dim;
    private static final String TAG="hello";




    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public aftlogin() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment aftlogin.
     */
    // TODO: Rename and change types and number of parameters
    public static aftlogin newInstance(String param1, String param2) {
        aftlogin fragment = new aftlogin();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db_dim=new DatabaseHelper(getActivity());

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView= inflater.inflate(R.layout.fragment_aftlogin, container, false);
        btn=(ImageButton) rootView.findViewById(R.id.logout);
        et=(AutoCompleteTextView)rootView.findViewById(R.id.autoCompleteTextView);//sex
        et1=(EditText)rootView.findViewById(R.id.editText4);//weight
        et2=(EditText)rootView.findViewById(R.id.editText5);//height
        et3=(EditText)rootView.findViewById(R.id.textView11);//age

        ss=new SessionRecord(getContext());
        t=(TextView)rootView.findViewById(R.id.textView6);
        // sugg=getResources().getStringArray(R.array.sex);
        ArrayAdapter<String> s=new ArrayAdapter<String>(getActivity(),android.R.layout.select_dialog_singlechoice,sugg);
        et.setAdapter(s);
        t.setText("Welcome "+ss.getUs_id());
        btnsub=(Button)rootView.findViewById(R.id.button3_sub);
        s1=(Spinner)rootView.findViewById(R.id.spinner2);
        aa=ArrayAdapter.createFromResource(getActivity(),R.array.fit_class,android.R.layout.simple_dropdown_item_1line);
        s1.setAdapter(aa);
        s1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                choice=String.valueOf(parent.getItemAtPosition(position));
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        s2=(Spinner)rootView.findViewById(R.id.spinner3);
        ab=ArrayAdapter.createFromResource(getActivity(),R.array.aim_class,android.R.layout.simple_dropdown_item_1line);
        s2.setAdapter(ab);
        s2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                choice1=String.valueOf(parent.getItemAtPosition(position));
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


  /*      weight=et1.getText().toString();
        height=et2.getText().toString();
        sex=et.getText().toString();
        age=et3.getText().toString();
     //   Log.d(TAG,"weight="+ weight);
Toast.makeText(getContext(),weight+" "+height+" "+sex,Toast.LENGTH_LONG).show();

        if(!this.weight.isEmpty()&&!this.height.isEmpty()&&!this.age.isEmpty())
        {
            w = Double.valueOf(weight);
            h = Double.valueOf(height);
            a = Double.valueOf(age);
        }





        if(sex.equals("Male"))
        {
            this.s=5;
            this.p=10.0*this.w+6.25*this.h-5.0*this.a+this.s;



        }
        else if(sex.equals("Female"))
        {
            this.s=5;
            p=10.0*w+6.25*h-5.0*a+this.s;

        }
*/
//        pd=new person_dimen(ss.getUs_id(),weight,height,sex,age,choice,choice1,Double.toString(p));

        btn.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {

                        ss.setLogggedIn(false);
                        ss.setId("");

                        Intent x=new Intent(getActivity(),front.class);
                        startActivity(x);

                        //startActivity(x);
                    }

                }
        );

        btnsub.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        weight=et1.getText().toString();
                        height=et2.getText().toString();
                        sex=et.getText().toString();
                        age=et3.getText().toString();
                        //   Log.d(TAG,"weight="+ weight);

                        if(!weight.isEmpty()&&!height.isEmpty()&&!age.isEmpty())
                        {
                            w = Double.valueOf(weight);
                            h = Double.valueOf(height);
                            a = Double.valueOf(age);
                        }





                        if(sex.equals("Male"))
                        {
                            double s=5;
                            p=(10.0*w)+(6.25*h)-(5.0*a)+s;



                        }
                        else if(sex.equals("Female"))
                        {
                            double s=-161;
                            p=(10.0*w)+(6.25*h)-(5.0*a)+s;

                        }
                        pd=new person_dimen(ss.getUs_id(),weight,height,sex,age,choice,choice1,Double.toString(p));


                        Toast.makeText(getContext(),ss.getUs_id()+" "+choice1,Toast.LENGTH_SHORT).show();
                        long rowId=db_dim.addDimInformation(pd);
                        FragmentTransaction ft=getFragmentManager().beginTransaction();
                        if(rowId!=-1)
                        {
                            Toast.makeText(getContext(),"data inserted"+rowId,Toast.LENGTH_SHORT).show();
                      //      ft.replace(R.id.content,new aftloginsub());
                            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                            ft.commit();

                        }
                        else
                        {
                            Toast.makeText(getContext(),"data not  inserted",Toast.LENGTH_SHORT).show();
                        }

                        //startActivity(x);
                    }

                }
        );




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
        } /*else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
  */  }

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
}
