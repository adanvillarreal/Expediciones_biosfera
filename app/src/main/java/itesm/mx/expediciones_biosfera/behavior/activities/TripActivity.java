package itesm.mx.expediciones_biosfera.behavior.activities;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.Text;

import java.sql.Date;

import itesm.mx.expediciones_biosfera.R;
import itesm.mx.expediciones_biosfera.entities.models.Destination;
import itesm.mx.expediciones_biosfera.entities.models.Trip;

public class TripActivity extends AppCompatActivity {
    FirebaseFirestore db;
    TextView tvNombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip);
        tvNombre = findViewById(R.id.tv_description);
        Destination destination = new Destination("Parras", "Coahuila", "Parras", 25.442905, -102.176722, "Pueblo magico", null);
//        Trip trip = new Trip("Titulo", Date.valueOf("2015-15-01"), 20, 500.13, 3, destination, null);
        db = FirebaseFirestore.getInstance();

       // getActionBar().setTitle(trip.getTitle());
        DocumentReference reference = db.collection("trips").document("53BlXS9FMqdp7c2QHxNn");
        reference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    DocumentSnapshot doc = task.getResult();
                    StringBuilder fields = new StringBuilder("");
                    tvNombre.setText(doc.get("title").toString());
                    System.out.println(doc.get("title"));
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                System.out.println("FALIURE");
            }
        });

    }

    public void read(){

    }
}
