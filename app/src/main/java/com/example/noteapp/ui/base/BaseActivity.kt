package com.example.noteapp.ui.base

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.noteapp.Helper
import com.example.noteapp.R
import com.example.noteapp.data.db.DatabaseManager
import com.example.noteapp.data.db.dao.NotesDao
import com.example.noteapp.data.db.dao.PictureDao
import com.example.noteapp.data.db.dao.UserDao
import com.example.noteapp.util.AppPermissions
import java.lang.Exception

open class BaseActivity : AppCompatActivity(), View {
    var database: DatabaseManager? = null
    lateinit var userDao: UserDao
    lateinit var notesDao: NotesDao
    lateinit var pictureDao: PictureDao
    lateinit var helper: Helper
    lateinit var appPermissions: AppPermissions
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
        helper = Helper()
        appPermissions = AppPermissions(this)
        database = DatabaseManager.getDatabaseManager(this)
        userDao = database!!.userDao()
        notesDao = database!!.notesDao()
        pictureDao = database!!.pictureDao()
    }

    override fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun showFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.content_frame, fragment)
            .commitAllowingStateLoss()
    }

    fun checkPermissions() {
        try {
            val permissions =
                arrayOf(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
            appPermissions = AppPermissions(this)
            if (!appPermissions.hasPermission(this, permissions)) {

                appPermissions.requestPermissions(
                    this,
                    permissions,
                    0
                )
            }
        } catch (e: Exception) {
            showMessage(e.toString())
        }
    }

    override fun onResume() {
        super.onResume()
        checkPermissions()
    }
}