package com.simplelifestudio.contactos1.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.simplelifestudio.contactos1.Model.User;
import com.simplelifestudio.contactos1.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class Adapter extends BaseAdapter {


    private Context context;
    private int cell;
    private ArrayList<User> db;

    public Adapter(Context context, int cell, ArrayList<User> db) {
        this.context = context;
        this.cell = cell;
        this.db = db;
    }

    @Override
    public int getCount() {
        return db.size();
    }

    @Override
    public Object getItem(int i) {
        return db.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View v = view;

        LayoutInflater inflater =  LayoutInflater.from(context);

        v = inflater.inflate(cell, null);


        String nombre  = db.get(i).getNombre();
        String apellido = db.get(i).getApellido();
        String telf = db.get(i).getNumTelf();
        String fullName = nombre + " "+ apellido;

        TextView nombreTV = v.findViewById(R.id.cellNombreTV);
        TextView telfTV = v.findViewById(R.id.cellTelfTV);
        CircleImageView img = v.findViewById(R.id.cellImgIV);

        nombreTV.setText(fullName);
        telfTV.setText(telf);
        img.setImageResource(R.drawable.placeholderhead);

        return v;
    }
}
