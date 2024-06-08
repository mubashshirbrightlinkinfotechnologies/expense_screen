package com.example.employee_screen

import android.app.DatePickerDialog
import android.os.Bundle
import android.text.InputType
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var employeeAdapter: EmployeeAdapter
    private lateinit var spinnerEmployee: Spinner
    private lateinit var editTextDate: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        spinnerEmployee = findViewById(R.id.spinnerEmployee)
        editTextDate = findViewById(R.id.editTextDate)

        setupSpinner()
        setupDatePicker()

        val employees = listOf(
            Employee("Umme Abiha", 20, "1.15", "11.5", "Rs.11.5x2"),
            Employee("Kartik", 20, "1.15", "11.5", "Rs.11.5x2"),
            Employee("Ashwini", 20, "1.15", "11.5", "Rs.11.5x2"),
            Employee("Yadnyaseni", 20, "1.15", "11.5", "Rs.11.5x2"),
            Employee("Aditya", 20, "1.15", "11.5", "Rs.11.5x2")
        )

        employeeAdapter = EmployeeAdapter(employees) { employee ->
            showEditDialog(employee)
        }
        recyclerView.adapter = employeeAdapter
    }

    private fun setupSpinner() {
        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.employees_array,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerEmployee.adapter = adapter
    }

    private fun setupDatePicker() {
        editTextDate.inputType = InputType.TYPE_NULL
        editTextDate.setOnClickListener {
            val calendar = Calendar.getInstance()
            val datePickerDialog = DatePickerDialog(
                this,
                { _, year, month, dayOfMonth ->
                    val selectedDate = Calendar.getInstance()
                    selectedDate.set(year, month, dayOfMonth)
                    val format = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                    editTextDate.setText(format.format(selectedDate.time))
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            )
            datePickerDialog.show()
        }
    }

    private fun showEditDialog(employee: Employee) {
        val dialogView = layoutInflater.inflate(R.layout.dialog_edit_employee, null)

        val editTextName = dialogView.findViewById<EditText>(R.id.editTextName)
        val editTextDistance = dialogView.findViewById<EditText>(R.id.editTextDistance)
        val editTextFareRate1 = dialogView.findViewById<EditText>(R.id.editTextFareRate1)
        val editTextUptoKm1 = dialogView.findViewById<EditText>(R.id.editTextUptoKm1)
        val editTextFareRate2 = dialogView.findViewById<EditText>(R.id.editTextFareRate2)
        val editTextUptoKm2 = dialogView.findViewById<EditText>(R.id.editTextUptoKm2)
        val buttonSubmit = dialogView.findViewById<Button>(R.id.buttonSubmit)
        val closeDialog = dialogView.findViewById<ImageView>(R.id.closeDialog)

        editTextName.setText(employee.name)
        editTextDistance.setText(employee.distance.toString())
        editTextFareRate1.setText(employee.fareRate1)
        editTextUptoKm1.setText("10") // Assuming 10 km is a constant value
        editTextFareRate2.setText(employee.fareRate2)
        editTextUptoKm2.setText("10") // Assuming 10 km is a constant value

        val dialog = AlertDialog.Builder(this)
            .setView(dialogView)
            .setCancelable(true)
            .create()

        buttonSubmit.setOnClickListener {
            // Handle the submit action, like updating the employee details
            dialog.dismiss()
        }

        closeDialog.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }
}
