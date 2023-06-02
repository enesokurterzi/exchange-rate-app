package com.example.ExchangeRateApp

import android.os.Bundle
import android.os.StrictMode
import android.view.View
import android.widget.Button
import android.widget.PopupMenu
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.ExchangeRateApp.databinding.ActivityMainBinding
import com.example.ExchangeRateApp.modules.Currency
import com.example.ExchangeRateApp.services.CurrencyService

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        val currencyService = CurrencyService()
        val arr = currencyService.result()
        val date = currencyService.getDate()

        binding.txtTitle.text = "Merkez Bankası $date günü verileridir."

        binding.btnShowCurrencies.setOnClickListener {
            showPopup(binding.btnShowCurrencies, arr)
        }

    }

    private fun showPopup(view: View, currencyList: List<Currency>) {
        val popupMenu = PopupMenu(this, view)

        currencyList.forEach { item ->
            val menuItem = popupMenu.menu.add(item.Isim)
            menuItem.setOnMenuItemClickListener {
                binding.apply {
                    btnShowCurrencies.text = item.Isim
                    txtSPAValue.text = item.BanknoteBuying
                    txtSPSValue.text = item.BanknoteSelling
                    txtBAValue.text = item.ForexBuying
                    txtBSValue.text = item.ForexSelling
                }

                true
            }
        }
        popupMenu.show()
    }

}