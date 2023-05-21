package com.example.salesnotes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.salesnotes.data.TokenManager

class MainActivity : AppCompatActivity() {

    val sharedViewModel: SharedViewModel by viewModels()
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
            R.id.tbTransactionHistory -> {
                Toast.makeText(this, "Kamu menekan Transaction History", Toast.LENGTH_LONG).show()
                navHostFragment.findNavController().navigate(R.id.transactionFragment)
            }
            R.id.tbLogout -> {
                Toast.makeText(this, "Kamu menekan Logout", Toast.LENGTH_LONG).show()
                sharedViewModel.token=""
                navHostFragment.findNavController().navigate(R.id.login2)
            }
            R.id.tbStock -> {
                Toast.makeText(this, "Kamu menekan Stock", Toast.LENGTH_LONG).show()
                navHostFragment.findNavController().navigate(R.id.stockFragment)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}