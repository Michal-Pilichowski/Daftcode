package activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.example.michal.daftcode.R
import kotlinx.android.synthetic.main.activity_main.*
import utils.ElementOfList
import utils.ElementOfListItemDecoration
import utils.ElementOfListRecyclerViewAdapter
import java.util.*

class MainActivity : AppCompatActivity() {
    private val noOfElements = 10
    private var elements = LinkedList<ElementOfList>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState!=null){
            for (i in 0..noOfElements.dec()){
                elements.add(ElementOfList(savedInstanceState.getInt("I" + i.toString()),
                        savedInstanceState.getBoolean("B" + i.toString())))
            }
        } else {
            elements = ElementOfList.getArrayOfElements(noOfElements)
        }

        val elementsAdapter = ElementOfListRecyclerViewAdapter(elements)

        recycler_view.adapter = elementsAdapter
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.addItemDecoration(ElementOfListItemDecoration())
    }

    /*
    Saving values stored in list so that displayed content doesn't change after screen rotation.
     */
    override fun onSaveInstanceState(outState: Bundle?) {

        for (i in 0..noOfElements.dec()){
            outState?.putBoolean("B" + i.toString(), elements[i].isRed)
            outState?.putInt("I" + i.toString(), elements[i].value)
        }

        super.onSaveInstanceState(outState)
    }

}
