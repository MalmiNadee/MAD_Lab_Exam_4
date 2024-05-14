package com.example.taskbuddy

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.taskbuddy.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var db: TaskDbHelper
    private lateinit var taskAdapter: TasksAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = TaskDbHelper(this)
        //setup adapter
        taskAdapter = TasksAdapter(db.getAllTasks(), this)

        //setup recycler view
        binding.tasksRecycler.layoutManager = LinearLayoutManager(this)
        binding.tasksRecycler.adapter =taskAdapter

        binding.addButton.setOnClickListener{
            val intent = Intent(this,AddTaskActivity::class.java)
            startActivity(intent)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onResume() { //automatically refresh data
        super.onResume()
        taskAdapter.refreshData((db.getAllTasks()))
    }























}