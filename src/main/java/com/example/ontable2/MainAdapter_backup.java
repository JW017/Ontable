//package com.example.ontable2;
//
//import android.annotation.SuppressLint;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.firebase.ui.database.FirebaseRecyclerAdapter;
//import com.firebase.ui.database.FirebaseRecyclerOptions;
//
//public class MainAdapter extends FirebaseRecyclerAdapter<MainModel, MainAdapter.myViewHolder> {
//
//    /**
//     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
//     * {@link FirebaseRecyclerOptions} for configuration options.
//     *
//     * @param options
//     */
//    public MainAdapter(@NonNull FirebaseRecyclerOptions<MainModel> options) {
//        super(options);
//    }
//
//
//    @Override
//    protected void onBindViewHolder(@NonNull myViewHolder holder, @SuppressLint("RecyclerView") int position, @NonNull MainModel model) {
////        holder.id.setText(model.getId());
//        holder.details.setText(String.valueOf(getItem(position).getDetails()));
//        Log.d("DEBUG_TAG", "Details: " + model.getDetails());
//
//
////        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
////                Map<String, Object> map = new HashMap<>();
////                map.put("details", holder.details.getText().toString());
////
////                FirebaseDatabase.getInstance().getReference().child("notes")
////                        .child(getRef(position).getKey()).updateChildren(map)
////                        .addOnSuccessListener(new OnSuccessListener<Void>() {
////                            @Override
////                            public void onSuccess(Void unused) {
////                                Toast.makeText(holder.id.getContext(), "Data Updated Successfully.", Toast.LENGTH_SHORT).show();
////                            }
////                        })
////                        .addOnFailureListener(new OnFailureListener() {
////                            @Override
////                            public void onFailure(Exception e) {
////                                Toast.makeText(holder.id.getContext(), "Error While Updating.", Toast.LENGTH_SHORT).show();
////                            }
////                        });
////            }
////        });
////
////        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
////                AlertDialog.Builder builder = new AlertDialog.Builder(holder.id.getContext());
////                builder.setTitle("Are you sure?");
////                builder.setMessage("Deleted data can't be undo.");
////
////                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
////                    @Override
////                    public void onClick(DialogInterface dialogInterface, int i) {
////                        FirebaseDatabase.getInstance().getReference().child("notes")
////                                .child(getRef(position).getKey()).removeValue();
////                    }
////                });
////
////                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
////                    @Override
////                    public void onClick(DialogInterface dialogInterface, int i) {
////                        Toast.makeText(holder.id.getContext(), "Cancelled.", Toast.LENGTH_SHORT).show();
////                    }
////                });
////                builder.show();
////            }
////        });
//    }
//
//    @NonNull
//    @Override
//    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.memo_details,parent,false);
//        return new myViewHolder(view);
//    }
//
//    static class myViewHolder extends RecyclerView.ViewHolder{
//
//        TextView id, details;
//        Button btnDelete, btnEdit;
//
//        public myViewHolder(@NonNull View itemView) {
//            super(itemView);
//
//            //id = itemView.findViewById(R.id.memoid);
//            details = itemView.findViewById(R.id.memodetails);
//
//            btnDelete = itemView.findViewById(R.id.btnDelete);
//            btnEdit = itemView.findViewById(R.id.btnEdit);
//        }
//    }
//}
