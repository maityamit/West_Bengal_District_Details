package districtwisedetailsofwbbyamit.example.districtdetails;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import pl.droidsonroids.gif.GifImageView;

public class HomeActivity extends AppCompatActivity {


    private DatabaseReference RootRef;
    private RecyclerView recyclerView;
    private GifImageView imageView;
    private LinearLayout lin;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        imageView = findViewById(R.id.district_load_image);
        lin = findViewById(R.id.lin_lay_dist);

        RootRef = FirebaseDatabase.getInstance ().getReference ().child("DistrictList");
        recyclerView = findViewById(R.id.district_list_recycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


    @Override
    protected void onStart() {
        super.onStart();


        imageView.setVisibility(View.VISIBLE);



        FirebaseRecyclerOptions<DistrictModel> options =
                new FirebaseRecyclerOptions.Builder<DistrictModel> ()
                        .setQuery(RootRef,DistrictModel.class)
                        .build ();


        FirebaseRecyclerAdapter<DistrictModel, StudentViewHolder2> adapter =
                new FirebaseRecyclerAdapter<DistrictModel, StudentViewHolder2> (options) {
                    @Override
                    protected void onBindViewHolder(@NonNull final StudentViewHolder2 holder, final int position, @NonNull final DistrictModel model) {

                        String stringg = getRef(position).getKey();

                        holder.name.setText ( model.getDISTNAME() );
                        holder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(HomeActivity.this,DistrictDetailsActivity.class);
                                intent.putExtra("DISTRICT",stringg);
                                startActivity(intent);
                            }
                        });




                    }

                    @NonNull
                    @Override
                    public StudentViewHolder2 onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
                        View view  = LayoutInflater.from ( viewGroup.getContext () ).inflate ( R.layout.district_layout,viewGroup,false );
                        StudentViewHolder2 viewHolder  = new StudentViewHolder2(  view);
                        return viewHolder;

                    }


                    @Override
                    public void onDataChanged() {
                        super.onDataChanged();

                        imageView.setVisibility(View.GONE);
                        lin.setVisibility(View.VISIBLE);
                    }
                };
        recyclerView.setAdapter ( adapter );
        adapter.startListening ();






    }


    public static class StudentViewHolder2 extends  RecyclerView.ViewHolder
    {

        TextView name;
        public StudentViewHolder2(@NonNull View itemView) {
            super ( itemView );
            name = itemView.findViewById ( R.id.district_name );

        }
    }
}