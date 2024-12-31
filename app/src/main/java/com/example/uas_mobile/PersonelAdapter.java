package com.example.uas_mobile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class PersonelAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Personel> personels;

    public void setPersonel(ArrayList<Personel> personels) {
        this.personels = personels;
    }

    public ArrayList<Personel> getUsers() {
        return personels;
    }

    public PersonelAdapter(Context context) {
        this.context = context;
        personels = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return personels.size();
    }

    @Override
    public Object getItem(int i) {
        return personels.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.fragment_personel , viewGroup, false);
        }

        ViewHolder viewHolder = new ViewHolder(view);
        Personel personel = (Personel) getItem(i);
        viewHolder.bind(personel);

        return view;
    }

    private class ViewHolder {
        private TextView txtName;
        private TextView txtDescription;
        private ImageView imgPhoto;

        ViewHolder(View view) {
            txtName = view.findViewById(R.id.txt_name);
            txtDescription = view.findViewById(R.id.txt_type);
            imgPhoto = view.findViewById(R.id.img_photo);
        }

        void bind(Personel personel) {
            txtName.setText(personel.getName());
            txtDescription.setText(personel.getType());
            Glide.with(context)
                    .load(personel.getPhoto())
                    .into(imgPhoto);
        }
    }
}
