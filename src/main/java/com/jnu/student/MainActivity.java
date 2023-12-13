package com.jnu.student;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int EDIT_REQUEST_CODE = 1;

    private RecyclerView recyclerView;
    private RecycleViewBookAdapter adapter;
    private List<Book> bookList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycle_view_books);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        bookList = new ArrayList<>();
        // Add your initial book data here
        bookList.add(new Book("Book 1", R.drawable.book1_cover));
        bookList.add(new Book("Book 2", R.drawable.book2_cover));
        // Add more books as needed

        adapter = new RecycleViewBookAdapter(this, bookList);
        recyclerView.setAdapter(adapter);

        // Register the RecyclerView for context menu
        registerForContextMenu(recyclerView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_new:
                launchBookDetailsActivity(null, -1); // -1 indicates new book
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int position = adapter.getPosition();

        switch (item.getItemId()) {
            case R.id.menu_edit:
                launchBookDetailsActivity(bookList.get(position).getTitle(), position);
                return true;

            case R.id.menu_delete:
                bookList.remove(position);
                adapter.notifyItemRemoved(position);
                return true;

            default:
                return super.onContextItemSelected(item);
        }
    }

    private void launchBookDetailsActivity(String bookName, int position) {
        Intent intent = new Intent(this, BookDetailsActivity.class);
        intent.putExtra("bookName", bookName);
        intent.putExtra("position", position);
        startActivityForResult(intent, EDIT_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == EDIT_REQUEST_CODE && resultCode == RESULT_OK) {
            if (data != null) {
                String editedBookName = data.getStringExtra("editedBookName");
                int position = data.getIntExtra("position", -1);

                if (position == -1) {
                    // New book, add to the list and notify the adapter
                    bookList.add(new Book(editedBookName, R.drawable.book_no_name));
                    adapter.notifyItemInserted(bookList.size() - 1);
                } else {
                    // Existing book, update the data in the list and notify the adapter
                    bookList.get(position).setTitle(editedBookName);
                    adapter.notifyItemChanged(position);

                    // Inside YourActivity (assuming it's your MainActivity)
                    @Override
                    public boolean onCreateOptionsMenu(Menu menu) {
                        getMenuInflater().inflate(R.menu.main_menu, menu);
                        return true;
                    }

                    @Override
                    public boolean onOptionsItemSelected(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.menu_new:
                                launchBookDetailsActivity(null, -1); // -1 indicates new book
                                return true;

                            default:
                                return super.onOptionsItemSelected(item);
                        }
                    }

                    @Override
                    public boolean onContextItemSelected(MenuItem item) {
                        int position = adapter.getPosition();

                        switch (item.getItemId()) {
                            case R.id.menu_edit:
                                launchBookDetailsActivity(bookList.get(position).getTitle(), position);
                                return true;

                            case R.id.menu_delete:
                                bookList.remove(position);
                                adapter.notifyItemRemoved(position);
                                return true;

                            default:
                                return super.onContextItemSelected(item);
                        }
                    }

                    private void launchBookDetailsActivity(String bookName, int position) {
                        Intent intent = new Intent(this, BookDetailsActivity.class);
                        intent.putExtra("bookName", bookName);
                        intent.putExtra("position", position);
                        startActivityForResult(intent, EDIT_REQUEST_CODE);
                    }

                    @Override
                    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
                        super.onActivityResult(requestCode, resultCode, data);

                        if (requestCode == EDIT_REQUEST_CODE && resultCode == RESULT_OK) {
                            if (data != null) {
                                String editedBookName = data.getStringExtra("editedBookName");
                                int position = data.getIntExtra("position", -1);

                                if (position == -1) {
                                    // New book, add to the list and notify the adapter
                                    bookList.add(new Book(editedBookName, R.drawable.book_no_name));
                                    adapter.notifyItemInserted(bookList.size() - 1);
                                } else {
                                    // Existing book, update the data in the list and notify the adapter
                                    bookList.get(position).setTitle(editedBookName);
                                    adapter.notifyItemChanged(position);
                                }
                            }
                        }
                    }

                }
            }
        }
    }
}

