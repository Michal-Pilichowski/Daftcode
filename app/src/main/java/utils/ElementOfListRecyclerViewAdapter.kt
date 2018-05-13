package utils

import android.graphics.Typeface
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.michal.daftcode.R
import java.util.*

/*
Custom Recycler View adapter used for populating and reusing displayed elements.
 */
class ElementOfListRecyclerViewAdapter(private val elements: LinkedList<ElementOfList>):
        RecyclerView.Adapter<ElementOfListRecyclerViewAdapter.ElementHolder>(){

    inner class ElementHolder(v: View): RecyclerView.ViewHolder(v){
        private val view = v

        /*
        Function used for binding data from element to view that displays it.
        @param element Element which values we'd like to display in current view.
        @param currentPosition position of given element in list containing all list elements.
         */
        fun bindData(element: ElementOfList, currentPosition: Int){
            val currentViewValue = view.findViewById<TextView>(R.id.element_of_list_value)
            val currentViewCircle = view.findViewById<ImageView>(R.id.element_of_list_circle)
            val customTypeface = Typeface.createFromAsset(view.context.assets, "fonts/edosz.ttf")

            currentViewValue.typeface = customTypeface

            if (element.isRed){
                currentViewValue.text = (3*element.value).toString()
                currentViewValue.setBackgroundResource(R.drawable.circle_red)
                currentViewValue.setTextColor(ContextCompat.getColor(view.context, R.color.red_light))
                currentViewCircle.setBackgroundResource(R.drawable.circle_red)
            } else {
                currentViewValue.text = element.value.toString()
                currentViewValue.setBackgroundResource(R.drawable.circle_blue)
                currentViewValue.setTextColor(ContextCompat.getColor(view.context, R.color.blue_light))
                currentViewCircle.setBackgroundResource(R.drawable.circle_blue)
            }

            view.setOnTouchListener(object: ElementOfListGestureDetector(view.context){

                override fun onDoubleTAP(event: MotionEvent?) {
                    element.value = 0
                    currentViewValue.text = element.value.toString()
                }

                override fun onSingleTAP(event: MotionEvent?) {
                    element.value = element.value + getPreviousElementValue(currentPosition)
                    if (element.isRed){
                        currentViewValue.text = (3*element.value).toString()
                    } else {
                        currentViewValue.text = element.value.toString()
                    }
                }

            })
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ElementOfListRecyclerViewAdapter.ElementHolder{
        val inflatedView = parent.inflate(R.layout.element_of_list, false)
        return ElementHolder(inflatedView)
    }

    override fun getItemCount(): Int {
        return elements.size
    }

    override fun onBindViewHolder(holder: ElementOfListRecyclerViewAdapter.ElementHolder, position: Int) {
        val currentElement = elements[position]
        holder.bindData(currentElement, position)
    }

    /*
    Function returning value field of list element that lies in list before the given position.
    @param currentPosition position of the element for which we want to get value of previous element.
    @return int number representing value field of previous list element.
     */
    private fun getPreviousElementValue(currentPosition: Int): Int{
        return if (currentPosition==0){
            elements[elements.size-1].value
        } else {
            elements[currentPosition-1].value
        }
    }

}