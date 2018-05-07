package hadenmcafee.cidm4385.sp2018.wt.challenge8_hm;import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class CrimeListFragment extends Fragment {
    private RecyclerView mCrimeRecyclerView;
    private CrimeAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_crime_list, container, false);

        mCrimeRecyclerView = (RecyclerView) view
                .findViewById(R.id.crime_recycler_view);
        mCrimeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }

    private void updateUI() {
        CrimeLab crimeLab = CrimeLab.get(getActivity());
        List<Crime> crimes = crimeLab.getCrimes();

        mAdapter = new CrimeAdapter(crimes);
        mCrimeRecyclerView.setAdapter(mAdapter);
    }

    private class CrimeHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        private Crime mCrime;

        private TextView mTitleTextView;
        private TextView mDateTextView;

        public CrimeHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_crime, parent, false));
            itemView.setOnClickListener(this);

            mTitleTextView = (TextView) itemView.findViewById(R.id.crime_title);
            mDateTextView = (TextView) itemView.findViewById(R.id.crime_date);
        }

        public void bind(Crime crime) {
            mCrime = crime;
            mTitleTextView.setText(mCrime.getTitle());
            mDateTextView.setText(mCrime.getDate().toString());
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(getActivity(),
                    mCrime.getTitle() + " clicked!", Toast.LENGTH_SHORT)
                    .show();
        }
    }

    private class MegaCrimeHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        private Crime mCrime;

        private TextView mTitleTextView;
        private TextView mDateTextView;
        private Button mCallButton;

        public MegaCrimeHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_mega_crime, parent, false));
            itemView.setOnClickListener(this);

            mTitleTextView = (TextView) itemView.findViewById(R.id.mega_crime_title);
            mDateTextView = (TextView) itemView.findViewById(R.id.mega_crime_date);
            mCallButton = (Button) itemView.findViewById(R.id.mega_crime_call_button);
        }

        public void bind(Crime crime) {
            mCrime = crime;
            mTitleTextView.setText(mCrime.getTitle());
            mDateTextView.setText(mCrime.getDate().toString());
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(getActivity(),
                    mCrime.getTitle() + " clicked!", Toast.LENGTH_SHORT)
                    .show();
        }
    }

    private class CrimeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
    {

        private List<Crime> mCrimes;

        public CrimeAdapter(List<Crime> crimes)
        {
            mCrimes = crimes;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());

            switch(viewType)
            {
                case 0: return new CrimeHolder(layoutInflater, parent);
                case 1: return new MegaCrimeHolder(layoutInflater, parent);
                default: return new CrimeHolder(layoutInflater, parent);
            }
        }

        @Override
        public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position)
        {
            switch(holder.getItemViewType())
            {
                case 0:
                {
                    CrimeHolder crimeHolder = (CrimeHolder)holder;
                    Crime crime = mCrimes.get(position);
                    crimeHolder.bind(crime);
                    break;
                }
                case 1:
                {
                    MegaCrimeHolder crimeHolder = (MegaCrimeHolder)holder;
                    Crime crime = mCrimes.get(position);
                    crimeHolder.bind(crime);
                    break;
                }
                default:
                {
                    CrimeHolder crimeHolder = (CrimeHolder)holder;
                    Crime crime = mCrimes.get(position);
                    crimeHolder.bind(crime);
                    break;
                }
            }
        }

        @Override
        public int getItemCount()
        {
            return mCrimes.size();
        }

        @Override public int getItemViewType(int position)
        {
            return (mCrimes.get(position).getRequiresPolice()) ? 1 : 0;
        }

    }
}
