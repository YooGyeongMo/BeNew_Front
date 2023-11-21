package com.codbs.todo

import android.app.AlertDialog
import android.graphics.Canvas
import android.graphics.Color
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator

class TouchHelper(private val adapter: ToDoAdapter) :
    ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return false
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        val position = viewHolder.adapterPosition
        if (direction == ItemTouchHelper.RIGHT) {
            val builder = AlertDialog.Builder(adapter.context)
            builder.setMessage("Are You Sure?")
                .setTitle("Delete Task")
                .setPositiveButton("Yes") { dialog, which -> adapter.deleteTask(position) }
                .setNegativeButton("No") { dialog, which -> adapter.notifyItemChanged(position) }
            val dialog = builder.create()
            dialog.show()
        } else {
            adapter.editTask(position)
        }
    }

    override fun onChildDraw(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        RecyclerViewSwipeDecorator.Builder(
            c,
            recyclerView,
            viewHolder,
            dX,
            dY,
            actionState,
            isCurrentlyActive
        )
            .addSwipeRightActionIcon(R.drawable.delete_img)
            .addSwipeRightBackgroundColor(Color.RED)
            .addSwipeLeftActionIcon(R.drawable.edit_img)
            .addSwipeLeftBackgroundColor(ContextCompat.getColor(adapter.context, R.color.white))
            .create()
            .decorate()
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
    }
}