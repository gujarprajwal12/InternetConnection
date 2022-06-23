package com.prajwal.internetconnectivitylibrary

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView

class Internetcheck : AppCompatActivity() {

    var dialog: Dialog? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_internetcheck)

        onResume()

    }

    override fun onResume() {
        super.onResume()

        if (!isNetworkAvailable == true) {

            dialog = Dialog(this)
            dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog!!.window!!.attributes.width =
                WindowManager.LayoutParams.MATCH_PARENT
            dialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog!!.setCancelable(false)
            dialog!!.setContentView(R.layout.common_dialog)
            dialog!!.show()

            var butYes: Button = dialog!!.findViewById(R.id.butYEs)
            var butNo: Button = dialog!!.findViewById(R.id.butNO)
            var tv_alert: TextView = dialog!!.findViewById(R.id.tv_alert)
            var txtDialogMessage: TextView = dialog!!.findViewById(R.id.txtDialogMessage)
            butYes.text = "Ok"
            butNo.visibility = View.GONE
            tv_alert.text = "Internet Connection"
            txtDialogMessage.text = "Please Check Your Internet Connection"

            butYes.setOnClickListener {
                dialog!!.dismiss()
            }

        }
    }

    val isNetworkAvailable: Boolean
        @SuppressLint("ServiceCast")
        get() {
            val connectivityManager =
                getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            if (connectivityManager != null) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    val capabilities =
                        connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
                    if (capabilities != null) {
                        if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                            return true
                        } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                            return true
                        } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                            return true
                        }
                    }
                }
            }
            return false
        }

}