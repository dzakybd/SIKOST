package id.ac.its.sikost.fragment;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.ac.its.sikost.R;
import id.ac.its.sikost.adapter.KamarAdapter;
import id.ac.its.sikost.model.Kamar;
import id.ac.its.sikost.model.KamarSingleton;


public class KamarListFragment extends Fragment {

    @BindView(R.id.rv_kamar)
    RecyclerView rvKamar;
    @BindView(R.id.fab_add_kamar)
    FloatingActionButton fabAddKamar;

    List<Kamar> kamars;

    public KamarListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_kamar_list, container, false);
        ButterKnife.bind(this, view);

        kamars = KamarSingleton.getInstance().getKamars();

        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        rvKamar.setLayoutManager(llm);
        rvKamar.setHasFixedSize(true);

        KamarAdapter adapter = new KamarAdapter(getContext(), kamars);
        rvKamar.setAdapter(adapter);

        fabAddKamar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KamarAddDialogFragment dialogFragment = new KamarAddDialogFragment();
                dialogFragment.show(getActivity().getSupportFragmentManager(), "add");
            }
        });

        return view;
    }


}
