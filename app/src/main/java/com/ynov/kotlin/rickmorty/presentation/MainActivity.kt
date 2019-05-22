package com.ynov.kotlin.rickmorty.presentation

import android.content.DialogInterface
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.ynov.kotlin.rickmorty.R
import com.ynov.kotlin.rickmorty.data.ApiManager
import com.ynov.kotlin.rickmorty.presentation.fragment.ListFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mHandler: Handler
    private lateinit var mRunnable:Runnable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.main_activity_fragment_container, ListFragment()).commit()

    }

    override fun onBackPressed() {
        AlertDialog.Builder(this)
            .setTitle("Quitter")
            .setMessage("Voulez-vous vraiment quitter ?")
            .setPositiveButton("Oui",
                DialogInterface.OnClickListener{ dialog, _ ->
                    dialog.dismiss()
                    finish()
                })
            .setNegativeButton("Non", DialogInterface.OnClickListener{ dialog, _ ->
                dialog.dismiss()
                })
            .show()
    }
}
