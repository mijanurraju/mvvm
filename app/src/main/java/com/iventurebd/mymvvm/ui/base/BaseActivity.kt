package com.iventurebd.mymvvm.ui.base

import android.annotation.TargetApi
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.text.Spanned
import android.view.inputmethod.InputMethodManager
import androidx.annotation.StringRes
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import com.iventurebd.mymvvm.R
import com.iventurebd.mymvvm.utils.NetworkUtils
import dagger.android.AndroidInjection
import dagger.android.support.DaggerAppCompatActivity

abstract class BaseActivity : DaggerAppCompatActivity(), BaseInterface {

  private var dialog: Dialog? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    performDI()
    super.onCreate(savedInstanceState)
  }

  override fun hideProgress() {
    dialog?.let { dialog_ -> if (dialog_.isShowing) dialog_.cancel() }
  }

  override fun showProgress() {
    hideProgress()
    //   dialog = CommonUtil.showLoadingDialog(this)
  }

  override fun setupToolbar(
    toolbar: Toolbar,
    title: String?
  ) {
    setToolbar(toolbar, title, false)
  }

  override fun setupToolbarBack(
    toolbar: Toolbar,
    title: String?
  ) {
    setToolbar(toolbar, title, true)
  }

  override fun setupToolbarBack(toolbar: Toolbar) {
    setToolbar(toolbar, getResString(R.string.app_name), true)
  }

  private fun setToolbar(
    toolbar: Toolbar,
    title: String?,
    isBack: Boolean
  ) {
    setSupportActionBar(toolbar)
    val bar = supportActionBar

    if (isBack) {
      bar?.setDisplayHomeAsUpEnabled(true)
      bar?.setDisplayShowHomeEnabled(true)
      toolbar.setNavigationOnClickListener { onBackPressed() }
    }
//    bar?.setBackgroundDrawable(ColorDrawable(Color.parseColor("#f42a41")))
    bar?.title = title ?: getString(R.string.app_name)
  }

  @TargetApi(Build.VERSION_CODES.M)
  fun requestPermissionsSafely(
    permissions: Array<String>,
    requestCode: Int
  ) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
      var isGranted = true

      for (permission in permissions) {
        if (ActivityCompat.checkSelfPermission(
                this,
                permission
            ) != PackageManager.PERMISSION_GRANTED
        ) {
          isGranted = false
          break
        }
      }
      if (isGranted) return
      ActivityCompat.requestPermissions(this, permissions, requestCode)
    }
  }

  @TargetApi(Build.VERSION_CODES.M)
  fun hasPermission(permission: String): Boolean {
    return Build.VERSION.SDK_INT < Build.VERSION_CODES.M || checkSelfPermission(
        permission
    ) == PackageManager.PERMISSION_GRANTED
  }

  override fun hideKeyboard() {
    val view = this.currentFocus
    if (view != null) {
      val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
      imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
  }

  override fun showKeyboard() {
    val view = this.currentFocus
    if (view != null) {
      val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
      imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
    }
  }

  override fun finishAction(): DialogInterface.OnClickListener = FinishAction()

  override fun getResString(resString: Int): String {
    return getString(resString)
  }

  override fun onError(message: String?) {
    //  IVSnackBar.instance.show(this, message ?: "Some Error Occurred!")
  }

  override fun onError(@StringRes resId: Int) {
    onError(getString(resId))
  }

  override fun showMessage(message: String?) {
    //  IVToast.instance.show(this, message ?: "Some Error Occurred!")
  }

  override fun showMessage(@StringRes resId: Int) {
    showMessage(getString(resId))
  }

  override fun isNetworkConnected(): Boolean {
    return NetworkUtils.isNetworkConnected(applicationContext)
  }

  override fun finishIt() {
    finish()
  }

  private fun makeAlert(
    title: String?,
    message: String,
    okListener: DialogInterface.OnClickListener?,
    noListener: DialogInterface.OnClickListener?
  ) {
    try {
      val builder = AlertDialog.Builder(this)

      if (title != null) {
        builder.setTitle(title)
      }
      builder.setCancelable(false)
      builder.setMessage(message.toSpanned())

      if (okListener != null) {
        builder.setPositiveButton(android.R.string.ok, okListener)
      }

      if (noListener != null) {
        builder.setNegativeButton(android.R.string.cancel, noListener)
      }

      val alert = builder.create()
      alert.setCancelable(false)
      alert.show()
    } catch (e: Exception) {
      // AppLogger.e("showMessage: %s", e.message)
    }

  }

  private fun String.toSpanned(): Spanned {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
      return Html.fromHtml(this, Html.FROM_HTML_MODE_LEGACY)
    } else {
      @Suppress("DEPRECATION")
      return Html.fromHtml(this)
    }
  }

  override fun showAlert(
    title: Int,
    message: String,
    okListener: DialogInterface.OnClickListener
  ) {
    makeAlert(getString(title), message, okListener, null)
  }

  override fun showAlert(
    title: String,
    message: String,
    okListener: DialogInterface.OnClickListener
  ) {
    makeAlert(title, message, okListener, null)
  }

  override fun showAlert(
    title: String,
    message: String,
    okListener: DialogInterface.OnClickListener,
    noListener: DialogInterface.OnClickListener
  ) {
    makeAlert(title, message, okListener, noListener)
  }

  override fun showAlert(
    title: Int,
    message: String
  ) {
    makeAlert(getString(title), message, NoAction(), null)
  }

  override fun showAlert(message: String) {
    makeAlert(null, message, NoAction(), null)
  }

  override fun showAlert(message: Int) {
    makeAlert(null, getString(message), NoAction(), null)
  }

  override fun showAlert(
    message: String,
    okListener: DialogInterface.OnClickListener
  ) {
    makeAlert(null, message, okListener, NoAction())
  }

  override fun showAlert(
    message: Int,
    okListener: DialogInterface.OnClickListener
  ) {
    makeAlert(null, getString(message), okListener, null)
  }

  private fun performDI() = AndroidInjection.inject(this)

  internal inner class FinishAction : DialogInterface.OnClickListener {
    override fun onClick(
      dialog: DialogInterface,
      which: Int
    ) {
      finish()
    }
  }

  internal inner class NoAction : DialogInterface.OnClickListener {
    override fun onClick(
      dialog: DialogInterface,
      which: Int
    ) {

    }
  }
}