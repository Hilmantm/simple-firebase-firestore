package id.example.practice_firebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import id.example.practice_firebase.entity.User

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = Firebase.firestore

        val newUser = User("Hilman", "Taris", 20)

        val hashNewUser = hashMapOf(
            "first" to newUser.firstName,
            "last" to newUser.lastName,
            "age" to newUser.age
        )

        db.collection("users")
            .add(hashNewUser)
            .addOnSuccessListener {
                Toast.makeText(this, "SUCCESS ADD NEW USER TO DATABASE", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                Log.w("MainActivity", "ERROR ADDING DOCUMENT", it)
            }

        db.collection("users")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d("MainActivity", "${document.id} => ${document.data["first"]}")
                }
            }
            .addOnFailureListener { exception ->
                Log.w("MainActivity", "Error getting documents.", exception)
            }

    }

}