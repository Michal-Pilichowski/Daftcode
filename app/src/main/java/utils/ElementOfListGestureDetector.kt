package utils

import android.content.Context
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View

/*
Class implementing detection of single and double click.
 */
abstract class ElementOfListGestureDetector(context: Context): View.OnTouchListener{
    private var gestureDetector = GestureDetector(context, GestureListener())

    override fun onTouch(view: View, motionEvent: MotionEvent): Boolean{
        return gestureDetector.onTouchEvent(motionEvent)
    }

    /*
    Inner class used for detection of motion events.
     */
    inner class GestureListener: GestureDetector.SimpleOnGestureListener(){

        override fun onDown(event: MotionEvent?): Boolean {
            return true
        }

        override fun onDoubleTap(event: MotionEvent?): Boolean {
            onDoubleTAP(event)
            return super.onDoubleTap(event)
        }

        override fun onSingleTapConfirmed(event: MotionEvent?): Boolean {
            onSingleTAP(event)
            return super.onSingleTapConfirmed(event)
        }

    }

    /*
    Functions that have to be overridden to implement custom behaviour on selected events.
    In this case single and double tap on screen.
     */
    abstract fun onDoubleTAP(event: MotionEvent?)

    abstract fun onSingleTAP(event: MotionEvent?)
}