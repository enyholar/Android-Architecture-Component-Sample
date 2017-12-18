package com.gideondev.androidcomponenttut.Model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import java.util.Date;

/**
 * Created by ${ENNY} on 11/29/2017.
 */
@Entity
public class User {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String userName;
    //public String avatar_url;
    //public String gravatar_id;
    //public String url;
    //public String html_url;
    //public String followers_url;
    //public String following_url;
    //public String gists_url;
    //public String starred_url;
    //public String subscriptions_url;
    //public String organizations_url;
    //public String repos_url;
    //public String events_url;
    //public String received_events_url;
    //public String type;
    //public String name;
    //public String blog;
    //public String location;
    //public String email;
    //public int public_repos;
    //public int public_gists;
    //public int followers;
    //public int following;
    //public Date created_at;
    //public Date updated_at;

    public User(String userName) {
        this.userName = userName;
    }

}
