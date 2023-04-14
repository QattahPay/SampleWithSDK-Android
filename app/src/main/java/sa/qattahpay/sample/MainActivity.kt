package sa.qattahpay.sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import sa.qattahpay.sample.databinding.ActivityMainBinding
import sa.qattahpay.sdk.PaymentRequest
import sa.qattahpay.sdk.QattahPay
import sa.qattahpay.sdk.domain.entities.Currency
import sa.qattahpay.sdk.domain.interfaces.PaymentCallback

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private val qattahPayApiKey = "API_KEY_HERE"
    private val paymentCallbacks = object : PaymentCallback {
        override fun onStarted(paymentId: String) {
            // Handle payment creation
            Toast.makeText(this@MainActivity, "Payment ID: $paymentId", Toast.LENGTH_SHORT)
                .show()
            hideProgress()
        }

        override fun onSuccess(paymentId: String) {
            // Handle payment success
            Toast.makeText(this@MainActivity, "Payment Success", Toast.LENGTH_SHORT).show()
            hideProgress()
        }

        override fun onError(errorMessage: String) {
            // Handle payment error
            Toast.makeText(
                this@MainActivity,
                "Payment Failed: $errorMessage",
                Toast.LENGTH_SHORT
            ).show()
            hideProgress()
        }

        override fun onCancel() {
            // Handle payment cancel
            Toast.makeText(this@MainActivity, "Payment canceled", Toast.LENGTH_LONG).show()
            hideProgress()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // initialize a new QattahPay object
        QattahPay.initialize(this, qattahPayApiKey)

        with(binding) {
            hideProgress()
            pay.setOnClickListener {

                // create a new payment request
                val paymentRequest: PaymentRequest = PaymentRequest.Builder()
                    .setAmount(amount.text.toString().toDouble()) // amount in Saudi Riyals (SAR)
                    .setCurrency(Currency.SAR)
                    .setOrderId(refId.text.toString())
                    .setDescription(description.text.toString())
                    .setCustomerEmail(customerEmail.text.toString())
                    .setCustomerMobileNumber(customerPhone.text.toString())
                    .isSandbox(true)
                    .build()

                // start a new payment session
                QattahPay.startPayment(paymentRequest, paymentCallbacks)

                showProgress()
            }
        }
    }

    private fun showProgress() {
        binding.isProgressing = true
    }

    private fun hideProgress() {
        binding.isProgressing = false
    }
}