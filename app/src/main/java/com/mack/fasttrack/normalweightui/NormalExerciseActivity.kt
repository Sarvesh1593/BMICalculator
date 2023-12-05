package com.mack.fasttrack.normalweightui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.storage.FirebaseStorage
import com.mack.fasttrack.R
import com.mack.fasttrack.databinding.ActivityNormalExerciseBinding
import com.mack.fasttrack.databinding.ActivityOverExerciseBinding
import com.mack.fasttrack.overweightui.ImageAdapter
import com.mack.fasttrack.overweightui.ImageModel

class NormalExerciseActivity : AppCompatActivity() {
    private lateinit var binding : ActivityNormalExerciseBinding
    private lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNormalExerciseBinding.inflate(layoutInflater)
        setContentView(binding.root)
        recyclerView = binding.exerciseNormalRc
        recyclerView.layoutManager = LinearLayoutManager(this)

        val storageRef = FirebaseStorage.getInstance().reference
        storageRef.child("normalweight").listAll()
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