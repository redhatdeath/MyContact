package ru.shanin.mycontact.presentation.main;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import ru.shanin.mycontact.R;

public class PeopleViewHolder extends RecyclerView.ViewHolder {
    TextView tvFirstName;
    TextView tvSecondName;
    ImageView imPhoto;

    public PeopleViewHolder(View itemView) {
        super(itemView);
        tvFirstName = itemView.findViewById(R.id.tv_fname);
        tvSecondName = itemView.findViewById(R.id.tv_sname);
        imPhoto = itemView.findViewById(R.id.im_photo);
    }
}
