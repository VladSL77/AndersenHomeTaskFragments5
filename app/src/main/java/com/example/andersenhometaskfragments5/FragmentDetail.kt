package com.example.andersenhometaskfragments5

import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment

class FragmentDetail : Fragment(R.layout.detail_fragment), BackPressedListener {

    private lateinit var list: ArrayList<String>
    private var index = 0
    private lateinit var saveButtonClickListener: SaveButtonClickListener

    private lateinit var etFirstName: TextView
    private lateinit var etLastName: TextView
    private lateinit var etNumber: TextView

    private var newFirstName = ""
    private var newLastName = ""
    private var newNumber = ""

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is SaveButtonClickListener) saveButtonClickListener = context
    }

    private fun init() {
        etFirstName = requireView().findViewById(R.id.etFirstName)
        etLastName = requireView().findViewById(R.id.etLastName)
        etNumber = requireView().findViewById(R.id.etNumber)
        when (index) {
            INDEX_CONTACT_1 -> {
                newFirstName = list[0]
                newLastName = list[1]
                newNumber = list[2]
            }
            INDEX_CONTACT_2 -> {
                newFirstName = list[3]
                newLastName = list[4]
                newNumber = list[5]
            }
            INDEX_CONTACT_3 -> {
                newFirstName = list[6]
                newLastName = list[7]
                newNumber = list[8]
            }
        }
        etFirstName.text = newFirstName
        etLastName.text = newLastName
        etNumber.text = newNumber
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        list = requireArguments().getStringArrayList(KEY_LIST)!!
        index = requireArguments().getInt(KEY_INDEX)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
        view.findViewById<Button>(R.id.buttonSave).setOnClickListener {
            if (etFirstName.text.toString() != "" && etLastName.text.toString() != "" && etNumber.text.toString() != "") {
                newFirstName = etFirstName.text.toString()
                newLastName = etLastName.text.toString()
                newNumber = etNumber.text.toString()
            } else {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.error_empty_field),
                    Toast.LENGTH_LONG
                ).show()
            }
            when (index) {
                INDEX_CONTACT_1 -> {
                    list[0] = newFirstName
                    list[1] = newLastName
                    list[2] = newNumber
                }
                INDEX_CONTACT_2 -> {
                    list[3] = newFirstName
                    list[4] = newLastName
                    list[5] = newNumber
                }
                INDEX_CONTACT_3 -> {
                    list[6] = newFirstName
                    list[7] = newLastName
                    list[8] = newNumber
                }
            }
            saveButtonClickListener.onSaveButtonClicked(list)
        }

    }

    interface SaveButtonClickListener {
        fun onSaveButtonClicked(list: ArrayList<String>)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putStringArrayList(KEY_LIST, list)
    }

    override fun onBackPressedClicked(): Boolean = true

    companion object {

        private const val KEY_LIST = "KEY_LIST"
        private const val KEY_INDEX = "KEY_INDEX"
        private const val INDEX_CONTACT_1 = 1
        private const val INDEX_CONTACT_2 = 2
        private const val INDEX_CONTACT_3 = 3

        const val FRAGMENT_DETAIL_TAG = "FRAGMENT_DETAIL_TAG"

        fun newInstance(list: ArrayList<String>, index: Int) = FragmentDetail().also {
            it.arguments = Bundle().apply {
                putStringArrayList(KEY_LIST, list)
                putInt(KEY_INDEX, index)
            }
        }
    }
}