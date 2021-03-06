package com.example.convidados.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.convidados.viewmodel.GuestFormViewModel
import com.example.convidados.R
import kotlinx.android.synthetic.main.activity_guest_form.*

class GuestFormActivity : AppCompatActivity (), View.OnClickListener{

    private lateinit var mViewMode: GuestFormViewModel

    override fun onCreate (savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guest_form)

        mViewMode = ViewModelProvider(this).get(GuestFormViewModel::class.java)

        setListeners()
        observe()
    }

    override fun onClick(v: View) {
        val id = v.id
        if (id== R.id.button_save) {

            val name =edit_name.text.toString()
            val presence = radio_presence.isChecked

            mViewMode.save(name, presence)
        }
    }

    private fun observe() {
        mViewMode.saveGuest.observe(this, Observer {
            if (it) {
                Toast.makeText(applicationContext, "Sucesso", Toast.LENGTH_SHORT).show()
            }else {
                Toast.makeText(applicationContext, "Falha", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun setListeners() {
        button_save.setOnClickListener(this)
    }
}