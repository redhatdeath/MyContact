package ru.shanin.mycontact.presentation.main;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import ru.shanin.mycontact.domain.entity.People;

public class PeopleDiffCallback extends DiffUtil.ItemCallback<People> {

    @Override
    public boolean areItemsTheSame(@NonNull People oldPeople, @NonNull People newPeople) {
        return (oldPeople.get_id()) == (newPeople.get_id());
    }

    @Override
    public boolean areContentsTheSame(@NonNull People oldPeople, @NonNull People newPeople) {
        //return oldPeople.equals(newPeople);
        return false;
    }
}
