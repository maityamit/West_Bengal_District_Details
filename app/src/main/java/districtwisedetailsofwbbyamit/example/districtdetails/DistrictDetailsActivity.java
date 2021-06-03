package districtwisedetailsofwbbyamit.example.districtdetails;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import pl.droidsonroids.gif.GifImageView;

public class DistrictDetailsActivity extends AppCompatActivity {



    private ImageView districtimagr;
    private DatabaseReference RootRef,TouristRef,GalaryRef;
    private TextView district_name,div,subdiv,block,ps,hq,lok,vidhan,area,popu,popu_den,litercy,sex_rat,mojor_hg,major_ct,river,hspitals;
    private RecyclerView tourist_rl,galary_rl;
    private GifImageView gifImageView;
    private LinearLayout linearLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_district_details);



        String districtposition = getIntent().getExtras().get("DISTRICT").toString();


        tourist_rl = findViewById(R.id.TouristRecyclerView);
        galary_rl = findViewById(R.id.Galary_RecyclerView);

        tourist_rl.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        galary_rl.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));


        RootRef= FirebaseDatabase.getInstance ().getReference ();

        GalaryRef = FirebaseDatabase.getInstance ().getReference ().child("DistrictList").child(districtposition).child("GALLARY");
        TouristRef = FirebaseDatabase.getInstance ().getReference ().child("DistrictList").child(districtposition).child("TOURISM");


        districtimagr = findViewById(R.id.dist_imagr);
        linearLayout = findViewById(R.id.district_details_layout_unload);
        gifImageView = findViewById(R.id.district_details_gif_unload);
        district_name = findViewById(R.id.district_details_name);
        div=findViewById(R.id.district_division);
        subdiv=findViewById(R.id.district_sub_division);
        block=findViewById(R.id.district_block);
        ps=findViewById(R.id.district_police);
        hq=findViewById(R.id.district_hq);
        lok=findViewById(R.id.district_lok);
        vidhan=findViewById(R.id.district_vidhan);

        area=findViewById(R.id.district_area);
        popu=findViewById(R.id.district_popu);
        popu_den=findViewById(R.id.district_popu_den);
        litercy=findViewById(R.id.district_litercy);
        sex_rat=findViewById(R.id.district_sex_rat);
        mojor_hg=findViewById(R.id.district_majh);
        major_ct=findViewById(R.id.district_major_city);
        river=findViewById(R.id.district_river);
        hspitals=findViewById(R.id.district_hospital);



        RetriveDetails(districtposition);

    }

    private void RetriveDetails(String districtposition) {

        gifImageView.setVisibility(View.VISIBLE);
        linearLayout.setVisibility(View.GONE);

        RootRef.child("DistrictList").child(districtposition).addValueEventListener ( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                String distimagestr = dataSnapshot.child ( "DISTIMAGE" ).getValue ().toString ();
                String distnamestr = dataSnapshot.child ( "DISTNAME" ).getValue ().toString ();
                String stringhq = dataSnapshot.child ( "HEADQT" ).getValue ().toString ();
                String stringps = dataSnapshot.child ( "POLICESTATION" ).getValue ().toString ();
                String stringlok = dataSnapshot.child ( "LOKSOBHA" ).getValue ().toString ();
                String stringvidhan = dataSnapshot.child ( "VIDHANSABHA" ).getValue ().toString ();
                String stringdiv = dataSnapshot.child ( "DIVISION" ).getValue ().toString ();
                String stringsubdiv = dataSnapshot.child ( "SUBDIV" ).getValue ().toString ();
                String stringblock = dataSnapshot.child ( "BLOCK" ).getValue ().toString ();
                String stringarea = dataSnapshot.child ( "AREA" ).getValue ().toString ();
                String stringpopu = dataSnapshot.child ( "POPULATION" ).getValue ().toString ();
                String stringpopuden = dataSnapshot.child ( "DENSITY" ).getValue ().toString ();
                String stringlitercy = dataSnapshot.child ( "LITERACY" ).getValue ().toString ();
                String stringsexrat = dataSnapshot.child ( "SEXRATIO" ).getValue ().toString ();
                String stringmojorhigh = dataSnapshot.child ( "MAJORHW" ).getValue ().toString ();
                String stringmajorcity = dataSnapshot.child ( "MAJORCITY" ).getValue ().toString ();
                String stringriver = dataSnapshot.child ( "RIVER" ).getValue ().toString ();
                String stringhospitals = dataSnapshot.child ( "HOSPITALS" ).getValue ().toString ();





                Picasso.get().load(distimagestr).placeholder(R.drawable.district_loading).error(R.drawable.district_loading).into(districtimagr);
                district_name.setText(distnamestr);
                div.setText(stringdiv);
                subdiv.setText(stringsubdiv);
                block.setText(stringblock);
                ps.setText(stringps);
                hq.setText(stringhq);
                lok.setText(stringlok);
                vidhan.setText(stringvidhan);
                area.setText(stringarea);
                popu.setText(stringpopu);
                popu_den.setText(stringpopuden);
                litercy.setText(stringlitercy);
                sex_rat.setText(stringsexrat);
                mojor_hg.setText(stringmojorhigh);
                major_ct.setText(stringmajorcity);
                river.setText(stringriver);
                hspitals.setText(stringhospitals);

                gifImageView.setVisibility(View.GONE);
                linearLayout.setVisibility(View.VISIBLE);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(DistrictDetailsActivity.this, "Error + ", Toast.LENGTH_SHORT).show();
            }
        } );


    }






    @Override
    public void onStart() {
        super.onStart ();




        FirebaseRecyclerOptions<TouristModel> options =
                new FirebaseRecyclerOptions.Builder<TouristModel> ()
                        .setQuery ( TouristRef,TouristModel.class )
                        .build ();

        FirebaseRecyclerOptions<GallaryModel> options2 =
                new FirebaseRecyclerOptions.Builder<GallaryModel> ()
                        .setQuery ( GalaryRef,GallaryModel.class )
                        .build ();


        FirebaseRecyclerAdapter<TouristModel, StudentViewHolderTourist> adapter =
                new FirebaseRecyclerAdapter<TouristModel, StudentViewHolderTourist> (options) {
                    @Override
                    protected void onBindViewHolder(@NonNull final StudentViewHolderTourist holder, final int position, @NonNull final TouristModel model) {


                        holder._name.setText ( model.getName() );


                        holder._description.setText(model.getDetails());

                        Picasso.get ().load ( model.getImage() ).placeholder ( R.drawable.image_placeholder ).error ( R.drawable.image_placeholder ).into ( holder._image);





                    }

                    @NonNull
                    @Override
                    public StudentViewHolderTourist onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
                        View view  = LayoutInflater.from ( viewGroup.getContext () ).inflate ( R.layout.tourist_layout,viewGroup,false );
                        StudentViewHolderTourist viewHolder  = new StudentViewHolderTourist(  view);
                        return viewHolder;

                    }
                };
        tourist_rl.setAdapter ( adapter );
        adapter.startListening ();


        FirebaseRecyclerAdapter<GallaryModel, StudentViewHolderPhoto> adapter2 =
                new FirebaseRecyclerAdapter<GallaryModel, StudentViewHolderPhoto> (options2) {
                    @Override
                    protected void onBindViewHolder(@NonNull final StudentViewHolderPhoto holder, final int position, @NonNull final GallaryModel model) {



                        Picasso.get ().load ( model.getImage() ).placeholder ( R.drawable.image_placeholder ).error ( R.drawable.image_placeholder ).into ( holder.image);





                    }

                    @NonNull
                    @Override
                    public StudentViewHolderPhoto onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
                        View view  = LayoutInflater.from ( viewGroup.getContext () ).inflate ( R.layout.gallary_layout,viewGroup,false );
                        StudentViewHolderPhoto viewHolder  = new StudentViewHolderPhoto(  view);
                        return viewHolder;

                    }
                };
        galary_rl.setAdapter ( adapter2 );
        adapter2.startListening ();




    }



    public static class StudentViewHolderPhoto extends  RecyclerView.ViewHolder
    {

        ImageView image;
        public StudentViewHolderPhoto(@NonNull View itemView) {
            super ( itemView );
            image = itemView.findViewById ( R.id.galary_image);

        }
    }


    public static class StudentViewHolderTourist extends  RecyclerView.ViewHolder
    {

        TextView _name,_description;
        ImageView _image;
        public StudentViewHolderTourist(@NonNull View itemView) {
            super ( itemView );
            _name = itemView.findViewById ( R.id.tourist_text);
            _description= itemView.findViewById ( R.id.tourist_details);
            _image = itemView.findViewById ( R.id.tourist_image);

        }
    }



}