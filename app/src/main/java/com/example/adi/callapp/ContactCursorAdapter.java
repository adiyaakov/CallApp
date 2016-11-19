package com.example.adi.callapp;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Adi on 19/11/2016.
 */
public class ContactCursorAdapter extends RecyclerView.Adapter<ContactCursorAdapter.ViewHolder1> {

    private CursorAdapter mCursorAdapter;
    private Context mContext;

    public ContactCursorAdapter (Context context, Cursor c) {

        this.mContext = context;
        this.mCursorAdapter = new CursorAdapter(mContext, c, 0) {

            @Override
            public View newView(Context context, Cursor cursor, ViewGroup parent) {
                // Inflate the view here
                View view = LayoutInflater.from(context).inflate(R.layout.item_contact, parent, false);
                return view;
            }



            @Override
            public void bindView(View view, Context context, Cursor cursor) {
                TextView name_TV = (TextView)view.findViewById(R.id.contact_name);
                TextView lastname_TV = (TextView)view.findViewById(R.id.contact_lastname);
                ImageView contactIV = (ImageView)view.findViewById(R.id.contactImg);


                String name = cursor.getString(cursor.getColumnIndex(DBopenHelper.COLUMN_NAME));
                String lastname = cursor.getString(cursor.getColumnIndex(DBopenHelper.COLUMN_LASTNAME));

                lastname_TV.setText(lastname);
                name_TV.setText(name);

            }
        };
    }

    public void updateData(){
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder1 onCreateViewHolder(ViewGroup parent, int viewType) {
        // Passing the inflater job to the cursor-adapter
        View v = mCursorAdapter.newView(mContext, mCursorAdapter.getCursor(), parent);
        return new ViewHolder1(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder1 holder, int position) {
        // Passing the binding operation to cursor loader
        mCursorAdapter.getCursor().moveToPosition(position); //EDITED: added this line as suggested in the comments below, thanks :)
        mCursorAdapter.bindView(holder.itemView, mContext, mCursorAdapter.getCursor());
    }

    @Override
    public int getItemCount() {
        return mCursorAdapter.getCount();
    }


    class ViewHolder1 extends RecyclerView.ViewHolder {
        TextView name_TV;
        ImageView contactIV;

        public ViewHolder1(View itemView) {
            super(itemView);
            name_TV = (TextView) itemView.findViewById(R.id.contact_name);
            contactIV = (ImageView) itemView.findViewById(R.id.contactImg);
        }
    }
}
