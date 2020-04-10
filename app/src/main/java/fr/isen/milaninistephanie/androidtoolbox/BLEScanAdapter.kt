package fr.isen.milaninistephanie.androidtoolbox

import android.bluetooth.BluetoothDevice
import android.bluetooth.le.ScanResult
import fr.isen.milaninistephanie.androidtoolbox.R



import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_ble_cell.view.*
import kotlinx.android.synthetic.main.activity_web_cell.view.*

class BLEScanAdapter(
    private val scanResults: ArrayList<ScanResult>,
    val deviceClickListener: (BluetoothDevice) -> Unit
) :
    RecyclerView.Adapter<BLEScanAdapter.BLEScanViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BLEScanViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(R.layout.activity_ble_cell, parent, false)
        return BLEScanAdapter.BLEScanViewHolder(view)
    }

    class BLEScanViewHolder(ScanBLEView: View) : RecyclerView.ViewHolder(ScanBLEView) {
        val layout = ScanBLEView.bleDeviceLayout
        val rssi: TextView = ScanBLEView.coordonnee
        val name: TextView = ScanBLEView.titre
        val address: TextView = ScanBLEView.distance
    }

    override fun getItemCount(): Int {
        return scanResults.size
    }

    override fun onBindViewHolder(holder: BLEScanAdapter.BLEScanViewHolder, position: Int) {
        holder.name.text =
            scanResults[position].device?.name ?: "Appareil Inconnu"
        holder.rssi.text = scanResults[position].device.address
        holder.address.text = scanResults[position].rssi.toString()
        holder.layout.setOnClickListener{
            deviceClickListener.invoke(scanResults[position].device)
        }
    }

    fun addDevice(result: ScanResult) {
        val index = scanResults.indexOfFirst {
            it.device.address == result.device.address
        }
        if (index != -1) {
            scanResults[index] = result
        } else {
            scanResults.add(result)
        }
    }

}
