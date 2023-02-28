package com.example.bitfitproject5

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class ItemAdapter (private val context: Context, private val foods: List<Items>) :
        RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.food_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val food = foods[position]
        holder.bind(food)
    }

    override fun getItemCount() = foods.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        private val tvFoodName = itemView.findViewById<TextView>(R.id.tv_FoodName)
        private val tvFoodCal = itemView.findViewById<TextView>(R.id.tv_CalNum)

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(food : Items ) {
            tvFoodName.text = food.foodName
            tvFoodCal.text = food.calorie.toString()
        }

        override fun onClick(v: View?) {
              //Toast.makeText(MainActivity,"this is toast message",Toast.LENGTH_SHORT).show()
//            val food = foods[absoluteAdapterPosition]
//            val intent = Intent(context, DetailActivity::class.java)
//            intent.putExtra("FOOD_EXTRA", food)
//            context.startActivity(intent)
        }
    }
}