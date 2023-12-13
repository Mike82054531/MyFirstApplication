package com.jnu.student;

// YourActivity.java
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class YourActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        RecycleViewBookAdapter adapter = new RecycleViewBookAdapter(this, bookList);
        recyclerView.setAdapter(adapter);

        // Register the RecyclerView for context menu
        registerForContextMenu(recyclerView);
    }
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        // Get the position of the item that was clicked
        int position = adapter.getPosition();

        switch (item.getItemId()) {
            case R.id.menu_edit:
                // Launch the EditActivity with the selected book's data
                launchEditActivity(bookList.get(position), position);
                return true;

            case R.id.menu_delete:
                // Remove the selected item from the list and update the adapter
                bookList.remove(position);
                adapter.notifyItemRemoved(position);
                return true;

            default:
                return super.onContextItemSelected(item);
        }
    }

    private void launchEditActivity(Book book, int position) {
        // Launch the EditActivity, and expect a result
        Intent intent = new Intent(this, EditActivity.class);
        intent.putExtra("book", book);
        intent.putExtra("position", position);
        startActivityForResult(intent, EDIT_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == EDIT_REQUEST_CODE && resultCode == RESULT_OK) {
            // Handle the result from EditActivity
            if (data != null) {
                Book editedBook = data.getParcelableExtra("editedBook");
                int position = data.getIntExtra("position", -1);

                if (position != -1) {
                    // Update the book data in the list and notify the adapter
                    bookList.set(position, editedBook);
                    adapter.notifyItemChanged(position);
                    // Inside EditActivity
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("editedBook", editedBook);
                    resultIntent.putExtra("position", position);
                    setResult(RESULT_OK, resultIntent);
                    finish();

                }
            }
        }
    }

