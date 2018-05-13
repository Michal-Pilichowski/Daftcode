package utils

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View

/*
Class used for adding margin to views displaying elements of list.
 */
class ElementOfListItemDecoration: RecyclerView.ItemDecoration() {
    private val offset = 24

    override fun getItemOffsets(outRect: Rect?, view: View?, parent: RecyclerView?, state: RecyclerView.State?) {
        super.getItemOffsets(outRect, view, parent, state)

        val childPosition = parent?.getChildAdapterPosition(view)

        when (childPosition){
            RecyclerView.NO_POSITION->{
                return
            }
            0->{
                outRect?.set(offset, offset, offset, offset/2)
            }
            parent?.adapter?.itemCount?.dec()->{
                outRect?.set(offset, offset/2, offset, offset)
            }
            else ->{
                outRect?.set(offset, offset/2, offset, offset/2)
            }
        }
    }

}
