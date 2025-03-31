package com.compose_demo.vishaldemo.utils

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.widget.Toast
import com.google.gson.Gson
import staff.management.system.data.domain.model.Admin
import staff.management.system.data.domain.model.Company
import staff.management.system.data.domain.model.Employee

class Shareprefs(private val context: Context) {

  companion object {
    const val PREF_FILE_NAME = "sharePref"
    const val KEY_ADMIN = "admin"
    const val KEY_EMPLOYEE = "employee"
    const val KEY_COMPANY = "company"
  }


  val isLoggedIn: Boolean
    get() {
      return admin != null || employee != null
    }

  val isAdmin : Boolean
    get() {
      return admin != null && employee == null
    }

  val admin: Admin?
    get() {
      return getObject(KEY_ADMIN, Admin::class.java)
    }

  val employee: Employee?
    get() {
      return getObject(KEY_EMPLOYEE, Employee::class.java)
    }

  val company: Company?
    get() {
      return getObject(KEY_COMPANY, Company::class.java)
    }

  val companyCode: String?
    get() {
      return company?.companyCode
    }

  val companyId: Int?
    get() {
      return company?.companyId
    }

  fun set(key: String, string: String?) {
    context
      .getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE)
      .edit()
      .putString(key, string)
      .apply()
  }

  private fun getString(key: String): String? {
    return context
      .getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE)
      .getString(key, null)
  }

  private fun <T : Any> getObject(key: String, clazz: Class<T>): T? {
    val s = getString(key)
    return if (s == null) {
      null
    } else {
      Gson().fromJson(s, clazz)
    }
  }

  fun setCompany(company: Company) {
    set(KEY_COMPANY, Gson().toJson(company))
  }

  fun setAdmin(admin: Admin) {
    set(KEY_ADMIN, Gson().toJson(admin))
  }

  fun setEmployee(employee: Employee) {
    set(KEY_EMPLOYEE, Gson().toJson(employee))
  }

  fun showToast(message: String?, length: Int = Toast.LENGTH_SHORT) {
    context.toast(message, length)
  }

  fun clearAll() {
    context
      .getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE)
      .edit()
      .clear()
      .apply()
  }

  fun launchIntent(intent: Intent) {
    context.startActivity(intent)
  }
}