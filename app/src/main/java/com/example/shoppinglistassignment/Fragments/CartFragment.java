package com.example.shoppinglistassignment.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shoppinglistassignment.Classes.ShoppingItem;
import com.example.shoppinglistassignment.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CartFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CartFragment extends Fragment {

    private EditText editTextItem, editTextQuantity;
    private Button buttonAdd;
    private ListView listViewItems;

    private List<ShoppingItem> shoppingList = new ArrayList<>();
    private ArrayAdapter<String> adapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CartFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CartFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CartFragment newInstance(String param1, String param2) {
        CartFragment fragment = new CartFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        editTextItem = view.findViewById(R.id.editTextItem);
        editTextQuantity = view.findViewById(R.id.editTextQuantity);
        buttonAdd = view.findViewById(R.id.buttonAdd);
        listViewItems = view.findViewById(R.id.listViewItems);
        adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1);
        listViewItems.setAdapter(adapter);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String itemName = editTextItem.getText().toString();  // Update the variable name
                String quantityStr = editTextQuantity.getText().toString();  // Update the variable name

                if (!itemName.isEmpty() && isValidQuantity(quantityStr)) {
                    int quantity = Integer.parseInt(quantityStr);
                    if (quantity > 0) {
                        ShoppingItem shoppingItem = new ShoppingItem(itemName, quantity);
                        shoppingList.add(shoppingItem);
                        updateListView();
                        clearEditTexts();
                    }
                } else {
                    // Either itemName is empty or quantity is not a valid number
                    Toast.makeText(requireContext(), "Please enter a valid input", Toast.LENGTH_SHORT).show();
                }
            }
        });
        listViewItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                shoppingList.remove(position);
                updateListView();
            }
        });

        if(getArguments() != null){
            String name = getArguments().getString("nameText");
            TextView userText = view.findViewById(R.id.textViewUser);
            userText.setText(name);
        }

        return view;
    }
    private void updateListView() {
        adapter.clear();
        for (ShoppingItem item : shoppingList) {
            adapter.add(item.toString());
        }
    }

    private void clearEditTexts() {
        editTextItem.setText("");
        editTextQuantity.setText("");
    }

    private boolean isValidQuantity(String quantityStr) {
        try {
            int quantity = Integer.parseInt(quantityStr);
            return quantity >= 0;  // Ensure quantity is non-negative
        } catch (NumberFormatException e) {
            // Parsing failed, it's not a valid integer
            return false;
        }

    }
}