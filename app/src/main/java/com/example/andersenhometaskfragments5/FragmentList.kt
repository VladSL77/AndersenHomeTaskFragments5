package com.example.andersenhometaskfragments5

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment

class FragmentList : Fragment(R.layout.list_fragment), BackPressedListener {

    private lateinit var infoClickListener: InfoClickListener
    private lateinit var list: ArrayList<String>
    private lateinit var tvFirstName1: TextView
    private lateinit var tvLastName1: TextView
    private lateinit var tvNumber1: TextView
    private lateinit var tvFirstName2: TextView
    private lateinit var tvLastName2: TextView
    private lateinit var tvNumber2: TextView
    private lateinit var tvFirstName3: TextView
    private lateinit var tvLastName3: TextView
    private lateinit var tvNumber3: TextView

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is InfoClickListener) infoClickListener = context
    }

    private fun init() {
        tvFirstName1 = requireView().findViewById(R.id.tvFirstName1)
        tvLastName1 = requireView().findViewById(R.id.tvLastName1)
        tvNumber1 = requireView().findViewById(R.id.tvNumber1)
        tvFirstName2 = requireView().findViewById(R.id.tvFirstName2)
        tvLastName2 = requireView().findViewById(R.id.tvLastName2)
        tvNumber2 = requireView().findViewById(R.id.tvNumber2)
        tvFirstName3 = requireView().findViewById(R.id.tvFirstName3)
        tvLastName3 = requireView().findViewById(R.id.tvLastName3)
        tvNumber3 = requireView().findViewById(R.id.tvNumber3)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        list = requireArguments().getStringArrayList(KEY_LIST)!!
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()

        tvFirstName1.text = list[0]
        tvLastName1.text = list[1]
        tvNumber1.text = list[2]
        tvFirstName2.text = list[3]
        tvLastName2.text = list[4]
        tvNumber2.text = list[5]
        tvFirstName3.text = list[6]
        tvLastName3.text = list[7]
        tvNumber3.text = list[8]

        view.findViewById<LinearLayout>(R.id.linearLayout1).setOnClickListener {
            infoClickListener.onInfoClicked(list, INDEX_CONTACT_1)
        }

        view.findViewById<LinearLayout>(R.id.linearLayout2).setOnClickListener {
            infoClickListener.onInfoClicked(list, INDEX_CONTACT_2)
        }

        view.findViewById<LinearLayout>(R.id.linearLayout3).setOnClickListener {
            infoClickListener.onInfoClicked(list, INDEX_CONTACT_3)
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putStringArrayList(KEY_LIST, list)
    }

    override fun onBackPressedClicked(): Boolean = false

    interface InfoClickListener {
        fun onInfoClicked(list: ArrayList<String>, index: Int)
    }


    companion object {

        private const val KEY_LIST = "KEY_LIST"
        private const val INDEX_CONTACT_1 = 1
        private const val INDEX_CONTACT_2 = 2
        private const val INDEX_CONTACT_3 = 3

        const val FRAGMENT_LIST_TAG = "FRAGMENT_LIST_TAG"

        fun newInstance(list: ArrayList<String>) = FragmentList().also {
            it.arguments = Bundle().apply {
                putStringArrayList(KEY_LIST, list)
            }
        }
    }

}