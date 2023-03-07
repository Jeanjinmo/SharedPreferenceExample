package com.jean.binarchapterenam

import android.content.Context
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.jean.binarchapterenam.databinding.ActivitySharedPreferenceExampleBinding

class SharedPreferenceExampleActivity : AppCompatActivity() {
    //Binding
    private lateinit var binding: ActivitySharedPreferenceExampleBinding

    //SharedPreference Step 2
    private lateinit var sharedPreference: SharedPreferences
    private lateinit var editor: Editor


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySharedPreferenceExampleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //panggil Setup Step 3
        setUpSharedPreference()
        setUpAction()
    }

    //Proses melakukan set SharedPreference dan editor
    private fun setUpSharedPreference() {
        sharedPreference = this.getSharedPreferences(TABLE_NAME, Context.MODE_PRIVATE)
        editor = sharedPreference.edit()
    }

    //
    private fun setUpAction() {
        //Save Data(Simpan Data)
        binding.btnSave.setOnClickListener {
            val idData = binding.etInputId.text.toString()
            val nameData = binding.etInputName.text.toString()
            if (idData.isNotEmpty() && nameData.isNotEmpty()) {
                editor.putInt(KEY_ID, idData.toInt())
                editor.putString(KEY_NAME, nameData)
                editor.apply()
                Toast.makeText(this, "Data berhasil disimpan", Toast.LENGTH_SHORT).show()
                clearEditText()
            } else {
                Toast.makeText(this, "Inputan tidak sesuai", Toast.LENGTH_SHORT).show()
            }
        }

        //View Data(Lihat Data)
        binding.btnView.setOnClickListener {
            val dataIdView = sharedPreference.getInt(KEY_ID, 0)
            val dataNameView = sharedPreference.getString(KEY_NAME, "")
            if (dataIdView != 0 && dataNameView != "") {
                Toast.makeText(this, "Data Tampil", Toast.LENGTH_SHORT).show()
//                binding.apply { }
                binding.tvShowId.text = dataIdView.toString()
                binding.tvShowName.text = dataNameView
            } else {
                Toast.makeText(this, "Data Tidak Tersedia", Toast.LENGTH_SHORT).show()
            }
        }

        //Clear Data(Hapus Data)
        binding.btnClear.setOnClickListener {
            editor.clear()
            editor.apply()
            clearEditText()
            clearView()
            Toast.makeText(this, "Data Dihapus", Toast.LENGTH_SHORT).show()
        }

    }

    //Clear TextView
    private fun clearView() {
        binding.apply {
            tvShowId.text = ""
            tvShowName.text = ""
        }
    }

    //Clear EditText
    private fun clearEditText() {
        binding.etInputId.text.clear()
        binding.etInputName.text.clear()
    }


    //KEY VALUE SharedPreference (Nama Table, Key Name yang akan disimpan) Step 1
    companion object {
        const val KEY_ID = "KEY_ID"
        const val KEY_NAME = "KEY_NAME"
        const val TABLE_NAME = "tablesharedpreference"
    }
}