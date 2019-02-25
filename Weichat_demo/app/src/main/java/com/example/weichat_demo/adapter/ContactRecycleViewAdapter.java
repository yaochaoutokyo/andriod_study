package com.example.weichat_demo.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.weichat_demo.R;
import com.example.weichat_demo.entity.ContactInfo;

import java.util.LinkedList;

/**
 * Created by yaochao on 2019/02/21
 */
public class ContactRecycleViewAdapter extends RecyclerView.Adapter<ContactRecycleViewAdapter.ContactRecycleViewHolder> {

	private LinkedList<ContactInfo> contactInfoList;

	public ContactRecycleViewAdapter(LinkedList<ContactInfo> contactInfoList) {
		this.contactInfoList = contactInfoList;
	}

	@NonNull
	@Override
	public ContactRecycleViewAdapter.ContactRecycleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
		Log.i("Contact", "onCreateViewHolder");
		View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.contact_info_item,
				viewGroup, false);
		return new ContactRecycleViewHolder(view);
	}

	@Override
	public void onBindViewHolder(@NonNull ContactRecycleViewAdapter.ContactRecycleViewHolder viewHolder, int i) {
		Log.i("Contact", "onBindViewHolder");
		viewHolder.imgContactIcon.setImageBitmap(contactInfoList.get(i).getContactBitmap());
		viewHolder.txtContactName.setText(contactInfoList.get(i).getContactName());
		viewHolder.txtContactPhone.setText(contactInfoList.get(i).getContactPhone());
		viewHolder.txtContactEmail.setText(contactInfoList.get(i).getContactEmail());
	}

	@Override
	public int getItemCount() {
		return contactInfoList.size();
	}

	public static class ContactRecycleViewHolder extends RecyclerView.ViewHolder {

		public final TextView txtContactName;
		public final TextView txtContactPhone;
		public final TextView txtContactEmail;
		public final ImageView imgContactIcon;

		public ContactRecycleViewHolder(@NonNull View itemView) {
			super(itemView);
			this.txtContactName = itemView.findViewById(R.id.contact_name);
			this.txtContactPhone = itemView.findViewById(R.id.contact_phone);
			this.txtContactEmail = itemView.findViewById(R.id.contact_email);
			this.imgContactIcon = itemView.findViewById(R.id.contact_image);
		}
	}
}
