package com.iventurebd.mymvvm.ui.main.view

import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.iventurebd.mymvvm.R
import com.iventurebd.mymvvm.databinding.ActivityMainBinding
import com.iventurebd.mymvvm.ui.base.BaseActivity
import com.iventurebd.mymvvm.ui.main.vm.MainViewModel
import com.iventurebd.mymvvm.utils.viewModelProvider
import javax.inject.Inject

class MainActivity : BaseActivity() {

  @Inject
  lateinit var viewModelFactory: ViewModelProvider.Factory

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
    val mainviewModel: MainViewModel = viewModelProvider(viewModelFactory)

    binding.mainViewModel = mainviewModel
    binding.lifecycleOwner = this

    mainviewModel.userName.observe(this, Observer { name_ ->
      if (name_.isNullOrEmpty()) {
        Toast.makeText(this, "empty", Toast.LENGTH_LONG)
            .show()
      } else {
        Toast.makeText(this, "Found: $name_", Toast.LENGTH_LONG)
            .show()
      }
    })
  }
}
