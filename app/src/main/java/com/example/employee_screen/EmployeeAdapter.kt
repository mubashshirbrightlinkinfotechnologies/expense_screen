package com.example.employee_screen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class EmployeeAdapter(
    private val employeeList: List<Employee>,
    private val itemClickListener: (Employee) -> Unit
) : RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_employee, parent, false)
        return EmployeeViewHolder(view)
    }

    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {
        val employee = employeeList[position]
        holder.bind(employee, itemClickListener)
    }

    override fun getItemCount() = employeeList.size

    class EmployeeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.tvEmployeeName)
        private val distanceTextView: TextView = itemView.findViewById(R.id.tvDistance)
        private val fareRate1TextView: TextView = itemView.findViewById(R.id.tvFareRate1)
        private val fareRate2TextView: TextView = itemView.findViewById(R.id.tvFareRate2)
        private val totalTextView: TextView = itemView.findViewById(R.id.tvTotal)

        fun bind(employee: Employee, itemClickListener: (Employee) -> Unit) {
            nameTextView.text = employee.name
            distanceTextView.text = "Distance: ${employee.distance} km"
            fareRate1TextView.text = "Fare Rate 1: ${employee.fareRate1}"
            fareRate2TextView.text = "Fare Rate 2: ${employee.fareRate2}"
            totalTextView.text = "Total: ${employee.total}"

            itemView.setOnClickListener { itemClickListener(employee) }
        }
    }
}
