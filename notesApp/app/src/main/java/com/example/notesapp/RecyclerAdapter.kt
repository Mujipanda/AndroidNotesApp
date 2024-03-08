package com.example.notesapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import android.util.Log
import android.widget.RelativeLayout


var currentFile: Int =0
class RecyclerAdapter(private  var titles: List<String>/* private  var details: List<String>, private var images:List<Int>*/) :
    RecyclerView.Adapter<RecyclerAdapter.ViewHolder>()
{

    val mainActivity = MainActivity()
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val itemTitle: TextView = itemView.findViewById(R.id.tv_title)
        //val editButton: Button = itemView.findViewById(R.id.btnDelete)
        val wrapper: RelativeLayout = itemView.findViewById(R.id.rl_wrapper)

        //val itemDetail: TextView = itemView.findViewById(R.id.tv_description)
        //val itemPicture: ImageView = itemView.findViewById(R.id.iv_image)


        //private  val ConstraintLayoutPrev: ConstraintLayout = itemView.findViewById(R.id.viewNotesConstrinat)
        public fun removeItem()
        {

        }

        // initilise function runs when created
        init {
            // creates a click listener on the recycler viewer element
            itemView.setOnClickListener { v: View ->
                val position: Int = adapterPosition// the index of the element clicked on in the list
                Toast.makeText(itemView.context, "You clicked on item ${position + 1}", Toast.LENGTH_SHORT).show()
                // message showing which element was clicked
                currentFile = position
                returnEvent()

            }
            /*editButton.setOnClickListener {
                val positionBtn: Int = adapterPosition
                //Toast.makeText(itemView.context, "Note deleted "+ positionBtn.toString(), Toast.LENGTH_SHORT).show()
                //mainActivity.deleteFunc()

                //wrapper.visibility = View.GONE

               // for (file in filesDir.listFiles()) {
            }*/
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_layout,parent, false)
        return  ViewHolder(v)
    }

    // tells the recycler viewer of many items are in the recycler viewer
    override fun getItemCount(): Int {
        return titles.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemTitle.text = titles[position]

        //holder.itemDetail.text = details[position]
        //holder.itemPicture.setImageResource(images[position])
    }

    public fun returnEvent(){
        Log.d(VAL,"return Event Playing ")
        val mainClass = MainActivity()
        //mainClass.openNote()d
    }




}
