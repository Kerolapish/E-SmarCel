package com.example.e_smarcel;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class MainAdapter extends FirebaseRecyclerAdapter<MainModel, MainAdapter.myViewHolder> {

    private DatePickerDialog datePickerDialog;

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public MainAdapter(@NonNull FirebaseRecyclerOptions<MainModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull MainModel model) {
        //holder to display data in recycle view
        holder.trackNum.setText(model.getTrackNum());
        holder.name.setText(model.getName());
        holder.phoneNum.setText(model.getNumPhone());
        holder.parcelStatus.setText(model.getParcelStatus());
        holder.dateClaimed.setText(model.getDateClaimed());

        //function for button edit
        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.trackNum.getContext())
                        .setContentHolder(new ViewHolder(R.layout.update_popup))
                        .setExpanded(true, 1100)
                        .create();

                //to show update_popup.xml
                dialogPlus.show();
                View view = dialogPlus.getHolderView();

                //declare and define variable in recycler_view.xml for update_popup.xml
                TextView parcelStatus = view.findViewById(R.id.txtParcelStatus);
                TextView dateClaimed = view.findViewById(R.id.txtDateClaimed);
                Button btnUpdate = view.findViewById(R.id.btnUpdate);

                //to show data on recycleView in editText update_popup.xml
                parcelStatus.setText(model.getParcelStatus());
                dateClaimed.setText(model.getDateClaimed());
                dialogPlus.show();

                //create function for button update
                btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Map<String, Object> map = new HashMap<>();
                        map.put("parcelStatus", parcelStatus.getText().toString());
                        map.put("dateClaimed", dateClaimed.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child("Recipient")
                                .child(getRef(position).getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(holder.trackNum.getContext(), "Updated Successfully", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(holder.trackNum.getContext(), "Updated Unsuccessfully", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                });
                    }
                });
            }
        });

        //function for button delete
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //create alert dialog for delete
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.trackNum.getContext());
                builder.setTitle("Are You Sure To Delete?");
                builder.setMessage("Delete data can't be undo!");

                //if user click "delete"
                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseDatabase.getInstance().getReference().child("Recipient")
                                .child(getRef(position).getKey()).removeValue();
                    }
                });

                //if user click "cancel"
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(holder.trackNum.getContext(), "Cancelled...", Toast.LENGTH_SHORT).show();
                    }
                });
                //to show alert dialog
                builder.show();
            }
        });
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyler_view, parent, false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{

        //declaration variable for textView
        TextView trackNum, name, phoneNum, parcelStatus, dateClaimed;

        //declaration variable for button
        Button btnEdit, btnDelete;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            //define variable for textView
            trackNum = (TextView) itemView.findViewById(R.id.txtTrackNum);
            name = (TextView) itemView.findViewById(R.id.txtName);
            phoneNum = (TextView) itemView.findViewById(R.id.txtPhone);
            parcelStatus = (TextView) itemView.findViewById(R.id.txtParcelStatus);
            dateClaimed = (TextView) itemView.findViewById(R.id.txtDate);

            //define variable for button
            btnEdit = (Button) itemView.findViewById(R.id.btnEdit);
            btnDelete = (Button) itemView.findViewById(R.id.btnDelete);
        }
    }
}