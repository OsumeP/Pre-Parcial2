package Model;

import ModelView.Calcular;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.List;

/**
 *
 * @author David Patarroyo
 */
public class RealTimeDataBase {

    static Scanner input = new Scanner(System.in);
    
    static public Firestore fbStore;
    static public FirebaseDatabase fbDataBase;
    static public List<String> lecturas = new ArrayList<>();

    static public void conectarBase() {
        try {
            FileInputStream credential = new FileInputStream("parcial.json");
            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(credential))
                    .setDatabaseUrl("https://pre-parcial-9d886-default-rtdb.firebaseio.com/")
                    .build();

            FirebaseApp fbApp = FirebaseApp.initializeApp(options);
            fbStore = FirestoreClient.getFirestore();
            fbDataBase = FirebaseDatabase.getInstance();
            System.out.println("Se realizó la conexión con la base de datos");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    static public void AgregarDatos(Calcular obj) {
        try {
            DatabaseReference ref = fbDataBase.getReference("server/saving-data/operaciones");
            DatabaseReference childRef = ref.child(String.valueOf((int)obj.valor));
            Map<String, Calcular> operaciones = new HashMap<>();
            operaciones.put(
                    obj.operacion, obj);
            childRef.setValueAsync(operaciones);
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(RealTimeDataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void EliminarDatos() {
        try {
            final DatabaseReference dataBase = fbDataBase.getReference("server/saving-data/operaciones");
            dataBase.removeValueAsync();
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(RealTimeDataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    static public void LeerDatoFinal() {
        try {
            DatabaseReference ref = fbDataBase.getReference("server/saving-data/operaciones");
            ref.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String obj = dataSnapshot.getValue().toString();
                    String lectura2 = obj.substring(1, obj.length() - 2);
                    String[] lectura1 = lectura2.split("}, ");
                    for (String i : lectura1) {
                        String lectura = i.split("\\{")[2];
                        lecturas.add(lectura.split("\\}")[0]);
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    
                }
            });
            Thread.sleep(3000);
        } catch (InterruptedException ex) {
            Logger.getLogger(RealTimeDataBase.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
