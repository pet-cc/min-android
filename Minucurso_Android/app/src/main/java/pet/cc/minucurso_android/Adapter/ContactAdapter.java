package pet.cc.minucurso_android.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import pet.cc.minucurso_android.ContactActivity;
import pet.cc.minucurso_android.MainActivity;
import pet.cc.minucurso_android.Model.Contact;
import pet.cc.minucurso_android.R;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {

    private ContactActivity activity;

    private Context context;

    private List<Contact> contacts;
    private List<Contact> filteredContacts;

    private boolean isSelectable;

    public ContactAdapter(ContactActivity activity, List<Contact> contacts) {
        this.activity         = activity;
        this.context          = activity.getBaseContext();
        this.contacts         = contacts;
        this.filteredContacts = new ArrayList<>(contacts);
        this.isSelectable     = false;
    }

    @NonNull
    @Override
    // Método chamado quando o ViewHolder é inicializado
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.itemlist_contacts, parent, false);

        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {

        Contact contact = filteredContacts.get(position);
        if(contact != null) {
            holder.bind(contact);
        }
    }

    @Override
    public int getItemCount() {
        // Número de itens presente nos dados
        return filteredContacts.size();
    }

    public List<Contact> getSelectedContacts() {
        List<Contact> selectedContacts = new ArrayList<>();

        for(Contact contact : contacts) {
            if(contact.isChecked()) {
                selectedContacts.add(contact);
            }
        }

        return selectedContacts;
    }

    public void diselectAllContacts() {
        for(Contact contact : contacts) {
            if(contact.isChecked()) {
                contact.setChecked(false);
            }
        }

        notifyDataSetChanged();
    }

    class ContactViewHolder extends RecyclerView.ViewHolder {

        private View view;

        private ImageView ivPicture;

        private TextView tvName;
        private TextView tvDescription;

        private CardView cvChecked;

        ContactViewHolder(@NonNull View itemView) {
            super(itemView);

            view = itemView;

            ivPicture = itemView.findViewById(R.id.imageview_contact);
            cvChecked = itemView.findViewById(R.id.imageview_checked);

            tvName        = itemView.findViewById(R.id.textview_contact_name);
            tvDescription = itemView.findViewById(R.id.textview_contact_description);
        }

        void bind(final Contact contact) {
            cvChecked.setVisibility(contact.isChecked() ? View.VISIBLE : View.GONE);
            view.setBackgroundColor(contact.isChecked() ? ContextCompat.getColor(context, R.color.bgSelected) : Color.WHITE);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(isSelectable) {
                        contact.setChecked(!contact.isChecked());
                        cvChecked.setVisibility(contact.isChecked() ? View.VISIBLE : View.GONE);
                        view.setBackgroundColor(contact.isChecked() ? ContextCompat.getColor(context, R.color.bgSelected) : Color.WHITE);
                        activity.setDefaultToolbar(false);
                        if(getSelectedContacts().size() == 0) {
                            isSelectable = !isSelectable;
                            activity.setDefaultToolbar(true);
                        }
                    }
                    else {
                        Toast.makeText(context, "Redirect to " + contact.getName() + "'s profile!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            view.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if(!isSelectable) {
                        isSelectable = true;
                    }

                    return false;
                }
            });

            tvName.setText(contact.getName());
            tvDescription.setText(contact.getDescription());
        }
    }

    private Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Contact> filteredContacts = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredContacts.addAll(contacts);
            }
            else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (Contact contact : contacts) {
                    if (contact.getName().toLowerCase().contains(filterPattern)) {
                        filteredContacts.add(contact);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values        = filteredContacts;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            filteredContacts.clear();
            filteredContacts.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

    public Filter getFilter() {
        return filter;
    }
}
