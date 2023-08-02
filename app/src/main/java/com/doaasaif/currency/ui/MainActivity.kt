package com.doaasaif.currency.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.doaasaif.currency.R
import com.doaasaif.currency.viewModel.ConverssionViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import com.doaasaif.currency.module.networkmodule.ApiStatus


@AndroidEntryPoint
class MainActivity : BaseActivity() {
    private val viewModel: ConverssionViewModel by viewModels()
    private lateinit var currencyFromSp: Spinner
    private lateinit var currencyToSp: Spinner
    private lateinit var fromEt: EditText
    private lateinit var toEt: EditText
    private lateinit var convertBtn: Button
    private lateinit var swapBtn: Button
    private lateinit var detailsBtn: Button
    private lateinit var baseCurrency: String
    private  var symbols: List<String>? = null


    //views saved values
    private val FROM_VALUE_TEXT: String? = "fromValue"
    private val TO_VALUE_TEXT: String? = "toValue"
    private lateinit var fromSavedText: CharSequence


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        if (savedInstanceState != null) {
            fromSavedText = savedInstanceState.getCharSequence(FROM_VALUE_TEXT)!!
            fromEt.setText(fromSavedText)
        }
        init()
        //initially get currencies to display on currency spinner
        viewModel.getSymbols(getString(R.string.key))
        fillCurrencySpinner()



        viewModel.converssionResult.observe(this, Observer {

            toEt.setText(it.toString())

        })

        viewModel.error.observe(this, Observer {

            Toast.makeText(
                this,
                it.toString(),
                Toast.LENGTH_LONG
            ).show()
        })
        viewModel.apiStatus.observe(this, Observer { status ->
            handleApiStatus(status)


        }
        )

    }

    override fun getLayoutResourceId(): Int {
        return R.layout.activity_main
    }

    private fun handleApiStatus(apiStatus: ApiStatus) {
        if (apiStatus == ApiStatus.SUCCESS) {
            dismissProgressDialog()

        } else if (apiStatus == ApiStatus.ERROR) {
            dismissProgressDialog()

        } else if (apiStatus == ApiStatus.LOADING
        ) {
            showProgressDailog(getString(R.string.loading))
        }
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState);
        outState.putCharSequence(FROM_VALUE_TEXT, fromEt.text);
        outState.putCharSequence(TO_VALUE_TEXT, toEt.text);
    }

    private fun init() {
        currencyFromSp = findViewById(R.id.from_currency_sp)
        currencyToSp = findViewById(R.id.to_currency_sp)
        fromEt = findViewById(R.id.input_value_et)
        toEt = findViewById(R.id.output_value_et)
        convertBtn = findViewById(R.id.convert_btn)

        //default value
        fromEt.setText("1")
        toEt.setText("1")

        convertBtn.setOnClickListener { click -> convertCurrency() }
        swapBtn = findViewById(R.id.swap_btn)
        swapBtn.setOnClickListener { click -> swapValues() }
        detailsBtn = findViewById(R.id.show_details_button)
        detailsBtn.setOnClickListener { click -> openDetailsActivity() }






    }

    private fun convertCurrency() {
        lifecycleScope.launch() {
            viewModel.getLatest(
                fromEt.text.toString().toDouble(),
                symbols?.get(currencyFromSp.selectedItemPosition),
                symbols?.get(currencyToSp.selectedItemPosition)
            )


        }

    }

    private fun openDetailsActivity() {
        intent = Intent(this@MainActivity, DetailsActivity::class.java)
/*
        intent.putExtra(getString(R.string.latest), viewModel.latest)
        startActivity()
*/

    }

    private fun swapValues() {
        var fromValue = fromEt.text
        fromEt.text = toEt.text
        toEt.text = fromValue

        var selectedCurrencyPosition: Int = currencyFromSp.selectedItemPosition
        var destCurrencyPosition: Int = currencyToSp.selectedItemPosition
        currencyFromSp.setSelection(destCurrencyPosition)
        currencyToSp.setSelection(selectedCurrencyPosition)
    }

    private fun fillCurrencySpinner() {

        if (currencyFromSp != null && currencyFromSp != null) {
            lifecycleScope.launch() {


                viewModel._symbols.collect { symbolsList ->
                    symbolsList?.let {

                            symbolsList ->
                        symbols = symbolsList
                        val fromSpinnerAdapter: ArrayAdapter<String> = ArrayAdapter<String>(
                            applicationContext,
                            android.R.layout.simple_spinner_dropdown_item,
                            symbolsList
                        )
                        val toSpinnerAdapter: ArrayAdapter<String> = ArrayAdapter<String>(
                            applicationContext,
                            android.R.layout.simple_spinner_dropdown_item,
                            symbolsList
                        )
                        fromSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        toSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

                        currencyFromSp.setAdapter(fromSpinnerAdapter)
                        currencyToSp.setAdapter(toSpinnerAdapter)
                        //currency_from_sp.adapter = fromSpinnerAdapter
                    }


                }
            }
        }
    }
}

