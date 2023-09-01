package com.example.mayank;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteHolder> {

    private List<Note> notes=new ArrayList<>();

//    public NoteAdapter(List<Note> notes) {
//        this.notes = notes;
//    }

    @NonNull
    @Override
    public NoteAdapter.NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item,parent,false);
        return new NoteHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteAdapter.NoteHolder holder, int position)
    {
        //here we have use gettitle and getdesc becz we have not crete the
        //recycler java class in which we define what all we have include in our layout
        // just as earlier we have done,, so byt this method it will automayically get the
        // title and desc
        holder.title.setText(notes.get(position).getTitle());
        holder.desc.setText(notes.get(position).getDescription());

    }

    @Override
    public int getItemCount() {

        return notes.size();
    }
    // we have create thsi method to see the live change in the data

    public void setNoteList(List<Note> notes){

        this.notes=notes;
        notifyDataSetChanged();

    }

    public class NoteHolder extends RecyclerView.ViewHolder{

        TextView title;
        TextView desc;
        public NoteHolder(@NonNull View itemView) {
            super(itemView);

            title=itemView.findViewById(R.id.title);
            desc=itemView.findViewById(R.id.desc);

        }
    }
}
