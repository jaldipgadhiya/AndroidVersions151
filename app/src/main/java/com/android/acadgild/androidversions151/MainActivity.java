package com.android.acadgild.androidversions151;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.acadgild.androidversions151.adapter.CustomAdapter;
import com.android.acadgild.androidversions151.bean.DataModel;
import com.android.acadgild.androidversions151.bean.MyData;

import java.util.ArrayList;

/*
Main activity class
 */
public class MainActivity extends AppCompatActivity {

    //Declared adapter,layoutManager,recyclerView,data,myOnClickListener
    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static ArrayList<DataModel> data;
    public static View.OnClickListener myOnClickListener;
    private static ArrayList<Integer> removedItems;

    //On Create method
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Object of MyOnClickListener
        myOnClickListener = new MyOnClickListener(this);

        //get object of recyclerView
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
      //  recyclerView.setHasFixedSize(true);

        //Object of LinearLayoutManager
        layoutManager = new LinearLayoutManager(this);
        //setLayoutManager to  recyclerView
        recyclerView.setLayoutManager(layoutManager);
        //setItemAnimator to recyclerView
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //Object of ArrayList
        data = new ArrayList<DataModel>();
        //Loop to load data and add it into ArrayList
        for (int i = 0; i < MyData.nameArray.length; i++) {
            data.add(new DataModel(
                    MyData.nameArray[i],
                    MyData.id_[i]
            ));
        }

        Log.e("Data Size-","Main Activity-"+data.size());
        //Object of removedItems ArrayList
        removedItems = new ArrayList<Integer>();
        //Object of adapter
        adapter = new CustomAdapter(data);
        //Set Adapter to recyclerView
        recyclerView.setAdapter(adapter);
    }

    //Class MyOnClickListener and implement View.OnClickListener
    public static class MyOnClickListener implements View.OnClickListener {
        //Context object
        private final Context context;

        private MyOnClickListener(Context context) {
            this.context = context;
          }

        //Override onClick to removeItem
        @Override
        public void onClick(View v) {
            removeItem(v);
        }

        //Remove item method to remove element from RecyclerClass
        private void removeItem(View v) {
            int selectedItemPosition = recyclerView.getChildPosition(v);
            RecyclerView.ViewHolder viewHolder
                    = recyclerView.findViewHolderForPosition(selectedItemPosition);
            TextView textViewName
                    = (TextView) viewHolder.itemView.findViewById(R.id.textViewName);
            String selectedName = (String) textViewName.getText();
            int selectedItemId = -1;
            for (int i = 0; i < MyData.nameArray.length; i++) {
                if (selectedName.equals(MyData.nameArray[i])) {
                    selectedItemId = MyData.id_[i];
                }
            }
            //Add selected item to removedItems list
            removedItems.add(selectedItemId);
            //Remove data from list
            data.remove(selectedItemPosition);
            //Notify adapter for removed item
            adapter.notifyItemRemoved(selectedItemPosition);
        }


    }

    //Override onCreateOptionsMenu to inflate menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    //Override onOptionsItemSelected to inflate menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        //If add menu item is selected
        if (item.getItemId() == R.id.menuAdd ) {
            //check if any items to add
            if (removedItems.size() != 0) {
                //call addRemovedItemToList to add removed item to the list
                addRemovedItemToList();
            } else {
                Toast.makeText(this, "Nothing to add", Toast.LENGTH_SHORT).show();
            }
        }

        if (item.getItemId() == R.id.menuDelete ) {
            //removeItem(v);

        }
        return true;
    }

    //addRemovedItemToList method to add removed item to the lost on 4th position
    private void addRemovedItemToList() {
        int addItemAtListPosition = 3;
        data.add(addItemAtListPosition, new DataModel(
                MyData.nameArray[removedItems.get(0)],
                MyData.id_[removedItems.get(0)]
        ));
        adapter.notifyItemInserted(addItemAtListPosition);
        removedItems.remove(0);
    }

}
