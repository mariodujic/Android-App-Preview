package com.groundzero.gloriapatri.base

import android.view.View
import androidx.fragment.app.Fragment
import com.groundzero.gloriapatri.ui.toolbar.ToolbarButtonGroup

open class BaseFragment : Fragment() {

  private lateinit var toolbarInflater: ToolbarInflater
  private var toolbarButtonGroup: ToolbarButtonGroup? = null

  protected fun setToolbarButtons(buttonIcons: Array<out View>) {
    toolbarButtonGroup = ToolbarButtonGroup(requireContext())
    toolbarButtonGroup!!.addButtons(buttonIcons)
    toolbarInflater = activity as ToolbarInflater
    toolbarInflater.addViewToolbar(toolbarButtonGroup!!)
  }

  protected fun setProgressBarVisibility(isVisible: Boolean) =
    (activity as ProgressBarCallback).changeVisibility(isVisible)

  override fun onStop() {
    super.onStop()
    toolbarButtonGroup?.onDestroy()
  }
}