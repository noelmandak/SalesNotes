package com.example.salesnotes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.my_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        when(item.itemId){
            R.id.tbOrder -> {
                Toast.makeText(this, "Kamu menekan Order", Toast.LENGTH_LONG).show()
                navHostFragment.findNavController().navigate(R.id.order)
            }
            R.id.tbCustomer -> {
                Toast.makeText(this, "Kamu menekan Customer", Toast.LENGTH_LONG).show()
                navHostFragment.findNavController().navigate(R.id.customerFragment)
            }
            R.id.tbPaymentHistory -> {
                Toast.makeText(this, "Kamu menekan Payment History", Toast.LENGTH_LONG).show()
                navHostFragment.findNavController().navigate(R.id.paymentHistoryFragment)
            }
            R.id.tbLogout -> {
                Toast.makeText(this, "Kamu menekan Logout", Toast.LENGTH_LONG).show()
                navHostFragment.findNavController().navigate(R.id.loginFragment)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}