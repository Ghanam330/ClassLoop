package com.example.coderamg.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.coderamg.Adapter.PDFAdapter;
import com.example.coderamg.Listener.ItemClickListener;
import com.example.coderamg.Model.PDFModel;
import com.example.coderamg.R;

import java.util.ArrayList;
import java.util.List;


public class BookFragment extends Fragment {

    RecyclerView recyclerView;
    public static List<PDFModel> list;
    Fragment fragment;


    public BookFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_book, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.Rv);

        list = new ArrayList<>();
        list.add(new PDFModel("PDF One", "https://drive.google.com/file/d/12aFJwpjTEigHDP67b3_cE0ey6gMUUp7Y/view"));
        list.add(new PDFModel("PDF Two", "https://firebasestorage.googleapis.com/v0/b/intro-slider-82f6f.appspot.com/o/uploads%2F1609974053307.pdf?alt=media&token=6aaefaf6-716d-44df-a34c-87ff0a376979"));
        list.add(new PDFModel("PDF Three", "https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf"));
        list.add(new PDFModel("PDF Four", "https://firebasestorage.googleapis.com/v0/b/codera-m-g.appspot.com/o/Book%2F__Flutter.pdf?alt=media&token=6b3359cc-ff2c-49b9-a1b7-153be9e64a06"));
        list.add(new PDFModel("PDF Five", "gs://codera-m-g.appspot.com/Book/__Flutter.pdf"));
        list.add(new PDFModel("PDF Six", "https://www.cs.cmu.edu/afs/cs.cmu.edu/user/gchen/www/download/java/LearnJava.pdf"));
        list.add(new PDFModel("PDF Seven", "https://www.cs.cmu.edu/afs/cs.cmu.edu/user/gchen/www/download/java/LearnJava.pdf"));
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        ItemClickListener itemClickListener = new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                fragment = new Pdf_Fragment();
              //  fragment=new BlankPDFView();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.add(R.id.fragment_container1, fragment);
                transaction.addToBackStack(null);
                transaction.commit();

                Bundle bundle = new Bundle();
                bundle.putString("url", list.get(position).getPdfUrl());
                bundle.putInt("position",position);

                fragment.setArguments(bundle);
            }
        };
        PDFAdapter adapter = new PDFAdapter(list,getContext(),itemClickListener);
        recyclerView.setAdapter(adapter);
    }
}