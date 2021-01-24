package com.lusi.movieapp.fragment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBar
import com.google.android.material.tabs.TabLayoutMediator
import com.lusi.movieapp.R
import com.lusi.movieapp.activity.AboutActivity
import com.lusi.movieapp.adapter.TabAdapter
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getSupportActionBar()?.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM)
        getSupportActionBar()?.setCustomView(R.layout.action_bar)
        setContentView(R.layout.activity_home)

        val tabAdapter = TabAdapter(supportFragmentManager, lifecycle)
        view_pager.adapter = tabAdapter

        val tabTitles = arrayOf("Popular", "Now Playing", "Top Rated", "Upcoming")
        TabLayoutMediator(tab_layout, view_pager) { tab, position ->
            tab.text = tabTitles[position]
        }.attach()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_about, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
       return when (item.itemId) {
           R.id.action_about -> {
               Intent(this@HomeActivity, AboutActivity::class.java).also {
                   startActivity(it)
               }
               true
           }
           else -> super.onOptionsItemSelected(item)
       }
    }
}