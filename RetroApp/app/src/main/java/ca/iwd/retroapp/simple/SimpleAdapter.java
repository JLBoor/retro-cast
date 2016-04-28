package ca.iwd.retroapp.simple;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ca.iwd.retroapp.R;
import retro.iwd.ca.retroApi.model.Retro;


public class SimpleAdapter extends RecyclerView.Adapter<SimpleAdapter.RetroVH> {

    private List<Retro> retroList = new ArrayList<>();

    @Override
    public RetroVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_retro_list_item, parent, false);
        RetroVH vh = new RetroVH(v);
        return vh;
    }

    @Override
    public int getItemCount() {
        return retroList.size();
    }

    @Override
    public void onBindViewHolder(RetroVH holder, int position) {

        Retro retro = retroList.get(position);

        holder.itemView.setTag(retro.getProjectName());
        holder.projectName.setText(retro.getProjectName());
        holder.room.setText(retro.getRoom());
    }

    public void setRetroList(List<Retro> retroList) {
        this.retroList = retroList;
        notifyDataSetChanged();
    }

    public class RetroVH extends RecyclerView.ViewHolder {

        private final TextView room;
        private final TextView projectName;

        public RetroVH(View itemView) {
            super(itemView);
            projectName = (TextView) itemView.findViewById(R.id.projectName);
            room = (TextView) itemView.findViewById(R.id.room);
        }
    }
}
