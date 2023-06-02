package com.example.odev_5

import android.os.Bundle
import android.os.StrictMode
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.PopupMenu
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.odev_5.modules.Currency
import com.example.odev_5.services.CurrencyService

class MainActivity : AppCompatActivity() {

    private lateinit var txtTitle:TextView
    private lateinit var btnShowCurrencies: Button
    private lateinit var txtSPAValue: TextView
    private lateinit var txtSPSValue: TextView
    private lateinit var txtBAValue: TextView
    private lateinit var txtBSValue: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        val currencyService = CurrencyService()
        val arr = currencyService.result()
        val date = currencyService.getDate()

        txtTitle = findViewById(R.id.txtTitle)
        btnShowCurrencies = findViewById(R.id.btnShowCurrencies)
        txtSPAValue = findViewById(R.id.txtSPAValue)
        txtSPSValue = findViewById(R.id.txtSPSValue)
        txtBAValue = findViewById(R.id.txtBAValue)
        txtBSValue = findViewById(R.id.txtBSValue)

        txtTitle.text = "Merkez Bankası $date günü verileridir."

        btnShowCurrencies.setOnClickListener {
            showPopup(btnShowCurrencies, arr)
        }

    }

    private fun showPopup(view: View, currencyList: List<Currency>) {
        val popupMenu = PopupMenu(this, view)

        currencyList.forEach { item ->
            val menuItem = popupMenu.menu.add(item.Isim)
            menuItem.setOnMenuItemClickListener {
                btnShowCurrencies.text = item.Isim
                txtSPAValue.text = item.BanknoteBuying
                txtSPSValue.text = item.BanknoteSelling
                txtBAValue.text = item.ForexBuying
                txtBSValue.text = item.ForexSelling
                true
            }
        }
        popupMenu.show()
    }

}