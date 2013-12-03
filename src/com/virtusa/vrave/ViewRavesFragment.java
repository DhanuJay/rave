package com.virtusa.vrave;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ViewRavesFragment extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState){
		View view;
		view = inflater.inflate(R.layout.view_raves_fragment_layout, container,false);
		ListView l = (ListView) view.findViewById(R.id.listView1);
		String[] from = {"Good job","Awesome code","Nice help","Nice thinking","Great help","Good job","Awesome code","Nice help","Nice thinking","Great help","Good job","Awesome code","Nice help","Nice thinking","Great help"};
		ArrayAdapter adapter = new ArrayAdapter(getActivity(),R.layout.view_rave_list_item, R.id.listText, from);
		l.setAdapter(adapter);
		
		l.setOnItemClickListener(new OnItemClickListener() {
	        public void onItemClick(AdapterView<?> adapter, View view,
	                int position, long id) {
	        }
	    });		
		
		return view;
	}

}
