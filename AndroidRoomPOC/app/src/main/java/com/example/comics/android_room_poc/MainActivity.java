package com.example.comics.android_room_poc;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.comics.android_room_poc.database.UserRepository;
import com.example.comics.android_room_poc.local.UserDataSource;
import com.example.comics.android_room_poc.local.UserDatabase;
import com.example.comics.android_room_poc.model.User;

import java.util.List;
import java.util.UUID;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private FloatingActionButton fab;

    List<User> userList;
    ArrayAdapter adapter;

    private CompositeDisposable compositeDisposable;
    private UserRepository userRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        compositeDisposable = new CompositeDisposable();


        listView = (ListView) findViewById(R.id.userListViewID);
        fab = (FloatingActionButton) findViewById(R.id.fab);

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, userList);
        registerForContextMenu(listView);
        listView.setAdapter(adapter);

//      Database
        UserDatabase userDatabase = UserDatabase.getmInstance(this);
        userRepository = UserRepository.getInstance(UserDataSource.getInstance(userDatabase.userDAO()));

//      Load all data from db
        loadData();


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Disposable disposable = Observable.create(new ObservableOnSubscribe<Object>() {
                    @Override
                    public void subscribe(ObservableEmitter<Object> e) throws Exception {
                        User user = new User("Kappa Dev", UUID.randomUUID().toString() + "@gmail.com");
                        userRepository.insertUser(user);
                        e.onComplete();
                    }
                })
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(new Consumer() {
                                       @Override
                                       public void accept(Object o) throws Exception {
                                           Toast.makeText(MainActivity.this, "User Added!", Toast.LENGTH_SHORT).show();
                                       }
                                   }, new Consumer<Throwable>() {
                                       @Override
                                       public void accept(Throwable throwable) throws Exception {
                                           Toast.makeText(MainActivity.this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                                       }

                                   }, new Action() {
                                       @Override
                                       public void run() throws Exception {
                                           loadData();
                                       }
                                   }
                        );
            }
        });
    }

    private void loadData() {

        Disposable disposable = userRepository.getAllUsers()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<List<User>>() {
                    @Override
                    public void accept(List<User> users) throws Exception {
                        onGetAllUserSuccess(users);
                    }

                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(MainActivity.this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        compositeDisposable.add(disposable);
    }

    private void onGetAllUserSuccess(List<User> users) {
        userList.clear();
        userList.addAll(users);
        adapter.notifyDataSetChanged();
    }
}
