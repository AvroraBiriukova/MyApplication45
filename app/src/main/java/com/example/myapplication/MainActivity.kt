package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {


    private val list = mutableListOf<Todo>()
    private lateinit var adapter: RecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val dbHelper = DBHelper(this)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        // заполнить list из базы
        val allData = dbHelper.getAll()
        list.addAll(allData)

        adapter = RecyclerAdapter(list) {
            val id = list[it].id
            val intent = Intent(this@MainActivity, Card_contact::class.java)
            intent.putExtra("Id", id)
            startActivity(intent)
        }

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        val buttonAdd = findViewById<Button>(R.id.button)
        buttonAdd.setOnClickListener {
            val intent = Intent(this@MainActivity, Addendum_contact::class.java)
            startActivity(intent)

        }

    }
}


