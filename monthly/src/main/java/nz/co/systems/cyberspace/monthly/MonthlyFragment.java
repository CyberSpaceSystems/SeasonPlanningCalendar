package nz.co.systems.cyberspace.monthly;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MonthlyFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MonthlyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MonthlyFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private RecyclerView recList;
    private static CharSequence mTitle = "Testing";

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MonthlyFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MonthlyFragment newInstance(String param1, String param2) {
        MonthlyFragment fragment = new MonthlyFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public MonthlyFragment() {
        // Required empty public constructor
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

        View monthlyView =  inflater.inflate(R.layout.fragment_monthly, container, false);

        recList = (RecyclerView) monthlyView.findViewById(R.id.week_in_a_month_cardList);
        recList.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(llm);
        MonthlyAdapter monthlyAdapter = new MonthlyAdapter(createList(4));
        recList.setAdapter(monthlyAdapter);


        Toolbar toolbar = (Toolbar) monthlyView.findViewById(R.id.card_toolbar);
        toolbar.setTitle("Testing");

        if (toolbar != null) {
            toolbar.inflateMenu(R.menu.card_toolbar);
            toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {
                    int i = menuItem.getItemId();
                    if (i == R.id.action_goal) {

                        return true;
                    }

                    return false;


                }
            });

        }




        return monthlyView;
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction("hew ");
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }





    private List<MonthlyInfo> createList(int size) {

        List<MonthlyInfo> result = new ArrayList<MonthlyInfo>();
        for (int i=1; i <= size; i++) {
            MonthlyInfo ci = new MonthlyInfo();

            ci.mcycle = MonthlyInfo.CYCLE_PREFIX;
            ci.volume = MonthlyInfo.VOLUME_PREFIX;
            ci.intensity = MonthlyInfo.INTENSITY_PREFIX;

            result.add(ci);

        }

        return result;
    }







    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(String uri);
    }

}
