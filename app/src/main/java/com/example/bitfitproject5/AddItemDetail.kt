package com.example.bitfitproject5

import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddItemDetail: AppCompatActivity()  {
    private lateinit var etfoodName: EditText
    private lateinit var etfoodCalorie: EditText
    private lateinit var btfoodAdd : Button
    private lateinit var foodRecyclerView: RecyclerView

    //private val items= mutableListOf<Items>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.new_record)

        etfoodName = findViewById(R.id.et_foodName)
        etfoodCalorie = findViewById(R.id.et_Calories)
        btfoodAdd = findViewById(R.id.bt_itemAdded)

        val item = Items(etfoodName.text.toString(),etfoodCalorie.text.toString())

        lifecycleScope.launch {
            (application as FoodApplication).db.itemDao().getAll().collect { databaseList ->
                databaseList.map { entity ->
                    Items(
                        entity.foodName,
                        entity.foodCalorie
                    )
                }
            }
        }
        btfoodAdd.setOnClickListener {

            lifecycleScope.launch(Dispatchers.IO) {
                (application as FoodApplication).db.itemDao().insert(
                    ItemEntry(
                        foodName = etfoodName.text.toString(),
                        foodCalorie = etfoodCalorie.text.toString()
                    )
                )
            }
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

        }


    }

}