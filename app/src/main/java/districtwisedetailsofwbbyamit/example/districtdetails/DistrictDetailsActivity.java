package districtwisedetailsofwbbyamit.example.districtdetails;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import pl.droidsonroids.gif.GifImageView;

public class DistrictDetailsActivity extends AppCompatActivity {



    private ImageView districtimagr;
    private String districtposition;
    private DatabaseReference RootRef;
    private TextView district_name,div,subdiv,block,ps,hq,lok,vidhan;
    private GifImageView gifImageView;
    private LinearLayout linearLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_district_details);

        RootRef= FirebaseDatabase.getInstance ().getReference ();
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
        districtposition = getIntent().getExtras().get("DISTRICT").toString();


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




                Picasso.get().load(distimagestr).placeholder(R.drawable.district_loading).error(R.drawable.district_loading).into(districtimagr);
                district_name.setText(distnamestr);
                div.setText(stringdiv);
                subdiv.setText(stringsubdiv);
                block.setText(stringblock);
                ps.setText(stringps);
                hq.setText(stringhq);
                lok.setText(stringlok);
                vidhan.setText(stringvidhan);

                gifImageView.setVisibility(View.GONE);
                linearLayout.setVisibility(View.VISIBLE);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(DistrictDetailsActivity.this, "Error + ", Toast.LENGTH_SHORT).show();
            }
        } );


    }
}