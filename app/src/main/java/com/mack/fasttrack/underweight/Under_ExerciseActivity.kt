package com.mack.fasttrack.underweight

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.storage.FirebaseStorage
import com.mack.fasttrack.R
import com.mack.fasttrack.databinding.ActivityNormalExerciseBinding
import com.mack.fasttrack.databinding.ActivityUnderExerciseBinding
import com.mack.fasttrack.overweightui.ImageAdapter
import com.mack.fasttrack.overweightui.ImageModel

class Under_ExerciseActivity : AppCompatActivity() {
    private lateinit var binding : ActivityUnderExerciseBinding
    private lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUnderExerciseBinding.inflate(layoutInflater)
        setContentView(binding.root)
        recyclerView = binding.underRv
        recyclerView.layoutManager = LinearLayoutManager(this)

        val storageRef = FirebaseStorage.getInstance().reference
        storageRef.child("underweight").listAll()
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