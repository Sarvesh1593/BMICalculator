package com.mack.fasttrack.overweightui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.mack.fasttrack.R
import com.mack.fasttrack.databinding.ActivityOverChoiceBinding
import com.mack.fasttrack.databinding.ActivityOverExerciseBinding

class OverExerciseActivity : AppCompatActivity() {
    private lateinit var binding : ActivityOverExerciseBinding
    private lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOverExerciseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recyclerView = binding.rcView
        recyclerView.layoutManager = LinearLayoutManager(this)



        val storageRef = FirebaseStorage.getInstance().reference
        storageRef.child("overweight").listAll()
            .addOnSuccessListener { listResult ->
                val imageList = ArrayList<ImageModel>()
                for (item in listResult.items) {
                    item.downloadUrl.addOnSuccessListener { uri ->
                        val imageUrl = uri.toString()
                        imageList.add(ImageModel(imageUrl))
                        val adapter = ImageAdapter(this, imageList)
                        recyclerView.adapter = adapter
                    }
                }
            }
            .addOnFailureListener { e ->
                // Handle errors
            }

    }
}