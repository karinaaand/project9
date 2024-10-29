package com.example.project9

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.project9.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    // Binding untuk mengakses elemen layout secara lebih mudah
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // Mengaktifkan tampilan edge-to-edge untuk aktivitas

        setContentView(binding.root) // Menetapkan tampilan dari binding ke aktivitas

        // Mengatur Toolbar sebagai ActionBar
        setSupportActionBar(findViewById(R.id.toolbar))

        with(binding) {
            // Mencari NavController untuk navigasi
            val navController = findNavController(R.id.nav_host_fragment_container)
            // Menghubungkan BottomNavigationView dengan NavController
            bottomNavigationView.setupWithNavController(navController)
        }

        // Mengatur listener untuk menerapkan WindowInsets pada view utama
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            // Mendapatkan ukuran bar sistem (status bar dan navigasi)
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            // Mengatur padding untuk view utama berdasarkan ukuran bar sistem
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets // Mengembalikan insets yang diterapkan
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Menginflate menu logout ke dalam opsi menu
        menuInflater.inflate(R.menu.menu_logout, menu)
        return true // Menyatakan bahwa menu telah dibuat
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.options -> {
                finish() // Menutup aktivitas saat ini
                true // Menyatakan bahwa opsi telah diproses
            }
            else -> super.onOptionsItemSelected(item) // Memanggil superclass untuk opsi lainnya
        }
    }
}
