# CRUDFirebase
```
package com.mycompany.firebase;


/**
 *
 * @author Juan David Patarroyo
 */
public class FireBase {
    public static void main(String[] args) {
        Conexion.conectarBase();
        RealTimeDataBase.LeerDatos();
        RealTimeDataBase.AgregarDatos();
        RealTimeDataBase.EliminarDatos();
        RealTimeDataBase.UpdateDatos();
        RealTimeDataBase.LeerDatoFinal();
       
    }
}
```


User.java
```
package com.mycompany.firebase;


/**
 *
 * @author Juan David Patarroyo
 */
public class User {
    public String name;
    public String birthDay;
    public String nickname;
    public User(String birthDay, String nickname){
        this.birthDay = birthDay;
        this.nickname = nickname;
    }
    public User(String name, String birthDay, String nickname){
        this.name = name;
        this.birthDay = birthDay;
        this.nickname = nickname;
    }
}
```



RealTimeDataBase.java
```
package com.mycompany.firebase;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Juan David Patarroyo
 */
public class RealTimeDataBase {


    static Scanner input = new Scanner(System.in);


    static public void AgregarDatos() {
        try {
            DatabaseReference ref = Conexion.fbDataBase.getReference("server/saving-data");
            DatabaseReference studensRef = ref.child("estudiantes");
            Map<String, User> studens = new HashMap<>();
            studens.put(
                    "80737015", new User("Cesar David Patarroyo", "March 23, 1983", "Salamandros"));
            studens.put(
                    "1034282941", new User("Juan David Patarroyo", "January 6, 2006", "Osumep"));
            studens.put(
                    "1034282942", new User("Astrid Sanchez", "January 6, 2006", "Osumep"));
            studensRef.setValueAsync(studens);
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(RealTimeDataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Estudiantes registrados correctamente");
    }


    public static void EliminarDatos() {
        try {
            final DatabaseReference dataBase = Conexion.fbDataBase.getReference("server/saving-data");
            dataBase.child("estudiantes/80737015").removeValueAsync();
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(RealTimeDataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Registro estudiante eliminado");


    }


    public static void UpdateDatos() {
        try {
            final DatabaseReference dataBase = Conexion.fbDataBase.getReference("server/saving-data");
            Map<String, Object> userUpdate = new HashMap<>();
            userUpdate.put("estudiantes/1034282941/name", "Cesar David");
            dataBase.updateChildrenAsync(userUpdate);
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(RealTimeDataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Registro Estudiante Actualizado");
    }


    static public void LeerDatos() {
        try {
            DatabaseReference ref = Conexion.fbDataBase.getReference("server/saving-data/estudiantes");
            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Object estudiante = dataSnapshot.getValue();
                    System.out.println("Lectura de estudiante: ");
                    System.out.println(estudiante);
                }


                @Override
                public void onCancelled(DatabaseError databaseError) {
                    System.out.println("The read failed: " + databaseError.getCode());
                }
            });
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(RealTimeDataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Inicia lectura de estudiantes");
    }


    static public void LeerDatoFinal() {
        try {
            DatabaseReference ref = Conexion.fbDataBase.getReference("server/saving-data/estudiantes");
            ref.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Object estudiante = dataSnapshot.getValue();
                    System.out.println("Lectura de estudiantes Final: ");
                    System.out.println(estudiante);
                }


                @Override
                public void onCancelled(DatabaseError databaseError) {
                    // ...
                }
            });
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(RealTimeDataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Lectura de dato final");
    }
}
```


