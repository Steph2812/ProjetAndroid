package fr.isen.milaninistephanie.androidtoolbox


import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothGatt
import android.bluetooth.BluetoothManager
import android.bluetooth.le.ScanCallback
import android.bluetooth.le.ScanResult
import android.content.Context
import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_blescan.*
import kotlin.math.log
import kotlin.lazy as lazy1

class BLEScanActivity : AppCompatActivity() {
    var scanPlay: String = "Lancer le scan BLE "
    var scanPause: String = "Scan BLE en cours ... "

    lateinit var playIcon: ImageView
    lateinit var handler: Handler
    private var mScanning: Boolean = false
    private lateinit var adapter: BLEScanAdapter

    private val bluetoothAdapter: BluetoothAdapter? by lazy1(LazyThreadSafetyMode.NONE) {
        val bluetoothManager = getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
        bluetoothManager.adapter
    }

    private val isBLEEnabled: Boolean
        get() = bluetoothAdapter?.isEnabled == true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blescan)

        Scan.text = scanPlay
        playIcon = findViewById(R.id.button)
        progressBar.visibility = View.GONE
        bleDivider.visibility = View.VISIBLE

        playIcon.setOnClickListener {
            Scan.text = scanPause
            playIcon.setImageResource(R.drawable.pausebutton)

            when {
                isBLEEnabled -> {
                    initScan()
                }
                bluetoothAdapter != null -> {
                    val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
                    startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT)
                }
            }


        }
    }

    override fun onStop() {
        super.onStop()
        if (isBLEEnabled) {
            scanLeDevice(true)
        }
    }

    companion object {
        private const val REQUEST_ENABLE_BT = 44
        private const val SCAN_PERIOD: Long = 10000
    }

    private fun initScan() {
        bleDivider.visibility = View.GONE
        progressBar.visibility = View.VISIBLE
        handler = Handler()
        adapter = BLEScanAdapter(arrayListOf(), ::onDeviceClicked)
        BLErecycler.adapter = adapter
        BLErecycler.layoutManager = LinearLayoutManager(this)
        scanLeDevice(true)
    }

    private fun scanLeDevice(enable: Boolean) {
        bluetoothAdapter?.bluetoothLeScanner?.apply {

            if (enable) {
                handler.postDelayed({
                    mScanning = false
                    stopScan(leScanCallback)
                }, SCAN_PERIOD)
                mScanning = false
                startScan(leScanCallback)
            } else {
                mScanning = false
                stopScan(leScanCallback)
            }
        }

    }

    private val leScanCallback = object : ScanCallback() {
        override fun onScanResult(callbackType: Int, result: ScanResult) {
            super.onScanResult(callbackType, result)
            Log.w("BLEScanActivity", "${result.device}")
            runOnUiThread {
                //bleDivider.visibility = View.gone
                adapter.addDevice(result)
                adapter.notifyDataSetChanged()
            }
        }
    }

    private fun onDeviceClicked(device: BluetoothDevice) {
        val intent = Intent(this, BLEDeviceActivity::class.java)
        intent.putExtra("ble_device", device)
        startActivity(intent)

    }
}
