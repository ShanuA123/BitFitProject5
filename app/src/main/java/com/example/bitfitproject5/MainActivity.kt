package com.example.bitfitproject5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Telephony.Mms.Addr
import android.widget.Button
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var foodRecyclerView: RecyclerView

    private val items= mutableListOf<Items>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        foodRecyclerView = findViewById(R.id.rv_foods)
        val addItemButton : Button = findViewById(R.id.bt_addItem)

        foodRecyclerView.layoutManager = LinearLayoutManager(this).also {
            val dividerItemDecoration = DividerItemDecoration(this, it.orientation)
            foodRecyclerView.addItemDecoration(dividerItemDecoration)
        }
        foodRecyclerView.layoutManager = GridLayoutManager(this, 1)
        val itemsAdapter =  ItemAdapter(this, items)
        foodRecyclerView.adapter = itemsAdapter


        addItemButton.setOnClickListener{
            val intent = Intent(this, AddItemDetail::class.java)
            startActivity(intent)
        }

        lifecycleScope.launch {
            (application as FoodApplication).db.itemDao().getAll().collect { databaseList ->
                databaseList.map { entity ->
                    Items(
                        entity.foodName,
                        entity.foodCalorie
                    )
                }.also { mappedList ->
                    items.clear()
                    items.addAll(mappedList)
                    itemsAdapter.notifyDataSetChanged()
                }
            }
        }





    }
}