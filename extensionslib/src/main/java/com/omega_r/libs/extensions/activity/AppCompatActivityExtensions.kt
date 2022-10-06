package com.omega_r.libs.extensions.activity

import androidx.annotation.IdRes
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout

/**
 * Created by Anton Knyazev on 16.11.2020.
 */
fun AppCompatActivity.setDrawerToggle(@IdRes drawerLayoutId: Int, @IdRes toolbarId: Int) {
    val drawerLayout = findViewById<DrawerLayout>(drawerLayoutId)!!
    val toolbar = findViewById<Toolbar>(toolbarId)!!
    val drawerToggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, 0, 0)
    drawerLayout.addDrawerListener(drawerToggle)
    supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    supportActionBar!!.setHomeButtonEnabled(true)
    drawerToggle.syncState()
}